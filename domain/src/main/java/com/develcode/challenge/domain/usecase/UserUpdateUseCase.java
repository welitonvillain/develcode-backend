package com.develcode.challenge.domain.usecase;

import com.develcode.challenge.domain.dataprovider.UserDataProvider;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.exception.BusinessException;
import com.develcode.challenge.domain.usecase.exception.ValidationException;

import javax.inject.Named;

@Named
public class UserUpdateUseCase {

    private final UserDataProvider userDataProvider;

    public UserUpdateUseCase(
            @Named("repository") UserDataProvider userDataProvider
    ) {
        this.userDataProvider = userDataProvider;
    }

    public User updateUser(User user) {
        if (user == null) {
            throw new ValidationException("You must provide the user");
        }

        if (!userDataProvider.userAlreadyRegistered(user.getCode())) {
            throw new BusinessException("User not found");
        }

        return userDataProvider.updateUser(user);
    }
}
