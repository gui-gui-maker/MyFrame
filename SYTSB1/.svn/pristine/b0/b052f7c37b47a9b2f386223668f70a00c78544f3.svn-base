package com.lsts.archives.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository; 

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.archives.bean.ArchivesBorrow;
import com.lsts.archives.bean.ArchivesYijina;
import com.lsts.humanresources.bean.EmployeeBase;
/**
 * <p>
 * 
 * </p>
 * 
 * @ClassName ArchivesYijinaDao
 * @JDK 1.5
 * @author 
 * @date  
 */
@Repository("archivesYijinaDao")
public class ArchivesYijinaDao extends EntityDaoImpl<ArchivesYijina> {

	/**获取单条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesYijina> getOne(String fileId) {
		String hql = "from ArchivesYijina where fileId=?";
		List<ArchivesYijina> list = createQuery(hql,fileId).list();
		return list;

	}
	/**获取申请时间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrow> gettime(String userId) {
		String sql = "select PROPOSER,APPLY_TIME,RESTORE_TIME from TJY2_ARCHIVES_BORROW where PROPOSER_ID=? and STATUS='PASS'";
		List<ArchivesBorrow> list = createSQLQuery(sql,userId).list();
		return list;

	}
	/**
	 * 设置申请借阅的权限
	 * JYQX=借阅权限
	 * */
	public void setEmployee(String proposerId) {
		String sql = "UPDATE TJY2_RL_EMPLOYEE_BASE set AUTHORITY='JYQX' where Id='"+ proposerId+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	/**
	 * 设置归还借阅的权限
	 * */
	public void getgh(String proposerId) {
		String sql = "UPDATE TJY2_RL_EMPLOYEE_BASE set AUTHORITY='' where Id='"+ proposerId+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	/**
	 * 设置归还借阅验证码权限
	 * */
	public void getyzm(String yzm) {
		String sql = "UPDATE TJY2_MESSAGE_CHECK set STATUS='disable' where FK_MSG='"+ yzm+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	/**查找权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeBase> getqx(String userId) {
		String sql = "select authority from TJY2_RL_EMPLOYEE_BASE where Id=?";
		List<EmployeeBase> list = createSQLQuery(sql,userId).list();
		return list;

	}
	/**查找申请人手机号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeBase> getsjh(String userId) {
		String sql = "select MOBILE_TEL from EMPLOYEE where Id=?";
		List<EmployeeBase> list = createSQLQuery(sql,userId).list();
		return list;

	}
	/**查找打印表的状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeBase> getzt(String fileId) {
		String sql = "select STATUS from TJY2_ARCHIVES_PRINT where Id=?";
		List<EmployeeBase> list = createSQLQuery(sql,fileId).list();
		return list;

	}
	/**查找打印表的状态
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrow> getReportNumber(String proposerId) {
		String sql = "select REPORT_NUMBER from TJY2_ARCHIVES_BORROW where PROPOSER_ID=?";
		List<ArchivesBorrow> list = createSQLQuery(sql,proposerId).list();
		return list;

	}
	/**设置归还日期
	 * @return
	 */
	public void getghrq(String Id) {
		String sql = "UPDATE TJY2_ARCHIVES_BORROW set FHBGSJ=(select RESTORE_TIME from "
				+ "TJY2_ARCHIVES_BORROW where id='"+Id+"') where id='"+Id+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
	/**查找报告id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArchivesBorrow> getbg(String yzm) {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		String a=",'yyyy/mm/dd";
		String sql = "select REPORT_NUMBER_ID from TJY2_ARCHIVES_BORROW where FK_MSG=? and RESTORE_TIME>=to_date('"+date+"'"+a+"')";
		List<ArchivesBorrow> list = createSQLQuery(sql,yzm).list();
		return list;

	}
	/**
	 * 设置销毁报告的状态
	 * */
	public void setReportNumberId(String reportNumberId) {
		String sql = "UPDATE TZSB_INSPECTION_INFO set ARCHIVES_STATUS='2' where id='"+ reportNumberId+"'";
		this.createSQLQuery(sql).executeUpdate();
	}
}