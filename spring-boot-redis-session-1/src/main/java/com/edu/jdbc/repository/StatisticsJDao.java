package com.edu.jdbc.repository;

import java.util.List;
import java.util.Set;

import com.edu.bean.CatalogJH;
import com.edu.bean.Statistics1;

public interface StatisticsJDao {
	
	List<Statistics1> findByParams(String yxdh) throws Exception;
	
	Set<String> findYxdm(String yxdms) throws Exception;
	
	List<CatalogJH> queryJhsByCatalog(String catalogs) throws Exception;
}
