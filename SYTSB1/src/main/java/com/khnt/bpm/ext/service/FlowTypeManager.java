package com.khnt.bpm.ext.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.bean.FlowType;
import com.khnt.bpm.ext.dao.FlowTypeDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;

/**
 * 流程分类业务类
 * 
 */
@Service("flowTypeManager")
public class FlowTypeManager extends EntityManageImpl<FlowType, FlowTypeDao> {

	@Autowired
	FlowTypeDao flowTypeDao;

	@Override
	public void save(FlowType entity) throws Exception {
		if (this.flowTypeDao.isExist(entity.getTypeCode(), entity.getId()))
			throw new KhntException("分类编号已经存在！");
		if (entity.getFlowType() != null && entity.getFlowType().getId() == null)
			entity.setFlowType(null);
		super.save(entity);
	}

	/**
	 * 获取所有父级类别
	 * 
	 * @param demonTypeId
	 * @return
	 */
	public List<FlowType> getAllParentFlowTypes(String demonTypeId) {
		FlowType demon = this.flowTypeDao.get(demonTypeId);
		List<FlowType> typeList = new ArrayList<FlowType>();
		this.findAllParent(demon, typeList);
		return typeList;
	}

	private void findAllParent(FlowType demon, List<FlowType> typeList) {
		typeList.add(demon);
		if (demon.getFlowType() != null) {
			this.findAllParent(demon.getFlowType(), typeList);
		}
	}
}
