package com.phantom.onboarding_service.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class DoctorApplication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorApplicationId;
    @Serial
    private static final long serialVersionUID = 1L;

    private String tmpDoctorName;
    private int tmpDoctorAge;
    private String tmpDoctorGender;
    private int tmpDoctorYearsOfExperience;
    private String tmpDoctorGraduateCollege;
    private String tmpDoctorFieldOfExpertise;
    private String tmpDoctorEmail;
    private String tmpDoctorMobile;
    private String tmpDoctorDetailAddress;
    private String tmpDoctorType; // Gov , Private
    private String hospitalAppliedFor;
    private String tmpDoctorCountryName;
    private String tmpDoctorStateName;
}