package com.scts.cspace.space.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.TS_Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.OrgDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.scts.cspace.path.bean.TjyptResourcePath;
import com.scts.cspace.path.dao.TjyptResourcePathDao;
import com.scts.cspace.resource.bean.QueryResourceInfo;
import com.scts.cspace.resource.bean.TjyptResourceInfo;
import com.scts.cspace.resource.dao.TjyptResourceInfoDao;
import com.scts.cspace.space.bean.TjyptResourceSpace;
import com.scts.cspace.space.dao.TjyptResourceSpaceDao;

/**
 * 资源空间属性业务逻辑对象
 * 
 * @ClassName TjyptRrsourceSpaceService
 * @JDK 1.7
 * @author xcb
 * @date 2016-10-24 下午04:40:00
 */
@Service("tjyptRrsourceSpaceService")
public class TjyptResourceSpaceService extends
		EntityManageImpl<TjyptResourceSpace, TjyptResourceSpaceDao> {

	@Autowired
	private TjyptResourceSpaceDao tjyptRrsourceSpaceDao;

	@Autowired
	private TjyptResourcePathDao tjyptResourcePathDao;
	@Autowired
	private TjyptResourceInfoDao tjyptResourceInfoDao;
	@Autowired
	private OrgDao orgDao;

	public HashMap<String, Object> initPersonalSpace() throws Exception {
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();

		CurrentSessionUser user = SecurityUtil.getSecurityUser();// 获取登录用户

		TjyptResourceSpace resourceSpace = new TjyptResourceSpace();// 创建个人空间对象

		// 设置个人空间属性
		resourceSpace.setSpace_type("1");// 空间属性9、院资源空间7、部门资源空间5、群组资源空间1、个人资源空间

		resourceSpace.setSpace_owner(user.getUserId());// 空间所属人员ID
		
		resourceSpace.setSpace_hidden_type("1");//默认隐藏

		resourceSpace.setSpace_create_date(new Date());// 空间创建时间

		resourceSpace.setSpace_use_size("0");// 空间实际使用大小，默认0

		resourceSpace.setSpace_max_size("10485760");// 空间大小 默认10G,单位KB// 之前为10485760
		resourceSpace.setSpace_hidden_password("123456");
		tjyptRrsourceSpaceDao.save(resourceSpace);

		// 创建资源路径分类根目录
		TjyptResourcePath resourcePath = new TjyptResourcePath();

		resourcePath.setFk_resource_space_id(resourceSpace.getId());// 所属空间ID

		resourcePath.setPath_name("根目录");// 名称

		resourcePath.setParent_path_id("0");

		resourcePath.setPath_important_flag("0");

		resourcePath.setPath_is_hidden("0");

		resourcePath.setPath_is_recyclebin("0");

		resourcePath.setPath_order_no("0");
		
		//resourcePath.setPath_accsess_password("123456");//初始化密码123456
		

		resourcePath.setPath_remark("0");

		tjyptResourcePathDao.save(resourcePath);
		
		wrapper.put("spaceId", resourceSpace.getId());
		wrapper.put("hiddenType", resourceSpace.getSpace_hidden_type());
		
		return wrapper;//返回个人空间ID

	}

	public TjyptResourceSpace openPersonalSpace(String userId, String spaceType)
			throws Exception {

		// 是否存在个人空间
		List<TjyptResourceSpace> list = tjyptRrsourceSpaceDao.createQuery(
				"from " + "TjyptResourceSpace where space_owner='" + userId
						+ "' and space_type='" + spaceType + "'").list();

		if (list.size() > 0) {// 存在数据返回true

			return list.get(0);

		}

		return null;

	}

	public HashMap<String, Object> queryResourceInfo(String spaceId)
			throws Exception {

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		List inlist = new  ArrayList();

		// 查询空间下的根目录文件
		List<TjyptResourcePath> list = tjyptRrsourceSpaceDao.createQuery(
				"from " + "TjyptResourcePath where fk_resource_space_id='"
						+ spaceId + "' and parent_path_id='0'").list();

		// 获取根目录下的文件和资源 在回收站的不查出
		List<TjyptResourcePath> infoList = tjyptResourcePathDao.createQuery(
				"from  TjyptResourcePath where  parent_path_id='" +list.get(0).getId()
						+ "' and path_is_recyclebin='0' order by path_order_no desc").list();
		
		if(infoList.size()>0){
			
			for(TjyptResourcePath path:infoList ){
				QueryResourceInfo info  = new QueryResourceInfo();
				info.setId(path.getId());
				info.setInfoIsHidden(path.getPath_is_hidden());
				info.setInfoName(path.getPath_name());
				info.setInfoType("1");//1是文件夹2是资源
				info.setInfoRemark(path.getPath_remark());
				info.setLast_update_date(path.getPath_last_update_date());
				info.setLevel(path.getPath_important_flag());
				info.setShareUser(path.getPath_share_user());
				inlist.add(info);
			}
		}
		List<TjyptResourceInfo> resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_path='" +list.get(0).getId()
						+ "' and resource_is_recyclebin='0' and resource_status='1' and resource_share_status is null order by resource_order_no desc").list();

		if(resourceList.size()>0){
		
			for(TjyptResourceInfo resinfo:resourceList ){
				QueryResourceInfo info  = new QueryResourceInfo();
			
				info.setId(resinfo.getId());
				info.setInfoIsHidden(resinfo.getResource_is_hidden());
				info.setInfoName(resinfo.getResource_name());
				info.setInfoRemark(resinfo.getResource_remark());
				info.setLast_update_date(resinfo.getResource_last_update_date());
				info.setInfoSize(resinfo.getResource_size());
				info.setInfoType("2");//1是文件夹2是资源
				info.setResourceType(resinfo.getResource_type());
				info.setLevel(resinfo.getResource_important_flag());
				info.setShareUser(resinfo.getResource_share_user());
				inlist.add(info);
			}
		}
		TjyptResourceSpace resourceSpace = tjyptRrsourceSpaceDao.get(spaceId);
		wrapper.put("spaceId", spaceId);//根目录ID
		wrapper.put("rootId", list.get(0).getId());//根目录ID
		wrapper.put("queryInfo",inlist);
		wrapper.put("hiddenType", resourceSpace.getSpace_hidden_type());
		
		
		return wrapper;

	}
	
	public HashMap<String, Object> queryResourceByType(String userId, String spaceType,String resourceType)
			throws Exception {

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		List inlist = new  ArrayList();
		//查询空间
		List<TjyptResourceSpace> resourceSpace = new ArrayList<TjyptResourceSpace>();
				String hql1="from TjyptResourceSpace where space_owner=? and space_type='" + spaceType + "'";
		 if(spaceType.equals("1")){
			 resourceSpace = tjyptRrsourceSpaceDao.createQuery(hql1,user.getId()).list();
		 }else if(spaceType.equals("9")){
			 resourceSpace = tjyptRrsourceSpaceDao.createQuery(hql1,"100000").list();
		 }else if(spaceType.equals("7")){
			 resourceSpace = tjyptRrsourceSpaceDao.createQuery(hql1,TS_Util.getCurOrg(user).getId()).list();
		 }else if(spaceType.equals("5")){
			 
		 }
		// 查询空间下的根目录文件
				List list1 = tjyptRrsourceSpaceDao.createSQLQuery(
						" SELECT T.ID from Tjypt_Resource_Path t where t.fk_resource_space_id='"
								+ resourceSpace.get(0).getId() 
								+"' connect by prior t.id = t.parent_path_id "
					    		  + " start with t.parent_path_id='0'").list();
		
		if(resourceSpace.size()>0){
			String resourceTypes[]=resourceType.split(",");
			for (int i = 0; i < resourceTypes.length; i++) {
				String hql="from  TjyptResourceInfo where  resource_owner_id=?" 
						+ " and resource_type='"+resourceTypes[i]+"' and resource_is_recyclebin='0'"
								+ " and resource_status='1' and resource_share_status is null order by resource_last_update_date desc nulls last ";
				List<TjyptResourceInfo> resourceList=new ArrayList<TjyptResourceInfo>();
				 if(spaceType.equals("1")){
					 resourceList = tjyptResourceInfoDao.createQuery(hql,user.getId()).list();
				 }else if(spaceType.equals("9")){
					 resourceList = tjyptResourceInfoDao.createQuery(hql,"100000").list();
				 }else if(spaceType.equals("7")){
					 resourceList = tjyptResourceInfoDao.createQuery(hql,TS_Util.getCurOrg(user).getId()).list();
				 }else if(spaceType.equals("5")){
					 
				 }

				if(resourceList.size()>0){
		
			    	for(TjyptResourceInfo resinfo:resourceList ){
					QueryResourceInfo info  = new QueryResourceInfo();
				
					info.setId(resinfo.getId());
					info.setInfoIsHidden(resinfo.getResource_is_hidden());
					info.setInfoName(resinfo.getResource_name());
					info.setInfoRemark(resinfo.getResource_remark());
					info.setLast_update_date(resinfo.getResource_last_update_date());
					info.setInfoSize(resinfo.getResource_size());
					info.setInfoType("2");//1是文件夹2是资源
					info.setResourceType(resinfo.getResource_type());
					info.setLevel(resinfo.getResource_important_flag());
					info.setShareUser(resinfo.getResource_share_user());
					inlist.add(info);
			    	}
				}
			}
			wrapper.put("queryInfo",inlist);
			wrapper.put("flag","2");
			
		}	
		
		return wrapper;

	}
	
	public HashMap<String, Object> queryResourceByTypes(String userId, String spaceType,String resourceType)
			throws Exception {

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		List inlist = new  ArrayList();
		//查询空间
		List<TjyptResourceSpace> resourceSpace = new ArrayList<TjyptResourceSpace>();
				String hql1="from TjyptResourceSpace where space_owner=? and space_type='" + spaceType + "'";
		 if(spaceType.equals("1")){
			 resourceSpace = tjyptRrsourceSpaceDao.createQuery(hql1,user.getId()).list();
		 }else if(spaceType.equals("9")){
			 resourceSpace = tjyptRrsourceSpaceDao.createQuery(hql1,"100000").list();
		 }else if(spaceType.equals("7")){
			 resourceSpace = tjyptRrsourceSpaceDao.createQuery(hql1,TS_Util.getCurOrg(user).getId()).list();
		 }else if(spaceType.equals("5")){
			 
		 }
		// 查询空间下的根目录文件
				List list1 = tjyptRrsourceSpaceDao.createSQLQuery(
						" SELECT T.ID from Tjypt_Resource_Path t where t.fk_resource_space_id='"
								+ resourceSpace.get(0).getId() 
								+"' connect by prior t.id = t.parent_path_id "
					    		  + " start with t.parent_path_id='0'").list();
		
		if(resourceSpace.size()>0){
			String resourceTypes[]=resourceType.split(",");
			for (int i = 0; i < resourceTypes.length; i++) {
				String hql="from  TjyptResourceInfo where  resource_owner_id=?" 
						+ " and resource_type='"+resourceTypes[i]+"' and resource_is_recyclebin='0'"
								+ " and resource_status='1' order by resource_last_update_date desc nulls last ";
				List<TjyptResourceInfo> resourceList=new ArrayList<TjyptResourceInfo>();
				 if(spaceType.equals("1")){
					 resourceList = tjyptResourceInfoDao.createQuery(hql,user.getId()).list();
				 }else if(spaceType.equals("9")){
					 resourceList = tjyptResourceInfoDao.createQuery(hql,"100000").list();
				 }else if(spaceType.equals("7")){
					 resourceList = tjyptResourceInfoDao.createQuery(hql,TS_Util.getCurOrg(user).getId()).list();
				 }else if(spaceType.equals("5")){
					 
				 }

				if(resourceList.size()>0){
		             int num=0;
			    	for(TjyptResourceInfo resinfo:resourceList ){
			    		if(num==20){
			    			break;
			    		}
					QueryResourceInfo info  = new QueryResourceInfo();
				
					info.setId(resinfo.getId());
					info.setInfoIsHidden(resinfo.getResource_is_hidden());
					info.setInfoName(resinfo.getResource_name());
					info.setInfoRemark(resinfo.getResource_remark());
					info.setLast_update_date(resinfo.getResource_last_update_date());
					info.setInfoSize(resinfo.getResource_size());
					info.setInfoType("2");//1是文件夹2是资源
					info.setResourceType(resinfo.getResource_type());
					info.setLevel(resinfo.getResource_important_flag());
					info.setShareUser(resinfo.getResource_share_user());
					inlist.add(info);
					num++;
			    	}
				}
			}
			wrapper.put("queryInfo",inlist);
			wrapper.put("flag","2");
			
		}	
		
		return wrapper;

	}

	public void updateSpaceType(String spaceId, String spaceType)
			throws Exception {

		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象

		resourceSpace.setSpace_type(spaceType);// 修改资源空间类型

		tjyptRrsourceSpaceDao.save(resourceSpace);// 保存修改后对象

	}

	public void updateSpaceMaxSize(String spaceId, String spaceMaxSize)
			throws Exception {

		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象

		resourceSpace.setSpace_max_size(spaceMaxSize);// 修改资源空间大小

		tjyptRrsourceSpaceDao.save(resourceSpace);// 保存修改后对象

	}

	public void updateSpaceUseSize(String spaceId, String spaceUseSize)
			throws Exception {

		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象

		resourceSpace.setSpace_use_size(spaceUseSize);// 修改资源实际空间大小

		tjyptRrsourceSpaceDao.save(resourceSpace);// 保存修改后对象

	}

	public void updateSpaceAccessPassword(String spaceId,
			String spaceAccessPassword) throws Exception {

		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象

		resourceSpace.setSpace_access_password(spaceAccessPassword);// 设置资源空间密码

		tjyptRrsourceSpaceDao.save(resourceSpace);// 保存修改后对象

	}

	public void updateSpaceOwner(String spaceId, String spaceOwner)
			throws Exception {

		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象

		resourceSpace.setSpace_owner(spaceOwner);// 设置资源空间密码

		tjyptRrsourceSpaceDao.save(resourceSpace);// 保存修改后对象

	}

	//更新访问隐藏模式密码
	public void updateSpaceHiddenPassword(String spaceId, String password){
		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象
		resourceSpace.setSpace_hidden_password(password);
		tjyptRrsourceSpaceDao.save(resourceSpace);
	}
	//验证隐藏模式密码
	public boolean VarifySpaceHiddenPassword(String spaceId , String password){
		boolean b = false ;
		TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象
		if(password.equals(resourceSpace.getSpace_hidden_password())){
			b = true ;
		}
		return b;
	}
	
	//更新空间隐藏模式 type 0 - 普通模式  1-隐藏模式
	public HashMap<String, Object> updateSpaceHiddenType(String spaceId , String passWord,String type ){
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		if(passWord.equals("")||passWord==null){
			
			wrapper.put("flag","1");
			
		}else{
			TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象	
			
			if(!passWord.equals(resourceSpace.getSpace_hidden_password())){
				wrapper.put("flag","2");
			}else{
				
				resourceSpace.setSpace_hidden_type(type);
				tjyptRrsourceSpaceDao.save(resourceSpace);
				
				wrapper.put("flag","3");
				
			}
			
			
		}
		return wrapper;
		
		
	
		
	}
	

	//更新空间隐藏模式 type 0 - 普通模式  1-隐藏模式
	public void updateSpaceNotHidden(String spaceId , String type ){
		
	
			TjyptResourceSpace resourceSpace = this.get(spaceId); // 获取资源空间对象	
			
			
				
			resourceSpace.setSpace_hidden_type(type);
			tjyptRrsourceSpaceDao.save(resourceSpace);
				
			
		
		
	
		
	}
	
	/**
	 * 根据用户Id和空间类型获取空间对象
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	@SuppressWarnings(value = { "unchecked" })
	public TjyptResourceSpace queryResourceByUserAndType(String userId,String spaceType){
		List<TjyptResourceSpace> list = tjyptRrsourceSpaceDao.createQuery(
				"from " + "TjyptResourceSpace where space_owner='" + userId
						+ "' and space_type='" + spaceType + "'").list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public JSONArray getOrgTree(String orgIds) {
		JSONArray orgs = new JSONArray();
		String[] ids = orgIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			Org org = orgDao.get(ids[i]);
			JSONObject obj = new JSONObject();
			obj.put("id", org.getId());
			obj.put("open", "true");
			obj.put("text", org.getOrgName());
			obj.put("level", "0");
			obj.put("sort", ""+i);
			obj.put("isexpand",true);
			obj.put("orgCode", org.getOrgCode());
			obj.put("levelCode", org.getLevelCode());
			obj.put("isParent", true);
			getChildren(obj,ids[i],0,i);
			System.out.println("----------------"+obj+"___________-");
			orgs.add(obj);
		}
		return orgs;
	}

	private void getChildren(JSONObject obj1, String id, int n,int m) {
		String hql = "from Org o where o.parent.id = ?";
		List<Org> list = orgDao.createQuery(hql, id).list();
		for (int j = 0; j < list.size(); j++) {
			Org org = list.get(j);
			JSONObject obj = new JSONObject();
			obj.put("id", org.getId());
			obj.put("open", "true");
			obj.put("text", org.getOrgName());
			obj.put("level", ""+n);
			String s = "0";
			for (int i = 0; i < n; i++) {
				s = s+"0";
			}
			obj.put("sort", s+m);
			obj.put("isexpand",true);
			obj.put("orgCode", org.getOrgCode());
			obj.put("levelCode", org.getLevelCode());
			obj.put("isParent", true);
			obj1.put("children", obj);
			getChildren(obj,org.getId(),n+1,m);
		}
		System.out.println("----------------"+obj1+"___________-");
	}

	public List<Map<String, Object>> getOrgByPid(String orgId) {
		List<Map<String, Object>> listm = new ArrayList<Map<String, Object>>();
		List<Object> list = new ArrayList<Object>();
		String hql = "";
		if(orgId==null){
			hql = "select o.id,o.parent_id,o.org_name from sys_Org o where o.parent_id is null and o.status='used'";
			list = tjyptRrsourceSpaceDao.createSQLQuery(hql).list();
		}else{
			hql = "select o.id,o.parent_id,o.org_name from sys_Org o where o.parent_id = ?  and o.status='used'";
			list = tjyptRrsourceSpaceDao.createSQLQuery(hql, orgId).list();
		}
		for (int i = 0; i < list.size(); i++) {
			Object [] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", (obj[0]==null)?"":obj[0].toString());
			map.put("pid", (obj[1]==null)?"":obj[1].toString());
			map.put("name", (obj[2]==null)?"":obj[2].toString());
			listm.add(map);
		}
		
		return listm;
	}

	public List<Map<String, Object>> getUserByOrgid(String orgId) {
		List<Map<String, Object>> listm = new ArrayList<Map<String, Object>>();
		String hql = "select o.id,o.name from sys_User o where o.org_id = '"+orgId+"' and o.status='1'";
		if(orgId==null){
			hql = "select o.id,o.name from sys_User o  where o.status='1'";
		}
		List<Object> list = tjyptRrsourceSpaceDao.createSQLQuery(hql).list();
		for (int i = 0; i < list.size(); i++) {
			Object [] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", (obj[0]==null)?"":obj[0].toString());
			map.put("name", (obj[1]==null)?"":obj[1].toString());
			listm.add(map);
		}
		
		return listm;
	}

	public boolean checkSpaceSize(String spaceType, long size, String spaceId) {
		
		
		TjyptResourceSpace resourceSpace =  tjyptRrsourceSpaceDao.get(spaceId);
		String use_size = (resourceSpace.getSpace_use_size()==null)?"0":resourceSpace.getSpace_use_size();
		
		if(resourceSpace.getSpace_type().equals("1")){
			if((new Float(use_size)
					+(size/1024))>new Float(resourceSpace.getSpace_max_size())){
				return false;
				//空间不足
			}else{
				return true;
			}
			
		}else{
			return true;
			//不用验证空间
		}
	} 



	
}
