package com.neo.mapper.plan;

import com.neo.model.Zydh;

import java.util.List;

public interface ZydhMapper {
	
	List<Zydh> getAll();
	
	List<Zydh> getOnlyYxdmAndMcAll();
	
	Zydh getOne(String id);

	void insert(Zydh zydh);

	void update(Zydh zydh);

	void delete(String id);

}