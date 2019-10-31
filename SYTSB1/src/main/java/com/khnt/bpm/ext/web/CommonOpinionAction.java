package com.khnt.bpm.ext.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.bpm.ext.bean.CommonOpinion;
import com.khnt.bpm.ext.service.CommonOpinionManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.core.crud.web.support.QueryCondition;
import com.khnt.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("bpm/common_opinion/")
public class CommonOpinionAction extends SpringSupportAction<CommonOpinion, CommonOpinionManager> {

	@Autowired
	CommonOpinionManager commonOpinionManager;

	public HashMap<String, Object> save(HttpServletRequest request, CommonOpinion entity) throws Exception {
		entity.setUserId(getCurrentUser().getId());
		if (StringUtil.isEmpty(entity.getId()))
			entity.setId(null);
		return super.save(request, entity);
	}

	@RequestMapping("set_default")
	@ResponseBody
	public String setDefault(String id, String dval) {
		this.commonOpinionManager.setDefault(id, dval);
		return JsonWrapper.successJSONString();
	}

	@RequestMapping("user_opinions")
	@ResponseBody
	public String getUserOpinions() throws Exception {
		QueryCondition qc = new QueryCondition(CommonOpinion.class);
		qc.addCondition("userId", "=", getCurrentUser().getId());
		qc.setOrderBy("sort", "asc");
		List<CommonOpinion> ucos = this.commonOpinionManager.query(qc);

		JSONObject rstval = new JSONObject();
		rstval.put("success", true);
		JSONArray jcos = new JSONArray();
		for (CommonOpinion co : ucos) {
			JSONObject jco = new JSONObject();
			jco.put("id", co.getId());
			jco.put("text", co.getContent());
			jco.put("isDefault", co.getIsDefault());
			if ("1".equals(co.getIsDefault())) {
				if (rstval.has("defaultOpinion"))
					rstval.remove("defaultOpinion");
				rstval.put("defaultOpinion", co.getContent());
			}
			jcos.put(jco);
		}
		rstval.put("data", jcos);
		return rstval.toString();
	}
}
