package com.phantom.identity_service.application.repository;

import com.phantom.identity_service.application.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.roleName = :name")
    Optional<Role> getByRoleName(@Param("name") String name);
}
