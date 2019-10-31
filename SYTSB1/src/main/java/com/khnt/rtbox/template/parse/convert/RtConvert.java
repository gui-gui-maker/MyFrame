package com.khnt.rtbox.template.parse.convert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.khnt.core.exception.KhntException;
import com.khnt.rtbox.config.bean.BaseConfig;
import com.khnt.rtbox.config.bean.PageContent;
import com.khnt.rtbox.config.bean.PageDirVersion;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.dao.RtDirDao;
import com.khnt.rtbox.config.service.PageContentManager;
import com.khnt.rtbox.config.service.PageDirVersionManager;
import com.khnt.rtbox.config.service.RtPageManager;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.constant.RtRunPath;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.rtbox.template.html.RtHtmlParser;
import com.khnt.rtbox.template.model.RtDirectory;
import com.khnt.rtbox.template.parse.page.RtPaging;
import com.khnt.rtbox.tools.BaseUtil;
import com.khnt.rtbox.tools.Utils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年3月16日 下午9:40:26 类说明
 */
public class RtConvert {
	static Log log = LogFactory.getLog(RtConvert.class);
	private RtPaging rtPaging;
	private RtPage rtPage;
	private RtHtmlParser rtHtmlParser;
	public static WebApplicationContext aplicationContext = ContextLoader.getCurrentWebApplicationContext();

	public RtConvert() {
	}

	public RtConvert(RtPage rtPage, RtPaging rtPaging, RtHtmlParser rtHtmlParser) {
		this.rtPage = rtPage;
		this.rtPaging = rtPaging;
		this.rtHtmlParser = rtHtmlParser;
	}

	/**
	 * 将docx转换成HTML
	 * 
	 * @param splits
	 * @throws Exception
	 */
	public void convert() throws Exception {
		log.debug("RtConvert convert...");
		if (rtPage != null) {
			if ("1".equals(rtPage.getIsPage())) {
				//copyIndex();
				pagingCovert();
			} else {
				normalConvert();
			}

			// 保存目录信息到数据库
			saveDir2DB(rtPage);
		} else {
			throw new Exception("convert error :rtPage is null...");
		}

	}

	public void saveDir2DB(RtPage rtPage) throws Exception {
		String rtPageId = rtPage.getId();
		String rtCode = rtPage.getRtCode();
		String rtName = rtPage.getRtName();
		
		// 获取SpringBeanId
		RtDirDao rtDirDao = (RtDirDao) aplicationContext.getBean("rtDirDao");
		//RtPageDao rtPageDao = (RtPageDao) aplicationContext.getBean("rtPageDao");
		RtPageManager rtPageManager = (RtPageManager) aplicationContext.getBean("rtPageManager");
		PageDirVersionManager pageDirVersionManager = (PageDirVersionManager) aplicationContext.getBean("pageDirVersionManager");
		
		
		if (rtDirDao != null && rtPageManager != null  && pageDirVersionManager != null ) {
			if (rtPaging.getSplits() != null && !rtPaging.getSplits().isEmpty()) {
				for (RtDirectory rtD : rtPaging.getSplits()) {
					rtD.setPageContent(null);
				}
			}
			
			String rtDirJson = "[{\"code\":\"root\",\"name\":\"目录\",\"children\":" + Utils.toJsonString(rtPaging.getSplits()) + "}]";
			
			// 保存目录数据
			rtPage.setRtDirJson(rtDirJson);
			rtPageManager.save(rtPage);
			
			// 记录目录数据版本
			PageDirVersion pageDirVersion = new PageDirVersion();
			pageDirVersion.setRtPage(rtPage);
			pageDirVersion.setRtCode(rtCode);
			pageDirVersion.setRtName(rtName);
			pageDirVersion.setBigVersion(rtPage.getVersion());
			pageDirVersion.setVersion(pageDirVersionManager.getMaxVersion(pageDirVersion));//获取最大可用版本号
			pageDirVersion.setRtDirJson(rtDirJson);//目录内容
			pageDirVersionManager.save(pageDirVersion);

			
			// 更新目录
			if(RtSign.STATUS_ENABLE.equals(rtPage.getStatus())) {
				rtDirDao.createSQLQuery("delete from RT_DIR where RT_CODE=?", rtCode).executeUpdate();
				rtDirDao.createSQLQuery("insert into RT_DIR(id,RT_CODE,RT_DIR_JSON) values (?,?,?)", Utils.uuid(), rtCode, rtDirJson).executeUpdate();
			}
		} else {
			throw new Exception("dir save error :" + rtPage.getRtCode());
		}
	}

