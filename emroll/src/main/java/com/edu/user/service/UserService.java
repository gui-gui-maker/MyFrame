package com.edu.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edu.user.domain.User;

public interface UserService {
	
	User findByUserName(String userName) throws Exception;

	Page<User> findByCondition(User user, Pageable pageable);
	
}
