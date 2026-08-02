package com.phantom.facility_service.application.repository;

import com.phantom.dto.response.FacilityListProjection;
import com.phantom.facility_service.application.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Long> {
    @Query("select new com.phantom.dto.response.FacilityListProjection("+
            "ms.facilityName, ms.facilityDescription) "+
            "from Facility ms order by ms.facilityName")
    List<FacilityListProjection> getAllAvailableFacility();

    @Query("SELECT f FROM Facility f WHERE f.facilityName IN :names")
    List<Facility> findAllFacilityByName(@Param("names") List<String> names);

    @Query("select f.facilityId from Facility f where f.facilityName in :facilityNames")
    List<Long> findAllFacilityIdByName(List<String> facilityNames);
}
