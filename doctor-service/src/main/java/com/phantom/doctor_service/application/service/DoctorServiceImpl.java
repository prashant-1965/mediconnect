package com.phantom.doctor_service.application.service;

import com.phantom.doctor_service.application.classexception.DoctorException;
import com.phantom.doctor_service.application.entity.Doctor;
import com.phantom.doctor_service.application.feign.FacilityFeign;
import com.phantom.doctor_service.application.feign.HospitalFeign;
import com.phantom.doctor_service.application.feign.IdentityFeign;
import com.phantom.doctor_service.application.feign.ProviderFacilityAssociationFeign;
import com.phantom.doctor_service.application.repository.DoctorRepository;
import com.phantom.doctor_service.application.util.DtoMapper;
import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.DoctorFacilityRegisterDto;
import com.phantom.dto.request.DoctorRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.projection.DoctorStatusProjection;
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
public class DoctorServiceImpl implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalFeign hospitalFeign;
    private final ProviderFacilityAssociationFeign providerFacilityAssociationFeign;
    private final FacilityFeign facilityFeign;
    private final IdentityFeign identityFeign;

    @Override
    @Transactional
    public String registerDoctor(DoctorRegisterDto doctorRegisterDto) throws DoctorException {
        Doctor doctor = DtoMapper.doctorMapper(doctorRegisterDto);
        Long appUserId;
        List<Long> facilityIds;
        try {
            hospitalFeign.findHospitalByHospitalId(doctor.getHospitalId());
            facilityIds = facilityFeign.findAllFacilityIdByName(doctorRegisterDto.getFacilityNames());
            DoctorFacilityRegisterDto doctorFacilityRegisterDto = DtoMapper.doctorFacilityRegisterDto(doctor.getDoctorId(), facilityIds);
            String facilityMessage = providerFacilityAssociationFeign.registerDoctorFacility(doctorFacilityRegisterDto);
            log.info(facilityMessage);
            AppUserRegisterDto appUserRegisterDto = DtoMapper.doctorAppUserMapper(doctorRegisterDto);
            String appUserMessage = identityFeign.userSignUp(appUserRegisterDto).getBody();
            appUserId = UIDExtractor.appUserIdExtractor(appUserMessage);
            doctor.setAppUserId(appUserId);
            log.info(appUserMessage);
        }catch (FeignException fe){
            throw new DoctorException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        log.info("Doctor registration has been successfully forwarded to admin");
        doctorRepository.save(doctor);
        return "Request for "+doctorRegisterDto.getDoctorName()+" registration has been sent and we will notify you shortly";
    }

    @Override
    public List<DoctorStatusProjection> findPendingDoctors(String status) throws DoctorException {
        List<IdentityStatusProjection> identityStatusProjections;
        try {
            identityStatusProjections = identityFeign.findPendingUsers(UserRole.DOCTOR, UserStatus.valueOf(status.toUpperCase()));
        }catch (FeignException fe){
            throw new DoctorException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        if(identityStatusProjections.isEmpty()){
            throw new DoctorException("No pending doctors found", HttpStatus.NOT_FOUND);
        }
        List<DoctorStatusProjection> doctorStatusProjections = new ArrayList<>();
        for(IdentityStatusProjection identityProjection: identityStatusProjections){
            Optional<Doctor> doctor = doctorRepository.findDoctorByAppUserId(identityProjection.getAppUserId());
            if(doctor.isEmpty()){
                throw new DoctorException("Doctor not found with appUserId"+identityProjection.getAppUserId(),HttpStatus.NOT_FOUND);
            }
            DoctorStatusProjection doctorStatusProjection = DtoMapper.DoctorIdentityMapper(doctor.get(),identityProjection);
            doctorStatusProjections.add(doctorStatusProjection);
        }

        return doctorStatusProjections;
    }
}
