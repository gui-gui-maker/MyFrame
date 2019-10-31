package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.Plan;
import com.edu.jdbc.repository.PlanJDao;

@Repository
public class PlanJDaoImpl implements PlanJDao{
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	@Override
	public List<Plan> getPlanBySql(String sql) throws Exception {
		// TODO Auto-generated method stub
		return primaryJdbcTemplate.query(sql, new BeanPropertyRowMapper(Plan.class));
	}

}
