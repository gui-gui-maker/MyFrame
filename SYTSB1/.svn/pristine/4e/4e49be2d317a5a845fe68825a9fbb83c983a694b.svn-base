package com.lsts.qualitymanage.service;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.bpm.ext.support.FlowExtWorktaskParam;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.log.service.SysLogService;
import com.lsts.office.bean.MeetingNotes;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.TxwjspSealRegSug;
import com.lsts.qualitymanage.bean.TxwjspSealreg;
import com.lsts.qualitymanage.dao.TxwjspSealRegSugDao;
import com.lsts.qualitymanage.dao.TxwjspSealregDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("txwjspSealregManager")
public class TxwjspSealregManager extends EntityManageImpl<TxwjspSealreg,TxwjspSealregDao>{
    @Autowired
    TxwjspSealregDao txwjspSealregDao;
    @Autowired
    TxwjspSealRegSugDao txwjspSealRegSugDao;
	@Autowired
	private FlowExtManager flowExtManager;
	@Autowired
	private TxwjspSealRegSugManager txwjspSealRegSugManager;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private SysLogService logService;
	@Autowired
    private SysLogDao sysLogDao;
	
	 /**状态常量*/
    public final static String QUALITY_FLOW_WTJ = "1";// 未提交
    public final static String QUALITY_FLOW_BMDSH="2"; //部门负责人待审核
	public final static String QUALITY_FLOW_FWBDSH = "3";// 服务部待审核
	public final static String QUALITY_FLOW_FGLDDSH = "4"; // 分管领导待审核
	public final static String QUALITY_FLOW_GZRDSH = "5"; // 盖章人待审核
	public final static String QUALITY_FLOW_YWC = "6"; // 已完成
	public final static String QUALITY_FLOW_SHBTG = "7"; // 审核不通过
	public final static String QUALITY_FLOW_SHTG = "8"; // 审核通过
	public final static String QUALITY_FLOW_TH = "9"; // 退回
  //保存
	public synchronized void save(TxwjspSealreg txwjspSealreg,String userId,String userName,HttpServletRequest request) throws Exception{
		String id=request.getParameter("id");
		Date date=new Date();
		if(null==txwjspSealreg.getId() || "".equals(txwjspSealreg.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		List<TxwjspSealreg> txwjspSealreglist = txwjspSealregDao.getTaskAllot();
 		if(txwjspSealreglist==null || txwjspSealreglist.size()==0) {
 			docNum = "CTJC-001-B14-"+nowYear+"-"+"0001";
 		} else {
 			int[] docNumArray = new int[txwjspSealreglist.size()];
 			for (int i=0;i<txwjspSealreglist.size();i++) {
 				//将编号去掉“-”，转换成int型存入数组
 				if(txwjspSealreglist.get(i).getIdentifier()!=null && !"".equals(txwjspSealreglist.get(i).getIdentifier())){
 					String str =txwjspSealreglist.get(i).getIdentifier();
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
 	 			docNum = "CTJC-001-B14-"+nowYear+"-"+"0001";
 	 		}else{
	 			//编号加1后重新组
	 			docNum = "CTJC-001-B14-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
 	 		}
 		} 
 		txwjspSealreg.setIdentifier(docNum);
		
    	}
		txwjspSealreg.setCreaterid(userId);
		txwjspSealreg.setCreater(userName);
		txwjspSealreg.setCreaterDate(date);
		if(null==txwjspSealreg.getStatus() || "".equals(txwjspSealreg.getStatus())){
			txwjspSealreg.setStatus(QUALITY_FLOW_WTJ);
		}
		txwjspSealregDao.save(txwjspSealreg);
		if (!StringUtil.isEmpty(txwjspSealreg.getUploadIds())) {
			String[] files = txwjspSealreg.getUploadIds().replaceAll("/^,/", "").split(",");
			attachmentManager.setAttachmentBusinessId(files,txwjspSealreg.getId());
		}
	}
    /**
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		try {
			flowExtManager.startFlowProcess(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
  	

    /**
  	 * 审核
  	 * */
  	
  	public void doProcess(Map<String, Object> map)throws Exception{
  		flowExtManager.submitActivity(map);
      }
  	
  	/**
  	 * 退回
  	 * */
  	
  	public void doreturn(Map<String, Object> map)throws Exception{
  		flowExtManager.returnedActivity(map);
      }
  	
  	/**
  	 * 流程结束
  	 * */
  	public void stop(Map<String, Object> map)throws Exception{
  		flowExtManager.finishProcess(map);
      }
  	/**
  	 *审核不通过，流程结束
  	 * */
  	 public void saveRet(HttpServletRequest request,String id,String cxyy,CurrentSessionUser user,String typeCode,String areaFlag,String processId) throws Exception{
  		 String actId=txwjspSealregDao.getFlowId(id).toString();
  		 String activityId=Pattern.compile("\\b([\\w\\W])\\b").matcher(actId.toString()
  				 .substring(1,actId.toString().length()-1)).replaceAll("'$1'"); 
  		 
  		//撤销流程
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put(FlowExtWorktaskParam.SERVICE_ID, id);
			map.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
			map.put(FlowExtWorktaskParam.SERVICE_TITLE, "非用章范围需盖章登记记录表-"+user.getName());
			map.put(FlowExtWorktaskParam.SERVICE_TYPE, typeCode);
			map.put(FlowExtWorktaskParam.IS_CURRENT_USER_TASK, false);
			map.put(FlowExtWorktaskParam.PROCESS_ID, processId);
	 			//根据业务id查询出流程id
	 			
	 			if (StringUtil.isEmpty(id)) {
	 				return;
	 			} else {
	 				
	 				if (StringUtil.isNotEmpty(activityId)) {
	 					 
	 			  		TxwjspSealreg txwjspSealreg=txwjspSealregDao.get(id);
	 			  		Date date=new Date();
	 				    String userName=user.getName();
	 			 		//修改业务状态为“不通过”
	 				    TxwjspSealRegSug txwjspSealRegSug=new TxwjspSealRegSug();
	 			  		txwjspSealreg.setStatus(QUALITY_FLOW_SHBTG);
	 			  		if(areaFlag.equals("1")){
	 			  			flowExtManager.finishProcess(map);
	 				    	//保存业务表数据
	 				    	txwjspSealreg.setDeliveryHead(userName);
	 				    	txwjspSealreg.setDeliveryHeadDate(date);
	 				    	
	 				    	//保存审核意见、记录
	 				    	txwjspSealRegSug.setBusinessId(id);
	 				    	txwjspSealRegSug.setSpUserName(user.getName());
	 				    	txwjspSealRegSug.setSpName("送件部门负责人签字");
	 				    	txwjspSealRegSug.setSpUserId(user.getUserId());
	 				    	txwjspSealRegSug.setSpTime(date);
	 				    	txwjspSealRegSug.setSpLevel("非用章范围需盖章登记记录表-"+user.getName());
	 				    	txwjspSealRegSug.setStatus(QUALITY_FLOW_SHBTG);
	 				    	txwjspSealRegSug.setSpResult(QUALITY_FLOW_SHBTG);
 				    		txwjspSealRegSugManager.saves(txwjspSealRegSug);
 				    		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
 			  		  		logService.setLogs(id, 
 			  		  				"非用章范围需盖章申请审核", 
 			  		  				"送件部门负责人审核。审核结果：不通过", 
 			  		  				user != null ? user.getId() : "未获取到操作用户编号",
 			  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
 			  						request != null ? request.getRemoteAddr() : "");
	 					}else if(areaFlag.equals("2")){
	 						flowExtManager.finishProcess(map);
	 							//保存业务表数据
	 							txwjspSealreg.setServiceHead(userName);
	 							txwjspSealreg.setServiceHeadDate(date);
	 							txwjspSealregDao.save(txwjspSealreg);
	 							//保存审核意见、记录
	 							txwjspSealRegSug.setBusinessId(id);
	 							txwjspSealRegSug.setSpUserName(user.getName());
	 				    	txwjspSealRegSug.setSpName("服务部负责人签字");
	 				    	txwjspSealRegSug.setSpUserId(user.getUserId());
	 				    	txwjspSealRegSug.setSpTime(date);
	 				    	txwjspSealRegSug.setSpLevel("非用章范围需盖章登记记录表-"+user.getName());
	 				    	txwjspSealRegSug.setStatus(QUALITY_FLOW_SHBTG);
	 				    	txwjspSealRegSug.setSpResult(QUALITY_FLOW_SHBTG);
	 				    	txwjspSealRegSugManager.saves(txwjspSealRegSug);
	 				    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	 		  		  		logService.setLogs(id, 
	 		  		  				"非用章范围需盖章申请审核", 
	 		  		  				"服务部负责人审核。审核结果：不通过", 
	 		  		  				user != null ? user.getId() : "未获取到操作用户编号",
	 		  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	 		  						request != null ? request.getRemoteAddr() : "");
	 						}else if(areaFlag.equals("3")){
	 						flowExtManager.finishProcess(map);
	 							//保存审核意见、记录
	 				    	txwjspSealRegSug.setBusinessId(id);
	 				    	txwjspSealRegSug.setSpUserName(user.getName());
	 				    	txwjspSealRegSug.setSpName("业务部门分管院领导签字");
	 				    	txwjspSealRegSug.setSpUserId(user.getUserId());
	 				    	txwjspSealRegSug.setSpTime(date);
	 				    	txwjspSealRegSug.setSpLevel("非用章范围需盖章登记记录表-"+user.getName());
	 				    	txwjspSealRegSug.setStatus(QUALITY_FLOW_SHBTG);
	 				    	txwjspSealRegSug.setSpResult(QUALITY_FLOW_SHBTG);
	 				    	txwjspSealRegSugManager.saves(txwjspSealRegSug);
	 							//保存业务表数据
	 							txwjspSealreg.setOpertionalManagement(userName);
	 							txwjspSealreg.setOpertionalManagementDate(date);
	 							txwjspSealregDao.save(txwjspSealreg);
	 							//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	 			  		  		logService.setLogs(id, 
	 			  		  				"非用章范围需盖章申请审核", 
	 			  		  				"业务部门分管院领导审核。审核结果：不通过", 
	 			  		  				user != null ? user.getId() : "未获取到操作用户编号",
	 			  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	 			  						request != null ? request.getRemoteAddr() : "");
	 						}else if(areaFlag.equals("4")){
	 							//保存审核意见、记录
	 						flowExtManager.finishProcess(map);
	 				    	txwjspSealRegSug.setBusinessId(id);
	 				    	txwjspSealRegSug.setSpUserName(user.getName());
	 				    	txwjspSealRegSug.setSpName("盖章人 签字");
	 				    	txwjspSealRegSug.setSpUserId(user.getUserId());
	 				    	txwjspSealRegSug.setSpTime(date);
	 				    	txwjspSealRegSug.setSpLevel("非用章范围需盖章登记记录表-"+user.getName());
	 				    	txwjspSealRegSug.setStatus(QUALITY_FLOW_SHBTG);
	 				    	txwjspSealRegSug.setSpResult(QUALITY_FLOW_SHBTG);
	 					    txwjspSealRegSugManager.saves(txwjspSealRegSug);
	 					    
	 							txwjspSealreg.setSealer(userName);
	 							txwjspSealreg.setSealDate(date);
	 							txwjspSealregDao.save(txwjspSealreg);
	 							//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	 			  		  		logService.setLogs(id, 
	 			  		  				"非用章范围需盖章申请审核", 
	 			  		  				"盖章人签字。操作结果：不通过", 
	 			  		  				user != null ? user.getId() : "未获取到操作用户编号",
	 			  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
	 			  						request != null ? request.getRemoteAddr() : "");
	 						}
	 					
	 				}else {
	 					JsonWrapper.failureWrapper("流程ID为空！");
	 					
	 				}
	 				
	 				 JsonWrapper.successWrapper(id);
	 			 }
	 			
  		
 		
 		 
 		
   
     }
 	public HashMap<String, Object> getTxwjspSealregInfo(String Id) throws Exception{
		   HashMap<String, Object> notesInfo = new HashMap<String, Object>();
		   TxwjspSealreg txwjspSealreg=txwjspSealregDao.get(Id);
			notesInfo.put("success", true);
			if(txwjspSealreg!=null){
				notesInfo.put("data", txwjspSealreg);
			}
			return notesInfo;
	}
 	
