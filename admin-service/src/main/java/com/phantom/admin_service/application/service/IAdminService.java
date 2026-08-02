package com.phantom.admin_service.application.service;

import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.DoctorStatusProjection;
import com.phantom.projection.HospitalStatusProjection;

import java.util.List;

public interface IAdminService {
    List<HospitalStatusProjection> findPendingHospitals();
    List<DoctorStatusProjection> findPendingDoctors();
    List<BrokerStatusProjection> findPendingBrokers();
    String updateDoctorStatus(Long doctorId, String status);
    String updateHospitalStatus(Long hospitalId, String status);
    String updateBrokerStatus(Long brokerId, String status);
}
