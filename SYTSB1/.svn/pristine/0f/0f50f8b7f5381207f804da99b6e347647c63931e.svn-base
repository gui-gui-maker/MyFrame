package com.lsts.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;
import com.lsts.employee.bean.EmployeeDTO;

/**
 * 人员持证情况数据处理对象
 * @ClassName EmployeeCertDao
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-03-03 下午04:56:00
 */
@Repository("employeeCertDao")
public class EmployeeCertDao extends EntityDaoImpl<EmployeeCert> {
	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集

	/**
	 * 根据基本信息ID查询持证情况
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-03 下午05:00:00
	 */
	@SuppressWarnings("unchecked")
    public List<EmployeeCert> queryEmployeeCertByBasicId(String id) {
    	List<EmployeeCert> list = new ArrayList<EmployeeCert>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from EmployeeCert c where c.employee.id=? and c.status='0'";
    			list = this.createQuery(hql, id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	
	/**
	 * 删除持证情况
	 * @param ids
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-03 下午05:10:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
        	// super.removeById(arr[i]);	//删除人员持证情况
            this.createQuery("delete from EmployeeCert where id=?", arr[i]).executeUpdate();     
        }
    }
    
    /**
	 *人员证书预警
	 * @return 
	 * @author GaoYa
	 * @date 2014-04-17 下午05:01:00
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeDTO> getEmployeeWarnList() {
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		try {
			conn = getConn();
			String sql = "select e.name,o.ORG_NAME,c.employee_id,(c.cert_end_date-sysdate) as warn_days"
					+ " from employee e,employee_cert c, sys_org o"
					+ " where e.id(+)=c.employee_id and e.org_id = o.id and c.status='0' and c.cert_end_date-sysdate<=180";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setName(rs.getString(1));
				employeeDTO.setOrg_name(rs.getString(2));
				employeeDTO.setEmployee_id(rs.getString(3));
				employeeDTO.setWarn_days(rs.getFloat(4));
				list.add(employeeDTO);
			}
			closeConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
			/*if (null != rs)
				rs.close();
			if (null != pstmt)
				pstmt.close();
			if (null != conn)
				conn.close();*/
			Factory.getDB().freeConnetion(conn);	// 释放连接
		} catch (Exception e) {
			logger.error("关闭数据库连接错误：" + e.getMessage());
			e.printStackTrace();
		}
	}
}
