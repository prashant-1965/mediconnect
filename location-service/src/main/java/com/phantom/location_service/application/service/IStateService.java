package com.phantom.location_service.application.service;


import com.phantom.dto.request.StateRegisterDto;
import com.phantom.dto.response.StateListProjection;

import java.util.List;

public interface IStateService {
    List<StateListProjection> getStateList(String countryName);
    String registerState(StateRegisterDto stateRegisterDto);
    Long findStateIdByName(String stateName);
}
