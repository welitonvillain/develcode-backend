package com.develcode.challenge.app.adapter.in.web.model;

import com.develcode.challenge.domain.entity.User;
import com.develcode.challenge.domain.usecase.exception.ValidationException;
import lombok.Builder;
import lombok.Data;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
@Data
public class UserModel {

  private Integer code;

  private String name;

  private String birthDate;

  private String avatarUrl;

  public static UserModel fromDomain(User user) {
    return UserModel.builder()
        .code(user.getCode())
        .name(user.getName())
        .birthDate(user.getBirthDate().toString())
        .avatarUrl(user.getAvatarUrl())
        .build();
  }

  public User toDomain() {
    return User.builder()
        .code(code)
        .name(name)
        .birthDate(stringToLocalDate(birthDate))
        .build();
  }

  private LocalDate stringToLocalDate(String date) {
    try {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      return LocalDate.parse(date, format);
    } catch (DateTimeException ex) {
      throw new ValidationException("The format of birth date must be 'yyyy-MM-dd'");
    }
  }
}
