package com.meliksah.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Entity
@Getter
@Setter
@Table(name = "`ORDER`")
public class Order {

    @SequenceGenerator(name = "Order", sequenceName = "ORDER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "Order")
    @Column(name = "ID")
    @Id
    private Long id;

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderLineItem> orderLineItemList;
}
