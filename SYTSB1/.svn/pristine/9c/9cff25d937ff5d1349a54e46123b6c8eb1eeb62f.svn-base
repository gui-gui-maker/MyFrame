package com.lsts.humanresources.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.lsts.humanresources.bean.OrgidLeaderid;
import com.lsts.humanresources.dao.OrgidLeaderidDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("orgidLeaderidManager")
public class OrgidLeaderidManager extends EntityManageImpl<OrgidLeaderid,OrgidLeaderidDao>{
    @Autowired
    OrgidLeaderidDao orgidLeaderidDao;
    //根据部门ID获取分管部门信息
    public OrgidLeaderid getInfoByOrgid(String orgId){
    	OrgidLeaderid orgidLeaderid = new OrgidLeaderid();
    	List<OrgidLeaderid> list = orgidLeaderidDao.getInfoByOrgid(orgId);
    	if(list!=null&&list.size()>0){
    		orgidLeaderid = list.get(0);
    	}
		return orgidLeaderid;
    }
}
