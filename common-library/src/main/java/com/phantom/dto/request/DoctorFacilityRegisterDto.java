package com.phantom.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DoctorFacilityRegisterDto {
    private Long doctorId;
    private List<Long> facilityIdList;
}
