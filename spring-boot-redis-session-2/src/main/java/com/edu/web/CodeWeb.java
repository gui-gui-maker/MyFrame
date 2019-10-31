package com.edu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.bean.CodeUniversity;
import com.edu.service.CodeService;
import com.edu.service.UniversityService;
import com.edu.util.StringUtil;

@Controller
@RequestMapping("codeWeb")
public class CodeWeb {
	@Autowired
	CodeService codeService;
	@Autowired
	UniversityService universityService;
	
	@RequestMapping(value="getPcCode")
	@ResponseBody
	public Map<String,Object> getPcCode(int nf) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",codeService.getPcdmByNf(nf));
		return wapper;
	}
	@RequestMapping(value="getKlCode")
	@ResponseBody
	public Map<String,Object> getKlCode(int nf) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",codeService.getKlByNf(nf));
		return wapper;
	}
	@RequestMapping(value="getJhxzCode")
	@ResponseBody
	public Map<String,Object> getJhxzCode(int nf) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",codeService.getJhxzByNf(nf));
		return wapper;
	}
	@RequestMapping(value="getJhlbCode")
	@ResponseBody
	public Map<String,Object> getJhlbCode(int nf) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",codeService.getJhlbByNf(nf));
		return wapper;
	}
	@RequestMapping(value="getZklxCode")
	@ResponseBody
	public Map<String,Object> getZklxCode(int nf) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		wapper.put("success",true);
		wapper.put("data",codeService.getZklxByNf(nf));
		return wapper;
	}
	@RequestMapping(value="getUniversityList")
	@ResponseBody
	public Map<String,Object> getUniversityList(int nf,String yxdm) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		List<CodeUniversity> list= null;
		if(StringUtil.isEmpty(yxdm)) {
			list = universityService.findByNf(nf);
		}else {
			list = universityService.findByYxdmAndNf(yxdm, nf);
		}
		//去重
		List<Map<String,Object>> result = new ArrayList<>();
		Map<String,Object> map=new HashMap<>();
		for(CodeUniversity cu : list) {
			
			map.put(cu.getYxdh(), cu.getYxdm()+cu.getYxmc());
		} 
		Iterator<String> i = map.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			Object obj = map.get(key);
			Map<String,Object> bMap = new HashMap<>();
			bMap.put("yxdh",key);
			bMap.put("yxmc",obj);
			result.add(bMap);
		}
		wapper.put("success",true);
		wapper.put("data",result);
		return wapper;
	}
	@RequestMapping(value="findUniversityByYxdm")
	@ResponseBody
	public Map<String,Object> findUniversityByYxdm(String yxdm,int nf) throws Exception{
		Map<String,Object> wapper = new HashMap<String,Object>();
		List<CodeUniversity> result = universityService.findByYxdmAndNf(yxdm,nf);
		
		wapper.put("success",true);
		wapper.put("data",result);
		return wapper;
	}
}
