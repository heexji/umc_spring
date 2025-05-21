// StoreController.java
package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.StoreService.StoreService;
import umc.study.web.dto.StoreRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<String> addMissionToStore(
            @PathVariable Long storeId,
            @RequestBody @Valid StoreRequestDto.AddMissionDto dto) {

        storeService.addMissionToStore(storeId, dto);
        return ApiResponse.onSuccess("success");
    }
}
