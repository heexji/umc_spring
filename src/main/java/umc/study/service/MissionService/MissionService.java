package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.converter.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionResponseDto;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * 미션 도전 API
     */
    @Transactional
    public void challenge(Long missionId) {
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        boolean alreadyChallenging = memberMissionRepository.existsByMemberAndMission(member, mission);
        if (alreadyChallenging) {
            throw new IllegalStateException("이미 도전 중인 미션입니다.");
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);
    }

    /**
     * 내가 작성한 리뷰 목록 API
     */
    public MissionResponseDto.ReviewList getMyReviews(Long memberId, int page) {
        Page<Review> reviewPage = reviewRepository.findAllByMemberId(memberId, PageRequest.of(page - 1, 10));
        return MissionConverter.toReviewList(reviewPage);
    }

    /**
     * 특정 가게의 미션 목록 API
     */
    public MissionResponseDto.MissionList getMissionsByStore(Long storeId, int page) {
        Page<Mission> missionPage = missionRepository.findAllByStoreId(storeId, PageRequest.of(page - 1, 10));
        return MissionConverter.toMissionList(missionPage);
    }

    /**
     * 내가 진행 중인 미션 목록 API
     */
    public MissionResponseDto.MissionList getMyOngoingMissions(Long memberId, int page) {
        Page<MemberMission> missionPage = memberMissionRepository.findByMemberIdAndStatus(
                memberId, MissionStatus.CHALLENGING, PageRequest.of(page - 1, 10));
        return MissionConverter.toMissionListFromMemberMission(missionPage);
    }

    /**
     * 진행중인 미션 완료 처리 API
     */
    @Transactional
    public void completeMission(Long missionId, Long memberId) {
        MemberMission memberMission = memberMissionRepository.findByMissionIdAndMemberId(missionId, memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 없음"));
        memberMission.complete(); // 상태를 COMPLETE로 변경하는 메서드
        memberMissionRepository.save(memberMission);
    }
}
