package com.develcode.challenge.domain.usecase;

import com.develcode.challenge.domain.dataprovider.AvatarDataProvider;
import com.develcode.challenge.domain.usecase.exception.BusinessException;
import com.develcode.challenge.domain.usecase.exception.ValidationException;

import javax.inject.Named;
import java.io.File;

@Named
public class AvatarShowUseCase {

  private final AvatarDataProvider avatarDataProvider;

  public AvatarShowUseCase(
      @Named("repositoryAvatar") AvatarDataProvider avatarDataProvider
  ) {
    this.avatarDataProvider = avatarDataProvider;
  }

  public File showAvatar(String fileName) {
    if (fileName == null) {
      throw new ValidationException("File name not found");
    }

    File avatarFile = avatarDataProvider.showAvatar(fileName);

    if (avatarFile == null) {
      throw new BusinessException("Avatar not found");
    }

    return avatarFile;
  }
}
