package com.phantom.provider_facility_association_service.application.service;

import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.provider_facility_association_service.application.classexception.HospitalFacilityException;
import com.phantom.provider_facility_association_service.application.entity.HospitalFacilityMapping;
import com.phantom.provider_facility_association_service.application.repository.HospitalFacilityRepository;
import com.phantom.provider_facility_association_service.application.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class HospitalFacilityServiceImpl implements IHospitalFacilityService{

    private final HospitalFacilityRepository hospitalFacilityRepository;

    @Override
    public String registerHospitalFacility(HospitalFacilityRegisterDto hospitalFacilityRegisterDto) throws HospitalFacilityException {

        List<HospitalFacilityMapping> hospitalFacilityMappings = new ArrayList<>();
        List<Long> existingFacilityList = hospitalFacilityRepository.findFacilityIdsByHospitalId(hospitalFacilityRegisterDto.getHospitalId());
        List<Long> newFacilityIds = hospitalFacilityRegisterDto.getFacilityIdList();
        Set<Long> existingFacilitySet = new HashSet<>(existingFacilityList);

        Long hospitalId = hospitalFacilityRegisterDto.getHospitalId();
        for (Long facilityId : newFacilityIds) {
            if (existingFacilitySet.contains(facilityId)) {
                throw new HospitalFacilityException("Hospital is already associated with facility id "+facilityId,HttpStatus.ALREADY_REPORTED);
            }
            HospitalFacilityMapping hospitalFacilityMapping = DtoMapper.hospitalFacilityMapper(hospitalId, facilityId);
            hospitalFacilityMappings.add(hospitalFacilityMapping);
        }
        hospitalFacilityRepository.saveAll(hospitalFacilityMappings);
        return "Facility has been associated with hospital successfully";
    }
}
