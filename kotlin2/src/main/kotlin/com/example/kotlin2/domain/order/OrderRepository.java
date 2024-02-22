package com.example.kotlin2.domain.order;

import com.example.kotlin2.domain.order.Order;
import com.example.kotlin2.domain.order.Status;

import java.util.List;

public interface OrderRepository {
    Order add(Order order);

    Order findById(Long orderId);

    List<Order> findByStatus(Status status);
}
