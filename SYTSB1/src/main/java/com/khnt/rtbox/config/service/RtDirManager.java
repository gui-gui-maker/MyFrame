package com.khnt.rtbox.config.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rtbox.config.bean.RtDir;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.bean.RtPersonDir;
import com.khnt.rtbox.config.bean.RtPersonDirVersion;
import com.khnt.rtbox.config.dao.RtDirDao;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.handle.paging.RtPagingOption;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtDirManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Service("rtDirManager")
public class RtDirManager extends EntityManageImpl<RtDir, RtDirDao> {
	@Autowired
	RtDirDao rtDirDao;
	@Autowired
	RtPageManager rtPageManager;
	@Autowired
	RtPersonDirManager rtPersonDirManager;
	@Autowired
	RtPersonDirVersionManager rtPersonDirVersionManager;
	static Integer MINVERSION = RtPath.MINVERSION;
	static String basePath = Factory.getWebRoot();

	@SuppressWarnings("unchecked")
	public String getDir(String id, String code) throws Exception {

		if (StringUtil.isNotEmpty(id)) {
			List<RtPersonDir> rpd = this.rtDirDao.createQuery("from RtPersonDir where fkBusinessId=? and rtCode=?", id, code).list();
			if (rpd != null && !rpd.isEmpty()) {
				return rpd.get(0).getRtDirJson();
			}
		}
		List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", code).list();
		if (rd != null && !rd.isEmpty()) {
			return rd.get(0).getRtDirJson();
		}
		throw new Exception("没有找到目录,code:" + code);

	}
	
	@SuppressWarnings("unchecked")
	public String getTempDirs(String code) throws Exception {
		String jsonstring = "";
		List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", code).list();
		if (rd != null && !rd.isEmpty()) {
			String rtDirJson = rd.get(0).getRtDirJson();
			if (StringUtil.isNotEmpty(rtDirJson)) {
				JSONArray array = new JSONArray(rtDirJson);
				JSONObject root = array.getJSONObject(0);
				JSONArray children = root.getJSONArray("children");

				for (int i = 0; i < children.length(); i++) {
					JSONObject child = children.getJSONObject(i);
					jsonstring += "{\"id\":\"" + child.getString("code") + "\",\"text\":\""
							+ child.getString("name") + "\"},";
				}

				if (jsonstring.endsWith(",")) {
					jsonstring = jsonstring.substring(0, jsonstring.length() - 1);
				}
				jsonstring = "[" + jsonstring + "]";
				return jsonstring;
			}
		}
		throw new Exception("没有找到模版目录，code:" + code);
	}

