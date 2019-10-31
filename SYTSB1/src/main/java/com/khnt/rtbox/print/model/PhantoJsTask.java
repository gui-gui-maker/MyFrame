package com.khnt.rtbox.print.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.khnt.base.Factory;
import com.khnt.rtbox.print.bean.PrintPdfLog;
import com.khnt.rtbox.print.bean.PrintPdfTask;

public class PhantoJsTask implements Callable<PrintPdfLog> {
	public static String CHARSET = "GBK";
	public static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	// JSP打印成PDF的第三方PHANTOMJS脚本
	public static String nodeJsPath = Factory.getSysPara().getProperty("RT_PRINT_PDF", "D:/rtbox/puppeteer/printPdf.js");
	// public static String phantomJsPath =
	// webApplicationContext.getServletContext().getRealPath("/").replace("\\",
	// "/")
	// + "rtbox/print/components/phantomjs/printPdf.js";

	private PrintPdfTask printPdfTask;
	private String pageNo;// 需要打印的JSP的页码编号
	private String url; // 需要打印的JSP URL
	private String savePath; // 单页PDF存放地
	private Vector<String> inputs;
	private Vector<String> errors;
	private String printBatch;// 打印批次

	public PhantoJsTask(PrintPdfTask printPdfTask, String pageNo, String url, String savePath, String printBatch) {
		this.printPdfTask = printPdfTask;
		this.pageNo = pageNo;
		this.url = url;
		this.savePath = savePath;
		this.printBatch = printBatch;
		this.inputs = new Vector<String>();
		this.errors = new Vector<String>();

	}

	static Log log = LogFactory.getLog(PhantoJsTask.class);

	/**
	 * 根据打印任务调用操作系统命令执行打印
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	public PrintPdfLog printPdf() throws Exception {
		log.info("printPdf begin ......");
		String os = System.getProperties().getProperty("os.name");
		// int screenWidth =
		// java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		// int screenHeight =
		// java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		String cmd = "node " + nodeJsPath + " " + this.url.replaceAll("&", "^&") + " " + this.savePath;
		if (os != null && os.toLowerCase().indexOf("linux") > -1) {

		} else {
			cmd = "cmd /c " + cmd;
		}
		final Process process = Runtime.getRuntime().exec(cmd);
		printMessage(process.getInputStream(), "input", inputs);
		printMessage(process.getErrorStream(), "error", errors);
		int value = process.waitFor();
		PrintPdfLog printPdfLog = log(value);
		log.info("printPdf cmd end:" + value);

		System.out.println("printPdf cmd end:" + value);
		File file = new File(this.savePath);
		if (!file.exists()) {
			printPdfLog.setResult("-1");
			printPdfLog.setErrorType("uncreate");
			// ZQ EDIT 20180827
			// this.save(printPdfLog);
			// throw new KhntException("未生成PDF文件:" + this.pageNo);
		}

		// this.save(printPdfLog);
		return printPdfLog;
	}

	private PrintPdfLog log(int value) {
		PrintPdfLog printPdfLog = new PrintPdfLog();
		printPdfLog.setFkInspectionId(this.printPdfTask.getFkInspectionInfoId());
		printPdfLog.setPrintPdfTask(this.printPdfTask);
		printPdfLog.setReportSn(this.printPdfTask.getReportSn());
		printPdfLog.setPageNo(this.pageNo);
		printPdfLog.setPageUrl(this.url);
		printPdfLog.setPagePath(this.savePath);
		printPdfLog.setResult(String.valueOf(value));
		// printPdfLog.setErrorType(null);
		String inputLog = listToString(this.inputs);
		String errorLog = listToString(this.errors);
		if (!this.inputs.isEmpty()) {
			printPdfLog.setLog(inputLog);
		}
		if (!this.errors.isEmpty()) {
			printPdfLog.setLog(
					printPdfLog.getLog() == null ? errorLog : printPdfLog.getLog() + "=====分割线======" + errorLog);
		}

		printPdfLog.setPrintTime(new Date());
		printPdfLog.setPrintBatch(printBatch);
		return printPdfLog;
	}

	private String listToString(Vector<String> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String pageName = list.get(i);
			sb.append(pageName).append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	private void printMessage(final InputStream input, final String preFix, final Vector<String> handle) {
		new Thread(new Runnable() {
			public void run() {
				Reader reader = null;
				try {
					reader = new InputStreamReader(input, CHARSET);
					BufferedReader bf = new BufferedReader(reader);
					String line = null;
					try {
						while ((line = bf.readLine()) != null) {
							log.info(preFix + ":" + line);
							System.out.println("preFix:" + line);
							handle.add(preFix + ":" + line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

			}
		}).start();
	}

	public PrintPdfTask getPrintPdfTask() {
		return printPdfTask;
	}

	public void setPrintPdfTask(PrintPdfTask printPdfTask) {
		this.printPdfTask = printPdfTask;
	}

	public Vector<String> getInputs() {
		return inputs;
	}

	public void setInputs(Vector<String> inputs) {
		this.inputs = inputs;
	}

	public Vector<String> getErrors() {
		return errors;
	}

	public void setErrors(Vector<String> errors) {
		this.errors = errors;
	}

	@Override
	public PrintPdfLog call() throws Exception {
		PrintPdfLog printPdfLog = this.printPdf();
		return printPdfLog;
	}
}
