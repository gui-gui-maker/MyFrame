package com.khnt.bpm.ext.dao;

import org.springframework.stereotype.Repository;

import com.khnt.bpm.ext.bean.FlowFunction;
import com.khnt.core.crud.dao.impl.EntityDaoImpl;

/**
 * <p>
 * 流程功能管理DAO
 * </p>
 * 
 * @JDK 1.5
 * @author
 * @date 2011-5-4 下午03:25:06
 */
@Repository("flowFunctionDao")
public class FlowFunctionDao extends EntityDaoImpl<FlowFunction> {
}
