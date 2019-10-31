package com.lsts.device.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.QueryCondition;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.DeviceWarningDeal;
import com.lsts.device.bean.DeviceWarningStatus;
import com.lsts.device.service.DeviceService;
import com.lsts.device.service.DeviceWarningService;
import com.lsts.device.service.DeviceWarningStatusService;
import com.lsts.inspection.service.InspectionInfoService;

/**
 * 
 * @author liming
 *
 */
@Controller
@RequestMapping("device/deal")
public class DeviceDealAction extends SpringSupportAction<DeviceWarningDeal, DeviceWarningService> {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceWarningStatusService deviceWarningStatusService;
	@Autowired
	private DeviceWarningService devicewarningService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	
	 /**
		 * 添加处理记录
		 * 
		 * @param request
		 * @param id --
		 * @return
		 */
		@RequestMapping(value = "saveDealRecord")
		@ResponseBody
		public HashMap<String, Object> saveDealRecord(HttpServletRequest request, String id,@RequestBody DeviceWarningDeal recordDeal) {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			try {
				/*
				String inspection_date=request.getParameter("inspection_date");
				record.setDeal_man(user.getUserName());
				record.setDeal_man_id(user.getId());
				record.setDeal_unit(user.getUnit().getOrgName());
				record.setDeal_unit_id(user.getUnit().getId());
				record.setDeal_time(new Date());
				 String fk_app_id = record.getFk_base_device_document_id();
			      String[] idArray1 = Util.splitString(fk_app_id, ",");
			      String status=record.getDeal_status();
			      SimpleDateFormat format1=new SimpleDateFormat();
			        for (int i = 0; i < idArray.length; i++) {
			        	record.setFk_base_device_document_id( idArray[i]);
			        	QueryCondition qc=new QueryCondition(DeviceWarningStatus.class);
			        	qc.addCondition("fk_base_device_document_id", "=",  idArray[i]);
			        	qc.addCondition("active_flag", "=", '1');
			        	List<DeviceWarningStatus> dwslist=deviceWarningStatusService.query(qc);
			        	
			        	// 获取设备检验日期
			        	String inspection_date = inspectionInfoService.queryInspection_date(idArray[i]);
			        	
			        	if(dwslist.size()>0){
			        		DeviceWarningStatus dws=dwslist.get(0);
			        		dws.setDeal_status(record.getDeal_status());
			        		dws.setDeal_time(record.getDeal_time());
			        		dws.setRemark(record.getDeal_remark());
			        		dws.setActive_flag('1');
			        		dws.setSend_status("0");
			        		 //获取检验日期
				            if((status.equals("101")||status.equals("104"))&&StringUtil.isNotEmpty(inspection_date)){
					        	dws.setInspection_date(format.parse(inspection_date));
				            }
				           deviceWarningStatusService.update(dws);
				        } else {
				        	DeviceWarningStatus dws=new DeviceWarningStatus();
				        	dws.setDeal_status(record.getDeal_status());
			        		dws.setDeal_time(record.getDeal_time());
			        		 dws.setRemark(record.getDeal_remark());
			        		 dws.setFk_base_device_document_id(record.getFk_base_device_document_id());
			        		 dws.setActive_flag('1');
				            if((status.equals("101")||status.equals("104"))&&StringUtil.isNotEmpty(inspection_date)){
				            	dws.setInspection_date(format.parse(inspection_date));
					         }
				           
				            deviceWarningStatusService.save(dws);
			        	}
			        }
			        devicewarningService.save(record);
					wrapper.put("success", true);
					wrapper.put("printContent","");
					*/
					String[] idArrays = recordDeal.getFk_base_device_document_id().split(",");
				    SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
				        for (int i = 0; i < idArrays.length; i++) {
				        	// 获取设备检验日期
				        	String inspection_date = inspectionInfoService.queryInspection_date(idArrays[i]);
				        	DeviceWarningDeal record = new DeviceWarningDeal();
				        	record.setFk_base_device_document_id(idArrays[i]);
				        	record.setDeal_status(recordDeal.getDeal_status());
				        	record.setDeal_remark(recordDeal.getDeal_remark());
				        	record.setDeal_man_id(user.getId());
				        	record.setDeal_man(user.getUserName());
				        	record.setDeal_unit_id(user.getUnit().getId());
				        	record.setDeal_unit(user.getUnit().getOrgName());
				        	record.setDeal_time(new Date());
				        	record.setSend_status("0"); 	// 数据传输状态（0：未传输 1：已传输）
				        	
				        	QueryCondition qc = new QueryCondition(DeviceWarningStatus.class);
				        	qc.addCondition("fk_base_device_document_id", "=",  idArrays[i]);
				        	qc.addCondition("active_flag", "=", '1');
				        	List<DeviceWarningStatus> dwslist = deviceWarningStatusService.query(qc);
				        	if(dwslist.size()>0){			        		
				        		DeviceWarningStatus dws=dwslist.get(0);
				        		dws.setDeal_status(recordDeal.getDeal_status());
				        		dws.setDeal_time(new Date());
				        		dws.setRemark(recordDeal.getDeal_remark());
				        		//dws.setFk_base_device_document_id(idArrays[i]);
				        		dws.setActive_flag('1');
				        		dws.setSend_status("0");
				        		 //获取检验日期
					            if((recordDeal.getDeal_status().equals("101") || recordDeal.getDeal_status().equals("104")) && StringUtil.isNotEmpty(inspection_date)){
						        	dws.setInspection_date(fmt.parse(inspection_date));
					            }
					           deviceWarningStatusService.update(dws);
					        } else {
					        	DeviceWarningStatus dws=new DeviceWarningStatus();
					        	dws.setDeal_status(recordDeal.getDeal_status());
				        		dws.setDeal_time(new Date());
				        		 dws.setRemark(recordDeal.getDeal_remark());
				        		 dws.setFk_base_device_document_id(idArrays[i]);
				        		 dws.setActive_flag('1');
					            if((recordDeal.getDeal_status().equals("101")||recordDeal.getDeal_status().equals("104"))&&StringUtil.isNotEmpty(inspection_date)){
					            	dws.setInspection_date(fmt.parse(inspection_date));
						         }
					           
					            deviceWarningStatusService.save(dws);
				        	}
				        	devicewarningService.save(record);
				        }
						wrapper.put("success", true);
			} catch (Exception e) {
				log.error("保存处理记录出错：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存处理记录出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		
		/**
		 * 批量添加处理记录
		 * 
		 * @param request
		 * @param id --
		 * @return
		 */
		@RequestMapping(value = "saveDealRecords")
		@ResponseBody
		public HashMap<String, Object> saveDealRecords(HttpServletRequest request, String ids, String deal_status) {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			try {
			      String[] idArray = ids.split(",");
			      SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			        for (int i = 0; i < idArray.length; i++) {
			        	// 获取设备检验日期
			        	String inspection_date = inspectionInfoService.queryInspection_date(idArray[i]);
			        	DeviceWarningDeal record = new DeviceWarningDeal();
			        	record.setFk_base_device_document_id(idArray[i]);
			        	record.setDeal_status(deal_status);
			        	record.setDeal_man_id(user.getId());
			        	record.setDeal_man(user.getUserName());
			        	record.setDeal_unit_id(user.getUnit().getId());
			        	record.setDeal_unit(user.getUnit().getOrgName());
			        	record.setDeal_time(new Date());
			        	record.setSend_status("0"); 	// 数据传输状态（0：未传输 1：已传输）
			        	
			        	QueryCondition qc = new QueryCondition(DeviceWarningStatus.class);
			        	qc.addCondition("fk_base_device_document_id", "=",  idArray[i]);
			        	qc.addCondition("active_flag", "=", '1');
			        	List<DeviceWarningStatus> dwslist = deviceWarningStatusService.query(qc);
			        	if(dwslist.size()>0){			        		
			        		DeviceWarningStatus dws=dwslist.get(0);
			        		dws.setDeal_status(record.getDeal_status());
			        		dws.setDeal_time(record.getDeal_time());
			        		dws.setRemark(record.getDeal_remark());
			        		dws.setActive_flag('1');
			        		dws.setSend_status("0");
			        		 //获取检验日期
				            if((deal_status.equals("101") || deal_status.equals("104")) && StringUtil.isNotEmpty(inspection_date)){
					        	dws.setInspection_date(format.parse(inspection_date));
				            }
				           deviceWarningStatusService.update(dws);
				        } else {
				        	DeviceWarningStatus dws=new DeviceWarningStatus();
				        	dws.setDeal_status(record.getDeal_status());
			        		dws.setDeal_time(record.getDeal_time());
			        		 dws.setRemark(record.getDeal_remark());
			        		 dws.setFk_base_device_document_id(record.getFk_base_device_document_id());
			        		 dws.setActive_flag('1');
				            if((deal_status.equals("101")||deal_status.equals("104"))&&StringUtil.isNotEmpty(inspection_date)){
				            	dws.setInspection_date(format.parse(inspection_date));
					         }
				           
				            deviceWarningStatusService.save(dws);
			        	}
			        	devicewarningService.save(record);
			        }
					wrapper.put("success", true);
			} catch (Exception e) {
				log.error("保存处理记录出错：" + e.getMessage());
				wrapper.put("success", false);
				wrapper.put("message", "保存处理记录出错！");
				e.printStackTrace();
			}
			return wrapper;
		}
		
		@RequestMapping(value = "checkisBooked")
		@ResponseBody
		public String checkisBooked(HttpServletRequest request,String id) {
			System.out.println("----------------------->"+id);
			return devicewarningService.countBook(id)+"";
			
		}
}
