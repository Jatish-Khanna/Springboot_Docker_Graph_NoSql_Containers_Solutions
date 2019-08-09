package io.cart.app.api;

import org.mapstruct.Mapper;
import io.cart.app.model.ApiErrorResponse;

@Mapper(componentModel = "spring")
  ApiErrorResponse exceptionToApiResponse(Exception exception);

}
