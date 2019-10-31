package com.khnt.bpm.ext.web;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.ext.bean.FlowType;
import com.khnt.bpm.ext.service.FlowTypeManager;
import com.khnt.core.crud.web.SpringSupportAction;

/**
 * 流程分类controller
 */
@Controller
@RequestMapping("/bpm/flowType/")
public class FlowTypeAction extends SpringSupportAction<FlowType, FlowTypeManager> {

	@Autowired
	private FlowTypeManager flowTypeManager;

	/**
	 * 获取分类树，以json方式展示。
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("viewTree")
	@ResponseBody
	public String viewTypeTree() throws Exception {
		List<FlowType> alltypes = this.flowTypeManager.queryAll();
		JSONArray jsonArray = new JSONArray();
		if (!alltypes.isEmpty()) {
			for (FlowType t : alltypes) {
				JSONObject jo = new JSONObject();
				jo.put("id", t.getId());
				jo.put("typeName", t.getTypeName());
				jo.put("typeCode", t.getTypeCode());
				jo.put("sort", t.getSort());
				if (t.getFlowType() == null) {
					jo.put("parentId", "");
				} else {
					jo.put("parentId", t.getFlowType().getId());
					JSONObject p = new JSONObject();
					p.put("id", t.getFlowType().getId());
					jo.put("flowType", p);
				}
				jo.put("remark", t.getRemark());
				jsonArray.put(jo);
			}
		}
		return jsonArray.toString();
	}

}
