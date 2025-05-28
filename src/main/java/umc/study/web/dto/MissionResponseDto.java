package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDto {

    @Getter
    @Builder
    public static class Review {
        private String body;
        private int score;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class ReviewList {
        private List<ReviewDTO> reviewList;   // ✅ 이 필드명이 중요
        private int totalPages;
        private long totalElements;
    }

    @Getter
    @Builder
    public static class Mission {
        private String name;
        private int point;
        private String status;
    }

    @Getter
    @Builder
    public static class MissionList {
        private List<MissionDTO> missionList;
        private int totalPages;
        private long totalElements;
    }

    @Builder
    @Getter
    public static class MissionDTO {
        private Long id;
        private String content;
        private int point;
    }

    @Builder
    @Getter
    public static class ReviewDTO {
        private Long id;
        private String body;
        private double score;
    }
}
