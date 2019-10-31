package com.scts.payment.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.MatrixToImageWriter;
import com.lsts.constant.Constant;
import com.lsts.device.dao.DeviceDao;
import com.lsts.finance.bean.CwBank2Pay;
import com.lsts.finance.bean.CwBankDTO;
import com.lsts.finance.bean.CwBankDetail;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.dao.CwBankDetailDao;
import com.lsts.finance.service.CwBank2PayManager;
import com.lsts.finance.service.CwInvoiceFManager;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportDrawSign;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.log.bean.SysLog;
import com.lsts.log.service.SysLogService;
import com.scts.payment.bean.InspectionInfoDTO;
import com.scts.payment.bean.InspectionPayDetail;
import com.scts.payment.bean.InspectionPayInfo;
import com.scts.payment.bean.InspectionPayInfoDTO;
import com.scts.payment.bean.MachinePayTrade;
import com.scts.payment.bean.ReportBorrow;
import com.scts.payment.bean.ReportBorrowDTO;
import com.scts.payment.dao.InspectionPayInfoDao;

import util.Base64Utils;
import util.TS_Util;

/**
 * 报检信息收费业务逻辑对象
 * @ClassName InspectionPayInfoService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:40:00
 */
@Service("inspectionPayInfoService")
public class InspectionPayInfoService extends
		EntityManageImpl<InspectionPayInfo, InspectionPayInfoDao> {
	@Autowired
	private InspectionPayInfoDao inspectionPayInfoDao;
	@Autowired
	private CwInvoiceFManager cwInvoiceFManager;
	@Autowired
	private SysLogService logService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
    private CwBankDetailDao bankDetailDao;
	@Autowired
    private CwBank2PayManager cwBank2PayManager;
	@Autowired
    private CwBankDetailDao cwBankDetailDao;
	
	/**
     * 是否涉及转账业务
     *
     * @param type
     * @return
     */
    private boolean isZhuanZhang(String type) {
        return "2".equals(type) || "4".equals(type) || "7".equals(type) || "14".equals(type) || "15".equals(type);
    }

	/*
	 * //保存支付信息 public void savePayInfo(HttpServletRequest request,InspectionPayInfo
	 * inspectionPayInfo) throws Exception{ CurrentSessionUser user =
	 * SecurityUtil.getSecurityUser(); long[] t = new long[14]; t[0] =
	 * System.currentTimeMillis(); // 原发票号 String old_invoice_no = ""; double
	 * old_pay_money = 0; if (StringUtil.isNotEmpty(inspectionPayInfo.getId())) {
	 * List<Object[]> ll =
	 * inspectionPayInfoDao.queryOldMoneyAndInvoice_no(inspectionPayInfo.getId());
	 * old_pay_money = Double.valueOf(ll.get(0)[0].toString()); old_invoice_no =
	 * ll.get(0)[1].toString(); } boolean isAdd = true; String status =
	 * request.getParameter("status"); if (!"add".equals(status)) { isAdd = false; }
	 * // 1、验证发票号 if(StringUtil.isEmpty(inspectionPayInfo.getInvoice_no()) &&
	 * !"3".equals(inspectionPayInfo.getPay_type())){ throw new Exception("未填写发票号");
	 * }; List<CwInvoiceF> list =
	 * cwInvoiceFManager.querysByInvoice_no(inspectionPayInfo.getInvoice_no()); if
	 * (list.isEmpty()) { throw new Exception("亲，系统未能识别发票号（" +
	 * inspectionPayInfo.getInvoice_no() + "），请联系财务人员导入！"); } else { CwInvoiceF
	 * cwInvoiceF = list.get(0); if ("SY".equals(cwInvoiceF.getStatus())) { String
	 * pay_date = DateUtil.format(inspectionPayInfo.getPay_date(),
	 * Constant.defaultDatePattern); String invoice_date =
	 * DateUtil.format(cwInvoiceF.getInvoiceDate(), Constant.defaultDatePattern);
	 * 
	 * if (pay_date.equals(invoice_date) &&
	 * inspectionPayInfo.getCompany_name().equals(cwInvoiceF.getInvoiceUnit())) { //
	 * 相同开票单位和相同开票日期允许重复登记收费，反之 } else { if (isAdd) { throw new Exception("亲，该发票号（"
	 * + inspectionPayInfo.getInvoice_no() + "）已经使用，请核实！"); } else { if
	 * (StringUtil.isNotEmpty(old_invoice_no)) { if
	 * (!inspectionPayInfo.getInvoice_no().equals(old_invoice_no)) { throw new
	 * Exception("亲，该发票号（" + inspectionPayInfo.getInvoice_no() + "）已经使用，请核实！"); } }
	 * } } } if ("ZF".equals(cwInvoiceF.getStatus())) { throw new
	 * Exception("亲，该发票号（" + inspectionPayInfo.getInvoice_no() + "）已经作废，不能使用，请核实！");
	 * } }
	 * 
	 * t[3] = System.currentTimeMillis(); //
	 * 2、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，验证转账剩余金额是否充足 double remark =
	 * Double.parseDouble(inspectionPayInfo.getRemark()); // 本次转账总金额 if
	 * (isZhuanZhang(inspectionPayInfo.getPay_type())) { if
	 * (null==inspectionPayInfo.getCwBankDTOList() ||
	 * inspectionPayInfo.getCwBankDTOList().isEmpty()) { throw new
	 * Exception("请核实账户信息"); } String bank_ids = ""; for (CwBankDTO cwBankDTO :
	 * inspectionPayInfo.getCwBankDTOList()) { if (bank_ids.length() > 0) { bank_ids
	 * += ","; } bank_ids += cwBankDTO.getId(); } if (StringUtil.isEmpty(bank_ids))
	 * { throw new Exception("账户id为空，请核实账户信息！"); } // 获取银行转账剩余金额 double money =
	 * cwBankDetailDao.queryBankMoney(bank_ids); // 如果剩余金额不足，提示用户 if (money <
	 * remark) { throw new Exception("亲，您所选的银行转账余额不足，请重新选择！"); } } t[4] =
	 * System.currentTimeMillis(); // 3、保存业务信息（更新收费状态、实收金额等） // int count =
	 * inspectionPayInfo.getInspectionInfoDTOList().size(); String ids = ""; String
	 * report_com_name = ""; // 报告书使用单位（受检单位） String report_sn = ""; //
	 * 报告书编号（用于西藏报告提醒消息推送） String check_unit_id = ""; String check_department = "";
	 * // 检验部门 for (InspectionInfoDTO inspectionInfoDTO :
	 * inspectionPayInfo.getInspectionInfoDTOList()) { InspectionInfo inspectionInfo
	 * = inspectionInfoService.get(inspectionInfoDTO.getId()); if
	 * (StringUtil.isEmpty(report_sn)) { report_sn = inspectionInfo.getReport_sn();
	 * } // 实收金额 if (inspectionInfo.getReceivable() == null ||
	 * inspectionInfo.getReceivable() == 0) {
	 * inspectionInfo.setReceivable(inspectionInfo.getAdvance_fees()); // 实收金额 } //
	 * 缴费方式 1 现金缴费 2 转账 3 免收费 4 现金及转账 5 pos 6 现金及pos 7 转账及pos
	 * inspectionInfo.setFee_type(inspectionPayInfo.getPay_type());
	 * inspectionInfo.setInvoice_no(inspectionPayInfo.getInvoice_no()); // 发票号 if
	 * ("3".equals(inspectionPayInfo.getPay_type())) {
	 * inspectionInfo.setAdvance_type("2"); // 收费类型 0 正常收费 1 协议收费 2 } else { //
	 * 10：支付宝 11：微信 if (!"10".equals(inspectionPayInfo.getPay_type()) &&
	 * !"11".equals(inspectionPayInfo.getPay_type())) {
	 * inspectionInfo.setAdvance_type("0"); } } // 收费状态（0 默认 1 待收费 2 已收费 3 借报告 4 借发票
	 * 5 借报告和发票） inspectionInfo.setFee_status("2");
	 * inspectionInfo.setInvoice_date(inspectionPayInfo.getPay_date()); // 缴费日期
	 * 
	 * if (StringUtil.isEmpty(check_unit_id)) { check_unit_id =
	 * inspectionInfo.getCheck_unit_id(); } if
	 * (StringUtil.isEmpty(check_department)) { if
	 * (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) { Org org =
	 * orgManager.get(inspectionInfo.getCheck_unit_id()); check_department =
	 * StringUtil.isNotEmpty(org.getOrgName()) ? org.getOrgName() : ""; // 检验部门 } }
	 * 
	 * inspectionInfoService.save(inspectionInfo); // 4、查询是否有外借情况 ReportBorrow
	 * reportBorrow =
	 * reportBorrowService.queryByInspectionInfoID(inspectionInfo.getId()); if
	 * (reportBorrow != null) { // 如果存在外借情况，重新收费后，重新更新外借业务和外借欠款金额 String
	 * fk_inspection_info_id = reportBorrow.getFk_inspection_info_id();
	 * fk_inspection_info_id = fk_inspection_info_id.replace(inspectionInfo.getId(),
	 * ""); double unpay = Double.parseDouble(reportBorrow.getUnpay_amount()); unpay
	 * = unpay - inspectionInfo.getAdvance_fees();
	 * reportBorrow.setFk_inspection_info_id(fk_inspection_info_id); // 更新外借业务id
	 * reportBorrow.setUnpay_amount(unpay + ""); // 更新外借欠款金额 boolean isCandel =
	 * true; // 是否是全部取消外借重新收费 String[] idArr = fk_inspection_info_id.split(","); for
	 * (int i = 0; i < idArr.length; i++) { if (StringUtil.isNotEmpty(idArr[i])) {
	 * isCandel = false; } } if (isCandel) { reportBorrow.setBorrow_status("3"); //
	 * 3：已取消外借 } if (unpay == 0) { reportBorrow.setBorrow_status("3"); // 3：已取消外借 }
	 * reportBorrowService.save(reportBorrow);
	 * logService.setLogs(inspectionInfo.getId(), "【外借业务】报告收费", "【外借业务】报告收费",
	 * user.getId(), user.getName(), new Date(), request.getRemoteAddr()); }
	 * 
	 * if (ids.length() > 0) { ids += ","; } ids += inspectionInfo.getId(); if
	 * (StringUtil.isEmpty(report_com_name)) { report_com_name =
	 * inspectionInfo.getReport_com_name(); } } t[5] = System.currentTimeMillis();
	 * // 5、保存缴费信息 if (StringUtil.isEmpty(inspectionPayInfo.getPay_no())) { String
	 * curYearMonth = TS_Util.getCurYearMonth(); // 获取当前年月 String no =
	 * inspectionPayInfoService.queryNextPay_no(curYearMonth); // 获取编号后四位序号 // 编号 =
	 * 当前年月 + 4位序号 inspectionPayInfo.setPay_no(curYearMonth + no); }
	 * inspectionPayInfo.setPayment_status("2"); // 缴费状态 0 默认 1 待收费 2 已收费
	 * inspectionPayInfo.setCreated_date(new Date()); // 操作日期
	 * inspectionPayInfo.setCreated_by(user.getName()); // 操作人 if
	 * (StringUtil.isEmpty(inspectionPayInfo.getReceive_man_name())) {
	 * inspectionPayInfo.setReceive_man_name(user.getName()); }
	 * inspectionPayInfoService.save(inspectionPayInfo); t[6] =
	 * System.currentTimeMillis(); // 6、保存缴费详细信息 InspectionPayDetail
	 * inspectionPayDetail = new InspectionPayDetail();
	 * BeanUtils.copyProperties(inspectionPayDetail, inspectionPayInfo);
	 * inspectionPayDetail.setId(null);
	 * inspectionPayDetail.setFk_pay_info_id(inspectionPayInfo.getId());
	 * inspectionPayDetail.setReceive_date(inspectionPayInfo.getPay_date());
	 * inspectionPayDetailService.save(inspectionPayDetail); t[7] =
	 * System.currentTimeMillis(); // 7、当收费方式为“银行转账、现金及转账、转账及pos机刷卡”时，返写银行转账记录信息表
	 * String cw_bank_ids = ""; if (isZhuanZhang(inspectionPayInfo.getPay_type())) {
	 * if (inspectionPayInfo.getCwBankDTOList() != null) { List<CwBankDTO>
	 * cwBankList = inspectionPayInfo.getCwBankDTOList(); if (!cwBankList.isEmpty())
	 * { for (CwBankDTO cwBankDTO : cwBankList) { // 4.2、保存收费业务与银行转账记录关联信息表
	 * inspectionPayInfoDao.insertCwBank2Pay(StringUtil.getUUID(),
	 * cwBankDTO.getId(), inspectionPayInfo.getId(), cwBankDTO.getUsedMoney(),
	 * cwBankDTO.getPay_remark());
	 * 
	 * // 4.3、返写银行转账记录信息表 CwBankDetail cwBankDetail =
	 * cwBankDetailManager.get(cwBankDTO.getId()); // 剩余金额
	 * cwBankDetail.setRestMoney(TS_Util.ratioTransform((cwBankDetail.getRestMoney()
	 * .doubleValue() - cwBankDTO.getUsedMoney().doubleValue()))); // 本次已收金额
	 * cwBankDetail.setUsedMoney(TS_Util.ratioTransform((cwBankDetail.getUsedMoney()
	 * .doubleValue() + cwBankDTO.getUsedMoney().doubleValue())));
	 * cwBankDetailManager.save(cwBankDetail); if (cw_bank_ids.length() > 0) {
	 * cw_bank_ids += ","; } cw_bank_ids += cwBankDetail.getId();
	 * 
	 * logService.setLogs(cwBankDTO.getId(), "报告收费，使用银行转账", "本次剩余金额：" +
	 * TS_Util.ratioTransform(cwBankDetail.getRestMoney().doubleValue()) + "，" +
	 * "本次收费金额：" + TS_Util.ratioTransform(cwBankDTO.getUsedMoney().doubleValue()),
	 * user.getId(), user.getName(), new Date(), request.getRemoteAddr()); } }
	 * 
	 * } } else { // 10：支付宝 11：微信 if ("10".equals(inspectionPayInfo.getPay_type())
	 * || "11".equals(inspectionPayInfo.getPay_type())) { if
	 * (StringUtil.isEmpty(inspectionPayInfo.getFk_alipay_trade_id()) ||
	 * StringUtil.isEmpty(inspectionPayInfo.getFk_weixin_trade_id())) {
	 * List<MachinePayTrade> tradeList = inspectionPayInfo.getMachinePayTradeList();
	 * if (!tradeList.isEmpty()) { for (MachinePayTrade trade : tradeList) { if
	 * (StringUtil.isEmpty(inspectionPayInfo.getFk_alipay_trade_id())) {
	 * inspectionPayInfo.setFk_alipay_trade_id(trade.getId()); break; } if
	 * (StringUtil.isEmpty(inspectionPayInfo.getFk_weixin_trade_id())) {
	 * inspectionPayInfo.setFk_weixin_trade_id(trade.getId()); break; } } } }
	 * 
	 * } } inspectionPayInfo.setFk_cw_bank_id(cw_bank_ids);
	 * inspectionPayInfoService.save(inspectionPayInfo); t[8] =
	 * System.currentTimeMillis(); // 8、返写发票信息 if (StringUtil.isNotEmpty(invoice_no)
	 * && !"3".equals(inspectionPayInfo.getPay_type())) { if
	 * (StringUtil.isNotEmpty(report_com_name)) {
	 * inspectionPayInfo.setReport_com_name(report_com_name); } if
	 * (StringUtil.isEmpty(old_invoice_no)) { // 5.1 返写更新本次使用的新发票号信息
	 * updateCwInvoiceF(request, inspectionPayInfo, check_department, isAdd, false);
	 * } else { if (!invoice_no.equals(old_invoice_no)) { // 5.2 返写更新原发票号信息 //
	 * 原票号是否作废（0：否 1：是） String if_del_invoice =
	 * request.getParameter("if_del_invoice"); if ("1".equals(if_del_invoice)) {
	 * zfOldCwInvoiceF(request, old_invoice_no, old_pay_money, check_department); }
	 * else { updateOldCwInvoiceF(request, old_invoice_no, old_pay_money,
	 * check_department); } } updateCwInvoiceF(request, inspectionPayInfo,
	 * check_department, isAdd, false); } } t[9] = System.currentTimeMillis(); //
	 * 9、记录日志 String[] idArr = ids.split(","); for (int i = 0; i < idArr.length;
	 * i++) { logService.setLogs(idArr[i], "报告收费", "报告收费", user.getId(),
	 * user.getName(), new Date(), request.getRemoteAddr()); } t[10] =
	 * System.currentTimeMillis(); // 10、西藏和新疆报告提醒消息推送 if
	 * (StringUtil.isNotEmpty(check_unit_id)) { if
	 * (Constant.CHECK_UNIT_100069.equals(check_unit_id)) { // 获取发送内容 String content
	 * = Constant.getXzReportPayNoticeContent(inspectionPayInfo.getCompany_name(),
	 * count, report_sn); // 获取发送目标号码 String mobile =
	 * employeesService.getMobile(Constant.XZ_REPORT_NOTIC_EMPID); // 发送微信
	 * messageService.sendWxMsg(request, inspectionPayInfo.getId(),
	 * Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile); //
	 * 发送短信 // messageService.sendMoMsg(request, // inspectionPayInfo.getId(),
	 * content, mobile); } // 2017-12-12应明子涵要求，增加新疆报告已收费提醒消息推送 if
	 * (Constant.CHECK_UNIT_100090.equals(check_unit_id)) { // 获取发送内容 String content
	 * = Constant.getXzReportPayNoticeContent(inspectionPayInfo.getCompany_name(),
	 * count, report_sn); // 获取发送目标号码 String emp_id =
	 * Factory.getSysPara().getProperty("XJ_REPORT_NOTIC_EMPID"); if
	 * (StringUtil.isEmpty(emp_id)) { emp_id = Constant.XJ_REPORT_NOTIC_EMPID; }
	 * String mobile = employeesService.getMobile(emp_id); // 发送微信
	 * messageService.sendWxMsg(request, inspectionPayInfo.getId(),
	 * Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile); //
	 * 发送短信 // messageService.sendMoMsg(request, // inspectionPayInfo.getId(),
	 * content, mobile); } // 2018-03-29应明子涵要求，增加九寨报告已收费提醒消息推送 if
	 * (Constant.CHECK_UNIT_100091.equals(check_unit_id)) { // 获取发送内容 String content
	 * = Constant.getJzReportPayNoticeContent(inspectionPayInfo.getCompany_name(),
	 * count, report_sn); // 获取发送目标号码 String emp_id =
	 * Factory.getSysPara().getProperty("JZ_REPORT_NOTIC_EMPID"); if
	 * (StringUtil.isEmpty(emp_id)) { emp_id = Constant.JZ_REPORT_NOTIC_EMPID; }
	 * String mobile = employeesService.getMobile(emp_id); // 发送微信
	 * messageService.sendWxMsg(request, inspectionPayInfo.getId(),
	 * Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, content, mobile); //
	 * 发送短信 // messageService.sendMoMsg(request, // inspectionPayInfo.getId(),
	 * content, mobile); } } t[11] = System.currentTimeMillis(); for(int
	 * i=0;i<14;i++) { System.out.println("times:"+t[i]); } }
	 */
	/**
	 * 
	 * 保存报告录入信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void batchSave(InspectionPayInfoDTO inspectionPayInfoDTO,
			HttpServletRequest request) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		try {
			for (InspectionPayInfo info : inspectionPayInfoDTO.getInspectionPayInfo()) {
				// 1、保存缴费基本信息
				info.setDevice_type("CY");
				info.setPayment_status("2"); // 2：已收费
				info.setReceive_man_name(user.getName());
				info.setCreated_by(user.getName());
				info.setCreated_date(new Date());
				inspectionPayInfoDao.save(info);
				
				// 2、返写发票信息
				if (StringUtil.isNotEmpty(info.getInvoice_no())) {
					CwInvoiceF cwInvoiceF = cwInvoiceFManager
							.queryByInvoice_no(info.getInvoice_no());
					// 发票金额
					cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform((cwInvoiceF
							.getInvoiceMoney().doubleValue() + Double.parseDouble(info.getPay_received()))));
					// 交易类型（1 现金缴费 2 转账 3 免收费/判废免收 4 现金及转账 5 pos 6 现金及pos 7 转账及pos）
					cwInvoiceF.setMoneyType(info.getPay_type());
					cwInvoiceF.setInvoiceUnit(info.getCompany_name()); // 开票单位
					cwInvoiceF.setCheckoutUnit(info.getReport_com_name()); // 受检单位
					cwInvoiceF.setCheckoutDep(info.getCheck_dep_name());
					cwInvoiceF.setStatus("SY"); // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用）
					cwInvoiceF.setInvoice_id(user.getId());		// 开票人id
					cwInvoiceF.setInvoice_name(user.getName());	// 开票人name
					cwInvoiceF.setInvoiceDate(info.getPay_date());// 开票日期
					cwInvoiceFManager.save(cwInvoiceF);
					logService.setLogs(cwInvoiceF.getId(), "批量开票，使用发票",
							"批量开票，报告（"+info.getReport_sn()+"）使用发票（发票号：" + info.getInvoice_no() + "）", user.getId(),
							user.getName(), new Date(), request.getRemoteAddr());
				}
				
				// 3、记录日志
				logService.setLogs(info.getId(), "通用开票批量导入", "通用开票批量导入", user.getId(), user
						.getName(), new Date(), request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
	}
	
	// 根据业务ID查询业务收费信息
	public InspectionPayInfo queryByInspectionInfoID(String fk_inspection_info_id) {
		return inspectionPayInfoDao.queryByInspectionInfoID(fk_inspection_info_id);
	}
	
	// 根据业务ID查询业务收费信息
	public List<InspectionPayInfo> queryList(String fk_inspection_info_id) {
		return inspectionPayInfoDao.queryList(fk_inspection_info_id);
	}
	
	// 获取编号后四位序号
	public String queryNextPay_no(String pay_no_pre){
		return inspectionPayInfoDao.queryNextPay_no(pay_no_pre);
	}
	
	// 根据收费业务id获取收费时使用的发票号
	public String queryInvoice_no(String id){
			return inspectionPayInfoDao.queryInvoice_no(id);
	}
	
	// 根据收费业务id获取收费时开票的金额
	public double queryMoney(String id) {
		return inspectionPayInfoDao.queryMoney(id);
	}
	
	// 导出交账明细、收入明细
	public List<InspectionPayInfoDTO> export(String check_unit_id, String invoice_start_date, String invoice_end_date) {
		return inspectionPayInfoDao.export(check_unit_id, invoice_start_date, invoice_end_date);
	}
	
	// 导出交账明细（承压）
	public List<InspectionPayInfoDTO> exportCY(String check_unit_id, String pay_start_date, String pay_end_date) {
		return inspectionPayInfoDao.exportCY(check_unit_id, pay_start_date, pay_end_date);
	}
	
	// 导出交账明细（承压）
	public List<InspectionPayInfoDTO> exportAll(String check_unit_id, String pay_start_date, String pay_end_date,
			String invoice_no_start, String invoice_no_end, String invoice_category, String payment_category,
			String op_user_name) {
		return inspectionPayInfoDao.exportAll(check_unit_id, pay_start_date, pay_end_date, invoice_no_start,
				invoice_no_end, invoice_category, payment_category, op_user_name);
	}
	
	// 导出交账明细、收入明细
	public List<InspectionPayInfoDTO> exportZZJD(String check_dep_name, String invoice_start_date, String invoice_end_date) {
		return inspectionPayInfoDao.exportZZJD(check_dep_name, invoice_start_date, invoice_end_date);
	}
	
	// 导出借报告
	public List<ReportBorrowDTO> exportBorrow(String check_unit_id, String sign_leader_id, String borrow_start_date, String borrow_end_date, String borrow_type) {
		return inspectionPayInfoDao.exportBorrow(check_unit_id, sign_leader_id, borrow_start_date, borrow_end_date, borrow_type);
	}
	
	// 导出借发票
	public List<ReportBorrowDTO> exportBorrow2(String check_unit_id, String sign_leader_id, String borrow_start_date, String borrow_end_date, String borrow_type) {
		return inspectionPayInfoDao.exportBorrow2(check_unit_id, sign_leader_id, borrow_start_date, borrow_end_date, borrow_type);
	}
	
	// 导出借发票和借报告
	public List<ReportBorrowDTO> exportBorrow3(String check_unit_id, String sign_leader_id, String borrow_start_date, String borrow_end_date, String borrow_type) {
		return inspectionPayInfoDao.exportBorrow3(check_unit_id, sign_leader_id, borrow_start_date, borrow_end_date, borrow_type);
	}
	
	// 导出借报告、借发票（批量报检）
	public List<ReportBorrowDTO> exportZZJDBorrow(String check_dep_name, String borrow_start_date, String borrow_end_date, String borrow_type) {
		return inspectionPayInfoDao.exportZZJDBorrow(check_dep_name, borrow_start_date, borrow_end_date, borrow_type);
	}
	
	/**
	 * 验证发票号是否已存在（使用）
	 * @param invoice_no -- 发票号
	 * 
	 * @return 存在发票号返回true，不存在返回false
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> validateInvoice(String invoice_no) throws Exception {
		HashMap<String, Object>  map = new HashMap<String, Object>();
		List list = inspectionPayInfoDao.createSQLQuery("select id from TZSB_INSPECTION_PAY_INFO where invoice_no='"+invoice_no.trim()+"' and payment_status <> '99'").list();
		if(list.size()>0){
			map.put("success", true);
		}else{
			map.put("success", false); 
		}
		return map;
	}
	
	/**
	 * 验证发票号是否存在
	 * @param invoice_no -- 发票号
	 * 
	 * @return 存在发票号返回true，不存在返回false
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> validateInvoices(String invoice_no) throws Exception {
		HashMap<String, Object>  map = new HashMap<String, Object>();
		List list = inspectionPayInfoDao.createSQLQuery("select id from TJY2_CW_INVOICE_F where invoice_code='"+invoice_no.trim()+"' and status !='ZF'").list();
		if(list.size()>0){
			map.put("success", true);
		}else{
			map.put("success", false); 
		}
		return map;
	}
	
	/**
	 * 验证发票号是否可用
	 * @param invoice_no -- 发票号
	 * 
	 * @return 可用发票号返回true，不可用返回false
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> validateInvoice2(String invoice_no) throws Exception {
		HashMap<String, Object>  map = new HashMap<String, Object>();
		List list = inspectionPayInfoDao.createSQLQuery("select id from TJY2_CW_INVOICE_F where invoice_code='"+invoice_no.trim()+"' and status !='SY' and status !='ZF'").list();
		if(list.size()>0){
			map.put("success", true);
		}else{
			map.put("success", false); 
		}
		return map;
	}
	
	// 获取开票日志
	@SuppressWarnings("unchecked")
	public HashMap<String, Object>  getFlowStep(String id) throws Exception{	
		HashMap<String, Object> map = new HashMap<String, Object>();		
		List<SysLog> list = inspectionPayInfoDao.createQuery("  from SysLog where business_id ='"+id+"' order by op_time,id asc").list();
		map.put("flowStep", list);
		map.put("size", list.size());
		map.put("invoice_no", inspectionPayInfoDao.get(id).getInvoice_no());
		map.put("success", true);
		return map;
    }
	
	public void delete(String id) {
		inspectionPayInfoDao.delete(id);
	}
	
	// 发票状态检查更新
	public void invoiceCheckUpd() {
		try {
			// 获取收费取消、外借取消的发票信息
			List<CwInvoiceF> cwInvoiceFList = cwInvoiceFManager.queryCwInvoiceQx();
			for (CwInvoiceF cwInvoiceF : cwInvoiceFList) {				
				// 根据发票号查询是否存在收费记录（未取消的正常收费记录）
				List<InspectionPayInfo> payList = inspectionPayInfoDao.queryPayList(cwInvoiceF.getInvoiceCode());
				if(!payList.isEmpty()){
					// 存在正常的收费记录，则更新发票状态及其开票信息等
					double invoiceMoney = 0.0;
					String invoiceUnit = "";
					String invoice_name = "";
					String moneyType = "";
					Date invoiceDate = null;
					String checkoutDep = "";
					
					for(InspectionPayInfo payInfo : payList){
						invoiceMoney += Double.parseDouble(payInfo.getPay_received());
						if(StringUtil.isEmpty(invoiceUnit)){
							invoiceUnit = payInfo.getCompany_name();
						}
						if(StringUtil.isEmpty(invoice_name)){
							invoice_name = payInfo.getReceive_man_name();
						}
						if(StringUtil.isEmpty(moneyType)){
							moneyType = payInfo.getPay_type();
						}
						if(StringUtil.isEmpty(checkoutDep)){
							if(StringUtil.isNotEmpty(payInfo.getCheck_dep_name())){
								checkoutDep = payInfo.getCheck_dep_name();
							}else{
								// 根据业务信息查询检验部门信息
								if(StringUtil.isNotEmpty(payInfo.getFk_inspection_info_id())){
									String[] info_id = payInfo.getFk_inspection_info_id().split(",");
									InspectionInfo info = inspectionInfoService.get(info_id[0]);
									if(info != null){
										Org org = orgManager.get(info.getCheck_unit_id());
										if(org != null){
											checkoutDep = org.getOrgName();
										}
									}
								}
							}
						}
						if(invoiceDate == null){
							invoiceDate = payInfo.getPay_date();
						}
					}
					
					cwInvoiceF.setStatus("SY"); 	// 发票状态（SY：使用）
					cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform(invoiceMoney));
					cwInvoiceF.setInvoiceUnit(invoiceUnit);
					cwInvoiceF.setInvoice_name(invoice_name);
					cwInvoiceF.setMoneyType(moneyType);
					cwInvoiceF.setCheckoutDep(checkoutDep);
					cwInvoiceF.setInvoiceDate(invoiceDate);
					cwInvoiceFManager.save(cwInvoiceF);
					
					logService.setLogs(cwInvoiceF.getId(), "发票系统更新",
							"发票信息系统更新（发票号：" + cwInvoiceF.getInvoiceCode() + "）", "",
							"系统", new Date(), "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
	}

	public String showTwoDim(String id, HttpServletRequest request) throws WriterException, IOException {
		//StringBuffer params = new StringBuffer();  
		String imagepath=request.getSession().getServletContext().getRealPath("upload");
		String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getSession().getServletContext().getContextPath();
	   // System.out.println(path+"/payment/payInfo/getMobileInfo.do?id="+id);
	   // InspectionPayInfo ins=getByinspectionInfoId(id);
	   // params.append("http://192.168.0.125:8080/SNTS/baseDeviceDocumentAction/getMobileInfo.do?id="+id);
	   // params.append(ins.getId());
	   // params.append(path+"/baseDeviceDocumentAction/getMobileInfo.do?id="+id);  
	    int width = 150; 
	    int height = 150; 
	    //二维码的图片格式 
	    String format = "gif"; 
	    Hashtable hints = new Hashtable(); 
	    //内容所使用编码 
	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
	    BitMatrix bitMatrix = new MultiFormatWriter().encode(id, 
	            BarcodeFormat.QR_CODE, width, height, hints); 
	    //System.out.println("-------------"+imagepath+File.separator+"new.gif"+"--------------");
	    File file1 = new File(imagepath);
	    if(!file1.mkdir()){
	    	file1.mkdir();
	    }
	    File file = new File(imagepath+File.separator+"new.gif");
	    if(!file.exists()){
	    	file.createNewFile();
	    }
	    //生成二维码 
	    OutputStream stream = new FileOutputStream(imagepath+File.separator+"new.gif"); 
	    
	    MatrixToImageWriter.writeToStream(bitMatrix, format, stream); 
	    
	    return imagepath+File.separator+"new.gif";
	}

	public   InspectionPayInfo getByinspectionInfoId(String id) {
		return inspectionPayInfoDao.getByInspectionInfoId(id);
	}

	public String showTwoDim2(HttpServletRequest request, String infoIds) throws WriterException, IOException {
		StringBuffer params = new StringBuffer();  
		String imagepath=request.getSession().getServletContext().getRealPath("upload");
		String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getSession().getServletContext().getContextPath();
	   
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String sql="insert into TZSB_WITH_TWOWEI (id,infoids) values (?,?)";
		inspectionPayInfoDao.createSQLQuery(sql,uuid,infoIds).executeUpdate();
		
		
		params.append(uuid);
	   // params.append(path+"/baseDeviceDocumentAction/getMobileInfo.do?id="+id);  
	    int width = 150; 
	    int height = 150; 
	    //二维码的图片格式 
	    String format = "gif"; 
	    Hashtable hints = new Hashtable(); 
	    //内容所使用编码 
	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
	    BitMatrix bitMatrix = new MultiFormatWriter().encode(params.toString(), 
	            BarcodeFormat.QR_CODE, width, height, hints); 
	    //System.out.println("-------------"+imagepath+File.separator+"new.gif"+"--------------");
	    File file1 = new File(imagepath);
	    if(!file1.mkdir()){
	    	file1.mkdir();
	    }
	    File file = new File(imagepath+File.separator+"new.gif");
	    if(!file.exists()){
	    	file.createNewFile();
	    }
	    //生成二维码 
	    OutputStream stream = new FileOutputStream(imagepath+File.separator+"new.gif"); 
	    
	    MatrixToImageWriter.writeToStream(bitMatrix, format, stream); 
	    
	    return imagepath+File.separator+"new.gif";
	}

	public String infoidds(HttpServletRequest request, String id) {
		String sql="select t.infoids from TZSB_WITH_TWOWEI t where id=?";
		List list=inspectionPayInfoDao.createSQLQuery(sql, id).list();
		Object obj=list.get(0);
		return obj.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getRefRep(String id) {
		return inspectionPayInfoDao.createSQLQuery("select fk_inspection_info_id from TZSB_INSPECTION_PAY_INFO where id = ?", id).list();
	}
	
	/**
	 * 查询收费结算单数据
	 * @param str
	 * @return
	 */
	public List<Map<String, Object>> queryAppSign(String str){
		String sql="select b.id,b.REPORT_COM_NAME,b.CHECK_UNIT_ID,o.org_name,b.REPORT_SN,b.DEVICE_REGISTRATION_CODE,b.advance_fees,t.DEVICE_REGISTRATION_CODE SBZCDM\n"+
					"from TZSB_INSPECTION_INFO b,SYS_ORG o,BASE_DEVICE_DOCUMENT t  where b.CHECK_UNIT_ID=o.id \n"
					+ "and t.id(+) = b.fk_tsjc_device_document_id  and b.data_status !='99'  and b.id in "+str+"";
		Query query=inspectionPayInfoDao.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	/**
	 * 查询检验业务收费信息表信息
	 * @param id
	 * @return
	 */
	public List<InspectionPayInfo> queryInspectionPayInfo(String id){
		String sql="select * from TZSB_INSPECTION_PAY_INFO where FK_INSPECTION_INFO_ID like '%"+id+"%' ";
		SQLQuery query=((SQLQuery) inspectionPayInfoDao.createSQLQuery(sql)).addEntity(InspectionPayInfo.class);
		List<InspectionPayInfo> list=query.list();
		return list;
	}
	
	/**
	 * 获取收费结算单签名图片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getPayInfoSign(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select c.base64_sign_file from TZSB_INSPECTION_PAY_INFO b,TZSB_INSPECTION_PAY_SIGN c \n"
				+ "where  b.id=c.FK_PAY_INFO_ID and b.fk_inspection_info_id like '%"+id+"%' ";
		List<String> list = inspectionPayInfoDao.createSQLQuery(sql).list();
		map.put("rows", list);
		//获取图片
		String rs = "";
		if(CollectionUtils.isEmpty(list)){
			throw new KhntException("未获取到签名图片");
		}
		String filePath = list.get(0).toString();
		if(StringUtil.isEmpty(filePath)){
			throw new KhntException("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
	    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		String fileType=filePath.substring(filePath.length()-3, filePath.length());
		if(fileType.equals("txt")){
			try {
				File file = new File(filePath);
			    // FileReader:用来读取字符文件的便捷类。
				bufReader = new BufferedReader(new FileReader(file));
				// buf = new BufferedReader(new InputStreamReader(new
				// FileInputStream(file)));
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					sb.append(temp);
			    }
				rs = sb.toString();
				map.put("image", rs);
			} catch (Exception e) {
			    e.getStackTrace();
			} finally {
			    if (bufReader != null ) {
			        try {
			            bufReader.close();
			        } catch (IOException e) {
			            e.getStackTrace();
			        }
			    }
			}
		}else{
//			filePath=filePath.replaceAll("//", "\\");
			filePath=filePath.replaceAll("\\\\", "\\/");
			System.out.println("=====filePath====:"+filePath);
//			String filePaths=filePath.replaceAll("\\\\", "/");
			map.put("image", filePath);
		}
		
		return map;
	}
	/**
	 * 获取收费签名及电话 guido 2018-12-20
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getPayInfoSignAndTel(HttpServletRequest request,String id,String invoice_no) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select b.fk_inspection_info_id, b.tel,c.base64_sign_file from TZSB_INSPECTION_PAY_INFO b,TZSB_INSPECTION_PAY_SIGN c \n"
				+ "where  b.id=c.FK_PAY_INFO_ID and b.invoice_no = ? ";
		List<Object[]> list = inspectionPayInfoDao.createSQLQuery(sql,invoice_no).list();
		
		//获取图片
		String rs = "";
		if(CollectionUtils.isEmpty(list)){
			throw new KhntException("未获取到签名图片");
		}
		String filePath = "";
		for(Object[] os : list){
			if(os[0].toString().indexOf(id.split(",")[0])!=-1){
				map.put("linkmode", os[1]==null?"":os[1].toString());
				filePath = os[2].toString();
				if(StringUtil.isEmpty(filePath)){
					break;
				}
			}
		}
		if(StringUtil.isEmpty(filePath)){
			throw new KhntException("未获取到签名图片！");
		}
		StringBuffer sb = new StringBuffer();
		// BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		String fileType=filePath.substring(filePath.length()-3, filePath.length());
		if(fileType.equals("txt")){
			try {
				File file = new File(filePath);
				// FileReader:用来读取字符文件的便捷类。
				bufReader = new BufferedReader(new FileReader(file));
				// buf = new BufferedReader(new InputStreamReader(new
				// FileInputStream(file)));
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					sb.append(temp);
				}
				rs = sb.toString();
				map.put("image", rs);
			} catch (Exception e) {
				e.getStackTrace();
			} finally {
				if (bufReader != null ) {
					try {
						bufReader.close();
					} catch (IOException e) {
						e.getStackTrace();
					}
				}
			}
		}else{
//			filePath=filePath.replaceAll("//", "\\");
			filePath=filePath.replaceAll("\\\\", "\\/");
			System.out.println("=====filePath====:"+filePath);
//			String filePaths=filePath.replaceAll("\\\\", "/");
			String paths = request.getServletContext().getRealPath("/");
			String base64Pic = Base64Utils.ImageToBase64ByLocal(paths+filePath);
			map.put("image", base64Pic);
		}
		
		return map;
	}
	
	/**
	 * 查询收费单是否签字
	 * @param id
	 * @return TZSB_INSPECTION_PAY_INFO
	 */
	public List<Map<String, Object>> queryInspectionPayInfoSign(String id){
		String sql="select * from TZSB_INSPECTION_PAY_SIGN where FK_PAY_INFO_ID=? ";
		Query query=inspectionPayInfoDao.createSQLQuery(sql,id).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	
	/**
	 * 查询检验业务收费信息表信息
	 * @param id
	 * @return
	 */
	public List<InspectionPayInfo> queryInspectionPayInfo1(String fkinsptionInfoId){
		String sql="select * from TZSB_INSPECTION_PAY_INFO where FK_INSPECTION_INFO_ID=? and payment_status='2' ";
//		SQLQuery query=((SQLQuery) inspectionPayInfoDao.createSQLQuery(sql,id)).addEntity(InspectionPayInfo.class);
		SQLQuery query=((SQLQuery) inspectionPayInfoDao.createSQLQuery(sql, fkinsptionInfoId)).addEntity(InspectionPayInfo.class);
		List<InspectionPayInfo> list=query.list();
		return list;
	}
	
	/**
	 * 根据发票编号查询
	 * 查询检验业务收费信息表信息
	 * @param id
	 * @return
	 */
	public List<InspectionPayInfo> queryInspectionPayInfo2(String invoiceNo){
		String sql="select a.* from TZSB_INSPECTION_PAY_INFO a ,TZSB_INSPECTION_PAY_DETAIL b where b.fk_pay_info_id=a.id and b.invoice_no=? and a.payment_status='2' ";
//		SQLQuery query=((SQLQuery) inspectionPayInfoDao.createSQLQuery(sql,id)).addEntity(InspectionPayInfo.class);
		SQLQuery query=((SQLQuery) inspectionPayInfoDao.createSQLQuery(sql, invoiceNo)).addEntity(InspectionPayInfo.class);
		List<InspectionPayInfo> list=query.list();
		return list;
	}
	
	/**
	 * 清空report_draw_sign表数据
	 * @param fkInspectionInfoId
	 * @param report_sn
	 */
	public void deleteReportDrawSign(String reportDrawSignId){
		String sql="delete from REPORT_DRAW_SIGN where id=?";
		inspectionPayInfoDao.createSQLQuery(sql,reportDrawSignId).executeUpdate();
	}
	
	
	/**
	 * 根本报检业务数据表id查询
	 * @param fkInspectionInfoId
	 * @return
	 */
	public List<Map<String, Object>> selectReportDrawSign(String fkInspectionInfoId){
		String sql="select * from REPORT_DRAW_SIGN where FK_INSPECTION_INFO_ID=? ";
		Query query= inspectionPayInfoDao.createSQLQuery(sql,fkInspectionInfoId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list=query.list();
		return list;
	}
	
	
	
	/**
	 * report_draw_sign表修改报告领取签名中间表签名状态为未重签
	 * @param fkInspectionInfoId
	 * @param report_sn
	 */
	public void updateInspectionPayInfoTel(String id,String tel){
		String sql="update TZSB_INSPECTION_PAY_INFO set TEL=? where id=? ";
		inspectionPayInfoDao.createSQLQuery(sql,tel,id).executeUpdate();
	}
	
	/**
	 * 根据查询条件查询收费统计
	 * @param request
	 * @return
	 */
	public List<Map<String, String>> getPaymentList(HttpServletRequest request){
		return inspectionPayInfoDao.getPaymentList(request);
	}
	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InspectionPayInfo getBatchPay(String id) throws Exception{
		String hql = "from InspectionPayInfo where batch = (select batch from InspectionPayInfo where id = ?) order by invoice_no";
		List<InspectionPayInfo> list = inspectionPayInfoDao.createQuery(hql, id).list();
		if(list.size()==0){
			list = inspectionPayInfoDao.createQuery("from InspectionPayInfo where id = ?", id).list();
		}
		InspectionPayInfo payInfo = new InspectionPayInfo();
		if(list.size()>0){
			payInfo.setReport_com_name(list.get(0).getReport_com_name());
			payInfo.setPay_no(list.get(0).getPay_no());
			payInfo.setCompany_name(list.get(0).getCompany_name());
			payInfo.setCheck_dep_name(list.get(0).getCheck_dep_name());
			payInfo.setPay_date(list.get(0).getPay_date());
			Integer report_count = new Integer(0);
			StringBuilder invoice_no = new StringBuilder();
			float cash_pay = 0.0f;
			float pos = 0.0f;
			float remark = 0.0f;
			float pay_received = 0.0f;
			float hand_in = 0.0f;
			float draft = 0.0f;
			List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
			for (InspectionPayInfo inspectionPayInfo : list) {
	            if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
	                String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(",");
	                for (int i = 0; i < bank_ids.length; i++) {
	                    CwBankDetail cwBankDetail = bankDetailDao.get(bank_ids[i]);
	                    if (cwBankDetail != null) {
	                        CwBankDTO cwBankDTO = new CwBankDTO();
	                        BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
	                        CwBank2Pay cwBank2Pay = cwBank2PayManager
	                                .queryCwBank2Pay(bank_ids[i], id);
	                        if (cwBank2Pay != null) {
	                            cwBankDTO
	                                    .setUsedMoney(TS_Util
	                                            .ratioTransform(cwBank2Pay
	                                                    .getCur_used_money()
	                                                    .doubleValue()));
	                            cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
	                        }
	                        cwBankDTOList.add(cwBankDTO);
	                    }
	                }
	            } else {
	                List<CwBank2Pay> list2 = cwBank2PayManager
	                        .queryCwBank2Pays(inspectionPayInfo.getId());
	                if (!list2.isEmpty()) {
	                    for (CwBank2Pay cwBank2Pay : list2) {
	                        if (cwBank2Pay != null) {
	                            CwBankDetail cwBankDetail = bankDetailDao
	                                    .get(cwBank2Pay.getFk_cw_bank_id());
	                            if (cwBankDetail != null) {
	                                CwBankDTO cwBankDTO = new CwBankDTO();
	                                BeanUtils.copyProperties(cwBankDTO,
	                                        cwBankDetail);
	                                cwBankDTO.setUsedMoney(TS_Util
	                                        .ratioTransform(cwBank2Pay
	                                                .getCur_used_money()
	                                                .doubleValue()));
	                                cwBankDTO.setPay_remark(cwBank2Pay
	                                        .getPay_remark());
	                                cwBankDTOList.add(cwBankDTO);
	                            }
	                        }
	                    }
	                }
	            }
				report_count += inspectionPayInfo.getReport_count();
				invoice_no.append(","+inspectionPayInfo.getInvoice_no());
				cash_pay += Float.parseFloat(inspectionPayInfo.getCash_pay()==null?"0.0":inspectionPayInfo.getCash_pay());
				pos += Float.parseFloat(inspectionPayInfo.getPos()==null?"0.0":inspectionPayInfo.getPos());
				remark += Float.parseFloat(inspectionPayInfo.getRemark()==null?"0.0":inspectionPayInfo.getRemark());
				pay_received += Float.parseFloat(inspectionPayInfo.getPay_received()==null?"0.0":inspectionPayInfo.getPay_received());
				hand_in += Float.parseFloat(inspectionPayInfo.getHand_in()==null?"0.0":inspectionPayInfo.getHand_in());
				draft += Float.parseFloat(inspectionPayInfo.getDraft()==null?"0.0":inspectionPayInfo.getDraft());
			}
			payInfo.setReport_count(report_count);
			payInfo.setInvoice_no(invoice_no.toString().substring(1));
			payInfo.setCash_pay(String.valueOf(cash_pay));
			payInfo.setPos(String.valueOf(pos));
			payInfo.setRemark(String.valueOf(remark));
			payInfo.setPay_received(String.valueOf(pay_received));
			payInfo.setHand_in(String.valueOf(hand_in));
			payInfo.setDraft(String.valueOf(draft));
			payInfo.setCwBankDTOList(cwBankDTOList);

		}

		return payInfo;
	}
	/**
	 * 根据发票号计算此发票实收总额（用于修改收费）
	 * @param invoice_no
	 * @return
	 */
	public double sumMoneyByInvoiceNo(String invoice_no) {
		return inspectionPayInfoDao.sumMoneyByInvoiceNo(invoice_no);
	}

	public InspectionPayInfo doPrint(String id) throws Exception {
		InspectionPayInfo inspectionPayInfo = this.queryByInspectionInfoID(id); // 报检业务收费信息
		String infoids = id;
		if (inspectionPayInfo == null) {
			inspectionPayInfo = new InspectionPayInfo();
		}else {
			infoids = inspectionPayInfo.getFk_inspection_info_id();
		}
		List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 业务收费信息列表
		String[] ids = id.split(",");
		
		if (StringUtil.isNotEmpty(infoids)) {
			// ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
			String idss = infoids.replace(",", "','");

			String hql = "select i.id,i.sn,i.check_unit_id,t.com_name,t.check_type,o.org_Name, "
					+ " d.device_sort_code,d.device_registration_code,d.device_name,i.advance_fees,i.receivable, "
					+ " i.report_sn ,i.report_com_name,i.advance_remark "
					+ " from tzsb_inspection_info i,base_device_document d,sys_org o,tzsb_inspection t  where t.id = i.fk_inspection_id and "
					+ " d.id = i.fk_tsjc_device_document_id  and o.id(+) = i.check_unit_id " + " and i.id in ('" + idss
					+ "')";

			List<Object> list = inspectionPayInfoDao.createSQLQuery(hql).list();

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
			inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
			 inspectionPayInfo.setPay_received(String.valueOf(receivable));  // 实收总金额
			inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

		}



  // 银行转账
  List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
  if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
      String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
              ",");
      for (int i = 0; i < bank_ids.length; i++) {
          CwBankDetail cwBankDetail = bankDetailDao
                  .get(bank_ids[i]);
          if (cwBankDetail != null) {
              CwBankDTO cwBankDTO = new CwBankDTO();
              BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
              CwBank2Pay cwBank2Pay = cwBank2PayManager
                      .queryCwBank2Pay(bank_ids[i], id);
              if (cwBank2Pay != null) {
                  cwBankDTO
                          .setUsedMoney(TS_Util
                                  .ratioTransform(cwBank2Pay
                                          .getCur_used_money()
                                          .doubleValue()));
                  cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
              }
              cwBankDTOList.add(cwBankDTO);
          }
      }
  } else {
      List<CwBank2Pay> list = cwBank2PayManager
              .queryCwBank2Pays(inspectionPayInfo.getId());
      if (!list.isEmpty()) {
          for (CwBank2Pay cwBank2Pay : list) {
              if (cwBank2Pay != null) {
                  CwBankDetail cwBankDetail = bankDetailDao
                          .get(cwBank2Pay.getFk_cw_bank_id());
                  if (cwBankDetail != null) {
                      CwBankDTO cwBankDTO = new CwBankDTO();
                      BeanUtils.copyProperties(cwBankDTO,
                              cwBankDetail);
                      cwBankDTO.setUsedMoney(TS_Util
                              .ratioTransform(cwBank2Pay
                                      .getCur_used_money()
                                      .doubleValue()));
                      cwBankDTO.setPay_remark(cwBank2Pay
                              .getPay_remark());
                      cwBankDTOList.add(cwBankDTO);
                  }
              }
          }
      }
  }
  inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

  String pay_received = inspectionPayInfo.getPay_received();
  if (StringUtil.isNotEmpty(pay_received)) {
      Double pay = Double.parseDouble(pay_received);
      inspectionPayInfo.setPay_received(String.valueOf(pay));
  }
  String pos = inspectionPayInfo.getPos();
  if (StringUtil.isNotEmpty(pos)) {
      Double pos_money = Double.parseDouble(pos);
      inspectionPayInfo.setPos(String.valueOf(pos_money));
  }
  String cash_pay = inspectionPayInfo.getCash_pay();
  if (StringUtil.isNotEmpty(cash_pay)) {
      Double cash = Double.parseDouble(cash_pay);
      inspectionPayInfo.setCash_pay(String.valueOf(cash));
  }
		return inspectionPayInfo;
	}


public InspectionPayInfo getDetail(String id) throws Exception {
	InspectionPayInfo inspectionPayInfo = this.queryByInspectionInfoID(id); // 报检业务收费信息
	String infoids = id;
	if (inspectionPayInfo == null) {
		inspectionPayInfo = new InspectionPayInfo();
	}else {
		infoids = inspectionPayInfo.getFk_inspection_info_id();
	}
	List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 业务收费信息列表
	String[] ids = id.split(",");
	
	if (StringUtil.isNotEmpty(infoids)) {
		// ids = inspectionPayInfo.getFk_inspection_info_id().split(",");
		String idss = infoids.replace(",", "','");

		String hql = "select i.id,i.sn,i.check_unit_id,t.com_name,t.check_type,o.org_Name, "
				+ " d.device_sort_code,d.device_registration_code,d.device_name,i.advance_fees,i.receivable, "
				+ " i.report_sn ,i.report_com_name,i.advance_remark "
				+ " from tzsb_inspection_info i,base_device_document d,sys_org o,tzsb_inspection t  where t.id = i.fk_inspection_id and "
				+ " d.id = i.fk_tsjc_device_document_id  and o.id(+) = i.check_unit_id " + " and i.id in ('" + idss
				+ "')";

		List<Object> list = inspectionPayInfoDao.createSQLQuery(hql).list();

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
		inspectionPayInfo.setPay_receive(String.valueOf(advance_fees)); // 应收总金额
		 inspectionPayInfo.setPay_received(String.valueOf(receivable));  // 实收总金额
		inspectionPayInfo.setInspectionInfoDTOList(inspectionInfoDTOList);

	}



// 银行转账
List<CwBankDTO> cwBankDTOList = new ArrayList<CwBankDTO>();
if (StringUtil.isNotEmpty(inspectionPayInfo.getFk_cw_bank_id())) {
  String[] bank_ids = inspectionPayInfo.getFk_cw_bank_id().split(
          ",");
  for (int i = 0; i < bank_ids.length; i++) {
      CwBankDetail cwBankDetail = bankDetailDao
              .get(bank_ids[i]);
      if (cwBankDetail != null) {
          CwBankDTO cwBankDTO = new CwBankDTO();
          BeanUtils.copyProperties(cwBankDTO, cwBankDetail);
          CwBank2Pay cwBank2Pay = cwBank2PayManager
                  .queryCwBank2Pay(bank_ids[i], id);
          if (cwBank2Pay != null) {
              cwBankDTO
                      .setUsedMoney(TS_Util
                              .ratioTransform(cwBank2Pay
                                      .getCur_used_money()
                                      .doubleValue()));
              cwBankDTO.setPay_remark(cwBank2Pay.getPay_remark());
          }
          cwBankDTOList.add(cwBankDTO);
      }
  }
} else {
  List<CwBank2Pay> list = cwBank2PayManager
          .queryCwBank2Pays(inspectionPayInfo.getId());
  if (!list.isEmpty()) {
      for (CwBank2Pay cwBank2Pay : list) {
          if (cwBank2Pay != null) {
              CwBankDetail cwBankDetail = bankDetailDao
                      .get(cwBank2Pay.getFk_cw_bank_id());
              if (cwBankDetail != null) {
                  CwBankDTO cwBankDTO = new CwBankDTO();
                  BeanUtils.copyProperties(cwBankDTO,
                          cwBankDetail);
                  cwBankDTO.setUsedMoney(TS_Util
                          .ratioTransform(cwBank2Pay
                                  .getCur_used_money()
                                  .doubleValue()));
                  cwBankDTO.setPay_remark(cwBank2Pay
                          .getPay_remark());
                  cwBankDTOList.add(cwBankDTO);
              }
          }
      }
  }
}
inspectionPayInfo.setCwBankDTOList(cwBankDTOList);

String pay_received = inspectionPayInfo.getPay_received();
if (StringUtil.isNotEmpty(pay_received)) {
  Double pay = Double.parseDouble(pay_received);
  inspectionPayInfo.setPay_received(String.valueOf(pay));
}
String pos = inspectionPayInfo.getPos();
if (StringUtil.isNotEmpty(pos)) {
  Double pos_money = Double.parseDouble(pos);
  inspectionPayInfo.setPos(String.valueOf(pos_money));
}
String cash_pay = inspectionPayInfo.getCash_pay();
if (StringUtil.isNotEmpty(cash_pay)) {
  Double cash = Double.parseDouble(cash_pay);
  inspectionPayInfo.setCash_pay(String.valueOf(cash));
}
	return inspectionPayInfo;
}

	@SuppressWarnings("unchecked")
	public synchronized InspectionPayInfo queryPayInfoCache(HttpServletRequest request,String infoId) {
		ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		ConcurrentHashMap<String,Object> pmap = (ConcurrentHashMap<String,Object>)context.getAttribute("payment");
		if(null == pmap || null == pmap.get(infoId)) 
		{
			return null;
		}
		InspectionPayInfo payInfo = (InspectionPayInfo)pmap.get(infoId);
		pmap.remove(infoId);
		return payInfo;
	}
}
