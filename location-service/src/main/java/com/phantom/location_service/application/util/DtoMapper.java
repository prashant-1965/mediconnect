package com.phantom.location_service.application.util;

import com.phantom.dto.request.CountryRegisterDto;
import com.phantom.dto.request.StateRegisterDto;
import com.phantom.location_service.application.entity.Country;
import com.phantom.location_service.application.entity.State;
import com.phantom.util.UIDGenerator;

public class DtoMapper {
    public static Country countryMapper(CountryRegisterDto countryRegisterDto){
        Country country = new Country();
        country.setCountryName(countryRegisterDto.getCountryName().toLowerCase());
        long countryId = UIDGenerator.uidGenerator();
        country.setCountryId(countryId);
        return country;
    }
    public static State stateMapper(StateRegisterDto stateRegisterDto){
        State state = new State();
        state.setStateName(stateRegisterDto.getStateName().toLowerCase());
        long stateId = UIDGenerator.uidGenerator();
        state.setStateId(stateId);
        return state;
    }
}
