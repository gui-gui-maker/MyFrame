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
    //查找流程
    //String report_type = request.getParameter("report_type");
    //String id = request.getParameter("id");
    //Entity reports = (Entity) request.getAttribute("REPORTS");
	//Entity tsjy_inspection_info = (Entity) request.getAttribute("TSJY_INSPECTION_INFO");
	List<ReportItemValue> reportItemValueList = (List<ReportItemValue>)request.getSession().getAttribute("reportItemValueList");
	
	
	
    // 构造DataRecord、MRDataSet对象
    MRDataSet mrds = new MRDataSet();
    DataRecord rec = new DataRecord();
    for (int i = 0; i < reportItemValueList.size(); i++)
    {
    	ReportItemValue reportItemValue = reportItemValueList.get(i);
    	
    		rec.setValue(reportItemValue.getItem_name(), reportItemValue.getItem_value()); 
    	
    }
    
 
	
	
	
	

	mrds.addRow(rec);

	 MREngine engine = new MREngine(pageContext, "app/flow/report/temple");    
	    engine.setUnicodeOption(1);
	    // 用MRDataSet对象为报表提供数据集：
	    engine.addMRDataSet("BGDS", mrds);
	    engine.addReport("XXB.mrf");//报表文件
	    engine.bind();
    
%>
<html>
<head>
<title> 报表信息 </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<%@include file="/k/kui-base-list.jsp"%>
<script language="javascript">
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
	
		MRViewer.Preview();
		<%
			if("yes".equals(printout)){				
		%>
				doPrintreport();
		<%
			}
		%>
	}
	
	
</script>
</head>
<body style="margin: 0" onload="setReports()">
<form name="formObj" method="post" action="">
</form>
<table height="100%" width="100%" border=0 cellpadding=0 cellspacing=0 >
    <tr height="100%">
        <td valign="top">
            <ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%" simple="false" invisiblebuttons="Export,Close,PrintPopup,ExportPopup,Find,FindNext"  postbackurl="" canedit="false" wrapparams="true"  />
        </td>
    </tr>  
</table>
</body>
</html>