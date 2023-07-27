package com.meliksah.orderservice.controller;

import com.meliksah.orderservice.dto.OrderRequestDto;
import com.meliksah.orderservice.dto.OrderResponseDto;
import com.meliksah.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
        return orderResponseDto;
    }
}
