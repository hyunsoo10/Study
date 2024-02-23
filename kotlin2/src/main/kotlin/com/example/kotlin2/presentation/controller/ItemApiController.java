package com.example.kotlin2.presentation.controller;


import com.example.kotlin2.presentation.dto.item.ItemDto;
import com.example.kotlin2.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemApiController {

    private final ItemService itemService;


    @Autowired
    public ItemApiController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<?> findAllItem() {
        List<ItemDto> allItem = itemService.findAll();
        return ResponseEntity.ok(allItem);
    }
}
