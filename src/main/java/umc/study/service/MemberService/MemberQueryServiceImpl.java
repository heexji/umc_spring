package umc.study.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.repository.MemberRepository;
import umc.study.web.dto.MemberResponseDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 기능이므로 readOnly = true로 설정하여 성능 최적화
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 요청에서 회원 정보를 추출하여 반환하는 메서드
     * @param request HTTP 요청
     * @return 회원 정보 DTO
     */
    @Override
    public MemberResponseDto.MemberInfoDTO getMemberInfo(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return MemberConverter.toMemberInfoDTO(member);
    }

    /**
     * 특정 회원이 존재하는지 확인하는 메서드
     * @param id 회원 ID
     * @return 회원이 존재하면 true, 아니면 false
     */
    @Override
    public boolean isMemberExist(Long id) {
        return memberRepository.existsById(id);
    }

    /**
     * 회원 ID로 회원을 찾는 메서드 (Optional 반환)
     * @param id 회원 ID
     * @return Optional<Member> - 회원이 존재하지 않을 수 있음을 나타냄
     */
    @Override
    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }
}
