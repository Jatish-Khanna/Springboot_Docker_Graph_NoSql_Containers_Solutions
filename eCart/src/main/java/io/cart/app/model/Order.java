package io.cart.app.model;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Order model - data transfer class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data
public class Order {
  private String orderId;
  private String displayName;
  private String category;
  private String sellerName;
  private String userId;
  private double price;
  private int quantity;
  private double totalPrice;
  private LocalDateTime orderedDate;
  private String orderStatus;

}
