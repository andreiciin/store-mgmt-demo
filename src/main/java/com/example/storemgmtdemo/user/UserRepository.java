package com.example.storemgmtdemo.user;

import com.example.storemgmtdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Object> {
}
