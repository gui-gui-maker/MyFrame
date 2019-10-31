package com.lsts.inspection.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webService.adddt.DeviceQueryClient;
import webService.adddt.JyDataNewJyService;
import webService.dqjy.DeviceRegularInspectClient;
import webService.dqjy.JyDataJyService;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.core.exception.KhntException;
import com.khnt.rbac.impl.bean.User;
import com.khnt.rbac.impl.dao.UserDao;
import com.khnt.rbac.impl.manager.EmployeeManager;
import com.khnt.utils.BeanUtils;
import com.lsts.advicenote.service.MessageService;
import com.lsts.constant.Constant;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.dao.DeviceDao;
import com.lsts.inspection.bean.InspectionHallPara;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.bean.ReportValidation;
import com.lsts.inspection.dao.InspectionDao;
import com.lsts.inspection.dao.InspectionHallParaDao;
import com.lsts.inspection.dao.InspectionInfoDao;
import com.lsts.inspection.dao.ReportValidationDao;
import com.lsts.inspection.web.InspectionAction;

/**
 * 
 * 
 * @author zpl
 */
@Service("reportValidationService")
public class ReportValidationService extends EntityManageImpl<ReportValidation, ReportValidationDao> {
	@Autowired
	private ReportValidationDao reportValidationDao;
	@Autowired
	private InspectionInfoDao inspectionInfoDao;
	@Autowired
	private InspectionDao inspectionDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private InspectionAction inspectionAction;
	@Autowired
	private InspectionService inspectionService;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private UserDao userDao;
    @Autowired
	private MessageService messageService;

