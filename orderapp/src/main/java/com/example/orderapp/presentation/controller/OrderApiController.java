package com.example.orderapp.presentation.controller;

import com.example.orderapp.presentation.dto.OrderItemRequestDto;
import com.example.orderapp.presentation.dto.OrderResponseDto;
import com.example.orderapp.service.OrderService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderApiController {

    private OrderService orderService;

    @Autowired
    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Description("상줌 주문 API")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<OrderItemRequestDto> orderRequestDtoList) {
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDtoList);
        return ResponseEntity.ok(orderResponseDto);

    }

}
