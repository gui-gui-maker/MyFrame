package com.lsts.inspection.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.StringUtil;
import com.lsts.common.service.CodeTablesService;
import com.lsts.constant.Constant;
import com.lsts.inspection.bean.InspectionHall;
import com.lsts.inspection.bean.InspectionHallPara;
import com.lsts.inspection.service.InspectionHallParaService;
import com.lsts.inspection.service.InspectionHallService;

/**
 * 报检大厅管理 web controller
 * 
 * @author 肖慈边 2014-1-26
 */

@Controller
@RequestMapping("inspection/basic")
public class InspectionHallAction extends
		SpringSupportAction<InspectionHall, InspectionHallService> {


	
	@Autowired
	private InspectionHallService inspectionHallService;
	@Autowired
	private InspectionHallParaService inspectionHallParaService;
	@Autowired
	private CodeTablesService codeTablesService;

	//保存报检大厅信息
		@RequestMapping(value = "saveBasic")
		@ResponseBody
		public HashMap<String, Object> saveBasic( @RequestBody InspectionHall hall,HttpServletRequest request)
				throws Exception {
			
			
			
//			Map<String, Object> map = new HashMap<String, Object>();
//			
//			map.put("type", request.getParameter("type"));
			try {
				InspectionHall baseinfo = inspectionHallService.saveBasic(hall);
				
	    		return JsonWrapper.successWrapper(baseinfo);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("保存失败！");
			}
//				
			
			
			
		}
		
		//保存分配信息
		@RequestMapping(value = "savePlan")
		@ResponseBody
		public HashMap<String, Object> savePlan( HttpServletRequest request)
				throws Exception {
					
					
					
					Map<String, Object> map = new HashMap<String, Object>();
				
//					String op = request.getParameter("op_name").toString();
//					
//					String op_name = new String(request.getParameter("op_name").toLowerCase().getBytes("GB2312"), "UTF-8"); 
					
					map.put("device_id", request.getParameter("device_id"));
					map.put("op_ids", request.getParameter("op_ids"));
					map.put("inc_time", request.getParameter("inc_time"));
					map.put("check_ids", request.getParameter("check_ids"));
					map.put("op_name", request.getParameter("op_name"));
					map.put("check_name", request.getParameter("check_name"));
				try {
				       inspectionHallService.savePlan(map);
			    	return JsonWrapper.successWrapper(map);
				} catch (Exception e) {
					e.printStackTrace();
						return JsonWrapper.failureWrapperMsg("保存失败！");
				}
				
			}
		
	
		//保存科室流转信息
		@RequestMapping(value = "saveTransfer")
		@ResponseBody
		public HashMap<String, Object> saveTransfer( HttpServletRequest request)
				throws Exception {
					
					
					
					Map<String, Object> map = new HashMap<String, Object>();
				
					
					map.put("device_id", request.getParameter("device_id"));
					map.put("unit_code", request.getParameter("unit_code"));
					
				try {
				       inspectionHallService.saveTransfer(map);
			    	return JsonWrapper.successWrapper(map);
				} catch (Exception e) {
					e.printStackTrace();
						return JsonWrapper.failureWrapperMsg("保存失败！");
				}
					
					
					
				
			}
		

		//删除报检大厅信息
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(String ids) throws Exception{
				
				
				 inspectionHallService.del(ids);
		  
				return JsonWrapper.successWrapper(ids);
			
	    }
	    		
				
				
	//获取科室报检信息
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail( HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		String hall_id = request.getParameter("hall_id");
		
		InspectionHall	inspectionHall	= inspectionHallService.getDe(id,hall_id);
				 
		 return JsonWrapper.successWrapper(inspectionHall);
	
		}				

	
	/**
	 * 获取大厅报检回执单打印内容
	 * 
	 * @param request
	 * @param id --
	 *            大厅报检ID
	 * @return
	 */
	@RequestMapping(value = "getPrintContent")
	@ResponseBody
	public HashMap<String, Object> getPrintContent(HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				InspectionHall inspectionHall = inspectionHallService.get(id);		
				/*List<InspectionHallPara>  paraList = inspectionHall.getParaList();
				if (paraList == null) {
					paraList = inspectionHallParaService.queryInspectionHallParaByHallId(id);
				}*/
				String inspection_type = codeTablesService.getValueByCode(Constant.CT_BASE_CHECK_TYPE,
						inspectionHall.getInspection_type());	
				inspectionHall.setInspection_type(inspection_type);
				// 获取大厅报检回执单打印内容
				String printContent = Constant.getInspectionHallPrintContent(inspectionHall);
				wrapper.put("success", true);
				wrapper.put("printContent", printContent);
			}
		} catch (Exception e) {
			log.error("获取大厅报检回执单打印内容出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "获取大厅报检回执单打印内容出错！");
			e.printStackTrace();
		}
		return wrapper;
	}

	//提交数据到科室
	@RequestMapping(value = "subDep")
	@ResponseBody
	public HashMap<String, Object> subDep( HttpServletRequest request)
			throws Exception {
		
			String ids = request.getParameter("ids");
		
			HashMap map = new HashMap();
			
			inspectionHallService.subDep(ids);
			
			map.put("success", true);
					 
		return JsonWrapper.successWrapper(map);
		
		}	

    
}