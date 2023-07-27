package com.meliksah.productservice.repository;

import com.meliksah.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author mselvi
 * @Created 26.07.2023
 */

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
