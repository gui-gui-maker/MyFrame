package com.edu.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.user.dao.UserRepository;
import com.edu.user.domain.User;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

	@Test
	public void test() {
		User user = new User("lily", "1234", "aa@126.com", "ll", new Date());
		userRepository.save(user);
		userRepository.save(new User("lucy", "aa123456", "aa@126.com", "aa", new Date()));
		userRepository.save(new User("lily", "bb123456", "bb@126.com", "bb", new Date()));
		userRepository.save(new User("kate", "cc123456", "cc@126.com", "cc", new Date()));

		// Assert.assertEquals(9,userRepository.findAll().size());
		// Assert.assertEquals("bb",userRepository.findByUserNameOrEmail("bb","cc@126.com").getNickName());
		userRepository.delete(userRepository.findByUserName("aa"));
	}

}
