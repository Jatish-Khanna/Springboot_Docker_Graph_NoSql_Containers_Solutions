package io.cart.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.cart.app.entity.WishlistEntity;

/**
 * Wishlist repository - provide implementation to CRUD operations
 * 
 * @author Jatish_Khanna
 *
 */
@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, String> {

  List<WishlistEntity> findByUserId(String userId);

  WishlistEntity findByUserIdAndDisplayName(String userId, String productName);
}
