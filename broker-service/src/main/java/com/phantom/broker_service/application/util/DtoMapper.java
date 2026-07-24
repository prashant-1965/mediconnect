package com.phantom.broker_service.application.util;

import com.phantom.broker_service.application.entity.Broker;
import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.BrokerRegisterDto;
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
        appUserRegisterDto.setRole("BROKER");
        return appUserRegisterDto;
    }
}
