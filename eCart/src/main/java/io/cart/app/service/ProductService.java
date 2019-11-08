package io.cart.app.service;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import io.cart.app.entity.ProductEntity;
import io.cart.app.exception.resource.ResourceNotFoundException;
import io.cart.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

/**
 * Product service to perform CRUD operations on product store
 * 
 * @author Jatish_Khanna
 *
 */
@RequiredArgsConstructor
@Service
public class ProductService {
  // Product repository to fetch or update product details
  @Autowired
  ProductRepository productRepository;

  public Optional<ProductEntity> findProductById(String productId) {
    Optional<ProductEntity> product = productRepository.findById(productId);
    if(!product.isPresent()) {
      throw new ResourceNotFoundException(ProductEntity.class, "productId", productId);
    }
    return product;
    
  }

  public Collection<ProductEntity> findAllBySpecifications(Specification<ProductEntity> filter) {
    return productRepository.findAll(filter);
  }
}
