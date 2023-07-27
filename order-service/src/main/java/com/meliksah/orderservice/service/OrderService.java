package com.meliksah.orderservice.service;

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

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Order order = createOrder(orderRequestDto);

        order = orderRepository.save(order);

        OrderResponseDto orderResponseDto = createOrderResponseDto(order);
        return orderResponseDto;
    }

    private OrderResponseDto createOrderResponseDto(Order order) {
        List<OrderLineItemDto> orderLineItemDtoList = OrderLineItemMapper.INSTANCE.orderLineItemListToOrderLineItemDtoList(order.getOrderLineItemList());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderNumber(order.getOrderNumber());
        orderResponseDto.setOrderLineItemsList(orderLineItemDtoList);
        return orderResponseDto;
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
}
