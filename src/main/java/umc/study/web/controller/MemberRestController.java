package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberResponseDto;
import umc.study.service.MemberService.MemberCommandService;

@Profile("jwt")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberQueryService memberQueryService; // 추가된 의존성 주입
    private final MemberCommandService memberCommandService;

    @PostMapping("/join")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponseDto.LoginResultDTO> login(@RequestBody @Valid MemberRequestDto.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }
    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<MemberResponseDto.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }
}
