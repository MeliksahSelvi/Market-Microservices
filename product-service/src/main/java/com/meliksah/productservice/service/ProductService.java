package com.meliksah.productservice.service;

import com.meliksah.productservice.dto.ProductRequestDto;
import com.meliksah.productservice.dto.ProductResponseDto;
import com.meliksah.productservice.mapper.ProductMapper;
import com.meliksah.productservice.model.Product;
import com.meliksah.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mselvi
 * @Created 26.07.2023
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Logger logger = LogManager.getLogger(ProductService.class);

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product product = ProductMapper.INSTANCE.productRequestDtoToProduct(productRequestDto);

        product = productRepository.save(product);

        logger.info("Product {} is saved", product.getId());

        ProductResponseDto responseDto = ProductMapper.INSTANCE.productToProductResponseDto(product);

        return responseDto;
    }

    public List<ProductResponseDto> getAll() {
        List<Product> allProduct = productRepository.findAll();

        List<ProductResponseDto> responseDtoList = ProductMapper.INSTANCE.productListToProductResponseDtoList(allProduct);
        return responseDtoList;
    }

}
