package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.CodeJhlb;

public interface CodeJhlbDao extends JpaRepository<CodeJhlb,String>{

	List<CodeJhlb> findByNf(int nf) throws Exception;
}
