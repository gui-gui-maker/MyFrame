package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.khnt.base.Factory;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

public class WxLeaveUtil {

	/**
	 * 获取请休假待处理数据
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 下午14:18:00
	 */
	public static List<Map<String, Object>> getUndealLeaveList() {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User) curUser.getSysUser();
			Employee employee = (Employee) user.getEmployee();

			String user_id = user.getId();
			String employee_id = employee.getId();
			String departmentid = curUser.getDepartment().getId();

			String sql = "";

			String sql1 = "select s.id,s.people_name,c2.name as leave_type,to_char(s.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(s.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,s.leave_count1,s.dep_name,to_char(s.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date "
					+ "from (select b.*,t.handler_id,t.org_id from TJY2_RL_LEAVE b, v_pub_worktask_add_position t "
					+ "where b.id=t.SERVICE_ID  and t.STATUS='0' " // and t.WORK_TYPE like '%TJY2_RL_%'
					+ "and (b.apply_status='YTJ' or b.apply_status='SPZ' )) s, "
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c, "
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
					+ "where s.handler_id = '" + user_id
					+ "' and s.apply_status=c.value(+) and s.leave_type=c2.value(+)";

			String sql2 = "and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d "
					+ "where d.fk_rl_emplpyee_id='" + employee_id + "'), s.dep_id) > 0";

			String sql3 = " select s.id,s.people_name,c2.name as leave_type,to_char(s.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(s.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,s.leave_count1,s.dep_name,to_char(s.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date from ( "
					+ "select b.*,t.handler_id,b.dep_id org_id from TJY2_RL_LEAVE b, v_pub_worktask t "
					+ "where b.id=t.SERVICE_ID and t.STATUS='0' " // and t.WORK_TYPE like '%TJY2_RL_%'
					+ "and (b.apply_status='YTJ' or b.apply_status='SPZ' )) s, "
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c,"
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 where s.handler_id = '"
					+ user_id + "' and s.apply_status=c.value(+) and s.leave_type=c2.value(+)"
					+ "and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d " + "where d.fk_rl_emplpyee_id='"
					+ employee_id + "'), s.dep_id) > 0";

			String sql4 = sql1 + " and s.org_id = '" + departmentid + "'";

			String sql5 = "select b.id,b.people_name,c2.name as leave_type,to_char(b.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(b.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,b.leave_count1,b.dep_name,to_char(b.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date "
					+ "from TJY2_RL_LEAVE b, "
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c, "
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
					+ "where b.APPLY_STATUS = 'SPTG' and b.apply_status=c.value(+) and b.leave_type=c2.value(+)";

			String sql6 = "select b.id,b.people_name,c2.name as leave_type,to_char(b.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(b.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,b.leave_count1,b.dep_name,to_char(b.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date "
					+ "from TJY2_RL_LEAVE b, "
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c,"
					+ " (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
					+ " where b.people_id = '0000' and b.apply_status=c.value(+) and b.leave_type=c2.value(+)";

			/*
			 * String orderby1 = " order by b.people_sign_date desc"; String orderby2 =
			 * " order by s.people_sign_date desc";
			 */
			String orderby = " order by people_sign_date desc";
			// 获取人员角色信息
			Map<String, String> roles = curUser.getRoles();
			String role_name = "";
			for (String roleid : roles.keySet()) {
				Object obj = roles.get(roleid);
				if (StringUtil.isNotEmpty(role_name)) {
					role_name += ",";
				}
				role_name += obj;
			}

			if (role_name.contains("部门负责人")) {
				sql = sql4;
				if (employee.getName().equals("孙宇艺")) {
					sql += " and s.dep_id in ('100025','100048')";
				}
				if (employee.getName().equals("韩绍义")) {
					sql += " and s.dep_id='100030'";
				}
				if (employee.getName().equals("李山桥")) {
					sql += " and s.dep_id='100044'";
				}
				sql += orderby;
			}
			if (role_name.contains("请休假申请人事审核")) {
				// 使用默认的查询语句sql1
				sql = sql1 + orderby;
			}
			if (role_name.contains("请休假分管领导审核")) {
				sql = sql1 + sql2 + orderby;
			}
			if (role_name.contains("部门负责人") && role_name.contains("请休假分管领导审核")) {
				sql = sql1 + sql2;

				if (employee.getName().equals("孙宇艺")) {
					sql = sql4 + " and s.dep_id in ('100025','100048') union all " + sql3;
				}
				if (employee.getName().equals("韩绍义")) {
					sql = sql4 + " and s.dep_id='100030' union all " + sql3;
				}
				sql += orderby;
			}
			if (role_name.contains("院长")) {
				sql = sql1 + orderby;
			}
			if (role_name.contains("销假")) {
				sql = sql5 + orderby;
			}
			if (!role_name.contains("请休假申请处理按钮")) {
				sql = sql6 + orderby;
			}

