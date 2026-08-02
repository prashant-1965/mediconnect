package com.phantom.review_service.application.repository;

import com.phantom.review_service.application.entity.HospitalReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalReviewRepository extends JpaRepository<HospitalReview, Long> {

    @Query("SELECT h FROM HospitalReview h WHERE h.hospitalId = :hospitalId AND h.appUserId = :appUserId")
    boolean existsByHospitalIdAndAppUserId(Long hospitalId, Long appUserId);

    @Query("SELECT COUNT(h) FROM HospitalReview h WHERE h.hospitalId = :hospitalId")
    int countByHospitalId(Long hospitalId);
}
