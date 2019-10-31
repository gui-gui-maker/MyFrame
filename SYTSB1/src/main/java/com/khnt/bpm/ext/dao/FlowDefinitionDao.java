package com.khnt.bpm.ext.dao;

import org.springframework.stereotype.Repository;

import com.khnt.bpm.ext.bean.FlowDefinition;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

/**
 * <p>
 * 流程定义DAO
 * </p>
 * 
 * @ClassName FlowDefinitionDao
 * @JDK 1.5
 * @author
 * @date 2011-5-4 下午03:25:06
 */
@Repository("flowDefinitionDao")
public class FlowDefinitionDao extends EntityDaoImpl<FlowDefinition> {
}
