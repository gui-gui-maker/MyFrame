package com.lsts.office.service;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


import org.hibernate.Session;

import net.sf.json.JSONArray;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.humanresources.dao.EmployeeBaseDao;
import com.lsts.office.bean.Ywhbsgz;
import com.lsts.office.dao.YwhbsgzDao;


/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("ywhbsgzManager")
public class YwhbsgzManager extends EntityManageImpl<Ywhbsgz, YwhbsgzDao> {

	// 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
	@Autowired
	YwhbsgzDao ywhbsgzDao;
	@Autowired
	EmployeesDao employeesDao;
	@Autowired
	private MessageXinxiService messageXinxiService;
	@Autowired
    EmployeeBaseDao employeeBaseDao;
	
		public final static String BG_RWZT_WXF="WXF"; //未下发
	    public final static String BG_RWZT_WKS="WKS"; //未开始
	    public final static String BG_RWZT_YJS="JXZ"; //进行中
	    public final static String BG_RWZT_YWC="YWC"; //已完成
	
	    /**
	     * 任务下发改变状态
	     * @param weightyTask
	     * @throws Exception
	     */
	    public void taskSend(Ywhbsgz ywhbsgz) throws Exception{
	    	ywhbsgz.setStatus(WeightyTaskManager.BG_RWZT_WKS);//未开始
			ywhbsgzDao.save(ywhbsgz);
			String taskLv = "1";
			dx1(taskLv,ywhbsgz.getId());
	    }
	    
	    /**
	     * 接收任务并改变状态
	     * @param weightyTask
	     * @throws Exception
	     */
		public void receive(Ywhbsgz ywhbsgz) throws Exception{
			ywhbsgz.setStatus(WeightyTaskManager.BG_RWZT_YJS);//已接收
			ywhbsgzDao.save(ywhbsgz);
			String taskLv = "2";
			dx1(taskLv,ywhbsgz.getId());
	  	}
		
		/**
	     * 微信接收任务并改变状态
	     * @param weightyTask
	     * @throws Exception
	     */
		public void WXreceive(Ywhbsgz ywhbsgz,Employee employee) throws Exception{
			ywhbsgzDao.save(ywhbsgz);
			String taskLv = "2";
			dx2(taskLv,ywhbsgz,employee);
	  	}
	    
	/**
	 * 级联删除写法
	 * @param ids
	 */
	public void delete(String ids) {
		Session session = ywhbsgzDao.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		for(String id:ids.split(",")){
			Ywhbsgz ywhbsgz = ywhbsgzDao.get(id);
			session.delete(ywhbsgz);
		}
	}
	
	
	
	 AttachmentManager attachmentManager;
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    /**
		 * 保存导入的工作任务数据
		 * 
		 * @param file
		 * @return
	     * @throws ParseException 
		 */
		public String saveBankData(String files, CurrentSessionUser user) throws ParseException {
			JSONArray array = JSONArray.fromObject(files);
			String repData="";
			String[] fileName = new String[array.length()];// 文件名
			String[] filePath = new String[array.length()];// 文件路径
//			String[] result= new String[array.length()];
			for (int i = 0; i < array.length(); i++) {
				fileName[i] = array.getJSONObject(i).getString("name");
				filePath[i] = array.getJSONObject(i).getString("path");
				repData = repData + saveDate(fileName[i], filePath[i], user);
			}
			return repData;
		}

