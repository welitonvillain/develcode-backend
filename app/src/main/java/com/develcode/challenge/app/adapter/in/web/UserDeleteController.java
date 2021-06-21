package com.develcode.challenge.app.adapter.in.web;

import com.develcode.challenge.app.adapter.in.web.model.UserModel;
import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.UserDeleteUseCase;
import com.develcode.challenge.domain.usecase.UserRegisterUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserDeleteController {

    private final UserDeleteUseCase usecase;

    public UserDeleteController(final UserDeleteUseCase usecase) { this.usecase = usecase; }

    @DeleteMapping("/user/delete/{code}")
    public ResponseEntity<UserModel> registerUser(@PathVariable Integer code) {
        User user = this.usecase.deleteUser(code);

        return ResponseEntity.status(HttpStatus.OK).body(
            UserModel.builder()
                .code(user.getCode())
                .name(user.getName())
                .birthDate(user.getBirthDate().toString())
                .build()
        );
    }
}
