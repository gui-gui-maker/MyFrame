package com.scts.transfer.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.StringUtil;
import com.lsts.device.bean.BoilerPara;
import com.lsts.device.bean.CranePara;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.bean.DeviceWarningDeal;
import com.lsts.device.bean.ElevatorPara;
import com.lsts.device.bean.EnginePara;
import com.lsts.device.bean.PressurevesselsPara;
import com.lsts.device.bean.RidesPara;
import com.lsts.device.dao.DeviceDao;
import com.lsts.device.dao.DeviceWarningDealDao;
import com.lsts.device.dao.DeviceWarningStatusDao;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.dao.InspectionDao;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.log.service.SysLogService;
import com.lsts.org.bean.EnterInfo;
import com.lsts.org.dao.EnterDao;
import com.lsts.report.dao.ReportDao;
import com.lsts.webservice.cxf.client.device_transfer.inspect.impl.TsInspectionService;
import com.lsts.webservice.cxf.client.device_transfer.server.TsDeivceService;
import com.lsts.webservice.cxf.client.device_transfer.server.impl.TsDeviceService;
import com.lsts.webservice.cxf.client.device_transfer.waring.impl.WarningDealService;
import com.lsts.webservice.cxf.server.IQueryData;
import com.scts.transfer.bean.DeviceTransfer;
import com.scts.transfer.dao.DeviceTransferDao;

import net.sf.json.JSONObject;
import util.TS_Util;

/**
 * 设备数据传输控制器业务逻辑对象
 * @ClassName DeviceTransferService
 * @JDK 1.7
 * @author GaoYa
 * @date 2017-02-22 上午10:25:00
 */
