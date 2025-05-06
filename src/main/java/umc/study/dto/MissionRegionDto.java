package umc.study.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRegionDto {
    private Long missionId;
    private Long storeId;
    private String storeName;
    private String regionName;
    private Integer reward;
    private String missionSpec;
    private LocalDate deadline;
    private Long regionCompletedCount;
    private String cursorValue;

    @QueryProjection
    public MissionRegionDto(Long missionId, Long storeId, String storeName, String regionName,
                            Integer reward, String missionSpec, LocalDate deadline,
                            Long regionCompletedCount, String cursorValue) {
        this.missionId = missionId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.regionName = regionName;
        this.reward = reward;
        this.missionSpec = missionSpec;
        this.deadline = deadline;
        this.regionCompletedCount = regionCompletedCount;
        this.cursorValue = cursorValue;
    }
}
