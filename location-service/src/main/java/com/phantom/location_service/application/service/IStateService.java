package com.phantom.location_service.application.service;


import com.phantom.dto.request.StateRegisterDto;
import com.phantom.dto.response.StateListProjection;
import com.phantom.location_service.application.entity.State;

import java.util.List;
import java.util.Optional;

public interface IStateService {
    List<StateListProjection> getStateList(String countryName);
    String registerState(StateRegisterDto stateRegisterDto);
    Optional<State> findStateByName(String stateName);
}
