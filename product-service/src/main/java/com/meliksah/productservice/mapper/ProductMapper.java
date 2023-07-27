package com.meliksah.productservice.mapper;

import com.meliksah.productservice.dto.ProductRequestDto;
import com.meliksah.productservice.dto.ProductResponseDto;
import com.meliksah.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author mselvi
 * @Created 27.07.2023
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponseDto productToProductResponseDto(Product product);

    List<ProductResponseDto> productListToProductResponseDtoList(List<Product> productList);

    Product productRequestDtoToProduct(ProductRequestDto productRequestDto);
}
