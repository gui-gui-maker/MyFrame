package com.edu.business.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.business.bean.MajorChange;
@Repository
public interface MajorChangeRepository  extends JpaRepository<MajorChange,String>{
	Page<MajorChange> findAll(Pageable pageable);
	
	List<MajorChange> findAll();
}
