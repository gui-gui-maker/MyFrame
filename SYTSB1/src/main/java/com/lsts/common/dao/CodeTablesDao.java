package com.lsts.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.utils.StringUtil;

/**
 * 数据字典数据处理对象
 * @ClassName CodeTablesDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-11 上午09:38:00
 */
@Repository("codeTablesDao")
public class CodeTablesDao extends EntityDaoImpl<CodeTable> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集
	
	/**
	 * 根据码表编码获取码表ID
	 * @param code -- 码表编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getCodeTableByCode(String code){
		String value="";
		String hql = "select id from PUB_CODE_TABLE where code='"+code+"'";
		List list = this.createSQLQuery(hql).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据码表编码、码值获取对应的name
	 * @param tcode -- 码表编码
	 * @param value -- 码值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getValueByCode(String tcode, String value){
		String name="";
		String hql = "select t.name from pub_code_table_values t,PUB_CODE_TABLE t1 where t.code_table_id=t1.id and t1.code=? and t.value=?";
		List list = this.createSQLQuery(hql, tcode, value).list(); 
		if(list.size()>0){
			name = String.valueOf(list.get(0));
		}
		return name;
	}
	
	/**
	 * 根据码表编码、码值name获取对应的value
	 * @param tcode -- 码表编码
	 * @param name -- 码值name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getValueByName(String tcode, String name){
		String value="";
		String hql = "select t.value from pub_code_table_values t,PUB_CODE_TABLE t1 where t.code_table_id=t1.id and t1.code=? and t.name=?";
		List list = this.createSQLQuery(hql, tcode, name).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
	}
	
	/**
	 * 根据码表编码、码值获取对应的name
	 * @param tcode -- 码表编码
	 * @param type 	-- 码值前缀
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDevice_types(String tcode, String type){
		Map<String, Object> device_types = new HashMap<String, Object>();
		String hql = "select t.name,t.value from pub_code_table_values t,PUB_CODE_TABLE t1 where t.code_table_id=t1.id and t1.code=? ";
		if (StringUtil.isNotEmpty(type)) {
			hql += " and t.value like '"+type.trim()+"%'";
		}
		List list = this.createSQLQuery(hql, tcode).list(); 
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				device_types.put(String.valueOf(obj[0]), obj[1]);
			}
		}
		return device_types;
	}
    
    /**
	 * 根据码表编码、码值获取对应的name
	 * @param tcode -- 码表编码
	 * @param value -- 码值
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-07 上午11:47:00
	 */
	@SuppressWarnings("unchecked")
	public String queryValueByCode(String tcode, String value){
		String ctname = "";
    	try {
    			conn = getConn();
    			String sql = "select t.name as ctname from pub_code_table_values t,PUB_CODE_TABLE t1 where t.code_table_id=t1.id and t1.code='"+tcode+"' and t.value='"+value+"'";
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			while (rs.next()){
    				String name = rs.getString("ctname");
    				ctname += StringUtil.isNotEmpty(name)?name:"";
    			}
    			closeConn();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ctname;
	}
    
	/**
	 * 根据设备所在地编码获取设备所在地名称
	 * 510100 -- 四川省
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getDeviceAreaName(String device_area_code){
		String value="";
		String hql = "select t.REGIONAL_NAME from BASE_ADMINISTRATIVE_DIVISIONS t where t.parent_id='510100' and t.regional_code='"+device_area_code+"'";
		List list = this.createSQLQuery(hql).list(); 
		if(list.size()>0){
			value = String.valueOf(list.get(0));
		}
		return value;
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
