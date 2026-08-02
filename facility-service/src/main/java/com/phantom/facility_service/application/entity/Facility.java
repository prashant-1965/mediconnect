package com.phantom.facility_service.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class Facility implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Serial
    private static final long serialVersionUID = 1L;
    private Long facilityId;
    @Column(unique = true)
    private String facilityName;
    private String facilityDescription;
}