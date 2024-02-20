package com.github.prgrms.orders;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Data
public class OrderResponseDto {
    private Long seq;
    private Long productId;
    private ReviewResponseDto review;
    private OrderState state;
    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public void setReview(Long reviewSeq, Long productSeq, String content, LocalDateTime createAt) {
        this.review = new ReviewResponseDto(reviewSeq, productSeq, content, createAt);
    }
}
