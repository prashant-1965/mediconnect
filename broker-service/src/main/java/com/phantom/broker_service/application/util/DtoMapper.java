package com.phantom.broker_service.application.util;

import com.phantom.broker_service.application.entity.Broker;
import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.BrokerRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.IdentityStatusProjection;
import com.phantom.util.UIDGenerator;

public class DtoMapper {
    public static Broker brokerMapper(BrokerRegisterDto brokerRegisterDto){
        Broker broker = new Broker();
        broker.setBrokerId(UIDGenerator.uidGenerator());
        broker.setBrokerAddress(brokerRegisterDto.getBrokerAddress());
        broker.setHospitalId(brokerRegisterDto.getBrokerHospitalId());
        broker.setBrokerRating(0.0);
        return broker;
    }

    public static AppUserRegisterDto brokerAppUserMapper(BrokerRegisterDto brokerRegisterDto){
        AppUserRegisterDto appUserRegisterDto = new AppUserRegisterDto();
        appUserRegisterDto.setUserName(brokerRegisterDto.getBrokerName());
        appUserRegisterDto.setUserAge(Integer.parseInt(brokerRegisterDto.getBrokerAge()));
        appUserRegisterDto.setUserGender(brokerRegisterDto.getBrokerGender());
        appUserRegisterDto.setUserMobile(brokerRegisterDto.getBrokerPhoneNumber());
        appUserRegisterDto.setUserEmail(brokerRegisterDto.getBrokerEmail());
        appUserRegisterDto.setUserState(brokerRegisterDto.getBrokerState());
        appUserRegisterDto.setUserCountry(brokerRegisterDto.getBrokerCountry());
        appUserRegisterDto.setRole(UserRole.BROKER);
        return appUserRegisterDto;
    }

    public static BrokerStatusProjection BrokerIdentityMapper(Broker broker, IdentityStatusProjection identityStatusProjection){
        BrokerStatusProjection brokerStatusProjection = new BrokerStatusProjection();
        brokerStatusProjection.setUserName(identityStatusProjection.getUserName());
        brokerStatusProjection.setUserAge(identityStatusProjection.getUserAge());
        brokerStatusProjection.setUserGender(identityStatusProjection.getUserGender());
        brokerStatusProjection.setUserMobile(identityStatusProjection.getUserMobile());
        brokerStatusProjection.setUserEmail(identityStatusProjection.getUserEmail());
        brokerStatusProjection.setUserCountry(identityStatusProjection.getUserCountry());
        brokerStatusProjection.setUserState(identityStatusProjection.getUserState());
        brokerStatusProjection.setUserStatus(identityStatusProjection.getUserStatus());
        brokerStatusProjection.setBrokerId(broker.getBrokerId());
        brokerStatusProjection.setBrokerAddress(broker.getBrokerAddress());
        brokerStatusProjection.setBrokerRating(broker.getBrokerRating());
        brokerStatusProjection.setBrokerYearOfExperience(broker.getBrokerYearOfExperience());
        brokerStatusProjection.setHospitalId(broker.getHospitalId());

        return brokerStatusProjection;
    }
}
