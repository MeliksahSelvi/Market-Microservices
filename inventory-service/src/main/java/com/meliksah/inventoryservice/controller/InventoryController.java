package com.meliksah.inventoryservice.controller;

import com.meliksah.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable String skuCode){
        boolean isInStock=inventoryService.isInStock(skuCode);
        return isInStock;
    }
}
