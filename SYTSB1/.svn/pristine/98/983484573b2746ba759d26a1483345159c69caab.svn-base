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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%
	Calendar cal = Calendar.getInstance();
    // 报告检验项目表信息
	List<ReportItemRecord> reportItemRecordList = (List<ReportItemRecord>)request.getSession().getAttribute("reportItemRecordList");
	// 报告页信息
	//List<ReportItem> reportItemList = (List<ReportItem>)request.getSession().getAttribute("reportItemList");
	// 业务信息
    InspectionInfo inspectionInfo = (InspectionInfo)request.getSession().getAttribute("inspectionInfo");
 	// 业务信息
    DeviceDocument deviceDocument = (DeviceDocument)request.getSession().getAttribute("deviceDocument");
	// 报告信息
	Report report = (Report) request.getSession().getAttribute("report");
	
	String id = inspectionInfo.getId();
	String report_id = inspectionInfo.getReport_type();
	String device_id = inspectionInfo.getFk_tsjc_device_document_id();
	
	String report_name = report.getReport_name().trim();
	String report_code = report.getReport_code().trim();
	String ysjl_item = inspectionInfo.getYsjl_item();
	String report_sn = inspectionInfo.getReport_sn();
	String com_name = "";
	String device_type = StringUtil.isNotEmpty(deviceDocument.getDevice_sort_code())
			? deviceDocument.getDevice_sort_code().substring(0, 1)
			: (StringUtil.isNotEmpty(deviceDocument.getDevice_sort())
					? deviceDocument.getDevice_sort().substring(0, 1) : "");
	String device_use_place = "";
	String security_op = "";
	String device_area_name = "";
	String device_street_name = "";
	String xsqts = inspectionInfo.getXsqts();

	// 构造DataRecord、MRDataSet对象
	MRDataSet mrds = new MRDataSet();
	DataRecord rec = new DataRecord();

	// 检验项目表信息处理
	for (int i = 0; i < reportItemRecordList.size(); i++) {
		ReportItemRecord reportItemRecord = reportItemRecordList.get(i);
		if ("COM_NAME".equals(reportItemRecord.getItem_name())) {
			com_name = TS_Util.nullToString(reportItemRecord.getItem_value());
		} else if ("DEVICE_AREA_NAME".equals(reportItemRecord.getItem_name())) {
			device_area_name = TS_Util.nullToString(reportItemRecord.getItem_value());
		} else if ("DEVICE_STREET_NAME".equals(reportItemRecord.getItem_name())) {
			device_street_name = TS_Util.nullToString(reportItemRecord.getItem_value());
		} else if (reportItemRecord.getItem_name().equals("INSPECTION_DATE_END")) {
			if (StringUtil.isNotEmpty(reportItemRecord.getItem_value())) {
				if (reportItemRecord.getItem_value().indexOf("/") != -1) {
					cal.setTime(DateUtil.convertStringToDate("yyyy/MM/dd", reportItemRecord.getItem_value()));
				} else {
					cal.setTime(DateUtil.convertStringToDate("yyyy-MM-dd", reportItemRecord.getItem_value()));
				}
				String newdate = DateUtil.getDateTime("yyyy-MM-dd", cal.getTime());
				rec.setValue("INSPECTION_DATE_END", newdate);
			}
		} else {
			rec.setValue(reportItemRecord.getItem_name(), reportItemRecord.getItem_value());
		}
	}
	
	//处理图片信息
  	Map<String,Object> picMap 
  		= (Map<String,Object>)request.getSession().getAttribute("PICTURE");
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

	// 人员电子签名处理
	byte[] check_op_img = (byte[]) request.getAttribute("check_op_img");
	byte[] examine_op_img = (byte[]) request.getAttribute("examine_op_img");
	byte[] issue_op_img = (byte[]) request.getAttribute("issue_op_img");
	byte[] enter_op_img = (byte[]) request.getAttribute("enter_op_img");
	// 校核人员手写签名图片处理
	byte[] exam_op_img = (byte[]) request.getAttribute("exam_op_img");

	// 设置人员姓名不可见，电子签名可见
	rec.setValue("INSPECTION_OP_STR", "");
	rec.setValue("INSPECTION_AUDIT_STR", "");
	rec.setValue("INSPECTION_CONFIRM_STR", "");
	rec.setValue("INSPECTION_ENTER_STR", "");

	// 检验员电子签名
	rec.setValue("INSPECTION_OP_PICTURE", check_op_img != null ? check_op_img : "");
	// 审核电子签名
	rec.setValue("INSPECTION_AUDIT_PICTURE", examine_op_img != null ? examine_op_img : "");
	// 签发(批准)电子签名
	rec.setValue("INSPECTION_CONFIRM_PICTURE", issue_op_img != null ? issue_op_img : "");
	// 编制电子签名
	rec.setValue("INSPECTION_ENTER_PICTURE", enter_op_img != null ? enter_op_img : "");
	// 校核人员手写签字图片
	rec.setValue("EXAMINE_NAME_1", exam_op_img != null ? exam_op_img : "");

	//将检验日期按照yyyy年MM月dd日方式显示
	String newdate = "";
	if (inspectionInfo.getAdvance_time() != null) {
		String advance_time = DateUtil.getDate(inspectionInfo.getAdvance_time());
		if (StringUtil.isNotEmpty(advance_time)) {
			newdate = DateUtil.getDateTime("yyyy-MM-dd", inspectionInfo.getAdvance_time());
			rec.setValue("INSPECTION_DATE", newdate);
			rec.setValue("INSPECTION_DATE＿1", newdate);
			rec.setValue("INSPECTION_DATE＿2", newdate);
			rec.setValue("INSPECTION_DATE_TOP", newdate);
		}
	}
	
	//将校核日期按照yyyy年MM月dd日方式显示
	if(inspectionInfo.getConfirm_Date() != null){
		String confirm_date = DateUtil.getDate(inspectionInfo.getConfirm_Date());
		if(StringUtil.isNotEmpty(confirm_date)){			
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getConfirm_Date()); 
			rec.setValue("EXAMINE_DATE",newdate);	
		}
	}

	// 报告书使用单位
	if (inspectionInfo.getReport_com_name() != null) {
		rec.setValue("COM_NAME", TS_Util.nullToString(inspectionInfo.getReport_com_name()).getBytes("GB2312"));
		com_name = TS_Util.nullToString(inspectionInfo.getReport_com_name());
	}

	if (StringUtil.isNotEmpty(com_name)) {
		rec.setValue("COM_NAME", com_name);
	}
	if (StringUtil.isNotEmpty(report_sn)) {
		rec.setValue("REPORT_SN", report_sn);
	}

	if (StringUtil.isEmpty(device_area_name)) {
		device_area_name = deviceDocument.getDevice_area_name();
	}
	if (StringUtil.isEmpty(device_street_name)) {
		device_street_name = deviceDocument.getDevice_street_name();
	}
	rec.setValue("DEVICE_AREA_NAME", device_area_name);
	rec.setValue("DEVICE_STREET_NAME", device_street_name);

	// 检验机构核准证号
	//rec.setValue("P_JGHZH", "TS7110306-2019");
	rec.setValue("TotalP", StringUtil.isNotEmpty(ysjl_item) ? ysjl_item.split(",").length : 0);
	mrds.addRow(rec);

	// 创建报表引擎对象，指定报表根目录，传递结果集，绑定报表
	MREngine engine = new MREngine(pageContext,
			StringUtil.isNotEmpty(report.getRootpath()) ? report.getRootpath() : "app/flow/report/temple");
	engine.setUnicodeOption(1);
	// 用MRDataSet对象为报表提供数据集：
	engine.addMRDataSet(StringUtil.isNotEmpty(report.getMrdataset()) ? report.getMrdataset() : "BGDS", mrds);
	engine.addReport(StringUtil.isNotEmpty(report.getYsjl_file()) ? report.getYsjl_file() : ""); // 原始记录报表文件
	engine.bind();
