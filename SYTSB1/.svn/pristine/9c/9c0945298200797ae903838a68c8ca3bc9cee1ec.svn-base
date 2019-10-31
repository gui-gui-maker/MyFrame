package com.lsts.qualitymanage.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.finance.bean.Clfbxd;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.log.service.SysLogService;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.QualityZssq;
import com.lsts.qualitymanage.bean.QualityZssqSub;
import com.lsts.qualitymanage.dao.QualityZssqDao;
import com.lsts.qualitymanage.dao.QualityZssqSubDao;
import com.lsts.report.bean.ReportPrint;
import com.lsts.report.bean.ReportPrintRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("Tjy2QualityZssq")
public class QualityZssqManager extends EntityManageImpl<QualityZssq,QualityZssqDao>{
    @Autowired
    QualityZssqDao qualityZssqDao;
    @Autowired
    QualityZssqSubDao qualityZssqSubDao;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
	private SysLogService logService;
    @Autowired
    private SysLogDao sysLogDao;
    
    public final static String ZL_SGCJ_WTJ = "WTJ";// 未提交
    public final static String ZL_SGCJ_YTJ = "YTJ"; // 已提交
	public final static String ZL_SGCJ_SHZ = "SHZ";// 审核中
	public final static String ZL_SGCJ_PASS = "PASS"; // 审核通过
	public final static String ZL_SGCJ_NO_PASS = "NO_PASS"; // 审核不通过
	public final static String ZL_SGCJ_NO_NEXT = "N"; // 未归还
	public final static String ZL_SGCJ_YES_NEXT = "Y"; // 已归还
	public final static String ZL_SGCJ_BF_NEXT = "BF"; // 部分归还
	public final static String ZL_SGCJ_confirm_NEXT = "C"; // 确认归还
	
	/*public void saveTask(QualityZssq qualityZssq, CurrentSessionUser user) {
		// TODO Auto-generated method stub
		
	}*/
	/**
	 * 生成修改单编号
	 * @throws Exception 
	 * */
	public synchronized void saveTask(QualityZssq qualityZssq,CurrentSessionUser user) throws Exception{
		List<QualityZssqSub> qualityZssqSubs=qualityZssq.getQualityZssqSubs();
		String useUnit="";
		String reportNumberId="";
		String reportNumber="";
		for(QualityZssqSub qualityZssqSub:qualityZssqSubs){
			useUnit+=qualityZssqSub.getComName()+",";
			reportNumberId+=qualityZssqSub.getReportFk()+",";
			reportNumber+=qualityZssqSub.getReportSn()+",";
		}
		useUnit=useUnit.trim();
		reportNumberId=reportNumberId.trim();
		reportNumber=reportNumber.trim();
		qualityZssq.setUseUnit(useUnit.substring(0, useUnit.length()-1));
		qualityZssq.setTotal(String.valueOf(qualityZssqSubs.size()));
		qualityZssq.setReportNumberId(reportNumberId.substring(0, reportNumberId.length()-1));
		qualityZssq.setReportNumber(reportNumber.substring(0, reportNumber.length()-1));
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==qualityZssq.getId() || "".equals(qualityZssq.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
			int nowYear = a.get(Calendar.YEAR);
			List<QualityZssq> fybxdlist = qualityZssqDao.getTaskAllot();
			if(fybxdlist==null || fybxdlist.size()==0) {
				docNum = nowYear+"-"+"0001";
			}else{
				int[] docNumArray = new int[fybxdlist.size()];
				for (int i=0;i<fybxdlist.size();i++) {
					//将编号去掉“-”，转换成int型存入数组
					if(fybxdlist.get(i).getFileNumber()!=null && !"".equals(fybxdlist.get(i).getFileNumber())){
						docNumArray[i] = Integer.parseInt(fybxdlist.get(i).getFileNumber().replaceAll("-", ""));
					}
				}
				int max = docNumArray[0];
				//获取数组中最大的值
				for (int i : docNumArray) {
					max = max>i?max:i;
				}
				String docNum1 = String.valueOf(max+1);
				if(nowYear>Integer.parseInt(docNum1.substring(0, 4))) {
					docNum = nowYear+"-"+"0001";
				}else{
					//编号加1后重新组
					docNum = docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
				}
			}
			qualityZssq.setFileNumber(docNum);
			qualityZssq.setCreateId(user.getId());
			qualityZssq.setCreateName(user.getName());
			qualityZssq.setCreateTime(new Date());
			qualityZssq.setStatus(ZL_SGCJ_WTJ);
		}
		qualityZssqDao.save(qualityZssq);
		
		String id=qualityZssq.getId();
		qualityZssqSubDao.deleteSub(id);
		for(QualityZssqSub qualityZssqSub:qualityZssqSubs){
			qualityZssqSub.setId(null);
			qualityZssqSub.setQualityZssFk(id);
			qualityZssqSub.setIsBack("0");//保存时初始化为未归还
			qualityZssqSubDao.save(qualityZssqSub);
		}
	}

	public HashMap<String, Object> getDetail(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			QualityZssq qualityZssq = qualityZssqDao.get(id);
			List<QualityZssqSub> list = qualityZssqSubDao.getQualityZssqSubs(id);
			if(list==null||list.isEmpty()){
				String reportNumber=qualityZssq.getReportNumber();
				if(reportNumber!=null&&reportNumber.length()>0){
					String[]arr=reportNumber.split(",");
					for(String str:arr){
						QualityZssqSub qualityZssqSub=new QualityZssqSub();
						qualityZssqSub.setComName(qualityZssq.getUseUnit());
						qualityZssqSub.setReportCount(new BigDecimal(1));
						qualityZssqSub.setReportSn(str);
						list.add(qualityZssqSub);
					}
				}else{
					
				}
			}
			
			
			map.put("data", qualityZssq);
			map.put("qualityZssqSubs", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}	
  	
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

	public void delete(String ids)throws Exception{
  	for(String id:ids.split(",")){
  		QualityZssq mtMP = qualityZssqDao.get(id);
  		qualityZssqDao.getHibernateTemplate().delete(mtMP);
  		qualityZssqSubDao.deleteSub(id);
  	}
  }
	/**
	 * 记录审核日志
	 */
	public void setCheckLog(HttpServletRequest request,String op_remark,String id,CurrentSessionUser user)throws Exception{
		List<QualityZssqSub> lists=qualityZssqSubDao.getQualityZssqSubs(id);
		for(QualityZssqSub qualityZssqSub:lists){
	  		//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
	  		logService.setLogs(qualityZssqSub.getReportFk() != null ? qualityZssqSub.getReportFk() : "此为手动填写的报告，无ID外键", 
	  				"无原始资料打印申请审核", 
	  				op_remark, 
	  				user != null ? user.getId() : "未获取到操作用户编号",
					user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
					request != null ? request.getRemoteAddr() : "");
	  	}
	  }
	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = sysLogDao.getBJLogs(id);
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("identifier", qualityZssqDao.get(id).getFileNumber());
		map.put("success", true);
		
		return map;
    }
}
