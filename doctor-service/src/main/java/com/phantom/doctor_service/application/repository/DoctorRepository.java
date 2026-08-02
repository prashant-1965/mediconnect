package com.phantom.doctor_service.application.repository;

import com.phantom.doctor_service.application.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findDoctorByAppUserId(Long appUserId);

    @Query("SELECT d FROM Doctor d WHERE d.doctorId = :doctorId")
    Optional<Doctor> findDoctorByDoctorId(Long doctorId);
}
