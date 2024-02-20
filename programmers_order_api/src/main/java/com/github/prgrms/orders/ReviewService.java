package com.github.prgrms.orders;

import com.github.prgrms.errors.DuplicateReviewException;
import com.github.prgrms.errors.NotContentException;
import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public ReviewResponseDto review(Long orderSeq, ReviewRequestDto reviewRequestDto) {


        //주문 확인
        Order order = orderRepository.findById(orderSeq);

        //리뷰 본문 내용이 있는지 확인
        String content = reviewRequestDto.getContent();
        if(content == null || content.isEmpty()) throw new NotContentException("no content");

        //리뷰 본문 길이 확인
        if(content.length() > 1000) throw new IllegalArgumentException();

        //중복 리뷰인지 확인
        if(order.getReviewSeq() != 0) throw new DuplicateReviewException("Could not write review for order " + orderSeq + " because have already written");

        //주문 완료 상태인지 확인
        order.canWriteReview();

        Long productSeq = order.getProductSeq();
        Product product = productRepository.findById(productSeq).orElseThrow(IllegalArgumentException::new);

        //상품의 review count 증가
        product.addReviewCount();

        //리뷰 생성
        Review review = reviewRepository.save(new Review(order.getUserSeq(), productSeq, content));

        Order orderWithReview = new Order(order, review.getSeq());

        //상품의 review count 증가 업데이트
        productRepository.update(product);
        orderRepository.update(orderWithReview);

        return ReviewResponseDto.toDto(review);
    }

}
