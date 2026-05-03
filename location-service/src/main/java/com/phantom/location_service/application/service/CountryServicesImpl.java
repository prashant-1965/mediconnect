package com.phantom.location_service.application.service;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;
import com.phantom.location_service.application.classexception.CountryException;
import com.phantom.location_service.application.entity.Country;
import com.phantom.location_service.application.repository.CountryRepo;
import com.phantom.location_service.application.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServicesImpl implements ICountryServices {

    @Autowired
    private CountryRepo countryRepo;

    @Override
    @Cacheable(value = "AllCountryList")
    public List<CountryListProjection> getCountryList() throws CountryException {
        List<CountryListProjection> countryListProjections = countryRepo.allCountryList();
        if(countryListProjections.isEmpty()){
            throw new CountryException("No country Found", HttpStatus.NOT_FOUND);
        }
        return countryListProjections.stream().sorted().toList();
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "AllCountryList",allEntries = true),
                    @CacheEvict(value = "Country",allEntries = true)
            }
    )
    public String addCountry(CountryRegisterDto countryRegisterDto) throws CountryException {
        Optional<Country> isExist = countryRepo.findCountryByName(countryRegisterDto.getCountryName());
        if(isExist.isEmpty()){
            throw new CountryException("Country Already Exist",HttpStatus.BAD_REQUEST);
        }
        Country country = DtoMapper.countryMapper(countryRegisterDto);
        countryRepo.save(country);
        return "Country Added SuccessFully";
    }
}
