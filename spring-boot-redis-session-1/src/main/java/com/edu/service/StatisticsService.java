package com.edu.service;

import java.util.List;
import java.util.Set;

import com.edu.bean.CatalogJH;
import com.edu.bean.Pcdm;
import com.edu.bean.Statistics1;

public interface StatisticsService {

	List<Statistics1> findByParams(String yxdm) throws Exception;

	List<Pcdm> findAllPcdm() throws Exception;
	
	Set<String> findYxdm(String yxdms) throws Exception;
	
	List<CatalogJH> queryJhsByCatalog(String catalogs) throws Exception;
}
