package io.cart.app.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Application Web Security Configuration implementation
 * @author Jatish_Khanna
 *
 */
@Configuration
@EnableWebSecurity
public class LoginSecurityConfiguration extends WebSecurityConfigurerAdapter {
  // Defined data source in application YML/properties 
  @Autowired
  DataSource dataSource;

  // Password Codec bean
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Adapting new configuration for AuthenticationManagerBuilder
   * @param authManagerBuilder - Autowired bean instance from Sping context
   * @throws Exception - generic expection handler for failures
   */
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder authManagerBuilder)
      throws Exception {
    // Build the Auth manager with JDBC connection, feed the data source configuration details
    authManagerBuilder.jdbcAuthentication().dataSource(dataSource)
    // Query to fetch Username, password, and account status(enabled) from custom database
        .usersByUsernameQuery("select name as username, password, enabled from account where name=?")
        // Query to fetch roles for the particular user
        .authoritiesByUsernameQuery(
            "Select name as username, role_name as role from roles where name=?")
        // Set password encoder for the user
        .passwordEncoder(passwordEncoder());

  }

  /**
   * Configure the websecurity to allow open access the resources/files under provided URI's
   */
  @Override
  public void configure(WebSecurity webSecurity) throws Exception {
    webSecurity.ignoring().antMatchers("/resources/**");
  }

  /**
   * Configure the HttpSecurity to secure the system pages
   */
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // Disable the Cross-site request forgery attack
    httpSecurity.csrf().disable();
    // Adapt the new URI authorization scheme as per the URI's
    httpSecurity
                .authorizeRequests().antMatchers("/addUser/**").permitAll().and()
                .authorizeRequests().anyRequest().authenticated()
                  .and()
                 .formLogin()
                .usernameParameter("userId")
                .passwordParameter("password");
                //.successForwardUrl("/login");
  }
}
