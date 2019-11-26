package io.cart.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * Review Model - data transfer class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data

@JsonInclude(Include.NON_NULL)
public class Review {

  private String userId;
  private double rating;
  private String reviewComments;

}
