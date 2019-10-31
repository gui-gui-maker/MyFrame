package com.edu.service;

import java.util.List;

import com.edu.bean.PlanEditApply;
import com.edu.bean.User;

public interface PlanEditApplyService{

	void save(String apply,User user) throws Exception;

	List<PlanEditApply> findByFid(String id) throws Exception;

	List<PlanEditApply> findByField(String key) throws Exception;


}
