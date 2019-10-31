package com.lsts.gis.quality.dao;

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
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.khnt.base.Factory;
import com.khnt.pub.codetable.service.CodeTableCache;
import com.khnt.utils.StringUtil;
import com.lsts.gis.inspect.bean.DeviceTjDTO;
import com.lsts.gis.quality.bean.QualityTjDTO;
import com.lsts.inspection.dao.InspectionInfoDao;

/**
 * 统计Dao
 *
 * @author zpl
 *
 * @date
 */
@Repository("qualityTjDao")
public class QualityTjDao extends HibernateDaoSupport {

	@Autowired
	private InspectionInfoDao inspectionInfoDao;

	// 统计
	public Map<String, Object> initCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取任务书数量
		String rwsTotalSql = "select count(*) from TJY2_QUALTTY_ALLOT t ";
		String rwsYSql = "select count(*) from TJY2_QUALTTY_ALLOT t where t.status = 'YWC' ";
		
		map.put("rws_y", inspectionInfoDao.createSQLQuery(rwsYSql).list().get(0));//已完成
		map.put("rws_t", inspectionInfoDao.createSQLQuery(rwsTotalSql).list().get(0));//总数
		// 获取内控到期数量
		String nkdqTotalSql = "select count(*) from ( "+
							"select t.id inspection_info_id,t.report_sn,t.report_com_name,d.make_units_name,t.enter_time, "+
							"       t.flow_note_name, t.enter_op_name,t.flow_note_id,t.is_user_defined,t.check_unit_id, "+
							"       r.id report_id,d.device_area_code, "+
							"       (case when t.flow_note_name = '报告归档' then '' "+
							"         else to_char(trunc(sysdate - t.enter_time)) end) days, "+
							"       (case when t.flow_note_name in ('报告归档', '报告领取', '打印报告') or t.flow_note_name is null then '' "+
							"         else to_char(trunc(sysdate - t.enter_time)) end) qf_days, "+
							"       (select r.tianshu from TJY2_REPORT_YJSZ r "+
							"         where r.flow = '2' and r.flows = '5' and r.state = '1') nk_days "+
							"  from tzsb_inspection_info t, base_device_document d, base_reports r "+
							" where d.id(+) = t.fk_tsjc_device_document_id and t.REPORT_TYPE = r.id(+) and t.data_status != '99' "+
							") t";
		String nkdqCSql = nkdqTotalSql+ " where t.qf_days - t.nk_days > 0 ";
		map.put("nkdq_c", inspectionInfoDao.createSQLQuery(nkdqCSql).list().get(0));//超期
		map.put("nkdq_t", inspectionInfoDao.createSQLQuery(nkdqTotalSql).list().get(0));//总数
		// 不合格报告数量
		String sql2 = "select (case when t1.inspection_conclusion like '%复检%' then 'f' "+
						"        when t1.inspection_conclusion like '%不合格%' then 'b' else 'w' end) s "+
						"  from BASE_DEVICE_DOCUMENT t, "+
						"       TZSB_INSPECTION_INFO t1, "+
						"       TZSB_INSPECTION t2, "+
						"       SYS_ORG o, "+
						"       BASE_REPORTS r, "+
						"       base_administrative_divisions t5, "+
						"       TZSB_REPORT_ERROR_INFO t7, "+
						"       (select c1.* "+
						"          from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "+
						"         where c.id = c1.code_table_id "+
						"           and c.code = 'device_classify') t6, "+
						"       (select c1.* "+
						"          from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "+
						"         where c.id = c1.code_table_id "+
						"           and c.code = 'PAYMENT_STATUS') t8 "+
						" where t.id(+) = t1.fk_tsjc_device_document_id "+
						"   and t1.CHECK_UNIT_ID = o.id(+) "+
						"   and t1.FK_INSPECTION_ID = t2.id(+) "+
						"   and t1.REPORT_TYPE = r.id(+) "+
						"   and t.device_area_code = t5.regional_code(+) "+
						"   and t1.id = t7.new_info_id(+) "+
						"   and t.device_sort_code = t6.value(+) "+
						"   and t1.fee_status = t8.value(+) "+
						"   and t1.data_status = '1' ";
		String bhg_b = "select count(*) from ("+sql2+") where s = 'b'";
		String bhg_f = "select count(*) from ("+sql2+") where s = 'f'";
		map.put("bhg_b", inspectionInfoDao.createSQLQuery(bhg_b).list().get(0));
		map.put("bhg_f", inspectionInfoDao.createSQLQuery(bhg_f).list().get(0));
		// 获取软件任务书数量
		String sql3 = "select s,count(s) c from (select DECODE(t.zlb_desc,'已完成',1,0) s from FUNCTION_TASK_INFO t where t.data_status != '99') group by s ";
		List rjrws = inspectionInfoDao.createSQLQuery(sql3).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		for (Object obj : rjrws) {
			Map<String, Object> ct = (Map<String, Object>)obj;
			if("0".equals(ct.get("S").toString())){
				map.put("rjrws_n", ct.get("C"));
			}else if("1".equals(ct.get("S").toString())){
				map.put("rjrws_y", ct.get("C"));
			}
		}
		
