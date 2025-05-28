package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.web.dto.MissionChallengeDto;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public void challengeMission(MissionChallengeDto.ChallengeDto dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new MemberHandler("존재하지 않는 유저입니다."));

        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new MissionHandler("존재하지 않는 미션입니다."));

        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionHandler("이미 도전 중인 미션입니다.");
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);
    }
}
