package com.lsts.archives.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesBorrowSub;
import com.lsts.archives.dao.ArchivesBorrowDao;
import com.lsts.archives.dao.ArchivesBorrowSubDao;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.dao.SysLogDao;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualityUpdateFile;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.QualityZssqSub;
import com.lsts.qualitymanage.service.QualityUpdateFileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("archivesBorrow")
public class ArchivesBorrowManager extends EntityManageImpl<ArchivesBorrow,ArchivesBorrowDao>{
    @Autowired
    ArchivesBorrowDao archivesBorrowDao;
    
    
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
	private SysLogService logService;
    @Autowired
    ArchivesBorrowSubDao archivesBorrowSubDao;
    @Autowired
    InspectionInfoDao inspectionInfoDao;
    /**状态常量*/
    public final static String DA_JYSQ_WTJ = "WTJ";// 未提交
    public final static String DA_JYSQ_YTJ = "YTJ"; // 已提交
	public final static String DA_JYSQ_SHZ = "SHZ";// 审核中
	public final static String DA_JYSQ_PASS = "PASS"; // 审核通过
	public final static String DA_JYSQ_NO_PASS = "NO_PASS"; // 审核不通过
	public final static String DA_JYSQ_JYQX = "JYTG"; // 
	public final static String DA_DYSQ = "DAYIN"; // 已打印
	public final static String DA_TUIH = "TUIH"; // 
	
	
	public final static String DA_JYLQ = "LQ"; // 已领取
	public final static String DA_JYGH = "GH"; // 已归还
	public final static String DA_JYBFGH = "BFGH"; // 部分归还


	
    /**	
  	 * 提交
  	 * */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
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
  	 * 生成修改单编号
  	 * @throws Exception 
  	 * */
  	public void saveTask(ArchivesBorrow archivesBorrow,CurrentSessionUser user) throws Exception{
  		List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrow.getArchivesBorrowSubs();
  		String reportNumberId="";
		String reportNumber="";
		for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
			reportNumberId+=archivesBorrowSub.getFkReportId()+",";
			reportNumber+=archivesBorrowSub.getReportSn()+",";
		}
		reportNumberId=reportNumberId.trim();
		reportNumber=reportNumber.trim();
		archivesBorrow.setReportNumberId(reportNumberId.substring(0, reportNumberId.length()-1));
		archivesBorrow.setShares(String.valueOf(archivesBorrowSubs.size()));
		archivesBorrow.setReportNumber(reportNumber.substring(0, reportNumber.length()-1));
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==archivesBorrow.getId() || "".equals(archivesBorrow.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
    		int nowYear = a.get(Calendar.YEAR);
    		List<ArchivesBorrow> archivesBorrowlist = archivesBorrowDao.getTaskAllot();
    		if(archivesBorrowlist==null || archivesBorrowlist.size()==0) {
    			docNum = "CTJC-001-B11-"+nowYear+"-"+"0001";
    		} else {
    			int[] docNumArray = new int[archivesBorrowlist.size()];
    			for (int i=0;i<archivesBorrowlist.size();i++) {
    				//将编号去掉“-”，转换成int型存入数组
     				if(archivesBorrowlist.get(i).getIdentifier()!=null && !"".equals(archivesBorrowlist.get(i).getIdentifier())){
						String str =archivesBorrowlist.get(i).getIdentifier();
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
     	 			docNum = "CTJC-001-B11-"+nowYear+"-"+"0001";
     	 		}else{
    	 			//编号加1后重新组
    	 			docNum = "CTJC-001-B11-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
     	 		}
    		}
    		archivesBorrow.setIdentifier(docNum);
    		archivesBorrow.setRegistrant(user.getName());
    		archivesBorrow.setRegistrantTime(new Date());
    		archivesBorrow.setStatus(ArchivesBorrowManager.DA_JYSQ_WTJ);
		}
//		qualityUpdateFile.setRegistrantId(user.getId());
   		//改变状态
   		String name =archivesBorrow.getProposer();
   		
//   		String a=archivesBorrowDao.getname(name).toString();
//		String ids = Pattern.compile("\\b([\\w\\W])\\b").matcher(a.toString()
//    			.substring(1,a.toString().length()-1)).replaceAll("'$1'");
//		archivesBorrow.setProposerId(ids);
   		if(archivesBorrow.getApplyType().equals("3")){
   			archivesBorrow.setFatalism("");
   		}
		archivesBorrowDao.save(archivesBorrow);
		String id=archivesBorrow.getId();
		archivesBorrowSubDao.deleteSub(id);
		try {
			for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
				archivesBorrowSub.setId(null);
				archivesBorrowSub.setFkArchivesBorrowId(id);
				archivesBorrowSub.setIsBack("0");//保存时初始化为未归还
				archivesBorrowSubDao.save(archivesBorrowSub);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  	
  	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			ArchivesBorrow archivesBorrow = archivesBorrowDao.get(id);
			List<ArchivesBorrowSub> list = archivesBorrowSubDao.getArchivesBorrowSubs(id);
			if(list==null||list.isEmpty()){
				String reportNumberId=archivesBorrow.getReportNumberId();
				String reportNumber=archivesBorrow.getReportNumber();
				ArchivesBorrowSub archivesBorrowSub=new ArchivesBorrowSub();
				archivesBorrowSub.setFkReportId(reportNumberId);
				archivesBorrowSub.setReportSn(reportNumber);
				list.add(archivesBorrowSub);
			}
			map.put("data", archivesBorrow);
			map.put("archivesBorrowSubs", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}
  	/**
	 * 记录审核日志
	 */
	public void setCheckLog(HttpServletRequest request,String op_remark,String id,CurrentSessionUser user)throws Exception{
		ArchivesBorrow archivesBorrow = archivesBorrowDao.get(id);
		List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrowSubDao.getArchivesBorrowSubs(id);
		if(archivesBorrowSubs!=null&&archivesBorrowSubs.size()>0){
			for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs){
				//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
		  		logService.setLogs(StringUtil.isNotEmpty(archivesBorrowSub.getFkReportId()) ? archivesBorrowSub.getFkReportId() : "未获取到报告ID", 
		  				"档案借阅申请审核", 
		  				op_remark, 
		  				user != null ? user.getId() : "未获取到操作用户编号",
						user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
						request != null ? request.getRemoteAddr() : "");
			}
		}else{
			if(StringUtil.isNotEmpty(archivesBorrow.getReportNumberId())){
				String[] reportNumberIds=archivesBorrow.getReportNumberId().split(",");
				for(String reportNumberId:reportNumberIds){
					if(StringUtil.isNotEmpty(reportNumberId)){
						//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
				  		logService.setLogs(reportNumberId, 
				  				"档案借阅申请审核", 
				  				op_remark, 
				  				user != null ? user.getId() : "未获取到操作用户编号",
								user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
								request != null ? request.getRemoteAddr() : "");
					}
			  	}
			}
		}
	  }
	/**
	 * 根据业务i删除数据
	 * @param ids
	 * @throws Exception
	 */
	public void delete(String ids)throws Exception{
	  	for(String id:ids.split(",")){
	  		ArchivesBorrow mtMP = archivesBorrowDao.get(id);
	  		archivesBorrowDao.getHibernateTemplate().delete(mtMP);
	  		archivesBorrowSubDao.deleteSub(id);
	  	}
	}
	/**
	 * 验证填写的档案借阅手工报告编号是否全为手工报告
	 * @param request
	 * @param archivesBorrow
	 * @return
	 */
	public String checkReportIsSg(HttpServletRequest request,ArchivesBorrow archivesBorrow) {
		String report_sns="";
		if(archivesBorrow!=null) {
			List<ArchivesBorrowSub> archivesBorrowSubs=archivesBorrow.getArchivesBorrowSubs();
			for(ArchivesBorrowSub archivesBorrowSub:archivesBorrowSubs) {
				String report_sn = archivesBorrowSub.getReportSn();
				if(inspectionInfoDao.getInfosByReportSn(report_sn)!=null) {
					report_sns = StringUtil.isNotEmpty(report_sns)?(report_sns+","+report_sn):report_sn;
				}
			}
		}
		return report_sns;
	}
}
