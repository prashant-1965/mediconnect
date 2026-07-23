package com.phantom.identity_service.application.repository;

import com.phantom.identity_service.application.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    @Query("select a from AppUser a where a.userEmail = :email")
    Optional<AppUser> findByUserEmail(@Param("email") String email);

//    @Transactional
//    @Modifying
//    @Query("update AppUser a set a.userPassword = :password where a.userEmail = :userEmail")
//    void updateUserPassword(@Param("userEmail") String userEmail, @Param("password") String password);
}
