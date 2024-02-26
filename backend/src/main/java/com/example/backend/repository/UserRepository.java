package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // custom find method
    // name function according to field name
    public User findByUsername(String username);
}