%>
<head>
<title>修改原始记录</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<%@ include file="/k/kui-base-form.jsp"%>
<%@ include file="record.js.jsp"%>
<script language="javascript">
	var com_name = "<%=com_name%>";
	var device_use_place = "<%=device_use_place%>";
	var security_op = "<%=security_op%>";
	var device_type = "<%=device_type%>";
	var info_id = "<%=id%>";
	var device_id = "<%=device_id%>";
	var report_id = "<%=report_id%>";
	
	$(function() {
		 manager = $("#btn").ligerButton({
			click: function (){
		    }
		 });
		
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
		setReports();
		
		$('#save').click(function(){		
			$("#save").attr("disabled","disabled");
			$("#sub").attr("disabled","disabled");
			doSave();		
			var id = info_id;		
			var reportId = report_id;		
			var deviceId = device_id;		
			var flag = "${param.isSub}";
			if(flag=="no"){
				api.data.window.api.data.window.saveRecordDetail(id,reportId,deviceId);
				api.data.window.api.close();
				api.close();
			}else{
				api.data.window.saveRecordDetail(id,reportId);
				api.close();
			}
		});
		
		$('#sub').click(function(){
			$("#sub").attr("disabled","disabled");
			$("#save").attr("disabled","disabled");
			doSave();			
			var flag = "${param.isSub}";		
			if(flag=="no"){
				api.data.window.api.data.window.refreshGrid();
				api.data.window.api.close();
				api.close();
			}else{
				api.data.window.refreshGrid();
				api.close();
			}	
		});
		
		$('#cancel').click(function(){
			var flag = "${param.isSub}";
			if(flag=="no"){
				api.data.window.api.close();
				api.close();
			}else{
				api.close();
				
			}
		});
	})
	
	//设置报表属性
	function setReports()
	{
		MRViewer = document.all("MRViewer") ;
		MRViewer.Zoom(100);
		
		//MRViewer.ShowToolbar=false;
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
		
		//设置显示页
		var ss = "<%=ysjl_item%>".split(",");	
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
					if(obj!=null && obj.Prop("TypeName")=="Memo"){
						if(obj.Prop("memo").indexOf("\"")!=-1&&obj.Prop("memo").indexOf("TotalP")==-1)
						{
							obj.Prop("PostbackOption.Key") = obj.Prop("memo").split("\"")[1];
						}
					}
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
	
	//保存开始禁用按钮
	function disableButton(){
		//$.ligerDialog.alert(111);
	}
	//保存结束按钮可用
	function enableButton(){
		//$.ligerDialog.alert(222);
	}
</script>
</head>
<body>
		<div class="scroll-tm" style="overflow: hidden" id="sssss">
			<ming:MRViewer id="MRViewer" shownow="true" width="100%"
				height="100%" simple="false" canedit="true"
				invisiblebuttons="Close,PrintPopup,ExportPopup,Find,FindNext"
				postbackurl="" wrapparams="true" />
		</div>
		<div class="toolbar-tm">
			<div class="toolbar-tm-bottom" style="text-align: center;">
				<a id="sub" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">保存并关闭</span> <span
						class="l-button-icon l-icon-search"></span> </span> </a>
				<a id="save" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">保存</span> <span
						class="l-button-icon l-icon-save"></span> </span> </a>

				<a id="cancel" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">关闭</span> <span
						class="l-button-icon l-icon-cancel"></span> </span> </a>
			</div>
		</div>
	</body>
</html>