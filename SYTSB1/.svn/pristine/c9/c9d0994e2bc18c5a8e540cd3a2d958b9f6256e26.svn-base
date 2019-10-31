package com.lsts.qualitymanage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.core.service.ActivityManager;
import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.log.service.SysLogService;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.qualitymanage.bean.QualitySgcjjybg;
import com.lsts.qualitymanage.bean.SgcjjybgNum;
import com.lsts.qualitymanage.dao.QualitySgcjjybgDao;
import com.lsts.qualitymanage.dao.SgcjjybgNumDao;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("QualitySgcjjybg")
public class QualitySgcjjybgManager extends EntityManageImpl<QualitySgcjjybg,QualitySgcjjybgDao>{
    @Autowired
    QualitySgcjjybgDao qualitySgcjjybgDao;
    @Autowired
	private ActivityManager activityManager;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
    private SgcjjybgNumDao sgcjjybgNumDao;
    @Autowired
	private SysLogService logService;
    @Autowired
    private SysLogDao sysLogDao;
    
    /**状态常量,手工出具检验报告/证书审批表*/
    public final static String ZL_SGCJ_WTJ = "WTJ";// 未提交
    public final static String ZL_SGCJ_YTJ = "YTJ"; // 已提交
	public final static String ZL_SGCJ_SHZ = "SHZ";// 审核中
	public final static String ZL_SGCJ_PASS = "PASS"; // 审核通过
	public final static String ZL_SGCJ_NO_PASS = "NO_PASS"; // 审核不通过
	public final static String ZL_SGCJ_CANCEL = "CANCEL"; // 作废
	
	 /**
  	 * 提交
  	 * */
  	
  	public Map<String, Object> doStartPress(Map<String, Object> map)throws Exception{
  		return flowExtManager.startFlowProcess(map);
    }
  	

    /**
  	 * 审核
  	 * */
  	
