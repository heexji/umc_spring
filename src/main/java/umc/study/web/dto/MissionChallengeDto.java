package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionChallengeDto {

    @Getter
    public static class ChallengeDto {
        @NotNull
        private Long memberId;

        @NotNull
        private Long missionId;
    }
}
