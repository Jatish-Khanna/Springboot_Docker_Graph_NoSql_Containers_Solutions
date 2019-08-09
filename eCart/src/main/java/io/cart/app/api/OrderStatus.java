package io.cart.app.api;

import lombok.Getter;

@Getter
public enum OrderStatus {

  INITIATED("initiated"), UNPAID("unpaid"), PAID("paid"), DELIVERED("delivered"), RETURNED(
      "returned"), CANCELLED("cancelled");
  String orderStatus;

  OrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }
}
