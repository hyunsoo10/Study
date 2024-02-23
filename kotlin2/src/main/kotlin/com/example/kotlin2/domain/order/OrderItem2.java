package com.example.kotlin2.domain.order;


import com.example.kotlin2.domain.item.Item;

public class OrderItem2 {
    private Long id;
    private Item item;
    private Integer orderPrice;
    private Integer orderAmount;


    public OrderItem2(Long id, Item item, Integer orderPrice, Integer orderAmount) {
        this.id = id;
        this.item = item;
        this.orderPrice = orderPrice;
        this.orderAmount = orderAmount;
    }

    public Long getId() {
        return id;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public Item getItem() {
        return item;
    }

    public void cancel() {
        getItem().addAmount(this.orderAmount);
    }
}
