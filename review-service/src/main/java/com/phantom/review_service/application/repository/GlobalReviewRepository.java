package com.phantom.review_service.application.repository;


import com.phantom.dto.request.GlobalReviewRegisterDto;
import com.phantom.review_service.application.entity.GlobalReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GlobalReviewRepository extends JpaRepository<GlobalReview, Long> {
    @Query("SELECT g FROM GlobalReview g WHERE g.appUserId = :appUserId")
    Optional<GlobalReview> findByAppUserId(Long appUserId);

    @Query("SELECT new com.phantom.dto.request.GlobalReviewRegisterDto(g.appUserId, g.rating, g.comments) FROM GlobalReview g")
    List<GlobalReviewRegisterDto> findGlobalReview();
}
