package com.lsts.inspection.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import webService.adddt.DeviceQueryClient;
import webService.adddt.JyDataNewJyService;
import webService.dqjy.DeviceRegularInspectClient;
import webService.dqjy.JyDataJyService;

import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.inspection.bean.InspectionHallPara;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportValidation;
import com.lsts.inspection.service.InspectionHallParaService;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.inspection.service.ReportValidationService;

/**
 * 
 * 
 * @author zpl
 */

@Controller
@RequestMapping("reportValidationAction")
public class ReportValidationAction extends
		SpringSupportAction<ReportValidation, ReportValidationService> {


	
	@Autowired
	private ReportValidationService reportValidationService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private DeviceService deviceService;
	//验证电梯报告
	@RequestMapping(value = "validation")
	@ResponseBody
	public HashMap<String, Object> getalidation(HttpServletResponse res,String deviceQrCode, String deviceRegistrationCode,String checkType,String report_sn,String inspectionInfo) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		DeviceRegularInspectClient query=new DeviceRegularInspectClient();
		DeviceQueryClient add=new DeviceQueryClient();
		String[] inspectionInfos=inspectionInfo.split(",");
		String[] deviceQrCodes=deviceQrCode.split(",");
		String[] deviceRegistrationCodes=null;
		String[] report_sns=null;
		if(checkType.equals("3")){
			deviceRegistrationCodes=deviceRegistrationCode.split(",");
		}
		if(checkType.equals("2")){
			report_sns=report_sn.split(",");
		}
		
		String str="";
		int sum=0;
		try {
			ReportValidation vali=new ReportValidation();
			JyDataJyService service = null ;
			JyDataNewJyService serviceadd=null;
			for (int i = 0; i < inspectionInfos.length; i++) {
				
			
			if(checkType.equals("3")){
				InspectionInfo info=inspectionInfoService.get(inspectionInfos[i]);
				DeviceDocument deviceDocument =  deviceService.get(info.getFk_tsjc_device_document_id());
				String jyjyFlag="";
				String s=String.valueOf(System.currentTimeMillis());
				HashMap<String, Object> map1=query.inspect(deviceQrCodes[i],deviceRegistrationCodes[i],deviceDocument.getDevice_area_code(),service,deviceDocument.getDevice_use_place());
				String e=String.valueOf(System.currentTimeMillis());
				if(service==null){
					service = (JyDataJyService)map1.get("service");
				}
				int d=Integer.parseInt(e.substring(7))-Integer.parseInt(s.substring(7));
				
				if(map1.get("result").equals("1")){//定期检验验证
					vali.setValidation_status("0");
					info.setIs_validation("0");//验证二维码通过
					map.put("success", true);
					jyjyFlag="校验通过";
				}else{
					vali.setValidation_status("1");
					info.setIs_validation("1");//验证二维码未通过
					//反写检验主表
					if(map1.get("jyjyFlag").equals("4")){
						jyjyFlag="校验被系统后台不通过";
					}else if(map1.get("jyjyFlag").equals("2")){
						jyjyFlag="需要校验，等待维保人员上传校验数据";
					}else if(map1.get("jyjyFlag").equals("3")){
						jyjyFlag="维保已经上传了校验数据，系统平台后台人员核实中";
					}
					map.put("success", false);
					vali.setData_status("0");
					if(sum==0){
						str=deviceRegistrationCodes[i];
					}else{
						str=str+"、"+deviceRegistrationCodes[i];
					}
					
					sum++;
					
				}
				vali.setValidation_results("result:"+map1.get("result")+",reason:"+map1.get("reason")+",registNumber:"+map1.get("registNumber")+
						",registCode:"+map1.get("registCode")+",area:"+map1.get("area")+",address:"+map1.get("address")+",buildingName:"+map1.get("buildingName")+
						",building:"+map1.get("building")+",unit:"+map1.get("unit")+",useNumber:"+map1.get("useNumber")+",registCodeAddress:"+map1.get("registCodeAddress")+",jyjyFlag:"+jyjyFlag);
				inspectionInfoService.save(info);
				vali.setCost_date(Integer.toString(d));
				vali.setCheck_type("定期检验");
			} else if (checkType.equals("2")){//监督检验验证
				String s=String.valueOf(System.currentTimeMillis());
				HashMap<String, Object> map1=add.queryDevice(deviceQrCodes[i],user.getName(),report_sns[i],serviceadd);
				String e=String.valueOf(System.currentTimeMillis());
				int d=Integer.parseInt(e.substring(7))-Integer.parseInt(s.substring(7));
				InspectionInfo info=inspectionInfoService.get(inspectionInfos[i]);
				if(map1.get("result").equals("1")){
					vali.setValidation_status("0");
					info.setIs_validation("0");//验证二维码通过
					map.put("success", true);
				}else{
					vali.setValidation_status("1");
					//反写检验主表
					info.setIs_validation("1");//验证二维码未通过
					map.put("success", false);
					vali.setData_status("0");
					if(sum==0){
						str=deviceQrCodes[i];
					}else{
						str=str+"、"+deviceQrCodes[i];
					}
					
					sum++;
				}
				vali.setValidation_results("result:"+map1.get("result")+",reason:"+map1.get("reason"));
				inspectionInfoService.save(info);
				vali.setCheck_type("监督检验");
				vali.setCost_date(Integer.toString(d));
			}
			if(checkType.equals("2")){
				vali.setReport_sn(report_sns[i]);
			}
			vali.setValidation_date(new Date());
			vali.setFk_inspection_info(inspectionInfos[i]);
			vali.setValidation_man(user.getName());
			vali.setValidation_type("0");
			reportValidationService.save(vali);
			}
			map.put("device_code", str);//返回验证不通过的设备代码
			map.put("sum", sum);//返回验证不通过的设备数量
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}

	//记录验证不通过的审核信息
		@RequestMapping(value = "setalidation")
		@ResponseBody
		public HashMap<String, Object> setalidation(HttpServletRequest request,String inspectionInfo,String flow_num,String acc_id,String check_flow,String sub_op_id) throws Exception {
			String[] inspectionInfos=inspectionInfo.split(",");
			String[] acc_ids=acc_id.split(",");
			String sub_op_name = request.getParameter("sub_op_name");
			HashMap<String, Object> map = new HashMap<String, Object>();
		     for (int i = 0; i < inspectionInfos.length; i++) {
		    	 List<ReportValidation> list= reportValidationService.setalidation(inspectionInfos[i]);
		    	 for (int j = 0; j < list.size(); j++) {
		    		 ReportValidation vali= list.get(j);
		    		 vali.setAcc_id(acc_ids[i]);
		    		 vali.setAudit_id(sub_op_id);
		    		 vali.setAudit_name(sub_op_name);
		    		 vali.setFlow_num(flow_num);
		    		 vali.setCheck_flow(check_flow);
		    		 reportValidationService.save(vali);
				}
		    	
			}
			return map;
		}
		//通过二维码查询信息
				@RequestMapping(value = "getByCode")
				@ResponseBody
				public HashMap<String, Object> getByCode(HttpServletRequest request,String device_up_code) throws Exception {
					HashMap<String, Object> map = new HashMap<String, Object>();
					DeviceRegularInspectClient query=new DeviceRegularInspectClient();
					try {
						if(device_up_code!=null||!device_up_code.equals("")){
							HashMap<String, Object> map1=query.getinspect(device_up_code);
							map=map1;
						}
					} catch (Exception e) {
						// TODO: handle exception
						map.put("success", false);
					}
					
					return map;
				}

	
    
 
    
}
