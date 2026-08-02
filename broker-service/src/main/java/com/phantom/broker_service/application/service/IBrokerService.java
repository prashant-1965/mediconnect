package com.phantom.broker_service.application.service;

import com.phantom.dto.request.BrokerRegisterDto;
import com.phantom.projection.BrokerStatusProjection;

import java.util.List;

public interface IBrokerService {
    String registerBroker(BrokerRegisterDto brokerRegisterDto);
    List<BrokerStatusProjection> findPendingBrokers(String status);
    String updateBrokerStatus(Long brokerId, String status);
}
