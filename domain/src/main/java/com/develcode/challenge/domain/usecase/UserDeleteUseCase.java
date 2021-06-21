package com.develcode.challenge.domain.usecase;

import com.develcode.challenge.domain.dataprovider.UserDataProvider;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.exception.BusinessException;
import com.develcode.challenge.domain.usecase.exception.ValidationException;

import javax.inject.Named;

@Named
public class UserDeleteUseCase {

  private final UserDataProvider userDataProvider;

  public UserDeleteUseCase(
      @Named("repository") UserDataProvider userDataProvider
  ) {
    this.userDataProvider = userDataProvider;
  }

  public User deleteUser(Integer code) {
    if (code == null) {
      throw new ValidationException("The user code is not valid.");
    }

    if (!userDataProvider.userAlreadyRegistered(code)) {
      throw new BusinessException("User not found.");
    }

    return userDataProvider.removeUser(code);
  }

}
