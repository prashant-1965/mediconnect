package com.phantom.appointment_service.application.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long appointmentId;

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDateTime appointmentDate;
    private LocalDateTime appointmentAppliedDate;
    private String appointmentStatus; // pending/confirmed/completed/cancelled
    private Long appUserId;
    private Long doctorId;
    private Long hospitalId;
    private Long facilityId;
}

