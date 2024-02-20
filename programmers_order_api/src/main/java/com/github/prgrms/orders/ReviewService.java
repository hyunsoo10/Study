package com.github.prgrms.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final JdbcReviewRepository reviewRepository;


    public ReviewResponseDto review(Long id, ReviewRequestDto reviewRequestDto) {

        Optional<Review> review = reviewRepository.findById(id);
    }

}
