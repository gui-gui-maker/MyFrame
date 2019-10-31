package com.edu.service;

import java.util.List;

import com.edu.bean.Catalog;

public interface CatalogService {

	Catalog findRoot() throws Exception;
	
	Catalog listOneByYxdh(String yxdh) throws Exception;

	Catalog listOneByYxdm(String yxdm) throws Exception;
}