		// 获取体系文件数量
		String sql4 = "select count(t.id) from  TJY2_QUALITY_MANAGER_FILES t where t.status not in ('9','5') ";
		List<Object> txwj = inspectionInfoDao.createSQLQuery(sql4).list();
		map.put("txwj", txwj);
		// 获取纠错报告数量
		String sql5 = "select s,count(*) c from  (select (case when t1.data_status= '01' then 'wwc' when (t1.data_status= '02') then 'wc' when (t1.data_status= '03') then 'wc' else 's' end) s from TZSB_REPORT_ERROR_INFO t,TZSB_REPORT_ERROR_RECORD t1 "
				+ " where t.FK_REPORT_ERROR_RECORD_ID=t1.id and t.data_status!='99'  )group by s";
		List jcbg = inspectionInfoDao.createSQLQuery(sql5).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		int jcbg_t = 0;
		int jcbg_n = 0;
		for (Object obj : jcbg) {
			Map<String, Object> ct = (Map<String, Object>)obj;
			if("wwc".equals(ct.get("S").toString())){
				jcbg_t += Integer.parseInt(ct.get("C").toString());
			}else if("wc".equals(ct.get("S").toString())){
				jcbg_t += Integer.parseInt(ct.get("C").toString());
				jcbg_n += Integer.parseInt(ct.get("C").toString());
			}
		}
		map.put("jcbg_n", jcbg_n);
		map.put("jcbg_t", jcbg_t);
		return map;

	}

	// 获取任务书列表数据
	public List<QualityTjDTO> initRwsList(String file_name) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取任务书
		String sql = "select t.task_sn,t.item_name,t.register_date,t.status,t.id from TJY2_QUALTTY_ALLOT t ";// where
																												// t.REGISTER_DATE>=to_date(to_char(sysdate,'yyyy')||'0101
																												// 00:00:00','yyyymmdd
																												// hh24:mi:ss')
		if (file_name != null && !file_name.equals("")) {//
			sql = sql + "  where t.item_name like '%" + file_name + "%' ";
		}
		sql = sql + " order by t.register_date desc ";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			int num = 0;
			if (list2.size() < 20 || (file_name != null && !file_name.equals(""))) {
				num = list2.size();
			} else {
				num = 20;
			}
			for (int j = 0; j < num; j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String sn = (vobjs[0] == null) ? "" : vobjs[0].toString();// 编号
				String name = (vobjs[1] == null) ? "" : vobjs[1].toString();// 任务书名称
				String date = (vobjs[2] == null) ? "" : vobjs[2].toString();// 日期
				String status = (vobjs[3] == null) ? "" : vobjs[3].toString();// 状态
				String id = vobjs[4].toString();// id
				status = getByCode("TJY2_ZLGL_RWXF", status);
				tj.setNo(sn);
				tj.setDate(date);
				tj.setStatus(status);
				tj.setName(name);
				tj.setType("rws");
				tj.setId(id);
				list.add(tj);
			}
		}

		return list;

	}

	// 获取内控到期列表数据
	public List<QualityTjDTO> initNkdqList(String file_name) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取内控到期
		String sql = "select t1.report_sn,t1.report_com_name,t1.enter_time,t1.flow_note_name,t1.inspection_info_id"
				+ "  from (select t.id inspection_info_id,  " + "                t.report_sn,  "
				+ "                t.report_com_name,  " + "                  d.make_units_name,  "
				+ "                 t.enter_time,  " + "                  t.flow_note_name,  "
				+ "                 t.enter_op_name,  " + "                 t.flow_note_id,  "
				+ "                 t.is_user_defined,  " + "                 t.check_unit_id,  "
				+ "                 r.id report_id,  " + "                 d.device_area_code,  "
				+ "                  (case  " + "                    when t.flow_note_name = '报告归档' then  "
				+ "                    ''  " + "                   else  "
				+ "                    to_char(trunc(sysdate - t.enter_time))  " + "                  end) days,  "
				+ "                   (case  "
				+ "                     when t.flow_note_name in ('报告归档', '报告领取', '打印报告') or  "
				+ "                          t.flow_note_name is null then  " + "                    ''  "
				+ "                   else  " + "                    to_char(trunc(sysdate - t.enter_time))  "
				+ "                end) qf_days,  " + "                 (select r.tianshu  "
				+ "                    from TJY2_REPORT_YJSZ r  " + "                    where r.flow = '2'  "
				+ "                      and r.flows = '5'  " + "                     and r.state = '1') nk_days  "
				+ "            from tzsb_inspection_info t,  " + "                 base_device_document d,  "
				+ "                 base_reports         r  "
				+ "           where d.id(+) = t.fk_tsjc_device_document_id  "
				+ "             and t.REPORT_TYPE = r.id(+)  "
				+ "            and t.data_status != '99') t1 where t1.qf_days - t1.nk_days > 0 ";// and
																									// t1.enter_time>=
																									// to_date(to_char(sysdate,'yyyy')||'0101
																									// 00:00:00','yyyymmdd
																									// hh24:mi:ss')

		if (file_name != null && !file_name.equals("")) {// 查询
			sql = sql + "  and t1.report_com_name like '%" + file_name + "%' ";
		}
		sql = sql + " order by t1.enter_time desc nulls last";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			int num = 0;
			if (list2.size() < 20 || (file_name != null && !file_name.equals(""))) {
				num = list2.size();
			} else {
				num = 20;
			}
			for (int j = 0; j < num; j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String sn = (vobjs[0] == null) ? "" : vobjs[0].toString();// 编号
				String name = (vobjs[1] == null) ? "" : vobjs[1].toString();// 任务书名称
				String date = (vobjs[2] == null) ? "" : vobjs[2].toString();// 日期
				String status = (vobjs[3] == null) ? "" : vobjs[3].toString();// 状态
				String id = vobjs[4].toString();// id
				tj.setNo(sn);
				tj.setDate(date);
				tj.setStatus(status);
				tj.setName(name);
				tj.setType("nkdq");
				tj.setId(id);
				list.add(tj);
			}
		}

		return list;

	}

	// 获取不合格列表数据
	public List<QualityTjDTO> initBhgList(String file_name) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取不合格
		String sql = "select t1.report_sn,t1.report_com_name,t1.enter_time,t1.flow_note_name "
				+ "      from BASE_DEVICE_DOCUMENT t, " + "      TZSB_INSPECTION_INFO t1, "
				+ "      TZSB_INSPECTION t2, " + "      SYS_ORG o, " + "      BASE_REPORTS r, "
				+ "      base_administrative_divisions t5, " + "      TZSB_REPORT_ERROR_INFO t7, "
				+ "     (select c1.* " + "    from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 "
				+ "       			   where c.id = c1.code_table_id "
				+ "           and c.code = 'device_classify') t6, " + "     (select c1.* "
				+ "       from PUB_CODE_TABLE c, PUB_CODE_TABLE_VALUES c1 " + "       where c.id = c1.code_table_id "
				+ "         and c.code = 'PAYMENT_STATUS') t8 " + "where t.id(+) = t1.fk_tsjc_device_document_id "
				+ "  and t1.CHECK_UNIT_ID = o.id(+) " + "  and t1.FK_INSPECTION_ID = t2.id(+) "
				+ "  and t1.REPORT_TYPE = r.id(+) " + "  and t.device_area_code = t5.regional_code(+) "
				+ "  and t1.id = t7.new_info_id(+) " + "  and t.device_sort_code = t6.value(+) "
				+ "  and t1.fee_status = t8.value(+) " + "  and t1.data_status='1' "
				+ "  and t1.inspection_conclusion='不合格' ";
		if (file_name != null && !file_name.equals("")) {// 查询
			sql = sql + "  and t1.report_com_name like '%" + file_name + "%' ";
		}
		/*
		 * +
		 * "and t1.ADVANCE_TIME>= to_date(to_char(sysdate,'yyyy')||'0101  00:00:00','yyyymmdd hh24:mi:ss')"
		 * ;
		 */
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			int num = 0;
			if (list2.size() < 20 || (file_name != null && !file_name.equals(""))) {
				num = list2.size();
			} else {
				num = 20;
			}
			for (int j = 0; j < num; j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String sn = (vobjs[0] == null) ? "" : vobjs[0].toString();// 编号
				String name = (vobjs[1] == null) ? "" : vobjs[1].toString();// 任务书名称
				String date = (vobjs[2] == null) ? "" : vobjs[2].toString();// 日期
				String status = (vobjs[3] == null) ? "" : vobjs[3].toString();// 状态
				tj.setNo(sn);
				tj.setDate(date);
				tj.setStatus(status);
				tj.setName(name);
				tj.setType("bhg");
				list.add(tj);
			}
		}

		return list;

	}

	// 获取软件任务书列表数据
	public List<QualityTjDTO> initRjrwsList(String file_name) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取软件任务书
		String sql = "select t.sn,t.TASK_NAME,t.ADVANCE_DATE,t.DATA_STATUS,t.id from FUNCTION_TASK_INFO t where t.data_status != '99' ";// where
																																		// t.REGISTER_DATE>=to_date(to_char(sysdate,'yyyy')||'0101
																																		// 00:00:00','yyyymmdd
																																		// hh24:mi:ss')

		if (file_name != null && !file_name.equals("")) {// 查询
			sql = sql + "  and t.TASK_NAME like '%" + file_name + "%' ";
		}
		sql = sql + "order by t.ADVANCE_DATE desc";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			int num = 0;
			if (list2.size() < 20 || (file_name != null && !file_name.equals(""))) {
				num = list2.size();
			} else {
				num = 20;
			}
			for (int j = 0; j < num; j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String sn = (vobjs[0] == null) ? "" : vobjs[0].toString();// 编号
				String name = (vobjs[1] == null) ? "" : vobjs[1].toString();// 任务书名称
				String date = (vobjs[2] == null) ? "" : vobjs[2].toString();// 日期
				String status = (vobjs[3] == null) ? "" : vobjs[3].toString();// 状态
				String id = vobjs[4].toString();// id
				tj.setNo(sn);
				tj.setDate(date);
				status = getByCode("FUNCTION_TASK_INFO", status);
				tj.setStatus(status);
				tj.setName(name);
				tj.setType("rjrws");
				tj.setId(id);
				list.add(tj);
			}
		}

		return list;

	}

	// 获取纠错报告列表数据
	public List<QualityTjDTO> initJcbgList(String file_name) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取纠错报告
		String sql = "select t.report_sn,t.error_desc,t1.DEAL_DATE,t1.DATA_STATUS,t.id from TZSB_REPORT_ERROR_INFO t,TZSB_REPORT_ERROR_RECORD t1 "
				+ "       where t.FK_REPORT_ERROR_RECORD_ID=t1.id and t.data_status!='99'  and t.error_desc  is not null "
				+ "      and t1.DEAL_DATE>= to_date(to_char(sysdate,'yyyy')||'0101  00:00:00','yyyymmdd hh24:mi:ss') ";
		if (file_name != null && !file_name.equals("")) {// 查询
			sql = sql + "  and t.error_desc like '%" + file_name + "%' ";
		}
		sql = sql + " order by t1.DEAL_DATE desc";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			int num = 0;
			if (list2.size() < 20 || (file_name != null && !file_name.equals(""))) {
				num = list2.size();
			} else {
				num = 20;
			}
			for (int j = 0; j < num; j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String sn = (vobjs[0] == null) ? "" : vobjs[0].toString();// 编号
				String name = (vobjs[1] == null) ? "" : vobjs[1].toString();// 任务书名称
				String date = (vobjs[2] == null) ? "" : vobjs[2].toString();// 日期
				String status = (vobjs[3] == null) ? "" : vobjs[3].toString();// 状态
				String id = vobjs[4].toString();// id
				tj.setNo(sn);
				tj.setDate(date);
				status = getByCode("REPORT_ERROR_STATUS", status);
				tj.setStatus(status);
				tj.setName(name);
				tj.setType("jcbg");
				tj.setId(id);
				list.add(tj);
			}
		}

		return list;

	}

	// 根据报告编号查询报告
	public List<QualityTjDTO> getByReportNo(String report_sn) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取查询报告设备id
		String sql = "select t.fk_tsjc_device_document_id,t.id from tzsb_inspection_info t where t.data_status!='99' and t.report_sn like '%"
				+ report_sn + "%'";
		List<Object> listt = inspectionInfoDao.createSQLQuery(sql).list();
		String device_id = "";
		if (listt.size() > 1 || listt.size() == 0) {
			return null;
		} else {
			Object[] vobjs = (Object[]) listt.get(0);
			device_id = vobjs[0].toString();
		}

		// 通过查询到的设备id 查询历年该设备的检验情况
		String sql1 = "select t.id,t.report_sn,t.ADVANCE_TIME,t.LAST_CHECK_TIME,t.CHECK_OP_NAME,t.inspection_conclusion,t.REPORT_TYPE,t.to_swf,t.export_pdf,t.IS_CUR_ERROR from tzsb_inspection_info t where t.data_status!='99' and t.fk_tsjc_device_document_id like '%"
				+ device_id + "%'  order by report_sn desc";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql1).list();
		if (list2.size() > 0) {
			int i = 0;
			if (list2.size() > 3) {// 判断list长度是否大于3 只取最近3年的检验情况
				i = 3;
			} else {
				i = list2.size();
			}
			for (int j = 0; j < i; j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String id = vobjs[0].toString();// id
				String report_sns = vobjs[1].toString();// 报告编号
				String advance_time = vobjs[2].toString();// 检验日期
				String last_check_time = vobjs[3].toString();// 下次检验
				String check_op_name = vobjs[4].toString();// 参与人员
				String inspection_conclusion = vobjs[5].toString();// 检验结论
				String report_id = vobjs[6].toString();// 检验结论
				String to_swf = (vobjs[7] == null) ? "" : vobjs[7].toString();// 检验结论
				String export_pdf = (vobjs[8] == null) ? "" : vobjs[8].toString();// 检验结论
				String is_cur_error = (vobjs[9] == null) ? "" : vobjs[9].toString();// 是否纠错
				if (is_cur_error.equals("1") || is_cur_error.equals("9")) {
					String sql2 = "select t1.data_status,t.mdy_date from TZSB_REPORT_ERROR_INFO t,TZSB_REPORT_ERROR_RECORD t1 "
							+ "       where t.FK_REPORT_ERROR_RECORD_ID=t1.id and t.data_status!='99'  and t.error_desc  is not null and t.report_sn='"
							+ report_sns + "'" + " order by t.mdy_date desc";
					List<Object> list3 = inspectionInfoDao.createSQLQuery(sql2).list();
					if (list3.size() > 0) {
						Object[] vobjss = (Object[]) list3.get(0);// 考虑多次纠错
																	// 取最新一次
						String type = vobjss[0].toString();// 状态
						if (type.equals("01")) {
							is_cur_error = "正在纠错";
						}
						if (type.equals("02") || type.equals("03")) {
							is_cur_error = "已纠错";
						}
					} else {
						is_cur_error = "";
					}
				}
				tj.setId(id);
				tj.setReport_sn(report_sns);
				tj.setAdvance_time(advance_time);
				tj.setLast_check_time(last_check_time);
				tj.setCheck_op_name(check_op_name);
				tj.setInspection_conclusion(inspection_conclusion);
				tj.setReport_id(report_id);
				tj.setTo_swf(to_swf);
				tj.setIs_cur_error(is_cur_error);
				tj.setExport_pdf(export_pdf);
				list.add(tj);
			}
		}

		return list;

	}

	// 根据报告编号查询原始记录修订记录
	public List<QualityTjDTO> getByReportNoYSJL(String report_sn) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取记录
		try {

			String sql = "select t.table_name,t.old_value,t.new_value,t.op_user_name,t.op_time from SYS_DATA_MDY_LOG t where  t.business_id like '%"
					+ report_sn + "%'";
			List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
			if (list2.size() > 0) {
				for (int j = 0; j < list2.size(); j++) {
					QualityTjDTO tj = new QualityTjDTO();
					Object[] vobjs = (Object[]) list2.get(j);
					String table_name = vobjs[0].toString();// 更改类型
					String old_value = (vobjs[1] == null) ? "" : vobjs[1].toString();// 修改之前内容
					String new_value = (vobjs[2] == null) ? "" : vobjs[2].toString();// 修改之后内容
					String op_user_name = vobjs[3].toString();// 修改人
					String op_time = vobjs[4].toString();// 修改时间
					tj.setTable_name(table_name);
					;
					tj.setOld_value(old_value);
					tj.setNew_value(new_value);
					tj.setOp_user_name(op_user_name);
					tj.setOp_time(op_time);

					list.add(tj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// 根据报告编号查询不合格项目
	public List<QualityTjDTO> getByReportNoBhg(String report_sn) {
		List<QualityTjDTO> list = new ArrayList<QualityTjDTO>();
		// 获取记录
		String sql = "select t.item_name,t.item_value,t1.report_name from TZSB_REPORT_ITEM_VALUE t,BASE_REPORTS t1 where t.fk_report_id=t1.id and t.item_value like '%不合格%' and t.item_name like '%D\\_%' escape '\\' and t.fk_inspection_info_id='"
				+ report_sn + "'";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			for (int j = 0; j < list2.size(); j++) {
				QualityTjDTO tj = new QualityTjDTO();
				Object[] vobjs = (Object[]) list2.get(j);
				String item_name = vobjs[0].toString();// 报告列名
				String item_value = vobjs[1].toString();// 报告列值
				String report_name = vobjs[2].toString();// 报告名称
				tj.setItem_name(item_name);
				;
				tj.setItem_value(item_value);
				tj.setReport_name(report_name);

				list.add(tj);
			}
		}

		return list;

	}

	public String getByCode(String code, String value) {
		String name = "";
		String sql = "select t1.name,t1.value from PUB_CODE_TABLE t,PUB_CODE_TABLE_VALUES t1 where  t.id=t1.code_table_id and t.code='"
				+ code + "'";
		List<Object> list2 = inspectionInfoDao.createSQLQuery(sql).list();
		if (list2.size() > 0) {
			for (int j = 0; j < list2.size(); j++) {
				Object[] vobjs = (Object[]) list2.get(j);

				String item_name = vobjs[0].toString();// 报告列名
				String item_value = vobjs[1].toString();// 报告列值
				if (item_value.equals(value)) {
					name = item_name;
				}
			}
		}

		return name;
	}
}