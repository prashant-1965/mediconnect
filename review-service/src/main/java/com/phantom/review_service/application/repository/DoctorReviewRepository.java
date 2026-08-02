package com.phantom.review_service.application.repository;

import com.phantom.review_service.application.entity.DoctorReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorReviewRepository extends JpaRepository<DoctorReview, Long> {

    @Query("SELECT d FROM DoctorReview d WHERE d.doctorId = :doctorId AND d.appUserId = :appUserId")
    boolean existsByDoctorIdAndAppUserId(Long doctorId, Long appUserId);

    @Query("SELECT COUNT(d) FROM DoctorReview d WHERE d.doctorId = :doctorId")
    int countByDoctorId(Long doctorId);
}