  	public Map<String, Object> doProcess(Map<String, Object> map)throws Exception{
  		return flowExtManager.submitActivity(map);
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
  	 * 获得开始和结束编号
  	 * */
  	public synchronized List setbgbh(String id,String test_coding,String njts){
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		String docNum = "";
  		Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		String code_year = test_coding+nowYear;
 		List<SgcjjybgNum> sgcjjybgNumlist = sgcjjybgNumDao.getFwbjhbg_num(test_coding,code_year);
 		if(sgcjjybgNumlist==null || sgcjjybgNumlist.size()==0) {
 			docNum = test_coding+nowYear+"-"+"0001";
 		}else {
	 		int[] docNumArray = new int[sgcjjybgNumlist.size()];
	 		for (int i=0;i<sgcjjybgNumlist.size();i++) {
 				if(sgcjjybgNumlist.get(i).getFwbjhbgNum()!=null && !"".equals(sgcjjybgNumlist.get(i).getFwbjhbgNum())){
 					String str =sgcjjybgNumlist.get(i).getFwbjhbgNum();
    				//将编号去掉test_coding、年份和-，转换成int型存入数组
    				docNumArray[i] = Integer.parseInt(str.substring(code_year.length()+1,str.length()));
 				}

	 		}
	 		int max = docNumArray[0];
 			//获取数组中最大的值
 			for (int i : docNumArray) {
 				max = max>i?max:i;
 			}
 			String docNum1 = String.valueOf(max+1);
 			int len = docNum1.length();
 			if(len>4) {
 				docNum = code_year+"-"+docNum1;
 			}else {
 				for(int i=0;i<4-len;i++) {
 					docNum1="0"+docNum1;
 				}
 				docNum = code_year+"-"+docNum1;
 			}
 			
 		}
		int a1=Integer.parseInt(docNum.substring(code_year.length()+1,docNum.length()));
		String b=String.valueOf(Integer.parseInt(njts)+a1-1);
		String c="";
		String bgbh;
		int len1 = b.length();
		if(len1>4) {
			c = code_year+"-"+b;
		}else {
			for(int i=0;i<4-len1;i++) {
				b="0"+b;
			}
			c = code_year+"-"+b;
		}
		if(njts.equals("1")){
			 bgbh=docNum;
		}else{
			 bgbh=docNum+"~"+c;
		}
 		List list=new ArrayList();
 		list.add(bgbh);
 		return list;
  	}

  	/**
	 * 开始编号
	 * */
  	public List getbgbh(String id,String test_coding,String njts){
  		String docNum = "";
  		Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		String code_year = test_coding+nowYear;
 		List<SgcjjybgNum> sgcjjybgNumlist = sgcjjybgNumDao.getFwbjhbg_num(test_coding,code_year);
 		if(sgcjjybgNumlist==null || sgcjjybgNumlist.size()==0) {
 			docNum = nowYear+"-"+"0001";
 		}else {
	 		int[] docNumArray = new int[sgcjjybgNumlist.size()];
	 		for (int i=0;i<sgcjjybgNumlist.size();i++) {
 				if(sgcjjybgNumlist.get(i).getFwbjhbgNum()!=null && !"".equals(sgcjjybgNumlist.get(i).getFwbjhbgNum())){
 					String str =sgcjjybgNumlist.get(i).getFwbjhbgNum();
    				//将编号去掉“-”，转换成int型存入数组
    				docNumArray[i] = Integer.parseInt(str.substring(str.length()-9,str.length()).replaceAll("-", ""));
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
 		List list=new ArrayList();
 		list.add(docNum);
 		return list;
  	}

    /**
	 * 生成修改单编号
     * @throws Exception 
	 * */
	public void saveSgcjjybg(HttpServletRequest request,QualitySgcjjybg qualitySgcjjybg,CurrentSessionUser user) throws Exception{
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==qualitySgcjjybg.getId() || "".equals(qualitySgcjjybg.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
	 		int nowYear = a.get(Calendar.YEAR);
	 		List<QualitySgcjjybg> qualitySgcjjybglist = qualitySgcjjybgDao.getTaskAllot();
	 		if(qualitySgcjjybglist==null || qualitySgcjjybglist.size()==0) {
	 			docNum = "CTJC-001-B14-"+nowYear+"-"+"0001";
	 		} else {
	 			int[] docNumArray = new int[qualitySgcjjybglist.size()];
	 			for (int i=0;i<qualitySgcjjybglist.size();i++) {
	 				//将编号去掉“-”，转换成int型存入数组
	 				if(qualitySgcjjybglist.get(i).getIdentifier()!=null && !"".equals(qualitySgcjjybglist.get(i).getIdentifier())){
	 					String str =qualitySgcjjybglist.get(i).getIdentifier();
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
	 		qualitySgcjjybg.setIdentifier(docNum);
	 		qualitySgcjjybg.setStatus("WTJ");
	 		//获取当前登录人姓名
	 		qualitySgcjjybg.setRegistrant(user.getName());
	 		qualitySgcjjybg.setRegistrantId(user.getId());
	 		qualitySgcjjybg.setRegistrantDate(new Date());
		}else {
			//若是审核不通过进行修改则状态变为审核中
	 		if(qualitySgcjjybg.getStatus().equals(QualitySgcjjybgManager.ZL_SGCJ_NO_PASS)) {
	 			//业务编号、操作动作、操作描述、操作用户编号、操作用户姓名、操作时间、操作IP
			  		logService.setLogs(qualitySgcjjybg.getId(), 
			  				"修改手工出具检验报告/证书审批表", 
			  				"修改手工出具检验报告/证书审批表并提交审核至上次审核不通过环节。操作结果：审核中", 
			  				user != null ? user.getId() : "未获取到操作用户编号",
							user != null ? user.getName() : "未获取到操作用户姓名", new Date(),
							request != null ? request.getRemoteAddr() : "");
	 			qualitySgcjjybg.setStatus(QualitySgcjjybgManager.ZL_SGCJ_SHZ);
	 		}
		}
		qualitySgcjjybgDao.save(qualitySgcjjybg);
	}
	
	/**
	 * 删除
	 * */
  	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		QualitySgcjjybg mtMP = qualitySgcjjybgDao.get(id);
      		qualitySgcjjybgDao.getHibernateTemplate().delete(mtMP);
      	}
      }
  	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<SysLog> list = sysLogDao.getBJLogs(id);
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("identifier", qualitySgcjjybgDao.get(id).getIdentifier());
		map.put("success", true);
		
		return map;
    }


	public List myApply() throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String sql = "select t.*, t.registrant_id handler_id "
				+ "from TJY2_QUALITY_SGCJJYBG t "
				+ "where t.registrant_id = ? "
				+ "order by t.identifier desc Nulls last, t.REGISTRANT_DATE desc";
		
		return qualitySgcjjybgDao.createSQLQuery(sql, user.getId()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}


	public List waitForDeal() throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String sql = "select b.*, t.handler_id from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "
				+ "where b.id = t.SERVICE_ID and t.WORK_TYPE like '%TJY2_ZL_SGCJJYBG%' "
				+ "and t.STATUS = '0' and t.handler_id = ? and b.status<> 'NO_PASS' order by b.REGISTRANT_DATE";
		
		return qualitySgcjjybgDao.createSQLQuery(sql, user.getId()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}


	public List dealed() throws Exception{
		List list = null;
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String sql = "select * from (select distinct b.*, t.handler_id, t.CREATER_TIME "+
	       "   from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "+
	       "  where b.id = t.SERVICE_ID "+
	       "    and t.WORK_TYPE like '%TJY2_ZL_SGCJJYBG%' "+
	       "    and t.STATUS <> '0' "+
	       "    and t.handler_id = ? "+
	       " union "+
	       " select b.*, t.handler_id, t.CREATER_TIME "+
	       "   from TJY2_QUALITY_SGCJJYBG b, v_pub_worktask t "+
	       "  where b.id = t.SERVICE_ID "+
	       "    and t.WORK_TYPE like '%TJY2_ZL_SGCJJYBG%' "+
	       "    and t.STATUS = '0' "+
	       "    and t.handler_id = ? "+
	       "    and b.status = 'NO_PASS') hb"+
	       "  where hb.DEPARTMENT_MAN = ? or hb.jyrjfzr = ? or hb.ywfwbjbr = ? or hb.zlshman = ? "+
	 	   "	order by CREATER_TIME desc";
		list = qualitySgcjjybgDao.createSQLQuery(sql, user.getId(), user.getId(),user.getName(),user.getName(),user.getName(),user.getName()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	public String getUserTel(BpmUser user){
		String sql= "select e.mobile_tel from employee e,sys_user u where u.employee_id = e.id and u.id = ?";
		List<Object> list = qualitySgcjjybgDao.createSQLQuery(sql, user.getId()).list();
		if(list.size()>0){
			String tel = list.get(0).toString();
			return tel;
		}
		return null;
	}
}
