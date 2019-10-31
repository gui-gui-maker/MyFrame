package com.lsts.equipment2.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.service.FlowExtManager;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.EquipmentCentre;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.dao.EquipmentCentreDao;
import com.lsts.equipment2.dao.EquipmentDao;
import com.lsts.equipment2.dao.EquipmentLoanDao;
import com.lsts.finance.bean.Clfbxd;



@Service("equipmentLoanManager")
public class EquipmentLoanManager extends EntityManageImpl<EquipmentLoan,EquipmentLoanDao>{
	@Autowired
	EquipmentCentreManager equipmentCentreManager;
	@Autowired
    EquipmentLoanDao equipmentLoanDao;
    @Autowired
    EquipmentDao baseEquipment2Dao;
    @Autowired
    FlowExtManager flowExtManager;
    @Autowired
    EquipmentCentreDao equipmentCentreDao;
    @Autowired
    EquipmentBoxDao equipmentBoxDao ;
    
    public final static String EQ_SHSQ_TG ="0"; //审核通过
    public final static String EQ_SHSQ_BTG ="1"; //审核不通过
    public final static String EQ_SHSQ_WTJ = "2"; //未提交
    public final static String EQ_SHSQ_YTJ = "3"; //已提交

    
    /**
     * 保存借用并遍历设备信息,将借用ID存入设备外键ID中
     * @param equipmentBox
     * @param user
     */
    public HashMap<String, Object> saveEqui(EquipmentLoan equipmentLoan,HttpServletRequest request,CurrentSessionUser user,String boxIds){
    	String status = request.getParameter("pageStatus");
    	if(equipmentLoan.getLoanType().equals("loan")&&equipmentLoan.getLoanNo().isEmpty()){
    		String orgID=equipmentLoan.getDepId();//获取部门ID
    		String orgCode=equipmentLoanDao.getOrgCode(orgID).get(0).getOrgCode();//获取部门Code
    		String loanNo = "";
    		Calendar a=Calendar.getInstance();
     		String nowYear = String.valueOf(a.get(Calendar.YEAR)).substring(2, 4);
    		List<EquipmentLoan> loanlist = equipmentLoanDao.getLoanTotal(nowYear);
     		if(loanlist==null || loanlist.size()==0) {
     			loanNo = orgCode+"-"+nowYear+"-"+"1";
     		} else {
     			int[] loanNoArray = new int[loanlist.size()];
     			for (int i=0;i<loanlist.size();i++) {
     				//将编号去掉“-”，转换成int型存入数组
     				if(loanlist.get(i).getLoanNo()!=null && !"".equals(loanlist.get(i).getLoanNo())){
     					int a1=loanlist.get(i).getLoanNo().lastIndexOf("-");
     					loanNoArray[i] = Integer.parseInt(loanlist.get(i).getLoanNo().substring(a1, loanlist.get(i).getLoanNo().length()));
     				}
     			}
     			int max = loanNoArray[0];
     			//获取数组中最大的值
     			for (int i : loanNoArray) {
     				max = max>i?max:i;
     			}
     			String max1 = String.valueOf(max+1);
     			loanNo = orgCode+"-"+nowYear+"-"+max1;
     		}
     		equipmentLoan.setLoanNo(loanNo);
    	}
    	//保存设备借用/领用
    	equipmentLoan.setRegisterName(user.getName()); //获取当前登陆人
    	equipmentLoan.setRegisterId(user.getId()); //获取当前登陆人ID
    	equipmentLoan.setRegisterTime(new Date());
    	if(equipmentLoan.getAuditStatus()==null || "".equals(equipmentLoan.getAuditStatus())){
    		equipmentLoan.setAuditStatus(EQ_SHSQ_WTJ);
    	}
    	equipmentLoanDao.save(equipmentLoan);
    	String recordId = equipmentLoan.getId();
    	if(status.equals("modify")){
    		//根据记录ID删除原来中间表的记录
    		equipmentCentreManager.delete(recordId);
    	}
    		//获取设备
        	if(equipmentLoan.getBaseEquipment2s()!=null && equipmentLoan.getBaseEquipment2s().size()>0){
            		//遍历设备ID
            		for(int i=0;i<equipmentLoan.getBaseEquipment2s().size();i++){
            			if(!equipmentLoan.getBaseEquipment2s().get(i).getBox_status().equals("01")){
            				Equipment baseEquipment2 = baseEquipment2Dao.get(equipmentLoan.getBaseEquipment2s().get(i).getId());
                			baseEquipment2Dao.save(baseEquipment2);
                			EquipmentCentre equipmentCentre = new EquipmentCentre(); //获取中间表对象
                			equipmentCentre.setEquipmentLoan(equipmentLoan); //将领用/借用ID存入外键ID中
                			equipmentCentre.setFk_equipment_id(baseEquipment2.getId());//获得设备ID并存入中间表中
                			equipmentCentreDao.save(equipmentCentre); //保存中间表
            			}
            		}
        	}else{
        		return JsonWrapper.failureWrapperMsg("没有设备信息！");
        	}
        	if(boxIds != null){
        		String[]  id = boxIds.substring(0,boxIds.length()-1).split(",");
        		for(int i=0;i<id.length;i++){
        			EquipmentBox equipmentBox = equipmentBoxDao.get(id[i]);
        			if(equipmentLoan.getLoanType().equals("loan")){
        				equipmentBox.setBoxLoanStatus("loan");
        			}
        			if(equipmentLoan.getLoanType().equals("lean")){
        				equipmentBox.setBoxLoanStatus("lean");
        			}
    				equipmentBoxDao.save(equipmentBox);
    				EquipmentCentre equipmentCentre = new EquipmentCentre(); //获取中间表对象
    				equipmentCentre.setEquipmentLoan(equipmentLoan); //将领用/借用ID存入外键ID中
    				equipmentCentre.setFk_box_id(id[i]);
    				equipmentCentreDao.save(equipmentCentre);
        		}
        	}
    	
		return null;
    	
    }
    

    /**
	 * 删除
	 * */
  	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		EquipmentLoan equipmentLoan = equipmentLoanDao.get(id);
      		List<EquipmentCentre> equipmentCentres= new ArrayList<EquipmentCentre>();//中间表list
      		equipmentCentres= equipmentCentreManager.queryEquipmentCentre(id);
      		if(!"".equals(equipmentCentres)&&equipmentCentres!=null){
    			for(int i = 0;i<equipmentCentres.size();i++){
    				EquipmentCentre equipmentCentre = equipmentCentres.get(i);
    				String box_id = equipmentCentre.getFk_box_id();
    				equipmentLoanDao.getHibernateTemplate().delete(equipmentLoan);
    				if(box_id!=null && !"".equals(box_id)){
    					EquipmentBox equipmentBox = equipmentBoxDao.get(box_id);
    					equipmentBox.setBoxLoanStatus(null);
    				}
    			}
      		}
      	}
      }
    
    
    /**
     * 提交
     * @param map
     * @throws Exception
     */
  	
  	public void doStartPress(Map<String, Object> map)throws Exception{
  		flowExtManager.startFlowProcess(map);
      }
    
  	
  	/**
  	 * 流程结束
  	 * */
  	public void stop(Map<String, Object> map)throws Exception{
  		flowExtManager.finishProcess(map);
      }
    
}
