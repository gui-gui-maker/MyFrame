package com.khnt.bpm.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.communal.BpmUser;
import com.khnt.bpm.communal.BpmUserOrgManager;
import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.core.bean.Participator;
import com.khnt.bpm.core.dao.ActivityDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.utils.StringUtil;

@Service("activityManager")
public class ActivityManager extends EntityManageImpl<Activity, ActivityDao> {

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	BpmUserOrgManager bpmUserOrgManager;

	/**
	 * 检查指定的活动环节是否拥有指定的功能
	 * 
	 * @param activityId
	 *            活动环节ID
	 * @param function
	 *            功能代码
	 * @return
	 */
	public boolean isActivityPre(String activityId, String function) {
		Activity activity = this.activityDao.get(activityId);
		if (activity == null)
			return false;

		String[] uFuns = function.split(",");
		for (String f : uFuns) {
			String functions = activity.getFunction();
			if (StringUtil.isEmpty(functions))
				return false;
			if (functions.contains(f))
				return true;
		}
		return false;
	}

	/**
	 * 根据流程业务标识，获取处于活动状态的环节
	 * 
	 * @param serviceId
	 * @return
	 */
	public List<Activity> getCurrentServiceActivity(String serviceId) {
		return this.activityDao.getCurrentServiceActivity(serviceId);
	}

	/**
	 * 获取到环节参与者，这些参与者是经过将角色转换为具体人员后的。
	 * 
	 * @param activity
	 * @return
	 */
	protected List<BpmUser> getBpmUserPaticipator(Activity activity) {
		List<BpmUser> bpmUsers = new ArrayList<BpmUser>();
		if (activity.getParticipators() == null || activity.getParticipators().isEmpty())
			return bpmUsers;
		for (Participator ptor : activity.getParticipators()) {
			BpmUser bpmUser = null;
			if (ptor.getParticipantType().equals("person")) {
				bpmUser = bpmUserOrgManager.getBpmUser(ptor.getParticipantId());
			} else if (ptor.getParticipantType().equals("role")) {
				bpmUser = bpmUserOrgManager.getRoleBpmUser(ptor.getParticipantId(), ptor.getParticipantRange());
			} else if (ptor.getParticipantType().equals("start")) {
				Activity startActivity = this.activityDao.getStartActivity(activity.getProcess().getId());
				bpmUsers.addAll(getBpmUserPaticipator(startActivity));
			}
			if (bpmUser != null)
				bpmUsers.add(bpmUser);
		}

		return bpmUsers;
	}

	/**
	 * 获取到环节最终人员参与者，这些参与者是经过将角色转换为具体人员后的。
	 * 
	 * @param activity
	 * @return
	 */
	public List<BpmUser> getBpmUserPaticipator(String activityId) {
		List<BpmUser> bpmUsers = new ArrayList<BpmUser>();
		Activity activity = this.activityDao.get(activityId);
		if (activity.getParticipators() == null || activity.getParticipators().isEmpty())
			return bpmUsers;
		for (Participator ptor : activity.getParticipators()) {
			BpmUser bu = null;
			if (ptor.getParticipantType().equals("person")) {
				bu = bpmUserOrgManager.getBpmUser(ptor.getParticipantId());

			} else if (ptor.getParticipantType().equals("role")) {
				bu = bpmUserOrgManager.getRoleBpmUser(ptor.getParticipantId(), ptor.getParticipantRange());
			} else if (ptor.getParticipantType().equals("start")) {
				// 获取启动者
				Activity startActivity = this.activityDao.getStartActivity(activity.getProcess().getId());
				bpmUsers.addAll(this.getBpmUserPaticipator(startActivity));
			}
			if (bu != null)
				bpmUsers.add(bu);
		}
		return bpmUsers;
	}
}
