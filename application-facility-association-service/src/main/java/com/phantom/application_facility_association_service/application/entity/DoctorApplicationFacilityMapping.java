package com.phantom.application_facility_association_service.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class DoctorApplicationFacilityMapping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long doctorApplicationId;
    private Long facilityId;
}
