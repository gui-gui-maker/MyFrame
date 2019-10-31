package com.edu.service;

import java.util.List;

import com.edu.bean.CodeUniversity;

public interface UniversityService {
	
	List<CodeUniversity> findAll() throws Exception;

	List<CodeUniversity> findByNf(int nf) throws Exception;

	List<CodeUniversity> findByYxdmAndNf(String yxdm, int nf) throws Exception;
	
	List<CodeUniversity> findByYxdhAndNf(String yxdh, int nf) throws Exception;
}
