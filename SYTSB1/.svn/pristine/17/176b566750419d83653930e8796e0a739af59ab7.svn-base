package com.lsts.finance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

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
@Repository("cwFytjDao")
public class CwFytjDao extends HibernateDaoSupport {

	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集
	
	/**
	 * 获取财务报账统计数据（个人）
	 * 
	 * @param startDate
	 *            审批通过开始日期
	 * @param endDate
	 *            审批通过结束日期
	 * @param org_id
	 *            部门ID
	 * @param userName
	 *            姓名
	 * 
	 * @return
	 * @author dxf
	 */
	public List<CwFytjDTO> getCwData(String startDate,
			String endDate, String org_id,String userName) {
		List<CwFytjDTO> list = new ArrayList<CwFytjDTO>();
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
			String sql = "select r.user_id,trunc(r.fee,0) fee,trunc(r.px_bz,0) px_bz,trunc(r.pxf,0) pxf,trunc(r.px_jt,0) px_jt,trunc(r.px_zs,0) px_zs,"
							+ "e.name,o.id org_id,o.org_name from "+
							  " (select user_id,sum(fee) fee,sum(px_bz) px_bz,sum(pxf) pxf,sum(px_jt) px_jt,sum(px_zs) px_zs"+
							  " from ((select c.userid user_id,"+
							  " c.fyid    business_id,"+
							  " c.fee,"+
							  " 0         px_bz,"+
							  " 0         pxf,"+
							  " 0         px_jt,"+
							  " 0         px_zs,"+
							  " c.bx_date,"+
							  " c.CREATE_DATE,"+
							  " c.yeas"+
							  " from TJY2_TJ_CLFBXD c)"+
							  " union all (select p.user_id,"+
							  " p.business_id,"+
							  " 0 fee,"+
							  " p.px_bz,"+
							  " p.pxf,"+
							  " p.px_jt,"+
							  " p.px_zs,"+
							  " p.bx_date,"+
							  " p.CREATE_DATE,"+
							  " p.yeas"+
							  " from TJY2_TJ_PXFBXD p))"+
							  " where CREATE_DATE>to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS')"+
							  " and CREATE_DATE<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS')"+
							  " group by user_id) r ,employee e,sys_org o where r.user_id=e.id"+
							  " and e.ORG_ID=o.id";
			if (StringUtil.isNotEmpty(userName)) {
				sql += " and name = '"+userName+"'";
			}
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				sql += " and ORG_ID = '"+org_id+"'";
			}
			sql += " order by o.orders2";
//			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
//				sql += " and org_id = '"+org_id+"'";
//			}
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			BigDecimal ClBz_total = new BigDecimal(0);
			BigDecimal PxBz_total = new BigDecimal(0);
			BigDecimal Pxf_total = new BigDecimal(0);
			BigDecimal PxJt_total = new BigDecimal(0);
			BigDecimal PxZs_total = new BigDecimal(0);
			BigDecimal Taotal_total = new BigDecimal(0);
			while (rs.next()) {
				BigDecimal total = new BigDecimal(0);
				CwFytjDTO cwFytjDTO = new CwFytjDTO();
				cwFytjDTO.setUserId(rs.getString("user_id"));//用户id
				cwFytjDTO.setUserName(rs.getString("name"));//用户姓名
				cwFytjDTO.setDepId(rs.getString("org_id"));//部门id
				cwFytjDTO.setDepName(rs.getString("org_name"));//部门名称
				cwFytjDTO.setClBz(rs.getDouble("fee"));//差旅补助
				cwFytjDTO.setPxBz(rs.getDouble("px_bz"));//培训补助
				cwFytjDTO.setPxf(rs.getDouble("pxf"));//培训费
				cwFytjDTO.setPxJt(rs.getDouble("px_jt"));//交通费（培训）
				cwFytjDTO.setPxZs(rs.getDouble("px_zs"));//住宿费（培训）
				total = new BigDecimal(rs.getDouble("fee")).add(new BigDecimal(rs.getDouble("px_bz"))).add(new BigDecimal(rs.getDouble("pxf"))).add(new BigDecimal(rs.getDouble("px_jt"))).add(new BigDecimal(rs.getDouble("px_zs")));
				cwFytjDTO.setTaotal(total);
				list.add(cwFytjDTO);
				ClBz_total = ClBz_total.add(new BigDecimal(rs.getDouble("fee")));
				PxBz_total = PxBz_total.add(new BigDecimal(rs.getDouble("px_bz")));
				Pxf_total = Pxf_total.add(new BigDecimal(rs.getDouble("pxf")));
				PxJt_total = PxJt_total.add(new BigDecimal(rs.getDouble("px_jt")));
				PxZs_total = PxZs_total.add(new BigDecimal(rs.getDouble("px_zs")));
				Taotal_total = Taotal_total.add(total);
			}
			conn.close();
			CwFytjDTO cwFytjDTO1 = new CwFytjDTO();
			cwFytjDTO1.setUserName("合计");//用户姓名
			cwFytjDTO1.setDepName("");//部门名称
			cwFytjDTO1.setClBz(ClBz_total.doubleValue());//差旅补助
			cwFytjDTO1.setPxBz(PxBz_total.doubleValue());//培训补助
			cwFytjDTO1.setPxf(Pxf_total.doubleValue());//培训费
			cwFytjDTO1.setPxJt(PxJt_total.doubleValue());//交通费（培训）
			cwFytjDTO1.setPxZs(PxZs_total.doubleValue());//住宿费（培训）
			cwFytjDTO1.setTaotal(Taotal_total);
			list.add(cwFytjDTO1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取财务报账统计数据（部门）
	 * 
	 * @param startDate
	 *            审批通过开始日期
	 * @param endDate
	 *            审批通过结束日期
	 * @param org_id
	 *            部门ID
	 * @param userName
	 *            姓名
	 * 
	 * @return
	 * @author dxf
	 */
	public List<CwFytjDTO> getCwBmData(String startDate,
			String endDate, String org_id,String unit) {
		List<CwFytjDTO> list = new ArrayList<CwFytjDTO>();
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
			String sql = "select r.DEPARTMENT_ID,r.unit,trunc(r.fee,0) fee,trunc(r.clf,0) clf,trunc(r.pxfy,0) pxfy,trunc(r.fybx,0) fybx,trunc(r.draw,0) draw, "+
             " o.id org_id,o.org_name from  "+
              " (select DEPARTMENT_ID,unit,sum(fee) fee,sum(clf) clf,sum(pxfy) pxfy,sum(fybx) fybx,sum(draw) draw "+
              "  from ((select c.DEPARTMENT_ID,c.CL_DW unit, "+
              "   (c.CL_HJ_HSBZ_JE+c.CL_HJ_GZF_JE) fee, "+
              "    (c.CL_HJ_JEXJ-(c.CL_HJ_HSBZ_JE+c.CL_HJ_GZF_JE)) clf, "+
              "  0 pxfy, "+
              "  0 fybx, "+
              "  0 draw, "+
              "   c.CL_SJ bs_date "+
              "   from TJY2_CLFBXD c where c.statue='CSTG' and ((c.CL_HJ_HSBZ_JE + c.CL_HJ_GZF_JE) is not null or (c.CL_HJ_JEXJ - (c.CL_HJ_HSBZ_JE + c.CL_HJ_GZF_JE)) is not null)) "+
              "    union all (select p.DEPARTMENT_ID ,p.CL_DW unit, "+
              "   0 fee, "+
              "   0 clf, "+
              "  (p.CL_HJ_JEXJ)  pxfy, "+
              "     0 fybx, "+
              "     0 draw, "+
              "   p.CL_SJ  bs_date "+
              "   from TJY2_PXFBXD p where p.statue='CSTG') "+
              "    union all(select f.DEPARTMENT_ID,f.unit unit, "+
              "   0 fee, "+
              "   0 clf, "+
              "   0 pxfy, "+
              "    f.TOTAL fybx, "+
              "    0 draw, "+
              "   f.BS_DATE bs_date "+
              "   from TJY2_FYBXD f where f.status='CSTG') "+
              "   union all(select d.DEPARTMENT_ID,d.unit unit, "+
              "   0 fee, "+
              "    0 clf, "+
              "    0 pxfy, "+
              "     0 fybx, "+
              "    d.money draw, "+
              "   d.DM_DATE bs_date "+
              "   from TJY2_CW_DRAWMONEY d where d.status='CSTG') "+
              "    ) "+
              " where bs_date>to_date('"+startDate+"','YYYY-MM-DD HH24:MI:SS')"+
			  " and bs_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS')"+
              "    group by DEPARTMENT_ID,unit) r ,sys_org o where r.DEPARTMENT_ID=o.id";
			if (StringUtil.isNotEmpty(unit)) {
				sql += " and unit = '"+unit+"'";
			}
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				sql += " and o.id = '"+org_id+"'";
			}
//			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
//				sql += " and org_id = '"+org_id+"'";
//			}
			sql += " order by o.orders2";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			BigDecimal ClBz_total = new BigDecimal(0);
			BigDecimal Clf_total = new BigDecimal(0);
			BigDecimal PxFy_total = new BigDecimal(0);
			BigDecimal FyBx_total = new BigDecimal(0);
			BigDecimal DrAw_total = new BigDecimal(0);
			BigDecimal Taotal_total = new BigDecimal(0);
			while (rs.next()) {
				BigDecimal total = new BigDecimal(0);
				CwFytjDTO cwFytjDTO = new CwFytjDTO();
				if(rs.getString("unit")!=null){
				if(rs.getString("unit").equals("tjy")){
					cwFytjDTO.setUnitName("四川省特种设备检验研究院");//单位名称
				}else if(rs.getString("unit").equals("ds")){
					cwFytjDTO.setUnitName("鼎盛公司");//单位名称
				} else if(rs.getString("unit").equals("xh")){
					cwFytjDTO.setUnitName("四川省特种设备检验检测协会");//单位名称
				} else if(rs.getString("unit").equals("jjd")){
					cwFytjDTO.setUnitName("基建办");//单位名称
				}  else if(rs.getString("unit").equals("ghwyh")){
					cwFytjDTO.setUnitName("四川省特种设备检验研究院工会委员会");//单位名称
				} 
				}else{
					cwFytjDTO.setUnitName("");//单位名称
				}
				cwFytjDTO.setUnitId(rs.getString("unit"));//单位名称
				cwFytjDTO.setDepId(rs.getString("org_id"));//部门id
				cwFytjDTO.setDepName(rs.getString("org_name"));//部门名称
				cwFytjDTO.setClBz(rs.getDouble("fee"));//差旅补助
				cwFytjDTO.setClf(rs.getDouble("clf"));//差旅费
				cwFytjDTO.setPxfy(rs.getDouble("pxfy"));//培训费
				cwFytjDTO.setFybx(rs.getDouble("fybx"));//费用报销
				cwFytjDTO.setDraw(rs.getDouble("draw"));//领款
				total = new BigDecimal(rs.getDouble("fee")).add(new BigDecimal(rs.getDouble("clf"))).add(new BigDecimal(rs.getDouble("pxfy"))).add(new BigDecimal(rs.getDouble("fybx"))).add(new BigDecimal(rs.getDouble("draw")));
				cwFytjDTO.setTaotal(total);
				list.add(cwFytjDTO);
				ClBz_total = ClBz_total.add(new BigDecimal(rs.getDouble("fee")));
				Clf_total = Clf_total.add(new BigDecimal(rs.getDouble("clf")));
				PxFy_total = PxFy_total.add(new BigDecimal(rs.getDouble("pxfy")));
				FyBx_total = FyBx_total.add(new BigDecimal(rs.getDouble("fybx")));
				DrAw_total = DrAw_total.add(new BigDecimal(rs.getDouble("draw")));
				Taotal_total = Taotal_total.add(total);
			}
			conn.close();
			CwFytjDTO cwFytjDTO1 = new CwFytjDTO();
			cwFytjDTO1.setUserName("合计");//用户姓名
			cwFytjDTO1.setDepName("");//部门名称
			cwFytjDTO1.setClBz(ClBz_total.doubleValue());//差旅补助
			cwFytjDTO1.setClf(Clf_total.doubleValue());//差旅费
			cwFytjDTO1.setPxfy(PxFy_total.doubleValue());//培训费
			cwFytjDTO1.setFybx(FyBx_total.doubleValue());//费用报销
			cwFytjDTO1.setDraw(DrAw_total.doubleValue());//领款
			cwFytjDTO1.setTaotal(Taotal_total);
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
}