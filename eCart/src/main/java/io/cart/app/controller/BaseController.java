package io.cart.app.controller;

import org.springframework.http.ResponseEntity;
import io.cart.app.model.ErrorResponse;

public abstract class BaseController {
  protected <T> ResponseEntity<T> toResponse(T body) {
    return ResponseEntity.ok(body);
  }

  protected <T extends ErrorResponse> ResponseEntity<T> errorToResponse(T error) {
    return ResponseEntity.status(error.getStatus()).body(error);
  }
}

