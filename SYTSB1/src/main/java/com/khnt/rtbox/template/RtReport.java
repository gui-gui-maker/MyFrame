package com.khnt.rtbox.template;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.csource.common.NameValuePair;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.R;

import com.khnt.base.Factory;
import com.khnt.common.utils.FastDFSUtil;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.bean.RtPersonDir;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.html.RtHtmlParser;
import com.khnt.rtbox.template.parse.convert.RtConvert;
import com.khnt.rtbox.template.parse.page.RtPaging;
import com.khnt.rtbox.template.parse.tag.impl.DocxTagImpl;
import com.khnt.rtbox.template.revert.RtRevertRound;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.rtbox.tools.MD5Utils;
import com.khnt.rtbox.tools.OfficeUtils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2017年3月9日 上午9:06:05 类说明
 */
public class RtReport {
	Log log = LogFactory.getLog(RtReport.class);
	private String templeteXmlFilePath;
	private String templeteDocFilePath;
	private String outputJspFilePath;// html输出地

	public RtReport() {
	}

	public RtReport(String templeteXmlFilePath, String outputDocFilePath) {
		this.templeteXmlFilePath = templeteXmlFilePath;
		this.templeteDocFilePath = outputDocFilePath;
	}

	/**
	 * 
	 * @param _docxFile
	 * @param rtPage
	 * @throws Exception
	 */
	public void docxToJsp(String _docxFile, RtPage rtPage) throws Exception {
		String name = rtPage.getId();
		if (rtPage != null) {
			name = rtPage.getId();
		}
		log.debug("1.载入DOCX文件...");
		WordprocessingMLPackage wordMLPackage = new WordprocessingMLPackage();
		
		// ZMH edit 20190109 增加存储到文件独立服务器判断
		String attachmentPosition = Factory.getSysPara().getProperty("attachmentPosition", "local");
		if("local".equals(attachmentPosition)) {
			File docxFile = new File(_docxFile);
			if (StringUtil.isEmpty(name)) {
				name = MD5Utils.getMD5(docxFile, "sha-1");
			}
			wordMLPackage = WordprocessingMLPackage.load(docxFile);
		}else{
			InputStream fastFile = FastDFSUtil.downloadInputStream(_docxFile);
			wordMLPackage = WordprocessingMLPackage.load(fastFile);
			name = MD5Utils.getMD5(_docxFile, "sha-1");
		}
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		
		log.debug("2.对XML进行自动标记...");
		DocxTagImpl docxTag = new DocxTagImpl();
		docxTag.tag(documentPart, rtPage);

		RtPaging paging = new RtPaging();
		if (docxTag.isBlank) {
			log.debug("2.1 将XML进行分页标记...");
			paging.initPaging(documentPart);
		}
		log.debug("3.保存XML分页信息...");
		// 直接通过字符串分页符判定进行分页XML存储
		paging.mainBreak(documentPart, rtPage);

		log.debug("4.保存已标记DOCX文档...");
		// ZMH edit 20190109 增加存储到文件独立服务器判断
		File docFile = null;
		File htmlFile = null;
		String filePath = "";
		String tmpPath = System.getProperty("java.io.tmpdir");// 临时路径，文件独立存储时使用
		if("local".equals(attachmentPosition)) {
			filePath = templeteDocFilePath + name + ".docx";// DOC模板存储路径
			docFile = new File(RtPath.basePath + filePath);
			htmlFile = new File(RtPath.basePath + templeteDocFilePath + name + ".html");
		}else{
			docFile = new File(tmpPath + File.separator + name + ".docx");
			htmlFile = new File(tmpPath + File.separator + name + ".html");
		}
		if (!docFile.getParentFile().exists()) {
			docFile.getParentFile().mkdirs();
		}

		wordMLPackage.save(docFile);
		NameValuePair[] valuePairs = new NameValuePair[3];
		valuePairs[0] = new NameValuePair("fileName", name);
		valuePairs[1] = new NameValuePair("fileLength", String.valueOf(docFile.length()));
		valuePairs[2] = new NameValuePair("fileExt", "docx");
		if(!"local".equals(attachmentPosition)) filePath = FastDFSUtil.upload(docFile,valuePairs);// 若为文件独立存储，保存返回后路径
		rtPage.setTempleteDocFilePath(filePath);// DOC模板存储路径
		documentPart = null;

		log.debug("5.通过DOCX4J ，将标记过的DOCX转为HTML...");
		String content = OfficeUtils.wordToHtml(docFile.getPath(),htmlFile.getPath(), "UTF-8");
		/*if ("1".equals(rtPage.getModelType())) {
			content = HtmlUtils.cleanSpecifyTagStyle(content, "table,th,td,p,span".split(","));
		}*/
		// String content = OfficeUtils.wordToHtmlByJacob(templeteDocFilePath +
		// name + ".docx", RtPath.outputHtmlPath + name + ".html", null);
		
		// ZQ add 20171204
		log.debug("5.1 替换 自闭合A标签 <a name=\"xxx\"/>， 解决浏览器无法识别问题...");
		content = HtmlUtils.removeSelfClosingTag(content, "a");
		// ZQ add 20180904
		log.debug("5.2 替换 page-break-before: always;， 解决pupeteer打印PDF报告空白页问题...");
		content = content.replace("page-break-before: always;", "");

		log.debug("6.解析HTML，DOCX中标记解析为INPUT...");
		RtHtmlParser htmlParser = new RtHtmlParser();
		content = htmlParser.parse(content, rtPage);
		System.out.println(content);
		log.debug("7.解析HTML，根据步骤3XML分页信息进行分页采集...");
		paging.checkSplit(content, rtPage); // 根据XML分页标记对HTML进行分页

		log.debug("8.解析HTML，分页存储为HTML...");
		RtConvert convert = new RtConvert(rtPage, paging, htmlParser);
		convert.convert();

		log.debug("9.文档解析完成...");
		// ZMH add 20190115 DOCX转换完成后删除html。若为文件独立存储，删除DOCX文件。
		if(!"local".equals(attachmentPosition)) docFile.delete();
		htmlFile.delete();
		
	}

