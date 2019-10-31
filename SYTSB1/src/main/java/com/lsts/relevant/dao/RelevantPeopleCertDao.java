package com.lsts.relevant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.relevant.bean.RelevantPeopleCert;
import com.lsts.relevant.bean.RelevantPeopleDTO;

/**
 * 特种作业人员持证情况数据处理对象
 * @ClassName RelevantPeopleCertDao
 * @JDK 1.6
 * @author GaoYa
 * @date 2014-02-13 下午04:50:00
 */
@Repository("relevantPeopleCertDao")
public class RelevantPeopleCertDao extends EntityDaoImpl<RelevantPeopleCert> {
	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集

	/**
	 * 根据基本信息ID查询持证情况
	 * @param id
	 * @return 
	 * @author GaoYa
	 * @date 2014-02-14 上午11:21:00
	 */
	@SuppressWarnings("unchecked")
    public List<RelevantPeopleCert> queryRelevantPeopleCertByBasicId(String id) {
    	List<RelevantPeopleCert> list = new ArrayList<RelevantPeopleCert>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			String hql = "from RelevantPeopleCert c where c.relevantPeople.id=?";
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
	 * @date 2014-02-14 上午11:27:00
	 */
    public void delete(String ids) {
        String arr[] = ids.split(",");
        for (int i = 0; i < arr.length; i++) {
        	// super.removeById(arr[i]);	//删除特种作业人员基本信息
            this.createQuery("delete from RelevantPeopleCert where id=?", arr[i]).executeUpdate();     
        }
    }
    
    /**
	 * 特种设备从业人员证书预警
	 * @return 
	 * @author GaoYa
	 * @date 2014-04-18 上午08:50:00
	 */
	@SuppressWarnings("unchecked")
	public List<RelevantPeopleDTO> getRelevantPeopleWarnList() {
		List<RelevantPeopleDTO> list = new ArrayList<RelevantPeopleDTO>();
		try {
			conn = getConn();
			String sql = "select b.people_name,b.work_com_name,c.fk_people_id,(c.cert_end_date-sysdate) as warn_days"
					+ " from TZSB_RELEVANT_PEOPLE b,TZSB_RELEVANT_PEOPLE_CERT c"
					+ " where b.id(+)=c.fk_people_id and c.cert_end_date-sysdate<=180";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RelevantPeopleDTO relevantPeopleDTO = new RelevantPeopleDTO();
				relevantPeopleDTO.setPeople_name(rs.getString(1));
				relevantPeopleDTO.setWork_com_name(rs.getString(2));
				relevantPeopleDTO.setFk_people_id(rs.getString(3));
				relevantPeopleDTO.setWarn_days(rs.getFloat(4));
				list.add(relevantPeopleDTO);
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
