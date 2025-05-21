package umc.study.web.dto;

import lombok.Getter;

public class ReviewRequestDto {

    @Getter
    public static class AddReviewDto {
        private Long memberId;
        private Float score;
        private String title;
        private String body;
    }
}
