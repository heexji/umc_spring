package umc.study.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberRepository;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.MemberRequestDto.LoginRequestDTO;
import umc.study.web.dto.MemberResponseDto;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {

        // 1. 사용자 정보 변환
        Member newMember = MemberConverter.toMember(request);

        // 2. 비밀번호 암호화
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        // 3. 선호 카테고리 조회 및 예외 처리
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(categoryId -> foodCategoryRepository.findById(categoryId)
                        .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                .collect(Collectors.toList());

        // 4. MemberPrefer 리스트 생성
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 5. 양방향 연관관계 설정
        memberPreferList.forEach(memberPrefer -> memberPrefer.setMember(newMember));
        newMember.setMemberPreferList(memberPreferList);

        // 6. cascade = ALL 설정이 되어 있으면 이것만으로 전부 저장됨
        return memberRepository.save(newMember);
    }

    @Override
    public MemberResponseDto.LoginResultDTO loginMember(LoginRequestDTO dto) {
        // 로그인 로직 구현
        return null;
    }

}
