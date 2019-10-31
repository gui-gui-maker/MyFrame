package com.scts.cspace.space.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.scts.cspace.space.bean.TjyptResourceSpace;
import com.scts.cspace.space.bean.TjyptSpaceExpand;
import com.scts.cspace.space.dao.TjyptSpaceExpandDao;

@Service("tjyptSpaceExpandService")
public class TjyptSpaceExpandService extends EntityManageImpl<TjyptSpaceExpand,TjyptSpaceExpandDao> {

	@Autowired
	private TjyptSpaceExpandDao spaceExpandDao;
	@Autowired
	private TjyptResourceSpaceService tjyptResourceSpaceService;

	public void applyExpand(String rj, String desc) {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		TjyptSpaceExpand spaceExpand = new TjyptSpaceExpand();
		
		spaceExpand.setApply_date(new Date());
		spaceExpand.setApply_expand(rj);
		spaceExpand.setApply_user_id(user.getId());
		spaceExpand.setApply_user_name(user.getName());
		spaceExpand.setApply_desc(desc);
		spaceExpand.setStep("1");
		
		spaceExpandDao.save(spaceExpand);
		
	}

	public void auditExpand(String id,String audit_conclusion, String audit_date, String audit_expand, String audit_desc) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		TjyptSpaceExpand spaceExpand = spaceExpandDao.get(id);
		TjyptResourceSpace space = tjyptResourceSpaceService.openPersonalSpace(
				spaceExpand.getApply_user_id(), "1");
		String size = space.getSpace_max_size();
		if("1".equals(audit_conclusion)){
			Float oldSize = Float.parseFloat(size);
			Integer newSize = Integer.parseInt(audit_expand)*1024*1024;
			space.setSpace_max_size(newSize+"");
			spaceExpand.setAudit_expand(Float.parseFloat(audit_expand));
			
		}
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(audit_date);
		spaceExpand.setAudit_date(date);
		spaceExpand.setAudit_user_id(user.getId());
		spaceExpand.setAudit_conclusion(audit_conclusion);
		spaceExpand.setAudit_desc(audit_desc);
		spaceExpand.setAudit_user_name(user.getName());
		spaceExpand.setStep("2");
		spaceExpandDao.save(spaceExpand);
		
	}
	
}
