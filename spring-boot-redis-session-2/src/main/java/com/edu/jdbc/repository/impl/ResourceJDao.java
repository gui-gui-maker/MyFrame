package com.edu.jdbc.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edu.bean.Resource;
import com.edu.jdbc.repository.ResourceJDaoI;
@Repository
public class ResourceJDao implements ResourceJDaoI{

	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	
	@Override
	public List<Resource> tree(JdbcTemplate jdbcTemplate) {
		if(jdbcTemplate==null){
			jdbcTemplate= primaryJdbcTemplate;
		}
		return jdbcTemplate.query(
				"select t.id, t.pid, t.code, t.name, t.url, t.rank\n" +
						"  from sys_resource t\n" + 
						" start with t.pid is null\n" + 
						"connect by prior t.id = t.pid\n" + 
						" order by id", new BeanPropertyRowMapper(Resource.class));
	}

	
}
