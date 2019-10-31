package com.lsts.report.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.report.bean.ReportErrorRecord;
import com.lsts.report.bean.ReportErrorRecordDTO;
import com.lsts.report.bean.ReportErrorRecordInfo;

/**
 * 检验报告/证书不符合纠正流转数据处理对象
 * @ClassName ReportErrorRecordDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-09-23 11:03:00
 */
@Repository("reportErrorRecordsDao")
public class ReportErrorRecordDao extends EntityDaoImpl<ReportErrorRecord> {
	
	/**
	 * 根据报告业务ID查询报告明细列表
	 * 
	 * @param ids
	 * @return
	 * @author GaoYa
	 * @date 2015-01-23 下午05:26:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordDTO> queryReportInfos(String ids) throws Exception{
		List<ReportErrorRecordDTO> infoList = new ArrayList<ReportErrorRecordDTO>();
		ids = IdFormat.formatIdStr(ids);
		String sql = "select t.report_sn,t.flow_note_name,t.id"
				+ " from tzsb_inspection_info t where t.id in ("
				+ ids
				+ ")";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				ReportErrorRecordDTO reportErrorRecordDTO = new ReportErrorRecordDTO();
				reportErrorRecordDTO.setReport_sn(String.valueOf(obj[0]));
				String flow_note_name = String.valueOf(obj[1]);
				if("打印报告".equals(flow_note_name)){
					reportErrorRecordDTO.setReport_status("1");	// 1：已封存
				}else if("报告领取".equals(flow_note_name) || "报告归档".equals(flow_note_name)){
					reportErrorRecordDTO.setReport_status("2");	// 2：已打印
				}
				reportErrorRecordDTO.setInfo_id(String.valueOf(obj[2]));
				infoList.add(reportErrorRecordDTO);
			}
		}
		return infoList;
	}
	
	/**
	 * 根据不符合报告ID查询不符合纠正流转表
	 * 
	 * @param report_error_id -- 不符合报告ID
	 * @return
	 * @author GaoYa
	 * @date 2015-11-11 下午03:26:00
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> queryRecordID(String report_error_id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();		
		String sql = "select t.id,t.error_dep_id "
				+ " from TZSB_REPORT_ERROR_RECORD t where t.fk_report_error_id = ?";
		List list = this.createSQLQuery(sql, report_error_id).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				map.put("record_id", String.valueOf(obj[0]));
				map.put("error_dep_id", String.valueOf(obj[1]));
				break;
			}
		}
		map.put("success", true);
		return map;
	}

	/**
	 * 获取不符合报告编号后三位序号
	 * @param sn_pre -- 不符合报告编号前缀
	 * @return 
	 * @author GaoYa
	 * @date 2015-09-11 上午11:03:00
	 */
	@SuppressWarnings("unchecked")
	public String queryNextSn(String sn_pre){
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num" +
        " from TZSB_REPORT_ERROR_RECORD t" +
        " where t.sn like '" + sn_pre + "%'";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			String curNum = list.get(0)+"";
			if (StringUtil.isNotEmpty(curNum)) {
				nextNum = getCountNum(Integer.parseInt(curNum)+1);
			}
		}
		return nextNum;
	}
	
	/**
	 * 根据业务ID查询报告不符合纠正流转情况
	 * 
	 * @param id -- 检验业务id
	 * @return
	 * @author GaoYa
	 * @date 2015-11-19 上午11:14:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecordDTO> queryReportErrors(String id) throws Exception{
		List<ReportErrorRecordDTO> infoList = new ArrayList<ReportErrorRecordDTO>();
		String sql = "select t2.id,t2.DATA_STATUS,t.report_sn"
				+ " from tzsb_inspection_info t,TZSB_REPORT_ERROR_INFO t1,TZSB_REPORT_ERROR_RECORD t2 " +
						" where t.id = t1.NEW_INFO_ID and t1.FK_REPORT_ERROR_RECORD_ID = t2.id and t.id = ? and t1.data_status!='99' and t2.data_status!='99'";
		List list = this.createSQLQuery(sql, id).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				ReportErrorRecordDTO reportErrorRecordDTO = new ReportErrorRecordDTO();
				reportErrorRecordDTO.setId(String.valueOf(obj[0]));
				reportErrorRecordDTO.setData_status(String.valueOf(obj[1]));
				reportErrorRecordDTO.setReport_sn(String.valueOf(obj[2]));
				infoList.add(reportErrorRecordDTO);
			}
		}
		return infoList;
	}
	
	/**
	 * 根据报告书编号查询报告不符合纠正记录
	 * @param report_sn -- 报告书编号
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-06-02 下午04:14:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorRecord> getInfos(String report_sn) {
		String hql = "from ReportErrorRecord i where i.report_sn like '%"+report_sn+"%' and i.data_status!='04'";
		List<ReportErrorRecord> list = this.createQuery(hql).list();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	// 生成3位序号
	private String getCountNum(int count_num){
        String nextNum = "";
       if((0 < count_num) && (count_num < 10))
    	   nextNum = "00" + count_num;
       if((9 < count_num) && (count_num < 100))
    	   nextNum = "0" + count_num;
       else if(count_num > 99)
    	   nextNum = String.valueOf(count_num);
       return  nextNum;
   }
	
}
