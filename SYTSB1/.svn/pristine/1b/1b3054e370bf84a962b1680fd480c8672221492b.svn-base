package com.lsts.qualitymanage.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.qualitymanage.bean.Tyfabh;
import com.lsts.qualitymanage.dao.TyfabhDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("Tyfabh")
public class TyfabhManager extends EntityManageImpl<Tyfabh,TyfabhDao>{
    @Autowired
    TyfabhDao tyfabhDao;
    @Autowired
    private SysLogDao sysLogDao;
    
    public final static String ZL_TYFABH_WTJ = "WTJ";// 未提交
    public final static String ZL_TYFABH_JBR = "JBR";// 经办人审核中
    public final static String ZL_TYFABH_POSS = "POSS";// 审核通过
    public final static String ZL_TYFABH_NO_POSS = "NO_POSS";// 未通过
    public final static String ZL_TYFABH_CANCELLING = "CANCELLING";// 作废审核中
    public final static String ZL_TYFABH_CANC = "CANC";// 已作废

    /**
	 * 生成修改单编号
	 * @throws ParseException 
	 * */
	public synchronized void saveTyfabh(Tyfabh tyfabh,CurrentSessionUser user) throws ParseException{
		//新增保存时，生成新编号，修改功能不需要重新生成编号
		if(null==tyfabh.getId() || "".equals(tyfabh.getId())){
			String docNum = "";
			Calendar a=Calendar.getInstance();
 		int nowYear = a.get(Calendar.YEAR);
 		List<Tyfabh> tyfabhlist = tyfabhDao.getTaskAllot();
 		if(tyfabhlist==null || tyfabhlist.size()==0) {
 			docNum = "CTJC-001-B19-"+nowYear+"-"+"0001";
 		} else {
 			int[] docNumArray = new int[tyfabhlist.size()];
 			for (int i=0;i<tyfabhlist.size();i++) {
 				//将编号去掉“-”，转换成int型存入数组
 				if(tyfabhlist.get(i).getIdentifier()!=null && !"".equals(tyfabhlist.get(i).getIdentifier())){
 					String str =tyfabhlist.get(i).getIdentifier();
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
 	 			docNum = "CTJC-001-B19-"+nowYear+"-"+"0001";
 	 		}else{
	 			//编号加1后重新组
	 			docNum = "CTJC-001-B19-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
 	 		}
 		}
 		tyfabh.setIdentifier(docNum);
 		tyfabh.setStatus("WTJ");
 		//获取当前登录人姓名
 		tyfabh.setRegistrant(user.getName());
 		tyfabh.setRegistrantId(user.getId());
 		tyfabh.setRegistrantDate(new Date());
		}
		
		tyfabhDao.save(tyfabh);
	}
	
	/**
	 * 删除
	 * */
  	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		Tyfabh mtMP = tyfabhDao.get(id);
      		tyfabhDao.getHibernateTemplate().delete(mtMP);
      	}
      }
  	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
  		HashMap<String, Object> map = new HashMap<String, Object>();
  		List<SysLog> list = sysLogDao.getBJLogs(id);
  		map.put("flowStep", list);
  		map.put("size", list.size());
  		map.put("identifier", tyfabhDao.get(id).getIdentifier());
  		map.put("success", true);
  		
  		return map;
      }
}
