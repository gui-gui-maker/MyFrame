package com.fwxm.personnelTraining.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.bean.Org;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

import com.fwxm.personnelTraining.bean.PersonalTraining;

@Repository("personalTrainingDao")
public class PersonalTrainingDao extends EntityDaoImpl<PersonalTraining>{
	
	//自动生成编号
	public String getSN() {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		Org org = user.getDepartment();
		String orgId=org.getId();
		String orgCode="0";
		if("10096".equals(orgId)){
			orgCode="1";
		}else if("100108".equals(orgId)){
			orgCode="2";
		}else if("100109".equals(orgId)){
			orgCode="3";
		}else if("100106".equals(orgId)){
			orgCode="3";
		}else if("100107".equals(orgId)){
			orgCode="5";
		}else if("100103".equals(orgId)){
			orgCode="6";
		}
		String userCode="S";
		String codeSql="select e.code from employee e,sys_user u "
				+ " where u.employee_id=e.id  and u.id='"+user.getId()+"'";
		List list = createSQLQuery(codeSql).list();
		if(!(list.isEmpty())){
			if(list.get(0)!=null){
				userCode=list.get(0).toString();
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date());
		String sn="";
		String snS="LTJ/RYGP-SQ-"+orgCode;
		String snE=userCode+"-"+year;
		String snSql="select max(SUBSTR(t.sn,14,3)) from ZL_PERSONAL_TRAINING t "
				+ " where t.sn like '"+snS+"%"+snE+"' and t.status <> '99'";
		List snList = createSQLQuery(snSql).list();
		if (snList.size()>0) {
			if(snList.get(0)!=null){
				String maxSn = snList.get(0).toString();
				if(StringUtil.isNotEmpty(maxSn)&&maxSn!=null){
					//有则流水号加1
					int len=maxSn.length();
					String num = maxSn.substring(len-3,len);
					String numN = new Integer(num)+1+"";
					int l = numN.length();
					for (int i = 0; i < 3-l; i++) {
						numN="0"+numN;
					}
					sn = snS+numN+"-"+snE;
				}else{
					sn = snS+"001"+"-"+snE;
				}
			}else{
				//流水号0001
				sn = snS+"001"+"-"+snE;
			}
		}else{
			//流水号0001
			sn = snS+"001"+"-"+snE;
		}
		return sn;
	}

}
