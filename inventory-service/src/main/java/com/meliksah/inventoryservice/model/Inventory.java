package com.meliksah.inventoryservice.model;

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
@Table(name = "INVENTORY")
public class Inventory {

    @SequenceGenerator(name = "Inventory", sequenceName = "INVENTORY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "Inventory")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "SKU_CODE")
    private String skuCode;

    @Column(name = "AMOUNT")
    private Integer amount;
}
