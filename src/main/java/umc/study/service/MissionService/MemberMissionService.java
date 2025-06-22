package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
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
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_CHALLENGING);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);
    }
}