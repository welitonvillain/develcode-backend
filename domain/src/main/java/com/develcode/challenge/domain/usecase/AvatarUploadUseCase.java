package com.develcode.challenge.domain.usecase;

import com.develcode.challenge.domain.dataprovider.AvatarDataProvider;
import com.develcode.challenge.domain.dataprovider.UserDataProvider;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.exception.BusinessException;
import com.develcode.challenge.domain.usecase.exception.ValidationException;

import javax.inject.Named;
import java.io.File;
import java.io.InputStream;

@Named
public class AvatarUploadUseCase {

  private final UserDataProvider userDataProvider;
  private final AvatarDataProvider avatarDataProvider;

  public AvatarUploadUseCase(
      @Named("repository") UserDataProvider userDataProvider,
      @Named("repositoryAvatar") AvatarDataProvider avatarDataProvider
  ) {
    this.userDataProvider = userDataProvider;
    this.avatarDataProvider = avatarDataProvider;
  }

  public User uploadAvatar(InputStream fileInputStream, String name, Integer userCode) {
    if (fileInputStream == null || name == null || userCode == null) {
      throw new ValidationException("Invalid parameters");
    }

    Boolean exists = userDataProvider.userAlreadyRegistered(userCode);

    if (!exists) {
      throw new BusinessException("User not found");
    }

    String storageNameFile = avatarDataProvider.uploadAvatar(fileInputStream, name);

    if (storageNameFile == null) {
      throw new BusinessException("An error occurred while trying to upload the avatar");
    }

    return userDataProvider.updateAvatar(storageNameFile, userCode);
  }

}
