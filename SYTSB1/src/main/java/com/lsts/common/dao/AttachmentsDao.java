package com.lsts.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.utils.StringUtil;

@Repository("attachmentsDao")
public class AttachmentsDao extends EntityDaoImpl<Attachment> {

	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集
    
    /**
	 * 获取附件路径
	 * @param businessId -- 业务ID
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-07 上午11:47:00
	 */
	@SuppressWarnings("unchecked")
	public String queryByBusinessId(String businessId){
		String filePath = "";
    	try {
    		if (StringUtil.isNotEmpty(businessId)) {
    			conn = getConn();
    			String sql = "select a.FILE_PATH as filepath from PUB_ATTACHMENT a where a.BUSINESS_ID='" + businessId + "' order by a.UPLOAD_TIME desc";
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			while (rs.next()){
    				String file_path = rs.getString("filepath");
    				if (filePath.length()>0) {
    					return filePath;
    					//filePath += ",";
					}
    				filePath += StringUtil.isNotEmpty(file_path)?file_path:"";
    			}
    			closeConn();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return filePath;
	}
	
	/**
	 * 获取原始记录手写签字图片路径
	 * @param businessId -- 业务ID
	 * @return 
	 * @author GaoYa
	 * @date 2016-04-01 下午20:47:00
	 */
	@SuppressWarnings("unchecked")
	public String querySignPicByBusinessId(String businessId){
		String filePath = "";
    	try {
    		if (StringUtil.isNotEmpty(businessId)) {
    			conn = getConn();
    			String sql = "select a.FILE_PATH as filepath from PUB_ATTACHMENT a where a.BUSINESS_ID='" + businessId + "' and a.JY_PIC_CATEGORY='EXAMINE_NAME' order by a.UPLOAD_TIME desc";
    			pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			while (rs.next()){
    				String file_path = rs.getString("filepath");
    				if (filePath.length()>0) {
    					return filePath;
    					//filePath += ",";
					}
    				filePath += StringUtil.isNotEmpty(file_path)?file_path:"";
    			}
    			closeConn();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return filePath;
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
