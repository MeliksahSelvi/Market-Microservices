package com.meliksah.orderservice.controller;

import com.meliksah.orderservice.dto.OrderRequestDto;
import com.meliksah.orderservice.dto.OrderResponseDto;
import com.meliksah.orderservice.dto.RestResponse;
import com.meliksah.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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
    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<RestResponse> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
        return CompletableFuture.supplyAsync(()->RestResponse.of(orderResponseDto));
    }

    public CompletableFuture<RestResponse> placeOrderFallbackMethod(OrderRequestDto orderRequestDto, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->RestResponse.of("Ooops! Something went wrong, please order after some time!"));
    }
}
