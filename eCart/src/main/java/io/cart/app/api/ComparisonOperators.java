package io.cart.app.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum ComparisonOperators {
  GREATER_THAN(">"), GREATER_THAN_OR_EQUALS_TO(">="), LESS_THAN("<"), LESS_THAN_EQUALS_TO(
      "<="), EQUALS_TO("=="), NOT_EQUALS_TO("!=");
  private String opeartor;

}
