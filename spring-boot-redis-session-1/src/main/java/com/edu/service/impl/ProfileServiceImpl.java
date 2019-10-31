package com.edu.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.Profile;
import com.edu.bean.User;
import com.edu.hb.repository.ProfileDao;
import com.edu.service.ProfileService;
@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileDao profileDao;

	@Override
	public void save(Profile profile) throws Exception {
		
		profileDao.save(profile);
	}

	@Override
	public Profile findByUser(User user) throws Exception {
		Set<Profile> ps = profileDao.findByUser(user);
		if(ps.iterator().hasNext()) {
			return ps.iterator().next();
		}else {
			return null;
		} 
	}

}
