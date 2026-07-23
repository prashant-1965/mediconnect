package com.phantom.provider_facility_association_service.application.repository;

import com.phantom.provider_facility_association_service.application.entity.HospitalFacilityMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalFacilityRepository extends JpaRepository<HospitalFacilityMapping,Long> {

    @Query("select hfm.facilityId from HospitalFacilityMapping hfm where hfm.hospitalId = :hospitalId")
    List<Long> findFacilityIdsByHospitalId(@Param("hospitalId") Long hospitalId);
}