	/**
	 * 获取原始目录JSON
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getDir(String code) throws Exception {
		List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", code).list();
		if (rd != null && !rd.isEmpty()) {
			return rd.get(0).getRtDirJson();
		}
		throw new Exception("没有找到目录,code:" + code);

	}

	// 设置目录树
	public void setDir(String id, RtDir rtDir, String oldCode, String newCode, String setType) throws Exception {
		
		//setDefault参数代表设置报告默认目录
		// 查询是否已设置
		int version = MINVERSION;
		String rtCode = rtDir.getRtCode();
		if((rtDir==null||rtDir.getRtCode()==null)&&"setDefault".equals(setType)){
			rtCode = newCode;
		}
		RtPersonDir rpd = this.rtPersonDirManager.getByBusinessId(id, rtCode);
		String oldDocPath = null;// 即将被操作的老版本DOCX
		if (rpd == null) {
			rpd = new RtPersonDir();
			if ("setDefault".equals(setType)) {
				
				List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", newCode).list();
				rtDir = rd.get(0);
				if(rtDir.getRtDefaultDirJson()==null){
					return;
				}
				rpd.setFkBusinessId(id);
				rpd.setRtCode(rtDir.getRtCode());
				String oldDirJson = rpd.getRtDirJson();
				rpd.setRtDirJson(rtDir.getRtDefaultDirJson());
				rpd.setFkCreateUserId(SecurityUtil.getSecurityUser().getId());
				rpd.setCreateTime(new Date());
				rpd.setVersion(version);// 版本号
				// rpd.setXmlPath(getXmlPath(rpd));
				this.rtPersonDirManager.save(rpd);
				/*String docPath = RtPath.basePath+RtPath.getExportPath(rpd);
				File oldDocFile = null;
				if (oldDocPath == null) {
					RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
					oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
				}else{
					oldDocPath = RtPath.basePath + oldDocFile;
				}
				oldDocFile = new File(oldDocPath);
				if (!oldDocFile.exists()) {
					RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
					oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
					oldDocFile = new File(oldDocPath);
					if (!oldDocFile.exists()) {
						throw new Exception("未找到原始模板");
					}
				}
				File doc = new File(docPath);
				if (!doc.getParentFile().exists()) {
					doc.getParentFile().mkdirs();
				}
				if(!doc.exists()){
					doc.createNewFile();
				}
				
				
				RtPagingOption rpo = new RtPagingOption();
				JSONArray array = new JSONArray(rtDir.getRtDirJson());
				JSONObject root = array.getJSONObject(0);
				JSONArray children = root.getJSONArray("children");
				if(rtDir.getRtDefaultDirJson()!=null){
					array = new JSONArray(rtDir.getRtDefaultDirJson());
					children = array.getJSONObject(0).getJSONArray("children");
				}
				List<String> dirs = new ArrayList<String>();
				for (int i = 0; i < children.length(); i++) {
					JSONObject child = children.getJSONObject(i);
					dirs.add(child.getString("name"));
				}
				rpo.reset(oldDocFile, doc, dirs);*/
				return;
			}
			
			
			
		} else {
			version = rpd.getVersion();
			// 获取老版本DOCX地址
			oldDocPath =  RtPath.basePath + RtPath.getExportPath(rpd);

			version++;
		}
		if ("setDefault".equals(setType)) {
			return;
		}
		rpd.setFkBusinessId(id);
		rpd.setRtCode(rtDir.getRtCode());
		String oldDirJson = rpd.getRtDirJson();
		rpd.setRtDirJson(rtDir.getRtDirJson());
		rpd.setFkCreateUserId(SecurityUtil.getSecurityUser().getId());
		rpd.setCreateTime(new Date());
		rpd.setVersion(version);// 版本号
		// rpd.setXmlPath(getXmlPath(rpd));
		this.rtPersonDirManager.save(rpd);

		// 保存历史记录
		RtPersonDirVersion dirVersion = new RtPersonDirVersion();
		dirVersion.setRtPersonDir(rpd);
		dirVersion.setFkBusinessId(id);
		dirVersion.setRtCode(rtDir.getRtCode());
		dirVersion.setRtDirJson(rtDir.getRtDirJson());
		dirVersion.setFkCreateUserId(SecurityUtil.getSecurityUser().getId());
		dirVersion.setCreateTime(new Date());
		dirVersion.setVersion(version);// 版本号
		this.rtPersonDirVersionManager.save(dirVersion);

		// 版本为0，需要将模板XML(RtPath.tplXmlPath)复制到输出位置((RtPath.tplXmlPath))
		// String xmlPath = RtPath.getXmlPath(rpd);
		/*String docPath = RtPath.basePath+RtPath.getExportPath(rpd);
		File oldDocFile = null;
		if (oldDocPath == null) {
			RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
			oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
		}else{
			oldDocPath = RtPath.basePath + oldDocFile;
		}
		oldDocFile = new File(oldDocPath);
		if (!oldDocFile.exists()) {
			RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
			oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
			oldDocFile = new File(oldDocPath);
			if (!oldDocFile.exists()) {
				throw new Exception("未找到原始模板");
			}
		}
		File doc = new File(docPath);
		if (!doc.getParentFile().exists()) {
			doc.getParentFile().mkdirs();
		}
		if(!doc.exists()){
			doc.createNewFile();
		}
		
		
		RtPagingOption rpo = new RtPagingOption();
		JSONArray array = new JSONArray(rtDir.getRtDirJson());
		JSONObject root = array.getJSONObject(0);
		JSONArray children = root.getJSONArray("children");
		if ("delPage".equals(setType)) {
			array = new JSONArray(oldDirJson);
			root = array.getJSONObject(0);
			StringBuilder tmp = new StringBuilder();
			parseChildrenCode(root, oldCode, tmp);
			String dirName = tmp.toString();
			if (dirName == null) {
				throw new KhntException("未找到对应页面");
			}
			rpo.delPage(oldDocFile, doc, dirName, newCode);
		} else if ("copyPage".equals(setType)) {
			RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
			oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
			String dirName = null;
			for (int i = 0; i < children.length(); i++) {
				JSONObject child = children.getJSONObject(i);
				if (child.getString("code").equals(oldCode)) {
					dirName = child.getString("name");
				}
			}

			StringBuilder tmp = new StringBuilder();
			parseChildrenCode(root, newCode, tmp);
			String newDirName = tmp.toString();
			if (dirName == null) {
				throw new KhntException("未找到对应页面");
			}
			rpo.copyPage(oldDocFile, doc, dirName, newCode, newDirName);
		} else if ("reset".equals(setType)) {
			List<String> dirs = new ArrayList<String>();
			for (int i = 0; i < children.length(); i++) {
				JSONObject child = children.getJSONObject(i);
				dirs.add(child.getString("name"));
			}
			rpo.reset(oldDocFile, doc, dirs);
		} */

	}

	@SuppressWarnings("unchecked")
	public RtPersonDir getRpd(String fkBusinessId, String code) throws Exception {
		// 目录版本，查找XML版本号
		/*
		 * if (StringUtil.isNotEmpty(fkBusinessId)) { throw new
		 * Exception("getRpd error :未找到对应的业务ID..."); }
		 */
		List<RtPersonDir> rpds = this.rtDirDao.createQuery("from RtPersonDir where fkBusinessId=? and rtCode=?", fkBusinessId, code).list();
		if (rpds != null && !rpds.isEmpty()) {
			return rpds.get(0);
		}
		List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", code).list();
		if (rd != null && !rd.isEmpty()) {
			RtDir rtDir = rd.get(0);
			RtPersonDir rpd = new RtPersonDir();
			rpd.setFkBusinessId(fkBusinessId);
			rpd.setRtCode(rtDir.getRtCode());
			rpd.setRtDirJson(rtDir.getRtDirJson());
			rpd.setVersion(MINVERSION);// 版本号
			return rpd;
		}
		throw new Exception("getRpd error :未找到业务CODE(" + code + ")配置的相应模板...");
	}

	/**
	 * 获取原始目录
	 * 
	 * @param fkBusinessId
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RtDir> getRd(String code) throws Exception {
		List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", code).list();
		if (rd != null && !rd.isEmpty()) {
			return rd;
		}
		throw new Exception("getRpd error :未找到业务CODE(" + code + ")配置的相应模板...");
	}

	// ctrl+z 撤销目录修改
	private void ctrlZ() {
		// TODO Auto-generated method stub

	}

	// ctrl+Y 撤销目录树之前的撤销操作
	private void ctrlY() {
		// TODO Auto-generated method stub

	}

	public void delDir(String id, String rtCode, String code) {
		RtPersonDir rpd = this.rtPersonDirManager.getByBusinessId(id, rtCode);

	}

	public String previewSingle(String id, String code, String rtCode,String sql,HashMap<String, Object> infoMap,String folder,String page) throws Exception {
		
		RtDir rtDir = this.rtDirDao.getByCode(rtCode);
		// 查询是否已设置
		RtPersonDir rpd = this.rtPersonDirManager.getByBusinessId(id, rtCode);
		String oldDocPath = null;// 即将被操作的老版本DOCX
		if (rpd == null) {
			rpd = new RtPersonDir();
			rpd.setRtCode(rtDir.getRtCode());
			rpd.setRtDirJson(rtDir.getRtDirJson());
			rpd.setFkBusinessId(id);
			rpd.setVersion(1);
		} else {
			oldDocPath = RtPath.getExportPath(rpd);
		}

		RtPagingOption rpo = new RtPagingOption();
		JSONArray array = new JSONArray(rpd.getRtDirJson());
		JSONObject root = array.getJSONObject(0);
		StringBuilder tmp = new StringBuilder();
		parseChildrenCode(root, code, tmp);
		String dirName = tmp.toString();
		if (StringUtil.isEmpty(dirName)) {
			throw new KhntException("未找到对应页面");
		}

		String docPath = RtPath.basePath+RtPath.getPreviewSinglePath(rpd);
		File doc = new File(docPath);
		if (!doc.getParentFile().exists()) {
			doc.getParentFile().mkdirs();
		}

		File oldDocFile = null;
		if (oldDocPath == null) {
			RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
			oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
		}
		oldDocFile = new File(oldDocPath);
		if (!oldDocFile.exists()) {
			RtPage rtPage = this.rtPageManager.getRtPageByCode(rtDir.getRtCode());
			oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
			oldDocFile = new File(oldDocPath);
			if (!oldDocFile.exists()) {
				throw new Exception("未找到原始模板");
			}
		}

		rpo.previewSingle(oldDocFile, doc, dirName, code);
		RtPersonDir tmpRpd = new RtPersonDir();
		tmpRpd.setVersion(rpd.getVersion());
		tmpRpd.setRtCode(rpd.getRtCode());
		tmpRpd.setFkBusinessId("preview/" + id);// 参见RTPATH.getPreviewSinglePath
		
		this.rtPageManager.previewSingle(id, rtCode, sql, tmpRpd,code, infoMap, folder,page);
		return RtPath.getPreviewSingleDocPath(tmpRpd,code);
	}

	private void parseChildrenCode(JSONObject root, String code, StringBuilder dirName) throws JSONException {
		if (root.has("children")) {
			JSONArray children = root.getJSONArray("children");
			for (int i = 0; i < children.length(); i++) {
				JSONObject child = children.getJSONObject(i);
				if (child.getString("code").equals(code)) {
					dirName.append(child.getString("name"));
					return;
				} else {
					parseChildrenCode(child, code, dirName);
				}
			}
		}
	}

	public String getErrorPage(String id) {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String errorPage = "";
		String sql = "select distinct t.page from RT_MARKS t where t.fk_business_id=? and ( t.status='0' or t.mark_by_id=? ) and is_del='0'";
		List<Object> list =  this.rtDirDao.createSQLQuery(sql, id,user.getId()).list();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=null){
				if(StringUtil.isNotEmpty(errorPage)){
					errorPage = errorPage + ",";
				}
				errorPage = errorPage + list.get(i).toString();
			}
			
		}
		
		return errorPage;
	}
	
	
	
	// 设置目录树
			public void resetModel(String id, RtPersonDir rpd) throws Exception {
				
				//setDefault参数代表设置报告默认目录
				// 查询是否已设置
				int version = rpd.getVersion();
				// 获取老版本DOCX地址
				String oldDocPath = null;
			

				// 版本为0，需要将模板XML(RtPath.tplXmlPath)复制到输出位置((RtPath.tplXmlPath))
				// String xmlPath = RtPath.getXmlPath(rpd);
				String docPath = RtPath.basePath+RtPath.getExportPath(rpd);
				
				RtPage rtPage = this.rtPageManager.getRtPageByCode(rpd.getRtCode());
				File oldDocFile = null;
				if (oldDocPath == null) {
				
					oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
				}
				
				
				
				oldDocFile = new File(oldDocPath);
				if (!oldDocFile.exists()) {

					oldDocPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
					oldDocFile = new File(oldDocPath);
					if (!oldDocFile.exists()) {
						throw new Exception("未找到原始模板");
					}
				}
				File doc = new File(docPath);
				if (!doc.getParentFile().exists()) {
					doc.getParentFile().mkdirs();
				}
				if(!doc.exists()){
					doc.createNewFile();
				}
				
				
				RtPagingOption rpo = new RtPagingOption();
				JSONArray array = new JSONArray(rpd.getRtDirJson());
				JSONObject root = array.getJSONObject(0);
				JSONArray children = root.getJSONArray("children");
			
				List<String> dirs = new ArrayList<String>();
			/*	//重选目录必须从最原始模板改
					
					for (int i = 0; i < children.length(); i++) {
						JSONObject child = children.getJSONObject(i);
						dirs.add(child.getString("name"));
					}*/
					parseChildren(root,dirs);
					rpo.resetModel(oldDocFile, doc, dirs);
				
			}


			private void parseChildren(JSONObject root, List<String> dirs) throws JSONException {
				if (root.has("children")) {
					JSONArray children = root.getJSONArray("children");
					for (int i = 0; i < children.length(); i++) {
						JSONObject child = children.getJSONObject(i);
						dirs.add(child.getString("name"));
						if (child.has("children")){
							parseChildren(child,dirs);
						}
					}
				}
			}
}
