package com.develcode.challenge.app.adapter.out.persistence.model;

import com.develcode.challenge.domain.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @Column(unique = true)
  private Integer code;

  @NotNull
  private String name;

  private LocalDate birthDate;

  private String avatarUrl;

  public static UserModel fromDomain(User user) {
    return UserModel.builder()
        .code(user.getCode())
        .name(user.getName())
        .birthDate(user.getBirthDate())
        .avatarUrl(user.getAvatarUrl())
        .build();
  }

  public User toDomain() {
    return User.builder()
        .code(code)
        .name(name)
        .birthDate(birthDate)
        .avatarUrl(avatarUrl)
        .build();
  }
}
