package com.lsts.equipment2.service;



import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.EquipmentBoxRecord;
import com.lsts.equipment2.dao.EquipmentDao;
import com.lsts.log.service.SysLogService;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.dao.EquipmentBoxRecordDao;
import com.lsts.report.bean.Report;




@Service("equipmentBoxManager")
public class EquipmentBoxManager extends EntityManageImpl<EquipmentBox,EquipmentBoxDao>{
    @Autowired
    EquipmentBoxDao equipmentBoxDao;
    @Autowired
    EquipmentDao baseEquipment2Dao;
    @Autowired
    EquipmentBoxRecordDao equipmentBoxRecordDao;
    @Autowired
	private SysLogService logService;
    /**
     * 保存设备箱并遍历设备信息,将箱子ID存入设备外键ID中
     * @param equipmentBox
     * @param user
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public void saveEqui(EquipmentBox equipmentBox,CurrentSessionUser user) throws IllegalAccessException, InvocationTargetException{
    	//取出传入的箱子里面的设备
    	List<Equipment> baseEquipment2sInbox = equipmentBox.getBaseEquipment2s();
    	//箱子ID不为空则删除箱子里面的设备
    	String boxId= equipmentBox.getId();
    	if(!"".equals(boxId)&& boxId!=null){
    		equipmentBoxDao.delEquip(boxId);
    	}
    	//保存设备箱
    	equipmentBox.setRegisterName(user.getName()); //获取当前登陆人
    	equipmentBox.setRegisterId(user.getId()); //获取当前登陆人ID
    	equipmentBox.setRegisterTime(new Date());	//获取登记时间
    	equipmentBox.setBaseEquipment2s(null);
    	equipmentBox.setBoxinoutStatus(EquipmentManager.BOX_STATUS1);
    	if(equipmentBox.getBoxLoanStatus()==null ||"".equals(equipmentBox.getBoxLoanStatus())){
    		equipmentBox.setBoxLoanStatus("leisure");
    	}
    	equipmentBoxDao.save(equipmentBox);
    	EquipmentBoxRecord equipmentBoxRecord = new EquipmentBoxRecord(); //获取设备箱记录表
    	equipmentBoxRecord.setFkEquipmentboxId(equipmentBox.getId()); //将设备箱ID存入记录表设备箱中
    	
    	if(baseEquipment2sInbox!=null){
    		if(baseEquipment2sInbox.size()>0){
        		//遍历设备ID
        		for(int i=0;i<baseEquipment2sInbox.size();i++){
        			Equipment baseEquipment2 = baseEquipment2Dao.get(baseEquipment2sInbox.get(i).getId());
        			baseEquipment2.setEquipmentBox(equipmentBox);
        			baseEquipment2.setBox_number(equipmentBox.getBoxNumber());//设备箱编号
        			baseEquipment2.setEq_inout_status("yck");//出入库状态
        			baseEquipment2.setBox_status(EquipmentManager.BOX_STATUS1);//回写装箱状态:已装箱
        			baseEquipment2Dao.save(baseEquipment2);
        			equipmentBoxRecord.setFkEquipmentId(baseEquipment2.getId()); //将设备ID存入记录表设备中
        			equipmentBoxRecordDao.save(equipmentBoxRecord); //保存记录表
        		}
        	}
    	}
    }
    
    
    public List<Report> getBaseReports(String reportName) throws Exception{
    	
    	return equipmentBoxDao.getList(reportName);
    	
    }
    
    
    
    /**删除
  	 * @param ids
  	 * @throws Exception
  	 */
  	public void delete(String ids)throws Exception{
      	for(String id:ids.split(",")){
      		EquipmentBox rp = equipmentBoxDao.get(id);
      		//获取箱子ID
      		String boxId  = rp.getId();
      		//移除箱子里面的设备
      		equipmentBoxDao.delEquip(boxId);
      		//删除箱子
      		equipmentBoxDao.delBox(boxId);
      	}
      }
    
  	/**
  	 * 删除设备箱中的设备
  	 * @param equipmentBox
  	 */
    public void delEquip(String boxId){
		if(boxId!=null && !boxId.equals("")) {
			//根据设备箱id去除设备信息
			EquipmentBox EquipmentBox = equipmentBoxDao.get(boxId);
			if(EquipmentBox!=null && EquipmentBox.getBaseEquipment2s().size()>0) {
				for(int i=0;i<EquipmentBox.getBaseEquipment2s().size();i++){
					EquipmentBox.getBaseEquipment2s().get(i).setEquipmentBox(null);//去除装箱关联
					EquipmentBox.getBaseEquipment2s().get(i).setBox_status(EquipmentManager.BOX_STATUS2);//状态改为“未装箱”
					baseEquipment2Dao.save(EquipmentBox.getBaseEquipment2s().get(i));
				}
			}
		}
	}
    /**
     * 实时更新设备箱检验日期是否过期，若过期则回写日期
     */
    public void synNextCheckTime(){
    	System.out.println("------------更新设备箱预警时间----开始--------");
    	List<EquipmentBox> list=equipmentBoxDao.getBoxs();//获取所有设备箱
    	for(EquipmentBox equipmentBox:list){
    		Date cacheDate = null;
    		List<Equipment> equipmentList = equipmentBox.getBaseEquipment2s();
    		for(Equipment equipment:equipmentList){
    			Date eq_next_check_date = equipment.getEq_next_check_date();//设备下次检定日期
    			if(eq_next_check_date!=null){
    				//当下次检定日期不为空时
    				if(cacheDate!=null){
    					if(eq_next_check_date.getTime()<=cacheDate.getTime()){
    						cacheDate=eq_next_check_date;
    					}
    				}else{
    					cacheDate=eq_next_check_date;
    				}
    			}
    		}
    		equipmentBox.setLastCheckDate(cacheDate);
    		equipmentBoxDao.save(equipmentBox);
    	}
    	System.out.println("------------更新设备箱预警时间----结束--------");
	}
    /**
     * 根据设备箱编号获取设备箱信息
     * @param boxNumber
     * @return
     */
    public HashMap<String, Object> getEquipmentBox(String boxNumber){
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	List<EquipmentBox> list = equipmentBoxDao.queryBox(boxNumber);
    	if(list.size()>1){
    		map.put("num", list.size());
    		map.put("msg", "此设备箱号存在重复问题，请到【设备箱管理】处更改重复信息！");
    	}else if(list.size()==1){
    		map.put("equipmentBox", list.get(0));
    		map.put("num", list.size());
    		map.put("msg", "此设备箱号已存在，请纠正设备箱号！");
    	}else if(list.size()==0){
    		map.put("num", list.size());
    		map.put("msg", "此设备箱号不存在，请到【设备箱管理】处添加设备箱信息再执行此操作！");
    	}
		return map;
    }
    /**
     * 根据设备箱编号获取设备箱信息
     * @param boxNumber
     * @return
     */
    public EquipmentBox addBoxInfo(HttpServletRequest request,EquipmentBox equipmentBox){
    	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
    	User user = (User) curUser.getSysUser();
    	try {
			equipmentBox.setRegisterId(user.getId());
			equipmentBox.setRegisterName(user.getName());
			equipmentBox.setRegisterTime(new Date());
			equipmentBoxDao.save(equipmentBox);
			logService.setLogs(equipmentBox.getId(),"新增设备箱信息","新增设备箱信息",user.getId(),user.getName(),new Date(),request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return equipmentBox;
    }
}
