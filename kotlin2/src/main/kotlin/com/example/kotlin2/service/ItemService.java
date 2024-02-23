package com.example.kotlin2.service;

import com.example.kotlin2.domain.item.Item;
import com.example.kotlin2.domain.item.ItemRepository;
import com.example.kotlin2.presentation.dto.item.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtoList = items.stream()
                .map(item -> ItemDto.toDto(item)).toList();

        return itemDtoList;
    }
}
