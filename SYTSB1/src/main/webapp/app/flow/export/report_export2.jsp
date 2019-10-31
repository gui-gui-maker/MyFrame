<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.lsts.device.bean.DeviceDocument"%>
<%@ page import="com.lsts.inspection.bean.ReportItemValue"%>
<%@ page import="com.lsts.report.bean.ReportItem"%>
<%@page import="com.khnt.pub.fileupload.bean.Attachment"%>
<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.lsts.inspection.dao.ReportPerDao"%>
<%@page import="com.lsts.common.dao.AttachmentsDao"%>
<%@page import="java.io.IOException"%>
<%@page import="util.TS_Util"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming" %>
<%@ page import="com.ming.webreport.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.khnt.utils.StringUtil" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="com.lsts.ImageTool"%>
<%@ page import="com.lsts.inspection.bean.*" %>
<%@ page import="com.lsts.report.bean.*" %>
<%@ page import="oracle.sql.*" %>
<%@ page import="com.khnt.base.Factory" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<%


	Calendar cal = Calendar.getInstance();
	String printout = request.getParameter("printout");
	String isLast = request.getParameter("isLast");	// 状态（提交后关闭窗口）
	//判断是否是提交数据后，如是则关闭窗口
	if (StringUtil.isNotEmpty(isLast))
	{
%>
	<script>
		//alert("打印完成！");
		parent.left.printAll();
	</script>
<%
	//return;
	}
