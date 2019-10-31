package com.lsts.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.config.bean.RtDir;
import com.khnt.rtbox.config.dao.RtDirDao;
import com.lsts.report.bean.BaseReportsMenuConfig;
import com.lsts.report.dao.BaseReportsMenuConfigDao;

@Service("baseReportsMenuConfigService")
public class BaseReportsMenuConfigService extends EntityManageImpl<BaseReportsMenuConfig, BaseReportsMenuConfigDao> {

	@Autowired
	private BaseReportsMenuConfigDao reportsMenuConfigDao;
	@Autowired
	private RtDirDao rtDirDao;

	/**
	 * 查看报告目录配置信息
	 * author pingZhou
	 * @param id
	 * @return
	 */
	public List<BaseReportsMenuConfig> getReportMenuConfig(String id) {
		// TODO Auto-generated method stub
		return reportsMenuConfigDao.getReportMenuConfig(id);
	}
	
	/**
	 * 查询模板的目录
	 * author pingZhou
	 * @param rtCode
	 * @param reportId
	 * @return
	 */
	public JSONArray getRecordModelDir(String rtCode) {
		
		RtDir rtDir = rtDirDao.getByCode(rtCode);
		//删除以前的目录信息
		String dirJson = rtDir.getRtDirJson();
		
		net.sf.json.JSONArray dirss = net.sf.json.JSONArray.fromString(dirJson);
		System.out.println("----------"+dirss.getJSONObject(0).getString("children"));
		JSONArray dirs = JSONArray.parseArray(dirss.getJSONObject(0).getString("children"));
		JSONArray dirsN = new JSONArray();
		for (int i = 0; i < dirs.size(); i++) {
			JSONObject json = new  JSONObject();
			JSONObject jsonO = dirs.getJSONObject(i);
			
			json.put("id", jsonO.getString("code"));
			json.put("text", jsonO.getString("name"));
			
			dirsN.add(json);
		}
		
		
		return dirsN;
	}
}
