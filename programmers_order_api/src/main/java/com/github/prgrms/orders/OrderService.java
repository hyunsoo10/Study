package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderResponseDto findById(Long id) {
        return orderRepository.findWithReviewById(id);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> findAll(Long userSeq, Pageable pageable) {
        long offset = pageable.getOffset();
        int size = pageable.getSize();

        return orderRepository.findAll(userSeq, offset, size);
    }

    public Boolean accept(Long id) {
        Order order = orderRepository.findById(id);
        OrderState state = order.getState();

        if(state.equals(OrderState.REQUESTED)){
            order.orderAccepted();
            orderRepository.update(order);
            return true;
        }
        return false;
    }
    public Boolean shipping(Long id) {
        Order order = orderRepository.findById(id);
        OrderState state = order.getState();

        if(state.equals(OrderState.ACCEPTED)){
            order.shippingAccepted();
            orderRepository.update(order);
            return true;
        }
        return false;
    }
    public Boolean complete(Long id) {
        Order order = orderRepository.findById(id);
        OrderState state = order.getState();

        if(state.equals(OrderState.SHIPPING)){
            order.completed();
            order.setCompletedAt(LocalDateTime.now());
            orderRepository.update(order);
            return true;
        }
        return false;
    }
    public Boolean reject(Long id, RejectRequestDto rejectRequestDto) {
        Order order = orderRepository.findById(id);
        OrderState state = order.getState();

        if(state.equals(OrderState.REQUESTED)){
            order.rejected();
            order.setRejectMsg(rejectRequestDto.getMessage());
            order.setRejectedAt(LocalDateTime.now());
            orderRepository.update(order);
            return true;
        }
        return false;
    }
}
