package com.phantom.provider_facility_association_service.application.repository;

import com.phantom.provider_facility_association_service.application.entity.DoctorFacilityMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorFacilityRepository extends JpaRepository<DoctorFacilityMapping,Long> {

    @Query("select dfm.facilityId from DoctorFacilityMapping dfm where dfm.doctorId = :doctorId")
    List<Long> findFacilityIdsByDoctorId(@Param("doctorId") Long doctorId);
}
