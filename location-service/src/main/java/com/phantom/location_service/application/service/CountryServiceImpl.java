package com.phantom.location_service.application.service;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.response.CountryListProjection;
import com.phantom.location_service.application.classexception.CountryException;
import com.phantom.location_service.application.entity.Country;
import com.phantom.location_service.application.repository.CountryRepo;
import com.phantom.location_service.application.util.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryServiceImpl implements ICountryService {

    private final CountryRepo countryRepo;

    @Override
//    @Cacheable(value = "AllCountryList")
    public List<CountryListProjection> getCountryList() throws CountryException {
        List<CountryListProjection> countryListProjections = countryRepo.allCountryList();
        if(countryListProjections.isEmpty()){
            log.error("No country registered yet!");
            throw new CountryException("No country Found", HttpStatus.NOT_FOUND);
        }
        return countryListProjections;
    }

    @Override
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "AllCountryList",allEntries = true),
//                    @CacheEvict(value = "Country",allEntries = true)
//            }
//    )
    public String registerCountry(CountryRegisterDto countryRegisterDto) throws CountryException {
        Optional<Country> isExist = this.findCountryByName(countryRegisterDto.getCountryName().toLowerCase());
        if(isExist.isPresent()){
            log.error("Country already registered with name: {}",countryRegisterDto.getCountryName());
            throw new CountryException("Country Already Exist",HttpStatus.BAD_REQUEST);
        }
        Country country = DtoMapper.countryMapper(countryRegisterDto);
        countryRepo.save(country);
        return "Country Added SuccessFully";
    }

    @Override
    @Transactional
    public String removeCountry(String countryName) throws CountryException {
        Optional<Country> isExist = this.findCountryByName(countryName);
        if(isExist.isEmpty()){
            log.error("Country is not registered with name: {}",countryName);
            throw new CountryException("Country does not exist",HttpStatus.NOT_FOUND);
        }
        countryRepo.deleteById(isExist.get().getId());
        return "Country Removed Successfully";
    }

    public Optional<Country> findCountryByName(String countryName) {
        return countryRepo.findCountryByName(countryName.toLowerCase());
    }
}
