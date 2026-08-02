package com.phantom.hospital_service.application.repository;

import com.phantom.dto.response.IndividualHospitalDetailProjection;
import com.phantom.hospital_service.application.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    @Query("select h from Hospital h where h.appUserId = :appUserId")
    Optional<Hospital> findHospitalByAppUserId(@Param("appUserId") Long appUserId);

    @Query("select h from Hospital h where h.hospitalId = :hospitalId")
    Optional<Hospital> findHospitalByHospitalId(@Param("hospitalId") Long hospitalId);

}
