package com.khnt.bpm.ext.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.khnt.bpm.ext.bean.FlowDefinition;
import com.khnt.bpm.ext.service.FlowDefinitionManager;
import com.khnt.bpm.ext.service.FlowFunctionManager;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.utils.FileUtil;
import com.khnt.utils.ZipCompress;

/******
 * 
 * 流程定义控制类
 * 
 * @author
 * @date 2012-2-7 下午03:14:03
 */
@Controller
@RequestMapping("/bpm/definition/")
public class FlowDefinitionAction extends SpringSupportAction<FlowDefinition, FlowDefinitionManager> {

	@Autowired
	FlowDefinitionManager flowDefinitionManager;

	@Autowired
	FlowFunctionManager flowFunctionManager;

	/**
	 * 新增流程
	 * 
	 * @return
	 */
	@RequestMapping(value = "create")
	public String create(HttpServletRequest request, String flowType) throws Exception {
		String functions = flowFunctionManager.getFunctions(flowType);
		request.setAttribute("functions", functions);
		return "pub/bpm/flexflow/JbpmWorkFlow";
	}

	/**
	 * 保存流程
	 * 
	 * @param id
	 * @param flowxml
	 * @param flowTypeID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveFlow")
	@ResponseBody
	public String save(String id, String flowxml, String flowTypeID) throws Exception {
		flowDefinitionManager.save(id, flowxml, flowTypeID);
		return "{\"success\":true}";
	}

	/**
	 * 导入流程定义
	 * 
	 * @param request
	 * @param typeId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("import")
	@ResponseBody
	public String doImport(HttpServletRequest request, String typeId) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		if (files.size() > 0) {
			Iterator<String> fileNames = multipartRequest.getFileNames();
			String fileName = (String) fileNames.next();
			CommonsMultipartFile file = (CommonsMultipartFile) files.get(fileName);
			String tmpFile = System.getProperty("java.io.tmpdir") + File.separator + new Date().getTime() + ".zip";
			FileUtil.writeFile(tmpFile, file.getBytes());
			this.flowDefinitionManager.doImport(tmpFile, typeId);
		}
		return "{\"success\":true}";
	}

	/**
	 * 导出流程定义
	 * 
	 * @param request
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("export")
	public void doExport(HttpServletResponse response, String ids) throws Exception {
		String[] idArr = ids.split(",");
		String tmpdir = System.getProperty("java.io.tmpdir");
		String zipDir = tmpdir + "bpm_export" + new Date().getTime() + File.separator;
		new File(zipDir).mkdir();
		for (String id : idArr) {
			FlowDefinition def = this.flowDefinitionManager.get(id);
			FileUtil.writeFile(zipDir + def.getId() + ".xml", def.getFlowxml(), "utf-8");
		}

		String fileName = "工作流定义备份(" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ").zip";
		String zipFile = tmpdir + File.separator + fileName;
		ZipCompress.zipFileWidthApacheZip(zipDir, zipFile, false);
		FileUtil.download(response, zipFile, fileName, "application/zip");

		// 删除临时文件
		FileUtil.delAllFile(zipFile);
		FileUtil.delAllFile(zipDir);
	}

	/**
	 * 流程修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "modifyFlow")
	public String modify(HttpServletRequest request, String id) throws Exception {
		FlowDefinition flowDefinition = flowDefinitionManager.get(id);
		String flowxml = flowDefinitionManager.flowXmlEnter(flowDefinition.getFlowxml());
		FlowDefinition fd = new FlowDefinition();
		fd.setFlowxml(flowxml);
		fd.setId(flowDefinition.getId());
		fd.setFlowname(flowDefinition.getFlowname());
		request.setAttribute("flowDefinition", fd);
		String functions = flowFunctionManager.getFunctions(flowDefinition.getFlowtype());
		request.setAttribute("functions", functions);
		return "pub/bpm/flexflow/JbpmWorkFlow";
	}

	/**
	 * 流程复制
	 * 
	 * @return
	 */
	@RequestMapping(value = "copyFlow")
	@ResponseBody
	public Map<String, Object> copyDefinition(String id, int copies, String type) throws Exception {
		flowDefinitionManager.copyDefinition(id, copies, type);
		return JsonWrapper.successWrapper();
	}

	/**
	 * 流程查看
	 * 
	 * @return
	 */
	@RequestMapping(value = "detailFlow")
	public String detailFlow(HttpServletRequest request, String id) throws Exception {
		FlowDefinition flowDefinition = flowDefinitionManager.get(id);
		// 获取流程定义XML
		String flowxml = flowDefinitionManager.flowXmlEnter(flowDefinition.getFlowxml());
		flowDefinition.setFlowxml(flowxml);
		request.setAttribute("flowxml", flowxml);
		request.setAttribute("flowDefinition", flowDefinition);
		return "pub/bpm/flexflow/FlowTrask";
	}

	/**
	 * 流程发布
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "flowIssue")
	@ResponseBody
	public Map<String, Object> flowIssue(String ids) throws Exception {
		flowDefinitionManager.flowIssue(ids.split(","), FlowDefinitionManager.FLOW_STATE_ISSUE);
		return JsonWrapper.successWrapper();
	}

	/**
	 * 流程取消发布
	 */
	@RequestMapping(value = "flowUnIssue")
	@ResponseBody
	public Map<String, Object> flowUnIssue(String ids) throws Exception {
		flowDefinitionManager.flowIssue(ids.split(","), FlowDefinitionManager.FLOW_STATE_UN_ISSUE);
		return JsonWrapper.successWrapper();
	}
}
