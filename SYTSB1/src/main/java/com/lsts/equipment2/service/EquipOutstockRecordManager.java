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
import org.springframework.web.bind.annotation.RequestBody;

import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipRecordRelation;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.bean.EquipmentUseRegister;
import com.lsts.equipment2.bean.EquipOutstockRecord;
import com.lsts.equipment2.dao.EquipmentDao;
import com.lsts.equipment2.dao.EquipRecordRelationDao;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.dao.EquipmentLoanDao;
import com.lsts.equipment2.dao.EquipmentUseRegisterDao;
import com.lsts.equipment2.dao.EquipOutstockRecordDao;

/**
 * 设备出库记录业务逻辑对象
 * 
 * @ClassName EquipOutstockRecordManager
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 下午03:22:00
 */
@Service("EquipOutstockRecordManager")
public class EquipOutstockRecordManager extends EntityManageImpl<EquipOutstockRecord, EquipOutstockRecordDao> {
	@Autowired
	EquipOutstockRecordDao equipOutstockRecordDao;
	@Autowired
	EquipmentDao baseEquipmentDao;
	@Autowired
	EquipmentBoxDao equipmentBoxDao;
	@Autowired
	EquipRecordRelationDao equipRecordRelationDao;
	@Autowired
	EquipmentUseRegisterDao useRegisterDao;
	@Autowired
	EquipmentLoanDao equipmentLoanDao;

		// 设备出库
		public HashMap<String, Object> outstock(String apply_id,EquipOutstockRecord equipOutstockRecord) {
			HashMap<String, Object> wrapper = JsonWrapper.successWrapper("");
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			EquipmentLoan loan = equipmentLoanDao.get(apply_id);
			if(loan.getLoanType().equals("loan")){
				loan.setAuditStatus("5");
			}else if(loan.getLoanType().equals("lean")){
				loan.setAuditStatus("4");
			}
			try {
				equipmentLoanDao.save(loan);
				String create_name = user.getName();
				Date craete_date = new Date();
				//保存出库记录
				equipOutstockRecord.setOutstock_by(create_name);
				equipOutstockRecord.setOutstock_date(craete_date);
				equipOutstockRecord.setCreate_by(create_name);
				equipOutstockRecord.setCreate_date(craete_date);
				equipOutstockRecordDao.save(equipOutstockRecord);
				//取出出库记录ID
				String recordId = equipOutstockRecord.getId();
				//取出设备箱到list中
				List<EquipmentBox> equipmentBoxs = equipOutstockRecord.getEquipmentBoxs();
				//取出设备到list中
				List<Equipment> baseEquipment2s = equipOutstockRecord.getBaseEquipment2s();
				if(equipmentBoxs!=null && equipmentBoxs.size()>0){
					if(recordId != null){
						for(int k = 0;k<equipmentBoxs.size();k++){
							//取出设备箱ID
							String box_id = equipmentBoxs.get(k).getId();
							EquipmentBox box = equipmentBoxDao.get(box_id);
							box.setBoxinoutStatus("02");//修改设备箱状态为已出库
							equipmentBoxDao.save(box);
						}
					}
				}
				if(baseEquipment2s!=null && baseEquipment2s.size()>0) {
					if (recordId != null) {
						for (int i = 0; i < baseEquipment2s.size(); i++) {
							//取出设备ID
							String equip_id = baseEquipment2s.get(i).getId();
							//回写设备为出库状态，使用状态为在用，将归还时间记录到设备中
							baseEquipmentDao.updateOutstatus(equip_id,equipOutstockRecord);;
							//保存关系表
							EquipRecordRelation equipRecordRelation = new EquipRecordRelation();
							equipRecordRelation.setInstock_id(recordId);;
							equipRecordRelation.setEquip_id(equip_id);
							equipRecordRelation.setCreate_by(create_name);
							equipRecordRelation.setCreate_date(craete_date);
							equipRecordRelationDao.addEquipRecordRelation(equipRecordRelation);
							//保存使用登记
							EquipmentUseRegister useRecord = new EquipmentUseRegister();
							useRecord.setCreateId(user.getId());
							useRecord.setCreateName(create_name);
							useRecord.setCreateTime(craete_date);
							useRecord.setBorrowerId(equipOutstockRecord.getBorrow_draw_id());
							useRecord.setBorrowerName(equipOutstockRecord.getBorrow_draw_by());
							useRecord.setDepartmentId(equipOutstockRecord.getOutstock_position_id());
							useRecord.setDepartmentName(equipOutstockRecord.getOutstock_position());
							useRecord.setUseType(equipOutstockRecord.getOutstock_type());
							useRecord.setBorrowerTime(equipOutstockRecord.getBorrow_draw_date());
							useRecord.setEquipId(equip_id);
							useRecord.setStatus("01");//未归还状态
							useRegisterDao.save(useRecord);
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
						equipOutstockRecordDao.deleteById(recordId);
					}
				}
			}
		}
}
