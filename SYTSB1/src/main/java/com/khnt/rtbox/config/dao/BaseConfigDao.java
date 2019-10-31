package com.khnt.rtbox.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.khnt.core.crud.dao.impl.EntityDaoImpl;
import com.khnt.rtbox.config.bean.BaseConfig;

@Repository("baseConfigDao")
public class BaseConfigDao extends EntityDaoImpl<BaseConfig> {

	/**
	 * 根据基础信息名字查找相关配置
	 * author pingZhou
	 * @param preText1
	 * @return
	 */
	public BaseConfig getCodeName(String preText1) {
		String hql = "select T.id,t.name,t.code,t.remark,t.type from BASE_NAME_CONFIG t where t.name = ?";
		List<Object> list = this.createSQLQuery(hql, preText1).list();
		if(list.size()>0){
			Object [] objs = (Object[]) list.get(0);
			BaseConfig config = new BaseConfig();
			config.setId(objs[0]==null?"":objs[0].toString());
			config.setName(objs[1]==null?"":objs[1].toString());
			config.setCode(objs[2]==null?"":objs[2].toString());
			config.setRemark(objs[3]==null?"":objs[3].toString());
			config.setType(objs[4]==null?"":objs[4].toString());
			return config;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getCodeTable(String name) {
		String sql = "select t.code,t.name from PUB_CODE_TABLE t where t.code like '%"+name+"%' or t.name like '%"+name+"%'";
		
		return this.createSQLQuery(sql).list();
	}

	public List<Object> getCodeTableValues(String code) {
		
		String sql = "select t.value,t.name from PUB_CODE_TABLE_VALUES t,PUB_CODE_TABLE c where t.code_table_id=c.id and c.code=?";
		
		return this.createSQLQuery(sql,code).list();
	}

}
