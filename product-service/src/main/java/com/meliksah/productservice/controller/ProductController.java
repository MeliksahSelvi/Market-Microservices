package com.meliksah.productservice.controller;

import com.meliksah.productservice.dto.ProductRequestDto;
import com.meliksah.productservice.dto.ProductResponseDto;
import com.meliksah.productservice.dto.RestResponse;
import com.meliksah.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mselvi
 * @Created 26.07.2023
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto responseDto = productService.createProduct(productRequestDto);
        return RestResponse.of(responseDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAll(){
        List<ProductResponseDto> responseDtoList=productService.getAll();
        return RestResponse.of(responseDtoList);
    }


}
