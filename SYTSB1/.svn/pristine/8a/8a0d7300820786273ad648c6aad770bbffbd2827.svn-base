<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.inspection.bean.ReportItemRecord"%>
<%@ page import="com.lsts.report.bean.ReportItem"%>
<%@page import="com.khnt.pub.fileupload.bean.Attachment"%>
<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.lsts.inspection.dao.ReportPerDao"%>
<%@page import="com.lsts.common.dao.AttachmentsDao"%>
<%@page import="java.io.IOException"%>
<%@page import="com.lsts.device.bean.DeviceDocument"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming" %>
<%@ page import="com.ming.webreport.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.khnt.utils.StringUtil" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="com.lsts.ImageTool"%>
<%@ page import="com.lsts.inspection.bean.*" %>
<%@ page import="com.lsts.report.bean.*" %>
<%@ page import="oracle.sql.*" %>
<%@ page import="util.*"%>
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
		alert("打印完成！");
		//parent.left.printAll();
	</script>
<%
	//return;
	}
%>
<%
    // 报告检验项目表信息
	List<ReportItemRecord> reportItemRecordList = (List<ReportItemRecord>)request.getSession().getAttribute("reportItemRecordList");
	// 报告页信息
	//List<ReportItem> reportItemList = (List<ReportItem>)request.getSession().getAttribute("reportItemList");
	// 业务信息
    InspectionInfo inspectionInfo = (InspectionInfo)request.getSession().getAttribute("inspectionInfo");
 	// 业务信息
    DeviceDocument deviceDocument = (DeviceDocument)request.getSession().getAttribute("deviceDocument");
	// 报告信息
    Report report = (Report)request.getSession().getAttribute("report");
 	String report_name = report.getReport_name().trim();
	String report_code = report.getReport_code().trim();
    String ysjl_item = inspectionInfo.getYsjl_item();
    String report_sn = inspectionInfo.getReport_sn();
    String com_name = "";
    String device_area_name = "";
    String device_street_name = "";
    String xsqts = inspectionInfo.getXsqts();
	
    // 构造DataRecord、MRDataSet对象
    MRDataSet mrds = new MRDataSet();
    DataRecord rec = new DataRecord();
    
    // 检验项目表信息处理
    for (int i = 0; i < reportItemRecordList.size(); i++)
    {
    	ReportItemRecord reportItemRecord = reportItemRecordList.get(i);
    	if(reportItemRecord.getItem_name().equals("DRAFT_DATE")){
    		if(StringUtil.isNotEmpty(reportItemRecord.getItem_value())){
    			if(reportItemRecord.getItem_value().indexOf("/")!=-1){
    				cal.setTime(DateUtil.convertStringToDate("yyyy/MM/dd", reportItemRecord.getItem_value()));
    			}else{
    				cal.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", reportItemRecord.getItem_value()));
    			}
        		String newdate = DateUtil.getDateTime("yyyy年MM月dd日",cal.getTime()); 
        		rec.setValue("DRAFT_DATE",newdate);
    		}
    	}else if("COM_NAME".equals(reportItemRecord.getItem_name())){
			com_name = TS_Util.nullToString(reportItemRecord.getItem_value());
		}else if("DEVICE_AREA_NAME".equals(reportItemRecord.getItem_name())){
			device_area_name = TS_Util.nullToString(reportItemRecord.getItem_value());
		}else if("DEVICE_STREET_NAME".equals(reportItemRecord.getItem_name())){
			device_street_name = TS_Util.nullToString(reportItemRecord.getItem_value());
		}else if(reportItemRecord.getItem_name().equals("INSPECTION_DATE_END")){
    		if(StringUtil.isNotEmpty(reportItemRecord.getItem_value())){
    			if(reportItemRecord.getItem_value().indexOf("/")!=-1){
    				cal.setTime(DateUtil.convertStringToDate("yyyy/MM/dd", reportItemRecord.getItem_value()));
    			}else{
    				cal.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", reportItemRecord.getItem_value()));
    			}
        		String newdate = DateUtil.getDateTime("yyyy年MM月dd日",cal.getTime()); 
        		rec.setValue("INSPECTION_DATE_END",newdate);
    		}
    	}else{
    		if(reportItemRecord.getItem_value()==null){
    			rec.setValue(reportItemRecord.getItem_name(), ""); 
    		}else{
    			if("null".equals(reportItemRecord.getItem_value())){
    				rec.setValue(reportItemRecord.getItem_name(), ""); 
    			}else{
    				rec.setValue(reportItemRecord.getItem_name(), reportItemRecord.getItem_value()); 
    			}
    		}
    	}
    }
	
	// 人员电子签名处理
	byte[] check_op_img = (byte[])request.getAttribute("check_op_img");
	byte[] examine_op_img = (byte[])request.getAttribute("examine_op_img");
	byte[] issue_op_img = (byte[])request.getAttribute("issue_op_img");
	byte[] enter_op_img = (byte[])request.getAttribute("enter_op_img");
	// 校核人员手写签名图片处理
	byte[] exam_op_img = (byte[])request.getAttribute("exam_op_img");
	
	// 设置人员姓名不可见，电子签名可见
	rec.setValue("INSPECTION_OP_STR", "");
	rec.setValue("INSPECTION_AUDIT_STR","");
	rec.setValue("INSPECTION_CONFIRM_STR","");
	rec.setValue("INSPECTION_ENTER_STR","");
	
	// 检验员电子签名
	rec.setValue("INSPECTION_OP_PICTURE", check_op_img!=null?check_op_img:"");			
 	// 审核电子签名
	rec.setValue("INSPECTION_AUDIT_PICTURE", examine_op_img!=null?examine_op_img:"");
	// 签发(批准)电子签名
	rec.setValue("INSPECTION_CONFIRM_PICTURE", issue_op_img!=null?issue_op_img:"");	                                                                              	                                                             
	// 编制电子签名
	rec.setValue("INSPECTION_ENTER_PICTURE", enter_op_img!=null?enter_op_img:"");	
	// 校核人员手写签字图片
	rec.setValue("EXAMINE_NAME_1", exam_op_img!=null?exam_op_img:"");	
	
	//将下次检验日期按照yyyy年MM月dd日方式显示
	 String newdate = "";
	if(inspectionInfo.getLast_check_time()!=null){
		String last_check_time = DateUtil.getDate(inspectionInfo.getLast_check_time());
		if(StringUtil.isNotEmpty(last_check_time)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getLast_check_time()); 
			rec.setValue("LAST_INSPECTION_DATE",newdate);
		}
	}
	//将批准日期按照yyyy年MM月dd日方式显示
	if(inspectionInfo.getIssue_Date() !=null){
		String confirm_date = DateUtil.getDate(inspectionInfo.getIssue_Date());
		if(StringUtil.isNotEmpty(confirm_date)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getIssue_Date()); 
			rec.setValue("CONFIRM_DATE",newdate);
		}
	}
	//将审批日期按照yyyy年MM月dd日方式显示
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
	
	//将检验日期按照yyyy年MM月dd日方式显示
	if(inspectionInfo.getAdvance_time() != null){
		String advance_time = DateUtil.getDate(inspectionInfo.getAdvance_time());
		if(StringUtil.isNotEmpty(advance_time)){
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getAdvance_time()); 
			rec.setValue("INSPECTION_DATE",newdate);
			rec.setValue("INSPECTION_DATE＿1", newdate);
			rec.setValue("INSPECTION_DATE＿2", newdate);
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
	if(StringUtil.isNotEmpty(report_sn)){
		rec.setValue("REPORT_SN", report_sn);
	}
	
	if(StringUtil.isEmpty(device_area_name)){
		device_area_name = deviceDocument.getDevice_area_name();
	}
	if(StringUtil.isEmpty(device_street_name)){
		device_street_name = deviceDocument.getDevice_street_name();
	}
	rec.setValue("DEVICE_AREA_NAME", device_area_name);
	rec.setValue("DEVICE_STREET_NAME", device_street_name);
	

	// 检验机构核准证号
	//rec.setValue("P_JGHZH","TS7110306-2019");	
	rec.setValue("TotalP", StringUtil.isNotEmpty(ysjl_item)?ysjl_item.split(",").length:0);
	mrds.addRow(rec);

    // 创建报表引擎对象，指定报表根目录，传递结果集，绑定报表
    MREngine engine = new MREngine(pageContext, StringUtil.isNotEmpty(report.getRootpath())?report.getRootpath():"");    
    engine.setUnicodeOption(1);
    // 用MRDataSet对象为报表提供数据集：
    engine.addMRDataSet(StringUtil.isNotEmpty(report.getMrdataset())?report.getMrdataset():"", mrds);
    engine.addReport(StringUtil.isNotEmpty(report.getYsjl_file())?report.getYsjl_file():"");	// 原始记录报表文件
    engine.bind();
    
    
%>
<html>
<head>
<title> 报表信息 </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<%@include file="/k/kui-base-list.jsp"%>
<script language="javascript">
	var com_name = "<%=com_name%>";
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
		var xsqts = "<%=xsqts%>";
		var reportcode = "<%=report_code%>";
		var reportname = "<%=report_name%>";
		var reportcodes = "07,08,11,12,13,14,15,16";
		var ss = "<%=ysjl_item%>".split(",");
		var ss_length = ss.length;
		var status = false;
		if(ss_length==1 && xsqts == "1" && reportname.indexOf("九寨") != -1){
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
		}else{
			for(var i=0;i<pagecount;i++){
				status = true;
				page = report.Pages(i);
				page.Prop("Visible") = "True";			
				if(reportcodes.indexOf(reportcode) == -1){
					if(xsqts == "0"){
						if(reportname.indexOf("九寨") != -1){
							if(reportname.indexOf("杂物电梯定期检验") != -1 || reportname.indexOf("自动扶梯与自动人行道定期检验") != -1){
								page = report.Pages(i);
								page.Prop("Visible") = "True";		
							}else{
								if(i>=(pagecount-1)){
									page = report.Pages(i);
									page.Prop("Visible") = "False";
								}
							}
						}else{
							if(reportname.indexOf("2号修改单") == -1 && reportname.indexOf("西藏") == -1 && reportname.indexOf("新疆") == -1){
								if(i>=(pagecount-2)){
									page = report.Pages(i);
									page.Prop("Visible") = "False";
								}
							}
						}
					}else if(xsqts == "1"){
						if(reportname.indexOf("九寨") == -1){
							if(i>=(pagecount-1)){
								page = report.Pages(i);
								page.Prop("Visible") = "False";
							}
						}else{
							page = report.Pages(i);
							page.Prop("Visible") = "True";		
						}		
					}	
				}
			}
		}
		if(!status)
		{
			alert("报告模板上没有对应的索引！");
			api.close();	// window.close();
		}
	
		parseValueReports();
		MRViewer.Preview();
	}
	
	function parseValueReports()
	{
		//把每一个是Memo的对象中间包含“”的并且不是总页数的控件对象都设置为可以提交的状态,提交KEY = memo中的字段名
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
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
	function showBB(){
		$("#sssss").show();
	}
	
	$(window).load(function() {
		$("#MRViewer").append('<param name="wmode" value="transparent" />');
	});
</script>
</head>
<body >
<form name="formObj" method="post" action="">
</form>
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"  height="100%" simple="false" invisiblebuttons="Print,Export,Close,PrintPopup,ExportPopup,Find,FindNext"  postbackurl="" canedit="false" wrapparams="true"  />
</div>
</body>
</html>