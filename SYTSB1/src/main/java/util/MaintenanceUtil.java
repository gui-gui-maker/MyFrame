package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.khnt.base.Factory;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.scts.maintenance.bean.MaintenanceInfoDTO;

public class MaintenanceUtil {
	
	/**
	 * 根据业务id获取运维日志打印内容
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2018-09-11 上午11:31:00
	 */
	public static MaintenanceInfoDTO getPrintContent(String id) {
		MaintenanceInfoDTO maintenanceInfoDTO = new MaintenanceInfoDTO();
		try {
			String sql = "select t.*,c.name as infotype from TZSB_MAINTENANCE_INFO t,"
					+ "(select c1.* from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "
					+ "where c.id = c1.code_table_id and c.code = 'MAINTENANCE_TYPE') c " + "where t.info_type=c.value(+) and id = '" + id + "'";

			// 获取connection
			Connection conn = Factory.getDB().getConnetion();
			Statement queryStatement = conn.createStatement();
			ResultSet executeQuery = queryStatement.executeQuery(sql);

			while (executeQuery.next()) {
				maintenanceInfoDTO.setId(executeQuery.getString("id"));
				maintenanceInfoDTO.setSn(executeQuery.getString("sn"));
				maintenanceInfoDTO.setInfo_type(executeQuery.getString("infotype"));
				maintenanceInfoDTO.setFunc_name(executeQuery.getString("func_name"));
				maintenanceInfoDTO.setPro_desc(executeQuery.getString("pro_desc"));
				maintenanceInfoDTO.setAdvance_user_name(executeQuery.getString("advance_user_name"));
				maintenanceInfoDTO.setOrg_name(executeQuery.getString("org_name"));
				maintenanceInfoDTO.setAdvance_date(DateUtil.convertStringToDate("yyyy-MM-dd", executeQuery.getString("advance_date")));
				maintenanceInfoDTO.setProve_user_name(executeQuery.getString("prove_user_name"));

				if(StringUtil.isNotEmpty(executeQuery.getString("prove_type"))) {
					if ("0".equals(executeQuery.getString("prove_type"))) {
						maintenanceInfoDTO.setProve_type("处理");
					} else {
						maintenanceInfoDTO.setProve_type("延期");
					}
				}
				maintenanceInfoDTO.setExpect_finish_date(DateUtil.convertStringToDate("yyyy-MM-dd", executeQuery.getString("expect_finish_date")));
				maintenanceInfoDTO.setProve_remark(executeQuery.getString("prove_remark"));
				maintenanceInfoDTO.setDevelop_user_name(executeQuery.getString("develop_user_name"));
				maintenanceInfoDTO.setDevelop_end_date(DateUtil.convertStringToDate("yyyy-MM-dd", executeQuery.getString("develop_end_date")));
				maintenanceInfoDTO.setTest_user_name(executeQuery.getString("test_user_name"));
				maintenanceInfoDTO.setTest_date(DateUtil.convertStringToDate("yyyy-MM-dd", executeQuery.getString("test_date")));
				maintenanceInfoDTO.setDevelop_desc(executeQuery.getString("develop_desc"));
				maintenanceInfoDTO.setWrite_user_name(executeQuery.getString("write_user_name"));
				maintenanceInfoDTO.setWrite_date(DateUtil.convertStringToDate("yyyy-MM-dd", executeQuery.getString("write_date")));
			}
			
			String sql2 = "select t.* from PUB_ATTACHMENT t where t.business_id = '"+id+"'";
			ResultSet executeQuery2 = queryStatement.executeQuery(sql2);
			String task_attach_id = "";
			String task_attach_name = "";
			String update_attach_id = "";
			String update_attach_name = "";
			String file_path = "";
			while (executeQuery2.next()) {
				String tz_category = executeQuery2.getString("tz_category");
				String file_type = executeQuery2.getString("file_type");
				if ("task".equals(tz_category)) {
					if (StringUtil.isNotEmpty(task_attach_id)) {
						task_attach_id += "、";
						task_attach_name += "、";
					}
					task_attach_id += executeQuery2.getString("id");
					task_attach_name += executeQuery2.getString("file_name");

				} else if ("update".equals(tz_category)) {
					if (StringUtil.isNotEmpty(update_attach_id)) {
						update_attach_id += "、";
						update_attach_name += "、";
					}
					update_attach_id += executeQuery2.getString("id");
					update_attach_name += executeQuery2.getString("file_name");
				}
				maintenanceInfoDTO.setTask_attach_id(task_attach_id);
				maintenanceInfoDTO.setTask_attach_name(task_attach_name);
				maintenanceInfoDTO.setUpdate_attach_id(update_attach_id);
				maintenanceInfoDTO.setUpdate_attach_name(update_attach_name);
				if(file_type.contains("image")) {
					if(StringUtil.isNotEmpty(file_path)) {
						file_path += ",";
					}
					file_path += executeQuery2.getString("file_path");
				}
				
			}
			//maintenanceInfoDTO.setAtta_file_paths(file_path);
			Factory.getDB().freeConnetion(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maintenanceInfoDTO;
	}
}