@Service("deviceTransferService")
public class DeviceTransferService extends
		EntityManageImpl<DeviceTransfer, DeviceTransferDao> {
	
	private static final QName SERVICE_NAME = new QName("http://impl.device.ws.ts.zjpt/", "tsDeviceService");
    private static final QName SERVICE_NAME_IN = new QName("http://impl.inspect.ws.ts.zjpt/", "tsInspectionService");
    private static final QName SERVICE_NAME_WAR =  new QName("http://impl.warning.ws.ts.zjpt/", "warningDealService");
	@Autowired
	private DeviceTransferDao deviceTransferDao;
	@Autowired
	private SysLogService logService;
	@Autowired
	private EnterDao enterDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private InspectionDao inspectionDao;
	@Autowired
	private InspectionInfoDao infoDao;
	@Autowired
	private ReportDao  reportDao;
	@Autowired
	private DeviceWarningStatusDao  waringDao;
	@Autowired
	private DeviceWarningDealDao  dealDao;
	
	//获取市局接口类
	private static TsDeivceService getInterface(){
				
	String url="http://10.190.233.150:9090/cxfws/tsDeviceService?wsdl"; 
//		String url="http://192.168.5.93:9090/CDWS/cxfws/tsDeviceService?wsdl"; 
		URL wsdlURL = null; 
		File wsdlFile = new File(url); 
		try { 
			    if (wsdlFile.exists()) { 
			     wsdlURL = wsdlFile.toURL(); 
			    } else { 
			                wsdlURL = new URL(url); 
			     } 
			   } catch (MalformedURLException e) { 
			       e.printStackTrace(); 
			   } 
			   TsDeviceService ss = new TsDeviceService(wsdlURL, SERVICE_NAME); 
			   TsDeivceService port = ss.getTsDeivceServiceImplPort();  
			        
			   return port;
	}
	//获取检验接口
	private static com.lsts.webservice.cxf.client.device_transfer.inspect.TsInspectionService getInspect(){
		
		try{
					
			String url="http://10.190.233.150:9090/cxfws/tsInspectionService?wsdl"; 
//				String url="http://192.168.5.93:9090/CDWS/cxfws/tsInspectionService?wsdl"; 
				URL wsdlURL = null; 
		        File wsdlFile = new File(url); 
		        try { 
				    if (wsdlFile.exists()) { 
				     wsdlURL = wsdlFile.toURL(); 
				    } else { 
				                wsdlURL = new URL(url); 
				     } 
				   } catch (MalformedURLException e) { 
				       e.printStackTrace(); 
				   } 
		      
		        TsInspectionService ss = new TsInspectionService(wsdlURL, SERVICE_NAME_IN);
		        com.lsts.webservice.cxf.client.device_transfer.inspect.TsInspectionService port = ss.getTsInspectionServiceImplPort();    
					        
		        return port;
			}catch(Exception e){
				
			
				e.printStackTrace();
			}
		
				return null;
		}
	//获取预警接口
	private static com.lsts.webservice.cxf.client.device_transfer.waring.WarningDealService getWaring(){
						
		String url="http://10.190.233.150:9090/cxfws/warningDealService?wsdl"; 
//				String url="http://192.168.5.93:9090/CDWS/cxfws/warningDealService?wsdl"; 
				URL wsdlURL = null; 
		        File wsdlFile = new File(url); 
		        try { 
				    if (wsdlFile.exists()) { 
				     wsdlURL = wsdlFile.toURL(); 
				    } else { 
				                wsdlURL = new URL(url); 
				     } 
				   } catch (MalformedURLException e) { 
				       e.printStackTrace(); 
				   } 
		      
		        WarningDealService ss = new WarningDealService(wsdlURL, SERVICE_NAME_WAR);
		        com.lsts.webservice.cxf.client.device_transfer.waring.WarningDealService port =  ss.getWarningDealServiceImplPort();   
					        
		        return port;
		}
	/**
	 * 启动
	 * @param request 
	 * @return 
	 * @author GaoYa
	 * @date 2017-02-22 上午11:12:00
	 */
	public HashMap<String, Object> start(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			DeviceTransfer deviceTransfer = deviceTransferDao.get(id);
			deviceTransfer.setOp_user_id(user.getId());
			deviceTransfer.setOp_user_name(user.getName());
			deviceTransfer.setOp_date(new Date());
			// 数据传输状态（数据字典DEVICE_TRANSFER_STATUS，0：未启动 1：已启动 2：已停止运行）
			deviceTransfer.setData_status("1");
			deviceTransferDao.save(deviceTransfer);

			logService.setLogs(id, "启动设备数据传输运行", "启动设备数据传输运行", user.getId(), user.getName(), new Date(), request.getRemoteAddr());

			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	/**
	 * 停止运行
	 * @param request 
	 * @return 
	 * @author GaoYa
	 * @date 2017-02-22 上午11:13:00
	 */
	public HashMap<String, Object> stop(HttpServletRequest request) throws Exception {
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			DeviceTransfer deviceTransfer = deviceTransferDao.get(id);
			deviceTransfer.setOp_user_id(user.getId());
			deviceTransfer.setOp_user_name(user.getName());
			deviceTransfer.setOp_date(new Date());
			// 数据传输状态（数据字典DEVICE_TRANSFER_STATUS，0：未启动 1：已启动 2：已停止运行）
			deviceTransfer.setData_status("2");
			deviceTransferDao.save(deviceTransfer);

			logService.setLogs(id, "停止设备数据传输运行", "停止设备数据传输运行", user.getId(), user.getName(), new Date(), request.getRemoteAddr());

			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "请求超时，请稍后再试！");
		}
		return map;
	}
	
	/**
	 * 获取设备数据传输管理控制器
	 * @param request 
	 * @return 
	 * @author GaoYa
	 * @date 2017-02-22 下午11:13:00
	 */
	public List<DeviceTransfer> getDeviceTransfer() {
		return deviceTransferDao.getDeviceTransfer();
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//创建WebService客户端代理工厂  
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
		//注册WebService接口  
		factory.setServiceClass(IQueryData.class);  
		
		//设置WebService地址  
		//factory.setAddress("http://localhost:8081/SYTS/ws/QueryData");  
		factory.setAddress("http://kh.scsei.org.cn/ws/QueryData");  
		IQueryData queryService = (IQueryData)factory.create();  
		System.out.println("invoke webservice...");  		
		try {
			// 4、测试根据查询条件（设备ID：device_id）查询罐车检验报告信息 		
            System.out.println("测试根据查询条件（设备device_id）查询罐车检验报告信息，正在查询中，请稍后...");  
            String res1 = queryService.queryGCInfos("device_id", "402883a052166c5b01521a057f486a76");
			System.out.println("返回结果（罐车检验报告信息）：" + res1);  
			// 解析返回结果字符串
            Document document1 = DocumentHelper.parseText(res1);
            Element root1 = document1.getRootElement();
            Element set1 = (Element) root1.selectSingleNode("/root");
            Iterator<Element> iterator1 = set1.elementIterator("set");
            List list1 = set1.elements();
            System.out.println("返回结果数量（罐车检验报告信息）====" + list1.size());
            Element element1 = null;
            while (iterator1.hasNext()) {
                element1 = iterator1.next();
                Iterator iter = element1.attributeIterator();
                while (iter.hasNext()) {
                    Attribute attribute = (Attribute) iter.next();
                    System.out.println(attribute.getName() + ":" + element1.attributeValue(attribute.getName()));                  
                } 
            }
            System.out.println("测试根据查询条件（设备ID：device_id）查询罐车检验报告信息，查询结束。");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * 设备数据传输
	 * @param  
	 * @return 
	 * @author GaoYa
	 * @date 2017-02-22 下午16:27:00
	 */
	public void transferDeviceInfos() {
		try {
			boolean transfer = true;	// 是否传输数据
			
			// 1、检查设备数据传输控制器是否已启动
			List<DeviceTransfer> transferList = deviceTransferDao.getDeviceTransfer();
			for(DeviceTransfer deviceTransfer : transferList){
				// 数据传输状态（0：未启动 1：已启动 2：已停止运行 99：已作废）
				if(!"1".equals(deviceTransfer.getData_status())){
					transfer = false;
				}
			}
			
			if(transfer){
				//上传检验及设备数据
				 upCheckInfo();
				//上传预警数据
				 upWaringInfo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	//获取需要传递市局数据
//	public List<DeviceDocument>  queryDeviceInfo() {
//		return deviceTransferDao.queryDeviceInfo();
//	}
	//获取檢驗報告
	public List<InspectionInfo>  queryCheckInfo() {
			return deviceTransferDao.queryCheckInfo();
	}
	//获取预警数据
	public List<DeviceWarningDeal>  queryDealInfo() {
			return dealDao.queryDealfo();
	}
		
	
	//根据电梯二维码查询数据
	public  String queryQrCode(String QrCode){
		try{
		return getInterface().geDeviceInfoByQrCode("zdfdf342", "adfghgd43", QrCode, "3");
		}catch(Exception e){
			log.info(e.getMessage());
			return "";
		}
	
	}
	
	
	//插入市局电梯数据
	public  String insertInfo(DeviceDocument doc) throws Exception {
		
		String temp="";
		//判断设备类型
		if(doc.getDevice_sort_code().startsWith("1")){
			BoilerPara boiler = new BoilerPara();
			//获取参数信息表
			Collection<BoilerPara> collection   =doc.getBoiler();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				boiler=(BoilerPara)iterator.next();
			
			}
			temp="\"registrationNum\":\""+boiler.getRegistration_num()+"\",\"boilerModel\":\""+boiler.getBoiler_model()+"\",\"boilerRoomType\":\""+boiler.getBoiler_room_type()+"\",\"manufacturer\":\""+boiler.getManufacturer()+"\",\"fkInspectionUnitId\":\""+boiler.getFk_inspection_unit_id()+"\","
					+ "\"boilerStructure\":\""+boiler.getBoiler_structure()+"\","
					+ "\"designPressure\":\""+boiler.getDesign_pressure()+"\","
					+ "\"permitPressure\":\""+boiler.getPermit_pressure()+"\","
					+ "\"ratedOutput\":\""+boiler.getRated_output()+"\","
					+ "\"outletTemperature\":\""+boiler.getOutlet_temperature()+"\","
					+ "\"heatingMethod\":\""+boiler.getHeating_method()+"\","
					+ "\"fuelType\":\""+boiler.getFuel_type()+"\","
					+ "\"boilerUse\":\""+boiler.getBoiler_use()+"\","
					+ "\"useState\":\""+boiler.getUse_state()+"\","
					+ "\"combustionType\":\""+boiler.getCombustion_type()+"\","
					+ "\"waterTreatment\":\""+boiler.getWater_treatment()+"\","
					+ "\"deoxidization\":\""+boiler.getDeoxidization()+"\","
					+ "\"deslagging\":\""+boiler.getDeslagging()+"\","
					+ "\"smokeDust\":\""+boiler.getSmoke_dust()+"\","
					+ "\"stokerNum\":\""+boiler.getStoker_num()+"\","
					+ "\"waterQualityNum\":\""+boiler.getWater_quality_num()+"\","
					+ "\"p10001011\":\""+boiler.getP10001011()+"\","
					+ "\"p10001012\":\""+boiler.getP10001012()+"\","
					+ "\"p10001013\":\""+boiler.getP10001013()+"\","
					+ "\"p10001015\":\""+boiler.getP10001015()+"\","
					+ "\"p10001016\":\""+boiler.getP10001016()+"\","
					+ "\"p10001017\":\""+boiler.getP10001017()+"\","
					+ "\"p10001018\":\""+boiler.getP10001018()+"\","
					+ "\"p10001019\":\""+boiler.getP10001019()+"\","
					+ "\"p10001020\":\""+boiler.getP10001020()+"\","
					+ "\"p10001021\":\""+boiler.getP10001021()+"\","
					+ "\"p10001022\":\""+boiler.getP10001022()+"\","
					+ "\"p10001023\":\""+boiler.getP10001023()+"\","
					+ "\"p10001024\":\""+boiler.getP10001024()+"\","
					+ "\"p10001025\":\""+boiler.getP10001025()+"\","
					+ "\"p10002005\":\""+boiler.getP10002005()+"\","
					+ "\"p10002006\":\""+boiler.getP10002006()+"\","
					+ "\"p10002007\":\""+boiler.getP10002007()+"\","
					+ "\"p10002008\":\""+boiler.getP10002008()+"\","
					+ "\"p10002009\":\""+boiler.getP10002009()+"\","
					+ "\"p10002010\":\""+boiler.getP10002010()+"\","
					+ "\"p10002011\":\""+boiler.getP10002011()+"\","
					+ "\"p10002012\":\""+boiler.getP10002012()+"\","
					+ "\"p10002013\":\""+boiler.getP10002013()+"\","
					+ "\"p10002014\":\""+boiler.getP10002014()+"\","
					+ "\"p10002015\":\""+boiler.getP10002015()+"\","
					+ "\"p10002016\":\""+boiler.getP10002016()+"\","
					+ "\"p10002017\":\""+boiler.getP10002017()+"\","
					+ "\"pSlmbzfs\":\""+boiler.getP_slmbzfs()+"\","
					+ "\"pZtcl\":\""+boiler.getP_ztcl()+"\","
					+ "\"cpBy1\":\""+boiler.getCp_by1()+"\"";
			
		}else if(doc.getDevice_sort_code().startsWith("2")){
			PressurevesselsPara pre = new PressurevesselsPara();
			//获取参数信息表
//			Collection<PressurevesselsPara> collection   =doc.getBoiler();
//			
//			Iterator iterator = collection .iterator();
//			while(iterator.hasNext()) {
//				pre=(PressurevesselsPara)iterator.next();
//			
//			}
//			temp="{\"workMpa\":\""+pre+"\","
//					
//					+ "\"workMpa\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"workMedium\":\""+boiler.getManufacturer()+"\","
//					+ "\"claimSettingMpa\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"checkType\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"nominalBore\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"seatCaliber\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"mpaLevel\":\""+boiler.getRated_output()+"\","
//					+ "\"designComName\":\""+boiler.getBoiler_model()+"\","
//					+ "\"designComCode\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"designComCert\":\""+boiler.getManufacturer()+"\","
//					+ "\"installComCert\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"installComCode\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"supervisionComName\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"supervisionComCode\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"supervisionComCert\":\""+boiler.getRated_output()+"\","
//					+ "\"p80001005\":\""+boiler.getBoiler_model()+"\","
//					+ "\"pGzclph\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"p80002010\":\""+boiler.getManufacturer()+"\","
//					+ "\"p80002011\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"p80003001\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"p80003002\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"p80002002\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"p80002003\":\""+boiler.getRated_output()+"\","
//					+ "\"p80002005\":\""+boiler.getBoiler_model()+"\","
//					+ "\"p80002006\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"p80001001\":\""+boiler.getManufacturer()+"\","
//					+ "\"pGdgg\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"pJrccl\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"pJrchd\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"pNccl\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"pNcbh\":\""+boiler.getRated_output()+"\","
//					+ "\"p80001002\":\""+boiler.getBoiler_model()+"\","
//					+ "\"p80002008\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"p80002009\":\""+boiler.getManufacturer()+"\","
//					+ "\"pGdbs\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"zdjkdj\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"btrq\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"bfrq\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"ccrq\":\""+boiler.getRated_output()+"\","
//					+ "\"zzmc\":\""+boiler.getBoiler_model()+"\","
//					+ "\"sbjb\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"pFfccl\":\""+boiler.getManufacturer()+"\","
//					+ "\"pFsyl\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"jcdw\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"ffdwMc\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"noticeDate\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"gcgm\":\""+boiler.getRated_output()+"\","
//					+ "\"dbsyncVersion\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"dbsyncUpdDate\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"dbsyncSrcid\":\""+boiler.getRated_output()+"\","
//			
		}else if(doc.getDevice_sort_code().startsWith("3")){
			ElevatorPara ele = new ElevatorPara();
			//获取参数信息表
			Collection<ElevatorPara> collection   =doc.getElevatorParas();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				 ele=(ElevatorPara)iterator.next();
			
			}
			
			temp="\"p30002005\":\""+ele.getP30002005()+"\",\"p30001010\":\""+ele.getP30001010()+"\",\"pSxydsd\":\""+ele.getP_sxydsd()+"\",\"p30001013\":\""+ele.getP30001013()+"\",\"p30001014\":\""+ele.getP30001014()+"\",\"pDqdzsd\":\""+ele.getP_dqdzsd()+"\",\"p30001011\":\""+ele.getP30001011()+"\",\"p30001012\":\""+ele.getP30001012()+"\",\"p30001018\":\""+ele.getP30001018()+"\",\"p30001017\":\""+ele.getP30001017()+"\",\"p30001016\":\""+ele.getP30001016()+"\",\"p30001015\":\""+ele.getP30001015()+"\",\"pAcqbh\":\""+ele.getP_acqbh()+"\",\"p30001019\":\""+ele.getP30001019()+"\",\"p30002003\":\""+ele.getP30002003()+"\",\"pHcqxh\":\""+ele.getP_hcqxh()+"\",\"p30002004\":\""+ele.getP30002004()+"\",\"p30002001\":\""+ele.getP30002001()+"\",\"p30002002\":\""+ele.getP30002002()+"\",\"pDcgd\":\""+ele.getP_dcgd()+"\",\"pYyb\":\""+ele.getP_yyb()+"\",\"pYyss\":\""+ele.getP_yyss()+"\",\"pYyryj\":\""+ele.getP_yyryj()+"\",\"pKmfx\":\""+ele.getP_kmfx()+"\",\"p30001020\":\""+ele.getP30001020()+"\",\"p30001021\":\""+ele.getP30001021()+"\",\"p30001022\":\""+ele.getP30001022()+"\",\"p30001023\":\""+ele.getP30001023()+"\",\"pDcksl\":\""+ele.getP_dcksl()+"\",\"pXxydsd\":\""+ele.getP_xxydsd()+"\",\"pXsqdzsd\":\""+ele.getP_xsqdzsd()+"\",\"pGdjtx\":\""+ele.getP_gdjtx()+"\",\"pYgsl\":\""+ele.getP_ygsl()+"\",\"pDy\":\""+ele.getP_dy()+"\",\"pSyqdcd\":\""+ele.getP_syqdcd()+"\",\"pDtzs\":\""+ele.getP_dtzs()+"\",\"pYyszj\":\""+ele.getP_yyszj()+"\",\"pJxg\":\""+ele.getP_jxg()+"\",\"pDtms\":\""+ele.getP_dtms()+"\",\"pJxk\":\""+ele.getP_jxk()+"\",\"pYxff\":\""+ele.getP_yxff()+"\",\"pAcqxh\":\""+ele.getP_acqxh()+"\",\"pBwsl\":\""+ele.getP_bwsl()+"\",\"pYybgl\":\""+ele.getP_yybgl()+"\",\"cpBy2\":\""+ele.getCp_by2()+"\",\"pHysd\":\""+ele.getP_hysd()+"\",\"pJxc\":\""+ele.getP_jxc()+"\",\"cpBy3\":\""+ele.getCp_by3()+"\",\"pYgxs\":\""+ele.getP_ygxs()+"\",\"cpBy1\":\""+ele.getCp_by1()+"\",\"pHcqbh\":\""+ele.getP_hcqbh()+"\",\"cpBy5\":\""+ele.getCp_by5()+"\",\"pYgly\":\""+ele.getP_ygly()+"\",\"cpBy4\":\""+ele.getCp_by4()+"\",\"pZcl\":\""+ele.getP_zcl()+"\",\"pDksd\":\""+ele.getP_dksd()+"\",\"pFjzdq\":\""+ele.getP_fjzdq()+"\",\"p30001001\":\""+ele.getP30001001()+"\",\"p30003008\":\""+ele.getP30003008()+"\",\"p30003007\":\""+ele.getP30003007()+"\",\"p30001003\":\""+ele.getP30001003()+"\",\"p30003006\":\""+ele.getP30003006()+"\",\"p30001002\":\""+ele.getP30001002()+"\",\"pDdjgl\":\""+ele.getP_ddjgl()+"\",\"pYybll\":\""+ele.getP_yybll()+"\",\"pEddl\":\""+ele.getP_eddl()+"\",\"pFtkd\":\""+ele.getP_ftkd()+"\",\"pZs\":\""+ele.getP_zs()+"\",\"pJjdzsd\":\""+ele.getP_jjdzsd()+"\",\"pSb\":\""+ele.getP_sb()+"\",\"p30001008\":\""+ele.getP30001008()+"\",\"p30003001\":\""+ele.getP30003001()+"\",\"p30001009\":\""+ele.getP30001009()+"\",\"p30003004\":\""+ele.getP30003004()+"\",\"p30001004\":\""+ele.getP30001004()+"\",\"p30003005\":\""+ele.getP30003005()+"\",\"p30001005\":\""+ele.getP30001005()+"\",\"p30003002\":\""+ele.getP30003002()+"\",\"p30001006\":\""+ele.getP30001006()+"\",\"p30003003\":\""+ele.getP30003003()+"\",\"p30001007\":\""+ele.getP30001007()+"\"";
			
		}else if(doc.getDevice_sort_code().startsWith("4")){
			CranePara cran = new CranePara();
			//获取参数信息表
			Collection<CranePara> collection   =doc.getCrane();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				cran=(CranePara)iterator.next();
			
			}
			temp="\"p40001001\":\""+cran.getP40001001()+"\","
					+ "\"p40001002\":\""+cran.getP40001002()+"\","
					+ "\"p40001003\":\""+cran.getP40001003()+"\","
					+ "\"p40001004\":\""+cran.getP40001004()+"\","
					+ "\"p40001005\":\""+cran.getP40001005()+"\","
					+ "\"p40001006\":\""+cran.getP40001006()+"\","
					+ "\"p40001007\":\""+cran.getP40001007()+"\","
					+ "\"p40001008\":\""+cran.getP40001008()+"\","
					+ "\"p40001009\":\""+cran.getP40001009()+"\","
					+ "\"p40001010\":\""+cran.getP40001010()+"\","
					+ "\"p40001011\":\""+cran.getP40001011()+"\","
					+ "\"p40001012\":\""+cran.getP40001012()+"\","
					+ "\"p40001013\":\""+cran.getP40001013()+"\","
					+ "\"p40001014\":\""+cran.getP40001014()+"\","
					+ "\"p40001015\":\""+cran.getP40001015()+"\","
					+ "\"p40001016\":\""+cran.getP40001016()+"\","
					+ "\"p40001017\":\""+cran.getP40001017()+"\","
					+ "\"p40001018\":\""+cran.getP40001018()+"\","
					+ "\"p40001019\":\""+cran.getP40001019()+"\","
					+ "\"p40001020\":\""+cran.getP40001020()+"\","
					+ "\"p40002001\":\""+cran.getP40002001()+"\","
					+ "\"p40002002\":\""+cran.getP40002002()+"\","
					+ "\"p40002003\":\""+cran.getP40002003()+"\","
					+ "\"p40002003Fg\":\""+cran.getP40002003_fg()+"\","
					+ "\"p40002004\":\""+cran.getP40002004()+"\","
					+ "\"p40002004Fg\":\""+cran.getP40002004_fg()+"\","
					+ "\"p40002005\":\""+cran.getP40002005()+"\","
					+ "\"p40002006\":\""+cran.getP40002006()+"\","
					+ "\"p40002006Fg\":\""+cran.getP40002006_fg()+"\","
					+ "\"p40002007\":\""+cran.getP40002007()+"\","
					+ "\"pCz\":\""+cran.getP_cz()+"\","
					+ "\"pQdxs\":\""+cran.getP_qdxs()+"\","
					+ "\"pBfsd\":\""+cran.getP_bfsd()+"\","
					+ "\"pHzsd\":\""+cran.getP_hzsd()+"\","
					+ "\"pXssd\":\""+cran.getP_xssd()+"\","
					+ "\"pKd\":\""+cran.getP_kd()+"\","
					+ "\"pDrgzxc\":\""+cran.getP_drgzxc()+"\","
					+ "\"pTsszj\":\""+cran.getP_tsszj()+"\","
					+ "\"pZydgd\":\""+cran.getP_zydgd()+"\","
					+ "\"pAcqxh\":\""+cran.getP_acqxh()+"\","
					+ "\"pAcqbh\":\""+cran.getP_acqbh()+"\","
					+ "\"pAcqxzsd\":\""+cran.getP_acqxzsd()+"\","
					+ "\"pYxsdDc\":\""+cran.getP_yxsd_dc()+"\","
					+ "\"pYxsdXc\":\""+cran.getP_yxsd_xc()+"\","
					+ "\"pGdcd\":\""+cran.getP_gdcd()+"\"";
			
			
		}else if(doc.getDevice_sort_code().startsWith("5")){
			
			EnginePara eng = new EnginePara();
			//获取参数信息表
			Collection<EnginePara> collection   =doc.getEngine();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				eng=(EnginePara)iterator.next();
			
			}
			temp="\"pPzhm\":\""+eng.getP_pzhm()+"\","
					+ "\"pFdjxh\":\""+eng.getP_fdjxh()+"\","
					+ "\"pDjccbh\":\""+eng.getP_djccbh()+"\","
					+ "\"pEdzzl\":\""+eng.getP_edzzl()+"\","
					+ "\"pYxsd\":\""+eng.getP_yxsd()+"\","
					+ "\"pDlfs\":\""+eng.getP_dlfs()+"\","
					+ "\"pJssdy\":\""+eng.getP_jssdy()+"\","
					+ "\"pRylx\":\""+eng.getP_rylx()+"\","
					+ "\"pCpxh\":\""+eng.getP_cpxh()+"\","
					+ "\"pCllx\":\""+eng.getP_cllx()+"\","
					+ "\"cpBy1\":\""+eng.getCp_by1()+"\","
					+ "\"cpBy2\":\""+eng.getCp_by2()+"\"";
		}else if(doc.getDevice_sort_code().startsWith("6")){
			RidesPara rid = new RidesPara();
			//获取参数信息表
			Collection<RidesPara> collection   =doc.getRidesPara();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				rid=(RidesPara)iterator.next();
			
			}
			temp="\"p60001001\":\""+rid.getP60001001()+"\","
					+ "\"p60002001\":\""+rid.getP60002001()+"\","
					+ "\"p60002002\":\""+rid.getP60002002()+"\","
					+ "\"p60002003\":\""+rid.getP60002003()+"\","
					+ "\"p60002004\":\""+rid.getP60002004()+"\","
					+ "\"p60002005\":\""+rid.getP60002005()+"\","
					+ "\"p60002006\":\""+rid.getP60002006()+"\","
					+ "\"p60002007\":\""+rid.getP60002007()+"\","
					+ "\"p60002008\":\""+rid.getP60002008()+"\","
					+ "\"p60002009\":\""+rid.getP60002009()+"\","
					+ "\"p60002010\":\""+rid.getP60002010()+"\","
					+ "\"p60002011\":\""+rid.getP60002011()+"\","
					+ "\"p60002012\":\""+rid.getP60002012()+"\","
					+ "\"p60002013\":\""+rid.getP60002013()+"\","
					+ "\"p60002014\":\""+rid.getP60002014()+"\","
					+ "\"p60002015\":\""+rid.getP60002015()+"\","
					+ "\"p60002016\":\""+rid.getP60002016()+"\","
					+ "\"pEdssxsd\":\""+rid.getP_edssxsd()+"\","
					+ "\"pClzs\":\""+rid.getP_clzs()+"\","
					+ "\"pCcmj\":\""+rid.getP_ccmj()+"\"";
		}
//		else if(doc.getDevice_sort_code().startsWith("7")){
//			
//		}else if(doc.getDevice_sort_code().startsWith("8")){
//			
//		}
		
//		ElevatorPara ele = new ElevatorPara();
//		//获取参数信息表
//		Collection<ElevatorPara> collection   =doc.getElevatorParas();
//		
//		Iterator iterator = collection .iterator();
//		while(iterator.hasNext()) {
//			 ele=(ElevatorPara)iterator.next();
//		
//		}
		
		//获取企业组织机构代码
		EnterInfo  enter = enterDao.get(doc.getFk_company_info_use_id());
		String orgCode = enter.getCom_code();
		//处理检验日期和下次检验日期
		
		String inspectDate=doc.getInspect_date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(doc.getInspect_date()):"";
		String inspectNextDate=doc.getInspect_next_date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(doc.getInspect_next_date()):"";
		
		
		
		
		String testStr = "{\"deviceSortCode\":\""+doc.getDevice_sort_code()+"\",\"BASE\":{\"companyAddress\":\""+enter.getCom_address()+"\",\"rqCode\":\""+doc.getDevice_qr_code()+"\",\"companyCode\":\""+enter.getCom_code()+"\",\"companyName\":\""+enter.getCom_name()+"\",\"securityOp\":\""+doc.getSecurity_op()+"\",\"securityTel\":\""+doc.getSecurity_tel()+"\",\"securityManagement\":\""+doc.getSecurity_management()+"\",\"deviceName\":\""+doc.getDevice_name()+"\",\"constructionUnitsName\":\""+doc.getConstruction_units_name()+"\",\"inspectDate\":\""+inspectDate+"\",\"inspectNextDate\":\""+inspectNextDate+"\",\"inspectConclusion\":\""+doc.getInspect_conclusion()+"\",\"makeUnitsName\":\""+doc.getMake_units_name()+"\",\"maintenanceMan\":\""+doc.getMaintenance_man()+"\",\"maintenanceTel\":\""+doc.getMaintenance_tel()+"\",\"deviceAreaCode\":\""+doc.getDevice_area_code()+"\",\"useDate\":\""+doc.getUse_date()+"\",\"makeDate\":\""+doc.getMake_date()+"\",\"deviceUsePlace\":\""+doc.getDevice_use_place()+"\",\"deviceStreetCode\":\""+doc.getDevice_street_code()+"\",\"deviceModel\":\""+doc.getDevice_model()+"\",\"internalNum\":\""+doc.getInternal_num()+"\"},\"PARAM\":{"+temp+"}}";
		
		
		
//		//获取企业组织机构代码
//		EnterInfo  enter = enterDao.get(doc.getFk_company_info_use_id());
//		String orgCode = enter.getCom_code();
//		
//		String inspectDate=doc.getInspect_date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(doc.getInspect_date()):"";
//		String inspectNextDate=doc.getInspect_next_date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(doc.getInspect_next_date()):"";
//		
		
		
//		String testStr = "{\"deviceSortCode\":\""+doc.getDevice_sort_code()+"\",\"BASE\":{\"companyAddress\":\""+enter.getCom_address()+"\",\"rqCode\":\""+doc.getDevice_qr_code()+"\",\"companyCode\":\""+enter.getCom_code()+"\",\"companyName\":\""+enter.getCom_name()+"\",\"securityOp\":\""+doc.getSecurity_op()+"\",\"securityTel\":\""+doc.getSecurity_tel()+"\",\"securityManagement\":\""+doc.getSecurity_management()+"\",\"deviceName\":\""+doc.getDevice_name()+"\",\"constructionUnitsName\":\""+doc.getConstruction_units_name()+"\",\"inspectDate\":\""+inspectDate+"\",\"inspectNextDate\":\""+inspectNextDate+"\",\"inspectConclusion\":\""+doc.getInspect_conclusion()+"\",\"makeUnitsName\":\""+doc.getMake_units_name()+"\",\"maintenanceMan\":\""+doc.getMaintenance_man()+"\",\"maintenanceTel\":\""+doc.getMaintenance_tel()+"\",\"deviceAreaCode\":\""+doc.getDevice_area_code()+"\",\"useDate\":\""+doc.getUse_date()+"\",\"makeDate\":\""+doc.getMake_date()+"\",\"deviceUsePlace\":\""+doc.getDevice_use_place()+"\",\"deviceStreetCode\":\""+doc.getDevice_street_code()+"\",\"deviceModel\":\""+doc.getDevice_model()+"\",\"internalNum\":\""+doc.getInternal_num()+"\"},\"PARAM\":"+temp+"}";
		
		
		
		log.info("@@@@@@@@@@@@@@"+testStr);
		
	
			//調用市局接口上傳數據
			String returnFlag = getInterface().insertDeviceInfo("zdfdf342", "adfghgd43", testStr.trim());
			
			log.info("@@@@@@@@@@@@@@"+returnFlag);
			
			//獲取返回的UUID
			String returnUUID= openJson(returnFlag);
			
			if(!returnUUID.equals("")){
				
				//记录日志
				enterDao.createSQLQuery("insert into UP_DEVICE_INFO (DEVICE_REGCODE,QRCODE,COMPANYADDRESS,COMPANYCODE,COMPANYNAME,SECURITYOP,SECURITYTEL,SECURITYMANAGEMENT,DEVICENAME,CONSTRUCTIONUNITSNAME,INSPECTDATE,INSPECTNEXTDATE,INSPECTCONCLUSION,MAKEUNITSNAME,MAINTENANCEMAN,MAINTENANCETEL,DEVICEAREACODE,USEDATE,MAKEDATE,DEVICEUSEPLACE,DEVICESTREETCODE,DEVICEMODEL,INTERNALNUM,UP_STATUS,UP_DATE,UP_TYPE)  "
						+ " VALUES('" + doc.getDevice_registration_code() +"','" + doc.getDevice_qr_code() +"','" + enter.getCom_address() +"','" + enter.getCom_code() +"','" + enter.getCom_name() +"','" + doc.getSecurity_op() +"','" + doc.getSecurity_tel() +"','" + doc.getSecurity_management() +"','" + doc.getDevice_name() +"','" + doc.getConstruction_units_name() +"','" +inspectDate+"','" +inspectNextDate+"','" +doc.getInspect_conclusion()+"','" +doc.getMake_units_name()+"','" +doc.getMaintenance_man()+"','" +doc.getMaintenance_tel()+"','" +doc.getDevice_area_code()+"','" +doc.getUse_date()+"','" +doc.getMake_date()+"','" +doc.getDevice_use_place()+"','" +doc.getDevice_street_code()+"','" +doc.getDevice_model()+"','" +doc.getInternal_num()+"','上传成功',sysdate,'插入设备数据')").executeUpdate();
				
			}else{
				//记录日志
				enterDao.createSQLQuery("insert into UP_DEVICE_INFO (DEVICE_REGCODE,QRCODE,COMPANYADDRESS,COMPANYCODE,COMPANYNAME,SECURITYOP,SECURITYTEL,SECURITYMANAGEMENT,DEVICENAME,CONSTRUCTIONUNITSNAME,INSPECTDATE,INSPECTNEXTDATE,INSPECTCONCLUSION,MAKEUNITSNAME,MAINTENANCEMAN,MAINTENANCETEL,DEVICEAREACODE,USEDATE,MAKEDATE,DEVICEUSEPLACE,DEVICESTREETCODE,DEVICEMODEL,INTERNALNUM,UP_STATUS,UP_DATE,UP_TYPE)  "
						+ " VALUES('" + doc.getDevice_registration_code() +"','" + doc.getDevice_qr_code() +"','" + enter.getCom_address() +"','" + enter.getCom_code() +"','" + enter.getCom_name() +"','" + doc.getSecurity_op() +"','" + doc.getSecurity_tel() +"','" + doc.getSecurity_management() +"','" + doc.getDevice_name() +"','" + doc.getConstruction_units_name() +"','" +inspectDate+"','" +inspectNextDate+"','" +doc.getInspect_conclusion()+"','" +doc.getMake_units_name()+"','" +doc.getMaintenance_man()+"','" +doc.getMaintenance_tel()+"','" +doc.getDevice_area_code()+"','" +doc.getUse_date()+"','" +doc.getMake_date()+"','" +doc.getDevice_use_place()+"','" +doc.getDevice_street_code()+"','" +doc.getDevice_model()+"','" +doc.getInternal_num()+"','上传失败',sysdate,'插入设备数据')").executeUpdate();
				
			}
		
		
			return returnUUID;
		
	}
	

	//更新市局电梯数据
	public  void updateInfo(DeviceDocument doc,String uuid) throws Exception {
		
//		ElevatorPara ele = new ElevatorPara();
//		//获取参数信息表
//		Collection<ElevatorPara> collection =doc.getElevatorParas();
//		
//		Iterator iterator = collection.iterator();
//		while(iterator.hasNext()) {
//			 ele=(ElevatorPara)iterator.next();
//			 continue;
//		}
		
		String temp="";
		//判断设备类型
		if(doc.getDevice_sort_code().startsWith("1")){
			BoilerPara boiler = new BoilerPara();
			//获取参数信息表
			Collection<BoilerPara> collection   =doc.getBoiler();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				boiler=(BoilerPara)iterator.next();
			
			}
			temp="\"registrationNum\":\""+boiler.getRegistration_num()+"\",\"boilerModel\":\""+boiler.getBoiler_model()+"\",\"boilerRoomType\":\""+boiler.getBoiler_room_type()+"\",\"manufacturer\":\""+boiler.getManufacturer()+"\",\"fkInspectionUnitId\":\""+boiler.getFk_inspection_unit_id()+"\","
					+ "\"boilerStructure\":\""+boiler.getBoiler_structure()+"\","
					+ "\"designPressure\":\""+boiler.getDesign_pressure()+"\","
					+ "\"permitPressure\":\""+boiler.getPermit_pressure()+"\","
					+ "\"ratedOutput\":\""+boiler.getRated_output()+"\","
					+ "\"outletTemperature\":\""+boiler.getOutlet_temperature()+"\","
					+ "\"heatingMethod\":\""+boiler.getHeating_method()+"\","
					+ "\"fuelType\":\""+boiler.getFuel_type()+"\","
					+ "\"boilerUse\":\""+boiler.getBoiler_use()+"\","
					+ "\"useState\":\""+boiler.getUse_state()+"\","
					+ "\"combustionType\":\""+boiler.getCombustion_type()+"\","
					+ "\"waterTreatment\":\""+boiler.getWater_treatment()+"\","
					+ "\"deoxidization\":\""+boiler.getDeoxidization()+"\","
					+ "\"deslagging\":\""+boiler.getDeslagging()+"\","
					+ "\"smokeDust\":\""+boiler.getSmoke_dust()+"\","
					+ "\"stokerNum\":\""+boiler.getStoker_num()+"\","
					+ "\"waterQualityNum\":\""+boiler.getWater_quality_num()+"\","
					+ "\"p10001011\":\""+boiler.getP10001011()+"\","
					+ "\"p10001012\":\""+boiler.getP10001012()+"\","
					+ "\"p10001013\":\""+boiler.getP10001013()+"\","
					+ "\"p10001015\":\""+boiler.getP10001015()+"\","
					+ "\"p10001016\":\""+boiler.getP10001016()+"\","
					+ "\"p10001017\":\""+boiler.getP10001017()+"\","
					+ "\"p10001018\":\""+boiler.getP10001018()+"\","
					+ "\"p10001019\":\""+boiler.getP10001019()+"\","
					+ "\"p10001020\":\""+boiler.getP10001020()+"\","
					+ "\"p10001021\":\""+boiler.getP10001021()+"\","
					+ "\"p10001022\":\""+boiler.getP10001022()+"\","
					+ "\"p10001023\":\""+boiler.getP10001023()+"\","
					+ "\"p10001024\":\""+boiler.getP10001024()+"\","
					+ "\"p10001025\":\""+boiler.getP10001025()+"\","
					+ "\"p10002005\":\""+boiler.getP10002005()+"\","
					+ "\"p10002006\":\""+boiler.getP10002006()+"\","
					+ "\"p10002007\":\""+boiler.getP10002007()+"\","
					+ "\"p10002008\":\""+boiler.getP10002008()+"\","
					+ "\"p10002009\":\""+boiler.getP10002009()+"\","
					+ "\"p10002010\":\""+boiler.getP10002010()+"\","
					+ "\"p10002011\":\""+boiler.getP10002011()+"\","
					+ "\"p10002012\":\""+boiler.getP10002012()+"\","
					+ "\"p10002013\":\""+boiler.getP10002013()+"\","
					+ "\"p10002014\":\""+boiler.getP10002014()+"\","
					+ "\"p10002015\":\""+boiler.getP10002015()+"\","
					+ "\"p10002016\":\""+boiler.getP10002016()+"\","
					+ "\"p10002017\":\""+boiler.getP10002017()+"\","
					+ "\"pSlmbzfs\":\""+boiler.getP_slmbzfs()+"\","
					+ "\"pZtcl\":\""+boiler.getP_ztcl()+"\","
					+ "\"cpBy1\":\""+boiler.getCp_by1()+"\"";
			
		}
