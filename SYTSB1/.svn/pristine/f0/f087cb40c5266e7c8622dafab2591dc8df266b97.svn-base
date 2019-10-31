package com.lsts.humanresources.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository; 
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.humanresources.bean.OrgidLeaderid;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName OrgidLeaderidDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("orgidLeaderidDao")
public class OrgidLeaderidDao extends EntityDaoImpl<OrgidLeaderid> {
	//根据部门ID获取分管部门信息
	public List<OrgidLeaderid> getInfoByOrgid(String orgId){
    	List<OrgidLeaderid> list = new ArrayList<OrgidLeaderid>();
    	String hql = "from OrgidLeaderid where instr(fkSysOrgId,?) > 0"; 
    	list = this.createQuery(hql, orgId).list();
		return list;
    }
}