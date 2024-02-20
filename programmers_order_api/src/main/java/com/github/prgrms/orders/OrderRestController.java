package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotContentException;
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


    @GetMapping(path = "/{id}")
    public ApiResult<OrderResponseDto> findById(@PathVariable Long id) {
        return success(orderService.findById(id));
    }

    @GetMapping
    public ApiResult<List<OrderResponseDto>> findAll(
            @AuthenticationPrincipal JwtAuthentication authentication,
            Pageable pageable
    ) {
        Long userSeq = authentication.id;
        List<OrderResponseDto> orders = orderService.findAll(userSeq, pageable);

        return success(orders);
    }

    @PatchMapping("/{id}/accept")
    public ApiResult<Boolean> accept(@PathVariable Long id) {

        return success(orderService.accept(id));
    }
    @PatchMapping("/{id}/shipping")
    public ApiResult<Boolean> shipping(@PathVariable Long id) {

        return success(orderService.shipping(id));
    }
    @PatchMapping("/{id}/complete")
    public ApiResult<Boolean> complete(@PathVariable Long id) {
        return success(orderService.complete(id));
    }
    @PatchMapping("/{id}/reject")
    public ApiResult<Boolean> reject(@PathVariable Long id, @RequestBody(required = false) RejectRequestDto rejectRequestDto) {
        if(rejectRequestDto == null) throw new NotContentException("no reject message");
        return success(orderService.reject(id, rejectRequestDto));
    }


}