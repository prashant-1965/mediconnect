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

    @Query("select count(h)>0 from Hospital h where h.hospitalEmail = :hospitalEmail")
    boolean findHospitalByEmail(@Param("hospitalEmail") String hospitalEmail);

    @Query("select count(h)>0 from Hospital h where h.hospitalId = :hospitalId")
    boolean findHospitalByHospitalId(@Param("hospitalId") Long hospitalId);

//    @Query("select h.hospitalRating from Hospital h where h.hospitalName = :name")
//    double getRattingByHospitalName(@Param("name") String name);

//    @Query("select h.hospitalName from Hospital h")
//    List<String> findAllHospital();
//
//    @Query("select h from Doctor d join d.hospital h where d.doctorName = :doctorName")
//    Optional<Hospital> getHospitalByDoctorName(@Param("doctorName") String doctorName);
//
//    @Query("select h.hospitalName from Doctor d join d.hospital h where d.doctorEmail = :doctorEmail")
//    Optional<String> getHospitalByDoctorEmail(@Param("doctorName") String doctorEmail);
//
//    @Query("select h.hospitalName from Hospital h join h.facilities f where f.facilityName = :facilityName order by h.hospitalName")
//    List<String> getHospitalByFacilityName(@Param("facilityName") String facilityName);
//
//    @Query("select h.hospitalName from Doctor d join d.hospital h join d.facilities f where d.doctorEmail = :doctorEmail and f.facilityName = :facilityName order by h.hospitalName desc")
//    List<String> getHospitalByFacilityNameAndDoctorEmail(@Param("facilityName") String facilityName,@Param("doctorEmail") String doctorEmail);
//
//    @Query("select new com.phantom.dto.response.IndividualHospitalDetailProjection( "+
//            "h.hospitalName,h.hospitalType,h.hospitalYearOfEstablishment,h.hospitalNumOfUsersServed,h.hospitalRating,h.hospitalContact,h.hospitalAddress) "+
//            " from Hospital h where h.hospitalName = :hospitalName")
//    Optional<IndividualHospitalDetailProjection> getHospitalDetailByName(@Param("hospitalName") String hospitalName);

}
