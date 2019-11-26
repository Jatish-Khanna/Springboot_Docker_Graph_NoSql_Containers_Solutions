package io.cart.app.exception.handler;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.cart.app.api.ExceptionMapper;
import io.cart.app.exception.resource.NoOrderHistoryFoundException;
import io.cart.app.exception.resource.ResourceNotFoundException;
import io.cart.app.model.ApiErrorResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

  ExceptionMapper exceptionMapper;
  
  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException exception) {
    ApiErrorResponse apiErrorResponse = exceptionMapper.exceptionToApiResponse(exception);
    apiErrorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
    apiErrorResponse.setTimestamp(LocalDateTime.now());
    return buildResponseEntity(apiErrorResponse);
  }

  // More Exceptions can be appended to array
  // It ensures code reusability
  @ExceptionHandler({NoOrderHistoryFoundException.class, ResourceRemovalException.class})
  protected ResponseEntity<?> handleOrderHistoryNotFound(NoOrderHistoryFoundException exception) {
    ApiErrorResponse apiErrorResponse = exceptionMapper.exceptionToApiResponse(exception);
    apiErrorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
    apiErrorResponse.setTimestamp(LocalDateTime.now());
    return buildResponseEntity(apiErrorResponse);
  }

  private ResponseEntity<?> buildResponseEntity(ApiErrorResponse apiErrorResponse) {

    return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, apiErrorResponse.getHttpStatus());
  }
}
