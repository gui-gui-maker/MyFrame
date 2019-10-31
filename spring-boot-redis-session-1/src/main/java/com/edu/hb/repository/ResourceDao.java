package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.edu.bean.Resource;

public interface ResourceDao extends JpaSpecificationExecutor<Resource>,JpaRepository<Resource,String>{

	public List<Resource> findByRank(int rank);
}
