package com.scts.car.service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.car.bean.CarWbRecord;
import com.scts.car.bean.CarWbRecordItem;
import com.scts.car.dao.CarWbRecordDao;
import com.scts.car.dao.CarWbRecordItemDao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carWbRecordManager")
public class CarWbRecordManager extends EntityManageImpl<CarWbRecord, CarWbRecordDao> {
	@Autowired
	CarWbRecordDao carWbRecordDao;
	@Autowired
	CarWbRecordItemDao carWbRecordItemDao;

	/**
	 * 保存维保记录
	 * 
	 * @param request
	 * @param carWbRecord
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveInfo(HttpServletRequest request, CarWbRecord carWbRecord) throws Exception {
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		String pageStatus = request.getParameter("pageStatus");
		List<CarWbRecordItem> list = carWbRecord.getCarWbRecordItems();
		if ("add".equals(pageStatus)) {
			carWbRecord.setCreateUserId(curUser.getId());
			carWbRecord.setCreateUserName(curUser.getName());
			carWbRecord.setCreateDate(new Date());
		} else if ("modify".equals(pageStatus)) {
			carWbRecord.setLastModifyUserId(curUser.getId());
			carWbRecord.setLastModifyUserName(curUser.getName());
			carWbRecord.setLastModifyDate(new Date());
		}
		try {
			carWbRecordDao.save(carWbRecord);
			if (StringUtil.isNotEmpty(carWbRecord.getId())) {
				carWbRecordItemDao.deleteWbRecordItemsPhy(carWbRecord.getId());
				if (list != null && list.size() > 0) {
					for (CarWbRecordItem carWbRecordItem : list) {
						carWbRecordItem.setId(null);
						carWbRecordItem.setFkWbRecordId(carWbRecord.getId());
						carWbRecordItem.setFkCarId(carWbRecord.getFkCarId());
						carWbRecordItemDao.save(carWbRecordItem);
					}
				}
			}
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 获取维保记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getDetail(String id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查询维保记录主表
			CarWbRecord carWbRecord = carWbRecordDao.get(id);
			// 查询维保记录明细
			List<CarWbRecordItem> list = carWbRecordItemDao.getWbRecordItems(id);
			map.put("data", carWbRecord);
			map.put("carWbRecordItems", list);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
		}
		return map;
	}

	/**
	 * 删除维保记录
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> deleteInfo(HttpServletRequest request, String id) throws Exception {
		try {
			CarWbRecord carWbRecord = carWbRecordDao.get(id);
			carWbRecord.setDataStatus("99");
			carWbRecordDao.save(carWbRecord);
			carWbRecordItemDao.deleteWbRecordItemsLogic(carWbRecord.getId());
			return JsonWrapper.successWrapper("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
	}

	/**
	 * 根据维保申请id获取维保记录
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getWbRecordsByFkWbId(HttpServletRequest request, String fkWbId) throws Exception {
		try {
			List<CarWbRecord> list = carWbRecordDao.getWbRecordsByFkWbId(fkWbId);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("查询失败！");
		}
	}

	/**
	 * 根据车辆id获取维保记录
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getWbRecordsByFkCarId(HttpServletRequest request, String fkCarId) throws Exception {
		try {
			List<CarWbRecord> list = carWbRecordDao.getWbRecordsByFkCarId(fkCarId);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.toString());
			return JsonWrapper.failureWrapperMsg("查询失败！");
		}
	}
}
