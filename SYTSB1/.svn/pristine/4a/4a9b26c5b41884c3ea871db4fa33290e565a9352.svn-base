package com.scts.maintenance.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.scts.maintenance.bean.MaintenanceInfo;
import com.scts.maintenance.bean.MaintenanceInfoDTO;

/**
 * 系统更新维护日志明细表数据处理对象
 * @ClassName MaintenanceInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-08 14:52:00
 */
@Repository("maintenanceInfoDao")
public class MaintenanceInfoDao extends EntityDaoImpl<MaintenanceInfo> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集

	@SuppressWarnings("unchecked")
	public List<MaintenanceInfo> getInfos(
			String maintenance_id) {
		List<MaintenanceInfo> list = new ArrayList<MaintenanceInfo>();
		String hql = "from MaintenanceInfo i where i.maintenance.id=? and i.data_status != '99'";
		list = this.createQuery(hql, maintenance_id).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<MaintenanceInfo> getInfosByAdvaceUser(
			String user_id) {
		List<MaintenanceInfo> list = new ArrayList<MaintenanceInfo>();
		String hql = "from MaintenanceInfo i where i.advance_user_id=? and i.data_status != '99'";
		list = this.createQuery(hql, user_id).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<MaintenanceInfo> queryInfos(
			String info_id) {
		List<MaintenanceInfo> list = new ArrayList<MaintenanceInfo>();
		String hql = "from MaintenanceInfo i where i.id=? and i.data_status != '99'";
		list = this.createQuery(hql, info_id).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<MaintenanceInfo> getInfosByTaskId(
			String fk_func_task_id) {
		List<MaintenanceInfo> list = new ArrayList<MaintenanceInfo>();
		String hql = "from MaintenanceInfo i where i.fk_func_task_id=? and i.data_status = '0'";
		list = this.createQuery(hql, fk_func_task_id).list();
		return list;
	}
	
	/**
	 * 获取已发布的系统建设台账信息
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2015-09-18 下午06:45:00
	 */
	@SuppressWarnings("unchecked")
	public List<MaintenanceInfoDTO> queryList() {
		List<MaintenanceInfoDTO> list = new ArrayList<MaintenanceInfoDTO>();
		try {
			conn = getConn();
			String sql = "select t.id,t.info_type,t.priority,t.pro_desc,to_char(t.publish_date,'yyyy-MM-dd'),t.func_name,to_char(t.develop_end_date,'yyyy-MM-dd') "
					+ " from TZSB_MAINTENANCE_INFO t"
					+ " where t.data_status = '4'"	// 4：已发布
					+ " and t.fk_maintenance_id is not null "
					+ "order by t.develop_end_date desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				MaintenanceInfoDTO maintenanceInfoDTO = new MaintenanceInfoDTO();
				maintenanceInfoDTO.setId(rs.getString(1));
				maintenanceInfoDTO.setInfo_type(rs.getString(2));
				maintenanceInfoDTO.setPriority(rs.getString(3));
				maintenanceInfoDTO.setPro_desc(rs.getString(4));
				maintenanceInfoDTO.setPublish_date(rs.getDate(5));
				maintenanceInfoDTO.setFunc_name(rs.getString(6));
				maintenanceInfoDTO.setDevelop_end_date(rs.getDate(7));
				list.add(maintenanceInfoDTO);
			}
			closeConn();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.toString());
		}
		return list;
	}
	
	/**
	 * 获取业务流水号后三位序号
	 * @param sn_pre -- 业务流水号前缀
	 * @return 
	 * @author GaoYa
	 * @date 2015-10-08 下午01:22:00
	 */
	@SuppressWarnings("unchecked")
	public String queryNextSn(String sn_pre){
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num" +
        " from TZSB_MAINTENANCE_INFO t" +
        " where t.sn like '" + sn_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0)+"";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum(Integer.parseInt(curNum)+1);
			}
		}
		return nextNum;
	}
	
	/**
	 * 获取业务流水号后三位序号
	 * @param sn_pre -- 业务流水号前缀
	 * @return 
	 * @author GaoYa
	 * @date 2017-07-27 下午04:03:00
	 */
	@SuppressWarnings("unchecked")
	public String queryRecordNextSn(String sn_pre){
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num" +
        " from TZSB_UPDATE_RECORD_INFO t" +
        " where t.sn like '" + sn_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0)+"";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum(Integer.parseInt(curNum)+1);
			}
		}
		return nextNum;
	}

	// 生成3位序号
	private String getCountNum(int count_num){
        String nextNum = "";
       if((0 < count_num) && (count_num < 10))
    	   nextNum = "00" + count_num;
       if((9 < count_num) && (count_num < 100))
    	   nextNum = "0" + count_num;
       else if(count_num > 99)
    	   nextNum = String.valueOf(count_num);
       return  nextNum;
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
    
	@SuppressWarnings("unchecked")
	public List<Object> getInfosByAdvaceOrg(
			String org_id) {
		List<Object> list = new ArrayList<Object>();
		String hql = "select i.pro_desc, to_char(i.advance_date,'yyyy-MM-dd') advance_date, ROWNUM RN from TZSB_MAINTENANCE_INFO i, employee e "
				+ "where i.advance_user_id = e.id and i.data_status != '99' and ROWNUM <= 10";
		if(org_id!=null){
			hql = hql +" and e.org_id = '"+org_id+"'";
		}
		list = this.createSQLQuery(hql).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public int getCountByAdvaceOrg(
			String org_id) {
		int result = 0;
		List<Object> list = new ArrayList<Object>();
		String hql = " select COUNT(I.ID) cc from TZSB_MAINTENANCE_INFO i, employee e where i.advance_user_id = e.id and i.data_status != '99'";
		if(org_id!=null){
			hql = hql +" and e.org_id = '"+org_id+"'";
		}
		list = this.createSQLQuery(hql).list();
		if(list.size()>0&&list.get(0)!=null){
			result = Integer.parseInt(list.get(0).toString());
		}
		return result;
	}
    
}
