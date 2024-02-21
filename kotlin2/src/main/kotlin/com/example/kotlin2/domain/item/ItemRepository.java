package com.example.kotlin2.domain.item;

import com.example.kotlin2.domain.item.Item;

public interface ItemRepository {

    Item findById(Long id);

}
