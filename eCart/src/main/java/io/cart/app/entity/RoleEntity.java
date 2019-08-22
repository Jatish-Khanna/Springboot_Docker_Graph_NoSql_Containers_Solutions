package io.cart.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

// * Lombok annotations starts -
// * provides implementation to mentioned methods
@Data

// Role Entity class
@Entity
@Table(name = "roles")
public class RoleEntity {

  @Id
  @Column(name = "role_id")
  private String roleId;
  @Column(name = "role_name")
  private String roleName;

}
