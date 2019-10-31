package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.User;
import com.edu.hb.repository.UserDao;
import com.edu.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> findALL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String userName) {
		User user = userDao.findByUsername(userName);
		/*
		 * if(null!=user.getRoles() && user.getRoles().size()>0) {
		 * 
		 * user.getRoles().iterator().next().getCode(); }else { user.setRoles(null); }
		 * if(null!=user.getContacts() && user.getContacts().size()>0) {
		 * 
		 * user.getContacts().iterator().next().getContact(); }else {
		 * user.setContacts(null); }
		 */
		return user;
	}

}
