package com.phantom.facility_service.application.util;

import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.facility_service.application.entity.Facility;
import com.phantom.util.UIDGenerator;

public class DtoMapper {
    public static Facility facilityMapper(FacilityRegisterDto facilityRegisterDto){
        Facility facility = new Facility();
        facility.setFacilityName(facilityRegisterDto.getFacilityName().toLowerCase());
        facility.setFacilityDescription(facilityRegisterDto.getFacilityDescription());
        long facilityId = UIDGenerator.uidGenerator();
        facility.setFacilityId(facilityId);
        return facility;
    }
}
