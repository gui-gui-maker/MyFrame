package com.lsts.report.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.lsts.report.bean.BaseReportsMenuConfig;

@Repository("baseReportsMenuConfigDao")
public class BaseReportsMenuConfigDao extends EntityDaoImpl<BaseReportsMenuConfig>{

	/**
	 * 查看报告目录配置信息
	 * author pingZhou
	 * @param id
	 * @return
	 */
	public List<BaseReportsMenuConfig> getReportMenuConfig(String id) {

		String hql = "from BaseReportsMenuConfig where fkReportsId = ?";
		List<BaseReportsMenuConfig> list = listQuery(hql, id);
		
		return list;
	}

}
