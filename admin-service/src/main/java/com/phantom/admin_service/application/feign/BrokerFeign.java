package com.phantom.admin_service.application.feign;

import com.phantom.projection.BrokerStatusProjection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "broker-service")
public interface BrokerFeign {

    @GetMapping("/broker/findPendingBrokers/{status}")
    List<BrokerStatusProjection> findPendingBrokers(@PathVariable String status);
}
