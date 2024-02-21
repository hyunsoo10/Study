//package com.example.kotlin2.presentation.controller;
//
//import com.example.kotlin2.domain.order.Status;
//import com.example.kotlin2.presentation.dto.ChangeStatusRequestDto;
//import com.example.kotlin2.presentation.dto.OrderItemRequestDto;
//import com.example.kotlin2.presentation.dto.OrderResponseDto;
//import com.example.kotlin2.service.OrderService;
//import jdk.jfr.Description;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/orders")
//public class OrderApiController {
//
//    private OrderService orderService;
//
//    @Autowired
//    public OrderApiController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @Description("상품 주문 API")
//    @PostMapping
//    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<OrderItemRequestDto> orderRequestDtoList) {
//        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDtoList);
//        return ResponseEntity.ok(orderResponseDto);
//
//    }
//
//    @Description("주문 상태 변경 API")
//    @PatchMapping("/{orderId}")
//    public ResponseEntity<OrderResponseDto> changeOrderStatus(
//            @PathVariable("orderId") Long orderId,
//            @RequestBody ChangeStatusRequestDto changeStatusRequestDto
//    ) {
//        OrderResponseDto orderResponseDto = orderService.changeStatus(orderId, changeStatusRequestDto);
//        return ResponseEntity.ok(orderResponseDto);
//    }
//
//    @Description("주문 번호로 조회 API")
//    @GetMapping("/{orderId}")
//    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
//        OrderResponseDto orderResponseDto = orderService.findById(orderId);
//        return ResponseEntity.ok(orderResponseDto);
//    }
//
//    @Description("주문 상태로 조회 API")
//    @GetMapping
//    public ResponseEntity<List<OrderResponseDto>> getOrderByStatus(@RequestParam Status status) {
//        List<OrderResponseDto> orderResponseDtoList = orderService.findByStatus(status);
//        return ResponseEntity.ok(orderResponseDtoList);
//    }
//
//    @Description("주문 취소 API")
//    @PatchMapping("/{orderId}/cancel")
//    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long orderId) {
//        OrderResponseDto orderResponseDto = orderService.cancelOrder(orderId);
//        return ResponseEntity.ok(orderResponseDto);
//    }
//}
