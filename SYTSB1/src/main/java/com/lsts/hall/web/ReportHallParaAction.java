package com.lsts.hall.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.UserManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.hall.bean.ReportHall;
import com.lsts.hall.bean.ReportHallInfo;
import com.lsts.hall.bean.ReportHallPara;
import com.lsts.hall.service.ReportHallInfoService;
import com.lsts.hall.service.ReportHallParaService;
import com.lsts.hall.service.ReportHallService;
import com.lsts.inspection.bean.InspectionHall;
import com.lsts.inspection.bean.InspectionHallPara;

/**
 * 
 * @author Mr.Dawn
 * @date 2014-12-22
 * summary 报检大厅流转管理
 */
@Controller
@RequestMapping("reportHallParaAction")
public class ReportHallParaAction extends
		SpringSupportAction<ReportHallPara, ReportHallParaService> {

	@Autowired
	private ReportHallParaService reportHallParaService;
	@Autowired
	private UserManagerImpl userManager;
	@Autowired
	private ReportHallInfoService reportHallInfoService;
	@Autowired
	ReportHallService reportHallService;
	/**
	 * 接受任务
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "subInspection")
	@ResponseBody
	public HashMap<String, Object> subInspection(HttpServletRequest request)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String ids = request.getParameter("ids");

		HashMap<String,Object> map = new HashMap<String,Object>();
		try{
			String id[] =ids.split(",");
			for(int i=0;i<id.length;i++){
				ReportHallPara reportHallPara = reportHallParaService.get(id[i]);
				String received_user_name = reportHallPara.getReceived_user_name();
				received_user_name+=","+user.getName();
				reportHallPara.setReceived_user_name(received_user_name);
				int check_user_length = StringUtil.isNotEmpty(reportHallPara.getCheck_name())?reportHallPara.getCheck_name().split(",").length:0;
				int receive_user_length = StringUtil.isNotEmpty(reportHallPara.getReceived_user_name())?reportHallPara.getReceived_user_name().split(",").length:0;
				if(check_user_length==receive_user_length){
					reportHallPara.setIs_rec("3");
				}
				reportHallParaService.save(reportHallPara);
			}
		//reportHallParaService.subInspection(ids);
			map.put("success", true);
			return JsonWrapper.successWrapper(map);
		} catch(KhntException e ){
			map.put("msg", "接受任务失败");
			return JsonWrapper.failureWrapper(map);
		}
		
	}
	
	/**
	 * 移动端接收任务
	 * @param request
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @date 2016-03-01 下午02:49:00
	 */
	@RequestMapping(value = "mo_receiveInsp")
	@ResponseBody
	public HashMap<String, Object> mo_receiveInsp(HttpServletRequest request)
			throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		try{
			String ids = request.getParameter("ids");	// 大厅报检参数ID
			String user_id = request.getParameter("user_id");	// 用户ID
			String deviceIds = request.getParameter("deviceIds");	// 接收设备ID
			
			if(StringUtil.isEmpty(user_id)){
				map.put("success", false);
				map.put("msg", "登录已过期，请重新登录！");
				return map;
			}else{
				ReportHallPara reportHallPara = reportHallParaService
						.get(ids);
				String received_user_name = reportHallPara
						.getReceived_user_name();
				User user = userManager.get(user_id);
				String device_id[] =deviceIds.split(",");
				for(int i=0;i<device_id.length;i++){		
					ReportHallInfo reportHallInfo = new ReportHallInfo();
					reportHallInfo.setFk_hall_para_id(ids);
					reportHallInfo.setFk_device_id(device_id[i]);
					reportHallInfo.setReceive_user_id(user_id);
					reportHallInfo.setReceive_user_name(user.getName());
					reportHallInfo.setReceive_time(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
					reportHallInfo.setOp_time(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", DateUtil.getCurrentDateTime()));
					reportHallInfo.setData_status("0");	// 数据状态（0：正常 99：已作废）
					reportHallInfoService.save(reportHallInfo);
					
					if(StringUtil.isNotEmpty(received_user_name)){
						received_user_name += "," + user.getName();
					}else{
						received_user_name = user.getName();
					}
					reportHallPara.setReceived_user_name(received_user_name);
					reportHallPara.setIs_rec("4");	// 数据状态（4：领取）
					reportHallParaService.save(reportHallPara);
				}
			}
			//reportHallParaService.subInspection(ids);
			map.put("success", true);
			return JsonWrapper.successWrapper(map);
		} catch(KhntException e ){
			map.put("msg", "接收任务失败");
			return JsonWrapper.failureWrapper(map);
		}
	}
	
	
	/**
	 * 获取已经报检的数量
	 */
	@RequestMapping(value = "getInspectCount")
	@ResponseBody
	public HashMap<String, Object> getInspectCount(HttpServletRequest request,
			String hall_para_id ) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			int count = reportHallParaService.getInspectCount(hall_para_id);
			wrapper.put("success", true);
			wrapper.put("data", count);
		} catch(Exception e){
			wrapper.put("success", false);
			wrapper.put("message", "获取已报检数量错误！");
		}
		return wrapper;
	}
	
	/** 检验任务生成提交后退回任务分配 **/
	@RequestMapping(value = "returnAssign")
	@ResponseBody
	public Map<String, Object> returnAssign(HttpServletRequest request)
			throws Exception {
		String hall_para_id = request.getParameter("hall_para_id");
		try {
			return reportHallParaService.returnAssign(hall_para_id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("退回任务分配失败！");
		}

	}
	
	//--------------------------新版移动端代码----------------------------------------
	/**
     * 保存及修改手机APP的设备流转信息
     *
     * @param reportHallPara
     * @return
     */
    @ResponseBody
    @RequestMapping("saveBasic")
    public HashMap<String, Object> saveBasic(@RequestBody ReportHallPara reportHallPara,HttpServletRequest request) throws Exception {

    	//String hallId = request.getParameter("hall_id");
    	
        //判断设备流转信息是新增还是修改
        if (StringUtil.isEmpty(reportHallPara.getId())) {

            ReportHall reportHall = reportHallService.get(reportHallPara.getReportHall().getId());
            reportHall.setData_status("1");
            reportHallService.save(reportHall);
            //此处是新增设备流转信息
            reportHallPara.setReportHall(reportHall);
            // 是否接收
            reportHallPara.setIs_rec("1");
            // 是否分配 默认否
            reportHallPara.setIs_plan("1");
            reportHallPara.setTransfer_op(reportHallPara.getTransfer_op());
            reportHallPara.setTransfer_date(new Date());
            reportHallPara.setUnit_nam(reportHallPara.getUnit_nam());
            reportHallPara.setUnit_code(reportHallPara.getUnit_code());

            reportHallParaService.save(reportHallPara);

        } else {
        	ReportHallPara reportHallPara1 = reportHallParaService.get(reportHallPara.getId());

            reportHallPara1.setDevice_type(reportHallPara.getDevice_type());
            reportHallPara1.setDevice_no(reportHallPara.getDevice_no());
            reportHallPara1.setUnit_nam(reportHallPara.getUnit_nam());
            reportHallPara1.setUnit_code(reportHallPara.getUnit_code());
            reportHallPara1.setCharge_situation(reportHallPara.getCharge_situation());
            reportHallPara1.setRemark(reportHallPara.getRemark());

            reportHallParaService.save(reportHallPara1);

        }

        return JsonWrapper.successWrapper(reportHallPara);
    }

	
    /**
     * APP获取设备流转详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getReportHallParaObj")
    public HashMap<String, Object> getReportHallParaObj(String id) throws Exception {
        if (StringUtil.isEmpty(id)) {
            return JsonWrapper.failureWrapperMsg("ID不能为空!");
        }
        ReportHallPara reportHallPara = reportHallParaService.get(id);
        return JsonWrapper.successWrapper(reportHallPara);
    }
	//--------------------------新版移动端代码----------------------------------------
}
