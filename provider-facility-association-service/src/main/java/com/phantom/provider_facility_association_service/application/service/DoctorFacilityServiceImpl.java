package com.phantom.provider_facility_association_service.application.service;

import com.phantom.dto.request.DoctorFacilityRegisterDto;
import com.phantom.provider_facility_association_service.application.classexception.DoctorFacilityException;
import com.phantom.provider_facility_association_service.application.entity.DoctorFacilityMapping;
import com.phantom.provider_facility_association_service.application.repository.DoctorFacilityRepository;
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
public class DoctorFacilityServiceImpl implements IDoctorFacilityService{

    private final DoctorFacilityRepository doctorFacilityRepository;

    @Override
    public String registerDoctorFacility(DoctorFacilityRegisterDto doctorFacilityRegisterDto) throws DoctorFacilityException {

        List<DoctorFacilityMapping> doctorFacilityMappings = new ArrayList<>();
        List<Long> existingFacilityList = doctorFacilityRepository.findFacilityIdsByDoctorId(doctorFacilityRegisterDto.getDoctorId());
        List<Long> newFacilityIds = doctorFacilityRegisterDto.getFacilityIdList();
        Set<Long> existingFacilitySet = new HashSet<>(existingFacilityList);

        Long doctorId = doctorFacilityRegisterDto.getDoctorId();
        for (Long facilityId : newFacilityIds) {
            if (existingFacilitySet.contains(facilityId)) {
                throw new DoctorFacilityException("Doctor is already associated with facility id "+facilityId,HttpStatus.ALREADY_REPORTED);
            }
            DoctorFacilityMapping doctorFacilityMapping = DtoMapper.doctorFacilityMapper(doctorId, facilityId);
            doctorFacilityMappings.add(doctorFacilityMapping);
        }
        doctorFacilityRepository.saveAll(doctorFacilityMappings);
        return "Facility has been associated with doctor successfully";
    }
}
