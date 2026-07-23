package com.phantom.hospital_service.application.service;

import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.hospital_service.application.classexception.HospitalException;
import com.phantom.hospital_service.application.entity.Hospital;
import com.phantom.hospital_service.application.feign.FacilityFeign;
import com.phantom.hospital_service.application.feign.LocationFeign;
import com.phantom.hospital_service.application.feign.ProviderFacilityAssociationFeign;
import com.phantom.hospital_service.application.repository.HospitalRepository;
import com.phantom.hospital_service.application.util.DtoMapper;
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

    private final LocationFeign locationFeign;
    private final HospitalRepository hospitalRepository;
    private final FacilityFeign facilityFeign;
    private final ProviderFacilityAssociationFeign providerFacilityAssociationFeign;

    @Override
    public String hospitalRegistrationRequest(HospitalRegisterDto hospitalRegisterDto) throws HospitalException {
        String hospitalEmail = hospitalRegisterDto.getHospitalEmail();
        boolean emailExists = hospitalRepository.findHospitalByEmail(hospitalEmail);
        if(emailExists){
            log.error("Hospital has already registered with email {}",hospitalEmail);
            throw new HospitalException("Hospital has already registered with email"+hospitalEmail, HttpStatus.ALREADY_REPORTED);
        }
        Hospital hospital = DtoMapper.hospitalMapper(hospitalRegisterDto);
        Long countryId;
        Long stateId;
        try {
            countryId = locationFeign.findCountryByName(hospitalRegisterDto.getCountryName());
            stateId = locationFeign.findStateByName(hospitalRegisterDto.getStateName());
        }catch (FeignException e){
            throw new HospitalException(e.contentUTF8(),HttpStatus.valueOf(e.status()));
        }
        hospital.setCountryId(countryId);
        hospital.setStateId(stateId);
        List<FacilityRegisterDto> facilityRegisterDtos = new ArrayList<>();
        for(String facilityDetails:hospitalRegisterDto.getFacilitiesWithDescription().keySet()){
            FacilityRegisterDto facilityRegisterDto = DtoMapper.hospitalFacilityMapper(facilityDetails,hospitalRegisterDto.getFacilitiesWithDescription().get(facilityDetails));
            facilityRegisterDtos.add(facilityRegisterDto);
        }
        List<Long> facilityIdList = facilityFeign.registerFacility(facilityRegisterDtos);
        HospitalFacilityRegisterDto hospitalFacilityRegisterDto = DtoMapper.hospitalFacilityMapper(hospital.getHospitalId(),facilityIdList);
        String message;
        try {
            message = providerFacilityAssociationFeign.registerHospitalFacility(hospitalFacilityRegisterDto);
        }catch (FeignException fe){
            throw new HospitalException(fe.contentUTF8(),HttpStatus.valueOf(fe.status()));
        }
        log.info(message);
        hospitalRepository.save(hospital);
        return "Request for "+hospitalRegisterDto.getHospitalName()+" registration has been sent and we will notify you shortly";
    }

    @Override
    public boolean findHospitalByHospitalId(Long hospitalId) {
        return hospitalRepository.findHospitalByHospitalId(hospitalId);
    }
}
