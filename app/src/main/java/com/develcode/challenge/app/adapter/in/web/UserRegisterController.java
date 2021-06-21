package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.app.adapter.in.web.model.UserModel;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.UserRegisterUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    private UserRegisterUseCase usecase;

    public UserRegisterController(final UserRegisterUseCase usecase) { this.usecase = usecase; }

    @PostMapping("/user/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel userModel) {
        User user = this.usecase.registerUser(userModel.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).body(
            UserModel.builder()
                .code(user.getCode())
                .name(user.getName())
                .birthDate(user.getBirthDate().toString())
                .build()
        );
    }
}
