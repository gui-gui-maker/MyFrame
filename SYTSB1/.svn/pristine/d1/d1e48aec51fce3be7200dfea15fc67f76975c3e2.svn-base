package com.lsts.flow.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;
import com.lsts.flow.bean.BaseFlowConfig;
import com.lsts.flow.service.BaseFlowConfigService;

/**
 * 流程配置控制器
 * 
 * @ClassName BaseFlowConfigAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-11 下午02:35:00
 */
@Controller
@RequestMapping("flow/config")
public class BaseFlowConfigAction extends
		SpringSupportAction<BaseFlowConfig, BaseFlowConfigService> {

	@Autowired
	private BaseFlowConfigService baseFlowConfigService;

	/**
	 * 保存
	 * 
	 * @param request
	 * @param baseFlowConfig
	 * @throws Exception
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,
			BaseFlowConfig baseFlowConfig) throws Exception {
		try {
			// 1、验证是否已存在同种设备同种检验类别的流程配置
//			String flow_config_id = baseFlowConfigService.getFlowConfig(
//					baseFlowConfig.getDevice_type(), baseFlowConfig
//							.getCheck_type());
//			if (StringUtil.isNotEmpty(flow_config_id)) {
//				boolean exist = false;
//				if (StringUtil.isEmpty(baseFlowConfig.getId())) {
//					exist = true;
//				} else {
//					if (!flow_config_id.equals(baseFlowConfig.getId())) {
//						exist = true;
//					}
//				}
//				if (exist) {
//					return JsonWrapper
//							.failureWrapperMsg("已存在该设备该检验类别的流程配置，请勿重复添加！");
//				}
//			}
//			baseFlowConfig.setDevice_type(baseFlowConfig.getDevice_type().substring(beginIndex));
			
			baseFlowConfigService.save(baseFlowConfig); // 2、保存流程配置信息
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存流程配置信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseFlowConfig);
	}

	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		return super.detail(request, id);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		baseFlowConfigService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}

	/**
	 * 获取流程编号
	 * 
	 * @param request
	 * @param device_type --
	 *            设备类别
	 * @param check_type --
	 *            检验类别
	 * @return
	 */
	@RequestMapping(value = "getFlow")
	@ResponseBody
	public HashMap<String, Object> getFlow(HttpServletRequest request,
			String device_type, String check_type) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			String flow_code = "";
			if (StringUtil.isNotEmpty(device_type)
					&& StringUtil.isNotEmpty(check_type)) {
				String class_type=device_type+"000";
				
				flow_code = baseFlowConfigService.getFlowCode(class_type,
						check_type);
			}
			wrapper.put("success", true);
			wrapper.put("data", flow_code);
		} catch (Exception e) {
			log.error("获取流程出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "获取流程出错！");
			e.printStackTrace();
		}
		return wrapper;
	}
}
