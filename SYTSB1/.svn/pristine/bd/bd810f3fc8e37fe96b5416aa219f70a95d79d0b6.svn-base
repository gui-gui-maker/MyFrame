package com.khnt.rtbox.config.web;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.bean.RtPersonDir;
import com.khnt.rtbox.config.service.RtDirManager;
import com.khnt.rtbox.config.service.RtPageManager;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Page
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Controller
@RequestMapping("com/rt/page")
public class RtPageAction extends SpringSupportAction<RtPage, RtPageManager> {
	@Autowired
	private RtPageManager rtPageManager;
	@Autowired
	private RtDirManager rtDirManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, RtPage entity) throws Exception {
		try {
			this.rtPageManager.save(entity);
			
			// 生成模板:状态为空或禁用可修改模版
			if(StringUtil.isEmpty(entity.getStatus()) || RtSign.STATUS_DISABLE.equals(entity.getStatus())) {
				this.rtPageManager.createTpl(entity);// 生成模版基础页面
				String attachment = request.getParameter("attachment");
				if(StringUtil.isNotEmpty(attachment)) {
					rtPageManager.setTemplate(entity, attachment);// 转换模版
				}
			}

			return JsonWrapper.successWrapper(entity);
		} catch (KhntException e) {
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
	
	/**
	 * 复制模版
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "copyTpl")
	@ResponseBody
	public HashMap<String, Object> copyTpl(HttpServletRequest request, String id) throws Exception {
		try {
			RtPage entity = rtPageManager.copyTpl(id);
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
	
	@RequestMapping(value = "setTemplate")
	@ResponseBody
	public HashMap<String, Object> setTemplate(HttpServletRequest request, RtPage entity, String attachment) throws Exception {
		try {
			entity = rtPageManager.setTemplate(entity, attachment);
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
	
	@RequestMapping(value = "enable", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> enable(HttpServletRequest request, String id) throws Exception {
		try {
			RtPage entity = rtPageManager.enable(id);
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
	
	@RequestMapping(value = "disable", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> disable(HttpServletRequest request, String id) throws Exception {
		try {
			RtPage entity = rtPageManager.disable(id);
			return JsonWrapper.successWrapper(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}

	/**
	 * 
	 * @param request
	 * @param id
	 *            报表ID
	 * @param attId
	 *            上传文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rtParse")
	@ResponseBody
	public HashMap<String, Object> rtParse(HttpServletRequest request, String id, String attId) throws Exception {
		try {
			this.rtPageManager.rtParse(id, attId);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("解析失败！");
		}
	}

	/**
	 * 导出报告 author pingZhou
	 * 
	 * @param request
	 * @param id
	 * @param code
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rtRevert")
	@ResponseBody
	public HashMap<String, Object> rtRevert(HttpServletRequest request, String id, String code, String sql) throws Exception {
		try {
			RtPersonDir rpd = rtDirManager.getRpd(id, code);
			HashMap<String, Object> infoMap = new HashMap<String, Object>();
			RtPage rtPage = this.rtPageManager.rtRevert(id, code, sql, rpd,infoMap);
			return JsonWrapper.successWrapper("data", rtPage);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("解析失败！");
		}
	}

	@RequestMapping("test")
	@ResponseBody
	public void test(HttpServletRequest request, String id, String code) {

		String path = "D:\\rtbox\\output\\word\\201705\\4028818b5ba83701015ba91392370010.docx";
		System.out.println("开始打印");
		ComThread.InitSTA();
		ActiveXComponent word = new ActiveXComponent("Word.Application");
		Dispatch doc = null;
		Dispatch.put(word, "Visible", new Variant(false));
		Dispatch docs = word.getProperty("Documents").toDispatch();
		doc = Dispatch.call(docs, "Open", path).toDispatch();

		try {
			Dispatch.call(doc, "PrintOut", new Object[] { "", "", 1 - 4, "" });// 打印
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("打印失败");
		} finally {
			try {
				if (doc != null) {
					Dispatch.call(doc, "Close", new Variant(0));
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// 释放资源
			ComThread.Release();
		}

	}

	/**
	 * 生成默认模板
	 * author pingZhou
	 * @param request
	 * @param id 报告配置id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createDefaultTemplete")
	@ResponseBody
	public HashMap<String, Object> createDefaultTemplete(HttpServletRequest request, String id,String code) throws Exception {
		try {
			
			rtPageManager.createDefaultTemplete(id, code);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	/**
	 * 保存原始记录模板文件可视化修改
	 * author pingZhou
	 * @param request
	 * @param id
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveIndexChange")
	@ResponseBody
	public HashMap<String, Object> saveIndexChange(HttpServletRequest request, String path,String content) throws Exception {
		try {
			
			rtPageManager.saveIndexChange(request,path,content);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	

	/**
	 * 保存报告文件可视化修改
	 * author pingZhou
	 * @param request
	 * @param id
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveIndexChangeReport")
	@ResponseBody
	public HashMap<String, Object> saveIndexChangeReport(HttpServletRequest request,String content,String rtboxId,String pageCode) throws Exception {
		try {
			
			rtPageManager.saveIndexChangeReport(request,content,rtboxId,pageCode);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	/**
	 * 取页面内容
	 * author pingZhou
	 * @param request
	 * @param rtPageId 模板id
	 * @param pageCode 页面code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPageContent")
	@ResponseBody
	public HashMap<String, Object> getPageContent(HttpServletRequest request,String rtPageId,String pageCode,String rtCode) throws Exception {
		try {
			
			String content  = rtPageManager.getPageContent(request,rtPageId,pageCode,rtCode);
			return JsonWrapper.successWrapper(content);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	

	@RequestMapping("getDir")
	@ResponseBody
	public String getDir(HttpServletRequest request, String id,String infoId,String code) {

		try {
			String json = this.rtPageManager.getDir(id,infoId,code);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}

	}
	
	@RequestMapping("getTempDir")
	@ResponseBody
	public HashMap<String, Object> getTempDir(HttpServletRequest request,String code) {

		

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String json = this.rtPageManager.getTempDirs(code);
			map.put("data", json);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("访问失败！");
		}
	}
	
	
}
