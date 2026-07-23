package com.phantom.hospital_service.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "location-service")
public interface LocationFeign {
    @GetMapping("/country/findByName/{countryName}")
    Long findCountryByName(@PathVariable String countryName);

    @GetMapping("/state/findByName/{stateName}")
    Long findStateByName(@PathVariable String stateName);
}