			System.out.println("待办理查询语句====================" + sql);
			if (StringUtil.isNotEmpty(sql)) {
				// 获取connection
				Connection conn = Factory.getDB().getConnetion();
				Statement queryStatement = conn.createStatement();
				ResultSet executeQuery = queryStatement.executeQuery(sql);

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (executeQuery.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", executeQuery.getString("id"));
					map.put("people_name", executeQuery.getString("people_name"));
					map.put("leave_type", executeQuery.getString("leave_type"));
					map.put("start_date", executeQuery.getString("start_date"));
					map.put("end_date", executeQuery.getString("end_date"));
					map.put("apply_status", executeQuery.getString("apply_status"));
					map.put("days", executeQuery.getString("leave_count1"));
					map.put("dep_name", executeQuery.getString("dep_name"));
					map.put("people_sign_date", executeQuery.getString("people_sign_date"));
					list.add(map);
				}

				Factory.getDB().freeConnetion(conn);
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取请休假已处理数据
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 下午14:18:00
	 */
	public static List<Map<String, Object>> getDealedLeaveList() {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User) curUser.getSysUser();
			Employee employee = (Employee) user.getEmployee();

			String employee_id = employee.getId();
			String departmentid = curUser.getDepartment().getId();

			String sql = "";

			/*
			 * String sql1 =
			 * "select b.id,b.people_name,c2.name as leave_type,to_char(b.start_date,'yyyy-MM-dd') as start_date, "
			 * +
			 * "to_char(b.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,b.leave_count1,b.dep_name,"
			 * + "to_char(b.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date " +
			 * "from TJY2_RL_LEAVE b, (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "
			 * + "where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c," +
			 * "(select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
			 * + "where b.people_id = '" + employee_id +
			 * "' and b.apply_status=c.value(+) and b.leave_type=c2.value(+)";
			 */

			String sql2 = "select b.id,b.people_name,c2.name as leave_type,to_char(b.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(b.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,b.leave_count1,b.dep_name,"
					+ "to_char(b.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date "
					+ "from TJY2_RL_LEAVE b, v_pub_worktask v, (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "
					+ "where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c,"
					+ "(select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
					+ "where b.id = v.SERVICE_ID and b.APPLY_STATUS in ('SPZ', 'SPTG', 'SPBTG', 'YCX', 'YXJ') and v.STATUS = '3' and v.ACTIVITY_NAME !='提交' and b.apply_status=c.value(+) and b.leave_type=c2.value(+)"
					+ "and v.HANDLER_ID='" + user.getId() + "'";
			String sql2_1 = "select b.id,b.people_name,c2.name as leave_type,to_char(b.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(b.end_date,'yyyy-MM-dd') as end_date,c.name as apply_status,b.leave_count1,b.dep_name,"
					+ "to_char(b.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date "
					+ "from TJY2_RL_LEAVE b, v_pub_worktask_add_position v, (select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "
					+ "where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c,"
					+ "(select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
					+ "where b.id = v.SERVICE_ID and b.APPLY_STATUS in ('SPZ', 'SPTG', 'SPBTG', 'YCX', 'YXJ') and v.STATUS = '3' and v.ACTIVITY_NAME !='提交' and b.apply_status=c.value(+) and b.leave_type=c2.value(+)"
					+ "and v.HANDLER_ID='" + user.getId() + "'";
			String sql3 = "and instr((select d.fk_sys_org_id from TJY2_RL_ORGID_LEADERID d "
					+ " where d.fk_rl_emplpyee_id='" + employee_id + "'), b.dep_id) > 0";
			// 获取人员角色信息
			Map<String, String> roles = curUser.getRoles();
			String role_name = "";
			for (String roleid : roles.keySet()) {
				Object obj = roles.get(roleid);
				if (StringUtil.isNotEmpty(role_name)) {
					role_name += ",";
				}
				role_name += obj;
			}

			/*
			 * if (role_name.contains("部门负责人")) { sql = sql1 + " union all " + sql2 +
			 * " and b.dep_id = '" + departmentid + "'"; } if
			 * (role_name.contains("请休假申请人事审核")) { sql = sql1 + " union all " + sql2 ; } if
			 * (role_name.contains("请休假分管领导审核")) { sql = sql1 + " union all " + sql2 + sql3;
			 * 
			 * if (employee.getName().equals("孙宇艺")) { sql = sql1 + " union all" + sql2 +
			 * " and b.dep_id='100025' union all" + sql2 + sql3; } } if
			 * (role_name.contains("院长")) { sql = sql1 + " union all " + sql2; } if
			 * (role_name.contains("销假")) { sql = sql1; }
			 */
			if (role_name.contains("部门负责人")) {
				sql = sql2 + " and b.dep_id = '" + departmentid + "' ";
				if (employee.getName().equals("李山桥")) {
					sql = sql2_1 + " and b.dep_id = '100044' ";
				}
			}
			if (role_name.contains("请休假申请人事审核")) {
				sql = sql2;
			}
			if (role_name.contains("请休假分管领导审核")) {
				sql = sql2 + sql3;

				if (employee.getName().equals("孙宇艺")) {
					sql = sql2 + sql3 + " union " + sql2_1 + " and b.dep_id in('100025','100048') ";
				}
				if (employee.getName().equals("韩绍义")) {
					sql = sql2 + sql3 + " union " + sql2_1 + " and b.dep_id ='100030' ";
				}
			}
			if (role_name.contains("院长")) {
				sql = sql2;
			}
			if (StringUtil.isNotEmpty(sql) && !sql.contains("order by")) {
				sql += " order by people_sign_date desc";
			}

			System.out.println("已办理查询语句====================" + sql);
			if (StringUtil.isNotEmpty(sql)) {
				// 获取connection
				Connection conn = Factory.getDB().getConnetion();
				Statement queryStatement = conn.createStatement();
				ResultSet executeQuery = queryStatement.executeQuery(sql);

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (executeQuery.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", executeQuery.getString("id"));
					map.put("people_name", executeQuery.getString("people_name"));
					map.put("leave_type", executeQuery.getString("leave_type"));
					map.put("start_date", executeQuery.getString("start_date"));
					map.put("end_date", executeQuery.getString("end_date"));
					map.put("apply_status", executeQuery.getString("apply_status"));
					map.put("days", executeQuery.getString("leave_count1"));
					map.put("dep_name", executeQuery.getString("dep_name"));
					map.put("people_sign_date", executeQuery.getString("people_sign_date"));
					list.add(map);
				}

				Factory.getDB().freeConnetion(conn);
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取我申请的请休假数据
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-08-21 下午14:18:00
	 */
	public static List<Map<String, Object>> getMyLeaveList() {
		try {
			CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
			User user = (User) curUser.getSysUser();
			Employee employee = (Employee) user.getEmployee();

			String sql = " select b.id,b.people_name,c2.name as leave_type,to_char(b.start_date,'yyyy-MM-dd') as start_date, "
					+ "to_char(b.end_date,'yyyy-MM-dd') as end_date, c.name as apply_status, b.leave_count1,b.dep_name,"
					+ "to_char(b.people_sign_date,'YYYY-MM-DD HH24:MI') as people_sign_date from TJY2_RL_LEAVE b, "
					+ "(select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_BG_LEAVE_STATUS') c,"
					+ "(select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 where c.id = c1.code_table_id and c.code = 'TJY2_RL_LEAVE_TYPE') c2 "
					+ "where b.people_id = '" + employee.getId()
					+ "' and b.apply_status=c.value(+) and b.leave_type=c2.value(+) order by b.people_sign_date desc";

			System.out.println("我的申请查询语句====================" + sql);

			// 获取connection
			Connection conn = Factory.getDB().getConnetion();
			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while (executeQuery.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", executeQuery.getString("id"));
				map.put("people_name", executeQuery.getString("people_name"));
				map.put("leave_type", executeQuery.getString("leave_type"));
				map.put("start_date", executeQuery.getString("start_date"));
				map.put("end_date", executeQuery.getString("end_date"));
				map.put("apply_status", executeQuery.getString("apply_status"));
				map.put("days", executeQuery.getString("leave_count1"));
				map.put("dep_name", executeQuery.getString("dep_name"));
				map.put("people_sign_date", executeQuery.getString("people_sign_date"));
				list.add(map);
			}

			Factory.getDB().freeConnetion(conn);
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
