package com.lsts.report.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.khnt.core.crud.web.SpringSupportAction;
import com.khnt.core.crud.web.support.JsonWrapper;
import com.khnt.security.CurrentSessionUser;
import com.khnt.security.util.SecurityUtil;
import com.khnt.utils.DateUtil;
import com.khnt.utils.FileUtil;
import com.khnt.utils.StringUtil;
import com.lsts.MatrixToImageWriter;
import com.lsts.common.dao.CodeTablesDao;
import com.lsts.device.bean.DeviceDocument;
import com.lsts.device.service.DeviceService;
import com.lsts.inspection.bean.InspectionInfo;
import com.lsts.inspection.service.InspectionInfoService;
import com.lsts.report.bean.ReportDraw;
import com.lsts.report.bean.ReportDrawDTO;
import com.lsts.report.service.ReportDrawService;
import com.scts.payment.bean.InspectionInfoDTO;


/**
 * 报告领取控制器
 * 
 * @ClassName ReportDrawAction
 * @JDK 1.7
 * @author GaoYa
 * @date 2014-03-04 下午03:22:00
 */
@Controller
@RequestMapping("report/draw")
public class ReportDrawAction extends
		SpringSupportAction<ReportDraw, ReportDrawService> {

	@Autowired
	private ReportDrawService reportDrawService;
	@Autowired
	private InspectionInfoService inspectionInfoService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private CodeTablesDao codeTablesDao;
	private static String appId = "j7Q8UKJtmL8oGGIWhtvni5";
    private static String appKey = "xzvkOJhdAF98CANabgxGB9";
    private static String masterSecret = "uXXoZJiRZP7vR601ZDFkM5";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    static String CID = "3057832a47cf92413634e2a2adc5f7c0";
   

	/**
	 * 保存
	 * 
	 * @param request
	 * @param reportDraw
	 * @throws Exception
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public HashMap<String, Object> save(HttpServletRequest request,
			ReportDraw reportDraw) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			CurrentSessionUser curUser = this.getCurrentUser(); // 获取当前用户登录信息
			String inspection_info_id = reportDraw.getInspectionInfo().getId();
			String[] info_ids = inspection_info_id.split(",");
			List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
			for (int i = 0; i < info_ids.length; i++) {
				ReportDraw reportDraw1 = new ReportDraw();
				reportDraw1.setIdcard(reportDraw.getIdcard());
				reportDraw1.setJob_unit(reportDraw.getJob_unit());
				reportDraw1.setLinkmode(reportDraw.getLinkmode());
				reportDraw1.setPulldown_op(reportDraw.getPulldown_op());
				reportDraw1.setInspectionInfo(reportDraw.getInspectionInfo());
				reportDraw1.setReport_sn(reportDraw.getReport_sn());
				reportDraw1.setRemark(reportDraw.getRemark());
				reportDraw1.getInspectionInfo().setId(info_ids[i]);
				reportDraw1.setPulldown_time(DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDateTime("yyyy-MM-dd", new Date())));
				reportDraw1.setData_status("0");	// 数据状态（0：正常  99：已删除）
				reportDrawService.save(reportDraw1);
				
				InspectionInfo inspectionInfo = inspectionInfoService.get(info_ids[i]);
				if (inspectionInfo != null) {
					InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
					inspectionInfoDTO.setId(inspectionInfo.getId());
					DeviceDocument deviceDocument = deviceService
					.get(inspectionInfo.getFk_tsjc_device_document_id());
					inspectionInfoDTO.setDevice_area_name(codeTablesDao.getDeviceAreaName(deviceDocument.getDevice_area_code()));	// 安装区域
					
					inspectionInfoDTO
							.setReport_com_name(StringUtil
									.isNotEmpty(inspectionInfo
											.getReport_com_name()) ? inspectionInfo
									.getReport_com_name()
									: inspectionInfo.getInspection().getCom_name()); // 报告书使用单位
					inspectionInfoDTOList.add(inspectionInfoDTO);
				}
			}
			reportDraw.setInspectionInfoDTOList(inspectionInfoDTOList);
			wrapper.put("success", true);
			// 获取报告领取打印内容
			wrapper.put("printContent", reportDraw);
			wrapper.put("op_user_name", curUser.getName());
		} catch (Exception e) {
			e.printStackTrace();
			wrapper.put("success", false);
			wrapper.put("msg", "保存报告领取信息失败，请重试！");
		}
		wrapper.put("data", reportDraw);
		return wrapper;
	}

	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "detail")
	@ResponseBody
	public HashMap<String, Object> detail(HttpServletRequest request, String id)
			throws Exception {
		return super.detail(request, id);
	}

	/**
	 * 详情
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveEvaluate")
	@ResponseBody
	public HashMap<String, Object> saveEvaluate(HttpServletRequest request, String ids,String evaluate)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			reportDrawService.saveEvaluate(ids,evaluate);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	
	/*@RequestMapping(value = "savemobie2")
	@ResponseBody
	public HashMap<String, Object> savemobie2(HttpServletRequest request,
			ReportDraw reportDraw) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String [] infos=reportDraw.getInfoIds().split(",");
		for(int i=0;i<infos.length;i++)
		{
			String infoId=infos[i];
			ReportDraw draw=this.reportDrawService.getByInfoId(infoId);
			draw.setIdcard(reportDraw.getIdcard());
			draw.setLinkmode(reportDraw.getLinkmode());
			draw.setPulldown_time(new Date());
			draw.setInspectionInfo(inspectionInfoService.get(infoId));
			draw.setPulldown_op(reportDraw.getPulldown_op());
			draw.setContent(reportDraw.getContent());
			BASE64Decoder decoder = new BASE64Decoder();
			if(!StringUtil.isEmpty(reportDraw.getDrawSign()))
			{
				String drawSign=reportDraw.getDrawSign();
				drawSign=drawSign.trim();
				drawSign=drawSign.replace("data:image/png;base64,","");
				drawSign=drawSign.replace(" ", "+");
				byte[] bytes = decoder.decodeBuffer(drawSign);
				for (int j = 0; j < bytes.length; ++j) {
					if (bytes[j] < 0) {// 调整异常数据
						bytes[j] += 256;
					}
				}
				draw.setDraw_pic_blob(bytes);
			}
			reportDrawService.save(draw);
		}
		
		return wrapper;
	}*/
	
	/*@RequestMapping(value = "savemobie3")
	@ResponseBody
	public HashMap<String, Object> savemobie3(HttpServletRequest request,
			ReportDraw reportDraw) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		String infoIds=reportDrawService.getInspectionBytableId(reportDraw.getInfoIds());
		String [] infos=infoIds.split(",");
		for(int i=0;i<infos.length;i++)
		{
			String infoId=infos[i];
			ReportDraw draw=this.reportDrawService.getByInfoId(infoId);
			draw.setIdcard(reportDraw.getIdcard());
			draw.setPulldown_time(new Date());
			draw.setLinkmode(reportDraw.getLinkmode());
			draw.setInspectionInfo(inspectionInfoService.get(infoId));
			draw.setPulldown_op(reportDraw.getPulldown_op());
			draw.setContent(reportDraw.getContent());
			BASE64Decoder decoder = new BASE64Decoder();
			if(!StringUtil.isEmpty(reportDraw.getDrawSign()))
			{
				String drawSign=reportDraw.getDrawSign();
				drawSign=drawSign.trim();
				drawSign=drawSign.replace("data:image/png;base64,","");
				drawSign=drawSign.replace(" ", "+");
				byte[] bytes = decoder.decodeBuffer(drawSign);
				for (int j = 0; j < bytes.length; ++j) {
					if (bytes[j] < 0) {// 调整异常数据
						bytes[j] += 256;
					}
				}
				draw.setDraw_pic_blob(bytes);
			}
			reportDrawService.save(draw);
		}
		
		return wrapper;
	}
	*/
	
	
	/**
	 * 获取报告受检单位（报告领取时，自动获取领取人工作单位）
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getComInfo")
	@ResponseBody
	public HashMap<String, Object> getComInfo(HttpServletRequest request,
			String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(id)) {
			String[] ids = id.split(",");
			wrapper.put("success", true);
			wrapper.put("data", inspectionInfoService.get(ids[0]));
		}
		return wrapper;

	}
	
	/**
	 * 根据报告使用单位获取领取历史记录（报告领取时，自动获取领取人和联系电话）
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @date 2018-05-02 下午04:22:00
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getContactsInfo")
	@ResponseBody
	public HashMap<String, Object> getContactsInfo(HttpServletRequest request,
			String com_name) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(com_name)) {
			com_name = URLDecoder.decode(com_name, "UTF-8");
			ReportDrawDTO dto = new ReportDrawDTO();
			String pulldown_ops = "";
			String linkmodes = "";
			String[] names = com_name.split(",");
			for (int i = 0; i < names.length; i++) {
				List<ReportDraw> list = reportDrawService.getInfos(com_name);
				if(!list.isEmpty()){
					for(ReportDraw reportDraw : list){
						if(pulldown_ops.length()>0){
							if(!pulldown_ops.contains(reportDraw.getPulldown_op())){
								pulldown_ops += ","+reportDraw.getPulldown_op();
							}
						}else{
							pulldown_ops += reportDraw.getPulldown_op();
						}
						if(linkmodes.length()>0){
							if(!linkmodes.contains(reportDraw.getLinkmode())){
								linkmodes += ","+reportDraw.getLinkmode();
							}
						}else{
							linkmodes += reportDraw.getLinkmode();
						}
					}
				}
				
			}
			dto.setPulldown_op(pulldown_ops);
			dto.setLinkmode(linkmodes);
			wrapper.put("success", true);
			wrapper.put("data", dto);
		}
		return wrapper;

	}*/
	
	/**
	 * 根据报告使用单位获取领取历史记录（报告领取时，自动获取领取人和联系电话）
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 * @author GaoYa
	 * @date 2018-05-02 下午04:22:00
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getContactsInfo")
	@ResponseBody
	public HashMap<String, Object> getContactsInfo(HttpServletRequest request,
			String com_name,String q) throws Exception {
		//String pulldown_op = new String(q.getBytes("iso8859-1"),"UTF-8");//正式服上无此段代码，否则会乱码
		String pulldown_op = q;
		ArrayList al = new ArrayList();
		if (StringUtil.isNotEmpty(pulldown_op)) {
			List<ReportDraw> list = reportDrawService.getInfosByName(pulldown_op);
			if(!list.isEmpty()){
				for(ReportDraw reportDraw : list){
					HashMap<String, Object> hm = new HashMap<String, Object>();
					hm.put("pulldown_ops", reportDraw.getPulldown_op());
					hm.put("linkmodes", reportDraw.getLinkmode());
					if(!al.contains(hm)) {
						al.add(hm);
					}
				}
			}
		}
		return JsonWrapper.successWrapper(al);
	}
	
	/**
	 * 获取制造单位（报告领取时，自动获取领取人的工作单位名称）
	 * 
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getZZJDComInfo")
	@ResponseBody
	public HashMap<String, Object> getZZJDComInfo(HttpServletRequest request,
			String id) throws Exception {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(id)) {
			String[] ids = id.split(",");
			wrapper.put("success", true);
			wrapper.put("data", inspectionInfoService.get(ids[0]));
		}
		return wrapper;

	}

	/**
	 * 获取报告领取记录打印内容（机电类设备）
	 * 
	 * @param request
	 * @param id --
	 *            报告领取ID
	 * @param inspection_info_id --
	 *            报检业务ID
	 * @return
	 */
	@RequestMapping(value = "getPrintContent")
	@ResponseBody
	public HashMap<String, Object> getPrintContent(HttpServletRequest request,
			String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				CurrentSessionUser user = super.getCurrentUser();
				String[] ids = id.split(",");
				ReportDraw reportDraw  = null;
				List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
				
				for (int i = 0; i < ids.length; i++) {
					ReportDraw reportDraw1 = reportDrawService.get(ids[i]);
					if (reportDraw == null) {
						reportDraw = reportDraw1;
					}
					InspectionInfo inspectionInfo = inspectionInfoService.get(reportDraw1.getInspectionInfo().getId());
					if (inspectionInfo != null) {
						InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
						inspectionInfoDTO.setId(inspectionInfo.getId());
						DeviceDocument deviceDocument = deviceService
						.get(inspectionInfo.getFk_tsjc_device_document_id());
						inspectionInfoDTO.setDevice_area_name(codeTablesDao.getDeviceAreaName(deviceDocument.getDevice_area_code()));	// 安装区域
						inspectionInfoDTO.setDevice_sort_code(deviceDocument.getDevice_sort_code());
						inspectionInfoDTO
								.setReport_com_name(StringUtil
										.isNotEmpty(inspectionInfo
												.getReport_com_name()) ? inspectionInfo
										.getReport_com_name()
										: inspectionInfo.getInspection().getCom_name()); // 报告书使用单位
						inspectionInfoDTOList.add(inspectionInfoDTO);
					}
				}
				reportDraw.setInspectionInfoDTOList(inspectionInfoDTOList);
				reportDraw.setPulldown_time(DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDateTime("yyyy-MM-dd", reportDraw.getPulldown_time())));
				wrapper.put("success", true);
				// 获取报告领取打印内容
				wrapper.put("printContent", reportDraw);
				wrapper.put("op_user_name", user.getName());
			}
		} catch (Exception e) {
			log.error("获取报告领取记录打印内容出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "获取报告领取记录打印内容出错！");
			e.printStackTrace();
		}
		return wrapper;
	}
	
	/**
	 * 获取报告领取记录打印内容（承压类设备）
	 * 
	 * @param request
	 * @param id --
	 *            报告领取ID
	 * @param inspection_info_id --
	 *            报检业务ID
	 * @return
	 */
	@RequestMapping(value = "getZZJDPrintContent")
	@ResponseBody
	public HashMap<String, Object> getZZJDPrintContent(HttpServletRequest request,
			String id) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(id)) {
				CurrentSessionUser user = super.getCurrentUser();
				String[] ids = id.split(",");
				ReportDraw reportDraw  = null;
				List<InspectionInfoDTO> inspectionInfoDTOList = new ArrayList<InspectionInfoDTO>(); // 报检业务信息列表
				
				for (int i = 0; i < ids.length; i++) {
					ReportDraw reportDraw1 = reportDrawService.get(ids[i]);
					if (reportDraw == null) {
						reportDraw = reportDraw1;
					}
					InspectionInfo inspectionInfo = inspectionInfoService.get(reportDraw1.getInspectionInfo().getId());
					if (inspectionInfo != null) {
						InspectionInfoDTO inspectionInfoDTO = new InspectionInfoDTO();
						inspectionInfoDTO.setId(inspectionInfo.getId());
						
						inspectionInfoDTO
								.setReport_com_name(StringUtil
										.isNotEmpty(inspectionInfo
												.getReport_com_name()) ? inspectionInfo
										.getReport_com_name()
										: inspectionInfo.getInspection().getCom_name()); // 报告书使用单位
						inspectionInfoDTOList.add(inspectionInfoDTO);
					}
				}
				reportDraw.setInspectionInfoDTOList(inspectionInfoDTOList);
				//reportDraw.setPulldown_time(DateUtil.convertStringToDate("yyyy-MM-dd", DateUtil.getDateTime("yyyy-MM-dd", reportDraw.getPulldown_time())));
				wrapper.put("success", true);
				// 获取报告领取打印内容
				wrapper.put("printContent", reportDraw);
				wrapper.put("op_user_name", user.getName());
			}
		} catch (Exception e) {
			log.error("获取报告领取记录打印内容出错：" + e.getMessage());
			wrapper.put("success", false);
			wrapper.put("message", "获取报告领取记录打印内容出错！");
			e.printStackTrace();
		}
		return wrapper;
	}
	
	// 修改报告领取记录的领取人、联系电话、报告书编号
    @RequestMapping(value = "modify")
    @ResponseBody
    public HashMap<String, Object> modify(@RequestBody ReportDrawDTO entity,HttpServletRequest request) throws Exception {	
    	String id=request.getParameter("id");
        return reportDrawService.modify(id,entity);
    }
    
    // 删除
	@RequestMapping(value = "del")
	@ResponseBody
	public HashMap<String, Object> del(HttpServletRequest request, String ids)
			throws Exception {
		String[] idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			reportDrawService.del(request, idArr[i]);
		}
		return JsonWrapper.successWrapper(ids);
	}
	
	@RequestMapping({ "detailmobie" })
	@ResponseBody
	public HashMap<String, Object> detailmobie(HttpServletRequest request, @RequestParam("id") String id) throws Exception {
		ReportDraw entity=null;
		if(StringUtil.isNotEmpty(id))
		{
			String [] xx=id.split(",");
			if(xx.length==1)
			{
				entity=reportDrawService.getInspectionInfoByid(id.split(",")[0]);
			}
		}
		return JsonWrapper.successWrapper(entity);
	}
	
	@RequestMapping({ "detailmobiedraw" })
	@ResponseBody
	public HashMap<String, Object> detailmobiedraw(HttpServletRequest request, @RequestParam("id") String id) throws Exception {
		String infoId=reportDrawService.getInspectionBytableId(id);
		ReportDraw entity=null;
		if(StringUtil.isNotEmpty(infoId))
		{
			String [] xx=infoId.split(",");
			if(xx.length==1)
			{
				entity=reportDrawService.getInspectionInfoByid(infoId.split(",")[0]);
			}
		}
		return JsonWrapper.successWrapper(entity);
	}
	

	
	/*public static TransmissionTemplate getTransmissionTemplate() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    String json="{'userName':'高雅','state':0,'pageUrl':'../../app/payment/report_draw.html', 'pageId':'../../app/payment/report_draw.html','pageFunction':'', 'params':[{'keyName':'name1','keyValue':'value1'},{'keyName':'name2','keyValue':'value2'}], 'toastMsg':'1111' ,'isDialog':true}";
	    template.setTransmissionContent(json);
	   
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    return template;
	}
	
	 @RequestMapping({ "reportpush" })
	 @ResponseBody
	 public void reportpush()
	 {
		 IGtPush push = new IGtPush(url, appKey, masterSecret);
		 TransmissionTemplate template = new TransmissionTemplate();
  	    template.setAppId(appId);
  	    template.setAppkey(appKey);
  	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
  	    template.setTransmissionType(2);
  	    String json="{'userName':'高雅','state':0,'pageUrl':'../../app/payment/report_draw.html', 'pageId':'../../app/payment/report_draw.html','pageFunction':'', 'params':[{'keyName':'name1','keyValue':'value1'},{'keyName':'name2','keyValue':'value2'}], 'toastMsg':'1111' ,'isDialog':true}";
  	    template.setTransmissionContent(json);
  	    
  	  List<String> appIds = new ArrayList<String>();
      appIds.add(appId);

      // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
      AppMessage message = new AppMessage();
      message.setData(template);
      message.setAppIdList(appIds);
      message.setOffline(true);
      message.setOfflineExpireTime(1000 * 600);

      IPushResult ret = push.pushMessageToApp(message);
      System.out.println(ret.getResponse().toString());
	 }*/
	
	/*

	 @RequestMapping({ "reportpush" })
	@ResponseBody
	public void reportpush()
	{
		 IGtPush push = new IGtPush(appKey, masterSecret, true);
	        // 此处true为https域名，false为http，默认为false。Java语言推荐使用此方式
	        // IGtPush push = new IGtPush(host, appkey, master);
	        // host为域名，根据域名区分是http协议/https协议
		 TransmissionTemplate template = getTransmissionTemplate();
	        SingleMessage message = new SingleMessage();
	        message.setOffline(true);
	        // 离线有效时间，单位为毫秒，可选
	        message.setOfflineExpireTime(24 * 3600 * 1000);
	        message.setData(template);
	        message.setPushNetWorkType(0); // 可选，判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
	        Target target = new Target();
	        target.setAppId(appId);
	        target.setClientId(CID);
	        // 用户别名推送，cid和用户别名只能2者选其一
	        // String alias = "个";
	        // target.setAlias(alias);
	        IPushResult ret = null;
	        try {
	            ret = push.pushMessageToSingle(message, target);
	        } catch (RequestException e) {
	            e.printStackTrace();
	            ret = push.pushMessageToSingle(message, target, e.getRequestId());
	        }
	        if (ret != null) {
	            System.out.println(ret.getResponse().toString());
	        } else {
	            System.out.println("服务器响应异常");
	        }

	}*/
	/**
	 * 报告领取详情获取签名图片
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getReportDrawSign")
	@ResponseBody
	public HashMap<String,Object> reportDrawDetail(String id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = reportDrawService.getReportDrawSign(id);
			//格式化报告书编号
			//List<Object> list = (List<Object>)serviceData.get("rows");
			//份数
			//map.put("count", list.size());
			/*String firstSnStr = null;
			int firstSnNum = 0;
			String tempSnStr = null;
			int tempSnNum = 0;
			Map<String,List<List<Integer>>> seqMap = new HashMap<String,List<List<Integer>>>();
			Map<String,List<Integer>> noSeqMap = new HashMap<String,List<Integer>>();
			List<Integer> seq = new ArrayList<Integer>();
			List<List<Integer>> seqs = null;
			if(list.size()==1){
				map.put("one", list.get(0).toString());
			}else{
				for(int i=0;i<list.size();i++){
			   		String resn = list.get(i).toString();
		   			tempSnStr = resn.substring(0,resn.length()-5);
			   		tempSnNum = Integer.parseInt(resn.substring(resn.length()-5,resn.length()));
			   		System.out.println(tempSnNum - firstSnNum == 1);
			   		if(i==0){
			   			firstSnStr = resn.substring(0,resn.length()-5);
				   		firstSnNum = Integer.parseInt(resn.substring(resn.length()-5,resn.length()));
				   		seq.add(firstSnNum);
				   		
			   		}else if(tempSnStr.equals(firstSnStr) && tempSnNum - firstSnNum == 1){
			   			//这里seq至少有两个对象
			   			seq.add(tempSnNum);
			   			
		   				if(seqMap.containsKey(tempSnStr)){
		   					//有相同前缀的报告书编号
		   					List<List<Integer>> ll = seqMap.get(tempSnStr);
		   					//但是如1-3号 和 6-8号 被分成两段了
		   					if(seq != ll.get(ll.size()-1)){
		   						ll.add(seq);
		   					}
		   				}else{
		   					seqs = new ArrayList<List<Integer>>(); 
		   					seqs.add(seq);
		   					seqMap.put(tempSnStr, seqs);
		   				}
		   				//重新将当前对象设置为第一个比较对象
			   			firstSnStr = tempSnStr;
			   			firstSnNum = tempSnNum;
			   				
			   		}else if(i==list.size()-1){
			   			//只有可能是1
			   			if(seq.size()==1){
			   				if(noSeqMap.containsKey(firstSnStr)){
			   					noSeqMap.get(firstSnStr).add(seq.get(0));
			   				}else{
			   					noSeqMap.put(firstSnStr, seq);
			   				}
			   			}
			   			//加入最后一个
		   				if(noSeqMap.containsKey(tempSnStr)){
		   					noSeqMap.get(tempSnStr).add(tempSnNum);
		   				}else{
		   					List<Integer> last = new ArrayList<Integer>();
		   					last.add(tempSnNum);
		   					noSeqMap.put(tempSnStr,last);
		   				}
			   		}else{
			   			//只有可能是1
			   			if(seq.size()==1){
			   				if(noSeqMap.containsKey(firstSnStr)){
			   					noSeqMap.get(firstSnStr).add(seq.get(0));
			   				}else{
			   					noSeqMap.put(firstSnStr, seq);
			   				}
			   			}
			   			//重新将当前对象设置为第一个比较对象
			   			firstSnStr = tempSnStr;
			   			firstSnNum = tempSnNum;
			   			//重新实例化一个容器
			   			seq = new ArrayList<Integer>(); 
			   			seq.add(tempSnNum);
			   		}
			   	}
			}*/
		   	
		   	//签名图片
			/*String  base64img = (String) serviceData.get("image");
			map.put("data", base64img);
			map.put("noseq", noSeqMap);
			map.put("seq", seqMap);*/
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", true);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "savePreparedQrcode")
	@ResponseBody
	public HashMap<String, Object> savePreparedQrcode(HttpServletResponse response,HttpServletRequest request,String ids,String report_sns) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			String qrcode = reportDrawService.saveDrawQrcode(ids,report_sns);
			map.put("data", qrcode);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			map.put("success", false);
		}
		return map;
	}
	/**
	 * 移动端报告领取二维码生成
	 * @param request
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "savePreparedQrcodeImg")
	public void savePreparedQrcodeImg(HttpServletResponse response,HttpServletRequest request,String qrcode) throws Exception {
		try{
			String imagepath=request.getSession().getServletContext().getRealPath("upload");
			String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getSession().getServletContext().getContextPath();
		    int width = 150; 
		    int height = 150; 
		    //二维码的图片格式 
		    String format = "gif"; 
		    Hashtable hints = new Hashtable(); 
		    //内容所使用编码 
		    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		    BitMatrix bitMatrix = new MultiFormatWriter().encode("reportDrawQrcode-"+qrcode, 
		            BarcodeFormat.QR_CODE, width, height, hints); 
		    File file1 = new File(imagepath);
		    if(!file1.mkdir()){
		    	file1.mkdir();
		    }
		    File file = new File(imagepath+File.separator+"new.gif");
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    String imageFile = imagepath+File.separator+"new.gif";
		    reportDrawService.saveDrawQrcodeImg(qrcode,imageFile);
		    //生成二维码 
		    OutputStream stream = new FileOutputStream(imageFile); 
		    
		    MatrixToImageWriter.writeToStream(bitMatrix, format, stream); 
		    
		    FileUtil.download(response, imageFile, "", "application/octet-stream");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取要领的报告关联的二维码信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDrawQrcode")
	@ResponseBody
	public HashMap<String, Object> getDrawQrcode(@RequestBody Map<String, Object> params) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			List<Object[]> list = reportDrawService.getDrawQrcode(params.get("id").toString());
			if(list.size()>0){
				map.put("data", list.get(0));
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
			map.put("success", false);
		}
		return map;
	}
	
	
	/**
	 * 报告领取详情获取签名图片(绝对路径)
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reportDrawDetail1")
	@ResponseBody
	public HashMap<String,Object> reportDrawDetail1(String id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = reportDrawService.getReportDrawSign1(id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", true);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 报告领取详情获取签名图片（相对路径）
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reportDrawDetail2")
	@ResponseBody
	public HashMap<String,Object> reportDrawDetail2(HttpServletRequest request,String id) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = reportDrawService.getReportDrawSign3(request,id);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", true);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	public HashMap<String, Object> updateReportDraw(HttpServletRequest request){
		String ids=request.getParameter("id");
    	for(int i=0;i<ids.split(",").length;i++){
    		String id=ids.split(",").toString();
    		ReportDraw entity=reportDrawService.get(id);
    	}
    	return JsonWrapper.successWrapper(ids);
	}
	
	@RequestMapping("updateReportPeople")
	@ResponseBody
	public HashMap<String, Object> updateReportPeople(String ids,String pulldown_op,String linkmode) throws Exception{
		CurrentSessionUser user=SecurityUtil.getSecurityUser();
		for(int i=0;i<ids.split(",").length;i++){
			String id=ids.split(",")[i].toString();
			ReportDraw entity=reportDrawService.get(id);
			entity.setPulldown_op(URLDecoder.decode(pulldown_op, "utf-8"));
			entity.setLinkmode(linkmode);
			entity.setMdy_user_id(user.getId());
			entity.setMdy_user_name(user.getName());
			entity.setMdy_date(new Date());
			reportDrawService.save(entity);
		}
		return JsonWrapper.successWrapper();
	}
}
