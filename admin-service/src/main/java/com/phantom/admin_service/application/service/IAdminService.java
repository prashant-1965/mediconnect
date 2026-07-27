package com.phantom.admin_service.application.service;

import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.DoctorStatusProjection;
import com.phantom.projection.HospitalStatusProjection;

import java.util.List;

public interface IAdminService {
    List<HospitalStatusProjection> findPendingHospitals();
    List<DoctorStatusProjection> findPendingDoctors();
    List<BrokerStatusProjection> findPendingBrokers();
}
