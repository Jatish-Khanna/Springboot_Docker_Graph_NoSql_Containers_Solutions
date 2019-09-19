package io.cart.app.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiErrorResponse {

  private HttpStatus httpStatus;
  @CreationTimestamp
  private LocalDateTime timestamp;
  private String message;
  private String localizedMessage;
}
