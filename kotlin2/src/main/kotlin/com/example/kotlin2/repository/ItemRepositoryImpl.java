package com.example.kotlin2.repository;

import com.example.kotlin2.domain.exception.EntityNotFoundException;
import com.example.kotlin2.domain.item.Item;
import com.example.kotlin2.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    static List<Item> itemList = new CopyOnWriteArrayList<>();

    @PostConstruct
    void initItems() {
        Item item1 = new Item(1L, "아이템1", 10000, 100);
        Item item2 = new Item(2L, "아이템2", 20000, 200);
        Item item3 = new Item(3L, "아이템3", 30000, 300);

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
    }

    @Override
    public Item findById(Long id) {
        return itemList
                .stream()
                .filter(item -> item.sameId(id))
                .findFirst()
                .orElseThrow(()->new EntityNotFoundException("Item을 찾지 못했습니다."));
    }
}
