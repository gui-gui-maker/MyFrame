package com.khnt.bpm.communal;

import java.util.Map;

/**
 * 流程中的参与者接口规范
 * 
 * @author hansel
 * 
 */
public interface BpmUser {

	/**
	 * 人员ID
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * 人员姓名
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 人员的单位
	 * 
	 * @return
	 */
	public BpmOrg getUnit();//

	/**
	 * 上级单位机构
	 * 
	 * @return
	 */
	public BpmOrg getParentUnit();

	/**
	 * 人员的部门
	 * 
	 * @return
	 */
	public BpmOrg getDepartment();//

	/**
	 * 人员对应的角色键值对，键:角色代码;值:角色名称
	 * 
	 * @return
	 */
	public Map<String, String> getRole();

}
