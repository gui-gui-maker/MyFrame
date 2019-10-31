package com.neo.mapper.plan;

import java.util.List;

import com.neo.model.Jhly;

public interface JhlyMapper {
	List<Jhly> getAll();
	
	Jhly getOne(String id);

	int addJhly(Jhly jhly);

	int update(Jhly jhly);

	void delete(String id);
	
	void deleteAll();

	void deleteByYxdm(String yxdm);
	
	void updateYxdh();
}
