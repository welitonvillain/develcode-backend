package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.domain.usecase.AvatarShowUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class AvatarShowController {

  private final AvatarShowUseCase usecase;

  public AvatarShowController(final AvatarShowUseCase usecase) {
    this.usecase = usecase;
  }

  @GetMapping(value = "user/avatar/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> showAvatar(@PathVariable String filename) throws IOException {
    File avatarFile = this.usecase.showAvatar(filename);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
        .body(Files.readAllBytes(avatarFile.toPath()));
  }

}
