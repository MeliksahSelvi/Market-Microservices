package com.meliksah.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Data
public class OrderLineItemDto {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer amount;
}
