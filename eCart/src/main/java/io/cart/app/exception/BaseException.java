package io.cart.app.exception;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public abstract class BaseException extends RuntimeException {

  public BaseException(String message) {
    super(message);
  }

  protected static String generateExceptionMessage(Class clazz, String resourceKey,
      String resourceValue) {
    return StringUtils.capitalize(clazz.getSimpleName()) + " resource is not found for [" + resourceKey
        + " : " + resourceValue + "]";
  }

  protected static String generateExceptionMessage(Class clazz,
      MultiValueMap<String, String> specifications) {
    return StringUtils.capitalize(clazz.getSimpleName()) + " resource is not found for [" + specifications
        + "]";
  }
}
