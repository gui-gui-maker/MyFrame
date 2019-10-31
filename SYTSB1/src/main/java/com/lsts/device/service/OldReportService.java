package com.lsts.device.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.OldReportApply;
import com.lsts.device.dao.OldReportApplyDao;
import com.lsts.log.service.SysLogService;
import com.lsts.report.service.ReportService;

import util.TS_Util;



@Service("oldReportService")
public class OldReportService extends EntityManageImpl<OldReportApply, OldReportApplyDao> {
	@Autowired
	private OldReportApplyDao oldReportApplyDao;
	
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private SysLogService logService;
	@Autowired
	private ReportService reportService;


	public void del(HttpServletRequest request,String ids) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String[] idss = ids.split(",");
		for (String id : idss) {
			oldReportApplyDao.createQuery("update OldReportApply set data_status = '99' where id = ?", id)
					.executeUpdate();
			//记录日志
			logService.setLogs(id, "删除申请表", "删除申请表",
					user.getId(), user.getName(), new Date(), request
							.getRemoteAddr());
		}
	}
	
	// 保存设备信息（含附件）
		public void saveEquipment(OldReportApply entity, String uploadFiles){
			
			oldReportApplyDao.save(entity);
			
			if(StringUtil.isNotEmpty(uploadFiles)){
				String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
				for(String file : files){
					if (StringUtil.isNotEmpty(file)) {
						attachmentManager.setAttachmentBusinessId(file, entity.getId());
					}
				}
			}
			
			
		}
	
	public void subs(HttpServletRequest request, String ids, String step) {

   		String [] idss = ids.split(",");
   		for (int i = 0; i < idss.length; i++) {
   			
   			OldReportApply reviews = oldReportApplyDao.get(idss[i]);
   			String ip = TS_Util.getIpAddress(request);
   			String conclusion  = request.getParameter("conclusion");
   			String op_date  = request.getParameter("op_date");
   			String remark  = request.getParameter("remark");
   			String next_op  = request.getParameter("next_op");
   			String next_op_id  = request.getParameter("next_op_id");
   			String dean_audit_remark  = request.getParameter("dean_audit_remark");
   			String leader_audit_remark  = request.getParameter("leader_audit_remark");
   			String fk_request_id = request.getParameter("fk_request_id");
   			String op = "提交审核";
   		
   			if(next_op_id!=null&&StringUtil.isNotEmpty(next_op_id)){
   				//流转操作人
   				reviews.setHandle_op_id(next_op_id);
   				reviews.setHandle_op(next_op);
   			}
   			if("0".equals(step)) {
   				reviews.setEnter_op(next_op);
   				reviews.setEnter_op_id(next_op_id);
   				reviews.setFlow_step(step);
   				 try {
   					 reviews.setEnter_time(new Date());
   					} catch (Exception e) {
   					}
   			}
   			if("1".equals(step)){
   				reviews.setDean_audit(next_op);
   				reviews.setDean_audit_op(next_op_id);
   				reviews.setFlow_step(step);
   				 try {
   					 reviews.setEnter_time(new Date());
   					} catch (Exception e) {
   					}
   			}else if("2".equals(step)){
   				 op = "提交批准";
   				 reviews.setLeader_audit(next_op);
   				 reviews.setLeader_audit_op(next_op_id);
   				 reviews.setFlow_step(step);
   				reviews.setDean_audit_remark(dean_audit_remark);
   				 try {
   					reviews.setDean_audit_time(new Date());
   				} catch (Exception e) {
   				}
   				
   			}else{
   				 op = "批准完成";
   				reviews.setLeader_audit_remark(leader_audit_remark);
   				reviews.setFlow_step(step);
   			    
   				 try {
   					reviews.setLeader_audit_time(new Date());
   				 } catch (Exception e) {
   					 e.printStackTrace();
   						// TODO: handle exception
   				 }
   		
				
   			}
   			String op_conclusion = conclusion;
   			if("不通过".equals(conclusion)||"0".equals(conclusion)){
   				//不通过则直接结束
   				reviews.setFlow_step("4");
   				op_conclusion = "不通过";
   			}else{
   				op_conclusion = "通过";
   			}
   			if(remark!=null&&StringUtil.isNotEmpty(remark)){
				   reviews.setRemark(reviews.getRemark()==null?op+":"+remark : reviews.getRemark()+";"+op+":"+remark);
			   }
   		   	oldReportApplyDao.save(reviews);
   		   	if("1".equals(conclusion)&&"3".equals(step)){
   		   	try {
   		   		String fk_k_request_id= reviews.getFk_request_id();
   		   		
				reportService.enable(request,fk_k_request_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		   	}
   		
   		
   		}
   	}
	
	
}
