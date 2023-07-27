package com.meliksah.orderservice.dto;


import lombok.Data;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Data
public class OrderResponseDto {

    private Long id;
    private String orderNumber;
    private List<OrderLineItemDto> orderLineItemsList;
}
