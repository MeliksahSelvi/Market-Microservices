package com.meliksah.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author mselvi
 * @Created 26.07.2023
 */


@Data
public class ProductResponseDto{

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
