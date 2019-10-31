package com.fwxm.scientific.service;


import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.fwxm.scientific.bean.InstructionProject;
import com.fwxm.scientific.dao.InstructionProjectDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName InstructionProjectManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-01-24 10:48:10
 */
@Service("instructionProjectManager")
public class InstructionProjectManager  extends EntityManageImpl<InstructionProject, InstructionProjectDao> {
	@Autowired
	InstructionProjectDao instructionProjectDao;
	
	public Map<String, String>  getBeanByYear( String year){
		String sql="SELECT substr(PROJECT_NO,6) projectNo from TJY2_INSTRUCTION_PROJECT WHERE PROJECT_NO LIKE '"+year+"%' and status !='99'  order by substr(PROJECT_NO,6) desc";
		List<Map<String, String>> list=instructionProjectDao.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
}
