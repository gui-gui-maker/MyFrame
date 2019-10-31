package com.guido.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.guido.model.BaseYx;

import java.util.List;

public interface BaseYxRepository  {

    int save(BaseYx user,JdbcTemplate jdbcTemplate);

    int update(BaseYx user,JdbcTemplate jdbcTemplate);

    int delete(String id,JdbcTemplate jdbcTemplate);

    List<BaseYx> findALL(JdbcTemplate jdbcTemplate);

    BaseYx findById(String id,JdbcTemplate jdbcTemplate);
    
    
}