package com.khnt.bpm.ext.dao;

import org.springframework.stereotype.Repository;

import com.khnt.bpm.ext.bean.FlowType;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 流程分类管理
 * </p>
 * 
 * @ClassName FlowTypeDao
 * @JDK 1.5
 * @author
 * @date 2011-5-4 下午03:25:06
 */
@Repository("flowTypeDao")
public class FlowTypeDao extends EntityDaoImpl<FlowType> {

	public boolean isExist(String code, String selfId) {
		String sql = "select count(id) from FlowType where typeCode=?";
		if (StringUtil.isNotEmpty(selfId))
			sql += " and id!='" + selfId + "'";
		return (Long) createQuery(sql, code).uniqueResult() > 0;
	}
}
