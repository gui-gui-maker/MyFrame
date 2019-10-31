package com.lsts.qualitymanage.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.lsts.log.bean.SysLog;
import com.lsts.log.dao.SysLogDao;
import com.lsts.qualitymanage.bean.QualityStaffTrain;
import com.lsts.qualitymanage.bean.TaskAllot;
import com.lsts.qualitymanage.dao.QualityStaffTrainDao;


@Service("QualityStaffTrain")
public class QualityStaffTrainManager extends EntityManageImpl<QualityStaffTrain,QualityStaffTrainDao>{
    @Autowired
    QualityStaffTrainDao qualityStaffTrainDao;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
    private SysLogDao sysLogDao;
    
    public final static String ZL_SH_WTJ = "WTJ"; //未提交
    public final static String ZL_SH_YTJ = "YTJ"; //已提交
    public final static String ZL_SH_SHZ = "SHZ"; //审核中	
    public final static String ZL_SH_PASS = "PASS"; //审核通过
    public final static String ZL_SH_NO_PASS = "NO_PASS"; //审核不通过
    
    
	    /**
	  	 * 保存
	  	 * @param qualityStaffTrain
	  	 * @param user
	  	 */
    	public synchronized void saveTrain(QualityStaffTrain qualityStaffTrain,CurrentSessionUser user){
    		//新增保存时，生成新编号，修改功能不需要重新生成编号
    		if(null==qualityStaffTrain.getId() || "".equals(qualityStaffTrain.getId())){
    			String docNum = "";
    			Calendar newYear=Calendar.getInstance();
        		int nowYear = newYear.get(Calendar.YEAR);
        		List<QualityStaffTrain> qualityStaffTrainList = qualityStaffTrainDao.getQualityStaffTrain();
        		if(qualityStaffTrainList==null || qualityStaffTrainList.size()==0) {
        			docNum =  "CTJC-004-B05-"+nowYear+"-"+"0001";
        		} else {
        			int[] docNumArray = new int[qualityStaffTrainList.size()];
        			for (int i=0;i<qualityStaffTrainList.size();i++) {
        				//将编号去掉“-”，转换成int型存入数组
        				if(qualityStaffTrainList.get(i).getTrainChartNumber()!=null && !"".equals(qualityStaffTrainList.get(i).getTrainChartNumber())){
        					String str = qualityStaffTrainList.get(i).getTrainChartNumber();
            		  		StringBuffer sb=new StringBuffer(str);
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
         	 			docNum =  "CTJC-004-B05-"+nowYear+"-"+"0001";
         	 		}else{
         	 			//编号加1后重新组
         	 			docNum = "CTJC-004-B05-"+docNum1.substring(0, 4)+"-"+docNum1.substring(4, 8);
         	 		}
        		}
        		qualityStaffTrain.setTrainChartNumber(docNum);
    		}
    		qualityStaffTrain.setRegistrantId(user.getId());
    		qualityStaffTrain.setRegistrantName(user.getName());
    		qualityStaffTrain.setRegistrantTime(new Date());
    		if(qualityStaffTrain.getStatus()==null||qualityStaffTrain.getStatus().equals("")){
    			qualityStaffTrain.setStatus(QualityStaffTrainManager.ZL_SH_WTJ);
    		}
    		qualityStaffTrainDao.save(qualityStaffTrain);
    	}
    	
    	/**
		 * 删除
		 * */
	  	public void delete(String ids)throws Exception{
	      	for(String id:ids.split(",")){
	      		QualityStaffTrain qualityStaffTrain = qualityStaffTrainDao.get(id);
	      		qualityStaffTrainDao.getHibernateTemplate().delete(qualityStaffTrain);
	      	}
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
      	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		List<SysLog> list = sysLogDao.getBJLogs(id);
    		map.put("flowStep", list);
    		map.put("size", list.size());
    		map.put("identifier", qualityStaffTrainDao.get(id).getTratnFileNum());
    		map.put("success", true);
    		
    		return map;
        }
}
