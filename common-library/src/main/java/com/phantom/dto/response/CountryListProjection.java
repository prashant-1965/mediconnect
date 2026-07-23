package com.phantom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class CountryListProjection {
    private String countryName;
}
