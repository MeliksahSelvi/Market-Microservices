package com.meliksah.inventoryservice.service;

import com.meliksah.inventoryservice.dto.InventoryResponse;
import com.meliksah.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> getInventoryListBySkuCodeList(List<String> skuCodeList) {
        return inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getAmount() > 0)
                                .build()
                ).toList();
    }
}
