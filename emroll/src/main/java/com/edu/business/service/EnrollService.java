package com.edu.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edu.business.bean.Enroll;

public interface EnrollService {
	Page<Enroll> findByCondition(Enroll params,Pageable pageable) throws Exception;
	
	List<Object[]> findCatalog(String yxdh) throws Exception;
	
	void saveOrUpdate(Enroll enroll) throws Exception;
	
	void logicDelete(List<String> ids) throws Exception;
}
