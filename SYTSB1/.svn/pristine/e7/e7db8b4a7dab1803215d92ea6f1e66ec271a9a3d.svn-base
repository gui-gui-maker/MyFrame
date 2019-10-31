package com.khnt.rtbox.config.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.rtbox.config.bean.PageContent;
import com.khnt.rtbox.config.bean.PageContentRecord;
import com.khnt.rtbox.config.bean.ReportMark;
import com.khnt.rtbox.config.bean.RtDir;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.config.bean.RtPersonDir;
import com.khnt.rtbox.config.bean.Template;
import com.khnt.rtbox.config.dao.PageContentDao;
import com.khnt.rtbox.config.dao.PageContentRecordDao;
import com.khnt.rtbox.config.dao.ReportMarkDao;
import com.khnt.rtbox.config.dao.RtDirDao;
import com.khnt.rtbox.config.dao.RtPageDao;
import com.khnt.rtbox.config.dao.TemplateDao;
import com.khnt.rtbox.template.RtReport;
import com.khnt.rtbox.template.constant.RtExportDataType;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.constant.RtRunPath;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.rtbox.template.parse.convert.RtTpl;
import com.khnt.rtbox.tools.FileUtils;
import com.khnt.rtbox.tools.Utils;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.inspection.bean.InspectionInfo;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName RtPageManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2016-03-22 09:51:04
 */
@Service("rtPageManager")
public class RtPageManager extends EntityManageImpl<RtPage, RtPageDao> {
	@Autowired
	RtPageDao rtPageDao;
	@Autowired
	AttachmentManager attachmentManager;
	@Autowired
	RtDirManager rtDirManager;
	@Autowired
	private ReportMarkDao reportMarkDao;
	@Autowired
	private RtDirDao rtDirDao;
	@Autowired
	private PageContentDao pageContentDao;
	@Autowired
	private PageContentRecordDao pageContentRecordDao;
	@Autowired
	private PageContentManager pageContentManager;
	@Autowired
	TemplateDao templateDao;
	@Autowired
	private TemplateManager templateManager;
	
	@Override
	public void save(RtPage entity) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		entity.setCreateTime(new Date());
		if(StringUtil.isEmpty(entity.getId())) {
			entity.setCreatedBy(user.getName());// 操作人名称
			entity.setCreatedById(user.getId());// 操作人ID
			entity.setCreatedDate(new Date());// 操作时间
			entity.setVersion(rtPageDao.getMaxVersion(entity));// 获取版本号
			if(entity.getVersion() == 1) {
				entity.setStatus(RtSign.STATUS_ENABLE);// 第一个版本默认启用
			}
		}
		entity.setLastUpdBy(user.getName());// 最后更新人名称
		entity.setLastUpdById(user.getId());// 最后更新人ID
		entity.setLastUpdDate(new Date());// 最后更新时间
		
