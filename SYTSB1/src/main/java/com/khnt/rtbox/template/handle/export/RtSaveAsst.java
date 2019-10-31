package com.khnt.rtbox.template.handle.export;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.R;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.khnt.rbac.bean.User;
import com.khnt.rtbox.config.bean.ReportMark;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.config.dao.ReportMarkDao;
import com.khnt.rtbox.config.dao.RtPageDao;
import com.khnt.rtbox.template.constant.RtExportDataType;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.model.RtExportAssts;
import com.khnt.rtbox.tools.Utils;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年7月7日 下午3:14:27 类说明
 */
public class RtSaveAsst extends RtExportBase {
	private RtPageDao rtPageDao;
	private String userId;
	private String fkBusinessId;
	private String rtCode;

	@Override
	public void execute(RtExportData rtExportData, WordprocessingMLPackage wordMLPackage, R r, RtExportAssts rtExportAssts) throws Exception {
		if (fkBusinessId == null) {
			throw new Exception("保存出错，报表ID为空，请设置....");
		}
		if (rtPageDao == null) {
			throw new Exception("保存出错，rtPageDao为空，请设置....");
		}
		// if (rtCode == null) {
		// throw new Exception("保存出错，报表CODE为空，请设置....");
		// }
		if (userId == null) {
			this.userId = SecurityUtil.getSecurityUser().getId();
		}
		super.execute(rtExportData, wordMLPackage, r, rtExportAssts);
	}

	@Override
	public void color() throws Exception {
		// 检查是否着色字段
		if (this.value != null) {
			String color = value.toString();
			if (isColor(color)) {
				color = color.replace(" ", "").trim();
				// 插入着色数据库
				this.rtPageDao.createSQLQuery("insert into rt_func_color "
						+ "(id,fk_business_id,rt_code,item_name,func_value,fk_create_user_id,create_time,page_no)"
						+ " values (?,?,?,?,?,?,?,?)", Utils.uuid(), this.fkBusinessId, this.rtCode, rtExportData.getName(), color,
						userId, new Date(),rtExportData.getPageNo()).executeUpdate();
			}
		}

	}

	@Override
	public void bold() throws Exception {
		// 检查是否加粗字段
		if (this.value != null) {
			String color = value.toString();
			if (isColor(color)) {
				color = color.replace(" ", "").trim();
				// 插入加粗数据库
				this.rtPageDao.createSQLQuery("insert into rt_func_bold "
						+ "(id,fk_business_id,rt_code,item_name,func_value,fk_create_user_id,create_time,page_no)"
						+ " values (?,?,?,?,?,?,?,?)", Utils.uuid(), this.fkBusinessId, this.rtCode, rtExportData.getName(), color,
						userId, new Date(),rtExportData.getPageNo()).executeUpdate();
			}
		}

	}

	@Override
	public void italic() throws Exception {
		// 检查是否倾斜
		if (this.value != null) {
			String color = value.toString();
			if (isColor(color)) {
				color = color.replace(" ", "").trim();
				// 插入着色数据库
				this.rtPageDao.createSQLQuery("insert into rt_func_italic "
						+ "(id,fk_business_id,rt_code,item_name,func_value,fk_create_user_id,create_time,page_no)"
						+ " values (?,?,?,?,?,?,?,?)", Utils.uuid(), this.fkBusinessId, this.rtCode, rtExportData.getName(), color,
						userId, new Date(),rtExportData.getPageNo()).executeUpdate();
			}
		}

	}

	@Override
	public void size() throws Exception {
		// 检查字体大小
		if (this.value != null) {
			String color = value.toString();
			if (isColor(color)) {
				color = color.replace(" ", "").trim();
				// 插入着色数据库
				this.rtPageDao.createSQLQuery("insert into rt_func_size "
						+ "(id,fk_business_id,rt_code,item_name,func_value,fk_create_user_id,create_time,page_no)"
						+ " values (?,?,?,?,?,?,?,?)", Utils.uuid(), this.fkBusinessId, this.rtCode, rtExportData.getName(), color,
						userId, new Date(),rtExportData.getPageNo()).executeUpdate();
			}
		}

	}

