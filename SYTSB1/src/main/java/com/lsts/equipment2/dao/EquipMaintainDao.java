package com.lsts.equipment2.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.EquipMaintain;
import com.lsts.inspection.bean.FlowInfoDTO;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName Tjy2EquipMaintainDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("equipMaintainDao")
public class EquipMaintainDao extends EntityDaoImpl<EquipMaintain> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集
	//通过业务ID查询流程主键ID和TITLE
    @SuppressWarnings("unchecked")
    public List<FlowInfoDTO> queryMainId(HttpServletRequest request, String id) {
		List<FlowInfoDTO> list = new ArrayList<FlowInfoDTO>();
    	try {
    		if (StringUtil.isNotEmpty(id)) {
    			conn = getConn();
    			String sql = "select t.ID,t.TITLE from v_pub_worktask t where t.SERVICE_ID = '"+id+"' and t.STATUS='0'";
    			System.out.println(sql.toString());
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			while (rs.next()){
    				FlowInfoDTO flowInfoDTO = new FlowInfoDTO();
    				flowInfoDTO.setId(rs.getString(1));
    				flowInfoDTO.setTitle(rs.getString(2));
    				list.add(flowInfoDTO);
    			}
    			if(list.size()<=0){
    				String sql1 = "select t.ID,t.TITLE from v_pub_worktask_add_position t where t.SERVICE_ID = '"+id+"' and t.STATUS='0'";
        			System.out.println(sql.toString());
        			pstmt = conn.prepareStatement(sql1);
        			rs = pstmt.executeQuery();
        			while (rs.next()){
        				FlowInfoDTO flowInfoDTO = new FlowInfoDTO();
        				flowInfoDTO.setId(rs.getString(1));
        				flowInfoDTO.setTitle(rs.getString(2));
        				list.add(flowInfoDTO);
        			}
    			}
    			closeConn();
			}
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
        	Factory.getDB().freeConnetion(conn);	// 释放连接
        } catch (Exception e) {
        	logger.error("关闭数据库连接错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}