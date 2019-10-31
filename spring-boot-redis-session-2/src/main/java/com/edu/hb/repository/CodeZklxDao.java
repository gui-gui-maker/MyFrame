package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.CodeZklx;

public interface CodeZklxDao extends JpaRepository<CodeZklx,String> {
	List<CodeZklx> findByNf(int nf) throws Exception;
}
