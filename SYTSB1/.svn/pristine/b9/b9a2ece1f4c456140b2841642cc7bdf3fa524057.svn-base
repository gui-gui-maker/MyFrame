package com.fwxm.scientific.web;

import com.fwxm.scientific.bean.Tjy2ScientificCost;
import com.fwxm.scientific.service.Tjy2ScientificCostManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.utils.StringUtil;
import com.lsts.employee.bean.EmployeeCert;

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
@RequestMapping("tjy2ScientificCostAction")
public class Tjy2ScientificCostAction extends SpringSupportAction<Tjy2ScientificCost, Tjy2ScientificCostManager> {

    @Autowired
    private Tjy2ScientificCostManager  tjy2ScientificCostManager;
	@Autowired
	private AttachmentManager attachmentManager;
	/**
	 * 保存
	 * 
	 * @param request
	 * @param employeeCert
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCost")
	@ResponseBody
	public HashMap<String, Object> saveCost(HttpServletRequest request, Tjy2ScientificCost entity) throws Exception {
		String uploadFiles = request.getParameter("uploadFiles");
		try {
			tjy2ScientificCostManager.saveCost(entity, uploadFiles); // 保存科研项目费用信息
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
				List<Tjy2ScientificCost> list = tjy2ScientificCostManager.queryTjy2ScientificCostByBasicId(id);
				if(!list.isEmpty()){
					for (Tjy2ScientificCost cost : list) {
						
						Map map = cost.to_Map();
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