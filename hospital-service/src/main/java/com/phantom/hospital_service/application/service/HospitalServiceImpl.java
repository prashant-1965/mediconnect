package com.phantom.hospital_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.hospital_service.application.classexception.HospitalException;
import com.phantom.hospital_service.application.entity.Hospital;
import com.phantom.hospital_service.application.feign.FacilityFeign;
import com.phantom.hospital_service.application.feign.IdentityFeign;
import com.phantom.hospital_service.application.feign.ProviderFacilityAssociationFeign;
import com.phantom.hospital_service.application.repository.HospitalRepository;
import com.phantom.hospital_service.application.util.DtoMapper;
import com.phantom.projection.HospitalStatusProjection;
import com.phantom.projection.IdentityStatusProjection;
import com.phantom.util.RatingCalculator;
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
public class HospitalServiceImpl implements IHospitalService{

    private final HospitalRepository hospitalRepository;
    private final FacilityFeign facilityFeign;
    private final ProviderFacilityAssociationFeign providerFacilityAssociationFeign;
    private final IdentityFeign identityFeign;

    @Transactional
    @Override
    public String hospitalRegistrationRequest(HospitalRegisterDto hospitalRegisterDto) throws HospitalException {
        System.out.println("Before dto operations: "+hospitalRegisterDto.getHospitalAddress());
        Hospital hospital = DtoMapper.hospitalMapper(hospitalRegisterDto);
        System.out.println("After dto operations: "+hospital.getHospitalAddress());
        Long appUserId;
        List<FacilityRegisterDto> facilityRegisterDtos = new ArrayList<>();
        for(String facilityDetails:hospitalRegisterDto.getFacilitiesWithDescription().keySet()){
            FacilityRegisterDto facilityRegisterDto = DtoMapper.hospitalFacilityMapper(facilityDetails,hospitalRegisterDto.getFacilitiesWithDescription().get(facilityDetails));
            facilityRegisterDtos.add(facilityRegisterDto);
        }
        String facilityMessage;
        String appUserMessage;
        try {
            List<Long> facilityIdList = facilityFeign.registerFacility(facilityRegisterDtos);
            HospitalFacilityRegisterDto hospitalFacilityRegisterDto = DtoMapper.hospitalFacilityMapper(hospital.getHospitalId(),facilityIdList);
            AppUserRegisterDto appUserRegisterDto = DtoMapper.hospitalAppUserMapper(hospitalRegisterDto);
            appUserMessage = identityFeign.userSignUp(appUserRegisterDto).getBody();
            appUserId = UIDExtractor.appUserIdExtractor(appUserMessage);
            hospital.setAppUserId(appUserId);
            log.info(appUserMessage);
            facilityMessage = providerFacilityAssociationFeign.registerHospitalFacility(hospitalFacilityRegisterDto);
            log.info(facilityMessage);

        }catch (FeignException fe){
            throw new HospitalException(fe.contentUTF8(),HttpStatus.valueOf(fe.status()));
        }
        hospitalRepository.save(hospital);
        return "Request for "+hospitalRegisterDto.getHospitalName()+" registration has been sent and we will notify you shortly";
    }

    @Override
    public boolean findHospitalByHospitalId(Long hospitalId) throws HospitalException{
        Optional<Hospital> hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if(hospital.isEmpty()){
            throw new HospitalException("Hospital not found",HttpStatus.NOT_FOUND);
        }
        boolean hospitalStatus = identityFeign.checkUserStatusByAppUserId(hospital.get().getAppUserId(), UserStatus.ACTIVE);
        if(!hospitalStatus){
            throw new HospitalException("Hospital status is INACTIVE for hospitalId: "+hospitalId,HttpStatus.FORBIDDEN);
        }
        return true;
    }

    @Override
    public List<HospitalStatusProjection> findPendingHospitals(String status) throws HospitalException{
        List<IdentityStatusProjection> identityStatusProjections;
        try {
            identityStatusProjections = identityFeign.findPendingUsers(UserRole.HOSPITAL, UserStatus.valueOf(status.toUpperCase()));
        }catch (FeignException fe){
            throw new HospitalException(fe.contentUTF8(),HttpStatus.valueOf(fe.status()));
        }
        if(identityStatusProjections.isEmpty()){
            throw new HospitalException("No pending hospitals found",HttpStatus.NOT_FOUND);
        }
        List<HospitalStatusProjection> hospitalStatusProjections = new ArrayList<>();
        for(IdentityStatusProjection identityProjection: identityStatusProjections){
            Optional<Hospital> hospital = hospitalRepository.findHospitalByAppUserId(identityProjection.getAppUserId());
            if(hospital.isEmpty()){
                throw new HospitalException("Hospital not found with appUserId"+identityProjection.getAppUserId(),HttpStatus.NOT_FOUND);
            }
            HospitalStatusProjection hospitalStatusProjection = DtoMapper.HospitalIdentityMapper(hospital.get(),identityProjection);
            hospitalStatusProjections.add(hospitalStatusProjection);
        }

        return hospitalStatusProjections;
    }

    @Override
    @Transactional
    public boolean updateHospitalRating(Long hospitalId, Double newRating, int totalReviews) throws HospitalException {
        Optional<Hospital> hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if(hospital.isEmpty()){
            throw new HospitalException("Hospital not found with hospitalId"+hospitalId,HttpStatus.NOT_FOUND);
        }
        double oldRating = hospital.get().getHospitalRating();
        double updatedRating = RatingCalculator.updateRating(oldRating, newRating, totalReviews);
        hospital.get().setHospitalRating(updatedRating);
        return true;
    }

    @Override
    public String updateHospitalStatus(Long hospitalId, String status) {
        Optional<Hospital> hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if(hospital.isEmpty()){
            return "Hospital not found with hospitalId"+hospitalId;
        }
        String response;
        try {
            response = identityFeign.updateUserStatus(hospital.get().getAppUserId(), status);
        }catch (FeignException fe) {
            throw new HospitalException(fe.contentUTF8(),HttpStatus.valueOf(fe.status()));
        }
        return "Hospital "+response;
    }
}
