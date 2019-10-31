package com.lsts.finance.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.codetable.bean.CodeTable;
import com.khnt.pub.codetable.bean.CodeTableValue;
import com.khnt.pub.codetable.service.CodeTableCache;
import com.khnt.utils.StringUtil;
import com.lsts.finance.bean.Pxfbxd;
import com.lsts.finance.dao.PxfbxdDao;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeeStatisticsService extends EntityManageImpl<Pxfbxd, PxfbxdDao>{
	@Autowired
	PxfbxdDao pxfbxdDao;

	@Autowired
	CodeTableCache codeTableCache;
	
	/**
	 * 统计部门、经济类型、合计费用明细
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsFeeMX(String start,String end,String dept, String clss) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql="SELECT * FROM ("
				+ "select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无' then '其他' else SUB_ITEM1  end) clss,"
				+ " t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state ,t.IDENTIFIER,t.PEOPLE_CONCERNED peopleConcernde,t.BS_DATE bsdate,t.unit,t.HANDLE_NAME,t.handle_Time"
				+ " from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.status ='BXYES'"
				+ " union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无' then '其他' else SUB_ITEM2  end) clss,"
				+ " t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state ,t.IDENTIFIER,t.PEOPLE_CONCERNED peopleConcernde,t.BS_DATE bsdate,t.unit,t.HANDLE_NAME,t.handle_Time"
				+ " from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0 and t.status ='BXYES'"
				+ " union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无' then '其他' else SUB_ITEM3  end) clss,"
				+ " t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state ,t.IDENTIFIER,t.PEOPLE_CONCERNED peopleConcernde,t.BS_DATE bsdate,t.unit,t.HANDLE_NAME,t.handle_Time"
				+ " from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.status ='BXYES'"
				+ " union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无' then '其他' else SUB_ITEM4  end) clss,"
				+ " t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state ,t.IDENTIFIER,t.PEOPLE_CONCERNED peopleConcernde,t.BS_DATE bsdate,t.unit,t.HANDLE_NAME,t.handle_Time"
				+ " from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.status ='BXYES'"
				+ " union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,"
				+ " t.ABOLISH,t.statue state,t.IDENTIFIER,t.USER_ peopleConcernde,t.cl_sj bsdate,t.cl_dw unit ,t.HANDLE_NAME,t.handle_Time"
				+ " from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE ='BXYES'"
				+ " union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,"
				+ " t.ABOLISH,t.statue state ,t.IDENTIFIER,t.USER_ peopleConcernde,t.cl_sj bsdate,t.cl_dw unit ,t.HANDLE_NAME,t.handle_Time"
				+ " from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE ='BXYES'"
				+ " ) p ,sys_org o where o.id = p.department_id and o.id not in ('100042','100047') and o.parent_id not in ('100042','100047')"
				+ "  AND tm >= to_date('"+start+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')"
				+ " and tm <= to_date('"+end+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')"
				+ " AND department='"+dept+"'";
		
		if(StringUtil.isNotEmpty(clss)){
			sql+=" AND clss='"+clss+"'";
		}
		List<Map<String, Object>> sts = pxfbxdDao.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		CodeTable ct = this.codeTableCache.getCodeTable("TJY2_UNIT");
		List<CodeTableValue> codeValues=ct.getCodeTableValues();
		
		for (Map<String, Object> map2 : sts) {
			String unit=String.valueOf(map2.get("UNIT"));
			for (CodeTableValue codeTableValue : codeValues) {
				if(codeTableValue.getValue().equals(unit)){
					map2.put("UNIT", codeTableValue.getName().trim());
				}
			}
			
			String bxrq=String.valueOf(map2.get("BSDATE")).trim();
			if(StringUtil.isNotEmpty(bxrq)){
				map2.put("BSDATE", bxrq.substring(0, 10));
			}
			String clsh=String.valueOf(map2.get("HANDLE_TIME")).trim();
			if(StringUtil.isNotEmpty(clsh)){
				map2.put("HANDLE_TIME", clsh.substring(0, 10));
			}
		}
		
		map.put("rows", sts);
		
		return map;
	}
	
	
	
	
	/**
	 * 统计部门、经济类型、合计费用
	 * @param unit 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsFee(String start,String end,String dept, String unit) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select * from (select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无' "
													+ "then '其他' else SUB_ITEM1  end) clss,t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state "
					+"from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.status not in  ('REGISTER','SUBMIT') "
					+"union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无' "
												+ "then '其他' else SUB_ITEM2  end) clss,t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state "
					+"from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0 and t.status not in  ('REGISTER','SUBMIT') "
					+"union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无' "
													+ "then '其他' else SUB_ITEM3  end) clss,t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state "
					+"from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.status not in  ('REGISTER','SUBMIT') "
					+"union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无' "
													+ "then '其他' else SUB_ITEM4  end) clss,t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state "
					+"from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.status not in  ('REGISTER','SUBMIT') "
					+"union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state "
					+"from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') "
					+"union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state "
					+"from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') ) where state = 'BXYES'";
		if(StringUtil.isNotEmpty(start)){
			sql += "and tm >= to_date('"+start+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(end)){
			sql += "and tm <= to_date('"+end+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(dept)){
			dept = dept.replaceAll(",", "','");
			sql += "and department in ('"+dept+"')";
		}
		

		String orgSql = " p,sys_org o where o.id = p.department_id and o.id not in ('100042','100047') and o.parent_id not in ('100042','100047') ";
		//处理统计单位问题
		if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
			orgSql = " p,sys_org o where o.id = p.department_id and (o.id = '100042' or o.parent_id ='100042')";
		}
		 
		String statistics = "select department dept,clss,SUM(money) money from ("
					+sql+")"+ orgSql+" group by department,clss order by department, clss ";
		 
		String department = "select distinct department,o.orders2 from ("+sql+")"+ orgSql + " order by o.orders2";
		String clsses = "select distinct clss from ("+sql+")"+ orgSql;
		List<Map<String, Object>> sts = pxfbxdDao.createSQLQuery(statistics).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		List<Object[]> stsDept = pxfbxdDao.createSQLQuery(department).list();
		List<String> depts = new ArrayList<String>();
		for (Object[] str : stsDept) {
			depts.add(str[0].toString());
		}
		List<String> stsClss = pxfbxdDao.createSQLQuery(clsses).list();
		map.put("rows", sts);
		map.put("depts", depts);
		map.put("clsses", stsClss);
		return map;
	}
	/**
	 * 统计部门、经济类型、合计费用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsCarFee(String start,String end,String carIds) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = " select * from ("+
	       "select t.id,NVL(t.PROJECT_ITEM_NAME1,'其他') car_id,(case when SUB_ITEM1 is null or SUB_ITEM1='无' then '其他' else SUB_ITEM1  end) clss, "+
	       "t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state,t.FYBX_TYPE "+
	       "from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0 and t.FYBX_TYPE = 1 "+
	       "union all "+
	       "select t.id,NVL(t.PROJECT_ITEM_NAME2,'其他') car_id,(case when SUB_ITEM2 is null or SUB_ITEM2='无' then '其他' else SUB_ITEM2  end) clss, "+
	       "t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state,t.FYBX_TYPE  "+ 
	       "from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0  and t.FYBX_TYPE = 1 "+
	       "union all "+
	       "select t.id,NVL(t.PROJECT_ITEM_NAME3,'其他') car_id,(case when SUB_ITEM3 is null or SUB_ITEM3='无' then '其他' else SUB_ITEM3  end) clss, "+
	       "t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state,t.FYBX_TYPE  "+
	       "from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0 and t.FYBX_TYPE = 1 "+
	       "union all "+
	       "select t.id,NVL(t.PROJECT_ITEM_NAME4,'其他') car_id,(case when SUB_ITEM4 is null or SUB_ITEM4='无' then '其他' else SUB_ITEM4  end) clss, "+
	       "t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state,t.FYBX_TYPE "+  
	       "from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0 and t.FYBX_TYPE = 1 "+
	       ") where state = 'BXYES'  ";
		if(StringUtil.isNotEmpty(start)){
			sql += "and tm >= to_date('"+start+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(end)){
			sql += "and tm <= to_date('"+end+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(carIds)){
			carIds = carIds.replaceAll(",", "','");
			sql += "and car_id in ('"+carIds+"')";
		}
		String statistics = "select  car_id,clss ,sum(money) money from ("
				+sql+") group by car_id,clss";
		
		String department = "select distinct car_id from ("+sql+")";
		String clsses = "select distinct clss from ("+sql+")";
		List<Map<String, Object>> sts = pxfbxdDao.createSQLQuery(statistics).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		List<String> stsDept = pxfbxdDao.createSQLQuery(department).list();
		List<String> stsClss = pxfbxdDao.createSQLQuery(clsses).list();
		map.put("rows", sts);
		map.put("carId", stsDept);
		map.put("clsses", stsClss);
		return map;
	}
	/**
	 * 统计各部门收入
	 * @param dept 
	 * @param endDate 
	 * @param startDate 
	 * @param unit 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsIncome(String startDate, String endDate, String dept, String unit) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select decode(t2.unit_name, null, t.check_dep_name, t2.unit_name) dept, "+
					               "(case "+
					                 "when t.invoice_type in ('tax', 'ordinary', 'knot') then '税票' "+
					                 "else '一般' "+
					               "end) clss, "+
					               "decode(t2.unit_name, null, t.pay_received, t2.money) total, "+ 
					               "t.pay_date, decode(t2.unit_id, null, t.check_dep_id, t2.unit_id) dept_id "+
					          "from PAYMENT_LIST t, TJY2_PAY_INFO_UNIT t2 "+
					         "where t.id = t2.fk_pay_info_id(+) ";
		sql = "select * from ("+sql+") where 1=1 ";
		if(StringUtil.isNotEmpty(startDate)){
			sql += "and pay_date >= to_date('"+startDate+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(endDate)){
			sql += "and pay_date <= to_date('"+endDate+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		
		
		if(StringUtil.isNotEmpty(dept)){
			dept = dept.replaceAll(",", "','");
			sql += "and dept in ('"+dept+"') ";
		}
		String department = "select distinct t.dept,o.orders2 from ("+sql+") t,sys_org o where t.dept_id = o.id order by o.orders2";
		String clsses = "select distinct clss from ("+sql+")";
		List<Object[]> stsDept = pxfbxdDao.createSQLQuery(department).list();
		List<String> depts = new ArrayList<String>();
		for (Object[] str : stsDept) {
			depts.add(str[0].toString());
		}
		List<String> stsClss = pxfbxdDao.createSQLQuery(clsses).list();
		
		String rowsql = "select t.dept, t.clss, sum(t.total) money from ("+sql+") t,sys_org o where t.dept_id = o.id group by t.dept,o.orders2,t.clss order by o.orders2"; 
		List<Map<String, Object>> list= pxfbxdDao.createSQLQuery(rowsql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		map.put("rows", list);
		map.put("depts", depts);
		map.put("clsses", stsClss);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> statisticsDeptDetail(String dept,String clss,String startDate,String endDate ){
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql="select * from ( select decode(t2.unit_name, null, t.check_dep_name, t2.unit_name) dept,"
				+ " (case when t.invoice_type in ('tax', 'ordinary', 'knot') then '税票' else '一般' end) clss,"
				+ " decode(t2.unit_name, null, t.pay_received, t2.money) total"
				+ ",t.invoice_no,t.company_name,t.check_dep_name,t.pay_no,t.pay_received"
				+ " ,t.pay_type,t.cash_pay,t.remark,pos,t.hand_in,t.receive_man_name, t.pay_date "
				+ " from PAYMENT_LIST t, TJY2_PAY_INFO_UNIT t2"
				+ " where t.id = t2.fk_pay_info_id(+) ) "
				+ " where 1=1  AND dept='"+dept+"'";
		
		if(StringUtil.isNotEmpty(startDate)){
			sql += "and pay_date >= to_date('"+startDate+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(endDate)){
			sql += "and pay_date <= to_date('"+endDate+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(clss)){
			sql+="AND clss='"+clss+"'";
		}
		
		List<Map<String, Object>> list= pxfbxdDao.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		CodeTable codeTable = codeTableCache.getCodeTable("PAY_TYPE");
		List<CodeTableValue> values = codeTable.getCodeTableValues();
		Map<String,String> codeMap = new HashMap<String,String>();
		for (int i = 0; i < values.size() ; i++) {
			codeMap.put(values.get(i).getValue(),values.get(i).getName());
		}
		for (Map<String, Object> map2 : list) {
			String payType=String.valueOf(map2.get("PAY_TYPE")) ;
			map2.put("PAY_TYPE",codeMap.get(payType));
			String patdate=String.valueOf(map2.get("PAY_DATE")).trim();
			if(StringUtil.isNotEmpty(patdate)){
				map2.put("PAY_DATE", patdate.substring(0, 10));
			}
		}
		map.put("rows", list);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> incomeCompareFee(String startDate, String endDate, String dept, String unit) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//支出
		String cost_sql = "select * from (select t.id,(case when SUB_ITEM1 is null or SUB_ITEM1='无' "
				+ "then '其他' else SUB_ITEM1  end) clss,t.department_id,t.department, t.money1 money,t.BS_DATE tm,t.ABOLISH ,t.status state "
				+"from TJY2_FYBXD t where t.ABOLISH is null and t.money1 is not null and t.money1 !=0  and t.status not in  ('REGISTER','SUBMIT') "
				+"union all select t.id,(case when SUB_ITEM2 is null or SUB_ITEM2='无' "
							+ "then '其他' else SUB_ITEM2  end) clss,t.department_id,t.department, t.money2,t.BS_DATE tm,t.ABOLISH ,t.status state "
				+"from TJY2_FYBXD t where t.ABOLISH is null and t.money2 is not null and t.money2 !=0  and t.status not in  ('REGISTER','SUBMIT') "
				+"union all select t.id,(case when SUB_ITEM3 is null or SUB_ITEM3='无' "
								+ "then '其他' else SUB_ITEM3  end) clss,t.department_id,t.department, t.money3,t.BS_DATE tm,t.ABOLISH ,t.status state "
				+"from TJY2_FYBXD t where t.ABOLISH is null and t.money3 is not null and t.money3 !=0  and t.status not in  ('REGISTER','SUBMIT') "
				+"union all select t.id,(case when SUB_ITEM4 is null or SUB_ITEM4='无' "
								+ "then '其他' else SUB_ITEM4  end) clss,t.department_id,t.department, t.money4,t.BS_DATE tm,t.ABOLISH ,t.status state "
				+"from TJY2_FYBXD t where t.ABOLISH is null and t.money4 is not null and t.money4 !=0  and t.status not in  ('REGISTER','SUBMIT') "
				+"union all select t.id,'差旅费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state "
				+"from TJY2_CLFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') "
				+"union all select t.id,'培训费' clss,t.department_id,t.department, t.CL_HJ_JEXJ money,t.CL_SJ tm,t.ABOLISH,t.statue state "
				+"from TJY2_PXFBXD t where t.ABOLISH is null and t.CL_HJ_JEXJ is not null and t.CL_HJ_JEXJ !=0  and t.STATUE not in  ('REGISTER','SUBMIT') ) where state = 'BXYES'";
		if(StringUtil.isNotEmpty(startDate)){
			cost_sql += "and tm >= to_date('"+startDate+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(endDate)){
			cost_sql += "and tm <= to_date('"+endDate+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		
		
		
		if(StringUtil.isNotEmpty(dept)){
			dept = dept.replaceAll(",", "','");
			cost_sql += "and department in ('"+dept+"')";
		}
		
		String orgSql = " p,sys_org o where o.id = p.department_id and o.id not in ('100042','100047') and o.parent_id not in ('100042','100047') ";
		//处理统计单位问题
		if(unit!=null&&StringUtil.isNotEmpty(unit)&&"协会".equals(unit)) {
			orgSql = " p,sys_org o where o.id = p.department_id and (o.id = '100042' or o.parent_id ='100042')";
		}
				
				
		//String cost_depts_sql = "select distinct department from ("+cost_sql+")"+ orgSql;
		String cost_clsses_sql = "select distinct clss from ("+cost_sql+")"+ orgSql;
		String cost_rows_sql = "select department dept,clss,SUM(money) money from ("+cost_sql+")"+ orgSql+" group by department,clss order by department, clss ";
		 
		//List<String> cost_depts = pxfbxdDao.createSQLQuery(cost_depts_sql).list();
		List<String> cost_clsses = pxfbxdDao.createSQLQuery(cost_clsses_sql).list();
		List<Map<String, Object>> cost_rows = pxfbxdDao.createSQLQuery(cost_rows_sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		//收入
		String income_sql = "select decode(t2.unit_name, null, t.check_dep_name, t2.unit_name) dept, "+
		               "(case "+
		                   "when t.invoice_type in ('tax', 'ordinary', 'knot') then '税票' "+
		                   "else '一般' "+
			               "end) clss, "+
			               "decode(t2.unit_name, null, t.pay_received, t2.money) total, "+ 
			               "t.pay_date, decode(t2.unit_id, null, t.check_dep_id, t2.unit_id) dept_id "+
			          "from PAYMENT_LIST t, TJY2_PAY_INFO_UNIT t2 "+
			         "where t.id = t2.fk_pay_info_id(+)";
		income_sql = "select * from ("+income_sql+") where 1=1 ";
		if(StringUtil.isNotEmpty(startDate)){
			income_sql += "and pay_date >= to_date('"+startDate+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		if(StringUtil.isNotEmpty(endDate)){
			income_sql += "and pay_date <= to_date('"+endDate+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		//dept = dept.replaceAll(",", "','");上面已经替换一次了，不要再替换
		if(StringUtil.isNotEmpty(dept)){
			income_sql += "and dept in ('"+dept+"')";
		}
		//String income_depts_sql = "select distinct dept from ("+income_sql+")";
		String queryDeptSql = "select distinct department,o.orders2 from (select dept_id department_id,dept department from ("+income_sql+") union all select department_id,department from ("+cost_sql+")) "+ orgSql + "order by o.orders2";
		String income_clsses_sql = "select distinct clss from ("+income_sql+")";
		String income_rows_sql = "select dept, clss, sum(total) money from ("+income_sql+") group by dept, clss order by dept"; 
		
		//List<String> income_depts = pxfbxdDao.createSQLQuery(income_depts_sql).list();
		List<Object[]> allDepts = pxfbxdDao.createSQLQuery(queryDeptSql).list();
		List<String> income_clsses = pxfbxdDao.createSQLQuery(income_clsses_sql).list();
		List<Map<String, Object>> income_rows= pxfbxdDao.createSQLQuery(income_rows_sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		//部门去重
		/*Set<String> set = new HashSet<String>();
		for(String department :cost_depts){
			set.add(department);
		}
		for(String department :income_depts){
			set.add(department);
			
		}*/
		List<String> depts = new ArrayList<String>();
		for (Object[] str : allDepts) {
			depts.add(str[0].toString());
		}
		map.put("depts", depts);
		map.put("clsses_in", income_clsses);
		map.put("clsses_out", cost_clsses);
		map.put("rows_in", income_rows);
		map.put("rows_out",cost_rows);
		return map;
	}
	
	/**
	 * 存货出入库按照部门统计
	 * author pingZhou
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param dept 部门
	 * @param unit 单位
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> inventory(String startDate, String endDate, String dept,String unit) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
	
		//出库
				String cost_sql = "select o.org_name dept, '出库' clss, sum(to_number(decode(a.total,null,0,a.total))) money,o.id  " + 
						"  from sys_org o, " + 
						"       (select t.department_id,  sum(t.total) total " + 
						"          from TJY2_FYBXD t " + 
						"         where t.fybx_type = '4' and t.status='BXYES' " ;
				if(StringUtil.isNotEmpty(startDate)){
					cost_sql += "and bs_date >= to_date('"+startDate+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
				}
				if(StringUtil.isNotEmpty(endDate)){
					cost_sql += "and bs_date <= to_date('"+endDate+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
				}
				
				cost_sql = cost_sql + "         group by t.department_id) a " + 
						" where a.department_id(+) = o.id and o.status='used'";
				
				if(StringUtil.isNotEmpty(dept)){
					dept = dept.replaceAll(",", "','");
					cost_sql += "and org_name in ('"+dept+"')";
				}
				cost_sql += "group by o.org_name,o.id";
				
				//String cost_depts_sql = "select distinct dept from ("+cost_sql+")";
				 
				//List<String> cost_depts = pxfbxdDao.createSQLQuery(cost_depts_sql).list();
				List<String> cost_clsses = new ArrayList<String>();
				cost_clsses.add("出库");
				List<Map<String, Object>> cost_rows = pxfbxdDao.createSQLQuery(cost_sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				
				//入库
				String in_sql =  "select o.org_name dept, '入库' clss, sum(to_number(decode(a.total,null,0,a.total))) money ,o.id " + 
						"  from sys_org o, " + 
						"       (select t.department_id,  sum(t.total) total " + 
						"          from TJY2_FYBXD t " + 
						"         where t.fybx_type = '3' and t.status='BXYES' " ;
				if(StringUtil.isNotEmpty(startDate)){
					in_sql += "and bs_date >= to_date('"+startDate+"'||' 00:00:00','yyyy-MM-dd hh24:mi:ss')";
				}
				if(StringUtil.isNotEmpty(endDate)){
					in_sql += "and bs_date <= to_date('"+endDate+"'||' 23:59:59','yyyy-MM-dd hh24:mi:ss')";
				}
				
				in_sql = in_sql + "         group by t.department_id) a " + 
						" where a.department_id(+) = o.id and o.status='used'";
				
				if(StringUtil.isNotEmpty(dept)){
					dept = dept.replaceAll(",", "','");
					in_sql += "and org_name in ('"+dept+"')";
				}
				in_sql += "group by o.org_name,o.id";
				//String in_depts_sql = "select distinct dept from ("+in_sql+")";
				String queryDeptSql = "select distinct t.dept,o.orders2 from (select * from ("+in_sql+") union all select * from ("+cost_sql+")) t,sys_org o where t.id = o.id order by o.orders2";
				 
				//List<String> in_depts = pxfbxdDao.createSQLQuery(in_depts_sql).list();
				List<Object[]> allDepts = pxfbxdDao.createSQLQuery(queryDeptSql).list();
				List<String> depts = new ArrayList<String>();
				for (Object[] str : allDepts) {
					depts.add(str[0].toString());
				}
				List<String> in_clsses = new ArrayList<String>();
				in_clsses.add("入库");
				List<Map<String, Object>> in_rows = pxfbxdDao.createSQLQuery(in_sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				//部门去重
				/*Set<String> set = new HashSet<String>();
				for(String department :cost_depts){
					set.add(department);
				}
				for(String department :in_depts){
					set.add(department);
				}*/
				map.put("depts", depts);
				map.put("clsses_in", in_clsses);
				map.put("clsses_out", cost_clsses);
				map.put("rows_in", in_rows);
				map.put("rows_out",cost_rows);
		
		return map;
	}
}
