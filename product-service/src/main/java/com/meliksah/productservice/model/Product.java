package com.meliksah.productservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @Author mselvi
 * @Created 26.07.2023
 */

@Document(value = "product")
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
