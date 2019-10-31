package com.edu.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edu.bean.Plan;
import com.edu.bean.PlanParam;


public interface PlanService {
	
	Page<Plan> findByCondition(Plan params,Pageable pageable) throws Exception;

	void save(Plan jyjh) throws Exception;

	void ldel(String ids) throws Exception;

	List<Plan> findAllApplyPlan() throws Exception;
	
	List<PlanParam> findByYxdhAndDyml(String yxdh,String dyml) throws Exception;
	
	List<PlanParam> queryMajors(String yxdh,String dyml) throws Exception;


}
