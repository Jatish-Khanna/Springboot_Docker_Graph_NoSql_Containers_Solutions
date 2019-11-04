package io.cart.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.cart.app.entity.AccountEntity;

/**
 * Account repository to perform CRUD operations on the database
 * 
 * @author Jatish_Khanna
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

  AccountEntity findByName(String userName);
}
