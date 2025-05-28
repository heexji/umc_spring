package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionResponseDto;
import umc.study.web.dto.StoreRequestDto;

import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(StoreRequestDto.AddMissionDto dto) {
        return Mission.builder()
                .missionSpec(dto.getMissionSpec())
                .deadline(dto.getDeadline())
                .reward(dto.getReward())
                .build();
    }

    // ✅ Review 페이지 → ReviewList DTO 변환
    public static MissionResponseDto.ReviewList toReviewList(Page<Review> page) {
        return MissionResponseDto.ReviewList.builder()
                .reviewList(
                        page.getContent().stream()
                                .map(review -> MissionResponseDto.ReviewDTO.builder()
                                        .id(review.getId())
                                        .body(review.getBody())
                                        .score(review.getScore())
                                        .build()
                                ).collect(Collectors.toList())
                )
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    // ✅ Mission 페이지 → MissionList DTO 변환
    public static MissionResponseDto.MissionList toMissionList(Page<Mission> page) {
        return MissionResponseDto.MissionList.builder()
                .missionList(
                        page.getContent().stream()
                                .map(mission -> MissionResponseDto.MissionDTO.builder()
                                        .id(mission.getId())
                                        .content(mission.getContent())
                                        .point(mission.getPoint())
                                        .build()
                                ).collect(Collectors.toList())
                )
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    // ✅ MemberMission 페이지 → MissionList DTO 변환 (진행 중 미션 등)
    public static MissionResponseDto.MissionList toMissionListFromMemberMission(Page<MemberMission> page) {
        return MissionResponseDto.MissionList.builder()
                .missionList(
                        page.getContent().stream()
                                .map(mm -> {
                                    Mission mission = mm.getMission();
                                    return MissionResponseDto.MissionDTO.builder()
                                            .id(mission.getId())
                                            .content(mission.getContent())
                                            .point(mission.getPoint())
                                            .build();
                                }).collect(Collectors.toList())
                )
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
