package com.lsts.inspection.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportItemRecord;
import com.lsts.inspection.bean.ReportItemValue;
import com.lsts.inspection.bean.ReportRecordParse;



/**
 * 移动端原始记录检验项目解析表数据处理对象
 * @ClassName ReportRecordParseDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2016-06-27 上午11:14:00
 */
@Repository("reportRecordParseDao")
public class ReportRecordParseDao extends EntityDaoImpl<ReportRecordParse> {
	
	@Autowired
	ReportItemValueDao ivDao;

	/**
	 * 根据report_id获取原始记录检验项目解析信息
	 * @param report_id -- 报告ID
	 * @return 
	 * @author GaoYa
	 * @date 2016-06-27 上午11:15:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportRecordParse> getByReport_Id(String report_id) {
		List<ReportRecordParse> list = new ArrayList<ReportRecordParse>();
		String hql = "from ReportRecordParse t where t.fk_report_id=? and t.data_status = '0'";
		list = this.createQuery(hql, report_id).list();
		return list;
	}
	
	/**
	 * 根据report_id和检验项目代码获取原始记录检验项目解析信息
	 * @param report_id -- 报告ID
	 * @return 
	 * @author GaoYa
	 * @date 2016-06-27 上午11:15:00
	 */
	@SuppressWarnings("unchecked")
	public List<ReportRecordParse> getInfos(String report_id, String item_name) {
		List<ReportRecordParse> list = new ArrayList<ReportRecordParse>();
		String hql = "from ReportRecordParse t where t.fk_report_id=? and t.item_name=? and t.data_status = '0'";
		list = this.createQuery(hql, report_id, item_name).list();
		return list;
	}
	public List<ReportRecordParse> getParseListByReportId(String fk_report_id) {
		String hql = "from ReportRecordParse where fk_report_id = ? and data_status='0'" ;
		List<ReportRecordParse> pList =  this.createQuery(hql, fk_report_id).list();
		return pList;
	}
	
	public List<ReportRecordParse> getParsesByReportId(String fk_report_id) {
		String hql = "from ReportRecordParse where fk_report_id = ? and data_status!='99'" ;
		List<ReportRecordParse> pList =  this.createQuery(hql, fk_report_id).list();
		return pList;
	}
	
	public List<ReportRecordParse> getParseListByItemName(String fk_report_id, String record_item_name) {
		String hql = "from ReportRecordParse where fk_report_id = ? and recordItemName = ? and data_status='0'" ;
		List<ReportRecordParse> pList =  this.createQuery(hql, fk_report_id, record_item_name).list();
		return pList;
	}
	
	public List<ReportRecordParse> getParsesByItemName(String fk_report_id, String record_item_name) {
		String hql = "from ReportRecordParse where fk_report_id = ? and recordItemName = ? and data_status!='99'" ;
		List<ReportRecordParse> pList =  this.createQuery(hql, fk_report_id, record_item_name).list();
		return pList;
	}
	
	public List<ReportRecordParse> getParsesByReportItem(String fk_report_id, String report_item_name) {
		String hql = "from ReportRecordParse where fk_report_id = ? and reportItemName = ? and data_status='0'" ;
		List<ReportRecordParse> pList =  this.createQuery(hql, fk_report_id, report_item_name).list();
		return pList;
	}
	
	public String getFormuleFromParse(ReportRecordParse parse,InspectionInfo info ) throws KhntException {
		String formule = parse.getFormule();
		//如果没有配置公式则抛出异常
		if(StringUtil.isEmpty(formule)) {
			throw new KhntException("没有配置转换公式。报告书编号："+info.getReport_sn()+";项目名："+parse.getReportItemName());
		}
		return formule ;
	}
	
	
	
	//得到转换之后的报告书页码
	public String parsePageNo(String recordPageNo,String ParseReportPageNo) {
		//根据原始记录数据保存的页码值按照__进行拆分
		String[] rpn = recordPageNo.split("__");
		String afterParseReportPageNo = ParseReportPageNo;
		//如果拆分后长度等于，那么说明有复制页
		if(rpn.length==2) {
			//有复制就组装报告的页码
			afterParseReportPageNo = afterParseReportPageNo+"__"+rpn[1].replace(rpn[0]+"_", ParseReportPageNo+"_");
		}
		return afterParseReportPageNo ;
	}
	
	
	//获取一份原始记录的itemName列表（排重后）
	public List<String> getItemNameListFromItemRecode(String info_id){
		String sql = " select distinct item_name from TZSB_REPORT_ITEM_RECORD order by item_name asc   " ;
		return this.createSQLQuery(sql, info_id).list();
	}
	//保存转换后的报告书信息
	public void  saveReportItemValue(Map<String,String> infoMap) {
		//得到转换之后的报告书页码
		String recordPageNo = infoMap.get("RECORD_PAGE_NO");
		String ParseReportPageNo = infoMap.get("PARSE_REPORT_PAGE_NO");
		//转换页码
		String pageNo = "";
		if(StringUtil.isNotEmpty(recordPageNo) && StringUtil.isNotEmpty(ParseReportPageNo)){
			pageNo = parsePageNo(recordPageNo,ParseReportPageNo);
		}
		
		//取得各类参数信息
		String fkInfoId = infoMap.get("FK_INFO_ID");
		String fkReportId =  infoMap.get("FK_REPORT_ID");
		String codeExt = null;
		if(pageNo.split("__").length>1){
			codeExt = pageNo.split("__")[1];
		}
		String itemName =  infoMap.get("ITEM_NAME")+(codeExt==null?"":"__"+codeExt);
		String itemValue =  infoMap.get("ITEM_VALUE");
		//往报告书内容表插入转换后的内容作为报告书内容
		insertReportItemValue(fkReportId,itemName,itemValue,fkInfoId,pageNo);
	}
	
