package com.example.orderapp.service;

import com.example.orderapp.domain.item.Item;
import com.example.orderapp.domain.item.ItemRepository;
import com.example.orderapp.domain.order.Order;
import com.example.orderapp.domain.order.OrderItem;
import com.example.orderapp.domain.order.OrderRepository;
import com.example.orderapp.presentation.dto.OrderItemRequestDto;
import com.example.orderapp.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    //order 생성 메서드
    public OrderResponseDto createOrder(List<OrderItemRequestDto> orderItemRequestDtoList) {

        //1. orderRequestDto 로 order 를 생성
        List<OrderItem> orderItemsList= makeOrderItem(orderItemRequestDtoList);
        //2. 생성된 order 의 경우 재고 수량 감소
        decreaseItemsAmount(orderItemsList);

        //3. Order 생성
        Order order = new Order(orderItemsList);
        orderRepository.add(order);

        //4. OrderResponse 생성
        return OrderResponseDto.toDto(order);
    }

    private List<OrderItem> makeOrderItem(List<OrderItemRequestDto> orderItemRequestDtoList) {

        return orderItemRequestDtoList
                .stream()
                .map(orderItemRequestDto -> {
                    //주문 아이템 id
                    Long itemId = orderItemRequestDto.getId();
                    //itemId가 없는 경우 EntityNotFoundException 예외 발생
                    Item item = itemRepository.findById(itemId);

                    //주문 수량
                    Integer orderAmount = orderItemRequestDto.getAmount();
                    //재고 부족이면 NotEnoughStockException 예외 발생
                    item.checkEnoughAmount(orderAmount);

                    return new OrderItem(
                            itemId,
                            item.getName(),
                            item.getPrice(),
                            orderAmount
                    );
                }).toList();
    }

    private void decreaseItemsAmount(List<OrderItem> orderItemsList) {
        orderItemsList
                .stream()
                .forEach(orderItem -> {
                    Long itemId = orderItem.getId();
                    Item item = itemRepository.findById(itemId);

                    Integer orderAmount = orderItem.getAmount();
                    item.decreaseAmount(orderAmount);
                });
    }

}
