package umc.study.service.MemberService;

import umc.study.domain.Member;
import umc.study.web.dto.MemberRequestDto;

public interface MemberCommandService {
    Member joinMember(MemberRequestDto.JoinDto request);
}
