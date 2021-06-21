package com.develcode.challenge.app.adapter.out.persistence;

import com.develcode.challenge.app.adapter.out.persistence.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

  UserModel findByCode(Integer code);
}
