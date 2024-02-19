package com.example.orderapp.domain.order;

import com.example.orderapp.domain.exception.CanNotCancellableStatusException;
import com.example.orderapp.domain.exception.EntityNotFoundException;

public enum Status {
    CREATED{
        @Override
        void checkCancellable() {}
    },
    SHIPPING,
    COMPLETED,
    CANCELED;

    void checkCancellable() {
        throw new CanNotCancellableStatusException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
    }
}
