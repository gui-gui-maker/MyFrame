package com.lsts.org.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.org.bean.EnterInfo;


/**
 * 单位信息管理   dao
 * 
 * @author 肖慈边 2014-1-21
 */

@Repository("enterDao")
public class EnterDao extends EntityDaoImpl<EnterInfo> {

	/**
	 * 获取单位信息
	 * @return 
	 * @author GaoYa
	 * @date 2014-04-03 下午02:12:00
	 */
	@SuppressWarnings("unchecked")
    public List<EnterInfo> queryEnterInfos() {
    	List<EnterInfo> list = new ArrayList<EnterInfo>();
    	try {
    		String hql = "from EnterInfo e where e.com_status != '05'";
    		list = this.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
	/**
	 * 根据组织结构代码查询单位信息
	 * @param code
	 * @return
	 */
	public EnterInfo queryEnterByCode(String code){
		String hql = "from EnterInfo e where e.com_code =?";
		Object obj=this.createQuery(hql, code).uniqueResult();
		if(obj!=null){
			return (EnterInfo)obj;
		}else{
			return null;
		}
	}
	

	/**
	 * 根据单位名称获取单位信息
	 * @return 
	 * @author GaoYa
	 * @date 2015-12-22 下午02:12:00
	 */
	@SuppressWarnings("unchecked")
    public List<EnterInfo> queryInfos(String com_name) {
    	List<EnterInfo> list = new ArrayList<EnterInfo>();
    	try {
    		String hql = "from EnterInfo e where e.com_status != '05' and e.com_name like:com_name";
    		list = this.createQuery(hql).setParameter("com_name",
					"%" + com_name + "%").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
    }
}