	public void autoValidation(){//定时任务
		System.out.println("执行定时任务");
		String hql="from InspectionInfo where is_validation in('1')";//筛选出验证不通过的数据 
		List<InspectionInfo> list=new ArrayList<InspectionInfo>();
		DeviceRegularInspectClient query=new DeviceRegularInspectClient();
		DeviceQueryClient add=new DeviceQueryClient();
		list=inspectionInfoDao.createQuery(hql).list();
		List<String> listq=new ArrayList<String>();
		try {
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				InspectionInfo info=list.get(i);
				ReportValidation valid=new ReportValidation();
				JyDataJyService service = null ;
				JyDataNewJyService serviceadd=null;
				
				DeviceDocument deviceDocument =  deviceDao.get(info.getFk_tsjc_device_document_id());
				if(!info.getData_status().equals("99")){//排除作废报告
				if(info.getInspection().getCheck_type().equals("3")){
					String jyjyFlag="";
					String s=String.valueOf(System.currentTimeMillis());
					HashMap<String, Object> map1=query.inspect(info.getDevice_qr_code(),deviceDocument.getDevice_registration_code(),deviceDocument.getDevice_area_code(),service,deviceDocument.getDevice_use_place());
					String e=String.valueOf(System.currentTimeMillis());
					int d=Integer.parseInt(e.substring(7))-Integer.parseInt(s.substring(7));
					if(map1.get("result").equals("1")){//定期检验验证
						valid.setValidation_status("3");
						valid.setData_status("1");				
						info.setIs_validation("0");//验证二维码通过
						jyjyFlag="校验通过";
						//自动提交验证通过的
						/*if(info.getFlow_note_name().equals("报告录入")){
							String sql="select AUDIT_ID,AUDIT_NAME,FLOW_NUM,ACC_ID,CHECK_FLOW from TS_REPORT_VALIDATION where FK_INSPECTION_INFO=? and VALIDATION_MAN!='定时任务'";
							List<Object> lists=reportValidationDao.createSQLQuery(sql, info.getId()).list();
							
								Object [] vobjs = (Object[]) lists.get(0);
								String audit_id = vobjs[0].toString();//
								String audit_name = vobjs[1].toString();//
								String flow_num = vobjs[2].toString();//
								String acc_id = vobjs[3].toString();//
								String check_flow = vobjs[4].toString();//
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("flow_num", flow_num);
								map.put("flag", "指定下一步审核人员");
								map.put("next_sub_op", audit_id);
								map.put("next_op_name", audit_name);
								map.put("ins_info_id", info.getId());
								map.put("acc_id", acc_id);
								inspectionService.autosubFlowProcess(map);
						}*/
						//验证成功的向list给值
						listq.add(info.getId());
						
					}else{
						valid.setValidation_status("1");
						info.setIs_validation("1");//验证二维码未通过
						//反写检验主表
						if(map1.get("jyjyFlag").equals("4")){		
							jyjyFlag="校验被系统后台不通过";
						}else if(map1.get("jyjyFlag").equals("2")){
							jyjyFlag="需要校验，等待维保人员上传校验数据";
						}else if(map1.get("jyjyFlag").equals("3")){
							jyjyFlag="维保已经上传了校验数据，系统平台后台人员核实中";
						}
					}
					inspectionInfoDao.save(info);
					valid.setValidation_results("result:"+map1.get("result")+",reason:"+map1.get("reason")+",registNumber:"+map1.get("registNumber")+
							",registCode:"+map1.get("registCode")+",area:"+map1.get("area")+",address:"+map1.get("address")+",buildingName:"+map1.get("buildingName")+
							",building:"+map1.get("building")+",unit:"+map1.get("unit")+",useNumber:"+map1.get("useNumber")+",registCodeAddress:"+map1.get("registCodeAddress")+",jyjyFlag:"+jyjyFlag);
					valid.setCost_date(Integer.toString(d));
					valid.setCheck_type("定期检验");
				} else if (info.getInspection().getCheck_type().equals("2")){//监督检验验证
					String s=String.valueOf(System.currentTimeMillis());
					HashMap<String, Object> map1=add.queryDevice(info.getDevice_qr_code(),"定时任务",info.getReport_sn(),serviceadd);
					String e=String.valueOf(System.currentTimeMillis());
					int d=Integer.parseInt(e.substring(7))-Integer.parseInt(s.substring(7));
					if(map1.get("result").equals("1")){
						valid.setValidation_status("3");
						valid.setData_status("1");	
						info.setIs_validation("0");//验证二维码通过
						//验证成功的向list给值
						listq.add(info.getId());
					}else{
						valid.setValidation_status("1");
						//反写检验主表
						info.setIs_validation("1");//验证二维码未通过
					}
					inspectionInfoDao.save(info);
					valid.setValidation_results("result:"+map1.get("result")+",reason:"+map1.get("reason"));
					valid.setCheck_type("监督检验");
					valid.setCost_date(Integer.toString(d));
				}
				}
				valid.setReport_sn(info.getReport_sn());
				valid.setValidation_date(new Date());
				valid.setValidation_type("1");
				valid.setFk_inspection_info(info.getId());
				valid.setValidation_man("定时任务");
				reportValidationDao.save(valid);
			}
		}
		//验证通过发送微信
		String sql = " select count(t.id) sum,REPORT_COM_NAME,ENTER_OP_ID from TZSB_INSPECTION_INFO  where 1=1  ";
        if(listq.size()>0){
            sql+=" and  id  in( ";
            for(int i=0;i<listq.size();i++){
                sql+="'"+listq.get(i)+"'";
                if(listq.size()-1!=i){
                    sql+=",";
                }
            }
            sql+=" ) group by ENTER_OP_ID,ENTER_OP_NAME";
            List<Object> lists= reportValidationDao.createSQLQuery(sql).list();
            for (int i = 0; i < lists.size(); i++) {
            	Object [] vobjs = (Object[]) lists.get(0);
				String sum = vobjs[0].toString();//
				String user_id = vobjs[1].toString();//
				String com_name = vobjs[2].toString();//
				 User user= userDao.get(user_id);
				 System.out.println("发送微信。。。");
				 String con="您好,"+com_name+"的"+sum+"份报告已经通过成都市局验证，可以提交审核或生成报告！";
		    		messageService.sendWxMsg(null,"定时任务", Constant.INSPECTION_CORPID, Constant.INSPECTION_PWD, 
		    				con,user.getEmployee().getMobileTel());
			}
            
           
    		
        }
		} catch (Exception e) {
			e.printStackTrace();
			KhntException kh = new KhntException(e);
			log.error(kh.getMessage());
		}
	}
	public List<ReportValidation> setalidation(String id){
		List<ReportValidation> list=new ArrayList<ReportValidation>();
		String hql="from ReportValidation where FK_INSPECTION_INFO=?";
		list=reportValidationDao.createQuery(hql, id).list();
		return list;
	}
}
