package com.example.kotlin2.domain.order;

import com.example.kotlin2.domain.exception.CanNotCancellableStatusException;

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
