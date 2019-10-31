package com.scts.cspace.resource.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.dao.OrgDao;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.scts.cspace.file.bean.FileCache;
import com.scts.cspace.file.bean.TjyptFileInfo;
import com.scts.cspace.file.dao.FileCacheDao;
import com.scts.cspace.file.dao.TjyptFileInfoDao;
import com.scts.cspace.fileupload.service.CloudAttachmentManager;
import com.scts.cspace.log.service.TjyptLogService;
import com.scts.cspace.path.bean.TjyptResourcePath;
import com.scts.cspace.path.dao.TjyptResourcePathDao;
import com.scts.cspace.path.service.TjyptResourcePathService;
import com.scts.cspace.resource.bean.QueryResourceInfo;
import com.scts.cspace.resource.bean.TjyptResourceInfo;
import com.scts.cspace.resource.dao.TjyptResourceInfoDao;
import com.scts.cspace.space.bean.TjyptResourceSpace;
import com.scts.cspace.space.dao.TjyptResourceSpaceDao;
import com.scts.cspace.space.service.TjyptResourceSpaceService;

import util.FileUtil;
import util.FileUtil.CompratorByLastModified;
import util.TS_Util;

/**
 * 资源属性业务逻辑对象
 * @ClassName TjyptResourceInfoService
 * @JDK 1.7
 * @author xcb
 * @date 2016-10-24 下午04:40:00
 */
