package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.security.JwtAuthentication;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.prgrms.utils.ApiUtils.success;
import static com.github.prgrms.utils.ApiUtils.ApiResult;


@RestController
@RequestMapping("api/orders")
public class OrderRestController {
  // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }




}