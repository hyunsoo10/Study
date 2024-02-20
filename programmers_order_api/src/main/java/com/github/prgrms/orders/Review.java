package com.github.prgrms.orders;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Builder
public class Review {

    private Long seq;
    private Long userSeq;
    private Long productSeq;
    private String content;
    private LocalDateTime createAt;

    public Review(Long seq, Long userSeq, Long productSeq, String content, LocalDateTime createAt){
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.content = content;
        this.createAt = defaultIfNull(createAt, now());
    }
}
