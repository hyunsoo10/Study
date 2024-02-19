package com.example.orderapp.domain.order;

import java.util.List;

public interface OrderRepository {
    Order add(Order order);

    Order findById(Long orderId);

    List<Order> findByStatus(Status status);
}
