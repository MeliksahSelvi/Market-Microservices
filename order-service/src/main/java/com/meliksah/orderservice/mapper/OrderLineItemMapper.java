package com.meliksah.orderservice.mapper;

import com.meliksah.orderservice.dto.OrderLineItemDto;
import com.meliksah.orderservice.model.OrderLineItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLineItemMapper {

    OrderLineItemMapper INSTANCE = Mappers.getMapper(OrderLineItemMapper.class);

    List<OrderLineItem> orderLineItemDtoListToOrderLineItemList(List<OrderLineItemDto> orderLineItemDtoList);

    List<OrderLineItemDto> orderLineItemListToOrderLineItemDtoList(List<OrderLineItem> orderLineItemList);

    OrderLineItem orderLineItemDtoToOrderLineItem(OrderLineItemDto orderLineItemDto);

    OrderLineItemDto orderLineItemToOrderLineItemDto(OrderLineItem orderLineItem);
}
