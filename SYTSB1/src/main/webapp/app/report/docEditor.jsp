<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.sinspection.bean.SupervisionInspection" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="util.MoneyUtil"%>
<%@page import="util.DateToChinese"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%@include file="/k/kui-base-form.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<script src="app/pub/office/js/editor.js" type="text/javascript"></script>
<script type="text/javascript" src="app/payment/payment_list.js"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	var beanData = api.data.bean;//父窗口的数据

	$(function(){
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
	
		$(".layout").ligerLayout({
			bottomHeight:30,
			space : 0
		});
	});
	
	function initPage(){
		initToolBar();
		createNtkoEditor("editor_container");
		//远程加载文档
		loadBumfDoc();
		initDocView();
	}
	
	
	function initDocView(){
		if("${param.status}"=="detail"){
			initView("view");
		}
	}
	
	function initToolBar(){
		var printBtn;
		var closeBtn;
		printBtn={
			id: "print",
			text: "打印",
			icon:"print",
			click: function(){						
				doPrint();
				if("2" == "${param.isPrint}"){
					api.data.pwindow.api.close();
					api.data.pwindow.api.data.window.refreshGrid();
				}
				api.close();
				return true;
			}
		};
		closeBtn={
			id: "close",
			text: "关闭",
			icon:"close",
			click: function(){
				if("2" == "${param.isPrint}"){
					api.data.pwindow.api.close();
					api.data.pwindow.api.data.window.refreshGrid();
				}
				api.close();
				return true;
			}
		};
		var itemArr=new Array();
		itemArr.push(printBtn);
		itemArr.push(closeBtn);
		toolBar=$("#toolbar").ligerButton({
			items:itemArr
		});
	}
	
	//加载正文
	function loadBumfDoc(){
		TANGER_OCX_OBJ.ToolBars=true;
			TANGER_OCX_OBJ.OpenFromURL("/app/report/01report_print_record.doc");
			var datas = beanData;//beanData["reportPrintRecord"];
			var num = 0;
			for(var i in datas){
				num ++;
				if(num == 1){
					setBookMarkValue1("org_name", datas[i]["org_name"]);	// 部门
				}
				setBookMarkValue1("report_sn"+num, datas[i]["report_sn"]);	// 报告书编号
				setBookMarkValue1("report_count"+num, datas[i]["report_count"]);	// 台数
				if(datas[i]["com_name"]!=null){
					setBookMarkValue1("com_name"+num, datas[i]["com_name"]);	// 使用单位
				}
				if(datas[i]["made_unit_name"]!=null){
					setBookMarkValue1("com_name"+num, datas[i]["made_unit_name"]);	// 制造单位
				}
				if(datas[i]["construction_units_name"]!=null){
					setBookMarkValue1("com_name"+num, datas[i]["construction_units_name"]);	// 安装单位
				}
				setBookMarkValue1("from_user"+num, datas[i]["commit_user_name"]+datas[i]["commit_time_str"]);	// 报送人、报送时间
				if(datas[i]["receive_user_name"]!=null){
					setBookMarkValue1("to_user"+num, datas[i]["receive_user_name"]+datas[i]["receive_time_str"]);	// 接收人、接收时间
				}
				if(datas[i]["remark"]!=null){
					setBookMarkValue1("remark"+num, datas[i]["remark"]);	// 备注
				}
			}			
		//saved属性用来判断文档是否被修改过,文档打开的时候设置成ture,当文档被修改,自动被设置为false,该属性由office提供.
		//TANGER_OCX_OBJ.activeDocument.saved=true;
	}

	function showBB(){
		$("#sssss").show();
	}
</script>
</head>
<body onload="initPage();">
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
<div class="layout">
    <div id="seal_container"></div>
	<div position="center" id="editor_container" style="width:100%;height:100%"></div>
	<div position="bottom" style="height: 50px;">
		<div class="div1" id="toolbar" style="padding:1px;text-align:right;"></div>
	</div>
</div>
<iframe id="export_doc_iframe" style="display:none;">
</iframe>
</div>
</body>
</html>