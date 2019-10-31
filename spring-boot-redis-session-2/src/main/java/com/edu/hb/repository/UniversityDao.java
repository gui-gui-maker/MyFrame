package com.edu.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.bean.University0;

public interface UniversityDao extends JpaRepository<University0, String>{

	University0 findByYxdm(String yxdm) throws Exception;
}
