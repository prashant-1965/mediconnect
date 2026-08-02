package com.phantom.review_service.application.service;

import com.phantom.dto.request.GlobalReviewRegisterDto;
import com.phantom.projection.GlobalReviewProjection;
import com.phantom.projection.IdentityDetailProjection;
import com.phantom.review_service.application.classexception.GlocalReviewException;
import com.phantom.review_service.application.entity.GlobalReview;
import com.phantom.review_service.application.feign.IdentityFeign;
import com.phantom.review_service.application.repository.GlobalReviewRepository;
import com.phantom.review_service.application.util.DtoMapper;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GlobalReviewServiceImpl implements IGlobalReviewService{

    private final GlobalReviewRepository globalReviewRepository;
    private final IdentityFeign identityFeign;

    @Override
    @Transactional
    public String globalReviewRegister(GlobalReviewRegisterDto globalReviewRegisterDto) throws GlocalReviewException {
        Optional<GlobalReview> globalReviewExist = globalReviewRepository.findByAppUserId(globalReviewRegisterDto.getAppUserId());
        if (globalReviewExist.isPresent()) {
            throw new GlocalReviewException("Your FeedBack Already Exists", HttpStatus.BAD_REQUEST);
        }
        GlobalReview globalReview = DtoMapper.globalReviewMapper(globalReviewRegisterDto);
        globalReviewRepository.save(globalReview);
        return "Thanks for your feedback";
    }

    @Override
    public List<GlobalReviewProjection> getAllGlobalReviews() throws GlocalReviewException {
        List<GlobalReviewRegisterDto> globalReviewRegisterDto = globalReviewRepository.findGlobalReview();
        List<Long> appUserIds = globalReviewRegisterDto.stream().map(GlobalReviewRegisterDto::getAppUserId).toList();
        List<IdentityDetailProjection> identityDetailProjections;
        try {
            identityDetailProjections = identityFeign.findUserDetailByAppUserIds(appUserIds);
        }catch (FeignException fe){
            throw new GlocalReviewException(fe.contentUTF8(), HttpStatus.valueOf(fe.status()));
        }
        Map<Long,String> appUserIdToUserName = identityDetailProjections.stream().collect(Collectors.toMap(IdentityDetailProjection::getAppUserId, IdentityDetailProjection::getUserName));
        List<GlobalReviewProjection> globalReviewProjections = DtoMapper.globalReviewMapper(globalReviewRegisterDto,appUserIdToUserName);
        if(globalReviewProjections.isEmpty()){
            throw new GlocalReviewException("No Reviews Found",HttpStatus.NOT_FOUND);
        }
        return globalReviewProjections;
    }
}
