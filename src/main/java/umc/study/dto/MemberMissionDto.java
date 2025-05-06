package umc.study.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberMissionDto {

    private Long memberMissionId;
    private MissionStatus status;
    private LocalDateTime updatedAt;
    private Long missionId;
    private Long storeId;
    private String storeName;
    private Integer reward;
    private LocalDate deadline;
    private String cursor;

    @QueryProjection
    public MemberMissionDto(Long memberMissionId,
                            MissionStatus status,
                            LocalDateTime updatedAt,
                            Long missionId,
                            Long storeId,
                            Integer reward,
                            LocalDate deadline,
                            String storeName,
                            String cursor) {
        this.memberMissionId = memberMissionId;
        this.status = status;
        this.updatedAt = updatedAt;
        this.missionId = missionId;
        this.storeId = storeId;
        this.reward = reward;
        this.deadline = deadline;
        this.storeName = storeName;
        this.cursor = cursor;
    }
}