	//往报告书内容表插入转换后的内容作为报告书内容
	/*public void createReportItemValue(String fkInfoId, String fkReportId, String itemName , String itemValue, String pageNo) {
		ivDao.insertReportItemValue(StringUtil.getUUID(),
				fkReportId,itemName,
				itemValue,fkInfoId,pageNo);*/
		//下面的用bean直接保存，但是由于会延迟持久化，所以暂时用SQL方式保存
		/*ReportItemValue iv = new ReportItemValue();
		iv.setFk_inspection_info_id(fk_info_id);
		iv.setFk_report_id(fk_report_id);
		iv.setItem_name(item_name);
		iv.setItem_value(item_value);
		iv.setPage_no(page_no);
		ivDao.save(iv);*/
	/*}*/
	
	//我存，我存...
	public void insertReportItemValue( String fkReportId,
			 String itemName,  String itemValue,
			 String fkInfoId,String pageNo) 
	{
		//如果值是null，转成空
		String itemValueT = StringUtil.transformNull(itemValue);
		//保存报告项目
		ReportItemValue iv = new ReportItemValue();
		iv.setFk_report_id(fkReportId);
		iv.setFk_inspection_info_id(fkInfoId);
		iv.setItem_name(itemName);
		iv.setItem_value(itemValueT);
		iv.setPage_no(pageNo);
		ivDao.save(iv);
	}
	
	// 保存原始记录数据
	public void saveReportItemRecord(Map<String, String> infoMap) {
		// 得到转换之后的报告书页码
		String recordPageNo = infoMap.get("RECORD_PAGE_NO");
		String ParseReportPageNo = infoMap.get("PARSE_REPORT_PAGE_NO");
		// 转换页码
		String pageNo = "";
		if (StringUtil.isNotEmpty(recordPageNo) && StringUtil.isNotEmpty(ParseReportPageNo)) {
			pageNo = parsePageNo(recordPageNo, ParseReportPageNo);
		}

		// 取得各类参数信息
		String fkInfoId = infoMap.get("FK_INFO_ID");
		String fkReportId = infoMap.get("FK_REPORT_ID");
		String itemName = infoMap.get("ITEM_NAME");
		String itemValue = infoMap.get("ITEM_VALUE");
		// 往原始记录内容表插入内容
		insertReportItemRecord(fkReportId, itemName, itemValue, fkInfoId, pageNo);
	}
	
	public void insertReportItemRecord( String fkReportId,
			 String itemName,  String itemValue,
			 String fkInfoId,String pageNo) 
	{
		//如果值是null，转成空
		String itemValueT = StringUtil.transformNull(itemValue);
		//保存原始记录项目
		ReportItemRecord ir = new ReportItemRecord();
		ir.setFk_report_id(fkReportId);
		ir.setFk_inspection_info_id(fkInfoId);
		ir.setItem_name(itemName);
		ir.setItem_value(itemValueT);
		ir.setPage_no(pageNo);
		ivDao.save(ir);
	}
	
	//我删，我删...
	public void deleteReportItemValue(String fkInfoId) {
		//我删，我删...
		String hql = "delete ReportItemValue where fk_inspection_info_id = ? " ;
		ivDao.createQuery(hql, fkInfoId).executeUpdate();
	}
	

	public void deleteReportItems(String fkInfoId, String report_id, String item_name) {
		// 我删，我删...
		String hql = "delete ReportItemValue where fk_inspection_info_id = ? and fk_report_id = ? and item_name = ?";
		ivDao.createQuery(hql, fkInfoId, report_id, item_name).executeUpdate();
	}
	
	
	/**
	 * 获取流程id
	 * @author zy
	 *
	 */
	public String getFlowId(String checkType,String orgId,String reportId){
		String sql="select flow_id from BASE_UNIT_FLOW t1 ,flow_service_config t2 "+
		" where t1.fk_flow_id=t2.id and t1.check_type ='"+checkType+"' and t1.fk_report_id='"+reportId+"' ";
		Object oFlowId = this.createSQLQuery(sql.toString()).uniqueResult();
		if(oFlowId==null){
			return null;
		}
		return oFlowId.toString();
	}
	/**
	 * 获取流程id
	 * @author guido 2018.10.09
	 * param isMobile 标识是否是移动端 
	 */
	public String getFlowId(String checkType,String orgId,String reportId,boolean isMobile){
		String sql="select flow_id from BASE_UNIT_FLOW t1 ,flow_service_config t2 "+
		" where t1.fk_flow_id=t2.id and t1.check_type ='"+checkType+"' and t1.fk_report_id='"+reportId+"' ";
		Object oFlowId = this.createSQLQuery(sql.toString()).uniqueResult();
		if(oFlowId==null){
			return null;
		}
		return oFlowId.toString();
	}
	
	
	public List<ReportRecordParse> getParseListByIds(String ids) {
		List<ReportRecordParse> list = new ArrayList<ReportRecordParse>();
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			String hql = "from ReportRecordParse where id = ? " ;
			ReportRecordParse parse = (ReportRecordParse)this.createQuery(hql, idArr[i]).uniqueResult();
			list.add(parse);
		}
		return list;
	}
}
