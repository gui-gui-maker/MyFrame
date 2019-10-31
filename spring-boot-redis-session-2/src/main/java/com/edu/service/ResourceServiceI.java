package com.edu.service;

import java.util.List;
import java.util.Set;

import com.edu.bean.Resource;

public interface ResourceServiceI {

	public List<Resource> findByRank(int rank) throws Exception;
	
	public void save(Resource r) throws Exception;
	
	public List<Resource> queryForTree() throws Exception;
	
	
}
