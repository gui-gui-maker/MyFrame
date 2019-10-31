package com.khnt.bpm.ext.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.ext.bean.Opinion;
import com.khnt.bpm.ext.dao.OpinionDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.service.IAttachmentManager;
import com.khnt.utils.StringUtil;

import net.sf.json.JSONArray;

/**
 * 工作流环节处理意见管理service
 * 
 */
@Service("opinionManager")
public class OpinionManager extends EntityManageImpl<Opinion, OpinionDao> {

	@Autowired
	OpinionDao opinionDao;

	@Autowired
	IAttachmentManager attachmentManager;

	public void save(Opinion entity, String att) throws Exception {
		this.opinionDao.save(entity);
		if (StringUtil.isNotEmpty(att)) {
			attachmentManager.setAttachmentBusinessId(att.split(","), entity.getId());
		}
	}

	public JSONArray queryServiceOpinion(String serviceId) {
		return this.opinionDao.queryServiceOpinion(serviceId);
	}

	/**
	 * 锁定意见
	 * 
	 * @param activityId
	 * @param signerId
	 */
	public void lockOpinion(String activityId, String signerId) {
		opinionDao.createQuery("update Opinion set lock='1' where activityId=? and signerId=?", activityId, signerId)
				.executeUpdate();
	}

	/**
	 * 锁定业务的所有意见
	 * 
	 * @param serviceId
	 */
	public void lockOpinion(String serviceId) {
		opinionDao.createQuery("update Opinion set lock='1' where serviceId=?", serviceId).executeUpdate();
	}

	/**
	 * 获取意见
	 * 
	 * @param name
	 *            意见名称
	 * @param serviceId
	 *            业务ID
	 * @param activityId
	 *            任务环节
	 * @param workitem
	 *            工作项
	 * @param signer
	 *            签字人
	 * @return
	 * @throws Exception
	 */
	public Opinion signOpinion(String name, String serviceId, String activityId, String workitem, String signer) {
		String where = "lock='0' ";
		List<Object> qValue = new ArrayList<Object>();
		if (!StringUtil.isEmpty(name)) {
			where += "and name=? ";
			qValue.add(name);
		}
		if (!StringUtil.isEmpty(serviceId)) {
			where += "and serviceId=? ";
			qValue.add(serviceId);
		}
		if (!StringUtil.isEmpty(activityId)) {
			where += "and activityId=? ";
			qValue.add(activityId);
		}
		if (!StringUtil.isEmpty(workitem)) {
			where += "and workitem=? ";
			qValue.add(workitem);
		}
		if (!StringUtil.isEmpty(signer)) {
			where += "and signerId=? ";
			qValue.add(signer);
		}
		if (where.length() < 1) {
			throw new KhntException("参数错误");
		} else {
			Query q = opinionDao.createQuery("from Opinion where " + where, qValue.toArray());
			return (Opinion) q.uniqueResult();
		}
	}

	/**
	 * 更新业务流程意见所关联的环节id，主要目的用于支持流程未启动前提供用户起草业务数据时同时签署意见，并在流程启动后
	 * 
	 * @param serviceId
	 * @param preAId
	 * @param newAId
	 */
	void updateOpinionActivityId(String serviceId, String preAId, String newAId) {
		opinionDao.createQuery("update Opinion set activityId=? where serviceId=? and activityId=?",
				new Object[] { newAId, serviceId, preAId }).executeUpdate();
	}
}