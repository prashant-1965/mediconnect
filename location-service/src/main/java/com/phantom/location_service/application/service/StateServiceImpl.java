package com.phantom.location_service.application.service;

import com.phantom.dto.request.StateRegisterDto;
import com.phantom.dto.response.StateListProjection;
import com.phantom.location_service.application.classexception.CountryException;
import com.phantom.location_service.application.classexception.StateException;
import com.phantom.location_service.application.entity.Country;
import com.phantom.location_service.application.entity.State;
import com.phantom.location_service.application.repository.CountryRepo;
import com.phantom.location_service.application.repository.StateRepo;
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
public class StateServiceImpl implements IStateService {

    @Autowired
    private StateRepo statesRepo;
    @Autowired
    private CountryRepo countryRepo;

    @Override
    @Cacheable(value = "StateListProjection",key = "#countryName",unless = "#result==null")
    public List<StateListProjection> getStateList(String countryName) throws StateException {
        List<StateListProjection> stateListProjections = statesRepo.allStateListByCountry(countryName);
        if(stateListProjections.isEmpty()){
            throw new StateException("No state found in "+countryName, HttpStatus.NOT_FOUND);
        }
        return stateListProjections.stream().sorted().toList();
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "State", allEntries = true),
                    @CacheEvict(value = "StateListProjection", allEntries = true)
            }
    )
    public String addState(StateRegisterDto stateRegisterDto) throws StateException, CountryException {
        Optional<Country> country = countryRepo.findCountryByName(stateRegisterDto.getCountryName());
        if(country.isEmpty()){
            throw new CountryException("Our Facility is not available in "+stateRegisterDto.getCountryName(),HttpStatus.BAD_REQUEST);
        }
        Optional<State> isExist = statesRepo.findByStateName(stateRegisterDto.getStateName());
        if(isExist.isPresent()){
            throw new StateException("State Already Exist",HttpStatus.BAD_REQUEST);
        }
        State state = DtoMapper.stateMapper(stateRegisterDto);
        state.setCountry(country.get());
        statesRepo.save(state);
        return "State added SuccessFully";
    }
}
