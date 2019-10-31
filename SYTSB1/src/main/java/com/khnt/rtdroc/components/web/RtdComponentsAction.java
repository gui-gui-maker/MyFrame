package com.khnt.rtdroc.components.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rtdroc.components.bean.RtdComponents;
import com.khnt.rtdroc.components.service.RtdComponentsManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName RtdComponentsAction
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-09-01 09:51:04
 */
@Controller
@RequestMapping("com/rtd/components")
public class RtdComponentsAction extends SpringSupportAction<RtdComponents, RtdComponentsManager> {

	@Autowired
	RtdComponentsManager rtdComponentsManager;

	/*
	 * 保存組件
	 */
	@RequestMapping("saveComponents")
	@ResponseBody
	public HashMap<String, Object> saveComponents(HttpServletRequest request, RtdComponents rtdComponents) throws Exception {
		try {
			this.rtdComponentsManager.saveComponents(rtdComponents);
			rtdComponents.setImgData(null);// 图片数据不做回传

			return JsonWrapper.successWrapper(rtdComponents);
		} catch (KhntException e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}

	}

	/*
	 * 获取組件列表
	 */
	@RequestMapping("getComponents")
	@ResponseBody
	public HashMap<String, Object> getComponents(HttpServletRequest request) throws Exception {
		try {
			List<RtdComponents> rtdComponents = this.rtdComponentsManager.getComponents();

			return JsonWrapper.successWrapper(rtdComponents);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}

}
