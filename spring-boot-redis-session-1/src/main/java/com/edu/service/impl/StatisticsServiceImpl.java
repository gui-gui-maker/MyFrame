package com.edu.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.CatalogJH;
import com.edu.bean.Pcdm;
import com.edu.bean.Statistics1;
import com.edu.jdbc.repository.StatisticsJDao;
import com.edu.jdbc.repository.impl.PcdmJDaoImpl;
import com.edu.service.StatisticsService;
@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private StatisticsJDao sjDao;
	@Autowired
	PcdmJDaoImpl pcdmJDaoImpl;
	@Override
	public List<Statistics1> findByParams(String yxdm) throws Exception {
		// TODO Auto-generated method stub
		return sjDao.findByParams(yxdm);
	}
	public List<Pcdm> findAllPcdm() throws Exception {
		// TODO Auto-generated method stub
		return pcdmJDaoImpl.findAll();
	}
	@Override
	public Set<String> findYxdm(String yxdms) throws Exception {
		// TODO Auto-generated method stub
		return sjDao.findYxdm(yxdms);
	}
	@Override
	public List<CatalogJH> queryJhsByCatalog(String catalogs) throws Exception {
		// TODO Auto-generated method stub
		return sjDao.queryJhsByCatalog(catalogs);
	}
	
}
