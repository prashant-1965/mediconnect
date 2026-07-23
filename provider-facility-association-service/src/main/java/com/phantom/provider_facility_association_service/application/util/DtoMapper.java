package com.phantom.provider_facility_association_service.application.util;

import com.phantom.provider_facility_association_service.application.entity.DoctorFacilityMapping;
import com.phantom.provider_facility_association_service.application.entity.HospitalFacilityMapping;

public class DtoMapper {

    public static HospitalFacilityMapping hospitalFacilityMapper(Long hospitalId,Long facilityId){
        HospitalFacilityMapping hospitalFacilityMapping = new HospitalFacilityMapping();
        hospitalFacilityMapping.setHospitalId(hospitalId);
        hospitalFacilityMapping.setFacilityId(facilityId);
        return hospitalFacilityMapping;
    }

    public static DoctorFacilityMapping doctorFacilityMapper(Long doctorId,Long facilityId){
        DoctorFacilityMapping doctorFacilityMapping = new DoctorFacilityMapping();
        doctorFacilityMapping.setDoctorId(doctorId);
        doctorFacilityMapping.setFacilityId(facilityId);
        return doctorFacilityMapping;
    }
}
