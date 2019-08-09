package io.cart.app.api;

import java.util.Collection;
import org.mapstruct.Mapper;
import io.cart.app.entity.WishlistEntity;
import io.cart.app.model.Wishlist;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
  WishlistEntity wishlistToWishlistEntity(Wishlist wishlistDto);

  /**
   * Utility to convert WishlistEntity to Wishlist DTO instance
   * 
   * @param wishlistDao - wishlistEntity intance to be transformed
   * @return - wishlist DTO instance
   */
  Wishlist wishlistEntityToWishlist(WishlistEntity wishlistDao);

  /**
   * Convert list of Wishlist Entity intances to Wishlist DTO intances
   * 
   * @param wishlistCollection
   * @return
   */
  Collection<Wishlist> wishlistEntityListToWishlist(Collection<WishlistEntity> wishlists);
}
