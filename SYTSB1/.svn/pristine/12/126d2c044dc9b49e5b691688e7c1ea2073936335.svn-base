package com.scts.payment.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.InspectionZZJDInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.InspectionZZJDInfoService;
import com.scts.payment.bean.InspectionInfoDTO;
import com.scts.payment.bean.InspectionPayBack;
import com.scts.payment.bean.InspectionZZJDPayInfoDTO;
import com.scts.payment.service.InspectionPayBackService;

/**
 * 报检业务退款控制器
 * 
 * @ClassName InspectionPayBackAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-05-04 下午04:45:00
 */
@Controller
@RequestMapping("payment/payBack")
public class InspectionPayBackAction extends
		SpringSupportAction<InspectionPayBack, InspectionPayBackService> {
	@Autowired
	private InspectionPayBackService inspectionPayBackService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private OrgManagerImpl orgManager;
	@Autowired
	private InspectionZZJDInfoService inspectionZZJDInfoService;

	// 保存
	@RequestMapping(value = "savePayBack")
	@ResponseBody
	public HashMap<String, Object> savePayBack(
			InspectionPayBack inspectionPayBack) {
		try {
			inspectionPayBackService.save(inspectionPayBack);
			return JsonWrapper.successWrapper(inspectionPayBack);
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("退款失败！");
		}
	}

	// 详情
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public HashMap<String, Object> getDetail(HttpServletRequest request,
			String id, String info_id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			InspectionPayBack inspectionPayBack = new InspectionPayBack();
			if (StringUtil.isNotEmpty(id)) {
				inspectionPayBack = inspectionPayBackService.get(id);
				if (inspectionPayBack == null) {
					inspectionPayBack = new InspectionPayBack();
				}
			}
			List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
			String[] ids = info_id.split(",");
			if (StringUtil.isNotEmpty(inspectionPayBack
					.getFk_inspection_info_id())) {
				ids = inspectionPayBack.getFk_inspection_info_id().split(",");
			}
			//float advance_fees = 0.00f;
			float receivable = 0.00f;
			for (int i = 0; i < ids.length; i++) { // 报检业务信息
				InspectionInfo inspectionInfo = inspectionInfoService
						.get(ids[i]);
				if (inspectionInfo != null) {
					InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
					inspectionInfoDTO.setId(inspectionInfo.getId());
					inspectionInfoDTO
							.setSn(StringUtil
									.isNotEmpty(inspectionInfo.getSn()) ? inspectionInfo
									.getSn()
									: ""); // 业务流水号
					inspectionInfoDTO.setCheck_type(inspectionInfo
							.getInspection().getCheck_type()); // 检验类别
					if (StringUtil
							.isNotEmpty(inspectionInfo.getCheck_unit_id())) {
						Org org = orgManager.get(inspectionInfo
								.getCheck_unit_id());
						inspectionInfoDTO.setCheck_department(StringUtil
								.isNotEmpty(org.getOrgName()) ? org
								.getOrgName() : ""); // 检验部门
					} else {
						inspectionInfoDTO.setCheck_department("");
					}
					DeviceDocument deviceDocument = deviceService
							.get(inspectionInfo.getFk_tsjc_device_document_id());
					inspectionInfoDTO.setDevice_sort_code(deviceDocument
							.getDevice_sort_code()); // 设备类别
					inspectionInfoDTO.setDevice_name(deviceDocument
							.getDevice_name()); // 设备名称
					inspectionInfoDTO
							.setAdvance_fees(inspectionInfo.getAdvance_fees() != null
									&& inspectionInfo.getAdvance_fees() != 0 ? inspectionInfo
									.getAdvance_fees()
									: 0); // 应收金额
					inspectionInfoDTO
							.setReceivable(inspectionInfo.getReceivable() != null
									&& inspectionInfo.getReceivable() != 0 ? inspectionInfo
									.getReceivable()
									: 0); // 实收金额
					//advance_fees += inspectionInfoDTO.getAdvance_fees();	// 预收总金额
					receivable += inspectionInfoDTO.getReceivable();	// 实收总金额
					inspectionInfoDTO
							.setReport_sn(StringUtil.isNotEmpty(inspectionInfo
									.getReport_sn()) ? inspectionInfo
									.getReport_sn() : ""); // 报告书编号
					inspectionInfoDTO
							.setReport_com_name(StringUtil
									.isNotEmpty(inspectionInfo
											.getReport_com_name()) ? inspectionInfo
									.getReport_com_name()
									: ""); // 报告书使用单位
					inspectionInfoDTO
							.setAdvance_remark(StringUtil
									.isNotEmpty(inspectionInfo
											.getAdvance_remark()) ? inspectionInfo
									.getAdvance_remark()
									: ""); // 预收金额备注
					inspectionInfoDTO.setFee_status(inspectionInfo
							.getFee_status());
					inspectionInfoDTOList.add(inspectionInfoDTO);
				}
			}
			inspectionPayBack.setInspectionInfoDTOList(inspectionInfoDTOList);
			wrapper.put("success", true);
			wrapper.put("data", inspectionPayBack);
			wrapper.put("receivable", receivable);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("退款信息查询失败！");
		}
	}
	
	// 详情
	@RequestMapping(value = "getZZJDDetail")
	@ResponseBody
	public HashMap<String, Object> getZZJDDetail(HttpServletRequest request,
			String id, String info_id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			InspectionPayBack inspectionPayBack = new InspectionPayBack();
			if (StringUtil.isNotEmpty(id)) {
				inspectionPayBack = inspectionPayBackService.get(id);
				if (inspectionPayBack == null) {
					inspectionPayBack = new InspectionPayBack();
				}
			}
			List<InspectionZZJDPayInfoDTO> list = new ArrayList<InspectionZZJDPayInfoDTO>(); // 业务收费信息列表
			String[] ids = info_id.split(",");
			if (StringUtil.isNotEmpty(inspectionPayBack
					.getFk_inspection_info_id())) {
				ids = inspectionPayBack.getFk_inspection_info_id().split(",");
			}
			//float advance_fees = 0.00f;
			float receivable = 0.00f;
			for (int i = 0; i < ids.length; i++) { // 报检业务信息
				InspectionInfo inspectionInfo = inspectionInfoService
						.get(ids[i]);
				InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService.getByInfoId(ids[i]);
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
					inspectionZZJDPayInfoDTO
							.setReport_sn(StringUtil.isNotEmpty(inspectionInfo
									.getReport_sn()) ? inspectionInfo
									.getReport_sn() : ""); // 报告书编号
					receivable += inspectionZZJDPayInfoDTO.getReceivable();	// 实收总金额
					list.add(inspectionZZJDPayInfoDTO);
				}
			}
			inspectionPayBack.setInspectionZZJDPayInfoDTOList(list);
			wrapper.put("success", true);
			wrapper.put("data", inspectionPayBack);
			wrapper.put("receivable", receivable);
			return wrapper;
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
			return JsonWrapper.failureWrapper("退款信息查询失败！");
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
				for (int i = 0; i < ids.length; i++) {
					InspectionInfo inspectionInfo = inspectionInfoService
							.get(ids[i]);
					InspectionZZJDInfo inspectionZZJDInfo = inspectionZZJDInfoService.getByInfoId(ids[i]);
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
						inspectionZZJDPayInfoDTO
								.setReport_sn(StringUtil.isNotEmpty(inspectionInfo
										.getReport_sn()) ? inspectionInfo
										.getReport_sn() : ""); // 报告书编号
						list.add(inspectionZZJDPayInfoDTO);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
}
