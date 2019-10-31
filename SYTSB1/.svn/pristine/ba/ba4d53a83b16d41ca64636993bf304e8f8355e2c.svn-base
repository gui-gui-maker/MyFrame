package com.fwxm.material.web;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fwxm.material.bean.Supplier;
import com.fwxm.material.service.SupplierManager;
import com.fwxm.supplies.service.WarehousingManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/supplier")
public class SupplierAction extends SpringSupportAction<Supplier, SupplierManager>{
	@Autowired
	private SupplierManager supplierManager;
	@Autowired
	private WarehousingManager warehousingManager;
	
	
	@RequestMapping(value = "getbean")
  	@ResponseBody
	public HashMap<String, Object> getbean(HttpServletRequest request,String id){
		Supplier bean=supplierManager.get(id);
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("data", bean);
		map.put("success", true);
		return map;
	}

  	@RequestMapping(value = "save")
  	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,Supplier entity){
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  	  		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
  	  		if(StringUtil.isEmpty(entity.getId())){
  	  			entity.setCreateDate(new Date());
  	  			entity.setCreateUserId(curUser.getId());
  	  			entity.setCreateUserName(curUser.getUserName());
  	  			entity.setCreateOrgId(curUser.getDepartment().getId());
  	  			entity.setCreateOrgName(curUser.getDepartment().getOrgName());
  	  			entity.setCreateUnitId(curUser.getUnit().getId());
  	  			entity.setCreateUserName(curUser.getUnit().getOrgName());
  	  		}
  	  		entity.setStatus("1");
  	  		
  	  		supplierManager.save(entity);
			
  	  		map.put("success", true);
  	  		map.put("data", entity);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败！");
		}
  		
  		return map;
	}

  	@RequestMapping(value = "deleteByIds")
  	@ResponseBody
  	public HashMap<String, Object> deleteByIds(HttpServletRequest request){
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  			String ids=request.getParameter("ids");
  	  		if(StringUtil.isNotEmpty(ids)){
  	  			String[] id=ids.split(",");
  	  			for (String delById : id) {
  	  	  			Supplier entity=supplierManager.get(delById);
  	  	  			entity.setStatus("99");//删除
  	  	  			supplierManager.save(entity);
				}
  	  			map.put("success", true);
  	  		}
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "删除失败！");
			log.info(e.getMessage());
			e.printStackTrace();
		}
  		return map;
  	}
	/**
  	 * 导出入库单
  	 * @param request
  	 * @param response
  	 */
  	@RequestMapping(value = "outRk")
  	@ResponseBody
  	public void outRk(HttpServletRequest request, HttpServletResponse response) {
	  	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	  	String gysId=request.getParameter("gysId");
	  	String startTime=request.getParameter("startTime");
	  	String endTime=request.getParameter("endTime");
	  	try {
//	  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
	  		
	  		HSSFWorkbook workbook =supplierManager.outRk(gysId,startTime,endTime,curUser.getDepartment().getId());
//		  	HSSFWorkbook workbook=warehousingManager.outRk(id);
//
//		  	
	        response.setContentType("application/vnd.ms-excel");
	        response.addHeader("Content-Disposition",
	                "attachment;filename=" + new String((startTime+"至"+endTime+"入库单"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
	        OutputStream outp = response.getOutputStream();
	        workbook.write(response.getOutputStream());
	        outp.flush();
	        outp.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	  	
  	}
  	
    /**
     * 导出办公耗材验收表
     * @param request
     * @param response
     */
  	@RequestMapping(value = "outBghcYs")
  	@ResponseBody
  	public void outBghcYs(HttpServletRequest request, HttpServletResponse response) {
  		try {
  	  		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
            String gysId = request.getParameter("gysId");//供应商id
            String startTime=request.getParameter("startTime");//开始时间
            String endTime=request.getParameter("endTime");//结束时间
            String org=curUser.getDepartment().getId();
        	HSSFWorkbook workbook =supplierManager.outBgYsb(gysId, startTime, endTime, org);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String((startTime+"至"+endTime+"办公耗材验收表"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
            OutputStream outp = response.getOutputStream();
            workbook.write(response.getOutputStream());
            outp.flush();
            outp.close();
		} catch (Exception e) {
			System.out.println(e);
		}
  	}
  	
  	
  	/**
  	 * 其它类型验收表
  	 * @param request
  	 * @param response
  	 */
  	@RequestMapping(value = "outYs")
  	@ResponseBody
  	public void outYs(HttpServletRequest request, HttpServletResponse response) {
  		try {
  			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
            String gysId = request.getParameter("gysId");//供应商id
            String startTime=request.getParameter("startTime");//开始时间
            String endTime=request.getParameter("endTime");//结束时间
            String org=curUser.getDepartment().getId();
        	HSSFWorkbook workbook =supplierManager.outYsb(gysId, startTime, endTime, org);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String((startTime+"至"+endTime+"验收表"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
            OutputStream outp = response.getOutputStream();
            workbook.write(response.getOutputStream());
            outp.flush();
            outp.close();
            
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
  		
  	}
}
