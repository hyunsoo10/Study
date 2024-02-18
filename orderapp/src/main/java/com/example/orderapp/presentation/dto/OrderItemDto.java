package com.example.orderapp.presentation.dto;

import com.example.orderapp.domain.order.OrderItem;

public class OrderItemDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public OrderItemDto(Long id, String name, Integer price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public static OrderItemDto toDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getName(),
                orderItem.getPrice(),
                orderItem.getAmount()
        );
    }


}
