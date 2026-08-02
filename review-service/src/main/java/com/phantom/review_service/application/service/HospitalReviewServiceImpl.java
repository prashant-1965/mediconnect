package com.phantom.review_service.application.service;

import com.phantom.dto.request.HospitalReviewRegisterDto;
import com.phantom.review_service.application.classexception.HospitalReviewException;
import com.phantom.review_service.application.entity.HospitalReview;
import com.phantom.review_service.application.feign.HospitalFeign;
import com.phantom.review_service.application.feign.IdentityFeign;
import com.phantom.review_service.application.repository.HospitalReviewRepository;
import com.phantom.review_service.application.util.DtoMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalReviewServiceImpl implements IHospitalReviewService{

    private final HospitalReviewRepository hospitalReviewRepository;
    private final IdentityFeign identityFeign;
    private final HospitalFeign hospitalFeign;

    @Override
    public String hospitalReviewRegister(HospitalReviewRegisterDto hospitalReviewRegisterDto) throws HospitalReviewException {

        if(hospitalReviewRepository.existsByHospitalIdAndAppUserId(hospitalReviewRegisterDto.getHospitalId(), hospitalReviewRegisterDto.getAppUserId())){
            throw new HospitalReviewException("User has already reviewed this hospital", HttpStatus.BAD_REQUEST);
        }

        try {
            if(!identityFeign.findUserByAppUserId(hospitalReviewRegisterDto.getAppUserId())){
                throw new HospitalReviewException("User not found with userId: "+ hospitalReviewRegisterDto.getAppUserId(), HttpStatus.NOT_FOUND);
            }
            if(!hospitalFeign.findHospitalByHospitalId(hospitalReviewRegisterDto.getHospitalId())){
                throw new HospitalReviewException("Hospital not found with hospitalId: "+ hospitalReviewRegisterDto.getHospitalId(), HttpStatus.NOT_FOUND);
            }
        } catch (FeignException fe) {
            return fe.getMessage();
        }
        try {
            int totalReviews = hospitalReviewRepository.countByHospitalId(hospitalReviewRegisterDto.getHospitalId());
            hospitalFeign.updateHospitalRating(hospitalReviewRegisterDto.getHospitalId(), hospitalReviewRegisterDto.getRating(), totalReviews);
        } catch (FeignException fe) {
            throw new HospitalReviewException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        HospitalReview hospitalReview = DtoMapper.hospitalReviewMapper(hospitalReviewRegisterDto);
        hospitalReviewRepository.save(hospitalReview);
        return "Thanks for your feedback";
    }
}
