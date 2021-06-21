package com.develcode.challenge.app.adapter.out.persistence;

import com.develcode.challenge.app.adapter.out.persistence.model.UserModel;
import com.develcode.challenge.domain.dataprovider.UserDataProvider;
import com.develcode.challenge.domain.entity.User;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("repository")
@RequiredArgsConstructor
public class PGUserRepository implements UserDataProvider {

  private final UserRepository userRepository;

  @Override
  public User registerUser(User user) {
    UserModel userModel = userRepository.save(
        UserModel.builder()
            .code(user.getCode())
            .name(user.getName())
            .birthDate(user.getBirthDate())
            .avatarUrl(user.getAvatarUrl())
            .build()
    );

    return userModel.toDomain();
  }

  @Override
  public Boolean userAlreadyRegistered(Integer code) {
    return userRepository.findByCode(code) != null;
  }

  @Override
  public User updateUser(User user) {
    UserModel userModel = userRepository.findByCode(user.getCode());

    userModel.setCode(user.getCode());
    userModel.setName(user.getName());
    userModel.setBirthDate(user.getBirthDate());

    userRepository.save(userModel);

    return userModel.toDomain();
  }

  @Override
  public User updateAvatar(String name, Integer code) {
    UserModel user = userRepository.findByCode(code);

    if (user == null) {
      return null;
    }

    user.setAvatarUrl(name);
    userRepository.save(user);

    return user.toDomain();
  }

  @Override
  public User removeUser(Integer code) {
    UserModel user = userRepository.findByCode(code);

    if (user == null) {
      return null;
    }

    userRepository.delete(user);
    return user.toDomain();
  }

  @Override
  public User getUser(Integer code) {
    UserModel user = userRepository.findByCode(code);

    if (user == null) {
      return null;
    }

    return user.toDomain();
  }

  @Override
  public List<User> listUsers() {
    return userRepository.findAll().stream()
        .map(UserModel::toDomain)
        .collect(Collectors.toList());
  }
}
