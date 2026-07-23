package com.phantom.facility_service.application.service;

import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.response.FacilityListProjection;
import com.phantom.facility_service.application.classexception.FacilityException;
import com.phantom.facility_service.application.entity.Facility;
import com.phantom.facility_service.application.repository.FacilityRepository;
import com.phantom.facility_service.application.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FacilityServiceImpl implements IFacilityService{

    private final FacilityRepository facilityRepository;


    @Override
//    @Caching(evict={
//            @CacheEvict(value = "FacilityListProjection",allEntries = true),
//            @CacheEvict(value = "facilityListByHospitalName",allEntries = true),
//            @CacheEvict(value = "MedicalFacilities",allEntries = true)
//    })
    public List<Long> registerFacility(List<FacilityRegisterDto> facilityRegisterDtos) {
        List<Long> facilityIds = new ArrayList<>();
        Set<String> facilityNames = new HashSet<>();
        List<Facility> alreadyRegisteredFacilities = facilityRepository.findAllFacilityByName(facilityRegisterDtos.stream()
                .map(FacilityRegisterDto::getFacilityName).toList());
        for(Facility facility:alreadyRegisteredFacilities){
            facilityIds.add(facility.getFacilityId());
            facilityNames.add(facility.getFacilityName());
        }
        List<FacilityRegisterDto> newFacilityRegistrationDto = facilityRegisterDtos.stream()
                .filter(dto -> !facilityNames.contains(dto.getFacilityName())).toList();
        List<Facility> newFacilities = new ArrayList<>();
        for(FacilityRegisterDto facilityRegisterDto:newFacilityRegistrationDto){
            Facility facility = DtoMapper.facilityMapper(facilityRegisterDto);
            newFacilities.add(facility);
            facilityIds.add(facility.getFacilityId());
        }
        facilityRepository.saveAll(newFacilities);
        return facilityIds;
    }

    @Override
//    @Cacheable(value = "FacilityListProjection")
    public List<FacilityListProjection> findAllAvailableFacilities() throws FacilityException {
        List<FacilityListProjection> getfacilitiesList = facilityRepository.getAllAvailableFacility();
        if (getfacilitiesList.isEmpty()){
            throw new FacilityException("No facility Available", HttpStatus.NOT_FOUND);
        }
        return getfacilitiesList.stream().sorted().toList();
    }

    @Override
    public List<Long> findAllFacilityIdByName(List<String> facilityNames) throws FacilityException {
        List<Long> facilityIds = facilityRepository.findAllFacilityIdByName(facilityNames);
        if(facilityNames.size()!=facilityIds.size()){
            throw new FacilityException("Some Facilities are not registered", HttpStatus.NOT_FOUND);
        }

        return facilityIds;
    }

//    @Override
//    @Cacheable(value = "doctorListByDoctorEmail",key = "#doctorEmail",condition = "#doctorEmail!=null")
//    public List<String> findFacilityByDoctorEmail(String doctorEmail) throws FacilityException {
//        List<String> doctorList = facilityRepository.findFacilityByDoctorEmail(doctorEmail);
//        if(doctorList.isEmpty()){
//            throw new FacilityException(doctorEmail +" is not providing any Facility!",HttpStatus.NOT_FOUND);
//        }
//        return doctorList;
//    }

//    @Override
//    @Cacheable(value = "facilityListByHospitalName",key = "#hospitalName", unless = "#result==null")
//    public List<String> findFacilityByHospitalName(String hospitalName) throws FacilityException {
//        List<String> facilityList = facilityRepository.getFacilityByHospitalName(hospitalName);
//        if (facilityList.isEmpty()){
//            throw new FacilityException(hospitalName+" doesn't provide any facility",HttpStatus.NOT_FOUND);
//        }
//        return facilityList;
//    }

    @Cacheable(value = "MedicalFacilities",key = "#facilityName", unless = "#result==null")
    public Optional<Facility> findByFacilityName(String facilityName) {
        return facilityRepository.findByFacilityName(facilityName);
    }
}
