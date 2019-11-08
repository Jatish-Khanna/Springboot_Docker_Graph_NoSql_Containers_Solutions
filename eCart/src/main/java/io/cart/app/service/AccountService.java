package io.cart.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.cart.app.entity.AccountEntity;
import io.cart.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

/**
 * Account Service - to perform CRUD operation using repository
 * 
 * @author Jatish_Khanna
 *
 */
@RequiredArgsConstructor
@Service
public class AccountService {

  // Repository provided by spring context
  @Autowired
  AccountRepository accountRepository;

  // Password encryption algorithm used
  @Autowired
  BCryptPasswordEncoder bCryptEncoder;

  /**
   * Add new account to the database
   * 
   * @param account - the account details to be inserted in the database
   * @return - if account has been inserted to database or not
   */
  public boolean addAccount(AccountEntity accountEntity) {

    if (!accountEntity.getPassword().equals(accountEntity.getConfirmPassword()))
      return false;

    accountRepository.saveAndFlush(accountEntity);
    return true;
  }

  /**
   * Find the account details by the name
   * 
   * @param userName - to fetch account details for user
   * @return - Account details of user
   */
  public AccountEntity findUserByName(String userName) {
    AccountEntity account = accountRepository.findByName(userName);
    return account;
  }
}
