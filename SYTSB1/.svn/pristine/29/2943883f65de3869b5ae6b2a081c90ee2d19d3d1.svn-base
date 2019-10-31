package com.lsts.approve.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;
import com.lsts.approve.bean.CertificateBy;
import com.lsts.approve.dao.CertificateByDao;
import com.lsts.report.dao.ReportDao;
@Service("certificateByService")
public class CertificateByService  extends EntityManageImpl<CertificateBy,CertificateByDao>{
	@Autowired
	private CertificateByDao certificateByDao;
	@Autowired
	private ReportDao reportDao;
	
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
	public List<CertificateBy> queryByDeviceDep(String device_type, String dep_id, String is_allow_selfdep,String is_ministerofaduit_person){
		return certificateByDao.queryByDeviceDep(device_type, dep_id, is_allow_selfdep, is_ministerofaduit_person);
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
	 * @date 2017-06-27 下午16:47:00
	 */
	public List<CertificateBy> queryByReport(String report_id, String dep_id, String is_allow_selfdep, String is_ministerofaduit_person){
		return certificateByDao.queryByReport(report_id, dep_id, is_allow_selfdep,is_ministerofaduit_person);
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
	public List<CertificateBy> queryByRuleId(String rule_id, String user_id) {
		return certificateByDao.queryByRuleId(rule_id, user_id);
	}
	
	/**
	 * 获取系统所有签发授权签字人信息
	 * @return
	 * @author GaoYa
	 * @date 2017-06-29 上午10:53:00
	 */
	public Map<String, Object> getAllIssues(){
		return certificateByDao.getAllIssues();
	}


	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	public void delete(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set status ='-1' where id=?", id).executeUpdate();
		}
	}
	public void start(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set status ='1' where id=?", id).executeUpdate();
		}
	}
	public void stop(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set status ='0' where id=?", id).executeUpdate();
		}
	}
	public void notToOne(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set IS_DISTRIBUTE_ONE ='0' where id=?", id).executeUpdate();
		}
	}
	public void toOne(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set IS_DISTRIBUTE_ONE ='1' where id=?", id).executeUpdate();
		}
	}
	public void notToLeast(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set IS_ASIGN_TO_LEAST ='0' where id=?", id).executeUpdate();
		}
	}
	public void toLeast(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set IS_ASIGN_TO_LEAST ='1' where id=?", id).executeUpdate();
		}
	}
	public void useSubstitute(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set IS_USE_SUBSTITUTE ='1' where id=?", id).executeUpdate();
		}
	}
	public void unuseSubstitute(String ids) throws Exception{
		String[] idss = ids.split(",");
		for(String id:idss){
			certificateByDao.createSQLQuery("update BASE_CERTIFICATE_BY set IS_USE_SUBSTITUTE ='0' where id=?", id).executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CertificateBy> details(String device, String dept, String report) throws Exception{
		List<CertificateBy> list = null;
		String hql=null;
		if(!StringUtil.isEmpty(dept)){
			hql="from CertificateBy where deviceTypeId=? and mayCertDeptId=?";
			list = certificateByDao.createQuery(hql, device,dept).list();
		}else if(!StringUtil.isEmpty(report)){
			hql="from CertificateBy where deviceTypeId=? and report_id=?";
			list = certificateByDao.createQuery(hql, device,report).list();
		}else{
			hql="from CertificateBy where deviceTypeId=?";
			list = certificateByDao.createQuery(hql, device).list();
		}
		return list;
	}
}
