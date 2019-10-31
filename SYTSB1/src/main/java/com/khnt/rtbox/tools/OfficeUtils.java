package com.khnt.rtbox.tools;

/**
 * @author ZQ
 * @version 2016年3月9日 上午10:12:30
 * JACOB WORD转HTML
 */
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.html.SdtToListSdtTagHandler;
import org.docx4j.convert.out.html.SdtWriter;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class OfficeUtils {
	private static Log log = LogFactory.getLog(OfficeUtils.class);
	public static final int WORD_HTML = 8;
	public static final int WORD_TXT = 7;
	public static final int EXCEL_HTML = 44;

	public static void main(String[] args) throws Exception {
		String content = wordToHtml("F:/TOOLS/134工业管道安装安全质量监督检验报告.docx", "f:/tools/1.html", null);
		log.debug(content);
	}

	/**
	 * docx4j DOCX导出HTML
	 * 
	 * @param docfile
	 * @param htmlfile
	 * @param chartset
	 * @return
	 * @throws Exception
	 */
	public static String wordToHtml(String docfile, String htmlfile, String chartset) throws Exception {
		File file = new File(htmlfile);
		File folder = new File(file.getParent());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		WordprocessingMLPackage wordMLPackage = null;
		HTMLSettings htmlSettings = null;
		OutputStream os = null;
		BufferedReader br = null;
		boolean save = true;// 调试阶段建议保存
		try {
			wordMLPackage = Docx4J.load(new java.io.File(docfile));
			htmlSettings = Docx4J.createHTMLSettings();
			htmlSettings.setImageDirPath(htmlfile + "_files");
			htmlSettings.setImageTargetUri(htmlfile.substring(htmlfile.lastIndexOf("/") + 1) + "_files");
			htmlSettings.setWmlPackage(wordMLPackage);
			SdtWriter.registerTagHandler("HTML_ELEMENT", new SdtToListSdtTagHandler());
			if (save) {
				os = new FileOutputStream(file);
			} else {
				os = new ByteArrayOutputStream();
			}
			Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);
			Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

			if (wordMLPackage.getMainDocumentPart().getFontTablePart() != null) {
				wordMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
			}

			if (save) {
				if (chartset == null) {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				} else {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file), chartset));
				}
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			} else {
				String html = ((ByteArrayOutputStream) os).toString();
				return html;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			htmlSettings = null;
			wordMLPackage = null;
			if (os != null) {
				os.close();
			}
			if (br != null) {
				br.close();
			}
			if (save) {
				try {
					// file.delete();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * JACOB DOCX导出HTML
	 * 
	 * @param docfile
	 * @param htmlfile
	 * @param chartset
	 * @return
	 * @throws Exception
	 */
	public static String wordToHtmlByJacob(String docfile, String htmlfile, String chartset) throws Exception {
		File file = new File(htmlfile);
		File folder = new File(file.getParent());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		if(chartset==null){
			chartset="GBK";
		}
		ComThread.InitSTA();
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		BufferedReader br = null;
		try {
			app.setProperty("Visible", new Variant(false));
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] { docfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(WORD_HTML) }, new int[1]);
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), chartset));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) {
				br.close();
			}
			try {
				// file.delete();
			} catch (Exception e) {
			}
			app.invoke("Quit", new Variant[0]);
			ComThread.Release();
			ComThread.quitMainSTA();
		}
	}
}
