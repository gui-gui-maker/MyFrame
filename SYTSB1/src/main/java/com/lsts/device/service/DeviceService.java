package com.lsts.device.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.base.Factory;
import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rbac.impl.bean.Employee;
import com.khnt.rbac.impl.bean.Org;
import com.khnt.rbac.impl.bean.User;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.Accessory;
import com.lsts.device.bean.BoilerPara;
import com.lsts.device.bean.CablewayPara;
import com.lsts.device.bean.CranePara;
import com.lsts.device.bean.DeviceDTO;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.bean.ElevatorPara;
import com.lsts.device.bean.EnginePara;
import com.lsts.device.bean.PressurevesselsPara;
import com.lsts.device.bean.RidesPara;
import com.lsts.device.dao.DeviceDao;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.service.SysLogService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.dao.EnterDao;

import util.TS_Util;

/**
 * 特种设备信息管理   servier
 * 
 * @author 肖慈边 2014-1-8
 */
@Service("deviceService")
public class DeviceService extends EntityManageImpl<DeviceDocument, DeviceDao> {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static Connection conn = null;  // 数据库连接
    private static PreparedStatement pstmt = null; // 数据库操作对象
    private static ResultSet rs = null; // 结果集
	
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private EnterDao enterDao;
	@Autowired
	private InspectionInfoDao infoDao;
	@Autowired
	private SysLogService logService;
	
	
	public void changeInfo(InspectionInfo entity,String id) throws Exception {
		// TODO Auto-generated method stub
		
		
		
//		infoDao.createSQLQuery("update  tzsb_inspection_info   set  advance_time=to_date('"+entity.getAdvance_time()+"','yyyy-MM-dd'),"
//				+ " report_sn='"+entity.getReport_sn()+"', last_check_time=to_date('"+entity.getLast_check_time()+"','yyyy-MM-dd'),"
//				+ " inspection_conclusion='"+entity.getInspection_conclusion()+"' where fk_tsjc_device_document_id ='"+id+"'").executeUpdate();
		entity.setFk_tsjc_device_document_id(id);
		infoDao.save(entity);
		
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
		
		DeviceDocument device =deviceDao.get(id);
		device.setInspect_conclusion(entity.getInspection_conclusion());
		device.setInspect_date(entity.getAdvance_time());
		device.setInspect_next_date(entity.getLast_check_time());
		device.setLast_upd_date(new Date());
		device.setSend_status("0");
		device.setLast_upd_by(emp.getName());
		try{
			deviceDao.save(device);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		logService.setLogs("", "修改报检信息", "修改报检信息", user.getId(), user.getName(), new Date(), "");
	}
	
	/**
	 * 根据使用单位ID和单位内部编号查询设备信息
	 * @param fk_company_info_use_id -- 使用单位id
	 * @param internal_num -- 单位内部编号
	 * 
	 * @return 
	 * @author GaoYa
	 * @date 2016-01-29 下午01:05:00
	 */
    public List<String> queryByInternal_num(String fk_company_info_use_id, String internal_num, String device_sort_code) {
		return deviceDao.queryByInternal_num(fk_company_info_use_id, internal_num, device_sort_code);
	}
	
	public void saveBasic(DeviceDocument entity,String type) throws Exception {
		// TODO Auto-generated method stub
		
		if("3".equals(type)){
			for (ElevatorPara eleva : entity.getElevatorParas()) {
				if("".equals(eleva.getId())){
					eleva.setId(null);
				}else{
					if (eleva.getId().equals(entity.getId())) {
						eleva.setId(null);
					}
				}
				eleva.setDeviceDocument(entity);
			}
			
		}else if("1".equals(type)){
			for (BoilerPara boiler : entity.getBoiler()) {
				if("".equals(boiler.getId())){
					boiler.setId(null);
				}else{
					if (boiler.getId().equals(entity.getId())) {
						boiler.setId(null);
					}
				}
				boiler.setDeviceDocument(entity);
			}
		}else if("4".equals(type)){
			for (CranePara crane : entity.getCrane()) {
				if("".equals(crane.getId())){
					crane.setId(null);
				}else{
					if (crane.getId().equals(entity.getId())) {
						crane.setId(null);
					}
				}
				crane.setDeviceDocument(entity);
			}
		}else if("2".equals(type)){
			for (PressurevesselsPara pressurevessels : entity.getPressurevessels()) {
				if("".equals(pressurevessels.getId())){
					pressurevessels.setId(null);
				}else{
					if (pressurevessels.getId().equals(entity.getId())) {
						pressurevessels.setId(null);
					}
				}
				pressurevessels.setDeviceDocument(entity);
				//如果是常压罐车，单位内部编号设置成为车牌号
				if(entity.getDevice_sort_code().equals("2610")){
					if(StringUtil.isNotEmpty(pressurevessels.getLadle_car_number())){
						entity.setInternal_num(pressurevessels.getLadle_car_number());
					}
				}
			}
			
		}else if("5".equals(type)){
			for (EnginePara engine : entity.getEngine()) {
				if("".equals(engine.getId())){
					engine.setId(null);
				}else{
					if (engine.getId().equals(entity.getId())) {
						engine.setId(null);
					}
				}
				engine.setDeviceDocument(entity);
			}
		}else if("6".equals(type)){
			try{
			for (RidesPara rides : entity.getRidesPara()) {
				if("".equals(rides.getId())){
					rides.setId(null);
				}else{
					if (rides.getId().equals(entity.getId())) {
						rides.setId(null);
					}
				}
				rides.setDeviceDocument(entity);
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}
		}else if("9".equals(type)){		// 客运索道
			List<CablewayPara> cablewayParaList = new ArrayList<CablewayPara>();
			for (CablewayPara cablewayPara : entity.getCableway()) {
				if(StringUtil.isEmpty(cablewayPara.getId())){
					cablewayPara.setId(null);
				}else{
					if (cablewayPara.getId().equals(entity.getId())) {
						cablewayPara.setId(null);
					}
				}
				cablewayPara.setDeviceDocument(entity);
				cablewayParaList.add(cablewayPara);
			}
			entity.getCableway().clear();
			for (CablewayPara cablewayPara : cablewayParaList) {
				if (cablewayPara!=null) {
					entity.addCableway(cablewayPara);
				}
			}
		}else if("Q".equals(type)||"7".equals(type)||"F".equals(type)){
			for (Accessory accessory : entity.getAccessory()) {
				if("".equals(accessory.getId())){
					accessory.setId(null);
				}else{
					if (accessory.getId().equals(entity.getId())) {
						accessory.setId(null);
					}
				}
				accessory.setDeviceDocument(entity);
			}
		}

		
		
		if(StringUtil.isEmpty(entity.getDevice_registration_code())){
			// 2017-08-25质量部谢方要求修改，除电梯外不得自行产生设备注册代码并出具检验报告，默认为“/”（电梯除外）
			// 2017-09-07根据9.6省院市局协调会纪要和成质监特[2017]48号文件要求，停止检验软件内所有设备注册代码的新生成
			/*if ("3".equals(type)) {
				String registration_code="";
				//if(!entity.getDevice_sort_code().substring(0, 2).equals("26")){
					//自动生成注册代码
					//registration_code = TS_Util.getRegistrationCode(entity.getDevice_sort_code(),entity.getDevice_area_code());
					registration_code = deviceDao.getRegistrationCode(entity.getDevice_sort_code(),entity.getDevice_area_code());
					entity.setDevice_registration_code(registration_code);
				//}
			}else{
				entity.setDevice_registration_code("/");
			}*/
			entity.setDevice_registration_code("/");
			
			/*if ("4".equals(type)) {
				entity.setRegistration_num(registration_code);	// 起重机的使用登记证编号自动生成（用设备注册代码），2015-06-18省院机电五部检验员需求
			}*/
		}else{
			// 原设备注册代码生成不是标准代码（长度20），则重新生成设备注册代码
			/*if (entity.getDevice_registration_code().length() != 20) {
				// 自动生成注册代码
				String registration_code = TS_Util.getRegistrationCode(
						entity.getDevice_sort_code(),
						entity.getDevice_area_code());

				entity.setDevice_registration_code(registration_code);
				if ("4".equals(type)) {
					entity.setRegistration_num(registration_code); // 起重机的使用登记证编号自动生成（用设备注册代码），2015-06-18省院机电五部检验员需求
				}
			}*/
		}
		
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
	
		
	
		
		
		
		//设置设备状态为可使用
		entity.setDevice_status("2");
		entity.setIs_cur_check("1");//设置未报检
		entity.setSend_status("0");//记录数据被修改
		//处理设备名称空白
		entity.setDevice_name(entity.getDevice_name().trim());
		
		
		if(entity.getId()==null||"".equals(entity.getId())){
		//创建日期和创建人员
			entity.setCreated_date(new Date());
			entity.setCreated_by(emp.getName());
		
		}
		
		entity.setLast_upd_date(new Date());
		entity.setLast_upd_by(emp.getName());
		
		
		deviceDao.save(entity);
	}
	
	public void saveSimple(DeviceDocument entity) throws Exception {
		// TODO Auto-generated method stub
		
		String type = entity.getDevice_sort_code().toString().substring(0,1);
		
		if("3".equals(type)){
			ElevatorPara eleva = new ElevatorPara();
			eleva.setDeviceDocument(entity);
			List list = new ArrayList();
			
			list.add(eleva);
			
			entity.setElevatorParas(list);
			
				
			
		}else if("1".equals(type)){
			BoilerPara boiler =new BoilerPara();
			
			boiler.setDeviceDocument(entity);
			List list = new ArrayList();
			
			list.add(boiler);
			
			entity.setBoiler(list);
			
		}else if("4".equals(type)){
			CranePara crane =new CranePara();
			crane.setDeviceDocument(entity);
			List list = new ArrayList();
			
			list.add(crane);
			
			entity.setCrane(list);
			
		}else if("2".equals(type)){
			PressurevesselsPara pressurevessels  = new PressurevesselsPara();
			pressurevessels.setDeviceDocument(entity);
			List list = new ArrayList();
				
			list.add(pressurevessels);
			entity.setPressurevessels(list);
			
		}else if("5".equals(type)){
			EnginePara engine = new EnginePara();
			engine.setDeviceDocument(entity);
			List list = new ArrayList();
			
			list.add(engine);
			
			entity.setEngine(list);
			
		}else if("6".equals(type)){
			RidesPara rides = new RidesPara();
			rides.setDeviceDocument(entity);
			List list = new ArrayList();
			list.add(rides);
			entity.setRidesPara(list);
			
		}else if("Q".equals(type)){
			Accessory accessory = new Accessory();
			
			accessory.setDeviceDocument(entity);
			List list = new ArrayList();
			
			list.add(accessory);
			
			entity.setAccessory(list);
			
		}

		
		
		CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
		User user = (User)curUser.getSysUser();
		Employee emp = (com.khnt.rbac.impl.bean.Employee)user.getEmployee();
		Org org = TS_Util.getCurOrg(curUser);
	
		//设置设备状态为可使用
		entity.setDevice_status("2");
		entity.setIs_cur_check("1");//设置报检状态未报检
		entity.setSend_status("0");//记录数据被修改
		
		if(entity.getId()==null||"".equals(entity.getId())){
		//创建日期和创建人员
			entity.setCreated_date(new Date());
			entity.setCreated_by(emp.getName());
		
		}
		
		entity.setLast_upd_date(new Date());
		entity.setLast_upd_by(emp.getName());
		
		
		super.save(entity);
	}
	

	public void del(String ids) {
		try {
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			// TODO Auto-generated method stub
			//判断是否多个ID
			if(ids.indexOf(",")!=-1){
				String id[] =ids.split(",");	
				for(int i=0;i<id.length;i++){
					//DeviceDocument device =deviceDao.get(id);
					//device.setDevice_status("99");	// 99：已删除
					//device.setLast_upd_by(user.getName());
					//device.setLast_upd_date(new Date());
					deviceDao.createSQLQuery("update  BASE_DEVICE_DOCUMENT  set device_status='99',send_status='0',last_upd_by=?,last_upd_date=? where id =?",user.getName(), new Date(), id[i] ).executeUpdate();
					//deviceDao.save(device);
				}	
			}else{
				//DeviceDocument device =deviceDao.get(ids);
				//device.setDevice_status("99");	
				//device.setLast_upd_by(user.getName());
				//device.setLast_upd_date(new Date());
				deviceDao.createSQLQuery("update  BASE_DEVICE_DOCUMENT  set device_status='99',send_status='0',last_upd_by=?,last_upd_date=? where id =?",user.getName(), new Date(), ids ).executeUpdate();
				//deviceDao.save(device);
				//deviceDao.save(device);
			}
		} catch (Exception e) {
			log.debug(e.toString());
			e.printStackTrace();
		}
		
	}
	
	public List<Map<String, String>> changeCom(HttpServletRequest request, DeviceDTO entity)
			throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();	 
		List<Map<String, String>> oldList = new ArrayList<Map<String,String>>();
		EnterInfo enter = null;
		if (StringUtil.isNotEmpty(entity.getFk_company_info_use_id())) {
			enter = enterDao.get(entity.getFk_company_info_use_id());
		}
		EnterInfo wbComInfo = null;
		if (StringUtil.isNotEmpty(entity.getFk_maintain_unit_id())) {
			wbComInfo = enterDao.get(entity.getFk_maintain_unit_id());
		}
		String sql = "update BASE_DEVICE_DOCUMENT set";
		boolean hasMdyCondition = false;
		if (StringUtil.isNotEmpty(entity.getFk_company_info_use_id()) && enter != null) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " FK_COMPANY_INFO_USE_ID='"+entity.getFk_company_info_use_id()+"', COMPANY_NAME='"+enter.getCom_name()+"'";
			if (StringUtil.isNotEmpty(enter.getCom_code())) {
				sql += ", COM_CODE='"+enter.getCom_code()+"'";
			}
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_area_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_AREA_CODE='"+entity.getDevice_area_code()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_area_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_AREA_NAME='"+entity.getDevice_area_name()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_street_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_STREET_CODE='"+entity.getDevice_street_code()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_street_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_STREET_NAME='"+entity.getDevice_street_name()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_use_place())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_USE_PLACE='"+entity.getDevice_use_place()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getFk_maintain_unit_id()) && wbComInfo != null) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " FK_MAINTAIN_UNIT_ID='"+entity.getFk_maintain_unit_id()+"', MAINTAIN_UNIT_NAME='"+wbComInfo.getCom_name()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getMaintenance_man())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " MAINTENANCE_MAN='"+entity.getMaintenance_man()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getMaintenance_tel())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " MAINTENANCE_TEL='"+entity.getMaintenance_tel()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getSecurity_op())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " SECURITY_OP='"+entity.getSecurity_op()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getSecurity_tel())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " SECURITY_TEL='"+entity.getSecurity_tel()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getRegistration_agencies())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " REGISTRATION_AGENCIES='"+entity.getRegistration_agencies()+"'";
		}
		if (hasMdyCondition) {
			sql += ",SEND_STATUS='0',LAST_UPD_BY='"+user.getName()+"',LAST_UPD_DATE = to_date('"+DateUtil.getCurrentDateTime()+"','YYYY-MM-DD HH24:MI:SS')";
		}else{
			hasMdyCondition = true;
		}
		boolean oState = false;
		// 获取数据库连接
		Connection conn = Factory.getDB().getConnetion();
		try{
			if (hasMdyCondition) {
				// 获取当前自动提交状态
				oState = conn.getAutoCommit();
				conn.setAutoCommit(false);
				
				Statement war = conn.createStatement();
				String deviceId = request.getParameter("deviceId");
				String temp[] = deviceId.split(",");
				for(int i=0;i<temp.length;i++){
					// 执行修改前，获取原数据
					Map<String, String> oldMap = queryDeviceById(temp[i]);
					oldList.add(oldMap);
					// 执行修改
					war.executeUpdate(sql+" where id='"+temp[i]+"'");
					// 记录日志
					logService.setLogs(temp[i], "修改设备信息", "修改设备信息", user.getId(), user
								.getName(), new Date(), request.getRemoteAddr());
					
				}
				war.close();
				conn.commit();
			}
		 } catch (Exception e){				
			try{
				conn.rollback();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
		 } finally{
			try{
				// 默认自动提交状态归位
				conn.setAutoCommit(oState);
				// 释放数据库连接
				Factory.getDB().freeConnetion(conn);
			} catch (Exception e){
				e.printStackTrace();					
			}
		 }
		 return oldList;
	}
	
	public List<Map<String, String>> modifyDevice(HttpServletRequest request, DeviceDTO entity)
	throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		List<Map<String, String>> oldList = new ArrayList<Map<String,String>>();
		EnterInfo enter = null;
		if (StringUtil.isNotEmpty(entity.getFk_company_info_use_id())) {
			enter = enterDao.get(entity.getFk_company_info_use_id());
		}
		
		String sql = "update BASE_DEVICE_DOCUMENT set";
		boolean hasMdyCondition = false;
		if (StringUtil.isNotEmpty(entity.getFk_company_info_use_id()) && enter != null) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " FK_COMPANY_INFO_USE_ID='"+entity.getFk_company_info_use_id()+"', COMPANY_NAME='"+enter.getCom_name()+"'";
			if (StringUtil.isNotEmpty(enter.getCom_code())) {
				sql += ", COM_CODE='"+enter.getCom_code()+"'";
			}
		}
		
