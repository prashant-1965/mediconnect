package com.phantom.location_service.application.service;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;

import java.util.List;

public interface ICountryServices {
    List<CountryListProjection> getCountryList();
    String addCountry(CountryRegisterDto countryRegisterDto);
}
