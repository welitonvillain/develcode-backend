package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.app.adapter.in.web.model.UserModel;
import com.develcode.challenge.domain.usecase.UserListUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserListController {

  private final UserListUseCase usecase;

  public UserListController(final UserListUseCase usecase) {
    this.usecase = usecase;
  }

  @GetMapping("/user/list")
  public ResponseEntity<List<UserModel>> registerUser() {
    List<UserModel> users = this.usecase.listUsers().stream()
        .map(UserModel::fromDomain)
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
