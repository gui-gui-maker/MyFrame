package com.khnt.rtbox.config.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rtbox.config.bean.RtDir;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.service.RtDirManager;
import com.khnt.utils.StringUtil;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName Dir
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Controller
@RequestMapping("com/rt/dir")
public class RtDirAction extends SpringSupportAction<RtDir, RtDirManager> {

	@Autowired
	private RtDirManager rtDirManager;

	@RequestMapping("getDir")
	@ResponseBody
	public String getDir(HttpServletRequest request, String id, String code) {

		try {
			String json = this.rtDirManager.getDir(id, code);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}

	}
	
	@RequestMapping(value = "getTempDirs")
	@ResponseBody
	public HashMap<String, Object> getTempDirs(HttpServletRequest request, String code)
			throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String json = rtDirManager.getTempDirs(code);
			map.put("data", json);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("访问失败！");
		}
	}

	@RequestMapping("setDir")
	@ResponseBody
	public HashMap<String, Object> setDir(HttpServletRequest request, String id, RtDir rtDir) {
		try {
			String oldCode = request.getParameter("oldCode");
			String newCode = request.getParameter("newCode");
			String setType = request.getParameter("setType");
			// String path = request.getParameter("path");
			if (StringUtil.isEmpty(id)) {
				throw new Exception("页面名称为空");
			}
			if (!"setDefault".equals(setType)) {
				String dirJson = rtDir.getRtDirJson();
				// dirJson =
				// dirJson.replaceAll("\\{\"code\":\"\\d{1,10}\",\"name\":\"[\\u4e00-\\u9fa5\\w\\d]*\",\"pageName\":\"index\\d{1,10}.jsp(\\?code_ext=[_\\d]){0,1}\",\"treedataindex\":\\d*,\"__status\":\"delete\"\\}(,){0,1}",
				// "");
				dirJson = dirJson.replaceAll(",\"treedataindex\":\\d*,\"__status\":\"add\"", "");
				JSONArray array = new JSONArray(dirJson);
				JSONArray temp = new JSONArray();
				filterDelNode(array, temp);
				dirJson = temp.toString();
				// dirJson = dirJson.replaceAll(",\"treedataindex\":\\d*", "");
				rtDir.setRtDirJson(dirJson);
			}
			this.rtDirManager.setDir(id, rtDir, oldCode, newCode, setType);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}

	/**
	 * 过滤删除掉的NODE
	 * 
	 * @param array
	 * @param temp
	 * @throws JSONException
	 */
	public static void filterDelNode(JSONArray array, JSONArray temp) throws JSONException {
		for (int i = 0, l = array.length(); i < l; i++) {
			JSONObject object = (JSONObject) array.get(i);
			if (!object.has("__status")||!"delete".equals(object.getString("__status"))) {
				temp.put(object);
				if (object.has("children")&&object.get("children") != null) {
					JSONArray children = object.getJSONArray("children");
					if (children != null && children.length() > 0) {
						JSONArray tempChildren = new JSONArray();
						object.put("children", tempChildren);
						filterDelNode(children, tempChildren);
					}
				}
			}

		}
	}

	public String filterDeleteNode(String dirJson) throws Exception {
		int pos = 0;
		StringBuilder json = new StringBuilder();
		while (pos >= 0) {
			int breakPos = dirJson.indexOf("\"__status\":\"delete\"", pos);
			if (breakPos < 0) {
				break;
			}
			int startPos = dirJson.lastIndexOf("[", breakPos);
			if (startPos < 0) {
				throw new Exception("filterDeleteNode error:错误的JSON串");
			}
			int endPos = dirJson.indexOf("}", breakPos);
			if (endPos < 0) {
				throw new Exception("filterDeleteNode error:错误的JSON串");
			}
			endPos++;// }的长度

			json.append(dirJson.substring(pos, startPos));
			pos = endPos;
		}
		json.append(dirJson.substring(pos, dirJson.length()));
		return json.toString();
	}

	@RequestMapping(value = "viewDir")
	@ResponseBody
	public String viewDir(HttpServletRequest request, String code) throws Exception {
		try {
			String dir = this.rtDirManager.getDir(code);
			return dir;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 预览单页
	 * 
	 */
	@RequestMapping(value = "previewSingle")
	@ResponseBody
	public HashMap<String, Object> previewSingle(HttpServletRequest request, String id, String code, String rtCode,String folder,String page) throws Exception {
		try {
			HashMap<String, Object> infoMap = new HashMap<String, Object>();
			String sql = "select item_name name,item_value value from tzsb_report_item_value where fk_inspection_info_id = '" + id + "'";
			String path = this.rtDirManager.previewSingle(id, code, rtCode,sql,infoMap, folder,page);
			return JsonWrapper.successWrapper("data", path);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("解析失败！");
		}
	}

	/**
	 * 查询有标注页码
	 * author pingZhou
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getErrorPage")
	@ResponseBody
	public HashMap<String, Object> getErrorPage(String id){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			String errorPage = this.rtDirManager.getErrorPage(id);
			map.put("errorPage", errorPage);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "查询失败！");
		}
		
		return map;
	}
	
}
