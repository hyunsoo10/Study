package com.example.kotlin2.domain.item;

import com.example.kotlin2.domain.item.Item;
import com.example.kotlin2.domain.order.OrderItem;

import java.util.List;

public interface ItemRepository {

    Item findById(Long id);

    List<Item> findAll();

}
