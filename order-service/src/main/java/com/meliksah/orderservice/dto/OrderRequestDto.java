package com.meliksah.orderservice.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Data
public class OrderRequestDto {

    private List<OrderLineItemDto> orderLineItemDtoList;
}
