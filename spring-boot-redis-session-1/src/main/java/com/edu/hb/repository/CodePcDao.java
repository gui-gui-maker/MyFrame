package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.CodePc;

public interface CodePcDao extends JpaRepository<CodePc,String>{

	List<CodePc> findByNf(int nf) throws Exception;
}
