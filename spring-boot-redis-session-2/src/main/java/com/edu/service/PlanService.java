package com.edu.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edu.bean.Plan;


public interface PlanService {
	Page<Plan> findByCondition(Plan params,Pageable pageable) throws Exception;

	void save(Plan jyjh) throws Exception;

	void ldel(String ids) throws Exception;


}
