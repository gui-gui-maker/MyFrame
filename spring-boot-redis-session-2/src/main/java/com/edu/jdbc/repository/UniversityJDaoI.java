package com.edu.jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.edu.bean.University0;

import java.util.List;

public interface UniversityJDaoI  {

    int save(University0 user,JdbcTemplate jdbcTemplate);

    int update(University0 user,JdbcTemplate jdbcTemplate);

    int delete(String id,JdbcTemplate jdbcTemplate);

    List< University0> findALL(JdbcTemplate jdbcTemplate);

    University0 findById(String id,JdbcTemplate jdbcTemplate);
    
    
}