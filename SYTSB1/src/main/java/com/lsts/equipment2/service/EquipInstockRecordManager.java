package com.lsts.equipment2.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipRecordRelation;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.EquipmentUseRegister;
import com.lsts.equipment2.bean.EquipInstockRecord;
import com.lsts.equipment2.dao.EquipmentDao;
import com.lsts.equipment2.dao.EquipRecordRelationDao;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.dao.EquipmentUseRegisterDao;
import com.lsts.equipment2.dao.EquipInstockRecordDao;

/**
 * 设备出入库记录业务逻辑对象
 * 
 * @ClassName EquipInstockRecordManager
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:22:00
 */
@Service("EquipInstockRecordManager")
public class EquipInstockRecordManager extends EntityManageImpl<EquipInstockRecord, EquipInstockRecordDao> {
	@Autowired
	EquipInstockRecordDao equipInstockRecordDao;
	@Autowired
	EquipmentDao baseEquipmentDao;
	@Autowired
	EquipmentBoxDao equipmentBoxDao;
	@Autowired
	EquipRecordRelationDao equipRecordRelationDao;
	@Autowired
	EquipmentUseRegisterDao useRegisterDao;

		// 设备入库
		public HashMap<String, Object> instock(EquipInstockRecord equipInstockRecord, HttpServletRequest request) {
			HashMap<String, Object> wrapper = JsonWrapper.successWrapper("");
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String create_name = user.getName();
			Date craete_date = new Date();
			try {
				//保存入库记录信息
				equipInstockRecord.setCreate_name(create_name);
				equipInstockRecord.setCreate_date(craete_date);
				equipInstockRecordDao.save(equipInstockRecord);
				//取出入库记录ID
				String recordId = equipInstockRecord.getId();
				//取出设备箱到list中
				List<EquipmentBox> equipmentBoxs = equipInstockRecord.getEquipmentBoxs();
				//取出设备到list中
				List<Equipment> baseEquipment2s = equipInstockRecord.getBaseEquipment2s();
				if(equipmentBoxs!=null && equipmentBoxs.size()>0){
					if(recordId != null){
						for(int k = 0;k<equipmentBoxs.size();k++){
							//取出设备箱ID
							String box_id = equipmentBoxs.get(k).getId();
							EquipmentBox box = equipmentBoxDao.get(box_id);
							box.setBoxinoutStatus("01");//修改设备箱状态为已入库
							equipmentBoxDao.save(box);
						}
					}
				}
				if(baseEquipment2s!=null && baseEquipment2s.size()>0) {
					if (recordId != null) {
						for (int i = 0; i < baseEquipment2s.size(); i++) {
							//取出设备ID
							String equip_id = baseEquipment2s.get(i).getId();
							//获取设备信息
							Equipment equipment = baseEquipmentDao.get(equip_id);
							//回写设备出入库状态
							baseEquipmentDao.updateInstock(equip_id,equipInstockRecord,equipment);;
							//保存关系表
							EquipRecordRelation equipRecordRelation = new EquipRecordRelation();
							equipRecordRelation.setInstock_id(recordId);
							equipRecordRelation.setEquip_id(equip_id);
							equipRecordRelation.setCreate_by(create_name);
							equipRecordRelation.setCreate_date(craete_date);
							equipRecordRelationDao.addEquipRecordRelation(equipRecordRelation);
							//保存使用归还记录
							useRegisterDao.saveReturn(equip_id,equipInstockRecord);
						}
					} else {
						return JsonWrapper.failureWrapperMsg("保存有误！");
					}
				} else {
					return JsonWrapper.failureWrapperMsg("没有设备信息！");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("获取信息失败！");
			}
			return wrapper;
		}
		//删除出库记录
		public void delete1(String ids) {
			String centerTableId[] = ids.split(",");
			if(centerTableId.length>0){
				for(int i = 0;i<centerTableId.length;i++){
					String recordId = equipRecordRelationDao.getRecordId(centerTableId[i]);
					int count = equipRecordRelationDao.countEquip(recordId);
					if(count>1){
						equipRecordRelationDao.deleteByCenterId(centerTableId[i]);
					}else if(count==1){
						equipRecordRelationDao.deleteByCenterId(centerTableId[i]);
						equipInstockRecordDao.deleteById(recordId);
					}
				}
			}
		}
}
