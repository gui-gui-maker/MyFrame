package com.lsts.approve.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.CertificateRule;

@Repository("certificateRuleDao")
public class CertificateRuleDao extends EntityDaoImpl<CertificateRule>{
	
	/**
	 * 根据设备类别查询优先分配规则
	 * 
	 * @param device_type --
	 *            设备类别
	 * @param dep_id --
	 *            检验部门ID
	 * @param report_name --
	 *            报告名称
	 * @param is_issue --
	 *            是否是签发指定报告类型（0：否 1：是） 
	 * @param report_id --
	 *            报告类型ID
	 *            
	 * @return
	 * @author GaoYa
	 * @date 2017-06-28 上午09:34:00
	 */
	public List<CertificateRule> queryRuleCode(String device_type, String dep_id, String is_issue, String report_name, String report_id) {
		List<CertificateRule> list = new ArrayList<CertificateRule>();
		String hql = " from CertificateRule t where (t.dept_id = '"+dep_id+"' or t.dept_id='1')";
		if(StringUtil.isNotEmpty(is_issue) && "1".equals(is_issue)){
			if("委托检验检测报告".equals(report_name)){
				if("0".equals(device_type)){
					hql += " and t.report_id = '"+report_id+"'";
				}else{
					hql += " and t.device_classify_id = '"+device_type+"'"; 
				}
			}else{
				hql += " and t.report_id = '"+report_id+"'";
			}
		}else{
			hql += " and t.device_classify_id = '"+device_type+"'"; 
		}
		list = this.createQuery(hql).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getUsersData() {
		List<Map<String,Object>> returnlist = new ArrayList<Map<String,Object>>();
		String sql = "select t.id,t.account,t.name,t.org_id,o.org_name from sys_user t,sys_org o where t.org_id= o.id";
		
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", obj[0]);
				map.put("account", obj[1]);
				map.put("name", obj[2]);
				map.put("org_id", obj[3]);
				map.put("org_name", obj[4]);
				returnlist.add(map);
			}
		}
		return returnlist;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getTreeData() {
		List<Map<String,Object>> returnlist = new ArrayList<Map<String,Object>>();
		String sql = "select * from (select ct.value id,'' pid, SUBSTR(ct.value,0,1) code ,ct.name text from PUB_CODE_TABLE c ,PUB_CODE_TABLE_VALUES ct where c.id = ct.code_table_id  and  ct.code_table_id='402883a04b35cf38014b38c2da07245a' and ct.code_table_values_id is null  union all select id,'3000' pid,id code, ORG_NAME text from SYS_ORG  where id in ('100020','100021','100022','100023','100063') union all select id,'0000' pid,id,b.report_name2 from base_reports b where b.is_issue='1') tt order by case when tt.id='0000' then 1 else 0 end, tt.id asc";
		
		List list = this.createSQLQuery(sql).list();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] objArr = list.toArray();
				Object[] obj = (Object[]) objArr[i];
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", obj[0]);
				map.put("pid", obj[1]);
				map.put("code", obj[2]);
				map.put("text", obj[3]);
				returnlist.add(map);
			}
		}
		return returnlist;
	}
}
