package com.lsts.office.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.office.bean.WeightyDep;
import com.lsts.office.bean.WeightyTask;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.dao.WeightyDepDao;
import com.lsts.office.dao.WeightyTaskDao;



@Service("weightyTaskManager")
public class WeightyTaskManager extends EntityManageImpl<WeightyTask,WeightyTaskDao>{
    @Autowired
    WeightyTaskDao weightyTaskDao;
    @Autowired
    AttachmentManager attachmentManager;
    @Autowired
    WeightyDepDao weightyDepDao;
    @Autowired
    MessageService messageservice;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    public final static String BG_RWZT_WXF="WXF"; //未下发
    public final static String BG_RWZT_WKS="WKS"; //未开始
    public final static String BG_RWZT_YJS="JXZ"; //进行中
    public final static String BG_RWZT_YWC="YWC"; //已完成
    public final static String BG_RWZT_WWC="WWC"; //未完成
    
    /**
     * 任务下发改变状态
     * @param weightyTask
     * @throws Exception
     */
    public void taskSend(WeightyTask weightyTask) throws Exception{
		weightyTask.setStatus(WeightyTaskManager.BG_RWZT_WKS);//未开始
		weightyTaskDao.save(weightyTask);
		String taskLv = "1";
		timing(taskLv,weightyTask.getId());
    }
    /**
     * 微信接收任务并改变状态
     * @param weightyTask
     * @throws Exception
     */
	public void WXreceive(HttpServletRequest request,WeightyTask weightyTask,Employee employee) throws Exception{
		weightyTask.setStatus(WeightyTaskManager.BG_RWZT_YJS);//已接收
		weightyTaskDao.save(weightyTask);
		String d=weightyTaskDao.gettel(weightyTask.getRegistrantId()).toString();
		String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(d.toString()
				.substring(1,d.toString().length()-1)).replaceAll("'$1'");
		String fkMsg="【重大任务提醒】您发布的重大任务，任务名称：'"+weightyTask.getTaskContent().toString()+"' 已被‘"+employee.getOrg().getOrgName()+"’部门的‘"+employee.getName()+"’所接收";//发送内容
		messageservice.sendMoMsg(request,weightyTask.getId(),fkMsg,Account);//发送短信
		messageservice.sendWxMsg(request,weightyTask.getId(),Constant.OFFICE_CORPID, Constant.OFFICE_PWD,fkMsg ,Account);//发送微信
		/*timing(taskLv,weightyTask.getId());*/
  	}
    /**
     * PC端接收任务并改变状态
     * @param weightyTask
     * @throws Exception
     */
	public void receive(HttpServletRequest request,WeightyTask weightyTask) throws Exception{
		weightyTask.setStatus(WeightyTaskManager.BG_RWZT_YJS);//已接收
		weightyTaskDao.save(weightyTask);
		String taskLv = "2";
		timing(taskLv,weightyTask.getId());
		/*//weightyTask.setStatus(WeightyTaskManager.BG_RWZT_YJS);//已接收
		weightyTaskDao.save(weightyTask);
		String d=weightyTaskDao.gettel(weightyTask.getRegistrantId()).toString();
		String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(d.toString()
				.substring(1,d.toString().length()-1)).replaceAll("'$1'");
		String fkMsg="【重大任务提醒】您发布的重大任务任务，已被‘"+weightyTask.getMainDep()+"’部门的‘"+weightyTask.getMainLeadName()+"’所接收";//发送内容
		messageservice.sendMoMsg(request,weightyTask.getId(),fkMsg,Account);//发送短信
		messageservice.sendWxMsg(request,weightyTask.getId(),Constant.OFFICE_CORPID, Constant.OFFICE_PWD,fkMsg ,Account);//发送微信
		timing(taskLv,weightyTask.getId());*/
  	}
	
	/**
	 * 保存导入的重大任务数据
	 * 
	 * @param file
	 * @return
     * @throws ParseException 
	 */
	public String saveTaskData(String files, CurrentSessionUser user) throws ParseException {
		JSONArray array = JSONArray.fromObject(files);
		String repData="";
		String[] fileName = new String[array.length()];// 文件名
		String[] filePath = new String[array.length()];// 文件路径
//		String[] result= new String[array.length()];
		for (int i = 0; i < array.length(); i++) {
			fileName[i] = array.getJSONObject(i).getString("name");
			filePath[i] = array.getJSONObject(i).getString("path");
			repData = repData + saveDate(fileName[i], filePath[i], user);
		}
		return repData;
	}

