package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.bean.CodeUniversity;

public interface CodeUniversityDao extends JpaRepository<CodeUniversity, String>{

	List<CodeUniversity> findByNf(int nf) throws Exception;

	@Query("from CodeUniversity where yxdm=?1 and nf = ?2")
	List<CodeUniversity> findByYxdmAndNf(String yxdm, int nf) throws Exception;
	
	@Query("from CodeUniversity where yxdh=?1 and nf = ?2")
	List<CodeUniversity> findByYxdhAndNf(String yxdh, int nf) throws Exception;
	


}
