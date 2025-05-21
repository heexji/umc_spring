package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.MissionService.MissionService;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/mission/{missionId}/challenge")
    public ApiResponse<String> challengeMission(@PathVariable Long missionId) {
        missionService.challenge(missionId);  // ✅ static 제거된 메서드 호출
        return ApiResponse.onSuccess("success");
    }
}
