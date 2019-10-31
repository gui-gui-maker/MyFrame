package com.lsts.statistics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.pub.codetable.bean.CodeTableValue;
import com.khnt.rbac.impl.bean.Area;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.statistics.bean.DeviceCountDTO;

/**
 * 统计分析
 * 
 * @author GaoYa
 * @Date 2014-9-6
 */
@Repository("analyseDao")
public class AnalyseDao extends HibernateDaoSupport {
	private static Connection conn = null; // 数据库连接
	private static PreparedStatement pstmt = null; // 数据库操作对象
	private static ResultSet rs = null; // 结果集

	/**
	 * 按区划和类型统计
	 * 
	 * @param mainCode
	 *            设备主类型
	 * @param areaCode
	 *            区划代码
	 * @return 统计结果
	 */
	public String deviceCountByArea(String mainCode, String areaCode,
			Date startDate, Date endDate) throws Exception {
		String sql = "select sum(recount) from (select t.device_sort_code,t.device_area_code, t.device_name,"
				+ " count(t.id) recount from BASE_DEVICE_DOCUMENT t "
				+ " where substr(t.device_sort_code, 0, 1) = ? and t.device_area_code=? and t.inspect_next_date - sysdate < 0 "
				+ " and t.device_status ='2' ";
		sql += " group by t.device_name,t.device_sort_code,t.device_area_code ) tb";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, mainCode);
		query.setString(1, areaCode);
		Object obj = query.uniqueResult();
		return obj == null ? "0" : obj.toString();
	}

	/**
	 * 按区划和类型统计
	 * 
	 * @param mainCode
	 *            设备主类型
	 * @param areaCode
	 *            区划代码
	 * @return 统计结果
	 */
	public String deviceUncheckCountByArea(String mainCode, String areaCode) {
		String sql = "select sum(recount1) uncheck_count from ( select t.device_sort_code,t.device_area_code, t.device_name,"
				+ " sum(nvl2(t3.id, 0, 1)) recount1 from BASE_DEVICE_DOCUMENT t, "
				+ "  (select s.fk_base_device_document_id id, max(s.deal_status) from TZSB_WARNING_STATUS s "
				+ " where deal_time >= add_months(sysdate, -6) group by s.fk_base_device_document_id) t3"
				+ " where  substr(t.device_sort_code, 0, 1) = ? and t.id = t3.id(+) and t.device_area_code=? and t.inspect_next_date - sysdate < 0 "
				+ " and t.device_status ='2' ";
		sql += " group by t.device_name,t.device_sort_code,t.device_area_code ) tb";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, mainCode);
		query.setString(1, areaCode);
		Object obj = query.uniqueResult();
		return obj == null ? "0" : obj.toString();
	}

	/**
	 * 
	 * @return 返回成都行政区划（锦江区、金牛区、高新区、双流县）
	 */
	public List<Area> vAreaList() {
		String sql = "select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE v where v.PARENT_ID='510100' and v.REGIONAL_CODE in ('510104','510106','510109','510122','510189')";
		SQLQuery query = getSession().createSQLQuery(sql);
		List list = query.list();
		List<Area> listarea = new ArrayList<Area>();

		for (int i = 0; i < list.size(); i++) {
			Area area = new Area();
			Object obj = list.get(i);
			Object[] ob = (Object[]) obj;
			area.setId(String.valueOf(ob[0]));
			area.setPid(String.valueOf(ob[1]));
			area.setCode(String.valueOf(ob[2]));
			area.setText(String.valueOf(ob[3]));
			listarea.add(area);
		}
		return listarea;
	}

	/**
	 * 
	 * @return 返回设备分类中的主类
	 */
	@SuppressWarnings("unchecked")
	public List<CodeTableValue> deviceSortMainCode() {
		String sql = "select id,name,value from PUB_CODE_TABLE_VALUES t where code_table_id=(select id from pub_code_table where code='device_classify') and code_table_values_id is null";
		SQLQuery query = getSession().createSQLQuery(sql);
		List list = query.list();
		List<CodeTableValue> listct = new ArrayList<CodeTableValue>();
		for (int i = 0; i < list.size(); i++) {
			CodeTableValue ctable = new CodeTableValue();
			Object obj = list.get(i);
			Object[] ob = (Object[]) obj;
			ctable.setId(String.valueOf(ob[0]));
			ctable.setName(String.valueOf(ob[1]));
			ctable.setValue(String.valueOf(ob[2]));
			listct.add(ctable);
		}
		return listct;
	}

	/**
	 * 各检验部门检验业务统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * @param org_id
	 *            当前用户所属部门ID
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */
	public List<DeviceCountDTO> queryCount(String startDate, String endDate,
			String device_type, String org_id) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd",
					startDate));
			calendar.add(Calendar.YEAR, -1);
			String lastStartDate = DateUtil.getDateTime("yyyy-MM-dd", calendar
					.getTime());
			calendar.setTime(DateUtil
					.convertStringToDate("yyyy-MM-dd", endDate));
			calendar.add(Calendar.YEAR, -1);
			String endStartDate = DateUtil.getDateTime("yyyy-MM-dd", calendar
					.getTime());

			String sql = "select o.org_name as check_unit, o.orders, "
					+ "count(case when t1.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as cur_dj_count, "
					+ "count(case when t1.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as cur_jj_count, "
					+ "count(case when t1.check_type = '4' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as cur_wj_count, "
					+ "count(case when t1.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as last_dj_count, "
					+ "count(case when t1.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as last_jj_count, "
					+ "count(case when t1.check_type = '4' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as last_wj_count "
					+ "from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ "where substr(d.device_sort_code,0,1)=? and t.print_time is not null "
					+ "and t.check_unit_id=o.id(+) and t.fk_inspection_id = t1.id(+) and t.fk_tsjc_device_document_id = d.id(+) ";
			if ("100020".equals(org_id) || "100021".equals(org_id)
					|| "100022".equals(org_id) || "100023".equals(org_id)
					|| "100024".equals(org_id) || "100033".equals(org_id)
					|| "100034".equals(org_id) || "100035".equals(org_id)
					|| "100036".equals(org_id) || "100037".equals(org_id)) {
				sql += " and t.check_unit_id = '" + org_id + "'";
			}
			sql += " and t.data_status <> '99' group by o.org_name, o.orders order by o.orders";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, lastStartDate);
			pstmt.setString(8, endStartDate);
			pstmt.setString(9, lastStartDate);
			pstmt.setString(10, endStartDate);
			pstmt.setString(11, lastStartDate);
			pstmt.setString(12, endStartDate);
			pstmt.setString(13, device_type);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setCheck_unit(rs.getString("check_unit"));
				deviceCount.setCur_dj_count(rs.getInt("cur_dj_count"));
				deviceCount.setCur_jj_count(rs.getInt("cur_jj_count"));
				deviceCount.setCur_wj_count(rs.getInt("cur_wj_count"));
				deviceCount
						.setCur_jy_total(rs.getInt("cur_dj_count")
								+ rs.getInt("cur_jj_count")
								+ rs.getInt("cur_wj_count"));
				deviceCount.setLast_jy_total(rs.getInt("last_dj_count")
						+ rs.getInt("last_jj_count")
						+ rs.getInt("last_wj_count"));
				deviceCount.setCompare_count(deviceCount.getCur_jy_total()
						- deviceCount.getLast_jy_total());
				list.add(deviceCount);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 各部门人员业务统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param org_id
	 *            检验部门ID
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-11-21 下午03:55:00
	 */
	public List<DeviceCountDTO> deviceCountByUser(String startDate,
			String endDate, String org_id) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			/*String sql = "with loginfo as (select t1.report_sn,t.business_id,t.op_user_id,t.op_user_name,t.op_action, max(t.op_time) op_time "
					+ " from sys_log t, tzsb_inspection_info t1 where t.business_id = t1.id and t1.data_status <> '99' "
					+ " and t.op_time>=to_date(?,'yyyy-MM-dd') and t.op_time<to_date(?,'yyyy-MM-dd') "
					+ " group by t1.report_sn,t.business_id,t.op_user_id,t.op_user_name,t.op_action),"
					+ " userinfo as ( select o.id org_id, o.org_name org_name, u.id user_id, u.name user_name,u.sort user_sort from sys_user u, sys_org o "
					+ " where u.org_id = o.id and u.status='1' ";
					if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
						sql += " and o.id = ? ";
					}else{
						sql += " and o.id in ('100020','100021','100022','100023','100063','100024','100033','100034','100035','100036','100037','100027')";
					}
					" ) select b.org_id,b.org_name,b.user_name,b.user_sort, count(case when a.op_action = '报告录入' then 1 else null end) as lr_count,"
					+ " count(case when a.op_action = '报告审核' then 1 else null end) as sh_count, "
					+ " count(case when a.op_action = '报告签发' then 1 else null end) as qf_count,"
					+ " count(case when a.op_action = '打印报告' then 1 else null end) as dy_count, "
					+ " count(case when a.op_action = '打印合格证' then 1 else null end) as dyhgz_count, "
					+ " count(case when a.op_action = '报告领取' then 1 else null end) as lq_count,"
					+ " count(case when a.op_action = '报告归档' then 1 else null end) as gd_count "
					+ " from loginfo a, userinfo b where b.user_id = a.op_user_id(+)  group by b.org_id, b.org_name,b.user_name,b.user_sort order by b.org_id,b.user_sort ";*/
			/*String sql = "with userinfo as (select o.id org_id, o.org_name org_name, u.id user_id, u.name user_name,u.sort user_sort from sys_user u, sys_org o "
					+ " where u.org_id = o.id and u.status='1' ";
					if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
						sql += " and o.id = ? ";
					}else{
						sql += " and o.id in ('100020','100021','100022','100023','100063','100024','100033','100034','100035','100036','100037','100027')";
					}
					sql += ") select b.org_id,b.org_name,b.user_name,b.user_sort, count(case when a.op_action = '报告录入' then 1 else null end) as lr_count,"
					+ " count(case when a.op_action = '报告审核' then 1 else null end) as sh_count, "
					+ " count(case when a.op_action = '报告签发' then 1 else null end) as qf_count,"
					+ " count(case when a.op_action = '打印报告' then 1 else null end) as dy_count, "
					+ " count(case when a.op_action = '打印合格证' then 1 else null end) as dyhgz_count, "
					+ " count(case when a.op_action = '报告领取' then 1 else null end) as lq_count,"
					+ " count(case when a.op_action = '报告归档' then 1 else null end) as gd_count "
					+ " from wv_sys_log a, userinfo b where b.user_id = a.op_user_id(+) "
					+ " and a.op_time >= to_date(?, 'yyyy-MM-dd')" 
					+ " and a.op_time <= to_date(?, 'yyyy-MM-dd')"
					+ " group by b.org_id, b.org_name,b.user_name,b.user_sort order by b.org_id,b.user_sort ";*/
			String sql = "with loginfo as (select t1.report_sn,t.business_id,t.op_user_id,t.op_user_name,t.op_action,op_time"
					+ " from ( select log.business_id,log.op_user_id,log.op_user_name,log.op_action,max(log.op_time) op_time"
					+ " from sys_log log  where log.op_time >= to_date(?, 'yyyy-MM-dd') and log.op_time <= to_date(?, 'yyyy-MM-dd')"
					+ " group by log.business_id,log.op_user_id,log.op_user_name,log.op_action) t,tzsb_inspection_info t1"
					+ " where t.business_id = t1.id and t1.data_status <> '99'),"
					+ " userinfo as (select o.id org_id,o.org_name org_name,u.id user_id,u.name user_name,u.sort user_sort from sys_user u, sys_org o"
					+ " where u.org_id = o.id and u.status='1'";
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				sql += " and o.id = ? ";
			}else{
				sql += " and o.id in ('100020','100021','100022','100023','100063','100024','100033','100034','100035','100036','100037','100027')";
			}
			sql += " ) select b.org_id,b.org_name,b.user_name,b.user_sort,"
					+ " count(case when a.op_action = '报告录入' then 1 else null end) as lr_count,"
					+ " count(case when a.op_action = '报告审核' then 1 else null end) as sh_count,"
					+ " count(case when a.op_action = '报告签发' then 1 else null end) as qf_count,"
					+ " count(case when a.op_action = '打印报告' then 1 else null end) as dy_count,"
					+ " count(case when a.op_action = '打印合格证' then 1 else null end) as dyhgz_count,"
					+ " count(case when a.op_action = '报告领取' then 1 else null end) as lq_count,"
					+ " count(case when a.op_action = '报告归档' then 1 else null end) as gd_count"
					+ " from loginfo a, userinfo b where b.user_id = a.op_user_id(+)  group by b.org_id, b.org_name, b.user_name,b.user_sort order by b.user_sort";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, org_id);
			}else {
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setOrg_name(rs.getString("org_name"));
				deviceCount.setUser_name(rs.getString("user_name"));
				deviceCount.setLr_count(rs.getInt("lr_count"));
				deviceCount.setSh_count(rs.getInt("sh_count"));
				deviceCount.setQf_count(rs.getInt("qf_count"));
				deviceCount.setDy_count(rs.getInt("dy_count"));
				deviceCount.setDyhgz_count(rs.getInt("dyhgz_count"));
				deviceCount.setLq_count(rs.getInt("lq_count"));
				deviceCount.setGd_count(rs.getInt("gd_count"));
				list.add(deviceCount);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 各部门人员业务统计表导出
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param org_id
	 *            检验部门ID
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-11-21 下午03:55:00
	 */
	public List<DeviceCountDTO> exportCountByUser(String startDate,
			String endDate, String org_id) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			/*String sql = "with userinfo as (select o.id org_id, o.org_name org_name, u.id user_id, u.name user_name,u.sort user_sort from sys_user u, sys_org o "
					+ " where u.org_id = o.id  and u.status='1' ";
					if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
						sql += " and o.id = ? ";
					}else{
						sql += " and o.id in ('100020','100021','100022','100023','100063','100024','100033','100034','100035','100036','100037','100027')";
					}
					sql += ") select b.org_id,b.org_name,b.user_name,b.user_sort, count(case when a.op_action = '报告录入' then 1 else null end) as lr_count,"
					+ " count(case when a.op_action = '报告审核' then 1 else null end) as sh_count, "
					+ " count(case when a.op_action = '报告签发' then 1 else null end) as qf_count,"
					+ " count(case when a.op_action = '打印报告' then 1 else null end) as dy_count, "
					+ " count(case when a.op_action = '打印合格证' then 1 else null end) as dyhgz_count, "
					+ " count(case when a.op_action = '报告领取' then 1 else null end) as lq_count,"
					+ " count(case when a.op_action = '报告归档' then 1 else null end) as gd_count "
					+ " from wv_sys_log a, userinfo b where b.user_id = a.op_user_id(+) "
					+ " and a.op_time >= to_date(?, 'yyyy-MM-dd')" 
					+ " and a.op_time <= to_date(?, 'yyyy-MM-dd')"
					+ " group by b.org_id, b.org_name,b.user_name,b.user_sort order by b.org_id,b.user_sort ";*/
			String sql = "with loginfo as (select t1.report_sn,t.business_id,t.op_user_id,t.op_user_name,t.op_action,op_time"
					+ " from ( select log.business_id,log.op_user_id,log.op_user_name,log.op_action,max(log.op_time) op_time"
					+ " from sys_log log  where log.op_time >= to_date(?, 'yyyy-MM-dd') and log.op_time <= to_date(?, 'yyyy-MM-dd')"
					+ " group by log.business_id,log.op_user_id,log.op_user_name,log.op_action) t,tzsb_inspection_info t1"
					+ " where t.business_id = t1.id and t1.data_status <> '99'),"
					+ " userinfo as (select o.id org_id,o.org_name org_name,u.id user_id,u.name user_name,u.sort user_sort from sys_user u, sys_org o"
					+ " where u.org_id = o.id and u.status='1'";
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				sql += " and o.id = ? ";
			}else{
				sql += " and o.id in ('100020','100021','100022','100023','100063','100024','100033','100034','100035','100036','100037','100027')";
			}
			sql += " ) select b.org_id,b.org_name,b.user_name,b.user_sort,"
					+ " count(case when a.op_action = '报告录入' then 1 else null end) as lr_count,"
					+ " count(case when a.op_action = '报告审核' then 1 else null end) as sh_count,"
					+ " count(case when a.op_action = '报告签发' then 1 else null end) as qf_count,"
					+ " count(case when a.op_action = '打印报告' then 1 else null end) as dy_count,"
					+ " count(case when a.op_action = '打印合格证' then 1 else null end) as dyhgz_count,"
					+ " count(case when a.op_action = '报告领取' then 1 else null end) as lq_count,"
					+ " count(case when a.op_action = '报告归档' then 1 else null end) as gd_count"
					+ " from loginfo a, userinfo b where b.user_id = a.op_user_id(+)  group by b.org_id, b.org_name, b.user_name,b.user_sort order by b.user_sort";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			if (StringUtil.isNotEmpty(org_id) && !"all".equals(org_id)) {
				pstmt.setString(1, org_id);
				pstmt.setString(2, startDate);
				pstmt.setString(3, endDate);
			}else {
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
			}
			rs = pstmt.executeQuery();
			int lr_total = 0;
			int sh_total = 0;
			int qf_total = 0;
			int dy_total = 0;
			int dyhgz_total = 0;
			int lq_total = 0;
			int gd_total = 0;

			while (rs.next()) {
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setOrg_name(rs.getString("org_name"));
				deviceCount.setUser_name(rs.getString("user_name"));
				deviceCount.setLr_count(rs.getInt("lr_count"));
				deviceCount.setSh_count(rs.getInt("sh_count"));
				deviceCount.setQf_count(rs.getInt("qf_count"));
				deviceCount.setDy_count(rs.getInt("dy_count"));
				deviceCount.setDyhgz_count(rs.getInt("dyhgz_count"));
				deviceCount.setLq_count(rs.getInt("lq_count"));
				deviceCount.setGd_count(rs.getInt("gd_count"));
				lr_total += deviceCount.getLr_count();
				sh_total += deviceCount.getSh_count();
				qf_total += deviceCount.getQf_count();
				dy_total += deviceCount.getDy_count();
				dyhgz_total += deviceCount.getDyhgz_count();
				lq_total += deviceCount.getLq_count();
				gd_total += deviceCount.getGd_count();
				list.add(deviceCount);
			}
			DeviceCountDTO deviceCount = new DeviceCountDTO();
			deviceCount.setUser_name("");
			deviceCount.setLr_total(lr_total);
			deviceCount.setSh_total(sh_total);
			deviceCount.setQf_total(qf_total);
			deviceCount.setDy_total(dy_total);
			deviceCount.setDyhgz_total(dyhgz_total);
			deviceCount.setLq_total(lq_total);
			deviceCount.setGd_total(gd_total);
			list.add(deviceCount);

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 各检验部门检验业务统计表导出
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-22 上午11:11:00
	 */
	public List<DeviceCountDTO> exportCount(String startDate, String endDate,
			String device_type) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.convertStringToDate("yyyy-MM-dd",
					startDate));
			calendar.add(Calendar.YEAR, -1);
			String lastStartDate = DateUtil.getDateTime("yyyy-MM-dd", calendar
					.getTime());
			calendar.setTime(DateUtil
					.convertStringToDate("yyyy-MM-dd", endDate));
			calendar.add(Calendar.YEAR, -1);
			String endStartDate = DateUtil.getDateTime("yyyy-MM-dd", calendar
					.getTime());

			String sql = "select o.org_name as check_unit, "
					+ "count(case when t1.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as cur_dj_count, "
					+ "count(case when t1.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as cur_jj_count, "
					+ "count(case when t1.check_type = '4' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as cur_wj_count, "
					+ "count(case when t1.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as last_dj_count, "
					+ "count(case when t1.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as last_jj_count, "
					+ "count(case when t1.check_type = '4' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as last_wj_count "
					+ "from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ "where substr(d.device_sort_code,0,1)=? and t.print_time is not null "
					+ "and t.check_unit_id=o.id(+) and t.fk_inspection_id = t1.id(+) and t.fk_tsjc_device_document_id = d.id(+) "
					+ " and t.data_status <> '99' group by o.org_name";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, lastStartDate);
			pstmt.setString(8, endStartDate);
			pstmt.setString(9, lastStartDate);
			pstmt.setString(10, endStartDate);
			pstmt.setString(11, lastStartDate);
			pstmt.setString(12, endStartDate);
			pstmt.setString(13, device_type);
			rs = pstmt.executeQuery();
			int dj_total = 0; // 定期检验合计
			int jj_total = 0; // 监督检验合计
			int wj_total = 0; // 委托检验合计
			int total = 0; // 当前检验合计（总数）
			int last_total = 0; // 去年同期检验合计（总数）
			int compare_total = 0; // 与去年同期比较合计（总数）
			while (rs.next()) {
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setCheck_unit(rs.getString("check_unit"));
				deviceCount.setCur_dj_count(rs.getInt("cur_dj_count"));
				deviceCount.setCur_jj_count(rs.getInt("cur_jj_count"));
				deviceCount.setCur_wj_count(rs.getInt("cur_wj_count"));
				deviceCount
						.setCur_jy_total(rs.getInt("cur_dj_count")
								+ rs.getInt("cur_jj_count")
								+ rs.getInt("cur_wj_count"));
				deviceCount.setLast_jy_total(rs.getInt("last_dj_count")
						+ rs.getInt("last_jj_count")
						+ rs.getInt("last_wj_count"));
				deviceCount.setCompare_count(deviceCount.getCur_jy_total()
						- deviceCount.getLast_jy_total());
				list.add(deviceCount);
				dj_total += deviceCount.getCur_dj_count();
				jj_total += deviceCount.getCur_jj_count();
				wj_total += deviceCount.getCur_wj_count();
				total += deviceCount.getCur_jy_total();
				last_total += deviceCount.getLast_jy_total();
				compare_total += deviceCount.getCompare_count();
			}
			DeviceCountDTO deviceCount = new DeviceCountDTO();
			deviceCount.setDj_total(dj_total);
			deviceCount.setJj_total(jj_total);
			deviceCount.setWj_total(wj_total);
			deviceCount.setTotal(total);
			deviceCount.setLast_total(last_total);
			deviceCount.setCompare_total(compare_total);
			list.add(deviceCount);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 各部门已打印的电梯定检、监检报告统计表
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */
	public List<DeviceCountDTO> devicePrintedCount(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " with info as ( "
					+ " select t.id,d.device_sort_code,t.print_time,t1.check_type,o.id org_id from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ " where t1.id = t.fk_inspection_id and t.fk_tsjc_device_document_id = d.id and t.check_unit_id = o.id and t.print_time is not null and d.device_sort_code(+) like '3%' "
					+ " ),codevalue as ( "
					+ " select pv.value,pv.name from pub_code_table p,pub_code_table_values pv "
					+ " where p.id = pv.code_table_id and p.code='device_classify' and pv.CODE_TABLE_VALUES_ID is not null and pv.value like '3%' ) "
					+ " select c.value,c.name, "
					+ " count(case when t.org_id='100020' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd1_jj_count, "
					+ " count(case when t.org_id='100020' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd1_dj_count, "
					+ " count(case when t.org_id='100021' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd2_jj_count, "
					+ " count(case when t.org_id='100021' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd2_dj_count, "
					+ " count(case when t.org_id='100022' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd3_jj_count, "
					+ " count(case when t.org_id='100022' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd3_dj_count, "
					+ " count(case when t.org_id='100023' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd4_jj_count, "
					+ " count(case when t.org_id='100023' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd4_dj_count "
					+ " from codevalue c , info t where c.value = t.device_sort_code(+) group by c.value,c.name ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, startDate);
			pstmt.setString(8, endDate);
			pstmt.setString(9, startDate);
			pstmt.setString(10, endDate);
			pstmt.setString(11, startDate);
			pstmt.setString(12, endDate);
			pstmt.setString(13, startDate);
			pstmt.setString(14, endDate);
			pstmt.setString(15, startDate);
			pstmt.setString(16, endDate);
			int jd1_yyyqz_dj = 0;
			int jd1_yyyqz_jj = 0;
			int jd2_yyyqz_dj = 0;
			int jd2_yyyqz_jj = 0;
			int jd3_yyyqz_dj = 0;
			int jd3_yyyqz_jj = 0;
			int jd4_yyyqz_dj = 0;
			int jd4_yyyqz_jj = 0;
			int jd1_yy_dj = 0;
			int jd1_yy_jj = 0;
			int jd2_yy_dj = 0;
			int jd2_yy_jj = 0;
			int jd3_yy_dj = 0;
			int jd3_yy_jj = 0;
			int jd4_yy_dj = 0;
			int jd4_yy_jj = 0;
			int jd1_ftrxd_dj = 0;
			int jd1_ftrxd_jj = 0;
			int jd2_ftrxd_dj = 0;
			int jd2_ftrxd_jj = 0;
			int jd3_ftrxd_dj = 0;
			int jd3_ftrxd_jj = 0;
			int jd4_ftrxd_dj = 0;
			int jd4_ftrxd_jj = 0;
			int jd1_qt_dj = 0;
			int jd1_qt_jj = 0;
			int jd2_qt_dj = 0;
			int jd2_qt_jj = 0;
			int jd3_qt_dj = 0;
			int jd3_qt_jj = 0;
			int jd4_qt_dj = 0;
			int jd4_qt_jj = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("value").endsWith("00")) {
					continue;
				}
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setDevice_code(rs.getString("value"));
				deviceCount.setDevice_name(rs.getString("name"));
				deviceCount.setJd1_dj_count(rs.getString("jd1_dj_count"));
				deviceCount.setJd1_jj_count(rs.getString("jd1_jj_count"));
				deviceCount.setJd2_dj_count(rs.getString("jd2_dj_count"));
				deviceCount.setJd2_jj_count(rs.getString("jd2_jj_count"));
				deviceCount.setJd3_dj_count(rs.getString("jd3_dj_count"));
				deviceCount.setJd3_jj_count(rs.getString("jd3_jj_count"));
				deviceCount.setJd4_dj_count(rs.getString("jd4_dj_count"));
				deviceCount.setJd4_jj_count(rs.getString("jd4_jj_count"));
				deviceCount.setTotal(rs.getInt("jd1_dj_count")
						+ rs.getInt("jd1_jj_count") + rs.getInt("jd2_dj_count")
						+ rs.getInt("jd2_jj_count") + rs.getInt("jd3_dj_count")
						+ rs.getInt("jd3_jj_count") + rs.getInt("jd4_dj_count")
						+ rs.getInt("jd4_jj_count"));
				if (rs.getString("value").startsWith("31")) {
					jd1_yyyqz_dj += rs.getInt("jd1_dj_count");
					jd1_yyyqz_jj += rs.getInt("jd1_jj_count");
					jd2_yyyqz_dj += rs.getInt("jd2_dj_count");
					jd2_yyyqz_jj += rs.getInt("jd2_jj_count");
					jd3_yyyqz_dj += rs.getInt("jd3_dj_count");
					jd3_yyyqz_jj += rs.getInt("jd3_jj_count");
					jd4_yyyqz_dj += rs.getInt("jd4_dj_count");
					jd4_yyyqz_jj += rs.getInt("jd4_jj_count");
				} else if (rs.getString("value").startsWith("32")) {
					jd1_yy_dj += rs.getInt("jd1_dj_count");
					jd1_yy_jj += rs.getInt("jd1_jj_count");
					jd2_yy_dj += rs.getInt("jd2_dj_count");
					jd2_yy_jj += rs.getInt("jd2_jj_count");
					jd3_yy_dj += rs.getInt("jd3_dj_count");
					jd3_yy_jj += rs.getInt("jd3_jj_count");
					jd4_yy_dj += rs.getInt("jd4_dj_count");
					jd4_yy_jj += rs.getInt("jd4_jj_count");
				} else if (rs.getString("value").startsWith("33")) {
					jd1_ftrxd_dj += rs.getInt("jd1_dj_count");
					jd1_ftrxd_jj += rs.getInt("jd1_jj_count");
					jd2_ftrxd_dj += rs.getInt("jd2_dj_count");
					jd2_ftrxd_jj += rs.getInt("jd2_jj_count");
					jd3_ftrxd_dj += rs.getInt("jd3_dj_count");
					jd3_ftrxd_jj += rs.getInt("jd3_jj_count");
					jd4_ftrxd_dj += rs.getInt("jd4_dj_count");
					jd4_ftrxd_jj += rs.getInt("jd4_jj_count");
				} else if (rs.getString("value").startsWith("34")) {
					jd1_qt_dj += rs.getInt("jd1_dj_count");
					jd1_qt_jj += rs.getInt("jd1_jj_count");
					jd2_qt_dj += rs.getInt("jd2_dj_count");
					jd2_qt_jj += rs.getInt("jd2_jj_count");
					jd3_qt_dj += rs.getInt("jd3_dj_count");
					jd3_qt_jj += rs.getInt("jd3_jj_count");
					jd4_qt_dj += rs.getInt("jd4_dj_count");
					jd4_qt_jj += rs.getInt("jd4_jj_count");
				}
				list.add(deviceCount);

			}
			DeviceCountDTO ckCount = new DeviceCountDTO();
			ckCount.setDevice_code("3100");
			ckCount.setDevice_name("<b>曳引与强制驱动电梯</b>");
			ckCount.setJd1_dj_count(String.valueOf(jd1_yyyqz_dj));
			ckCount.setJd1_jj_count(String.valueOf(jd1_yyyqz_jj));
			ckCount.setJd2_dj_count(String.valueOf(jd2_yyyqz_dj));
			ckCount.setJd2_jj_count(String.valueOf(jd2_yyyqz_jj));
			ckCount.setJd3_dj_count(String.valueOf(jd3_yyyqz_dj));
			ckCount.setJd3_jj_count(String.valueOf(jd3_yyyqz_jj));
			ckCount.setJd4_dj_count(String.valueOf(jd4_yyyqz_dj));
			ckCount.setJd4_jj_count(String.valueOf(jd4_yyyqz_jj));
			ckCount.setTotal(jd1_yyyqz_dj + jd1_yyyqz_jj + jd2_yyyqz_dj
					+ jd2_yyyqz_jj + jd3_yyyqz_dj + jd3_yyyqz_jj + jd4_yyyqz_dj
					+ jd4_yyyqz_jj);
			list.add(ckCount);
			DeviceCountDTO zhCount = new DeviceCountDTO();
			zhCount.setDevice_code("3200");
			zhCount.setDevice_name("<b>液压驱动电梯</b>");
			zhCount.setJd1_dj_count(String.valueOf(jd1_yy_dj));
			zhCount.setJd1_jj_count(String.valueOf(jd1_yy_jj));
			zhCount.setJd2_dj_count(String.valueOf(jd2_yy_dj));
			zhCount.setJd2_jj_count(String.valueOf(jd2_yy_jj));
			zhCount.setJd3_dj_count(String.valueOf(jd3_yy_dj));
			zhCount.setJd3_jj_count(String.valueOf(jd3_yy_jj));
			zhCount.setJd4_dj_count(String.valueOf(jd4_yy_dj));
			zhCount.setJd4_jj_count(String.valueOf(jd4_yy_jj));
			zhCount.setTotal(jd1_yy_dj + jd1_yy_jj + jd2_yy_dj + jd2_yy_jj
					+ jd3_yy_dj + jd3_yy_jj + jd4_yy_dj + jd4_yy_jj);
			list.add(zhCount);
			DeviceCountDTO yyCount = new DeviceCountDTO();
			yyCount.setDevice_code("3300");
			yyCount.setDevice_name("<b>自动扶梯与自动人行道</b>");
			yyCount.setJd1_dj_count(String.valueOf(jd1_ftrxd_dj));
			yyCount.setJd1_jj_count(String.valueOf(jd1_ftrxd_jj));
			yyCount.setJd2_dj_count(String.valueOf(jd2_ftrxd_dj));
			yyCount.setJd2_jj_count(String.valueOf(jd2_ftrxd_jj));
			yyCount.setJd3_dj_count(String.valueOf(jd3_ftrxd_dj));
			yyCount.setJd3_jj_count(String.valueOf(jd3_ftrxd_jj));
			yyCount.setJd4_dj_count(String.valueOf(jd4_ftrxd_dj));
			yyCount.setJd4_jj_count(String.valueOf(jd4_ftrxd_jj));
			yyCount.setTotal(jd1_ftrxd_dj + jd1_ftrxd_jj + jd2_ftrxd_dj
					+ jd2_ftrxd_jj + jd3_ftrxd_dj + jd3_ftrxd_jj + jd4_ftrxd_dj
					+ jd4_ftrxd_jj);
			list.add(yyCount);
			DeviceCountDTO ftCount = new DeviceCountDTO();
			ftCount.setDevice_code("3400");
			ftCount.setDevice_name("<b>其它类型电梯</b>");
			ftCount.setJd1_dj_count(String.valueOf(jd1_qt_dj));
			ftCount.setJd1_jj_count(String.valueOf(jd1_qt_jj));
			ftCount.setJd2_dj_count(String.valueOf(jd2_qt_dj));
			ftCount.setJd2_jj_count(String.valueOf(jd2_qt_jj));
			ftCount.setJd3_dj_count(String.valueOf(jd3_qt_dj));
			ftCount.setJd3_jj_count(String.valueOf(jd3_qt_jj));
			ftCount.setJd4_dj_count(String.valueOf(jd4_qt_dj));
			ftCount.setJd4_jj_count(String.valueOf(jd4_qt_jj));
			ftCount.setTotal(jd1_qt_dj + jd1_qt_jj + jd2_qt_dj + jd2_qt_jj
					+ jd3_qt_dj + jd3_qt_jj + jd4_qt_dj + jd4_qt_jj);
			list.add(ftCount);

			Collections.sort(list, new MyComparetor());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 各部门已打印的电梯定检、监检报告统计表
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */

	public List<DeviceCountDTO> exportPrintedCount(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " with info as ( "
					+ " select t.id,d.device_sort_code,t.print_time,t1.check_type,o.id org_id from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ " where t1.id = t.fk_inspection_id and t.fk_tsjc_device_document_id = d.id and t.check_unit_id = o.id and t.print_time is not null and d.device_sort_code(+) like '3%' and t.data_status <> '99'"
					+ " ),codevalue as ( "
					+ " select pv.value,pv.name from pub_code_table p,pub_code_table_values pv "
					+ " where p.id = pv.code_table_id and p.code='device_classify' and pv.CODE_TABLE_VALUES_ID is not null and pv.value like '3%' ) "
					+ " select c.value,c.name, "
					+ " count(case when t.org_id='100020' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd1_jj_count, "
					+ " count(case when t.org_id='100020' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd1_dj_count, "
					+ " count(case when t.org_id='100021' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd2_jj_count, "
					+ " count(case when t.org_id='100021' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd2_dj_count, "
					+ " count(case when t.org_id='100022' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd3_jj_count, "
					+ " count(case when t.org_id='100022' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd3_dj_count, "
					+ " count(case when t.org_id='100023' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd4_jj_count, "
					+ " count(case when t.org_id='100023' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd4_dj_count, "
					+ " count(case when t.org_id='100063' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd5_jj_count, "
					+ " count(case when t.org_id='100063' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd5_dj_count "
					+ " from codevalue c , info t where c.value = t.device_sort_code(+) group by c.value,c.name ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			pstmt.setString(7, startDate);
			pstmt.setString(8, endDate);
			pstmt.setString(9, startDate);
			pstmt.setString(10, endDate);
			pstmt.setString(11, startDate);
			pstmt.setString(12, endDate);
			pstmt.setString(13, startDate);
			pstmt.setString(14, endDate);
			pstmt.setString(15, startDate);
			pstmt.setString(16, endDate);
			pstmt.setString(17, startDate);
			pstmt.setString(18, endDate);
			pstmt.setString(19, startDate);
			pstmt.setString(20, endDate);
			int jd1_yyyqz_dj = 0;
			int jd1_yyyqz_jj = 0;
			int jd2_yyyqz_dj = 0;
			int jd2_yyyqz_jj = 0;
			int jd3_yyyqz_dj = 0;
			int jd3_yyyqz_jj = 0;
			int jd4_yyyqz_dj = 0;
			int jd4_yyyqz_jj = 0;
			int jd5_yyyqz_dj = 0;
			int jd5_yyyqz_jj = 0;
			int jd1_yy_dj = 0;
			int jd1_yy_jj = 0;
			int jd2_yy_dj = 0;
			int jd2_yy_jj = 0;
			int jd3_yy_dj = 0;
			int jd3_yy_jj = 0;
			int jd4_yy_dj = 0;
			int jd4_yy_jj = 0;
			int jd5_yy_dj = 0;
			int jd5_yy_jj = 0;
			int jd1_ftrxd_dj = 0;
			int jd1_ftrxd_jj = 0;
			int jd2_ftrxd_dj = 0;
			int jd2_ftrxd_jj = 0;
			int jd3_ftrxd_dj = 0;
			int jd3_ftrxd_jj = 0;
			int jd4_ftrxd_dj = 0;
			int jd4_ftrxd_jj = 0;
			int jd5_ftrxd_dj = 0;
			int jd5_ftrxd_jj = 0;
			int jd1_qt_dj = 0;
			int jd1_qt_jj = 0;
			int jd2_qt_dj = 0;
			int jd2_qt_jj = 0;
			int jd3_qt_dj = 0;
			int jd3_qt_jj = 0;
			int jd4_qt_dj = 0;
			int jd4_qt_jj = 0;
			int jd5_qt_dj = 0;
			int jd5_qt_jj = 0;
			int jd1_dj_total = 0;
			int jd1_jj_total = 0;
			int jd2_dj_total = 0;
			int jd2_jj_total = 0;
			int jd3_dj_total = 0;
			int jd3_jj_total = 0;
			int jd4_dj_total = 0;
			int jd4_jj_total = 0;
			int jd5_dj_total = 0;
			int jd5_jj_total = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("value").endsWith("00")) {
					continue;
				}
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setDevice_code(rs.getString("value"));
				deviceCount.setDevice_name(rs.getString("name"));
				deviceCount.setJd1_dj_count(rs.getString("jd1_dj_count"));
				deviceCount.setJd1_jj_count(rs.getString("jd1_jj_count"));
				deviceCount.setJd2_dj_count(rs.getString("jd2_dj_count"));
				deviceCount.setJd2_jj_count(rs.getString("jd2_jj_count"));
				deviceCount.setJd3_dj_count(rs.getString("jd3_dj_count"));
				deviceCount.setJd3_jj_count(rs.getString("jd3_jj_count"));
				deviceCount.setJd4_dj_count(rs.getString("jd4_dj_count"));
				deviceCount.setJd4_jj_count(rs.getString("jd4_jj_count"));
				deviceCount.setJd5_dj_count(rs.getString("jd5_dj_count"));
				deviceCount.setJd5_jj_count(rs.getString("jd5_jj_count"));
				deviceCount.setTotal(rs.getInt("jd1_dj_count")
						+ rs.getInt("jd1_jj_count") + rs.getInt("jd2_dj_count")
						+ rs.getInt("jd2_jj_count") + rs.getInt("jd3_dj_count")
						+ rs.getInt("jd3_jj_count") + rs.getInt("jd4_dj_count")
						+ rs.getInt("jd4_jj_count") + rs.getInt("jd5_dj_count")
						+ rs.getInt("jd5_jj_count"));
				if (rs.getString("value").startsWith("31")) {
					jd1_yyyqz_dj += rs.getInt("jd1_dj_count");
					jd1_yyyqz_jj += rs.getInt("jd1_jj_count");
					jd2_yyyqz_dj += rs.getInt("jd2_dj_count");
					jd2_yyyqz_jj += rs.getInt("jd2_jj_count");
					jd3_yyyqz_dj += rs.getInt("jd3_dj_count");
					jd3_yyyqz_jj += rs.getInt("jd3_jj_count");
					jd4_yyyqz_dj += rs.getInt("jd4_dj_count");
					jd4_yyyqz_jj += rs.getInt("jd4_jj_count");
					jd5_yyyqz_dj += rs.getInt("jd5_dj_count");
					jd5_yyyqz_jj += rs.getInt("jd5_jj_count");
				} else if (rs.getString("value").startsWith("32")) {
					jd1_yy_dj += rs.getInt("jd1_dj_count");
					jd1_yy_jj += rs.getInt("jd1_jj_count");
					jd2_yy_dj += rs.getInt("jd2_dj_count");
					jd2_yy_jj += rs.getInt("jd2_jj_count");
					jd3_yy_dj += rs.getInt("jd3_dj_count");
					jd3_yy_jj += rs.getInt("jd3_jj_count");
					jd4_yy_dj += rs.getInt("jd4_dj_count");
					jd4_yy_jj += rs.getInt("jd4_jj_count");
					jd5_yy_dj += rs.getInt("jd5_dj_count");
					jd5_yy_jj += rs.getInt("jd5_jj_count");
				} else if (rs.getString("value").startsWith("33")) {
					jd1_ftrxd_dj += rs.getInt("jd1_dj_count");
					jd1_ftrxd_jj += rs.getInt("jd1_jj_count");
					jd2_ftrxd_dj += rs.getInt("jd2_dj_count");
					jd2_ftrxd_jj += rs.getInt("jd2_jj_count");
					jd3_ftrxd_dj += rs.getInt("jd3_dj_count");
					jd3_ftrxd_jj += rs.getInt("jd3_jj_count");
					jd4_ftrxd_dj += rs.getInt("jd4_dj_count");
					jd4_ftrxd_jj += rs.getInt("jd4_jj_count");
					jd5_ftrxd_dj += rs.getInt("jd5_dj_count");
					jd5_ftrxd_jj += rs.getInt("jd5_jj_count");
				} else if (rs.getString("value").startsWith("34")) {
					jd1_qt_dj += rs.getInt("jd1_dj_count");
					jd1_qt_jj += rs.getInt("jd1_jj_count");
					jd2_qt_dj += rs.getInt("jd2_dj_count");
					jd2_qt_jj += rs.getInt("jd2_jj_count");
					jd3_qt_dj += rs.getInt("jd3_dj_count");
					jd3_qt_jj += rs.getInt("jd3_jj_count");
					jd4_qt_dj += rs.getInt("jd4_dj_count");
					jd4_qt_jj += rs.getInt("jd4_jj_count");
					jd5_qt_dj += rs.getInt("jd5_dj_count");
					jd5_qt_jj += rs.getInt("jd5_jj_count");
				}
				list.add(deviceCount);

			}
			jd1_dj_total += jd1_yyyqz_dj + jd1_yy_dj + jd1_ftrxd_dj + jd1_qt_dj;
			jd1_jj_total += jd1_yyyqz_jj + jd1_yy_jj + jd1_ftrxd_jj + jd1_qt_jj;
			jd2_dj_total += jd2_yyyqz_dj + jd2_yy_dj + jd2_ftrxd_dj + jd2_qt_dj;
			jd2_jj_total += jd2_yyyqz_jj + jd2_yy_jj + jd2_ftrxd_jj + jd2_qt_jj;
			jd3_dj_total += jd3_yyyqz_dj + jd3_yy_dj + jd3_ftrxd_dj + jd3_qt_dj;
			jd3_jj_total += jd3_yyyqz_jj + jd3_yy_jj + jd3_ftrxd_jj + jd3_qt_jj;
			jd4_dj_total += jd4_yyyqz_dj + jd4_yy_dj + jd4_ftrxd_dj + jd4_qt_dj;
			jd4_jj_total += jd4_yyyqz_jj + jd4_yy_jj + jd4_ftrxd_jj + jd4_qt_jj;
			jd5_dj_total += jd5_yyyqz_dj + jd5_yy_dj + jd5_ftrxd_dj + jd5_qt_dj;
			jd5_jj_total += jd5_yyyqz_jj + jd5_yy_jj + jd5_ftrxd_jj + jd5_qt_jj;
			DeviceCountDTO ckCount = new DeviceCountDTO();
			ckCount.setDevice_code("3100");
			ckCount.setDevice_name("<b>曳引与强制驱动电梯</b>");
			ckCount.setJd1_dj_count("<b>" + jd1_yyyqz_dj + "</b>");
			ckCount.setJd1_jj_count("<b>" + jd1_yyyqz_jj + "</b>");
			ckCount.setJd2_dj_count("<b>" + jd2_yyyqz_dj + "</b>");
			ckCount.setJd2_jj_count("<b>" + jd2_yyyqz_jj + "</b>");
			ckCount.setJd3_dj_count("<b>" + jd3_yyyqz_dj + "</b>");
			ckCount.setJd3_jj_count("<b>" + jd3_yyyqz_jj + "</b>");
			ckCount.setJd4_dj_count("<b>" + jd4_yyyqz_dj + "</b>");
			ckCount.setJd4_jj_count("<b>" + jd4_yyyqz_jj + "</b>");
			ckCount.setJd5_dj_count("<b>" + jd5_yyyqz_dj + "</b>");
			ckCount.setJd5_jj_count("<b>" + jd5_yyyqz_jj + "</b>");
			ckCount.setTotal(jd1_yyyqz_dj + jd1_yyyqz_jj + jd2_yyyqz_dj
					+ jd2_yyyqz_jj + jd3_yyyqz_dj + jd3_yyyqz_jj + jd4_yyyqz_dj
					+ jd4_yyyqz_jj + jd5_yyyqz_dj + jd5_yyyqz_jj);
			list.add(ckCount);
			DeviceCountDTO zhCount = new DeviceCountDTO();
			zhCount.setDevice_code("3200");
			zhCount.setDevice_name("<b>液压驱动电梯</b>");
			zhCount.setJd1_dj_count("<b>" + jd1_yy_dj + "</b>");
			zhCount.setJd1_jj_count("<b>" + jd1_yy_jj + "</b>");
			zhCount.setJd2_dj_count("<b>" + jd2_yy_dj + "</b>");
			zhCount.setJd2_jj_count("<b>" + jd2_yy_jj + "</b>");
			zhCount.setJd3_dj_count("<b>" + jd3_yy_dj + "</b>");
			zhCount.setJd3_jj_count("<b>" + jd3_yy_jj + "</b>");
			zhCount.setJd4_dj_count("<b>" + jd4_yy_dj + "</b>");
			zhCount.setJd4_jj_count("<b>" + jd4_yy_jj + "</b>");
			zhCount.setJd5_dj_count("<b>" + jd5_yy_dj + "</b>");
			zhCount.setJd5_jj_count("<b>" + jd5_yy_jj + "</b>");
			zhCount.setTotal(jd1_yy_dj + jd1_yy_jj + jd2_yy_dj + jd2_yy_jj
					+ jd3_yy_dj + jd3_yy_jj + jd4_yy_dj + jd4_yy_jj + jd5_yy_dj + jd5_yy_jj);
			list.add(zhCount);
			DeviceCountDTO yyCount = new DeviceCountDTO();
			yyCount.setDevice_code("3300");
			yyCount.setDevice_name("<b>自动扶梯与自动人行道</b>");
			yyCount.setJd1_dj_count("<b>" + jd1_ftrxd_dj + "</b>");
			yyCount.setJd1_jj_count("<b>" + jd1_ftrxd_jj + "</b>");
			yyCount.setJd2_dj_count("<b>" + jd2_ftrxd_dj + "</b>");
			yyCount.setJd2_jj_count("<b>" + jd2_ftrxd_jj + "</b>");
			yyCount.setJd3_dj_count("<b>" + jd3_ftrxd_dj + "</b>");
			yyCount.setJd3_jj_count("<b>" + jd3_ftrxd_jj + "</b>");
			yyCount.setJd4_dj_count("<b>" + jd4_ftrxd_dj + "</b>");
			yyCount.setJd4_jj_count("<b>" + jd4_ftrxd_jj + "</b>");
			yyCount.setJd5_dj_count("<b>" + jd5_ftrxd_dj + "</b>");
			yyCount.setJd5_jj_count("<b>" + jd5_ftrxd_jj + "</b>");
			yyCount.setTotal(jd1_ftrxd_dj + jd1_ftrxd_jj + jd2_ftrxd_dj
					+ jd2_ftrxd_jj + jd3_ftrxd_dj + jd3_ftrxd_jj + jd4_ftrxd_dj
					+ jd4_ftrxd_jj + jd5_ftrxd_dj + jd5_ftrxd_jj);
			list.add(yyCount);
			DeviceCountDTO ftCount = new DeviceCountDTO();
			ftCount.setDevice_code("3400");
			ftCount.setDevice_name("<b>其它类型电梯</b>");
			ftCount.setJd1_dj_count("<b>" + jd1_qt_dj + "</b>");
			ftCount.setJd1_jj_count("<b>" + jd1_qt_jj + "</b>");
			ftCount.setJd2_dj_count("<b>" + jd2_qt_dj + "</b>");
			ftCount.setJd2_jj_count("<b>" + jd2_qt_jj + "</b>");
			ftCount.setJd3_dj_count("<b>" + jd3_qt_dj + "</b>");
			ftCount.setJd3_jj_count("<b>" + jd3_qt_jj + "</b>");
			ftCount.setJd4_dj_count("<b>" + jd4_qt_dj + "</b>");
			ftCount.setJd4_jj_count("<b>" + jd4_qt_jj + "</b>");
			ftCount.setJd5_dj_count("<b>" + jd5_qt_dj + "</b>");
			ftCount.setJd5_jj_count("<b>" + jd5_qt_jj + "</b>");
			ftCount.setTotal(jd1_qt_dj + jd1_qt_jj + jd2_qt_dj + jd2_qt_jj
					+ jd3_qt_dj + jd3_qt_jj + jd4_qt_dj + jd4_qt_jj + jd5_qt_dj + jd5_qt_jj);
			list.add(ftCount);
			DeviceCountDTO totalCount = new DeviceCountDTO();
			totalCount.setDevice_code("99999"); // 用于合计行标记
			totalCount.setDevice_name("<b>合计</b>");
			totalCount.setJd1_dj_count("<b>" + jd1_dj_total + "</b>");
			totalCount.setJd1_jj_count("<b>" + jd1_jj_total + "</b>");
			totalCount.setJd2_dj_count("<b>" + jd2_dj_total + "</b>");
			totalCount.setJd2_jj_count("<b>" + jd2_jj_total + "</b>");
			totalCount.setJd3_dj_count("<b>" + jd3_dj_total + "</b>");
			totalCount.setJd3_jj_count("<b>" + jd3_jj_total + "</b>");
			totalCount.setJd4_dj_count("<b>" + jd4_dj_total + "</b>");
			totalCount.setJd4_jj_count("<b>" + jd4_jj_total + "</b>");
			totalCount.setJd5_dj_count("<b>" + jd5_dj_total + "</b>");
			totalCount.setJd5_jj_count("<b>" + jd5_jj_total + "</b>");
			totalCount.setTotal(jd1_dj_total + jd1_jj_total + jd2_dj_total
					+ jd2_jj_total + jd3_dj_total + jd3_jj_total + jd4_dj_total
					+ jd4_jj_total + jd5_dj_total + jd5_jj_total);
			list.add(totalCount);

			Collections.sort(list, new MyComparetor());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 业务服务部综合统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-12-11 上午10:48:00
	 */

	public List<DeviceCountDTO> all_count(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			// 1、检验报告打印统计
			String sql = " select count(case when o.org_code like 'jd%' and t.flow_note_name in ('报告领取','报告归档') then 1 else null end) as jd_dev_p_count, ";
			sql += " sum(case when o.org_code like 'jd%' and t.flow_note_name in ('报告领取','报告归档') then r.printcopies else null end) as jd_rep_p_count, ";
			sql += " count(case when o.org_code like 'cy%' and t.flow_note_name in ('报告领取','报告归档') then 1 else null end) as cy_dev_p_count, ";
			sql += " sum(case when o.org_code like 'cy%' and t.flow_note_name in ('报告领取','报告归档') then r.printcopies else null end) as cy_rep_p_count ";
			sql += " from tzsb_inspection_info t, sys_org o, base_reports r ";
			sql += " where t.check_unit_id = o.id and t.data_status!='99' and t.report_type=r.id";
			sql += " and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			int jd_dev_p_count = 0;
			int jd_rep_p_count = 0;
			int cy_dev_p_count = 0;
			int cy_rep_p_count = 0;
			rs = pstmt.executeQuery();
			
			DeviceCountDTO jd_count = new DeviceCountDTO();
			DeviceCountDTO cy_count = new DeviceCountDTO();
			while (rs.next()) {
				jd_dev_p_count = rs.getInt("jd_dev_p_count");
				jd_rep_p_count = rs.getInt("jd_rep_p_count");
				cy_dev_p_count = rs.getInt("cy_dev_p_count");
				cy_rep_p_count = rs.getInt("cy_rep_p_count");
				
				jd_count.setCate_sort("01");
				jd_count.setCategory("机电打印");
				jd_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("jd_dev_p_count"))?rs.getString("jd_dev_p_count"):"0");
				jd_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("jd_rep_p_count"))?rs.getString("jd_rep_p_count"):"0");
				// jd_count.setDev_lq_count(rs.getString("jd_dev_lq_count"));
				// jd_count.setRep_lq_count(rs.getString("jd_rep_lq_count"));
				//list.add(jd_count);

				cy_count.setCate_sort("02");
				cy_count.setCategory("承压打印");
				cy_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("cy_dev_p_count"))?rs.getString("cy_dev_p_count"):"0");
				cy_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("cy_rep_p_count"))?rs.getString("cy_rep_p_count"):"0");
				// cy_count.setDev_lq_count(rs.getString("cy_dev_lq_count"));
				// cy_count.setRep_lq_count(rs.getString("cy_rep_lq_count"));
				//list.add(cy_count);
			}

			// 2、检验报告发放（领取）统计
			String sql1 = " select count(case when o.org_code like 'jd%' and t.flow_note_name ='报告归档' then t.id else null end) as jd_dev_lq_count, ";
			sql1 += " sum(case when o.org_code like 'jd%' and t.flow_note_name ='报告归档' then (r.printcopies-1) else null end) as jd_rep_lq_count, ";
			sql1 += " count(case when o.org_code like 'cy%' and t.flow_note_name in ('报告领取','报告归档') then t.id else null end) as cy_dev_lq_count, ";
			sql1 += " sum(case when o.org_code like 'cy%' and t.flow_note_name in ('报告领取','报告归档') then (r.printcopies-1) else null end) as cy_rep_lq_count ";
			sql1 += " from tzsb_inspection_info t, sys_org o, base_reports r, sys_log l ";
			sql1 += " where t.check_unit_id = o.id and t.data_status!='99' and t.report_type=r.id and t.id = l.BUSINESS_ID and l.op_action='报告领取'";
			sql1 += " and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			int jd_dev_lq_count = 0;
			int jd_rep_lq_count = 0;
			int cy_dev_lq_count = 0;
			int cy_rep_lq_count = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				jd_dev_lq_count = rs.getInt("jd_dev_lq_count");
				jd_rep_lq_count = rs.getInt("jd_rep_lq_count");
				cy_dev_lq_count = rs.getInt("cy_dev_lq_count");
				cy_rep_lq_count = rs.getInt("cy_rep_lq_count");
				jd_count.setDev_lq_count(StringUtil.isNotEmpty(rs.getString("jd_dev_lq_count"))?rs.getString("jd_dev_lq_count"):"0");
				jd_count.setRep_lq_count(StringUtil.isNotEmpty(rs.getString("jd_rep_lq_count"))?rs.getString("jd_rep_lq_count"):"0");
				list.add(jd_count);

				cy_count.setDev_lq_count(StringUtil.isNotEmpty(rs.getString("cy_dev_lq_count"))?rs.getString("cy_dev_lq_count"):"0");
				cy_count.setRep_lq_count(StringUtil.isNotEmpty(rs.getString("cy_rep_lq_count"))?rs.getString("cy_rep_lq_count"):"0");
				list.add(cy_count);
			}

			// 3、补打统计
			String sql2 = " select count(case when o.org_code like 'jd%' and l.print_type_name ='补办打印' then 1 else null end) as jd_bb_dev_p_count, ";
			sql2 += " sum(case when o.org_code like 'jd%' and l.print_type_name ='补办打印' then l.print_count else null end) as jd_bb_rep_p_count,";
			sql2 += " count(case when o.org_code like 'cy%' and l.print_type_name ='补办打印' then 1 else null end) as cy_bb_dev_p_count, ";
			sql2 += " sum(case when o.org_code like 'cy%' and l.print_type_name ='补办打印' then l.print_count else null end) as cy_bb_rep_p_count,";
			sql2 += " count(case when l.print_type_name ='换证打印' then 1 else null end) as hz_dev_p_count, ";
			sql2 += " sum(case when l.print_type_name ='换证打印' then l.print_count else null end) as hz_rep_p_count, ";
			sql2 += " count(case when l.print_type_name ='其他打印' then 1 else null end) as qt_dev_p_count, ";
			sql2 += " sum(case when l.print_type_name ='其他打印' then l.print_count else null end) as qt_rep_p_count ";
			sql2 += " from tzsb_report_print_log l, tzsb_inspection_info t, sys_org o ";
			sql2 += " where l.info_id = t.id and t.check_unit_id = l.org_id and t.check_unit_id = o.id ";
			sql2 += " and to_char(l.print_time,'yyyy-MM-dd') >= ? and to_char(l.print_time,'yyyy-MM-dd') <= ?";
			pstmt.clearParameters();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			int jd_bb_dev_p_count = 0;
			int jd_bb_rep_p_count = 0;
			int cy_bb_dev_p_count = 0;
			int cy_bb_rep_p_count = 0;
			int hz_dev_p_count = 0;
			int hz_rep_p_count = 0;
			int qt_dev_p_count = 0;
			int qt_rep_p_count = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				jd_bb_dev_p_count = rs.getInt("jd_bb_dev_p_count");
				jd_bb_rep_p_count = rs.getInt("jd_bb_rep_p_count");
				cy_bb_dev_p_count = rs.getInt("cy_bb_dev_p_count");
				cy_bb_rep_p_count = rs.getInt("cy_bb_rep_p_count");
				hz_dev_p_count = rs.getInt("hz_dev_p_count");
				hz_rep_p_count = rs.getInt("hz_rep_p_count");
				qt_dev_p_count = rs.getInt("qt_dev_p_count");
				qt_rep_p_count = rs.getInt("qt_rep_p_count");

				DeviceCountDTO jd_bb_count = new DeviceCountDTO();
				jd_bb_count.setCate_sort("03");
				jd_bb_count.setCategory("机电补办");
				jd_bb_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("jd_bb_dev_p_count"))?rs.getString("jd_bb_dev_p_count"):"0");
				jd_bb_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("jd_bb_rep_p_count"))?rs.getString("jd_bb_rep_p_count"):"0");
				jd_bb_count.setDev_lq_count("/");
				jd_bb_count.setRep_lq_count("/");
				list.add(jd_bb_count);

				DeviceCountDTO cy_bb_count = new DeviceCountDTO();
				cy_bb_count.setCate_sort("04");
				cy_bb_count.setCategory("承压补办");
				cy_bb_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("cy_bb_dev_p_count"))?rs.getString("cy_bb_dev_p_count"):"0");
				cy_bb_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("cy_bb_rep_p_count"))?rs.getString("cy_bb_rep_p_count"):"0");
				cy_bb_count.setDev_lq_count("/");
				cy_bb_count.setRep_lq_count("/");
				list.add(cy_bb_count);

				DeviceCountDTO hz_count = new DeviceCountDTO();
				hz_count.setCate_sort("05");
				hz_count.setCategory("换证打印");
				hz_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("hz_dev_p_count"))?rs.getString("hz_dev_p_count"):"0");
				hz_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("hz_rep_p_count"))?rs.getString("hz_rep_p_count"):"0");
				hz_count.setDev_lq_count("/");
				hz_count.setRep_lq_count("/");
				list.add(hz_count);

				DeviceCountDTO qt_count = new DeviceCountDTO();
				qt_count.setCate_sort("06");
				qt_count.setCategory("其他打印");
				qt_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("qt_dev_p_count"))?rs.getString("qt_dev_p_count"):"0");
				qt_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("qt_rep_p_count"))?rs.getString("qt_rep_p_count"):"0");
				qt_count.setDev_lq_count("/");
				qt_count.setRep_lq_count("/");
				list.add(qt_count);
			}

			// 4、无原始资料打印统计
			String sql3 = " select count(t2.id) as wyszl_dev_p_count, ";
			sql3 += " sum(r.printcopies) as wyszl_rep_p_count ";
			sql3 += " from TJY2_QUALITY_ZSSQ t, TJY2_QUALITY_ZSSQ_SUB t2, tzsb_inspection_info info, base_reports r";
			sql3 += " where t2.quality_zssq_fk = t.id and t2.report_fk = info.id and info.report_type = r.id and t.status = 'PASS'";
			sql3 += " and to_char(t.RECIPIENT_OPINION_TIME, 'yyyy-MM-dd') >= ? and to_char(t.RECIPIENT_OPINION_TIME, 'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			int wyszl_dev_p_count = 0;
			int wyszl_rep_p_count = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				wyszl_dev_p_count = rs.getInt("wyszl_dev_p_count");
				wyszl_rep_p_count = rs.getInt("wyszl_rep_p_count");
				DeviceCountDTO wyszl_count = new DeviceCountDTO();
				wyszl_count.setCate_sort("07");
				wyszl_count.setCategory("无原始资料打印报告");
				wyszl_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("wyszl_dev_p_count"))?rs.getString("wyszl_dev_p_count"):"0");
				wyszl_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("wyszl_rep_p_count"))?rs.getString("wyszl_rep_p_count"):"0");
				wyszl_count.setDev_lq_count("/");
				wyszl_count.setRep_lq_count("/");
				list.add(wyszl_count);
			}

			// 5、计算合计
			DeviceCountDTO totalCount = new DeviceCountDTO();
			totalCount.setCate_sort("09"); // 用于合计行标记
			totalCount.setCategory("<b>打印合计</b>");
			totalCount.setDev_p_count("<b>" + (jd_dev_p_count + cy_dev_p_count + jd_bb_dev_p_count + cy_bb_dev_p_count
					+ hz_dev_p_count + qt_dev_p_count + wyszl_dev_p_count) + "</b>");
			totalCount.setRep_p_count("<b>" + (jd_rep_p_count + cy_rep_p_count + jd_bb_rep_p_count + cy_bb_rep_p_count
					+ hz_rep_p_count + qt_rep_p_count + wyszl_rep_p_count) + "</b>");
			totalCount.setDev_lq_count("<b>" + (jd_dev_lq_count + cy_dev_lq_count) + "</b>");
			totalCount.setRep_lq_count("<b>" + (jd_rep_lq_count + cy_rep_lq_count) + "</b>");
			list.add(totalCount);

			// 6、罐车中心盖章统计
			String sql4 = " select count(t.id) as gc_dev_p_count ";
			sql4 += " from tzsb_inspection_info t ";
			sql4 += " where t.CHECK_UNIT_ID = '100036' and t.data_status!='99' and t.EXPORT_PDF is not null ";
			sql4 += " and to_char(t.PRINT_TIME, 'yyyy-MM-dd') >= ? and to_char(t.PRINT_TIME, 'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql4);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO gc_count = new DeviceCountDTO();
				gc_count.setCate_sort("10");
				gc_count.setCategory("罐车中心盖章");
				gc_count.setDev_p_count(StringUtil.isNotEmpty(rs.getString("gc_dev_p_count"))?rs.getString("gc_dev_p_count"):"0");
				gc_count.setRep_p_count("/");
				gc_count.setDev_lq_count("/");
				gc_count.setRep_lq_count("/");
				list.add(gc_count);
			}

			// 7、手工报告编号统计
			String sql5 = " select sum(t.NJTS) as sg_rep_count1, sum(t.NJTS2) as sg_rep_count2, sum(t.NJTS3) as sg_rep_count3,";
			sql5 += " sum(t.NJTS4) as sg_rep_count4, sum(t.NJTS5) as sg_rep_count5, sum(t.NJTS6) as sg_rep_count6, sum(t.NJTS7) as sg_rep_count7 ";
			sql5 += " from TJY2_QUALITY_SGCJJYBG t ";
			sql5 += " where t.status = 'PASS' ";
			sql5 += " and to_char(t.YWFWBJBR_DATE, 'yyyy-MM-dd') >= ? and to_char(t.YWFWBJBR_DATE, 'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql5);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			int sg_rep_count1 = 0;
			int sg_rep_count2 = 0;
			int sg_rep_count3 = 0;
			int sg_rep_count4 = 0;
			int sg_rep_count5 = 0;
			int sg_rep_count6 = 0;
			int sg_rep_count7 = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sg_rep_count1 = rs.getInt("sg_rep_count1");
				sg_rep_count2 = rs.getInt("sg_rep_count2");
				sg_rep_count3 = rs.getInt("sg_rep_count3");
				sg_rep_count4 = rs.getInt("sg_rep_count4");
				sg_rep_count5 = rs.getInt("sg_rep_count5");
				sg_rep_count6 = rs.getInt("sg_rep_count6");
				sg_rep_count7 = rs.getInt("sg_rep_count7");
				DeviceCountDTO sg_count = new DeviceCountDTO();
				sg_count.setCate_sort("11");
				sg_count.setCategory("手工报告编号");
				sg_count.setDev_p_count(String.valueOf(sg_rep_count1 + sg_rep_count2 + sg_rep_count3 + sg_rep_count4
						+ sg_rep_count5 + sg_rep_count6 + sg_rep_count7));
				sg_count.setRep_p_count("/");
				sg_count.setDev_lq_count("/");
				sg_count.setRep_lq_count("/");
				list.add(sg_count);
			}

			// 8、报告借阅统计
			String sql6 = " select count(case when o.org_code like 'jd%' then 1 else null end) as jd_jy_rep_count, ";
			sql6 += " count(case when o.org_code like 'cy%' then 1 else null end) as cy_jy_rep_count ";
			sql6 += " from TJY2_ARCHIVES_BORROW t, TJY2_ARCHIVES_BORROW_SUB t2, tzsb_inspection_info info, sys_org o";
			sql6 += " where t2.fk_archives_borrow_id = t.id and t2.FK_REPORT_ID = info.id and info.CHECK_UNIT_ID = o.id and t.status in ('GH','BFGH','LQ')";
			sql6 += " and to_char(t.JFBGSJ, 'yyyy-MM-dd') >= ? and to_char(t.JFBGSJ, 'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql6);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO jy_count = new DeviceCountDTO();
				jy_count.setCate_sort("12");
				jy_count.setCategory("报告借阅");
				jy_count.setDev_p_count("机电：" + (StringUtil.isNotEmpty(rs.getString("jd_jy_rep_count"))?rs.getString("jd_jy_rep_count"):"0") + "台");
				jy_count.setRep_p_count("承压：" + (StringUtil.isNotEmpty(rs.getString("cy_jy_rep_count"))?rs.getString("cy_jy_rep_count"):"0") + "台");
				jy_count.setDev_lq_count("/");
				jy_count.setRep_lq_count("/");
				list.add(jy_count);
			}

			// 9、承压类非检验专用用章统计
			String sql7 = " select sum(t.SEAL_SCORE) as fyzgzsq_rep_count";
			sql7 += " from TJY2_QUALITY_TXWJSP_SEALREG t ";
			sql7 += " where t.status = '6' ";
			sql7 += " and to_char(t.SEAL_DATE, 'yyyy-MM-dd') >= ? and to_char(t.SEAL_DATE, 'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql7);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO fyzgzsq_count = new DeviceCountDTO();
				fyzgzsq_count.setCate_sort("13");
				fyzgzsq_count.setCategory("承压类非检验专用用章");
				fyzgzsq_count.setDev_p_count("/");
				fyzgzsq_count.setRep_p_count(StringUtil.isNotEmpty(rs.getString("fyzgzsq_rep_count"))?rs.getString("fyzgzsq_rep_count"):"0");
				fyzgzsq_count.setDev_lq_count("/");
				fyzgzsq_count.setRep_lq_count("/");
				list.add(fyzgzsq_count);
			}

			// 10、纠错报告统计
			String sql8 = " select count(case when o.org_code like 'jd%' and t.DATA_STATUS in ('01','02','03') then t2.id else null end) as jd_jc_zs_count, ";
			sql8 += " count(case when o.org_code like 'jd%' and t.DATA_STATUS in ('02','03') then t2.id else null end) as jd_jc_qr_count, ";
			sql8 += " count(case when o.org_code like 'cy%' and t.DATA_STATUS in ('01','02','03') then t2.id else null end) as cy_jc_zs_count, ";
			sql8 += " count(case when o.org_code like 'cy%' and t.DATA_STATUS in ('02','03') then t2.id else null end) as cy_jc_qr_count ";
			sql8 += " from TZSB_REPORT_ERROR_RECORD t,TZSB_REPORT_ERROR_info t2, tzsb_inspection_info info, sys_org o";
			sql8 += " where t.id = t2.FK_REPORT_ERROR_RECORD_ID and t2.INFO_ID = info.id and info.CHECK_UNIT_ID = o.id ";
			sql8 += " and t.DATA_STATUS != '99' and t.DATA_STATUS != '04' and t2.DATA_STATUS != '99'";
			sql8 += " and to_char(t.FIND_DATE, 'yyyy-MM-dd') >= ? and to_char(t.FIND_DATE, 'yyyy-MM-dd') <= ?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql8);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO jc_count = new DeviceCountDTO();
				jc_count.setCate_sort("14");
				jc_count.setCategory("纠错报告");
				jc_count.setDev_p_count(
						"机电：" + (StringUtil.isNotEmpty(rs.getString("jd_jc_zs_count"))?rs.getString("jd_jc_zs_count"):"0") + "台，其中已确认：" + (StringUtil.isNotEmpty(rs.getString("jd_jc_qr_count"))?rs.getString("jd_jc_qr_count"):"0") + "台");
				jc_count.setRep_p_count(
						"承压：" + (StringUtil.isNotEmpty(rs.getString("cy_jc_zs_count"))?rs.getString("cy_jc_zs_count"):"0") + "台，其中已确认：" + (StringUtil.isNotEmpty(rs.getString("cy_jc_qr_count"))?rs.getString("cy_jc_qr_count"):"0") + "台");
				jc_count.setDev_lq_count("/");
				jc_count.setRep_lq_count("/");
				list.add(jc_count);
			}

			// 11、补办合格证统计（因系统暂无补办合格证登记功能，暂时无法统计该项数据，此处只做空白显示）
			DeviceCountDTO bbhgz_count = new DeviceCountDTO();
			bbhgz_count.setCate_sort("15");
			bbhgz_count.setCategory("补办合格证");
			bbhgz_count.setDev_p_count("/");
			bbhgz_count.setRep_p_count("/");
			bbhgz_count.setDev_lq_count("/");
			bbhgz_count.setRep_lq_count("/");
			list.add(bbhgz_count);

			Collections.sort(list, new MyComparetor());
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
			/*
			 * if (null != rs) rs.close(); if (null != pstmt) pstmt.close(); if
			 * (null != conn) conn.close();
			 */
			Factory.getDB().freeConnetion(conn); // 释放连接
		} catch (Exception e) {
			logger.error("关闭数据库连接错误：" + e.getMessage());
			e.printStackTrace();
		}
	}

	public class MyComparetor implements Comparator {
		// 按设备代码排序
		public int compare(Object o1, Object o2) {
			DeviceCountDTO p1 = (DeviceCountDTO) o1;
			DeviceCountDTO p2 = (DeviceCountDTO) o2;
			return (p1.getCate_sort().compareTo(p2.getCate_sort()));
		}
	}
	
	/**
	 *  查询个人的检验相关数据
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param org_id
	 *            检验部门ID
	 * 
	 * @return
	 * @author pingZhou
	 * @date 2017-01-10 下午14:00:00
	 */
	public List<DeviceCountDTO> inspectCountByUser(String startDate,
			String endDate, String user_id) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = "with loginfo as (select t1.report_sn,t.business_id,t.op_user_id,t.op_user_name,t.op_action, max(t.op_time) op_time "
					+ " from sys_log t, tzsb_inspection_info t1 where t.business_id = t1.id and t1.data_status <> '99' "
					+ " and to_char(t.op_time, 'yyyy-MM-dd') >= ? and to_char(t.op_time, 'yyyy-MM-dd') <= ?  "
					+ " group by t1.report_sn,t.business_id,t.op_user_id,t.op_user_name,t.op_action),"
					+ " userinfo as ( select o.id org_id, o.org_name org_name, u.id user_id, u.name user_name,u.sort user_sort from sys_user u, sys_org o "
					+ " where u.org_id = o.id  ";
					if (StringUtil.isNotEmpty(user_id) && !"all".equals(user_id)) {
						sql += " and u.id = ? ";
					}
					sql += " ) select b.org_id,b.org_name,b.user_name,b.user_sort, count(case when a.op_action = '报告录入' then 1 else null end) as lr_count,"
					+ " count(case when a.op_action = '报告审核' then 1 else null end) as sh_count, "
					+ " count(case when a.op_action = '报告签发' then 1 else null end) as qf_count,"
					+ " count(case when a.op_action = '打印报告' then 1 else null end) as dy_count, "
					+ " count(case when a.op_action = '报告领取' then 1 else null end) as lq_count,"
					+ " count(case when a.op_action = '报告归档' then 1 else null end) as gd_count,"
					+ " count(case when a.op_action = '打印合格证' then 1 else null end) as dyhgz_count"
					+ " from loginfo a, userinfo b where b.user_id = a.op_user_id(+)  group by b.org_id, b.org_name,b.user_name,b.user_sort order by b.org_id,b.user_sort ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			if (StringUtil.isNotEmpty(user_id) && !"all".equals(user_id)) {
				pstmt.setString(3, user_id);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setOrg_name(rs.getString("org_name"));
				deviceCount.setUser_name(rs.getString("user_name"));
				deviceCount.setLr_count(rs.getInt("lr_count"));
				deviceCount.setSh_count(rs.getInt("sh_count"));
				deviceCount.setQf_count(rs.getInt("qf_count"));
				deviceCount.setDy_count(rs.getInt("dy_count"));
				deviceCount.setLq_count(rs.getInt("lq_count"));
				deviceCount.setGd_count(rs.getInt("gd_count"));
				deviceCount.setDyhgz_count(rs.getInt("dyhgz_count"));
				list.add(deviceCount);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 *  查询个人的检验相关数据 2016
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param org_id
	 *            检验部门ID
	 * 
	 * @return
	 * @author pingZhou
	 * @date 2017-01-10 下午14:00:00
	 */
	public List<DeviceCountDTO> inspectCountByUserS( String user_id) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " select T.op_user_id,T.lr_count,t.sh_count,t.qf_count,t.dy_count,t.lq_count,t.gd_count,t.dyhgz_count from REPORT_LOG_STA t WHERE T.OP_USER_ID=?";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			if (StringUtil.isNotEmpty(user_id) && !"all".equals(user_id)) {
				pstmt.setString(1, user_id);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setLr_count(rs.getInt("lr_count"));
				deviceCount.setSh_count(rs.getInt("sh_count"));
				deviceCount.setQf_count(rs.getInt("qf_count"));
				deviceCount.setDy_count(rs.getInt("dy_count"));
				deviceCount.setLq_count(rs.getInt("lq_count"));
				deviceCount.setGd_count(rs.getInt("gd_count"));
				deviceCount.setDyhgz_count(rs.getInt("dyhgz_count"));
				list.add(deviceCount);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 机电六部起重机械定检、监检、委检统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */

	public List<DeviceCountDTO> exportPrintedCount_JD6qz(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " with info as ( "
					+ " select t.id,d.device_sort_code,t.print_time,t1.check_type,o.id org_id from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ " where t1.id = t.fk_inspection_id and t.fk_tsjc_device_document_id = d.id and t.check_unit_id = o.id and t.print_time is not null and d.device_sort_code(+) like '4%' and t.data_status <> '99'"
					+ " ),codevalue as ( "
					+ " select pv.value,pv.name from pub_code_table p,pub_code_table_values pv "
					+ " where p.id = pv.code_table_id and p.code='device_classify' and pv.CODE_TABLE_VALUES_ID is not null and pv.value like '4%' ) "
					+ " select c.value,c.name, "
					+ " count(case when t.org_id='100024' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_dj_count, "
					+ " count(case when t.org_id='100024' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_jj_count, "
					+ " count(case when t.org_id='100024' and t.check_type = '4' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_wj_count "
					+ " from codevalue c , info t where c.value = t.device_sort_code(+) group by c.value,c.name ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			pstmt.setString(5, startDate);
			pstmt.setString(6, endDate);
			//桥式起重机
			int jd6_qsqzj_dj = 0;
			int jd6_qsqzj_jj = 0;
			int jd6_qsqzj_wj = 0;
			//门式起重机
			int jd6_msqzj_dj = 0;
			int jd6_msqzj_jj = 0;
			int jd6_msqzj_wj = 0;
			//塔式起重机
			int jd6_tsqzj_dj = 0;
			int jd6_tsqzj_jj = 0;
			int jd6_tsqzj_wj = 0;
			//流动式起重机
			int jd6_ldsqzj_dj = 0;
			int jd6_ldsqzj_jj = 0;
			int jd6_ldsqzj_wj = 0;
			//门座式起重机
			int jd6_mzsqzj_dj = 0;
			int jd6_mzsqzj_jj = 0;
			int jd6_mzsqzj_wj = 0;
			//升降机
			int jd6_sjj_dj = 0;
			int jd6_sjj_jj = 0;
			int jd6_sjj_wj = 0;
			//缆索式起重机
			int jd6_lssqzj_dj = 0;
			int jd6_lssqzj_jj = 0;
			int jd6_lssqzj_wj = 0;
			//桅杆式起重机
			int jd6_wgsqzj_dj = 0;
			int jd6_wgsqzj_jj = 0;
			int jd6_wgsqzj_wj = 0;
			//旋臂式起重机
			int jd6_xbsqzj_dj = 0;
			int jd6_xbsqzj_jj = 0;
			int jd6_xbsqzj_wj = 0;
			//轻小型式起重机
			int jd6_qxxsqzj_dj = 0;
			int jd6_qxxsqzj_jj = 0;
			int jd6_qxxsqzj_wj = 0;
			//机械式停车设备
			int jd6_jxstcsb_dj = 0;
			int jd6_jxstcsb_jj = 0;
			int jd6_jxstcsb_wj = 0;
			
			int jd6_dj_total = 0;
			int jd6_jj_total = 0;
			int jd6_wj_total = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("value").endsWith("00")) {
					continue;
				}
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setDevice_code(rs.getString("value"));
				deviceCount.setDevice_name(rs.getString("name"));
				deviceCount.setJd6_dj_count(rs.getString("jd6_dj_count"));
				deviceCount.setJd6_jj_count(rs.getString("jd6_jj_count"));
				deviceCount.setJd6_wj_count(rs.getString("jd6_wj_count"));
				deviceCount.setTotal(rs.getInt("jd6_dj_count")
						+ rs.getInt("jd6_jj_count") + rs.getInt("jd6_wj_count"));
				if (rs.getString("value").startsWith("41")) {
					jd6_qsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_qsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_qsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("42")) {
					jd6_msqzj_dj += rs.getInt("jd6_dj_count");
					jd6_msqzj_jj += rs.getInt("jd6_jj_count");
					jd6_msqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("43")) {
					jd6_tsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_tsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_tsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("44")) {
					jd6_ldsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_ldsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_ldsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("47")) {
					jd6_mzsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_mzsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_mzsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("48")) {
					jd6_sjj_dj += rs.getInt("jd6_dj_count");
					jd6_sjj_jj += rs.getInt("jd6_jj_count");
					jd6_sjj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("49")) {
					jd6_lssqzj_dj += rs.getInt("jd6_dj_count");
					jd6_lssqzj_jj += rs.getInt("jd6_jj_count");
					jd6_lssqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("4A")) {
					jd6_wgsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_wgsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_wgsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("4B")) {
					jd6_xbsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_xbsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_xbsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("4C")) {
					jd6_qxxsqzj_dj += rs.getInt("jd6_dj_count");
					jd6_qxxsqzj_jj += rs.getInt("jd6_jj_count");
					jd6_qxxsqzj_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("4D")) {
					jd6_jxstcsb_dj += rs.getInt("jd6_dj_count");
					jd6_jxstcsb_jj += rs.getInt("jd6_jj_count");
					jd6_jxstcsb_wj += rs.getInt("jd6_wj_count");
				}
				list.add(deviceCount);

			}
			jd6_dj_total += jd6_qsqzj_dj + jd6_msqzj_dj + jd6_tsqzj_dj + jd6_ldsqzj_dj + jd6_mzsqzj_dj + jd6_sjj_dj + jd6_lssqzj_dj + jd6_wgsqzj_dj + jd6_xbsqzj_dj + jd6_qxxsqzj_dj + jd6_jxstcsb_dj;
			jd6_jj_total += jd6_qsqzj_jj + jd6_msqzj_jj + jd6_tsqzj_jj + jd6_ldsqzj_jj + jd6_mzsqzj_jj + jd6_sjj_jj + jd6_lssqzj_jj + jd6_wgsqzj_jj + jd6_xbsqzj_jj + jd6_qxxsqzj_jj + jd6_jxstcsb_jj;
			jd6_wj_total += jd6_qsqzj_wj + jd6_msqzj_wj + jd6_tsqzj_wj + jd6_ldsqzj_wj + jd6_mzsqzj_wj + jd6_sjj_wj + jd6_lssqzj_wj + jd6_wgsqzj_wj + jd6_xbsqzj_wj + jd6_qxxsqzj_wj + jd6_jxstcsb_wj;
			DeviceCountDTO qsCount = new DeviceCountDTO();
			qsCount.setDevice_code("4100");
			qsCount.setDevice_name("<b>桥式起重机</b>");
			qsCount.setJd6_dj_count("<b>" + jd6_qsqzj_dj + "</b>");
			qsCount.setJd6_jj_count("<b>" + jd6_qsqzj_jj + "</b>");
			qsCount.setJd6_wj_count("<b>" + jd6_qsqzj_wj + "</b>");
			qsCount.setTotal(jd6_qsqzj_dj + jd6_qsqzj_jj + jd6_qsqzj_wj);
			list.add(qsCount);
			DeviceCountDTO msCount = new DeviceCountDTO();
			msCount.setDevice_code("4200");
			msCount.setDevice_name("<b>门式起重机</b>");
			msCount.setJd6_dj_count("<b>" + jd6_msqzj_dj + "</b>");
			msCount.setJd6_jj_count("<b>" + jd6_msqzj_jj + "</b>");
			msCount.setJd6_wj_count("<b>" + jd6_msqzj_wj + "</b>");
			msCount.setTotal(jd6_msqzj_dj + jd6_msqzj_jj + jd6_msqzj_wj);
			list.add(msCount);
			DeviceCountDTO tsCount = new DeviceCountDTO();
			tsCount.setDevice_code("4300");
			tsCount.setDevice_name("<b>塔式起重机</b>");
			tsCount.setJd6_dj_count("<b>" + jd6_tsqzj_dj + "</b>");
			tsCount.setJd6_jj_count("<b>" + jd6_tsqzj_jj + "</b>");
			tsCount.setJd6_wj_count("<b>" + jd6_tsqzj_wj + "</b>");
			tsCount.setTotal(jd6_tsqzj_dj + jd6_tsqzj_jj + jd6_tsqzj_wj);
			list.add(tsCount);
			DeviceCountDTO ldsCount = new DeviceCountDTO();
			ldsCount.setDevice_code("4400");
			ldsCount.setDevice_name("<b>流动式起重机</b>");
			ldsCount.setJd6_dj_count("<b>" + jd6_ldsqzj_dj + "</b>");
			ldsCount.setJd6_jj_count("<b>" + jd6_ldsqzj_jj + "</b>");
			ldsCount.setJd6_wj_count("<b>" + jd6_ldsqzj_wj + "</b>");
			ldsCount.setTotal(jd6_ldsqzj_dj + jd6_ldsqzj_jj + jd6_ldsqzj_wj);
			list.add(ldsCount);
			DeviceCountDTO mzsCount = new DeviceCountDTO();
			mzsCount.setDevice_code("4700");
			mzsCount.setDevice_name("<b>门座式起重机</b>");
			mzsCount.setJd6_dj_count("<b>" + jd6_mzsqzj_dj + "</b>");
			mzsCount.setJd6_jj_count("<b>" + jd6_mzsqzj_jj + "</b>");
			mzsCount.setJd6_wj_count("<b>" + jd6_mzsqzj_wj + "</b>");
			mzsCount.setTotal(jd6_mzsqzj_dj + jd6_mzsqzj_jj + jd6_mzsqzj_wj);
			list.add(mzsCount);
			DeviceCountDTO sjjCount = new DeviceCountDTO();
			sjjCount.setDevice_code("4800");
			sjjCount.setDevice_name("<b>升降机</b>");
			sjjCount.setJd6_dj_count("<b>" + jd6_sjj_dj + "</b>");
			sjjCount.setJd6_jj_count("<b>" + jd6_sjj_jj + "</b>");
			sjjCount.setJd6_wj_count("<b>" + jd6_sjj_wj + "</b>");
			sjjCount.setTotal(jd6_sjj_dj + jd6_sjj_jj + jd6_sjj_wj);
			list.add(sjjCount);
			DeviceCountDTO lssCount = new DeviceCountDTO();
			lssCount.setDevice_code("4900");
			lssCount.setDevice_name("<b>缆索式起重机</b>");
			lssCount.setJd6_dj_count("<b>" + jd6_lssqzj_dj + "</b>");
			lssCount.setJd6_jj_count("<b>" + jd6_lssqzj_jj + "</b>");
			lssCount.setJd6_wj_count("<b>" + jd6_lssqzj_wj + "</b>");
			lssCount.setTotal(jd6_lssqzj_dj + jd6_lssqzj_jj + jd6_lssqzj_wj);
			list.add(lssCount);
			DeviceCountDTO wgsCount = new DeviceCountDTO();
			wgsCount.setDevice_code("4A00");
			wgsCount.setDevice_name("<b>桅杆式起重机</b>");
			wgsCount.setJd6_dj_count("<b>" + jd6_wgsqzj_dj + "</b>");
			wgsCount.setJd6_jj_count("<b>" + jd6_wgsqzj_jj + "</b>");
			wgsCount.setJd6_wj_count("<b>" + jd6_wgsqzj_wj + "</b>");
			wgsCount.setTotal(jd6_wgsqzj_dj + jd6_wgsqzj_jj + jd6_wgsqzj_wj);
			list.add(wgsCount);
			DeviceCountDTO xbsCount = new DeviceCountDTO();
			xbsCount.setDevice_code("4B00");
			xbsCount.setDevice_name("<b>旋臂式起重机</b>");
			xbsCount.setJd6_dj_count("<b>" + jd6_xbsqzj_dj + "</b>");
			xbsCount.setJd6_jj_count("<b>" + jd6_xbsqzj_jj + "</b>");
			xbsCount.setJd6_wj_count("<b>" + jd6_xbsqzj_wj + "</b>");
			xbsCount.setTotal(jd6_xbsqzj_dj + jd6_xbsqzj_jj + jd6_xbsqzj_wj);
			list.add(xbsCount);
			DeviceCountDTO qxxCount = new DeviceCountDTO();
			qxxCount.setDevice_code("4C00");
			qxxCount.setDevice_name("<b>轻小型起重机</b>");
			qxxCount.setJd6_dj_count("<b>" + jd6_qxxsqzj_dj + "</b>");
			qxxCount.setJd6_jj_count("<b>" + jd6_qxxsqzj_jj + "</b>");
			qxxCount.setJd6_wj_count("<b>" + jd6_qxxsqzj_wj + "</b>");
			qxxCount.setTotal(jd6_qxxsqzj_dj + jd6_qxxsqzj_jj + jd6_qxxsqzj_wj);
			list.add(qxxCount);
			DeviceCountDTO jxsCount = new DeviceCountDTO();
			jxsCount.setDevice_code("4D00");
			jxsCount.setDevice_name("<b>机械式停车设备</b>");
			jxsCount.setJd6_dj_count("<b>" + jd6_jxstcsb_dj + "</b>");
			jxsCount.setJd6_jj_count("<b>" + jd6_jxstcsb_jj + "</b>");
			jxsCount.setJd6_wj_count("<b>" + jd6_jxstcsb_wj + "</b>");
			jxsCount.setTotal(jd6_jxstcsb_dj + jd6_jxstcsb_jj + jd6_jxstcsb_wj);
			list.add(jxsCount);
			DeviceCountDTO totalCount = new DeviceCountDTO();
			totalCount.setDevice_code("99999"); // 用于合计行标记
			totalCount.setDevice_name("<b>合计</b>");
			totalCount.setJd6_dj_count("<b>" + jd6_dj_total + "</b>");
			totalCount.setJd6_jj_count("<b>" + jd6_jj_total + "</b>");
			totalCount.setJd6_wj_count("<b>" + jd6_wj_total + "</b>");
			totalCount.setTotal(jd6_dj_total + jd6_jj_total + jd6_wj_total);
			list.add(totalCount);

			Collections.sort(list, new MyComparetor());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 机电六部场（厂）内专用机动车辆监检、委检统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */

	public List<DeviceCountDTO> exportPrintedCount_JD6cn(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " with info as ( "
					+ " select t.id,d.device_sort_code,t.print_time,t1.check_type,o.id org_id from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ " where t1.id = t.fk_inspection_id and t.fk_tsjc_device_document_id = d.id and t.check_unit_id = o.id and t.print_time is not null and d.device_sort_code(+) like '5%' and t.data_status <> '99'"
					+ " ),codevalue as ( "
					+ " select pv.value,pv.name from pub_code_table p,pub_code_table_values pv "
					+ " where p.id = pv.code_table_id and p.code='device_classify' and pv.CODE_TABLE_VALUES_ID is not null and pv.value like '5%' ) "
					+ " select c.value,c.name, "
					+ " count(case when t.org_id='100024' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_jj_count, "
					+ " count(case when t.org_id='100024' and t.check_type = '4' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_wj_count "
					+ " from codevalue c , info t where c.value = t.device_sort_code(+) group by c.value,c.name ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			//机动工程车辆
			int jd6_jdgccl_jj = 0;
			int jd6_jdgccl_wj = 0;
			//非公路用旅游观光车辆
			int jd6_fglyggcl_jj = 0;
			int jd6_fglyggcl_wj = 0;
			
			int jd6_jj_total = 0;
			int jd6_wj_total = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("value").endsWith("00")) {
					continue;
				}
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setDevice_code(rs.getString("value"));
				deviceCount.setDevice_name(rs.getString("name"));
				deviceCount.setJd6_jj_count(rs.getString("jd6_jj_count"));
				deviceCount.setJd6_wj_count(rs.getString("jd6_wj_count"));
				deviceCount.setTotal(rs.getInt("jd6_jj_count") + rs.getInt("jd6_wj_count"));
				if (rs.getString("value").startsWith("51")) {
					jd6_jdgccl_jj += rs.getInt("jd6_jj_count");
					jd6_jdgccl_wj += rs.getInt("jd6_wj_count");
				} else if (rs.getString("value").startsWith("52")) {
					jd6_fglyggcl_jj += rs.getInt("jd6_jj_count");
					jd6_fglyggcl_wj += rs.getInt("jd6_wj_count");
				}
				list.add(deviceCount);

			}
			jd6_jj_total += jd6_jdgccl_jj + jd6_fglyggcl_jj;
			jd6_wj_total += jd6_jdgccl_wj + jd6_fglyggcl_wj;
			DeviceCountDTO jdgyCount = new DeviceCountDTO();
			jdgyCount.setDevice_code("5100");
			jdgyCount.setDevice_name("<b>机动工业车辆</b>");
			jdgyCount.setJd6_jj_count("<b>" + jd6_jdgccl_jj + "</b>");
			jdgyCount.setJd6_wj_count("<b>" + jd6_jdgccl_wj + "</b>");
			jdgyCount.setTotal(jd6_jdgccl_jj + jd6_jdgccl_wj);
			list.add(jdgyCount);
			DeviceCountDTO fglggCount = new DeviceCountDTO();
			fglggCount.setDevice_code("5200");
			fglggCount.setDevice_name("<b>非公路用旅游观光车辆</b>");
			fglggCount.setJd6_jj_count("<b>" + jd6_fglyggcl_jj + "</b>");
			fglggCount.setJd6_wj_count("<b>" + jd6_fglyggcl_wj + "</b>");
			fglggCount.setTotal(jd6_fglyggcl_jj + jd6_fglyggcl_wj);
			list.add(fglggCount);
			DeviceCountDTO totalCount = new DeviceCountDTO();
			totalCount.setDevice_code("99999"); // 用于合计行标记
			totalCount.setDevice_name("<b>合计</b>");
			totalCount.setJd6_jj_count("<b>" + jd6_jj_total + "</b>");
			totalCount.setJd6_wj_count("<b>" + jd6_wj_total + "</b>");
			totalCount.setTotal(jd6_jj_total + jd6_wj_total);
			list.add(totalCount);

			Collections.sort(list, new MyComparetor());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 机电六部大型游乐设施定检、监检统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */

	public List<DeviceCountDTO> exportPrintedCount_JD6yl(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " with info as ( "
					+ " select t.id,d.device_sort_code,t.print_time,t1.check_type,o.id org_id from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ " where t1.id = t.fk_inspection_id and t.fk_tsjc_device_document_id = d.id and t.check_unit_id = o.id and t.print_time is not null and d.device_sort_code(+) like '6%' and t.data_status <> '99'"
					+ " ),codevalue as ( "
					+ " select pv.value,pv.name from pub_code_table p,pub_code_table_values pv "
					+ " where p.id = pv.code_table_id and p.code='device_classify' and pv.CODE_TABLE_VALUES_ID is not null and pv.value like '6%' ) "
					+ " select c.value,c.name, "
					+ " count(case when t.org_id='100024' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_dj_count, "
					+ " count(case when t.org_id='100024' and t.check_type = '2' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_jj_count "
					+ " from codevalue c , info t where c.value = t.device_sort_code(+) group by c.value,c.name ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			//观览车类
			int jd6_ggcl_dj = 0;
			int jd6_ggcl_jj = 0;
			//滑行车类
			int jd6_hxcl_dj = 0;
			int jd6_hxcl_jj = 0;
			//架空游览车类
			int jd6_jkylcl_dj = 0;
			int jd6_jkylcl_jj = 0;
			//陀螺类
			int jd6_tll_dj = 0;
			int jd6_tll_jj = 0;
			//飞行塔类
			int jd6_fxtl_dj = 0;
			int jd6_fxtl_jj = 0;
			//转马类
			int jd6_zml_dj = 0;
			int jd6_zml_jj = 0;
			//自控飞机类
			int jd6_zkfjl_dj = 0;
			int jd6_zkfjl_jj = 0;
			//赛车类
			int jd6_scl_dj = 0;
			int jd6_scl_jj = 0;
			//小火车类
			int jd6_xhcl_dj = 0;
			int jd6_xhcl_jj = 0;
			//碰碰车类
			int jd6_ppcl_dj = 0;
			int jd6_ppcl_jj = 0;
			//滑道类
			int jd6_hdl_dj = 0;
			int jd6_hdl_jj = 0;
			//水上游乐设施
			int jd6_ssylss_dj = 0;
			int jd6_ssylss_jj = 0;
			//无动力游乐设施
			int jd6_wdlylss_dj = 0;
			int jd6_wdlylss_jj = 0;
			
			int jd6_dj_total = 0;
			int jd6_jj_total = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("value").endsWith("00")) {
					continue;
				}
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setDevice_code(rs.getString("value"));
				deviceCount.setDevice_name(rs.getString("name"));
				deviceCount.setJd6_dj_count(rs.getString("jd6_dj_count"));
				deviceCount.setJd6_jj_count(rs.getString("jd6_jj_count"));
				deviceCount.setTotal(rs.getInt("jd6_dj_count") + rs.getInt("jd6_jj_count"));
				if (rs.getString("value").startsWith("61")) {
					jd6_ggcl_dj += rs.getInt("jd6_dj_count");
					jd6_ggcl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("62")) {
					jd6_hxcl_dj += rs.getInt("jd6_dj_count");
					jd6_hxcl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("63")) {
					jd6_jkylcl_dj += rs.getInt("jd6_dj_count");
					jd6_jkylcl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("64")) {
					jd6_tll_dj += rs.getInt("jd6_dj_count");
					jd6_tll_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("65")) {
					jd6_fxtl_dj += rs.getInt("jd6_dj_count");
					jd6_fxtl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("66")) {
					jd6_zml_dj += rs.getInt("jd6_dj_count");
					jd6_zml_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("67")) {
					jd6_zkfjl_dj += rs.getInt("jd6_dj_count");
					jd6_zkfjl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("68")) {
					jd6_scl_dj += rs.getInt("jd6_dj_count");
					jd6_scl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("69")) {
					jd6_xhcl_dj += rs.getInt("jd6_dj_count");
					jd6_xhcl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("6A")) {
					jd6_ppcl_dj += rs.getInt("jd6_dj_count");
					jd6_ppcl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("6B")) {
					jd6_hdl_dj += rs.getInt("jd6_dj_count");
					jd6_hdl_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("6D")) {
					jd6_ssylss_dj += rs.getInt("jd6_dj_count");
					jd6_ssylss_jj += rs.getInt("jd6_jj_count");
				} else if (rs.getString("value").startsWith("6E")) {
					jd6_wdlylss_dj += rs.getInt("jd6_dj_count");
					jd6_wdlylss_jj += rs.getInt("jd6_jj_count");
				}
				list.add(deviceCount);

			}
			jd6_dj_total += jd6_ggcl_dj + jd6_hxcl_dj + jd6_jkylcl_dj + jd6_tll_dj + jd6_fxtl_dj + jd6_zml_dj + jd6_zkfjl_dj + jd6_scl_dj + jd6_xhcl_dj + jd6_ppcl_dj + jd6_hdl_dj + jd6_ssylss_dj + jd6_wdlylss_dj;
			jd6_jj_total += jd6_ggcl_jj + jd6_hxcl_jj + jd6_jkylcl_jj + jd6_tll_jj + jd6_fxtl_jj + jd6_zml_jj + jd6_zkfjl_jj + jd6_scl_jj + jd6_xhcl_jj + jd6_ppcl_jj + jd6_hdl_jj + jd6_ssylss_jj + jd6_wdlylss_jj;
			DeviceCountDTO glclCount = new DeviceCountDTO();
			glclCount.setDevice_code("6100");
			glclCount.setDevice_name("<b>观览车类</b>");
			glclCount.setJd6_dj_count("<b>" + jd6_ggcl_dj + "</b>");
			glclCount.setJd6_jj_count("<b>" + jd6_ggcl_jj + "</b>");
			glclCount.setTotal(jd6_ggcl_dj + jd6_ggcl_jj);
			list.add(glclCount);
			DeviceCountDTO hxclCount = new DeviceCountDTO();
			hxclCount.setDevice_code("6200");
			hxclCount.setDevice_name("<b>滑行车类</b>");
			hxclCount.setJd6_dj_count("<b>" + jd6_hxcl_dj + "</b>");
			hxclCount.setJd6_jj_count("<b>" + jd6_hxcl_jj + "</b>");
			hxclCount.setTotal(jd6_hxcl_dj + jd6_hxcl_jj);
			list.add(hxclCount);
			DeviceCountDTO jkylclCount = new DeviceCountDTO();
			jkylclCount.setDevice_code("6300");
			jkylclCount.setDevice_name("<b>架空游览车类</b>");
			jkylclCount.setJd6_dj_count("<b>" + jd6_jkylcl_dj + "</b>");
			jkylclCount.setJd6_jj_count("<b>" + jd6_jkylcl_jj + "</b>");
			jkylclCount.setTotal(jd6_jkylcl_dj + jd6_jkylcl_jj);
			list.add(jkylclCount);
			DeviceCountDTO tllCount = new DeviceCountDTO();
			tllCount.setDevice_code("6400");
			tllCount.setDevice_name("<b>陀螺类</b>");
			tllCount.setJd6_dj_count("<b>" + jd6_tll_dj + "</b>");
			tllCount.setJd6_jj_count("<b>" + jd6_tll_jj + "</b>");
			tllCount.setTotal(jd6_tll_dj + jd6_tll_jj);
			list.add(tllCount);
			DeviceCountDTO fxtlCount = new DeviceCountDTO();
			fxtlCount.setDevice_code("6500");
			fxtlCount.setDevice_name("<b>飞行塔类</b>");
			fxtlCount.setJd6_dj_count("<b>" + jd6_fxtl_dj + "</b>");
			fxtlCount.setJd6_jj_count("<b>" + jd6_fxtl_jj + "</b>");
			fxtlCount.setTotal(jd6_fxtl_dj + jd6_fxtl_jj);
			list.add(fxtlCount);
			DeviceCountDTO zmlCount = new DeviceCountDTO();
			zmlCount.setDevice_code("6600");
			zmlCount.setDevice_name("<b>转马类</b>");
			zmlCount.setJd6_dj_count("<b>" + jd6_zml_dj + "</b>");
			zmlCount.setJd6_jj_count("<b>" + jd6_zml_jj + "</b>");
			zmlCount.setTotal(jd6_zml_dj + jd6_zml_jj);
			list.add(zmlCount);
			DeviceCountDTO zkfjlCount = new DeviceCountDTO();
			zkfjlCount.setDevice_code("6700");
			zkfjlCount.setDevice_name("<b>自控飞机类</b>");
			zkfjlCount.setJd6_dj_count("<b>" + jd6_zkfjl_dj + "</b>");
			zkfjlCount.setJd6_jj_count("<b>" + jd6_zkfjl_jj + "</b>");
			zkfjlCount.setTotal(jd6_zkfjl_dj + jd6_zkfjl_jj);
			list.add(zkfjlCount);
			DeviceCountDTO sclCount = new DeviceCountDTO();
			sclCount.setDevice_code("6800");
			sclCount.setDevice_name("<b>赛车类</b>");
			sclCount.setJd6_dj_count("<b>" + jd6_scl_dj + "</b>");
			sclCount.setJd6_jj_count("<b>" + jd6_scl_jj + "</b>");
			sclCount.setTotal(jd6_scl_dj + jd6_scl_jj);
			list.add(sclCount);
			DeviceCountDTO xhclCount = new DeviceCountDTO();
			xhclCount.setDevice_code("6900");
			xhclCount.setDevice_name("<b>小火车类</b>");
			xhclCount.setJd6_dj_count("<b>" + jd6_xhcl_dj + "</b>");
			xhclCount.setJd6_jj_count("<b>" + jd6_xhcl_jj + "</b>");
			xhclCount.setTotal(jd6_xhcl_dj + jd6_xhcl_jj);
			list.add(xhclCount);
			DeviceCountDTO ppclCount = new DeviceCountDTO();
			ppclCount.setDevice_code("6A00");
			ppclCount.setDevice_name("<b>碰碰车类</b>");
			ppclCount.setJd6_dj_count("<b>" + jd6_ppcl_dj + "</b>");
			ppclCount.setJd6_jj_count("<b>" + jd6_ppcl_jj + "</b>");
			ppclCount.setTotal(jd6_ppcl_dj + jd6_ppcl_jj);
			list.add(ppclCount);
			DeviceCountDTO hdlCount = new DeviceCountDTO();
			hdlCount.setDevice_code("6B00");
			hdlCount.setDevice_name("<b>滑道类</b>");
			hdlCount.setJd6_dj_count("<b>" + jd6_hdl_dj + "</b>");
			hdlCount.setJd6_jj_count("<b>" + jd6_hdl_jj + "</b>");
			hdlCount.setTotal(jd6_hdl_dj + jd6_hdl_jj);
			list.add(hdlCount);
			DeviceCountDTO ssylssCount = new DeviceCountDTO();
			ssylssCount.setDevice_code("6D00");
			ssylssCount.setDevice_name("<b>水上游乐设施</b>");
			ssylssCount.setJd6_dj_count("<b>" + jd6_ssylss_dj + "</b>");
			ssylssCount.setJd6_jj_count("<b>" + jd6_ssylss_jj + "</b>");
			ssylssCount.setTotal(jd6_ssylss_dj + jd6_ssylss_jj);
			list.add(ssylssCount);
			DeviceCountDTO wdlylssCount = new DeviceCountDTO();
			wdlylssCount.setDevice_code("6E00");
			wdlylssCount.setDevice_name("<b>无动力游乐设施</b>");
			wdlylssCount.setJd6_dj_count("<b>" + jd6_wdlylss_dj + "</b>");
			wdlylssCount.setJd6_jj_count("<b>" + jd6_wdlylss_jj + "</b>");
			wdlylssCount.setTotal(jd6_wdlylss_dj + jd6_wdlylss_jj);
			list.add(wdlylssCount);
			DeviceCountDTO totalCount = new DeviceCountDTO();
			totalCount.setDevice_code("99999"); // 用于合计行标记
			totalCount.setDevice_name("<b>合计</b>");
			totalCount.setJd6_dj_count("<b>" + jd6_dj_total + "</b>");
			totalCount.setJd6_jj_count("<b>" + jd6_jj_total + "</b>");
			totalCount.setTotal(jd6_dj_total + jd6_jj_total);
			list.add(totalCount);

			Collections.sort(list, new MyComparetor());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 机电六部客运索道定检统计
	 * 
	 * @param startDate
	 *            统计开始日期
	 * @param endDate
	 *            统计结束日期
	 * @param device_type
	 *            设备类别
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2014-10-21 下午05:55:00
	 */

	public List<DeviceCountDTO> exportPrintedCount_JD6kysd(String startDate,
			String endDate) {
		List<DeviceCountDTO> list = new ArrayList<DeviceCountDTO>();
		try {
			String sql = " with info as ( "
					+ " select t.id,d.device_sort_code,t.print_time,t1.check_type,o.id org_id from tzsb_inspection_info t,tzsb_inspection t1,base_device_document d,SYS_ORG o "
					+ " where t1.id = t.fk_inspection_id and t.fk_tsjc_device_document_id = d.id and t.check_unit_id = o.id and t.print_time is not null and d.device_sort_code(+) like '9%' and t.data_status <> '99'"
					+ " ),codevalue as ( "
					+ " select pv.value,pv.name from pub_code_table p,pub_code_table_values pv "
					+ " where p.id = pv.code_table_id and p.code='device_classify' and pv.CODE_TABLE_VALUES_ID is not null and pv.value like '9%' ) "
					+ " select c.value,c.name, "
					+ " count(case when t.org_id='100024' and t.check_type = '3' and to_char(t.print_time,'yyyy-MM-dd') >= ? and to_char(t.print_time,'yyyy-MM-dd') <= ? then 1 else null end) as jd6_dj_count "
					+ " from codevalue c , info t where c.value = t.device_sort_code(+) group by c.value,c.name ";
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			//客运架空索道
			int jd6_kyjk_dj = 0;
			//客运缆车
			int jd6_ky_dj = 0;
			//客运拖牵索道
			int jd6_kytq_dj = 0;
			
			int jd6_dj_total = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("value").endsWith("00")) {
					continue;
				}
				DeviceCountDTO deviceCount = new DeviceCountDTO();
				deviceCount.setDevice_code(rs.getString("value"));
				deviceCount.setDevice_name(rs.getString("name"));
				deviceCount.setJd6_dj_count(rs.getString("jd6_dj_count"));
				deviceCount.setTotal(rs.getInt("jd6_dj_count"));
				if (rs.getString("value").startsWith("91")) {
					jd6_kyjk_dj += rs.getInt("jd6_dj_count");
				} else if (rs.getString("value").startsWith("92")) {
					jd6_ky_dj += rs.getInt("jd6_dj_count");
				} else if (rs.getString("value").startsWith("93")) {
					jd6_kytq_dj += rs.getInt("jd6_dj_count");
				} 
				list.add(deviceCount);

			}
			jd6_dj_total += jd6_kyjk_dj + jd6_ky_dj + jd6_kytq_dj;
			DeviceCountDTO kyjkCount = new DeviceCountDTO();
			kyjkCount.setDevice_code("9100");
			kyjkCount.setDevice_name("<b>客运架空索道</b>");
			kyjkCount.setJd6_dj_count("<b>" + jd6_kyjk_dj + "</b>");
			kyjkCount.setTotal(jd6_kyjk_dj);
			list.add(kyjkCount);
			DeviceCountDTO kyCount = new DeviceCountDTO();
			kyCount.setDevice_code("9200");
			kyCount.setDevice_name("<b>客运缆车</b>");
			kyCount.setJd6_dj_count("<b>" + jd6_ky_dj + "</b>");
			kyCount.setTotal(jd6_ky_dj);
			list.add(kyCount);
			DeviceCountDTO kytqCount = new DeviceCountDTO();
			kytqCount.setDevice_code("9300");
			kytqCount.setDevice_name("<b>客运拖牵索道</b>");
			kytqCount.setJd6_dj_count("<b>" + jd6_kytq_dj + "</b>");
			kytqCount.setTotal(jd6_kytq_dj);
			list.add(kytqCount);
			
			DeviceCountDTO totalCount = new DeviceCountDTO();
			totalCount.setDevice_code("99999"); // 用于合计行标记
			totalCount.setDevice_name("<b>合计</b>");
			totalCount.setJd6_dj_count("<b>" + jd6_dj_total + "</b>");
			totalCount.setTotal(jd6_dj_total);
			list.add(totalCount);

			Collections.sort(list, new MyComparetor());
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
