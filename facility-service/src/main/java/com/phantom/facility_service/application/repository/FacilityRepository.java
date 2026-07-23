package com.phantom.facility_service.application.repository;

import com.phantom.dto.response.FacilityListProjection;
import com.phantom.facility_service.application.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Long> {
    @Query("select new com.phantom.dto.response.FacilityListProjection("+
            "ms.facilityName, ms.facilityDescription) "+
            "from Facility ms order by ms.facilityName")
    List<FacilityListProjection> getAllAvailableFacility();

    @Query("select f from Facility f where f.facilityName in :name")
    List<Facility> findAllFacilityByName(List<String> name);

    @Query("select f.facilityId from Facility f where f.facilityName in :facilityNames")
    List<Long> findAllFacilityIdByName(List<String> facilityNames);

    @Query("select f from Facility f where f.facilityName = :name")
    Optional<Facility> findByFacilityName(String name);

//    @Query("select f.facilityName from Doctor d join d.facilities f where d.doctorEmail = :doctorEmail order by f.facilityName desc")
//    List<String> findFacilityByDoctorEmail(@Param("doctorEmail") String doctorEmail);
//
//    @Query("select f.facilityName from Hospital h join h.facilities f where h.hospitalName = :hospitalName order by h.hospitalName desc")
//    List<String> getFacilityByHospitalName(@Param("hospitalName") String hospitalName);

}
