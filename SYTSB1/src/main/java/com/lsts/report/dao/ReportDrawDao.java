package com.lsts.report.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.IdFormat;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.report.bean.ReportDraw;

/**
 * 报告领取记录数据处理对象
 * @ClassName ReportDrawDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-25 下午03:49:00
 */
@Repository("reportDrawDao")
public class ReportDrawDao extends EntityDaoImpl<ReportDraw> {

	/**
	 * 获取报告领取记录
	 * @param inspectionInfoId -- 报检信息ID
	 * @return 
	 * @author GaoYa
	 * @date 2014-03-04 下午03:18:00
	 */
	@SuppressWarnings("unchecked")
	public ReportDraw queryByInspectionInfoId(String inspectionInfoId){
		ReportDraw reportDraw = null;
    	try {
    		if (StringUtil.isNotEmpty(inspectionInfoId)) {
    			String hql = "from ReportDraw d where d.inspectionInfo.id=?";
    			List list = this.createQuery(hql, inspectionInfoId).list();
    			if(!list.isEmpty()){
    				reportDraw = (ReportDraw)list.get(0);
    			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return reportDraw;
	}

	public ReportDraw getInspectionInfoByid(String inspectionInfoId) {
		String hql = "from ReportDraw d where d.inspectionInfo.id=?";
		List list = this.createQuery(hql, inspectionInfoId).list();
		if(list==null)
		{
			return null;
		}else
		{
			if(list.get(0)==null)
			{
				return null;
			}else
			{
				return (ReportDraw) list.get(0);
			}
		}
	}

	public String getInspectionBytableId(String id) {
		String sql="select infoids from TZSB_WITH_TWOWEI where id=?";
		Object obj=this.createSQLQuery(sql, id).uniqueResult();
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportDraw> getInfos(
			String com_name,String pulldown_op) {
		List<ReportDraw> list = new ArrayList<ReportDraw>();
		String hql = "from ReportDraw i where i.job_unit like '%"+com_name+"%' and i.data_status != '99'";
		if(StringUtil.isNotEmpty(pulldown_op)) {
			hql = "from ReportDraw i where i.job_unit like '%"+com_name+"%' and i.pulldown_op like '%"+pulldown_op+"%' and i.data_status != '99'";
		}
		list = this.createQuery(hql).list();
		return list;
	}
	/**
	 * 根据领取人姓名获取领取人信息
	 * @param com_name
	 * @param pulldown_op
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ReportDraw> getInfosByName(
			String pulldown_op) {
		List<ReportDraw> list = new ArrayList<ReportDraw>();
		String hql = "from ReportDraw i where i.pulldown_op like '%"+pulldown_op+"%' and i.data_status != '99'";
		list = this.createQuery(hql).list();
		return list;
	}
}
