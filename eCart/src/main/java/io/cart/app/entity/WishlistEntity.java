package io.cart.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;


// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data

// Wishlist Entity class
@Entity
@Table(name = "wishlist")
public class WishlistEntity {

  @Id
  // ID generation strategy for String is UUID - to guarantee uniqueness
  @GeneratedValue(generator = "hibernateUUIDGenerator")
  @GenericGenerator(name = "hibernateUUIDGenerator", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "wish_id")
  private String wishId;
  @Column(name = "user_id")
  private String userId;
  @Column(name = "display_name")
  private String displayName;
  @Column(name = "short_desc")
  private String shortDesc;
  private String category;
}
