package com.edu.hb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.bean.Catalog;

public interface CatalogDao extends JpaRepository<Catalog,String>{
	@Query("from Catalog t where t.id = '00000'")
	List<Catalog> findRootNode();
	@Query(value = "select t.ml,t.msql from t_mulu t where t.sflb='1'",nativeQuery=true)
	List<Object[]> findAllRule();
	
}
