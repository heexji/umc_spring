package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDto;

// ReviewConverter.java
public class ReviewConverter {
    public static Review toReview(ReviewRequestDto.AddReviewDto dto) {
        return Review.builder()
                .score(dto.getScore())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }
}