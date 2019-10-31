package com.lsts.finance.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.Salary;
import com.lsts.finance.dao.SalaryDao;



/**
 * 实体Manager，继承自泛型类EntityManageImpl，同时声明泛型的运行时bean和dao为Demo,DemoDao。<br/>
 * 通过这样的继承方式，自动获得了对Demo实体的crud操作<br/>
 * 注解@Service声明了该类为一个spring对象
 */
@Service("feeSalaryManager")
public class FeeSalaryManager extends EntityManageImpl<Salary, SalaryDao> {

	// 必须提供Demo实体的dao对象，使用注解@Autowired标识为自动装配
	@Autowired
	SalaryDao salaryDao;
	
	/**
	 * 统计个人工资
	 * @param peoId 
	 * @param gzItem 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsFee(String start,String end,String unit,String dept, String gzItem, String peoId) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String peoSql = null;

		
		if(peoId!=null&&StringUtil.isNotEmpty(peoId)&&!"null".equals(peoId)) {
			peoSql = " and u.id in ('"+peoId.replace(",", "','")+"') ";
		}
		
		if(gzItem!=null&&StringUtil.isNotEmpty(gzItem)&&!"null".equals(gzItem)) {
			
			//自定义了查询项
			String[] items = gzItem.split(",");

			String itemSql = "";
			for(int i = 0;i<items.length;i++) {
				itemSql =  itemSql +",sum(t." + items[i] + ")";
			}
			
			
		String sql ="select t.NAME " +itemSql+ ",u.org_id from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
				     + "sys_user u,employee e where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and u.status ='1' ";
			
			if(StringUtil.isNotEmpty(start)){
				sql += " and dates >= to_date('"+start+"','yyyy-MM')";
			}
			if(StringUtil.isNotEmpty(end)){
				sql += " and dates <= to_date('"+end+"','yyyy-MM')";
			}
			
			if(peoSql!=null) {
				sql += peoSql;
			}
			sql+="group by t.NAME,u.org_id";
			
			String sqlSum = "select '合计' NAME " +itemSql+ " from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
						     + "sys_user u,employee e,sys_org o where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and u.org_id = o.id and u.status ='1' ";
								if(StringUtil.isNotEmpty(start)){
									sqlSum += " and dates >= to_date('"+start+"','yyyy-MM')";
								}
								if(StringUtil.isNotEmpty(end)){
									sqlSum += " and dates <= to_date('"+end+"','yyyy-MM')";
								}
								if(peoSql!=null) {
									sqlSum += peoSql;
								}
								if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
									sqlSum = sqlSum + " and o.id = '100042' or o.parent_id ='100042'";
								}else {
									sqlSum = sqlSum +  " and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
								}
								
								if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
									String depts = dept.replace(",", "','");
									sqlSum = sqlSum + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
								}	
					
								String orgSql = " p,sys_org o where o.id = p.org_id and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
								//处理统计单位问题
								if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
									orgSql = " p,sys_org o where o.id = p.org_id and (o.id = '100042' or o.parent_id ='100042')";
								}
								
								if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
									String depts = dept.replace(",", "','");
									orgSql = orgSql + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
								}
								
								
								String sqlResult = "select p.* from ("+sql+")"+ orgSql;
						
								String sqlOrg = "select p.name from ("+sql+")"+ orgSql;
								
								String sqlSums = "select p.* from ("+sqlSum+") p";
								
								List<Object[]> stsClss = salaryDao.createSQLQuery(sqlResult).list();
								List<String> stsOrg = salaryDao.createSQLQuery(sqlOrg).list();
								List<Object[]> sum = salaryDao.createSQLQuery(sqlSums).list();
								
								map.put("data", stsClss);
								map.put("classs", stsOrg);
								map.put("sum", sum);
		}else {
		
		
		
			
			String sql = "select t.NAME,sum(t.JBGZ_GWGZ),sum( t.JBGZ_XJGZ),sum( t.JBGZ_BLBT),sum( t.JBGZ_LT),sum(t.JBGZ_BFX),"
	      + "sum(t.JBGZ_DZ),sum( t.JBGZ_XJ),sum( t.JXGZ_JCJX_MY),sum( t.JXGZ_JCJX_BF),sum( t.JXGZ_JDJX),sum( t.JXGZ_BT_ZCBT),sum( t.JXGZ_BT_QT),"
	      +" sum( t.JXGZ_XJ),sum(t.KKX_ZYNJ_MY),sum( t.KKX_ZYNJ_BK),sum( t.KKX_YLBX_MY),sum( t.KKX_YLBX_BF),sum(t.KKX_SY_MY),"
	      +"sum(t.KKX_SY_BF),sum(t.KKX_GJJ_MY),sum(t.KKX_GJJ_BF),sum(t.KKX_GHJF),sum( t.KKX_SDS),sum( t.KKX_OLDBX_MY),sum( t.KKX_OLDBX_BF),sum( t.KKX_XJ),"
	     +" sum( t.FSALARY),u.org_id from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
	     + "sys_user u,employee e where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and u.status ='1' ";
			if(StringUtil.isNotEmpty(start)){
				sql += " and dates >= to_date('"+start+"','yyyy-MM')";
			}
			if(StringUtil.isNotEmpty(end)){
				sql += " and dates <= to_date('"+end+"','yyyy-MM')";
			}
			if(peoSql!=null) {
				sql += peoSql;
			}
			sql+="group by t.NAME,u.org_id";
			
			
			String sqlSum = "select '合计' NAME,sum(t.JBGZ_GWGZ),sum( t.JBGZ_XJGZ),sum( t.JBGZ_BLBT),sum( t.JBGZ_LT),sum(t.JBGZ_BFX),"
				      + "sum(t.JBGZ_DZ),sum( t.JBGZ_XJ),sum( t.JXGZ_JCJX_MY),sum( t.JXGZ_JCJX_BF),sum( t.JXGZ_JDJX),sum( t.JXGZ_BT_ZCBT),sum( t.JXGZ_BT_QT),"
				      +" sum( t.JXGZ_XJ),sum(t.KKX_ZYNJ_MY),sum( t.KKX_ZYNJ_BK),sum( t.KKX_YLBX_MY),sum( t.KKX_YLBX_BF),sum(t.KKX_SY_MY),"
				      +"sum(t.KKX_SY_BF),sum(t.KKX_GJJ_MY),sum(t.KKX_GJJ_BF),sum(t.KKX_GHJF),sum( t.KKX_SDS),sum( t.KKX_OLDBX_MY),sum( t.KKX_OLDBX_BF),sum( t.KKX_XJ),"
				     +" sum( t.FSALARY) from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
				     + "sys_user u,employee e,sys_org o where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and u.org_id = o.id and u.status ='1' ";
						if(StringUtil.isNotEmpty(start)){
							sqlSum += " and dates >= to_date('"+start+"','yyyy-MM')";
						}
						if(StringUtil.isNotEmpty(end)){
							sqlSum += " and dates <= to_date('"+end+"','yyyy-MM')";
						}
	
						if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
							sqlSum = sqlSum + " and o.id = '100042' or o.parent_id ='100042'";
						}else {
							sqlSum = sqlSum +  " and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
						}
						if(peoSql!=null) {
							sqlSum += peoSql;
						}
						if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
							String depts = dept.replace(",", "','");
							sqlSum = sqlSum + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
						}
			
			
			
			String orgSql = " p,sys_org o where o.id = p.org_id and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
			//处理统计单位问题
			if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
				orgSql = " p,sys_org o where o.id = p.org_id and (o.id = '100042' or o.parent_id ='100042')";
			}
			
			if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
				String depts = dept.replace(",", "','");
				orgSql = orgSql + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
			}
			
			
			String sqlResult = "select p.* from ("+sql+")"+ orgSql;
	
			String sqlOrg = "select p.name from ("+sql+")"+ orgSql;
			
			String sqlSums = "select p.* from ("+sqlSum+") p";
			
			List<Object[]> stsClss = salaryDao.createSQLQuery(sqlResult).list();
			List<String> stsOrg = salaryDao.createSQLQuery(sqlOrg).list();
			List<Object[]> sum = salaryDao.createSQLQuery(sqlSums).list();
			
			map.put("data", stsClss);
			map.put("classs", stsOrg);
			map.put("sum", sum);
		}
		return map;
	}
	/**
	 * 按月份统计个人工资
	 * @param peoId 
	 * @param gzItem 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsSalary(String start,String end,String unit,String dept) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.YEAR)+1;
		if(StringUtil.isEmpty(start)){
			start = year+"-"+1;
		}else{
			start = start.substring(0,7);
		}
		if(StringUtil.isEmpty(end)){
			end = year+"-"+month;
		}else{
			end = end.substring(0,7);
		}
			
		String sql = "select p.* "+
					 " from (select  t1.dates,t.NAME, sum(t.FSALARY), u.org_id "+
					 "         from TJY2_CW_SALARY_NEW t, "+
					 "              (select t.*, "+
					 "                      to_date(trim(t.SALAR_TMONTH) || '-' || "+
					 "                              trim(t.SALART_YEAR), "+
					 "                              'yyyy-MM') dates "+
					 "                 from TJY2_IMPORT_DATA_RECORDS t) t1, "+
					 "              sys_user u, "+
					 "              employee e "+
					 "        where t1.id = t.IMPORT_ID "+
					 "          and t.fk_employeebase_id = e.id "+
					 "          and u.employee_id = e.id "+
					 "          and u.status = '1'  "+
					 "          and dates >= to_date('"+start+"', 'yyyy-MM') "+
					 "          and dates <= to_date('"+end+"', 'yyyy-MM') "+
					 "        group by t1.dates,t.NAME, u.org_id) p, "+
					 "      sys_org o "+
					 "where o.id = p.org_id "+
					 "  and o.id not in ('100042','100044', '100047','100052') "+
					 "  and o.parent_id not in ('100042','100044', '100047','100052') "+
					 "  order by dates";

		List<Object[]> data = salaryDao.createSQLQuery(sql).list();
		Set<String> s = new HashSet<String>();
		for (Object[] objects : data) {
			s.add(objects[0].toString());
		}
		map.put("data", data);
		map.put("yAxis", s);
		return map;
	}
	
	/**
	 * 统计部门工资
	 * @param gzItem 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsFeeUnit(String start,String end,String unit,String dept, String gzItem) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
	
		if(gzItem!=null&&StringUtil.isNotEmpty(gzItem)&&!"null".equals(gzItem)) {
			
			//自定义了查询项
			String[] items = gzItem.split(",");

			String itemSql = "";
			for(int i = 0;i<items.length;i++) {
				itemSql =  itemSql +",sum(t." + items[i] + ")";
			}
			String sql = "select o.org_name NAME "+itemSql+",u.org_id from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
				     + "sys_user u,employee e,sys_org o where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and o.id = u.org_id and u.status ='1' ";
						if(StringUtil.isNotEmpty(start)){
							sql += " and dates >= to_date('"+start+"','yyyy-MM')";
						}
						if(StringUtil.isNotEmpty(end)){
							sql += " and dates <= to_date('"+end+"','yyyy-MM')";
						}
						
						sql+="group by o.org_name,u.org_id";
						
			String sqlSum = "select '合计' NAME"+itemSql+ "from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
				     + "sys_user u,employee e,sys_org o where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and o.id = u.org_id and u.status ='1' ";
						if(StringUtil.isNotEmpty(start)){
							sqlSum += " and dates >= to_date('"+start+"','yyyy-MM')";
						}
						if(StringUtil.isNotEmpty(end)){
							sqlSum += " and dates <= to_date('"+end+"','yyyy-MM')";
						}
	
						if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
							sqlSum = sqlSum + " and o.id = '100042' or o.parent_id ='100042'";
						}else {
							sqlSum = sqlSum +  " and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
						}
						
						if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
							String depts = dept.replace(",", "','");
							sqlSum = sqlSum + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
						}
			
			
			String orgSql = " p,sys_org o where o.id = p.org_id and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
			//处理统计单位问题
			if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
				orgSql = " p,sys_org o where o.id = p.org_id and (o.id = '100042' or o.parent_id ='100042')";
			}
			
			if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
				String depts = dept.replace(",", "','");
				orgSql = orgSql + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
			}
			orgSql = orgSql + "  order by o.orders2 ";
			
			String sqlResult = "select p.* from ("+sql+")"+ orgSql;
			
			String sqlOrg = "select p.name from ("+sql+")"+ orgSql;
			
			List<Object[]> stsClss = salaryDao.createSQLQuery(sqlResult).list();
			
			List<String> stsOrg = salaryDao.createSQLQuery(sqlOrg).list();
			
			String sqlSums = "select p.* from ("+sqlSum+") p";
			List<Object[]> stsSum = salaryDao.createSQLQuery(sqlSums).list();
			
			map.put("data", stsClss);
			map.put("sum", stsSum);
			map.put("classs", stsOrg);
		}else {	
		
				String sql = "select o.org_name NAME,sum(t.JBGZ_GWGZ),sum( t.JBGZ_XJGZ),sum( t.JBGZ_BLBT),sum( t.JBGZ_LT),sum(t.JBGZ_BFX),"
		      + "sum(t.JBGZ_DZ),sum( t.JBGZ_XJ),sum( t.JXGZ_JCJX_MY),sum( t.JXGZ_JCJX_BF),sum( t.JXGZ_JDJX),sum( t.JXGZ_BT_ZCBT),sum( t.JXGZ_BT_QT),"
		      +" sum( t.JXGZ_XJ),sum(t.KKX_ZYNJ_MY),sum( t.KKX_ZYNJ_BK),sum( t.KKX_YLBX_MY),sum( t.KKX_YLBX_BF),sum(t.KKX_SY_MY),"
		      +"sum(t.KKX_SY_BF),sum(t.KKX_GJJ_MY),sum(t.KKX_GJJ_BF),sum(t.KKX_GHJF),sum( t.KKX_SDS),sum( t.KKX_OLDBX_MY),sum( t.KKX_OLDBX_BF),sum( t.KKX_XJ),"
		     +" sum( t.FSALARY),u.org_id from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
		     + "sys_user u,employee e,sys_org o where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and o.id = u.org_id and u.status ='1' ";
				if(StringUtil.isNotEmpty(start)){
					sql += " and dates >= to_date('"+start+"','yyyy-MM')";
				}
				if(StringUtil.isNotEmpty(end)){
					sql += " and dates <= to_date('"+end+"','yyyy-MM')";
				}
				
				sql+="group by o.org_name,u.org_id";
				
				String sqlSum = "select '合计' NAME,sum(t.JBGZ_GWGZ),sum( t.JBGZ_XJGZ),sum( t.JBGZ_BLBT),sum( t.JBGZ_LT),sum(t.JBGZ_BFX),"
					      + "sum(t.JBGZ_DZ),sum( t.JBGZ_XJ),sum( t.JXGZ_JCJX_MY),sum( t.JXGZ_JCJX_BF),sum( t.JXGZ_JDJX),sum( t.JXGZ_BT_ZCBT),sum( t.JXGZ_BT_QT),"
					      +" sum( t.JXGZ_XJ),sum(t.KKX_ZYNJ_MY),sum( t.KKX_ZYNJ_BK),sum( t.KKX_YLBX_MY),sum( t.KKX_YLBX_BF),sum(t.KKX_SY_MY),"
					      +"sum(t.KKX_SY_BF),sum(t.KKX_GJJ_MY),sum(t.KKX_GJJ_BF),sum(t.KKX_GHJF),sum( t.KKX_SDS),sum( t.KKX_OLDBX_MY),sum( t.KKX_OLDBX_BF),sum( t.KKX_XJ),"
					     +" sum( t.FSALARY) from TJY2_CW_SALARY_NEW t, (select t.*, to_date(trim(t.SALAR_TMONTH) || '-' || trim(t.SALART_YEAR),'yyyy-MM') dates from TJY2_IMPORT_DATA_RECORDS t) t1,"
					     + "sys_user u,employee e,sys_org o where t1.id=t.IMPORT_ID and t.fk_employeebase_id = e.id and u.employee_id = e.id and o.id = u.org_id and u.status ='1' ";
							if(StringUtil.isNotEmpty(start)){
								sqlSum += " and dates >= to_date('"+start+"','yyyy-MM')";
							}
							if(StringUtil.isNotEmpty(end)){
								sqlSum += " and dates <= to_date('"+end+"','yyyy-MM')";
							}
		
							if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
								sqlSum = sqlSum + " and o.id = '100042' or o.parent_id ='100042'";
							}else {
								sqlSum = sqlSum +  " and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
							}
							
							if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
								String depts = dept.replace(",", "','");
								sqlSum = sqlSum + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
							}
				
				
				String orgSql = " p,sys_org o where o.id = p.org_id and o.id not in ('100042','100044','100047','100052') and o.parent_id not in ('100042','100044','100047','100052') ";
				//处理统计单位问题
				if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
					orgSql = " p,sys_org o where o.id = p.org_id and (o.id = '100042' or o.parent_id ='100042')";
				}
				
				if(dept!=null&&StringUtil.isNotEmpty(dept)&&!"null".equals(dept)) {
					String depts = dept.replace(",", "','");
					orgSql = orgSql + " and (o.id in ('"+depts+"') or o.org_name in ('"+depts+"'))";
				}
				
				orgSql = orgSql + "  order by o.orders2 ";
				
				String sqlResult = "select p.* from ("+sql+")"+ orgSql;
				
				String sqlOrg = "select p.name from ("+sql+")"+ orgSql;
				
				List<Object[]> stsClss = salaryDao.createSQLQuery(sqlResult).list();
				
				List<String> stsOrg = salaryDao.createSQLQuery(sqlOrg).list();
				
				String sqlSums = "select p.* from ("+sqlSum+") p";
				List<Object[]> stsSum = salaryDao.createSQLQuery(sqlSums).list();
				
				map.put("data", stsClss);
				map.put("sum", stsSum);
				map.put("classs", stsOrg);
		}
		return map;
	}

	
}
