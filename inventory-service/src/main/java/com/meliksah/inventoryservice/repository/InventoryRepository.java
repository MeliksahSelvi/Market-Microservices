package com.meliksah.inventoryservice.repository;

import com.meliksah.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findBySkuCodeIn(List<String> skuCodeList);
}
