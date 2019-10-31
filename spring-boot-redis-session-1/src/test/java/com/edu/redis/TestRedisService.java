package com.edu.redis;

import com.edu.bean.Analysis;
import com.edu.bean.Role;
import com.edu.bean.User;
import com.edu.jdbc.repository.AnalysisDao;
import com.edu.service.impl.RedisService;

import net.sf.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {
	@Autowired
	private AnalysisDao analysisDao;
	@Autowired
	private RedisService redisService;

	/*
	 * @Test public void testString() throws Exception { redisService.set("neo",
	 * "ityouknow"); Assert.assertEquals("ityouknow", redisService.get("neo")); }
	 * 
	 * @Test public void testObj() throws Exception { User user=new User();
	 * user.setUserName("ityouknow@126.com"); user.setPassword("smile"); Role role =
	 * new Role(); role.setName("college"); role.setCode("college");
	 * role.setDepict("院校用户"); Set<Role> set = new HashSet<Role>(); set.add(role);
	 * user.setRoles(set); redisService.set("user",user); User userR=(User)
	 * redisService.get("user"); System.out.println("userR== "+userR.toString()); }
	 */
	/*
	 * @Test public void testObj() throws Exception { Set<Object> roles =
	 * redisService.setMembers("rolesFD62CD829DEB4DD29CDEB2C87BA947AC"); for (Object
	 * object : roles) {
	 * 
	 * //Role role = (Role) JSONObject.toBean(JSONObject.fromObject(object),
	 * Role.class); //System.out.println(role.toString());
	 * System.out.println(object.toString()); } }
	 */
    @Test
	public void queryList()  {
		List<Analysis> users=analysisDao.findByParams("5112","1","02",551);
		for (Analysis user:users){
			System.out.println("user == "+user.toString());
		}
	}
}