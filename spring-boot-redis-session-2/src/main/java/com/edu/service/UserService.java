package com.edu.service;

import java.util.List;

import com.edu.bean.User;

public interface UserService {
	
	int save(User user);

    int update(User user);

    int delete(String id);

    List<User> findALL();

    User findById(String id);
    
    User findByUserName(String userName);
}
