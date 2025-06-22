package umc.study.service.MemberService;

import umc.study.domain.Member;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberResponseDto;

public interface MemberCommandService {
    MemberResponseDto.LoginResultDTO loginMember(MemberRequestDto.LoginRequestDTO request);
    Member joinMember(MemberRequestDto.JoinDto request);
}
