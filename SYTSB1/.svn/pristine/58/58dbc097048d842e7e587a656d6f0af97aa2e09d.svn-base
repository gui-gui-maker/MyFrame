package com.scts.patent.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.scts.paper.bean.Paper;
import com.scts.paper.service.PaperService;
import com.scts.patent.bean.Patent;
import com.scts.patent.service.PatentService;

/**
 * 论文管理管理控制器
 * 
 * @ClassName PatentAction
 * @JDK 1.8
 * @author xcb
 * @date 2019-02-28 
 */
@Controller
@RequestMapping("patentAction")
public class PatentAction extends
		SpringSupportAction<Patent, PatentService> {
	
	@Autowired
	private PatentService patentmanage;
	@Autowired
	private AttachmentManager attachmentManager;
	
		// 保存
		@RequestMapping(value = "saveBasic")
		@ResponseBody
		public HashMap<String, Object> saveBasic(HttpServletRequest request, Patent patent) throws Exception {
			
			String uploadFiles = request.getParameter("uploadFiles");
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			try {
				
				patentmanage.saveBasic(patent,uploadFiles);
				
				wrapper.put("success", true);
				
			} catch (Exception e) {
				log.debug(e.toString());
				wrapper.put("success", false);
				wrapper.put("msg", "保存失败！");
				e.printStackTrace();
			}
			return wrapper;
		}

		// 获取论文信息
		@RequestMapping(value = "detailPatent")
		@ResponseBody
		public HashMap<String, Object> detailPatent(HttpServletRequest request)
				throws Exception {
			HashMap<String, Object> wrapper = new HashMap<String, Object>();
			String id = request.getParameter("id");
			try {
				Patent patent=patentmanage.get(id);
				List<Attachment> list = attachmentManager.getBusAttachment(id);
				wrapper.put("success", true);
				wrapper.put("data", patent);
				wrapper.put("attachs", list);
			} catch (Exception e) {
				log.debug(e.toString());
				return JsonWrapper.failureWrapperMsg("读取用车申请信息失败！");
			}
			return wrapper;
		}
		
		// 删除
		@RequestMapping(value = "del")
		@ResponseBody
		public HashMap<String, Object> del(HttpServletRequest request, String ids)
				throws Exception {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				patentmanage.del(request, idArr[i]);
			}
			return JsonWrapper.successWrapper(ids);
		}
		
		//提交
		@RequestMapping(value = "submit")
		@ResponseBody
		public HashMap<String, Object> submit(HttpServletRequest request,String ids) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (ids.isEmpty()){
				map.put("success", false);
				map.put("msg", "所选业务ID为空！");
			} else {
				String[] idArr = ids.split(",");
				for (int i = 0; i < idArr.length; i++) {
					patentmanage.submit(request, idArr[i]);
				}
				map.put("success", true);
			}
			return map;
		}
		//提交
		@RequestMapping(value = "dispose")
		@ResponseBody
		public HashMap<String, Object> dispose(HttpServletRequest request,String ids) throws Exception {
			HashMap<String, Object> map = new HashMap<String, Object>();
				if (ids.isEmpty()){
					map.put("success", false);
					map.put("msg", "所选业务ID为空！");
				} else {
					String[] idArr = ids.split(",");
					for (int i = 0; i < idArr.length; i++) {
						patentmanage.dispose(request, idArr[i]);
					}
					map.put("success", true);
				}
				return map;
		}
		
}
