package com.phantom.location_service.application.controller;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;
import com.phantom.location_service.application.entity.Country;
import com.phantom.location_service.application.service.ICountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final ICountryService countryServices;

    @PostMapping("/register")
    public ResponseEntity<String> registerCountry(@RequestBody CountryRegisterDto countryRegisterDto){
        return ResponseEntity.status(200).body(countryServices.registerCountry(countryRegisterDto));
    }
    @GetMapping("/getCountryList")
    public ResponseEntity<List<CountryListProjection>> getCountryList(){
        return ResponseEntity.status(200).body(countryServices.getCountryList());
    }

    @GetMapping("/findByName/{countryName}")
    public Long findCountryByName(@PathVariable String countryName){
        return countryServices.findCountryIdByName(countryName);
    }

    @DeleteMapping("/remove/{countryName}")
    public ResponseEntity<String> removeCountryByName(@PathVariable String countryName){
        return ResponseEntity.status(200).body(countryServices.removeCountry(countryName));
    }
}
