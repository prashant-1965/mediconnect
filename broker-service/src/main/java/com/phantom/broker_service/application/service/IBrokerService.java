package com.phantom.broker_service.application.service;

import com.phantom.dto.request.BrokerRegisterDto;

public interface IBrokerService {
    String registerBroker(BrokerRegisterDto brokerRegisterDto);
}
