package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.app.adapter.in.web.model.UserModel;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.UserListUseCase;
import com.develcode.challenge.domain.usecase.UserShowUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserShowController {

  private final UserShowUseCase usecase;

  public UserShowController(final UserShowUseCase usecase) {
    this.usecase = usecase;
  }

  @GetMapping("/user/show/{code}")
  public ResponseEntity<UserModel> registerUser(@PathVariable Integer code) {
    User user = this.usecase.showUser(code);

    return ResponseEntity.status(HttpStatus.OK).body(
        UserModel.builder()
            .code(user.getCode())
            .name(user.getName())
            .birthDate(user.getBirthDate().toString())
            .avatarUrl(user.getAvatarUrl())
            .build()
    );
  }
}