%>
<%
    //查找流程
    //String report_type = request.getParameter("report_type");
    //String id = request.getParameter("id");
    //Entity reports = (Entity) request.getAttribute("REPORTS");
	//Entity tsjy_inspection_info = (Entity) request.getAttribute("TSJY_INSPECTION_INFO");
	DeviceDocument deviceDocument = (DeviceDocument)request.getSession().getAttribute("deviceDocument");
	List<ReportItemValue> reportItemValueList = (List<ReportItemValue>)request.getSession().getAttribute("reportItemValueList");
	List<ReportItem> reportItemList = (List<ReportItem>)request.getSession().getAttribute("reportItemList");
    InspectionInfo inspectionInfo = (InspectionInfo)request.getSession().getAttribute("inspectionInfo");
    Report report = (Report)request.getSession().getAttribute("report");
	String printer_name = (String)request.getSession().getAttribute("printer_name");	// 打印机名称  
	String print_copies = request.getParameter("printcopies");	// 打印份数
	String print_pages = request.getParameter("printpages");	// 打印页码
	//String jghzh = (String)request.getSession().getAttribute("JGHZH");	// 检验机构核准证号
	String report_sn = inspectionInfo.getReport_sn();	// 报告编号
	
    String report_item = inspectionInfo.getReport_item();//tsjy_inspection_info.getStringValue("report_item",0);
	String device_sort_code = deviceDocument.getDevice_sort_code();
	String big_class = StringUtil.isNotEmpty(device_sort_code)?device_sort_code.substring(0, 1):"";	// 设备大类
    if(StringUtil.isEmpty(big_class)){
    	big_class = StringUtil.isNotEmpty(deviceDocument.getDevice_sort())?deviceDocument.getDevice_sort().substring(0, 1):"";	// 设备大类
    }
    String com_name = "";
	String device_registration_code = "";
	String cqjgjpj_date = "";
    String inspect_date = "";
    
    System.out.println("`````````````````````````````````````"+report_item);
	
    // 构造DataRecord、MRDataSet对象
    MRDataSet mrds = new MRDataSet();
    DataRecord rec = new DataRecord();
    for (int i = 0; i < reportItemValueList.size(); i++)
    {
    	ReportItemValue reportItemValue = reportItemValueList.get(i);
    	//if(reportItemValue.getItem_name().equals("NEXT_INSPECTION_DATE")){
    	//	cal.setTime(DateUtil.convertStringToDate("yyyy年MM月dd日", reportItemValue.getItem_value()));
    	//	String newdate = DateUtil.getDate(cal.getTime()); 
    	//	rec.setValue("NEXT_INSPECTION_DATE",newdate);
    	//} else if(reportItemValue.getItem_name().toUpperCase().indexOf("PICTURETEXT") != -1){
    	//	rec.setValue(reportItemValue.getItem_name()+"P",reportItemValue.getItem_value());
    	//	rec.setValue(reportItemValue.getItem_name(), "");  
    	//} else {
        //	rec.setValue(reportItemValue.getItem_name(), reportItemValue.getItem_value()); 
    	//}
    	String item_value = "";
    	if("ZLSCQK".equals(reportItemValue.getItem_name()) || "LAST_CHECK_PROBLEMS".equals(reportItemValue.getItem_name())){
    		item_value = TS_Util.nullToString(reportItemValue.getItem_value());
    	}else{
    		item_value = TS_Util.nullToString(reportItemValue.getItem_value()).replaceAll("\r|\n","");
    	}
    	if(reportItemValue.getItem_name().equals("DRAFT_DATE")){
    		if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
    			if(reportItemValue.getItem_value().indexOf("/")!=-1){
    				cal.setTime(DateUtil.convertStringToDate("yyyy/MM/dd", reportItemValue.getItem_value()));
    			}else{
    				cal.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", reportItemValue.getItem_value()));
    			}
        		String newdate = DateUtil.getDateTime("yyyy年MM月dd日",cal.getTime()); 
        		rec.setValue("DRAFT_DATE",newdate);
    		}
    	}else if("COM_NAME".equals(reportItemValue.getItem_name())){
			com_name = TS_Util.nullToString(item_value);
		}else if(reportItemValue.getItem_name().equals("DEVICE_CODE")){
    		// 所有起重机监检，打印合格证时，设备注册代码取“设备代码”不取系统生成的“设备注册代码”
    		if("4".equals(big_class) && inspectionInfo.getReport_sn().contains("QA")){	
    			if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
        			device_registration_code = reportItemValue.getItem_value();
        			rec.setValue("DEVICE_REGISTRATION_CODE", reportItemValue.getItem_value());	// 设备代码
        		}else{
        			device_registration_code = deviceDocument.getDevice_code();
        			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_code());	// 设备代码
        		}
    		}
    		rec.setValue(reportItemValue.getItem_name(), item_value); 
    	}else if(reportItemValue.getItem_name().startsWith("INSPECTION_DATE") || "INSPECTION_END_DATE".equals(reportItemValue.getItem_name())
    			 || "INSPECT_NEXT_DATE".equals(reportItemValue.getItem_name()) || reportItemValue.getItem_name().contains("INSPECTION_DATE")){
    		if(!"LAST_INSPECTION_DATE_Y".equals(reportItemValue.getItem_name()) && !"LAST_INSPECTION_DATE_M".equals(reportItemValue.getItem_name())
    				&& !"LAST_INSPECTION_DATE_AUTO".equals(reportItemValue.getItem_name())
    				&& !"DEVICE_INSPECTION_DATE_MONTH".equals(reportItemValue.getItem_name())
    				&& !"DEVICE_INSPECTION_DATE_YEAR".equals(reportItemValue.getItem_name())
    				&& !"KY_LAST_INSPECTION_DATE".equals(reportItemValue.getItem_name())
    				&& !"DEVICE_INSPECTION_DATE".equals(reportItemValue.getItem_name())){
    			if(StringUtil.isNotEmpty(item_value)){
    				if(item_value.indexOf(".")!=-1){
        				item_value = item_value.replaceAll("\\.", "-");
        			} else if(item_value.indexOf("－")!=-1){
        				item_value = item_value.replaceAll("－", "-");
        			} else if(item_value.indexOf("/")!=-1){
        				cal.setTime(DateUtil.convertStringToDate("yyyy/MM/dd", item_value));
        			} else {
        				cal.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", item_value));
        			}
            		String newdate = DateUtil.getDateTime("yyyy年MM月dd日",cal.getTime()); 
            		rec.setValue(reportItemValue.getItem_name(),newdate);
        		}
    		}else{
    			rec.setValue(reportItemValue.getItem_name(),item_value);
    		}
    	}else if(reportItemValue.getItem_name().equals("CQJ_GJ_PJ_DATE")){
    		cqjgjpj_date = TS_Util.nullToString(item_value);
    		if(StringUtil.isNotEmpty(cqjgjpj_date)){
    			if(cqjgjpj_date.indexOf("/")!=-1){
    				cal.setTime(DateUtil.convertStringToDate("yyyy/MM/dd", cqjgjpj_date));
    			}else{
    				cal.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", cqjgjpj_date));
    			}
        		String newdate = DateUtil.getDateTime("yyyy年MM月dd日",cal.getTime()); 
        		rec.setValue("CQJ_GJ_PJ_DATE",newdate);
    		}
    	}else if("INSPECT_DATE".equals(reportItemValue.getItem_name())){
			inspect_date = TS_Util.nullToString(item_value);
			rec.setValue("INSPECT_DATE",inspect_date);
		}else if(reportItemValue.getItem_name().equals("DEVICE_REGISTRATION_CODE")){
    		if(!"4".equals(big_class)){	
    			if(StringUtil.isNotEmpty(reportItemValue.getItem_value())){
        			device_registration_code = reportItemValue.getItem_value();
        			rec.setValue("DEVICE_REGISTRATION_CODE", reportItemValue.getItem_value());	// 设备注册代码
        		}
    			rec.setValue(reportItemValue.getItem_name(), item_value); 
    		}
    	}else{
    		rec.setValue(reportItemValue.getItem_name(), item_value); 
    	}
    }
    
    System.out.println("1111111111111111111="+(StringUtil.isNotEmpty(report_item)?report_item.split(",").length:0));
	
	rec.setValue("TotalP", StringUtil.isNotEmpty(report_item)?report_item.split(",").length:0);

	byte[] check_op_img = (byte[])request.getAttribute("check_op_img");
	byte[] examine_op_img = (byte[])request.getAttribute("examine_op_img");
	byte[] issue_op_img = (byte[])request.getAttribute("issue_op_img");
	byte[] enter_op_img = (byte[])request.getAttribute("enter_op_img");
	
	//设置打印签名不可见，电子签名可见
	rec.setValue("INSPECTION_OP_STR", "");
	rec.setValue("INSPECTION_AUDIT_STR","");
	rec.setValue("INSPECTION_CONFIRM_STR","");
	rec.setValue("INSPECTION_ENTER_STR","");
	
	//检验员电子签名
	//out.println(tsjy_inspection_info.getStringValue("check_op_id",0));
	rec.setValue("INSPECTION_OP_PICTURE", check_op_img!=null?check_op_img:"");			
 	//审核电子签名
	rec.setValue("INSPECTION_AUDIT_PICTURE", examine_op_img!=null?examine_op_img:"");	// Util.splitString(cfd.tsjy.JYUtil.getAuditName(id),"||")[0]
	//签发(批准)电子签名
	rec.setValue("INSPECTION_CONFIRM_PICTURE", issue_op_img!=null?issue_op_img:"");	// Util.splitString(cfd.tsjy.JYUtil.getConfirmName(id),"||")[0]                                                                                 	                                                             
	//编制电子签名
	rec.setValue("INSPECTION_ENTER_PICTURE", enter_op_img!=null?enter_op_img:"");	// Util.splitString(cfd.tsjy.JYUtil.getEnterName(id),"||")[0]
	
	// 电子签名
	ReportPerDao reportPerDao = new ReportPerDao();	// 报告配置
	ImageTool imageTool = new ImageTool();	// 图片处理工具
	for(ReportItem reportItem : reportItemList){
		String is_inspect_man = reportItem.getIs_inspect_man();		// 是否单独检验员（1：是 2：否）
		String is_audit_man = reportItem.getIs_audit_man();			// 是否单独审核人（1：是 2：否）
		String is_eval_pic_man = reportItem.getIs_eval_pic_man();	// 是否单独评片人（1：是 2：否）
		
		if(is_inspect_man!=null && is_inspect_man.equals("1")){
			int page_index = StringUtil.isNotEmpty(reportItem.getPage_index())?Integer.parseInt(reportItem.getPage_index()):0;
			String item_name = "INSPECT_MAN_PTR"+page_index;
			String item_name_picture =  "INSPECT_MAN_PICTURE"+page_index;
			String item_value_id = reportPerDao.queryByInspectionInfoId(inspectionInfo.getId(), report.getId(), item_name);
			if(StringUtil.isNotEmpty(item_value_id)){
				rec.setValue(item_name,"");
				rec.setValue(item_name_picture,imageTool.getEmployeeImg(item_value_id));
			}
		}
		
		if(is_audit_man!=null && is_audit_man.equals("1")){
			int page_index = StringUtil.isNotEmpty(reportItem.getPage_index())?Integer.parseInt(reportItem.getPage_index()):0;
			String item_name = "AUDIT_MAN_PTR"+page_index;
			String item_name_picture =  "AUDIT_MAN_PICTURE"+page_index;
			String item_value_id = reportPerDao.queryByInspectionInfoId(inspectionInfo.getId(), report.getId(), item_name);
			if(StringUtil.isNotEmpty(item_value_id)){
				rec.setValue(item_name,"");
				rec.setValue(item_name_picture, imageTool.getEmployeeImg(item_value_id));
			}else{
				// 如果没有选择则默认为报告审核人
				rec.setValue(item_name,"");
				rec.setValue(item_name_picture, imageTool.getEmployeeImg(inspectionInfo.getExamine_id()));
			}
		}
		
		if(is_eval_pic_man!=null && is_eval_pic_man.equals("1")){
			int page_index = StringUtil.isNotEmpty(reportItem.getPage_index())?Integer.parseInt(reportItem.getPage_index()):0;
			String item_name = "EVAL_PIC_MAN_PTR"+page_index;
			String item_name_picture =  "EVAL_PIC_DATE"+page_index;
			String item_value_id = reportPerDao.queryByInspectionInfoId(inspectionInfo.getId(), report.getId(), item_name);
			if(StringUtil.isNotEmpty(item_value_id)){
				rec.setValue(item_name,"");
				rec.setValue(item_name_picture, imageTool.getEmployeeImg(item_value_id));
			}
		}
	}
	
	
	//将下次检验日期按照yyyy-MM-dd 方式显示
	 String newdate = "";
	//System.out.println("------------------"+tsjy_inspection_info.getFormatStringValue("last_check_time",0,"yyyy-MM-dd"));
	if(inspectionInfo.getLast_check_time()!=null){
		String last_check_time = DateUtil.getDate(inspectionInfo.getLast_check_time());
		if(StringUtil.isNotEmpty(last_check_time)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getLast_check_time()); 
		rec.setValue("LAST_INSPECTION_DATE",newdate);
		}
		
	}
	//将签发日期按照yyyy年MM月dd日方式显示
	if(inspectionInfo.getIssue_Date() !=null){
		String confirm_date = DateUtil.getDate(inspectionInfo.getIssue_Date());
		if(StringUtil.isNotEmpty(confirm_date)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getIssue_Date()); 
			rec.setValue("CONFIRM_DATE",newdate);
		}
	}
	//将审核日期按照yyyy年MM月dd日方式显示
	if(inspectionInfo.getExamine_Date() != null){
		String examine_date = DateUtil.getDate(inspectionInfo.getExamine_Date());
		if(StringUtil.isNotEmpty(examine_date)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getExamine_Date()); 
			rec.setValue("AUDIT_DATE",newdate);	
		}
	}
	//将编制日期按照yyyy年MM月dd日方式显示
		if(inspectionInfo.getEnter_time() != null){
			String enter_date = DateUtil.getDate(inspectionInfo.getEnter_time());
			if(StringUtil.isNotEmpty(enter_date)){
				newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getEnter_time()); 
				rec.setValue("DRAFT_DATE",newdate);	
			}
		}

	String ins="";
	//将检验日期按照yyyy年MM月dd日方式显示
	if(inspectionInfo.getAdvance_time() != null){
		String advance_time = DateUtil.getDate(inspectionInfo.getAdvance_time());
		ins= DateUtil.getDateTime("yyyyMMdd", inspectionInfo.getAdvance_time());
		if(StringUtil.isNotEmpty(advance_time)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getAdvance_time()); 
			if(StringUtil.isEmpty(inspect_date)){
				rec.setValue("INSPECT_DATE",newdate);
			}else{
				rec.setValue("INSPECT_DATE",inspect_date);
			}
			rec.setValue("INSPECTION_DATE",newdate);
			rec.setValue("INSPECTION_DATE_1",newdate);
			rec.setValue("INSPECTION_DATE_2",newdate);
			rec.setValue("INSPECTION_DATE__1",newdate);
			rec.setValue("INSPECTION_DATE__2",newdate);
			rec.setValue("INSPECTION_DATE_TOP",newdate );	
		}
	}
	
	
	// 报告书使用单位
	if(inspectionInfo.getReport_com_name() != null){
		rec.setValue("COM_NAME", 
				TS_Util.nullToString(inspectionInfo.getReport_com_name()).getBytes("GB2312") );
		com_name = TS_Util.nullToString(inspectionInfo.getReport_com_name());	
	}
	
	if(StringUtil.isNotEmpty(com_name)){
		rec.setValue("COM_NAME", com_name);
	}	
	
	//处理图片信息
	Map<String,Object> picMap 
		= (Map<String,Object>)request.getAttribute("PICTURE");
	for (String key : picMap.keySet()) {
		String keyid = key.substring(0,key.length()-1);
		rec.setValue(keyid, "");
		if(key.endsWith("P")){
			rec.setValue(key,  (byte[])picMap.get(key) );
		}else{
			rec.setValue(key, 
					TS_Util.nullToString(picMap.get(key)).getBytes("GB2312") );
		}
	}
	
	// 处理单独检验、审核人员电子签名
	Map<String, Object> page_check_imgMap = (Map<String, Object>) request
			.getAttribute("PAGE_CHECK_IMAGES");
	for (String key : page_check_imgMap.keySet()) {
		if(key.startsWith("INSPECT_MAN_PICTURE")){
			String page_index = key.substring("INSPECT_MAN_PICTURE".length());
			String item_name = "INSPECT_MAN_PTR" + page_index;
			rec.setValue(item_name, "");	// 设置检验人员姓名不显示
			//rec.setValue("INSPECT_DATE"+page_index, DateUtil.getDateTime("yyyy-MM-dd", new Date()));	// 默认检验日期为系统当前日期
		}
		if(key.startsWith("AUDIT_MAN_PICTURE")){
			String item_name = "AUDIT_MAN_PTR" + key.substring("AUDIT_MAN_PICTURE".length());
			rec.setValue(item_name, "");	// 设置审核人员姓名不显示
		}
		if(key.startsWith("EVAL_PIC_MAN_PICTURE")){
			String page_index = key.substring("EVAL_PIC_MAN_PICTURE".length());
			String item_name = "EVAL_PIC_MAN_PTR" + page_index;
			rec.setValue(item_name, "");	// 设置评片人员姓名不显示
			//rec.setValue("EVAL_PIC_DATE"+page_index, DateUtil.getDateTime("yyyy-MM-dd", new Date()));	// 默认评片日期为系统当前日期
		}
		rec.setValue(key, page_check_imgMap.get(key));	// 设置显示人员电子签名
	}

	if(StringUtil.isEmpty(device_registration_code)){
		if("4".equals(big_class) && inspectionInfo.getReport_sn().contains("QA")){
			device_registration_code = deviceDocument.getDevice_code();
			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_code());	// 设备代码
		}else{
			device_registration_code = deviceDocument.getDevice_registration_code(); 
			rec.setValue("DEVICE_REGISTRATION_CODE", deviceDocument.getDevice_registration_code());	// 设备注册代码
		}
	}
    
	if(device_registration_code!=null){
		//System.out.println("二维码+======二维码========二维码"+imageTool.setCodeToByteArray(device_registration_code,400,400));
		rec.setValue("DTHGZ", imageTool.setCodeToByteArray(report_sn,400,400));
	}
	
	if(StringUtil.isNotEmpty(report_sn)){
		rec.setValue("DT_DZYZ", imageTool.setCodeToDZYZByteArray(report_sn, 400, 400));
	}
	
	// 罐车检验报告封面二维码（查询使用单位、车牌、报告编号、咨询电话18190875982）
	if(deviceDocument.getId() != null){
		rec.setValue("GC_FM_EWM", imageTool.setGCCodeToByteArray(deviceDocument.getId(),400,400));
	}

	// 报告书编号
	rec.setValue("REPORT_SN", report_sn);

	//Entity checkEn = new Entity();
	//String checkSql = "select to_date('2010-07-30','yyyy-mm-dd') - to_date('"+tsjy_inspection_info.getFormatStringValue("advance_time",0,"yyyy-MM-dd") +"','yyyy-mm-dd') as flag from dual" ;
	//checkEn.excuteQuery(checkSql);
	//if(checkEn.getIntegerValue("flag",0) <= 0)
	//rec.setValue("JGHZH", "TS7110306-2019");
	
	//rec.setValue("JGHZH", StringUtil.isNotEmpty(jghzh)?jghzh:"");	// 检验机构核准证号
	mrds.addRow(rec);
	
    //查找报告
    //Entity reportsEn = new Entity();
    //String sql_report = "select * from tsjy_reports where id=" + report_type;
    //reportsEn.excuteQuery(sql_report);
    // 创建报表引擎对象，指定报表根目录，传递结果集，绑定报表
    MREngine engine = new MREngine(pageContext, StringUtil.isNotEmpty(report.getRootpath())?report.getRootpath():"");    
    engine.setUnicodeOption(1);
    // 用MRDataSet对象为报表提供数据集：
    engine.addMRDataSet(StringUtil.isNotEmpty(report.getMrdataset())?report.getMrdataset():"", mrds);
    engine.addReport(StringUtil.isNotEmpty(report.getReport_file())?report.getReport_file():"");//报表文件
    engine.bind();
    //String pros = "Pages=1,3";
    //engine.exportFile("E:\\"+inspectionInfo.getReport_sn()+".pdf",pros);
