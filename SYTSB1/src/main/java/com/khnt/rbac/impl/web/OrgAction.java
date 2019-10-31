/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.khnt.rbac.impl.web;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.manager.OrgManagerImpl;
import com.khnt.utils.StringUtil;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "/rbac/org/" })
public class OrgAction extends SpringSupportAction<Org, OrgManagerImpl> {

	@Autowired
	private OrgManagerImpl orgManager;


	@RequestMapping({ "orgTree" })
	public void orgTree(HttpServletResponse response, String orgid)
			throws Exception {
		String menu = this.orgManager.searchOrgTreeById(orgid);
		response.getWriter().write(menu);
	}

	@RequestMapping({ "getSubordinate" })
	@ResponseBody
	public String getSubordinate(String orgid) throws Exception {
		boolean isRoot = StringUtil.isEmpty(orgid);
		Org parent;
		if (isRoot)
			parent = this.orgManager.getTop();
		else {
			parent = (Org) this.orgManager.get(orgid);
		}
		JSONArray jay = new JSONArray();
		Iterator orgsItr = parent.getChildren().iterator();
		int j=0;
		for (int i = 0; i < parent.getChildren().size(); ++i) {
			Org o = (Org) orgsItr.next();
			boolean hasSub = !(o.getChildren().isEmpty());
			JSONObject jo = new JSONObject();		
			// ||o.getId().equals("100069") 针对2017赴藏特检突击队，放开该部门在系统机构的显示和选择
			if(o.getId().equals("100039")||o.getId().equals("100061")||o.getId().equals("100032")||o.getId().equals("100070")||o.getId().equals("100038")){
				
				continue;
			}
			
			jo.put("id", o.getId());
			jo.put("text", o.getOrgName());
			jo.put("orgName", o.getOrgName());
			jo.put("orgCode", o.getOrgCode());
			jo.put("tellphone", o.getTellphone());
			jo.put("property", o.getProperty());
			jo.put("sort", o.getSort());
			jo.put("areaCode", o.getAreaCode());
			jo.put("areaName", o.getAreaName());
			jo.put("describle", o.getDiscrible());
			jo.put("levelCode", o.getLevelCode());
			jo.put("level", (hasSub) ? "1" : (o.getParent() == null) ? "0"
					: "2");
			if (hasSub) {
				jo.put("children", new JSONArray());
				jo.put("isexpand", false);
				jo.put("open", false);
				jo.put("isParent", true);
			}
			jay.put(j, jo);
			j++;
		}
		if (isRoot) {
			JSONArray result = new JSONArray();
			JSONObject jo = new JSONObject();
			jo.put("id", parent.getId());
			jo.put("text", parent.getOrgName());
			jo.put("orgName", parent.getOrgName());
			jo.put("orgCode", parent.getOrgCode());
			jo.put("tellphone", parent.getTellphone());
			jo.put("property", parent.getProperty());
			jo.put("sort", parent.getSort());
			jo.put("areaCode", parent.getAreaCode());
			jo.put("areaName", parent.getAreaName());
			jo.put("describle", parent.getDiscrible());
			jo.put("levelCode", parent.getLevelCode());
			jo.put("level", "0");
			jo.put("children", jay);
			jo.put("isexpand", true);
			jo.put("open", true);
			jo.put("isParent", true);
			result.put(jo);
			return result.toString();
		}
		return jay.toString();
	}
	@RequestMapping({ "getSubordinates" })
	@ResponseBody
	public String getSubordinates(String orgid) throws Exception {
		boolean isRoot = StringUtil.isEmpty(orgid);
		Org parent;
		if (isRoot)
			parent = this.orgManager.getTop();
		else {
			parent = (Org) this.orgManager.get(orgid);
		}
		JSONArray jay = new JSONArray();
		Iterator orgsItr = parent.getChildren().iterator();
		for (int i = 0; i < parent.getChildren().size(); ++i) {
			Org o = (Org) orgsItr.next();
			boolean hasSub = !(o.getChildren().isEmpty());
			JSONObject jo = new JSONObject();
			
			
			jo.put("id", o.getId());
			jo.put("text", o.getOrgName());
			jo.put("orgName", o.getOrgName());
			jo.put("orgCode", o.getOrgCode());
			jo.put("tellphone", o.getTellphone());
			jo.put("property", o.getProperty());
			jo.put("sort", o.getSort());
			jo.put("areaCode", o.getAreaCode());
			jo.put("areaName", o.getAreaName());
			jo.put("describle", o.getDiscrible());
			jo.put("levelCode", o.getLevelCode());
			jo.put("level", (hasSub) ? "1" : (o.getParent() == null) ? "0"
					: "2");
			if (hasSub) {
				jo.put("children", new JSONArray());
				jo.put("isexpand", false);
				jo.put("open", false);
				jo.put("isParent", true);
			}
			jay.put(i, jo);
		}
		if (isRoot) {
			JSONArray result = new JSONArray();
			JSONObject jo = new JSONObject();
			jo.put("id", parent.getId());
			jo.put("text", parent.getOrgName());
			jo.put("orgName", parent.getOrgName());
			jo.put("orgCode", parent.getOrgCode());
			jo.put("tellphone", parent.getTellphone());
			jo.put("property", parent.getProperty());
			jo.put("sort", parent.getSort());
			jo.put("areaCode", parent.getAreaCode());
			jo.put("areaName", parent.getAreaName());
			jo.put("describle", parent.getDiscrible());
			jo.put("levelCode", parent.getLevelCode());
			jo.put("level", "0");
			jo.put("children", jay);
			jo.put("isexpand", true);
			jo.put("open", true);
			jo.put("isParent", true);
			result.put(jo);
			return result.toString();
		}
		return jay.toString();
	}
	@RequestMapping({ "orgUserTreeById" })
	public void orgUserTreeById(HttpServletResponse response, String orgid)
			throws Exception {
		String menu = this.orgManager.searchOrgUserTreeById(orgid);
		response.getWriter().write(menu);
	}

