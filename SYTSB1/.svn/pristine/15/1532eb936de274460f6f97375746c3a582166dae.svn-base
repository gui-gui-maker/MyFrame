package com.lsts.equipment2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipInstockRecord;
import com.lsts.equipment2.bean.EquipOutstockRecord;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.Equipment2DTO;
import com.lsts.equipment2.bean.EquipmentLoan;
import com.lsts.equipment2.bean.InoutRecord;
import com.lsts.finance.bean.CwInvoiceF;

/**
 * 设备信息数据处理对象
 * 
 * @ClassName EquipmentDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 上午11:24:00
 */
@Repository("equipmentDao")
public class EquipmentDao extends EntityDaoImpl<Equipment> {
	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集

	

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
	 * 删除重复的设备信息
	 * */
	@SuppressWarnings("unchecked")
	public List<Equipment> getinvoiceCode(String eq_no,String eq_category) {
		List<Equipment> list = new ArrayList<Equipment>();
		try {
			String hql = "from Equipment t where t.eq_no=? and t.eq_category=?";	
			list = this.createQuery(hql, eq_no, eq_category).list();
			if (list.size()>0) {
				String sql = "DELETE FROM TJY2_EQUIPMENT where EQ_NO='"+eq_no+"' and EQ_CATEGORY='"+eq_category+"'";
				this.createSQLQuery(sql).executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取设备可用数量、设备状态、设备使用状态
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-04-18 下午01:38:00
	 */
	public Equipment2DTO getEquipmentCount(String id) {
		Equipment2DTO equipmentDTO = null;
		try {
			conn = getConn();
			String sql = "select e.eq_validate_count,e.eq_status,e.eq_use_status"
					+ " from BASE_EQUIPMENT e" + " where e.id = '" + id + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				equipmentDTO = new Equipment2DTO();
				equipmentDTO.setEq_validate_count(rs.getInt(1));
				equipmentDTO.setEq_status(rs.getString(2));
				equipmentDTO.setEq_use_status(rs.getString(3));
			}
			closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipmentDTO;
	}

	

	/**
	 * 维修、借用、停用申请时，更新设备信息，将设备使用状态更新为“申请中”
	 * 
	 * @param baseEquipment --
	 *            设备信息
	 * @return
	 */
	/*public void updateEquipment(Equipment baseEquipment) {
		String hql = "update BaseEquipment2 set eq_no=?,eq_model=?,eq_sn=?,eq_use_status=? where id=?";
		this.createQuery(hql, baseEquipment.getEq_no(),
				baseEquipment.getEq_model(), baseEquipment.getEq_sn(), "04", // 04：申请中
				baseEquipment.getId()).executeUpdate();
	}*/

	/**
	 * 报废申请时，更新设备信息，将设备使用状态更新为“申请中”
	 * 
	 * @param baseEquipment --
	 *            设备信息
	 * @return
	 */
	/*public void updateScrapEquipment(Equipment baseEquipment) {
		String hql = "update BaseEquipment2 set eq_no=?,eq_model=?,eq_sn=?,eq_manufacturer=?,eq_accurate=?,eq_use_status=? where id=?";
		this.createQuery(hql, baseEquipment.getEq_no(),
				baseEquipment.getEq_model(), baseEquipment.getEq_sn(),
				baseEquipment.getEq_manufacturer(),
				baseEquipment.getEq_accurate(), "04", baseEquipment.getId())
				.executeUpdate();
	}*/

	/**
	 * 删除设备信息
	 * 
	 * @param ids
	 * @return
	 * @author GaoYa
	 * @date 2014-02-18 上午11:24:00
	 */
	public void delete(String ids) {
		String arr[] = ids.split(",");
		for (int i = 0; i < arr.length; i++) {
			String sql = "update TJY2_EQUIPMENT t set t.eq_isdelete = '1' where t.id= ?";
			this.createSQLQuery(sql, arr[i]).executeUpdate();
		}
	}

	/**
	 * 设备统计
	 * 
	 * @param startDate
	 *            入库时间（区间值，开始）
	 * @param endDate
	 *            入库时间（区间值，结束）
	 * @param minPrice
	 *            设备金额（区间值，最小）
	 * @param maxPrice
	 *            设备金额（区间值，最大）
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2013-11-25 上午11:15:00
	 */
	/*public Equipment2DTO queryCount(String startDate, String endDate,
			String minPrice, String maxPrice) {
		Equipment2DTO equipmentDTO = new Equipment2DTO();
		try {
			String sql = "SELECT ";
			// 设备数量（台）
			sql += queryCountSqlByEq_type("01", startDate, endDate, minPrice,
					maxPrice)
					+ " jy_count, ";
			sql += queryCountSqlByEq_type("03", startDate, endDate, minPrice,
					maxPrice)
					+ " bg_count, ";
			sql += queryCountSqlByEq_type("02", startDate, endDate, minPrice,
					maxPrice)
					+ " jl_count, ";
			sql += queryCountSqlByEq_category("01", "03", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_cy_count, ";
			sql += queryCountSqlByEq_category("01", "04", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_jd_count, ";
			sql += queryCountSqlByEq_category("01", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_nx_count, ";
			sql += queryCountSqlByEq_category("01", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_jz_count, ";
			sql += queryCountSqlByEq_category("01", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_aqf_count, ";
			sql += queryCountSqlByEq_category("03", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " bg_jj_count, ";
			sql += queryCountSqlByEq_category("03", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " bg_dz_count, ";
			// 设备价值（金额）
			sql += queryPriceSqlByEq_type("01", startDate, endDate, minPrice,
					maxPrice)
					+ "jy_amount, ";
			sql += queryPriceSqlByEq_type("03", startDate, endDate, minPrice,
					maxPrice)
					+ "bg_amount, ";
			sql += queryPriceSqlByEq_type("02", startDate, endDate, minPrice,
					maxPrice)
					+ "jl_amount, ";
			sql += queryPriceSqlByEq_category("01", "03", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_cy_amount, ";
			sql += queryPriceSqlByEq_category("01", "04", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_jd_amount, ";
			sql += queryPriceSqlByEq_category("01", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_nx_amount, ";
			sql += queryPriceSqlByEq_category("01", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_jz_amount, ";
			sql += queryPriceSqlByEq_category("01", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " jy_aqf_amount, ";
			sql += queryPriceSqlByEq_category("03", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " bg_jj_amount, ";
			sql += queryPriceSqlByEq_category("03", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " bg_dz_amount, ";
			// 完好在用数量
			sql += queryCountSqlByEq_Status("01", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " wh_jy_count, ";
			sql += queryCountSqlByEq_Status("03", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " wh_bg_count, ";
			sql += queryCountSqlByEq_Status("02", "01", startDate, endDate,
					minPrice, maxPrice)
					+ " wh_jl_count, ";
			sql += queryCountSqlByEq_Status("01", "03", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_cy_count, ";
			sql += queryCountSqlByEq_Status("01", "04", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_jd_count, ";
			sql += queryCountSqlByEq_Status("01", "02", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_nx_count, ";
			sql += queryCountSqlByEq_Status("01", "01", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_jz_count, ";
			sql += queryCountSqlByEq_Status("01", "05", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_aqf_count, ";
			sql += queryCountSqlByEq_Status("03", "01", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_jj_count, ";
			sql += queryCountSqlByEq_Status("03", "02", "01", startDate,
					endDate, minPrice, maxPrice)
					+ " wh_dz_count, ";
			// 待维修数量
			sql += queryCountSqlByEq_Status("01", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " dx_jy_count, ";
			sql += queryCountSqlByEq_Status("03", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " dx_bg_count, ";
			sql += queryCountSqlByEq_Status("02", "02", startDate, endDate,
					minPrice, maxPrice)
					+ " dx_jl_count, ";
			sql += queryCountSqlByEq_Status("01", "03", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_cy_count, ";
			sql += queryCountSqlByEq_Status("01", "04", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_jd_count, ";
			sql += queryCountSqlByEq_Status("01", "02", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_nx_count, ";
			sql += queryCountSqlByEq_Status("01", "01", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_jz_count, ";
			sql += queryCountSqlByEq_Status("01", "05", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_aqf_count, ";
			sql += queryCountSqlByEq_Status("03", "01", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_jj_count, ";
			sql += queryCountSqlByEq_Status("03", "02", "02", startDate,
					endDate, minPrice, maxPrice)
					+ " dx_dz_count, ";
			// 待报废数量
			sql += queryCountSqlByEq_Status("01", "03", startDate, endDate,
					minPrice, maxPrice)
					+ " df_jy_count, ";
			sql += queryCountSqlByEq_Status("03", "03", startDate, endDate,
					minPrice, maxPrice)
					+ " df_bg_count, ";
			sql += queryCountSqlByEq_Status("02", "03", startDate, endDate,
					minPrice, maxPrice)
					+ " df_jl_count, ";
			sql += queryCountSqlByEq_Status("01", "03", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_cy_count, ";
			sql += queryCountSqlByEq_Status("01", "04", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_jd_count, ";
			sql += queryCountSqlByEq_Status("01", "02", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_nx_count, ";
			sql += queryCountSqlByEq_Status("01", "01", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_jz_count, ";
			sql += queryCountSqlByEq_Status("01", "05", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_aqf_count, ";
			sql += queryCountSqlByEq_Status("03", "01", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_jj_count, ";
			sql += queryCountSqlByEq_Status("03", "02", "03", startDate,
					endDate, minPrice, maxPrice)
					+ " df_dz_count, ";
			// 已报废数量
			sql += queryCountSqlByEq_Status("01", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " yf_jy_count, ";
			sql += queryCountSqlByEq_Status("03", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " yf_bg_count, ";
			sql += queryCountSqlByEq_Status("02", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " yf_jl_count, ";
			sql += queryCountSqlByEq_Status("01", "03", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_cy_count, ";
			sql += queryCountSqlByEq_Status("01", "04", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_jd_count, ";
			sql += queryCountSqlByEq_Status("01", "02", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_nx_count, ";
			sql += queryCountSqlByEq_Status("01", "01", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_jz_count, ";
			sql += queryCountSqlByEq_Status("01", "05", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_aqf_count, ";
			sql += queryCountSqlByEq_Status("03", "01", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_jj_count, ";
			sql += queryCountSqlByEq_Status("03", "02", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_dz_count, ";
			// 已报废设备金额
			sql += queryPriceSqlByEq_Status("01", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " yf_jy_amount, ";
			sql += queryPriceSqlByEq_Status("03", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " yf_bg_amount, ";
			sql += queryPriceSqlByEq_Status("02", "05", startDate, endDate,
					minPrice, maxPrice)
					+ " yf_jl_amount, ";
			sql += queryPriceSqlByEq_Status("01", "03", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_cy_amount, ";
			sql += queryPriceSqlByEq_Status("01", "04", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_jd_amount, ";
			sql += queryPriceSqlByEq_Status("01", "02", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_nx_amount, ";
			sql += queryPriceSqlByEq_Status("01", "01", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_jz_amount, ";
			sql += queryPriceSqlByEq_Status("01", "05", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_aqf_amount, ";
			sql += queryPriceSqlByEq_Status("03", "01", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_jj_amount, ";
			sql += queryPriceSqlByEq_Status("03", "02", "05", startDate,
					endDate, minPrice, maxPrice)
					+ " yf_dz_amount, ";
			// 已停用数量
			sql += queryCountSqlByEq_Status("01", "04", startDate, endDate,
					minPrice, maxPrice)
					+ " ty_jy_count, ";
			sql += queryCountSqlByEq_Status("03", "04", startDate, endDate,
					minPrice, maxPrice)
					+ " ty_bg_count, ";
			sql += queryCountSqlByEq_Status("02", "04", startDate, endDate,
					minPrice, maxPrice)
					+ " ty_jl_count, ";
			sql += queryCountSqlByEq_Status("01", "03", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_cy_count, ";
			sql += queryCountSqlByEq_Status("01", "04", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_jd_count, ";
			sql += queryCountSqlByEq_Status("01", "02", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_nx_count, ";
			sql += queryCountSqlByEq_Status("01", "01", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_jz_count, ";
			sql += queryCountSqlByEq_Status("01", "05", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_aqf_count, ";
			sql += queryCountSqlByEq_Status("03", "01", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_jj_count, ";
			sql += queryCountSqlByEq_Status("03", "02", "04", startDate,
					endDate, minPrice, maxPrice)
					+ " ty_dz_count, ";
			// 需检定设备数量
			sql += queryCountSqlByIs_check("01", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_jy_count, ";
			sql += queryCountSqlByIs_check("03", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_bg_count, ";
			sql += queryCountSqlByIs_check("02", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_jl_count, ";
			sql += queryCountSqlByIs_check("01", "03", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_cy_count, ";
			sql += queryCountSqlByIs_check("01", "04", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_jd_count, ";
			sql += queryCountSqlByIs_check("01", "02", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_nx_count, ";
			sql += queryCountSqlByIs_check("01", "01", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_jz_count, ";
			sql += queryCountSqlByIs_check("01", "05", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_aqf_count, ";
			sql += queryCountSqlByIs_check("03", "01", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_jj_count, ";
			sql += queryCountSqlByIs_check("03", "02", "2", startDate, endDate,
					minPrice, maxPrice)
					+ " jd_dz_count, ";
			// 需校准设备数量
			sql += queryCountSqlByIs_check("01", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_jy_count, ";
			sql += queryCountSqlByIs_check("03", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_bg_count, ";
			sql += queryCountSqlByIs_check("02", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_jl_count, ";
			sql += queryCountSqlByIs_check("01", "03", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_cy_count, ";
			sql += queryCountSqlByIs_check("01", "04", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_jd_count, ";
			sql += queryCountSqlByIs_check("01", "02", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_nx_count, ";
			sql += queryCountSqlByIs_check("01", "01", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_jz_count, ";
			sql += queryCountSqlByIs_check("01", "05", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_aqf_count, ";
			sql += queryCountSqlByIs_check("03", "01", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_jj_count, ";
			sql += queryCountSqlByIs_check("03", "02", "1", startDate, endDate,
					minPrice, maxPrice)
					+ " jz_dz_count ";
			sql += " FROM DUAL ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 设备数量（台）
				equipmentDTO.setJy_count(rs.getInt("jy_count"));
				equipmentDTO.setBg_count(rs.getInt("bg_count"));
				equipmentDTO.setJl_count(rs.getInt("jl_count"));
				equipmentDTO.setJy_cy_count(rs.getInt("jy_cy_count"));
				equipmentDTO.setJy_jd_count(rs.getInt("jy_jd_count"));
				equipmentDTO.setJy_nx_count(rs.getInt("jy_nx_count"));
				equipmentDTO.setJy_jz_count(rs.getInt("jy_jz_count"));
				equipmentDTO.setJy_aqf_count(rs.getInt("jy_aqf_count"));
				equipmentDTO.setBg_jj_count(rs.getInt("bg_jj_count"));
				equipmentDTO.setBg_dz_count(rs.getInt("bg_dz_count"));
				equipmentDTO.setTotal_count(equipmentDTO.getJy_count()
						+ equipmentDTO.getBg_count()
						+ equipmentDTO.getJl_count());
				// 设备价值（金额）
				equipmentDTO.setJy_amount(rs.getFloat("jy_amount"));
				equipmentDTO.setBg_amount(rs.getFloat("bg_amount"));
				equipmentDTO.setJl_amount(rs.getFloat("jl_amount"));
				equipmentDTO.setJy_cy_amount(rs.getFloat("jy_cy_amount"));
				equipmentDTO.setJy_jd_amount(rs.getFloat("jy_jd_amount"));
				equipmentDTO.setJy_nx_amount(rs.getFloat("jy_nx_amount"));
				equipmentDTO.setJy_jz_amount(rs.getFloat("jy_jz_amount"));
				equipmentDTO.setJy_aqf_amount(rs.getFloat("jy_aqf_amount"));
				equipmentDTO.setBg_jj_amount(rs.getFloat("bg_jj_amount"));
				equipmentDTO.setBg_dz_amount(rs.getFloat("bg_dz_amount"));
				equipmentDTO.setTotal_amount(equipmentDTO.getJy_amount()
						+ equipmentDTO.getBg_amount()
						+ equipmentDTO.getJl_amount());
				// 完好在用数量
				equipmentDTO.setWh_jy_count(rs.getInt("wh_jy_count"));
				equipmentDTO.setWh_bg_count(rs.getInt("wh_bg_count"));
				equipmentDTO.setWh_jl_count(rs.getInt("wh_jl_count"));
				equipmentDTO.setWh_cy_count(rs.getInt("wh_cy_count"));
				equipmentDTO.setWh_jd_count(rs.getInt("wh_jd_count"));
				equipmentDTO.setWh_nx_count(rs.getInt("wh_nx_count"));
				equipmentDTO.setWh_jz_count(rs.getInt("wh_jz_count"));
				equipmentDTO.setWh_aqf_count(rs.getInt("wh_aqf_count"));
				equipmentDTO.setWh_jj_count(rs.getInt("wh_jj_count"));
				equipmentDTO.setWh_dz_count(rs.getInt("wh_dz_count"));
				equipmentDTO.setWh_total_count(equipmentDTO.getWh_jy_count()
						+ equipmentDTO.getWh_bg_count()
						+ equipmentDTO.getWh_jl_count());
				// 待维修数量
				equipmentDTO.setDx_jy_count(rs.getInt("dx_jy_count"));
				equipmentDTO.setDx_bg_count(rs.getInt("dx_bg_count"));
				equipmentDTO.setDx_jl_count(rs.getInt("dx_jl_count"));
				equipmentDTO.setDx_cy_count(rs.getInt("dx_cy_count"));
				equipmentDTO.setDx_jd_count(rs.getInt("dx_jd_count"));
				equipmentDTO.setDx_nx_count(rs.getInt("dx_nx_count"));
				equipmentDTO.setDx_jz_count(rs.getInt("dx_jz_count"));
				equipmentDTO.setDx_aqf_count(rs.getInt("dx_aqf_count"));
				equipmentDTO.setDx_jj_count(rs.getInt("dx_jj_count"));
				equipmentDTO.setDx_dz_count(rs.getInt("dx_dz_count"));
				equipmentDTO.setDx_total_count(equipmentDTO.getDx_jy_count()
						+ equipmentDTO.getDx_bg_count()
						+ equipmentDTO.getDx_jl_count());
				// 待报废数量
				equipmentDTO.setDf_jy_count(rs.getInt("df_jy_count"));
				equipmentDTO.setDf_bg_count(rs.getInt("df_bg_count"));
				equipmentDTO.setDf_jl_count(rs.getInt("df_jl_count"));
				equipmentDTO.setDf_cy_count(rs.getInt("df_cy_count"));
				equipmentDTO.setDf_jd_count(rs.getInt("df_jd_count"));
				equipmentDTO.setDf_nx_count(rs.getInt("df_nx_count"));
				equipmentDTO.setDf_jz_count(rs.getInt("df_jz_count"));
				equipmentDTO.setDf_aqf_count(rs.getInt("df_aqf_count"));
				equipmentDTO.setDf_jj_count(rs.getInt("df_jj_count"));
				equipmentDTO.setDf_dz_count(rs.getInt("df_dz_count"));
				equipmentDTO.setDf_total_count(equipmentDTO.getDf_jy_count()
						+ equipmentDTO.getDf_bg_count()
						+ equipmentDTO.getDf_jl_count());
				// 已报废数量
				equipmentDTO.setYf_jy_count(rs.getInt("yf_jy_count"));
				equipmentDTO.setYf_bg_count(rs.getInt("yf_bg_count"));
				equipmentDTO.setYf_jl_count(rs.getInt("yf_jl_count"));
				equipmentDTO.setYf_cy_count(rs.getInt("yf_cy_count"));
				equipmentDTO.setYf_jd_count(rs.getInt("yf_jd_count"));
				equipmentDTO.setYf_nx_count(rs.getInt("yf_nx_count"));
				equipmentDTO.setYf_jz_count(rs.getInt("yf_jz_count"));
				equipmentDTO.setYf_aqf_count(rs.getInt("yf_aqf_count"));
				equipmentDTO.setYf_jj_count(rs.getInt("yf_jj_count"));
				equipmentDTO.setYf_dz_count(rs.getInt("yf_dz_count"));
				equipmentDTO.setYf_total_count(equipmentDTO.getYf_jy_count()
						+ equipmentDTO.getYf_bg_count()
						+ equipmentDTO.getYf_jl_count());
				// 已报废设备金额
				equipmentDTO.setYf_jy_amount(rs.getFloat("yf_jy_amount"));
				equipmentDTO.setYf_bg_amount(rs.getFloat("yf_bg_amount"));
				equipmentDTO.setYf_jl_amount(rs.getFloat("yf_jl_amount"));
				equipmentDTO.setJy_cy_amount(rs.getFloat("yf_cy_amount"));
				equipmentDTO.setYf_jd_amount(rs.getFloat("yf_jd_amount"));
				equipmentDTO.setYf_nx_amount(rs.getFloat("yf_nx_amount"));
				equipmentDTO.setYf_jz_amount(rs.getFloat("yf_jz_amount"));
				equipmentDTO.setYf_aqf_amount(rs.getFloat("yf_aqf_amount"));
				equipmentDTO.setYf_jj_amount(rs.getFloat("yf_jj_amount"));
				equipmentDTO.setYf_dz_amount(rs.getFloat("yf_dz_amount"));
				equipmentDTO.setYf_total_amount(equipmentDTO.getYf_jy_amount()
						+ equipmentDTO.getYf_bg_amount()
						+ equipmentDTO.getYf_jl_amount());
				// 已停用数量
				equipmentDTO.setTy_jy_count(rs.getInt("ty_jy_count"));
				equipmentDTO.setTy_bg_count(rs.getInt("ty_bg_count"));
				equipmentDTO.setTy_jl_count(rs.getInt("ty_jl_count"));
				equipmentDTO.setTy_cy_count(rs.getInt("ty_cy_count"));
				equipmentDTO.setTy_jd_count(rs.getInt("ty_jd_count"));
				equipmentDTO.setTy_nx_count(rs.getInt("ty_nx_count"));
				equipmentDTO.setTy_jz_count(rs.getInt("ty_jz_count"));
				equipmentDTO.setTy_aqf_count(rs.getInt("ty_aqf_count"));
				equipmentDTO.setTy_jj_count(rs.getInt("ty_jj_count"));
				equipmentDTO.setTy_dz_count(rs.getInt("ty_dz_count"));
				equipmentDTO.setTy_total_count(equipmentDTO.getTy_jy_count()
						+ equipmentDTO.getTy_bg_count()
						+ equipmentDTO.getTy_jl_count());
				// 需检定设备数量
				equipmentDTO.setJd_jy_count(rs.getInt("jd_jy_count"));
				equipmentDTO.setJd_bg_count(rs.getInt("jd_bg_count"));
				equipmentDTO.setJd_jl_count(rs.getInt("jd_jl_count"));
				equipmentDTO.setJd_cy_count(rs.getInt("jd_cy_count"));
				equipmentDTO.setJd_jd_count(rs.getInt("jd_jd_count"));
				equipmentDTO.setJd_nx_count(rs.getInt("jd_nx_count"));
				equipmentDTO.setJd_jz_count(rs.getInt("jd_jz_count"));
				equipmentDTO.setJd_aqf_count(rs.getInt("jd_aqf_count"));
				equipmentDTO.setJd_jj_count(rs.getInt("jd_jj_count"));
				equipmentDTO.setJd_dz_count(rs.getInt("jd_dz_count"));
				equipmentDTO.setJd_total_count(equipmentDTO.getJd_jy_count()
						+ equipmentDTO.getJd_bg_count()
						+ equipmentDTO.getJd_jl_count());
				// 需校准设备数量
				equipmentDTO.setJz_jy_count(rs.getInt("jz_jy_count"));
				equipmentDTO.setJz_bg_count(rs.getInt("jz_bg_count"));
				equipmentDTO.setJz_jl_count(rs.getInt("jz_jl_count"));
				equipmentDTO.setJz_cy_count(rs.getInt("jz_cy_count"));
				equipmentDTO.setJz_jd_count(rs.getInt("jz_jd_count"));
				equipmentDTO.setJz_nx_count(rs.getInt("jz_nx_count"));
				equipmentDTO.setJz_jz_count(rs.getInt("jz_jz_count"));
				equipmentDTO.setJz_aqf_count(rs.getInt("jz_aqf_count"));
				equipmentDTO.setJz_jj_count(rs.getInt("jz_jj_count"));
				equipmentDTO.setJz_dz_count(rs.getInt("jz_dz_count"));
				equipmentDTO.setJz_total_count(equipmentDTO.getJz_jy_count()
						+ equipmentDTO.getJz_bg_count()
						+ equipmentDTO.getJz_jl_count());
			}
			closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipmentDTO;
	}*/
	
	/**
	 * 根据固化箱id获取固化箱内设备信息
	 * @param fk_uv_id -- 固化箱id
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-11-21 13:38:00
	 */
	@SuppressWarnings("unchecked")
	public List<Equipment> getEquipments(String fk_uv_id) {
		List<Equipment> list = new ArrayList<Equipment>();
		String hql = "from Equipment t where t.equipmentBox.id=? ";
		list = this.createQuery(hql, fk_uv_id).list();
		return list;
	}

	/*// 根据设备类别统计设备数量
	private String queryCountSqlByEq_type(String eq_type, String startDate,
			String endDate, String minPrice, String maxPrice) {
		String sql = "(select count(id) from BASE_EQUIPMENT where eq_type='"
				+ eq_type + "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、设备类型统计设备数量
	private String queryCountSqlByEq_category(String eq_type,
			String eq_category, String startDate, String endDate,
			String minPrice, String maxPrice) {
		String sql = "(select count(id) from BASE_EQUIPMENT where eq_type='"
				+ eq_type + "' and eq_category='" + eq_category + "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别统计设备价值
	private String queryPriceSqlByEq_type(String eq_type, String startDate,
			String endDate, String minPrice, String maxPrice) {
		String sql = "(select sum(eq_buy_price) from BASE_EQUIPMENT where eq_type='"
				+ eq_type
				+ "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、设备类型统计设备价值
	private String queryPriceSqlByEq_category(String eq_type,
			String eq_category, String startDate, String endDate,
			String minPrice, String maxPrice) {
		String sql = "(select sum(eq_buy_price) from BASE_EQUIPMENT where eq_type='"
				+ eq_type
				+ "' and eq_category='"
				+ eq_category
				+ "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、设备状态统计设备数量
	private String queryCountSqlByEq_Status(String eq_type, String eq_status,
			String startDate, String endDate, String minPrice, String maxPrice) {
		String sql = "(select count(id) from BASE_EQUIPMENT where eq_type='"
				+ eq_type + "' and eq_status='" + eq_status + "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、设备类型、设备状态统计设备数量
	private String queryCountSqlByEq_Status(String eq_type, String eq_category,
			String eq_status, String startDate, String endDate,
			String minPrice, String maxPrice) {
		String sql = "(select count(id) from BASE_EQUIPMENT where eq_type='"
				+ eq_type + "' and eq_category='" + eq_category
				+ "' and eq_status='" + eq_status + "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、设备状态统计设备价值
	private String queryPriceSqlByEq_Status(String eq_type, String eq_status,
			String startDate, String endDate, String minPrice, String maxPrice) {
		String sql = "(select sum(eq_buy_price) from BASE_EQUIPMENT where eq_type='"
				+ eq_type
				+ "' and eq_status='"
				+ eq_status
				+ "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、设备类型、设备状态统计设备价值
	private String queryPriceSqlByEq_Status(String eq_type, String eq_category,
			String eq_status, String startDate, String endDate,
			String minPrice, String maxPrice) {
		String sql = "(select sum(eq_buy_price) from BASE_EQUIPMENT where eq_type='"
				+ eq_type
				+ "' and eq_category='"
				+ eq_category
				+ "' and eq_status='"
				+ eq_status
				+ "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}*/

	/*// 根据设备类别、计量方式统计设备数量
	private String queryCountSqlByIs_check(String eq_type, String is_check,
			String startDate, String endDate, String minPrice, String maxPrice) {
		String sql = "(select count(id) from BASE_EQUIPMENT where eq_type='"
				+ eq_type + "' and is_check='" + is_check + "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}

	// 根据设备类别、设备类型、计量方式统计设备数量
	private String queryCountSqlByIs_check(String eq_type, String eq_category,
			String is_check, String startDate, String endDate, String minPrice,
			String maxPrice) {
		String sql = "(select count(id) from BASE_EQUIPMENT where eq_type='"
				+ eq_type + "' and eq_category='" + eq_category
				+ "' and is_check='" + is_check + "'"
				+ queryConditionSql(startDate, endDate, minPrice, maxPrice)
				+ ")";
		return sql;
	}

	// 设备统计查询条件SQL语句
	private String queryConditionSql(String startDate, String endDate,
			String minPrice, String maxPrice) {
		String sql = " and is_new='0' ";
		if (StringUtil.isNotEmpty(startDate)) {
			sql += " and eq_instock_date >= to_date('" + startDate
					+ "', 'yyyy-MM-dd')";
		}
		if (StringUtil.isNotEmpty(endDate)) {
			sql += " and eq_instock_date < to_date('" + endDate
					+ "', 'yyyy-MM-dd')";
		}
		if (StringUtil.isNotEmpty(minPrice)) {
			sql += " and eq_buy_price >= '" + minPrice + "'";
		}
		if (StringUtil.isNotEmpty(maxPrice) && !"0".equals(maxPrice)) {
			sql += " and eq_buy_price < '" + maxPrice + "'";
		}
		return sql;
	}*/
	
	/**
	 * 根据申请ID查询设备详情
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-19 上午10:49:00
	 */
    public List<Equipment> queryBaseEquipmentById(String id) {
    	List<Equipment> list = new ArrayList<Equipment>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from Equipment r where r.id=?";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }

    // 回写设备为入库状态,使用状态为空闲
 	public void updateInstock(String id,EquipInstockRecord equipInstockRecord,Equipment equipment) {
 		String updateSql = "update TJY2_EQUIPMENT  set EQ_INOUT_STATUS=?, EQ_USE_STATUS=?,EQ_USE_DEPARTMENT1_ID=?,EQ_USE_DEPARTMENT1=?,EQ_USER1_ID=?,EQ_USER1=?,EQ_RETURN_DATE=? where id=?";
 		String updateSql1 = "update TJY2_EQUIPMENT  set EQ_INOUT_STATUS=?, EQ_USE_STATUS=?,EQ_USE_DEPARTMENT_ID=?,EQ_USE_DEPARTMENT=?,EQ_USER_ID=?,EQ_USER=?,EQ_USE_DEPARTMENT1_ID=?,EQ_USE_DEPARTMENT1=?,EQ_USER1_ID=?,EQ_USER1=?,EQ_RETURN_DATE=? where id=?";
 		try {
 			Query sqlQuery = null;
 	 		if(equipInstockRecord.getInstock_type().equals("lygh")){
 	 			sqlQuery=getSession().createSQLQuery(updateSql1);
 	 			sqlQuery.setString(0, "yrk");//入库
 	 	 		sqlQuery.setString(1, "kx");//使用状态为空闲
 	 	 		sqlQuery.setString(2, equipInstockRecord.getInstock_position_id());//管理（使用）部门ID
 	 			sqlQuery.setString(3, equipInstockRecord.getInstock_position());//管理（使用）部门
 	 			sqlQuery.setString(4, equipInstockRecord.getInstock_manager_id());//管理（使用）人员ID
 	 			sqlQuery.setString(5, equipInstockRecord.getInstock_manager());//管理（使用）人员
 	 	 		sqlQuery.setString(6, equipInstockRecord.getInstock_position_id());//当前管理（使用）部门ID
 	 			sqlQuery.setString(7, equipInstockRecord.getInstock_position());//当前管理（使用）部门
 	 			sqlQuery.setString(8, equipInstockRecord.getInstock_manager_id());//当前管理（使用）人员ID
 	 			sqlQuery.setString(9, equipInstockRecord.getInstock_manager());//当前管理（使用）人员
 	 			sqlQuery.setDate(10, equipInstockRecord.getBack_time());//设备归还时间
 	 	 		sqlQuery.setString(11, id);
 	 		}else{
 	 			sqlQuery=getSession().createSQLQuery(updateSql);
 	 			sqlQuery.setString(0, "yrk");//入库
 	 	 		sqlQuery.setString(1, "kx");//使用状态为空闲
 	 	 		sqlQuery.setString(2, equipment.getEq_use_department_id());//当前管理（使用）部门ID
 	 			sqlQuery.setString(3, equipment.getEq_use_department());//当前管理（使用）部门
 	 			sqlQuery.setString(4, equipment.getEq_user_id());//当前管理（使用）人员ID
 	 			sqlQuery.setString(5, equipment.getEq_user());//当前管理（使用）人员
 	 			sqlQuery.setDate(6, equipInstockRecord.getBack_time());//设备归还时间
 	 	 		sqlQuery.setString(7, id);
 	 		}
 	 		sqlQuery.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
 	}
 	// 回写设备为出库状态，使用状态为在用,以及当前使用部门和使用人
 	public void updateOutstatus(String id,EquipOutstockRecord equipOutstockRecord) {
 		String updateSql = "update TJY2_EQUIPMENT  set EQ_INOUT_STATUS=?, EQ_USE_STATUS=?,EQ_USE_DEPARTMENT1_ID=?,EQ_USE_DEPARTMENT1=?,EQ_USER1_ID=?,EQ_USER1=?,EQ_RETURN_DATE=? where id=?";
 		String updateSql1 = "update TJY2_EQUIPMENT  set EQ_INOUT_STATUS=?, EQ_USE_STATUS=?,EQ_USE_DEPARTMENT_ID=?,EQ_USE_DEPARTMENT=?,EQ_USER_ID=?,EQ_USER=?,EQ_USE_DEPARTMENT1_ID=?,EQ_USE_DEPARTMENT1=?,EQ_USER1_ID=?,EQ_USER1=?,EQ_RETURN_DATE=? where id=?";
 		try {
 			Query sqlQuery = null;
			if(equipOutstockRecord.getOutstock_type().equals("02")){
				sqlQuery = getSession().createSQLQuery(updateSql1);
				sqlQuery.setString(0, "yck");//出库
				sqlQuery.setString(1, "zy");//使用状态为在用
				sqlQuery.setString(2, equipOutstockRecord.getOutstock_position_id());//管理（使用）部门ID
				sqlQuery.setString(3, equipOutstockRecord.getOutstock_position());//管理（使用）部门
				sqlQuery.setString(4, equipOutstockRecord.getBorrow_draw_id());//管理（使用）人员ID
				sqlQuery.setString(5, equipOutstockRecord.getBorrow_draw_by());//管理（使用）人员
				sqlQuery.setString(6, equipOutstockRecord.getOutstock_position_id());//当前管理（使用）部门ID
				sqlQuery.setString(7, equipOutstockRecord.getOutstock_position());//当前管理（使用）部门
				sqlQuery.setString(8, equipOutstockRecord.getBorrow_draw_id());//当前管理（使用）人员ID
				sqlQuery.setString(9, equipOutstockRecord.getBorrow_draw_by());//当前管理（使用）人员
				sqlQuery.setDate(10, equipOutstockRecord.getReturn_date());//设备归还时间
				sqlQuery.setString(11, id);
			}else if(equipOutstockRecord.getOutstock_type().equals("01")){
				sqlQuery = getSession().createSQLQuery(updateSql);
				sqlQuery.setString(0, "yck");//出库
				sqlQuery.setString(1, "zy");//使用状态为在用
				sqlQuery.setString(2, equipOutstockRecord.getOutstock_position_id());//当前管理（使用）部门ID
				sqlQuery.setString(3, equipOutstockRecord.getOutstock_position());//当前管理（使用）部门
				sqlQuery.setString(4, equipOutstockRecord.getBorrow_draw_id());//当前管理（使用）人员ID
				sqlQuery.setString(5, equipOutstockRecord.getBorrow_draw_by());//当前管理（使用）人员
				sqlQuery.setDate(6, equipOutstockRecord.getReturn_date());//设备归还时间
				sqlQuery.setString(7, id);
			}
			sqlQuery.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	//启用设备，修改设备状态
 	public void start(String id) {
 		String uppdateSql = "update TJY2_EQUIPMENT  set eq_status=?,eq_use_status=?  where id=?";
 		Query sqlQuery = getSession().createSQLQuery(uppdateSql);
 		sqlQuery.setString(0, "qy");//启用
 		sqlQuery.setString(1, "kx");//使用状态为空闲
 		sqlQuery.setString(2, id);
 		sqlQuery.executeUpdate();
 	}
 	//封存设备，修改设备状态
 	public void sealed(String id) {
 		String uppdateSql = "update TJY2_EQUIPMENT  set eq_status=?, eq_use_status=? where id=?";
 		Query sqlQuery = getSession().createSQLQuery(uppdateSql);
 		sqlQuery.setString(0, "fc");//封存
 		sqlQuery.setString(1, "ty");//使用状态为停用
 		sqlQuery.setString(2, id);
 		sqlQuery.executeUpdate();
 	}
 	//设备报废，修改设备状态
 	public void scraped(String id) {
 		String uppdateSql = "update TJY2_EQUIPMENT  set eq_status=?,eq_use_status=? where id=?";
 		Query sqlQuery = getSession().createSQLQuery(uppdateSql);
 		sqlQuery.setString(0, "bf");//报废
 		sqlQuery.setString(1, "ty");//使用状态为停用
 		sqlQuery.setString(2, id);
 		sqlQuery.executeUpdate();
 	}
 	//通过barcode查询设备
 	public List<Equipment> searchByBarcode(String barcode) {
    	String hql = "from Equipment r where r.barcode=?";
    	List<Equipment> baseEquipment2List = this.createQuery(hql, barcode).list();
    	return baseEquipment2List;
 	}
 	//通过equip_id查询设备所有出入库记录
 	public List searchRecord(String equip_id) {
    	String sql = "select * from (select '0' as inoutClass,t1.instock_type as inoutType,t1.instock_date as inoutDate,t1.back_user_name as relatedName from TJY2_EQUIP_INSTOCK t1 "+
    			"where t1.id in (select t.instock_id from TJY2_EQUIPMENT_CENTER t where t.EQUIP_ID ='"+equip_id+"') union "+
    			"select '1' as inoutClass,t2.outstock_type as inoutType,t2.outstock_date as inoutDate,t2.borrow_draw_by as relatedName from TJY2_EQUIP_OUTSTOCK t2 "+
    			"where t2.id in (select t.instock_id from TJY2_EQUIPMENT_CENTER t where t.EQUIP_ID ='"+equip_id+"'))";
    	return this.createSQLQuery(sql).list();
 	}
	// 获取数据库连接
	public Connection getConn() {
		try {
			conn = Factory.getDB().getConnetion();
		} catch (Exception e) {
			logger.error("获取数据库连接失败：" + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭连接
	public void closeConn() {
		try {
			if (null != rs)
				rs.close();
			if (null != pstmt)
				pstmt.close();
			if (null != conn)
				conn.close();
		} catch (SQLException e) {
			logger.error("关闭数据库连接错误：" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据卡片编号查询设备档案信息
	 * @author QuincyXu
	 * @date 2016年7月14日19:06:45
	 * 
	 */
	public List<Equipment> getByCardNo(String card_no,String eq_category) {
		List<Equipment> list = new ArrayList<Equipment>();
		try {
			String hql = "from Equipment t where t.card_no=? and t.eq_category=? and t.eq_isdelete='0'";	
			list = this.createQuery(hql, card_no, eq_category).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据设备编号查询设备档案信息
	 * @author QuincyXu
	 * @date 2016年7月14日19:07:08
	 * 
	 */
	public List<Equipment> getByEqNo(String eq_no,String eq_category) {
		List<Equipment> list = new ArrayList<Equipment>();
		try {
			String hql = "from Equipment t where t.eq_no=?  and t.eq_category=? and t.eq_isdelete='0' ";	
			list = this.createQuery(hql, eq_no, eq_category).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
