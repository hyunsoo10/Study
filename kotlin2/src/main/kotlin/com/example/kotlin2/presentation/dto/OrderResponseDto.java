package com.example.kotlin2.presentation.dto;

import com.example.kotlin2.domain.order.Order;
import com.example.kotlin2.domain.order.Status;

import java.util.List;

public class OrderResponseDto {

    private Long id;
    private List<OrderItemDto> orderItems;
    private Integer totalPrice;
    private Integer totalAmount;
    private Status status;

    public Long getId() {
        return id;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public OrderResponseDto(Long id, List<OrderItemDto> orderItems, Integer totalPrice, Integer totalAmount, Status status) {
        this.id = id;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    //Order 를 인자로 받았을 때 OrderResponseDto 를 return 하는 메서드
    public static OrderResponseDto toDto(Order order) {
        List<OrderItemDto> orderItemDtoList = order.getOrderItemList()
                .stream()
                .map(orderItem -> OrderItemDto.toDto(orderItem))
                .toList();

        return new OrderResponseDto(
                order.getId(),
                orderItemDtoList,
                order.getTotalPrice(),
                order.getTotalAmount(),
                order.getStatus()
        );
    }
}
