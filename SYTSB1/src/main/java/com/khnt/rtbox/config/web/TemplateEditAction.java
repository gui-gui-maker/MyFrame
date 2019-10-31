package com.khnt.rtbox.config.web;

import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rtbox.config.service.RtPageManager;
import com.khnt.utils.FileUtil;
import com.khnt.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Controller
@RequestMapping("rtbox/sourceEdit/")
public class TemplateEditAction {
	
	@Autowired
	private RtPageManager rtPageManager;
	
	@RequestMapping("ajaxRquest")
	@ResponseBody
	public Map<String, Object> ajaxRequest(HttpServletRequest request, String filePath) throws Exception {
		System.out.println(filePath);
		return JsonWrapper.successWrapper(this.readFile(request, filePath, null));
	}

	@RequestMapping("preview")
	public void preview(HttpServletRequest request, HttpServletResponse response, ModelMap map, String filePath, String fileContent) throws Exception {
		// 文件方式
		/*if(StringUtil.isEmpty(fileContent)) {
			fileContent = this.readFile(request, filePath, null);
		}
		fileContent += "<script type=\"text/javascript\">$(function(){$('input').focus(function(){if(this.id)parent.findInputLine(this.id,false);if(this.name)parent.findInputLine(this.name,false);});});function onDocKeydown(e){e=e||window.event;if(e.ctrlKey&&e.keyCode==83){parent.save();return false;}}document.onkeydown=onDocKeydown;</script>";
		filePath = "rtbox/app/sourceEdit/tpl_preview.jsp";
		this.writeFile(request, filePath, fileContent);
		return "rtbox/app/sourceEdit/tpl_preview";
		*/
		
		// 字符串方式
		String pageContent = "无数据";
		String rtPageId = request.getParameter("rtPageId");
		String pageCode = request.getParameter("pageCode");
		
		
		if(StringUtil.isEmpty(fileContent)) {
			pageContent = rtPageManager.getPageContent(request, rtPageId, pageCode,null);
		}else {
			pageContent = fileContent;
		}
		pageContent += "<script type='text/javascript' src='../public/jQuery/jquery-1.10.2.js'></script>";
		pageContent += "<script type=\"text/javascript\">$(function(){$('input').focus(function(){if(this.id)parent.findInputLine(this.id,false);if(this.name)parent.findInputLine(this.name,false);});});function onDocKeydown(e){e=e||window.event;if(e.ctrlKey&&e.keyCode==83){parent.save();return false;}}document.onkeydown=onDocKeydown;</script>";
		response.getWriter().write(pageContent);
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, String filePath, String fileContent) throws Exception {
		try {
			this.writeFile(request, filePath, fileContent);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper();
		}
	}
	
	@RequestMapping("download")
	public void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws Exception {
		String fullPath = this.getFileFullPath(request, filePath);
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1 ,filePath.length());
		FileUtil.download(response, fullPath, fileName, null);
	}
	
	private String readFile(HttpServletRequest request, String filePath, String fileContent) throws Exception {
		if(StringUtil.isNotEmpty(fileContent)) {
			return fileContent;
		}
		String str = "";
		String line = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String fullPath = this.getFileFullPath(request, filePath);
		    fis = new FileInputStream(fullPath);
		    isr = new InputStreamReader(fis, "UTF-8");
		    br = new BufferedReader(isr);
		    while ((line = br.readLine()) != null) {
		        str += line + "\n";
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    br.close();
		    isr.close();
		    fis.close();
		}
		return str;
	}
	
	private void writeFile(HttpServletRequest request, String filePath, String fileContent) throws Exception {
		String fullPath = this.getFileFullPath(request, filePath);
		try {
            FileOutputStream fos = new FileOutputStream(fullPath);
            fos.write(fileContent.getBytes());
            fos.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private String getFileFullPath(HttpServletRequest request, String filePath) throws Exception {
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		String myB = application.getRealPath("/");
		String fullPath = myB + filePath;
		return fullPath;
	}
}
