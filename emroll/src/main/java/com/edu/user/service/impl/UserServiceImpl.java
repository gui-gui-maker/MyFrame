package com.edu.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edu.user.dao.UserRepository;
import com.edu.user.domain.User;
import com.edu.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUserName(String userName) throws Exception{
		User user = userRepository.findByUserName(userName);
		//System.out.println("权限数量："+user.getPermissions().size());
		return user;
	}

	@Override
	public Page<User> findByCondition(User user, Pageable pageable) {
		return userRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //默认查询未删除的
           // predicates.add(cb.equal(root.get("state"),"0"));
            //equal 示例
			/*
			 * if (!StringUtils.isEmpty(user.getYxdh())){
			 * predicates.add(cb.equal(root.get("yxdh"),user.getYxdh())); } if
			 * (!StringUtils.isEmpty(user.getDyml())){
			 * predicates.add(cb.equal(root.get("dyml"),user.getDyml())); }
			 */
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        }, pageable);
	}

}
