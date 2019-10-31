package com.khnt.bpm.communal;

/**
 * 工作流引擎用户机构管理接口规范，每个业务系统中都应该实现此接口， 使得工作流引擎能够使用业务系统的机构与用户信息。
 * 
 * @author hansel
 * 
 */
public interface BpmUserOrgManager {
	/**
	 * 根据机构ID获取机构所有信息
	 * 
	 * @param id
	 * @return
	 */
	public BpmOrg getBpmOrg(String id);

	/**
	 * 根据用户ID，获取用户信息
	 * 
	 * @param uid
	 * @return
	 */
	public BpmUser getBpmUser(String uid);

	/**
	 * 根据角色ID获取用户信息
	 * 
	 * @param roleId
	 * @return
	 */
	public BpmUser getRoleBpmUser(String roleId, String range);
}
