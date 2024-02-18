package com.example.orderapp.domain.order;

import java.util.List;

public class Order {

    private Long id;
    private List<OrderItem> orderItemList;

    private Integer totalPrice; //총 주문 가격
    private Integer totalAmount; // 총 주문 수량
    private Status status;

    public Order(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
        this.totalPrice = calculateTotalPrice(orderItemList);
        this.totalAmount = calculateTotalAmount(orderItemList);
        this.status = Status.CREATED;
    }

    public Long getId() {
        return id;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
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

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public boolean sameStatus(Status status) {
        return this.status.equals(status);
    }

    public void setId(Long id) {
        this.id = id;
    }


    // orderItemList 에서 총 가격 계산 메서드
    private Integer calculateTotalPrice(List<OrderItem> orderItemList) {
        return orderItemList
                .stream()
                .mapToInt(orderItem -> orderItem.getPrice() * orderItem.getAmount())
                .sum();
    }

    private Integer calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList
                .stream()
                .mapToInt(orderItem -> orderItem.getAmount())
                .sum();
    }
}