package com.fwxm.supplies.web;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fwxm.material.bean.GoodsAndOrder;
import com.fwxm.supplies.bean.Warehousing;
import com.fwxm.supplies.bean.Zdbq;
import com.fwxm.supplies.service.WarehousingManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.service.EmployeesService;
import com.lsts.finance.bean.Fybxd;

@SuppressWarnings("serial")
@Controller
@RequestMapping("com/tjy2/warehousings")
public class WarehousingAction extends SpringSupportAction<Warehousing, WarehousingManager>{
	@Autowired
	private WarehousingManager warehousingManager;
    @Autowired
	private EmployeesService employeesService;
	
    /**
     * 入库费用报销
     * @param request
     * @return
     * @throws ParseException
     */
	@RequestMapping(value = "saveFybx")
  	@ResponseBody
    public HashMap<String, Object> saveFybx(HttpServletRequest request) throws ParseException{
    	HashMap<String, Object> map=new HashMap<String, Object>();
    	try {
    		String fybx=request.getParameter("fybx").toString();
        	JSONObject json= JSON.parseObject(fybx);
        	Fybxd entity=JSON.parseObject(fybx, Fybxd.class);
        	String rkid=json.getString("rkids");
        	String rkdh=json.getString("rkdh");
        	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
        	warehousingManager.fybxSave(entity, curUser, rkid.split(","),rkdh);
    	    map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
		}
    	
	    
    	return map;
    }
    
    
    
