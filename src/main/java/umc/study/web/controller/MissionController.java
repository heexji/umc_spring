package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionService;
import umc.study.web.dto.MissionResponseDto;

@Validated
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/mission/{missionId}/challenge")
    public ApiResponse<String> challengeMission(@PathVariable Long missionId) {
        missionService.challenge(missionId);
        return ApiResponse.onSuccess("success");
    }

    @GetMapping("/my-reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회")
    public ApiResponse<MissionResponseDto.ReviewList> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(name = "page") @Min(value = 1, message = "page는 1 이상이어야 합니다.") Integer page) {
        return ApiResponse.onSuccess(missionService.getMyReviews(memberId, page));
    }

    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회")
    public ApiResponse<MissionResponseDto.MissionList> getMissionsByStore(
            @PathVariable Long storeId,
            @RequestParam(name = "page") @Min(value = 1, message = "page는 1 이상이어야 합니다.") Integer page) {
        return ApiResponse.onSuccess(missionService.getMissionsByStore(storeId, page));
    }

    @GetMapping("/my")
    @Operation(summary = "내가 진행 중인 미션 목록 조회")
    public ApiResponse<MissionResponseDto.MissionList> getMyOngoingMissions(
            @RequestParam Long memberId,
            @RequestParam(name = "page") @Min(value = 1, message = "page는 1 이상이어야 합니다.") Integer page) {
        return ApiResponse.onSuccess(missionService.getMyOngoingMissions(memberId, page));
    }

    @PostMapping("/complete")
    @Operation(summary = "미션 완료 처리")
    public ApiResponse<String> completeMission(
            @RequestParam Long missionId,
            @RequestParam Long memberId) {
        missionService.completeMission(missionId, memberId);
        return ApiResponse.onSuccess("완료되었습니다");
    }
}
