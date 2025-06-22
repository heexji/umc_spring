package umc.study.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.domain.Member;
import umc.study.web.dto.MemberResponseDto;

import java.util.Optional;

public interface MemberQueryService {

    /**
     * 요청에서 회원 정보를 추출하여 반환하는 메서드
     * @param request HTTP 요청
     * @return 회원 정보 DTO
     */
    MemberResponseDto.MemberInfoDTO getMemberInfo(HttpServletRequest request);

    /**
     * 특정 회원이 존재하는지 확인하는 메서드
     * @param id 회원 ID
     * @return 회원이 존재하면 true, 아니면 false
     */
    boolean isMemberExist(Long id);

    /**
     * 회원 ID로 회원을 찾는 메서드 (Optional 반환)
     * @param id 회원 ID
     * @return Optional<Member> - 회원이 존재하지 않을 수 있음을 나타냄
     */
    Optional<Member> findMemberById(Long id);
}