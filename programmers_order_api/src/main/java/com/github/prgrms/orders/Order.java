package com.github.prgrms.orders;


import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class Order {
    private final Long seq;

    private final Long userSeq;

    private final Long productSeq;

    private final Long reviewSeq;

    private OrderState state;

    private String requestMsg;

    private String rejectMsg;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createAt;

    public Order(Order before, Long reviewSeq) {
        this(
                before.getSeq(),
                before.getUserSeq(),
                before.getProductSeq(),
                reviewSeq,
                before.getState(),
                before.getRequestMsg(),
                before.getRejectMsg(),
                before.getCompletedAt(),
                before.getRejectedAt(),
                before.getCreateAt()
        );
    }

    public Order(Long seq, Long userSeq, Long productSeq, Long reviewSeq, OrderState state, String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.reviewSeq = reviewSeq;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = createAt;
    }

    public void canWriteReview() {
        this.state.checkAvailable(this.state);
    }

    public void orderAccepted() {
        this.state = OrderState.ACCEPTED;
    }
    public void shippingAccepted() {
        this.state = OrderState.SHIPPING;
    }
    public void completed() {
        this.state = OrderState.COMPLETED;
    }
    public void rejected() {
        this.state = OrderState.REJECTED;
    }
}
