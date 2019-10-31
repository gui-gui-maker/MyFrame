package com.khnt.mobile.version.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.mobile.version.bean.AppUpdate;
import com.khnt.mobile.version.service.AppUpdateManager;


@Controller
@RequestMapping("appclient/update")
public class AppUpdateAction extends
		SpringSupportAction<AppUpdate, AppUpdateManager> {

	@Autowired
	private AppUpdateManager sdyAppUpdateManager;

	// 保存
	@RequestMapping({ "save_update_data" })
	@ResponseBody
	public HashMap<String, Object> applyLabel(HttpServletRequest request,
			AppUpdate entity, String carpicId){
		HashMap<String, Object> map = new HashMap<>();
		try{
			this.sdyAppUpdateManager.saveSdyAppUpdateData(entity, carpicId);
			map.put("success",true);
			map.put("data", entity);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success",false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 
	 * @param request
	 * @param appid bundleId
	 * @param version 资源版本号
	 * @param appVersion 应用版本号
	 * @param osName 手机类型 android 或者Ios
	 * @return
	 * @throws Exception 
	 */
	// 获取更新资源包地址
	@RequestMapping({ "get_update_address" })
	@ResponseBody
	public Map<String, Object> getUpdateAddress(HttpServletRequest request,
			String appid, String version, String appVersion,String osName) throws Exception{
		Map<String, Object> checkUpdate = this.sdyAppUpdateManager.checkUpdate(appid, version, appVersion,osName);
		return JsonWrapper.successWrapper(checkUpdate);
	}

}