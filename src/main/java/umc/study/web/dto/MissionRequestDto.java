package umc.study.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class AddMissionDto {
        @NotNull
        private Integer reward;

        @Future
        private LocalDate deadline;

        @NotNull
        private String missionSpec;

        @NotNull
        private Long storeId;
    }
}
