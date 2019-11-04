package io.cart.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.cart.app.entity.CartEntity;

/**
 * Cart repository to perform CRUD operations on the database
 * 
 * @author Jatish_Khanna
 *
 */
@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {
}
