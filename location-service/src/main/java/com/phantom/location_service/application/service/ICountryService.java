package com.phantom.location_service.application.service;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;

import java.util.List;

public interface ICountryService {
    List<CountryListProjection> getCountryList();
    String registerCountry(CountryRegisterDto countryRegisterDto);
    Long findCountryIdByName(String countryName);
    String removeCountry(String countryName);
}
