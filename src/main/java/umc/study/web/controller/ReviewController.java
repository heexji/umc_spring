package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.ReviewService.ReviewService;
import umc.study.web.dto.ReviewRequestDto;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/review")
    public ApiResponse<String> addReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequestDto.AddReviewDto dto) {

        reviewService.addReview(storeId, dto);
        return ApiResponse.onSuccess("success");
    }
}
