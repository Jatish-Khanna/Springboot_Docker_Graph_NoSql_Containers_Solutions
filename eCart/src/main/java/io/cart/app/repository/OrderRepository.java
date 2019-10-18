package io.cart.app.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.cart.app.entity.OrderEntity;

/**
 * Order repository - provide implementation to CRUD operations
 * 
 * @author Jatish_Khanna
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

  /**
   * To find the Order based on following criteria - 
   * - UserId must match 
   * - Order Status must match 
   * - OrderDate
   * 
   * @param userId - Order for user
   * @param orderStatus - Order status
   * @param ordersFromLastDays - to look for order for last X days 
   * @return - a list of Orders match the give criteria
   */
  List<OrderEntity> findByUserIdAndOrderStatusAndOrderedDateAfter(String userId, String orderStatus,
      LocalDateTime ordersFromLastDays);
}
