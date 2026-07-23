package com.phantom.identity_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.identity_service.application.classexception.AppUserException;
import com.phantom.identity_service.application.entity.AppUser;
import com.phantom.identity_service.application.entity.Role;
import com.phantom.identity_service.application.repository.AppUserRepository;
import com.phantom.identity_service.application.repository.RoleRepository;
import com.phantom.identity_service.application.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements IAppUserService{

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
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
        AppUser appUser = DtoMapper.appUserMapper(appUserRegisterDto,role.get());
        appUserRepository.save(appUser);
        return  "Account Created Successfully";
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

//    @Cacheable(value = "AppUser",key = "#Email",unless = "#result==null")
    public Optional<AppUser> findByUserEmail(String Email) {
        return appUserRepository.findByUserEmail(Email);
    }
}
