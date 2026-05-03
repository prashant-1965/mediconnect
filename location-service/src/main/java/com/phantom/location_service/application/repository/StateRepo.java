package com.phantom.location_service.application.repository;

import com.phantom.dto.response.StateListProjection;
import com.phantom.location_service.application.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepo extends JpaRepository<State, Long> {

    @Query("select new com.healthcare.finder.doctorHospitalFinder.application.projection.StateListProjection(s.stateName) " +
            "from State s join s.country c " +
            "where c.countryName = :countryName")
    List<StateListProjection> allStateListByCountry(@Param("countryName") String countryName);

    @Query("select s from State s where s.stateName = :name")
    Optional<State> findByStateName(String name);
}