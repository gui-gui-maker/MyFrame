package com.edu.jdbc.repository;

import java.util.List;

import com.edu.bean.Plan;

public interface PlanJDao {

	List<Plan> getPlanBySql(String sql) throws Exception;
}
