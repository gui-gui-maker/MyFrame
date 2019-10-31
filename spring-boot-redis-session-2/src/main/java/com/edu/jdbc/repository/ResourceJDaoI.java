package com.edu.jdbc.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.edu.bean.Resource;

public interface ResourceJDaoI {
	List<Resource> tree(JdbcTemplate jdbcTemplate);
}
