package com.develcode.challenge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class User {
  private final Integer code;

  private final String name;

  private final LocalDate birthDate;

  private final String avatarUrl;
}
