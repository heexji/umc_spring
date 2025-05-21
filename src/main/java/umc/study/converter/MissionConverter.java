package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.web.dto.StoreRequestDto;

public class MissionConverter {

    public static Mission toMission(StoreRequestDto.AddMissionDto dto) {
        return Mission.builder()
                .missionSpec(dto.getMissionSpec())
                .deadline(dto.getDeadline())
                .reward(dto.getReward())
                .build();
    }
}
