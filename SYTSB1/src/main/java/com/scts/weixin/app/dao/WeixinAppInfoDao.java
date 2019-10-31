package com.scts.weixin.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rbac.impl.bean.User;
import com.scts.weixin.app.bean.WeixinAppInfo;

/**
 * 微信应用基础数据处理对象
 * 
 * @ClassName WeixinAppInfoDao
 * @JDK 1.7
 * @author GaoYa
 * @date 2018-08-17 下午05:09:00
 */
@Repository("weiXinAppInfoDao")
public class WeixinAppInfoDao extends EntityDaoImpl<WeixinAppInfo> {

	/**
	 * 根据应用编号查询微信应用
	 * 
	 * @param app_code -- 微信应用编号
	 * @return
	 * @author GaoYa
	 * @date 2018-08-23 下午02:12:00
	 */
	public WeixinAppInfo getInfo(String app_code) {
		WeixinAppInfo info = null;
		String hql = "from WeixinAppInfo t where t.app_code=? and t.data_status != '99'";
		info = (WeixinAppInfo) this.createQuery(hql, app_code).uniqueResult();
		return info;
	}

	/**
	 * 根据微信应用编号查询首页响应地址
	 * 
	 * @param app_code -- 微信应用编号
	 * @return
	 * @author GaoYa
	 * @date 2018-08-20 上午10:31:00
	 */
	public String getAppIndexUrl(String app_code) {
		String sql = "select t.app_index_url from WEIXIN_APP_INFO t where t.app_code = ? and t.data_status = '0' ";
		String app_index_url = null;
		try {
			List<?> list = this.createSQLQuery(sql, app_code).list();
			app_index_url = list.get(0).toString();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return app_index_url;
	}

	/**
	 * 根据微信应用编号查询微信应用
	 * 
	 * @param app_code -- 微信应用编号
	 * @return
	 * @author GaoYa
	 * @date 2018-08-27 上午09:51:00
	 */
	public String getAppInfo(String app_code) {
		String sql = "select t.id from WEIXIN_APP_INFO t where t.app_code = ? and t.data_status = '0' ";
		String id = null;
		try {
			List<?> list = this.createSQLQuery(sql, app_code).list();
			if(list != null && list.size()>0) {
				id = list.get(0).toString();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 根据手机号码查询系统在用的登录用户account
	 * 
	 * @param phone -- 用户手机号码
	 * @return
	 * @author GaoYa
	 * @date 2018-08-17 下午05:12:00
	 */
	public List<?> getAccount(String phone) {
		List<?> list = new ArrayList<>();
		String sql = "select t.account from SYS_USER t,EMPLOYEE e where t.employee_id = e.id and e.mobile_tel = ? and t.status = '1' ";
		try {
			list = this.createSQLQuery(sql, phone).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据手机号码查询系统在用User
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public List<User> getUser(String phone) throws Exception {
		String sql = "select t.* from SYS_USER t,EMPLOYEE e where t.employee_id = e.id and e.mobile_tel = ? and t.status = '1' ";
		List<User> userList = super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(User.class)
				.setParameter(0, phone).list();
		return userList;
	}
}