	/**
	 * 根据DB值，导出DOCX
	 * 
	 * @param infoMap
	 * @param view
	 * 
	 * @param dataMap
	 * 
	 * @throws Exception
	 */
	public void jspToDocx(RtPage rtPage, RtPersonDir rpd, Map<String, RtExportData> transMap,
			HashMap<String, Object> infoMap) throws Exception {
		log.debug("1.准备模板DOCX文件...");
		WordprocessingMLPackage wordMLPackage = null;
		// 每条业务的docx模板文件地址
		String outputExportPath = null;
		if (infoMap.get("docpath") != null) {
			outputExportPath = infoMap.get("docpath").toString();
		}
		if (outputExportPath == null || !new File(outputExportPath).exists()) {
			if (rpd != null) {
				outputExportPath = RtPath.basePath + RtPath.getExportPath(rpd);
			} else {
				outputExportPath = RtPath.basePath + rtPage.getTempleteDocFilePath();
			}

		}

		File docxFile = new File(outputExportPath);
		if (!docxFile.exists()) {
			// 如果用户没有修改过目录，加载原模板
			if (StringUtil.isEmpty(rtPage.getTempleteDocFilePath())) {
				throw new Exception("未找到导出模板...");
			}
			File templeteDocFile = new File(RtPath.basePath + rtPage.getTempleteDocFilePath());
			if (!templeteDocFile.exists()) {
				throw new Exception("未找到导出模板...");
			}
			wordMLPackage = WordprocessingMLPackage.load(templeteDocFile);

		} else {
			wordMLPackage = WordprocessingMLPackage.load(docxFile);
		}
		log.debug("2.开始替换$标记...");
		RtRevertRound rtRevertRound = new RtRevertRound();
		if (infoMap.get("path") != null && StringUtil.isNotEmpty(infoMap.get("path").toString())) {
			rtRevertRound.replaceTag(rtPage, transMap, wordMLPackage, infoMap, "single");

		} else {
			rtRevertRound.replaceTag(rtPage, transMap, wordMLPackage, infoMap, null);

		}

		log.debug("2.1 .开始清理rels中无用标记...");
		// RtHyperlink.clearRtLink(documentPart);

		log.debug("2.2 .文档如果带图片，开始整理conentType...");

		log.debug("2.3 回写docx文档");

		String filePath = "";// RtPath.basePath + rtPage.getOutPutDocDirPath() +
								// infoMap.get("report_sn").toString() +
								// ".docx";

		if (infoMap.get("path") != null && StringUtil.isNotEmpty(infoMap.get("path").toString())) {

			filePath = infoMap.get("path").toString();

		}

		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		wordMLPackage.save(file);

	}

	public String getTempleteXmlFilePath() {
		return templeteXmlFilePath;
	}

	public void setTempleteXmlFilePath(String templeteXmlFilePath) {
		this.templeteXmlFilePath = templeteXmlFilePath;
	}

	public String getOutputDocFilePath() {
		return templeteDocFilePath;
	}

	public void setOutputDocFilePath(String outputDocFilePath) {
		this.templeteDocFilePath = outputDocFilePath;
	}

	public String getOutputHtmlFilePath() {
		return outputJspFilePath;
	}

	public void setOutputHtmlFilePath(String outputHtmlFilePath) {
		this.outputJspFilePath = outputHtmlFilePath;
	}

	public static void main(String[] args) {
		try {
			// String _docxFile =
			// "C:/Users/Administrator/Desktop/分页/114工业锅炉锅炉外部检验报告.docx";
			// String templeteXmlFilePath = RtPath.templeteXmlPath +
			// Utils.monthDir() + "/";// 模板存放地
			// String templeteDocFilePath = RtPath.templeteDocPath +
			// Utils.monthDir() + "/";// word输出地
			// // 解析
			// RtReport parser = new RtReport(templeteXmlFilePath,
			// templeteDocFilePath);
			// RtPage rtPage = new RtPage();
			// rtPage.setRtCode("default");
			// rtPage.setId("test");
			// parser.docxToJsp(_docxFile, rtPage);
			// System.out.println("templeteDocFilePath:" + templeteDocFilePath);
			String _docxFile = "D:\\rtbox\\templete\\word\\201703\\402880c45a10e67d015a112f88be0008.docx";
			String _docxFile1 = "D:\\rtbox\\templete\\word\\201703\\1111.docx";
			File docxFile = new File(_docxFile);
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(docxFile);
			MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
			List<Object> rs = Docx4jUtil.getAllElementFromObject(documentPart, Hyperlink.class);
			for (Object obj : rs) {
				Hyperlink hyperlink = (Hyperlink) obj;
				hyperlink.getContent().get(0);
				P p = (P) hyperlink.getParent();
				R r = (R) hyperlink.getContent().get(0);
				p.getContent().clear();
				int idx = p.getContent().indexOf(hyperlink);
				if (idx >= 0) {
					p.getContent().add(idx, r);
					p.getContent().remove(hyperlink);
				}
				// else {
				// throw new Exception("位置错误...");
				// }
				System.out.println(Docx4jUtil.getElementContent(hyperlink));
			}
			wordMLPackage.save(new File(_docxFile1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
