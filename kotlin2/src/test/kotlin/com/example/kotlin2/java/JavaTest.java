package com.example.kotlin2.java;

import com.example.kotlin2.domain.exception.UserNotFoundException;
import com.example.kotlin2.domain.item.Item;
import com.example.kotlin2.presentation.dto.OrderItemRequestDto;
import com.example.kotlin2.presentation.dto.OrderResponseDto;
import com.example.kotlin2.presentation.dto.user.UserSignDto;
import com.example.kotlin2.service.ItemService;
import com.example.kotlin2.service.OrderService;
import com.example.kotlin2.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class JavaTest {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;

//    static List<Item> itemList = new CopyOnWriteArrayList<>();


//    void init() {
//        List<Item> itemList = new CopyOnWriteArrayList<>();
//        Item item1 = new Item(1L, "아이템1", 10000, 100);
//        Item item2 = new Item(2L, "아이템2", 20000, 100);
//        Item item3 = new Item(3L, "아이템3", 30000, 100);
//
//        itemList.add(item1);
//        itemList.add(item2);
//        itemList.add(item3);
//    }

    @Test
    @DisplayName("1-1 회원 - 중복 회원 테스트")
    void user() throws Exception {

        //given
        UserSignDto userSignDto1 = new UserSignDto("test99", "1234", "테스트", "test@test.com", 10);

        //when
        userService.signUp(userSignDto1);

        //then
        UserSignDto userSignDto2 = new UserSignDto("test99", "123234", "테스트2", "test2@test.com", 10);
        Assertions.assertThrows(IllegalStateException.class, () -> userService.signUp(userSignDto2));

    }

    @Test
    @DisplayName("1-2 회원 없음 ")
    void user1_2() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, ()->userService.getUserById("notfound"));
    }

    @Test
    @DisplayName("2-1 주문시 재고 감소")
    void cancel2_1() {

        //given
        List<OrderItemRequestDto> orderItemRequestDtoList = new ArrayList<>();
        orderItemRequestDtoList.add(new OrderItemRequestDto(1L, 10));
        orderItemRequestDtoList.add(new OrderItemRequestDto(2L, 20));

        Item item1 = itemService.findById(1L);
        assertThat(item1.getAmount()).isEqualTo(100);

        //when
        orderService.createOrder(orderItemRequestDtoList);

        //then2
        assertThat(item1.getAmount()).isEqualTo(90);

    }

    @Test
    @DisplayName("2-2 주문 취소시 재고 증가")
    void cancel2_2() {
        List<OrderItemRequestDto> orderItemRequestDtoList = new ArrayList<>();
        orderItemRequestDtoList.add(new OrderItemRequestDto(3L, 299));

        Item item3 = itemService.findById(3L);

        assertThat(item3.getAmount()).isEqualTo(300);
        //when1
        OrderResponseDto order = orderService.createOrder(orderItemRequestDtoList);
        Long id = order.getId();
        //then1
        assertThat(item3.getAmount()).isEqualTo(1);
        //when2
        orderService.cancelOrder(id);
        //then2
        assertThat(item3.getAmount()).isEqualTo(300);

    }
}
