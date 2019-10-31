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
	var report_sn = api.data.report_sn;		// 父窗口的数据-报告编号
	var device_type = api.data.device_type;	// 父窗口的数据-设备类型（大类）
	var check_type = api.data.check_type;	// 父窗口的数据-检验类别
	var check_op_name = api.data.check_op_name;	// 父窗口的数据-检验人员
	var advance_time = api.data.advance_time;	// 父窗口的数据-检验时间
	var report_com_name = api.data.report_com_name;	// 父窗口的数据-报告使用单位

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
				api.close();
				return true;
			}
		};
		closeBtn={
			id: "close",
			text: "关闭",
			icon:"close",
			click: function(){
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
		
		if(report_sn!=null){	
			var arr = new Array();
			arr = report_sn.split(",");			
			if("3" == device_type){	// 电梯
				// 归档目录文档
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/query/end_list.doc");	
				setBookMarkValue1("report_count", arr.length);			// 检验报告份数
				setBookMarkValue1("report_count1", arr.length);			// 检验原始记录份数
			}else if("4" == device_type){	// 起重机
				// 归档目录文档
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/query/qzj_end_list.doc");	
				setBookMarkValue1("report_com_name", report_com_name);		// 使用单位
			}else if("5" == device_type){	// 厂车
				// 归档目录文档
				TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/query/cc_end_list.doc");	
				setBookMarkValue1("report_com_name", report_com_name);		// 使用单位
				setBookMarkValue1("report_count", arr.length);			// 数量
				setBookMarkValue1("report_count2", arr.length);			// 检验报告份数
				setBookMarkValue1("report_count3", arr.length);			// 检验原始记录份数
				setBookMarkValue1("report_count4", arr.length);			// 自检报告份数
			}else if("6" == device_type){ //游乐设施
				if("制造监督检验"==check_type || "安改维监督检验"==check_type){
					// 归档目录文档
					TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/query/ylss_ysjy_list.doc");	
					setBookMarkValue1("report_com_name", report_com_name);		// 使用单位
					setBookMarkValue1("advance_time", advance_time);			// 检验时间
					setBookMarkValue1("check_op_name", check_op_name);			// 检验人员
				}else if("定期检验"==check_type){
					// 归档目录文档
					TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/query/ylss_dqjy_list.doc");	
					setBookMarkValue1("report_com_name", report_com_name);		// 使用单位
					setBookMarkValue1("advance_time", advance_time);			// 检验时间
					setBookMarkValue1("check_op_name", check_op_name);			// 检验人员
				}
				
			}
			setBookMarkValue1("report_sn", report_sn);				// 报告书编号
		}else{
			TANGER_OCX_OBJ.OpenFromURL("${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/payment/templete.doc");
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