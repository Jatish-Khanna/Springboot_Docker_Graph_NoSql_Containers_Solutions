package io.cart.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.cart.app.entity.OrderEntity;
import io.cart.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

/**
 * Order Service to perform CRUD operation using repository
 * 
 * @author Jatish_Khanna
 *
 */
@RequiredArgsConstructor
@Service
public class OrderService {
  // Repository instance from spring context
  @Autowired
  OrderRepository orderRepository;

  /**
   * Find the list of Orders from last X days
   * 
   * @param userId - find orders for user
   * @param ordersFromLastDays - number of days in past from current date
   * @param orderStatus - provide the status of order
   * @return - list of Order found for the users in last X days
   */
  public Optional<Collection<OrderEntity>> findOrdersDetailsAfterDate(String userId, String orderStatus,
      int ordersFromLastDays) {
    // Calculate the date in the past - subtract orderFromLastDays
    LocalDateTime dateOneMonthBefore = LocalDate.now().minusDays(ordersFromLastDays).atStartOfDay();

    // List of orders found for the userId
    return Optional.of(orderRepository.findByUserIdAndOrderStatusAndOrderedDateAfter(userId, orderStatus,
        dateOneMonthBefore));
  }
}
