package org.eureka;

import org.eureka.feign.Application;
import org.eureka.feign.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTests {
	@Autowired
    private ApiService apiService;

    @Test
    public void test(){
        try {
            System.out.println(">>>>>>>>>>>>>>>:"+apiService.index());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
