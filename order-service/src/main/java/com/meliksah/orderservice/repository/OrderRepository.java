package com.meliksah.orderservice.repository;

import com.meliksah.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
