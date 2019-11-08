package io.cart.app.service;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.cart.app.entity.WishlistEntity;
import io.cart.app.exception.resource.NoSuchWishIdPresent;
import io.cart.app.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;

/**
 * Wishlist service to perform CRUD operation using repository
 * 
 * @author Jatish_Khanna
 *
 */
@RequiredArgsConstructor
@Service
public class WishlistService {
  // Repository instance from spring context
  @Autowired
  WishlistRepository wishlistRepository;

  /**
   * Get all the items present in user wishlist
   * 
   * @param userId - user for which wishlist to be returned
   * @return - List of Products and details present in user wishlist
   */
  public Collection<WishlistEntity> getUserWishlist(String userId) {
    return wishlistRepository.findByUserId(userId);
  }

  /**
   * Add new items to the wishlist
   * 
   * @param wishlistDao - wishlist item to be stored
   */
  public String saveWishlistItem(WishlistEntity wishlistDao) {
    return wishlistRepository.saveAndFlush(wishlistDao).getWishId();
  }

  public void deleteByWishId(String wishId) {
    Optional<WishlistEntity> entityToDelete = wishlistRepository.findById(wishId);
    if (!entityToDelete.isPresent()) {
      throw new NoSuchWishIdPresent("wishId", wishId);
    }
    wishlistRepository.delete(entityToDelete.get());
  }
}
