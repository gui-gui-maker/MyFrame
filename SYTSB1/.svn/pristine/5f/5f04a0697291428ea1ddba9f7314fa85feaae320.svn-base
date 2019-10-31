package com.scts.cspace.path.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.cspace.log.service.TjyptLogService;
import com.scts.cspace.path.bean.TjyptResourcePath;
import com.scts.cspace.path.dao.TjyptResourcePathDao;
import com.scts.cspace.resource.bean.QueryResourceInfo;
import com.scts.cspace.resource.bean.TjyptResourceInfo;
import com.scts.cspace.resource.dao.TjyptResourceInfoDao;
import com.scts.cspace.resource.service.TjyptResourceInfoService;
import com.scts.cspace.space.bean.TjyptResourceSpace;
import com.scts.cspace.space.dao.TjyptResourceSpaceDao;

import util.TS_Util;

/**
 * 资源分类属性业务逻辑对象
 * @ClassName TjyptRrsourceSpaceService
 * @JDK 1.7
 * @author xcb
 * @date 2016-10-24 下午04:40:00
 */
@Service("tjyptResourcePathService")
public class TjyptResourcePathService extends
		EntityManageImpl<TjyptResourcePath, TjyptResourcePathDao> {
	
	@Autowired
	private TjyptResourcePathDao tjyptResourcePathDao;
	@Autowired
	private  TjyptResourceInfoDao tjyptResourceInfoDao;
	@Autowired
	private TjyptLogService tjyptLogService;
	@Autowired
	private TjyptResourceInfoService tjyptResourceInfoService;
	@Autowired
	private TjyptResourceSpaceDao tjyptResourceSpaceDao;
	@Autowired
	private UserDao userDao;
	
	
	public void updateParentPathId(String pathId,String parentPathId) throws Exception {
	
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setParent_path_id(parentPathId);//修改资源上级资源分类
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	
	public void delResourcePath(String delId,String type, HttpServletRequest request) throws Exception {
		//批量
		if(delId.indexOf(",")!=-1){
			
			String id[] =delId.split(",");
			String temp[]=type.split(",");
			
			for(int i=0;i<id.length;i++){
				
				if(temp[i].equals("1")){//文件夹
					 TjyptResourcePath resourcePath = tjyptResourcePathDao.get(id[i]);//获取是否为文件对象
					 deletePath(resourcePath.getId(),request);//递归算法
					 
					tjyptResourcePathDao.createSQLQuery("update  tjypt_resource_path  set path_is_recyclebin='1' where id =?",id[i] ).executeUpdate();
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					String ip = "";
					if(request!=null){
						ip = TS_Util.getIpAddress(request);
					}
					TjyptResourcePath  resourceInfo = tjyptResourcePathDao.get(delId);	//获取资源对象	
					 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
							 delId, "删除文件："+resourceInfo.getPath_name(), ip,resourceInfo.getFk_resource_space_id(),"");
				}else{//资源
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					String ip = "";
					if(request!=null){
						ip = TS_Util.getIpAddress(request);
					}
					TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(id[i]);	//获取资源对象	
					/*TjyptResourcePath  resourcepath= tjyptResourcePathDao.get(resourceInfo.getResource_path());
					TjyptResourceSpace space = tjyptResourceSpaceDao.get(resourcepath.getFk_resource_space_id());*/
					 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
							 id[i], "删除文件："+resourceInfo.getResource_name(), ip,"","");
					tjyptResourceInfoDao.createSQLQuery("update  tjypt_resource_info  set resource_is_recyclebin='1' where id =?", id[i]).executeUpdate();
				}
				
				
			}
			
			
		}else{
			
			if(type.equals("1")){//文件夹
				 TjyptResourcePath resourcePath = tjyptResourcePathDao.get(delId);//获取是否为文件对象
				 deletePath(resourcePath.getId(),request);//递归算法
				 
				tjyptResourcePathDao.createSQLQuery("update  tjypt_resource_path  set path_is_recyclebin='1' where id =?",delId ).executeUpdate();
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
						 delId, "删除文件："+resourcePath.getPath_name(), ip,resourcePath.getFk_resource_space_id(),"");
				
			}else{//资源
				
				tjyptResourceInfoDao.createSQLQuery("update  tjypt_resource_info  set resource_is_recyclebin='1' where id =?", delId).executeUpdate();
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(delId);	//获取资源对象	
				 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
						 delId, "删除文件："+resourceInfo.getResource_name(), ip,"","");
			}
			
		}
		
		
		
	}
	
	public String   createResourcePath(String spaceId,String parentId,String pathName) throws Exception {
		
		String flag="";
		
		List<TjyptResourcePath> list = tjyptResourcePathDao.createQuery(
				"from  TjyptResourcePath where path_name='"+pathName+
				"' and fk_resource_space_id= '"+spaceId+"' and parent_path_id= '"+parentId+"' and path_is_recyclebin<>'1'").list();
		
		if(list.size()>0){
			
			 flag="1";
			
		}else{
		
		
			TjyptResourcePath  resourcePath = new  TjyptResourcePath();	//创建资源分类对象
			
			resourcePath.setFk_resource_space_id(spaceId);//所属资源空间
			
			resourcePath.setParent_path_id(parentId);//资源上级资源分类
			
			resourcePath.setPath_name(pathName);//资源路径名称
			
			resourcePath.setPath_is_recyclebin("0");
			resourcePath.setPath_last_update_date(new Date());
			resourcePath.setPath_create_date(new Date());
			CurrentSessionUser user=SecurityUtil.getSecurityUser();
			resourcePath.setPath_create_user_id(user.getId());
			
			tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
			flag="2";
		}
		return flag;
		
		
	}
	
	public void updatePathName(String pathId,String pathName) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_name(pathName);//修改资源分类名称
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	
	public void updatePathRemark(String pathId,String pathRemark) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_remark(pathRemark);//修改资源分类备注
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void hiddenPath(String pathId) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_is_hidden("1");//修改资源分类为隐藏
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void showPath(String pathId) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_is_hidden("0");//修改资源分类为显示
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void pathInRecyclebin(String pathId) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_is_recyclebin("1");//修改资源分类在回收站
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void pathOutRecyclebin(String pathId) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_is_recyclebin("0");//修改资源分类不在回收站
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void updatePathImportantFlag(String pathId,String pathImportantFlag) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_important_flag(pathImportantFlag);//修改资源分类重要等级
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void updatePathOrderNo(String pathId,String pathOrderNo) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_order_no(pathOrderNo);//修改资源分类自定义排序
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	public void updatePathAccessPassword(String pathId,String pathAccessPassword) throws Exception {
		
		TjyptResourcePath  resourcePath = this.get(pathId);	//获取资源分类对象
		
		resourcePath.setPath_accsess_password(pathAccessPassword);//修改资源分类访问验证密码
		
		tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		
		
	}
	
	
	public HashMap<String, Object> queryResourceByPath(String pathId,String orderName,String orderBy, String spaceType, String name)
			throws Exception {

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		
		
			List inlist = new  ArrayList();
			
			String pathTemp="";
			String  resourceTemp="";
			 //组装排序条件	
			if(!"".equals(orderName)&&orderName!=null){
				pathTemp+=",path_"+orderName;
				resourceTemp+=",resource_"+orderName;
			}
			if(!"".equals(orderBy)&&orderBy!=null){
				pathTemp+=" "+orderBy;
				resourceTemp+=" "+orderBy;
			}
		
			String pathhql = "from  TjyptResourcePath where  parent_path_id='" +pathId
					+ "' and path_is_recyclebin ='0'";
			if("9".equals(spaceType)){
				pathhql = pathhql+" and id <> '4028839458af31b40158d23e06590011' ";
			}
			pathhql = pathhql+"  order by path_important_flag desc,path_order_no desc "+pathTemp+"";
	
				List<TjyptResourcePath> infoList = tjyptResourcePathDao.createQuery(pathhql).list();
				
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
				
			
				
		String sql = "from  TjyptResourceInfo where  resource_path='" +pathId
				+ "' and resource_is_recyclebin='0' and resource_status='1' and resource_share_status is null";
				
				if(name!=null&&StringUtil.isNotEmpty(name)&&!"null".equals(name)){
					sql = sql + " and resource_name like '%"+name+"%' ";
				}
				sql = sql 	+ " order by  resource_important_flag desc,resource_order_no desc "+resourceTemp+"";

		List<TjyptResourceInfo> resourceList = tjyptResourceInfoDao.createQuery(sql).list();

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
	
		wrapper.put("queryInfo",inlist);
		wrapper.put("pathId",pathId);
		
		
		return wrapper;

	}

	public HashMap<String, Object> queryFileByPath(String pathId,String orderName,String orderBy)
			throws Exception {

		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		
		
		List inlist = new  ArrayList();
		
		String pathTemp="";
		
		 //组装排序条件	
		if(!"".equals(orderName)&&orderName!=null){
			pathTemp+=",path_"+orderName;
		
		}
		if(!"".equals(orderBy)&&orderBy!=null){
			pathTemp+=" "+orderBy;
		
		}
	
				List<TjyptResourcePath> infoList = tjyptResourcePathDao.createQuery(
						"from  TjyptResourcePath where  parent_path_id='" +pathId
								+ "' and path_is_recyclebin='0' order by path_important_flag desc,path_order_no desc "+pathTemp+"").list();
				
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
				
			
		wrapper.put("queryInfo",inlist);
		
		
		
		return wrapper;

	}
	
	public HashMap<String, Object> queryParentId(String pathId)
			throws Exception {
		
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		TjyptResourcePath path = tjyptResourcePathDao.get(pathId);
		
		wrapper.put("parentId", path.getParent_path_id());
		wrapper.put("success", "true");
		
		return wrapper;
		
		
		
		
		
	
	}

	public HashMap<String, Object> queryResourceNeedReceive(String spaceType) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		List inlist = new  ArrayList();
		
		String pathTemp="";
		String  resourceTemp="";
		String hql="from  TjyptResourcePath where  parent_path_id is null and path_is_recyclebin ='0'"
				+ "and fk_resource_space_id=? order by path_last_update_date desc nulls last";
			List<TjyptResourcePath> infoList = new ArrayList<TjyptResourcePath>();
			 if(spaceType.equals("1")){
				 String hql1="from  TjyptResourceSpace where space_owner = ? ";
				 System.out.println("------------------------"+hql1);
				List<TjyptResourceSpace> sList = tjyptResourceSpaceDao.createQuery(hql1,user.getId()).list();
				if(sList.size()>0&&sList.get(0)!=null){
					TjyptResourceSpace space = sList.get(0);
					infoList = tjyptResourcePathDao.createQuery(hql,space.getId()).list();
				}
				 
			 }else if(spaceType.equals("9")){
				 infoList = tjyptResourcePathDao.createQuery(hql,"100000").list();
			 }else if(spaceType.equals("7")){
				 infoList = tjyptResourcePathDao.createQuery(hql,TS_Util.getCurOrg(user).getId()).list();
			 }else if(spaceType.equals("5")){
				 
			 }
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
			
		
			
		
			 List<TjyptResourceInfo> resourceList = new ArrayList<TjyptResourceInfo>();
		 if(spaceType.equals("1")){
			 String hql1="from  TjyptResourceInfo where  resource_path is null "+
	     				" and resource_is_recyclebin='0' and resource_status='1'"
	    				+ " and resource_owner_id=? and resource_share_status='0' order by "+
	    				" resource_last_update_date desc nulls last";
			 resourceList = tjyptResourceInfoDao.createQuery(hql1,user.getId()).list();
		 }else if(spaceType.equals("9")){
			 String hql1=" from  TjyptResourceInfo where  (resource_path is null or resource_path='100000') "+
	     				" and resource_is_recyclebin='0' and resource_status='1'"
	    				+ " and resource_owner_id=? and resource_share_status='0' order by "+
	    				" resource_last_update_date desc nulls last";
			 resourceList = tjyptResourceInfoDao.createQuery(hql1,"100000").list();
		 }else if(spaceType.equals("7")){
			 String hql1=" from  TjyptResourceInfo where  (resource_path is null or resource_path='"+TS_Util.getCurOrg(user).getId()+"') "+
	     				" and resource_is_recyclebin='0' and resource_status='1'"
	    				+ " and resource_owner_id=? and resource_share_status='0' order by "+
	    				" resource_last_update_date desc nulls last";
			 resourceList = tjyptResourceInfoDao.createQuery(hql1,TS_Util.getCurOrg(user).getId()).list();
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
	
		wrapper.put("queryInfo",inlist);
		
		
		return wrapper;
	}
	
	public HashMap<String, Object> queryResourceNeedReceiveNew(String spaceType) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		List inlist = new  ArrayList();
		
		String pathTemp="";
		String  resourceTemp="";
		String hql="from  TjyptResourcePath where  parent_path_id is null and path_is_recyclebin ='0'"
				+ "and fk_resource_space_id=? order by path_last_update_date desc nulls last";
			List<TjyptResourcePath> infoList = new ArrayList<TjyptResourcePath>();
			 if(spaceType.equals("1")){
				 String hql1="from  TjyptResourceSpace where space_owner = ? ";
				 System.out.println("------------------------"+hql1);
				List<TjyptResourceSpace> sList = tjyptResourceSpaceDao.createQuery(hql1,user.getId()).list();
				if(sList.size()>0&&sList.get(0)!=null){
					TjyptResourceSpace space = sList.get(0);
					infoList = tjyptResourcePathDao.createQuery(hql,space.getId()).list();
				}
				 
			 }else if(spaceType.equals("9")){
				 infoList = tjyptResourcePathDao.createQuery(hql,"100000").list();
			 }else if(spaceType.equals("7")){
				 infoList = tjyptResourcePathDao.createQuery(hql,TS_Util.getCurOrg(user).getId()).list();
			 }else if(spaceType.equals("5")){
				 
			 }
			if(infoList.size()>0){
				wrapper.put("size",infoList.size());
				
				
				return wrapper;
				/*for(TjyptResourcePath path:infoList ){
					QueryResourceInfo info  = new QueryResourceInfo();
					info.setId(path.getId());
					info.setInfoIsHidden(path.getPath_is_hidden());
					info.setInfoName(path.getPath_name());
					info.setInfoType("1");//1是文件夹2是资源
					info.setInfoRemark(path.getPath_remark());
					info.setLast_update_date(path.getPath_last_update_date());
					info.setLevel(path.getPath_important_flag());
					inlist.add(info);
				}*/
			}
			
		
			
		
			 List<TjyptResourceInfo> resourceList = new ArrayList<TjyptResourceInfo>();
			 Date currentTime = new Date();
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  String dateString = formatter.format(currentTime);
		 if(spaceType.equals("1")){
			 String hql1="from  TjyptResourceInfo where  resource_path is null "+
	     				" and resource_is_recyclebin='0' and resource_status='1'"
	    				+ " and resource_owner_id=? and resource_share_status='0'"+
	     				"and ((to_date( to_char(sysdate, 'yyyy-mm-dd hh24-mi-ss'), 'yyyy-mm-dd hh24-mi-ss') - to_date( to_char(resource_last_update_date, 'yyyy-mm-dd hh24-mi-ss'), 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 *60)<60"
	    				+ " order by "+
	    				" resource_important_flag desc,resource_order_no desc "+resourceTemp+"";
			 resourceList = tjyptResourceInfoDao.createQuery(hql1,user.getId()).list();
		 }else if(spaceType.equals("9")){
			 String hql1="from  TjyptResourceInfo where  (resource_path is null or resource_path='100000') "+
	     				" and resource_is_recyclebin='0' and resource_status='1'"
	     				+ " and resource_owner_id=? and resource_share_status='0'"+
	     				"and ((to_date( to_char(sysdate, 'yyyy-mm-dd hh24-mi-ss'), 'yyyy-mm-dd hh24-mi-ss') - to_date( to_char(resource_last_update_date, 'yyyy-mm-dd hh24-mi-ss'), 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 *60)<60"
	    				+ " order by "+
	    				" resource_important_flag desc,resource_order_no desc "+resourceTemp+"";
			 resourceList = tjyptResourceInfoDao.createQuery(hql1,"100000").list();
		 }else if(spaceType.equals("7")){
			 String hql1="from  TjyptResourceInfo where  (resource_path is null or resource_path='"+TS_Util.getCurOrg(user).getId()+"') "+
	     				" and resource_is_recyclebin='0' and resource_status='1'"
	     				+ " and resource_owner_id=? and resource_share_status='0'"+
	     				"and ((to_date( to_char(sysdate, 'yyyy-mm-dd hh24-mi-ss'), 'yyyy-mm-dd hh24-mi-ss') - to_date( to_char(resource_last_update_date, 'yyyy-mm-dd hh24-mi-ss'), 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 *60)<60"
	    				+ " order by "+
	    				" resource_important_flag desc,resource_order_no desc "+resourceTemp+"";
			 resourceList = tjyptResourceInfoDao.createQuery(hql1,TS_Util.getCurOrg(user).getId()).list();
		 }else if(spaceType.equals("5")){
			 
		 }
		
		if(resourceList.size()>0){
		
			/*for(TjyptResourceInfo resinfo:resourceList ){
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
				inlist.add(info);
			}*/
		}
	
		wrapper.put("size",resourceList.size());
		
		
		return wrapper;
	}
	
	public void delResourceRecycle(String delId,String type) throws Exception {
		//批量
		if(delId.indexOf(",")!=-1){
			
			String id[] =delId.split(",");
			String temp[]=type.split(",");
			
			for(int i=0;i<id.length;i++){
				
				if(temp[i].equals("1")){//文件夹
					 TjyptResourcePath resourcePath = tjyptResourcePathDao.get(id[i]);//获取是否为文件对象
					 //递归算法
					deleteRecyclePath(resourcePath.getId());
					tjyptResourcePathDao.createSQLQuery("update  tjypt_resource_path  set path_is_recyclebin='2' where id =?",id[i] ).executeUpdate();
					
				}else{//资源
					
					tjyptResourceInfoDao.createSQLQuery("update  tjypt_resource_info  set resource_is_recyclebin='2' where id =?", id[i]).executeUpdate();
				}
				
				
			}
			
		}else{
			
			if(type.equals("1")){//文件夹
				 TjyptResourcePath resourcePath = tjyptResourcePathDao.get(delId);//获取是否为文件对象
				 //递归算法
				deleteRecyclePath(resourcePath.getId());
				tjyptResourcePathDao.createSQLQuery("update  tjypt_resource_path  set path_is_recyclebin='2' where id =?",delId ).executeUpdate();
				
			}else{//资源
				
				tjyptResourceInfoDao.createSQLQuery("update  tjypt_resource_info  set resource_is_recyclebin='2' where id =?", delId).executeUpdate();
			}
			
		}
		
		
		
	}
	
	public void backResourceRecycle(String delId,String type, HttpServletRequest request) throws Exception {
		//批量
		if(delId.indexOf(",")!=-1){
			
			String id[] =delId.split(",");
			String temp[]=type.split(",");
			
			for(int i=0;i<id.length;i++){
				
				if(temp[i].equals("1")){//文件夹
					TjyptResourcePath  resourceInfo = tjyptResourcePathDao.get(id[i]);	//获取资源对象	
					backPath(resourceInfo.getId(),request);
					tjyptResourcePathDao.createSQLQuery("update  tjypt_resource_path  set path_is_recyclebin='0' where id =?",id[i] ).executeUpdate();
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					String ip = "";
					if(request!=null){
						ip = TS_Util.getIpAddress(request);
					}
					
					 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
							 id[i], "恢复文件："+resourceInfo.getPath_name(), ip,resourceInfo.getFk_resource_space_id(),"");
				}else{//资源
					String name;
					TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(id[i]);	
					
					//2017-03-07pingZhou修改
					name  = resourceInfo.getResource_name();
					int j = name.lastIndexOf(".");
					String suffix = name.substring(j,name.length());
					String name1 = name.substring(0,j);
					try {
						
						String sql = "select to_char(lo.op_time,'yyyyMMddHHmmss') time from TJYPT_RESOURCE_INFO t,tjypt_log lo "
								+ " where lo.target_id = t.id and"
								+ " lo.event like '%删除文件%'"
								+ " and t.id = ? order by lo.op_time desc";
						List<Object> list = tjyptResourceInfoDao.createSQLQuery(sql, resourceInfo.getId()).list();
						String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"back";
						if(list.size()>0){
							now = list.get(0).toString()+"back";
						}
						
						name = name1+"-"+now+suffix;
						//name = tjyptResourceInfoService.checkInfoName(resourceInfo.getResource_name(),0,resourceInfo.getResource_path());
					} catch (Exception e) {
						name = name1+"-back"+suffix;
						e.printStackTrace();
					}
					tjyptResourceInfoDao.createSQLQuery("update  tjypt_resource_info  set resource_name='"+name+"', resource_is_recyclebin='0' where id =?", id[i]).executeUpdate();
					CurrentSessionUser user = SecurityUtil.getSecurityUser();
					String ip = "";
					if(request!=null){
						ip = TS_Util.getIpAddress(request);
					}
					 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
							 id[i], "恢复文件："+resourceInfo.getResource_name(), ip,"","");
				}
				
				
			}
			
		}else{
			
			if(type.equals("1")){//文件夹
				TjyptResourcePath  resourceInfo = tjyptResourcePathDao.get(delId);	//获取资源对象	
				backPath(resourceInfo.getId(),request);
				tjyptResourcePathDao.createSQLQuery("update  tjypt_resource_path  set path_is_recyclebin='0' where id =?",delId ).executeUpdate();
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				 tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
						 delId, "恢复文件："+resourceInfo.getPath_name(), ip,resourceInfo.getFk_resource_space_id(),"");
			}else{//资源
				String name;
				TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(delId);	
				
				//2017-03-07pingZhou修改
				name  = resourceInfo.getResource_name();
				int j = name.lastIndexOf(".");
				String suffix = name.substring(j,name.length());
				String name1 = name.substring(0,j);
				try {
					
					String sql = "select to_char(lo.op_time,'yyyyMMddHHmmss') time from TJYPT_RESOURCE_INFO t,tjypt_log lo "
							+ " where lo.target_id = t.id and"
							+ " lo.event like '%删除文件%'"
							+ " and t.id = ? order by lo.op_time desc";
					List<Object> list = tjyptResourceInfoDao.createSQLQuery(sql, resourceInfo.getId()).list();
					String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"back";
					if(list.size()>0){
						now = list.get(0).toString()+"back";
					}
					
					name = name1+"-"+now+suffix;
					//name = tjyptResourceInfoService.checkInfoName(resourceInfo.getResource_name(),0,resourceInfo.getResource_path());
				} catch (Exception e) {
					name = name1+"-back"+suffix;
					e.printStackTrace();
				}
				tjyptResourceInfoDao.createSQLQuery("update  tjypt_resource_info  set resource_name='"+name+"', resource_is_recyclebin='0' where id =?", delId).executeUpdate();
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				tjyptLogService.saveLog(type, user.getId(), user.getName(), new Date(),
						 delId, "恢复文件："+resourceInfo.getResource_name(), ip,"","");
			}
			
		}
		
		
		
	}
	/**
	 * 删除文件夹时循环处理
	 * @param parentPathId
	 * @param request 
	 * @throws Exception
	 */
	public void deletePath(String parentPathId, HttpServletRequest request) throws Exception{
		String hql="from TjyptResourcePath where parent_path_id=? and path_is_recyclebin = '0'";//获取该文件下存在的文件
		List<TjyptResourcePath> listResourcePath= tjyptResourcePathDao.createQuery(hql, parentPathId).list();
		if(listResourcePath.size()>0){
			for (int i = 0; i < listResourcePath.size(); i++) {
				TjyptResourcePath path = listResourcePath.get(i);
				path.setPath_is_recyclebin("3");
				tjyptResourcePathDao.save(path);
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				 tjyptLogService.saveLog("1", user.getId(), user.getName(), new Date(),
						 path.getId(), "删除文件夹："+path.getPath_name(), ip,path.getFk_resource_space_id(),"");
				deletePath(path.getId(),request);
			}
			
		}
		String hql2="from TjyptResourceInfo where resource_path=? and resource_is_recyclebin='0'";//获取该文件下存在的资源
		List<TjyptResourceInfo> listResourceInfo= tjyptResourceInfoDao.createQuery(hql2, parentPathId).list();
		if(listResourceInfo.size()>0){
			for (int j = 0; j < listResourceInfo.size(); j++) {
				TjyptResourceInfo info = listResourceInfo.get(j);
				info.setResource_is_recyclebin("3");
				tjyptResourceInfoDao.save(info);
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				 tjyptLogService.saveLog("2", user.getId(), user.getName(), new Date(),
						 info.getId(), "删除文件："+info.getResource_name(), ip,"","");
			}
			
		}
	}
	
	/**
	 * 恢复文件夹时循环处理
	 * @param parentPathId
	 * @param request 
	 * @throws Exception
	 */
	public void backPath(String parentPathId, HttpServletRequest request) throws Exception{
		String hql="from TjyptResourcePath where parent_path_id=? and path_is_recyclebin = '3'";//获取该文件下存在的文件
		List<TjyptResourcePath> listResourcePath= tjyptResourcePathDao.createQuery(hql, parentPathId).list();
		if(listResourcePath.size()>0){
			for (int i = 0; i < listResourcePath.size(); i++) {
				TjyptResourcePath path = listResourcePath.get(i);
				path.setPath_is_recyclebin("0");
				tjyptResourcePathDao.save(path);
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				 tjyptLogService.saveLog("1", user.getId(), user.getName(), new Date(),
						 path.getId(), "恢复文件夹："+path.getPath_name(), ip,"","");
				 backPath(path.getId(),request);
			}
			
		}
		String hql2="from TjyptResourceInfo where resource_path=?  and resource_is_recyclebin='3'";//获取该文件下存在的资源
		List<TjyptResourceInfo> listResourceInfo= tjyptResourceInfoDao.createQuery(hql2, parentPathId).list();
		if(listResourceInfo.size()>0){
			for (int j = 0; j < listResourceInfo.size(); j++) {
				TjyptResourceInfo info = listResourceInfo.get(j);
				info.setResource_is_recyclebin("0");
				tjyptResourceInfoDao.save(info);
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				String ip = "";
				if(request!=null){
					ip = TS_Util.getIpAddress(request);
				}
				 tjyptLogService.saveLog("2", user.getId(), user.getName(), new Date(),
						 info.getId(), "恢复文件："+info.getResource_name(), ip,"","");
			}
			
		}
	}
	
	/**
	 * 删除回收站文件夹时循环处理
	 * @param parentPathId
	 * @param request 
	 * @throws Exception
	 */
	public void deleteRecyclePath(String parentPathId) throws Exception{
		String hql="from TjyptResourcePath where parent_path_id=? and path_is_recyclebin = '3'";//获取该文件下存在的文件
		List<TjyptResourcePath> listResourcePath= tjyptResourcePathDao.createQuery(hql, parentPathId).list();
		if(listResourcePath.size()>0){
			for (int i = 0; i < listResourcePath.size(); i++) {
				TjyptResourcePath path = listResourcePath.get(i);
				path.setPath_is_recyclebin("2");
				tjyptResourcePathDao.save(path);
				 deleteRecyclePath(path.getId());
			}
			
		}
		String hql2="from TjyptResourceInfo where resource_path=? and resource_is_recyclebin='3'";//获取该文件下存在的资源
		List<TjyptResourceInfo> listResourceInfo= tjyptResourceInfoDao.createQuery(hql2, parentPathId).list();
		if(listResourceInfo.size()>0){
			for (int j = 0; j < listResourceInfo.size(); j++) {
				TjyptResourceInfo info = listResourceInfo.get(j);
				info.setResource_is_recyclebin("2");
				tjyptResourceInfoDao.save(info);
			}
			
		}
	}
	
	/**
	 * 删除待接收文件夹时循环处理
	 * @param parentPathId
	 * @param request 
	 * @throws Exception
	 */
	public void deleteReceivePath(String parentPathId) throws Exception{
		String hql="from TjyptResourcePath where parent_path_id=? ";//获取该文件下存在的文件
		List<TjyptResourcePath> listResourcePath= tjyptResourcePathDao.createQuery(hql, parentPathId).list();
		if(listResourcePath.size()>0){
			for (int i = 0; i < listResourcePath.size(); i++) {
				TjyptResourcePath path = listResourcePath.get(i);
				String id = path.getId();
				String hql1 =" delete from TjyptResourcePath where id = ?";//获取该文件下存在的文件
				tjyptResourcePathDao.createQuery(hql1, id).executeUpdate();
				 deleteRecyclePath(path.getId());
				 
			}
			
		}
		String hql2="from TjyptResourceInfo where resource_path=? and resource_share_status= '0'";//获取该文件下存在的资源
		List<TjyptResourceInfo> listResourceInfo= tjyptResourceInfoDao.createQuery(hql2, parentPathId).list();
		if(listResourceInfo.size()>0){
			for (int j = 0; j < listResourceInfo.size(); j++) {
				TjyptResourceInfo info = listResourceInfo.get(j);
				String id = info.getId();
				String hql3="delete from TjyptResourceInfo where id = ?";//获取该文件下存在的资源
				tjyptResourceInfoDao.createQuery(hql3, id).executeUpdate();
			}
			
		}
	}
	
	/**
	 * 拒绝接收待接收文件 ZHOU
	 * @param delId
	 * @param type
	 * @param request
	 * @throws Exception
	 */
	public void refuseShare(String delId,String type, HttpServletRequest request) throws Exception {
		//批量
		if(delId.indexOf(",")!=-1){
			
			String id[] =delId.split(",");
			String temp[]=type.split(",");
			
			for(int i=0;i<id.length;i++){
				
				if(temp[i].equals("1")){//文件夹
					TjyptResourcePath  resourceInfo = tjyptResourcePathDao.get(id[i]);	//获取资源对象	
					if(resourceInfo!=null){
						deleteReceivePath(resourceInfo.getId());
					}
					tjyptResourcePathDao.createSQLQuery("delete from  tjypt_resource_path  where id =?",id[i] ).executeUpdate();
				}else{//资源
					
					tjyptResourceInfoDao.createSQLQuery("delete from tjypt_resource_info  where id =?", id[i]).executeUpdate();
				}
				
				
			}
			
		}else{
			
			if(type.equals("1")){//文件夹
				TjyptResourcePath  resourceInfo = tjyptResourcePathDao.get(delId);	//获取资源对象	
				if(resourceInfo!=null){
					deleteReceivePath(resourceInfo.getId());
				}
				tjyptResourcePathDao.createSQLQuery("delete from tjypt_resource_path  where id =?",delId ).executeUpdate();
			}else{//资源
				tjyptResourceInfoDao.createSQLQuery("delete from tjypt_resource_info where id =?", delId).executeUpdate();
			}
			
		}
		
		
		
	}
	
	/**
	 * 接收文件夹时循环处理
	 * @param parentPathId
	 * @param spacId 
	 * @param request 
	 * @throws Exception
	 */
	public void ReceivePath(String parentPathId, String spacId) throws Exception{
		String hql="from TjyptResourcePath where parent_path_id=? ";//获取该文件下存在的文件
		List<TjyptResourcePath> listResourcePath= tjyptResourcePathDao.createQuery(hql, parentPathId).list();
		if(listResourcePath.size()>0){
			for (int i = 0; i < listResourcePath.size(); i++) {
				TjyptResourcePath path = listResourcePath.get(i);
				path.setFk_resource_space_id(spacId);
				tjyptResourcePathDao.save(path);
				ReceivePath(path.getId(),spacId);
				 
			}
			
		}
		String hql2="from TjyptResourceInfo where resource_path=? and resource_share_status= '0'";//获取该文件下存在的资源
		List<TjyptResourceInfo> listResourceInfo= tjyptResourceInfoDao.createQuery(hql2, parentPathId).list();
		if(listResourceInfo.size()>0){
			for (int j = 0; j < listResourceInfo.size(); j++) {
				TjyptResourceInfo info = listResourceInfo.get(j);
				info.setResource_share_status(null);
				tjyptResourceInfoDao.save(info);
			}
		}
	}

	public HashMap<String, Object> queryDownResourceByPath(String pathId, String orderName, String orderBy,
			String spaceType, String name,String orgF) {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			
			List inlist = new  ArrayList();
			
			String pathTemp="";
			String  resourceTemp="";
			 //组装排序条件	
			if(!"".equals(orderName)&&orderName!=null){
				pathTemp+=",path_"+orderName;
				resourceTemp+=",resource_"+orderName;
			}
			if(!"".equals(orderBy)&&orderBy!=null){
				pathTemp+=" "+orderBy;
				resourceTemp+=" "+orderBy;
					}
			String sql = "from  TjyptResourceInfo where  resource_path='" +pathId
					+ "' and resource_is_recyclebin='0' and resource_status='1' and resource_share_status is null";
					
					if(name!=null&&StringUtil.isNotEmpty(name)&&!"null".equals(name)){
						sql = sql + " and resource_name like '%"+name+"%' ";
					}
					/*sql = sql 	+ " order by  resource_important_flag desc,resource_order_no desc "+resourceTemp+"";*/
					sql = sql 	+ " order by resource_last_update_date desc,resource_important_flag desc,resource_order_no desc "+resourceTemp+"";
		
			List<TjyptResourceInfo> resourceList = tjyptResourceInfoDao.createQuery(sql).list();
		
			if(resourceList.size()>0){
			
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				
				String oid = TS_Util.getCurOrg(user).getId();
				
				for(TjyptResourceInfo resinfo:resourceList ){
					QueryResourceInfo info  = new QueryResourceInfo();
					
					Org org = userDao.get(resinfo.getResource_share_user_id()).getOrg();
					//Org org = TS_Util.getCurOrg((CurrentSessionUser) userDao.get(resinfo.getResource_share_user_id()));
					String org_id = org.getId();
					
					boolean flag = false;
					if(orgF==null){
						flag = true;
					}
					if(orgF!=null&&"1".equals(orgF)&&oid.equals(org_id)){
						flag = true;
					}
					if(orgF!=null&&!"1".equals(orgF)&&orgF.equals(org_id)){
						flag = true;
					}
					//按照部门查询则查询当前部门的，不按照部门查询则查询全部
					if(flag){
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
			wrapper.put("pathId",pathId);
			
			
			return wrapper;
	}
	
}
