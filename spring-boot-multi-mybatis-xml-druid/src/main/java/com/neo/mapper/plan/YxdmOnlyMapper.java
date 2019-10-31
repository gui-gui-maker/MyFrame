package com.neo.mapper.plan;

import com.neo.model.ext.YxdmOnly;

import java.util.List;

public interface YxdmOnlyMapper {
	
	List<YxdmOnly> findYxdmByYxdm(String yxdm);
	
	List<YxdmOnly> findYxdhByYxdm(String yxdm);
	
	List<YxdmOnly> getAll();
	
	List<YxdmOnly> getAllYxdh();

}