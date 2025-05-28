package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreResponseDto;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);

    StoreResponseDto.ReviewPreViewListDTO getReviewList(Long storeId, Integer page);
    Page<Review> getReviewPage(Long storeId, Integer page); // 이름 변경


}
