package io.cart.app.exception.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericErrorController implements ErrorController {


  /**
   * Handle generic error for the service - "/error"
   * 
   * @return - Response body
   */

  @GetMapping("/error")
  public ResponseEntity<String> handleError() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("<html>\r\n" + "    <body>\r\n" + "        <h1>Something went wrong!</h1>\r\n"
            + "        <h2>Our Engineers are working on it.</h2>\r\n"
            + "        <a href=\"/login\">Home</a>\r\n" + "    </body>\r\n" + "</html>");
  }

  /**
   * The error URI triggered on issues
   */
  @Override
  public String getErrorPath() {
    return "/error";
  }
}