//		else if(doc.getDevice_sort_code().startsWith("2")){
//			PressurevesselsPara pre = new PressurevesselsPara();
//			//获取参数信息表
//			Collection<PressurevesselsPara> collection   =doc.getPressurevessels();
//			
//			Iterator iterator = collection .iterator();
//			while(iterator.hasNext()) {
//				pre=(PressurevesselsPara)iterator.next();
//			
//			}
//			temp="{\"workMpa\":\""+pre+"\","
//					
//					+ "\"workMpa\":\""+pre.getw+"\","
//					+ "\"workMedium\":\""+boiler.getManufacturer()+"\","
//					+ "\"claimSettingMpa\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"checkType\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"nominalBore\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"seatCaliber\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"mpaLevel\":\""+boiler.getRated_output()+"\","
//					+ "\"designComName\":\""+boiler.getBoiler_model()+"\","
//					+ "\"designComCode\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"designComCert\":\""+boiler.getManufacturer()+"\","
//					+ "\"installComCert\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"installComCode\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"supervisionComName\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"supervisionComCode\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"supervisionComCert\":\""+boiler.getRated_output()+"\","
//					+ "\"p80001005\":\""+boiler.getBoiler_model()+"\","
//					+ "\"pGzclph\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"p80002010\":\""+boiler.getManufacturer()+"\","
//					+ "\"p80002011\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"p80003001\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"p80003002\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"p80002002\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"p80002003\":\""+boiler.getRated_output()+"\","
//					+ "\"p80002005\":\""+boiler.getBoiler_model()+"\","
//					+ "\"p80002006\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"p80001001\":\""+boiler.getManufacturer()+"\","
//					+ "\"pGdgg\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"pJrccl\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"pJrchd\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"pNccl\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"pNcbh\":\""+boiler.getRated_output()+"\","
//					+ "\"p80001002\":\""+boiler.getBoiler_model()+"\","
//					+ "\"p80002008\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"p80002009\":\""+boiler.getManufacturer()+"\","
//					+ "\"pGdbs\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"zdjkdj\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"btrq\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"bfrq\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"ccrq\":\""+boiler.getRated_output()+"\","
//					+ "\"zzmc\":\""+boiler.getBoiler_model()+"\","
//					+ "\"sbjb\":\""+boiler.getBoiler_room_type()+"\","
//					+ "\"pFfccl\":\""+boiler.getManufacturer()+"\","
//					+ "\"pFsyl\":\""+boiler.getFk_inspection_unit_id()+"\","
//					+ "\"jcdw\":\""+boiler.getBoiler_structure()+"\","
//					+ "\"ffdwMc\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"noticeDate\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"gcgm\":\""+boiler.getRated_output()+"\","
//					+ "\"dbsyncVersion\":\""+boiler.getDesign_pressure()+"\","
//					+ "\"dbsyncUpdDate\":\""+boiler.getPermit_pressure()+"\","
//					+ "\"dbsyncSrcid\":\""+boiler.getRated_output()+"\","
//			
//		}
		else if(doc.getDevice_sort_code().startsWith("3")){
			ElevatorPara ele = new ElevatorPara();
			//获取参数信息表
			Collection<ElevatorPara> collection   =doc.getElevatorParas();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				 ele=(ElevatorPara)iterator.next();
			
			}
			
			temp="\"p30002005\":\""+ele.getP30002005()+"\",\"p30001010\":\""+ele.getP30001010()+"\",\"pSxydsd\":\""+ele.getP_sxydsd()+"\",\"p30001013\":\""+ele.getP30001013()+"\",\"p30001014\":\""+ele.getP30001014()+"\",\"pDqdzsd\":\""+ele.getP_dqdzsd()+"\",\"p30001011\":\""+ele.getP30001011()+"\",\"p30001012\":\""+ele.getP30001012()+"\",\"p30001018\":\""+ele.getP30001018()+"\",\"p30001017\":\""+ele.getP30001017()+"\",\"p30001016\":\""+ele.getP30001016()+"\",\"p30001015\":\""+ele.getP30001015()+"\",\"pAcqbh\":\""+ele.getP_acqbh()+"\",\"p30001019\":\""+ele.getP30001019()+"\",\"p30002003\":\""+ele.getP30002003()+"\",\"pHcqxh\":\""+ele.getP_hcqxh()+"\",\"p30002004\":\""+ele.getP30002004()+"\",\"p30002001\":\""+ele.getP30002001()+"\",\"p30002002\":\""+ele.getP30002002()+"\",\"pDcgd\":\""+ele.getP_dcgd()+"\",\"pYyb\":\""+ele.getP_yyb()+"\",\"pYyss\":\""+ele.getP_yyss()+"\",\"pYyryj\":\""+ele.getP_yyryj()+"\",\"pKmfx\":\""+ele.getP_kmfx()+"\",\"p30001020\":\""+ele.getP30001020()+"\",\"p30001021\":\""+ele.getP30001021()+"\",\"p30001022\":\""+ele.getP30001022()+"\",\"p30001023\":\""+ele.getP30001023()+"\",\"pDcksl\":\""+ele.getP_dcksl()+"\",\"pXxydsd\":\""+ele.getP_xxydsd()+"\",\"pXsqdzsd\":\""+ele.getP_xsqdzsd()+"\",\"pGdjtx\":\""+ele.getP_gdjtx()+"\",\"pYgsl\":\""+ele.getP_ygsl()+"\",\"pDy\":\""+ele.getP_dy()+"\",\"pSyqdcd\":\""+ele.getP_syqdcd()+"\",\"pDtzs\":\""+ele.getP_dtzs()+"\",\"pYyszj\":\""+ele.getP_yyszj()+"\",\"pJxg\":\""+ele.getP_jxg()+"\",\"pDtms\":\""+ele.getP_dtms()+"\",\"pJxk\":\""+ele.getP_jxk()+"\",\"pYxff\":\""+ele.getP_yxff()+"\",\"pAcqxh\":\""+ele.getP_acqxh()+"\",\"pBwsl\":\""+ele.getP_bwsl()+"\",\"pYybgl\":\""+ele.getP_yybgl()+"\",\"cpBy2\":\""+ele.getCp_by2()+"\",\"pHysd\":\""+ele.getP_hysd()+"\",\"pJxc\":\""+ele.getP_jxc()+"\",\"cpBy3\":\""+ele.getCp_by3()+"\",\"pYgxs\":\""+ele.getP_ygxs()+"\",\"cpBy1\":\""+ele.getCp_by1()+"\",\"pHcqbh\":\""+ele.getP_hcqbh()+"\",\"cpBy5\":\""+ele.getCp_by5()+"\",\"pYgly\":\""+ele.getP_ygly()+"\",\"cpBy4\":\""+ele.getCp_by4()+"\",\"pZcl\":\""+ele.getP_zcl()+"\",\"pDksd\":\""+ele.getP_dksd()+"\",\"pFjzdq\":\""+ele.getP_fjzdq()+"\",\"p30001001\":\""+ele.getP30001001()+"\",\"p30003008\":\""+ele.getP30003008()+"\",\"p30003007\":\""+ele.getP30003007()+"\",\"p30001003\":\""+ele.getP30001003()+"\",\"p30003006\":\""+ele.getP30003006()+"\",\"p30001002\":\""+ele.getP30001002()+"\",\"pDdjgl\":\""+ele.getP_ddjgl()+"\",\"pYybll\":\""+ele.getP_yybll()+"\",\"pEddl\":\""+ele.getP_eddl()+"\",\"pFtkd\":\""+ele.getP_ftkd()+"\",\"pZs\":\""+ele.getP_zs()+"\",\"pJjdzsd\":\""+ele.getP_jjdzsd()+"\",\"pSb\":\""+ele.getP_sb()+"\",\"p30001008\":\""+ele.getP30001008()+"\",\"p30003001\":\""+ele.getP30003001()+"\",\"p30001009\":\""+ele.getP30001009()+"\",\"p30003004\":\""+ele.getP30003004()+"\",\"p30001004\":\""+ele.getP30001004()+"\",\"p30003005\":\""+ele.getP30003005()+"\",\"p30001005\":\""+ele.getP30001005()+"\",\"p30003002\":\""+ele.getP30003002()+"\",\"p30001006\":\""+ele.getP30001006()+"\",\"p30003003\":\""+ele.getP30003003()+"\",\"p30001007\":\""+ele.getP30001007()+"\"";
			
		}else if(doc.getDevice_sort_code().startsWith("4")){
			CranePara cran = new CranePara();
			//获取参数信息表
			Collection<CranePara> collection   =doc.getCrane();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				cran=(CranePara)iterator.next();
			
			}
			temp="\"p40001001\":\""+cran.getP40001001()+"\","
					+ "\"p40001002\":\""+cran.getP40001002()+"\","
					+ "\"p40001003\":\""+cran.getP40001003()+"\","
					+ "\"p40001004\":\""+cran.getP40001004()+"\","
					+ "\"p40001005\":\""+cran.getP40001005()+"\","
					+ "\"p40001006\":\""+cran.getP40001006()+"\","
					+ "\"p40001007\":\""+cran.getP40001007()+"\","
					+ "\"p40001008\":\""+cran.getP40001008()+"\","
					+ "\"p40001009\":\""+cran.getP40001009()+"\","
					+ "\"p40001010\":\""+cran.getP40001010()+"\","
					+ "\"p40001011\":\""+cran.getP40001011()+"\","
					+ "\"p40001012\":\""+cran.getP40001012()+"\","
					+ "\"p40001013\":\""+cran.getP40001013()+"\","
					+ "\"p40001014\":\""+cran.getP40001014()+"\","
					+ "\"p40001015\":\""+cran.getP40001015()+"\","
					+ "\"p40001016\":\""+cran.getP40001016()+"\","
					+ "\"p40001017\":\""+cran.getP40001017()+"\","
					+ "\"p40001018\":\""+cran.getP40001018()+"\","
					+ "\"p40001019\":\""+cran.getP40001019()+"\","
					+ "\"p40001020\":\""+cran.getP40001020()+"\","
					+ "\"p40002001\":\""+cran.getP40002001()+"\","
					+ "\"p40002002\":\""+cran.getP40002002()+"\","
					+ "\"p40002003\":\""+cran.getP40002003()+"\","
					+ "\"p40002003Fg\":\""+cran.getP40002003_fg()+"\","
					+ "\"p40002004\":\""+cran.getP40002004()+"\","
					+ "\"p40002004Fg\":\""+cran.getP40002004_fg()+"\","
					+ "\"p40002005\":\""+cran.getP40002005()+"\","
					+ "\"p40002006\":\""+cran.getP40002006()+"\","
					+ "\"p40002006Fg\":\""+cran.getP40002006_fg()+"\","
					+ "\"p40002007\":\""+cran.getP40002007()+"\","
					+ "\"pCz\":\""+cran.getP_cz()+"\","
					+ "\"pQdxs\":\""+cran.getP_qdxs()+"\","
					+ "\"pBfsd\":\""+cran.getP_bfsd()+"\","
					+ "\"pHzsd\":\""+cran.getP_hzsd()+"\","
					+ "\"pXssd\":\""+cran.getP_xssd()+"\","
					+ "\"pKd\":\""+cran.getP_kd()+"\","
					+ "\"pDrgzxc\":\""+cran.getP_drgzxc()+"\","
					+ "\"pTsszj\":\""+cran.getP_tsszj()+"\","
					+ "\"pZydgd\":\""+cran.getP_zydgd()+"\","
					+ "\"pAcqxh\":\""+cran.getP_acqxh()+"\","
					+ "\"pAcqbh\":\""+cran.getP_acqbh()+"\","
					+ "\"pAcqxzsd\":\""+cran.getP_acqxzsd()+"\","
					+ "\"pYxsdDc\":\""+cran.getP_yxsd_dc()+"\","
					+ "\"pYxsdXc\":\""+cran.getP_yxsd_xc()+"\","
					+ "\"pGdcd\":\""+cran.getP_gdcd()+"\"";
			
			
			
			
		}else if(doc.getDevice_sort_code().startsWith("5")){
			
			EnginePara eng = new EnginePara();
			//获取参数信息表
			Collection<EnginePara> collection   =doc.getEngine();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				eng=(EnginePara)iterator.next();
			
			}
			temp="\"pPzhm\":\""+eng.getP_pzhm()+"\","
					+ "\"pFdjxh\":\""+eng.getP_fdjxh()+"\","
					+ "\"pDjccbh\":\""+eng.getP_djccbh()+"\","
					+ "\"pEdzzl\":\""+eng.getP_edzzl()+"\","
					+ "\"pYxsd\":\""+eng.getP_yxsd()+"\","
					+ "\"pDlfs\":\""+eng.getP_dlfs()+"\","
					+ "\"pJssdy\":\""+eng.getP_jssdy()+"\","
					+ "\"pRylx\":\""+eng.getP_rylx()+"\","
					+ "\"pCpxh\":\""+eng.getP_cpxh()+"\","
					+ "\"pCllx\":\""+eng.getP_cllx()+"\","
					+ "\"cpBy1\":\""+eng.getCp_by1()+"\","
					+ "\"cpBy2\":\""+eng.getCp_by2()+"\"";
		}else if(doc.getDevice_sort_code().startsWith("6")){
			RidesPara rid = new RidesPara();
			//获取参数信息表
			Collection<RidesPara> collection   =doc.getRidesPara();
			
			Iterator iterator = collection .iterator();
			while(iterator.hasNext()) {
				rid=(RidesPara)iterator.next();
			
			}
			temp="\"p60001001\":\""+rid.getP60001001()+"\","
					+ "\"p60002001\":\""+rid.getP60002001()+"\","
					+ "\"p60002002\":\""+rid.getP60002002()+"\","
					+ "\"p60002003\":\""+rid.getP60002003()+"\","
					+ "\"p60002004\":\""+rid.getP60002004()+"\","
					+ "\"p60002005\":\""+rid.getP60002005()+"\","
					+ "\"p60002006\":\""+rid.getP60002006()+"\","
					+ "\"p60002007\":\""+rid.getP60002007()+"\","
					+ "\"p60002008\":\""+rid.getP60002008()+"\","
					+ "\"p60002009\":\""+rid.getP60002009()+"\","
					+ "\"p60002010\":\""+rid.getP60002010()+"\","
					+ "\"p60002011\":\""+rid.getP60002011()+"\","
					+ "\"p60002012\":\""+rid.getP60002012()+"\","
					+ "\"p60002013\":\""+rid.getP60002013()+"\","
					+ "\"p60002014\":\""+rid.getP60002014()+"\","
					+ "\"p60002015\":\""+rid.getP60002015()+"\","
					+ "\"p60002016\":\""+rid.getP60002016()+"\","
					+ "\"pEdssxsd\":\""+rid.getP_edssxsd()+"\","
					+ "\"pClzs\":\""+rid.getP_clzs()+"\","
					+ "\"pCcmj\":\""+rid.getP_ccmj()+"\"";
		}