		/**
		 * 根据导入的文件名获取并解析财务数据
		 * 
		 * @param file
		 * @throws ParseException 
		 * @throws IOException
		 */
		public String saveDate(String fileName, String filePath, CurrentSessionUser user) throws ParseException {
			String repData="";
			fileName = this.getSystemFilePath()+File.separator+filePath;
			File bankfile = new File(fileName); // 创建文件对象  
			Workbook bankWb=null;
			try {
				bankWb = WorkbookFactory.create(bankfile);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    Sheet bankSheet = bankWb.getSheetAt(0);//获得sheet
		   
		    for (int i=3;i<=bankSheet.getLastRowNum();i++) {
		    	Row row = bankSheet.getRow(i);//行
		    	if(row!=null && !"null".equals(row)) {
		    		if(row.getCell(0)!=null && !"null".equals(row.getCell(0))) {
		    			Ywhbsgz Ywhbsgz = new Ywhbsgz();
		    			
		    			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		    			User uu = (User)curUser.getSysUser();
		    			com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
		    			String eId=e.getId();
		    			String eName=e.getName();
		    			Ywhbsgz.setCreaterId(eId);
		    			Ywhbsgz.setCreater(eName);
		    			Ywhbsgz.setCreaterTime(new Date());
		    			
		    			//得到列
		    			String tim = row.getCell(0).getStringCellValue();
		    			System.out.println(tim);
		    			Ywhbsgz.setStatus(WeightyTaskManager.BG_RWZT_YWC);//导入状态
		    			CurrentSessionUser user1 = SecurityUtil.getSecurityUser();//
		    			String userid = getmid(user1.getId());
		    			Ywhbsgz.setResponsiblePersonid(userid);//当前责任人id
		    			Ywhbsgz.setResponsiblePerson(user1.getName());//当前责任人
		    		/*	for(int t=1;t<1000;t++){
		    				System.out.println(bankSheet.getRow(i-t).getCell(0).getStringCellValue());
		    			if(bankSheet.getRow(i-t).getCell(0).getStringCellValue()==""){
		    				String tim1 = bankSheet.getRow(i-t-1).getCell(0).getStringCellValue();
		    				String[] arr1 = tim1.split("-");
		    				Ywhbsgz.setStartTime(parseDate(arr1[0]));
		    				Ywhbsgz.setEndTim(parseDate(arr1[0].split("年")[0]+"年"+arr1[1]));
		    			}else{
		    				String tim2 = bankSheet.getRow(i).getCell(0).getStringCellValue();
		    				String[] arr = tim2.split("-");
		    				Ywhbsgz.setStartTime(parseDate(arr[0]));
		    				Ywhbsgz.setEndTim(parseDate(arr[0].split("年")[0]+"年"+arr[1]));
		    				break;
		    			}}*/
		    			/*if(tim==""){//开始时间和结束时间
		    				if(bankSheet.getRow(i-1).getCell(0).getStringCellValue()==""){
		    					if(bankSheet.getRow(i-2).getCell(0).getStringCellValue()==""){
		    						if(bankSheet.getRow(i-3).getCell(0).getStringCellValue()==""){
		    							if(bankSheet.getRow(i-4).getCell(0).getStringCellValue()==""){
		    								if(bankSheet.getRow(i-5).getCell(0).getStringCellValue()==""){
		    									if(bankSheet.getRow(i-6).getCell(0).getStringCellValue()==""){
		    										if(bankSheet.getRow(i-7).getCell(0).getStringCellValue()==""){
		    											if(bankSheet.getRow(i-8).getCell(0).getStringCellValue()==""){
		    												 if(bankSheet.getRow(i-9).getCell(0).getStringCellValue()==""){
		    													 if(bankSheet.getRow(i-10).getCell(0).getStringCellValue()==""){
		    														 if(bankSheet.getRow(i-11).getCell(0).getStringCellValue()==""){
		    															 if(bankSheet.getRow(i-12).getCell(0).getStringCellValue()==""){
		    																 if(bankSheet.getRow(i-13).getCell(0).getStringCellValue()==""){
		    																	 if(bankSheet.getRow(i-14).getCell(0).getStringCellValue()==""){
		    																		 String tim15 = bankSheet.getRow(i-15).getCell(0).getStringCellValue();
							    									    				String[] arr15 = tim15.split("-");
							    									    				Ywhbsgz.setStartTime(parseDate(arr15[0]));
							    									    				Ywhbsgz.setEndTim(parseDate(arr15[0].split("年")[0]+"年"+arr15[1]));
		    																	 }else{
		    																	 String tim14 = bankSheet.getRow(i-14).getCell(0).getStringCellValue();
						    									    				String[] arr14 = tim14.split("-");
						    									    				Ywhbsgz.setStartTime(parseDate(arr14[0]));
						    									    				Ywhbsgz.setEndTim(parseDate(arr14[0].split("年")[0]+"年"+arr14[1]));
		    																	 } }
		    																 String tim13 = bankSheet.getRow(i-13).getCell(0).getStringCellValue();
					    									    				String[] arr13 = tim13.split("-");
					    									    				Ywhbsgz.setStartTime(parseDate(arr13[0]));
					    									    				Ywhbsgz.setEndTim(parseDate(arr13[0].split("年")[0]+"年"+arr13[1]));
		    															 }else{
		    															 String tim12 = bankSheet.getRow(i-12).getCell(0).getStringCellValue();
				    									    				String[] arr12 = tim12.split("-");
				    									    				Ywhbsgz.setStartTime(parseDate(arr12[0]));
				    									    				Ywhbsgz.setEndTim(parseDate(arr12[0].split("年")[0]+"年"+arr12[1]));
		    															 } }else{
		    														 String tim11 = bankSheet.getRow(i-11).getCell(0).getStringCellValue();
			    									    				String[] arr11 = tim11.split("-");
			    									    				Ywhbsgz.setStartTime(parseDate(arr11[0]));
			    									    				Ywhbsgz.setEndTim(parseDate(arr11[0].split("年")[0]+"年"+arr11[1]));
		    														 } }else{
		    													 String tim10 = bankSheet.getRow(i-10).getCell(0).getStringCellValue();
		    									    				String[] arr10 = tim10.split("-");
		    									    				Ywhbsgz.setStartTime(parseDate(arr10[0]));
		    									    				Ywhbsgz.setEndTim(parseDate(arr10[0].split("年")[0]+"年"+arr10[1]));
		    													 }}else{
		    													 String tim9 = bankSheet.getRow(i-9).getCell(0).getStringCellValue();
		    									    				String[] arr9 = tim9.split("-");
		    									    				Ywhbsgz.setStartTime(parseDate(arr9[0]));
		    									    				Ywhbsgz.setEndTim(parseDate(arr9[0].split("年")[0]+"年"+arr9[1]));
		    												 }}else{
		    													 String tim8 = bankSheet.getRow(i-8).getCell(0).getStringCellValue();
		    									    				String[] arr8 = tim8.split("-");
		    									    				Ywhbsgz.setStartTime(parseDate(arr8[0]));
		    									    				Ywhbsgz.setEndTim(parseDate(arr8[0].split("年")[0]+"年"+arr8[1]));
		    											}}else{
		    												String tim7 = bankSheet.getRow(i-7).getCell(0).getStringCellValue();
		    							    				String[] arr7 = tim7.split("-");
		    							    				Ywhbsgz.setStartTime(parseDate(arr7[0]));
		    							    				Ywhbsgz.setEndTim(parseDate(arr7[0].split("年")[0]+"年"+arr7[1]));
		    										}}else{
		    											String tim6 = bankSheet.getRow(i-6).getCell(0).getStringCellValue();
		    						    				String[] arr6 = tim6.split("-");
		    						    				Ywhbsgz.setStartTime(parseDate(arr6[0]));
		    						    				Ywhbsgz.setEndTim(parseDate(arr6[0].split("年")[0]+"年"+arr6[1]));
		    									}}else{
		    										String tim5 = bankSheet.getRow(i-5).getCell(0).getStringCellValue();
		    					    				String[] arr5 = tim5.split("-");
		    					    				Ywhbsgz.setStartTime(parseDate(arr5[0]));
		    					    				Ywhbsgz.setEndTim(parseDate(arr5[0].split("年")[0]+"年"+arr5[1]));
		    								}}else{
		    									String tim4 = bankSheet.getRow(i-4).getCell(0).getStringCellValue();
		    				    				String[] arr4 = tim4.split("-");
		    				    				Ywhbsgz.setStartTime(parseDate(arr4[0]));
		    				    				Ywhbsgz.setEndTim(parseDate(arr4[0].split("年")[0]+"年"+arr4[1]));
		    							}}else{
		    								String tim3 = bankSheet.getRow(i-3).getCell(0).getStringCellValue();
		    			    				String[] arr3 = tim3.split("-");
		    			    				Ywhbsgz.setStartTime(parseDate(arr3[0]));
		    			    				Ywhbsgz.setEndTim(parseDate(arr3[0].split("年")[0]+"年"+arr3[1]));
		    						}}else{
		    							String tim2 = bankSheet.getRow(i-2).getCell(0).getStringCellValue();
		    		    				String[] arr2 = tim2.split("-");
		    		    				Ywhbsgz.setStartTime(parseDate(arr2[0]));
		    		    				Ywhbsgz.setEndTim(parseDate(arr2[0].split("年")[0]+"年"+arr2[1]));
		    					}}else{
		    				String tim1 = bankSheet.getRow(i-1).getCell(0).getStringCellValue();
		    				String[] arr1 = tim1.split("-");
		    				Ywhbsgz.setStartTime(parseDate(arr1[0]));
		    				Ywhbsgz.setEndTim(parseDate(arr1[0].split("年")[0]+"年"+arr1[1]));
		    				}
		    			}else{
		    				String[] arr = tim.split("-");
		    			Ywhbsgz.setStartTime(parseDate(arr[0]));
		    			Ywhbsgz.setEndTim(parseDate(arr[0].split("年")[0]+"年"+arr[1]));
		    			}*/
		    			
		    			Ywhbsgz.setMissionContent(row.getCell(2).getStringCellValue());//内容
		    			Ywhbsgz.setPerformance(row.getCell(3).getStringCellValue());
		    			Ywhbsgz.setUnfinishedReason(row.getCell(4).getStringCellValue());
		    			String De = row.getCell(1).getStringCellValue();
		    			if(De==""){//部门
		    				if(bankSheet.getRow(i-1).getCell(1).getStringCellValue()==""){
		    					if(bankSheet.getRow(i-2).getCell(1).getStringCellValue()==""){
		    						if(bankSheet.getRow(i-3).getCell(1).getStringCellValue()==""){
		    							if(bankSheet.getRow(i-4).getCell(1).getStringCellValue()==""){
		    								if(bankSheet.getRow(i-5).getCell(1).getStringCellValue()==""){
		    									if(bankSheet.getRow(i-6).getCell(1).getStringCellValue()==""){
		    										if(bankSheet.getRow(i-7).getCell(1).getStringCellValue()==""){
		    											if(bankSheet.getRow(i-8).getCell(1).getStringCellValue()==""){
		    												 if(bankSheet.getRow(i-9).getCell(1).getStringCellValue()==""){
		    														Ywhbsgz.setDepartment(bankSheet.getRow(i-10).getCell(1).getStringCellValue());
		    												 }else{
		    												Ywhbsgz.setDepartment(bankSheet.getRow(i-9).getCell(1).getStringCellValue());
		    												 }}else{
		    											Ywhbsgz.setDepartment(bankSheet.getRow(i-8).getCell(1).getStringCellValue());
		    											}}else{
		    										Ywhbsgz.setDepartment(bankSheet.getRow(i-7).getCell(1).getStringCellValue());
		    										}}else{
		    									Ywhbsgz.setDepartment(bankSheet.getRow(i-6).getCell(1).getStringCellValue());
		    									}}else{
		    								Ywhbsgz.setDepartment(bankSheet.getRow(i-5).getCell(1).getStringCellValue());
		    								}}else{
		    							Ywhbsgz.setDepartment(bankSheet.getRow(i-4).getCell(1).getStringCellValue());
		    							}}else{
		    						Ywhbsgz.setDepartment(bankSheet.getRow(i-3).getCell(1).getStringCellValue());
		    						}}else{
		    					Ywhbsgz.setDepartment(bankSheet.getRow(i-2).getCell(1).getStringCellValue());
		    					}}else{
		    				Ywhbsgz.setDepartment(bankSheet.getRow(i-1).getCell(1).getStringCellValue());
		    				}}else{
		    			Ywhbsgz.setDepartment(row.getCell(1).getStringCellValue());
		    			}
		    			
				    	ywhbsgzDao.save(Ywhbsgz);
		    		}
		    	}
		    }
		    //取出前一个月的银行转账信息
	
//			} catch (Exception e) {
//				 //导入失败
//				 e.printStackTrace();
//			}
			return repData;
		}
		
		/**
		 * 日期转换
		 * @param st
		 * @return
		 * @throws ParseException
		 */
		public Date parseDate(String st) throws ParseException {
			SimpleDateFormat sdm=new SimpleDateFormat ("yyyy年MM月dd日");
			Date time = sdm.parse(st);//将字符串转换成日期类型
				return time;
			
		}
		
		/**
		 * 数值转换
		 * @param st
		 * @return
		 */
		@SuppressWarnings("finally")
		public BigDecimal parseBigDecimal(String st) {
			BigDecimal bg=null;
			try {
				bg = new BigDecimal(st);
			} finally {
				return bg;
			}
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
		 * 回写父表状态
		 */
		public Ywhbsgz ywhbsgzid(String ids) throws ParseException {
			
			List<Ywhbsgz> ywhbsgz =	ywhbsgzDao.Ywhbsgz(ids);
			Ywhbsgz nowid= ywhbsgz.get(0);
			
			
			return nowid;
		}
		/**
	     * 短信定时推送
	     * @return
		 * @throws Exception 
	     */
	    
	    public void dx(){
	    	try {
				dx1("0","");
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    }
		/**
		 *  短信提醒
		 *  发送短信 将信息放进数据库
		 * @return
		 * @throws ParseException
		 */
		public StringBuffer dx1(String taskLv,String Taskid) throws ParseException {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			Ywhbsgz ywhbsgz = ywhbsgzDao.get(Taskid);
			String task=ywhbsgz.getMissionContent();//获得任务内容
			Date  day=ywhbsgz.getEndTim(); //获取任务结束时间
			String[] userId = ywhbsgz.getResponsiblePersonid().split(",");//用","号拆分负责人ID
			Date sendTime= new Date();//当前日期
			String businessType="工作任务";
			String transmitMode="每周一7点";
			String sendType="短信、微信";
			String fkMsg="";
			try {
				if(taskLv == "1"){
					fkMsg="【工作任务提醒】您部门有工作任务未开始，任务名称："+task+"  请注意及时接收！";//发送内容
					for(String userIds:userId){  //循环人员ID
						
						String mobile=ywhbsgzDao.getsjh(userIds).toString();//获取电话
						String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
								.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
						messageXinxiService.setSaveMessageXinxi(userIds,employeeBaseDao.get(userIds).getEmpName().toString(), mobile, sendTime, fkMsg, businessType, transmitMode, sendType);
						messageXinxiService.setSendMessage(Account, fkMsg);//短信接口
						messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, Account, fkMsg);//发送微信
					}
				}else if(taskLv == "2"){
					String mobile=ywhbsgzDao.getsjh(ywhbsgz.getCreaterId()).toString();//获取电话
					String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
							.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
					fkMsg="【工作任务提醒】您发布的工作任务，任务名称："+task+"  已被‘"+user.getDepartment().getOrgName()+"’的‘"+user.getName()+"’所接收！";;//发送内容
					messageXinxiService.setSaveMessageXinxi(ywhbsgz.getCreaterId().toString(), ywhbsgz.getCreater(), ywhbsgzDao.getsjh(ywhbsgz.getCreaterId()).toString(), sendTime, fkMsg, businessType, transmitMode, sendType);
					messageXinxiService.setSendMessage(Account, fkMsg);//短信接口
					messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, Account, fkMsg);//发送微信
				}else{
					fkMsg="【工作任务提醒】您部门有工作任务还在执行中，任务名称："+task+",截止日期："+day+"！";//发送内容
					for(String userIds:userId){  //循环人员ID
						//获取电话
						String mobile=ywhbsgzDao.getsjh(userIds).toString();
						String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
								.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
						messageXinxiService.setSaveMessageXinxi(userIds, employeeBaseDao.get(userIds).getEmpName().toString(),mobile, sendTime, fkMsg, businessType, transmitMode, sendType);
						messageXinxiService.setSendMessage(Account, fkMsg);//短信接口
						messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, Account, fkMsg);//发送微信
					}
				}
			/*	List userList1 = ywhbsgzDao.getUser(taskLv,Taskid); //获取SQL信息
				//因为责任人不同 需要 每个人物发送给对应的责任人
			if (userList1 != null && userList1.size() > 0) { 
				for (int u = 0; u < userList1.size(); u++) {
					Object user1[] = null;
					user1 = (Object[]) userList1.get(u);
					System.out.println("责任人所在部门："+user1[0]);
					System.out.println("工作内容："+user1[1]);
					System.out.println("结束时间："+user1[2]);
					System.out.println("责任人姓名："+user1[3]);
					System.out.println("电话号码："+user1[4]);
					System.out.println("责任人employeeID："+user1[5]);
			
			
			String personName=(String) user1[3];//责任人
			String account=(String) user1[4];//手机号码
			String personId=(String) user1[5];//责任人id
			
			if(taskLv == "1"){
				fkMsg="【工作任务提醒】您部门有工作任务未开始，任务名称："+task+"  请注意及时接收！";//发送内容
			}else if(taskLv == "2"){
				//发布任务的人的employeeID
				personId = ywhbsgz.getCreaterId();
				personName = ywhbsgz.getCreater();
				account = employeesDao.get(personId).getMobileTel();
				System.out.println("发布人ID："+personId);
				System.out.println("发布人姓名："+personName);
				System.out.println("发布人电话："+account);
				fkMsg="【工作任务提醒】您发布的工作任务，任务名称："+task+"  已被‘"+user.getDepartment().getOrgName()+"’的‘"+user.getName()+"’所接收！";;//发送内容
			}else{
				fkMsg="【工作任务提醒】您部门有工作任务还在执行中，任务名称："+task+",截止日期："+day+"！";//发送内容
			}
			Date sendTime= new Date();//当前日期
			String businessType="工作任务";
			String transmitMode="每周一7点";
			String sendType="短信、微信";
			//放入数据库
			messageXinxiService.setSaveMessageXinxi(personId, personName, account, sendTime, fkMsg, businessType, transmitMode, sendType);
			messageXinxiService.setSendMessage(account, fkMsg);//短信接口
			messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, account, fkMsg);//发送微信
						}
					}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		
			
		}

		public String getmid(String id) throws ParseException {
		
		List getmids =	ywhbsgzDao.getmid(id);
		
		Object user1[] = null;
		user1 = (Object[]) getmids.get(0);
		
		System.out.println(user1[7]);
		String mids =  user1[7].toString();
		return mids;
	}
		
		
		/**
		 *  微信接收任务短信提醒
		 *  发送短信 将信息放进数据库
		 * @return
		 * @throws ParseException
		 */
		public StringBuffer dx2(String taskLv,Ywhbsgz ywhbsgz,Employee employee) throws ParseException {
			String task=ywhbsgz.getMissionContent();//获得任务内容
			Date  day=ywhbsgz.getEndTim(); //获取任务结束时间
			String[] userId = ywhbsgz.getResponsiblePersonid().split(",");//用","号拆分负责人ID
			Date sendTime= new Date();//当前日期
			String businessType="工作任务";
			String transmitMode="每周一7点";
			String sendType="短信、微信";
			String fkMsg="";
			try {
				if(taskLv == "1"){
					fkMsg="【工作任务提醒】您部门有工作任务未开始，任务名称："+task+"  请注意及时接收！";//发送内容
					for(String userIds:userId){  //循环人员ID
						
						String mobile=ywhbsgzDao.getsjh(userIds).toString();//获取电话
						String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
								.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
						messageXinxiService.setSaveMessageXinxi(userIds,employeeBaseDao.get(userIds).getEmpName().toString(), mobile, sendTime, fkMsg, businessType, transmitMode, sendType);
						messageXinxiService.setSendMessage(Account, fkMsg);//短信接口
						messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, Account, fkMsg);//发送微信
					}
				}else if(taskLv == "2"){
					String mobile=ywhbsgzDao.getsjh(ywhbsgz.getCreaterId()).toString();//获取电话
					String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
							.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
					fkMsg="【工作任务提醒】您发布的工作任务，任务名称："+task+"  已被‘"+employee.getOrg().getOrgName()+"’的‘"+employee.getName()+"’所接收！";;//发送内容
					messageXinxiService.setSaveMessageXinxi(ywhbsgz.getCreaterId().toString(), ywhbsgz.getCreater(), ywhbsgzDao.getsjh(ywhbsgz.getCreaterId()).toString(), sendTime, fkMsg, businessType, transmitMode, sendType);
					messageXinxiService.setSendMessage(Account, fkMsg);//短信接口
					messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, Account, fkMsg);//发送微信
				}else{
					fkMsg="【工作任务提醒】您部门有工作任务还在执行中，任务名称："+task+",截止日期："+day+"！";//发送内容
					for(String userIds:userId){  //循环人员ID
						//获取电话
						String mobile=ywhbsgzDao.getsjh(userIds).toString();
						String Account = Pattern.compile("\\b([\\w\\W])\\b").matcher(mobile.toString()
								.substring(1,mobile.toString().length()-1)).replaceAll("'$1'");
						messageXinxiService.setSaveMessageXinxi(userIds, employeeBaseDao.get(userIds).getEmpName().toString(),mobile, sendTime, fkMsg, businessType, transmitMode, sendType);
						messageXinxiService.setSendMessage(Account, fkMsg);//短信接口
						messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, Account, fkMsg);//发送微信
					}
				}
			/*	List userList1 = ywhbsgzDao.getUser(taskLv,Taskid); //获取SQL信息
				//因为责任人不同 需要 每个任务发送给对应的责任人
			if (userList1 != null && userList1.size() > 0) { 
				for (int u = 0; u < userList1.size(); u++) {
					Object user1[] = null;
					user1 = (Object[]) userList1.get(u);
					System.out.println("责任人所在部门："+user1[0]);
					System.out.println("工作内容："+user1[1]);
					System.out.println("结束时间："+user1[2]);
					System.out.println("责任人姓名："+user1[3]);
					System.out.println("电话号码："+user1[4]);
					System.out.println("责任人employeeID："+user1[5]);
			
			String task=user1[1].toString();//获得任务内容
			Date  day=(Date) user1[2]; //获取任务结束时间
			String personName=(String) user1[3];//责任人
			String account=(String) user1[4];//手机号码
			String personId=(String) user1[5];//责任人id
			String fkMsg="";
			if(taskLv == "1"){
				fkMsg="【工作任务提醒】您部门有工作任务未开始，任务名称："+task+"  请注意及时接收！";//发送内容
			}else if(taskLv == "2"){
				//发布任务的人的employeeID
				personId = ywhbsgz.getCreaterId();
				personName = ywhbsgz.getCreater();
				account = employeesDao.get(personId).getMobileTel();
				System.out.println("发布人ID："+personId);
				System.out.println("发布人姓名："+personName);
				System.out.println("发布人电话："+account);
				fkMsg="【工作任务提醒】您发布的工作任务，任务名称："+task+"  已被‘"+employee.getOrg().getOrgName()+"’的‘"+employee.getName()+"’所接收！";;//发送内容
			}else{
				fkMsg="【工作任务提醒】您部门有工作任务还在执行中，任务名称："+task+",截止日期："+day+"！";//发送内容
			}
			
			Date sendTime= new Date();//当前日期
			String businessType="工作任务";
			String transmitMode="每周一7点";
			String sendType="短信、微信";
			//放入数据库
			messageXinxiService.setSaveMessageXinxi(personId, personName, account, sendTime, fkMsg, businessType, transmitMode, sendType);
			messageXinxiService.setSendMessage(account, fkMsg);//短信接口
			messageXinxiService.sendWxMsg(Constant.OFFICE_CORPID, Constant.OFFICE_PWD, account, fkMsg);//发送微信
						}
					}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

}

