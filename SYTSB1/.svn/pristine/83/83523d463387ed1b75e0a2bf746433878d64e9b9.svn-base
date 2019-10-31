package com.lsts.office.web;

import com.alibaba.fastjson.JSON;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.security.CurrentSessionUser;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.humanresources.bean.EmployeeBase;
import com.lsts.humanresources.bean.RemindMessage;
import com.lsts.humanresources.bean.RlMessage;
import com.lsts.humanresources.service.EmployeeBaseManager;
import com.lsts.office.bean.OfficeMessage;
import com.lsts.office.service.OfficeMessageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("officeMessageAction")
public class OfficeMessageAction extends SpringSupportAction<OfficeMessage, OfficeMessageManager> {

    @Autowired
    private OfficeMessageManager  officeMessageManager;
    @Autowired
    private EmployeeBaseManager  employeeBaseManager;
    @Autowired
	private MessageService messageService;
    
	/**
	 * 保存消息信息
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "saveMessage")
	@ResponseBody
	public HashMap<String, Object> saveMessage(HttpServletRequest request,@RequestBody OfficeMessage officeMessage){
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		String saveType = request.getParameter("saveType");//获取保存方式（立即发送、保存）
		String pageStatus = request.getParameter("pageStatus");
		if(pageStatus.equals("add")){
			officeMessage.setCreateDate(new Date());
			officeMessage.setCreateId(curUser.getId());
			officeMessage.setCreateBy(curUser.getName());
		}else if(pageStatus.equals("modify")){
			officeMessage.setLastModifyDate(new Date());
			officeMessage.setLastModifyId(curUser.getId());
			officeMessage.setLastModifyBy(curUser.getName());
		}
		
		List<EmployeeBase> employeeBases = officeMessage.getEmployee();
		if(employeeBases!=null&&employeeBases.size()>0){//当不是临时号码时，获取选择人员的信息
			String ids=null;
			String names=null;
			String tels=null;
			for(int i=0;i<employeeBases.size();i++){
				EmployeeBase employeeBase = employeeBaseManager.get(employeeBases.get(i).getId());
				ids = ids+employeeBase.getId()+',';
				names = names+employeeBase.getEmpName()+',';
				tels=tels+employeeBase.getEmpPhone()+',';
			}
			officeMessage.setSendId(ids.substring(4, ids.length()-1));
			officeMessage.setSendName(names.substring(4, names.length()-1));
			officeMessage.setSendNumber(tels.substring(4, tels.length()-1));
		}
		if(saveType.equals("save")){
			officeMessage.setSendStatus("0");
		}else if(saveType.equals("saveAndSend")){
			if(officeMessage.getSendType().equals("0")){//微信发送
				String[] ids = officeMessage.getSendId().split(",");
				String[] tels = officeMessage.getSendNumber().split(",");
				for(int i=0;i<tels.length;i++){
					messageService.sendWxMsg(request,
							ids[i], Constant.OFFICE_CORPID, Constant.OFFICE_PWD, officeMessage.getSendDimension(), tels[i]);
				}
			}else if(officeMessage.getSendType().equals("1")){//短信发送
				String[] ids = officeMessage.getSendId().split(",");
				String[] tels = officeMessage.getSendNumber().split(",");
				for(int i=0;i<tels.length;i++){
					messageService.sendMoMsg(request, ids[i], officeMessage.getSendDimension(), tels[i]);	
				}
			}else if(officeMessage.getSendType().equals("2")){//微信、短信发送
				String[] ids = officeMessage.getSendId().split(",");
				String[] tels = officeMessage.getSendNumber().split(",");
				for(int i=0;i<tels.length;i++){
					messageService.sendWxMsg(request,
							ids[i], Constant.OFFICE_CORPID, Constant.OFFICE_PWD, officeMessage.getSendDimension(), tels[i]);
					messageService.sendMoMsg(request, ids[i], officeMessage.getSendDimension(), tels[i]);	
				}
			}else{
				System.out.println("未知的发送方式！");
			}
			officeMessage.setSendStatus("1");
			officeMessage.setSendTime(new Date());
			officeMessage.setSendById(curUser.getId());
			officeMessage.setSendBy(curUser.getName());
		}
		try {
			officeMessageManager.save(officeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败，请重试！");
		}
		return JsonWrapper.successWrapper(1);
	}

    /**
	 * 手动发送消息
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value = "sendByHand")
	@ResponseBody
	public HashMap<String, Object> sendByHand(HttpServletRequest request){
		CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
		try {	
			String mainIds = request.getParameter("ids");
			if(!mainIds.isEmpty()){
				String[] mainIdsArr = mainIds.split(",");
				for(int i=0;i<mainIdsArr.length;i++){
					OfficeMessage officeMessage = officeMessageManager.get(mainIdsArr[i]);
					if(officeMessage.getSendType().equals("0")){//微信发送
						String[] ids = officeMessage.getSendId().split(",");
						String[] tels = officeMessage.getSendNumber().split(",");
						for(int j=0;j<tels.length;j++){
							messageService.sendWxMsg(request,
									ids[j], Constant.OFFICE_CORPID, Constant.OFFICE_PWD, officeMessage.getSendDimension(), tels[j]);
						}
					}else if(officeMessage.getSendType().equals("1")){//短信发送
						if(officeMessage.getIsTemporaryTel().equals("1")){
							String[] tels = officeMessage.getSendNumber().split(",");
							for(int j=0;j<tels.length;j++){
								messageService.sendMoMsg(request, "临时号码", officeMessage.getSendDimension(), tels[j]);	
							}
						}else if(officeMessage.getIsTemporaryTel().equals("0")){
							String[] ids = officeMessage.getSendId().split(",");
							String[] tels = officeMessage.getSendNumber().split(",");
							for(int j=0;j<tels.length;j++){
								messageService.sendMoMsg(request, ids[j], officeMessage.getSendDimension(), tels[j]);	
							}
						}
					}else if(officeMessage.getSendType().equals("2")){//微信、短信发送
						String[] ids = officeMessage.getSendId().split(",");
						String[] tels = officeMessage.getSendNumber().split(",");
						for(int j=0;j<tels.length;j++){
							messageService.sendWxMsg(request,
									ids[j], Constant.OFFICE_CORPID, Constant.OFFICE_PWD, officeMessage.getSendDimension(), tels[j]);
							messageService.sendMoMsg(request, ids[j], officeMessage.getSendDimension(), tels[j]);	
						}
					}else{
						System.out.println("未知的发送方式！");
					}
					officeMessage.setSendStatus("1");
					officeMessage.setSendTime(new Date());
					officeMessage.setSendById(curUser.getId());
					officeMessage.setSendBy(curUser.getName());
					officeMessageManager.save(officeMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("发送消息失败，请重试！");
		}
		return JsonWrapper.successWrapper("消息发送成功!");
	}
	//查询信息
   	@RequestMapping(value = "detailMessage")
   	@ResponseBody
   	public HashMap<String, Object> detailMessage(String id,HttpServletRequest request)throws Exception {
   		HashMap<String, Object> wrapper = new HashMap<String, Object>();
   		try{
   			List<EmployeeBase> employees= new ArrayList<EmployeeBase>();
   			OfficeMessage officeMessage=officeMessageManager.get(id);
   			String sendId=officeMessage.getSendId();
   			if(sendId!=null&&sendId!=""){
   				String[] sendIdArr=sendId.split(",");
   				for(int i=0;i<sendIdArr.length;i++){
   					EmployeeBase employee=employeeBaseManager.get(sendIdArr[i]);
   					employees.add(employee);
   				}
   				wrapper.put("employees",employees);
   			}
   			wrapper.put("officeMessage", officeMessage);
   			wrapper.put("success", true);
   		}catch(Exception e){
   			log.error("获取信息：" + e.getMessage());
   			wrapper.put("success", false);
   			wrapper.put("message", "获取信息出错！");
   			e.printStackTrace();	
   		}
   		return wrapper;
   	}
	
}