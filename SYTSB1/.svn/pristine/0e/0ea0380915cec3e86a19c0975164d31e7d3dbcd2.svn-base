package com.lsts.equipment2.service;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.dao.EquipPpeDao;
import com.lsts.log.service.SysLogService;

import jxl.Cell;
import net.sf.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 作者 QuincyXu
 * @JDK 1.6
 * @version 创建时间：2016年5月16日16:02:20 类说明
 */
@Service("equipPpeManager")
public class EquipPpeManager extends EntityManageImpl<EquipPpe, EquipPpeDao> {
	@Autowired
	EquipPpeDao equipPpeDao;
	@Autowired
	EmployeesDao employeesDao;
	@Autowired
	private SysLogService logService;

	/**
	 * 保存导入
	 * 
	 * @param file
	 * @return
	 * @throws ParseException
	 */
	public String[] saveTaskData(String files, CurrentSessionUser user) throws ParseException {
		JSONArray array = JSONArray.fromObject(files);
		String[] contents = new String[2];
		contents[0] = "0";
		contents[1] = "";
		String[] fileName = new String[array.length()];// 文件名
		String[] filePath = new String[array.length()];// 文件路径
		for (int i = 0; i < array.length(); i++) {
			String[] content = new String[2];
			fileName[i] = array.getJSONObject(i).getString("name");
			filePath[i] = array.getJSONObject(i).getString("path");
			content = saveDate(fileName[i], filePath[i], user);
			contents[0] = Integer.toString(Integer.parseInt(contents[0]) + Integer.parseInt(content[0]));
			contents[1] = contents[1] + content[1];
		}
		return contents;
	}

