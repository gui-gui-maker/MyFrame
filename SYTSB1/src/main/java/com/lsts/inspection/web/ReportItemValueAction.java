package com.lsts.inspection.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rtbox.config.bean.ReportMark;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.handle.export.RtSaveAsst;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.ReportItemValue;
import com.lsts.inspection.service.ReportItemValueService;

import net.sf.json.JSONArray;

/**
 * @author 作者 PingZhou
 * @JDK 1.6
 * @version 创建时间：2016年1月19日 下午3:36:36 类说明
 */
@Controller
@RequestMapping("reportItemValueAction")
public class ReportItemValueAction extends SpringSupportAction<ReportItemValue, ReportItemValueService> {

	@Autowired
	private ReportItemValueService reportItemValueService;

	@RequestMapping(value = "checkIfHg")
	@ResponseBody
	public HashMap<String, Object> checkIfHg(HttpServletRequest request, String ids) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			boolean flag = reportItemValueService.checkIfHg(ids);

			map.put("sfhg", flag);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}

		return map;

	}

	/**
	 * 加载报告（录入）信息(新报表)
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @author xuqianhui
	 */
	@RequestMapping("detailMap")
	@ResponseBody
	private HashMap<String, Object> detailMap(HttpServletRequest request, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String fk_report_id = request.getParameter("fk_report_id");
			String input = request.getParameter("input");
			String code_ext = request.getParameter("code_ext");
			String fk_inspection_info_id = request.getParameter("fk_inspection_info_id");
			if (StringUtil.isEmpty(fk_inspection_info_id)) {
				fk_inspection_info_id = id;
			}
			String nowpage = request.getParameter("now");
			String page = nowpage;
			List<Map<String, String>> list = this.reportItemValueService.detailMap(fk_inspection_info_id, input, page,code_ext);
			HashMap<String, Object> pageMap = reportItemValueService.getRecordModelDir(fk_inspection_info_id, null);
			map.put("data", list);
			map.put("pageMap", pageMap);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 保存报告信息（新报表）
	 * @param request
	 * @param list
	 * @return
	 * @author xuqianhui
	 */
	@RequestMapping("saveMap")
	@ResponseBody
	private HashMap<String, Object> saveMap(HttpServletRequest request, @RequestBody List<Map<String, Object>> list) {
		try {
			Map<String, RtExportData> map = RtSaveAsst.transToMap(list, RtPageType.TABLE);
			String page = request.getParameter("page");
			String pageName = request.getParameter("pageName");
			this.reportItemValueService.saveMap(map, page,pageName);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	/**
	 * 保存应收金额（新报表录入前保存）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("saveYsje")
	@ResponseBody
	private HashMap<String, Object> saveYsje(HttpServletRequest request) {
		return reportItemValueService.saveYsje(request);
	}
	
	@RequestMapping("saveLabel")
	@ResponseBody
	private HashMap<String, Object> saveLabel(HttpServletRequest request, @RequestBody ReportMark reportMark) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String fk_report_id = request.getParameter("fk_report_id");
			String fk_inspection_info_id = request.getParameter("fk_inspection_info_id");
			this.reportItemValueService.saveLabel(reportMark,fk_report_id,fk_inspection_info_id);
			map.put("success", true);
			map.put("data", reportMark);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	@RequestMapping("deleteLabel")
	@ResponseBody
	private HashMap<String, Object> deleteLabel(HttpServletRequest request, String id) {
		try {
			this.reportItemValueService.deleteLabel(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	@RequestMapping("checkLabel")
	@ResponseBody
	private HashMap<String, Object> checkLabel(HttpServletRequest request, String businessId) {
		try {
			int num = this.reportItemValueService.checkLabel(businessId);
			return JsonWrapper.successWrapper("data", num);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
	
	@RequestMapping("checkLabel2")
	@ResponseBody
	private HashMap<String, Object> checkLabel2(HttpServletRequest request, String objects) {
		try {
			JSONArray objs = JSONArray.fromObject(objects);
			final int len = objs.length();
			String[] ids = new String[len];
			for (int i = 0; i < len; i++) {
				ids[i] = objs.getJSONObject(i).getString("id");
			}
			List<Object[]> list = reportItemValueService.checkLabel2(ArrayUtils.toString(ids, ","));
			if(list.size()>0) {
				StringBuilder msg = new StringBuilder();
				for (int i = 0; i < list.size(); i++) {
					Object[] item = list.get(i);
					msg.append("报告ID："+item[0].toString()+",未处理标注数量："+item[1].toString()+"\n");
				}
				return JsonWrapper.failureWrapperMsg(msg.toString());
			}
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	
}
