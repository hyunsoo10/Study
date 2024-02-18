package com.example.orderapp.domain.member;


import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private Long id;

    private Address address;

    private List<Order> orderList= new ArrayList<>();
}
