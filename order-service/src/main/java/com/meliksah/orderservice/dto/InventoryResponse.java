package com.meliksah.orderservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Data
@Builder
public class InventoryResponse {

    private String skuCode;
    private boolean isInStock;
}
