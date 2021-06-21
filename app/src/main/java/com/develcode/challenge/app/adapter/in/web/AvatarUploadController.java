package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.app.adapter.in.web.model.UserModel;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.AvatarUploadUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;

@RestController
public class AvatarUploadController {

  private final AvatarUploadUseCase usecase;

  public AvatarUploadController(final AvatarUploadUseCase usecase) {
    this.usecase = usecase;
  }

  @PatchMapping("user/{code}/update/avatar")
  public ResponseEntity<UserModel> uploadAvatar(@RequestParam MultipartFile avatar, @PathVariable Integer code) {
    try {
      User user = this.usecase.uploadAvatar(avatar.getInputStream(), avatar.getOriginalFilename(), code);

      return ResponseEntity.ok().body(
          UserModel.builder()
              .code(user.getCode())
              .name(user.getName())
              .birthDate(user.getBirthDate().toString())
              .avatarUrl(user.getAvatarUrl())
              .build()
      );
    } catch (IOException ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
