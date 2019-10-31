<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.khnt.base.Factory"%>
<%@page import="java.sql.*"%>
<%@ page import="com.ming.webreport.*"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	 
    String draw_template=request.getParameter("draw_template");
	String drawTemplate="app/flow/report/temple/"+draw_template;
	 
	
	MREngine engine = new MREngine(pageContext);
	
	engine.addReport(drawTemplate);
 
	engine.bind();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>

<HEAD>

<TITLE></TITLE>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {

    var height = $(document.body).height() - $('#button').height();
	$('#reportTable').height(height);
	$('#MRViewer').height(height);

	$("#cancel").click(function(){
	api.close(); 
	});
	$("#print").click(function(){
	doPrintreport();
	});
	});
	
	//打印报告
	function doPrintreport()
	{
		var MRViewer=document.all("MRViewer");
		MRViewer.PrintSetup(0,0,true,"",0,1,true);
		MRViewer.Print(false);	// false不提示打印设置框，调用默认的
		MRViewer_AfterPrint('1');
	}
	
</script>
</HEAD>
<BODY style="margin: 0">
<table id="reportTable" width="100%" border=0 cellpadding=0 cellspacing=0 >
				<tr height="100%">
					<td valign="top"><ming:MRViewer id="MRViewer" shownow="true"
							width="100%" height="100%" simple="false" canedit="true"
							invisiblebuttons="Close,PrintPopup,ExportPopup,Find,FindNext"
							postbackurl="" wrapparams="true" /></td>
				</tr>
			</table>
			<div id="button" class="toolbar-tm">
		<div class="toolbar-tm-bottom" style="text-align: center;">
			
			<a id="cancel" class="l-button-warp l-button" ligeruiid="Button1012" onclick="close()">
				<span class="l-button-main l-button-hasicon"> <span
					class="l-button-text">关闭</span> <span
					class="l-button-icon l-icon-cancel"></span>
			</span>
			</a>
		
			
		</div>
	</div>
		
</BODY>
</HTML>

