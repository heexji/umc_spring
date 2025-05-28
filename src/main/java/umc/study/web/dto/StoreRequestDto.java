package umc.study.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class StoreRequestDto {

    @Getter
    public static class AddMissionDto {

        @NotNull
        private String missionSpec;

        @NotNull
        @Future
        private LocalDate deadline;

        @NotNull
        private Integer reward;
    }
}
