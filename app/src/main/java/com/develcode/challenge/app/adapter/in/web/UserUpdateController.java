package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.app.adapter.in.web.model.UserModel;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.UserUpdateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserUpdateController {

  private final UserUpdateUseCase usecase;

  public UserUpdateController(final UserUpdateUseCase usecase) {
    this.usecase = usecase;
  }

  @PutMapping("/user/update")
  public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel) {
    User user = this.usecase.updateUser(userModel.toDomain());

    return ResponseEntity.status(HttpStatus.OK).body(
        UserModel.builder()
            .code(user.getCode())
            .name(user.getName())
            .birthDate(user.getBirthDate().toString())
            .build()
    );
  }
}
