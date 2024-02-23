package com.example.kotlin2.presentation.dto.item;

import com.example.kotlin2.domain.item.Item;

public class ItemDto {

    String name;
    Integer price;
    Integer quantity;

    public ItemDto(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static ItemDto toDto(Item item) {
        return new ItemDto(item.getName(), item.getPrice(), item.getAmount());
    }
}
