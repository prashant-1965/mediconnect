package com.phantom.identity_service.application.entity;

import com.phantom.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<AppUser> userList;
}
