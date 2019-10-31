<%@page import="com.lsts.device.bean.DeviceDocument"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.inspection.bean.ReportItemValue"%>
<%@ page import="com.lsts.report.bean.ReportItem"%>
<%@page import="com.khnt.pub.fileupload.bean.Attachment"%>
<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.lsts.inspection.dao.ReportPerDao"%>
<%@page import="com.lsts.common.dao.AttachmentsDao"%>
<%@page import="java.io.IOException"%>
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
	String modle = request.getParameter("modle");	
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
	// 报告业务信息
	InspectionInfo inspectionInfo = (InspectionInfo)request.getSession().getAttribute("inspectionInfo");
	// 设备信息
	DeviceDocument device = (DeviceDocument)request.getSession().getAttribute("device");
	List<ReportItemValue> reportItemValueList = (List<ReportItemValue>)request.getSession().getAttribute("reportItemValueList");
	String print_copies = request.getParameter("printcopies");	// 打印份数
	String printer_name = (String)request.getSession().getAttribute("printer_name");	// 打印机名称 
	String report_sn = inspectionInfo.getReport_sn();
	String report_item = inspectionInfo.getReport_item();
	String construction_units_name = "";
	String security_op = ""; 	// 安全管理人员
	String security_tel = ""; 	// 安全管理联系电话

    // 构造DataRecord、MRDataSet对象
    MRDataSet mrds = new MRDataSet();
    DataRecord rec = new DataRecord();
    for (int i = 0; i < reportItemValueList.size(); i++)
    {
    	ReportItemValue reportItemValue = reportItemValueList.get(i);
    	String item_value = "";
		if ("ZLSCQK".equals(reportItemValue.getItem_name()) || "LAST_CHECK_PROBLEMS".equals(reportItemValue.getItem_name())
				|| reportItemValue.getItem_name().contains("REMARKS")
				|| reportItemValue.getItem_name().contains("INSPECTION_CONCLUSION")
				|| reportItemValue.getItem_name().contains("JLBG_WTJCLYJ")
				|| reportItemValue.getItem_name().contains("JLBG_FXWT")
				 || reportItemValue.getItem_name().contains("DTQDP30012")) {
			item_value = TS_Util.nullToString(reportItemValue.getItem_value());
		} else {
			item_value = TS_Util.nullToString(reportItemValue.getItem_value()).replaceAll("\r|\n", "");
		}
		
		if ("SECURITY_OP".equals(reportItemValue.getItem_name())) {
			security_op = TS_Util.nullToString(item_value);
 		}
		if ("SECURITY_TEL".equals(reportItemValue.getItem_name())) {
			security_tel = TS_Util.nullToString(item_value);
 		}
    	
		if ("CONSTRUCTION_UNITS_NAME".equals(reportItemValue.getItem_name())) {
 			construction_units_name = TS_Util.nullToString(item_value);
 		}
    	rec.setValue(reportItemValue.getItem_name(), item_value); 
    }
    
 	// 报告书编号
 	rec.setValue("REPORT_SN", report_sn);
 	if(StringUtil.isEmpty(security_op)){
 		rec.setValue("SECURITY_OP", device.getSecurity_op()); 
 	}
 	if(StringUtil.isEmpty(security_tel)){
 		rec.setValue("SECURITY_TEL", device.getSecurity_tel()); 
 	}

	mrds.addRow(rec);	
    
	// 创建报表引擎对象，指定报表根目录，传递结果集，绑定报表
    MREngine engine = new MREngine(pageContext, "app/flow/report/temple");    
    engine.setUnicodeOption(1);
    // 用MRDataSet对象为报表提供数据集：
    engine.addMRDataSet("BGDS", mrds);
    System.out.println("@@@@@@@@@@"+modle);
    engine.addReport(modle);//报表文件
    engine.bind();
    
    
%>
<html>
<head>
<title> 报表信息 </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<%@include file="/k/kui-base-list.jsp"%>
<script language="javascript">
var construction_units_name = "<%=construction_units_name%>";
var report_item = "<%=report_item%>";

$(function() {
	var height = $(window).height()-$('.toolbar-tm').height();
	$("#scroll-tm").css({height:height});
	setReports();
})
function setReports()
{
	//MRViewer.ShowToolbar=false;
	report = MRViewer.Report;
	pagecount = MRViewer.PageCount;
	
	
	parseValueReports();
	MRViewer.Preview();
	<%
		if("yes".equals(printout)){				
	%>
			doPrintreport();
			MRViewer_AfterPrint();
	<%
		}
	%>
}

function parseValueReports()
{
	//把每一个是Memo的对象中间包含“”的并且不是总页数的控件对象都设置为可以提交的状态,提交KEY = memo中的字段名
	report = MRViewer.Report;
	pagecount = MRViewer.PageCount;
	var ss = report_item.split(",");
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
				}else if(obj.Prop("memo").indexOf("[BGDS.\"CONSTRUCTION_UNITS_NAME\"]")!=-1){
					if(construction_units_name.indexOf("江苏")!=-1 && construction_units_name.indexOf("祥电梯有限公司")!=-1){
						obj.Prop("Memo") = "江苏燊祥电梯有限公司";
					}else{
						obj.Prop("Memo") = construction_units_name;
					}
				}
			}
		} catch(e){}
	}
}
	
//打印报告
function doPrintreport()
{	
	MRViewer.PrintSetup(0,0,true,"",0,1,true,"<%=StringUtil.isNotEmpty(printer_name)?printer_name:""%>");
	MRViewer.Print(false);	// false不提示打印设置框，调用默认的			
	//subP();
}

function MRViewer_AfterPrint()
{
	//alert("print_afer_begin")
	//alert("print_afer")
	parent.left._opid<%=request.getParameter("opid")%>.innerHTML="<img src='k/kui/images/icons/16/check.png' border='0' >";	
	subP();	// 打印完后写数据库记录打印操作
	//alert("MRViewer_AfterPrint")
}

function subP()
{
	
	formObj.action="app/query/report_print_message.jsp?isLast=yes&modle=<%=modle%>";
	formObj.submit();
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
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%"
	 simple="false" 
	 invisiblebuttons="Print,Export,Close,PrintPopup,ExportPopup,Find,FindNext" 
	 postbackurl=""  canedit="false"   wrapparams="true"  />
</div>
</body>
</html>