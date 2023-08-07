package com.meliksah.orderservice.service;

import com.meliksah.orderservice.dto.InventoryResponse;
import com.meliksah.orderservice.dto.OrderLineItemDto;
import com.meliksah.orderservice.dto.OrderRequestDto;
import com.meliksah.orderservice.dto.OrderResponseDto;
import com.meliksah.orderservice.event.OrderPlacedEvent;
import com.meliksah.orderservice.model.Order;
import com.meliksah.orderservice.model.OrderLineItem;
import com.meliksah.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
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
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        InventoryResponse[] inventoryResponses = accessInventoryService(orderRequestDto);

        checkAllProductIsInStock(inventoryResponses);

        Order order = createOrder(orderRequestDto);

        order = orderRepository.save(order);

        kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));

        OrderResponseDto orderResponseDto = createOrderResponseDto(order);
        return orderResponseDto;
    }

    private InventoryResponse[] accessInventoryService(OrderRequestDto orderRequestDto) {

        List<String> skuCodeList = orderRequestDto.getOrderLineItemDtoList().stream().map(OrderLineItemDto::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCodeList", skuCodeList).build())
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
        List<OrderLineItem> orderLineItemsList = orderRequestDto.getOrderLineItemDtoList().stream()
                .map(this::convertDtoToModel).toList();

        Order order = new Order();
        order.setOrderNumber(randomUUID);
        order.setOrderLineItemList(orderLineItemsList);
        return order;
    }

    private OrderLineItem convertDtoToModel(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setId(orderLineItemDto.getId());
        orderLineItem.setAmount(orderLineItemDto.getAmount());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private OrderResponseDto createOrderResponseDto(Order order) {
        List<OrderLineItemDto> orderLineItemDtoList = order.getOrderLineItemList().stream()
                .map(this::convertModelToDto).toList();

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderNumber(order.getOrderNumber());
        orderResponseDto.setOrderLineItemsList(orderLineItemDtoList);
        return orderResponseDto;
    }

    private OrderLineItemDto convertModelToDto(OrderLineItem orderLineItem) {
        OrderLineItemDto orderLineItemDto = new OrderLineItemDto();
        orderLineItemDto.setId(orderLineItem.getId());
        orderLineItemDto.setAmount(orderLineItem.getAmount());
        orderLineItemDto.setPrice(orderLineItem.getPrice());
        orderLineItemDto.setSkuCode(orderLineItem.getSkuCode());
        return orderLineItemDto;
    }

    public List<OrderResponseDto> getAll() {
        List<Order> orderList = orderRepository.findAll();

        log.info("Fetch All Order");
        List<OrderResponseDto> orderResponseDtoList = orderList.stream().map(this::createOrderResponseDto).toList();
        return orderResponseDtoList;
    }
}