		super.save(entity);
	}
	
	@Override
	public void deleteBusiness(Serializable id) throws Exception {
		RtPage entity = rtPageDao.get(id);
		/**根据自己业务状态判断是否可删除数据，不能删除抛出异常*/
		if(RtSign.STATUS_ENABLE.equals(entity.getStatus())) {
			throw new KhntException("您提交的报告代码：【" + entity.getRtCode() + "】，版本号：【" + entity.getVersion() + "】正在使用中,不可删除！") ;
		}
		entity.setStatus(RtSign.STATUS_DEL);// 彻底删除
		rtPageDao.save(entity);
	}
	
	@Override
	public void deleteBusiness(String[] ids) throws Exception {
		for(String id:ids){
			deleteBusiness(id);
		}
	}
	
	/**
	 * 复制模版
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RtPage copyTpl(String id)throws Exception {
		// 复制模版
		RtPage srcRtPage = rtPageDao.get(id);
		RtPage newRtPage = new RtPage();
		BeanUtils.copyProperties(srcRtPage, newRtPage, new String[] {"id","createdById","createdBy","createdDate","lastUpdById","lastUpdBy","lastUpdDate","version","createTime","outPutJspFilePath",""});
		newRtPage.setStatus(RtSign.STATUS_DISABLE);
		this.save(newRtPage);
		
		// 复制模版内容
		List<PageContent> lPageContent = pageContentDao.findBy("rtPage.id", id);
		for(PageContent srcPageContent : lPageContent) {
			PageContent newPageContent = new PageContent();
			BeanUtils.copyProperties(srcPageContent, newPageContent, new String[] {"id","rtPage","createdById","createdBy","createdDate","lastUpdById","lastUpdBy","lastUpdDate","pageContentRecords"});
			newPageContent.setRtPage(newRtPage);
			pageContentManager.save(newPageContent);
		}
		return newRtPage;
	}
	
	/**
	 * 启用模版：启用任一个模版，其它全部禁用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RtPage enable(String id)throws Exception {
		RtPage entity = rtPageDao.get(id);
		entity.setStatus(RtSign.STATUS_ENABLE);
		rtPageDao.save(entity);
		
		// 禁用其它模版
		String rtCode = entity.getRtCode();
		rtPageDao.createSQLQuery("update RT_PAGE set STATUS=? where status<>'99' and RT_CODE=? and ID<>?", RtSign.STATUS_DISABLE,rtCode,id).executeUpdate();
		
		// 更新模版最新版本
		Template template = entity.getTemplate();
		template.setVersion(entity.getVersion());
		templateManager.save(template);
		return entity;
	}

	/**
	 * 禁用模版：禁用后，把最大版本号设置为启用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RtPage disable(String id)throws Exception {
		RtPage entity = rtPageDao.get(id);
		entity.setStatus(RtSign.STATUS_DISABLE);
		rtPageDao.save(entity);
		
		// 禁用后，启用最大版本号模版
		String rtCode = entity.getRtCode();
		String hql = "select max(version) from RtPage where rtCode=?";
		
		String maxVersion = templateDao.createQuery(hql, rtCode).uniqueResult().toString();
		if(!"1".equals(maxVersion)) {
			Template template = entity.getTemplate();
			template.setVersion(StringUtil.isEmpty(maxVersion) ? 1 : Integer.valueOf(maxVersion));
			templateManager.save(template);
		}
		
		return entity;
	}
	
	public void rtParse(String id, String attId) throws Exception {
		RtPage rtPage = this.get(id);
		Attachment att = this.attachmentManager.downloadAsFile(attId);
		RtReport rtReport = new RtReport();
		rtReport.docxToJsp(att.getFilePath(), rtPage);

	}

	/**
	 * 为报表生成最基本的模板
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void createTpl(RtPage entity) throws Exception {
		String tpl = new RtTpl().getTemplateHtml(getTplPagePath(entity));
		
		// 数据库保存方式，去掉jsp头信息
		if("1".equals(RtPath.saveDB)) {
			try {
				if("<%@".equals(tpl.substring(0, 3))) {
					tpl = tpl.substring(tpl.indexOf("%>",0) + 2 , tpl.length()-1);
				}
			}catch(Exception e) {
				e.printStackTrace();
				log.debug("报告模版页保存数据库，去掉jsp头信息发生错误，模版信息不做处理！");
			}
		}
		if("1".equals(entity.getModelType())){
			//检验原始记录
			// 替换隐藏的关联ID
			if (StringUtil.isNotEmpty(entity.getRelColCode())) {
				StringBuilder relCol = new StringBuilder();
				String[] columns = entity.getRelColCode().split(",");
				for (String column : columns) {
					if (column != null && column.contains("_")) {
									column = column.toLowerCase();
					}
					relCol.append(" 		<input type=\"hidden\" id=\"" + column + "\" name=\"" + column + "\" ").append(" value=\"\" ").append(" >").append("\n");
				}
				tpl = tpl.replace("[$RtPageHidden]", relCol.toString());
			} else {
				tpl = tpl.replace("[$RtPageHidden]", "");
			}
		}else {
			
			//检验报告
			// 替换标题
			tpl = tpl.replace("[$RtPageTitle]", entity.getRtName());

			// 替换CSS
			StringBuilder css = new StringBuilder();
			if (StringUtil.isNotEmpty(entity.getLinkCss())) {
				String[] linkCss = entity.getLinkCss().split(",");
				for (String link : linkCss) {
					css.append("<link href=\"" + link + "\" rel=\"stylesheet\" />").append("\n");
				}
			} else {
				css.append("<link href=\"rtbox/app/templates/default/tpl.css\" rel=\"stylesheet\" />").append("\n");
			}
			tpl = tpl.replace("[$RtPageCss]", css.toString());
			// 替换JS
			if (StringUtil.isNotEmpty(entity.getLinkJs())) {
				StringBuilder js = new StringBuilder();
				String[] linkJs = entity.getLinkJs().split(",");
				for (String link : linkJs) {
					js.append("<script type=\"text/javascript\" src=\"" + link + "\"></script>").append("\n");
				}
				tpl = tpl.replace("[$RtPageJs]", js.toString());
			} else {
				tpl = tpl.replace("[$RtPageJs]", "");
			}

			// 替换保存ACTION路径
			if (StringUtil.isNotEmpty(entity.getSavePath())) {
				tpl = tpl.replace("[$RtPageSaveAction]", entity.getSavePath());
			} else {
				tpl = tpl.replace("[$RtPageSaveAction]", "reportItemValueAction/saveMap.do");
			}
			// 替换详情ACTION路径
			if (StringUtil.isNotEmpty(entity.getDetailPath())) {
				tpl = tpl.replace("[$RtPageGetAction]", entity.getDetailPath());
			} else {
				tpl = tpl.replace("[$RtPageGetAction]", "reportItemValueAction/detailMap.do");
			}

			// 替换隐藏的关联ID
			if (StringUtil.isNotEmpty(entity.getRelColCode())) {
				StringBuilder relCol = new StringBuilder();
				String[] columns = entity.getRelColCode().split(",");
				for (String column : columns) {
					if (column != null && column.contains("_")) {
						column = column.toLowerCase();
					}
					relCol.append(" 		<input type=\"hidden\" id=\"" + column + "\" name=\"" + column + "\" ").append(" value=\"${param." + column + "}\" ").append(" >").append("\n");
				}
				tpl = tpl.replace("[$RtPageHidden]", relCol.toString());
			} else {
				tpl = tpl.replace("[$RtPageHidden]", "");
			}

			if (StringUtil.isNotEmpty(entity.getRelColCode())) {
				StringBuilder relCol = new StringBuilder("var relColumn='");
				String[] columns = entity.getRelColCode().split(",");
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
				relCol.append("';\n");
				tpl = tpl.replace("[$RtPageRelColumn]", relCol.toString());
			} else {
				tpl = tpl.replace("[$RtPageRelColumn]", "");
			}

		}
		
		String rtPageId = entity.getId();
		String rtCode = entity.getRtCode();
		String rtName = entity.getRtName();
		//String pageVersionId = pageVersion.getId();
		// 模版保存方式判断
		if("1".equals(RtPath.saveDB)) {
			log.debug("create base tpl for code(" + entity.getRtCode() + ") to DB" );
			PageContent pageContent = pageContentManager.findByRtCode(rtPageId, RtSign.REPORT_TEMPLATE_TPL);
			if(pageContent == null) {
				pageContent = new PageContent();
			}
			pageContent.setRtPage(entity);
			pageContent.setRtCode(rtCode);
			pageContent.setRtName(rtName);
			pageContent.setPageType(RtSign.REPORT_TEMPLATE_TPL);//模版类型
			pageContent.setContent(tpl);//模版内容
			pageContentManager.save(pageContent);
			
			//将报告内容加入缓存
			//redisUtil.bhPut(RedisConstant.KEY_REPORT_TEMPLATE, pageContent.getId() + "_" + RtSign.REPORT_TEMPLATE_DEFAULT, tpl);
		}else {
			String pagePath = RtRunPath.PROJECT_PATH + getTplPageDir(entity) + entity.getRtCode() + File.separator + entity.getVersion()+ File.separator + "tpl.html";
			log.debug("create base tpl for code(" + entity.getRtCode() + ")" + pagePath);
			File file = new File(pagePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			out.write(tpl);
			out.flush();
			out.close();
		}
	}

	/**
	 * 设置报表模板
	 * 
	 * @param entity
	 * @param attachment
	 * @return
	 * @throws Exception
	 */
	public RtPage setTemplate(RtPage entity, String attachmentId) throws Exception {
		entity = this.get(entity.getId());
		// 向附件中增加业务id
		if (!StringUtil.isEmpty(attachmentId)) {
			attachmentId = attachmentId.replaceAll("/^,/", "");
			attachmentManager.setAttachmentBusinessId(attachmentId, entity.getId());
		}
		if (StringUtil.isEmpty(entity.getPagePath())) {
			String pagePath = getTplPageDir(entity) + entity.getRtCode() + File.separator;
			entity.setPagePath(pagePath);
		} else {
			if (!entity.getPagePath().endsWith(File.separator)) {
				entity.setPagePath(entity.getPagePath() + File.separator);
			}
		}

		// Attachment att = this.attachmentManager.get(attachmentId);
		// String _docxFile = AttachmentManager.getSystemFilePath() +
		// File.separator + att.getFilePath();

		Attachment att = this.attachmentManager.downloadAsFile(attachmentId);
		// 获取文件路劲
		String _docxFile = att.getFilePath();
		String templeteXmlFilePath = RtPath.templeteXmlPath + Utils.monthDir() + File.separator;// 模板存放地
		String templeteDocFilePath = RtPath.templeteDocPath + Utils.monthDir() + File.separator;// word输出地
		String outPutJspFilePath = entity.getPagePath();// jsp输出地
		
		if("1".equals(RtPath.saveDB)) {
			templeteXmlFilePath = "保存到数据库";
			templeteDocFilePath = "保存到数据库";
			outPutJspFilePath = "保存到数据库";
			entity.setPagePath(outPutJspFilePath);
		}
		
		// 解析
		RtReport rtReport = new RtReport(templeteXmlFilePath, templeteDocFilePath);
		// Word文档转存为JSP，供用户填写
		rtReport.docxToJsp(_docxFile, entity);

		// 使用TOMCAT 虚拟路径进行匹配
		entity.setOutPutJspFilePath(outPutJspFilePath);

		this.save(entity);

		/**
		 * 操作日志未实现
		 */

		//

		return entity;
	}

	/**
	 * 导出DOCX
	 * 
	 * @param id
	 * @param sql
	 * @param rpd
	 * @param infoMap 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public RtPage rtRevert(String id, String code, String sql, RtPersonDir rpd, HashMap<String, Object> infoMap) throws Exception {
		RtPage rtPage = getRtPageByCode(code);

		// StringBuilder sql = new
		// StringBuilder("select item_name,item_value from tzsb_report_item_value ");
		List<Map<String, Object>> list = this.rtPageDao.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();

		// Map<String, Object> dataMap = new HashMap<String, Object>();
		// 装载功能数据
		Map<String, Object> dataMap = loadFuncData(id, null,"");
		// transMap为主要值传递MAP 将功能数据插入transMap
		Map<String, RtExportData> transMap = loadValueData(list, dataMap, id);

		DateFormat df = new SimpleDateFormat("yyyyMM");
		
		String outPutDocDirPath =  RtPath.outputDocPath + df.format(new Date()) + File.separator;// word输出地
		
		if(infoMap.get("outPutDocDirPath")!=null){
			
			outPutDocDirPath = infoMap.get("outPutDocDirPath").toString();
			
		}
		
		rtPage.setOutPutDocDirPath(outPutDocDirPath);

		// 生成DOC
		RtReport rtReport = new RtReport();
		rtReport.jspToDocx(rtPage, rpd, transMap, infoMap);

		
		
		// 修改本地路径为可下载路径,报告书编号为名称
		outPutDocDirPath = outPutDocDirPath.replace(RtPath.basePath, "");
		rtPage.setOutPutDocDirPath(File.separator + outPutDocDirPath);
		return rtPage;
	}

	/**
	 * 装载功能数据，包括着色，图片等
	 * 
	 * @param input
	 * @param page 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> loadFuncData(String id, String input, String page) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// 着色数据
		String colorSql = "select item_name name,func_value value from rt_func_color where FK_BUSINESS_ID = ?";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			colorSql = colorSql + "  and page_no='"+page+"'   ";
		}
		List<Map<String, Object>> list = this.rtPageDao.createSQLQuery(colorSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> colorMap = loadData(list);
		if (colorMap != null && !colorMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_COLOR, colorMap);
		}

		// 上传文件数据
		// String imageSql =
		// "select work_item name,id value from pub_attachment a where business_id = ? ";
		// String imageSql =
		// "select item_name name,func_value value from rt_func_image where FK_BUSINESS_ID = ? ";
		String imageSql = "select item_name name,func_value||'||'||scale value from rt_func_image where FK_BUSINESS_ID = ?  ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			imageSql = imageSql + "  and page_no='"+page+"'   ";
		}
		list = this.rtPageDao.createSQLQuery(imageSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> imageMap = loadData(list);
		if (imageMap != null && !imageMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_IMAGE, imageMap);
		}
		// 加粗数据
		String boldSql = "select item_name name,func_value value from rt_func_bold where FK_BUSINESS_ID = ?  ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			boldSql = boldSql + "  and page_no='"+page+"'   ";
		}
		list = this.rtPageDao.createSQLQuery(boldSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> boldMap = loadData(list);
		if (boldMap != null && !boldMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_BOLD, boldMap);
		}
		// 倾斜数据
		String italicSql = "select item_name name,func_value value from rt_func_italic where FK_BUSINESS_ID = ? ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			italicSql = italicSql + "  and page_no='"+page+"'   ";
		}
		list = this.rtPageDao.createSQLQuery(italicSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> italicMap = loadData(list);
		if (italicMap != null && !italicMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_ITALIC, italicMap);
		}
		// 字体大小
		String sizeSql = "select item_name name,func_value value from rt_func_size where FK_BUSINESS_ID = ? ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			sizeSql = sizeSql + "  and page_no='"+page+"'   ";
		}
		list = this.rtPageDao.createSQLQuery(sizeSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> sizeMap = loadData(list);
		if (sizeMap != null && !sizeMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_SIZE, sizeMap);
		}
		// 字体样式
		String familySql = "select item_name name,func_value value from rt_func_family where FK_BUSINESS_ID = ? ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			familySql = familySql + "  and page_no='"+page+"'   ";
		}
		list = this.rtPageDao.createSQLQuery(familySql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> familyMap = loadData(list);
		if (familyMap != null && !familyMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_FAMILY, familyMap);
		}
		// 标注
		/*String markSql = "select t.* from rt_marks t where t.FK_BUSINESS_ID = ?";
		if (input == null || StringUtil.isEmpty(input)) {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			markSql = markSql + " and t.mark_by_id = '" + user.getId() + "'";
		}
		markSql = markSql +  " and t.mark_time = (select max(r.mark_time) from rt_marks r where r.FK_BUSINESS_ID = ? and r.item=t.item)";
		list = this.rtPageDao.createSQLQuery(markSql, id, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();*/
		try {
			String markSql = "from ReportMark t where t.fkBusinessId = ? and t.isDel = '0' ";
			/*if (input == null || StringUtil.isEmpty(input)) {
				CurrentSessionUser user = SecurityUtil.getSecurityUser();
				markSql = markSql + " and t.markById = '" + user.getId() + "'";
			}*/
			//markSql = markSql +  " and t.markTime = (select max(r.markTime) from ReportMark r where r.fkBusinessId = ? and r.item=t.item)";
			List<ReportMark> marks = this.reportMarkDao.createQuery(markSql, id).list();
			
			Map<String, Object> labels = null;
			if(marks.size()>0){
				labels = new HashMap<String, Object>();
				for (ReportMark mark : marks) {
					labels.put(mark.getItem(),net.sf.json.JSONObject.fromBean(mark).toString());
				}
			}
			if (labels != null && !labels.isEmpty()) {
				dataMap.put(RtExportDataType.EXPORT_DATA_MARK, labels);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataMap;

	}

	
	/**
	 * 根据分页装载功能数据，包括着色，图片等
	 * 
	 * @param input
	 * @param page 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> loadFuncDataByPages(String id, String input, String page) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String pages = page.replace(",", "','");
		// 着色数据
		String colorSql = "select item_name name,func_value value from rt_func_color where FK_BUSINESS_ID = ?";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			colorSql = colorSql + "  and page_no in ('"+pages+"')   ";
		}
		List<Map<String, Object>> list = this.rtPageDao.createSQLQuery(colorSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> colorMap = loadDataBypage(list);
		if (colorMap != null && !colorMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_COLOR, colorMap);
		}

		// 上传文件数据
		// String imageSql =
		// "select work_item name,id value from pub_attachment a where business_id = ? ";
		// String imageSql =
		// "select item_name name,func_value value from rt_func_image where FK_BUSINESS_ID = ? ";
		String imageSql = "select item_name name,func_value||'||'||scale value from rt_func_image where FK_BUSINESS_ID = ?  ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			imageSql = imageSql + "  and page_no in ('"+pages+"')   ";
		}
		list = this.rtPageDao.createSQLQuery(imageSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> imageMap = loadDataBypage(list);
		if (imageMap != null && !imageMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_IMAGE, imageMap);
		}
		// 加粗数据
		String boldSql = "select item_name name,func_value value from rt_func_bold where FK_BUSINESS_ID = ?  ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			boldSql = boldSql + "  and page_no in ('"+pages+"')   ";
		}
		list = this.rtPageDao.createSQLQuery(boldSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> boldMap = loadDataBypage(list);
		if (boldMap != null && !boldMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_BOLD, boldMap);
		}
		// 倾斜数据
		String italicSql = "select item_name name,func_value value from rt_func_italic where FK_BUSINESS_ID = ? ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			italicSql = italicSql + "  and page_no in ('"+pages+"')   ";
		}
		list = this.rtPageDao.createSQLQuery(italicSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> italicMap = loadDataBypage(list);
		if (italicMap != null && !italicMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_ITALIC, italicMap);
		}
		// 字体大小
		String sizeSql = "select item_name name,func_value value from rt_func_size where FK_BUSINESS_ID = ? ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			sizeSql = sizeSql + "  and page_no in ('"+pages+"')   ";
		}
		list = this.rtPageDao.createSQLQuery(sizeSql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> sizeMap = loadDataBypage(list);
		if (sizeMap != null && !sizeMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_SIZE, sizeMap);
		}
		// 字体样式
		String familySql = "select item_name name,func_value value from rt_func_family where FK_BUSINESS_ID = ? ";
		if(page!=null&&StringUtil.isNotEmpty(page)) {
			familySql = familySql  + "  and page_no in ('"+pages+"')   ";
		}
		list = this.rtPageDao.createSQLQuery(familySql, id).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> familyMap = loadDataBypage(list);
		if (familyMap != null && !familyMap.isEmpty()) {
			dataMap.put(RtExportDataType.EXPORT_DATA_FAMILY, familyMap);
		}
		
		//此方法基本用于复制，不用复制标注信息
		
		
		return dataMap;

	}

	
	/**
	 * 删除所有功能值记录
	 * 
	 * @param id
	 * @param itemName
	 */
	public void delFuncData(String id, String itemName) {
		this.rtPageDao.createSQLQuery("delete from rt_func_color where item_name=? and fk_business_id=?", itemName, id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from rt_func_bold where item_name=? and fk_business_id=?", itemName, id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from rt_func_family where item_name=? and fk_business_id=?", itemName, id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from rt_func_image where item_name=? and fk_business_id=?", itemName, id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from RT_FUNC_ITALIC where item_name=? and fk_business_id=?", itemName, id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from RT_FUNC_SIZE where item_name=? and fk_business_id=?", itemName, id).executeUpdate();
		
	}

	public String getTplPageDir(RtPage rtPage) {
		if("1".equals(rtPage.getModelType())){
			return RtPath.tplRecordPageDir;
		}else{
			return RtPath.tplPageDir;
		}
		

	}

	public String getTplPagePath(RtPage rtPage) {
		// if ("1".equals(rtPage.getIsPage())) {
		// return RtRunPath.PROJECT_PATH + getTplPageDir() +
		// "default/tpl02.jsp";
		// } else {
		// return RtRunPath.PROJECT_PATH + getTplPageDir() + "default/tpl.jsp";
		// }
		
		if("1".equals(rtPage.getModelType())){
			//原始记录
			return RtRunPath.PROJECT_PATH + RtPath.tplPageDir+ "default/tpl_record.html";
		}else {
			return RtRunPath.PROJECT_PATH + RtPath.tplPageDir + "default/tpl_report.html";
		}
		

	}

	/**
	 * 将功能数据装在到值数据中
	 * 
	 * @param transMap
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public Map<String, RtExportData> loadValueData(List<Map<String, Object>> list, Map<String, Object> dataMap, String fkReportId) {
		Map<String, RtExportData> transMap = null;
		if (list != null && !list.isEmpty()) {
			transMap = new HashMap<String, RtExportData>();
			boolean flag = (dataMap != null && !dataMap.isEmpty()) ? true : false;
			for (Map<String, Object> map : list) {
				String key = String.valueOf(map.get("NAME"));
				// pingZhou2017/03/21
				String name = key;
				if (!(key.contains(RtPageType.TABLE) || key.contains("FK") || key.contains("fk") || key.contains("picture")||key.startsWith("base__")
						||key.startsWith(RtPath.getPropetyValue("inspect_record", "record")+"__")
						||key.startsWith(RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__"))) {
					name = "base__" + name;
				}
				Object value = map.get("VALUE");
				RtExportData rtExportData = new RtExportData();
				rtExportData.setName(name);// 字段名
				rtExportData.setValue(value == null ? "" : value.toString());// 字段值的
				rtExportData.setFkReportId(fkReportId);

				// 将着色，上传图片等功能数据加载到rtExportData中
				if (flag) {
					for (String funcKey : dataMap.keySet()) {
						Map<String, Object> funcMap = (Map<String, Object>) dataMap.get(funcKey);
						if (funcMap != null) {

							if (funcMap.containsKey(key)) {
								if ("image".equals(funcKey) && funcMap.get(rtExportData.getName()) != null) {
									// 图片样式不为空值给image赋值 pingZhou2017/03/21
									rtExportData.setImage(funcMap.get(rtExportData.getName()).toString());
									System.out.println("---------------" + key + "---" + funcMap + "---------------------");
								}
								Map<String, Object> exportFuncMap = rtExportData.getFuncMap();
								if (exportFuncMap == null) {
									exportFuncMap = new HashMap<String, Object>();
									rtExportData.setFuncMap(exportFuncMap);
								}
								System.out.println(funcKey + "---" + funcMap.get(rtExportData.getName()) + "---");
								if(funcMap.get(rtExportData.getName())==null){
									int l = rtExportData.getName().length();
									exportFuncMap.put(funcKey, funcMap.get(rtExportData.getName().substring(6, l)));
								}else{
									exportFuncMap.put(funcKey, funcMap.get(rtExportData.getName()));
								}
								
							}
						}
					}
				}

				transMap.put(name, rtExportData);
			}
		}
		return transMap;
	}

	public Map<String, Object> loadData(List<Map<String, Object>> list) {
		Map<String, Object> transMap = null;
		if (list != null && !list.isEmpty()) {
			transMap = new HashMap<String, Object>();
			for (Map<String, Object> map : list) {
				String key = String.valueOf(map.get("NAME"));
				transMap.put(key, map.get("VALUE"));
			}
		}
		return transMap;
	}

	/**
	 * 转载样式数据（加分页信息）
	 * author pingZhou
	 * @param list
	 * @return
	 */
	public Map<String, Object> loadDataBypage(List<Map<String, Object>> list) {
		Map<String, Object> transMap = null;
		if (list != null && !list.isEmpty()) {
			transMap = new HashMap<String, Object>();
			for (Map<String, Object> map : list) {
				String pageNo = map.get("PAGE_NO")==null?"":String.valueOf(map.get("PAGE_NO"));
				String key = String.valueOf(map.get("NAME"))+pageNo;
				transMap.put(key, map.get("VALUE"));
			}
		}
		return transMap;
	}
	
	/**
	 * 根据报表代码获取报表配置
	 * 
	 * @param rtCode
	 * @return
	 * @throws Exception
	 */
	public RtPage getRtPageByCode(String code) throws Exception {
		List<RtPage> rtPages = this.rtPageDao.listQuery("from RtPage where rtCode=? and status=?", code, "0");
		RtPage rtPage = null;
		if (rtPages != null && rtPages.size() == 1) {
			rtPage = rtPages.get(0);
			return rtPage;
		} else {
			throw new Exception("未找到，或找到多个报表代码(" + code + ")配置，请价差");
		}
	}
	

	/**
	 * 单页导出DOCX
	 * 
	 * @param id
	 * @param sql
	 * @param rpd
	 * @param folder 文件夹
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void previewSingle(String id, String code, String sql, RtPersonDir rpd,String nowcode,HashMap<String, Object> infoMap,String folder,String page) throws Exception {
		RtPage rtPage = getRtPageByCode(code);

		// StringBuilder sql = new
		// StringBuilder("select item_name,item_value from tzsb_report_item_value ");
		List<Map<String, Object>> list = this.rtPageDao.createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();

		// Map<String, Object> dataMap = new HashMap<String, Object>();
		// 装载功能数据
		Map<String, Object> dataMap = loadFuncData(id, null,page);
		// transMap为主要值传递MAP 将功能数据插入transMap
		Map<String, RtExportData> transMap = loadValueData(list, dataMap, id);
		if(folder!=null){
			String outPutDocDirPath = RtPath.outputDocPath + folder + File.separator;// word输出地
			rtPage.setOutPutDocDirPath(outPutDocDirPath);

		}

		//用于单页预览的地址
		infoMap.put("path", RtPath.basePath+RtPath.getPreviewSingleDocPath(rpd,nowcode));
		// 生成DOC
		RtReport rtReport = new RtReport();
		rtReport.jspToDocx(rtPage, rpd, transMap, infoMap);

	}

	
	/**
	 * 生成默认模板
	 * author pingZhou
	 * @param itemCodes 必选页code，多页“,”分隔
	 * @param code 模板code
	 * @throws Exception 
	 */
	public void createDefaultTemplete(String itemCodes, String code) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<RtDir> rd = this.rtDirDao.createQuery("from RtDir where rtCode=?", code).list();
		RtDir rpd = rd.get(0);
		//取出原始目录
		
		String dirJson = rpd.getRtDirJson();
		// dirJson =
		// dirJson.replaceAll("\\{\"code\":\"\\d{1,10}\",\"name\":\"[\\u4e00-\\u9fa5\\w\\d]*\",\"pageName\":\"index\\d{1,10}.jsp(\\?code_ext=[_\\d]){0,1}\",\"treedataindex\":\\d*,\"__status\":\"delete\"\\}(,){0,1}",
		// "");
		dirJson = dirJson.replaceAll(",\"treedataindex\":\\d*,\"__status\":\"add\"", "");
		JSONArray array = new JSONArray(dirJson);
		JSONArray temp = new JSONArray();
		filterDelNode(array, temp,itemCodes);
		
		dirJson = temp.toString();
		
		rpd.setRtDefaultDirJson(dirJson);
		rtDirDao.save(rpd);
		
	}
	
	public static void filterDelNode(JSONArray array, JSONArray temp,String itemCodes) throws JSONException {
		for (int i = 0, l = array.length(); i < l; i++) {
			JSONObject object = (JSONObject) array.get(i);
			if (itemCodes.contains(object.getString("code")) || "root".equals(object.getString("code"))) {
				temp.put(object);
				if (object.has("children")&&object.get("children") != null) {
					JSONArray children = object.getJSONArray("children");
					boolean validateFlag = true;
					if (children.length() == 1) {
						JSONObject child = (JSONObject) children.get(0);
						if (itemCodes.contains(child.getString("code"))) {
							validateFlag = false;
						}
					}
					if (validateFlag) {
						if (children != null && children.length() > 0) {
							JSONArray tempChildren = new JSONArray();
							object.put("children", tempChildren);
							filterDelNode(children, tempChildren,itemCodes);
						}
					} else {
						object.remove("children");
					}
				}else if (!itemCodes.contains(object.getString("code"))) {
					array.opt(i);
				
				}
			}
		}
	}

	/**
	 * 批量删除所有功能值记录
	 * 
	 * @param id
	 * @param itemName
	 * @param page 
	 */
	public void delFuncDataBacth(String id, String itemName, String page) {
		String pgaes = page.replace(",", "','");
		this.rtPageDao.createSQLQuery("delete from rt_func_color where item_name in ('"+itemName+"') and fk_business_id=? and page_no in ('"+pgaes+"')" , id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from rt_func_bold where item_name in ('"+itemName+"') and fk_business_id=? and page_no in ('"+pgaes+"')" , id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from rt_func_family where item_name in ('"+itemName+"') and fk_business_id=? and page_no in ('"+pgaes+"')" , id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from rt_func_image where item_name in ('"+itemName+"') and fk_business_id=? and page_no in ('"+pgaes+"')" , id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from RT_FUNC_ITALIC where item_name in ('"+itemName+"') and fk_business_id=? and page_no in ('"+pgaes+"')" , id).executeUpdate();
		this.rtPageDao.createSQLQuery("delete from RT_FUNC_SIZE where item_name in ('"+itemName+"') and fk_business_id=? and page_no in ('"+pgaes+"')" , id).executeUpdate();
		
	}

	public void saveIndexChange(HttpServletRequest request, String path, String content) {
		String basepath = Factory.getWebRoot();
		String filePath = basepath +File.separator+path;
		File file = new File(filePath);
		if(!file.exists()) {
			throw new KhntException("文件不存在！");
		}
		
		String beforeContent = FileUtils.readToString(filePath);
		//System.out.println(beforeContent);
		String resultContent = "";
		int start = beforeContent.indexOf("<body>");
		int end = beforeContent.indexOf("</body>")+7;
		String body = beforeContent.substring(start,end);
		
		
		String regex1 = "<u:dict (.*?)/>";
		String regexAudit = "<input id=\"base__audit_op(.*?)/>";
		String regexSign = "<input id=\"base__sign_op(.*?)/>";
		String regexConfirm = "<input id=\"base__confirm_op(.*?)/>";
		String regexEnter = "<input id=\"base__enter_op(.*?)/>";
		List<String> list1 = new ArrayList<String>();
		List<String> listAudit = new ArrayList<String>();
		List<String> listSign = new ArrayList<String>();
		List<String> listConfirm = new ArrayList<String>();
		List<String> listEnter = new ArrayList<String>();
		//beforeContent = beforeContent.replaceAll(regex,  "<body>"+content+"</body>");
		
	    Pattern pattern = Pattern.compile(regex1);
	    Matcher matcher = pattern.matcher(beforeContent);
	    while (matcher.find()) {
	    	
	    	String inspectOp = matcher.group();
	    	System.err.println(inspectOp);
	    	list1.add(inspectOp);
	    }
	    
	    
	    Pattern patternAudit = Pattern.compile(regexAudit);
	    Matcher matcherAudit = patternAudit.matcher(beforeContent);
	    while (matcherAudit.find()) {
	    	
	    	String inspectAudit = matcherAudit.group();
	    	System.err.println(inspectAudit);
	    	listAudit.add(inspectAudit);
	    }
	    
	    Pattern patternSign = Pattern.compile(regexSign);
	    Matcher matcherSign = patternSign.matcher(beforeContent);
	    while (matcherSign.find()) {
	    	
	    	String inspectSign = matcherSign.group();
	    	System.err.println(inspectSign);
	    	listSign.add(inspectSign);
	    }
	    
	    Pattern patternConfirm = Pattern.compile(regexConfirm);
	    Matcher matcherConfirm = patternConfirm.matcher(beforeContent);
	    while (matcherConfirm.find()) {
	    	
	    	String inspectConfirm = matcherConfirm.group();
	    	System.err.println(inspectConfirm);
	    	listConfirm.add(inspectConfirm);
	    }
	    
	    Pattern patternEnter = Pattern.compile(regexEnter);
	    Matcher matcherEnter = patternEnter.matcher(beforeContent);
	    while (matcherEnter.find()) {
	    	
	    	String inspectEnter = matcherEnter.group();
	    	System.err.println(inspectEnter);
	    	listEnter.add(inspectEnter);
	    }
	    
	    resultContent = beforeContent.replace(body, "<body><div id=\"layout2\" style=\"width: 99.8%\">"+content+"</div></body>");
	    if(StringUtil.isNotEmpty(resultContent)) {
	    	 try {
				 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		         out.write(resultContent);
		         out.close();
			} catch (IOException e) {
				
			}
	    }
	}

	public void saveIndexChangeReport(HttpServletRequest request, String content , String rtboxId,String pageCode) throws IOException {
		RtPage rtPage = rtPageDao.get(rtboxId);
		if(rtPage==null) {
			throw new KhntException("没有对应模板！");
		}
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		
		 String resultContent ="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
	 	    		"<html><body>"+content+"</div></body></html>";
		 PageContentRecord pageContentRecord = new PageContentRecord();
		 String verion = "";
	    if("1".equals(RtPath.saveDB)) {
	    	//保存在数据库
	    	PageContent pageContent = pageContentDao.getByRtPageAndCode(rtboxId,pageCode);
	    	if(pageContent==null) {
	    		//为null，初始化
	    		pageContent = new PageContent();
	    		pageContent.setCreatedById(user.getId());
	    		pageContent.setCreatedBy(user.getName());
	    		pageContent.setCreatedDate(new Date());
	    		pageContent.setRtPage(rtPage);
	    		pageContent.setRtCode(rtPage.getRtCode());
	    		pageContent.setRtName(rtPage.getRtName());
	    		pageContent.setPageType(pageCode);
	    	}
	    	
	    	pageContent.setLastUpdBy(user.getId());
    		pageContent.setLastUpdById(user.getName());
    		pageContent.setLastUpdDate(new Date());
    		
    		
    		//更新内容
	    	
	    	 pageContent.setContent(resultContent);
	    	 
	    	
	    	 
	    	 //记录小版本
	 	    verion = pageContentRecordDao.getMaxVersion(pageContent.getId(),pageCode,rtPage.getVersion());
	 	    
	 	   pageContent.setVersion(Integer.parseInt(verion)+1);
	 	   
	 	   pageContentDao.save(pageContent);
	 	   
	 	   pageContentRecord.setPageContent(pageContent);
	    }else {
	    	//默认 保存磁盘
	    	//文件存在磁盘
			 //模板根目录
			 String basepath = RtPath.projectPath;
			 
			 if("1".equals(rtPage.getModelType())) {
				 //原始记录
				 basepath += File.separator+RtPath.tplRecordPageDir;
			 }else {
				 //报告
				 basepath += File.separator+RtPath.tplPageDir;
			 }
			 
			 String filePath = basepath + rtPage.getRtCode()+File.separator+rtPage.getVersion()+File.separator+"index"+pageCode+".html";
			 
			File file = new File(filePath);
			if(!file.exists()) {
				file.createNewFile();
			}
			
	    	 String resultContentDisk = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
	 	    		"<html><body>"+content+"</div></body></html>";
	 	    if(StringUtil.isNotEmpty(resultContentDisk)) {
	 	    	 try {
	 				 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
	 		         out.write(resultContentDisk);
	 		         out.close();
	 			} catch (IOException e) {
	 				
	 			}
	 	    }
	 	   //记录小版本
	 	    verion = pageContentRecordDao.getMaxVersion(null,pageCode,rtPage.getVersion());
	    }
	 
   
 	   pageContentRecord.setRtCode(rtPage.getRtCode());
 	   pageContentRecord.setRtName(rtPage.getRtName());
 	   pageContentRecord.setPageType(pageCode);
 	   pageContentRecord.setContent(resultContent);
 	   //新版本 从1开始
 	   pageContentRecord.setVersion(Integer.parseInt(verion)+1);
 	  
 	   pageContentRecord.setCreatedById(user.getId());
 	   pageContentRecord.setCreatedBy(user.getName());
 	   pageContentRecord.setCreatedDate(new Date());
 	   pageContentRecord.setLastUpdBy(user.getId());
 	   pageContentRecord.setLastUpdById(user.getName());
 	   pageContentRecord.setLastUpdDate(new Date());
 	   
 	   pageContentRecord.setBigVersion(rtPage.getVersion());
 	   
 	   pageContentRecordDao.save(pageContentRecord);
	   
	   
	    
		
	}

	/**
	 * 取页面内容
	 * author pingZhou
	 * @param request
	 * @param rtPageId 大版本模板id
	 * @param pageCode 页面code
	 * @return
	 * @throws Exception
	 */
	public String getPageContent(HttpServletRequest request, String rtPageId, String pageCode,String rtCode) throws Exception {

		RtPage rtPage = null;
		if(rtPageId==null||StringUtil.isEmpty(rtPageId)) {
			rtPage = rtPageDao.getNewByCode(rtCode);
		}else {
			rtPage = rtPageDao.get(rtPageId);
		}
		
		 if("1".equals(RtPath.saveDB)) {
		    //保存在数据库
			 PageContent pageContent = pageContentDao.getByRtPageAndCode(rtPageId,pageCode);
			 return pageContent.getContent();
		 }else{
			 //文件存在磁盘
			 //模板根目录
			 String basepath = RtPath.projectPath;
			 
			 if("1".equals(rtPage.getModelType())) {
				 //原始记录
				 basepath += File.separator+RtPath.tplRecordPageDir;
			 }else {
				 //报告
				 basepath += File.separator+RtPath.tplPageDir;
			 }
			 
			 String filePath = basepath + rtPage.getRtCode()+File.separator+rtPage.getVersion()+File.separator+"index"+pageCode+".html";
			 System.out.println(filePath.substring(1));
			 File file = new File(filePath.substring(1));
			 if(!file.exists()) {
				throw new KhntException("文件不存在！");
			 }
			 return FileUtils.readFileToString(file,"UTF-8");
		 }
	}
	
	/**
	 * 根据模板查询模板内容，没有传入版本号时默认最新
	 * author pingZhou
	 * @param request
	 * @param rtPageId
	 * @param pageCode
	 * @param version 
	 * @return
	 * @throws Exception
	 */
	public String getPageContentByVersion(HttpServletRequest request, String rtPageId, String pageCode, Integer version) throws Exception {


		RtPage rtPage = rtPageDao.get(rtPageId);
		 if("1".equals(RtPath.saveDB)) {
			//保存在数据库
			 PageContent pageContent = pageContentDao.getByRtPageAndCode(rtPageId,pageCode);
			 if(version==null) {
				 //没有指定模板
				 if(pageContent==null) {
					 log.debug("模板文件不存在--rtPageId:"+rtPageId+"---pageCode:"+pageCode+"---version："+version);
					 throw new KhntException("模板文件不存在！");
				 }
				 return pageContent.getContent();
				 
			 }else {
				 
				 PageContentRecord pageContentRecord = pageContentRecordDao.getByRtContentAndCodeAndVersion(pageContent.getId(),pageCode,version);
				 if(pageContentRecord==null) {
					 log.debug("不存在指定版本模板--rtPageId:"+rtPageId+"---pageCode:"+pageCode+"---version："+version);
					 throw new KhntException("不存在指定版本模板！");
				 }
				 return pageContentRecord.getContent();
			 }
		   
		 }else{
			 //文件存在磁盘
			 
			
			 if(version==null) {
				 //没有指定模板
				//模板根目录
				 String basepath = RtPath.projectPath;
				 if("1".equals(rtPage.getModelType())) {
					 //原始记录
					 basepath += File.separator+RtPath.tplRecordPageDir;
				 }else {
					 //报告
					 basepath += File.separator+RtPath.tplPageDir;
				 }
				 
				 String filePath = basepath + rtPage.getRtCode()+File.separator+rtPage.getVersion()+File.separator+"index"+pageCode+".html";
				 
				 File file = new File(filePath.substring(1));
				 System.out.println(filePath.substring(1));
				 if(!file.exists()) {
					 log.debug("模板文件不存在--rtPageId:"+rtPageId+"---pageCode:"+pageCode+"---version："+version);
					throw new KhntException("文件不存在！");
				 }
				 return FileUtils.readFileToString(file,"UTF-8");
				 
			 }else {
				 Integer bigVersion = rtPage.getVersion();
				 PageContentRecord pageContentRecord = pageContentRecordDao.getByBigVersionAndCodeAndVersion(rtPage.getRtCode(),bigVersion,pageCode,version);
				 if(pageContentRecord==null) {
					 log.debug("不存在指定版本模板--rtPageId:"+rtPageId+"---pageCode:"+pageCode+"---version："+version);
					 //throw new KhntException("不存在指定版本模板！");
					 String basepath = RtPath.projectPath;
					 if("1".equals(rtPage.getModelType())) {
						 //原始记录
						 basepath += File.separator+RtPath.tplRecordPageDir;
					 }else {
						 //报告
						 basepath += File.separator+RtPath.tplPageDir;
					 }
					 
					 String filePath = basepath + rtPage.getRtCode()+File.separator+rtPage.getVersion()+File.separator+"index"+pageCode+".html";
					 
					 File file = new File(filePath.substring(1));
					 System.out.println(filePath.substring(1));
					 if(!file.exists()) {
						 log.debug("模板文件不存在--rtPageId:"+rtPageId+"---pageCode:"+pageCode+"---version："+version);
						throw new KhntException("文件不存在！");
					 }
					 return FileUtils.readFileToString(file,"UTF-8");
				 }
				 return pageContentRecord.getContent();
			 }
			 
		 }
	}

	/**
	 * 根据模板和版本查询报告
	 * author pingZhou
	 * @param templateId 模板id
	 * @param bigVersion 版本号
	 * @return 模板对象
	 */
	public RtPage getByTemplateAndVersion(String templateId, Integer bigVersion) {
		
		return rtPageDao.getByTemplateAndVersion(templateId, bigVersion);
	}

	public String getVersionJson(String id) throws JSONException {
		RtPage rtPage = rtPageDao.get(id);
		List<Object> list =  pageContentRecordDao.getByMaxVersion(rtPage.getRtCode(),rtPage.getVersion());
		JSONObject json = new JSONObject();
				
		for (int i = 0; i < list.size(); i++) {
			Object [] obj = (Object[]) list.get(i);
			json.put("page"+obj[1], obj[0]);
		}
		JSONObject jsonN = new JSONObject();
		jsonN.put("page",json);
		jsonN.put("version",rtPage.getVersion());
		return jsonN.toString();
	}
	public String getDir(String id,String infoId,String code) {
		//由于在录入报告选择目录时要显示所有目录，故取消下面获取业务目录的代码
		/*if (StringUtil.isNotEmpty(infoId)) {
			List<RtPersonDir> rpd = this.rtDirDao.createQuery("from RtPersonDir where fkBusinessId=? and rtCode=?", infoId, code).list();
			if (rpd != null && !rpd.isEmpty()) {
				return rpd.get(0).getRtDirJson();
			}else {
				RtPage rtPage = rtPageDao.getNewByCode(code);
				return rtPage.getRtDirJson();
			}
		}*/
		if(id==null||StringUtil.isEmpty(id)) {
			
			RtPage rtPage = rtPageDao.getNewByCode(code);
			return rtPage.getRtDirJson();
		}else {
			RtPage rtPage = rtPageDao.get(id);
			return rtPage.getRtDirJson();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String getTempDirs(String code) throws Exception {
		String jsonstring = "";
		RtPage rtPage = rtPageDao.getNewByCode(code);
			String rtDirJson = rtPage.getRtDirJson();
			if (StringUtil.isNotEmpty(rtDirJson)) {
				JSONArray array = new JSONArray(rtDirJson);
				JSONObject root = array.getJSONObject(0);
				JSONArray children = root.getJSONArray("children");

				for (int i = 0; i < children.length(); i++) {
					JSONObject child = children.getJSONObject(i);
					jsonstring += "{\"id\":\"" + child.getString("code") + "\",\"text\":\""
							+ child.getString("name") + "\"},";
				}

				if (jsonstring.endsWith(",")) {
					jsonstring = jsonstring.substring(0, jsonstring.length() - 1);
				}
				jsonstring = "[" + jsonstring + "]";
				return jsonstring;
			}
		
		throw new Exception("没有找到模版目录，code:" + code);
	}
	
}
