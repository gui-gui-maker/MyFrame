package com.lsts.device.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.device.bean.BoilerPara;
import com.lsts.device.bean.CranePara;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.bean.DeviceSpecial;
import com.lsts.device.bean.ElevatorPara;
import com.lsts.device.bean.EnginePara;
import com.lsts.device.bean.PressurevesselsPara;
import com.lsts.device.bean.RidesPara;
import com.lsts.device.bean.RopeWayPara;
import com.lsts.device.bean.TzsbAppDevice;
import com.lsts.device.dao.BoilerParaDao;
import com.lsts.device.dao.CraneParaDao;
import com.lsts.device.dao.DeviceDao;
import com.lsts.device.dao.DeviceSpecialDao;
import com.lsts.device.dao.ElevatorParaDao;
import com.lsts.device.dao.EngineParaDao;
import com.lsts.device.dao.PressurevesselsParaDao;
import com.lsts.device.dao.RidesParaDao;
import com.lsts.device.dao.RopeWayParaDao;
import com.lsts.device.dao.TzsbAppDeviceDao;


/**
 * 设备信息导入业务逻辑对象
 * @ClassName DeviceImportService
 * @JDK 1.7
 * @author Liming
 * @date 2014-05-22 下午02:25:00
 */
@Service("tzsbAppDeviceService")
public class TzsbAppDeviceService{
	@Autowired
	private BoilerParaDao boilerParaDao;
	@Autowired	
	private PressurevesselsParaDao pressurevesselsParaDao;
	@Autowired	
	private DeviceSpecialDao deviceSpecialDao;
	@Autowired	
	private ElevatorParaDao elevatorParaDao;
	@Autowired	
	private CraneParaDao craneParaDao;
	@Autowired	
	private RopeWayParaDao ropeWayParaDao;
	@Autowired	
	private RidesParaDao ridesParaDao;
	@Autowired	
	private EngineParaDao engineParaDao;
    @Autowired
	private TzsbAppDeviceDao tzsbAppDeviceDao;
	@Autowired
	private DeviceDao deviceDao;
    
    public  void delete(String id){
    	tzsbAppDeviceDao.removeById(id);
    }
    
    public void save(TzsbAppDevice entity,HashMap<String, String> params){
            	
            	CurrentSessionUser user = SecurityUtil.getSecurityUser();
    			String construct_sort=params.get("construct_sort");
    			String fk_construct_units_id=params.get("fk_construct_units_id"); //建设单位（使用单位ID）
    			String fk_construction_units_id=params.get("fk_construction_units_id");//施工单位（安装单位ID）
    			String construction_units_name=params.get("construction_units_name");
    			
    			tzsbAppDeviceDao.save(entity);
    				String sql="select a.id from ( select t.* from base_device_categories t where t.parent_id <> 0 connect by prior t.parent_id = t.id start with t.id =? order by t.parent_id asc ) a where rownum = 1";
    				Query res=tzsbAppDeviceDao.createSQLQuery(sql, entity.getDevice_sort_code());
//    				DeviceCategories dc= (DeviceCategories)res.uniqueResult();
    				Object obj=res.uniqueResult();
    				String dcId=obj==null?"":obj.toString();
    				if(!"8000".equals(dcId)){
    					if("1".equals(construct_sort)){
    						DeviceDocument deviceDoc=new DeviceDocument();
    						deviceDoc.setDevice_sort(entity.getDevice_sort());
    						deviceDoc.setDevice_sort_code(entity.getDevice_sort_code());
    						deviceDoc.setDevice_name(entity.getDevice_name());
    						deviceDoc.setInternal_num(entity.getInternal_num());
    						deviceDoc.setFactory_code(entity.getInternal_num());
    						deviceDoc.setFk_company_info_use_id(fk_construct_units_id);
    						deviceDoc.setFk_company_info_install_id(fk_construction_units_id);
    						deviceDoc.setConstruction_units_name(construction_units_name);
    						deviceDoc.setFk_company_info_make_id(entity.getFk_company_info_make_id());
    						deviceDoc.setMake_units_name(entity.getMake_units_name());
    						deviceDoc.setMake_date(entity.getMake_date());
    						deviceDoc.setDevice_status("0");
    						   //加入设备同步状态  
    						deviceDoc.setCreated_by(user.getName());
    						deviceDoc.setCreated_date(new Date());
    						deviceDoc.setLast_upd_by(user.getName());
    						deviceDoc.setLast_upd_date(new Date());
    						deviceDao.save(deviceDoc);
    						
    					    if("1000".equals(dcId)) {
    		                	//如果是锅炉，则插入锅炉参数表信息
    					    	BoilerPara bp=new BoilerPara();
    					    	bp.setDeviceDocument(deviceDoc);
    					    	boilerParaDao.save(bp);
    		                } else if ("2000".equals(dcId)) {
    		                	//如果是压力容器
    		                	PressurevesselsPara pp=new PressurevesselsPara();
    		                	pp.setDeviceDocument(deviceDoc);
    		                	pressurevesselsParaDao.save(pp);
    		                	
    		                } else if ("3000".equals(dcId)) {
    		                	//如果是电梯
    		                	
    			                DeviceSpecial ds=new DeviceSpecial();
    			                ds.setDeviceDocument(deviceDoc);
    		                	deviceSpecialDao.save(ds);
    		                	ElevatorPara ep=new ElevatorPara();
    		                	ep.setDeviceDocument(deviceDoc);
    		                	elevatorParaDao.save(deviceDoc);
    		                	
    		                } else if ("4000".equals(dcId)) {
    		                	//如果是起重机
    		                	DeviceSpecial ds=new DeviceSpecial();
    			                ds.setDeviceDocument(deviceDoc);
    		                	deviceSpecialDao.save(ds);
    			                	
    		                	CranePara cp=new CranePara();
    		                	cp.setDeviceDocument(deviceDoc);
    		                	craneParaDao.save(cp);
    		                } else if ("5000".equals(deviceDoc)) {
    		                	//如果是厂内机动车
    		                	DeviceSpecial ds=new DeviceSpecial();
    			                ds.setDeviceDocument(deviceDoc);
    		                	deviceSpecialDao.save(ds);
    		                	
    		                	EnginePara ep=new EnginePara();
    		                	ep.setDeviceDocument(deviceDoc);
    		                	engineParaDao.save(ep);
    		                } else if ("6000".equals(dcId)) {
    		                	//如果是游乐设施
    		                	DeviceSpecial ds=new DeviceSpecial();
    			                ds.setDeviceDocument(deviceDoc);
    		                	deviceSpecialDao.save(ds);
    		                	
    		                	RidesPara rp=new RidesPara();
    		                	rp.setDeviceDocument(deviceDoc);
    		                	ridesParaDao.save(rp);
    		                } else if ("9000".equals(dcId)) {
    		                	//如果是客运索道
    		                	DeviceSpecial ds=new DeviceSpecial();
    			                ds.setDeviceDocument(deviceDoc);
    		                	deviceSpecialDao.save(ds);
    		                	
    		                	RopeWayPara rwp=new RopeWayPara();
    		                	rwp.setDeviceDocument(deviceDoc);
    		                	ropeWayParaDao.save(rwp);
    		                } 
    					}
    						
    				}
    			}
    public List<TzsbAppDevice> listByAppId(String appId){
    	return tzsbAppDeviceDao.listByAppId(appId);
    	
    }
}
