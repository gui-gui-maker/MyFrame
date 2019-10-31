package com.guido.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.guido.model.BaseYx;
import com.guido.model.User;
import com.guido.repository.BaseYxRepository;
@Repository
public class BaseYxRepositoryImpl implements BaseYxRepository{
	
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
	@Override
	public int save(BaseYx user, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BaseYx user, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BaseYx> findALL(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseYx findById(String id, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BaseYx> findOne(String yxdm) {
	
        return primaryJdbcTemplate.query("SELECT distinct yxdm,yxmc FROM BASE_YX WHERE id=?", new Object[] {yxdm}, new BeanPropertyRowMapper<BaseYx>(BaseYx.class));
	}

	public List<BaseYx> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BaseYx> findObjectOfYxdh(String yxdm) {
		String sql = "select distinct t.yxdh,t.yxdm,t.yxmc from Enroll t where t.yxdm = ?";
		return primaryJdbcTemplate.query(sql, new Object[] {yxdm}, new BeanPropertyRowMapper<BaseYx>(BaseYx.class));
	}

}
