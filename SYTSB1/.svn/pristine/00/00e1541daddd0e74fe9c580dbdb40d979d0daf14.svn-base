package com.lsts.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.IdFormat;
import com.lsts.finance.bean.CwBankDetail;

/**
 *银行转账明细Dao
 *
 * @author dxf
 *
 * @date 2015年10月16日
 */
@Repository("cwBankDetailDao")
public class CwBankDetailDao extends EntityDaoImpl<CwBankDetail> {
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集
	
	/**获取前一个月的转账记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CwBankDetail> getCwBankDetailM() {
		String hql = "from CwBankDetail t where t.jyTime>add_months(sysdate,-1)";
		return createQuery(hql).list();
		}
	
	/**
	 * 根据银行转账ids获取银行转账信息
	 * @param ids -- 银行转账id字符串
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-18 上午10:57:00
	 */
	@SuppressWarnings("unchecked")
	public List<CwBankDetail> queryBankInfos(String ids) throws Exception{
		ids = IdFormat.formatIdStr(ids);
		String hql = "from CwBankDetail t where t.id in ("
				+ ids
				+ ") order by t.jyTime asc";
		return this.createQuery(hql).list();
	}
	
	/**
	 * 根据银行转账ids获取转账剩余金额
	 * @param ids -- 银行转账id字符串
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2015-12-03 上午08:56:00
	 */
	@SuppressWarnings("unchecked")
	public double queryBankMoney(String ids) throws Exception{
		double money = 0;
		ids = IdFormat.formatIdStr(ids);
		String sql = "select sum(t.REST_MONEY) from TJY2_CW_BANK_DETAIL t where t.id in ("
				+ ids
				+ ")";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			money = list.get(0)!=null?Double.valueOf(list.get(0)+""):0;
		}
		return money;
	}
	
	/**根据银行转账id获取收费明细
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> queryPayDetails(String id) {
			/*String sql="select b.id,c.invoice_date,i.receive_man_name,i.pay_man_name,p.cur_used_money,i.invoice_no"
					+" from tjy2_cw_bank_detail b,tjy2_cw_bank2pay p,TZSB_INSPECTION_PAY_INFO i,tjy2_cw_invoice_f c"
					+" where b.id=p.fk_cw_bank_id"
					+" and i.id=p.fk_inspection_pay_id"
					+" and c.invoice_code=i.invoice_no"
					+" and b.id=? order by i.created_date asc";
		String sql = "select b.id,l.op_time,l.op_user_name,l.op_action,l.op_remark"
					+" from sys_log l,tjy2_cw_bank_detail b"
					+" where l.business_id=b.id "
		            //+" and l.op_action in('报告收费，使用银行转账','取消收费，取消银行转账','修改收费，修改银行转账','报告开票，使用银行转账','取消开票，取消银行转账','修改开票，修改银行转账') "
		            +" and b.id=?"
		            + "order by l.op_time asc";*/
		String sql = "select b.id,i.created_date,i.receive_man_name,p.cur_used_money"
				+" from TJY2_CW_BANK2PAY p,tjy2_cw_bank_detail b,TZSB_INSPECTION_PAY_INFO i"
				+" where p.fk_cw_bank_id=b.id and p.fk_inspection_pay_id=i.id"
	            +" and b.id=?"
	            + "order by i.created_date desc";
		
		
			return createSQLQuery(sql,id).list();
		}
	
	/**根据银行转账id获取退款明细
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> queryBackDetails(String id) {
			String sql="select t.id,t.operator_time,t.operator_name,t.fefund_money,t.unit_name,t.fefund_reason" 
					+" from TJY2_CW_BANK_FEFUND t where t.fk_bank_detail_id=? and (t.data_status ='2' or t.data_status is null) order by t.operator_time desc";
			return createSQLQuery(sql,id).list();
		}
	
	/**删除信息
	 * @param ids
	 */
	public void delete(String ids) {
		String arr[] = ids.split(",");
		for (int i = 0; i < arr.length; i++) {
			super.removeById(arr[i]); // 删除信息
		}
	}
	/**
	 * 根据ID获取数据
	 * @param bankDetailId
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public Map<String, String> queryBankDetailById(String bankDetailId){
    	Map<String, String> dataMap = new HashMap<String, String>();
    	String sql = "select * from TJY2_CW_BANK_DETAIL t where t.id='"+bankDetailId+"'"; 
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
        	rs = pstmt.executeQuery();
          	ResultSetMetaData rsmd = rs.getMetaData();
          	int columnCount = rsmd.getColumnCount();
         	while (rs.next()) {
            	for (int i = 1; i <= columnCount; i++) {
            		String column_name = rsmd.getColumnName(i);
            		String column_value = rs.getString(column_name);
            		dataMap.put(column_name.toLowerCase(), column_value);
            	}
        	}     
        } catch (Exception e) {
    
            e.printStackTrace();
        }
        closeConn();
        return dataMap;
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