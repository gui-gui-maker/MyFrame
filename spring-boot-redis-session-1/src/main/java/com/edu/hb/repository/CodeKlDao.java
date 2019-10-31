package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.CodeKl;

public interface CodeKlDao extends JpaRepository<CodeKl,String>{
	List<CodeKl> findByNf(int nf) throws Exception;
}
