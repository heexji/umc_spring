package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.web.dto.StoreResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository; // ✅ 추가

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredStores.forEach(store -> System.out.println("Store: " + store));
        return filteredStores;
    }

    @Override
    public StoreResponseDto.ReviewPreViewListDTO getReviewList(Long storeId, Integer page) {
        Page<Review> reviewPage = getReviewPage(storeId, page); // 내부 메서드 재사용

        return StoreResponseDto.ReviewPreViewListDTO.builder()
                .reviewList(
                        reviewPage.getContent().stream()
                                .map(review -> StoreResponseDto.ReviewPreViewDTO.builder()
                                        .ownerNickname(
                                                review.getMember() != null ? review.getMember().getName() : "알 수 없음"
                                        )
                                        .score(review.getScore())
                                        .body(review.getBody())
                                        .createdAt(review.getCreatedAt().toLocalDate())
                                        .build()
                                ).collect(Collectors.toList())
                )
                .listSize(reviewPage.getNumberOfElements())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    @Override
    public Page<Review> getReviewPage(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }
}
