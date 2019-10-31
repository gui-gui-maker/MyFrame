package com.edu.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bean.Catalog;
import com.edu.hb.repository.CatalogDao;
import com.edu.hb.repository.PlanDao;
import com.edu.service.CatalogService;
import com.edu.util.StringUtil;
@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogDao catalogDao;
	@Autowired
	private PlanDao planDao;
	@Autowired
	private RedisService redisService;
	
	public Catalog findRootCatalog() throws Exception {
		Catalog rootCatalog = null;
		if(redisService.exists("rootCatalog")){
			rootCatalog = (Catalog) redisService.get("rootCatalog");
		}else {
			rootCatalog = catalogDao.findRootNode().get(0);
			
		}
		return rootCatalog;
	}
	
	@Override
	public Catalog findRoot() throws Exception {
		Catalog root = findRootCatalog();
		root.setHidden(false);
		//show
		show(root);
		//存到redis
		if(!redisService.exists("rootCatalog")) {
			redisService.set("rootCatalog", root);
		}
		return root;
	}
	public Catalog listOneByYxdh(String yxdh) throws Exception {
		Catalog root= this.findRootCatalog();
		//必须将所有node hide,因为之前保存在redis的已经全部show
		hide(root);
		//查询的时候已经去重
		List<String> distinctMl= planDao.findDymlByYxdh(yxdh);
		for(String nodeId : distinctMl) {
			showNode(root,nodeId);
		}
		filtNode(root,1);
		return root;
	}
	@Override
	public Catalog listOneByYxdm(String yxdm) throws Exception {
		// TODO Auto-generated method stub
		Catalog root= this.findRootCatalog();
		//必须将所有node hide
		hide(root);
		//查询的时候已经去重
		List<String> distinctMl= planDao.findDymlByYxdm(yxdm);
		for(String nodeId : distinctMl) {
			showNode(root,nodeId);
		}
		filtNode(root,1);
		return root;
	}
	private void showNode(Catalog root,String nodeId) {
		if(StringUtil.isEmpty(nodeId)) {
			return;
		}
		String s1 = nodeId.substring(0, 1);
		String s2 = nodeId.substring(1, 2);
		String s3 = nodeId.substring(2, 3);
		String s4 = nodeId.substring(3, 4);
		String s5 = nodeId.substring(4, 5);
		Catalog currentNode = null;
		if(!"0".equals(s1)) {
			for(Catalog childNode : root.getNodes()) {
				if(childNode.getId().substring(0, 1).equals(s1)) {
					childNode.setHidden(false);
					currentNode = childNode;
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
	
	private void filtNode(Catalog root,int flag) {
		
		List<Catalog> nodes = root.getNodes();
		if(nodes.size()==0) {
			return;
		}
		// 需要筛选的条件：从stuList中筛选不隐藏的node
		List<Boolean> filter = new ArrayList<Boolean>();
		filter.add(false);
		
		// JDK1.8提供了lambda表达式， 可以从stuList中过滤出符合条件的结果。
		// 定义结果集
		List<Catalog> result = nodes.stream().filter((Catalog c) -> filter.contains(c.isHidden())).collect(Collectors.toList());
 
		// 打印原有stuList集合中的数据
		//System.out.println("原有stuList集合中的数据");
		//set.forEach((Catalog c) -> System.out.println(c.getId() + "--->" + c.getText()));
 
		// 打印过滤筛选后的result结果
		//System.out.println("过滤筛选后的result结果");
		//result.forEach((Catalog c) -> System.out.println(c.getId() + "--->" + c.getText()));
		
		if(++flag <= 5) 
		{
			for(Catalog catalog : result) {
				filtNode(catalog, flag);
			}
		}
		root.setNodes(result);
	}
	public void show(Catalog root) {
		//排序
		//List<Catalog> children = root.getNodes().stream().sorted(Comparator.comparing(CatalogServiceImpl::comparingById)).collect(Collectors.toList());
		List<Catalog> children = root.getNodes();
		//设置显示
		for (Catalog child : children) {
			child.setHidden(false);
			if("0".equals(child.getSflb())) {
				child.setSelectable(false);
			}
			if(child.getNodes()!=null && child.getNodes().size()>0) {
				show(child);
			}
		}
	}
	public void hide(Catalog root) {
		//排序
		//List<Catalog> children = root.getNodes().stream().sorted(Comparator.comparing(CatalogServiceImpl::comparingById)).collect(Collectors.toList());
		List<Catalog> children = root.getNodes();
		//设置显示
		for (Catalog child : children) {
			child.setHidden(true);
			if("0".equals(child.getSflb())) {
				child.setSelectable(false);
			}
			if(child.getNodes()!=null && child.getNodes().size()>0) {
				hide(child);
			}
		}
	}
	private static String comparingById(Catalog catalog) {
		return catalog.getId();
	}
}