@Service("tjyptResourceInfoService")
public class TjyptResourceInfoService extends
		EntityManageImpl<TjyptResourceInfo, TjyptResourceInfoDao> {
	
	@Autowired
	private TjyptResourceInfoDao tjyptResourceInfoDao;
	@Autowired
	private TjyptResourcePathDao tjyptResourcePathDao;
	@Autowired
	private TjyptResourceSpaceDao tjyptResourceSpaceDao ;
	@Autowired
	private TjyptResourceSpaceService tjyptResourceSpaceService;
	@Autowired
	private TjyptLogService tjyptLogService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TjyptResourcePathService tjyptResourcePathService;
	@Autowired
	private FileCacheDao fileCacheDao; 
	@Autowired
	private OrgDao orgDao;
	@Autowired 
	private AttachmentDao attachmentDao;
	@Autowired
	private TjyptFileInfoDao fileInfoDao;
	@Autowired
	private CloudAttachmentManager cloudAttachmentManager;
	
	/**
	 * 云盘文件存储位置：相对路径
	 */
	public static final String CLOUD_SAVE_PATH_R = "cloud_relative";
	/**
	 * 云盘文件存储位置：绝对路径
	 */
	public static final String CLOUD_SAVE_PATH_A = "cloud_absolute";
	
	
	public static final String CLOUD_IP = Factory.getSysPara().getProperty("cloud_ip");
	public static final String CLOUD_FOLDER = "四川特检云";
	
	
	/**
	 * 
	 * @param file_type  是否为文件夹
	 * @param resourceId 文件夹(资源)id
	 * @param resourceName 新的名字
	 * @throws Exception
	 */
	public void updateResourceName(String file_type,String resourceId,String resourceName) throws Exception {
		if(file_type.equals("1")){
			TjyptResourcePath  resourcePath = tjyptResourcePathDao.get(resourceId);//获取文件对象
			resourcePath.setPath_name(resourceName);//修改文件名称
			tjyptResourcePathDao.save(resourcePath);//保存修改后对象
		}else if(file_type.equals("2")){
			TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(resourceId);	//获取资源对象
			resourceInfo.setResource_name(resourceName);//修改资源名称
			tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		}
	}
	/**
	 * 
	 * @param file_type 是否为文件夹
	 * @param resourceId 文件夹(资源)id 多选
	 * @param resourcePath 新的路径
	 * @throws Exception
	 */
	public void updateResourcePath(String file_type,String resourceId,String resourcePath) throws Exception {
		if(file_type.equals("1")){
			String  resourceIds[]=resourceId.split(",");
			for (int i = 0; i < resourceIds.length; i++) {
				TjyptResourcePath  resourcePaths = tjyptResourcePathDao.get(resourceIds[i]);//获取文件对象
				resourcePaths.setParent_path_id(resourcePath);//修改上级文件夹
				tjyptResourcePathDao.save(resourcePaths);//保存修改后对象
			}
			
		}else if(file_type.equals("2")){
			String  resourceIds[]=resourceId.split(",");
			for (int i = 0; i < resourceIds.length; i++) {
			TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(resourceIds[i]);	//获取资源对象
			resourceInfo.setResource_path(resourcePath);//修改资源自定义分类（路径）
			tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
			}
		}
		
		
		
		
	}
	public void updateResourceOwnerId(String resourceId,String resourceOwnerId) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_owner_id(resourceOwnerId);//修改资源拥有者
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceShareUserId(String resourceId,String resourceShareUserId) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_share_user_id(resourceShareUserId);//修改资源分享人
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceShareStatus(String resourceId,String resourceShareStatus) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_share_status(resourceShareStatus);//修改分享状态
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	/**
	 * 
	 * @param resourceId 分享后文件或资源id (多选)
	 * @param parentPathId 接受路径
	 * @throws Exception 
	 */
	public void resourceShareAccept(String resourceId,String parentPathId) throws Exception{
		String resourceIds[]=resourceId.split(",");
		CurrentSessionUser user = SecurityUtil.getSecurityUser();// 获取登录用户资源空间id
		String hql="from TjyptResourceSpace where space_owner=?";
		TjyptResourcePath path = tjyptResourcePathDao.get(parentPathId);
		TjyptResourceSpace space = tjyptResourceSpaceDao.get(path.getFk_resource_space_id());
		//List<TjyptResourceSpace> tjyptResourceSpace= tjyptResourceSpaceDao.createQuery(hql, user.getId()).list();
		//if(tjyptResourceSpace.size()>0){
			//修改文件夹表分享文件的上级文件夹和所属资源空间
			for (int i = 0; i < resourceIds.length; i++) {
				String sql1="update  TJYPT_RESOURCE_PATH set parent_path_id='"+parentPathId+"' , fk_resource_space_id='"+space.getId()+"' where id=? ";
				tjyptResourcePathDao.createSQLQuery(sql1, resourceIds[i]).executeUpdate();
				tjyptResourcePathService.ReceivePath(resourceIds[i],space.getId());
			}
			
		//}
		//修改资源表分享资源的路径和接受状态
		for (int i = 0; i < resourceIds.length; i++) {
			String sql2="update  TJYPT_RESOURCE_INFO set resource_path='"+parentPathId+"' , resource_share_status=null where id=? ";

			tjyptResourceInfoDao.createSQLQuery(sql2, resourceIds[i]).executeUpdate();
			//TjyptResourceInfo info = tjyptResourceInfoDao.get(resourceIds[i]);
			
		}
		
		
	}
	/**
	 * 
	 * @param file_type 是否为文件夹
	 * @param resourceId 选择文件id(多选)
	 * @param userId 分享对象id(多选)
	 * @param request 
	 * @param spaceId 
	 * @throws Exception
	 */
	public void resourceShare(String resourceId,String userId, HttpServletRequest request, String spaceId) throws Exception {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();// 获取登录用户
		String resourceIds[]=resourceId.split(",");
		 String userIds[]=userId.split(",");
		for (int i = 0; i < resourceIds.length; i++) {
			 TjyptResourcePath resourcePath = tjyptResourcePathDao.get(resourceIds[i]);//获取是否为文件对象
			 if(resourcePath!=null){
					for (int j = 0; j < userIds.length; j++) {
						
						//User user1 = userDao.get(userIds[j]);
						String ip = "";
						if(request!=null){
							ip = TS_Util.getIpAddress(request);
						}
						 tjyptLogService.saveLog("1", user.getId(), user.getName(), new Date(),
								 resourcePath.getId(), "分享文件夹给"+userId+",文件夹名："+resourcePath.getPath_name(), ip,spaceId,userId);
						recursion(resourcePath,userIds[j],"",request);//递归算法
					}
				}else if(resourcePath==null){
					
					for (int j = 0; j < userIds.length; j++) {
						
						TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(resourceIds[i]);	//获取资源对象
						addResource(resourceInfo,userIds[j],"",request);//复制资源bean

					}
		}
		
		
		}
		
		
		
	}
	public void recursion(TjyptResourcePath resourcePath,String userId,String parentPathId, HttpServletRequest request) throws Exception{
		String newPathId=addResourcePath(resourcePath,parentPathId,userId);//复制文件bean,返回id
		String hql="from TjyptResourcePath where parent_path_id=?";//获取该文件下存在的文件
		List<TjyptResourcePath> listResourcePath= tjyptResourcePathDao.createQuery(hql, resourcePath.getId()).list();
		if(listResourcePath.size()>0){
			for (int i = 0; i < listResourcePath.size(); i++) {
				recursion(listResourcePath.get(i),userId,newPathId,request);
			}
			
		}
		String hql2="from TjyptResourceInfo where resource_path=?";//获取该文件下存在的资源
		List<TjyptResourceInfo> listResourceInfo= tjyptResourceInfoDao.createQuery(hql2, resourcePath.getId()).list();
		if(listResourceInfo.size()>0){
			for (int j = 0; j < listResourceInfo.size(); j++) {
					addResource(listResourceInfo.get(j),userId,newPathId,request);//复制资源bean,赋值上级文件id
			}
			
		}
	}
	//复制文件
	public  String addResourcePath(TjyptResourcePath resourcePath,String parentPathId, String userId) throws Exception{
		TjyptResourcePath TjyptResourcePathCopy=new TjyptResourcePath();
		CurrentSessionUser user  = SecurityUtil.getSecurityUser();
		String hql="from TjyptResourcePath where path_name='"+resourcePath.getPath_name()+"' and fk_resource_space_id='"+userId+"'";
		List<TjyptResourcePath> list= tjyptResourceInfoDao.createQuery(hql).list();
		if(list.size()>0){
			TjyptResourcePathCopy=list.get(0);
		}else{
		TjyptResourcePathCopy.setFk_resource_space_id(userId);//清空所属资源空间
		TjyptResourcePathCopy.setParent_path_id(parentPathId);//初始化上级文件
		TjyptResourcePathCopy.setPath_name(resourcePath.getPath_name());
		TjyptResourcePathCopy.setPath_accsess_password("");//初始化访问密码
		TjyptResourcePathCopy.setPath_order_no(resourcePath.getPath_order_no());
		TjyptResourcePathCopy.setPath_important_flag(resourcePath.getPath_important_flag());
		TjyptResourcePathCopy.setPath_is_recyclebin(resourcePath.getPath_is_recyclebin());
		TjyptResourcePathCopy.setPath_remark(resourcePath.getPath_remark());
		TjyptResourcePathCopy.setPath_is_hidden(resourcePath.getPath_is_hidden());
		TjyptResourcePathCopy.setPath_share_user(user.getName());
		TjyptResourcePathCopy.setPath_share_user_id(user.getId());
		}
		TjyptResourcePathCopy.setPath_last_update_date(new Date());
		tjyptResourcePathDao.save(TjyptResourcePathCopy);
		return TjyptResourcePathCopy.getId();

	}
	//复制资源
	public void addResource(TjyptResourceInfo resourceInfo,String userId,String resourcePath, HttpServletRequest request){
		String spaceId = request.getParameter("spaceId");//获取分享对象
		CurrentSessionUser user = SecurityUtil.getSecurityUser();// 获取登录用户
		TjyptResourceInfo resourceInfoCopy = new  TjyptResourceInfo();
		String hql="from TjyptResourceInfo where resource_name='"+resourceInfo.getResource_name()+"' and resource_share_user_id='"+user.getUserId()+"' and fk_file_id='"+resourceInfo.getFk_file_id()+"' and resource_owner_id='"+userId+"'";
		List<TjyptResourceInfo> list= tjyptResourceInfoDao.createQuery(hql).list();
		if(list.size()>0){
			resourceInfoCopy=list.get(0);
		}else{
		resourceInfoCopy.setFk_file_id(resourceInfo.getFk_file_id());
		resourceInfoCopy.setResource_access_password(resourceInfo.getResource_access_password());
		resourceInfoCopy.setResource_download_date(resourceInfo.getResource_download_date());
		resourceInfoCopy.setResource_download_ip(resourceInfo.getResource_download_ip());
		resourceInfoCopy.setResource_important_flag(resourceInfo.getResource_important_flag());
		resourceInfoCopy.setResource_is_hidden(resourceInfo.getResource_is_hidden());
		resourceInfoCopy.setResource_is_recyclebin(resourceInfo.getResource_is_recyclebin());
		resourceInfoCopy.setResource_last_open_date(resourceInfo.getResource_last_open_date());
		resourceInfoCopy.setResource_last_open_ip(resourceInfo.getResource_last_open_ip());
		resourceInfoCopy.setResource_name(resourceInfo.getResource_name());
		resourceInfoCopy.setResource_order_no(resourceInfo.getResource_order_no());
		resourceInfoCopy.setResource_owner_id(userId);
		resourceInfoCopy.setResource_path(resourcePath);
		resourceInfoCopy.setResource_remark(resourceInfo.getResource_remark());
		resourceInfoCopy.setResource_share_status("0");//0未接受 1 已接受
		resourceInfoCopy.setResource_share_user_id(user.getUserId());//资源分享人
		resourceInfoCopy.setResource_share_user(user.getName());//资源分享人
		resourceInfoCopy.setResource_size(resourceInfo.getResource_size());
		resourceInfoCopy.setResource_status(resourceInfo.getResource_status());
		resourceInfoCopy.setResource_type(resourceInfo.getResource_type());
		}
		resourceInfoCopy.setResource_last_update_date(new Date());
		//User user1 =  userDao.get(userId);
		String ip = "";
		if(request!=null){
			ip = TS_Util.getIpAddress(request);
		}
		 tjyptLogService.saveLog("2", user.getId(), user.getName(), new Date(),
				 resourceInfo.getId(), "分享文件给"+userId+",文件名："+resourceInfo.getResource_name(), ip,spaceId,userId);
		tjyptResourceInfoDao.save(resourceInfoCopy);//保存复制对象
		
	}
	public void updateResourceSize(String resourceId,String resourceSize) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_size(resourceSize);//修改资源大小
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceType(String resourceId,String resourceType) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_type(resourceType);//修改资源类型
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceRemark(String resourceId,String resourceRemark) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_remark(resourceRemark);//修改资源备注
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void hiddenResource(String resourceId) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_is_hidden("1");//隐藏资源,"1"表示资源隐藏
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void showResource(String resourceId) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_is_hidden("0");//显示资源,"0"表示显示资源
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceImportantFlag(String resourceId,String resourceImportantFlag) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_important_flag(resourceImportantFlag);//修改资源重要等级
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceOrderNo(String resourceId,String resourceOrderNo) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_order_no(resourceOrderNo);//修改资源自定义排序
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceAccessPassword(String resourceId,String resourceAccessPassword) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_access_password(resourceAccessPassword);//修改资源访问验证密码
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void resourceInRecyclebin(String resourceId) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_is_recyclebin("1");//资源在回收站,"1"表示资源在回收站
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void resourceOutRecyclebin(String resourceId) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_is_recyclebin("0");//资源不在回收站,"0"表示资源不在回收站
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}
	public void updateResourceStatus(String resourceId,String resourceStatus) throws Exception {
		
		TjyptResourceInfo  resourceInfo = this.get(resourceId);	//获取资源对象
		
		resourceInfo.setResource_status(resourceStatus);//修改资源状态
		
		tjyptResourceInfoDao.save(resourceInfo);//保存修改后对象
		
		
	}

    public HashMap<String, Object>  queryResource(String name,String spaceType) throws KhntException{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	CurrentSessionUser user  = SecurityUtil.getSecurityUser();
    	List<TjyptResourceSpace> list = new ArrayList<TjyptResourceSpace>();
		List inlist = new  ArrayList();
		List<TjyptResourceInfo> resourceList = null;
		if("1".equals(spaceType)){
			//个人
			 list = tjyptResourceSpaceDao.createQuery(
					"from " + "TjyptResourceSpace where space_owner='" + user.getId()
							+ "' and space_type='" + spaceType + "'").list();
			 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='" +user.getId()
						+ "' and resource_is_recyclebin='0'  and"+
    			"  resource_name like '%"+name
    			+"%' and resource_status='1' and resource_share_status is null order by  resource_important_flag desc,resource_order_no desc").list();

		}else if("7".equals(spaceType)){
			//部门
			 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='" +TS_Util.getCurOrg(user).getId()
						+ "' and resource_is_recyclebin='0'  and"+
    			" resource_name like '%"+name
    			+"%' and resource_status='1' and resource_share_status is null order by  resource_important_flag desc,resource_order_no desc ").list();

		}else if("9".equals(spaceType)){
			//院
			 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='100000'" 
						+ " and resource_is_recyclebin='0'  and"+
    			"  resource_name like '%"+name
    			+"%' and resource_status='1' and resource_share_status is null order by  resource_important_flag desc,resource_order_no desc ").list();
			 try {
				 //检索图片库//D:\\文件\\图片
				// List<QueryResourceInfo> dbplist =  searchResouceDb(name,"D:\\文件\\图片","2");
				 //List<QueryResourceInfo> dbplist =  searchResouceDb(name,"\\\\192.168.3.188"+File.separator +"pv"+File.separator+"photo1","2");
				 //System.out.println("\\\\192.168.3.188"+File.separator +"pv"+File.separator+"photo1"+"---检索结果数量---"+dbplist.size());
				//视频库
				
				 //List<QueryResourceInfo> dbvlist =  searchResouceDb(name,"\\\\192.168.3.188"+File.separator +"pv"+File.separator+"video1","5");
				/* for (QueryResourceInfo queryResourceInfo : dbvlist) {
					 dbplist.add(queryResourceInfo);
					 
				}*/
				/* for (FileCache queryResourceInfo : dbplist) {
					 inlist.add(queryResourceInfo);
				}*/
				 
				 List<FileCache> dbplist = fileCacheDao.getInfos(name);
				 for (int i = 0; i < dbplist.size(); i++) {
						String hql = "select count(1) from tjypt_log l where l.target_id = ? and l.op_type='2'";
						List<Object> list1  =  tjyptResourceInfoDao.createSQLQuery(hql, "\\"+dbplist.get(i).getFile_path()).list();
						dbplist.get(i).setDownTimes(list1.get(0).toString());
						
				}
				 wrapper.put("dbplist", dbplist);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			 
			 
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
				
				String hql = "select count(1) from tjypt_log l where l.target_id = ? and l.op_type='2'";
				List<Object> list1  =  tjyptResourceInfoDao.createSQLQuery(hql, resinfo.getId()).list();
				info.setDownTimes(list1.get(0).toString());
				
				inlist.add(info);
			}
		}

    	/*@SuppressWarnings("unchecked")
		List<Object> resourceList = tjyptResourceInfoDao.createSQLQuery(
				"select t.id,t.resource_is_hidden ,t.resource_name,t.resource_remark,"+
		"t.resource_last_update_date,t.resource_size,t.resource_type,t.resource_important_flag from  tjypt_resource_info t where  " +
    			" t.resource_is_recyclebin='0' and t.resource_status='1' and"+
    			"  t.resource_name like '%"+name
    			+"%' and t.resource_path in("+
    		    "select t.id from tjypt_resource_path t "+
    			" where t.fk_resource_space_id='"+list.get(0).getId()+"'"
    		     +"connect by prior t.id = t.parent_path_id "
    		  + " start with t.parent_path_id='0'"
    			+") order by "
    			+ " t.resource_important_flag desc,t.resource_order_no desc ").list();

		if(resourceList.size()>0){
		
			for (int i = 0; i < resourceList.size(); i++) {
				QueryResourceInfo info  = new QueryResourceInfo();
				Object[] obj = (Object[]) resourceList.get(i);
				info.setId((obj[0]==null)?"":obj[0].toString());
				info.setInfoIsHidden((obj[1]==null)?"":obj[1].toString());
				info.setInfoName((obj[2]==null)?"":obj[2].toString());
				info.setInfoRemark((obj[3]==null)?"":obj[3].toString());
				try {
					info.setLast_update_date((obj[4]==null)?null:
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj[4].toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					info.setLast_update_date(null);
				}
				info.setInfoSize((obj[5]==null)?"":obj[5].toString());
				info.setInfoType("2");//1是文件夹2是资源
				info.setResourceType((obj[6]==null)?"":obj[6].toString());

				info.setLevel((obj[7]==null)?"":obj[7].toString());
				inlist.add(info);
			}
		}
		*/
		wrapper.put("queryInfo",inlist);
		return wrapper;
    }
    
    public HashMap<String, Object>  queryRecycle(String spaceType) throws KhntException{
    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
    	CurrentSessionUser user  = SecurityUtil.getSecurityUser();
    	List<TjyptResourceSpace> list = new ArrayList<TjyptResourceSpace>();
		List inlist = new  ArrayList();
		List<TjyptResourceInfo> resourceList = null;
		if("1".equals(spaceType)){
			//个人
			 list = tjyptResourceSpaceDao.createQuery(
					"from " + "TjyptResourceSpace where space_owner='" + user.getId()
							+ "' and space_type='" + spaceType + "'").list();
			 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='" +user.getId()
						+ "' and resource_is_recyclebin='1' and resource_share_status is null order by  resource_important_flag desc,resource_order_no,resource_last_update_date desc").list();

		}else if("7".equals(spaceType)){
			//部门
			list = tjyptResourceSpaceDao.createQuery(
					"from " + "TjyptResourceSpace where space_owner='" +TS_Util.getCurOrg(user).getId()
							+ "' and space_type='" + spaceType + "'").list();
			 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='" +TS_Util.getCurOrg(user).getId()
						+ "' and resource_is_recyclebin='1' and resource_share_status is null  and resource_status='1' order by  resource_important_flag desc,resource_order_no,resource_last_update_date desc ").list();

		}else if("9".equals(spaceType)){
			//院
			list = tjyptResourceSpaceDao.createQuery(
					"from " + "TjyptResourceSpace where space_owner='100000'"
							+ " and space_type='" + spaceType + "'").list();
			 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='100000'" 
						+ " and resource_is_recyclebin='1' and resource_share_status is null and resource_status='1' order by  resource_important_flag desc,resource_order_no,resource_last_update_date desc ").list();

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
		/*List inlist = new  ArrayList();
		List<TjyptResourceSpace> list = tjyptResourceSpaceDao.createQuery(
				"from " + "TjyptResourceSpace where space_owner='" + user.getId()
						+ "' and space_type='" + spaceType + "'").list();
    	@SuppressWarnings("unchecked")
		List<Object> resourceList = tjyptResourceInfoDao.createSQLQuery(
				"select t.id,t.resource_is_hidden ,t.resource_name,t.resource_remark,"+
		"t.resource_last_update_date,t.resource_size,t.resource_type,t.resource_important_flag from  tjypt_resource_info t where  " +
    			" t.resource_is_recyclebin='1' and t.resource_status='1' "
    			+" and t.resource_path in("+
    		    "select t.id from tjypt_resource_path t "+
    			" where t.fk_resource_space_id='"+list.get(0).getId()+"'"
    		     +"connect by prior t.id = t.parent_path_id "
    		  + " start with t.parent_path_id='0'"
    			+") order by "
    			+ " t.resource_important_flag desc,t.resource_order_no desc ").list();

		if(resourceList.size()>0){
		
			for (int i = 0; i < resourceList.size(); i++) {
				QueryResourceInfo info  = new QueryResourceInfo();
				Object[] obj = (Object[]) resourceList.get(i);
				info.setId((obj[0]==null)?"":obj[0].toString());
				info.setInfoIsHidden((obj[1]==null)?"":obj[1].toString());
				info.setInfoName((obj[2]==null)?"":obj[2].toString());
				info.setInfoRemark((obj[3]==null)?"":obj[3].toString());
				try {
					info.setLast_update_date((obj[4]==null)?null:
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj[4].toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					info.setLast_update_date(null);
				}
				info.setInfoSize((obj[5]==null)?"":obj[5].toString());
				info.setInfoType("2");//1是文件夹2是资源
				info.setResourceType((obj[6]==null)?"":obj[6].toString());
				info.setLevel((obj[7]==null)?"":obj[7].toString());
				inlist.add(info);
			}
		}*/
		List<TjyptResourcePath> infoList = tjyptResourcePathDao.createQuery(
				"from  TjyptResourcePath where  fk_resource_space_id='" +list.get(0).getId()
						+ "' and path_is_recyclebin='1' order by path_important_flag desc,path_order_no,path_last_update_date desc ").list();
		
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
	public void openDownLog(String logType, String file_id, String file_type, HttpServletRequest request) {
		
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String ip = "";
		if(request!=null){
			ip = TS_Util.getIpAddress(request);
		}
		TjyptResourceInfo  resourceInfo = this.get(file_id);	//获取资源对象	
		
		 tjyptLogService.saveLog(file_type, user.getId(), user.getName(), new Date(),
				 file_id, logType+"文件："+resourceInfo.getResource_name(), ip,"","");
		
	}
	
	public String checkInfoName(String name,int i,String pid, String spaceType) throws Exception {
		//7 部门空间
		String newName = name;
		int j = name.lastIndexOf(".");
		String suffix = name.substring(j,name.length());
		String name1 = name.substring(0,j);
		 String hql="from TjyptResourceInfo where resource_path=? and resource_name='"+name+
				 "' and resource_is_recyclebin <> '1'";//获取该文件下存在的资源
		 
		 //pingZhou2017-03-07修改
		 if("7".equals(spaceType)){
			 //部门空间上传同名文件直接覆盖，本质上只是把原来的文件的名字加上删除日期，状态改为删除
				List<TjyptResourceInfo> listResourceInfo= tjyptResourcePathDao.createQuery(hql,pid).list();
				if(listResourceInfo.size()>0){
					TjyptResourceInfo info = listResourceInfo.get(0);
					String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					info.setResource_name(name1+"-"+now+suffix);
					info.setResource_is_recyclebin("2");
					tjyptResourceInfoDao.save(info);
				}
				//删除后再返回原来的名字
				return name;
				
		 }else{
			 if(i==0){
				 hql="from TjyptResourceInfo where resource_path=? and resource_name='"+name+
						 "' and resource_is_recyclebin <> '1'";//获取该文件下存在的资源
			}else{
				hql="from TjyptResourceInfo where resource_path=? and resource_name='"+name1+
		        		 "（"+i+"）"+suffix+"' and resource_is_recyclebin <> '1'";//获取该文件下存在的资源
			}
	 		List<TjyptResourceInfo> listResourceInfo= tjyptResourcePathDao.createQuery(hql,pid).list();
			if(listResourceInfo.size()>0){
				newName = checkInfoName(name,i+1,pid,spaceType);
			}else{
				if(i==0){
					return newName;
				}else{
					newName = name1+"（"+i+"）"+suffix;
					return newName;
				}
				
			}
		 }
		
		
		return newName;
	}
	public List<QueryResourceInfo> searchResouceDb(String name,String path,String type) {
		
		List<QueryResourceInfo> infoList = new ArrayList<QueryResourceInfo>();
		
		// 定义list，用于存储数据文件的全路径
        List<File> filelist = new ArrayList<File>();
        String dataFileTempDir = path;
        // 得到返回文件全路径的list集合
        List<File> list = FileUtil.getFiles(dataFileTempDir, filelist,name);
        
        String dataFileTempPath = null;
        for (int i = 0; i < list.size(); i++) {
            // 数据文件在临时区的路径
        	File file = list.get(i);
        	if(name==null){
        		 dataFileTempPath = file.getAbsolutePath();
        		 QueryResourceInfo info = new QueryResourceInfo();
        		 info.setId(dataFileTempPath);
        		 info.setInfoIsHidden("0");
        		 info.setInfoName(file.getName());
        		 info.setInfoSize((file.length()/1024)+"");
        		  if (file.isDirectory()) {
        			  info.setInfoType("4");//图片视频库 文件夹
                  }else{
                	  info.setInfoType("3");//图片视频库 文件
                  }
        		
        		 info.setLast_update_date(new Date(file.lastModified()));
        		 info.setResourceType(type);
        		 infoList.add(info);
                 System.out.println(i+"dataFileTempPath:"+dataFileTempPath+"---name:"+file.getName());
        	}else if((file.getName()).indexOf(name)!=-1){
        		 dataFileTempPath = file.getAbsolutePath();
        		 QueryResourceInfo info = new QueryResourceInfo();
        		 info.setId(dataFileTempPath);
        		 info.setInfoIsHidden("0");
        		 info.setInfoName(file.getName());
        		 info.setInfoSize((file.length()/1024)+"");
        		  if (file.isDirectory()) {
        			  info.setInfoType("4");//图片视频库 文件夹
                  }else{
                	  info.setInfoType("3");//图片视频库 文件
                  }
        		
        		 info.setLast_update_date(new Date(file.lastModified()));
        		 info.setResourceType(type);
        		 infoList.add(info);
                System.out.println(i+"dataFileTempPath:"+dataFileTempPath+"---name:"+file.getName());
        	}
           
        }
          
		return infoList;
	}

