package com.guido.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.guido.model.BaseList;

public interface BaseListRepository {

	int save(BaseList blist,JdbcTemplate jdbcTemplate);

    int update(BaseList blist,JdbcTemplate jdbcTemplate);

    int delete(String code,JdbcTemplate jdbcTemplate);

    List<BaseList> findALL(JdbcTemplate jdbcTemplate);

    BaseList findById(String code,JdbcTemplate jdbcTemplate);
    
    List<Object[]> findCatalog(String yxdh);
}
