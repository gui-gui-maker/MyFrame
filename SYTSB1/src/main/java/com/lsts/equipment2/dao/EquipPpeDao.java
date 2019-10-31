package com.lsts.equipment2.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.Equipment;
/**
 * 
 * @ClassName EquipPpeDao
 * @JDK 1.5
 * @author 作者 QuincyXu
 * @date  创建时间：2016年5月16日16:00:40
 * 类说明
 */
@Repository("equipPpeDao")
public class EquipPpeDao extends EntityDaoImpl<EquipPpe> {

	/**
	 * 导入数据查看是否有卡片号重复的数据
	 * */
	@SuppressWarnings("unchecked")
	public List<EquipPpe> getinvoiceCode(String cardNo) {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			String hql = "from EquipPpe t where t.cardNo=? ";	
			list = this.createQuery(hql, cardNo).list();
			if (list.size()>0) {
				String sql = "DELETE FROM TJY2_EQUIP_PPE where CARD_NO='"+cardNo+"'";
				this.createSQLQuery(sql).executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据人员姓名查询用户信息
	 * @author QuincyXu
	 * @date 2016年5月25日09:42:33
	 */
    public Employee queryEmployee(String name) {
        String hql = "from Employee e where e.name=?";
        return (Employee)this.createQuery(hql, name).uniqueResult();
    }
    
    /**
	 * 通过barcode查询固定资产
	 * @author QuincyXu
	 * @date 2016年5月29日14:46:53
	 */
	public List<EquipPpe> searchByBarcode(String barcode) {
      	String hql = "from EquipPpe t where t.barcode=?";
      	List<EquipPpe> equipPpeList = this.createQuery(hql, barcode).list();
      	return equipPpeList;
   	}
	/**
	 * 根据卡片编号查询固定资产信息
	 * @author QuincyXu
	 * @date 2016年7月14日18:09:03
	 * 
	 */
	public List<EquipPpe> getByCardNo(String cardNo) {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			String hql = "from EquipPpe t where t.cardNo=? ";	
			list = this.createQuery(hql, cardNo).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据自编号查询固定资产信息
	 * @author QuincyXu
	 * @date 2016年7月14日18:09:03
	 * 
	 */
	public List<EquipPpe> getBySelfNo(String selfNo) {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			String hql = "from EquipPpe t where t.selfNo=? ";	
			list = this.createQuery(hql, selfNo).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 导入数据查看是否有自编号重复的数据
	 * */
	@SuppressWarnings("unchecked")
	public List<EquipPpe> deleteBySelfNo(String selfNo) {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			String hql = "from EquipPpe t where t.selfNo=? ";	
			list = this.createQuery(hql, selfNo).list();
			if (list.size()>0) {
				String sql = "DELETE FROM TJY2_EQUIP_PPE where SELF_NO='"+selfNo+"'";
				this.createSQLQuery(sql).executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取二维码位数不正确的资产
	 * */
	@SuppressWarnings("unchecked")
	public List<EquipPpe> getEquipPpeByErrBarcode() {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			String hql = "from EquipPpe t where t.barcode is null or length(t.barcode)!=13 ";	
			list = this.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}