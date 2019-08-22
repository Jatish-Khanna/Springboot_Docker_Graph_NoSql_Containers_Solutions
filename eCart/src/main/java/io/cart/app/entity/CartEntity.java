package io.cart.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Cart Entity class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data
// Prefer - Wrapper classes over Primitives
@Entity
@Table(name = "cart")
public class CartEntity {

  @Id
  @Column(name = "cart_id")
  private String cartId;
  @Column(name = "product_name")
  private String productName;
  @Column(name = "seller_name")
  private String sellerName;
  private int quantity;
  @Column(name = "cartoffer_price")
  private double cartOfferPrice;

}
