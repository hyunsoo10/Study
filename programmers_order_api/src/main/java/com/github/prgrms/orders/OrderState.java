package com.github.prgrms.orders;


import com.github.prgrms.errors.CanNotWriteReviewException;
import lombok.Getter;

@Getter
public enum OrderState {
    REQUESTED,
    ACCEPTED,
    SHIPPING,
    COMPLETED{
        @Override
        void checkAvailable(OrderState state){}
    },
    REJECTED;
    
    void checkAvailable(OrderState state){
        throw new CanNotWriteReviewException("Could not write review for order 1 because state(+"+state+") is not allowed");
    }
}
