package com.info.mapper.one;

import java.util.List;

import com.info.model.User;

public interface User1Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}