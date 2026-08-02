package com.phantom.facility_service.application.util;


import com.phantom.facility_service.application.entity.Facility;
import com.phantom.util.UIDGenerator;

public class DtoMapper {
    public static Facility facilityMapper(String facilityName, String facilityDescription){
        Facility facility = new Facility();
        facility.setFacilityName(facilityName.toLowerCase());
        facility.setFacilityDescription(facilityDescription);
        long facilityId = UIDGenerator.uidGenerator();
        facility.setFacilityId(facilityId);
        return facility;
    }
}
