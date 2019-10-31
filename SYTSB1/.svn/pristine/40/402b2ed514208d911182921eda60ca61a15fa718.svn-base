package com.khnt.rtbox.config.service;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.config.bean.PageContentRecord;
import com.khnt.rtbox.config.dao.PageContentRecordDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName PageContentRecordManager
 * @JDK 1.7
 * @author zmh
 * @date 2019-01-14 19:22:34
 */
@Service("pageContentRecordManager")
public class PageContentRecordManager extends EntityManageImpl<PageContentRecord, PageContentRecordDao> {
	@Autowired
	PageContentRecordDao pageContentRecordDao;
	/*@Autowired
	BusinessManager businessManager;*/
	
	@Override
	public void save(PageContentRecord entity) throws Exception {
		super.save(entity);
		
		/** 保存业务记录 */
		/*String HandleTypeCode = StringUtil.isEmpty(entity.getId())?GlobalConstant.HANDLE_TYPE_ADD:GlobalConstant.HANDLE_TYPE_EDIT;
		String HandleTypeName = StringUtil.isEmpty(entity.getId())?"新增":"修改";
		this.saveHandle(entity, HandleTypeCode, HandleTypeName, BusinessType.RECORDONLY);*/
	}
	
	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		PageContentRecord entity = pageContentRecordDao.get(id);
		/**根据自己业务状态判断是否可删除数据，不能删除抛出异常*/
		/**
		if(!RSConstant.ATTEND_STATUS_NOSUBMIT.equals(entity.getStatus())) {
			throw new KhntException("您提交的【" + CodeTableUtil.getNameByValue(RSConstant.ATTEND_APPLY_TYPE, entity.getApplyType()) + 
					"】申请正在审核中,不可删除！");
		}
		*/
		
		/** 保存业务记录 */
		/*String HandleTypeCode = GlobalConstant.HANDLE_TYPE_DEL;
		String HandleTypeName = "删除";
		this.saveHandle(entity, HandleTypeCode, HandleTypeName, BusinessType.RECORDONLY);*/
	}
	
	@Override
	public void deleteBusiness(String[] ids) throws Exception {
		for(String id:ids){
			deleteBusiness(id);
		}
	}
	
	/**
	 * 保存操作日志
	 * @param entity
	 * @param HandleTypeCode
	 * @param HandleTypeName
	 * @param businessType
	 * @throws Exception
	 */
	/*public void saveHandle(PageContentRecord entity,String HandleTypeCode,String HandleTypeName,BusinessType businessType) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		entity.setHandleOrgId(user.getUnit().getId());//操作人单位ID
		entity.setHandleOrgName(user.getUnit().getOrgName());//操作人单位名称
		entity.setHandleDepId(user.getDepartment().getId());//操作人部门ID
		entity.setHandleDepName(user.getDepartment().getOrgName());//操作人部门名称
		entity.setHandleUserId(user.getId());//操作人ID
		entity.setHandleUserName(user.getName());//操作人姓名
		entity.setHandleDate(new Date());//操作时间
		entity.setHandleType(HandleTypeCode);//操作类型
		pageContentRecordDao.save(entity);
		
		*//** 业务记录 *//*
		Map<BusinessMapParam, Object> dataMap = new HashMap<BusinessMapParam, Object>();
		dataMap.put(BusinessMapParam.opType, HandleTypeName);// 操作类型
		dataMap.put(BusinessMapParam.businessId, entity.getId()); // 业务id
		dataMap.put(BusinessMapParam.businessTitle, -----------这里填写业务标题，自行修改-----------); // 业务标题

		// 存储方式,0,1,2
		businessManager.businessRecord(BusinessConstant.RS.Attendance.class, businessType, dataMap);
	}*/
}
