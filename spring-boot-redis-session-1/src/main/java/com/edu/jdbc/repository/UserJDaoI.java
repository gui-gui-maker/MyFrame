package com.edu.jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.edu.bean.User;

import java.util.List;

public interface UserJDaoI  {

    int save(User user,JdbcTemplate jdbcTemplate);

    int update(User user,JdbcTemplate jdbcTemplate);

    int delete(long id,JdbcTemplate jdbcTemplate);

    List<User> findALL(JdbcTemplate jdbcTemplate);

    User findById(long id,JdbcTemplate jdbcTemplate);
}