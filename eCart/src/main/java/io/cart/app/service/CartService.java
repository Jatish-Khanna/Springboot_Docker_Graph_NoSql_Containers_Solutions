package io.cart.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.cart.app.repository.CartRepository;
import lombok.RequiredArgsConstructor;

/**
 * Cart Service - to perform CRUD operation using repository
 * 
 * @author Jatish_Khanna
 *
 */
@RequiredArgsConstructor
@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;
}