	/**
	 * 根据导入的文件名获取并解析数据
	 * 
	 * @param file
	 * @throws ParseException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public String[] saveDate(String fileName, String filePath, CurrentSessionUser user) throws ParseException {
		String repData = "";
		int total = 0;// 导入成功的数量
		String[] content = new String[2];
		fileName = this.getSystemFilePath() + File.separator + filePath;
		File taskfile = new File(fileName); // 创建文件对象
		HashMap<String, Object> UIDandNAME = new HashMap<String, Object>();// 缓存使用人ID及姓名
		HashMap<String, Object> DIDandNAME = new HashMap<String, Object>();// 缓存使用部门ID及名称
		Workbook taskWb = null;
		try {
			taskWb = WorkbookFactory.create(taskfile);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet taskSheet = taskWb.getSheetAt(0);// 获得sheet
		for (int i = 1; i <= taskSheet.getLastRowNum(); i++) {
			Row row = taskSheet.getRow(i);// 行
			if (row != null && !"null".equals(row)) {
				if (row.getCell(0) != null && !"null".equals(row.getCell(0))) {
					// 通过自编号判断设备是否存在，若存在则只更新数据；若不存在则添加数据
					String selfNo = row.getCell(2).toString();// 自编号
					List<EquipPpe> list = this.getBySelfNo(selfNo);
					if (list != null && list.size() > 0) {
						System.out.println("-------------此资产已存在，只更新数据信息！-------------");
						String owner = "";
						EquipPpe equipPpeIsExist = list.get(0);
						this.setValue(equipPpeIsExist, false, row);
						equipPpeDao.save(equipPpeIsExist);
						total = total + 1;
					} else {
						System.out.println("-------------此资产不存在，要添加数据信息！-------------");
						EquipPpe equipPpe = new EquipPpe();
						this.setValue(equipPpe, true, row);
						try {// 判断自编号是否重复
							List<EquipPpe> listIsExist = equipPpeDao.deleteBySelfNo(selfNo);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							total = total + 1;
							String barcode = this.barcode();// 生成13位长度的条码
							if (this.searchByBarcode(barcode) == null) {
								equipPpe.setBarcode(barcode);
							} else {
								while (true) {
									barcode = this.barcode();
									if (this.searchByBarcode(barcode) == null) {
										equipPpe.setBarcode(barcode);
										break;
									}
								}
							}
							equipPpe.setCreateDate(new Date());
							equipPpe.setCreateId(user.getUserId());
							equipPpe.setCreateBy(user.getName());
							equipPpeDao.save(equipPpe);
						}
					}

				}
			}
			System.out.println(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + row.getCell(0) + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		content[0] = Integer.toString(total);
		content[1] = repData;
		return content;

	}

	public String getSystemFilePath() {
		SysParaInf sp = Factory.getSysPara();
		String attachmentPath = sp.getProperty("attachmentPath");
		String attachmentPathType = sp.getProperty("attachmentPathType", "relative");
		if ("relative".equals(attachmentPathType)) {
			return Factory.getWebRoot() + File.separator + attachmentPath;
		}
		return attachmentPath;
	}

	// 生成13位数的条形码
	public String barcode() {
		Random random = new Random();
		String barcode = String.valueOf(random.nextInt(10));
		for (int i = 0; i < 12; i++) {
			barcode += random.nextInt(10);
		}
		return barcode;
	}

	// 通过barcode查询固定资产
	public EquipPpe searchByBarcode(String barcode) {
		EquipPpe equipPpe = new EquipPpe();
		List<EquipPpe> equipPpeList = equipPpeDao.searchByBarcode(barcode);
		if (equipPpeList != null && equipPpeList.size() > 0) {
			equipPpe = equipPpeList.get(0);
		} else {
			equipPpe = null;
		}
		return equipPpe;
	}

	// 根据卡片编号查询固定资产信息
	public List<EquipPpe> getByCardNo(String cardNo) {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			list = equipPpeDao.getByCardNo(cardNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 根据自编号查询固定资产信息
	public List<EquipPpe> getBySelfNo(String selfNo) {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		try {
			list = equipPpeDao.getBySelfNo(selfNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 格式化导入EXCEL文件中的日期
	public Date formatDate(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if (strDate.indexOf(".") != -1) {
			strDate = strDate.replaceAll("\\.", "/");
		} else if (strDate.indexOf("—") != -1) {
			strDate = strDate.replaceAll("－", "/");
		} else if (strDate.indexOf("-") != -1) {
			strDate = strDate.replaceAll("-", "/");
		} else if (strDate.indexOf("/") != -1) {
			strDate = strDate.replaceAll("/", "/");
		}
		Date date = sdf.parse(strDate);
		return date;
	}

	// 更新资产错误的二维码
	public int updateErrBarcode() {
		List<EquipPpe> list = new ArrayList<EquipPpe>();
		int updateCount = 0;
		try {
			list = equipPpeDao.getEquipPpeByErrBarcode();
			if (list.size() > 0) {
				for (EquipPpe equipPpe : list) {
					updateCount++;
					String barcode = this.barcode();// 生成13位长度的条码
					if (this.searchByBarcode(barcode) == null) {
						equipPpe.setBarcode(barcode);
					} else {
						while (true) {
							barcode = this.barcode();
							if (this.searchByBarcode(barcode) == null) {
								equipPpe.setBarcode(barcode);
								break;
							}
						}
					}
					equipPpeDao.save(equipPpe);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateCount;
	}

	// 盘点固定资产（手动）
	public HashMap<String, Object> inventoryByHand(HttpServletRequest request, String ids) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 判断是否多个ID
			if (ids.indexOf(",") != -1) {
				String id[] = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					equipPpeDao.createSQLQuery(
							"update TJY2_EQUIP_PPE t set t.inventory = 'YPD', t.inventory_id = ?, t.inventory_name = ?, t.inventory_date = ? where id =?",
							user.getId(), user.getName(), new Date(), id[i]).executeUpdate();
					// 写入日志
					logService.setLogs(id[i], "固定资产盘点（手动）", "手动盘点固定资产", user.getId(), user.getName(), new Date(),
							request != null ? request.getRemoteAddr() : "");
				}
			} else {
				equipPpeDao.createSQLQuery(
						"update TJY2_EQUIP_PPE t set t.inventory = 'YPD', t.inventory_id = ?, t.inventory_name = ?, t.inventory_date = ? where id =?",
						user.getId(), user.getName(), new Date(), ids).executeUpdate();
				// 写入日志
				logService.setLogs(ids, "固定资产盘点（手动）", "手动盘点固定资产", user.getId(), user.getName(), new Date(),
						request != null ? request.getRemoteAddr() : "");
			}
			map.put("success", true);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}

	// 固定资产报废操作
	public HashMap<String, Object> scrap(String ids) {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 判断是否多个ID
			if (ids.indexOf(",") != -1) {
				String id[] = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					equipPpeDao.createSQLQuery(
							"update TJY2_EQUIP_PPE t set t.asset_status = 'YBF', t.last_modify_id = ?, t.last_modify_by = ?, t.last_modify_date = ? where id =?",
							user.getId(), user.getName(), new Date(), id[i]).executeUpdate();
				}
			} else {
				equipPpeDao.createSQLQuery(
						"update TJY2_EQUIP_PPE t set t.asset_status = 'YBF', t.last_modify_id = ?, t.last_modify_by = ?, t.last_modify_date = ? where id =?",
						user.getId(), user.getName(), new Date(), ids).executeUpdate();
			}
			map.put("success", true);
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}

	public EquipPpe setValue(EquipPpe equipPpe, Boolean isNew, Row row) throws ParseException {
		System.out.println("卡片编号==" + row.getCell(1));
		if (row.getCell(1) == null || row.getCell(1).equals("")
				|| row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setCardNo(null);
		} else {
			equipPpe.setCardNo(row.getCell(1).toString());
		}
		System.out.println("自编号==" + row.getCell(2));
		if (row.getCell(2) == null || row.getCell(2).equals("")
				|| row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setSelfNo(null);
		} else {
			equipPpe.setSelfNo(row.getCell(2).toString());
		}
		System.out.println("资产名称==" + row.getCell(3));
		if (row.getCell(3) == null || row.getCell(3).equals("")
				|| row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setAssetName(null);
		} else {
			equipPpe.setAssetName(row.getCell(3).toString());
		}
		System.out.println("规格型号==" + row.getCell(4));
		if (row.getCell(4) == null || row.getCell(4).equals("")
				|| row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setSpaciModel(null);
		} else {
			equipPpe.setSpaciModel(row.getCell(4).toString());
		}
		System.out.println("序列号==" + row.getCell(5));
		if (row.getCell(5) == null || row.getCell(5).equals("")
				|| row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setSn(null);
		} else {
			equipPpe.setSn(row.getCell(5).toString());
		}
		System.out.println("单位==" + row.getCell(6));
		if (row.getCell(6) == null || row.getCell(6).equals("")
				|| row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setUnit(null);
		} else {
			equipPpe.setUnit(row.getCell(6).toString());
		}
		System.out.println("价值==" + row.getCell(7));
		if (row.getCell(7) != null && !row.getCell(7).toString().isEmpty()) {
			if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				equipPpe.setAssetValue(String.format("%.2f", row.getCell(7).getNumericCellValue()));
			} else if (row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_STRING) {
				equipPpe.setAssetValue(String.format("%.2f", Float.parseFloat(row.getCell(7).getStringCellValue())));
			}
		} else {
			equipPpe.setAssetValue(null);
		}
		try {
			System.out.println("原值==" + row.getCell(8));
			if (row.getCell(8) != null && !row.getCell(8).toString().isEmpty()) {
				if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					equipPpe.setOriginalValue(String.format("%.2f", row.getCell(8).getNumericCellValue()));
				} else if (row.getCell(8).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					equipPpe.setOriginalValue(String.format("%.2f", Float.parseFloat(row.getCell(8).getStringCellValue())));
				}
			} else {
				equipPpe.setOriginalValue(null);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("放置地点==" + row.getCell(9));
		if (row.getCell(9) != null && !row.getCell(9).toString().isEmpty()) {
			if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				String str = String.valueOf(row.getCell(9));
				equipPpe.setPlaceLocation(str.substring(0, str.indexOf(".")));
			} else if (row.getCell(9).getCellType() == HSSFCell.CELL_TYPE_STRING) {
				equipPpe.setPlaceLocation(row.getCell(9).getStringCellValue());
			}
		} else {
			equipPpe.setPlaceLocation(null);
		}
		System.out.println("使用部门==" + row.getCell(10));
		if (row.getCell(10) == null || row.getCell(10).equals("")
				|| row.getCell(10).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setUserDepartment(null);
		} else {
			equipPpe.setUserDepartment(row.getCell(10).getStringCellValue());
		}
		// 使用部门ID
		/*
		 * if(row.getCell(10)!=null && !"".equals(row.getCell(10))){ String
		 * userDepartment = row.getCell(10).getStringCellValue();//得到使用部门名称
		 * equipPpe.setUserDepartment(userDepartment); try { String userDepartmentId =
		 * DIDandNAME.get(userDepartment).toString();
		 * equipPpe.setUserDepartmentId(userDepartmentId);//
		 * 若DIDandNAME有userDepartmentId则直接赋值 if(!userDepartmentId.isEmpty()){
		 * equipPpe.setUserDepartmentId(userDepartmentId);//
		 * 若DIDandNAME有userDepartmentId则直接赋值 }else{ String orgId =
		 * employeesDao.getOrg(userDepartment).get(0).getId();//
		 * 若DIDandNAME没有userDepartmentId则从数据库中获取 equipPpe.setUserDepartmentId(orgId);
		 * DIDandNAME.put(userDepartment, orgId); } } catch (Exception e) { String orgId
		 * = employeesDao.getOrg(userDepartment).get(0).getId();//
		 * 若DIDandNAME没有userDepartmentId则从数据库中获取 equipPpe.setUserDepartmentId(orgId);
		 * DIDandNAME.put(userDepartment, orgId); } }
		 */
		// 使用人ID
		System.out.println("使用人==" + row.getCell(11));
		if (row.getCell(11) == null || row.getCell(11).equals("")
				|| row.getCell(11).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			equipPpe.setUserName(null);
		} else {
			equipPpe.setUserName(row.getCell(11).getStringCellValue());
		}
		/*
		 * if(row.getCell(11)!=null && !"".equals(row.getCell(11))){ String userName =
		 * row.getCell(11).getStringCellValue();//得到使用人姓名
		 * equipPpe.setUserName(userName); try { String userID =
		 * UIDandNAME.get(userName).toString();
		 * equipPpe.setUserId(userID);//若UIDandNAME有userID则直接赋值 } catch (Exception e) {
		 * String eId =
		 * equipPpeDao.queryEmployee(userName).getId();//若UIDandNAME没有userID则从数据库中获取
		 * equipPpe.setUserId(eId); UIDandNAME.put(userName, eId); } }
		 */
		System.out.println("出厂日期==" + row.getCell(12));
		if (!row.getCell(12).toString().isEmpty()) {
			try {
				equipPpe.setProductDate(row.getCell(12).getDateCellValue());
			} catch (Exception e) {
				equipPpe.setProductDate(this.formatDate(row.getCell(12).toString()));
			}
		} else {
			equipPpe.setProductDate(null);
		}
		System.out.println("入库日期==" + row.getCell(13));
		if (!row.getCell(13).toString().isEmpty()) {
			try {
				equipPpe.setInstockDate(row.getCell(13).getDateCellValue());
			} catch (Exception e) {
				equipPpe.setInstockDate(this.formatDate(row.getCell(13).toString()));
			}
		} else {
			equipPpe.setProductDate(null);
		}
		System.out.println("发放日期==" + row.getCell(14));
		if (!row.getCell(14).toString().isEmpty()) {
			try {
				equipPpe.setReleaseDate(row.getCell(14).getDateCellValue());
			} catch (Exception e) {
				equipPpe.setReleaseDate(this.formatDate(row.getCell(14).toString()));
			}
		} else {
			equipPpe.setReleaseDate(null);
		}
		// 固定资产状态
		System.out.println("资产状态==" + row.getCell(15));
		if (row.getCell(15) != null && !"".equals(row.getCell(15))) {
			if (row.getCell(15).getStringCellValue().equals("未使用")) {
				equipPpe.setAssetStatus("WSY");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("在用")) {
				equipPpe.setAssetStatus("ZY");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("停用")) {
				equipPpe.setAssetStatus("TY");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("已退回")) {
				equipPpe.setAssetStatus("YTH");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("已归还")) {
				equipPpe.setAssetStatus("YGH");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("待报废")) {
				equipPpe.setAssetStatus("DBF");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("已报废")) {
				equipPpe.setAssetStatus("YBF");// 状态
			}
			if (row.getCell(15).getStringCellValue().equals("其他")) {
				equipPpe.setAssetStatus("OTHER");// 状态
			}
		}
		// 使用年限
		System.out.println("使用年限==" + row.getCell(16));
		if (!row.getCell(16).toString().isEmpty()) {
			if (row.getCell(16).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				String str = String.valueOf(row.getCell(16));
				equipPpe.setServiceLife(str.substring(0, str.indexOf(".")));
			} else if (row.getCell(16).getCellType() == HSSFCell.CELL_TYPE_STRING) {
				equipPpe.setServiceLife(row.getCell(16).getStringCellValue());
			}
		} else {
			equipPpe.setServiceLife(null);
		}
		// 归属
		System.out.println("归属==" + row.getCell(17));
		if (row.getCell(17) != null && !"".equals(row.getCell(17))) {
			if (row.getCell(17).getStringCellValue().equals("四川省特种设备检验研究院")) {
				equipPpe.setOwner("100000");// 状态
			} else if (row.getCell(17).getStringCellValue().equals("鼎盛公司")) {
				equipPpe.setOwner("100047");// 状态
			} else if (row.getCell(17).getStringCellValue().equals("司法鉴定中心")) {
				equipPpe.setOwner("100044");// 状态
			} else if (row.getCell(17).getStringCellValue().equals("四川省特种设备检验检测协会")) {
				equipPpe.setOwner("100042");// 状态
			} else {
				equipPpe.setOwner("UNKNOWN");// 状态
			}
		}
		// 是否盘点
		System.out.println("是否盘点==" + row.getCell(18));
		if (row.getCell(18) != null && !"".equals(row.getCell(18))) {
			if (row.getCell(18).getStringCellValue().equals("未盘点")) {
				equipPpe.setInventory("WPD");// 状态
			}
			if (row.getCell(18).getStringCellValue().equals("已盘点")) {
				equipPpe.setInventory("YPD");// 状态
			}
		}
		// 备注
		System.out.println("备注==" + row.getCell(19));
		if (row.getCell(19) != null && !"".equals(row.getCell(19))) {
			equipPpe.setRemark(row.getCell(19).toString());
		} else {
			equipPpe.setRemark(null);
		}
		// 借用状态
		System.out.println("借用状态==" + row.getCell(20));
		if (row.getCell(20) != null && !"".equals(row.getCell(20))) {
			if (row.getCell(20).getStringCellValue().equals("未借用")) {
				equipPpe.setLoanStatus("0");
			} else if (row.getCell(20).getStringCellValue().equals("已借用")) {
				equipPpe.setLoanStatus("1");
			}
		} else {
			equipPpe.setLoanStatus(null);
		}
		return equipPpe;

	}
}
