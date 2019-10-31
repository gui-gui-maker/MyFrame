package com.lsts.equipment2.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.report.bean.Report;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName EquipmentBoxDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("equipmentBoxDao")
public class EquipmentBoxDao extends EntityDaoImpl<EquipmentBox> {

	
	/**
	 * 查看报告类型
	 * @param reportName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Report> getList(String reportName){
		String hql="from Report r where  r.flag='1' and r.report_name like'%"+reportName+"%'";
		List<Report> list = this.createQuery(hql).list();
		return list;
		
	}
	/**
	 * 去除设备箱里的设备
	 * @param boxId
	 * @return
	 */
	public void delEquip(String boxId){
		String sql = "update TJY2_EQUIPMENT b set b.fk_uv_id='',b.box_status='02',b.eq_inout_status='yrk',b.box_number='' where b.fk_uv_id=?";
		this.createSQLQuery(sql,boxId).executeUpdate();
	}
	/**
	 * 根据设备箱ID删除箱子
	 * @param boxId
	 * @return
	 */
	public void delBox(String boxId){
		String sql = "delete TJY2_EQUIPMENT_BOX b where b.id=?";
		this.createSQLQuery(sql,boxId).executeUpdate();
	}
	/**
	 * 根据设备箱号查询箱子是否存在
	 * @param boxId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EquipmentBox> queryBox(String box_number){
		List<EquipmentBox> list = new ArrayList<EquipmentBox>();
		try {
			String hql = "from EquipmentBox t where t.boxNumber=? ";	
			list = this.createQuery(hql, box_number).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取所有设备箱
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EquipmentBox> getBoxs(){
		List<EquipmentBox> list = new ArrayList<EquipmentBox>();
		try {
			String hql = "from EquipmentBox t";	
			list = this.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}