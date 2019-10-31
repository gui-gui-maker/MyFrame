package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.University0;
import com.edu.jdbc.repository.UniversityJDaoI;
@Repository
public class UniversityJDao implements UniversityJDaoI{
	
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
	@Override
	public int save(University0 university, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(University0 university, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<University0> findALL(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public University0 findById(String id, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<University0> findOne(String yxdm) {
	
        return primaryJdbcTemplate.query("SELECT distinct yxdm,yxmc FROM BASE_YX WHERE id=?", new Object[] {yxdm}, new BeanPropertyRowMapper<University0>(University0.class));
	}

	public List<University0> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<University0> findObjectOfYxdh(String yxdm) {
		String sql = "select distinct t.yxdh,t.yxdm,t.yxmc from Enroll t where t.yxdm = ?";
		return primaryJdbcTemplate.query(sql, new Object[] {yxdm}, new BeanPropertyRowMapper<University0>(University0.class));
	}

}
