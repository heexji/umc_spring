package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDto;
import umc.study.web.dto.StoreResponseDto;

import java.util.List;


// ReviewConverter.java
public class ReviewConverter {
    public static Review toReview(ReviewRequestDto.AddReviewDto dto) {
        return Review.builder()
                .score(dto.getScore())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }
    public static StoreResponseDto.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return null;
    }
    public static StoreResponseDto.ReviewPreViewListDTO reviewPreViewListDTO(List<Review> reviewList){
        return null;
    }
}