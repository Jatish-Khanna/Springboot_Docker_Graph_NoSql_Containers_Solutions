package io.cart.app.model;

import lombok.Data;

/**
 * Cart model- data transfer class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data
public class Cart {

  private String cartId;
  private String productName;
  private String sellerName;
  private int quantity;
  private double cartOfferPrice;

}
