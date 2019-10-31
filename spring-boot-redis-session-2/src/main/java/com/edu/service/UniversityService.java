package com.edu.service;

import java.util.List;

import com.edu.bean.CodeUniversity;
import com.edu.bean.University0;

public interface UniversityService {
	University0 findByYxdm(String yxdm) throws Exception;
	
	List<CodeUniversity> findAll() throws Exception;

	List<CodeUniversity> findByNf(int nf) throws Exception;

	List<CodeUniversity> findByYxdmAndNf(String yxdm, int nf) throws Exception;
}
