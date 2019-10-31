package com.lsts.approve.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.CertificateBy;

@Repository("certificateByDao")
public class CertificateByDao extends EntityDaoImpl<CertificateBy>{
	
	/**
	 * 根据设备类别、检验部门查询可签发人员信息（启用并且在岗状态）
	 * @param device_type -- 设备类别
	 * @param dep_id -- 检验部门ID
	 * @param is_allow_selfdep -- 是否可签本部门报告（1：是 0：否）
	 * @param is_ministerofaduit_person -- 是否签本部门部长（1：是 0：否）
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-06-27 上午11:02:00
	 */
	@SuppressWarnings("unchecked")
	public List<CertificateBy> queryByDeviceDep(String device_type, String dep_id, String is_allow_selfdep,String is_ministerofaduit_person) {
		List<CertificateBy> list = new ArrayList<CertificateBy>();
		try {
			if (StringUtil.isNotEmpty(device_type) && StringUtil.isNotEmpty(dep_id)) {
				String hql = "from CertificateBy r where r.deviceTypeId = ? and (r.mayCertDeptId=? or r.mayCertDeptId='1') and r.status='1' and r.is_vacation='0'";
				if("0".equals(is_allow_selfdep)){
					hql += " and r.deptId != ? ";
					list = this.createQuery(hql, device_type, dep_id, dep_id).list();
				}else{
					if(StringUtil.isNotEmpty(is_ministerofaduit_person)) {
						hql += " and r.deptId = ? and r.is_ministerofaduit_person = ? ";
						list = this.createQuery(hql, device_type, dep_id, dep_id, is_ministerofaduit_person).list();
					}else {
						list = this.createQuery(hql, device_type, dep_id).list();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据报告类型、检验部门查询可签发人员信息（启用并且在岗状态）
	 * @param report_id -- 设备类别
	 * @param dep_id -- 检验部门ID
	 * @param is_allow_selfdep -- 是否可签本部门报告（1：是 0：否）
	 * @param is_ministerofaduit_person -- 是否签本部门部长（1：是 0：否）
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-06-27 下午16:17:00
	 */
	@SuppressWarnings("unchecked")
	public List<CertificateBy> queryByReport(String report_id, String dep_id, String is_allow_selfdep,String is_ministerofaduit_person) {
		List<CertificateBy> list = new ArrayList<CertificateBy>();
		try {
			String hql = "from CertificateBy r where r.status='1' and r.is_vacation='0'";
			if (StringUtil.isNotEmpty(report_id)) {
				hql += " and r.report_id='" + report_id + "'";
			}
			if("0".equals(is_allow_selfdep)){
				hql += " and r.deptId!=?";
				list = this.createQuery(hql, dep_id).list();
			}else{
				if(StringUtil.isNotEmpty(is_ministerofaduit_person)) {
					hql += " and r.deptId =? and r.is_ministerofaduit_person = ? ";
					list = this.createQuery(hql, dep_id, is_ministerofaduit_person).list();
				}else {
					list = this.createQuery(hql).list();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取系统所有签发授权签字人信息
	 * @return
	 * @author GaoYa
	 * @date 2017-06-29 上午10:53:00
	 */
	public Map<String, Object> getAllIssues(){
		Map<String, Object> issues = new HashMap<String, Object>();
		String hql = "select t.USER_ID,t.USER_NAME from BASE_CERTIFICATE_BY t ";
		List list = this.createSQLQuery(hql).list(); 
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				issues.put(String.valueOf(obj[0]), obj[1]);
			}
		}
		return issues;
	}
	
	/**
	 * 根据规则外键、签发人员ID获取签字人员信息
	 * @param rule_id -- 规则外键
	 * @param user_id -- 签发人员ID
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2017-07-18 上午11:40:00
	 */
	@SuppressWarnings("unchecked")
	public List<CertificateBy> queryByRuleId(String rule_id, String user_id) {
		List<CertificateBy> list = new ArrayList<CertificateBy>();
		try {
			if (StringUtil.isNotEmpty(rule_id) && StringUtil.isNotEmpty(user_id)) {
				String hql = "from CertificateBy r where r.fk_rule = ? and r.user_id=? ";
				list = this.createQuery(hql, rule_id, user_id).list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
