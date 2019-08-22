package io.cart.app.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

/**
 * Order Entity class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data

@Entity
@Table(name = "order_table")

public class OrderEntity {
  @Id
  @Column(name = "order_id")
  String orderId;
  @Column(name = "display_name")
  String displayName;
  String category;
  @Column(name = "seller_name")
  String sellerName;
  @Column(name = "user_id")
  String userId;
  double price;
  int quantity;
  @Column(name = "total_price")
  double totalPrice;
  @Column(name = "ordered_date")
  // @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  LocalDateTime orderedDate;
  @Column(name = "order_status")
  String orderStatus;

}
