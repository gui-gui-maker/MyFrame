package com.lsts.mobileapp.input.service;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.mobileapp.input.bean.InspectionNotice;
import com.lsts.mobileapp.input.bean.TzsbRecordLog;
import com.lsts.mobileapp.input.dao.InspectionNoticeDao;
import com.lsts.mobileapp.input.dao.TzsbRecordLogDao;

import util.TS_Util;

@Service("inspectionNoticeService")
public class InspectionNoticeService extends EntityManageImpl<InspectionNotice, InspectionNoticeDao> {

	@Autowired
	InspectionNoticeDao inspectionNoticeDao;
	@Autowired
	AttachmentDao attachmentDao;
	@Autowired
	InspectionInfoDao infoDao;
	@Autowired
	TzsbRecordLogDao recordLogDao;
	@Autowired
	ReportItemRecordHisService itemRecordHisService;

	@Override
	public void save(InspectionNotice entity) throws Exception {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if(entity.getId()==null&&StringUtil.isEmpty(entity.getId())) {
			entity.setEnter_op(user.getName());
			entity.setEnter_op_id(user.getId());
		}
		
		inspectionNoticeDao.save(entity);
		if(entity.getFile_id()!=null&&StringUtil.isNotEmpty(entity.getFile_id())) {
			String fileId = entity.getFile_id();
			String [] fileIds = fileId.split(",");
			for (int j = 0; j < fileIds.length; j++) {
				Attachment attachment = attachmentDao.get(fileIds[j]);
				if(attachment!=null) {
					attachment.setBusinessId(entity.getId());
					attachmentDao.save(attachment);
				}
			}
			
			
		}
	}
	
	public InspectionNotice getByInspectInfoId(String inspectInfoId) {
		return this.inspectionNoticeDao.getByInspectInfoId(inspectInfoId);
	}

	public void saveAndSub(HttpServletRequest request, InspectionNotice entity) throws Exception {
		this.save(entity);
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		if(entity.getFk_inspect_info_id()!=null) {
			String [] infoIds = entity.getFk_inspect_info_id().split(",");
			for (int i = 0; i < infoIds.length; i++) {
				InspectionInfo info = infoDao.get(infoIds[i]);
				info.setRecordFlow("3");
				infoDao.save(info);
				//复制原始记录数据
				itemRecordHisService.copyHisRecord(info.getId(), info.getReport_type());
				
				TzsbRecordLog log  = new TzsbRecordLog();
				log.setBusiness_id(infoIds[i]);
				log.setOp_action("中止");
				log.setOp_ip(TS_Util.getIpAddress(request));
				log.setOp_remark("原始记录录入中止");
				log.setOp_status("退回");
				log.setOp_time(new Date());
				log.setOp_user_id(user.getId());
				log.setOp_user_name(user.getName());
				
				recordLogDao.save(log);
			}
			
		}
	}
	
	public void callInput(HttpServletRequest request, String id) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		InspectionNotice entity = this.inspectionNoticeDao.getByInspectInfoId(id);
		if(entity==null) {
			throw new KhntException("没有检验意见通知书！");
		}
		String eDate = DateUtil.format(entity.getEnd_date(), "yyyy-MM-dd");
		String nDate = DateUtil.format(new Date(), "yyyy-MM-dd");
		if("3".equals(entity.getStatus())||nDate.compareTo(eDate)>0) {
			if(entity.getFk_inspect_info_id()!=null) {
				String [] infoIds = entity.getFk_inspect_info_id().split(",");
				for (int i = 0; i < infoIds.length; i++) {
					InspectionInfo info = infoDao.get(infoIds[i]);
					info.setRecordFlow("0");
					infoDao.save(info);
					TzsbRecordLog log  = new TzsbRecordLog();
					log.setBusiness_id(infoIds[i]);
					log.setOp_action("整改重检");
					log.setOp_ip(TS_Util.getIpAddress(request));
					log.setOp_remark("原始记录录入整改后重检");
					log.setOp_status("退回");
					log.setOp_time(new Date());
					log.setOp_user_id(user.getId());
					log.setOp_user_name(user.getName());
					
					recordLogDao.save(log);
				}
				
			}
		}else {
			throw new KhntException("没有整改完成确认！");
		}
		
	}
	/**
	 * 检验意见通知书反馈
	 * @param id
	 * @param feed_that
	 * @param feedback_file_id
	 * @return
	 */
	public HashMap<String,Object> feedback(String id, String feed_that, String feedback_file_id,String feedback_file_name){
		HashMap<String,Object> map = new HashMap<String, Object>();
		String sql = "UPDATE TZSB_INSPECTION_NOTICE  SET feedback_file_id = ?, feedback_file_name = ?, feed_that = ?, STATUS = '2'  WHERE id = ?";
		if(id != "" && id != null){
			int a = inspectionNoticeDao.createSQLQuery(sql,feedback_file_id,feedback_file_name,feed_that,id).executeUpdate();
			if(a != 0){
				map.put("success",true);
			}else{
				map.put("success",false);
			}
		}else{
			map.put("success",false);
		}
		return map;
	}

	/**
	 * 通过检验反馈
	 * @param id
	 * @return
	 */
	public HashMap<String,Object> through(String id){
		HashMap<String,Object> map = new HashMap<String, Object>();
		String sql = "UPDATE TZSB_INSPECTION_NOTICE  SET STATUS = '3'  WHERE id = ?";
		if(id != "" && id != null){
			int a = inspectionNoticeDao.createSQLQuery(sql,id).executeUpdate();
			if(a != 0){
				map.put("success",true);
			}else{
				map.put("success",false);
			}
		}else{
			map.put("success",false);
		}
		return map;
	}

	
}
