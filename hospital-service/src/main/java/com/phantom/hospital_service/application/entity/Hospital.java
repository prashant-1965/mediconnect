package com.phantom.hospital_service.application.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

public class Hospital implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hospitalId;
    @Serial
    private static final long serialVersionUID = 1L;

    private String hospitalName;
    private String hospitalType;
    private int hospitalYearOfEstablishment;
    private int hospitalNumOfUsersServed;
    private double hospitalRating;
    private String hospitalContact;
    private String hospitalAddress;
    private Long countryId;
    private Long stateId;
}


