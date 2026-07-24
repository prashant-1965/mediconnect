package com.phantom.hospital_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.hospital_service.application.classexception.HospitalException;
import com.phantom.hospital_service.application.entity.Hospital;
import com.phantom.hospital_service.application.feign.FacilityFeign;
import com.phantom.hospital_service.application.feign.IdentityFeign;
import com.phantom.hospital_service.application.feign.ProviderFacilityAssociationFeign;
import com.phantom.hospital_service.application.repository.HospitalRepository;
import com.phantom.hospital_service.application.util.DtoMapper;
import com.phantom.util.UIDExtractor;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements IHospitalService{

    private final HospitalRepository hospitalRepository;
    private final FacilityFeign facilityFeign;
    private final ProviderFacilityAssociationFeign providerFacilityAssociationFeign;
    private final IdentityFeign identityFeign;

    @Override
    public String hospitalRegistrationRequest(HospitalRegisterDto hospitalRegisterDto) throws HospitalException {
        String hospitalEmail = hospitalRegisterDto.getHospitalEmail();
        boolean emailExists = hospitalRepository.findHospitalByEmail(hospitalEmail);
        if(emailExists){
            log.error("Hospital has already registered with email {}",hospitalEmail);
            throw new HospitalException("Hospital has already registered with email"+hospitalEmail, HttpStatus.ALREADY_REPORTED);
        }
        Hospital hospital = DtoMapper.hospitalMapper(hospitalRegisterDto);
        Long appUserId;
        List<FacilityRegisterDto> facilityRegisterDtos = new ArrayList<>();
        for(String facilityDetails:hospitalRegisterDto.getFacilitiesWithDescription().keySet()){
            FacilityRegisterDto facilityRegisterDto = DtoMapper.hospitalFacilityMapper(facilityDetails,hospitalRegisterDto.getFacilitiesWithDescription().get(facilityDetails));
            facilityRegisterDtos.add(facilityRegisterDto);
        }
        List<Long> facilityIdList = facilityFeign.registerFacility(facilityRegisterDtos);
        HospitalFacilityRegisterDto hospitalFacilityRegisterDto = DtoMapper.hospitalFacilityMapper(hospital.getHospitalId(),facilityIdList);
        String facilityMessage;
        String appUserMessage;
        try {
            facilityMessage = providerFacilityAssociationFeign.registerHospitalFacility(hospitalFacilityRegisterDto);
            log.info(facilityMessage);
            AppUserRegisterDto appUserRegisterDto = DtoMapper.hospitalAppUserMapper(hospitalRegisterDto);
            appUserMessage = identityFeign.userSignUp(appUserRegisterDto).getBody();
            appUserId = UIDExtractor.appUserIdExtractor(appUserMessage);
            hospital.setAppUserId(appUserId);
            log.info(appUserMessage);

        }catch (FeignException fe){
            throw new HospitalException(fe.contentUTF8(),HttpStatus.valueOf(fe.status()));
        }
        hospitalRepository.save(hospital);
        return "Request for "+hospitalRegisterDto.getHospitalName()+" registration has been sent and we will notify you shortly";
    }

    @Override
    public boolean findHospitalByHospitalId(Long hospitalId) {
        return hospitalRepository.findHospitalByHospitalId(hospitalId);
    }
}
