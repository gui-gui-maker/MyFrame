package com.lsts.report.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.common.service.CodeTablesService;
import com.lsts.common.service.MessageXinxiService;
import com.lsts.constant.Constant;
import com.lsts.humanresources.bean.WorkExperience;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.log.service.SysLogService;
import com.lsts.report.bean.ReportYjsz;
import com.lsts.report.bean.SysOrg;
import com.lsts.report.dao.ReportDrawDao;
import com.lsts.report.dao.ReportYjszDao;


@Service("reportYjszManager")
public class ReportYjszManager extends EntityManageImpl<ReportYjsz,ReportYjszDao>{
    @Autowired
    ReportYjszDao reportYjszDao;
    @Autowired
	private MessageXinxiService messageXinxiService;
    @Autowired
	private CodeTablesService codeTablesService;
    @Autowired
	private	SysLogService logService;
    @Autowired
	private ReportDrawDao reportDrawDao;
    @Autowired
   	private MessageService messageservice;
    /**状态常量*/
	public final static String YJ_QFDDY  = "QFDDY"; // 签发到打印
	public final static String YJ_QCDQF  = "QCDQF"; // 起草到签发
	public final static String YJ_QCDDY  = "QCDDY"; // 起草到打印
	
	public final static String YJ_1  = "1"; // 科室报检
	public final static String YJ_2  = "2"; // 报告录入
	public final static String YJ_3  = "3"; // 报告送审
	public final static String YJ_4  = "4"; // 报告审核
	public final static String YJ_5  = "5"; // 报告签发
	public final static String YJ_6  = "6"; // 报告打印
	public final static String YJ_7  = "7"; // 报告领取
	public final static String YJ_8  = "8"; // 报告归档
	public final static String YJ_QY  = "1"; // 启用
	public final static String YJ_TZ  = "2"; // 停用
	public final static String YJ_WQY  = "3"; // 未启用

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 规则
	 * */
	public  void reportYj() throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		List<ReportYjsz> reportYjszList = reportYjszDao.getReportYjsz();
		if(reportYjszList!=null && reportYjszList.size()>0 ){
			//遍历预警规则
			for (int i=0;i<reportYjszList.size();i++){
				//启用&&推送方式为是
				if(reportYjszList.get(i).getState().equals(YJ_QY) && reportYjszList.get(i).getDuanxinPush().equals("1")){
					//取出预警规则
					String startflow = reportYjszList.get(i).getFlow();
					String endflow = reportYjszList.get(i).getFlows();
					int sf = Integer.parseInt(startflow);
					int ef = Integer.parseInt(endflow);
					String flowName="'";
					for (int f=sf;f<ef;f++) {
						flowName = flowName + codeTablesService.getValueByCode("TJY2_BGYJ",String.valueOf(f))+"','";
					}
					flowName = flowName.substring(0, flowName.length()-2);
					//获得预警天数
					int yjDay = Integer.parseInt(reportYjszList.get(i).getTianshu());
					int tempDay=86400000*yjDay;
					
					//取出机构信息
		    		List<SysOrg> sysOrgList = getSysOrg();
		    		if(sysOrgList!=null && sysOrgList.size()>0){
		    			//遍历所有部门
		    			for(int k=0;k<sysOrgList.size();k++){
		    				
		    				List<InspectionInfo> inspectionInfoList = this.getInspectionInfo(sysOrgList.get(k).getOrgid(),flowName);
							InspectionInfo inspectionInfo = new InspectionInfo();
							if(inspectionInfoList!=null && inspectionInfoList.size()>0){
								int reportNum = 0;
								//遍历该部门下所有报告书
								for(int j=0;j<inspectionInfoList.size();j++){
									inspectionInfo = inspectionInfoList.get(j);
										//起草到签发大于预警天数，发出预警推送
									if(inspectionInfo.getEnter_time()!=null && endflow.equals(YJ_2)){
										if(((new Date()).getTime()-inspectionInfo.getEnter_time().getTime())>tempDay){
											reportNum = reportNum+1;
										}
									}else if(inspectionInfo.getIssue_Date()!=null && endflow.equals(YJ_5)){
										//签发
										if(((new Date()).getTime()-inspectionInfo.getIssue_Date().getTime())>tempDay){
											reportNum = reportNum+1;
										}
									}else if(inspectionInfo.getExamine_Date()!=null && endflow.equals(YJ_4)){
										//审核
										if(((new Date()).getTime()-inspectionInfo.getExamine_Date().getTime())>tempDay){
											reportNum = reportNum+1;
										}
									}else if(inspectionInfo.getPrint_time()!=null && endflow.equals(YJ_6)){
										//打印
										if(((new Date()).getTime()-inspectionInfo.getPrint_time().getTime())>tempDay){
											reportNum = reportNum+1;
										}
									}
									
								}
								if(reportNum>0){
									//获取改机构的负责人信息
									@SuppressWarnings("rawtypes")
									List userList1 = reportYjszDao.getUser(sysOrgList.get(k).getOrgid());
									
									if (userList1 != null && userList1.size() > 0) {
								    	for (int u = 0; u < userList1.size(); u++) {
								    		Object user1[] = null;
								    		user1 = (Object[]) userList1.get(u);
								    		String msg = "【报告书内控】您部门还有 "+reportNum+" 份报告超过质量内控期限("+codeTablesService.getValueByCode("TJY2_BGYJ",String.valueOf(startflow))+"-"+codeTablesService.getValueByCode("TJY2_BGYJ",String.valueOf(endflow))+")！";
											//发送短信
								    		HttpServletRequest request = null;
								    		messageservice.sendMoMsg(request,user1[0].toString(),msg,user1[2].toString() );
								    		messageservice.sendWxMsg(request,user1[0].toString(),Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, msg,user1[2].toString());
								    		//保存日志
											messageXinxiService.setSaveMessageXinxi(user1[0].toString(), user1[1].toString(), user1[2].toString(), new Date(), msg, "内控","短信","实时发送");
											//日志
//											logService.setLogs(null, "【报告书内控】", "发送短信记录", 
//													user.getId(), 
//													user.getName(), 
//													new Date(), request.getRemoteAddr());
								    	}
									}
								}
							}
		    			}
		    		}
				}
				
			}
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public List<InspectionInfo> getInspectionInfo(String sysOrgId,String flowName) throws ParseException{
		List list=reportYjszDao.queryInspectionInfo(sysOrgId,flowName);
	    List<InspectionInfo> inspectionInfoList = new ArrayList<InspectionInfo>();
	    if (list != null && list.size() > 0) {
	    	for (int u = 0; u < list.size(); u++) {
	    		Object report[] = null;
	    		report = (Object[]) list.get(u);
	    		InspectionInfo inspectionInfo = new InspectionInfo();
	    		if(report[0] != null){
	    			inspectionInfo.setFlow_note_name(report[0].toString());		  //流程步骤名
	    		}
	    		if(report[1] != null){
	    			inspectionInfo.setCheck_unit_id(report[1].toString());		  //部门id
	    		}
	    		if(report[2] != null){
	    			inspectionInfo.setIssue_Date(sdf.parse(report[2].toString()));//签发时间
	    		}
	    		if(report[3] != null){
	    			inspectionInfo.setEnter_time(sdf.parse(report[3].toString()));//录入时间
	    		}
	    		if(report[4] != null){
	    			inspectionInfo.setExamine_Date(sdf.parse(report[4].toString()));//审核时间
	    		}
	    		if(report[5] != null){
	    			inspectionInfo.setPrint_time(sdf.parse(report[5].toString()));//打印时间
	    		}
		    	inspectionInfoList.add(inspectionInfo);
			}
	    }
	    return inspectionInfoList;
	}
	/**
	 * 获取机构信息
	 * @return List<SysOrg>
	 */
	@SuppressWarnings("rawtypes")
	public List<SysOrg> getSysOrg(){
		List list=reportYjszDao.getSysOrg();
		List<SysOrg> sysOrgList = new ArrayList<SysOrg>();
		if (list != null && list.size() > 0) {
	    	for (int u = 0; u < list.size(); u++) {
	    		Object sysOrg[] = null;
	    		SysOrg sysOrg1 = new SysOrg();
	    		sysOrg = (Object[]) list.get(u);
	    		sysOrg1.setOrgid(sysOrg[0].toString());
	    		sysOrg1.setOrgName(sysOrg[1].toString());
	    		sysOrgList.add(sysOrg1);
	    	}
		}
		return sysOrgList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportYjsz> getByEmpId(){
    	List<ReportYjsz> list =new ArrayList<ReportYjsz>();
    	String hql="from ReportYjsz";
    	list=reportYjszDao.createQuery(hql).list();
    	return list;
    }
}
