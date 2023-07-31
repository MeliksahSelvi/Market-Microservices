package com.meliksah.productservice.service;

import com.meliksah.productservice.dto.ProductRequestDto;
import com.meliksah.productservice.dto.ProductResponseDto;
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

        Product product = convertDtoToModel(productRequestDto);

        product = productRepository.save(product);

        logger.info("Product {} is saved", product.getId());

        ProductResponseDto responseDto = convertModelToDto(product);

        return responseDto;
    }

    private Product convertDtoToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setPrice(productRequestDto.getPrice());
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        return product;
    }

    private ProductResponseDto convertModelToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        return productResponseDto;
    }

    public List<ProductResponseDto> getAll() {
        List<Product> allProduct = productRepository.findAll();

        List<ProductResponseDto> responseDtoList = allProduct.stream().map(this::convertModelToDto).toList();
        return responseDtoList;
    }

}
