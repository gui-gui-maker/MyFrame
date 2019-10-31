package com.edu.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.bean.Analysis;
import com.edu.bean.Catalog;
import com.edu.hb.repository.CatalogDao;
import com.edu.hb.repository.PlanDao;
import com.edu.jdbc.repository.AnalysisDao;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private AnalysisDao analysisDao;
	
	@Autowired
	private CatalogDao listsDao;
	@Autowired
	private PlanDao publishPlanDao;

	/*
	 * @Test public void queryList() { List<Analysis>
	 * users=analysisDao.findByParams("5112","5","01,02,03,04,07",551); for
	 * (Analysis user:users){ System.out.println("user == "+user.toString()); } }
	 */
	@Test
	public void queryList()  {
		List<Catalog> lists= listsDao.findRootNode();
		List<String> mls= publishPlanDao.findDymlByYxdm("10633");
		markAll(mls,lists);
	}


	private void markAll(List<String> mls,List<Catalog> lists){
		for(String ml : mls) {
			Catalog currentNode = null;
			String s1 = ml.substring(0, 1);
			String s2 = ml.substring(1, 2);
			String s3 = ml.substring(2, 3);
			String s4 = ml.substring(3, 4);
			String s5 = ml.substring(4, 5);
			if(!"0".equals(s1)) {
				for(Catalog node : lists) {
					if(node.getId().substring(0, 1).equals(s1)) {
						node.setHidden(false);
						currentNode = node;
						break;
					}
				}
			}
			if(!"0".equals(s2)) 
			{
				for(Catalog childNode : currentNode.getNodes()) {
					if(childNode.getId().substring(1, 2).equals(s2)) {
						childNode.setHidden(false);
						currentNode = childNode;
						break;
					}
				}
			} 
			if(!"0".equals(s3)) 
			{
				for(Catalog childNode : currentNode.getNodes()) {
					if(childNode.getId().substring(2, 3).equals(s3)) {
						childNode.setHidden(false);
						currentNode = childNode;
						break;
					}
				}
			}
			if(!"0".equals(s4)) 
			{
				for(Catalog childNode : currentNode.getNodes()) {
					if(childNode.getId().substring(3, 4).equals(s4)) {
						childNode.setHidden(false);
						currentNode = childNode;
						break;
					}
				}
			}
			if(!"0".equals(s5)) 
			{
				for(Catalog childNode : currentNode.getNodes()) {
					if(childNode.getId().substring(4, 5).equals(s5)) {
						childNode.setHidden(false);
						currentNode = childNode;
						break;
					}
				}
			}
		}
	}
}