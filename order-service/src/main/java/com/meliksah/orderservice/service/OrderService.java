package com.meliksah.orderservice.service;

import com.meliksah.orderservice.dto.InventoryResponse;
import com.meliksah.orderservice.dto.OrderLineItemDto;
import com.meliksah.orderservice.dto.OrderRequestDto;
import com.meliksah.orderservice.dto.OrderResponseDto;
import com.meliksah.orderservice.mapper.OrderLineItemMapper;
import com.meliksah.orderservice.model.Order;
import com.meliksah.orderservice.model.OrderLineItem;
import com.meliksah.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        InventoryResponse[] inventoryResponses = accessInventoryService(orderRequestDto);

        checkAllProductIsInStock(inventoryResponses);

        Order order = createOrder(orderRequestDto);

        order = orderRepository.save(order);

        OrderResponseDto orderResponseDto = createOrderResponseDto(order);
        return orderResponseDto;
    }

    private InventoryResponse[] accessInventoryService(OrderRequestDto orderRequestDto) {

        List<String> skuCodeList = orderRequestDto.getOrderLineItemDtoList().stream().map(OrderLineItemDto::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();//default olarak webclient asynchronous çalışıyor biz block methodu ile synchronous çalışmasını sağladık.
        return inventoryResponses;
    }

    private void checkAllProductIsInStock(InventoryResponse[] inventoryResponses) {
        boolean allProductIsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (Boolean.FALSE.equals(allProductIsInStock)) {
            throw new RuntimeException("Products are not in stock,please check your Order");//TODO exception özelleştirilebilir.
        }
    }

    private Order createOrder(OrderRequestDto orderRequestDto) {
        String randomUUID = getRandomUUID();
        List<OrderLineItem> orderLineItemsList = OrderLineItemMapper.INSTANCE.orderLineItemDtoListToOrderLineItemList(orderRequestDto.getOrderLineItemDtoList());

        Order order = new Order();
        order.setOrderNumber(randomUUID);
        order.setOrderLineItemList(orderLineItemsList);
        return order;
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private OrderResponseDto createOrderResponseDto(Order order) {
        List<OrderLineItemDto> orderLineItemDtoList = OrderLineItemMapper.INSTANCE.orderLineItemListToOrderLineItemDtoList(order.getOrderLineItemList());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderNumber(order.getOrderNumber());
        orderResponseDto.setOrderLineItemsList(orderLineItemDtoList);
        return orderResponseDto;
    }
}
