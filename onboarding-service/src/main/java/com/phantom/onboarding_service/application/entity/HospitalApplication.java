package com.phantom.onboarding_service.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class HospitalApplication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hospitalApplicationId;

    @Serial
    private static final long serialVersionUID = 1L;

    private String tempHospitalName;
    private String tempHospitalType;
    private int tempHospitalYearOfEstablishment;
    private int tempHospitalNumOfUsersServed;
    private double tempHospitalRating;
    private String tempHospitalContact;
    private String tempHospitalAddress;
    private String tempCountryName;
    private String tempStateName;
}