	/**
	 * 分页首页
	 * 
	 * @param rtPage
	 * @throws Exception
	 */
	private void copyIndex() throws Exception {
		String filePath = RtRunPath.PROJECT_PATH + RtPath.tplPageDir + "default" + File.separator + "tpl01.html";
		String code = rtPage.getRtCode();
		if (code == null) {
			code = "default";
		}
		///20171208 pingZhou 修改，防止以后出现路径问题，取不到报告
		//String pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + "/";
		//String pagePath = rtPage.getPagePath();
		//if (pagePath == null) {
			//pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + "/";
		/*} else {
			pagePath = RtRunPath.PROJECT_PATH + pagePath;
		}*/
		RtTpl rtTpl = new RtTpl();
		String tpl = rtTpl.getTemplateHtml(filePath);//
		tpl = tpl.replace("[$RtPageTitle]", rtPage.getRtName());

		if (StringUtil.isNotEmpty(rtPage.getRelColCode())) {
			StringBuilder relCol = new StringBuilder("<script type=\"text/javascript\"> var relColumn='");
			String[] columns = rtPage.getRelColCode().split(",");
			for (int i = 0; i < columns.length; i++) {
				String column = columns[i];
				if (column != null && column.contains("_")) {
					column = column.toLowerCase();
				}
				if (i != 0) {
					relCol.append("&");
				}
				relCol.append(column).append("=").append("${param." + column + "}");
			}
			relCol.append("';</script>").append("\n");
			tpl = tpl.replace("[$RtPageRelColumn]", relCol.toString());
		} else {
			tpl = tpl.replace("[$RtPageRelColumn]", "");
		}

		// ZMH edit 20190116 兼容保存到数据库
		String rtPageId = rtPage.getId();
		String RtCode = rtPage.getRtCode();
		String RtName = rtPage.getRtName();
		if("1".equals(RtPath.saveDB)) {
			// 保存到库
			PageContentManager pageContentManager = (PageContentManager) aplicationContext.getBean("pageContentManager");
			PageContent pageContent = pageContentManager.findByRtCode(rtPageId, RtSign.REPORT_TEMPLATE_INDEX);
			if(pageContent == null) {
				pageContent = new PageContent();
			}
			pageContent.setRtPage(rtPage);
			pageContent.setRtCode(RtCode);
			pageContent.setRtName(RtName);
			pageContent.setPageType(RtSign.REPORT_TEMPLATE_INDEX);//模版类型
			pageContent.setContent(tpl);//模版内容
			pageContentManager.save(pageContent);
		}else {
			// 保存到磁盘
			//20171208 pingZhou 修改，防止以后出现路径问题，取不到报告
			String pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + File.separator + rtPage.getVersion() + File.separator;

			File file = new File(pagePath + "index.html");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			out.write(tpl);
			out.flush();
			out.close();
			file = null;
		}
	}

