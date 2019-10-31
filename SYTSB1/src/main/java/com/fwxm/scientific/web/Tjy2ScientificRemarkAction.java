package com.fwxm.scientific.web;

import com.fwxm.scientific.bean.InstructionRw;
import com.fwxm.scientific.bean.Tjy2ScientificCost;
import com.fwxm.scientific.bean.Tjy2ScientificRemark;
import com.fwxm.scientific.service.Tjy2ScientificCostManager;
import com.fwxm.scientific.service.Tjy2ScientificRemarkManager;
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
@RequestMapping("tjy2ScientificRemarkAction")
public class Tjy2ScientificRemarkAction extends SpringSupportAction<Tjy2ScientificRemark, Tjy2ScientificRemarkManager> {

    @Autowired
    private Tjy2ScientificRemarkManager  tjy2ScientificRemarkManager;
	@Autowired
	private AttachmentManager attachmentManager;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request,String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				List<Tjy2ScientificRemark> list = tjy2ScientificRemarkManager.queryTjy2ScientificRemarkByBasicId(id);
				if(!list.isEmpty()){
					for (Tjy2ScientificRemark cost : list) {
						
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detailBaic")
	@ResponseBody
	public HashMap<String, Object> detailBaic(HttpServletRequest request,String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Map> datalist = new ArrayList<Map>();
		try {
			Tjy2ScientificRemark maintenance = tjy2ScientificRemarkManager.get(id);
	   		List<Attachment> list = attachmentManager.getBusAttachment(id);
	   		System.out.println("**************"+maintenance.getId());
	   		wrapper.put("success", true);
	   		wrapper.put("data", maintenance);
	   		wrapper.put("attachs", list);
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
}