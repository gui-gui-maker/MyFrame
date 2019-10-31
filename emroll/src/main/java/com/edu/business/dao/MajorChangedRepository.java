package com.edu.business.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.business.bean.MajorChanged;
@Repository
public interface MajorChangedRepository extends JpaRepository<MajorChanged,String>{
	Page<MajorChanged> findAll(Pageable pageable);
}
