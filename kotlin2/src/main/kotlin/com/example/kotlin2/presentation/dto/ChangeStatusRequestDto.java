package com.example.kotlin2.presentation.dto;

import com.example.kotlin2.domain.order.Status;

public class ChangeStatusRequestDto {

    private Status status;

    public Status getStatus() {
        return status;
    }
}
