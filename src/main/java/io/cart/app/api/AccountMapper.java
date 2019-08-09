package io.cart.app.api;

import org.mapstruct.Mapper;
import io.cart.app.entity.AccountEntity;
import io.cart.app.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account accountEntitytoAccount(AccountEntity accountDao);
}