	/**
	 * 生成分页类
	 * 
	 * @param tpl
	 * @throws Exception
	 */
	private void pagingCovert() throws Exception {
		RtTpl rtTpl = new RtTpl();
		String code = rtPage.getRtCode();
		if (code == null) {
			code = "default";
		}

		String tpl = "";
		String rtPageId = rtPage.getId();
		String RtCode = rtPage.getRtCode();
		String RtName = rtPage.getRtName();
		if("1".equals(RtPath.saveDB)) {
			PageContentManager pageContentManager = (PageContentManager) aplicationContext.getBean("pageContentManager");
			PageContent pageContent = pageContentManager.findByRtCode(rtPageId, RtSign.REPORT_TEMPLATE_TPL);
			if(tpl == null ) {
				throw new KhntException("保存模版分页时未找到初始模版！");
			}
			tpl = pageContent.getContent();
		}else {
			String filePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + rtPage.getRtCode() + File.separator + rtPage.getVersion() + File.separator + "tpl.html";
			tpl = rtTpl.getTemplateHtml(filePath);//
		}
		tpl = tpl.replace("[$RtPageHead]", rtPaging.getComponent().get("head"));
		tpl = tpl.replace("[$RtPageInitJs]", rtHtmlParser.jsFunsToString());

		 List<BaseConfig> confList = BaseUtil.getConfigSqlList();
		 //by pingZhou 2018-11-6 后期统一处理，不在模板里面体现
		/* StringBuffer buffer = new StringBuffer();
		 buffer.append("<%");
		 buffer.append(" String infoId = request.getParameter(\"info_id\");   ");
		 for (int i = 0; i < confList.size(); i++) {
		 String[] infoIds = confList.get(i).getDataSql().split("infoId");
		 if (infoIds.length > 1) {
		 buffer.append(" String " + confList.get(i).getCode() +
		 confList.get(i).getId() + " = \"" + infoIds[0] + "\"+infoId+\"" +
		 infoIds[1] + "\";  ");
		 } else {
		 buffer.append(" String " + confList.get(i).getCode() +
		 confList.get(i).getId() + " = \"" + confList.get(i).getDataSql() +
		 "\"; ");
		
		 }
		
		 }
		 buffer.append("%>");*/
		 tpl = tpl.replace("[$SqlHead]", "");
		
		//tpl = tpl.replace("[$SqlHead]", "");
		if (StringUtil.isNotEmpty(rtPage.getRelColCode())) {
			StringBuilder relCol = new StringBuilder("<script type=\"text/javascript\"> var relColumn='");
			String[] columns = rtPage.getRelColCode().split(",");
			for (int i = 0; i < columns.length; i++) {
				String column = columns[i];
				if (column != null && column.contains("_")) {
					column = column.toLowerCase();
				}
				if (i != 0) {
					relCol.append("&");
				}
				relCol.append(column).append("=").append("${param." + column + "}");
			}
			relCol.append("';</script>").append("\n");
			tpl = tpl.replace("[$RtPageRelColumn]", relCol.toString());
		} else {
			tpl = tpl.replace("[$RtPageRelColumn]", "");
		}
		List<RtDirectory> bodySplits = rtPaging.getSplits();
		// 按照拆分信息进行页面生成
		for (int i = 1, l = bodySplits.size(); i <= l; i++) {
			RtDirectory rtDir = bodySplits.get(i - 1);
			// 替换BODY值
			String html = tpl.replace("[$RtPageBody]", rtDir.getPageContent());
			// 替换页面下一页值
			if (i == l) {
				html = html.replace("[$RtNextPage]", "-1");
			} else {
				html = html.replace("[$RtNextPage]", (i + 1) + "");
			}
			html = html.replace("[$RtThisPage]", "?page=" + i);
			
			// ZMH add 处理不明符号变成乱码问号
			html = html.replace(" ", "");
			
			// ZMH edit 20190116 兼容保存到数据库
			if("1".equals(RtPath.saveDB)) {
				// 保存到库
				PageContentManager pageContentManager = (PageContentManager) aplicationContext.getBean("pageContentManager");
				PageContent pageContent = pageContentManager.findByRtCode(rtPageId, RtSign.REPORT_TEMPLATE_INDEX + String.valueOf(i));
				if(pageContent == null) {
					pageContent = new PageContent();
				}
				pageContent.setRtPage(rtPage);
				pageContent.setRtCode(RtCode);
				pageContent.setRtName(RtName);
				pageContent.setPageType(RtSign.REPORT_TEMPLATE_INDEX + String.valueOf(i));//模版类型
				pageContent.setContent(html);//模版内容
				pageContentManager.save(pageContent);
			}else {
				String fileName = RtSign.REPORT_TEMPLATE_INDEX + i + ".html";
				rtDir.setPageName(fileName);
				
				///20171208 pingZhou 修改，防止以后出现路径问题，取不到报告
				String pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + File.separator + rtPage.getVersion() + File.separator;
				/*String pagePath = rtPage.getPagePath();
				if (pagePath == null) {
					pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + "/";
					log.debug(pagePath);
				} else {
					pagePath = RtRunPath.PROJECT_PATH + pagePath;
					log.debug(pagePath);
				}*/
				File file = new File(pagePath + fileName);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				out.write(html);
				out.flush();
				out.close();
				file = null;
			}
		}

	}

