package com.lsts.qualitymanage.service;

import java.util.Calendar;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.qualitymanage.bean.Taskbook;
import com.lsts.qualitymanage.dao.TaskbookDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("TaskbookManager")
public class TaskbookManager extends EntityManageImpl<Taskbook,TaskbookDao>{
    @Autowired
    TaskbookDao taskbookDao;
    
    /**
     * 生成编号
     * */
    public synchronized String getbh(){
    	String docNum = "";
		Calendar a=Calendar.getInstance();
		int nowYear = a.get(Calendar.YEAR);
    	List<Taskbook> taskbookList=taskbookDao.getTaskAllot();
    	if(taskbookList==null || taskbookList.size()==0) {
 			docNum = "CTJC-012-B01-"+nowYear+"-"+"0001";
 		}else{
 			int[] docNumArray = new int[taskbookList.size()];
 			for (int i=0;i<taskbookList.size();i++) {
 				//将编号去掉“-”，转换成int型存入数组
 				if(taskbookList.get(i).getIdentifier()!=null && !"".equals(taskbookList.get(i).getIdentifier())){
 					String str =taskbookList.get(i).getIdentifier();
    		  		StringBuffer sb=new StringBuffer(str);
    				//将编号去掉“-”，转换成int型存入数组
    				docNumArray[i] = Integer.parseInt(sb.substring(13,22).replaceAll("-", ""));
 				}
 			}
 			int max = docNumArray[0];
 			//获取数组中最大的值
 			for (int i : docNumArray) {
 				max = max>i?max:i;
 			}
 			String docNum1 = String.valueOf(max+1);
 			if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
 	 			docNum = "CTJC-012-B01-"+nowYear+"-"+"0001";
 	 		}else{
	 			//编号加1后重新组
	 			docNum = "CTJC-012-B01-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
 	 		}
 		}
    	return docNum;
    }
    /**
     * 生成任务书
     * @param identifier  编号
     * @param testNature  检验性质（法定，委托）
     * @param reportNumber  报告编号
     * @param userName  用户名称
     * @param equipmentName  设备名称
     * @param content  检验/委托内容（设备品种）（设备类别）
     * @param zlfs  资料份数（台数）
     * @param bjwtsj  报检/委托时间
     * @param department  承接部门（申请部门）
     * @param departmentId  部门id
     * @param contractNumber  合同编号
     * @param registrant
     * @param registrantId
     * @param registrantDate
     * @param ywid  业务ID
     * @param rwdId 任务单id
     * @param rwdSn 任务单编号
     * */
    public void setTaskbook(String identifier,String testNature,String reportNumber,String userName,String equipmentName,
    		String content,String zlfs,java.util.Date bjwtsj,String department,String departmentId,
    		String contractNumber,String registrant,String registrantId,java.util.Date registrantDate,String ywid,String rwdId,String rwdSn){
    	Taskbook taskbook=new Taskbook();
    	taskbook.setIdentifier(identifier);
    	taskbook.setTestNature(testNature);
    	taskbook.setReportNumber(reportNumber);
    	taskbook.setUserName(userName);
    	taskbook.setEquipmentName(equipmentName);
    	taskbook.setContent(content);
    	taskbook.setZlfs(zlfs);
    	taskbook.setBjwtsj(bjwtsj);
    	taskbook.setDepartment(department);
    	taskbook.setDepartmentId(departmentId);
    	taskbook.setContractNumber(contractNumber);
    	taskbook.setRegistrant(registrant);
    	taskbook.setRegistrantId(registrantId);
    	taskbook.setRegistrantDate(registrantDate);
    	taskbook.setYwid(ywid);
    	taskbook.setStatus("未填写");
    	taskbook.setRwdId(rwdId);
    	taskbook.setRwdSn(rwdSn);
    	taskbookDao.save(taskbook);
    }
}
