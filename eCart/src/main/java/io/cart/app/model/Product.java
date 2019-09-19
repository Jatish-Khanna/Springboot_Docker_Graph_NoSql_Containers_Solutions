package io.cart.app.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * Product model - data transfer class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data
@JsonInclude(Include.NON_NULL)
public class Product {

  private String productId;
  private String productName;
  private String description;
  private Double price;
  private Double discount;
  private Double deliveryCharges;
  private Integer avgRating;
  private String sellerName;
  private List<Review> reviews;

}
