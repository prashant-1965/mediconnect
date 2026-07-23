package com.phantom.doctor_service.application.service;

import com.phantom.doctor_service.application.classexception.DoctorException;
import com.phantom.doctor_service.application.entity.Doctor;
import com.phantom.doctor_service.application.feign.FacilityFeign;
import com.phantom.doctor_service.application.feign.HospitalFeign;
import com.phantom.doctor_service.application.feign.LocationFeign;
import com.phantom.doctor_service.application.feign.ProviderFacilityAssociationFeign;
import com.phantom.doctor_service.application.repository.DoctorRepository;
import com.phantom.doctor_service.application.util.DtoMapper;
import com.phantom.dto.request.DoctorFacilityRegisterDto;
import com.phantom.dto.request.DoctorRegisterDto;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalFeign hospitalFeign;
    private final ProviderFacilityAssociationFeign providerFacilityAssociationFeign;
    private final FacilityFeign facilityFeign;
    private final LocationFeign locationFeign;

    @Override
    @Transactional
    public String registerDoctor(DoctorRegisterDto doctorRegisterDto) throws DoctorException {
        Long hospitalId = doctorRegisterDto.getHospitalId();
        boolean hospitalExists = hospitalFeign.findHospitalByHospitalId(hospitalId);
        if(!hospitalExists){
            throw new DoctorException("Hospital not found with id "+hospitalId, HttpStatus.NOT_FOUND);
        }
        Long countryId;
        Long stateId;
        try {
            countryId = locationFeign.findCountryByName(doctorRegisterDto.getCountryName());
            stateId = locationFeign.findStateByName(doctorRegisterDto.getStateName());
        }catch (FeignException fe){
            throw new DoctorException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        Doctor doctor = DtoMapper.doctorMapper(doctorRegisterDto);
        doctor.setCountryId(countryId);
        doctor.setStateId(stateId);
        List<Long> facilityIds;
        try {
            facilityIds = facilityFeign.findAllFacilityIdByName(doctorRegisterDto.getFacilityNames());
            DoctorFacilityRegisterDto doctorFacilityRegisterDto = DtoMapper.doctorFacilityRegisterDto(doctor.getDoctorId(), facilityIds);
            String message = providerFacilityAssociationFeign.registerDoctorFacility(doctorFacilityRegisterDto);
            log.info(message);
        }catch (FeignException fe){
            throw new DoctorException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        log.info("Doctor registration has been successfully forwarded to admin");
        doctorRepository.save(doctor);
        return "Request for "+doctorRegisterDto.getDoctorName()+" registration has been sent and we will notify you shortly";
    }
}
