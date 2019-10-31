package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.Pcdm;
@Repository
public class PcdmJDaoImpl {
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
    public List<Pcdm> findAll() throws Exception{
		String sql = "select * from pcdmall ";
        return primaryJdbcTemplate.query(sql, new BeanPropertyRowMapper(Pcdm.class));
    }
}
