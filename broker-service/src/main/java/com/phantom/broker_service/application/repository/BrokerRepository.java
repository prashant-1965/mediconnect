package com.phantom.broker_service.application.repository;

import com.phantom.broker_service.application.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<Broker,Long> {
}
