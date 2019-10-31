package com.lsts.equipment2.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.base.SysParaInf;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.BeanUtils;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.employee.dao.EmployeesDao;
import com.lsts.equipment2.bean.DocimasyFks;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.Equipment2DTO;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.InoutRecord;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.dao.EquipmentDao;
import com.lsts.finance.bean.CwInvoiceF;

import net.sf.json.JSONArray;

/**
 * 设备信息业务逻辑对象
 * @ClassName EquipmentManager
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 上午11:25:00
 */
@Service("equipment2Service")
public class EquipmentManager extends EntityManageImpl<Equipment, EquipmentDao>{

	public final static String INSTOCK="yrk"; //已入库
    public final static String OUTSTOCK="yck"; //已出库
    public final static String INSTOCK1="drk"; //待入库
    public final static String BOX_STATUS1="01"; //已装箱
    public final static String BOX_STATUS2="02"; //未装箱
	@Autowired
	private EquipmentDao baseEquipmentDao;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
    EmployeesDao employeesDao;
	@Autowired
    EquipmentBoxDao equipmentBoxDao;
	
	
	/**
   	 * 保存设备信息导入
   	 * 
   	 * @param file
   	 * @return
        * @throws ParseException 
   	 */
   	public String[] saveTaskData(String files, CurrentSessionUser user) throws ParseException {
   		JSONArray array = JSONArray.fromObject(files);
		String[] contents = new String[2];
		contents[0]="0";
		contents[1]="";
		String[] fileName = new String[array.length()];// 文件名
		String[] filePath = new String[array.length()];// 文件路径
		for (int i = 0; i < array.length(); i++) {
			String[] content = new String[2];
			fileName[i] = array.getJSONObject(i).getString("name");
			filePath[i] = array.getJSONObject(i).getString("path");
			content = saveDate(fileName[i], filePath[i], user);
			contents[0] = Integer.toString(Integer.parseInt(contents[0])+Integer.parseInt(content[0]));
			contents[1] = contents[1]+content[1];
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
   		String repData="";
   		int total=0;//导入成功的数量
   		String[] content = new String[2];
   		fileName = this.getSystemFilePath()+File.separator+filePath;
   		File taskfile = new File(fileName); // 创建文件对象  
   		HashMap<String,Object> UIDandNAME=new HashMap<String,Object>();//缓存管理（使用）人ID及姓名
   		HashMap<String,Object> DIDandNAME=new HashMap<String,Object>();//缓存管理（使用）部门ID及名称
   		Workbook taskWb=null;
   		try {
   			taskWb = WorkbookFactory.create(taskfile);
   		} catch (InvalidFormatException e) {
   			e.printStackTrace();
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   	    Sheet taskSheet = taskWb.getSheetAt(0);//获得sheet
   	    for (int i=1;i<=taskSheet.getLastRowNum();i++) {
   	    	Row row = taskSheet.getRow(i);//行
   	    	if(row!=null && !"null".equals(row)) {
   	    		if(row.getCell(0)!=null && !"null".equals(row.getCell(0))) {
   	    			Equipment equipment = new Equipment();
   	    			String status = "";
   	    			equipment.setEq_category("01");//设备类别
   	    			
   	    			System.out.println("卡片编号=="+row.getCell(1));
   	    			equipment.setCard_no(row.getCell(1).toString());
   	    			
   	    			System.out.println("档案分类号=="+row.getCell(2));
   	    			equipment.setEq_archive_class_id(row.getCell(2).toString());
   	    			
   	    			System.out.println("设备编号=="+row.getCell(3));
   	    			equipment.setEq_no(row.getCell(3).toString());
   	    			
   	    			System.out.println("设备名称=="+row.getCell(4));
   	    			equipment.setEq_name(row.getCell(4).toString());
   	    			
   	    			System.out.println("设备型号=="+row.getCell(5));
   	    			equipment.setEq_model(row.getCell(5).toString());
   	    			
   	    			System.out.println("出厂编号=="+row.getCell(6));
   	    			equipment.setEq_sn(row.getCell(6).toString());
   	    			
   	    			System.out.println("设备原值=="+row.getCell(7));
   	    			if(!row.getCell(7).toString().isEmpty()){
   	    				if(row.getCell(7).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
   	    					equipment.setEq_value(String.format("%.2f", row.getCell(7).getNumericCellValue()));
	    				}else if(row.getCell(7).getCellType()==HSSFCell.CELL_TYPE_STRING){
	    					equipment.setEq_value(String.format("%.2f", Float.parseFloat(row.getCell(7).getStringCellValue())));
	    				}
   	    			}else{
   	    				equipment.setEq_value(null);
   	    			}
   	    			
   	    			System.out.println("精度=="+row.getCell(8));
   	    			equipment.setEq_accurate(row.getCell(8).toString());
   	    			
   	    			System.out.println("购买日期=="+row.getCell(9));
   	    			if(!row.getCell(9).toString().isEmpty()){
	   	    			try {
	   	    				equipment.setEq_buy_date(row.getCell(9).getDateCellValue());
						} catch (Exception e) {
							equipment.setEq_buy_date(this.formatDate(row.getCell(9).toString()));
						}
   	    			}else{
   	    				equipment.setEq_buy_date(null);
   	    			}
   	    			
   	    			System.out.println("生产厂家=="+row.getCell(10));
   	    			equipment.setEq_manufacturer(row.getCell(10).toString());
   	    			
   	    			System.out.println("检定周期=="+row.getCell(11));
   	    			if(!row.getCell(11).toString().isEmpty()){
   	    				if(row.getCell(11).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
   	    					equipment.setEq_check_cycle((int) row.getCell(11).getNumericCellValue());
	    				}else if(row.getCell(11).getCellType()==HSSFCell.CELL_TYPE_STRING){
	    					equipment.setEq_check_cycle(Integer.parseInt(row.getCell(11).getStringCellValue()));
	    				}
   	    			}else{
   	    				equipment.setEq_value(null);
   	    			}

   	    			System.out.println("设备状态=="+row.getCell(12));
   	    			if(row.getCell(12)!=null && !"".equals(row.getCell(12))){
   	    				if(row.getCell(12).getStringCellValue().equals("准用")){
	   	    				status = "zy";
	   	    			}
   	    				if(row.getCell(12).getStringCellValue().equals("停用")){
	   	    				status = "ty";
	   	    			}
	   	    			if(row.getCell(12).getStringCellValue().equals("启用")){
	   	    				status = "qy";
	   	    			}
	   	    			if(row.getCell(12).getStringCellValue().equals("封存")){
	   	    				status = "fc";
	   	    			}
	   	    			if(row.getCell(12).getStringCellValue().equals("报废")){
	   	    				status = "bf";
	   	    			}
	   	    			if(row.getCell(12).getStringCellValue().equals("检定")){
	   	    				status = "jd";
	   	    			}
	   	    			if(row.getCell(12).getStringCellValue().equals("设备维修审核中")){
	   	    				status = "wxshz";
	   	    			}
	   	    			if(row.getCell(12).getStringCellValue().equals("维修设备验收审核中")){
	   	    				status = "wxysshz";
	   	    			}
	   	    			equipment.setEq_status(status);//状态
   	    			}
   	    			
   	    			System.out.println("使用部门=="+row.getCell(13));
   	    			equipment.setEq_use_department(row.getCell(13).getStringCellValue());
   	    			/*if(row.getCell(13)!=null && !"".equals(row.getCell(13))){
   	    				String userDepartment = row.getCell(13).getStringCellValue();//得到使用部门名称
   	    				equipment.setEq_use_department(userDepartment);
   	    				try {
							String userDepartmentId = DIDandNAME.get(userDepartment).toString();
							equipment.setEq_use_department_id(userDepartmentId);//若DIDandNAME有userDepartmentId则直接赋值
							if(!userDepartmentId.isEmpty()){
								equipPpe.setUserDepartmentId(userDepartmentId);//若DIDandNAME有userDepartmentId则直接赋值
							}else{
								String orgId = employeesDao.getOrg(userDepartment).get(0).getId();//若DIDandNAME没有userDepartmentId则从数据库中获取
								equipPpe.setUserDepartmentId(orgId);
								DIDandNAME.put(userDepartment, orgId);
							}
						} catch (Exception e) {
							String orgId = employeesDao.getOrg(userDepartment).get(0).getId();//若DIDandNAME没有userDepartmentId则从数据库中获取
							equipment.setEq_use_department_id(orgId);
							DIDandNAME.put(userDepartment, orgId);
						}
   	    			}*/

   	    			System.out.println("使用人=="+row.getCell(14));
   	    			equipment.setEq_user(row.getCell(14).getStringCellValue());
   	    			/*if(row.getCell(14)!=null && !"".equals(row.getCell(14))){
   	    				String userName = row.getCell(14).getStringCellValue();//得到使用人姓名
   	    				equipment.setEq_user(userName);
   	    				try {
							String userID = UIDandNAME.get(userName).toString();
							equipment.setEq_user_id(userID);//若UIDandNAME有userID则直接赋值
						} catch (Exception e) {
							String eId = baseEquipmentDao.queryEmployee(userName).getId();//若UIDandNAME没有userID则从数据库中获取
							equipment.setEq_user_id(eId);
   	    					UIDandNAME.put(userName, eId);
						}
   	    			}*/
   	    			
   	    			System.out.println("设备箱号=="+row.getCell(15));
   	    			if(row.getCell(15)!=null && !"".equals(row.getCell(15).toString())){
   	    				equipment.setBox_number(row.getCell(15).toString());
   	    				equipment.setBox_status("01");//已装箱
   	    				/*//根据设备箱编号查询设备箱是否已经存在
   	    				List<EquipmentBox> list = equipmentBoxDao.queryBox(row.getCell(15).toString());
   	    				if(list.size()>0&&list!=null){
   	    					//如果存在则直接保存到设备箱
   	    					EquipmentBox equipmentBox = list.get(0);
   	    					List<Equipment> baseEquipment2s = new ArrayList<Equipment>();
   	    				    baseEquipment2s.add(equipment);
   	    				    equipmentBoxDao.save(equipmentBox);
   	    				}else{
   	    					//如果不存在就创建设备箱再保存
   	    					EquipmentBox equipmentBox = new EquipmentBox();
   	    				    List<Equipment> baseEquipment2s = new ArrayList<Equipment>();
   	    				    baseEquipment2s.add(equipment);
   	    				    equipmentBox.setBaseEquipment2s(baseEquipment2s);
   	    				    equipmentBox.setBoxNumber(row.getCell(15).toString());
   	    				    equipmentBoxDao.save(equipmentBox);
   	    				}*/
   	    			}else{
   	    				equipment.setBox_status("02");//未装箱
   	    			}
   	    			System.out.println("备注=="+row.getCell(16));
   	    			equipment.setRemark(row.getCell(16).toString());
   	    			String eq_no = row.getCell(3).toString();//设备编号
   	    			try {//判断设备是否重复，若是就删除之前的
   	    				baseEquipmentDao.getinvoiceCode(eq_no,"01");
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						total = total + 1;
						String barcode = this.barcode();//生成13位长度的条码
						if(this.searchByBarcode(barcode)==null){
							equipment.setBarcode(barcode);
						}else{
							while(true){
								barcode = this.barcode();
								if(this.searchByBarcode(barcode)==null){
									equipment.setBarcode(barcode);
									break;
								}
							}
						}
						equipment.setInventory("WPD");
						equipment.setEq_isdelete("0");
						equipment.setEq_inout_status("yrk");
						equipment.setCreate_date(new Date());
						equipment.setCreate_by(user.getName());
						baseEquipmentDao.save(equipment);
					}
   	    		}
   	    	}
   	    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+row.getCell(0)+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
   	    }
   	    content[0]=Integer.toString(total);
	    content[1]=repData;
		return content;
   		
   	}
   	
   	/**
   	 * 保存检定周期导入
   	 * 
   	 * @param file
   	 * @return
        * @throws ParseException 
   	 */
   	public String[] saveJdzq(String files, CurrentSessionUser user) throws ParseException {
   		JSONArray array = JSONArray.fromObject(files);
		String[] contents = new String[2];
		contents[0]="0";
		contents[1]="";
		String[] fileName = new String[array.length()];// 文件名
		String[] filePath = new String[array.length()];// 文件路径
		for (int i = 0; i < array.length(); i++) {
			String[] content = new String[2];
			fileName[i] = array.getJSONObject(i).getString("name");
			filePath[i] = array.getJSONObject(i).getString("path");
			content = saveJdzq(fileName[i], filePath[i], user);
			contents[0] = Integer.toString(Integer.parseInt(contents[0])+Integer.parseInt(content[0]));
			contents[1] = contents[1]+content[1];
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
	public String[] saveJdzq(String fileName, String filePath, CurrentSessionUser user) throws ParseException {
   		String repData="";
   		int total=0;//导入成功的数量
   		String[] content = new String[2];
   		fileName = this.getSystemFilePath()+File.separator+filePath;
   		File taskfile = new File(fileName); // 创建文件对象  
   		HashMap<String,Object> UIDandNAME=new HashMap<String,Object>();//缓存管理（使用）人ID及姓名
   		HashMap<String,Object> DIDandNAME=new HashMap<String,Object>();//缓存管理（使用）部门ID及名称
   		Workbook taskWb=null;
   		try {
   			taskWb = WorkbookFactory.create(taskfile);
   		} catch (InvalidFormatException e) {
   			e.printStackTrace();
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   	    Sheet taskSheet = taskWb.getSheetAt(0);//获得sheet
   	    for (int i=4;i<=taskSheet.getLastRowNum();i++) {
   	    	Row row = taskSheet.getRow(i);//行
   	    	if(row!=null && !"null".equals(row)) {
   	    		if(row.getCell(0)!=null && !"null".equals(row.getCell(0))) {
   	    			if(!row.getCell(4).toString().isEmpty()){
   	    				try {
							Equipment equipment = baseEquipmentDao.getByEqNo(row.getCell(4).toString(),"01").get(0);
							if(!row.getCell(7).toString().isEmpty()){
	   	   	    				try {
									equipment.setEq_last_check_date(row.getCell(7).getDateCellValue());
								} catch (Exception e) {
									equipment.setEq_last_check_date(this.formatDate(row.getCell(7).toString()));
								}
	   	   	    			}else{
	   	   	    				equipment.setEq_last_check_date(null);
	   	   	    			}
	   	   	    			if(!row.getCell(8).toString().isEmpty()){
		   	   	    			try {
									equipment.setEq_next_check_date(row.getCell(8).getDateCellValue());
								} catch (Exception e) {
									equipment.setEq_next_check_date(this.formatDate(row.getCell(8).toString()));
								}
		   	    			}else{
	   	   	    				equipment.setEq_next_check_date(null);
	   	   	    			}
	   	   	    			if(!row.getCell(9).toString().isEmpty()){
		   	   	    			try {
									equipment.setEq_execute_date(row.getCell(9).getDateCellValue());
								} catch (Exception e) {
									equipment.setEq_execute_date(this.formatDate(row.getCell(9).toString()));
								}
	   	   	    			}else{
	   	   	    				equipment.setEq_execute_date(null);
	   	   	    			}
	   	   	    			equipment.setCheck_unit(row.getCell(10).toString());
	   	   	    			equipment.setCheck_op("张恒,李波");
	   	   	    			try {
	   						} catch (Exception e) {
	   							e.printStackTrace();
	   						}finally{
	   							total = total + 1;
	   							baseEquipmentDao.save(equipment);
	   						}
						} catch (Exception e) {
						}
   	   	    			
   	    			}
   	    		}
   	    	}
   	    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+row.getCell(0)+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
   	    }
   	    content[0]=Integer.toString(total);
	    content[1]=repData;
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
   	
	// 保存设备信息（含附件）
	public void saveEquipment(Equipment entity, String uploadFiles){
		baseEquipmentDao.save(entity);
		if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					attachmentManager.setAttachmentBusinessId(file, entity.getId());
				}
			}
		}
	}
	
	// 删除设备信息
    public void delete(String ids) {
    	baseEquipmentDao.delete(ids);
    }
    
    // 获取设备可用数量、设备状态、设备使用状态
    public Equipment2DTO getEquipmentCount(String id) {
    	return baseEquipmentDao.getEquipmentCount(id);
    }
    
    /*// 维修、借用、停用申请时，更新设备信息
    public void updateEquipment(Equipment baseEquipment) {
    	baseEquipmentDao.updateEquipment(baseEquipment);
    }
    
    // 报废申请时，更新设备信息
    public void updateScrapEquipment(Equipment baseEquipment) {
    	baseEquipmentDao.updateScrapEquipment(baseEquipment);
    }
    
    // 设备统计
    public Equipment2DTO queryCount(String startDate, String endDate, String minPrice, String maxPrice){
    	return baseEquipmentDao.queryCount(startDate, endDate, minPrice, maxPrice);
    }*/
    // 根据设备ID查询设备详情
    public List<Equipment> queryBaseEquipmentById(String id){
    	return baseEquipmentDao.queryBaseEquipmentById(id);
    }
    //启用设备，回写设备状态
    public HashMap<String, Object> start(String ids) throws Exception {
    	try {
			String idArr[] = ids.split(",");
			if (idArr.length > 0) {
				for (int i = 0; i < idArr.length; i++) {
					//更新设备档案里设备状态
					baseEquipmentDao.start(idArr[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
		return JsonWrapper.successWrapper(ids);
	}
    //封存设备，回写设备状态
    public HashMap<String, Object> sealed(String ids) throws Exception {
    	try {
			String idArr[] = ids.split(",");
			if (idArr.length > 0) {
				for (int i = 0; i < idArr.length; i++) {
					//更新设备档案里设备状态
					baseEquipmentDao.sealed(idArr[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
		return JsonWrapper.successWrapper(ids);
	}
    //设备报废，回写设备状态
    public HashMap<String, Object> scraped(String ids) throws Exception {
    	try {
			String idArr[] = ids.split(",");
			if (idArr.length > 0) {
				for (int i = 0; i < idArr.length; i++) {
					//更新设备档案里设备状态
					baseEquipmentDao.scraped(idArr[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("操作失败！");
		}
		return JsonWrapper.successWrapper(ids);
	}
    
    
    //添加设备检定回写
  	public void saveDocimasyFk(String ids) {
  		Equipment baseEquipment2 =	baseEquipmentDao.get(ids);
  		baseEquipment2.setEq_inout_status(OUTSTOCK);;//检定回写成出库
  		baseEquipment2.setEq_use_status("zy"); //回写成检定在用
  		baseEquipment2.setEq_status("jd"); //回写为检定状态
  		baseEquipmentDao.save(baseEquipment2);
  	}
  	/**
  	 * 检定完成后回写设备的实际检定时间、下次检定时间、状态 
  	 * @param uploadFiles
  	 * @param ids
  	 * @param nowtime
  	 * @param nexttime
  	 * @param type
  	 * @throws IllegalAccessException
  	 * @throws InvocationTargetException
  	 */
    public void saveDocimasyFks(String uploadFiles,String ids,Date nowtime,Date nexttime) throws IllegalAccessException, InvocationTargetException {
    	Equipment baseEquipment2 =	baseEquipmentDao.get(ids);
    	Date executeDate = baseEquipment2.getEq_execute_date();
    	Date nextCheckDate = baseEquipment2.getEq_next_check_date();
    	if(executeDate==null || nextCheckDate==null || nowtime.getTime()>executeDate.getTime()){
    		baseEquipment2.setEq_last_check_date(baseEquipment2.getEq_execute_date());//回写上次检定时间
    		baseEquipment2.setEq_execute_date(nowtime);//回写实际检定日期
    		baseEquipment2.setEq_next_check_date(nexttime);;//回写下次检定日期
        	baseEquipment2.setEq_inout_status(INSTOCK);;//检定回写成入库
          	baseEquipment2.setEq_use_status("kx"); //回写成空闲
          	baseEquipment2.setEq_status("zy"); //回写为状态准用
        	baseEquipmentDao.save(baseEquipment2);
    	}
    	if(StringUtil.isNotEmpty(uploadFiles)){
			String[] files = uploadFiles.replaceAll("/^,/", "").split(",");
			for(String file : files){
				if (StringUtil.isNotEmpty(file)) {
					Attachment attachment = new Attachment();
		        	BeanUtils.copyProperties(attachment, attachmentManager.get(file));
		        	attachment.setId(null);//设置ID为空
		        	attachmentManager.save(attachment);
		        	attachmentManager.setAttachmentBusinessId(attachment.getId(), baseEquipment2.getId());
				}
			}
		}
    }
    //删除回写设备状态
    public void deleteDocimasyFks(String ids) {
    	Equipment baseEquipment2 =	baseEquipmentDao.get(ids);
    	baseEquipment2.setEq_status(INSTOCK);;//检定回写成入库
      	baseEquipment2.setEq_use_status("kx"); //会写成检定空闲
      	baseEquipment2.setEq_status("qy"); //回写为状态启用
    	baseEquipmentDao.save(baseEquipment2);
    }
	//通过barcode查询设备
	public Equipment searchByBarcode(String barcode) {
		Equipment baseEquipment2 = new Equipment();
		List<Equipment> baseEquipment2List = baseEquipmentDao.searchByBarcode(barcode);
		if(baseEquipment2List!=null&&baseEquipment2List.size()>0){
			baseEquipment2 = baseEquipment2List.get(0);
		}else{
			baseEquipment2 = null;
		}
		return baseEquipment2;
	}
	//通过equip_id查询设备出入库记录
	public List<InoutRecord> searchRecord(String equip_id) {
		List list = baseEquipmentDao.searchRecord(equip_id);
    	List<InoutRecord> inoutRecords = new ArrayList<InoutRecord>();
    	if(list!=null && list.size()>0){
    		for(int i=0;i<list.size();i++){
    			InoutRecord inoutRecord = new InoutRecord();
    			Object list1[] = null;
    			list1 = (Object[]) list.get(i);
    			inoutRecord.setInoutClass(list1[0].toString());
    			inoutRecord.setInoutType(list1[1].toString());
    			inoutRecord.setRelatedName(list1[3].toString());
    			try {
					inoutRecord.setInoutDate(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(list1[2].toString())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			inoutRecords.add(inoutRecord);
    		}
		}
		return inoutRecords;
	}
	//生成计划检定日期 
	public Date nextCheckDate(Date date,int check_cycle) {
		Date nextCheckDate = new Date();
		Calendar calendar = Calendar.getInstance();
		Date nowTime = date;//获得购买时间
        calendar.setTime(nowTime);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        java.math.BigDecimal bg2 = new BigDecimal(check_cycle);//把String的周期方进来
        int  i1 = bg2.intValue();//再转成 Int 直接转成Int会出错 
        calendar.add(Calendar.MONTH, i1);   //得到的结果日期
        nextCheckDate = calendar.getTime();
		return nextCheckDate;
	}
	//生成13位数的条形码
	public String barcode(){
		Random random = new Random();
		String barcode="";
		for(int i=0;i<13;i++){
			barcode+=random.nextInt(10);
		}
		return barcode;
	}
	
	//根据卡片编号和设备类别查询设备档案信息
  	public List<Equipment> getByCardNo(String card_no,String eq_category) {
		List<Equipment> list = new ArrayList<Equipment>();
		try {
			list = baseEquipmentDao.getByCardNo(card_no,eq_category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
  	//根据设备编号和设备类别查询设备档案信息
  	public List<Equipment> getByEqNo(String eq_no,String eq_category) {
		List<Equipment> list = new ArrayList<Equipment>();
		try {
			list = baseEquipmentDao.getByEqNo(eq_no,eq_category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
  	/**
   	 * 转换导入EXCEL文件中的日期为yyyy/MM/dd格式
   	 * 
   	 * @param strDate
   	 * @throws ParseException 
   	 */
  	public Date formatDate(String strDate) throws ParseException {
  		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
  		if(strDate.indexOf(".")!=-1){
  			strDate = strDate.replaceAll("\\.", "/");
		}else if(strDate.indexOf("—")!=-1){
			strDate = strDate.replaceAll("－", "/");
		}else if(strDate.indexOf("-")!=-1){
			strDate = strDate.replaceAll("-", "/");
		}else if(strDate.indexOf("/")!=-1){
			strDate = strDate.replaceAll("/", "/");
		}
  		Date date=sdf.parse(strDate);
		return date;
	}
}
