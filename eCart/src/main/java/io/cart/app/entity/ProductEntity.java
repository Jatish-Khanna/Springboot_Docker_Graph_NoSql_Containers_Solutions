package io.cart.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data

// Product Entity class
@Entity
@Table(name = "product")
public class ProductEntity {

  @Id
  @Column(name = "product_id")
  private String productId;
  @Column(name = "product_name")
  private String productName;
  private String description;
  private double price;
  private double discount;
  @Column(name = "delivery_charges")
  private double deliveryCharges;
  @Column(name = "avg_rating")
  private int avgRating;
  @Column(name = "seller_name")
  private String sellerName;

}
