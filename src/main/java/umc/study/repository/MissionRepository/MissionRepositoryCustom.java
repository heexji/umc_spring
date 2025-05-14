package umc.study.repository.MissionRepository;

import umc.study.web.dto.MissionRegionDto;
import java.time.LocalDate;
import java.util.List;

public interface MissionRepositoryCustom {
    List<MissionRegionDto> findMissionByRegionWithCursor(Long regionId, Long memberId, LocalDate deadline, Long missionId);
}
