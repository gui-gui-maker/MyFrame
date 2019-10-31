package com.lsts.employee.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.pub.fileupload.service.AttachmentTsManager;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.KHSecuritySaltSource;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.FileUtil;
import com.khnt.utils.Md5Util;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.inspection.dao.ReportItemRecordDao;
import com.lsts.inspection.dao.ReportPicValueDao;
import com.lsts.inspection.service.ReportPicValueService;

/**
 * 人员信息业务逻辑对象
 * @ClassName EmployeesService
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-03 下午03:17:00
 */
@Service("employeesService")
public class EmployeesService extends EntityManageImpl<Employee, EmployeesDao>{

	@Autowired
	private EmployeesDao employeesDao;
	@Autowired
	AttachmentDao attachmentDao;
	@Autowired
	private AttachmentTsManager attachmentTsManager;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private ReportPicValueService reportPicValueService;
	@Autowired
	private ReportItemRecordDao reportItemRecordDao;
	
	private static ArrayList<Field> employeeFields;
	
	/** 附件的存储方式 */
	private static String attachmentType;

	/** 如果附件的存储方式为磁盘时,移动检验现场图片存放位置 */
	private static String mo_attachmentPath;
	
	/** 如果附件的存储方式为磁盘时,移动检验不合格图片存放位置 */
	private static String mo_bhg_attachmentPath;
	
	/** 如果附件的存储方式为磁盘时,移动终止检验图片存放位置 */
	private static String mo_zzjy_attachmentPath;
	
	/** 如果附件的存储方式为磁盘时,校核人员手写签字图片存放位置 */
	private static String mo_sign_attachmentPath;

	/** #如果附件的存储方式为磁盘时，存放位置类型 */
	private static String attachmentPathType;
	
	/** 如果附件的存储方式为磁盘时,罐车移动检验部位示意图存放位置 */
	private static String mo_gc_attachmentPath;

	/** 附件存储类型：文件路径类别 */
	public static final String ATTACH_PATH_TYPE = "attachmentPathType";

	/** 附件存储类型：文件路径 */
	public static final String ATTACH_PATH = "mo_attachmentPath";

	/** 附件存储类型：文件路径 */
	public static final String ATTACH_TYPE = "attachmentType";

	/** 附件存储类型：磁盘文件系统 */
	public static final String SAVE_TYPE_DISK = "disk";

	/** 附件存储类型：数据库 */
	public static final String SAVE_TYPE_DB = "db";

	/** 磁盘存储位置：相对路径 */
	public static final String SAVE_PATH_R = "relative";

	/** 磁盘存储位置：绝对路径 */
	public static final String SAVE_PATH_A = "absolute";
	
	protected static final String INIT_PASSWORD = "123456";

	static {
		employeeFields = new ArrayList<Field>();
		Field[] temp = Employee.class.getDeclaredFields();
		for (Field f : temp) {
			employeeFields.add(f);
		}

	}
	
	public EmployeesService() {
		super();
		// 系统配置参数初始化
		SysParaInf sp = Factory.getSysPara();
		attachmentType = sp.getProperty("attachmentType", SAVE_TYPE_DISK);
		mo_attachmentPath = sp.getProperty("mo_attachmentPath");
		mo_bhg_attachmentPath = sp.getProperty("mo_bhg_attachmentPath");
		mo_zzjy_attachmentPath = sp.getProperty("mo_zzjy_attachmentPath");
		mo_sign_attachmentPath = sp.getProperty("mo_sign_attachmentPath");
		mo_gc_attachmentPath = sp.getProperty("mo_gc_attachmentPath");
		attachmentPathType = sp.getProperty("attachmentPathType", "relative");
	}
	
