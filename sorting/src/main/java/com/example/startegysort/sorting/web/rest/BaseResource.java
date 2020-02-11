package com.example.startegysort.sorting.web.rest;

import org.springframework.http.ResponseEntity;
import com.example.startegysort.sorting.model.ErrorResponse;

public abstract class BaseResource {

  protected <T> ResponseEntity<T> toResponse(T body) {
    return ResponseEntity.ok(body);
  }

  protected <T extends ErrorResponse> ResponseEntity<T> errorToResponse(T error) {
    return ResponseEntity.status(error.getStatus()).body(error);
  }
}
