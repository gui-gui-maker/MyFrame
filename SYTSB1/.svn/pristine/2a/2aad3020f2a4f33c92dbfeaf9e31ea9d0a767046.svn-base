package com.lsts.report.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.report.bean.ReportError;
import com.lsts.report.bean.ReportErrorDTO;

/**
 * 不符合报告数据处理对象
 * @ClassName ReportErrorDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2015-11-04 10:03:00
 */
@Repository("reportErrorDao")
public class ReportErrorDao extends EntityDaoImpl<ReportError> {
	
	/**
	 * 根据报告业务ID查询报告明细列表
	 * 
	 * @param ids
	 * @return
	 * @author GaoYa
	 * @date 2015-11-04 11:12:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportErrorDTO> queryReportInfos(String ids) throws Exception{
		List<ReportErrorDTO> infoList = new ArrayList<ReportErrorDTO>();
		ids = IdFormat.formatIdStr(ids);
		String sql = "select t.report_sn,t.flow_note_name"
				+ " from tzsb_inspection_info t where t.id in ("
				+ ids
				+ ")";
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				ReportErrorDTO reportErrorDTO = new ReportErrorDTO();
				reportErrorDTO.setReport_sn(String.valueOf(obj[0]));
				String flow_note_name = String.valueOf(obj[1]);
				if("打印报告".equals(flow_note_name)){
					reportErrorDTO.setReport_status("1");	// 1：已封存
				}else if("报告领取".equals(flow_note_name) || "报告归档".equals(flow_note_name)){
					reportErrorDTO.setReport_status("2");	// 2：已打印
				}
				infoList.add(reportErrorDTO);
			}
		}
		return infoList;
	}

	/**
	 * 获取不符合报告编号后三位序号
	 * @param sn_pre -- 不符合报告编号前缀
	 * @return 
	 * @author GaoYa
	 * @date 2015-11-04 15:03:00
	 */
	@SuppressWarnings("unchecked")
	public String queryNextSn(String sn_pre){
		String nextNum = "";
		String sql = "select nvl(max(Substr(t.sn, length('" + sn_pre + "')+1)), '000') count_num" +
        " from TZSB_REPORT_ERROR t" +
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
	
	
	/**查找申请人手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> gettol(String userId) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id in (select t.employee_id from SYS_USER t where t.id=?)";
		List<Employee> list = createSQLQuery(sql,userId).list();
		return list;

	}
	
	/**
	 * 获取检验员待纠正的不符合报告数据列表
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2016-08-18 15:22:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportError> getInfos() {
		List<ReportError> list = new ArrayList<ReportError>();
		String hql = "from ReportError i where i.data_status = '03'";
		list = this.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 获取不符合编号获取不符合报告
	 * 
	 * @return
	 * @author GaoYa
	 * @date 2017-08-07 10:41:00
	 */
	public ReportError getInfosBySn(String sn, String report_sn) {
		ReportError reportError = null;
		String hql = "from ReportError i where i.sn = ? and i.report_sn=?";
		reportError = (ReportError)this.createQuery(hql, sn, report_sn).uniqueResult();
		return reportError;
	}
	
	/**查找申请人手机号
	 * @return
	 */
	public List getemployeeid(String userId) {
		String sql = "select * from EMPLOYEE where Id in (select t.employee_id from SYS_USER t where t.id=?)";
		List list = createSQLQuery(sql,userId).list();
		return list;

	}
}
