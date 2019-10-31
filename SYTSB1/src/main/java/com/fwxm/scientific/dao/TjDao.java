package com.fwxm.scientific.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fwxm.scientific.bean.TjDTO;
import com.khnt.base.Factory;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.CwFytjDTO;

/**
 *财务统计Dao
 *
 * @author dxf
 *
 * @date 2015年10月16日
 */
@Repository("tjDao")
public class TjDao extends HibernateDaoSupport {

	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集
	
	/**
	 * 获取科研项目统计数据
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param org_id
	 *            部门ID
	 * 
	 */
	public List<TjDTO> getKyData(String startDate,
			String endDate, String org_id,String userName) {
		List<TjDTO> list = new ArrayList<TjDTO>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtil.isEmpty(startDate)) {
			//初始开始时间为当年的第一天
			startDate = format.format(getCurrYearFirst());
		}
		if (StringUtil.isEmpty(endDate)) {
			//初始结束时间为系统当前时间
			startDate = format.format(new Date());
		}
		try {
			String sql = "select project_name,project_department,project_department_id,start_date,end_date,sum(fy) fy,sum(lw) lw,sum(zl) zl from (  "+
                         "(select t.project_name,t.project_department,t.project_department_id,t.start_date,t.end_date,sum(t1.CL_HJ_JEXJ) fy, 0 lw,0 zl  "+
                         " from TJY2_SCIENTIFIC_RESEARCH t, TJY2_CLFBXD t1  "+
                         " where t.id = t1.ITEM_ID(+) and t.status not in('0','1','2','3','4') group by t.project_name,t.project_department,t.project_department_id,t.start_date,t.end_date)  "+
                        "  union all (select t.project_name,t.project_department,t.project_department_id,t.start_date,t.end_date,0 fy, count(1) lw,0 zl from TJY2_SCIENTIFIC_RESEARCH t, TJY2_SCIENTIFIC_PAPER t2  "+
                       "  where t.id = t2.fk_tjy2_scientific_id and t.status not in('0','1','2','3','4')  group by t.project_name,t.project_department,t.project_department_id,t.start_date,t.end_date)  "+
                       "  union all (select t.project_name,t.project_department,t.project_department_id,t.start_date,t.end_date,0 fy, 0 lw,count(1) zl from TJY2_SCIENTIFIC_RESEARCH t, TJY2_SCIENTIFIC_PATENT t3  "+
                       "    where t.id = t3.fk_tjy2_scientific_id and t.status not in('0','1','2','3','4')  group by t.project_name,t.project_department,t.project_department_id,t.start_date,t.end_date)) c where 1=1  ";
			if (StringUtil.isNotEmpty(endDate) && StringUtil.isNotEmpty(startDate)) {
				sql += " and end_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') "+
          			     " and start_date>=to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS') ";
			}      
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				sql += " and project_name = '"+org_id+"'";
			}
			sql+=" group by project_name,project_department,project_department_id,start_date,end_date ";
//			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
//				sql += " and org_id = '"+org_id+"'";
//			}
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			//获取费用报销
			
			
			BigDecimal ClBz_total = new BigDecimal(0);
			BigDecimal PxBz_total = new BigDecimal(0);
			BigDecimal Pxf_total = new BigDecimal(0);
			BigDecimal PxJt_total = new BigDecimal(0);
			BigDecimal PxZs_total = new BigDecimal(0);
			BigDecimal Taotal_total = new BigDecimal(0);
			Double num=0.0;
			while (rs.next()) {
				BigDecimal total = new BigDecimal(0);
				TjDTO TjDTO = new TjDTO();
				TjDTO.setDepId(rs.getString("project_department_id"));
				TjDTO.setXmmc(rs.getString("project_name"));
			/*	List<Object> list1=getFybx();
				for(int i=0;i<list1.size();i++){//判断项目名称一致  如果是则取出费用信息
					HashMap<String, Object> wrapper=(HashMap<String, Object>)list1.get(i);
					if(wrapper.get("project_name").equals(rs.getString("project_name"))){
						List<Object> list2=(List<Object>)wrapper.get("list");
						for(int j=0;j<list2.size();j++){
							HashMap<String, Object> wrapper1=(HashMap<String, Object>)list2.get(i);
							num=num+(Double)wrapper1.get("MONEY1")+(Double)wrapper1.get("MONEY2")+(Double)wrapper1.get("MONEY3")+(Double)wrapper1.get("MONEY4");
						}
					}
				}*/
				TjDTO.setFybx(num);
				TjDTO.setDepName(rs.getString("project_department"));//部门名称
				TjDTO.setClf(rs.getDouble("fy"));//差旅费费用
				TjDTO.setMoney(rs.getDouble("fy")+num);//总费用
				TjDTO.setZlgs(rs.getDouble("zl"));//专利
				TjDTO.setLwts(rs.getDouble("lw"));//论文
				list.add(TjDTO);
				PxJt_total=PxJt_total.add(new BigDecimal(num));//费用报销
				PxZs_total=PxZs_total.add(new BigDecimal(rs.getDouble("fy")));//差旅费
				ClBz_total = ClBz_total.add(new BigDecimal(rs.getDouble("fy")+num));//总费用
				PxBz_total = PxBz_total.add(new BigDecimal(rs.getDouble("lw")));
				Pxf_total = Pxf_total.add(new BigDecimal(rs.getDouble("zl")));
				Taotal_total = Taotal_total.add(total);
			}
			
