package com.lsts.office.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.office.bean.OfficeMessage;
import com.lsts.office.dao.OfficeMessageDao;
import com.scts.cspace.path.bean.TjyptResourcePath;
import com.scts.cspace.resource.bean.TjyptResourceInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("officeMessageManager")
public class OfficeMessageManager extends EntityManageImpl<OfficeMessage,OfficeMessageDao>{
    @Autowired
    OfficeMessageDao officeMessageDao;
    @Autowired
	private MessageService messageService;
    @Autowired
	private EmployeeBaseDao employeeBaseDao;
    /**
	 * 手动发送消息
	 * @param   
	 * @throws Exception
	 */
	public void sendByHand(String file_type) throws Exception {
		if(file_type.equals("1")){
		}else if(file_type.equals("2")){
		}
	}
	/**
     * 办公管理信息定时发送
     * @return
	 * @throws Exception 
     */
    public void sendTimeMessage(){
    	System.out.println("------------系统定时发送办公管理信息-----------");
    	HttpServletRequest request = null;
		List<?> list = officeMessageDao.getTimeMessage();//获取退休提醒对象集合
		//遍历获取发送内容并发送信息
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				String id=(String) objs[0];//业务ID
				String sendIds=(String) objs[1];//发送对象ID
				String sendNumbers=(String) objs[3];//发送号码
				String isTemporaryTel=(String) objs[4];//是否临时号码
				String sendDimension=(String) objs[5];//发送内容
				String sendType=(String) objs[6];//发送方式
				
				if(isTemporaryTel.equals("0")){//不是临时号码
					if(!sendIds.isEmpty()){
						String[] sendId=sendIds.split(",");
						if(sendType.equals("0")){//微信发送
							for(String sendId1:sendId){
								String sendMobile = employeeBaseDao.get(sendId1).getEmpPhone();
								messageService.sendWxMsg(request,
										sendId1, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, sendDimension, sendMobile);
							}
						}else if(sendType.equals("1")){//短信发送
							for(String sendId1:sendId){
								String sendMobile = employeeBaseDao.get(sendId1).getEmpPhone();
								messageService.sendMoMsg(request, sendId1, sendDimension, sendMobile);	
							}
						}else if(sendType.equals("2")){//微信、短信发送
							for(String sendId1:sendId){
								String sendMobile = employeeBaseDao.get(sendId1).getEmpPhone();
								messageService.sendWxMsg(request,
										sendId1, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, sendDimension, sendMobile);
								messageService.sendMoMsg(request, sendId1, sendDimension, sendMobile);
							}
						}
						
					}
				}else if(isTemporaryTel.equals("1")){//是临时号码
					if(!sendNumbers.isEmpty()){
						String[] sendNumber=sendNumbers.split(",");
						for(String sendMobile:sendNumber){
							messageService.sendMoMsg(request, "", sendDimension, sendMobile);	
						}
					}
				}
				OfficeMessage officeMessage = officeMessageDao.get(id);
				officeMessage.setSendBy("系统");
				officeMessage.setSendById("");
				officeMessage.setSendStatus("1");
				officeMessageDao.save(officeMessage);
			}
		}
    }
}
