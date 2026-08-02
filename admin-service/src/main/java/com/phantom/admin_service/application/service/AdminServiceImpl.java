package com.phantom.admin_service.application.service;

import com.phantom.admin_service.application.classexception.AdminException;
import com.phantom.admin_service.application.feign.BrokerFeign;
import com.phantom.admin_service.application.feign.DoctorFeign;
import com.phantom.admin_service.application.feign.HospitalFeign;
import com.phantom.enums.UserStatus;
import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.DoctorStatusProjection;
import com.phantom.projection.HospitalStatusProjection;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService{

    private final HospitalFeign hospitalFeign;
    private final DoctorFeign doctorFeign;
    private final BrokerFeign brokerFeign;

    @Override
    public List<HospitalStatusProjection> findPendingHospitals() throws AdminException {
        try {
            return hospitalFeign.findPendingHospitals(UserStatus.PENDING.toString());
        }catch (FeignException fe) {
            throw new AdminException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
    }

    @Override
    public List<DoctorStatusProjection> findPendingDoctors() throws AdminException {
        try {
            return doctorFeign.findPendingDoctors(UserStatus.PENDING.toString());
        }catch (FeignException fe) {
            throw new AdminException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
    }

    @Override
    public List<BrokerStatusProjection> findPendingBrokers() throws AdminException {
        try {
            return brokerFeign.findPendingBrokers(UserStatus.PENDING.toString());
        }catch (FeignException fe) {
            throw new AdminException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
    }

    @Override
    public String updateDoctorStatus(Long doctorId, String status) throws AdminException{
        String response;
        try {
            response = doctorFeign.updateDoctorStatus(doctorId, status);
        }catch (FeignException fe) {
            throw new AdminException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        return response;
    }

    @Override
    public String updateHospitalStatus(Long hospitalId, String status) throws AdminException{
        String response;
        try {
            response = hospitalFeign.updateHospitalStatus(hospitalId, status);
        }catch (FeignException fe) {
            throw new AdminException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        return response;
    }

    @Override
    public String updateBrokerStatus(Long brokerId, String status) throws AdminException {
        String response;
        try {
            response = brokerFeign.updateBrokerStatus(brokerId,status);
        }catch (FeignException fe){
            throw new AdminException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        return response;
    }
}
