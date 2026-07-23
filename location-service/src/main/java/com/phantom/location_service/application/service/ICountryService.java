package com.phantom.location_service.application.service;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;
import com.phantom.location_service.application.entity.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    List<CountryListProjection> getCountryList();
    String registerCountry(CountryRegisterDto countryRegisterDto);
    Optional<Country> findCountryByName(String countryName);
    String removeCountry(String countryName);
}
