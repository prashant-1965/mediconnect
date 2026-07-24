package com.phantom.dto.request;

import lombok.Data;

@Data
public class BrokerRegisterDto {
    private String brokerName;
    private String brokerGender;
    private String brokerAge;
    private String brokerEmail;
    private String brokerPhoneNumber;
    private String brokerAddress;
    private String brokerState;
    private String brokerCountry;
    private Long brokerHospitalId;
//    private String brokerPassword;
}