	private void normalConvert() throws Exception {
		RtTpl rtTpl = new RtTpl();
		String code = rtPage.getRtCode();
		if (code == null) {
			code = "default";
		}
		String tpl = "";
		String rtPageId = rtPage.getId();
		String RtCode = rtPage.getRtCode();
		String RtName = rtPage.getRtName();
		if("1".equals(RtPath.saveDB)) {
			PageContentManager pageContentManager = (PageContentManager) aplicationContext.getBean("pageContentManager");
			PageContent pageContent = pageContentManager.findByRtCode(rtPageId, RtSign.REPORT_TEMPLATE_INDEX);
			tpl = pageContent.getContent();
		}else {
			String filePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + rtPage.getRtCode() + File.separator + rtPage.getVersion() + File.separator + "tpl.html";
			tpl = rtTpl.getTemplateHtml(filePath);//
		}
		tpl = tpl.replace("[$RtPageHead]", rtPaging.getComponent().get("head"));
		tpl = tpl.replace("[$RtPageInitJs]", rtHtmlParser.jsFunsToString());

		List<RtDirectory> bodySplits = rtPaging.getSplits();
		RtDirectory rtDir = bodySplits.get(0);
		// 替换BODY值
		String html = tpl.replace("[$RtPageBody]", rtDir.getPageContent());
		// 替换页面下一页值
		html = html.replace("[$RtNextPage]", "-1");

		// ZMH edit 20190116 兼容保存到数据库
		if("1".equals(RtPath.saveDB)) {
			// 保存到库
			PageContentManager pageContentManager = (PageContentManager) aplicationContext.getBean("pageContentManager");
			PageContent pageContent = pageContentManager.findByRtCode(rtPageId, RtSign.REPORT_TEMPLATE_INDEX);
			if(pageContent == null) {
				pageContent = new PageContent();
			}
			pageContent.setRtPage(rtPage);
			pageContent.setRtCode(RtCode);
			pageContent.setRtName(RtName);
			pageContent.setPageType(RtSign.REPORT_TEMPLATE_INDEX);//模版类型
			pageContent.setContent(tpl);//模版内容
			pageContentManager.save(pageContent);
		}else {
			String fileName = "index.html";
			rtDir.setPageName(fileName);
			///20171208 pingZhou 修改，防止以后出现路径问题，取不到报告
			
			String pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + File.separator + rtPage.getVersion() + File.separator;
			/*String pagePath = rtPage.getPagePath();
			if (pagePath == null) {
				pagePath = RtRunPath.PROJECT_PATH + getFolderPath(rtPage) + code + "/";
				log.debug(pagePath);
			} else {
				pagePath = RtRunPath.PROJECT_PATH + pagePath;
				log.debug(pagePath);
			}*/
			
			File file = new File(pagePath + fileName);
			// System.out.println(pagePath + fileName);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			out.write(html);
			out.flush();
			out.close();
			file = null;
		}
	}

	/**
	 * 根据配置生成模板文件
	 * 
	 * @param rtPage
	 */
	public void generateTpl(RtPage rtPage) {
		// 替换CSS
		// 替换JS
		// 替换getAction
		// 替换saveAction
		// 生成模板
	}
	
	public String getFolderPath(RtPage rtPage) {
		String folder =  RtPath.tplPageDir;
		if("1".equals(rtPage.getModelType())) {
			folder =  RtPath.tplRecordPageDir;
		}
		return folder;
	}
	
}
