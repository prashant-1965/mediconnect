package com.phantom.review_service.application.service;

import com.phantom.dto.request.DoctorReviewRegisterDto;
import com.phantom.review_service.application.classexception.DoctorReviewException;
import com.phantom.review_service.application.entity.DoctorReview;
import com.phantom.review_service.application.feign.DoctorFeign;
import com.phantom.review_service.application.feign.IdentityFeign;
import com.phantom.review_service.application.repository.DoctorReviewRepository;
import com.phantom.review_service.application.util.DtoMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorReviewServiceImpl implements IDoctorReviewService{

    private final DoctorReviewRepository doctorReviewRepository;
    private final IdentityFeign identityFeign;
    private final DoctorFeign doctorFeign;

    @Override
    public String doctorReviewRegister(DoctorReviewRegisterDto doctorReviewRegisterDto) throws DoctorReviewException {

        if(doctorReviewRepository.existsByDoctorIdAndAppUserId(doctorReviewRegisterDto.getDoctorId(), doctorReviewRegisterDto.getAppUserId())){
            throw new DoctorReviewException("User has already reviewed this doctor", HttpStatus.BAD_REQUEST);
        }

        try {
            if(!identityFeign.findUserByAppUserId(doctorReviewRegisterDto.getAppUserId())){
                throw new DoctorReviewException("User not found with userId: "+ doctorReviewRegisterDto.getAppUserId(), HttpStatus.NOT_FOUND);
            }
            if(!doctorFeign.findDoctorByDoctorId(doctorReviewRegisterDto.getDoctorId())){
                throw new DoctorReviewException("Doctor not found with doctorId: "+ doctorReviewRegisterDto.getDoctorId(), HttpStatus.NOT_FOUND);
            }
        } catch (FeignException fe) {
            return fe.getMessage();
        }
        try {
            int totalReviews = doctorReviewRepository.countByDoctorId(doctorReviewRegisterDto.getDoctorId());
            doctorFeign.updateDoctorRating(doctorReviewRegisterDto.getDoctorId(), doctorReviewRegisterDto.getRating(), totalReviews);
        } catch (FeignException fe) {
            throw new DoctorReviewException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        DoctorReview doctorReview = DtoMapper.doctorReviewMapper(doctorReviewRegisterDto);
        doctorReviewRepository.save(doctorReview);
        return "Thanks for your feedback";
    }
}
