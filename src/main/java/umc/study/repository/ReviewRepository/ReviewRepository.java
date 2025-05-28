package umc.study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Review;
import umc.study.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 스토어 ID로 리뷰 목록 페이징 조회
    Page<Review> findAllByStoreId(Long storeId, Pageable pageable);
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);

}
