package com.edu.service;

import com.edu.bean.Profile;
import com.edu.bean.User;

public interface ProfileService {
	
	void save(Profile profile) throws Exception;
	
	Profile findByUser(User user) throws Exception;
	

}
