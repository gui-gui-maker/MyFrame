package com.edu.jdbc.repository;

import java.util.List;

import com.edu.bean.Plan;
import com.edu.bean.PlanParam;

public interface PlanJDao {

	List<Plan> getPlanBySql(String sql) throws Exception;
	
	List<PlanParam> findByYxdhAndDyml(String yxdh,String dyml) throws Exception;
	
	
	List<PlanParam> queryMajors(String yxdh, String dyml) throws Exception;
}
