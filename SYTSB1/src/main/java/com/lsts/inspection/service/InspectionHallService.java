package com.lsts.inspection.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.TS_Util;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.inspection.bean.InspectionHall;
import com.lsts.inspection.bean.InspectionHallPara;
import com.lsts.inspection.dao.InspectionHallDao;
import com.lsts.inspection.dao.InspectionHallParaDao;

/**
 * 报检大厅管理   servier
 * 
 * @author 肖慈边 2014-1-26
 */
@Service("inspectionHallService")
public class InspectionHallService extends EntityManageImpl<InspectionHall, InspectionHallDao> {
	@Autowired
	private InspectionHallDao inspectionHallDao;

	private InspectionHallParaDao hallParaDao;
	 /**
     * 
     * @param hall
     * @param user
     * @param map 前台提供的参数
     * @return
     * @throws Exception
     */
	public InspectionHall saveBasic(InspectionHall hall) throws Exception
    {
    	
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
    	
    	for (InspectionHallPara hallPara : hall.getHallPara()) {
			
    		hallPara.setInspectionHall(hall);
    		hallPara.setIs_rec("1");//是否接收
    		hallPara.setIs_plan("1");//是否分配 默认否
    		hallPara.setTransfer_op(user.getUserName());
    		hallPara.setTransfer_date(new Date());
		}
    	hall.setReg_date(new Date());
    	hall.setFlow_status("1");
    	hall.setReg_op(user.getUserName());
    	hall.setData_status("1");
    	
    	if(hall.getHall_no()==null||"".endsWith(hall.getHall_no())){
    	//获取表单号
    	String num = TS_Util.getHallNumber();
    	
    	
    		hall.setHall_no(num);

    	}
    	inspectionHallDao.save(hall);
		
		
		return hall;
    }
	public void saveTransfer(Map<String, Object> map) throws Exception
    {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		String  id=map.get("device_id").toString();
		String unit_code=map.get("unit_code").toString();
		
		
		
		String ids="";
		//判断是否多个id
	     StringBuffer json = new StringBuffer();
				if(id.indexOf(",")!=-1){
					String temp[]=id.split(",");
				
					for (int i = 0; i < temp.length; i++) {
						
						json.append("'").append(temp[i] ).append("'");
						if(i!=temp.length-1){
							json.append(",");
						}
					
						
					}
					ids =json.toString();
				}else{
					ids=json.append("'").append(id).append("'").toString();
				}
				
				
				
		inspectionHallDao.createSQLQuery("update  TZSB_INSPECTION_HALL_PARA  set UNIT_CODE='"+unit_code+"',TRANSFER_OP='"+user.getUserName()+"',TRANSFER_DATE=sysdate where id in("+ids+")").executeUpdate();
    	
		
    }
	
	
	public void savePlan(Map<String, Object> map) throws Exception
    {
		
		
		
		String  id=map.get("device_id").toString();
		String op_ids=map.get("op_ids").toString();
		String check_ids=map.get("check_ids").toString();
		String op_name=map.get("op_name").toString();
		String check_name=map.get("check_name").toString();
		String inc_time = map.get("inc_time").toString();
		
		String ids="";
		//判断是否多个id
	     StringBuffer json = new StringBuffer();
				if(id.indexOf(",")!=-1){
					String temp[]=id.split(",");
				
					for (int i = 0; i < temp.length; i++) {
						
						json.append("'").append(temp[i] ).append("'");
						if(i!=temp.length-1){
							json.append(",");
						}
					
						
					}
					ids =json.toString();
				}else{
					ids=json.append("'").append(id).append("'").toString();
				}
		
		inspectionHallDao.createSQLQuery("update  TZSB_INSPECTION_HALL_PARA  set INC_TIME=to_date('"+inc_time+"','yyyy-MM-dd'), INC_OP_ID='"+op_ids+"',CHECK_OP_IDS='"+check_ids+"',OP_NAME='"+op_name+"',CHECK_NAME='"+check_name+"',IS_PLAN='2' where id in("+ids+")").executeUpdate();
    	
		
    }
	
	
	public InspectionHall  getDe(String id,String hall_id) throws Exception
    {	
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//获取大厅参数信息
		//InspectionHallPara 	hallPara = hallParaDao.get(id);
		//获取大厅信息
		InspectionHall 	hall = inspectionHallDao.get(hall_id);
		
		
		String ids ="";
		//判断是否多个id
		StringBuffer json = new StringBuffer();
		if(id.indexOf(",")!=-1){
			String temp[]=id.split(",");
		
			for (int i = 0; i < temp.length; i++) {
				
				json.append("'").append(temp[i] ).append("'");
				if(i!=temp.length-1){
					json.append(",");
				}
			
				
			}
			ids =json.toString();
		}else{
			ids=json.append("'").append(id).append("'").toString();
		}
		
		String sql = "select t.* from TZSB_INSPECTION_HALL_PARA t where  t.id in("+ids+")";
		
		SQLQuery  query = inspectionHallDao.getquery(sql);
		
		query.addEntity(InspectionHallPara.class);
		
		List  list = query.list();
		
		hall.setParaList(list);
			
		
		
		return hall;
		
    }
	
	

	public void del(String ids) throws Exception {
		// TODO Auto-generated method stub
		//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			
			for(int i=0;i<id.length;i++){
				
				inspectionHallDao.createSQLQuery("update  TZSB_INSPECTION_HALL  set data_status='2' where id =?",id[i] ).executeUpdate();
				
			}
			
		}else{
		
			inspectionHallDao.createSQLQuery("update  TZSB_INSPECTION_HALL  set data_status='2' where id = ?", ids).executeUpdate();
			
		}
		
	}
	
	public void subDep(String ids) throws Exception {
		// TODO Auto-generated method stub
		//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			
			for(int i=0;i<id.length;i++){
				
				inspectionHallDao.createSQLQuery("update  TZSB_INSPECTION_HALL  set flow_status='2' where id =?",id[i] ).executeUpdate();
				
			}
			
		}else{
		
			inspectionHallDao.createSQLQuery("update  TZSB_INSPECTION_HALL  set flow_status='2' where id = ?", ids).executeUpdate();
			
		}
		
	}
	
	
	

}