public List<QueryResourceInfo> searchResouceDbPath(String name,String path,String type) {
		
		List<QueryResourceInfo> infoList = new ArrayList<QueryResourceInfo>();
		
		// 定义list，用于存储数据文件的全路径
        List<File> filelist = new ArrayList<File>();
        String dataFileTempDir = path;
        // 得到返回文件全路径的list集合
        List<File> list = FileUtil.getFilesChild(dataFileTempDir, filelist);
        
        String dataFileTempPath = null;
        for (int i = 0; i < list.size(); i++) {
            // 数据文件在临时区的路径
        	File file = list.get(i);
        	if(name==null){
        		 dataFileTempPath = file.getAbsolutePath();
        		 QueryResourceInfo info = new QueryResourceInfo();
        		 info.setId(dataFileTempPath);
        		 info.setInfoIsHidden("0");
        		 info.setInfoName(file.getName());
        		 info.setInfoSize((file.length()/1024)+"");
        		  if (file.isDirectory()) {
        			  info.setInfoType("4");//图片视频库 文件夹
                  }else{
                	  info.setInfoType("3");//图片视频库 文件
                  }
        		
        		 info.setLast_update_date(new Date(file.lastModified()));
        		 info.setResourceType(type);
        		 infoList.add(info);
                 System.out.println(i+"dataFileTempPath:"+dataFileTempPath+"---name:"+file.getName());
        	}else if((file.getName()).indexOf(name)!=-1){
        		 dataFileTempPath = file.getAbsolutePath();
        		 QueryResourceInfo info = new QueryResourceInfo();
        		 info.setId(dataFileTempPath);
        		 info.setInfoIsHidden("0");
        		 info.setInfoName(file.getName());
        		 info.setInfoSize((file.length()/1024)+"");
        		  if (file.isDirectory()) {
        			  info.setInfoType("4");//图片视频库 文件夹
                  }else{
                	  info.setInfoType("3");//图片视频库 文件
                  }
        		
        		 info.setLast_update_date(new Date(file.lastModified()));
        		 info.setResourceType(type);
        		 infoList.add(info);
                 System.out.println(i+"dataFileTempPath:"+dataFileTempPath+"---name:"+file.getName());
        	}
           
        }
          
		return infoList;
	}


	/**
	 * 扫描文件库
	 * author pingZhou
	 */
	public void  readFileDb(){
		//\\\\192.168.3.188"+File.separator +"pv"+File.separator+"photo1
		
		try {
			String sql = "delete from file_cache";
			tjyptResourceInfoDao.createSQLQuery(sql).executeUpdate();
			
				//System.out.println("----------缓存文件库-----------"+"\\\\192.168.3.188"+File.separator +"pv"+File.separator+"photo1");
			//getFiles("\\\\192.168.3.188"+File.separator +"pv"+File.separator+"photo1","0","2");
			System.out.println("----------缓存文件库-----------"+"\\\\"+CLOUD_IP+File.separator +CLOUD_FOLDER+File.separator+"photo1");
			getFiles("\\\\"+CLOUD_IP+File.separator +CLOUD_FOLDER+File.separator+"photo1","0","2");
			
			
				//System.out.println("----------缓存文件库-----------"+"\\\\192.168.3.188"+File.separator +"pv"+File.separator+"video1");
			//getFiles("\\\\192.168.3.188"+File.separator +"pv"+File.separator+"video1","0","5");
			System.out.println("----------缓存文件库-----------"+"\\\\"+CLOUD_IP+File.separator +CLOUD_FOLDER+File.separator+"video1");
			getFiles("\\\\"+CLOUD_IP+File.separator +CLOUD_FOLDER+File.separator+"video1","0","5");
				System.out.println("----------缓存文件库结束-----------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public synchronized void getFiles(String filePath,String pid,String type) throws KhntException{
		 File root = new File(filePath);
	        if (!root.exists()) {
	            log.info(filePath + " not exist!");
	        } else {
	            File[] files = root.listFiles();
	            Arrays.sort(files, new CompratorByLastModified());  
	            for (File file : files) {
	            	FileCache fileCache = new  FileCache();
	            	fileCache.setInfoName(file.getName());
	            	fileCache.setFile_path(file.getPath());
	            	fileCache.setInfoSize((file.length()/1024)+"");
	            	fileCache.setLast_update_date(new Date(file.lastModified()));
	            	fileCache.setParent_id(pid);
	            	fileCache.setResourceType(type);
	                if (file.isDirectory()) {
	                	
	                	fileCache.setInfoType("4");
	                }else {
	                    //logger.info("目录:" + filePath + "文件全路径:" + file.getAbsolutePath());
	                	fileCache.setInfoType("3");
	                }
	                fileCacheDao.save(fileCache);
	                if (file.isDirectory()) {
	                	 getFiles(file.getAbsolutePath(),fileCache.getId(),type);
	                   
	                }
	            }
	        }
	     
	}
	
	
	 public HashMap<String, Object>  queryDownResource(String name,String spaceType) throws KhntException{
	    	HashMap<String, Object> wrapper = new HashMap<String, Object>();
			List inlist = new  ArrayList();
			List<TjyptResourceInfo> resourceList = null;
			if("9".equals(spaceType)){
				//院
				
				 resourceList = tjyptResourceInfoDao.createQuery("from  TjyptResourceInfo where  resource_owner_id='100000'" 
							+ " and resource_is_recyclebin='0'  and"+
	    			"(  resource_name like '%"+name
	    			+"%' or id = '"+name+
	    			"' )and resource_status='1' and resource_share_status is null  order by  resource_important_flag desc,resource_order_no desc ").list();
				
				 
			}
			if(resourceList.size()>0){
			
				for(TjyptResourceInfo resinfo:resourceList ){
					QueryResourceInfo info  = new QueryResourceInfo();
				
					info.setId(resinfo.getId());
					info.setInfoIsHidden(resinfo.getResource_is_hidden());
					try {
						info.setInfoName(new String(URLDecoder.decode(resinfo.getResource_name(),"UTF-8")));
					} catch (UnsupportedEncodingException e) {
						info.setInfoName(resinfo.getResource_name());
						e.printStackTrace();
					}
					info.setInfoRemark(resinfo.getResource_remark());
					info.setLast_update_date(resinfo.getResource_last_update_date());
					info.setInfoSize(resinfo.getResource_size());
					info.setInfoType("2");//1是文件夹2是资源
					info.setResourceType(resinfo.getResource_type());
					info.setLevel(resinfo.getResource_important_flag());
					if("4028839458af31b40158d23e06590011".equals(resinfo.getResource_path())){
						info.setShareUser("下载中心");
					}else{
						info.setShareUser("院空间");
					}
					
					
					JSONObject obj = info.to_Json();
					obj.put("is_db", "0");
					inlist.add(obj);
				}
			}
			
			
			List<FileCache> ylist11 = fileCacheDao.getInfosOnly(name);
			List<JSONObject> ylist11j = new ArrayList<JSONObject>();
			for (int i = 0; i < ylist11.size(); i++) {
				FileCache info = ylist11.get(i);
				JSONObject obj = info.to_Json();
				obj.put("is_db", "1");//磁盘资源库文件
				obj.put("shareUser", "院空间");
				inlist.add(obj);
			}
			int sumC = inlist.size()/8;
			
			if(inlist.size()%8>0){
				sumC = sumC + 1;
			}
			if(sumC ==0){
				sumC = 1;
			}
			if(resourceList.size()==1){
				wrapper.put("name",resourceList.get(0).getResource_name());
				wrapper.put("id",name);
			}else{
				wrapper.put("name",name);
			}
			
			wrapper.put("queryInfo",inlist);
			wrapper.put("queryInfoC",inlist.size());
			wrapper.put("sumC",sumC);
			return wrapper;
	    }
	public HashMap<String, Object> queryDownFile(String spaceType,String name) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List inlist = new  ArrayList();
		List<TjyptResourceInfo> resourceList = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		if("9".equals(spaceType)){
			//院
			String hql ="from  TjyptResourceInfo where  resource_owner_id='100000'" 
					+ " and resource_is_recyclebin='0' and if_down='1' and resource_status='1'";
			
			if(name!=null&&StringUtil.isNotEmpty(name)){
				hql = hql + " and resource_name like '%"+name+"%' ";
			}
			
			hql = hql + " and resource_share_status is null  order by  resource_last_update_date desc ";
			
		
			 resourceList = tjyptResourceInfoDao.createQuery(hql).list();
			
			 
		}
		if(resourceList.size()>0){
		
			for(TjyptResourceInfo resinfo:resourceList ){
				
				QueryResourceInfo info  = new QueryResourceInfo();
			
				//Org org = userDao.get(resinfo.getResource_share_user_id()).getOrg();
				//Org org = TS_Util.getCurOrg((CurrentSessionUser) userDao.get(resinfo.getResource_share_user_id()));
				Org org = orgDao.get(resinfo.getOrg_id());
				String orgName = org.getOrgName();
				String org_id = org.getId();
				List inlisti = null;
				if(map.containsKey("o"+org_id)){
					inlisti = (List) (((HashMap<String, Object>)(map.get("o"+org_id))).get("list"));
				}
				if(inlisti == null){
					inlisti = new  ArrayList();
					HashMap<String, Object> mapi = new HashMap<String, Object>();
					mapi.put("list", inlisti);
					
					try {
						mapi.put("name", URLDecoder.decode(orgName,"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						mapi.put("name", orgName);
						e.printStackTrace();
					}
					map.put("o"+org_id, mapi);
				}
				
				info.setId(resinfo.getId());
				info.setInfoIsHidden(resinfo.getResource_is_hidden());
				try {
					info.setInfoName(URLDecoder.decode(resinfo.getResource_name(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					info.setInfoName(resinfo.getResource_name());
					e.printStackTrace();
				}
				
				info.setInfoRemark(resinfo.getResource_remark());
				info.setLast_update_date(resinfo.getResource_last_update_date());
				info.setInfoSize(resinfo.getResource_size());
				info.setInfoType("2");//1是文件夹2是资源
				info.setResourceType(resinfo.getResource_type());
				info.setLevel(resinfo.getResource_important_flag());
				info.setShareUser(resinfo.getResource_share_user());
				JSONObject obj = info.to_Json();
				obj.put("is_db", "0");
				inlisti.add(obj);
			}
		}
		
		int sumC = resourceList.size()/8;
		
		if(resourceList.size()%8>0){
			sumC = sumC + 1;
		}
		if(sumC ==0){
			sumC = 1;
		}
		
		String orgsql = "select t.id from SYS_ORG t,tjypt_resource_space p where t.id = p.id and t.id <> '100000' and t.status='used' order by t.orders";
		List orglist =  tjyptResourceInfoDao.createSQLQuery(orgsql).list();
		wrapper.put("queryInfo",map);
		wrapper.put("orglist",orglist);
		wrapper.put("queryInfoC",resourceList.size());
		wrapper.put("sumC",sumC);
		return wrapper;
	}
	
	/**
	 * 附件同步到资料云
	 * author pingZhou
	 * @param request HttpServletRequest
	 * @param orgId 归属部门
	 * @param fk_attachment_id 附件id
	 * @param business_id 业务id
	 * @param fileName 文件名  为空时取附件名
	 */
	public void copyUploadToCloud(HttpServletRequest request,String orgId,String fk_attachment_id,String business_id,String fileName){
		
		if(business_id==null){
			throw new KhntException("同步云没有传入业务id！");
		}
		
		boolean flag = false; 
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		//判断附件是否存在
		Attachment attachment = attachmentDao.get(fk_attachment_id);
		if(attachment==null){
			throw new KhntException("附件不存在！");
		}
		
		//判断是否已经同步过
		TjyptFileInfo file = null; 
		
		TjyptResourceInfo tjyptResourceInfo  = tjyptResourceInfoDao.getByBusinessId(business_id);
		if(tjyptResourceInfo==null){
			tjyptResourceInfo  = new TjyptResourceInfo();
			file = new TjyptFileInfo();
			flag = true; 
		}else{
			file = fileInfoDao.get(tjyptResourceInfo.getFk_file_id());
		}
		String realPath;// 文件路径
		if (CLOUD_SAVE_PATH_R.equals(Factory.getSysPara().getProperty("attachmentPathType", "relative"))) // 相对路径
			realPath = Factory.getWebRoot() + Factory.getSysPara().getProperty("attachmentPath");
		else
			realPath = Factory.getSysPara().getProperty("attachmentPath");// 绝对路径

		file.setFile_path(realPath);
		file.setFk_attachment_id(attachment.getId());
		file.setFile_upload_date(attachment.getUploadTime());
		file.setFile_upload_user_id(attachment.getUploaderName());
		file.setFk_attachment_id(fk_attachment_id);

		fileInfoDao.save(file);
		tjyptResourceInfo.setFk_file_id(file.getId());
		tjyptResourceInfo.setResource_name(fileName == null ? attachment.getFileName() : fileName);
		CurrentSessionUser sessionUser = (CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Map<String, String> roles = sessionUser.getRoles();

		tjyptResourceInfo.setResource_owner_id("100000");
		if (TS_Util.getCurOrg(user).getId().equals("100028") || roles.get("402883a058a93e3f0158aa1d104f2964") != null) {

		} else {
			tjyptResourceInfo.setResource_share_status("0");
		}

		tjyptResourceInfo.setResource_path("4028839458af31b40158d23e06590011");// 放在下载中心
		tjyptResourceInfo.setResource_size((attachment.getFileSize() / 1024) + "");
		tjyptResourceInfo.setResource_type(cloudAttachmentManager.judgeResourceType(attachment.getFileName()));
		tjyptResourceInfo.setResource_last_update_date(attachment.getUploadTime());
		tjyptResourceInfo.setResource_is_recyclebin("0");
		tjyptResourceInfo.setResource_is_hidden("0");
		tjyptResourceInfo.setResource_status("1");

		tjyptResourceInfo.setResource_share_user(user.getName());
		tjyptResourceInfo.setResource_share_user_id(user.getId());
		tjyptResourceInfo.setIf_down("1");
		tjyptResourceInfo.setResource_owner_id("100000");
		tjyptResourceInfo.setOrg_id(orgId);

		tjyptResourceInfoDao.save(tjyptResourceInfo);

		if (flag) {
			String ip = "";
			if (request != null) {
				ip = TS_Util.getIpAddress(request);
			}

			tjyptLogService.saveLog("2", user.getId(), user.getName(), new Date(), tjyptResourceInfo.getId(),
					"上传文件：" + tjyptResourceInfo.getResource_name(), ip, "", "");
		}

	}

}

