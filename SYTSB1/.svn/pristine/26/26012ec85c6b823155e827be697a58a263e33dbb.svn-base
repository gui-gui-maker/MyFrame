package com.fwxm.personnelTraining.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import com.fwxm.personnelTraining.bean.PersonalTraining;
import com.fwxm.personnelTraining.dao.PersonalTrainingDao;

@Service("personalTrainingService")
public class PersonalTrainingService extends EntityManageImpl<PersonalTraining, PersonalTrainingDao>{
	
	@Autowired
	PersonalTrainingDao personalTrainingDao;

	public  HashMap<String, Object> initData(HttpServletRequest request) {
		HashMap<String, Object> map= new HashMap<String, Object>();
		//获得编号
		//String sn=personalTrainingDao.getSN();
		//map.put("sn", sn);
		CurrentSessionUser user = SecurityUtil.getSecurityUser();		
		map.put("apply_op",user.getName());//培训人
		map.put("apply_op_id",user.getId());//培训人ID
		map.put("apply_org_id",user.getDepartment().getId());//培训人
		map.put("apply_org",user.getDepartment().getOrgName());//培训人
		map.put("create_op",user.getName());//记录人
		map.put("create_op_id",user.getId());//记录人ID
		map.put("create_date", new Date());//记录时间
		map.put("traning_date", new Date());//培训时间
		map.put("create_date", new Date());//创建时间
		map.put("status", "0");		
		return map;
	}

	public void saveBasic(PersonalTraining pt) {
		if(StringUtil.isEmpty(pt.getId())){
			personalTrainingDao.save(pt);
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
		}else{
			if("2".equals(pt.getStatus())){
				if("1".equals(pt.getAudit_opinion())){
					pt.setStatus("4");
				}else if("2".equals(pt.getAudit_opinion())){
					pt.setStatus("5");
				}
			}
			personalTrainingDao.save(pt);	
		}
	}

	//提交下一步流程
	public void sub(String ids, String op_id, String op, String step) {
		String[] id = ids.split(",");
		if("org_audit".equals(step)){
			for (int i = 0; i < id.length; i++) {
				PersonalTraining pt = personalTrainingDao.get(id[i]);
				pt.setStatus("1");
				pt.setOrg_audit_op(op);
				pt.setOrg_audit_op_id(op_id);				
				personalTrainingDao.save(pt);
			}
		}else if("audit".equals(step)){
			for (int i = 0; i < id.length; i++) {
				PersonalTraining pt = personalTrainingDao.get(id[i]);
				if("2".equals(pt.getOrg_audit_opinion())){
					pt.setStatus("5");
				}else{
					pt.setStatus("2");
					pt.setAudit_op(op);
					pt.setAudit_op_id(op_id);
				}
				personalTrainingDao.save(pt);
			}
		}else if("sign".equals(step)){
			for (int i = 0; i < id.length; i++) {
				PersonalTraining pt = personalTrainingDao.get(id[i]);
				if("2".equals(pt.getAudit_opinion())){
					pt.setStatus("5");
				}else{
					pt.setStatus("3");
					pt.setSign_op(op);
					pt.setSign_op_id(op_id);
				}
				personalTrainingDao.save(pt);
			}
		}		
	}

	public void back(String ids, String step) {
		String[] id = ids.split(",");
		if("audit".equals(step)){
			for (int i = 0; i < id.length; i++) { 
				PersonalTraining pt = personalTrainingDao.get(id[i]);
				pt.setStatus("2");
				pt.setSign_op_id(null);
				pt.setSign_op(null);
				pt.setSign_date(null);
				pt.setSign_opinion(null);
				personalTrainingDao.save(pt);			
			}
		}else if("org_audit".equals(step)){
			for (int i = 0; i < id.length; i++) { 
				PersonalTraining pt = personalTrainingDao.get(id[i]);
				pt.setStatus("1");
				pt.setSign_op_id(null);
				pt.setSign_op(null);
				pt.setSign_date(null);
				pt.setSign_opinion(null);
				pt.setAudit_op(null);
				pt.setAudit_op_id(null);
				pt.setAudit_date(null);
				pt.setAudit_opinion(null);
				personalTrainingDao.save(pt);			
			}
		}else if("apply".equals(step)){
			for (int i = 0; i < id.length; i++) {
				PersonalTraining pt = personalTrainingDao.get(id[i]);
				pt.setStatus("0");
				pt.setSign_op_id(null);
				pt.setSign_op(null);
				pt.setSign_date(null);
				pt.setSign_opinion(null);
				pt.setAudit_op(null);
				pt.setAudit_op_id(null);
				pt.setAudit_date(null);
				pt.setOrg_audit_op(null);
				pt.setOrg_audit_opinion(null);
				pt.setOrg_audit_op_id(null);
				pt.setOrg_audit_date(null);
				pt.setOrg_audit_opinion(null);
				personalTrainingDao.save(pt);
			}
		}
		
	}

	public void del(String ids) {
		String id[] = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			personalTrainingDao
					.createSQLQuery(
							"update  ZL_PERSONAL_TRAINING  set status='99' where id =?",
							id[i]).executeUpdate();

		}
		
	}
}
