package com.phantom.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class HospitalFacilityRegisterDto {
    private Long hospitalId;
    private List<Long> facilityIdList;
}
