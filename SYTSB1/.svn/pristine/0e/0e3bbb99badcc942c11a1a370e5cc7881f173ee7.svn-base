package com.lsts.approve.web;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.CertificateBy;
import com.lsts.approve.service.CertificateByService;
import com.lsts.report.bean.Report;

@Controller
@RequestMapping("certificateByAction")
public class CertificateByAction extends SpringSupportAction<CertificateBy, CertificateByService>{
	@Autowired
	private CertificateByService certificateByService;
	
	@RequestMapping("del")
	@ResponseBody
	public HashMap<String, Object> delete(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.delete(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败，请重试！");
		}
		return map;
	}
	@RequestMapping("start")
	@ResponseBody
	public HashMap<String, Object> start(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.start(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "启用失败，请重试！");
		}
		return map;
	}
	@RequestMapping("stop")
	@ResponseBody
	public HashMap<String, Object> stop(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.stop(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "停用失败，请重试！");
		}
		return map;
	}
	@RequestMapping("notToOne")
	@ResponseBody
	public HashMap<String, Object> notToOne(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.notToOne(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "状态更改失败，请重试！");
		}
		return map;
	}
	@RequestMapping("toOne")
	@ResponseBody
	public HashMap<String, Object> toOne(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.toOne(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "状态更改失败，请重试！");
		}
		return map;
	}
	@RequestMapping("notToLeast")
	@ResponseBody
	public HashMap<String, Object> notToLeast(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.notToLeast(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "状态更改失败，请重试！");
		}
		return map;
	}
	@RequestMapping("toLeast")
	@ResponseBody
	public HashMap<String, Object> toLeast(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.toLeast(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "状态更改失败，请重试！");
		}
		return map;
	}
	
	@RequestMapping("useSubstitute")
	@ResponseBody
	public HashMap<String, Object> useSubstitute(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.useSubstitute(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "状态更改失败，请重试！");
		}
		return map;
	}
	@RequestMapping("unuseSubstitute")
	@ResponseBody
	public HashMap<String, Object> unuseSubstitute(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			certificateByService.unuseSubstitute(ids);
			map.put("success", true);
			map.put("data", "1");
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "状态更改失败，请重试！");
		}
		return map;
	}
	@RequestMapping("details")
	@ResponseBody
	public HashMap<String, Object> details(HttpServletRequest request, String device,String dept,String report) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			List<CertificateBy> list = certificateByService.details(device,dept,report);
			map.put("success", true);
			map.put("data", list);
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "获取失败，请重试！");
		}
		return map;
	}
	
}
