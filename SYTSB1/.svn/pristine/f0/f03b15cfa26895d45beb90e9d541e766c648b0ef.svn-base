package com.lsts.equipment2.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.equipment2.bean.EquipmentUseRegister;
import com.lsts.equipment2.bean.InoutRecord;
import com.lsts.equipment2.dao.EquipmentUseRegisterDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("equipmentUseRegisterManager")
public class EquipmentUseRegisterManager extends EntityManageImpl<EquipmentUseRegister,EquipmentUseRegisterDao>{
    @Autowired
    EquipmentUseRegisterDao useRegisterDao;
    
    
    
    public static final String  SBJD_YGH = "02";
    public static final String  SBJD_WGH = "01";
    //通过设备ID查出设备所有使用登记记录
  	public List<EquipmentUseRegister> searchUseRecord(String equip_id) throws ParseException {
      	List<EquipmentUseRegister> useRecords = useRegisterDao.searchRecord(equip_id);
      	List<EquipmentUseRegister> useRecords1 = new ArrayList<EquipmentUseRegister>();
      	if(useRecords!=null && useRecords.size()>0){
      		for(int i = 0;i<useRecords.size();i++){
      			Date date1 = useRecords.get(i).getBorrowerTime();
      			Date date2 = useRecords.get(i).getReturnTime();
      			useRecords.get(i).setBorrowerTime(new java.sql.Date(date1.getTime()));
      			if(date2!=null && useRecords.size()>0){
      				useRecords.get(i).setReturnTime(new java.sql.Date(date2.getTime()));
      			}
      		}
      		useRecords1 = useRecords;
      	}else{
      		useRecords1 = useRecords;
      	}
  		return useRecords1;
  	}
}