			conn.close();
			TjDTO TjDTO1 = new TjDTO();
			TjDTO1.setXmmc("合计");//用户姓名
			TjDTO1.setDepName("");//部门名称
			TjDTO1.setFybx(PxJt_total.doubleValue());//费用报销
			TjDTO1.setClf(PxZs_total.doubleValue());//差旅费
			TjDTO1.setMoney(ClBz_total.doubleValue());//总费用
			TjDTO1.setLwts(PxBz_total.doubleValue());//专利
			TjDTO1.setZlgs(Pxf_total.doubleValue());//论文
			list.add(TjDTO1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取持证统计信息
	 * 
	 * @param startDate
	 *           开始日期
	 * @param endDate
	 *           结束日期
	 * @param org_id
	 *            部门ID
	 * @param 
	 */
	public List<TjDTO> getCzData(String startDate,
			String endDate, String org_id,String unit) {
		List<TjDTO> list = new ArrayList<TjDTO>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			String sql = "select s.org_name,t1.org_id,cert_category,cert_type,count(*) sums from  (select * from  employee_cert where 1=1 ";
			 if(StringUtil.isNotEmpty(startDate)){
		        	sql+=" and first_get_date>=to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS') ";
		        }
			 if(StringUtil.isNotEmpty(endDate)){
		        	sql+=" and first_get_date<to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') ";
		        }
			sql+= ") t,employee t1,sys_org s  "
					+ " where t.employee_id=t1.id and t1.org_id=s.id ";
	       
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				sql += " and t1.org_id = '"+org_id+"'";
			}
			sql += " group by  s.org_name,t1.org_id,cert_category,cert_type";
//			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
//				sql += " and org_id = '"+org_id+"'";
//			}
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			BigDecimal ClBz_total = new BigDecimal(0);
			while (rs.next()) {
				BigDecimal total = new BigDecimal(0);
				TjDTO tjDTO = new TjDTO();
				tjDTO.setDepId(rs.getString("org_id"));//部门id
				tjDTO.setDepName(rs.getString("org_name"));//部门名称
				tjDTO.setZsxz(rs.getString("cert_type"));//持证性质
				tjDTO.setZsxm(rs.getString("cert_category"));//持证项目
				tjDTO.setNumber(rs.getDouble("sums"));//数量
				
				
				list.add(tjDTO);
				ClBz_total = ClBz_total.add(new BigDecimal(rs.getDouble("sums")));
			}
			conn.close();
			TjDTO cwFytjDTO1 = new TjDTO();
			cwFytjDTO1.setDepId("");//部门id
			cwFytjDTO1.setDepName("总计");//部门名称
			cwFytjDTO1.setZsxz("");//持证性质
			cwFytjDTO1.setZsxm("");//持证项目
			cwFytjDTO1.setNumber(ClBz_total.doubleValue());//数量
			list.add(cwFytjDTO1);
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
				Factory.getDB().freeConnetion(conn); // 释放连接
			} catch (Exception e) {
				logger.error("关闭数据库连接错误：" + e.getMessage());
				e.printStackTrace();
			}
		}
		/** 
	     * 获取当年的第一天 
	     * @param year 
	     * @return 
	     */  
	    public static Date getCurrYearFirst(){  
	        Calendar currCal=Calendar.getInstance();    
	        int currentYear = currCal.get(Calendar.YEAR);  
	        return getYearFirst(currentYear);  
	    } 
	    
	    /** 
	     * 获取当年的最后一天 
	     * @param year 
	     * @return 
	     */  
	    public static Date getCurrYearLast(){  
	        Calendar currCal=Calendar.getInstance();    
	        int currentYear = currCal.get(Calendar.YEAR);  
	        return getYearLast(currentYear);  
	    } 
	    
	    /** 
	     * 获取某年第一天日期 
	     * @param year 年份 
	     * @return Date 
	     */  
	    public static Date getYearFirst(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        Date currYearFirst = calendar.getTime();  
	        return currYearFirst;  
	    }
	    
	    /** 
	     * 获取某年最后一天日期 
	     * @param year 年份 
	     * @return Date 
	     */  
	    public static Date getYearLast(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
	        Date currYearLast = calendar.getTime();  
	          
	        return currYearLast;  
	    }
	    //获取费用报销科研项目列表
	    public  List<String>  getFybxlb(){
	    	String sql1="select project_name from TJY2_SCIENTIFIC_RESEARCH where status not in('1','2','3','4')";
			
	    	List<String> list=new ArrayList<String>();try {
				pstmt = conn.prepareStatement(sql1);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					list.add(rs.getString("project_name"));
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	return list;
	    }
	    //获取费用报销
	    public  List<Object>  getFybx(List<String> lists){
	    	//List<String> lists=getFybxlb();
	    	List<Object> list = new ArrayList<Object>();
	    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
	    	try {
	    		for (int i = 0; i < lists.size(); i++) {
	    		String sql1="select MONEY1,MONEY2,MONEY3,MONEY4,COST_ITEM_ID1,COST_ITEM_ID2,COST_ITEM_ID3,COST_ITEM_ID4 from TJY2_FYBXD where FYBX_TYPE='2' and ( SUB_ITEM_ID1='"+list.get(i)+"' or SUB_ITEM_ID2='"+list.get(i)+"' or SUB_ITEM_ID3='"+list.get(i)+"' or SUB_ITEM_ID4='"+list.get(i)+"')";
	    		pstmt = conn.prepareStatement(sql1);
				rs = pstmt.executeQuery();
				wrapper.put("project_name", list.get(i));
				List<Object> list1 = new ArrayList<Object>();
		    	HashMap<String, Object> wrapper1 = new HashMap<String, Object>();
				while (rs.next()) {
						wrapper1.put("MONEY1", rs.getDouble("MONEY1"));
						wrapper1.put("MONEY2", rs.getDouble("MONEY2"));
						wrapper1.put("MONEY3", rs.getDouble("MONEY3"));
						wrapper1.put("MONEY4", rs.getDouble("MONEY4"));
						wrapper1.put("COST_ITEM_ID1", rs.getString("COST_ITEM_ID1"));
						wrapper1.put("COST_ITEM_ID2", rs.getString("COST_ITEM_ID2"));
						wrapper1.put("COST_ITEM_ID3", rs.getString("COST_ITEM_ID3"));
						wrapper1.put("COST_ITEM_ID4", rs.getString("COST_ITEM_ID4"));
						list1.add(wrapper1);
				}
				conn.close();
				wrapper.put("list", list1);
				list.add(wrapper);
	    	}
	    	
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	return list;
	    }
}