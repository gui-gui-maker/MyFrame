package com.lsts.device.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.FileUtil;
import com.khnt.utils.StringUtil;
import com.lsts.constant.Constant;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceImportService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.service.EnterService;

/**
 * 设备信息导入控制器
 * 
 * @ClassName DeviceImportAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-04-02 下午02:24:00
 */
@Controller
@RequestMapping("/device/import")
public class DeviceImportAction {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DeviceImportService deviceImportService;
	@Autowired
	private EnterService enterService;

	private static final String CONTENT_TYPE = "text/html;charset=utf-8";

	/**
	 * 上传Excel文件、解析Excel并保存到数据库
	 * 
	 * @param request
	 * @param response
	 * @return JSON
	 * @author GaoYa
	 * @date 2014-04-02 下午02:40:00
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public HashMap<String, Object> upload(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		// 获取文件上传路径
		String importpath = Factory.getSysPara().getProperty("UPLOAD_PATH");
		if (StringUtil.isEmpty(importpath)) {
			return JsonWrapper.failureWrapper("错误：未配置系统文件上传路径！");
		}
		File importdir = new File(importpath);
		if (!importdir.exists())
			importdir.mkdirs();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile upfile = (CommonsMultipartFile) multipartRequest
				.getFile("file");
		if (upfile == null || StringUtil.isEmpty(upfile.getOriginalFilename())) {
			return JsonWrapper.failureWrapper("错误：文件名不能为空！");
		}

		// 验证上传文件格式
		boolean isexcel = false;
		if (FileUtil.getSuffix(upfile.getOriginalFilename()).equals(".xls")
				|| FileUtil.getSuffix(upfile.getOriginalFilename()).equals(
						".xlsx")) {
			isexcel = true;
		}
		if (!isexcel) {
			return JsonWrapper
					.failureWrapper("错误：系统不支持导入的文件格式，请导入设备信息excel文件，只支持后缀为xls或xlsx的文件格式！");
		}

		File file = new File(importdir, upfile.getOriginalFilename());
		if (file.exists()) {
			FileUtil.delAllFile(importdir);
		}

		try {
			writeFile(file, upfile.getInputStream()); // 写文件
			if (file.canRead()) {
				List<DeviceDocument> deviceList = new ArrayList<DeviceDocument>();
				Map<String, EnterInfo> enterInfosMap = new HashMap<String, EnterInfo>();
				readEnterInfosMap(enterInfosMap);
				List<String> errors = new ArrayList<String>();
				// 解析Excel文件
				parseExcelDatas(file, deviceList, enterInfosMap, errors);
				if (errors.isEmpty()) {
					// 保存数据
					deviceImportService.saveData(deviceList);
				} else {
					return JsonWrapper.failureWrapper("导入失败：" + errors);
				}
				FileUtil.delAllFile(importdir);
				return JsonWrapper.successWrapper("恭喜您，导入成功！");
			} else {
				return JsonWrapper.failureWrapper("导入文件不可读！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper("导入失败：" + e.getMessage());
		}
	}

	/**
	 * 根据文件路径解析文件（Excel）并保存到数据库
	 * 
	 * @param request
	 * @param filename --
	 *            文件名
	 * @return JSON
	 * @author GaoYa
	 * @date 2014-04-02 下午03:35:00
	 */
	@RequestMapping(value = "importData")
	@ResponseBody
	public HashMap<String, Object> parse(String filename) {
		// 获取文件上传路径
		//String importpath = Factory.getSysPara().getProperty("UPLOAD_PATH");
		SysParaInf sp = Factory.getSysPara();
		boolean relative = AttachmentManager.SAVE_PATH_R.equals(sp.getProperty("attachmentPathType"));
		String uploadPath = sp.getProperty("attachmentPath");
		String importpath = (relative ? (Factory.getWebRoot() + "/" + uploadPath + "/") : "");
		if (StringUtil.isEmpty(importpath)) {
			return JsonWrapper.failureWrapper("错误：未配置系统文件上传路径！");
		}
		File importdir = new File(importpath);
		if (!importdir.exists())
			importdir.mkdirs();

		File file = new File(importdir, filename);
		try {
			if (file.canRead()) {
				List<DeviceDocument> deviceList = new ArrayList<DeviceDocument>();
				Map<String, EnterInfo> enterInfosMap = new HashMap<String, EnterInfo>();
				readEnterInfosMap(enterInfosMap);
				List<String> errors = new ArrayList<String>();
				// 解析Excel文件
				parseExcelDatas(file, deviceList, enterInfosMap, errors);
				if (errors.isEmpty()) {
					// 保存数据
					deviceImportService.saveData(deviceList);
				} else {
					return JsonWrapper.failureWrapper("导入失败：" + errors);
				}
				return JsonWrapper.successWrapper("恭喜您，导入成功！");
			} else {
				return JsonWrapper.failureWrapper("导入文件不可读！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper("导入失败：" + e.getMessage());
		}
	}

	/**
	 * 解析Excel
	 * 
	 * @param xlsfile --
	 *            Excel文件
	 * @param idList --
	 *            设备ID集合
	 * @param deviceList --
	 *            设备基础信息对象集合
	 * @param enterInfosMap --
	 *            企业信息对象集合
	 * @param errors --
	 *            错误信息
	 * @param request
	 * @return
	 * @author GaoYa
	 * @date 2014-04-02 下午04:40:00
	 */
	public void parseExcelDatas(File xlsfile, List<DeviceDocument> deviceList, Map<String, EnterInfo> enterInfosMap, List<String> errors)
			throws Exception {
		Workbook excelfile = null;
		try {
			excelfile = createWorkbook(xlsfile); // 创建工作簿
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("读excel文件失败！");
			return;
		}

		// 遍历工作簿中的工作表
		for (int numSheets = 0; numSheets < excelfile.getNumberOfSheets(); numSheets++) {
			if (excelfile.getSheetAt(numSheets) != null) {
				Sheet sheet = excelfile.getSheetAt(numSheets); // 根据序号获取工作表
				// 解析设备基础信息工作表
				readExcelOfDeviceDocument(sheet, deviceList, enterInfosMap, errors);
			}
		}
	}

	/**
	 * 解析设备基础信息工作表
	 * 
	 * @param sheet --
	 *            工作表
	 * @param idList --
	 *            设备ID集合
	 * @param deviceList --
	 *            设备基础信息对象集合
	 * @param enterInfosMap --
	 *            企业信息对象集合
	 * @param errors --
	 *            错误信息
	 * @return
	 * @author GaoYa
	 * @date 2014-04-02 下午05:15:00
	 */
	@SuppressWarnings("unchecked")
	private void readExcelOfDeviceDocument(Sheet sheet, List<DeviceDocument> deviceList, Map<String, EnterInfo> enterInfosMap, List<String> errors) {
		int line = 0;
		for (Iterator it = sheet.rowIterator(); it.hasNext();) {
			if (errors.size() > 1000)
				return;

			Row row = (Row) it.next();
			line++;
			if (line == 1) {
				if (row.getPhysicalNumberOfCells() < Constant.DEVICE_TITLES.length) {
					errors.add("导入文件中格式错误，必须含有 "
							+ Constant.DEVICE_TITLES.length + " 列！请核实后再导入数据！");
					return;
				} else {
					for (int i = 0; i < Constant.DEVICE_TITLES.length; i++) {
						String columnName = readCellData(row, i);
						if (!Constant.DEVICE_TITLES[i]
								.equalsIgnoreCase(columnName)) {
							errors.add("导入文件中格式错误，列 " + columnName + " 必须改为 "
									+ Constant.DEVICE_TITLES[i]);
							return;
						}
					}
				}
				continue;
			}

			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			DeviceDocument device = new DeviceDocument();
			// 设备注册代码
			String device_registration_code = readCellData(row, 0); // 从0开始，表示第一列
			if (StringUtil.isNotEmpty(device_registration_code.trim())) {
				device.setDevice_registration_code(device_registration_code
						.trim());
			}

			// 使用登记证号
			String registration_num = readCellData(row, 1);
			if (StringUtil.isNotEmpty(registration_num.trim())) {
				device.setRegistration_num(registration_num.trim());
			}
			// 设备名称
			String device_name = readCellData(row, 2);
			if (StringUtil.isNotEmpty(device_name.trim())) {
				device.setDevice_name(device_name.trim());
			}

			// 设备类别
			String device_sort_code = readCellData(row, 3);
			if (StringUtil.isNotEmpty(device_sort_code.trim())) {
				device.setDevice_sort_code(device_sort_code.trim());
			}

			// 出厂编号
			String factory_code = readCellData(row, 4);
			if (StringUtil.isNotEmpty(factory_code.trim())) {
				device.setFactory_code(factory_code.trim());
			}

			// 使用单位
			String com_name = readCellData(row, 5);
			if (StringUtil.isNotEmpty(com_name.trim())) {
				if (enterInfosMap.containsKey(com_name.trim())) {
					EnterInfo enterInfo = enterInfosMap.get(com_name.trim());
					if (enterInfo != null) {
						device.setFk_company_info_use_id(enterInfo.getId());
					}
				}else{
					errors.add("导入文件中数据有误，第" + line + "行 使用单位“"
							+ com_name.trim() + "”不存在，请核实！");
					return;
				}
			}

			// 安全管理员
			String security_op = readCellData(row, 6);
			if (StringUtil.isNotEmpty(security_op.trim())) {
				device.setSecurity_op(security_op.trim());
			}

			// 安全管理员联系电话
			String security_tel = readCellData(row, 7);
			if (StringUtil.isNotEmpty(security_tel.trim())) {
				device.setSecurity_tel(security_tel.trim());
			}

			// 设备使用地点
			String device_use_place = readCellData(row, 8);
			if (StringUtil.isNotEmpty(device_use_place.trim())) {
				device.setDevice_use_place(device_use_place.trim());
			}

			// 设备所在地
			String device_area_code = readCellData(row, 9);
			if (StringUtil.isNotEmpty(device_area_code.trim())) {
				device.setDevice_area_code(device_area_code.trim());
			}

			// 设备所在街道
			String device_street_code = readCellData(row, 10);
			if (StringUtil.isNotEmpty(device_street_code.trim())) {
				device.setDevice_street_code(device_street_code.trim());
			}

			// 下次检验日期
			String inspect_next_date = readCellData(row, 11);
			if (StringUtil.isNotEmpty(inspect_next_date.trim())) {
				try {
					device.setInspect_next_date(DateUtil.convertStringToDate(
							Constant.defaultDatePattern, inspect_next_date
									.trim()));
				} catch (ParseException pe) {
					errors.add("导入文件中数据有误，第" + line + "行 下次检验日期为“"
							+ inspect_next_date.trim() + "”格式错误！");
					return;
				}
			}

			// 备注
			String note = readCellData(row, 12);
			if (StringUtil.isNotEmpty(note.trim())) {
				device.setNote(note.trim());
			}
			
			device.setDevice_status("80");	// 设置设备状态为“重点监控设备”（导入数据）
			device.setCreated_by(user.getName());
			device.setCreated_date(new Date());
			device.setLast_upd_by(user.getName());
			device.setLast_upd_date(new Date());
			
			if (errors.isEmpty()) {
				deviceList.add(device);
			}
		}
	}
	
	/**
	 * 获取企业信息
	 * 
	 * @param companysMap
	 */
	private void readEnterInfosMap(Map<String, EnterInfo> enterInfosMap) {
		List<EnterInfo> enterInfoList = enterService.queryEnterInfos();
		for (EnterInfo enterInfo : enterInfoList) {
			enterInfosMap.put(enterInfo.getCom_name(), enterInfo);
		}
	}

	/**
	 * 写文件
	 * 
	 * @param file --
	 *            文件
	 * @return
	 * @author GaoYa
	 * @date 2014-04-02 下午02:55:00
	 */
	public void writeFile(File file, InputStream inStream) throws Exception {
		int bytesum = 0;
		int byteread = 0;
		FileOutputStream fs = new FileOutputStream(file);
		byte buffer[] = new byte[1444];
		while ((byteread = inStream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
		}
		inStream.close();
		fs.close();
	}

	/**
	 * 创建工作簿
	 * 
	 * @param importfile --
	 *            Excel文件
	 * @return Workbook -- 工作簿
	 * @author GaoYa
	 * @date 2014-04-02 下午04:50:00
	 */
	public Workbook createWorkbook(File importfile) throws Exception {
		// 创建对Excel工作簿文件的引用
		Workbook wb = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(importfile);
			if (importfile.getName().toLowerCase().endsWith("xlsx")) {
				wb = new XSSFWorkbook(fis);
			}
			if (importfile.getName().toLowerCase().endsWith("xls")) {
				wb = new HSSFWorkbook(fis);
			}
			fis.close();
		} catch (Exception e) {
			logger.info("Reading excel file error that " + e);
			throw new Exception("读excel文件失败！");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return wb;
	}

	/**
	 * 读取单元格内容
	 * 
	 * @param row --
	 *            行
	 * @param col --
	 *            列
	 * @return String -- 单元格内容
	 * @author GaoYa
	 * @date 2014-04-02 下午05:25:00
	 */
	public String readCellData(Row row, int col) {
		Cell cell = row.getCell((short) col);
		String value = "";
		if (cell != null) {
			int cellType = cell.getCellType();

			switch (cellType) {
			case Cell.CELL_TYPE_FORMULA:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				DecimalFormat decimalFormat = new DecimalFormat();
				decimalFormat.setDecimalSeparatorAlwaysShown(false);
				decimalFormat.setGroupingUsed(false);
				value = decimalFormat.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				if (cell.getBooleanCellValue()) {
					value = "true";
				} else {
					value = "false";
				}
				break;
			}
		} else {
			value = "";
		}
		return value.trim();
	}
}
