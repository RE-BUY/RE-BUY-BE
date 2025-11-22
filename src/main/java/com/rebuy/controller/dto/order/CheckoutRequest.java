package com.rebuy.controller.dto.order;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckoutRequest {
    @Min(0)
    private Integer creditToUse;

    private String receiverName;
    private String address;
    private String contactPhone;
}