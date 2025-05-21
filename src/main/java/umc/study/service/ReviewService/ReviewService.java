package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewRepository; // 리뷰 리포지토리가 있다면 경로에 맞게
import umc.study.web.dto.ReviewRequestDto;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public void addReview(Long storeId, ReviewRequestDto.AddReviewDto dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Review review = ReviewConverter.toReview(dto);
        review.setStore(store);
        review.setMember(member);
        reviewRepository.save(review);
    }
}