		if (StringUtil.isNotEmpty(entity.getFk_maintain_unit_id())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " fk_maintain_unit_id='"+entity.getFk_maintain_unit_id()+"', maintain_unit_name='"+entity.getMaintain_unit_name()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getMaintenance_man())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " maintenance_man='"+entity.getMaintenance_man()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getMaintenance_tel())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " maintenance_tel='"+entity.getMaintenance_tel()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getSecurity_op())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " security_op='"+entity.getSecurity_op()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getSecurity_tel())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " security_tel='"+entity.getSecurity_tel()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_area_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_AREA_CODE='"+entity.getDevice_area_code()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_area_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_AREA_NAME='"+entity.getDevice_area_name()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_street_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_STREET_CODE='"+entity.getDevice_street_code()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_street_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_STREET_NAME='"+entity.getDevice_street_name()+"'";
		}
		
		if (StringUtil.isNotEmpty(entity.getDevice_use_place())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " DEVICE_USE_PLACE='"+entity.getDevice_use_place()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getRegistration_agencies())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " REGISTRATION_AGENCIES='"+entity.getRegistration_agencies()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getRegistration_num())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " REGISTRATION_NUM='"+entity.getRegistration_num()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getRegistration_op())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " REGISTRATION_OP='"+entity.getRegistration_op()+"'";
		}
		if (StringUtil.isNotEmpty(entity.getRegistration_date())) {
			if (hasMdyCondition) {
				sql += " ,";
			}else{
				hasMdyCondition = true;
			}
			sql += " REGISTRATION_DATE = to_date('"+entity.getRegistration_date()+"','yyyy-MM-dd')";
		}
		if (hasMdyCondition) {
			sql += ",SEND_STATUS='0',LAST_UPD_BY='"+user.getName()+"',LAST_UPD_DATE = to_date('"+DateUtil.getCurrentDateTime()+"','YYYY-MM-DD HH24:MI:SS')";
		}else{
			hasMdyCondition = true;
		}
		
		boolean oState = false;
		// 获取数据库连接
		Connection conn = Factory.getDB().getConnetion();
		try{
			if (hasMdyCondition) {
				// 获取当前自动提交状态
				oState = conn.getAutoCommit();
				conn.setAutoCommit(false);
				
				Statement war = conn.createStatement();
				String deviceId = request.getParameter("deviceId");
				String temp[] = deviceId.split(",");
				for(int i=0;i<temp.length;i++){
					// 执行修改前，获取原数据
					Map<String, String> oldMap = queryDeviceById(temp[i]);
					oldList.add(oldMap);
					// 执行修改
					war.executeUpdate(sql+" where id='"+temp[i]+"'");
					// 记录日志
					logService.setLogs(temp[i], "区县局修改设备信息", "区县局修改设备信息", user.getId(), user
								.getName(), new Date(), request.getRemoteAddr());
					
				}
				war.close();
				conn.commit();
			}
		 } catch (Exception e){				
			try{
				conn.rollback();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
		 } finally{
			try{
				// 默认自动提交状态归位
				conn.setAutoCommit(oState);
				// 释放数据库连接
				Factory.getDB().freeConnetion(conn);
			} catch (Exception e){
				e.printStackTrace();					
			}
		 }
		 return oldList;
	}
	
	public List<Map<String, String>> updateDevices(HttpServletRequest request, DeviceDocument device, String device_id) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		List<Map<String, String>> oldList = new ArrayList<Map<String, String>>();

		String sql = "update BASE_DEVICE_DOCUMENT set";
		boolean hasMdyCondition = false;
		// 使用单位代码
		if (StringUtil.isNotEmpty(device.getCom_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " COM_CODE='" + device.getCom_code() + "'";
			hasMdyCondition = true;
		}
		// 单位内编号
		if (StringUtil.isNotEmpty(device.getInternal_num())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " INTERNAL_NUM='" + device.getInternal_num() + "'";
			hasMdyCondition = true;
		}
		// 金属二维码
		if (StringUtil.isNotEmpty(device.getDevice_qr_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " DEVICE_QR_CODE='" + device.getDevice_qr_code() + "'";
			hasMdyCondition = true;
		}
		// 设备型号
		if (StringUtil.isNotEmpty(device.getDevice_model())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " DEVICE_MODEL='" + device.getDevice_model() + "'";
			hasMdyCondition = true;
		}
		// 出厂编号（产品编号）
		if (StringUtil.isNotEmpty(device.getFactory_code())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " FACTORY_CODE='" + device.getFactory_code() + "'";
			hasMdyCondition = true;
		}
		// 安全管理人员
		if (StringUtil.isNotEmpty(device.getSecurity_op())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " security_op='" + device.getSecurity_op() + "'";
			hasMdyCondition = true;
		}
		// 安全管理人员联系电话
		if (StringUtil.isNotEmpty(device.getSecurity_tel())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " security_tel='" + device.getSecurity_tel() + "'";
			hasMdyCondition = true;
		}
		// 设备使用地点
		if (StringUtil.isNotEmpty(device.getDevice_use_place())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " DEVICE_USE_PLACE='" + device.getDevice_use_place() + "'";
			hasMdyCondition = true;
		}
		// 制造日期
		if (StringUtil.isNotEmpty(device.getMake_date())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " MAKE_DATE='" + device.getMake_date() + "'";
			hasMdyCondition = true;
		}
		/*// 制造单位
		if (StringUtil.isNotEmpty(device.getMake_units_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " MAKE_UNITS_NAME='" + device.getMake_units_name() + "'";
			hasMdyCondition = true;
		}
		// 维保单位
		if (StringUtil.isNotEmpty(device.getMaintain_unit_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " MAINTAIN_UNIT_NAME='" + device.getMaintain_unit_name() + "'";
			hasMdyCondition = true;
		}
		// 安装/施工单位
		if (StringUtil.isNotEmpty(device.getConstruction_units_name())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " CONSTRUCTION_UNITS_NAME='" + device.getConstruction_units_name() + "'";
			hasMdyCondition = true;
		}*/
		// 施工单位许可证编号
		if (StringUtil.isNotEmpty(device.getConstruction_licence_no())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " CONSTRUCTION_LICENCE_NO='" + device.getConstruction_licence_no() + "'";
			hasMdyCondition = true;
		}
		// 使用登记证号
		if (StringUtil.isNotEmpty(device.getRegistration_num())) {
			if (hasMdyCondition) {
				sql += " ,";
			}
			sql += " REGISTRATION_NUM='" + device.getRegistration_num() + "'";
			hasMdyCondition = true;
		}
		if (hasMdyCondition) {
			sql += ",SEND_STATUS='0',LAST_UPD_BY='" + user.getName() + "',LAST_UPD_DATE = to_date('"
					+ DateUtil.getCurrentDateTime() + "','YYYY-MM-DD HH24:MI:SS')";
		}
		if (hasMdyCondition) {
			boolean oState = false;
			// 获取数据库连接
			Connection conn = Factory.getDB().getConnetion();
			try {
				// 获取当前自动提交状态
				oState = conn.getAutoCommit();
				conn.setAutoCommit(false);
				Statement war = conn.createStatement();

				// 执行修改前，获取原数据
				Map<String, String> oldMap = queryDeviceById(device_id);
				oldList.add(oldMap);
				// 执行修改
				war.executeUpdate(sql + " where id='" + device_id + "'");
				// 记录日志
				logService.setLogs(device_id, "同步设备信息",
						StringUtil.isNotEmpty(device.getNote()) ? device.getNote() : "修改设备基础信息", user.getId(),
						user.getName(), new Date(), request.getRemoteAddr());
				war.close();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					// 默认自动提交状态归位
					conn.setAutoCommit(oState);
					// 释放数据库连接
					Factory.getDB().freeConnetion(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return oldList;
	}
	
	
	
	public HashMap<String, Object> getNum(String device_code)
			throws Exception {
		
		 HashMap<String, Object>  map = new HashMap();
		 
		List list =   deviceDao.createSQLQuery("select id from base_device_document where device_registration_code='"+device_code+"' and device_status <> '99'").list();
		 
		 if(list.size()>0){
			 map.put("success", true);
		 }else{
			 map.put("success", false); 
		 }
		
		return map;
	
	}
	
	/*// 验证电梯二维码编号是否重复
	public HashMap<String, Object> getQRCode(String device_qr_code) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List list = deviceDao
				.createSQLQuery("select id,device_registration_code from base_device_document where device_qr_code='"
						+ device_qr_code + "' and device_status <> '99'")
				.list();
		if (list.size() > 0) {
			map.put("success", true);
			map.put("device_registration_code", list.get(1));
		} else {
			map.put("success", false);
		}
		return map;
	}

	// 根据二维码编号获取设备ID
	@SuppressWarnings("unchecked")
	public String getDeviceIdByQRCode(String device_qr_code) throws Exception {
		String device_id = "";
		List list = deviceDao.createSQLQuery("select id from base_device_document where device_qr_code='"
				+ device_qr_code + "' and device_status <> '99'").list();
		if (list.size() > 0) {
			device_id = String.valueOf(list.get(0));
		}
		return device_id;
	}*/
	
	// 根据二维码编号获取设备信息
	@SuppressWarnings("unchecked")
	public List<DeviceDocument> getDeviceInfosByQRCode(String device_id, String device_qr_code) throws Exception {
		List<DeviceDocument> list = new ArrayList<DeviceDocument>();
		String hql = "from DeviceDocument where device_qr_code='"
				+ device_qr_code + "' and device_status <> '99'";
		if(StringUtil.isNotEmpty(device_id)){
			hql += " and id != '"+device_id+"'";
		}
		list = deviceDao.createQuery(hql).list();
		return list;
	}
	
	// 根据使用登记证号获取设备信息
	@SuppressWarnings("unchecked")
	public List<DeviceDocument> getDeviceInfosByRegistrationNum(String device_id, String registration_num) throws Exception {
		List<DeviceDocument> list = new ArrayList<DeviceDocument>();
		String hql = "from DeviceDocument where registration_num='" + registration_num + "' and device_status <> '99'";
		if (StringUtil.isNotEmpty(device_id)) {
			hql += " and id != '" + device_id + "'";
		}
		list = deviceDao.createQuery(hql).list();
		return list;
	}
	
	// 根据设备注册代码查询是否已存在设备信息
	public boolean existCode(String device_registration_code) throws Exception {
		boolean exist = false;
		List list = deviceDao.createSQLQuery("select id from base_device_document where device_registration_code='"
				+ device_registration_code + "' and device_status <> '99'").list();
		if (list.size() > 0) {
			exist = true;
		}
		return exist;
	}
	
	
	public void toLate(String ids) throws Exception {
		// TODO Auto-generated method stub
		//判断是否多个ID
		if(ids.indexOf(",")!=-1){
			
			String id[] =ids.split(",");
			
			for(int i=0;i<id.length;i++){
				
				deviceDao.createSQLQuery("update  BASE_DEVICE_DOCUMENT  set device_status='8' where id =?",id[i] ).executeUpdate();
				
			}
			
		}else{
		
				deviceDao.createSQLQuery("update  BASE_DEVICE_DOCUMENT  set device_status='8' where id = ?", ids).executeUpdate();
			
		}
		
	}
	
	//  根据使用单位ID查询设备信息
	public List<DeviceDocument> queryDevicesByComId(String fk_company_info_use_id) throws Exception {
		return deviceDao.queryDevicesByComId(fk_company_info_use_id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryInfo(String deviceIds) {
		return deviceDao.queryInfo(deviceIds);
	}
	
	// 启用设备
	public HashMap<String, Object> enable(HttpServletRequest request)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");	// 设备ID
		try{
			String temp[] = ids.split(",");
			for(int i=0;i<temp.length;i++){
				DeviceDocument deviceDocument = deviceDao.get(temp[i]);
				// 数据状态（数据字典BASE_DEVICE_STATUS，0：告知 1：审批中 2：使用 4：拆除 5：停用 6：报废 7：废弃信息 99：已删除）
				deviceDocument.setDevice_status("2");
				deviceDocument.setLast_upd_by(user.getName());	// 最后修改人姓名
				deviceDocument.setLast_upd_date(new Date());	// 最后修改时间
				deviceDao.save(deviceDocument);
				logService.setLogs(temp[i], "启用设备", "启用设备", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
			}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 停用设备
	public HashMap<String, Object> disable(HttpServletRequest request)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");	// 设备ID
		try{
			String temp[] = ids.split(",");
			for(int i=0;i<temp.length;i++){
				DeviceDocument deviceDocument = deviceDao.get(temp[i]);
				// 数据状态（数据字典BASE_DEVICE_STATUS，0：告知 1：审批中 2：使用 4：拆除 5：停用 6：报废 7：废弃信息 99：已删除）
				deviceDocument.setDevice_status("5");
				deviceDocument.setLast_upd_by(user.getName());	// 最后修改人姓名
				deviceDocument.setLast_upd_date(new Date());	// 最后修改时间
				deviceDao.save(deviceDocument);
				logService.setLogs(temp[i], "停用设备", "停用设备", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
			}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	// 报废设备
	public HashMap<String, Object> useless(HttpServletRequest request)
		throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object>  map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");	// 设备ID
		try{
			String temp[] = ids.split(",");
			for(int i=0;i<temp.length;i++){
				DeviceDocument deviceDocument = deviceDao.get(temp[i]);
				// 数据状态（数据字典BASE_DEVICE_STATUS，0：告知 1：审批中 2：使用 4：拆除 5：停用 6：报废 7：废弃信息 99：已删除）
				deviceDocument.setDevice_status("6");
				deviceDocument.setLast_upd_by(user.getName());	// 最后修改人姓名
				deviceDocument.setLast_upd_date(new Date());	// 最后修改时间
				deviceDao.save(deviceDocument);
				logService.setLogs(temp[i], "报废设备", "报废设备", user.getId(), user
							.getName(), new Date(), request.getRemoteAddr());
			}
			map.put("success", true);
		 }catch(Exception e){
			 e.printStackTrace();
			 map.put("success", false);
			 map.put("msg", "请求超时，请稍后再试！");
		 }
		return map;
	}
	
	/**
	 * 查询设备
	 * @param query_column -- 查询字段
	 * @param column_value -- 字段值
	 * @return 
	 * @author GaoYa
	 * @date 2014-09-16 上午10:02:00
	 */
    @SuppressWarnings("unchecked")
    public Map<String, String> queryDeviceById(String device_id){
    	Map<String, String> newMap = new HashMap<String, String>();
    	String sql = "select * from base_device_document  where id = '"+device_id+"' "; 
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
        	rs = pstmt.executeQuery();
          	ResultSetMetaData rsmd = rs.getMetaData();
          	int columnCount = rsmd.getColumnCount();
         	while (rs.next()) {
            	for (int i = 1; i <= columnCount; i++) {
            		String column_name = rsmd.getColumnName(i);
            		String column_value = rs.getString(column_name);
                 	newMap.put(column_name.toLowerCase(), column_value);
            	}
        	}     
        } catch (Exception e) {
    
            e.printStackTrace();
        }
        closeConn();
        return newMap;
    }
	
	// 获取数据库连接
    public Connection getConn() {
        try {
            conn = Factory.getDB().getConnetion();
        } catch (Exception e) {
        	logger.error("获取数据库连接失败：" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭连接
    public void closeConn() {
        try {
            /*if (null != rs)
                rs.close();
            if (null != pstmt)
                pstmt.close();
            if (null != conn)
                conn.close();*/
        	Factory.getDB().freeConnetion(conn);	// 释放连接
        } catch (Exception e) {
        	logger.error("关闭数据库连接错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
