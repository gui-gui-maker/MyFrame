package com.guido.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guido.rabbit.many.DdlSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
	
	  @Autowired 
	  private DdlSender ddlSender;
	 
	@Test
	public void testDdl() throws Exception {
		for (int i = 0; i < 10; i++) {
			ddlSender.send("dead_test"+i);
		}
	}

}