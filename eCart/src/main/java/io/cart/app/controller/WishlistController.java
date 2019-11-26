package io.cart.app.controller;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.cart.app.api.WishlistMapper;
import io.cart.app.entity.WishlistEntity;
import io.cart.app.exception.resource.ResourceNotFoundException;
import io.cart.app.exception.resource.ResourceRemovalException;
import io.cart.app.exception.resource.WishlistPersistentException;
import io.cart.app.model.Wishlist;
import io.cart.app.service.WishlistService;
import lombok.AllArgsConstructor;

/**
 * Controller to handle Wishlist request
 * 
 * @author Jatish_Khanna
 *
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class WishlistController extends BaseController {

  // Wishlist service instance from spring context
  WishlistService wishlistService;

  WishlistMapper wishlistMapper;

  // logger to trace the calls
  private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

  private static final String WISHLIST_KEY = "wishId";

  /**
   * Get the wishlist stored for the user
   * 
   * @param wishId - wishlist for the user
   * @return - A list of Wishlist items stored by user
   */
  @GetMapping("/wishlists/{wishId}")
  public ResponseEntity<Collection<Wishlist>> getUserWishlist(@PathVariable String wishId) {

    Collection<WishlistEntity> wishlist = wishlistService.getUserWishlist(wishId);

    if (wishlist == null || wishlist.isEmpty()) {
      throw new ResourceNotFoundException(WishlistEntity.class, WISHLIST_KEY, wishId);
    }

    return ResponseEntity.ok(wishlistMapper.wishlistEntityListToWishlist(wishlist));
  }

  /**
   * Add another item to the user wishlist
   * 
   * @param userId - User for which wishlist to add
   * @param productName - new product to be added in wishlist
   * @param wishlistItem - Wishlist details
   * @return - List of all the items present in wishlist including latest appended
   */
  @PostMapping("/wishlists")
  public ResponseEntity<Wishlist> addWishlistItem(@RequestBody Wishlist wishlistItem) {
    logger.info("Adding item to wishlist");
    // Bad request Exception to check ID should be null
    WishlistEntity wishlist = wishlistMapper.wishlistToWishlistEntity(wishlistItem);
    // Call to service method - to add new item to the wishlist
    String wishId = null;
    try {
      wishId = wishlistService.saveWishlistItem(wishlist);
    } catch (Exception exception) {
      throw new WishlistPersistentException("Error, while persisting wishlist.", wishlistItem);
    }
    wishlistItem.setWishId(wishId);

    return ResponseEntity.status(HttpStatus.CREATED).body(wishlistItem);
  }

  /**
   * Remove an item from the wishlist
   * 
   * @param userId - user for which item to be removed from the wishlist
   * @param productName - Product name to be removed from the wishlist
   * @return - Complete list of items remaining in the user wishlist but last removed
   */
  @DeleteMapping("/wishlists/{id}")
  public ResponseEntity<Collection<Wishlist>> removeWishlistItem(@PathVariable String wishId) {
    logger.info("Removing item from wishlist");
    // Call to service method - remove item with given userId, and productName from the wishlist
    try {
      wishlistService.deleteByWishId(wishId);
    } catch (Exception exception) {
      throw new ResourceRemovalException(WishlistEntity.class, "wishId", wishId);
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
