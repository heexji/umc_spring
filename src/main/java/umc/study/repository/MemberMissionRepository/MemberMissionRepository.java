package umc.study.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

    // 특정 유저가 특정 미션을 수행 중인지 확인
    boolean existsByMemberAndMission(Member member, Mission mission);

    // 특정 유저가 수행 중인 미션들 (진행 상태)
    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);

    // 특정 유저의 전체 미션 이력 (모든 상태)
    Page<MemberMission> findByMember(Member member, Pageable pageable);

    // 특정 유저의 특정 미션 도전 이력
    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);

    Optional<MemberMission> findByMissionIdAndMemberId(Long missionId, Long memberId);

}
