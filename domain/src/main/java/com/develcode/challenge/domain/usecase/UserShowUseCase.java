package com.develcode.challenge.domain.usecase;

import com.develcode.challenge.domain.dataprovider.UserDataProvider;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.exception.BusinessException;
import com.develcode.challenge.domain.usecase.exception.ValidationException;

import javax.inject.Named;
import java.util.List;

@Named
public class UserShowUseCase {

    private final UserDataProvider userDataProvider;

    public UserShowUseCase(
            @Named("repository") UserDataProvider userDataProvider
    ) {
        this.userDataProvider = userDataProvider;
    }

    public User showUser(Integer code) {
        if (code == null) {
            throw new ValidationException("Code must be informed");
        }

        if (!userDataProvider.userAlreadyRegistered(code)) {
            throw new BusinessException("User not found");
        }

        return userDataProvider.getUser(code);
    }

}
