package com.khnt.bpm.ext.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.bpm.core.bean.Activity;
import com.khnt.bpm.ext.bean.FlowDefinition;
import com.khnt.bpm.ext.dao.FlowDefinitionDao;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.StringUtil;
import com.khnt.utils.Zip;

@Service("flowDefinitionManager")
public class FlowDefinitionManager extends EntityManageImpl<FlowDefinition, FlowDefinitionDao> {

	@Autowired
	FlowDefinitionDao flowDefinitionDao;

	public static final String FLOW_STATE_ISSUE = "1";

	public static final String FLOW_STATE_UN_ISSUE = "0";

	/**
	 * 流程保存
	 * 
	 * @param id
	 * @param flowxml
	 * @param flowxml
	 * @throws flowTypeID
	 */
	public void save(String id, String flowxml, String flowTypeID) throws Exception {
		FlowDefinition flowDefinition;
		if (StringUtil.isEmpty(id)) {
			flowDefinition = new FlowDefinition();
			flowDefinition.setState(FLOW_STATE_UN_ISSUE);
			flowDefinition.setFlowtype(flowTypeID);
		} else {
			flowDefinition = flowDefinitionDao.get(id);
		}
		InputStream inputStream = new ByteArrayInputStream(flowxml.getBytes("UTF-8"));
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(inputStream);
		Element rootProcess = doc.getRootElement();
		flowDefinition.setRemark(rootProcess.attributeValue("remark"));
		flowDefinition.setFlowname(rootProcess.attributeValue("name"));
		flowDefinition.setSort(rootProcess.attributeValue("sort"));
		flowDefinition.setFlowxml(flowxml);
		flowDefinitionDao.save(flowDefinition);
	}

	/**
	 * 流程发布，和取消发布
	 * 
	 * @param id
	 * @param flg
	 *            1:发布 0：取消发布
	 * @throws Exception
	 */
	public void flowIssue(String[] ids, String state) throws Exception {
		for (String id : ids) {
			FlowDefinition flowDefinition = flowDefinitionDao.get(id);
			flowDefinition.setState(state);
			flowDefinitionDao.save(flowDefinition);
		}
	}

	/**
	 * 去掉流程中的回车
	 * 
	 * @param flowxml
	 * @throws Exception
	 */
	public String flowXmlEnter(String flowxml) throws Exception {
		if (StringUtil.isEmpty(flowxml))
			return "";
		// 查看系统中字符串的回车符是什么
		Pattern p = Pattern.compile("\r|\n");
		Matcher m = p.matcher(flowxml);
		String after = m.replaceAll("");
		return after;
	}

	/**
	 * 复制流程定义
	 * 
	 * @param id
	 *            要复制的流程定义ID
	 * @param copies
	 *            复制份数
	 * @throws Exception
	 */
	public void copyDefinition(String id, int copies, String type) throws Exception {
		if (copies < 1)
			return;
		int i = copies;
		FlowDefinition definition = this.flowDefinitionDao.get(id);
		while (--i >= 0) {
			FlowDefinition copy = new FlowDefinition();
			BeanUtils.copyProperties(copy, definition);
			copy.setId(null);
			copy.setFlowname("【复制】" + copy.getFlowname());
			copy.setState(FLOW_STATE_UN_ISSUE);
			if (!StringUtil.isEmpty(type))
				copy.setFlowtype(type);
			this.flowDefinitionDao.save(copy);
		}
	}

	/**
	 * 导入备份文件
	 * 
	 * @param zipFile
	 * @throws Exception
	 */
	public void doImport(String zipFile, String typeId) throws Exception {
		String tmpdir = System.getProperty("java.io.tmpdir");
		String unzipDir = tmpdir + File.separator + "bpm_import" + new Date().getTime();
		File fileDir = Zip.unzip(zipFile, unzipDir);

		// 解压完成，开始读取文件
		File[] fs = fileDir.listFiles();
		for (File file : fs) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			StringBuilder xmls = new StringBuilder();
			while (true) {
				String lstr = reader.readLine();
				if (lstr == null)
					break;
				xmls.append(lstr);
			}
			reader.close();
			this.save(null, xmls.toString(), typeId);
		}
	}

	@SuppressWarnings("unchecked")
	public Activity parseFlowFirstActivity(String flowId) throws Exception {
		FlowDefinition flowDefinition = this.flowDefinitionDao.get(flowId);

		InputStream inputStream = new ByteArrayInputStream(flowDefinition.getFlowxml().getBytes("UTF-8"));
		SAXReader saxReader = new SAXReader();
		Document xmlDoc = saxReader.read(inputStream);
		Element processElement = xmlDoc.getRootElement();
		List<Element> els = processElement.elements("start");
		if (els.size() != 1)
			throw new KhntException("流程定义错误，无法解析");
		Element start = els.get(0);
		els = start.elements("transition");
		if (els.size() != 1)
			throw new KhntException("流程定义错误，无法解析");
		String firstId = els.get(0).attributeValue("toId");
		els = processElement.elements("task");
		Element first = null;
		for (Element te : els) {
			if (firstId.equals(te.attributeValue("id"))) {
				first = te;
				break;
			}
		}
		if (first == null)
			throw new KhntException("流程定义错误，无法解析");
		Activity a = new Activity();
		a.setActivityId(first.attributeValue("id"));
		a.setFunction(first.attributeValue("functions"));
		return a;
	}
}
