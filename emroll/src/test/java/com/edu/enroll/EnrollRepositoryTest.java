package com.edu.enroll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.business.dao.EnrollRepository;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrollRepositoryTest {

    @Resource
    private EnrollRepository enrollRepository;

	@Test
	public void test() {
		enrollRepository.findAll();
	}

}
