package com.phantom.location_service.application.repository;

import com.phantom.dto.response.CountryListProjection;
import com.phantom.location_service.application.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

    @Query("select new com.healthcare.finder.doctorHospitalFinder.application.projection.CountryListProjection("+
    "c.countryName) "+
    "from Country c")
    List<CountryListProjection> allCountryList();

    @Query("select c from Country c where c.countryName = :name")
    Optional<Country> findCountryByName(String name);
}
