package com.scts.payment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.bean.Org;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.log.bean.SysLog;
import com.scts.payment.bean.InspectionInfoDTO;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.bean.ReportBorrow;
import com.scts.payment.bean.ReportBorrowDTO;
import com.scts.payment.dao.ReportBorrowDao;

import util.MoneyUtil;

/**
 * 外借记录业务逻辑对象
 * @ClassName ReportBorrowService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-12 下午05:26:00
 */
@Service("reportBorrowService")
public class ReportBorrowService extends
		EntityManageImpl<ReportBorrow, ReportBorrowDao> {
	@Autowired
	private ReportBorrowDao reportBorrowDao;
	
	// 根据业务ID查询业务外借记录
	public ReportBorrow queryByInspectionInfoID(String info_id) {
		return reportBorrowDao.queryByInspectionInfoID(info_id);
	}
	
	// 导出借发票
	public List<ReportBorrowDTO> exportBorrowCY(String check_unit_id, String borrow_start_date, String borrow_end_date) {
		return reportBorrowDao.exportBorrowCY(check_unit_id, borrow_start_date, borrow_end_date);
	}
	
	// 获取借票日志
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();		
		List<SysLog> list = reportBorrowDao.createQuery("  from SysLog where business_id ='"+id+"' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("invoice_no", reportBorrowDao.get(id).getInvoice_no());
		map.put("success", true);
		return map;
    }
	
	// 根据借票业务id获取借票时使用的发票号
	public String queryInvoice_no(String id){
		return reportBorrowDao.queryInvoice_no(id);
	}
	
	// 根据收费业务id获取收费时开票的金额
	public double queryMoney(String id) {
		return reportBorrowDao.queryMoney(id);
	}
	
	public ReportBorrow getReportBorrowByInvoice_no(String invoice_no){
		return reportBorrowDao.getReportBorrowByInvoice_no(invoice_no);
	}

	public ReportBorrow getDetail(String id) {
		ReportBorrow reportBorrow = this.queryByInspectionInfoID(id); // 报检业务收费信息
		String infoids = id;
		
		if (reportBorrow == null) {
			reportBorrow = new ReportBorrow();
		}else {
			infoids = reportBorrow.getFk_inspection_info_id();
		}
		List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
		String[] ids = id.split(",");
		if (StringUtil.isNotEmpty(reportBorrow.getFk_inspection_info_id())) {
			ids = reportBorrow.getFk_inspection_info_id().split(",");
		}

		if (StringUtil.isNotEmpty(infoids)) {
			// ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
			String idss = infoids.replace(",", "','");

			String hql = "select i.id,i.sn,i.check_unit_id,t.com_name,t.check_type,o.org_Name, "
					+ " d.device_sort_code,d.device_registration_code,d.device_name,i.advance_fees,i.receivable, "
					+ " i.report_sn ,i.report_com_name,i.advance_remark "
					+ " from tzsb_inspection_info i,base_device_document d,sys_org o,tzsb_inspection t  where t.id = i.fk_inspection_id and "
					+ " d.id = i.fk_tsjc_device_document_id  and o.id(+) = i.check_unit_id " + " and i.id in ('" + idss
					+ "')";

			List<Object> list = reportBorrowDao.createSQLQuery(hql).list();

			float advance_fees = 0.00f;
			float receivable = 0.00f;
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
				inspectionInfoDTO.setId(obj[0] == null ? "" : obj[0].toString());
				inspectionInfoDTO.setSn(obj[1] == null ? "" : obj[1].toString());
				inspectionInfoDTO.setCom_id(obj[2] == null ? "" : obj[2].toString());
				inspectionInfoDTO.setCom_name(obj[3] == null ? "" : obj[3].toString());
				inspectionInfoDTO.setCheck_type(obj[4] == null ? "" : obj[4].toString());

				inspectionInfoDTO.setCheck_department(obj[5] == null ? "" : obj[5].toString());
				inspectionInfoDTO.setDevice_sort_code(obj[6] == null ? "" : obj[6].toString());
				inspectionInfoDTO.setDevice_registration_code(obj[7] == null ? "" : obj[7].toString());
				inspectionInfoDTO.setDevice_name(obj[8] == null ? "" : obj[8].toString());
				inspectionInfoDTO.setAdvance_fees(obj[9] == null ? 0 : Double.parseDouble(obj[9].toString()));
				inspectionInfoDTO.setReceivable(obj[10] == null ? 0 : Double.parseDouble(obj[10].toString()));
				advance_fees += inspectionInfoDTO.getAdvance_fees();
				receivable += inspectionInfoDTO.getReceivable();
				inspectionInfoDTO.setReport_sn(obj[11] == null ? "" : obj[11].toString());
				inspectionInfoDTO.setReport_com_name(obj[12] == null ? "" : obj[12].toString());
				inspectionInfoDTO.setAdvance_remark(obj[13] == null ? "" : obj[13].toString());
				inspectionInfoDTOList.add(inspectionInfoDTO);
			}
			reportBorrow.setInspectionInfoDTOList(inspectionInfoDTOList);

		
			if (StringUtil.isEmpty(reportBorrow.getUnpay_amount())) {
				if(receivable!=0){
					reportBorrow.setUnpay_amount(String.valueOf(receivable)); // 实收总金额（欠款金额）
				}else{
					reportBorrow.setUnpay_amount(String.valueOf(advance_fees)); // 实收总金额（欠款金额）
				}
			}
			reportBorrow.setUnpay_amount_uppercase(MoneyUtil.toChinese(reportBorrow.getUnpay_amount()));
			reportBorrow.setInspectionInfoDTOList(inspectionInfoDTOList);
		}
		return reportBorrow;
	}
}