%>
<html>
<head>
<title> 报表信息 </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="app/flow/export/report_export_pdf.js"></script>
<script language="javascript">
	var com_name = "<%=com_name%>";
	var http_request= false;
	
	$(function() {
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
		setReports();
	})
	
	//设置报表属性
	function setReports()
	{
		//MRViewer.ShowToolbar=false;
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
		
		//设置显示页
		var ss = "<%=report_item%>".split(",");
		var ss_length = ss.length;
		var status = false;
		for(var i=0;i<pagecount;i++){
			for(var j=0;j<ss_length;j++){
				if((i+1)==ss[j])
				{
					status = true;
					page = report.Pages(i);
					page.Prop("Visible") = "True";
					break;
				}
				else
				{
					try{
						page = report.Pages(i);
						page.Prop("Visible") = "False";
					} catch(e){}
				}
			}
		}

		//alert(ss_length+"-----"+(ss_length%2)+"-----"+(ss_length%2==1))
		//如果为基数页的话，自动在页后添加空白页
		if(ss_length%2==1)
		{
			//alert();
			//report.AddPage();
		}
		if(!status)
		{
			alert("报告模板上没有对应的索引！");
			api.close();	// window.close();
		}
	
		parseValueReports();
		MRViewer.Preview();
		<%
			if("yes".equals(printout)){	
				String realPath1 = request.getSession().getServletContext().getRealPath("upload");
				String[] id = realPath1.split("\\\\");
				String realPath="";
				for(int i=0;i<id.length;i++){
					if(StringUtil.isNotEmpty(realPath)){
					realPath = realPath +"/"+id[i];
					}else{
						realPath = id[0];
					}
				}
		%>
		//报告导出pdf，上传pdf并盖章
				var report_sn = "<%=inspectionInfo.getReport_sn()%>";
				var realPath = "<%=realPath%>";
				var time = new Date( ); //获得当前时间
				var m = time.getMonth();
				var d = time.getDate();
				if(m<10){
					m = "0"+(m+1);
				}
				if(d<10){
					d = "0"+d;
				}
				var day = <%=ins%>;//检验日期
				var pdfPath = realPath+"/"+day
							+"/"+report_sn+"/"+report_sn+".pdf";
				
				doPrintreport(day,"<%=inspectionInfo.getId()%>",pdfPath,report_sn);	
				MRViewer_AfterPrint(1);
		<%
			}
		%>
	}
	
	function parseValueReports()
	{
		//把每一个是Memo的对象中间包含“”的并且不是总页数的控件对象都设置为可以提交的状态,提交KEY = memo中的字段名
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
		var ss = '${REPORTPARA.report_item}'.split(",");
		for(var i=0;i<pagecount;i++){
			try{
				page = report.Pages(i);
				objCount = page.ObjectCount;
				for(var j=0;j<objCount;j++){
					obj = page.Objects(j); 
					if(obj.Prop("memo").indexOf("[BGDS.\"COM_NAME\"]")!=-1){
						if(com_name.indexOf("尊宴餐饮娱乐有限公司")!=-1){
							obj.Prop("Memo") = "成都珺龍尊宴餐饮娱乐有限公司";
						}else{
							obj.Prop("Memo") = com_name;
						}
					}
				}
			} catch(e){}
		}
	}
	
	
	function MRViewer_AfterPrint(flag)
	{
	
		if(flag==undefined || flag =="" ) {
			return;
		}
		parent.left._opid<%=request.getParameter("opid")%>.innerHTML="<img src='k/kui/images/icons/16/check.png' border='0' >";
		subP();	// 打印完后写数据库记录打印操作
		//alert("导出盖章成功！")
		
	}
	
	function subP()
	{
		
		formObj.action="report/query/insertExportRecord.do?id=<%=inspectionInfo.getId() %>&report_id=<%=request.getSession().getAttribute("report_id") %>&isLast=yes&op_type=";
		formObj.submit();
		//alert("insertPrintRecord end.");
		
	}
	
	function showBB(){
		$("#sssss").show();
	}
	
	$(window).load(function() {
		$("#MRViewer").append('<param name="wmode" value="transparent" />');
	});
</script>
</head>
<body>
<form name="formObj" method="post" action="">
</form>
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%"
	 simple="false" 
	 invisiblebuttons="Print,Export,Close,PrintPopup,ExportPopup,Find,FindNext" 
	 postbackurl=""  canedit="false"   wrapparams="true"  />
</div>
</body>
</html>