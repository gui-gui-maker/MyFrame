package com.edu.jdbc.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.edu.bean.Catalog;

public interface ListsJDaoI {

	//int save(Lists blist,JdbcTemplate jdbcTemplate);

    int update(Catalog blist,JdbcTemplate jdbcTemplate);

    int delete(String code,JdbcTemplate jdbcTemplate);

    List<Catalog> findALL(JdbcTemplate jdbcTemplate);
    
    List<Catalog> listAll(JdbcTemplate jdbcTemplate);

    Catalog findById(String code,JdbcTemplate jdbcTemplate);
    
    public List<Catalog> listOneByYxdh(JdbcTemplate jdbcTemplate,String yxdh) ;
}