	// 保存人员信息
	public void saveEmployee(Employee entity, String uploadFiles){
		employeesDao.save(entity);	// 1、保存人员信息
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentTsManager.setAttachmentBusinessId(file, entity.getId());	// 2、保存人员电子签名
				}
			}
		}
		// 3、保存人员登录帐号信息
		employeeManager.produceUser(entity.getId());
		
		// 4、更新登录人员姓名和部门信息
		
		String sql = "update sys_user set ORG_ID = ?,NAME=?,LAST_PWD_DATE=?  where EMPLOYEE_ID = ?";
		employeesDao.createSQLQuery(sql, entity.getOrg().getId(), entity.getName(), new Date(), entity.getId()).executeUpdate();
		
	}
	
	// 删除人员信息
    public void delete(String ids) {
    	employeesDao.delete(ids);
    }
    
    // 根据身份证号查询用户信息
	public Employee queryEmployeeByIdNo(String idNo) throws Exception {
		return employeesDao.queryEmployeeByIdNo(idNo);
    }
	
	// 根据用户ID查询用户信息
	public Employee queryEmployeeById(String id) throws Exception {
		return employeesDao.queryEmployeeById(id);
    }
	
	// 根据部门ID和姓名查询登录用户信息
	public String getUserID(String org_id,String name){
		return employeesDao.getUserID(org_id, name);
    }
	
	// 根据部门ID查询部门人员信息
	public Map<String, Object> getEmployees(String org_id){
		return employeesDao.getEmployees(org_id);
	}
	
	// 根据姓名查询登录用户信息
	public String getUserID(String name){
		return employeesDao.getUserID(name);
	}
	
	// 根据角色姓名查询登录用户信息
	public String getEmpIDByRoleName(String role_name, String org_id){
		return employeesDao.getEmpIDByRoleName(role_name, org_id);
	}
	
	/**
	 * 根据部门ID和姓名查询登录用户信息
	 * @param org_id -- 部门ID
	 * @param name -- 姓名
	 * @author GaoYa
	 * @date 2016-01-11 下午19:06:00
	 */
	public String getEmployee(String org_id,String name){
		return employeesDao.getEmployee(org_id, name);
	}
	
	/**
	 * 根据人员ID查询用户手机号码
	 * @param emp_id -- employee id
	 * @return mobile -- 手机号码
	 * 
	 * @author GaoYa
	 * @date 2016-10-16 上午11:24:00
	 */
	public String getMobile(String emp_id){
		return employeesDao.getMobile(emp_id);
	}
	
	/**
	 * 根据user_id查询用户手机号码
	 * @param user_id -- sys_user id
	 * @return mobile -- 手机号码
	 * 
	 * @author GaoYa
	 * @date 2017-08-21 下午17:46:00
	 */
	public String getMobileByUserId(String user_id){
		return employeesDao.getMobileByUserId(user_id);
	}
	
	/**
	 * 保存附件
	 * 
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * 
	 * @throws Exception
	 */
	public HashMap<String, Object> saveAttached(InputStream inputStream, Attachment attachment, String saveType, boolean saveDB)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String attType;
			if (StringUtil.isEmpty(saveType)) {
				attType = attachmentType;
			} else {
				attType = saveType.equals("1") ? SAVE_TYPE_DB : SAVE_TYPE_DISK;
			}

			// 如果指定了唯一业务名称，先从数据库删除该附件
			if (StringUtil.isNotEmpty(attachment.getBusUniqueName())) {
				Attachment busUniqeAtt = this.getBusUniqueAttachment(attachment.getBusUniqueName());
				if (busUniqeAtt != null)
					this.deleteAttach(busUniqeAtt);
			}

			attachment.setSaveType(attType);// 设置存储类别
			attachment.setUploadTime(Calendar.getInstance().getTime());// 上传时间设置
			boolean isExist = StringUtil.isNotEmpty(attachment.getId());

			if(StringUtil.isEmpty(mo_attachmentPath)){
				map.put("success", false);
				map.put("msg", "系统暂未配置移动检验图片上传路径，请联系系统管理员！");
				return map;
			}
			// 将文件存储到磁盘
			if (SAVE_TYPE_DISK.equals(attType)) {
				String realPath;// 文件路径
				if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
					realPath = Factory.getWebRoot() + mo_attachmentPath;
				else
					realPath = mo_attachmentPath;// 绝对路径

				String newName = this.createRandomFile(attachment.getFileName(), realPath);
				attachment.setFilePath(newName);

				String existPath = null;
				if (isExist) {
					Attachment currentAttach = this.attachmentDao.get(attachment.getId());
					existPath = currentAttach.getFilePath();
					BeanUtils.copyProperties(attachment, currentAttach);
				}

				// 根据参数saveDB检查是否需要将附件信息写入数据库
				else if (saveDB)
					// 将附件信息持久化
					this.attachmentDao.save(attachment);

				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);
				FileUtil.writeFile(realPath + File.separator + newName, data);// 写入文件系统

				// 删除之前的文件
				if (StringUtil.isNotEmpty(existPath))
					FileUtil.delAllFile(realPath + File.separator + existPath);
			}
			// 将文件存储到DB
			else {
				if (isExist)// 如果已经存在，先删除
					this.deleteAttach(attachment);
				this.attachmentDao.saveAttachToDB(attachment, inputStream);// 写入数据库
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "图片上传失败！错误代码："+e.toString());
			return map;
		}
		
		map.put("success", true);
		return map;
	}
	
	/**
	 * 保存附件
	 * 
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * @param pic_type 
	 *            图片类型（现场图片：nomal_pic 不合格图片：bhg_pic 终止检验图片：zzjy_pic 手写签字图片：EXAMINE_NAME 罐车检验部位示意图：GC）
	 *            罐车检验部位示意图：GC_8_CHDBWT、GC_17_QXWZSYT、GC_18_QXWZSYT、GC_19_QXWZSYT
	 * @throws Exception
	 * @author GaoYa
	 * @date 2016-01-11 下午21:55:00
	 */
	public HashMap<String, Object> saveAttachment(InputStream inputStream, Attachment attachment, String saveType, boolean saveDB, String pic_type)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String attType;
			if (StringUtil.isEmpty(saveType)) {
				attType = attachmentType;
			} else {
				attType = saveType.equals("1") ? SAVE_TYPE_DB : SAVE_TYPE_DISK;
			}

			// 如果指定了唯一业务名称，先从数据库删除该附件
			if (StringUtil.isNotEmpty(attachment.getBusUniqueName())) {
				Attachment busUniqeAtt = this.getBusUniqueAttachment(attachment.getBusUniqueName());
				if (busUniqeAtt != null)
					this.deleteAttach(busUniqeAtt);
			}

			attachment.setSaveType(attType);// 设置存储类别
			attachment.setUploadTime(Calendar.getInstance().getTime());// 上传时间设置
			// 当文件类型为图片时，检验图片类别
			// 现场图片：nomal_pic 不合格图片：bhg_pic 终止检验图片：zzjy_pic 手写签字图片：EXAMINE_NAME 
			// 罐车检验部位示意图：GC_8_CHDBWT、GC_17_QXWZSYT、GC_18_QXWZSYT、GC_19_QXWZSYT
			attachment.setJy_pic_category(pic_type);	
			boolean isExist = StringUtil.isNotEmpty(attachment.getId());

			String attachmentPath = mo_attachmentPath;
			if ("bhg_pic".equals(pic_type)) {
				attachmentPath = mo_bhg_attachmentPath;
			} else if ("zzjy_pic".equals(pic_type)) {
				attachmentPath = mo_zzjy_attachmentPath;
			} else if ("EXAMINE_NAME".equals(pic_type)) {
				attachmentPath = mo_sign_attachmentPath;
			} else if ("GC_8_CHDBWT".equals(pic_type) || "GC_17_QXWZSYT".equals(pic_type)
					|| "GC_18_QXWZSYT".equals(pic_type) || "GC_19_QXWZSYT".equals(pic_type)) {
				attachmentPath = mo_gc_attachmentPath;			
			}
			
			if(StringUtil.isEmpty(attachmentPath)){
				map.put("success", false);
				map.put("msg", "系统暂未配置移动检验图片上传路径，请联系系统管理员！");
				return map;
			}
			// 将文件存储到磁盘
			if (SAVE_TYPE_DISK.equals(attType)) {
				String realPath;// 文件路径
				if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
					realPath = Factory.getWebRoot() + attachmentPath;
				else
					realPath = attachmentPath;// 绝对路径

				String newName = this.createRandomFile(attachment.getFileName(), realPath);
				attachment.setFilePath(newName);

				String existPath = null;
				if (isExist) {
					Attachment currentAttach = this.attachmentDao.get(attachment.getId());
					existPath = currentAttach.getFilePath();
					BeanUtils.copyProperties(attachment, currentAttach);
				}

				// 根据参数saveDB检查是否需要将附件信息写入数据库
				else if (saveDB)
					// 将附件信息持久化
					this.attachmentDao.save(attachment);

				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);
				FileUtil.writeFile(realPath + File.separator + newName, data);// 写入文件系统

				// 删除之前的文件
				if (StringUtil.isNotEmpty(existPath))
					FileUtil.delAllFile(realPath + File.separator + existPath);
			}
			// 将文件存储到DB
			else {
				if (isExist)// 如果已经存在，先删除
					this.deleteAttach(attachment);
				this.attachmentDao.saveAttachToDB(attachment, inputStream);// 写入数据库
			}
			
			// 当图片为校核人员手写签字时，压缩图片
			if("EXAMINE_NAME".equals(pic_type)){
				File oFile = new File(attachment.getFilePath());
				this.attachmentTsManager.compressPic(oFile, attachment.getId(), 80, 40);
				Attachment compressAtta = new Attachment();
				BeanUtils.copyProperties(attachment, compressAtta);
				compressAtta.setId(null);
				compressAtta.setFileName("compress_pic_"+attachment.getFileName());
				this.attachmentDao.saveAttachToDB(compressAtta, inputStream);// 写入数据库
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "图片上传失败！错误代码："+e.toString());
			return map;
		}
		
		map.put("success", true);
		return map;
	}
	
	/**
	 * 保存附件（罐车原始记录上传图片专用）
	 * 
	 * @param inputStream
	 *            文件流
	 * @param attachment
	 *            附件信息BEAN
	 * @param saveType
	 *            存储类别，数据库/文件系统，参考AttachmentManager.SAVE_TYPE_DISK,
	 *            AttachmentManager.SAVE_TYPE_DB
	 * @param saveDB
	 *            是否往数据库写入附件信息，此项只在存储类型为文件系统时有效
	 * @param pic_type 
	 *            图片类型（现场图片：nomal_pic 不合格图片：bhg_pic 终止检验图片：zzjy_pic 手写签字图片：EXAMINE_NAME 罐车检验部位示意图：GC）
	 *            罐车检验部位示意图：GC_8_CHDBWT、GC_17_QXWZSYT、GC_18_QXWZSYT、GC_19_QXWZSYT
	 * @param report_id 
	 *            报告模板id
	 * @param userId
	 *            用户id
	 * @param userName
	 *            用户姓名
	 * @throws Exception
	 * @author GaoYa
	 * @date 2018-04-10 下午17:02:00
	 */
	public HashMap<String, Object> saveAttachment2(CommonsMultipartFile file, Attachment attachment, String saveType,
			boolean saveDB, String pic_type, String report_id, String userId, String userName) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			InputStream inputStream = file.getInputStream();

			String attType;
			if (StringUtil.isEmpty(saveType)) {
				attType = attachmentType;
			} else {
				attType = saveType.equals("1") ? SAVE_TYPE_DB : SAVE_TYPE_DISK;
			}

			// 如果指定了唯一业务名称，先从数据库删除该附件
			if (StringUtil.isNotEmpty(attachment.getBusUniqueName())) {
				Attachment busUniqeAtt = this.getBusUniqueAttachment(attachment.getBusUniqueName());
				if (busUniqeAtt != null)
					this.deleteAttach(busUniqeAtt);
			}

			attachment.setSaveType(attType);// 设置存储类别
			attachment.setUploadTime(Calendar.getInstance().getTime());// 上传时间设置
			// 当文件类型为图片时，检验图片类别
			// 现场图片：nomal_pic 不合格图片：bhg_pic 终止检验图片：zzjy_pic 手写签字图片：EXAMINE_NAME
			// 罐车检验部位示意图：GC_8_CHDBWT、GC_17_QXWZSYT、GC_18_QXWZSYT、GC_19_QXWZSYT
			attachment.setJy_pic_category(pic_type);
			boolean isExist = StringUtil.isNotEmpty(attachment.getId());

			String attachmentPath = mo_attachmentPath;
			if ("bhg_pic".equals(pic_type)) {
				attachmentPath = mo_bhg_attachmentPath;
			} else if ("zzjy_pic".equals(pic_type)) {
				attachmentPath = mo_zzjy_attachmentPath;
			} else if ("EXAMINE_NAME".equals(pic_type)) {
				attachmentPath = mo_sign_attachmentPath;
			} else if ("GC_8_CHDBWT".equals(pic_type) || "GC_17_QXWZSYT".equals(pic_type)
					|| "GC_18_QXWZSYT".equals(pic_type) || "GC_19_QXWZSYT".equals(pic_type)) {
				attachmentPath = mo_gc_attachmentPath;

				// 罐车部位示意图写入报告图片库处理
				String item_name = "";
				if ("GC_8_CHDBWT".equals(pic_type)) {
					item_name = "PICTURETEXT8";
				} else if ("GC_17_QXWZSYT".equals(pic_type)) {
					item_name = "PICTURETEXT17";
				} else if ("GC_18_QXWZSYT".equals(pic_type)) {
					item_name = "PICTURETEXT18";
				} else if ("GC_19_QXWZSYT".equals(pic_type)) {
					item_name = "PICTURETEXT19";
				}
				String reportPic_id = reportPicValueService.fileUp(file, item_name, attachment.getBusinessId(), "B");
				if(StringUtil.isNotEmpty(reportPic_id)){
					// 增加罐车分项报告部位示意图
					reportItemRecordDao.insertReportItemRecord(StringUtil.getUUID(), report_id, item_name, reportPic_id,
							attachment.getBusinessId(), userId, userName);
				}
			}

			if (StringUtil.isEmpty(attachmentPath)) {
				map.put("success", false);
				map.put("msg", "系统暂未配置移动检验图片上传路径，请联系系统管理员！");
				return map;
			}
			// 将文件存储到磁盘
			if (SAVE_TYPE_DISK.equals(attType)) {
				String realPath;// 文件路径
				if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
					realPath = Factory.getWebRoot() + attachmentPath;
				else
					realPath = attachmentPath;// 绝对路径

				String newName = this.createRandomFile(attachment.getFileName(), realPath);
				attachment.setFilePath(newName);

				String existPath = null;
				if (isExist) {
					Attachment currentAttach = this.attachmentDao.get(attachment.getId());
					existPath = currentAttach.getFilePath();
					BeanUtils.copyProperties(attachment, currentAttach);
				}

				// 根据参数saveDB检查是否需要将附件信息写入数据库
				else if (saveDB)
					// 将附件信息持久化
					this.attachmentDao.save(attachment);

				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);
				FileUtil.writeFile(realPath + File.separator + newName, data);// 写入文件系统

				// 删除之前的文件
				if (StringUtil.isNotEmpty(existPath))
					FileUtil.delAllFile(realPath + File.separator + existPath);
			}
			// 将文件存储到DB
			else {
				if (isExist)// 如果已经存在，先删除
					this.deleteAttach(attachment);
				this.attachmentDao.saveAttachToDB(attachment, inputStream);// 写入数据库
			}

			// 当图片为校核人员手写签字时，压缩图片
			if ("EXAMINE_NAME".equals(pic_type)) {
				File oFile = new File(attachment.getFilePath());
				this.attachmentTsManager.compressPic(oFile, attachment.getId(), 80, 40);
				Attachment compressAtta = new Attachment();
				BeanUtils.copyProperties(attachment, compressAtta);
				compressAtta.setId(null);
				compressAtta.setFileName("compress_pic_" + attachment.getFileName());
				this.attachmentDao.saveAttachToDB(compressAtta, inputStream);// 写入数据库
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "图片上传失败！错误代码：" + e.toString());
			return map;
		}

		map.put("success", true);
		return map;
	}
	
	/**
	 * 以业务指定唯一标识获取附件信息
	 * 
	 * @param busUniqueName
	 * @return
	 */
	public Attachment getBusUniqueAttachment(String busUniqueName) {
		List<Attachment> atts = attachmentDao.findBy("busUniqueName", busUniqueName);
		if (atts.size() == 1)
			return atts.get(0);
		else
			return null;
	}
	
	/**
	 * 根据ID、文件相对路径删除文件。支持多文件删除(ids,path使用分号;分隔)
	 */
	public void deleteAttach(String ids, String path) throws Exception {
		// 按照指定ID删除文件
		if (StringUtil.isNotEmpty(ids)) {
			for (String id : ids.split(";")) {
				Attachment attachment = attachmentDao.get(id);
				this.deleteAttach(attachment);
			}
		}

		// 删除指定路径的文件
		if (StringUtil.isNotEmpty(path)) {
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + mo_attachmentPath;
			else
				realPath = mo_attachmentPath;// 绝对路径
			for (String pstr : path.split(";")) {
				realPath += File.separator + pstr;
				FileUtil.delAllFile(realPath);
			}
		}
	}

	/**
	 * 删除附件对象，如果有文件也同时删除
	 * 
	 * @param attachment
	 * @param realPath
	 * @throws Exception
	 */
	public void deleteAttach(Attachment attachment) throws Exception {
		if (attachment == null)
			return;
		if (attachment.getSaveType().equals(SAVE_TYPE_DISK)) {// 存储于磁盘，需要删除磁盘文件
			String realPath;// 文件路径
			if (SAVE_PATH_R.equals(attachmentPathType)) // 相对路径
				realPath = Factory.getWebRoot() + mo_attachmentPath;
			else
				realPath = mo_attachmentPath;// 绝对路径
			realPath += File.separator + attachment.getFilePath();
			FileUtil.delAllFile(realPath);
		}

		this.attachmentDao.removeById(attachment.getId());// 从数据删除附件记录
	}

	/**
	 * 生成文件名
	 * 
	 * @param originalName
	 *            原始文件名
	 * @param realPath
	 *            上传目录
	 * @return
	 * @throws IOException
	 */
	protected String createRandomFile(String originalName, String realPath) throws IOException {
		// 获取原始文件的后缀
		String suffix = FileUtil.getSuffix(originalName);
		while (true) {
			// 文件名 = 当前时间+随机数
			String fileName = System.currentTimeMillis() + "" + new Double(899999 * Math.random() + 100000).intValue()
					+ suffix;
			
			File importdir = new File(realPath);
			if (!importdir.exists())
				importdir.mkdirs();
			
			File file = new File(realPath + "/" + fileName);
			if (file.exists() && file.isFile()) {// 如果文件已存在则重新生成文件名
				continue;
			}
			if (!file.createNewFile()) {
				continue;
			}
			return fileName;
		}
	}
	
	/** 根据姓名或电话号码获取人员信息
	 * @param q
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List<Employee> sugguest(String q) throws IllegalArgumentException, IllegalAccessException {
		return employeesDao.suggest(q);
	}
	
	/** 根据机构名称获取机构信息
	 * @param orgName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List<Org> getOrg(String orgName) throws IllegalArgumentException, IllegalAccessException {
		return employeesDao.getOrg(orgName);
	}
	
	// 根据userId和userName查询Employee信息
	public Employee queryEmployeeByUser(String userId, String userName) {
		return employeesDao.queryEmployeeByUser(userId, userName);
	}
	
	/**
	 * 验证财务独立密码
	 * 
	 * @param employee_id 
	 * @param pwd

	 * @throws KhntException
	 */
	public boolean validSecondPwd(String employee_id, String pwd) throws KhntException {
		boolean result = false;
		String second_pwd = employeesDao.getSecondPwd(employee_id);
		Object salt = KHSecuritySaltSource.getSalt();
		if (!Md5Util.checkEncode(pwd, second_pwd, salt)) {
			result = false;
		}else{
			result = true;
		}
		return result;
	}
	
	/**
	 * 修改财务独立密码
	 * 
	 * @param employee_id 
	 * @param oldPwd
	 * @param newPwd
	 * @throws KhntException
	 */
	public void mdySecondPwd(String employee_id, String oldPwd, String newPwd) throws KhntException {
		String second_pwd = employeesDao.getSecondPwd(employee_id);
		Object salt = KHSecuritySaltSource.getSalt();
		if (!Md5Util.checkEncode(oldPwd, second_pwd, salt)) {
			log.info("修改财务独立密码时发生错误：原密码错误！");
			throw new KhntException("原密码错误！");
		}
		try {
			newPwd = Md5Util.encodeCode(newPwd, salt);
			updateSecondPwd(employee_id, newPwd);
		} catch (Exception e) {
			log.info("修改财务独立密码时发生错误!" + e.getMessage());
			throw new KhntException("财务独立密码设置错误." + e.getMessage());
		}
	}

	/**
	 * 恢复财务独立密码，重置为初始密码（123456）
	 * 
	 * @param ids
	 */
	public void initSecondPwd(String ids) {
		 String arr[] = ids.split(",");
		 for (int i = 0; i < arr.length; i++) {
			Object salt = KHSecuritySaltSource.getSalt();
			updateSecondPwd(arr[i], Md5Util.encodeCode(INIT_PASSWORD, salt));	
		 }
	}
	
	/**
	 * 验证印章独立密码
	 * 
	 * @param employee_id 
	 * @param pwd

	 * @throws KhntException
	 */
	public boolean validPrintPwd(String employee_id, String pwd) throws KhntException {
		boolean result = false;
		String print_pwd = employeesDao.getPrintPwd(employee_id);
		Object salt = KHSecuritySaltSource.getSalt();
		if (!Md5Util.checkEncode(pwd, print_pwd, salt)) {
			result = false;
		}else{
			result = true;
		}
		return result;
	}
	
	/**
	 * 修改印章密码
	 * 
	 * @param employee_id 
	 * @param oldPwd
	 * @param newPwd
	 * @throws KhntException
	 */
	public void mdyPrintPwd(String employee_id, String oldPwd, String newPwd) throws KhntException {
		String print_pwd = employeesDao.getPrintPwd(employee_id);
		Object salt = KHSecuritySaltSource.getSalt();
		if (!Md5Util.checkEncode(oldPwd, print_pwd, salt)) {
			log.info("修改印章密码时发生错误：原密码错误！");
			throw new KhntException("原密码错误！");
		}
		try {
			newPwd = Md5Util.encodeCode(newPwd, salt);
			updatePrintPwd(employee_id, newPwd);
		} catch (Exception e) {
			log.info("修改印章密码时发生错误！" + e.getMessage());
			throw new KhntException("印章印章密码设置错误." + e.getMessage());
		}
	}

	/**
	 * 恢复印章独立密码，重置为初始密码（123456）
	 * 
	 * @param ids
	 */
	public void initPrintPwd(String ids) {
		 String arr[] = ids.split(",");
		 for (int i = 0; i < arr.length; i++) {
			Object salt = KHSecuritySaltSource.getSalt();
			updatePrintPwd(arr[i], Md5Util.encodeCode(INIT_PASSWORD, salt));	
		 }
	}
	
	/**
	 * 修改财务独立密码
	 * 
	 * @param employee_id --
	 *            人员信息ID
	 * @param second_pwd --
	 *            财务独立密码
	 * @return
	 */
	public void updateSecondPwd(String employee_id, String second_pwd) {
		String sql = "update employee set SECOND_PWD = ? where id = ?";
		employeesDao.createSQLQuery(sql, second_pwd, employee_id).executeUpdate();
	}
	
	/**
	 * 修改印章独立密码
	 * 
	 * @param employee_id --
	 *            人员信息ID
	 * @param print_pwd --
	 *            印章独立密码
	 * @return
	 */
	public void updatePrintPwd(String employee_id, String print_pwd) {
		String sql = "update employee set PRINT_PWD = ? where id = ?";
		employeesDao.createSQLQuery(sql, print_pwd, employee_id).executeUpdate();
	}
	
	// 新增人员岗位信息
    public void addEmpPosition(String emp_id, String position_id) {
    	employeesDao.createSQLQuery("insert into SYS_EMPLOYEE_POSITION(POSITION_ID, EMPLOYEE_ID) values('"+position_id+"','"+emp_id+"')").executeUpdate();
    }
    
    // 删除人员岗位信息
    public void delEmpPosition(String emp_id) {
    	employeesDao.createSQLQuery("delete from SYS_EMPLOYEE_POSITION where EMPLOYEE_ID='"+emp_id+"'").executeUpdate();
    }
    
    // 根据employee_id查询人员岗位信息
    public List<String> getPositionIDs(String employee_id){
    	return employeesDao.getPositionIDs(employee_id);
    }
    
    /**
     * 根据Employee ID查询SYS_USER ID信息
     * @param emp_id -- Employee ID
     * 
     * @return
     */
    public String queryUserIdByEmpId(String emp_id) {
    	return employeesDao.queryUserIdByEmpId(emp_id);
    }

    /**
     * 单点登陆密码验证 
     * author pingZhou
     * @param name 用户名
     * @param password 密码MD5
     * @return
     * @throws Exception
     */
	public HashMap<String, Object> checkPass(String name, String password) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject result = new JSONObject();
		String strResult= null;
		//http://sso.scsei.org.cn----------------192.168.3.15
		String strSign = java.net.URLEncoder.encode(name,"UTF-8");
		String url1 = "http://192.168.3.15/sso_service.php?action=login&"
				+ "apiIdentity=kh.scsei.org.cn"
				+ "&apiKey=2774ab4e730554c8a0b097d610fefe16"
				+ "&userIdentity="+name+"&userPasswordHash="+password;
	   String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
	       
        java.net.URL url = new URL(strURL);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        strResult=reader.readLine(); 
       result = JSONObject.parseObject(strResult);
       int flag = result.getIntValue("errorcode");
       if(flag==0){
    	   //用户名密码错误
    	   JSONObject data = result.getJSONObject("data");
    	   String userMobile = data.getString("userMobile");
			List<Employee>  empL = employeesDao.suggest(userMobile);
			if(empL.size()>0){
				Employee emp = empL.get(0);
				String sql = "select u.account from sys_user u where u.employee_id = ? and u.status='1'";
				List<Object> list = employeesDao.createSQLQuery(sql, emp.getId()).list();
				if(list.size()>0){
					
					map.put("success", true);
					map.put("userName", list.get(0).toString());
					map.put("userSessionKey", data.getString("userSessionKey"));
					map.put("userSessionKeyTimeout", data.getString("userSessionKeyTimeout"));
				}else{
					System.out.println("------------------没找到用户信息---------------------");
					map.put("success", false);
					throw new KhntException("没有用户信息，请联系028-86607814！");
				}
				
				
			}else{
				System.out.println("------------------没找到人员信息---------------------");
				//手机号码信息不一致提示
				map.put("success", false);
				throw new KhntException("用户名或密码错误，手机号信息不一致，请联系028-86607814！");
			}
			
       }else{
    	   //用户名正确
    	   System.out.println("------------------接口验证失败---------------------");
			map.put("success", false);
			throw new KhntException("用户名或密码错误！");
       }
       
		
		
		return map;
	}

	/**
	 * 生成桌面登陆快捷方式
	 * author pingZhou
	 * @param name
	 * @param password
	 */
	public void createLoginFile(String name, String password) {
		//File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
				//String desktopPath = desktopDir.getAbsolutePath(); 
				 String str="<html><head><title>正在跳转</title></head> <body> "
				 		+ "<script language='javascript'>"
				 		+ "document.location = 'http://pt.scsei.org.cn?userName="+name+"&password="+password+"'</script>"
				 		+ "</body></html>"; 
				 
				 FileWriter writer;  
				 try {        
					 writer = new FileWriter("D:"+File.separator+"TEMP"+ File.separator +"四川特检平台.html");   
					 writer.write(str);      
					 writer.flush();       
					 writer.close();     
				} catch (IOException e) {   
					 e.printStackTrace();   
				} 
	}

	/**
	 * 单点登陆修改密码
	 * author pingZhou
	 * @param oldPwd 旧密码MD5
	 * @param newPwd 新密码MD5
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updatePassword(String oldPwd, String newPwd) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		JSONObject result = new JSONObject();
		String strResult= null;
		String sql = "select e.mobile_tel from sys_user u,employee e where e.id = u.employee_id and u.id=?";
		List<Object> list = employeesDao.createSQLQuery(sql, user.getId()).list();
		if(list.size()==0||list.get(0)==null){
			throw new KhntException("没找到用户的手机号码信息!");
		}
		String mobile = list.get(0).toString();
		//http://sso.scsei.org.cn
		String strSign = java.net.URLEncoder.encode(mobile,"UTF-8");//sso.scsei.org.cn
		String url1 = "http://192.168.3.15/sso_service.php?action=login&"
				+ "apiIdentity=kh.scsei.org.cn"
				+ "&apiKey=2774ab4e730554c8a0b097d610fefe16"
				+ "&userIdentity="+mobile+"&userPasswordHash="+oldPwd;
	   String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
	       
        java.net.URL url = new URL(strURL);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        strResult=reader.readLine(); 
       result = JSONObject.parseObject(strResult);
       int flag = result.getIntValue("errorcode");
       if(flag==0){
    	 //用户名正确
    	   JSONObject data= result.getJSONObject("data");
           String userName = data.getString("userName");//sso.scsei.org.cn
   			String url2 = "http://192.168.3.15/sso_service.php?action=UpdatePassword&"
   				+ "apiIdentity=kh.scsei.org.cn"
   				+ "&apiKey=2774ab4e730554c8a0b097d610fefe16"
   				+ "&setUserPasswordHash="+newPwd+"&userName="+userName;
   			String strURL2 = Factory.getSysPara().getProperty("GPS_URL", url2);
   	       
           java.net.URL ur2 = new URL(strURL2);
           HttpURLConnection httpConnection2 = (HttpURLConnection)ur2.openConnection();
           BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpConnection2.getInputStream()));   
          String strResult2=reader2.readLine(); 
          JSONObject result2 = JSONObject.parseObject(strResult2);
          int flag2 = result2.getIntValue("errorcode");
         
          if(flag2==0){
        	  map.put("success", true);
          }else{
        	  System.out.println("------------------修改密码失败---------------------");

  			map.put("success", false);
      	   //用户名错误
      	   throw new KhntException("修改密码失败!");
          }
			
       }else{
    	   System.out.println("------------------接口验证失败---------------------");

			map.put("success", false);
    	   //用户名错误
    	   throw new KhntException("原密码错误!");
    	 
       }
       
		
		
		return map;
	}
	
	/**
	 * 单点登陆重置密码
	 * author pingZhou
	 * @param oldPwd 旧密码MD5
	 * @param newPwd 新密码MD5
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> resetPassword(String userId) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		JSONObject result = new JSONObject();
		String strResult= null;
		String sql = "select e.mobile_tel,e.name from sys_user u,employee e where e.id = u.employee_id and u.id=?";
		List<Object> list = employeesDao.createSQLQuery(sql, userId).list();
		if(list.size()==0||list.get(0)==null){
			throw new KhntException("没找到用户的手机号码信息!");
		}
		String mobile = ((Object[])list.get(0))[0].toString();
		String name = ((Object[])list.get(0))[1].toString();
		//http://sso.scsei.org.cn
		String strSign = java.net.URLEncoder.encode(mobile,"UTF-8");//sso.scsei.org.cn
		String url1 = "http://192.168.3.15/sso_service.php?action=getUsers&"
				+ "apiIdentity=kh.scsei.org.cn"
				+ "&apiKey=2774ab4e730554c8a0b097d610fefe16";
	   String strURL = Factory.getSysPara().getProperty("GPS_URL", url1);
	       
        java.net.URL url = new URL(strURL);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        strResult=reader.readLine(); 
       result = JSONObject.parseObject(strResult);
       int flag = result.getIntValue("errorcode");
       if(flag==0){
    	   String userName = "";
    	   JSONArray data = result.getJSONArray("data");
    	   for (int i = 0; i < data.size(); i++) {
    		   JSONObject datai = (JSONObject) data.get(i);
    		   JSONObject profile = datai.getJSONObject("profile");
				if(name.equals(profile.getString("realName"))
						&&mobile.equals(profile.getString("mobileNumber"))
						&&datai.getBoolean("isEnabled")){
					userName = datai.getString("userName");
				}
    	   }
    	   
    	   if("".equals(userName)){
    		   throw new KhntException("没有找到对应的单点登陆用户信息!");
    	   }
    	 //用户名正确
    	  // JSONObject data= result.getJSONObject("data");
           //String userName = data.getString("userName");//sso.scsei.org.cn
   			String url2 = "http://192.168.3.15/sso_service.php?action=UpdatePassword&"
   				+ "apiIdentity=kh.scsei.org.cn"
   				+ "&apiKey=2774ab4e730554c8a0b097d610fefe16"
   				+ "&setUserPasswordHash=202cb962ac59075b964b07152d234b70&userName="+userName;
   			String strURL2 = Factory.getSysPara().getProperty("GPS_URL", url2);
   	       
           java.net.URL ur2 = new URL(strURL2);
           HttpURLConnection httpConnection2 = (HttpURLConnection)ur2.openConnection();
           BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpConnection2.getInputStream()));   
          String strResult2=reader2.readLine(); 
          JSONObject result2 = JSONObject.parseObject(strResult2);
          int flag2 = result2.getIntValue("errorcode");
         
          if(flag2==0){
        	  map.put("success", true);
        	  System.out.println("------------------重置密码成功-------用户："+userName+"---密码：123");
          }else{
        	  System.out.println("------------------重置密码失败---------------------");

  			map.put("success", false);
      	   //用户名错误
      	   throw new KhntException("重置密码失败!");
          }
			
       }else{
    	   System.out.println("------------------没有找到单点登陆用户信息---------------------");

			map.put("success", false);
    	   //用户名错误
    	   throw new KhntException("没有找到单点登陆用户信息!");
    	 
       }
       
		
		
		return map;
	}
	
	
	public String getEmpSignId(String userId,String type) {

		String signId = "";
		String sql = "select t.id from PUB_ATTACHMENT  t where t.business_id = ?";
		if("user".equals(type)){
			sql = "select t.id from PUB_ATTACHMENT  t,sys_user u,employee e where t.business_id = e.id and e.id = u.employee_id and u.id=?";
		}
		
		List<Object> list = employeesDao.createSQLQuery(sql, userId).list();
		if (list.size() > 0) {
			if (list.get(0) != null) {
				signId = list.get(0).toString();
			}
		}

		return signId;
	}
}
