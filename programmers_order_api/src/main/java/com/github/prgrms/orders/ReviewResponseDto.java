package com.github.prgrms.orders;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Long seq;
    private Long productId;
    private String content;
    private LocalDateTime createAt;

    public ReviewResponseDto(Long seq, Long productId, String content, LocalDateTime createAt) {
        this.seq = seq;
        this.productId = productId;
        this.content = content;
        this.createAt = createAt;
    }

    public static ReviewResponseDto toDto(Review review) {
        return new ReviewResponseDto(review.getSeq(), review.getProductSeq(), review.getContent(), review.getCreateAt());
    }

}