	/**
	 * 根据导入的文件名获取并解析数据
	 * 
	 * @param file
	 * @throws ParseException 
	 * @throws IOException
	 */
	public String saveDate(String fileName, String filePath, CurrentSessionUser user) throws ParseException {
		String repData="";
//		try{
		fileName = this.getSystemFilePath()+File.separator+filePath;
		File taskfile = new File(fileName); // 创建文件对象  
		Workbook taskWb=null;
		try {
			taskWb = WorkbookFactory.create(taskfile);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Sheet taskSheet = taskWb.getSheetAt(0);//获得sheet
	   
	    for (int i=2;i<=taskSheet.getLastRowNum();i++) {
	    	Row row = taskSheet.getRow(i);//行
	    	if(row!=null && !"null".equals(row)) {
	    		if(row.getCell(0)!=null && !"null".equals(row.getCell(0))) {
	    			WeightyTask weightyTask = new WeightyTask();
		    			//保存任务信息
		    			weightyTask.setMainDep(row.getCell(1).getStringCellValue());
		    			weightyTask.setTaskContent(row.getCell(2).getStringCellValue());
		    			String code = row.getCell(3).getStringCellValue();
		    			if(row.getCell(3).getStringCellValue()!= null ||row.getCell(3).getStringCellValue().equals("")){
		    				if(code.equals("全年")){
		    					code = "YEAR";
		    				}
		    				if(code.equals("上半年")){
		    					code = "SYEAR";
		    				}
		    				if(code.equals("下半年")){
		    					code = "XYEAR";
		    				}
		    				if(code.equals("第一季度")){
		    					code = "QUARTER1";
		    				}
		    				if(code.equals("第二季度")){
		    					code = "QUARTER2";
		    				}
		    				if(code.equals("第三季度")){
		    					code = "QUARTER3";
		    				}
		    				weightyTask.setFinishLimit(code);
		    			}
		    			
		    			weightyTask.setMainLeadName(row.getCell(4).getStringCellValue());
		    			weightyTask.setMainDutyName(row.getCell(5).getStringCellValue());
		    			weightyTask.setRegistrantId(user.getId());
		    			weightyTask.setRegistrantName(user.getName());
		    			weightyTask.setRegistrantTime(new Date());
		    			weightyTaskDao.save(weightyTask);
	    			
	    			//保存配合部门信息
	    			String depNames = row.getCell(6).getStringCellValue();
	    			String[]  depName = depNames.split("、");
	    			if (depName.length>0) {
	    				for (int j=0;j<depName.length;j++) {
	    					WeightyDep weightyDep = new WeightyDep();
	    					weightyDep.setPhDepName(depName[j]);
	    					try{
	    						weightyDep.setWeightyTask(weightyTask);;
	    					}catch(Exception e){
	    						e.printStackTrace();
	    					}
	    					weightyDepDao.save(weightyDep);
	    				}
	    			}
	    		}
	    	}
	    }
		return repData;
		
	}

	
	public String getSystemFilePath() {
		
		SysParaInf sp = Factory.getSysPara();
		String attachmentPath = sp.getProperty("attachmentPath");
		String attachmentPathType = sp.getProperty("attachmentPathType", "relative");
		
		if ("relative".equals(attachmentPathType)) {
			return Factory.getWebRoot() + File.separator + attachmentPath;
		}
		return attachmentPath;
	}
	
	/**
	 * 保存信息 并遍历部门信息获得ID
	 * @param weightyTask
	 * @param user
	 * @param ids
	 */
	public void saveWei(WeightyTask weightyTask,CurrentSessionUser user){
		weightyTask.setRegistrantId(user.getId());
		weightyTask.setRegistrantName(user.getName());
		weightyTask.setRegistrantTime(new Date());
		weightyTaskDao.save(weightyTask);
		
//		if(ids.length>0){
//    		//遍历部门id
//    		for(int i=0;i<ids.length;i++){
//    			WeightyDep weightyDep = weightyDepDao.get(ids[i]);
//    			weightyDep.setPkDepId(weightyTask.getId());
//    			weightyDepDao.save(weightyDep);
//    		}
//		}
	}
	
	 /**
     * 短信定时推送
     * @return
	 * @throws Exception 
     */
    
    public void Autopush(){
    		timing("0","");
    }
    
    
    /**
     * 短信推送
     * @return
	 * @throws Exception 
     */
    
    public void timing(String taskLv,String Taskid){
    	CurrentSessionUser user = SecurityUtil.getSecurityUser();
		WeightyTask weightyTask  = weightyTaskDao.get(Taskid);
		String task = weightyTask.getTaskContent();//获得任务内容
		Date  day = weightyTask.getFinishTime(); //获取任务结束时间
		String[] userIds = weightyTask.getMainDutyId().split(",");//用","号拆分负责人ID
		Date sendTime= new Date();//当前日期
		String fkMsg="";
		if(taskLv == "1"){
			fkMsg="【重大任务提醒】您部门有工作任务未开始，任务名称：'"+task+"' 请注意及时接收!";//发送内容
			for(String userId:userIds){  //循环人员ID
				String mobile=weightyTaskDao.getsjh(userId).toString();//获取电话
				String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
						.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
				messageservice.sendMoMsg(null, userId, fkMsg, Account);//发送短信
				messageservice.sendWxMsg(null, userId, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, fkMsg, Account);//发送微信
			}
		}else if(taskLv == "2"){
			
			fkMsg="【重大任务提醒】您发布的重大任务，任务名称：'"+task+"' 已被‘"+user.getDepartment().getOrgName()+"’的‘"+user.getName()+"’所接收!";//发送内容
			String mobile=weightyTaskDao.gettel(weightyTask.getRegistrantId()).toString();//获取电话
			String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
					.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
			messageservice.sendMoMsg(null, weightyTask.getRegistrantId(), fkMsg, Account);
			messageservice.sendWxMsg(null, weightyTask.getRegistrantId(), Constant.OFFICE_CORPID, Constant.OFFICE_PWD, fkMsg, Account);
		}else{
			fkMsg="【重大任务提醒】您部门有工作任务还在执行中，任务名称：'"+task+"' 截止日期："+day+"!";//发送内容
			for(String userId:userIds){  //循环人员ID
				String mobile=weightyTaskDao.getsjh(userId).toString();//获取电话
				String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
						.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
				messageservice.sendMoMsg(null, userId, fkMsg, Account);//发送短信
				messageservice.sendWxMsg(null, userId, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, fkMsg, Account);//发送微信
			}
		}
			/*List userList = weightyTaskDao.getUser(taskLv,Taskid); //获取SQL信息
			//因为责任人不同 需要 每个人物发送给对应的责任人
			if (userList != null && userList.size() > 0) { 
				for (int u = 0; u < userList.size(); u++) {
					Object user1[] = null;
					user1 = (Object[]) userList.get(u);
					String id = (String) user1[0];
					String task = user1[3].toString();//获得任务内容
					Date  day = (Date) user1[4]; //获取任务结束时间
					String Account = (String) user1[2];
					if(taskLv == "1"){
						String fkMsg="【重大任务提醒】您部门有工作任务未开始，任务名称："+task+",请注意及时接收!";//发送内容
						messageservice.sendMoMsg(null, id, fkMsg, Account);
						messageservice.sendWxMsg(null, id, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, fkMsg, Account);
					}else if(taskLv == "2"){
						String fkMsg="【重大任务提醒】您发布的工作任务，任务名称："+task+",已被"+user.getDepartment().getOrgName()+"的'"+user.getName()+"'所接收";//发送内容
						messageservice.sendMoMsg(null, id, fkMsg, Account);
						messageservice.sendWxMsg(null, id, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, fkMsg, Account);
					}else{
						String fkMsg="【重大任务提醒】您部门有工作任务还在执行中，任务名称："+task+",截止日期："+day+"!";//发送内容
						messageservice.sendMoMsg(null, id, fkMsg, Account);
						messageservice.sendWxMsg(null, id, Constant.OFFICE_CORPID, Constant.OFFICE_PWD, fkMsg, Account);
					}
				}		
			}*/
	
		
	}

    
	
	public void delete(String ids){
		Session session = weightyTaskDao.getHibernateTemplate().getSessionFactory().getCurrentSession();
		for(String id : ids.split(",")){
			WeightyTask weightyTask = weightyTaskDao.get(id);
			session.delete(weightyTask);
		}
	}
}
