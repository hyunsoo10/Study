package com.example.orderapp.repository;

import com.example.orderapp.domain.exception.EntityNotFoundException;
import com.example.orderapp.domain.order.Order;
import com.example.orderapp.domain.order.OrderRepository;
import com.example.orderapp.domain.order.Status;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private List<Order> orderList = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);
    @Override
    public Order add(Order order) {
        //현재 seq 를 가져온 후에 1을 더함
        order.setId(sequence.getAndAdd(1L));

        orderList.add(order);
        return order;
    }

    @Override
    public Order findById(Long orderId) {
        return orderList
            .stream()
            .filter(order -> order.sameId(orderId))
            .findFirst()
            .orElseThrow(()-> new EntityNotFoundException("Order를 찾지 못했습니다."));
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return orderList
                .stream()
                .filter(order -> order.sameStatus(status))
                .toList();
    }
}
