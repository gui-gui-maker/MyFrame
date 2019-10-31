package com.edu.business;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.business.service.MajorChangeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorChangeTest {
	 @Resource
	 private MajorChangeService majorChangeService;

	@Test
	public void test() {
		majorChangeService.splitMajor();
	}
	
	@Test
	public void testAll() {
		majorChangeService.splitAllMajor();
	}
}
