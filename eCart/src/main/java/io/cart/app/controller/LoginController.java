package io.cart.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.cart.app.service.AccountService;
import lombok.AllArgsConstructor;

/**
 * Controller handles the login request
 * 
 * @author Jatish_Khanna
 *
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController


public class LoginController extends BaseController {
  // Spring provided account service class component instance
  AccountService accountService;

  // To Encrypt the password received from the user
  BCryptPasswordEncoder bcryptPasswordEncoder;

  // Logger for tracing
  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  /**
   * To handle the login request
   * 
   * @param modelMap - To fetch header information
   * @return - Response Entity with HttpStatus.Ok and User who logged in
   */
  @GetMapping("/")
  public ResponseEntity<String> login(ModelMap modelMap) {
    User accountDetails =
        (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String accountName = accountDetails.getUsername();
    logger.info("User: {} has loggged into account.", accountName);
    // Controller advice(Exception to HttpStatus codes)
    return ResponseEntity.ok(accountName);
  }

  /**
   * To get the encrypted password using BCrypt Password encoder service
   * 
   * @param pass - the string to be encoded
   * @return BCrypt encoded string
   */
  @GetMapping("/passw/{pass}")
  public ResponseEntity<String> loginResponse(@PathVariable String pass) {
    return ResponseEntity.ok(bcryptPasswordEncoder.encode(pass));
  }


  // /**
  // *
  // * @param account
  // * @return
  // */
  // @PostMapping("/addUser")
  // public String addNewAccount(@Valid @RequestBody Account account) {
  // System.out.println("Add user called");
  // accountService.addAccount(account);
  // return "account added successfully";
  // }
}
