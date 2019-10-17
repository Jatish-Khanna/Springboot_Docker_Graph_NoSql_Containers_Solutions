package io.cart.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import io.cart.app.entity.ProductEntity;

/**
 * Product repository - provide implementation to CRUD operations
 * 
 * @author Jatish_Khanna
 *
 */
@Repository
public interface ProductRepository
    extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
  /**
   * Find the list of product match the following criteria: - The keyword should be present in the
   * product name - Ignore the case of product name - Order the result by price in ASC order
   * 
   * @param productName - keyword to be searched in the product name
   * @return - List of products satisfies the following criteria
   */
  public List<ProductEntity> findByProductNameContainingIgnoreCaseOrderByPrice(String productName);

  /**
   * Find the list of Products match the following criteria: - Seller name should match - Product
   * name should contain the keyword, ignore case
   * 
   * @param sellerName - given seller name
   * @param productName - given product keyword
   * @return - list of products match the criteria
   */
  public List<ProductEntity> findBySellerNameAndProductNameContainingIgnoreCase(String sellerName,
      String productName);

  /**
   * Find the list of products which offers discount
   * 
   * @param discount - Give the discount should be greater than specified
   * @return - List of product match the criteria
   */
  public List<ProductEntity> findByDiscountGreaterThan(double discount);

  /**
   * Find the product that match the following criteria
   * 
   * @param productName - product name should be same as given, ignore case
   * @return - the product details
   */
  public ProductEntity findByProductNameIgnoreCase(String productName);

  /**
   * Find the list of products that match the criteria
   * 
   * @param sellerName - give the seller name
   * @return - list of products whose seller is specified
   */
  public List<ProductEntity> findBySellerName(String sellerName);
}
