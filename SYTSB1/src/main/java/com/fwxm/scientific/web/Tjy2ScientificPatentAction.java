package com.fwxm.scientific.web;

import com.fwxm.scientific.bean.Tjy2ScientificPaper;
import com.fwxm.scientific.bean.Tjy2ScientificPatent;
import com.fwxm.scientific.service.Tjy2ScientificPatentManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("tjy2ScientificPatentAction")
public class Tjy2ScientificPatentAction extends SpringSupportAction<Tjy2ScientificPatent, Tjy2ScientificPatentManager> {

    @Autowired
    private Tjy2ScientificPatentManager  tjy2ScientificPatentManager;
    @Autowired
	private AttachmentManager attachmentManager;
    /**
	 * 保存
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "savePatent")
	@ResponseBody
	public HashMap<String, Object> savePatent(HttpServletRequest request, Tjy2ScientificPatent entity) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {
			tjy2ScientificPatentManager.savePatent(entity, uploadFiles); // 保存科研项目费用信息
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存项目费用失败，请重试！");
		}
		return JsonWrapper.successWrapper(entity);
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request,String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<Tjy2ScientificPatent> list = tjy2ScientificPatentManager.queryTjy2ScientificPatentByBasicId(id);
				if(!list.isEmpty()){
					for (Tjy2ScientificPatent patent : list) {
						
						Map map = patent.to_Map();
						datalist.add(map);
					}
				}
				wrapper.put("success", true);
				wrapper.put("datalist", datalist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
}