package com.example.kotlin2.domain.item;

import com.example.kotlin2.domain.exception.NotEnoughStockException;

public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Item(Long id, String name, Integer price, Integer amount) {
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

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    //재고 수량 검증 메서드
    public void checkEnoughAmount(Integer amount) {
        if(this.amount < amount) throw new NotEnoughStockException(this.id + "번 상품의 수량이 부족합니다.");
    }

    //제고 감소 메서드
    public void decreaseAmount(Integer amount) {
        this.amount -= amount;
    }


    //재고 증가 메서드
    public void increaseAmount(Integer amount) {
        this.amount += amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
