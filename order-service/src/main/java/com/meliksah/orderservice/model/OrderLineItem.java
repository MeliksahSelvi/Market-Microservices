package com.meliksah.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Entity
@Getter
@Setter
@Table(name = "ORDER_LINE_ITEM")
public class OrderLineItem {

    @SequenceGenerator(name = "OrderLineItem",sequenceName = "ORDER_LINE_ITEM_ID_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "OrderLineItem")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "SKU_CODE")
    private String skuCode;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AMOUNT")
    private Integer amount;
}
