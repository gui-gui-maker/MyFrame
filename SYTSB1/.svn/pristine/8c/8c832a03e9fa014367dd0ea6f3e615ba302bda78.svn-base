package com.lsts.mobileapp.insing.web;


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
import com.lsts.inspection.bean.Inspection;
import com.lsts.inspection.service.ReportItemRecordService;
import com.lsts.mobileapp.insing.service.DeptInspectService;

@Controller
@RequestMapping("/deptInspectAction")
public class DeptInspectAction extends SpringSupportAction<Inspection, DeptInspectService>{
	@Autowired
	DeptInspectService deptInspectService;
	@Autowired
	ReportItemRecordService reportItemRecordService;
	/**
	 * 根据报检信息查询所有可选设备（分页）
	 * @param request
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="queryPagingDevices")
	@ResponseBody
	public HashMap<String, Object> queryPagingDevices(HttpServletRequest request, @RequestBody Map<String, Object> params) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		String deviceType = params.get("device_type").toString();
		
		if("7310".equals(deviceType)){
			sql.append("	with device_code as	");
			sql.append("	 (select id from base_device_classify  CONNECT BY prior id = pid START WITH id = '7310'	) ");
			sql.append("	select t.fk_company_info_use_id,	");
			sql.append("	       t.id,	");
			sql.append("	       t.device_registration_code,	");
			sql.append("	       t1.seal_number,	");
			sql.append("	       t.device_sort_code,	");
			sql.append("	       t.device_sort_code sort_code,	");
			sql.append("	       t.make_date,	");
			sql.append("	       t1.make_units,	");
			sql.append("	       t1.accessory_type,	");
			sql.append("	       '0' done,	");
			sql.append("	       t1.accessory_name as device_name　	");
			sql.append("	   from base_device_document t,	");
			sql.append("	       base_device_accessory  t1 	");
			sql.append("	    where t.id = t1.fk_tsjc_device_document_id 	");
			sql.append("	          and t.device_status <> '99'      	");
			sql.append("	          and exists (select 1	");
			sql.append("	                         from device_code codes	");
			sql.append("	                             where t.device_sort_code =	");
			sql.append("	                          codes.id)	");
			
		}else{
			sql.append("	with device_code as	");
			sql.append("	 (select id from base_device_classify  CONNECT BY prior id = pid START WITH id = '"+deviceType+"'	), ");
			sql.append("	already_done as	");
			sql.append("	 (select distinct info.fk_tsjc_device_document_id device_id	");
			sql.append("	    from TZSB_INSPECTION ins, TZSB_INSPECTION_INFO info	");
			sql.append("	   where ins.id = info.fk_inspection_id	and info.data_status='1' and info.sign_time is null )");
			sql.append("	select t.*,	");
			sql.append("	       t.device_sort_code sort_code,	");
			sql.append("	       decode(t1.device_id, '', '0', '1') done　from base_device_document t,	");
			sql.append("	       already_done t1 where t.device_status <> '99' and exists (select 1	");
			sql.append("	 from device_code codes	");
			sql.append("	where t.device_sort_code =	");
			sql.append("	      codes.id)  and t.id = t1.device_id(+)	");
		}
		params.put("sql", sql.toString()); 
		try {
			List<HashMap<String, Object>> data = deptInspectService.queryPagingDevices(params);
			map.put("success", true);
			map.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	/**
	 * 根据报检信息inspection查询所有可选设备（不分页）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryAllDevices")
	@ResponseBody
	public HashMap<String, Object> queryAllDevices(String device_type,String fk_company_info_use_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<HashMap<String, Object>> data = deptInspectService.queryAllDevices(device_type,fk_company_info_use_id);
			map.put("success", true);
			map.put("data", data);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 根据报检信息inspection查询选中的设备
	 * @param id (inspection.id)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "querySelectedDevices")
	@ResponseBody
	public HashMap<String, Object> querySelectedDevices(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Object> data = deptInspectService.querySelectedDevices(id);
			map.put("success", true);
			map.put("data", data);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	/**
	 * 科室报检-->保存报检信息
	 * @param inspection
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveInspection")
	@ResponseBody
	public HashMap<String, Object> saveInspection(HttpServletRequest request,@RequestBody Inspection inspection)throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			deptInspectService.saveInspection(request,inspection);
			map.put("success", true);
			map.put("data", inspection);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;

	}

    /**
     * 大厅报检-->保存报检信息
     * @param inspection
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveHallInspection")
    @ResponseBody
    public HashMap<String, Object> saveHallInspection(HttpServletRequest request,@RequestBody Inspection inspection)throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            deptInspectService.saveHallInspection(request,inspection);
            map.put("success", true);
            map.put("data", inspection);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }
	@RequestMapping(value = "deleteInspection")
	@ResponseBody
	public HashMap<String, Object> deleteInspection(HttpServletRequest request,String ids)throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			deptInspectService.deleteInspection(request,ids);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;

	}
	
	
	/**
	 * 科室报检-->提交报检信息
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submitInspection")
	@ResponseBody
	public HashMap<String, Object> submitInspection(HttpServletRequest request,String ids)throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			deptInspectService.submitInspection(request,ids);
			map.put("success", true);
			map.put("data", ids);
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;

	}
	/**
	 * 科室报检-->任务接收
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "acceptMission")
	@ResponseBody
	public HashMap<String, Object> acceptMission(HttpServletRequest request,String id)throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = reportItemRecordService.reviceInspection(map,request,id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 查询报告类型
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getReportTypes")
	@ResponseBody
	public HashMap<String, Object> getReportTypes(HttpServletRequest request)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String device_type = request.getParameter("device_type");
			String check_type = request.getParameter("check_type");
			String fk_unit_id = request.getParameter("fk_unit_id");
			List<HashMap<String, Object>> data=deptInspectService.getReportTypes(fk_unit_id,device_type,check_type);
			map.put("success", true);
			map.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
    }
	
	@RequestMapping(value = "hallInspectInit")
	@ResponseBody
	public HashMap<String, Object> hallInspectInit(String hallParamId) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<Inspection> list = deptInspectService.getHallInspect(hallParamId);
			map.put("success", true);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * PC端报移动检验
	 * @param inspection
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveInspectionFromPc")
	@ResponseBody
	public HashMap<String, Object> saveInspectionFromPc(HttpServletRequest request,@RequestBody Inspection inspection)throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			deptInspectService.saveInspectionFromPc(request,inspection);
			deptInspectService.submitInspection(request,inspection.getId());
			return JsonWrapper.successWrapper("111");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;

	}
	
}
	

