package com.lsts.common.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.pub.codetable.bean.CodeTable;
import com.lsts.common.dao.CodeTablesDao;

/**
 * 数据字典业务逻辑对象
 * @ClassName CodeTablesService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-21 上午10:08:00
 */
@Service("codeTablesService")
public class CodeTablesService extends EntityManageImpl<CodeTable, CodeTablesDao>{

	@Autowired
	private CodeTablesDao codeTablesDao;
	
	public String getValueByCode(String tcode, String value){
		return codeTablesDao.getValueByCode(tcode, value);
	}
	
	public String getValueByName(String tcode, String name){
		return codeTablesDao.getValueByName(tcode, name);
	}
	
	public Map<String, Object> getDevice_types(String tcode, String type){
		return codeTablesDao.getDevice_types(tcode, type);
	}
}
