package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.CodeJhxz;

public interface CodeJhxzDao extends JpaRepository<CodeJhxz,String>{

	List<CodeJhxz> findByNf(int nf) throws Exception;
}
