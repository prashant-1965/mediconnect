package com.phantom.location_service.application.controller;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;
import com.phantom.location_service.application.service.ICountryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private ICountryServices ICountryServices;

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody CountryRegisterDto countryRegisterDto){
        return ResponseEntity.status(200).body(ICountryServices.addCountry(countryRegisterDto));
    }
    @GetMapping("/getCountryList")
    public ResponseEntity<List<CountryListProjection>> getCountryList(){
        return ResponseEntity.status(200).body(ICountryServices.getCountryList());
    }
}
