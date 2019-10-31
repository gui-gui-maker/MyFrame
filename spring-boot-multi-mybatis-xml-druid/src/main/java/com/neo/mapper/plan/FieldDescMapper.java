package com.neo.mapper.plan;

import com.neo.model.FieldDesc;

import java.util.List;

public interface FieldDescMapper {
	
	List<FieldDesc> getAll();

	int hasField(String fieldName);
}