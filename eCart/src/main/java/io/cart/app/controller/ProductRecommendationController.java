package io.cart.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import io.cart.app.api.OrderStatus;
import io.cart.app.api.ProductMapper;
import io.cart.app.controller.specification.ProductSpecification;
import io.cart.app.entity.OrderEntity;
import io.cart.app.entity.ProductEntity;
import io.cart.app.exception.resource.NoOrderHistoryFoundException;
import io.cart.app.exception.resource.NoProductDealsAvailable;
import io.cart.app.exception.resource.NoRecommendationsFound;
import io.cart.app.model.Product;
import io.cart.app.service.OrderService;
import io.cart.app.service.ProductService;


@Controller
public class ProductRecommendationController extends BaseController {

  // Spring provided bean to call service methods
  OrderService orderService;

  // Spring provide bean to call service methods
  ProductService productService;

  ProductMapper productMapper;

  ProductSpecification productSpecification;

  @Value("${user.order.history.days:31}")
  private Integer ordersFromLastDays;

  private static final String SELLER_KEY = "sellerName";
  private static final String USER_KEY = "userId";

  // Products with discount zero
  private static final double ZERO_DISCOUNT = 0.0;

  private static final String NO_PRODUCT_DEALS_FOUND = "No product deals found, today!";


  public ProductRecommendationController(OrderService orderService, ProductService productService,
      ProductMapper productMapper, ProductSpecification productSpecification) {
    this.orderService = orderService;
    this.productService = productService;
    this.productMapper = productMapper;
    this.productSpecification = productSpecification;
  }

  /**
   * Find the recommendations for the user - The recommendations are based on the orders purchased
   * by user within 30 Days
   * 
   * @param userId - Given the userId to recommend products
   * @return - List of recommendations for the user
   */
  @GetMapping("/recommendations")
  public ResponseEntity<Collection<Product>> findProductRecommendationsByDate() {

    // Fetch a list of Orders, [delivered] to user in last 30 Days
    String orderStatus = OrderStatus.DELIVERED.getOrderStatus();
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = user.getUsername();

    Optional<Collection<OrderEntity>> orderDetails =
        orderService.findOrdersDetailsAfterDate(userId, orderStatus, ordersFromLastDays);

    if (orderDetails.get().isEmpty()) {
      throw new NoOrderHistoryFoundException(OrderEntity.class, USER_KEY, userId);
    }

    MultiValueMap<String, String> sellerNameMap = new LinkedMultiValueMap<>();
    List<String> sellerNameList = new ArrayList<>(
        orderDetails.get().stream().map(OrderEntity::getSellerName).collect(Collectors.toSet()));
    sellerNameMap.put(SELLER_KEY, sellerNameList);

    Collection<ProductEntity> recommendedProducts =
        productService.findAllBySpecifications(productSpecification.getFilter(sellerNameMap));
    
    if (recommendedProducts == null || recommendedProducts.isEmpty()) {
      throw new NoRecommendationsFound(ProductEntity.class, sellerNameMap);
    }

    // Fetch the list of products, as a recommendations to user
    return ResponseEntity.ok(productMapper.productEntityListToProductList(recommendedProducts));
  }

  /**
   * Find all the products have discount
   * 
   * @return -
   */
  @GetMapping("/deals")
  public ResponseEntity<Collection<Product>> findAllProductsWithDiscount() {
    // Get list of product with discount
    Collection<ProductEntity> deals = productService
    .findAllBySpecifications(productSpecification.discountGreaterThan(ZERO_DISCOUNT));
    
    if(deals == null || deals.isEmpty()) {
      throw new NoProductDealsAvailable(NO_PRODUCT_DEALS_FOUND);
    }
    
    return ResponseEntity.ok(productMapper.productEntityListToProductList(deals));
  }
}


