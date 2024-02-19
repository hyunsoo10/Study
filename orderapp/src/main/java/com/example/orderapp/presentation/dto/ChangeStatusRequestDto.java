package com.example.orderapp.presentation.dto;

import com.example.orderapp.domain.order.Status;

public class ChangeStatusRequestDto {

    private Status status;

    public Status getStatus() {
        return status;
    }
}
