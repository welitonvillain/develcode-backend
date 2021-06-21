package com.develcode.challenge.domain.dataprovider;

import com.develcode.challenge.domain.entity.User;

import java.util.List;

public interface UserDataProvider {
  User registerUser(User user);

  Boolean userAlreadyRegistered(Integer code);

  User updateUser(User user);

  User updateAvatar(String name, Integer code);

  User removeUser(Integer code);

  User getUser(Integer code);

  List<User> listUsers();
}
