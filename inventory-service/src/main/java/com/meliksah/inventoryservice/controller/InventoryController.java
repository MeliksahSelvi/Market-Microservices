package com.meliksah.inventoryservice.controller;

import com.meliksah.inventoryservice.dto.InventoryResponse;
import com.meliksah.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getInventoryResponsesBySkuCodes(@RequestParam List<String> skuCodeList) {
        List<InventoryResponse> inventoryListBySkuCodeList = inventoryService.getInventoryListBySkuCodeList(skuCodeList);
        return inventoryListBySkuCodeList;
    }
}
