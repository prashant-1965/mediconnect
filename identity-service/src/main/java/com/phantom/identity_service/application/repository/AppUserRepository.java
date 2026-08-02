package com.phantom.identity_service.application.repository;

import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.identity_service.application.entity.AppUser;
import com.phantom.projection.IdentityDetailProjection;
import com.phantom.projection.IdentityStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    @Query("select a from AppUser a where a.userEmail = :email")
    Optional<AppUser> findByUserEmail(@Param("email") String email);

    @Query("select new com.phantom.projection.IdentityStatusProjection(" +
            "a.appUserId, a.userName,a.userAge,a.userGender,a.userMobile, a.userEmail,a.userCountry,a.userState,a.userStatus)" +
            " from AppUser a inner join a.role r where a.userStatus = :userStatus and r.roleName = :roleName")
    List<IdentityStatusProjection> findPendingUserByStatusAndRole(@Param("roleName") UserRole roleName, @Param("userStatus") UserStatus userStatus);

    @Query("select a from AppUser a where a.appUserId = :appUserId")
    Optional<AppUser> findUserByAppUserId(@Param("appUserId") Long appUserId);

    @Query("select new com.phantom.projection.IdentityDetailProjection(a.appUserId,a.userName) from AppUser a where a.appUserId in :appUserIdList")
    List<IdentityDetailProjection> findUserDetailByAppUserIds(@Param("appUserId") List<Long> appUserIdList);

    @Query("select count(a)>0 from AppUser a where a.appUserId = :appUserId and a.userStatus = :userStatus")
    boolean checkUserStatusByAppUserId(@Param("appUserId") Long appUserId, @Param("userStatus") UserStatus userStatus);

//    @Transactional
//    @Modifying
//    @Query("update AppUser a set a.userPassword = :password where a.userEmail = :userEmail")
//    void updateUserPassword(@Param("userEmail") String userEmail, @Param("password") String password);
}
