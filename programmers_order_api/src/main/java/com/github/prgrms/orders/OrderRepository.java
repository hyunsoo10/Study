package com.github.prgrms.orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order findById(Long id);
    List<OrderResponseDto> findAll(Long userSeq, long offset, int size);
    OrderResponseDto findWithReviewById(Long orderSeq);
    void update(Order order);
}
