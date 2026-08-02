package com.phantom.facility_service.application.service;

import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.response.FacilityListProjection;
import com.phantom.facility_service.application.classexception.FacilityException;
import com.phantom.facility_service.application.entity.Facility;
import com.phantom.facility_service.application.repository.FacilityRepository;
import com.phantom.facility_service.application.util.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FacilityServiceImpl implements IFacilityService{

    private final FacilityRepository facilityRepository;


    @Transactional
    @Override
    public List<Long> registerFacility(List<FacilityRegisterDto> facilityRegisterDtos) {

        Map<String, String> facilityMap = new LinkedHashMap<>();
        for (FacilityRegisterDto dto : facilityRegisterDtos) {
            facilityMap.putIfAbsent(dto.getFacilityName().toLowerCase(), dto.getFacilityDescription());
        }
        List<Facility> existingFacilities = facilityRepository.findAllFacilityByName(
                new ArrayList<>(facilityMap.keySet())
        );
        List<Long> facilityIds = new ArrayList<>();
        Set<String> existingFacilityNames = new HashSet<>();
        for (Facility facility : existingFacilities) {
            existingFacilityNames.add(facility.getFacilityName());
            facilityIds.add(facility.getFacilityId());
        }
        List<Facility> newFacilities = new ArrayList<>();

        for (Map.Entry<String, String> entry : facilityMap.entrySet()) {

            if (!existingFacilityNames.contains(entry.getKey())) {

                Facility facility = DtoMapper.facilityMapper(
                        entry.getKey(),
                        entry.getValue()
                );

                newFacilities.add(facility);
            }
        }
        if (!newFacilities.isEmpty()) {
            facilityRepository.saveAll(newFacilities);
            for (Facility facility : newFacilities) {
                facilityIds.add(facility.getFacilityId());
            }
        }
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

}
