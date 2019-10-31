package com.scts.cspace.path.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.scts.cspace.file.bean.TjyptFileInfo;
import com.scts.cspace.file.dao.TjyptFileInfoDao;
import com.scts.cspace.path.bean.TjyptResourcePath;
import com.scts.cspace.path.dao.TjyptResourcePathDao;
import com.scts.cspace.path.service.TjyptResourcePathService;
import com.scts.cspace.resource.bean.TjyptResourceInfo;
import com.scts.cspace.resource.dao.TjyptResourceInfoDao;

/**
 * 资源分类属性
 * 
 * @ClassName TjyptResourceSpaceAction
 * @JDK 1.7
 * @author XCB
 * @date 2016-10-24 下午04:49:00
 */
@Controller
@RequestMapping("resourcePath")
public class TjyptResourcePathAction extends
		SpringSupportAction<TjyptResourcePath, TjyptResourcePathService> {

	@Autowired
	private TjyptResourcePathService tjyptResourcePathService;
	@Autowired
	private TjyptResourcePathDao tjyptResourcePathDao;
	@Autowired
	private  TjyptResourceInfoDao tjyptResourceInfoDao;
	@Autowired
	private  TjyptFileInfoDao fileInfoDao;

	/**
	 * 新建资源路劲分类 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "createResourcePath")
	@ResponseBody
	public HashMap<String, Object> createResourcePath(HttpServletRequest request) {

		try {

			String spaceId = request.getParameter("spaceId");// 获取资源空间ID

			String parentId = request.getParameter("parentId");// 获取上级资源路径ID

			String pathName = request.getParameter("pathName");// 获取路径名称

			String flag = tjyptResourcePathService.createResourcePath(spaceId,
					parentId, pathName);

			if (flag.equals("2")) {
				return JsonWrapper.successWrapper(1);
			} else {
				return JsonWrapper.failureWrapperMsg("该文件夹已存在，请重新命名！");
			}

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("创建资源路径分类失败，请重试！");
		}

	}

	/**
	 * 删除文件或资源路 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "delResourcePath")
	@ResponseBody
	public HashMap<String, Object> delResourcePath(HttpServletRequest request) {

		try {

			String delId = request.getParameter("delId");// 获取ID

			String type = request.getParameter("type");// 删除的类型 文件夹或资源 1文件夹2资源
			
			String spaceType = request.getParameter("spaceType");// 获取ID
			
			CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Map<String,String> roles=sessionUser.getRoles();
			if(spaceType.equals("7")){
				/*if(roles.get("402883a058a93e3f0158aa1d104f2964")==null){
					return JsonWrapper.failureWrapperMsg("对不起，你没有删除权限！");
				}*/
			}else if(spaceType.equals("9")){
				String id[] =delId.split(",");
				String temp[]=type.split(",");
				for(int i=0;i<id.length;i++){
					if(temp[i].equals("1")){//文件夹
						TjyptResourcePath resourcePath = tjyptResourcePathDao.get(id[i]);
						if(!resourcePath.getPath_create_user_id().equals(sessionUser.getId())&&roles.get("402883a058a93e3f0158aa1c7d842956")==null){
							return JsonWrapper.failureWrapperMsg("对不起，只能删除自己上传的文件夹！");
						}
						
					}else{
						TjyptResourceInfo  resourceInfo = tjyptResourceInfoDao.get(id[i]);	//获取资源对象	
						
						TjyptFileInfo info=fileInfoDao.get(resourceInfo.getFk_file_id());
						if(!info.getFile_upload_user_id().equals(sessionUser.getName())&&roles.get("402883a058a93e3f0158aa1c7d842956")==null){
							return JsonWrapper.failureWrapperMsg("对不起，只能删除自己上传的文件！");
						}
					}
					
				}
			}
			tjyptResourcePathService.delResourcePath(delId, type,request);
			
			return JsonWrapper.successWrapper(1);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("删除数据失败，请重试！");
		}

	}
	/**
	 * 删除文件或资源路 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "delResourceRecycle")
	@ResponseBody
	public HashMap<String, Object> delResourceRecycle(HttpServletRequest request) {

		try {

			String delId = request.getParameter("delId");// 获取ID

			String type = request.getParameter("type");// 删除的类型 文件夹或资源 1文件夹2资源

			tjyptResourcePathService.delResourceRecycle(delId, type);
			
			return JsonWrapper.successWrapper(1);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("删除数据失败，请重试！");
		}

	}
	
	/**
	 * 恢复文件或资源路 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "backResourceRecycle")
	@ResponseBody
	public HashMap<String, Object> backResourceRecycle(HttpServletRequest request) {

		try {

			String delId = request.getParameter("delId");// 获取ID

			String type = request.getParameter("type");// 删除的类型 文件夹或资源 1文件夹2资源

			tjyptResourcePathService.backResourceRecycle(delId, type,request);
			
			return JsonWrapper.successWrapper(1);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("删除数据失败，请重试！");
		}

	}
	
	/**
	 * 根据ID查询父ID xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "queryParentId")
	@ResponseBody
	public HashMap<String, Object> queryParentId(HttpServletRequest request) {
		
	

		try {

			String pathId = request.getParameter("pathId");// 获取文件ID


			return tjyptResourcePathService.queryParentId(pathId);
			
		
		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("查询父ID失败，请重试！");
		}

	}

	/**
	 * 查询文件夹下的文件和资源 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "queryResourceByPath")
	@ResponseBody
	public HashMap<String, Object> queryResourceByPath(
			HttpServletRequest request) {

		try {

			String pathId = request.getParameter("pathId");// 获取文件夹ID

			String orderName = request.getParameter("orderName");// 获取排序字段名 3个参数
																	// 名称name
																	// 最后修改日期last_update_date
																	// 创建日期
																	// create_date

			String orderBy = request.getParameter("orderBy");// 获取排序
			String spaceType = request.getParameter("spaceType");// 空间类型
			String name = request.getParameter("name");// 查询条件

			return tjyptResourcePathService.queryResourceByPath(pathId,
					orderName, orderBy,spaceType,name);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("查询资源路径分类失败，请重试！");
		}

	}

	/**
	 * 查询文件夹下的文件 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "queryFileByPath")
	@ResponseBody
	public HashMap<String, Object> queryFileByPath(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("pathId");// 获取文件夹ID

			String orderName = request.getParameter("orderName");// 获取排序字段名 3个参数
																	// 名称name
																	// 最后修改日期last_update_date
																	// 创建日期
																	// create_date

			String orderBy = request.getParameter("orderBy");// 获取排序

			return tjyptResourcePathService.queryFileByPath(pathId, orderName,
					orderBy);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("查询资源路径分类失败，请重试！");
		}

	}

	/**
	 * 修改上级资源分类
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updateParentPathId")
	@ResponseBody
	public HashMap<String, Object> updateParentPathId(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			String parentPathId = request.getParameter("parentPathId");// 获取上级资源分类

			tjyptResourcePathService.updateParentPathId(pathId, parentPathId);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源上级分类失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源分类名称
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePathName")
	@ResponseBody
	public HashMap<String, Object> updatePathName(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			String pathName = request.getParameter("pathName");// 获取资源分类名称

			tjyptResourcePathService.updatePathName(pathId, pathName);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类名称失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源分类备注
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePathRemark")
	@ResponseBody
	public HashMap<String, Object> updatePathRemark(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			String pathRemark = request.getParameter("pathRemark");// 获取资源分类名称

			tjyptResourcePathService.updatePathRemark(pathId, pathRemark);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类备注失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 设置资源分类为隐藏
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "hiddenPath")
	@ResponseBody
	public HashMap<String, Object> hiddenPath(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			tjyptResourcePathService.hiddenPath(pathId);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类为隐藏失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 设置资源分类为显示
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "showPath")
	@ResponseBody
	public HashMap<String, Object> showPath(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			tjyptResourcePathService.showPath(pathId);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类为显示失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 设置资源分类在回收站
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "pathInRecyclebin")
	@ResponseBody
	public HashMap<String, Object> pathInRecyclebin(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			tjyptResourcePathService.pathInRecyclebin(pathId);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类在回收站失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 设置资源分类不在回收站
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "pathOutRecyclebin")
	@ResponseBody
	public HashMap<String, Object> pathOutRecyclebin(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			tjyptResourcePathService.pathOutRecyclebin(pathId);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类不在回收站失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源分类重要等级
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePathImportantFlag")
	@ResponseBody
	public HashMap<String, Object> updatePathImportantFlag(
			HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			String pathImportantFlag = request
					.getParameter("pathImportantFlag");// 获取资源分类重要等级

			tjyptResourcePathService.updatePathImportantFlag(pathId,
					pathImportantFlag);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类重要等级失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源分类自定义排序
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePathOrderNo")
	@ResponseBody
	public HashMap<String, Object> updatePathOrderNo(HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			String pathOrderNo = request.getParameter("pathOrderNo");// 获取资源分类自定义排序

			tjyptResourcePathService.updatePathImportantFlag(pathId,
					pathOrderNo);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类自定义排序失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}

	/**
	 * 修改资源分类访问验证密码
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePathAccessPassword")
	@ResponseBody
	public HashMap<String, Object> updatePathAccessPassword(
			HttpServletRequest request) {

		try {

			String pathId = request.getParameter("id");// 获取分类对象ID

			String pathAccessPassword = request
					.getParameter("pathAccessPassword");// 获取资源分类访问验证密码

			tjyptResourcePathService.updatePathImportantFlag(pathId,
					pathAccessPassword);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("修改资源分类访问验证密码失败，请重试！");
		}

		return JsonWrapper.successWrapper(1);
	}
	
	/**
	 * 查询分享时待接收的文件夹下的文件和资源 pingZhou
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "queryResourceNeedReceive")
	@ResponseBody
	public HashMap<String, Object> queryResourceNeedReceive(
			HttpServletRequest request) {

		try {
			String spaceType=request.getParameter("spaceType");
			return tjyptResourcePathService.queryResourceNeedReceive(spaceType);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("查询待接收资源路径分类失败，请重试！");
		}

	}
	/**
	 * 定时查询分享最新待接收的文件夹下的文件和资源 pingZhou
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "queryResourceNeedReceiveNew")
	@ResponseBody
	public HashMap<String, Object> queryResourceNeedReceiveNew(
			HttpServletRequest request) {

		try {
			String spaceType=request.getParameter("spaceType");
			return tjyptResourcePathService.queryResourceNeedReceiveNew(spaceType);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("查询待接收资源路径分类失败，请重试！");
		}

	}
	
	/**
	 * 待接收文件不接收 ZHOU
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "refuseShare")
	@ResponseBody
	public HashMap<String, Object> refuseShare(HttpServletRequest request) {

		try {

			String delId = request.getParameter("delId");// 获取ID

			String type = request.getParameter("type");// 删除的类型 文件夹或资源 1文件夹2资源

			tjyptResourcePathService.refuseShare(delId, type,request);
			
			return JsonWrapper.successWrapper(1);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("删除数据失败，请重试！");
		}

	}
	

	/**
	 * 查询文件夹下的文件和资源 xcb
	 * 
	 * @param request
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "queryDownResourceByPath")
	@ResponseBody
	public HashMap<String, Object> queryDownResourceByPath(
			HttpServletRequest request) {

		try {

			String pathId = request.getParameter("pathId");// 获取文件夹ID

			String orderName = request.getParameter("orderName");// 获取排序字段名 3个参数
																	// 名称name
																	// 最后修改日期last_update_date
																	// 创建日期
																	// create_date

			String orderBy = request.getParameter("orderBy");// 获取排序
			String spaceType = request.getParameter("spaceType");// 空间类型
			String name = request.getParameter("name");// 查询条件
			String orgF = request.getParameter("orgF");
			return tjyptResourcePathService.queryDownResourceByPath(pathId,
					orderName, orderBy,spaceType,name,orgF);

		} catch (Exception e) {

			e.printStackTrace();

			return JsonWrapper.failureWrapperMsg("查询资源路径分类失败，请重试！");
		}

	}

	
}