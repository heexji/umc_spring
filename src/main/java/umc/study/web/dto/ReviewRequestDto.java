package umc.study.web.dto;

import lombok.Getter;

import java.time.LocalDate;

public class ReviewRequestDto {

    @Getter
    public static class AddReviewDto {
        private Long memberId;
        private Float score;
        private String title;
        private String body;
        LocalDate createAt;
    }
}
