package com.phantom.identity_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.identity_service.application.classexception.AppUserException;
import com.phantom.identity_service.application.entity.AppUser;
import com.phantom.identity_service.application.entity.Role;
import com.phantom.identity_service.application.feign.LocationFeign;
import com.phantom.identity_service.application.repository.AppUserRepository;
import com.phantom.identity_service.application.repository.RoleRepository;
import com.phantom.identity_service.application.util.DtoMapper;
import com.phantom.projection.IdentityDetailProjection;
import com.phantom.projection.IdentityStatusProjection;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements IAppUserService{

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final LocationFeign locationFeign;

    @Override
    @Transactional
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "loadUserByUsername",allEntries = true),
//                    @CacheEvict(value = "AppUser",allEntries = true)
//            }
//    )
    public String addAppUser(AppUserRegisterDto appUserRegisterDto) throws AppUserException {
        Optional<Role> role = roleRepository.getByRoleName(appUserRegisterDto.getRole());
        if(role.isEmpty()){
            throw new AppUserException("Invalid Role",HttpStatus.BAD_REQUEST);
        }
        Optional<AppUser> appUserExist = appUserRepository.findByUserEmail(appUserRegisterDto.getUserEmail());
        if(appUserExist.isPresent()){
            throw new AppUserException("User already exists",HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = DtoMapper.appUserMapper(appUserRegisterDto,role.get());
        Long countryId;
        Long stateId;
        try {
            countryId = locationFeign.findCountryByName(appUserRegisterDto.getUserCountry());
            stateId = locationFeign.findStateByName(appUserRegisterDto.getUserState());
        }catch (FeignException fe){
            throw new AppUserException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        catch (Exception e) {
            throw new AppUserException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        appUser.setUserCountry(countryId);
        appUser.setUserState(stateId);
        appUserRepository.save(appUser);
        return  "Account Created Successfully and Your userId is: "+appUser.getAppUserId();
    }

    @Override
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "loadUserByUsername",allEntries = true),
//                    @CacheEvict(value = "AppUser",allEntries = true)
//            }
//    )
    public String changeAppUserPasswordRequest(String userEmail,String newPassword) {
        Optional<AppUser> appUser = this.findByUserEmail(userEmail);
        if(appUser.isEmpty()){
            throw new AppUserException(userEmail+" has not registered in our system!",HttpStatus.NOT_FOUND);
        }
//        appUserRepository.updateUserPassword(userEmail,newPassword);
        return "You Password Updated SuccessFully!";
    }

    @Override
    public List<IdentityStatusProjection> findPendingUsers(UserRole role, UserStatus status) throws AppUserException {
        List<IdentityStatusProjection> identityStatusProjections = appUserRepository.findPendingUserByStatusAndRole(role,status);
        if(identityStatusProjections.isEmpty()){
            throw new AppUserException("No Pending Users Found",HttpStatus.NOT_FOUND);
        }
        return identityStatusProjections;
    }

    @Override
    public boolean findUserByAppUserId(Long appUserId){
        return appUserRepository.findUserByAppUserId(appUserId).isPresent();
    }

    @Override
    public String updateUserStatus(Long appUserId, String userStatus) {
        Optional<AppUser> appUser = appUserRepository.findUserByAppUserId(appUserId);
        if(appUser.isEmpty()){
            throw new AppUserException("User not found",HttpStatus.NOT_FOUND);
        }
        appUser.get().setUserStatus(UserStatus.valueOf(userStatus));
        return "status changed to "+userStatus+" Successfully";
    }

    @Override
    public List<IdentityDetailProjection> findUserDetailByAppUserIds(List<Long> appUserIdList) throws AppUserException{
        List<IdentityDetailProjection> identityDetailProjections;
        try {
            identityDetailProjections = appUserRepository.findUserDetailByAppUserIds(appUserIdList);

        }catch (JpaSystemException jpaSystemException){
            throw new AppUserException(jpaSystemException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (identityDetailProjections.isEmpty()) {
            throw new AppUserException("No User Found",HttpStatus.NOT_FOUND);
        }
        return identityDetailProjections;
    }

    @Override
    public boolean checkUserStatusByAppUserId(Long appUserId, UserStatus userStatus) {
        return appUserRepository.checkUserStatusByAppUserId(appUserId, userStatus);
    }

    //    @Cacheable(value = "AppUser",key = "#Email",unless = "#result==null")
    private Optional<AppUser> findByUserEmail(String Email) {
        return appUserRepository.findByUserEmail(Email);
    }
}
