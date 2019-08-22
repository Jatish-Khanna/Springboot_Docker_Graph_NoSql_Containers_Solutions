package io.cart.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Account Entity class
 * 
 * @author Jatish_Khanna
 *
 */

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data

@Entity
@Table(name = "account")
public class AccountEntity {
  @Id
  private String name;
  private String password;
  @Column(name = "confirm_password")
  private String confirmPassword;
  private String email;

}
