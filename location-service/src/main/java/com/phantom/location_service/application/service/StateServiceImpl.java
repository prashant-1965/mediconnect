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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StateServiceImpl implements IStateService {

    private final StateRepo statesRepo;
    private final CountryRepo countryRepo;

    @Override
//    @Caching(
//            evict = {
//                    @CacheEvict(value = "State", allEntries = true),
//                    @CacheEvict(value = "StateListProjection", allEntries = true)
//            }
//    )
    public String registerState(StateRegisterDto stateRegisterDto) throws StateException, CountryException {
        Optional<Country> country = countryRepo.findCountryByName(stateRegisterDto.getCountryName().toLowerCase());
        if(country.isEmpty()){
            log.error("Country not found fot the state {}",stateRegisterDto.getStateName());
            throw new CountryException("Our Facility is not available in "+stateRegisterDto.getCountryName(),HttpStatus.NOT_FOUND);
        }
        Optional<State> isExist = this.findStateByName(stateRegisterDto.getStateName());
        if(isExist.isPresent()){
            log.error("State already registered for the country {}",stateRegisterDto.getStateName());
            throw new StateException("State Already Exist",HttpStatus.BAD_REQUEST);
        }
        State state = DtoMapper.stateMapper(stateRegisterDto);
        state.setCountry(country.get());
        statesRepo.save(state);
        return "State added SuccessFully";
    }

    @Override
//    @Cacheable(value = "StateListProjection",key = "#countryName",unless = "#result==null")
    public List<StateListProjection> getStateList(String countryName) throws StateException {
        List<StateListProjection> stateListProjections = statesRepo.allStateListByCountry(countryName.toLowerCase());
        if(stateListProjections.isEmpty()){
            log.error("No state is available for the country {}",countryName);
            throw new StateException("No state found in "+countryName, HttpStatus.NOT_FOUND);
        }
        return stateListProjections.stream().sorted().toList();
    }

    private Optional<State> findStateByName(String stateName) {
        return statesRepo.findByStateName(stateName.toLowerCase());
    }

    @Override
    public Long findStateIdByName(String stateName) {
        Optional<State> state = statesRepo.findByStateName(stateName.toLowerCase());
        if(state.isEmpty()){
            log.error("State is not registered with name: {}",stateName);
            throw new StateException("State does not exist",HttpStatus.NOT_FOUND);
        }
        return state.get().getId();
    }
}
