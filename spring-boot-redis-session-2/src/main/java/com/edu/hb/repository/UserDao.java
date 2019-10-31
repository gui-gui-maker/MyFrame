package com.edu.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.User;

public interface UserDao extends JpaRepository<User, String> {
    User findByUserName(String userName);
}