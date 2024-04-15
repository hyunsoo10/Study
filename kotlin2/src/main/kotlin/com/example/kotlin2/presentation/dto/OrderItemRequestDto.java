package com.example.kotlin2.presentation.dto;

public class OrderItemRequestDto {

    private Long id;
    private Integer amount;

    public OrderItemRequestDto(Long id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

}
