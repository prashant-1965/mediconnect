package com.phantom.identity_service.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    private int userAge;
    private String userGender;
    private String userMobile;
    private String userEmail;
    private String userCountry;
    private String userState;
    private String userPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
