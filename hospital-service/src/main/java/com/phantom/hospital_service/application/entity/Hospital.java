package com.phantom.hospital_service.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Hospital implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hospitalId;
    @Serial
    private static final long serialVersionUID = 1L;

    private String hospitalName;
    private String hospitalEmail;
    private String hospitalType; // Gov or Private
    private int hospitalYearOfEstablishment;
    private int hospitalNumOfUsersServed;
    private double hospitalRating;
    private String hospitalMobile;
    private String hospitalAddress;
    private Long countryId;
    private Long stateId;
    private String hospitalStatus; // active or inactive or Pending
    private LocalDateTime hospitalRegistrationDateTime;
}


