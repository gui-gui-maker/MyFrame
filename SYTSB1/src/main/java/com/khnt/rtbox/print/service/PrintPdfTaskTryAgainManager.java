package com.khnt.rtbox.print.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.rtbox.print.bean.PrintPdfTask;
import com.khnt.rtbox.print.dao.PrintPdfTaskDao;

/**
 * 尝试机制
 * <p>
 * 1.尝试次数不超过3次
 * </p>
 * <p>
 * 2.仅尝试timeout错误类
 * </p>
 * 
 * @author ZQ
 * 
 */
@Service("printPdfTaskTryAgainManager")
public class PrintPdfTaskTryAgainManager {
	@Autowired
	PrintPdfTaskDao printPdfTaskDao;
	@Autowired
	PrintPdfTaskManager printPdfTaskManager;
	static Log log = LogFactory.getLog(PrintPdfTaskTryAgainManager.class);

	@SuppressWarnings("unchecked")
	public void doPrint() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		StringBuffer sql = new StringBuffer();
		sql.append("  with aa as (  ");
		sql.append("  select b.* from   ");
		sql.append("  (select a.*,row_number() over( PARTITION BY  a.FK_INSPECTION_INFO_ID order by a.print_time desc nulls last) rnum from RT_PRINT_PDF_TASK a ) b where b.rnum=1   ");
		sql.append("  ),  ");
		sql.append("  bb as (  ");
		sql.append("  select distinct(fk_task_id) from RT_PRINT_PDF_LOG b where  b.RESULT!='0' and b.log like '%TimeoutError%'   ");
		sql.append(" )   ");
		sql.append(" select aa.* from  aa, bb where aa.id=bb.fk_task_id and  aa.try_count<=3  ");
		List<Map<String, Object>> list = this.printPdfTaskDao.createSQLQuery(sql.toString()).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		List<PrintPdfTask> tasks = new ArrayList<PrintPdfTask>();
		if (list != null && !list.isEmpty()) {
			log.info("printPdfTaskTryAgainManager 正在执行，已找到" + list.size() + "条需要重新打印的任务");
			for (Map<String, Object> map : list) {
				Map<String, String> lowerMap = new HashMap<String, String>();
				for (String key : map.keySet()) {
					Object v = map.get(key);
					if (v instanceof Date) {
						Date o = (Date) v;
						lowerMap.put(key.toLowerCase(), v == null ? null : sdf.format(o));
					} else if (v instanceof java.sql.Date) {
						java.sql.Date o = (java.sql.Date) v;
						lowerMap.put(key.toLowerCase(), v == null ? null : sdf.format(new Date(o.getTime())));
					} else {
						lowerMap.put(key.toLowerCase(), v == null ? null : String.valueOf(v));
					}

				}

				PrintPdfTask task = new PrintPdfTask();
				try {
					this.initTask(task, lowerMap);
					tasks.add(task);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			log.info("printPdfTaskTryAgainManager 装载完毕，开始重新打印。。。");
			for (PrintPdfTask task : tasks) {
				try {
					this.printPdfTaskManager.print(task, null);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
				}
			}
		} else {
			log.info("printPdfTaskTryAgainManager 已执行，没有找到需要重新打印的任务(重新打印需满足:1.尝试次数小于3;2.timeouterror)。。");

		}

	}

	private void initTask(PrintPdfTask task, Map<String, String> lowerMap) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		task.setId(lowerMap.get("id"));

		task.setFkInspectionInfoId(lowerMap.get("fk_inspection_info_id"));
		task.setReportSn(lowerMap.get("report_sn"));
		task.setReportType(lowerMap.get("report_type"));
		task.setReportTplId(lowerMap.get("report_tpl_id"));
		task.setReportTplNo(lowerMap.get("report_tpl_no"));
		task.setReportTplUrl(lowerMap.get("report_tpl_url"));
		task.setPdfAtt(lowerMap.get("pdf_att"));
		task.setReportDir(lowerMap.get("report_dir"));
		task.setPdfPath(lowerMap.get("pdf_path"));
		task.setCreateTime(sdf.parse(lowerMap.get("create_time")));
		task.setStatus(lowerMap.get("status"));
		task.setPrintTime(sdf.parse(lowerMap.get("print_time")));
		String _tryCount = lowerMap.get("try_count");
		int tryCount = 0;
		try {
			tryCount = Integer.parseInt(_tryCount);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		task.setTryCount(tryCount);
		task.setRemark(lowerMap.get("remark"));
		task.setInspectDate(lowerMap.get("inspect_date"));
		task.setPrintHost(lowerMap.get("print_host"));
	}
}
