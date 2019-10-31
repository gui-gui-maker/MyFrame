package com.khnt.bpm.communal;

import java.util.List;

import com.khnt.rbac.bean.Org;

public class BpmOrgImpl implements BpmOrg {

	private String id;
	private String name;
	private BpmOrg parent;

	/**
	 * 新的构造方法，支持上下级关系
	 * 
	 * @since 由1.0.3版本开始提供，以替代原来的构造方法：BpmOrgImpl(String id, String name)
	 * 
	 * @param sysOrg
	 */
	public BpmOrgImpl(Org sysOrg) {
		this.id = sysOrg.getId();
		this.name = sysOrg.getOrgName();
		if (sysOrg.getParent() != null)
			this.parent = new BpmOrgImpl(sysOrg.getParent());
	}

	/**
	 * @deprecated 自1.0.3版本不再使用
	 * 
	 * @param id
	 * @param name
	 */
	@Deprecated
	public BpmOrgImpl(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public BpmOrg getParent() {
		return parent;
	}

	@Override
	public List<BpmUser> getBpmUserByRole(String roleCode) {
		return null;
	}

	@Override
	public List<BpmUser> getBpmUserByOrg(String orgCode) {
		return null;
	}

}
