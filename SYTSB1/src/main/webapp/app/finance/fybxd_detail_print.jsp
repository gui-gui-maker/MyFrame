
<%@page import="util.ReportUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.base.Factory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.ming.webreport.*"%>

<%@ page import="java.util.*"%>

<head pageStatus="add">
<title>报告书录入</title>



<%@ include file="/k/kui-base-form.jsp"%>

<%	

int total_page=1;

MRDataSet mrds = new MRDataSet();
DataRecord rec = new DataRecord();

//获取返回的业务信息List
Map<String,String> infoMap 
	= (Map<String,String>)request.getAttribute("fybxd");

//加载业务信息
	ReportUtil.setRecodeFromStringMap(rec, infoMap);


	

mrds.addRow(rec);
MREngine engine = new MREngine(pageContext);
engine.addReport("fxbxd.mrf");
engine.setRootPath("app/flow/report/temple/CW");
//engine.setRootPath("C:/Users/zy/Desktop/");
 engine.addMRDataSet("BGDS", mrds);

engine.bind();






%>

<%@ include file="report.js.jsp"%>

<script language="javascript">
	$(function() {
		
		$('#audit').click(function(){
			doAudit();
		});
		
		$('#print').click(function(){
			doPrintreport(1,"");
		});
		
		$('#cancel').click(function(){
			api.close();
		});
	})
	
	
	function doPrintreport(print_copies,printer_name)
	{
		MRViewer = document.all("MRViewer") ;
		MRViewer.PrintSetup(0,0,true,"",0,print_copies,true,printer_name);
		MRViewer.Print(false);	// false不提示打印设置框，调用默认的
		MRViewer_AfterPrint('1');
	}
	
	function MRViewer_AfterPrint(flag){
		if(flag=='1'){
			api.close();
		}
	}
	function showBB(){
		$("#sssss").show();
	}
	
	//保存开始禁用按钮
	function disableButton(){
		//$.ligerDialog.alert(111);
	}
	//保存结束按钮可用
	function enableButton(){
		//$.ligerDialog.alert(222);
	}
	$(window).load(function() {
		$("#MRViewer").append('<param name="wmode" value="transparent" />');
	});
//-->
</script>
</head>
<body>
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%"
	 simple="false" 
	 invisiblebuttons="Export,Close,PrintPopup,ExportPopup,Find,FindNext" 
	 postbackurl=""  canedit="false"   wrapparams="true"  />
	 <script>
	

	 </script>
</div>
<div class="toolbar-tm">
	<div class="toolbar-tm-bottom" style="text-align:center;">
		<a id="print" class="l-button-warp l-button" ligeruiid="Button1012">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">打印</span>
				<span class="l-button-icon l-icon-print"></span>
			</span>
		</a>
		<a id="cancel" class="l-button-warp l-button" ligeruiid="Button1012">
			<span class="l-button-main l-button-hasicon">
				<span class="l-button-text">关闭</span>
				<span class="l-button-icon l-icon-cancel"></span>
			</span>
		</a>
	</div>
</div>
</body>
</html>