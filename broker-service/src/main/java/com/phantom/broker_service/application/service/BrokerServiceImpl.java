package com.phantom.broker_service.application.service;

import com.phantom.broker_service.application.classexception.BrokerException;
import com.phantom.broker_service.application.entity.Broker;
import com.phantom.broker_service.application.feign.HospitalFeign;
import com.phantom.broker_service.application.feign.IdentityFeign;
import com.phantom.broker_service.application.repository.BrokerRepository;
import com.phantom.broker_service.application.util.DtoMapper;
import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.BrokerRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.IdentityStatusProjection;
import com.phantom.util.UIDExtractor;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrokerServiceImpl implements IBrokerService {

    private final BrokerRepository brokerRepository;
    private final HospitalFeign hospitalFeign;
    private final IdentityFeign identityFeign;

    @Override
    @Transactional
    public String registerBroker(BrokerRegisterDto brokerRegisterDto) throws BrokerException {
        Broker broker = DtoMapper.brokerMapper(brokerRegisterDto);
        Long appUserId;
        try {
            hospitalFeign.findHospitalByHospitalId(broker.getHospitalId());
            AppUserRegisterDto appUserRegisterDto = DtoMapper.brokerAppUserMapper(brokerRegisterDto);
            String appUserMessage = identityFeign.userSignUp(appUserRegisterDto).getBody();
            appUserId = UIDExtractor.appUserIdExtractor(appUserMessage);
            broker.setAppUserId(appUserId);
            log.info(appUserMessage);
        }catch (FeignException fe){
            throw new BrokerException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        log.info("Broker registration has been successfully forwarded to admin");
        brokerRepository.save(broker);
        return "Request for "+brokerRegisterDto.getBrokerName()+" registration has been sent and we will notify you shortly";
    }

    @Override
    public List<BrokerStatusProjection> findPendingBrokers(String status) throws BrokerException {
        List<IdentityStatusProjection> identityStatusProjections;
        try {
            identityStatusProjections = identityFeign.findPendingUsers(UserRole.BROKER, UserStatus.valueOf(status.toUpperCase()));
        }catch (FeignException fe){
            throw new BrokerException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        if(identityStatusProjections.isEmpty()){
            throw new BrokerException("No pending brokers found", HttpStatus.NOT_FOUND);
        }
        List<BrokerStatusProjection> brokerStatusProjections = new ArrayList<>();
        for(IdentityStatusProjection identityProjection: identityStatusProjections){
            Optional<Broker> broker = brokerRepository.findBrokerByAppUserId(identityProjection.getAppUserId());
            if(broker.isEmpty()){
                throw new BrokerException("Broker not found with appUserId"+identityProjection.getAppUserId(),HttpStatus.NOT_FOUND);
            }
            BrokerStatusProjection brokerStatusProjection = DtoMapper.BrokerIdentityMapper(broker.get(),identityProjection);
            brokerStatusProjections.add(brokerStatusProjection);
        }

        return brokerStatusProjections;
    }
}
