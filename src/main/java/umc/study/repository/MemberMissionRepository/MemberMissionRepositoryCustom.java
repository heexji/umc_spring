package umc.study.repository.MemberMissionRepository;

import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.MemberMissionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMissionDto> findMissionsWithCursor(
            Long memberId,
            MissionStatus status,
            LocalDateTime updatedAt,
            Long missionId,
            int pageSize
    );
}

