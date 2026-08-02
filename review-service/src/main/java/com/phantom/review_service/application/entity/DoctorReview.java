package com.phantom.review_service.application.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class DoctorReview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Serial
    private static final long serialVersionUID = 1L;

    private String comments;
    private LocalDateTime createdAt;
    private Long appUserId;
    private Long doctorId;
}

