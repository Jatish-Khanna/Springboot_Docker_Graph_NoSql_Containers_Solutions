package io.cart.app.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import io.cart.app.api.ProductMapper;
import io.cart.app.controller.specification.ProductSpecification;
import io.cart.app.entity.ProductEntity;
import io.cart.app.exception.resource.ResourceNotFoundException;
import io.cart.app.model.Product;
import io.cart.app.model.Review;
import io.cart.app.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@RestController

public class ProductController extends BaseController {
  // Spring provide bean to call service methods
  ProductService productService;

  // RestTemplate instance to call other services
  RestTemplate restTemplate;

  ProductMapper productMapper;

  ProductSpecification productSpecification;

  private static String REVIEW_SERVICE_URL;

  @Value("${connection.exchange.review.url}")
  public void setReviewUrl(String reviewServiceUrl) {
    REVIEW_SERVICE_URL = reviewServiceUrl;
  }

  // Log for tracing
  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  /**
   * Find the Product details, given product name
   * 
   * @param productName - given product name to fetch all the details for product
   * @return - Product with details
   */
  
  @GetMapping("/api/products/{productId}")
  public ResponseEntity<Product> findProductById(@PathVariable String productId) {
    
    // Find all the details for the product
    Optional<ProductEntity> product = null;
    try {
      product = productService.findProductById(productId);
    } catch (ResourceNotFoundException exception) {
      throw new ResourceNotFoundException(ProductEntity.class, "productId", productId);
    }
    
    Product productDto = productMapper.productEntityToProduct(product.get());
    
    try {
      // Get list of reviews from the review service
      productDto.setReviews(restTemplate.exchange(REVIEW_SERVICE_URL + productDto.getProductId(),
          HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody());
    } catch (ResourceAccessException exception) {
      logger.warn("Review service not working");
    }
    return ResponseEntity.ok(productDto);
  }

  @GetMapping("/api/products")
  public Collection<ProductEntity> findAllProductsBySpecifications(
      @RequestParam MultiValueMap<String, String> specifications) {

    Collection<ProductEntity> productEntities =
        productService.findAllBySpecifications(productSpecification.getFilter(specifications));

    // Empty list should be returned not Exception
    if (productEntities == null || productEntities.isEmpty()) {
      throw new ResourceNotFoundException(ProductEntity.class, specifications);
    }

    return productEntities;
  }


}
