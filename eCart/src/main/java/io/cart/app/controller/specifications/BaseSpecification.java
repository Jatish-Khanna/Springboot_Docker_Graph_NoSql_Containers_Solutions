package io.cart.app.controller.specification;

import org.springframework.data.jpa.domain.Specification;


public abstract class BaseSpecification<T, U> {
  
  private static final String ANY_CHAR_WILDCARD = "%";

  public abstract Specification<T> getFilter(U request);

  protected String containsLowerCase(String lookupField) {
      return ANY_CHAR_WILDCARD + lookupField.toLowerCase() + ANY_CHAR_WILDCARD;
  }
  
  protected Specification<T> numericSpecificationCompare(String attribute, double value,
      String operator) {
    return (root, query, criteriaBuilder) -> {
      switch (operator) {
        case ">":
          return criteriaBuilder.greaterThan(root.get(attribute), value);
        case ">=":
          return criteriaBuilder.greaterThanOrEqualTo(root.get(attribute), value);
        case "<":
          return criteriaBuilder.lessThan(root.get(attribute), value);
        case "<=":
          return criteriaBuilder.lessThanOrEqualTo(root.get(attribute), value);
        case "==":
          return criteriaBuilder.equal(root.get(attribute), value);
        case "!=":
          return criteriaBuilder.notEqual(root.get(attribute), value);
        default:
          return null;
      }
    };
  }
  
  protected Specification<T> textSpecificationContains(String attribute,
      String value) {
    return (root, query, criteriaBuilder) -> {
      if (value == null) {
        return null;
      }

      return criteriaBuilder.like(criteriaBuilder.lower(root.get(attribute)),
          containsLowerCase(value));
    };
  }
}
