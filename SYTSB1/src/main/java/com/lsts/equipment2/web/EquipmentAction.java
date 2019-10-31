package com.lsts.equipment2.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.service.AttachmentManager;
import com.khnt.security.CurrentSessionUser;
import com.khnt.utils.StringUtil;
import com.lsts.equipment2.bean.Equipment;
import com.lsts.equipment2.bean.EquipmentBox;
import com.lsts.equipment2.bean.EquipmentBuyRelation;
import com.lsts.equipment2.bean.EquipInstockRecord;
import com.lsts.equipment2.bean.EquipPpe;
import com.lsts.equipment2.bean.InoutRecord;
import com.lsts.equipment2.dao.EquipmentBoxDao;
import com.lsts.equipment2.service.EquipmentBoxManager;
import com.lsts.equipment2.service.EquipmentBoxRecordManager;
import com.lsts.equipment2.service.EquipmentManager;

/**
 *  设备信息控制器
 * @ClassName EquipmentAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-02-18 上午11:26:00
 */
@Controller
@RequestMapping("equipment2Action")
public class EquipmentAction extends SpringSupportAction<Equipment, EquipmentManager>{

	@Autowired
	private EquipmentManager baseEquipmentService;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private EquipmentBoxManager equipmentBoxManager;
	@Autowired
    EquipmentBoxDao equipmentBoxDao;
	@Autowired
	EquipmentBoxRecordManager equipmentBoxRecordManager;
	/**
     * 保存设备信息导入文件
     * @param files
     * @return
     */
    @RequestMapping(value = "saveTask")
	@ResponseBody
	public HashMap<String,Object> saveTask(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		String[] contents = new String[2];
		try {
			contents = baseEquipmentService.saveTaskData(files,getCurrentUser());
			data.put("success", true);
			data.put("total",contents[0]);
	    	data.put("repData",contents[1]);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
	
    /**
     * 保存检定周期导入文件
     * @param files
     * @return
     */
    @RequestMapping(value = "saveJdzq")
	@ResponseBody
	public HashMap<String,Object> saveJdzq(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		String[] contents = new String[2];
		try {
			contents = baseEquipmentService.saveJdzq(files,getCurrentUser());
			data.put("success", true);
			data.put("total",contents[0]);
	    	data.put("repData",contents[1]);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
    
	/**
	 * 保存
	 * 
	 * @param request
	 * @param baseEquipment
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> save(HttpServletRequest request, Equipment baseEquipment) throws Exception {
		String status = request.getParameter("status");
		String uploadFiles = request.getParameter("uploadFiles");
		String fk_uv_id= request.getParameter("fk_uv_id");
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			if ("add".equals(status)) {
				if(StringUtil.isNotEmpty(fk_uv_id)){
					EquipmentBox equipmentBox = equipmentBoxManager.get(fk_uv_id);
					baseEquipment.setEquipmentBox(equipmentBox);
					baseEquipment.setBox_status("01");//已装箱
				}else {
					baseEquipment.setBox_status("02");//未装箱
				}
				if(baseEquipment.getEq_category().equals("01")){
					String barcode = baseEquipment.getBarcode();
					Equipment equipment = baseEquipmentService.searchByBarcode(barcode);
					if(equipment==null){
						baseEquipment.setBarcode(barcode);
					}else{
						while(true){
							String barcode2 = baseEquipmentService.barcode();//生成13位数的条形码
							Equipment equipment1 = baseEquipmentService.searchByBarcode(barcode2);
							if(equipment1==null){
								baseEquipment.setBarcode(barcode2);
								break;
							}
						}
					}
					/*Calendar calendar = Calendar.getInstance();
					Date nowTime = baseEquipment.getEq_buy_date();//获得购买时间
			        calendar.setTime(nowTime);
			        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
			        String number = baseEquipment.getEq_check_cycle();//获得周期（月份）
			        java.math.BigDecimal bg2 = new BigDecimal(number);//把String的周期方进来
			        int  i1 = bg2.intValue();//再转成 Int 直接转成Int会出错 
			        calendar.add(Calendar.MONTH, i1);   //得到的结果日期*/
					/*Date nextCheckDate = baseEquipmentService.nextCheckDate(baseEquipment.getEq_buy_date(), baseEquipment.getEq_check_cycle());*/
					baseEquipment.setEq_user1_id(baseEquipment.getEq_user_id());
					baseEquipment.setEq_user1(baseEquipment.getEq_user());
					baseEquipment.setEq_use_department1_id(baseEquipment.getEq_use_department_id());
					baseEquipment.setEq_use_department1(baseEquipment.getEq_use_department());
					/*baseEquipment.setEq_next_check_date(nextCheckDate);*/
					if(StringUtil.isEmpty(baseEquipment.getEq_use_status())){
						baseEquipment.setEq_use_status("kx");	// 设备使用状态，码表：TJY2_EQ_USE_STATUS（01：在用；02：停用；03：空闲）
					}
					if(StringUtil.isEmpty(baseEquipment.getEq_buy_count())){
						baseEquipment.setEq_buy_count("1");	// 设备（购买、入库）数量默认为1
					}
					if(StringUtil.isEmpty(baseEquipment.getEq_inout_status())){
						baseEquipment.setEq_inout_status("drk");	// 设备默认出入库状态为待入库
					}
				}
				baseEquipment.setCreate_by(curUser.getName());
				baseEquipment.setCreate_date(new Date());
				baseEquipment.setEq_isdelete("0");//初始化删除状态为未删除
				baseEquipmentService.saveEquipment(baseEquipment, uploadFiles);
			}else if ("modify".equals(status)) {
				Equipment baseEquipment_1 = baseEquipmentService.get(baseEquipment.getId());
				/*卡片编号*/
				baseEquipment_1.setCard_no(baseEquipment.getCard_no());
				/*档案分类号*/
				baseEquipment_1.setEq_archive_class_id(baseEquipment.getEq_archive_class_id());
				/*设备编号*/
				baseEquipment_1.setEq_no(baseEquipment.getEq_no());
				/*设备名称*/
				baseEquipment_1.setEq_name(baseEquipment.getEq_name());
				/*规格型号*/
				baseEquipment_1.setEq_model(baseEquipment.getEq_model());
				/*出厂编号*/
				baseEquipment_1.setEq_sn(baseEquipment.getEq_sn());
				/*设备原值*/
				baseEquipment_1.setEq_value(baseEquipment.getEq_value());
				/*精度*/
				baseEquipment_1.setEq_accurate(baseEquipment.getEq_accurate());
				/*购买日期*/
				baseEquipment_1.setEq_buy_date(baseEquipment.getEq_buy_date());
				/*制造厂家*/
				baseEquipment_1.setEq_manufacturer(baseEquipment.getEq_manufacturer());
				/*检定周期*/
				baseEquipment_1.setEq_check_cycle(baseEquipment.getEq_check_cycle());
				/*设备状态*/
				baseEquipment_1.setEq_status(baseEquipment.getEq_status());
				/*管理（使用）人员*/
				baseEquipment_1.setEq_user_id(baseEquipment.getEq_user_id());
				baseEquipment_1.setEq_user(baseEquipment.getEq_user());
				/*管理（使用）部门*/
				baseEquipment_1.setEq_use_department_id(baseEquipment.getEq_use_department_id());
				baseEquipment_1.setEq_use_department(baseEquipment.getEq_use_department());
				/*设备箱信息*/
				if(StringUtil.isNotEmpty(fk_uv_id)){
					EquipmentBox equipmentBox = equipmentBoxManager.get(fk_uv_id);
					baseEquipment_1.setEquipmentBox(equipmentBox);
					baseEquipment_1.setBox_status("01");//已装箱
					baseEquipment_1.setBox_number(baseEquipment.getBox_number());
				}else {
					baseEquipment_1.setEquipmentBox(null);
					baseEquipment_1.setBox_status("02");//未装箱
					baseEquipment_1.setBox_number(null);
				}
				/*是否盘点*/
				baseEquipment_1.setInventory(baseEquipment.getInventory());
				/*设备位置*/
				baseEquipment_1.setPosition(baseEquipment.getPosition());
				/*归属*/
				baseEquipment_1.setOwner(baseEquipment.getOwner());
				/*出入库状态*/
				baseEquipment_1.setEq_inout_status(baseEquipment.getEq_inout_status());
				/*备注*/
				baseEquipment_1.setRemark(baseEquipment.getRemark());
				baseEquipment_1.setLast_modify_date(new Date());
				baseEquipment_1.setLast_modify_by(curUser.getUserName());
				baseEquipmentService.saveEquipment(baseEquipment_1, uploadFiles);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存设备信息失败，请重试！");
		}
		return JsonWrapper.successWrapper(baseEquipment);
	}
	
	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "dle")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String ids) throws Exception {
		Equipment baseEquipment = baseEquipmentService.get(ids); 
		List<Attachment> list = attachmentManager.getBusAttachment(ids);
		String fk_uv_id = "";
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		if(baseEquipment.getEquipmentBox()!=null) {
			fk_uv_id = baseEquipment.getEquipmentBox().getId();
		}
		wrapper.put("success", true);
		wrapper.put("data", baseEquipment);
		wrapper.put("fk_uv_id", fk_uv_id);
		wrapper.put("attachs", list);
		return wrapper;
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public HashMap<String, Object> delete(String ids) throws Exception {
		baseEquipmentService.delete(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 将选中的设备展示在入库列表里
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getList")
	@ResponseBody
	public HashMap<String, Object> getList(HttpServletRequest request, String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		List<Equipment> datalist = new ArrayList<Equipment>();
		try {
			String ids[] = id.split(",");
			if(ids.length>0) {
				for (int i=0;i<ids.length;i++){
					if (StringUtil.isNotEmpty(ids[i])) {
						List<Equipment> list = baseEquipmentService
								.queryBaseEquipmentById(ids[i]);
						if (list!=null && list.size()>0) {
								datalist.add(list.get(0));
						}
						
					}
				}
			}
			wrapper.put("success", true);
			wrapper.put("Rows", datalist);
			
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	/**
	 * 设备启用
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "start")
	@ResponseBody
	public HashMap<String, Object> start(String ids) throws Exception {
		baseEquipmentService.start(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 设备封存
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sealed")
	@ResponseBody
	public HashMap<String, Object> sealed(String ids) throws Exception {
		baseEquipmentService.sealed(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 设备报废
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "scraped")
	@ResponseBody
	public HashMap<String, Object> scraped(String ids) throws Exception {
		baseEquipmentService.scraped(ids);
		return JsonWrapper.successWrapper(ids);
	}
	/**
	 * 出入库通过barcode查询设备信息
	 * 
	 * @param barcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "searchByBarcode")
	@ResponseBody
	public HashMap<String, Object> searchByBarcode(String barcode,String type) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		Equipment baseEquipment = new Equipment();
		try {
			baseEquipment = baseEquipmentService.searchByBarcode(barcode);
			System.out.println("------------------");
			if(type.equals("instock")){
				if(baseEquipment==null){
					wrapper.put("success", false);
					wrapper.put("message", "此设备不存在！");
				}else if(!baseEquipment.equals("")&&baseEquipment!=null){
					if(baseEquipment.getEq_inout_status().equals("yrk")){
						wrapper.put("success", false);
						wrapper.put("message", "此设备已入库！");
					}else{
						wrapper.put("success", true);
						wrapper.put("baseEquipment", baseEquipment);
					}
				}
			}else if(type.equals("outstock")){
				if(baseEquipment==null){
					wrapper.put("success", false);
					wrapper.put("message", "此设备不存在！");
				}else if(!baseEquipment.equals("")&&baseEquipment!=null){
					if(!baseEquipment.getEq_inout_status().equals("yrk")){
						wrapper.put("success", false);
						wrapper.put("message", "此设备未入库！");
					}else{
						wrapper.put("success", true);
						wrapper.put("baseEquipment", baseEquipment);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	
	/**
	 * 通过设备ID查询设备所有出入库记录
	 * equip_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "searchRecord")
	@ResponseBody
	public HashMap<String, Object> searchRecord(HttpServletRequest request,String equip_id) throws Exception {
		String id = request.getParameter("equip_id");
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			List<InoutRecord> inoutRecords = new ArrayList<InoutRecord>();
			inoutRecords = baseEquipmentService.searchRecord(equip_id);
			if(inoutRecords!=null && inoutRecords.size()>0){
				wrapper.put("success", true);
				wrapper.put("Rows", inoutRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("error", true);
		}
		return wrapper;
	}
	
	/**
	 * 通过barcode查询设备信息
	 * 
	 * @param barcode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryByBarcode")
	@ResponseBody
	public HashMap<String, Object> queryByBarcode(String barcode){
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			Equipment baseEquipment = baseEquipmentService.searchByBarcode(barcode);
			if(baseEquipment==null){
				wrapper.put("success", false);
				wrapper.put("message", "没有查到与二维码关联的设备！！！");
			}else{
				wrapper.put("success", true);
				wrapper.put("equipment", baseEquipment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("message", "扫码查询失败！！！");
		}
		return wrapper;
	}
	/**
     * 盘点设备
     * @param id
     * @return
     */
    @RequestMapping(value = "inventory")
	@ResponseBody
	public HashMap<String,Object> inventory(String barcode) {
    	CurrentSessionUser curUser = this.getCurrentUser(); //获取当前用户登录信息
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	Equipment equipment = baseEquipmentService.searchByBarcode(barcode);
    	if(equipment==null){
    		hashMap.put("message", "没查到相应的设备！");
    	}else{
    		equipment.setInventory_date(new Date());
    		equipment.setInventory_id(curUser.getId());
    		equipment.setInventory_name(curUser.getName());
    		equipment.setInventory("YPD");
    		try {
    			baseEquipmentService.save(equipment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		hashMap.put("equipment", equipment);
    	}
		return hashMap;
    	
	}
    
    /**
     * 根据卡片编号和设备类型获取设备档案信息
     * @param card_no
     * @param eq_category
     * @return
     */
    @RequestMapping(value = "checkCardNo")
	@ResponseBody
	public HashMap<String,Object> getByCardNo(String card_no,String eq_category) {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	List<Equipment> list = new ArrayList<Equipment>();
    	list = baseEquipmentService.getByCardNo(card_no,eq_category);
    	hashMap.put("list", list);
		return hashMap;
	}
    
    /**
     * 根据设备编号和设备类型获取设备档案信息
     * @param eq_no
     * @return
     */
    @RequestMapping(value = "checkEqNo")
	@ResponseBody
	public HashMap<String,Object> getByEqNo(String eq_no,String eq_category) {
    	HashMap<String, Object> hashMap = new HashMap<String, Object>();
    	List<Equipment> list = new ArrayList<Equipment>();
    	list = baseEquipmentService.getByEqNo(eq_no,eq_category);
    	hashMap.put("list", list);
		return hashMap;
	}
    /**
     * 根据ID获取工检具信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getToolDetail")
	@ResponseBody
	public HashMap<String,Object> getToolDetail(String id) {
    	Equipment toolDetail=baseEquipmentService.get(id);
    	return JsonWrapper.successWrapper(toolDetail);
	}
    /**
     * 保存工检具信息导入文件
     * @param files
     * @return
     */
    @RequestMapping(value = "saveToolTask")
	@ResponseBody
	public HashMap<String,Object> saveToolTask(String files) {
		HashMap<String,Object> data=new HashMap<String,Object>();
		String[] contents = new String[2];
		try {
			contents = baseEquipmentService.saveTaskData(files,getCurrentUser());
			data.put("success", true);
			data.put("total",contents[0]);
	    	data.put("repData",contents[1]);
		} catch(Exception e) {
			data.put("success", false);
		}
    	return  data;
	}
}
