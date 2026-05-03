package com.phantom.doctor_service.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;
    @Serial
    private static final long serialVersionUID = 1L;

    private String doctorName;
    private int doctorAge;
    private String doctorGender;
    private int doctorYearsOfExperience;
    private double doctorRating;
    private String doctorGraduateCollege;
    private String doctorFieldOfExpertise;
    private String doctorEmail;
    private String doctorDetailAddress;
    private String doctorType; // gov or private
    private Long countryId;
    private Long stateId;
    private Long hospitalId;

}


