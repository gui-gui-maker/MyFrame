package com.khnt.bpm.communal;

import java.util.List;

/**
 * 流程中涉及的人员机构接口规范
 * 
 * @author hansel
 * 
 */
public interface BpmOrg {

	/**
	 * 机构ID
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * 机构名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 上级单位
	 * 
	 * @return
	 */
	public BpmOrg getParent();

	/**
	 * 根据角色编号得到对应的人员集合
	 * 
	 * @param roleCode
	 * @return
	 */
	public List<BpmUser> getBpmUserByRole(String roleCode);

	/**
	 * 根据角色编号得到对应人员集合
	 * 
	 * @param orgCode
	 * @return
	 */
	public List<BpmUser> getBpmUserByOrg(String orgCode);
}
