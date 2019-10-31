package com.guido.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.guido.model.BaseList;
import com.guido.model.User;
import com.guido.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BaseListRepository baseListRepository;
	@Autowired
    private JdbcTemplate primaryJdbcTemplate;
	@Autowired
	private JdbcTemplate secondaryJdbcTemplate;

	/*
	 * @Test public void testSave() { User user =new User("smile","123456",30);
	 * userRepository.save(user,primaryJdbcTemplate);
	 * userRepository.save(user,secondaryJdbcTemplate); }
	 */
	@Test
	public void findAllBaseList() {
		List<BaseList> list = baseListRepository.findALL(primaryJdbcTemplate);
		for (BaseList baseList : list) {
			System.out.println(baseList.toString());
		}
	}

}