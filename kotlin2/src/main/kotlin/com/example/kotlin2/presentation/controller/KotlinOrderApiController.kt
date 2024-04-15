package com.example.kotlin2.presentation.controller

import com.example.kotlin2.domain.item.ItemRepository
import com.example.kotlin2.domain.order.OrderRepository
import com.example.kotlin2.domain.order.Status
import com.example.kotlin2.presentation.dto.ApiResponse
import com.example.kotlin2.presentation.dto.ChangeStatusRequestDto
import com.example.kotlin2.presentation.dto.OrderItemRequestDto
import com.example.kotlin2.presentation.dto.OrderResponseDto
import com.example.kotlin2.repository.ItemRepositoryImpl
import com.example.kotlin2.repository.OrderRepositoryImpl
import com.example.kotlin2.service.OrderService
import jdk.jfr.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class KotlinOrderApiController (private val orderService: OrderService) {

    @Description("상품 주문 API")
    @PostMapping
    fun createOrder(@RequestBody orderRequestDtoList: List<OrderItemRequestDto?>?): ResponseEntity<OrderResponseDto> {
        val orderResponseDto: OrderResponseDto = orderService.createOrder(orderRequestDtoList)
        return ResponseEntity.ok(orderResponseDto)
    }

    @Description("주문 상태 변경 API")
    @PatchMapping("/{orderId}")
    fun changeOrderStatus(
            @PathVariable("orderId") orderId: Long?,
            @RequestBody changeStatusRequestDto: ChangeStatusRequestDto?
    ): ResponseEntity<OrderResponseDto> {
        val orderResponseDto: OrderResponseDto = orderService.changeStatus(orderId, changeStatusRequestDto)
        return ResponseEntity.ok(orderResponseDto)
    }

    @Description("주문 번호로 조회 API")
    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Long?): ApiResponse<OrderResponseDto> {
        val orderResponseDto: OrderResponseDto = orderService.findById(orderId)
        return ApiResponse(orderResponseDto, "success")
    }

    @Description("주문 상태로 조회 API")
    @GetMapping
    fun getOrderByStatus(@RequestParam status: Status?): ResponseEntity<List<OrderResponseDto>> {
        val orderResponseDtoList: List<OrderResponseDto> = orderService.findByStatus(status)
        return ResponseEntity.ok(orderResponseDtoList)
    }

    @Description("주문 취소 API")
    @PatchMapping("/{orderId}/cancel")
    fun cancelOrder(@PathVariable orderId: Long?): ResponseEntity<OrderResponseDto> {
        val orderService = OrderService(itemRepository(), orderRepository())
        val orderResponseDto: OrderResponseDto = orderService.cancelOrder(orderId)
        return ResponseEntity.ok(orderResponseDto)
    }


    fun orderRepository(): OrderRepository = OrderRepositoryImpl()
    fun itemRepository(): ItemRepository = ItemRepositoryImpl()
}