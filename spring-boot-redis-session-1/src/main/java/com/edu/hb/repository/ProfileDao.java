package com.edu.hb.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.bean.Profile;
import com.edu.bean.User;

public interface ProfileDao extends JpaRepository<Profile, String> {
	@Query("from Profile p where p.user = ?1")
	Set<Profile> findByUser(User user) throws Exception;

}
