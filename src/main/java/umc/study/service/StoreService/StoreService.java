package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.StoreRequestDto;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public void addMissionToStore(Long storeId, StoreRequestDto.AddMissionDto dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(dto);
        mission.setStore(store);
        missionRepository.save(mission);
    }
}