	@RequestMapping(value = "getUserTel")
  	@ResponseBody
	public HashMap<String, Object> getUserTel(HttpServletRequest request,String userId){
		HashMap<String, Object> map=new HashMap<String, Object>();
		try {
			Employee employee =employeesService.get(userId);
			String destNumber = employee.getMobileTel();
			map.put("success", true);
			map.put("data", destNumber);
		} catch (Exception e) {
			map.put("success", false);
			System.err.println(e);
			e.printStackTrace();
		}
  		return map;
	}
	/**
	 * 批量提交
	 * @param request
	 * @return
	 */
  	@RequestMapping(value = "beanPlTj")
  	@ResponseBody
	public HashMap<String, Object> beanPlTj(HttpServletRequest request){
  		
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  			String ids=request.getParameter("ids");
  			if(StringUtil.isNotEmpty(ids)){
  				warehousingManager.beanPlTj(ids);
  			}
  			
  			map.put("success", true);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "提交失败！");
		}
  		return map;
  	}
	
  	@RequestMapping(value = "saveBean")
  	@ResponseBody
	public HashMap<String, Object> saveBean(HttpServletRequest request){
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  	  		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
  			String warehousing=request.getParameter("warehousing").toString();
  			map=warehousingManager.saveBean(curUser,warehousing);
  		}catch(Exception e){
			log.info(e.getMessage());
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "保存失败！");
  		}
  		return map;
  	}

  	@RequestMapping(value = "detailGoods")
  	@ResponseBody
  	public HashMap<String, Object> detailGoods(HttpServletRequest request){
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
			String id=request.getParameter("id");
			
			List<GoodsAndOrder> list=warehousingManager.getGoodsByRkId(id);
			map.put("success", true);
			map.put("data", list);
			System.out.println(id);
		} catch (Exception e) {
			System.out.println(e);
			map.put("success", false);
			map.put("msg", "查询出错！");
		}
  		
  		return map;
  	}
  	

  	@RequestMapping(value = "deleteWarehoesing")
  	@ResponseBody
  	public HashMap<String,Object> deleteWarehoesing(HttpServletRequest request){

  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  			String ids=request.getParameter("ids");
  	  		if(StringUtil.isNotEmpty(ids)){
  	  			warehousingManager.deleteWarehoesing(ids);
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
  	 * 删除修改页面预删除数据
  	 * @param request
  	 * @return
  	 */
  	@RequestMapping(value = "deleteWarehoesingByGoodsId")
  	@ResponseBody
  	public HashMap<String,Object> deleteWarehoesingByGoodsId(HttpServletRequest request,String[] ids){
  		
  		HashMap<String, Object> map=new HashMap<String, Object>();
  		try {
  			if(ids!=null && ids.length>=0){
  				warehousingManager.deleteWarehoesingByGoodsId(ids);
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
  	 * 跟具入库单导出验收表
  	 * @param request
  	 * @param response
  	 */
  	@RequestMapping(value = "outYs")
  	@ResponseBody
  	public void outYs(HttpServletRequest request, HttpServletResponse response) {
  		try {
  		  	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
            String id = request.getParameter("id");
            String tel= request.getParameter("tel");
            HSSFWorkbook workbook=warehousingManager.outYsb(id, curUser,tel);
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(("验收表"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
            OutputStream outp = response.getOutputStream();
            workbook.write(response.getOutputStream());
            outp.flush();
            outp.close();
		} catch (Exception e) {
			System.out.println(e);
		}
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
	  	String id=request.getParameter("id");
	  	String orgId=request.getParameter("orgId");
	  	String departmentId="";
	  	try {
	  		if(null!=orgId){
	  			departmentId=orgId;
	  			}else{
	  				departmentId=curUser.getDepartment().getId();
	  			}
		  	HSSFWorkbook workbook=warehousingManager.outRk(id,departmentId);

		  	
	        response.setContentType("application/vnd.ms-excel");
	        response.addHeader("Content-Disposition",
	                "attachment;filename=" + new String(("入库单"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
	        OutputStream outp = response.getOutputStream();
	        workbook.write(response.getOutputStream());
	        outp.flush();
	        outp.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	  	
  	}
  	@RequestMapping(value = "outTz")
  	@ResponseBody
  	public void outTz(HttpServletRequest request, HttpServletResponse response){
  		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	  	String id=request.getParameter("id");
	  	try {
	  		String startTime=request.getParameter("startTime");
	  		String endTime=request.getParameter("endTime");
	  		HSSFWorkbook workbook=warehousingManager.outTz(id,curUser,startTime,endTime);//
	        response.setContentType("application/vnd.ms-excel");
	        response.addHeader("Content-Disposition",
	                "attachment;filename=" + new String(("存货管理台账"+ ".xls").getBytes("gb2312"), "ISO8859-1"));
	        OutputStream outp = response.getOutputStream();
	        workbook.write(response.getOutputStream());
	        outp.flush();
	        outp.close();
		} catch (Exception e) {
			System.out.println(e);
		}
  	}
  	/**
  	 * 获取自动补全数据
  	 * @param request
  	 * @return
  	 */
  	@RequestMapping(value = "getzdbq")
  	@ResponseBody
  	public HashMap<String,String[]> getzdbq(HttpServletRequest request){
	  	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	  	HashMap<String,String[]> map=new HashMap<String, String[]>();
  		String[] text1=null;
  		String[] text2=null;
  		try {
  	  		List<Zdbq> list=warehousingManager.getZdbq(curUser.getDepartment().getId(), "1");//名称
  	  		List<Zdbq> list2=warehousingManager.getZdbq(curUser.getDepartment().getId(), "2");//规格及型号
  	  		text1=new String[list.size()];
  	  		text2=new String[list2.size()];
			for (int i = 0; i < list.size(); i++) {
				Zdbq z=list.get(i);
				text1[i] =z.getText();
			}
			map.put("wpmc", text1);
			for (int i = 0; i < list2.size(); i++) {
				Zdbq z=list2.get(i);
				text2[i] =z.getText();
			}
			map.put("ggxh", text2);
  	  		
		} catch (Exception e) {
			System.err.println(e);
		}
  		return map;
  		
  	}

  	@RequestMapping(value = "sfOut")
  	@ResponseBody
  	public HashMap<String, Boolean> sfOut(HttpServletRequest request,String goodsId){
  		HashMap<String, Boolean> map=new HashMap<String, Boolean>();
  		try {
  			Boolean ck=warehousingManager.sfOut(goodsId);
  			map.put("success", true);
  			map.put("ck", ck);
		} catch (Exception e) {
			map.put("success", false);
			System.out.println(e);
		}
  		return map;
  	}
}
