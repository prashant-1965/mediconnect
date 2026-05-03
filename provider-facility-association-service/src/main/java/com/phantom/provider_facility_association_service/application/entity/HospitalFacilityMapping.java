package com.phantom.provider_facility_association_service.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class HospitalFacilityMapping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Serial
    private static final long serialVersionUID = 1L;
    private Long hospitalId;
    private Long facilityId;
}
