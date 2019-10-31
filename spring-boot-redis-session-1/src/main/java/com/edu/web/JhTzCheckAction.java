package com.edu.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bean.JhTzDetail;

@Controller
@RequestMapping("jhtzAction")
public class JhTzCheckAction {
	@Autowired
	com.edu.jdbc.repository.impl.AddJHJDaoImpl addJHJDaoImpl;
	
	@RequestMapping(value="/page")
	public String page(){
		return "tz-comparing";
	}
	@RequestMapping(value="/total")
	@ResponseBody
	public HashMap<String,Object> queryTotal(){
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		
		wapper.put("rows", new Object[]{addJHJDaoImpl.queryTotal()});
		return wapper;
	}
	/**
	 * 来源系统与录取系统计划
	 * @return
	 */
	@RequestMapping(value="/lyNotEqualLq")
	@ResponseBody
	public HashMap<String,Object> lyNotEqualLq(){
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		
		wapper.put("rows", addJHJDaoImpl.lyNotEqualLq());
		return wapper;
	}
	
	/**
	 * 来源系统调整了但是录取系统没有增加，或反之
	 * @return
	 */
	@RequestMapping(value="/onlyAddLyOrLq")
	@ResponseBody
	public HashMap<String,Object> onlyAddLyOrLq(){
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("rows", addJHJDaoImpl.onlyAddLyOrLq());
		return wapper;
	}
	/**
	 * 调整详细
	 * @return
	 */
	@RequestMapping(value="/yxAddJhDetail")
	@ResponseBody
	public HashMap<String,Object> yxAddJhDetail(String id){
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		
		wapper.put("rows", addJHJDaoImpl.yxAddJhDetail(id));
		return wapper;
	}
	@RequestMapping(value="/yxzyTzDetail")
	@ResponseBody
	public HashMap<String,Object> yxzyTzDetail(String yxdh,String pcdm,String kldm,String jhxz){
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		List<JhTzDetail> list = addJHJDaoImpl.yxAddJhDetail();
		//java 8 list 聚合
		Map<String,Integer> map = list.stream().collect(Collectors.groupingBy(JhTzDetail::getYxzy,Collectors.summingInt(JhTzDetail::getTzjhs)));
		List<JhTzDetail> resultList = new ArrayList<>();
		for (JhTzDetail jhTzDetail : list) {
			if(map.containsKey(jhTzDetail.getYxzy()) && map.get(jhTzDetail.getYxzy())!=0 && jhTzDetail.getPcdm() !=null) {
				resultList.add(jhTzDetail);
			}
		}
		resultList.stream().sorted(Comparator.comparing(JhTzCheckAction::comparingByYxdh)
				.thenComparing(JhTzCheckAction::comparingByPcdm)
				.thenComparing(JhTzCheckAction::comparingByZydh))
				.collect(Collectors.toList());
		wapper.put("rows",resultList);
		wapper.put("total",resultList.size());
		return wapper;
	}
	private static String comparingByYxdh(JhTzDetail t) {
		return t.getYxdh();
	}

	private static String comparingByPcdm(JhTzDetail t) {
		
		return t.getPcdm();
	}

	private static String comparingByZydh(JhTzDetail t) {
		return t.getZydh();
	}
	/**
	 * 统计各批次计划增加数
	 * @return
	 */
	@RequestMapping(value="/toPcAddJh")
	public String toPcAddJh(){
		return "tz-pc";
	}
	
	
	@RequestMapping(value="/statisticsPcAddJh")
	@ResponseBody
	public HashMap<String,Object> statisticsPcAddJh(){
		HashMap<String,Object> wapper = new HashMap<String,Object>();
		
		wapper.put("rows", addJHJDaoImpl.queryAddJh());
		return wapper;
	}
}