	@Override
	public void family() throws Exception {
		// 检查字体样式
		if (this.value != null) {
			String color = value.toString();
			if (isColor(color)) {
				color = color.replace(" ", "").trim();
				// 插入着色数据库
				this.rtPageDao.createSQLQuery("insert into rt_func_family "
						+ "(id,fk_business_id,rt_code,item_name,func_value,fk_create_user_id,create_time,page_no)"
						+ " values (?,?,?,?,?,?,?,?)", Utils.uuid(), this.fkBusinessId, this.rtCode, rtExportData.getName(), color,
						userId, new Date(),rtExportData.getPageNo()).executeUpdate();
			}
		}

	}

	public static Map<String, RtExportData> transToMap(List<Map<String, Object>> list, String filter) {
		Map<String, RtExportData> returnMap = new HashMap<String, RtExportData>();
		for (Map<String, Object> map : list) {
			String name = String.valueOf(map.get("name"));
			if (StringUtil.isEmpty(name)) {
				continue;
			}
			System.out.println("---" + name + "--" + String.valueOf(map.get("value")) + "---");
			String value = String.valueOf(map.get("value"));
			String type = String.valueOf(map.get("type"));
			String color = String.valueOf(map.get("color"));
			String bold = String.valueOf(map.get("bold"));
			String italic = String.valueOf(map.get("italic"));
			String size = String.valueOf(map.get("size"));
			String family = String.valueOf(map.get("family"));
			String image = String.valueOf(map.get("image"));
			String markContent = String.valueOf(map.get("markContent"));
			RtExportData rtExportData = new RtExportData();
			rtExportData.setName(name);
			rtExportData.setValue(value);
			rtExportData.setRemark(color);
			rtExportData.setType(type);
			rtExportData.setBold(bold);
			rtExportData.setItalic(italic);
			rtExportData.setSize(size);
			rtExportData.setFamily(family);
			rtExportData.setImage(image);
			rtExportData.setMarkContent(markContent);
			Map<String, Object> funcMap = new HashMap<String, Object>();
			rtExportData.setFuncMap(funcMap);

			if (isColor(color)) {
				funcMap.put(RtExportDataType.EXPORT_DATA_COLOR, color);
			}
			if (StringUtil.isNotEmpty(image)) {
				funcMap.put(RtExportDataType.EXPORT_DATA_IMAGE, image);
			}
			if (bold != null && !bold.equals("400")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_BOLD, bold);
			}
			if (italic != null && !italic.equals("normal")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_ITALIC, italic);
			}
			if (size != null && !size.equals("14px")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_SIZE, size);
			}
			if (family != null && !family.equals("宋体")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_FAMILY, family);
			}
			if (!StringUtil.isEmpty(markContent) && !"null".equals(markContent) && markContent != null) {
				funcMap.put(RtExportDataType.EXPORT_DATA_MARK, markContent);
			}
			// pingZhou2017/03/17修改 基础信息加上统一前缀
			if (name.contains(filter) || name.contains("FK") || name.contains("fk") || name.contains("picture") 
					|| name.contains("base__")||name.startsWith(RtPath.getPropetyValue("inspect_record", "record")+"__")
					||name.startsWith(RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__")) {
				// name.contains(filter) ||name.contains("FK") ||
				// name.contains("fk"))&&

				if (value != null && !"null".equals(value)&& StringUtil.isNotEmpty(value)) {
					// 
					// pingZhou2017/03/17修改 基础信息加上统一前缀
					if (name.contains("base__")) {
						// 保存时去掉前缀
						name = name.split("__")[1];
					}
					returnMap.put(name, rtExportData);
				}/* else{// if (StringUtil.isNotEmpty(markContent) && !"null".equals(markContent) && markContent != null) {
					if (name.contains("base__")) {
						// 保存时去掉前缀
						name = name.split("__")[1];
					}
					returnMap.put(name, rtExportData);
				}*/
			}
		}
		return returnMap;
	}

	public static boolean isColor(String color) {
		if (color != null && color.trim().length() > 0 && !color.equals("null") && !color.equals("undefined") && !color.equals("rgb(0, 0, 0)")) {
			return true;
		}
		return false;
	}

	@Override
	public void image() throws Exception {
		// image的值包含了width和height，格式为“width,height”,如："100px,100px"

		if (this.value != null) {
			String image = value.toString();
			System.out.println(image + "------------------image----------------------------------");
			if (isColor(image)) {
				image = image.replace(" ", "").trim();
				// 插入图片数据库
				// 获取宽度
				String width = image.split(",")[0];
				// 获取高度
				String height = image.split(",")[1];
				String scale = null;

				if ((image.split(",")).length > 2) {
					scale = image.split(",")[2];
				}
				String value = "width:" + width + ";height:" + height + ";";
				if(width.contains("width")) {
					value =  width + ";" + height + ";";
				}
				
				this.rtPageDao.createSQLQuery("delete from rt_func_image i where i.fk_business_id = ? and i.item_name= ? and i.page_no=?", this.fkBusinessId, rtExportData.getName(),rtExportData.getPageNo())
						.executeUpdate();
				this.rtPageDao.createSQLQuery("insert into rt_func_image "
						+ "(id,fk_business_id,rt_code,item_name,func_value,fk_create_user_id,create_time,width,height,scale,page_no)"
						+ " values (?,?,?,?,?,?,?,?,?,?,?)", Utils.uuid(), this.fkBusinessId, this.rtCode, rtExportData.getName(),
						value, userId, new Date(), width.substring(0, width.length() - 2), height.substring(0, height.length() - 2), scale,rtExportData.getPageNo()).executeUpdate();
			}
		}
	}

	@Override
	public void value() throws Exception {
		// TODO Auto-generated method stub

	}

	public RtPageDao getRtPageDao() {
		return rtPageDao;
	}

	public void setRtPageDao(RtPageDao rtPageDao) {
		this.rtPageDao = rtPageDao;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFkBusinessId() {
		return fkBusinessId;
	}

	public void setFkBusinessId(String fkBusinessId) {
		this.fkBusinessId = fkBusinessId;
	}

	public String getRtCode() {
		return rtCode;
	}

	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	@Override
	public void sub() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sup() throws Exception {
		// TODO Auto-generated method stub

	}

	@Deprecated
	@Override
	public void addPictureImp(int i, String signId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void mark() throws Exception {
		// 检查是否有标注
		// if (this.value != null) {
		String content = value.toString();
		// 插入标记数据库
		JSONObject jsonobject = JSONObject.parseObject(content);
		ReportMark mark = JSONObject.toJavaObject(jsonobject, ReportMark.class);
		
		User user = SecurityUtil.getSecurityUser().getSysUser();
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		ReportMarkDao reportMarkDao = (ReportMarkDao)context.getBean("reportMarkDao");
		if(StringUtil.isEmpty(mark.getId())){
			mark.setIsDel("0");
			mark.setMarkBy(user.getName());
			mark.setMarkById(user.getId());
			mark.setMarkTime(new Date());
			reportMarkDao.save(mark);
		}else{
			String hql = "update ReportMark set itemValue=?,markType=?,markContent=?,editById=?,"
					+ "editBy=?,editTime=?,status=? where id=?";
			reportMarkDao.createQuery(hql, mark.getItemValue(),
					mark.getMarkType(),mark.getMarkContent(),user.getId(),
					user.getName(),new Date(),mark.getStatus(),mark.getId()).executeUpdate();
		}
		/*if (!StringUtil.isEmpty(mark.getId())) {
			// 修改
			// 其实可以什么都不做，除非检验人员自己改了标注
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = this.rtPageDao.createSQLQuery("select * from rt_marks where id = ?", mark.getId())
			.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
			if (list.size() > 0) {
				Map<String, Object> map = list.get(0);
				String markString = map.get("MARK_CONTENT").toString();
				JSONObject jsonobject2 = JSONObject.parseObject(markString);
				ReportMark mark2 = JSONObject.toJavaObject(jsonobject2, ReportMark.class);
				if (StringUtil.isEmpty(mark.getOriginalContent())) {
					// 录入阶段,只能改变状态
					mark2.setStatus(mark.getStatus());
					mark2.setEditBy(SecurityUtil.getSecurityUser().getSysUser().getName());
					mark2.setEditTime(new Date());
					mark2.setItemValue(mark.getItemValue());
				} else {
					// 审核阶段，改变类型和内容
					mark2.setStatus(mark.getStatus());
					mark2.setMarkType(mark.getMarkType());
					mark2.setMarkContent(mark.getMarkContent());

				}

				String contentNew = JSONObject.toJSONString(mark2);
				this.rtPageDao.createSQLQuery("update rt_marks set mark_content=?,status=? where id = ?", contentNew,mark.getStatus(), mark.getId()).executeUpdate();

			}
		} else {
			// 新增
			String id = Utils.uuid();
			mark.setId(id);
			String userName = SecurityUtil.getSecurityUser().getSysUser().getName();
			mark.setMarkBy(userName);
			mark.setMarkTime(new Date());
			mark.setStatus("0");
			String contentNew = JSONObject.toJSONString(mark);
			this.rtPageDao
					.createSQLQuery("insert into rt_marks values (?,?,?,?,?,?,?,?,?,?)", id, this.fkBusinessId, this.rtCode,mark.getId(), rtExportData.getName(), contentNew, userId, new Date(),mark.getPage(),mark.getStatus())
					.executeUpdate();
		}*/

	}

	@Override
	public void imageFlush() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sign() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void signFlush() throws Exception {
		// TODO Auto-generated method stub

	}

	public static Map<String, RtExportData> transToMap(net.sf.json.JSONArray list, String filter) {
		Map<String, RtExportData> returnMap = new HashMap<String, RtExportData>();
		
		for (int i = 0;i<list.length();i++) {
			net.sf.json.JSONObject map = list.getJSONObject(i);
			String name = String.valueOf(map.get("itemName"));
			System.out.println("---------------"+name);
			if (StringUtil.isEmpty(name)) {
				continue;
			}
			System.out.println("---" + name + "--" + String.valueOf(map.get("itemValue")) + "---");
			String value = String.valueOf(map.get("itemValue"));
			String type = String.valueOf(map.get("type"));
			String color = String.valueOf(map.get("color"));
			String bold = String.valueOf(map.get("bold"));
			String italic = String.valueOf(map.get("italic"));
			String size = String.valueOf(map.get("size"));
			String family = String.valueOf(map.get("family"));
			String image = String.valueOf(map.get("image")).replace(";", ",");
			String pageNo = String.valueOf(map.get("pageNo"));
			String markContent = null;
			
			if(map.has("markContent")) {
				markContent = String.valueOf(map.get("markContent"));
			}

			RtExportData rtExportData = new RtExportData();
			rtExportData.setValue(value);
			rtExportData.setRemark(color);
			rtExportData.setType(type);
			rtExportData.setBold(bold);
			rtExportData.setItalic(italic);
			rtExportData.setSize(size);
			rtExportData.setFamily(family);
			rtExportData.setImage(image);
			rtExportData.setPageNo(pageNo);
			rtExportData.setMarkContent(markContent);
			Map<String, Object> funcMap = new HashMap<String, Object>();
			rtExportData.setFuncMap(funcMap);

			if (isColor(color)) {
				funcMap.put(RtExportDataType.EXPORT_DATA_COLOR, color);
			}
			if (StringUtil.isNotEmpty(image)) {
				funcMap.put(RtExportDataType.EXPORT_DATA_IMAGE, image);
			}
			if (bold != null && !bold.equals("400")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_BOLD, bold);
			}
			if (italic != null && !italic.equals("normal")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_ITALIC, italic);
			}
			if (size != null && !size.equals("14px")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_SIZE, size);
			}
			if (family != null && !family.equals("宋体")) {
				funcMap.put(RtExportDataType.EXPORT_DATA_FAMILY, family);
			}
			if ( markContent != null&&!StringUtil.isEmpty(markContent) && !"null".equals(markContent)) {
				funcMap.put(RtExportDataType.EXPORT_DATA_MARK, markContent);
			}
			// pingZhou2017/03/17修改 基础信息加上统一前缀
			if (name.contains(filter) || name.contains("FK") || name.contains("fk") || name.contains("picture") 
					|| name.contains("base__")||name.startsWith(RtPath.getPropetyValue("inspect_record", "record")+"__")
					||name.startsWith(RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__")) {
				// name.contains(filter) ||name.contains("FK") ||
				// name.contains("fk"))&&

				if (value != null && !"null".equals(value)&& StringUtil.isNotEmpty(value)) {
					// 
					// pingZhou2017/03/17修改 基础信息加上统一前缀
					if (name.contains("base__")) {
						// 保存时去掉前缀
						name = name.substring(6,name.length());
					}
					rtExportData.setName(name);
					returnMap.put(name+"__"+pageNo, rtExportData);
				} else{// if (StringUtil.isNotEmpty(markContent) && !"null".equals(markContent) && markContent != null) {
					if (name.contains("base__")) {
						// 保存时去掉前缀
						name = name.substring(6,name.length());
					}
					rtExportData.setName(name);
					returnMap.put(name+"__"+pageNo, rtExportData);
				}
			}
		}
		return returnMap;
	}
}