  public void saves(TxwjspSealreg txwjspSealreg,TxwjspSealRegSug txwjspSealRegSug){
	  txwjspSealregDao.save(txwjspSealreg);
	  txwjspSealRegSugDao.save(txwjspSealRegSug);
  }
	  /**
	   * 环节退回
	   * 
	   * @param activityId
	   *            审批环节ID
	   * @throws Exception
	   */
	  public void turnback(HttpServletRequest request,CurrentSessionUser user,String id) throws Exception {
		  /*String actId=txwjspSealregDao.getFlowId(id).toString();
		  String activityId=Pattern.compile("\\b([\\w\\W])\\b").matcher(actId.toString()
					 .substring(1,actId.toString().length()-1)).replaceAll("'$1'"); */
		  TxwjspSealreg txwjspSealreg=txwjspSealregDao.get(id);
		  TxwjspSealRegSug txwjspSealRegSug=new TxwjspSealRegSug();
		  String activityId = request.getParameter("activityId");
		  String seal = request.getParameter("seal");
		  String areaFlag = request.getParameter("areaFlag");
		  Map<String, Object> paramMap = new HashMap<String, Object>();
	
	      // ------------- 必要参数 -----------------------
	      paramMap.put(FlowExtWorktaskParam.ACTIVITY_ID, activityId);
	       
	      // ------------- 可选参数 -----------------------
	      // paramMap.put(FlowExtWorktaskParam.DATA_BUS, "{\"testDatabusKey\":\"testdatabusvalue\}");
	      // paramMap.put(FlowExtWorktaskParam.WORKTASK_PARAMETER,JSONObject.fromString("{\"testParam1\":\"returnparam\",\"returnParam2\":\"returnparamValue\"}"));
	
	      // 调用流程引擎接口方法执行退回
	      try {
			this.flowExtManager.returnedActivity(paramMap);
			if(txwjspSealreg.getStatus().equals("2")){
				txwjspSealreg.setDeliveryHead(null);
		    	txwjspSealreg.setDeliveryHeadDate(null);
				txwjspSealreg.setStatus("1");
				txwjspSealregDao.save(txwjspSealreg);
				txwjspSealRegSug.setBusinessId(id);
		    	txwjspSealRegSug.setSpUserName(user.getName());
		    	txwjspSealRegSug.setSpName("送件部门 负责人签字");
		    	txwjspSealRegSug.setSpUserId(user.getUserId());
		    	txwjspSealRegSug.setSpTime(new Date());
		    	txwjspSealRegSug.setSeal(seal);//印章id
		    	txwjspSealRegSug.setSpLevel(areaFlag);
		    	txwjspSealRegSug.setStatus(this.QUALITY_FLOW_WTJ);
		    	txwjspSealRegSug.setSpResult(this.QUALITY_FLOW_TH);
		    	txwjspSealRegSugDao.save(txwjspSealRegSug);
		    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"非用章范围需盖章申请审核", 
  		  				"送件部门负责人审核。审核结果：退回至申请人，重新提交", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
			}else if(txwjspSealreg.getStatus().equals("3")){
				txwjspSealreg.setServiceHead(null);
				txwjspSealreg.setServiceHeadDate(null);
				txwjspSealreg.setStatus("2");
				txwjspSealregDao.save(txwjspSealreg);
				txwjspSealRegSug.setBusinessId(id);
		    	txwjspSealRegSug.setSpUserName(user.getName());
		    	txwjspSealRegSug.setSpName("服务部 负责人签字");
		    	txwjspSealRegSug.setSpUserId(user.getUserId());
		    	txwjspSealRegSug.setSpTime(new Date());
		    	txwjspSealRegSug.setSeal(seal);//印章id
		    	txwjspSealRegSug.setSpLevel(areaFlag);
		    	txwjspSealRegSug.setStatus(this.QUALITY_FLOW_BMDSH);
		    	txwjspSealRegSug.setSpResult(this.QUALITY_FLOW_TH);
		    	txwjspSealRegSugDao.save(txwjspSealRegSug);
		    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"非用章范围需盖章申请审核", 
  		  				"服务部负责人审核。审核结果：退回至送件部门负责人审核", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
			}else if(txwjspSealreg.getStatus().equals("4")){
				txwjspSealreg.setOpertionalManagement(null);
				txwjspSealreg.setOpertionalManagementDate(null);
				txwjspSealreg.setStatus("3");
				txwjspSealregDao.save(txwjspSealreg);
				txwjspSealRegSug.setBusinessId(id);
		    	txwjspSealRegSug.setSpUserName(user.getName());
		    	txwjspSealRegSug.setSpName("业务部门 分管院领导签字");
		    	txwjspSealRegSug.setSpUserId(user.getUserId());
		    	txwjspSealRegSug.setSpTime(new Date());
		    	txwjspSealRegSug.setSeal(seal);//印章id
		    	txwjspSealRegSug.setSpLevel(areaFlag);
		    	txwjspSealRegSug.setStatus(this.QUALITY_FLOW_FWBDSH);
		    	txwjspSealRegSug.setSpResult(this.QUALITY_FLOW_TH);
		    	txwjspSealRegSugDao.save(txwjspSealRegSug);
		    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"非用章范围需盖章申请审核", 
  		  				"业务部门分管院领导审核。审核结果：退回至服务部负责人审核", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
			}else if(txwjspSealreg.getStatus().equals("5")){
				txwjspSealreg.setSealer(null);
				txwjspSealreg.setSealDate(null);
				txwjspSealreg.setStatus("4");
				txwjspSealregDao.save(txwjspSealreg);
				
				txwjspSealRegSug.setBusinessId(id);
		    	txwjspSealRegSug.setSpUserName(user.getName());
		    	txwjspSealRegSug.setSpName("盖章人 签字");
		    	txwjspSealRegSug.setSpUserId(user.getUserId());
		    	txwjspSealRegSug.setSpTime(new Date());
		    	txwjspSealRegSug.setSeal(seal);//印章id
		    	txwjspSealRegSug.setSpLevel(areaFlag);
		    	txwjspSealRegSug.setStatus(this.QUALITY_FLOW_FGLDDSH);
		    	txwjspSealRegSug.setSpResult(this.QUALITY_FLOW_TH);
		    	txwjspSealRegSugDao.save(txwjspSealRegSug);
		    	//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
  		  		logService.setLogs(id, 
  		  				"非用章范围需盖章申请退回", 
  		  				"盖章人签字。操作结果：退回至业务部门分管院领导审核", 
  		  				user != null ? user.getId() : "未获取到操作用户编号",
  						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
  						request != null ? request.getRemoteAddr() : "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		List<SysLog> list = sysLogDao.getBJLogs(id);
  		map.put("flowStep", list);
  		map.put("size", list.size());
  		map.put("identifier", txwjspSealregDao.get(id).getIdentifier());
  		map.put("success", true);
  		
  		return map;
      }
}