//		else if(doc.getDevice_sort_code().startsWith("7")){
//			
//		}else if(doc.getDevice_sort_code().startsWith("8")){
//			
//		}
		
//		ElevatorPara ele = new ElevatorPara();
//		//获取参数信息表
//		Collection<ElevatorPara> collection   =doc.getElevatorParas();
//		
//		Iterator iterator = collection .iterator();
//		while(iterator.hasNext()) {
//			 ele=(ElevatorPara)iterator.next();
//		
//		}
		
		//获取企业组织机构代码
		EnterInfo  enter = enterDao.get(doc.getFk_company_info_use_id());
		String orgCode = enter.getCom_code();
		//处理检验日期和下次检验日期
		
		String inspectDate=doc.getInspect_date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(doc.getInspect_date()):"";
		String inspectNextDate=doc.getInspect_next_date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(doc.getInspect_next_date()):"";
		
		
		
		
		String testStr = "{\"UUID\":\""+uuid+"\",\"BASE\":{\"companyAddress\":\""+enter.getCom_address()+"\",\"rqCode\":\""+doc.getDevice_qr_code()+"\",\"companyCode\":\""+enter.getCom_code()+"\",\"companyName\":\""+enter.getCom_name()+"\",\"securityOp\":\""+doc.getSecurity_op()+"\",\"securityTel\":\""+doc.getSecurity_tel()+"\",\"securityManagement\":\""+doc.getSecurity_management()+"\",\"deviceName\":\""+doc.getDevice_name()+"\",\"constructionUnitsName\":\""+doc.getConstruction_units_name()+"\",\"inspectDate\":\""+inspectDate+"\",\"inspectNextDate\":\""+inspectNextDate+"\",\"inspectConclusion\":\""+doc.getInspect_conclusion()+"\",\"makeUnitsName\":\""+doc.getMake_units_name()+"\",\"maintenanceMan\":\""+doc.getMaintenance_man()+"\",\"maintenanceTel\":\""+doc.getMaintenance_tel()+"\",\"deviceAreaCode\":\""+doc.getDevice_area_code()+"\",\"useDate\":\""+doc.getUse_date()+"\",\"makeDate\":\""+doc.getMake_date()+"\",\"deviceUsePlace\":\""+doc.getDevice_use_place()+"\",\"deviceStreetCode\":\""+doc.getDevice_street_code()+"\",\"deviceModel\":\""+doc.getDevice_model()+"\",\"internalNum\":\""+doc.getInternal_num()+"\"},\"PARAM\":{"+temp+"}}";
		
	
		

		
		log.info("@@@@@@@@@@@@@@"+testStr);
		//調用市局接口上傳數據
		String returnFlag = getInterface().updatDeviceInfo("zdfdf342", "adfghgd43", testStr.trim());
		
		log.info("@@@@@@@@@@@@@@"+returnFlag);
		JSONObject jsonObject = JSONObject.fromObject(returnFlag);
				
				
		String flag = (String) jsonObject.get("SUCCESS");
		
		if(flag.equals("true")){
			
			//记录日志
			enterDao.createSQLQuery("insert into UP_DEVICE_INFO (DEVICE_REGCODE,QRCODE,COMPANYADDRESS,COMPANYCODE,COMPANYNAME,SECURITYOP,SECURITYTEL,SECURITYMANAGEMENT,DEVICENAME,CONSTRUCTIONUNITSNAME,INSPECTDATE,INSPECTNEXTDATE,INSPECTCONCLUSION,MAKEUNITSNAME,MAINTENANCEMAN,MAINTENANCETEL,DEVICEAREACODE,USEDATE,MAKEDATE,DEVICEUSEPLACE,DEVICESTREETCODE,DEVICEMODEL,INTERNALNUM,UP_STATUS,UP_DATE,UP_TYPE)  "
					+ " VALUES('" + doc.getDevice_registration_code() +"','" + doc.getDevice_qr_code() +"','" + enter.getCom_address() +"','" + enter.getCom_code() +"','" + enter.getCom_name() +"','" + doc.getSecurity_op() +"','" + doc.getSecurity_tel() +"','" + doc.getSecurity_management() +"','" + doc.getDevice_name() +"','" + doc.getConstruction_units_name() +"','" +inspectDate+"','" +inspectNextDate+"','" +doc.getInspect_conclusion()+"','" +doc.getMake_units_name()+"','" +doc.getMaintenance_man()+"','" +doc.getMaintenance_tel()+"','" +doc.getDevice_area_code()+"','" +doc.getUse_date()+"','" +doc.getMake_date()+"','" +doc.getDevice_use_place()+"','" +doc.getDevice_street_code()+"','" +doc.getDevice_model()+"','" +doc.getInternal_num()+"','上传成功',sysdate,'更新设备数据')").executeUpdate();
			
		}else{
			//记录日志
			enterDao.createSQLQuery("insert into UP_DEVICE_INFO (DEVICE_REGCODE,QRCODE,COMPANYADDRESS,COMPANYCODE,COMPANYNAME,SECURITYOP,SECURITYTEL,SECURITYMANAGEMENT,DEVICENAME,CONSTRUCTIONUNITSNAME,INSPECTDATE,INSPECTNEXTDATE,INSPECTCONCLUSION,MAKEUNITSNAME,MAINTENANCEMAN,MAINTENANCETEL,DEVICEAREACODE,USEDATE,MAKEDATE,DEVICEUSEPLACE,DEVICESTREETCODE,DEVICEMODEL,INTERNALNUM,UP_STATUS,UP_DATE,UP_TYPE)  "
					+ " VALUES('" + doc.getDevice_registration_code() +"','" + doc.getDevice_qr_code() +"','" + enter.getCom_address() +"','" + enter.getCom_code() +"','" + enter.getCom_name() +"','" + doc.getSecurity_op() +"','" + doc.getSecurity_tel() +"','" + doc.getSecurity_management() +"','" + doc.getDevice_name() +"','" + doc.getConstruction_units_name() +"','" +inspectDate+"','" +inspectNextDate+"','" +doc.getInspect_conclusion()+"','" +doc.getMake_units_name()+"','" +doc.getMaintenance_man()+"','" +doc.getMaintenance_tel()+"','" +doc.getDevice_area_code()+"','" +doc.getUse_date()+"','" +doc.getMake_date()+"','" +doc.getDevice_use_place()+"','" +doc.getDevice_street_code()+"','" +doc.getDevice_model()+"','" +doc.getInternal_num()+"','上传失败',sysdate,'更新设备数据')").executeUpdate();
			
		}
	}
	
	
	//获取UUID
	
	public  String getQrCode(String QrCode){
			
			
			
			String result = queryQrCode(QrCode);
			
			log.info("@￥￥￥￥￥￥￥￥￥￥…………………………………………………………………………"+result);
			if(StringUtil.isEmpty(result)){
				return "";
				
			}
			
			return openJson(result);
			
		
	}
	
	//解析返回數據獲取UUID
	public  String openJson(String result){
		
		String uuid="";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(result);
		
		String  flag = (String)jsonObject.get("SUCCESS");
	
		
		 if(flag.equals("true")){
			 
			 return uuid=(String)jsonObject.get("UUID");
			 
		 }else{
			 return uuid;
			 
		 }
	
	
	}
	

	//上傳檢驗信息
	public  String upCheckInfo(String upStr){
		

		log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!"+upStr);
		
		String renturFlag= getInspect().updateInspectionInfo("zdfdf342", "adfghgd43", upStr);
		
		log.info("!!!!!!!!!!!!!!!!!!!!!!!!"+renturFlag);

		JSONObject jsonObject = JSONObject.fromObject(renturFlag);
		
		
		return (String)jsonObject.get("SUCCESS");
		
	}
	
	
	//上傳檢驗報告數據 
	public  void upCheckInfo() throws Exception{
//		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat mat = new  SimpleDateFormat();
		//获取检验报告信息
		List<InspectionInfo> list = queryCheckInfo();
		
		log.info("#########################有没有数据"+list.size());
	
		//循环处理报告数据
		if(list.size()>0){
			
			for(int i=0;i<list.size();i++){
				//獲取檢驗業業務對象
				InspectionInfo info = list.get(i);
				//獲取檢驗類型
				String checkTye = info.getInspection().getCheck_type();
				
				if(!checkTye.equals("3")&&!checkTye.equals("1")&&!checkTye.equals("2")){
					log.info("#￥￥￥￥￥￥￥￥￥￥￥"+"只传定期和监督");
					continue;
				}
				
				String typeName="";
				//转化中文
				if("3".equals(checkTye)){
					typeName="定期检验";
				}else if("1".equals(checkTye)){
					typeName="制造监督检验";
				}else if("2".equals(checkTye)){
					typeName="安改维监督检验";
				}
				//获取报告书名称
				String reportName =reportDao.get(info.getReport_type()).getReport_name(); 
				//獲取設備信息
				DeviceDocument doc = deviceDao.get(info.getFk_tsjc_device_document_id());
				
				if(doc.getDevice_sort().startsWith("3")){
				
					if(StringUtil.isEmpty(doc.getDevice_qr_code())){
						
						log.info("#￥￥￥￥￥￥￥￥￥￥￥"+"没有二维码 ，怎么办 只能开溜");
						continue;
					}
				}
				
				try{
				  
					String temp =doc.getDevice_qr_code()+"#"+doc.getDevice_registration_code();
					
					log.info("#￥￥￥￥￥￥￥￥￥￥￥"+"有二维码 ，开始插入设备数据");
					//設備根據二維碼查詢市局接口數據
					String uuid = getQrCode(temp.trim());
				    
					
			
					//判斷是否存在數據
					if("".equals(uuid)){//如果uuid為空測插入一條新的數據,會返回uuid,再根據uuid傳遞報告檢驗數據
						
						if(!"3".equals(checkTye)){
							
						
						
							log.info("*………………&&&&&&&&&%%%%%%￥￥￥￥￥￥￥"+"有二维码 ，开始插入设备数据");
							String returnUUID = insertInfo(doc);
							
				
							//处理项目负责人和参检验人员 合并
							String  checkOp ="";
							if(StringUtil.isEmpty(info.getItem_op_name())){
								checkOp=info.getCheck_op_name();
							}else{
								checkOp = TS_Util.mergeCheckOps(info.getItem_op_name(), info.getCheck_op_name());
							}
							
							String upStr="{\"UUID\":\""+returnUUID+"\",\"PARAM\":{\"report_sn\":\""+(info.getReport_sn()!=null?info.getReport_sn():"")+"\",\"rqCode\":\""+(doc.getDevice_qr_code()!=null?doc.getDevice_qr_code():"")+"\",\"report_name\":\""+(reportName!=null?reportName:"")+"\",\"report_com_name\":\""+(info.getReport_com_name()!=null?info.getReport_com_name():"")+"\",\"report_com_address\":\""+(info.getReport_com_address()!=null?info.getReport_com_address():"")+"\",\"inspect_type_name\":\""+(typeName!=null?typeName:"")+"\",\"inspect_org\":\"四川省特种设备检验研究院\",\"inspect_date\":\""+new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time())+"\",\"inspect_next_date\":\""+new SimpleDateFormat("yyyy-MM-dd").format(info.getLast_check_time())+"\",\"inspect_conclusion\":\""+(info.getInspection_conclusion()!=null?info.getInspection_conclusion():"")+"\",\"inspect_ops\":\""+checkOp+"\",\"report_audit_name\":\""+(info.getExamine_name()!=null?info.getExamine_name():"")+"\",\"report_audit_date\":\""+(info.getExamine_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getExamine_Date()):"")+"\",\"report_sign_name\":\""+(info.getIssue_name()!=null?info.getIssue_name():"")+"\",\"report_sign_date\":\""+(info.getIssue_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getIssue_Date()):"")+"\"}}";
							//上傳檢驗信息
							
							String flag= upCheckInfo(upStr);
							
							
							if(flag.equals("true")){
								log.info("%%%%%%%%%%%%!"+"上传成功");
								
								//记录日志
								deviceDao.createSQLQuery("insert into UP_INSPECT_INFO (REPORT_SN,REPORT_NAME,REPORT_COM_NAME,REPORT_COM_ADDRESS,INSPECT_TYPE_NAME,INSPECT_ORG,INSPECT_DATE,INSPECT_NEXT_DATE,INSPECT_CONCLUSION,INSPECT_OPS,REPORT_AUDIT_NAME,REPORT_AUDIT_DATE,REPORT_SIGN_NAME,REPORT_SIGN_DATE,UP_STATUS,UP_DATE)  "
										+ " VALUES('"+(info.getReport_sn()!=null?info.getReport_sn():"")+"','"+(reportName!=null?reportName:"")+"','"+(info.getReport_com_name()!=null?info.getReport_com_name():"")+"','"+(info.getReport_com_address()!=null?info.getReport_com_address():"")+"','"+(checkTye!=null?checkTye:"")+"','四川省特种设备检验研究院','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getLast_check_time())+"','"+(info.getInspection_conclusion()!=null?info.getInspection_conclusion():"")+"','"+checkOp+"','"+(info.getExamine_name()!=null?info.getExamine_name():"")+"','"+(info.getExamine_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getExamine_Date()):"")+"','"+(info.getIssue_name()!=null?info.getIssue_name():"")+"','"+(info.getIssue_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getIssue_Date()):"")+"','上传成功',sysdate)").executeUpdate();
							//修改状态表示已传送
//								info.setSend_status("1");
								int cout=infoDao.createSQLQuery("update tzsb_inspection_info  t set t.send_status='1' where t.id='"+info.getId()+"'").executeUpdate();
								log.info("…………………………………………………………!"+"更新info状态成功"+cout);
								
							}else{
								//记录日志
								deviceDao.createSQLQuery("insert into UP_INSPECT_INFO (REPORT_SN,REPORT_NAME,REPORT_COM_NAME,REPORT_COM_ADDRESS,INSPECT_TYPE_NAME,INSPECT_ORG,INSPECT_DATE,INSPECT_NEXT_DATE,INSPECT_CONCLUSION,INSPECT_OPS,REPORT_AUDIT_NAME,REPORT_AUDIT_DATE,REPORT_SIGN_NAME,REPORT_SIGN_DATE,UP_STATUS,UP_DATE)  "
										+ " VALUES('"+(info.getReport_sn()!=null?info.getReport_sn():"")+"','"+(reportName!=null?reportName:"")+"','"+(info.getReport_com_name()!=null?info.getReport_com_name():"")+"','"+(info.getReport_com_address()!=null?info.getReport_com_address():"")+"','"+(checkTye!=null?checkTye:"")+"','四川省特种设备检验研究院','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getLast_check_time())+"','"+(info.getInspection_conclusion()!=null?info.getInspection_conclusion():"")+"','"+checkOp+"','"+(info.getExamine_name()!=null?info.getExamine_name():"")+"','"+(info.getExamine_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getExamine_Date()):"")+"','"+(info.getIssue_name()!=null?info.getIssue_name():"")+"','"+(info.getIssue_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getIssue_Date()):"")+"','上传失败',sysdate)").executeUpdate();
								
							}
											
						}
					}else{
						//如果uuid存在，測更新設備數據
						log.info("#￥￥￥￥￥￥￥￥￥￥￥"+"发现二维码 ，开始更新设备数据");
						updateInfo(doc,uuid);
						//处理项目负责人和参检验人员 合并
						String  checkOp ="";
						if(StringUtil.isEmpty(info.getItem_op_name())){
							checkOp=info.getCheck_op_name();
						}else{
							checkOp = TS_Util.mergeCheckOps(info.getItem_op_name(), info.getCheck_op_name());
						}
						
						String upStr="{\"UUID\":\""+uuid+"\",\"PARAM\":{\"report_sn\":\""+(info.getReport_sn()!=null?info.getReport_sn():"")+"\",\"rqCode\":\""+(doc.getDevice_qr_code()!=null?doc.getDevice_qr_code():"")+"\",\"report_name\":\""+(reportName!=null?reportName:"")+"\",\"report_com_name\":\""+(info.getReport_com_name()!=null?info.getReport_com_name():"")+"\",\"report_com_address\":\""+(info.getReport_com_address()!=null?info.getReport_com_address():"")+"\",\"inspect_type_name\":\""+(typeName!=null?typeName:"")+"\",\"inspect_org\":\"四川省特种设备检验研究院\",\"inspect_date\":\""+new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time())+"\",\"inspect_next_date\":\""+new SimpleDateFormat("yyyy-MM-dd").format(info.getLast_check_time())+"\",\"inspect_conclusion\":\""+(info.getInspection_conclusion()!=null?info.getInspection_conclusion():"")+"\",\"inspect_ops\":\""+checkOp+"\",\"report_audit_name\":\""+(info.getExamine_name()!=null?info.getExamine_name():"")+"\",\"report_audit_date\":\""+(info.getExamine_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getExamine_Date()):"")+"\",\"report_sign_name\":\""+(info.getIssue_name()!=null?info.getIssue_name():"")+"\",\"report_sign_date\":\""+(info.getIssue_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getIssue_Date()):"")+"\"}}";
						
						log.info("()()()()()()()("+info.getReport_sn());
						
						//上傳檢驗信息
						String flagInspect = upCheckInfo(upStr);
						
						if(flagInspect.equals("true")){
							
							
							
							//记录日志
							deviceDao.createSQLQuery("insert into UP_INSPECT_INFO (REPORT_SN,REPORT_NAME,REPORT_COM_NAME,REPORT_COM_ADDRESS,INSPECT_TYPE_NAME,INSPECT_ORG,INSPECT_DATE,INSPECT_NEXT_DATE,INSPECT_CONCLUSION,INSPECT_OPS,REPORT_AUDIT_NAME,REPORT_AUDIT_DATE,REPORT_SIGN_NAME,REPORT_SIGN_DATE,UP_STATUS,UP_DATE)  "
							
									+ " VALUES('"+(info.getReport_sn()!=null?info.getReport_sn():"")+"','"+(reportName!=null?reportName:"")+"','"+(info.getReport_com_name()!=null?info.getReport_com_name():"")+"','"+(info.getReport_com_address()!=null?info.getReport_com_address():"")+"','"+(checkTye!=null?checkTye:"")+"','四川省特种设备检验研究院','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getLast_check_time())+"','"+(info.getInspection_conclusion()!=null?info.getInspection_conclusion():"")+"','"+(info.getCheck_op_name()!=null?info.getCheck_op_name():"")+"','"+(info.getExamine_name()!=null?info.getExamine_name():"")+"','"+(info.getExamine_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getExamine_Date()):"")+"','"+(info.getIssue_name()!=null?info.getIssue_name():"")+"','"+(info.getIssue_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getIssue_Date()):"")+"','上传成功',sysdate)").executeUpdate();
							log.info("%%%%%%%%%%%%!"+"上传成功");
							//修改状态表示已传送
//							info.setSend_status("1");
//							infoDao.save(info);send_status
							int cout = infoDao.createSQLQuery("update tzsb_inspection_info  t set t.send_status='1' where t.id='"+info.getId()+"'").executeUpdate();
							log.info("…………………………………………………………!"+"更新info状态成功"+cout);
						
						}else{
							//记录日志
							deviceDao.createSQLQuery("insert into UP_INSPECT_INFO (REPORT_SN,REPORT_NAME,REPORT_COM_NAME,REPORT_COM_ADDRESS,INSPECT_TYPE_NAME,INSPECT_ORG,INSPECT_DATE,INSPECT_NEXT_DATE,INSPECT_CONCLUSION,INSPECT_OPS,REPORT_AUDIT_NAME,REPORT_AUDIT_DATE,REPORT_SIGN_NAME,REPORT_SIGN_DATE,UP_STATUS,UP_DATE)  "
									+ " VALUES('"+(info.getReport_sn()!=null?info.getReport_sn():"")+"','"+(reportName!=null?reportName:"")+"','"+(info.getReport_com_name()!=null?info.getReport_com_name():"")+"','"+(info.getReport_com_address()!=null?info.getReport_com_address():"")+"','"+(checkTye!=null?checkTye:"")+"','四川省特种设备检验研究院','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getAdvance_time())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(info.getLast_check_time())+"','"+(info.getInspection_conclusion()!=null?info.getInspection_conclusion():"")+"','"+(info.getCheck_op_name()!=null?info.getCheck_op_name():"")+"','"+(info.getExamine_name()!=null?info.getExamine_name():"")+"','"+(info.getExamine_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getExamine_Date()):"")+"','"+(info.getIssue_name()!=null?info.getIssue_name():"")+"','"+(info.getIssue_Date()!=null?new SimpleDateFormat("yyyy-MM-dd").format(info.getIssue_Date()):"")+"','上传失败',sysdate)").executeUpdate();
							
						}
						
					}
				
					}catch(Exception e){
						log.info("*&&&&&&*******&&&&&&&&**&"+e.getMessage());
						e.printStackTrace();
					}
				
				
				}
				
			}
		
		
		
	}
	
	public  String upWaring(String upStr){
			
		System.out.println("（）））（）（）（）（）（）（）（）（）（ 调用预警的数据");
		String renturFlag= getWaring().updateWarningDeal("zdfdf342", "adfghgd43", upStr);
		System.out.println("（）））（）（）（）（）（）（）（）（）（ 返回数据"+renturFlag);
		JSONObject jsonObject = JSONObject.fromObject(renturFlag);
		
		
		return (String)jsonObject.get("SUCCESS");
		
	}
	
	//上傳预警報告數據
	public  void upWaringInfo() throws Exception{
			//获取预警数据
			List<DeviceWarningDeal> dealList = queryDealInfo();
					
			//循环处理预警数据
			if(dealList.size()>0){
				
				for(int i=0;i<dealList.size();i++){
					//獲取预警对象
					DeviceWarningDeal deal = dealList.get(i);
					
					DeviceDocument doc = deviceDao.get(deal.getFk_base_device_document_id());
					try{
						
						String waringStr="{\"PARAM\":{\"device_registration_code\":\"11005101222006010002\",\"deal_status\":\""+(deal.getDeal_status()!=null?deal.getDeal_status():"")+"\",\"deal_man\":\""+(deal.getDeal_man()!=null?deal.getDeal_man():"")+"\",\"deal_time\":\""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(deal.getDeal_time())+"\",\"deal_receive_man\":\""+(deal.getDeal_receive_man()!=null?deal.getDeal_receive_man():"")+"\",\"inspect_org\":\"四川省特种设备检验研究院\",\"deal_unit\":\""+(deal.getDeal_unit()!=null?deal.getDeal_unit():"")+"\",\"deal_mark\":\""+(deal.getDeal_remark()!=null?deal.getDeal_remark():"")+"\"}}";
						
						String flag = upWaring(waringStr.trim());
						
						if(flag.equals("true")){
							//写日志并且更改状态
							//记录日志
							waringDao.createSQLQuery("insert into UP_WARING_INFO (DEVICE_REGISTRATION_CODE,DEAL_STATUS,DEAL_MAN,DEAL_TIME,DEAL_RECEIVE_MAN,INSPECT_ORG,DEAL_UNIT,DEAL_MARK,UP_STATUS,UP_DATE)  "
									+ " VALUES('"+(doc.getDevice_registration_code()!=null?doc.getDevice_registration_code():"")+"','"+(deal.getDeal_status()!=null?deal.getDeal_status():"")+"','"+(deal.getDeal_man()!=null?deal.getDeal_man():"")+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(deal.getDeal_time())+"','"+(deal.getDeal_receive_man()!=null?deal.getDeal_receive_man():"")+"','四川省特种设备检验研究院','"+(deal.getDeal_unit()!=null?deal.getDeal_unit():"")+"','"+(deal.getDeal_remark()!=null?deal.getDeal_remark():"")+"','上传成功',sysdate)").executeUpdate();
							
//							deal.setSend_status("1");
//							
////							
//							dealDao.save(deal);
							
							dealDao.createSQLQuery("update TZSB_WARNING_DEAL  t set t.send_status='1' where t.id='"+deal.getId()+"'").executeUpdate();
							
						}else{
							//记录日志
							waringDao.createSQLQuery("insert into UP_WARING_INFO (DEVICE_REGISTRATION_CODE,DEAL_STATUS,DEAL_MAN,DEAL_TIME,DEAL_RECEIVE_MAN,INSPECT_ORG,DEAL_UNIT,DEAL_MARK,UP_STATUS,UP_DATE)  "
									+ " VALUES('"+(doc.getDevice_registration_code()!=null?doc.getDevice_registration_code():"")+"','"+(deal.getDeal_status()!=null?deal.getDeal_status():"")+"','"+(deal.getDeal_man()!=null?deal.getDeal_man():"")+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(deal.getDeal_time())+"','"+(deal.getDeal_receive_man()!=null?deal.getDeal_receive_man():"")+"','四川省特种设备检验研究院','"+(deal.getDeal_unit()!=null?deal.getDeal_unit():"")+"','"+(deal.getDeal_remark()!=null?deal.getDeal_remark():"")+"','上传失败',sysdate)").executeUpdate();

						}
					}catch(Exception e){
						log.info("*&&&&&&*******&&&&&&&&**&"+e.getMessage());
						e.printStackTrace();
					}
				}
			
					
					
					
					
				}
			
				
			
	}
		
}
