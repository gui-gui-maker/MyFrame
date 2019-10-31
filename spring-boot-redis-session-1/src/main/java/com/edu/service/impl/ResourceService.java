package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.Resource;
import com.edu.hb.repository.ResourceDao;
import com.edu.jdbc.repository.impl.ResourceJDao;
import com.edu.service.ResourceServiceI;
@Service
public class ResourceService implements ResourceServiceI{
	@Autowired
	ResourceDao sResourceDao;
	@Autowired
	ResourceJDao sResourceJDao;

	@Override
	public List<Resource> findByRank(int rank) throws Exception {
		// TODO Auto-generated method stub
		return sResourceDao.findByRank(rank);
	}

	@Override
	public void save(Resource r) throws Exception {
		sResourceDao.save(r);
	}

	@Override
	public List<Resource> queryForTree() throws Exception {
		// TODO Auto-generated method stub
		return sResourceJDao.tree(null);
	}

}
