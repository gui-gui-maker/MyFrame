package com.lsts.log.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.log.bean.SysLog;

/**
 * 系统日志数据处理对象
 * @ClassName SysLogDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-28 下午02:55:00
 */
@Repository("sysLogDao")
public class SysLogDao extends EntityDaoImpl<SysLog> {

	// 根据业务ID获取日志
	@SuppressWarnings("unchecked")
	public SysLog getBJLog(String info_id) {
		String hql = "from SysLog i where i.business_id=? order by i.op_time asc";
		List<SysLog> list = this.createQuery(hql, info_id).list();
		if (!list.isEmpty()) {
			return (SysLog)list.get(0);
		}
		return null;
	}	
	
	// 根据业务ID和业务环节名称获取日志
	@SuppressWarnings("unchecked")
	public List<SysLog> getReportLogs(String info_id, String op_action) {
		List<SysLog> list = new  ArrayList<SysLog>();
		String hql = "from SysLog i where i.business_id=? and i.op_action like '%"+op_action+"%' and i.op_action != '打印原始记录'";
		list = this.createQuery(hql, info_id).list();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	// 根据业务ID和业务环节名称获取日志除开发送微信、短信
	@SuppressWarnings("unchecked")
	public List<SysLog> getBJLogs(String business_id) {
		List<SysLog> list = new  ArrayList<SysLog>();
		String hql = "from SysLog i where i.business_id=? and i.op_action not in ('发送短信','发送微信') order by i.op_time,id asc";
		list = this.createQuery(hql, business_id).list();
		return list;
	}

	// 根据业务ID获取日志(所有信息)
	@SuppressWarnings("unchecked")
	public List<SysLog> getBJLogsAll(String business_id) {
		List<SysLog> list = new ArrayList<SysLog>();
		String hql = "from SysLog i where i.business_id=? order by i.op_time,id asc";
		list = this.createQuery(hql, business_id).list();
		return list;
	}
}
