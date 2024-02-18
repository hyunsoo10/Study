package com.example.orderapp.domain.item;

import com.example.orderapp.domain.item.Item;

public interface ItemRepository {

    Item findById(Long id);

}
