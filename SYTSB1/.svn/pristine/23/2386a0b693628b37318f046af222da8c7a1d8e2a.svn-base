package com.fwxm.library.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.library.bean.Receive;
import com.fwxm.library.service.ReceiveManager;
import com.khnt.core.crud.web.SpringSupportAction;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Receive
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-10-23 10:57:26
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("lib/receive")
public class ReceiveAction extends
		SpringSupportAction<Receive, ReceiveManager> {

	@Autowired
	private ReceiveManager receiveManager;

	@Override
	public HashMap<String, Object> save(HttpServletRequest request, @RequestBody Receive entity) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			receiveManager.save(entity);
			map.put("success", true);
			map.put("data",entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@Override
	public HashMap<String, Object> detail(HttpServletRequest request, String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Receive entity = receiveManager.detail(id);
			map.put("success", true);
			map.put("data",entity);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 提交
	 * @param request
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="sub")
	@ResponseBody
	public HashMap<String, Object> sub(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			receiveManager.sub(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 审核
	 * @param request
	 * @param ids
	 * @param approveResult
	 * @param approveSuggestion
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="approve")
	@ResponseBody
	public HashMap<String, Object> approve(HttpServletRequest request, String ids,String approveResult,String approveSuggestion) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			receiveManager.approve(ids,approveResult,approveSuggestion);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			receiveManager.delete(ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	
	
	

}
