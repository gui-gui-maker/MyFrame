package com.edu.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}