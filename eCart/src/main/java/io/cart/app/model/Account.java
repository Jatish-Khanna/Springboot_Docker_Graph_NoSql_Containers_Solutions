package io.cart.app.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Account model - data transfer class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data
public class Account {

  @NotNull(message = "Name is null or empty")
  @Size(max = 15, message = "{custom.validation.character.maxSize.fifteen}")
  private String name;
  @NotNull(message = "Password is null or empty")
  @Size(max = 15, message = "{custom.validation.character.maxSize.fifteen}")
  private String password;
  @NotNull(message = "Confirm Password is null or empty")
  @Size(max = 15, message = "{custom.validation.character.maxSize.fifteen}")
  private String confirmPassword;
  @NotNull(message = "Email is null or empty")
  @Size(max = 15, message = "{custom.validation.character.maxSize.fifteenn}")
  @Email(message = "{custom.validation.email.format}")
  private String email;

}
