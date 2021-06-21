package com.develcode.challenge.domain.usecase;

import com.develcode.challenge.domain.dataprovider.UserDataProvider;
import com.develcode.challenge.domain.entity.User;

import javax.inject.Named;
import java.util.List;

@Named
public class UserListUseCase {

    private final UserDataProvider userDataProvider;

    public UserListUseCase(
            @Named("repository") UserDataProvider userDataProvider
    ) {
        this.userDataProvider = userDataProvider;
    }

    public List<User> listUsers() {
        return userDataProvider.listUsers();
    }

}
