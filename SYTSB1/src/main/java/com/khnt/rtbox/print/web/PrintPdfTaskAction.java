package com.khnt.rtbox.print.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.rtbox.print.bean.PrintPdfTask;
import com.khnt.rtbox.print.service.PrintPdfTaskManager;
import com.khnt.rtbox.print.service.PrintPdfTaskTryAgainManager;

/**
 * <p>
 * 类说明
 * </p>
 * 
 * @ClassName PrintPdfTask
 * @JDK 1.7
 * @author CODER_V3.0
 * @date 2018-07-27 14:02:49
 */
@Controller
@RequestMapping("com/rt/printPdfTask")
public class PrintPdfTaskAction extends SpringSupportAction<PrintPdfTask, PrintPdfTaskManager> {

	@Autowired
	private PrintPdfTaskManager printPdfTaskManager;
	@Autowired
	PrintPdfTaskTryAgainManager printPdfTaskTryAgainManager;
	

	@RequestMapping("saveTask")
	@ResponseBody
	private HashMap<String, Object> saveTask(HttpServletRequest request, String id) {
		try {
			id = request.getParameter("fkInspectionInfoId");// 402880fe6462d3a2016463cb33100093

			String infoNo = request.getParameter("reportSn");// 000055
			String fkReportId = request.getParameter("reportTplId");// 402880485eb6aa8d015eb79f40bb0002
			String reportTplCode = request.getParameter("reportTplNo");// XCTS_YLRQDQ2017
			// id = "402880fe6462d3a2016463cb33100093";
			// infoNo = "000055";//
			// fkReportId = "402880485eb6aa8d015eb79f40bb0002";//
			// reportTplCode = "XCTS_YLRQDQ2017";// XCTS_YLRQDQ2017

			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				this.printPdfTaskManager.saveTask(ids[i], request);
			}
			
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	@RequestMapping("tryAgain")
	@ResponseBody
	private HashMap<String, Object> tryAgain(HttpServletRequest request) {
		try {
			this.printPdfTaskTryAgainManager.doPrint();

			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	@RequestMapping("printTaskId")
	@ResponseBody
	private HashMap<String, Object> printTaskId(HttpServletRequest request, String id) {
		try {
			this.printPdfTaskManager.printTaskId(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}


	/**
	 * 打印原始记录pdf
	 * author pingZhou
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("saveRecordTask")
	@ResponseBody
	private HashMap<String, Object> saveRecordTask(HttpServletRequest request, String id) {
		try {
			id = request.getParameter("fkInspectionInfoId");// 402880fe6462d3a2016463cb33100093

			String infoNo = request.getParameter("reportSn");// 000055
			String fkReportId = request.getParameter("reportTplId");// 402880485eb6aa8d015eb79f40bb0002
			String reportTplCode = request.getParameter("reportTplNo");// XCTS_YLRQDQ2017
			// id = "402880fe6462d3a2016463cb33100093";
			// infoNo = "000055";//
			// fkReportId = "402880485eb6aa8d015eb79f40bb0002";//
			// reportTplCode = "XCTS_YLRQDQ2017";// XCTS_YLRQDQ2017

			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				this.printPdfTaskManager.saveRecordTask(ids[i], request);
			}

			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	
	
	
}
