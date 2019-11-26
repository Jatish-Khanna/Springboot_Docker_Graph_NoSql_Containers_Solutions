package io.cart.app.model;

import lombok.Data;

/**
 * Wishlist Model - data transfer class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data
public class Wishlist {

  private String wishId;
  private String userId;
  private String displayName;
  private String shortDesc;
  private String category;

}
