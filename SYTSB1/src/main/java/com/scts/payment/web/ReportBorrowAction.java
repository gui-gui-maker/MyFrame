package com.scts.payment.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.advicenote.service.MessageService;
import com.lsts.common.dao.CodeTablesDao;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.finance.bean.CwInvoiceF;
import com.lsts.finance.service.CwInvoiceFManager;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.InspectionZZJDInfoService;
import com.lsts.log.service.SysLogService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.service.EnterService;
import com.scts.payment.bean.InspectionInfoDTO;
import com.scts.payment.bean.InspectionZZJDPayInfoDTO;
import com.scts.payment.bean.ReportBorrow;
import com.scts.payment.bean.ReportBorrowDTO;
import com.scts.payment.service.ReportBorrowService;

import util.MoneyUtil;
import util.TS_Util;

/**
 * 外借记录控制器
 * 
 * @ClassName ReportBorrowAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-12 下午05:28:00
 */
@Controller
@RequestMapping("report/borrow")
public class ReportBorrowAction extends
		SpringSupportAction<ReportBorrow, ReportBorrowService> {
	@Autowired
	private ReportBorrowService reportBorrowService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
	private CwInvoiceFManager cwInvoiceFManager;
	@Autowired
	private CodeTablesDao codeTablesDao;
	@Autowired
	private InspectionZZJDInfoService inspectionZZJDInfoService;
	@Autowired
	private SysLogService logService;
	@Autowired
	private EnterService enterService;
	@Autowired
	private MessageService messageService;

	// 外借保存（机电类）
	@RequestMapping(value = "saveBorrow")
	@ResponseBody
	public HashMap<String, Object> saveBorrow(@RequestBody
	ReportBorrow reportBorrow, HttpServletRequest request) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			// 1、外借发票时，验证发票号是否存在
			// 1.1、本次修改的新发票号
			String invoice_no = reportBorrow.getInvoice_no();
			// 1.2、原发票号
			String old_invoice_no = "";
			
			boolean isAdd = true;
			String status = request.getParameter("status");
			if(!"add".equals(status)){
				isAdd = false;
			}
			
			double old_borrow_money = 0;
			if(StringUtil.isNotEmpty(reportBorrow.getId())){
				old_invoice_no = reportBorrowService
						.queryInvoice_no(reportBorrow.getId());
				old_borrow_money = reportBorrowService.queryMoney(reportBorrow.getId());
			}
			
			// 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
			if ("1".equals(reportBorrow.getBorrow_type()) || 
					"2".equals(reportBorrow.getBorrow_type())) {
				if(StringUtil.isNotEmpty(invoice_no)){
					CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(invoice_no);
					if(cwInvoiceF == null){
						return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（"+invoice_no+"），请联系财务人员导入！");
					} else {
						if ("SY".equals(cwInvoiceF.getStatus())) {
							if (reportBorrow.getBorrow_date().equals(cwInvoiceF.getInvoiceDate())
									&& reportBorrow.getCom_name().equals(cwInvoiceF.getInvoiceUnit())) {
								// 相同开票单位和相同开票日期允许重复登记收费，反之
							}else{
								if(isAdd){
									return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
								}else{
									if(StringUtil.isNotEmpty(old_invoice_no)){
										if(!invoice_no.equals(old_invoice_no)){
											return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
										}
									}
								}
							}
						}
						
						if ("ZF".equals(cwInvoiceF.getStatus())) {
							return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
						}
					}
				}
			}
			
			// 2、更新检验业务收费信息
			String report_com_name = "";	// 报告书使用单位（受检单位）
			String check_department = "";	// 检验部门
			for (InspectionInfoDTO inspectionInfoDTO : reportBorrow
					.getInspectionInfoDTOList()) {
				InspectionInfo inspectionInfo = inspectionInfoService
						.get(inspectionInfoDTO.getId());
				// 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
				if ("0".equals(reportBorrow.getBorrow_type())) { 
					inspectionInfo.setIs_borrow("2"); // 是否预借报告书 2：是
					inspectionInfo.setFee_status("3"); // 收费状态(0 默认 1 待收费 2 已收费
														// 3 借报告 4 借发票 5 借报告和发票)
				} else if ("1".equals(reportBorrow.getBorrow_type())) {
					inspectionInfo.setFee_status("4");
				} else {
					inspectionInfo.setIs_borrow("2"); // 是否预借报告书 2：是
					inspectionInfo.setFee_status("5");
				}
				if (StringUtil.isNotEmpty(reportBorrow.getInvoice_no())) {
					inspectionInfo.setInvoice_no(reportBorrow.getInvoice_no()); // 发票号
				}
				inspectionInfo.setBorrow_date(reportBorrow.getBorrow_date());	// 外借日期
				inspectionInfoService.save(inspectionInfo); // 1、保存业务信息（更新收费状态等）
				if(StringUtil.isEmpty(report_com_name)){
					report_com_name = inspectionInfo.getReport_com_name();
				}
				if(StringUtil.isEmpty(check_department)){
					if (StringUtil.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
						Org org = orgManager.get(inspectionInfo
									.getCheck_unit_id());
						check_department = StringUtil
									.isNotEmpty(org.getOrgName()) ? org
									.getOrgName() : ""; // 检验部门
					}
				}
				logService.setLogs(inspectionInfoDTO.getId(), "外借登记",
						"外借登记", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}
			
			// 3、保存外借记录
			if (StringUtil.isNotEmpty(reportBorrow.getInvoice_no())) {
				reportBorrow.setBorrow_status("2"); // 状态，数据字典：TZSB_BORROW_STATUS（0：申请中，1：已批准，2：已借出）
			} else {
				reportBorrow.setBorrow_status("0");
			}
			reportBorrow.setOperator_name(user.getName()); 	// 操作人
			reportBorrow.setOperator_date(new Date()); 		// 操作时间
			reportBorrowService.save(reportBorrow); // 2、保存外借记录
			if(!isAdd){
				// 3.1、写日志
				logService.setLogs(reportBorrow.getId(), "修改借票",
						"修改借票，使用发票（发票号："+reportBorrow.getInvoice_no()+"）", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}
			
			// 4、返写发票信息（外借发票、外借报告和发票时返写发票信息，外借报告无发票号，不返写发票信息）
			if(!"0".equals(reportBorrow.getBorrow_type())){
				if(StringUtil.isNotEmpty(invoice_no)){
					if(StringUtil.isEmpty(old_invoice_no)){
						// 5.1 返写更新本次使用的新发票号信息
						updateCwInvoiceF(request, reportBorrow, check_department, isAdd);
					}else{
						if (!invoice_no.equals(old_invoice_no)) {
							// 5.2 返写更新原发票号信息
							// 原票号是否作废（0：否 1：是）
							String if_del_invoice = request.getParameter("if_del_invoice");
							if("1".equals(if_del_invoice)){
								zfOldCwInvoiceF(request, old_invoice_no, check_department);
							}else{
								updateOldCwInvoiceF(request, old_invoice_no, old_borrow_money, check_department);
							}
						}
						updateCwInvoiceF(request, reportBorrow, check_department, isAdd);
					}
				}
			}
			
			return JsonWrapper.successWrapper(reportBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("外借记录保存失败！");
		}
	}
	
	// 借票保存（承压类，目前只有借票没有借报告，2015-12-12）
	@RequestMapping(value = "saveBorrowCY")
	@ResponseBody
	public HashMap<String, Object> saveBorrowCY(@RequestBody
	ReportBorrow reportBorrow, HttpServletRequest request) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			// 1、外借发票时，验证发票号是否存在
			// 1.1、本次修改的新发票号
			String invoice_no = reportBorrow.getInvoice_no();
			// 1.2、原发票号
			String old_invoice_no = "";
			
			boolean isAdd = true;
			String status = request.getParameter("status");
			if(!"add".equals(status)){
				isAdd = false;
			}
			
			double old_borrow_money = 0;
			if(StringUtil.isNotEmpty(reportBorrow.getId())){
				old_invoice_no = reportBorrowService
						.queryInvoice_no(reportBorrow.getId());
				old_borrow_money = reportBorrowService.queryMoney(reportBorrow.getId());
			}
			
			// 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
			if ("1".equals(reportBorrow.getBorrow_type()) || "2".equals(reportBorrow.getBorrow_type())) {
				if(StringUtil.isNotEmpty(invoice_no)){
					CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(invoice_no);
					if(cwInvoiceF == null){
						return JsonWrapper.failureWrapperMsg("亲，系统未能识别发票号（"+invoice_no+"），请联系财务人员导入！");
					} else {
						if ("SY".equals(cwInvoiceF.getStatus())) {
							if (reportBorrow.getBorrow_date().equals(cwInvoiceF.getInvoiceDate())
									&& reportBorrow.getCom_name().equals(cwInvoiceF.getInvoiceUnit())) {
								// 相同开票单位和相同开票日期允许重复登记收费，反之
                            	// 经确认通用借票不存在同一票号重复登记的情况（2019/01/18）
							}else{
								if(isAdd){
									return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
								}else{
									if(StringUtil.isNotEmpty(old_invoice_no)){
										if(!invoice_no.equals(old_invoice_no)){
											return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经使用，请核实！");
										}
									}
								}
							}
						}
						if ("ZF".equals(cwInvoiceF.getStatus())) {
							return JsonWrapper.failureWrapperMsg("亲，该发票号（" + invoice_no + "）已经作废，不能使用，请核实！");
						}
					}
				}else{
					return JsonWrapper.failureWrapperMsg("亲，发票号是必填项，请填写发票号！");
				}
			}	
			// 2、更新外借记录
			String check_dep_name = "";
			if(StringUtil.isEmpty(reportBorrow.getCheck_dep_name())){
				if (StringUtil.isNotEmpty(reportBorrow.getCheck_dep_id())) {
					Org org = orgManager.get(reportBorrow.getCheck_dep_id());
					check_dep_name = StringUtil
										.isNotEmpty(org.getOrgName()) ? org
										.getOrgName() : ""; 
					reportBorrow.setCheck_dep_name(check_dep_name);
				}
					
			}
			if(StringUtil.isEmpty(reportBorrow.getDevice_type())){
				reportBorrow.setDevice_type("CY");	// 承压借票
			}
			reportBorrow.setBorrow_status("2"); // 状态，数据字典：TZSB_BORROW_STATUS（0：申请中 1：已批准 2：已借出 3：已取消外借）
			reportBorrow.setOperator_user_id(user.getId());	// 操作人ID
			reportBorrow.setOperator_name(user.getName()); 	// 操作人姓名
			reportBorrow.setOperator_date(new Date()); 		// 操作时间
			reportBorrowService.save(reportBorrow); // 4、保存外借记录
			if(!isAdd){	
				// 3.1、写日志
				logService.setLogs(reportBorrow.getId(), "修改借票",
						"通用借票，使用发票（发票号："+reportBorrow.getInvoice_no()+"）", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}
			
			
			// 5、返写发票信息（外借发票时返写发票信息）
			if(!"0".equals(reportBorrow.getBorrow_type())){
				if(StringUtil.isNotEmpty(invoice_no)){
					if(StringUtil.isEmpty(old_invoice_no)){
						// 5.1 返写更新本次使用的新发票号信息
						updateCwInvoiceF(request, reportBorrow, check_dep_name, isAdd);
					}else{
						if (!invoice_no.equals(old_invoice_no)) {
							// 5.2 返写更新原发票号信息
							// 原票号是否作废（0：否 1：是）
							String if_del_invoice = request.getParameter("if_del_invoice");
							if("1".equals(if_del_invoice)){
								zfOldCwInvoiceF(request, old_invoice_no, check_dep_name);
							}else{
								updateOldCwInvoiceF(request, old_invoice_no, old_borrow_money, check_dep_name);
							}
						}
						updateCwInvoiceF(request, reportBorrow, check_dep_name, isAdd);
					}
				}
			}
			
			return JsonWrapper.successWrapper(reportBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("承压借票保存失败！");
		}
	}
	
	// 更新发票为已使用状态
	private void updateCwInvoiceF(HttpServletRequest request, ReportBorrow reportBorrow, String check_dep_name,
			boolean isAdd) {
		try {
			CurrentSessionUser user = super.getCurrentUser();

			// 1.1 获取发票基本信息
			CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(reportBorrow.getInvoice_no());
			// 1.2 更新发票金额
			if(isAdd) {
				cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform(
						(cwInvoiceF.getInvoiceMoney().doubleValue() + Double.parseDouble(reportBorrow.getUnpay_amount()))));
			}else {
				cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform(Double.parseDouble(reportBorrow.getUnpay_amount())));
			}
			// 1.3 更新发票交易类型
			cwInvoiceF.setMoneyType("");
			if (StringUtil.isEmpty(reportBorrow.getCom_name())) {
				EnterInfo enterInfo = enterService.get(reportBorrow.getFk_company_id());
				cwInvoiceF.setInvoiceUnit(enterInfo.getCom_name()); // 外借单位 to
																	// 开票单位
			} else {
				cwInvoiceF.setInvoiceUnit(reportBorrow.getCom_name()); // 外借单位
																		// to
																		// 开票单位
			}
			cwInvoiceF.setCheckoutUnit(""); // 受检单位
			cwInvoiceF.setCheckoutDep(check_dep_name);
			cwInvoiceF.setStatus("SY"); // 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用）
			cwInvoiceF.setInvoice_id(user.getId()); // 开票人id
			cwInvoiceF.setInvoice_name(user.getName()); // 开票人name
			cwInvoiceF.setInvoiceDate(new Date()); // 开票日期
			cwInvoiceFManager.save(cwInvoiceF);

			String borrow_name = "1".equals(reportBorrow.getBorrow_type()) ? "外借发票" : "";
			borrow_name += isAdd ? "，使用发票" : "修改外借，更新使用发票";
			logService.setLogs(cwInvoiceF.getId(), borrow_name,
					borrow_name + "（发票号：" + reportBorrow.getInvoice_no() + "）", user.getId(), user.getName(),
					new Date(), request.getRemoteAddr());
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
	}
	
	// 更新发票为外借取消状态
	private void updateOldCwInvoiceF(HttpServletRequest request, String old_invoice_no, double old_borrow_money,
			String check_dep_name) {
		try {
			CurrentSessionUser user = super.getCurrentUser();

			CwInvoiceF old_cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(old_invoice_no);
			if (old_cwInvoiceF != null) {
				// 发票金额
				old_cwInvoiceF.setInvoiceMoney(
						TS_Util.ratioTransform(old_cwInvoiceF.getInvoiceMoney().doubleValue() - old_borrow_money));
				// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 WJQX：外借取消）
				old_cwInvoiceF.setStatus("WJQX");
				cwInvoiceFManager.save(old_cwInvoiceF);

				logService.setLogs(old_cwInvoiceF.getId(), "借票取消", "取消使用发票号：" + old_invoice_no, user.getId(),
						user.getName(), new Date(), request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
	}
	
	// 更新发票为作废状态
	private void zfOldCwInvoiceF(HttpServletRequest request, String old_invoice_no,
			String check_dep_name) {
		try {
			CurrentSessionUser user = super.getCurrentUser();

			CwInvoiceF old_cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(old_invoice_no);
			if (old_cwInvoiceF != null) {
				// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 WJQX：外借取消）
				old_cwInvoiceF.setStatus("WJQX");
				cwInvoiceFManager.save(old_cwInvoiceF);

				logService.setLogs(old_cwInvoiceF.getId(), "借票取消", "取消使用发票号：" + old_invoice_no, user.getId(),
						user.getName(), new Date(), request.getRemoteAddr());
			}
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
	}

	// 保存
	@RequestMapping(value = "saveZZJDBorrow")
	@ResponseBody
	public HashMap<String, Object> saveZZJDBorrow(@RequestBody
	ReportBorrow reportBorrow, HttpServletRequest request) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			// 1、更新检验业务收费信息
			String report_com_name = "";	// 报告书使用单位（受检单位）
			for (InspectionZZJDPayInfoDTO inspectionZZJDPayInfoDTO : reportBorrow
					.getInspectionZZJDPayInfoDTOList()) {
				InspectionInfo inspectionInfo = inspectionInfoService
						.get(inspectionZZJDPayInfoDTO.getId());
				if ("0".equals(reportBorrow.getBorrow_type())) { // 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
					inspectionInfo.setIs_borrow("2"); // 是否预借报告书 2：是
					inspectionInfo.setFee_status("3"); // 收费状态(0 默认 1 待收费 2 已收费
														// 3 借报告 4 借发票 5 借报告和发票)
				} else if ("1".equals(reportBorrow.getBorrow_type())) {
					inspectionInfo.setFee_status("4");
				} else {
					inspectionInfo.setIs_borrow("2"); // 是否预借报告书 2：是
					inspectionInfo.setFee_status("5");
				}
				if (StringUtil.isNotEmpty(reportBorrow.getInvoice_no())) {
					inspectionInfo.setInvoice_no(reportBorrow.getInvoice_no()); // 发票号
				}
				inspectionInfo.setBorrow_date(reportBorrow.getBorrow_date());	// 外借日期
				inspectionInfoService.save(inspectionInfo); // 1、保存业务信息（更新收费状态等）
				if(StringUtil.isEmpty(report_com_name)){
					report_com_name = inspectionInfo.getReport_com_name();
				}
				logService.setLogs(inspectionZZJDPayInfoDTO.getId(), "外借登记",
						"外借登记", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
			}
			
			// 2、保存外借记录
			if (StringUtil.isNotEmpty(reportBorrow.getInvoice_no())) {
				reportBorrow.setBorrow_status("2"); // 状态，数据字典：TZSB_BORROW_STATUS（0：申请中，1：已批准，2：已借出）
			} else {
				reportBorrow.setBorrow_status("0");
			}
			reportBorrow.setOperator_user_id(user.getId());
			reportBorrow.setOperator_name(user.getName()); // 操作人
			reportBorrow.setOperator_date(new Date()); // 操作时间
			reportBorrowService.save(reportBorrow); // 2、保存外借记录
			
			// 3、返写发票信息（外借发票、外借报告和发票时返写发票信息，外借报告无发票号，不返写发票信息）
			if(!"0".equals(reportBorrow.getBorrow_type())){
				String invoice_no = reportBorrow.getInvoice_no();
				if(StringUtil.isNotEmpty(invoice_no)){
					String borrow_name = "1".equals(reportBorrow.getBorrow_type())?"外借发票":"外借报告和发票";
					CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(invoice_no);
					// 发票金额
					cwInvoiceF.setInvoiceMoney(new BigDecimal(reportBorrow.getUnpay_amount()));
					// 交易类型
					cwInvoiceF.setMoneyType("");
					if(StringUtil.isEmpty(reportBorrow.getCom_name())){
						String[] fk_com_id = reportBorrow.getFk_company_id().split(",");
						EnterInfo enterInfo = enterService.get(fk_com_id[0]);
						cwInvoiceF.setInvoiceUnit(enterInfo.getCom_name());		// 外借单位 to 开票单位
					}else{
						cwInvoiceF.setInvoiceUnit(reportBorrow.getCom_name());	// 外借单位 to 开票单位
					}
					cwInvoiceF.setCheckoutUnit(report_com_name);	// 开票单位
					cwInvoiceF.setStatus("SY");	// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用）
					cwInvoiceF.setInvoice_id(user.getId());		// 开票人id
					cwInvoiceF.setInvoice_name(user.getName());	// 开票人name
					cwInvoiceFManager.save(cwInvoiceF);
					logService.setLogs(cwInvoiceF.getId(), borrow_name,
							borrow_name+"（发票号："+invoice_no+"）", user.getId(), user.getName(),
							new Date(), request.getRemoteAddr());
				}
			}
			
			return JsonWrapper.successWrapper(reportBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("外借记录保存失败！");
		}
	}

	// 取消外借
	@RequestMapping(value = "delBorrow")
	@ResponseBody
	public HashMap<String, Object> delBorrow(String info_id, HttpServletRequest request) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			// 1、取消外借时，更新检验业务收费信息
			ReportBorrow reportBorrow = reportBorrowService
					.queryByInspectionInfoID(info_id);
			if(reportBorrow!=null){
				if(StringUtil.isNotEmpty(reportBorrow.getFk_inspection_info_id())){
					String[] info_ids = reportBorrow.getFk_inspection_info_id().split(",");
					for (int i = 0; i < info_ids.length; i++) {
						if(StringUtil.isNotEmpty(info_ids[i])){
							InspectionInfo inspectionInfo = inspectionInfoService
									.get(info_ids[i]);
							if(inspectionInfo!=null){
								// 外借类型，数据字典：TZSB_BORROW_TYPE（0：外借报告，1：外借发票，2：外借报告和发票）
								inspectionInfo.setIs_borrow(""); 
								inspectionInfo.setFee_status("1"); 	// 收费状态(0 默认 1 待收费 2 已收费 3 借报告 4 借发票 5 借报告和发票)
								inspectionInfo.setInvoice_no("");	// 发票号
								inspectionInfo.setBorrow_date(null);// 外借日期
								inspectionInfoService.save(inspectionInfo); // 1、保存业务信息（还原收费状态等）
								logService.setLogs(info_ids[i], "取消外借", 
										"取消外借", user.getId(), user.getName(), new Date(), request.getRemoteAddr());
							}
						}
					}
				}
			}
			
			
			// 2、取消外借，保存外借记录
			reportBorrow.setBorrow_status("3"); // 状态，数据字典：TZSB_BORROW_STATUS（0：申请中
												// 1：已批准 2：已借出 3：已取消外借）
			reportBorrow.setOperator_user_id(user.getId());
			reportBorrow.setOperator_name(user.getName()); // 操作人
			reportBorrow.setOperator_date(new Date()); // 操作时间
			reportBorrowService.save(reportBorrow); // 2、保存外借记录
			
			// 3、取消外借（外借发票、外借报告和发票）时，修改发票使用状态
			if(!"0".equals(reportBorrow.getBorrow_type())){
				String invoice_no = reportBorrow.getInvoice_no();
				if(StringUtil.isNotEmpty(invoice_no)){
					CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(invoice_no);
					// 发票金额
					cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform(
							cwInvoiceF.getInvoiceMoney().doubleValue()-
							Double.parseDouble(reportBorrow.getUnpay_amount())));
					cwInvoiceF.setStatus("WJQX");	// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消 WJQX：外借取消）
					cwInvoiceFManager.save(cwInvoiceF);
					logService.setLogs(cwInvoiceF.getId(), "外借取消",
							"外借取消，还原发票（发票号："+invoice_no+"）", user.getId(), user.getName(),
							new Date(), request.getRemoteAddr());
				}
			}
			
			return JsonWrapper.successWrapper(reportBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("取消外借失败！");
		}
	}
	
	// 取消外借（通用借票取消）
	@RequestMapping(value = "delBorrowCY")
	@ResponseBody
	public HashMap<String, Object> delBorrowCY(String ids, HttpServletRequest request) {
		try {
			CurrentSessionUser user = super.getCurrentUser();
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				// 1、取消外借时，获取外借信息
				ReportBorrow reportBorrow = reportBorrowService.get(idArr[i]);
				// 2、取消外借，保存外借记录
				reportBorrow.setBorrow_status("3"); // 状态，数据字典：TZSB_BORROW_STATUS（0：申请中
													// 1：已批准 2：已借出 3：已取消外借）
				reportBorrow.setOperator_user_id(user.getId());
				reportBorrow.setOperator_name(user.getName()); 	// 操作人
				reportBorrow.setOperator_date(new Date()); 		// 操作时间
				reportBorrowService.save(reportBorrow); 		// 2、保存外借记录
				
				// 3、写日志
				logService.setLogs(reportBorrow.getId(), "取消借票",
						"取消借票，还原发票（发票号："+reportBorrow.getInvoice_no()+"）", user.getId(), user.getName(),
						new Date(), request.getRemoteAddr());
				
				// 4、取消外借（1：外借发票）时，修改发票使用状态
				if("1".equals(reportBorrow.getBorrow_type())){
					String invoice_no = reportBorrow.getInvoice_no();
					if(StringUtil.isNotEmpty(invoice_no)){
						CwInvoiceF cwInvoiceF = cwInvoiceFManager.queryByInvoice_no(invoice_no);
						// 发票金额
						cwInvoiceF.setInvoiceMoney(TS_Util.ratioTransform(
								cwInvoiceF.getInvoiceMoney().doubleValue()-
								Double.parseDouble(reportBorrow.getUnpay_amount())));
						// 若取消外借后金额为0则清除相应数据
                        if(cwInvoiceF.getInvoiceMoney().compareTo(new BigDecimal("0"))==0) {
                        	cwInvoiceF.setMoneyType("");	// 交易类型
                        	cwInvoiceF.setInvoiceUnit("");	// 开票单位
                        	cwInvoiceF.setCheckoutUnit("");	// 受检单位
                        	cwInvoiceF.setCheckoutDep("");	// 检验部门
                        	cwInvoiceF.setInvoice_id("");	// 开票人id
                        	cwInvoiceF.setInvoice_name("");	// 开票人name
                        	cwInvoiceF.setInvoiceDate(null);	// 开票日期
                        }
						cwInvoiceF.setStatus("WJQX");	// 发票状态（WSY：未使用 SY：使用 ZF：作废 LY：领用 SFQX：收费取消 WJQX：外借取消）
						cwInvoiceFManager.save(cwInvoiceF);
						logService.setLogs(cwInvoiceF.getId(), "借票取消",
								"借票取消，还原发票（发票号："+invoice_no+"）", user.getId(), user.getName(),
								new Date(), request.getRemoteAddr());
					}
				}
			}
			return JsonWrapper.successWrapper(ids);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("取消借票失败！");
		}
	}

	// 详情
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request,
			String id, String status) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			ReportBorrow reportBorrow = reportBorrowService.getDetail(id);
			wrapper.put("success", true);
			wrapper.put("data", reportBorrow);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("业务收费信息查询失败！");
		}
	}
	
	// 承压借票详情
	@RequestMapping(value = "getCYDetail")
	@ResponseBody
	public HashMap<String, Object> getCYDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {			
			return this.detail(request, id);
		} catch (Exception e) {
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("读取借票信息失败！");
		}
	}
	
	/**
	 * 导出借票（承压）
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportBorrowCY")
	public String exportBorrowCY(HttpServletRequest request) {
		try {
			String check_dep_name = "";
			String check_unit_id = request.getParameter("org_id");
			String borrow_start_date = request
					.getParameter("borrow_start_date");
			String borrow_end_date = request.getParameter("borrow_end_date");
			float total = 0;
			List<ReportBorrowDTO> list = reportBorrowService.exportBorrowCY(
					check_unit_id, borrow_start_date, borrow_end_date);
			if (!list.isEmpty()) {
				for (ReportBorrowDTO reportBorrowDTO : list) {
					total += Float
							.parseFloat(reportBorrowDTO.getUnpay_amount());
					if (StringUtil.isEmpty(check_dep_name)) {
						check_dep_name = reportBorrowDTO.getCheck_department();
					}
				}
			}
			request.getSession().setAttribute("check_dep_name", check_dep_name); // 检验部门
			request.getSession().setAttribute("data", list); 	// 承压借票记录
			request.getSession().setAttribute("total", total); 	// 金额合计
		} catch (Exception e) {
			e.printStackTrace();
			log.error("借发票导出失败！");
		}
		return "app/payment/export_borrow_cy";
	}

	// 详情
	@RequestMapping(value = "getZZJDDetail")
	@ResponseBody
	public HashMap<String, Object> getZZJDDetail(HttpServletRequest request,
			String id, String status) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			ReportBorrow reportBorrow = reportBorrowService
					.queryByInspectionInfoID(id); // 报检业务收费信息
			if (reportBorrow == null) {
				reportBorrow = new ReportBorrow();
			}
			List<InspectionZZJDPayInfoDTO> list = new ArrayList<InspectionZZJDPayInfoDTO>(); // 业务收费信息列表
			String[] ids = id.split(",");
			if (StringUtil.isNotEmpty(reportBorrow.getFk_inspection_info_id())) {
				ids = reportBorrow.getFk_inspection_info_id().split(",");
			}
			float advance_fees = 0.00f;
			for (int i = 0; i < ids.length; i++) { // 报检业务信息
				InspectionInfo inspectionInfo = inspectionInfoService
						.get(ids[i]);
				InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
						.getByInfoId(ids[i]);
				if (inspectionInfo != null) {
					InspectionZZJDPayInfoDTO inspectionZZJDPayInfoDTO = new InspectionZZJDPayInfoDTO();
					BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
							inspectionInfo);
					BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
							inspectionZZJDInfo);
					inspectionZZJDPayInfoDTO.setId(inspectionInfo.getId());
					inspectionZZJDPayInfoDTO
							.setSn(StringUtil
									.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo
									.getSn()
									: ""); // 业务流水号
					inspectionZZJDPayInfoDTO
							.setAdvance_fees(inspectionInfo.getAdvance_fees() != null
									&& inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
									.getAdvance_fees()
									: 0); // 应收金额
					inspectionZZJDPayInfoDTO
							.setReceivable(inspectionInfo.getReceivable() != null
									&& inspectionInfo.getReceivable() != 0 ? inspectionInfo
									.getReceivable()
									: 0); // 实收金额
					advance_fees += inspectionZZJDPayInfoDTO.getAdvance_fees();
					inspectionZZJDPayInfoDTO
							.setReport_sn(StringUtil.isNotEmpty(inspectionInfo
									.getReport_sn()) ? inspectionInfo
									.getReport_sn() : ""); // 报告书编号
					list.add(inspectionZZJDPayInfoDTO);
				}
			}
			if (StringUtil.isEmpty(reportBorrow.getUnpay_amount())) {
				reportBorrow.setUnpay_amount(String.valueOf(advance_fees)); // 应收总金额（欠款金额）
			}
			reportBorrow.setUnpay_amount_uppercase(MoneyUtil
					.toChinese(reportBorrow.getUnpay_amount()));
			reportBorrow.setInspectionZZJDPayInfoDTOList(list);
			wrapper.put("success", true);
			wrapper.put("data", reportBorrow);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("业务收费信息查询失败！");
		}
	}

	// 查询报检业务信息
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getInspectionInfoList")
	@ResponseBody
	public HashMap<String, Object> getInspectionInfoList(
			HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
		try {
			if (StringUtil.isNotEmpty(id)) {
				String[] ids = id.split(",");
				ReportBorrow reportBorrow = reportBorrowService
						.queryByInspectionInfoID(id); // 报检业务收费信息
				if (StringUtil.isNotEmpty(reportBorrow
						.getFk_inspection_info_id())) {
					ids = reportBorrow.getFk_inspection_info_id().split(",");
				}
				for (int i = 0; i < ids.length; i++) {
					InspectionInfo inspectionInfo = inspectionInfoService
							.get(ids[i]);
					if (inspectionInfo != null) {
						InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
						BeanUtils.copyProperties(inspectionInfoDTO,
								inspectionInfo);
						inspectionInfoDTO.setCom_name(inspectionInfo
								.getInspection().getCom_name());
						inspectionInfoDTO.setCheck_type(inspectionInfo
								.getInspection().getCheck_type()); // 检验类别
						DeviceDocument deviceDocument = deviceService
								.get(inspectionInfo
										.getFk_tsjc_device_document_id());
						inspectionInfoDTO.setDevice_sort_code(deviceDocument
								.getDevice_sort_code()); // 设备类别
						inspectionInfoDTO.setDevice_name(deviceDocument
								.getDevice_name()); // 设备名称
						inspectionInfoDTOList.add(inspectionInfoDTO);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", inspectionInfoDTOList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}

	// 查询报检业务信息
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getInspectionZZJDInfoList")
	@ResponseBody
	public HashMap<String, Object> getInspectionZZJDInfoList(
			HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<InspectionZZJDPayInfoDTO> list = new ArrayList<InspectionZZJDPayInfoDTO>(); // 业务收费信息列表
		try {
			if (StringUtil.isNotEmpty(id)) {
				String[] ids = id.split(",");
				ReportBorrow reportBorrow = reportBorrowService
						.queryByInspectionInfoID(id); // 报检业务收费信息
				if (StringUtil.isNotEmpty(reportBorrow
						.getFk_inspection_info_id())) {
					ids = reportBorrow.getFk_inspection_info_id().split(",");
				}
				for (int i = 0; i < ids.length; i++) {
					InspectionInfo inspectionInfo = inspectionInfoService
							.get(ids[i]);
					InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService
							.getByInfoId(ids[i]);

					if (inspectionInfo != null) {
						InspectionZZJDPayInfoDTO inspectionZZJDPayInfoDTO = new InspectionZZJDPayInfoDTO();
						BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
								inspectionInfo);
						BeanUtils.copyProperties(inspectionZZJDPayInfoDTO,
								inspectionZZJDInfo);
						inspectionZZJDPayInfoDTO.setId(inspectionInfo.getId());
						inspectionZZJDPayInfoDTO
								.setSn(StringUtil.isNotEmpty(inspectionInfo
										.getSn()) ? inspectionInfo.getSn() : ""); // 业务流水号
						inspectionZZJDPayInfoDTO
								.setAdvance_fees(inspectionInfo
										.getAdvance_fees() != null
										&& inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
										.getAdvance_fees()
										: 0); // 应收金额
						inspectionZZJDPayInfoDTO
								.setReceivable(inspectionInfo.getReceivable() != null
										&& inspectionInfo.getReceivable() != 0 ? inspectionInfo
										.getReceivable()
										: 0); // 实收金额
						inspectionZZJDPayInfoDTO
								.setReport_sn(StringUtil
										.isNotEmpty(inspectionInfo
												.getReport_sn()) ? inspectionInfo
										.getReport_sn()
										: ""); // 报告书编号
						list.add(inspectionZZJDPayInfoDTO);
					}
					wrapper.put("success", true);
					wrapper.put("datalist", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	
	// 查询借票日志信息
	@RequestMapping(value = "getFlowStep")
	@ResponseBody
	public ModelAndView getFlowStep(HttpServletRequest request)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = reportBorrowService
				.getFlowStep(request.getParameter("id"));
		ModelAndView mav = new ModelAndView("app/payment/flow_card", map);
		return mav;
	}
	
	// 发送外借查询提醒消息
	@RequestMapping(value = "sendMsg")
	@ResponseBody
	public HashMap<String, Object> sendMsg(HttpServletRequest request,String id)
			throws Exception {
		ReportBorrow reportBorrow = reportBorrowService.queryByInspectionInfoID(id); // 报检业务收费信息
		String borrow_name = reportBorrow.getBorrow_name();//欠款人
		String contack_number = reportBorrow.getContack_number();//联系电话
		Date repay_date = reportBorrow.getRepay_date();//还款日期
		String com_name=null;
		String com_name_temp=null;
		
		String[] ids = id.split(",");
		if (StringUtil.isNotEmpty(reportBorrow.getFk_inspection_info_id())) {
			ids = reportBorrow.getFk_inspection_info_id().split(",");
			for (int i = 0; i < ids.length; i++) { // 报检业务信息
				InspectionInfo inspectionInfo = inspectionInfoService.get(ids[i]);
				com_name_temp=inspectionInfo.getReport_com_name();
				com_name=com_name!=null?(com_name.indexOf(com_name_temp)==-1?(com_name+"、"+com_name_temp):com_name):com_name_temp;
				}
		}
		messageService.sendMoMsg(request, reportBorrow.getId(), 
				"尊敬的"+borrow_name+",您在四川省特种设备检验研究院办理的“"+com_name+"”报告/发票 外借事宜将于"+(repay_date!=null?repay_date.getYear():"-")+"年"+(repay_date!=null?repay_date.getMonth():"-")+"月"+(repay_date!=null?repay_date.getDay():"-")+"日到达还款日期，请于此日前来我院办理销账。详情咨询028-86607899。（四川特检）", 
				contack_number);
		return JsonWrapper.successWrapper();
	}
}