	@RequestMapping({ "deletenode" })
	@ResponseBody
	public Map<String, Object> deletenode(String ids) throws Exception {
		if (StringUtil.isEmpty(ids)) {
			this.log.error("要删除的ID集合为空");
			return JsonWrapper.failureWrapper(new Object[0]);
		}
		Org org = (Org) this.orgManager.get(ids);
		this.orgManager.deleteWithExt(ids.split(","));
		HashMap map = new HashMap();
		if (!(StringUtil.isEmpty(org.getParent().toString()))) {
			map.put("pid", org.getParent().getId());
		}
		return JsonWrapper.wrapperMap(true, map);
	}

	@RequestMapping({ "saveOrg" })
	@ResponseBody
	public HashMap<String, Object> save(Org org, String pid) throws Exception {
		if (!(StringUtil.isEmpty(pid))) {
			Org parent = new Org();
			parent.setId(pid);
			org.setParent(parent);
		}
		this.orgManager.saveWithExt(org);
		return JsonWrapper.successWrapper(org);
	}

	public HashMap<String, Object> detail(HttpServletRequest request, String id) {
		Org org = (Org) this.orgManager.get(id);
		if (org.getParent() != null)
			org.getParent().setParent(null);
		return JsonWrapper.successWrapper(org);
	}

	@RequestMapping({ "changeParent" })
	@ResponseBody
	public Map<String, Object> changeParent(String orgId, String parentId)
			throws Exception {
		this.orgManager.changeParentWithExt(orgId, parentId);
		return JsonWrapper.successWrapper(new Object[0]);
	}

	@RequestMapping({ "authorizedRoleBatch" })
	@ResponseBody
	public Map<String, Object> authorizedRolesBatch(String orgIds, String roles)
			throws Exception {
		this.orgManager.authorizedRolesBatch(orgIds, roles);
		return JsonWrapper.successWrapper(new Object[0]);
	}

	@RequestMapping({ "authorizedRole" })
	@ResponseBody
	public Map<String, Object> authorizedRoles(String orgId, String roles)
			throws Exception {
		this.orgManager.authorizeRoles(orgId, roles);
		return JsonWrapper.successWrapper(new Object[0]);
	}

	@RequestMapping({ "initAuthorizedRole" })
	public ModelAndView initAuthorizedRoles(String orgId) throws Exception {
		ModelAndView mav = new ModelAndView("pub/rbac/org_Authorized_role");
		Org org = (Org) this.orgManager.get(orgId);
		if (org != null)
			mav.getModel().put("roles", org.getRoles());
		return mav;
	}

	@RequestMapping({ "initAuthorizedPermission" })
	public String initAuthorizedPermission(HttpServletRequest request,
			String orgId) throws Exception {
		Org org = (Org) this.orgManager.get(orgId);
		if (org != null) {
			request.setAttribute("orgPermissions", org.getPermissions());
		}
		return "pub/rbac/org_Authorized_permission_index";
	}

	@RequestMapping({ "authorizedPermission" })
	@ResponseBody
	public Map<String, Object> authorizedPermissions(String orgId,
			String permissions) throws Exception {
		this.orgManager.authorizePermissions(orgId, permissions);
		return JsonWrapper.successWrapper(new Object[0]);
	}

	@RequestMapping({ "authorizedPerBatch" })
	@ResponseBody
	public Map<String, Object> authorizedPersBatch(String orgIds,
			String permissions) throws Exception {
		this.orgManager.authorizedPersBatch(orgIds, permissions);
		return JsonWrapper.successWrapper(new Object[0]);
	}

	@RequestMapping({ "getTopOrg" })
	@ResponseBody
	public Map<String, Object> getTopOrg() throws Exception {
		return JsonWrapper.successWrapper(this.orgManager.getTop());
	}
}