package com.khnt.rtbox.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.pub.codetable.service.CodeTableCache;
import com.khnt.rtbox.config.dao.RtPageDao;

/**
 * @author ZQ
 * 
 */
@Service("rtUI")
public class RtUI {
	@Autowired
	RtPageDao rtPageDao;
	@Autowired
	CodeTableCache codeTableCache;

	/**
	 * 绑定ligerui选项
	 * @param type
	 * @param code
	 * @param tree
	 * @return
	 */
	public String buildData(String type, String code, boolean tree) {
		StringBuilder data = new StringBuilder();
		if ("code".equals(type)) {
			data.append(buildByCode(code, tree));
		} else if ("sql".equals(type)) {
			data.append(buildBySql(code, tree));
		} else if ("data".equals(type)) {
			data.append(code);
		} else {
			data.append(code);
		}

		return data.toString();
	}

	public String buildBySql(String sql, boolean tree) {
		if (tree) {
			return createTreeBySql(sql);
		} else {
			return createBySql(sql);
		}
	}

	public String createTreeBySql(String sql) {
		StringBuilder data = new StringBuilder();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> list = rtPageDao.createSQLQuery(sql).list();
			if (list != null && list.size() > 0) {
				List<Map<String, String>> trimlist = treeDataTrim(list);
				parserTreeData(data, "-1", trimlist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}

	public String createBySql(String sql) {
		StringBuilder data = new StringBuilder();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> list = rtPageDao.createSQLQuery(sql).list();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = list.get(i);
					String id = obj[0].toString();
					String text = obj[1].toString();
					if (data.length() > 0) {
						data.append(",");
					}
					data.append("{text:'" + text + "', id:'" + id + "'}");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}

	private String buildByCode(String code, boolean tree) {
		if (tree) {
			return this.codeTableCache.getTreeCodeTableJSON(code, false).toString();
		} else {
			return this.codeTableCache.getCodeTableJson(code).toString();
		}
	}

	private boolean hasChildren(String id, List<Map<String, String>> list) {
		for (int i = 0, l = list.size(); i < l; i++) {
			Map<String, String> m = list.get(i);
			String pid = m.get("pid");
			if (id.equals(pid)) {
				return true;
			}
		}
		return false;
	}

	private void parserTreeData(StringBuilder sb, String pid, List<Map<String, String>> list) {
		int k = 0;
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> m = list.get(i);
			String id = m.get("id");
			String text = m.get("text");
			String parent = m.get("pid");
			String value = m.get("value");
			if (pid.equals(parent)) {
				if (k != 0) {
					sb.append(",");
				}
				k++;
				sb.append("{");
				sb.append("id:'" + value + "',text:'" + text + "'");
				if (hasChildren(id, list)) {
					sb.append(",children:[");
					parserTreeData(sb, id, list);
					sb.append("]");
				}
				sb.append("}");
			}
		}
	}

	private List<Map<String, String>> treeDataTrim(List<Object[]> list) {
		List<Map<String, String>> val = new ArrayList<Map<String, String>>();
		for (int i = 0, l = list.size(); i < l; i++) {
			Object[] obj = list.get(i);
			String oldId = obj[0].toString();
			String oldText = obj[1].toString();
			String value = obj[3].toString();
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", oldId);
			map.put("text", oldText);
			map.put("value", value);
			if (obj[2] != null) {
				String oldParentId = obj[2].toString();
				if ((!"-1".equals(oldParentId))) {
					boolean found = false;
					for (int k = 0; k < l; k++) {
						Object[] o = list.get(k);
						String id = o[0].toString();
						if (!oldId.equals(id)) {
							if (id.equals(oldParentId)) {
								found = true;
								map.put("pid", oldParentId);
								break;
							}
						}
					}
					if (!found) {
						map.put("pid", "-1");
					}
				} else {
					map.put("pid", "-1");
				}
			} else {
				map.put("pid", "-1");
			}
			val.add(map);
		}
		return val;
	}

	public static void main(String[] args) {
	}
}
