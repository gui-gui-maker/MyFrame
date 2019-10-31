<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<%
String realPath1 = request.getSession().getServletContext().getRealPath("upload");

//System.out.print(">>>>>>>"+realPath1+"<<<<<<");
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
<!--  -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%@include file="/k/kui-base-form.jsp"%>
<style type="text/css">
    .l-icon-exportDoc{background:url('k/kui/images/16/icons/word2.png') no-repeat center;}
    .l-icon-printPreview{background:url('k/kui/images/16/icons/search2.png') no-repeat center;}
    .l-icon-fullScreen{background:url('k/kui/images/16/icons/div-drag.gif') no-repeat center;}
</style>
<script src="pub/office/ntkoofficecontrol.js" type="text/javascript"></script>
<script src="pub/office/editor.js" type="text/javascript"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}/";
	var toolBar;//工具条
	$(function(){
		$(".layout").ligerLayout({
			bottomHeight:30,
			space : 0
		});
	});
	
	//文档标签赋值
	function setBookMarkValue1(bookMarkName,inputValue){
		var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(bookMarkName);
		if(!bkmkObj){
			return;
		}
		var saverange = bkmkObj.Range;
		saverange.Text = inputValue;
		TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(bookMarkName,saverange);
	}
	
	function initPage(){
		initToolBar();
		createNtkoEditor("editor_container");
		
			
			TANGER_OCX_OBJ.Menubar = false;
			TANGER_OCX_OBJ.Statusbar = false;
			TANGER_OCX_OBJ.Toolbars = false;
			var date="${param.advance_time}";
			
			//加载pdf插件
			addPdfPlugin();
			//alert(date+"-----------------${param.report_sn}")
			if("${param.record}"!=""&&"${param.record}"!="null"){
		    	if("${param.pdf_record_att}"!=""&&"${param.pdf_record_att}"!="null"&&"${param.pdf_record_att}"!="undefined"){

		    		TANGER_OCX_OBJ.OpenFromURL(  $("base").attr("href") +  "fileupload/download.do?id=${param.pdf_record_att}");
		    	}
		    }else{
		    	if("${param.pdf_att}"!=""&&"${param.pdf_att}"!="null"&&"${param.pdf_att}"!="undefined"){
		    		TANGER_OCX_OBJ.OpenFromURL(  $("base").attr("href") +  "fileupload/download.do?id=${param.pdf_att}");
		    	}else{
		    		
		    		TANGER_OCX_OBJ.OpenFromURL(  $("base").attr("href") +  "upload/"+date.substring(0,4)+date.substring(5,7)+date.substring(8,10)
		    				+"/${param.report_sn}/${param.report_sn}"+".pdf"); 
		    		TANGER_OCX_OBJ.SetReadOnly(true);
		    		
		    	}
		    }
			
			var is_double_sider =  '${PARA_MAP.BASE_REPORT_INFO.isDoubleSidePrinter}';
			var report_item = '${PARA_MAP.report_item}';
			//setReportsFroPrint(report_item,is_double_sider);
			if(parent.left.printFlag){
				var print_copies = '${param.print_copies}';
				for (var i = 0; i < print_copies-0; i++) {
					TANGER_OCX_OBJ.printout(false);
				}
				MRViewer_AfterPrint("1")
				//parent.left.setAlreadyPrint();
				
			} 
		
	}
	
	function doPrintreport(print_copies){
		for (var i = 0; i < print_copies-0; i++) {
			TANGER_OCX_OBJ.printout(false);
		}
		
		MRViewer_AfterPrint("1")
	}
	
  	function initToolBar(){
    	var saveBtn;
    	var closeBtn;
    	var printBtn;
    	var printPreviewBtn;
    	var setLayoutBtn;
    	var fullScreenBtn;
    	var subBtn;
    	var backBtn;
    
		closeBtn={
				id: "close",
				text: "关闭",
				icon:"close",
				click: function(){
				    api.data.window.Qm.refreshGrid();
				    api.close();
					return true;
				}
		};
		printBtn={
				id: "print",
				text: "打印",
				icon:"print",
				click: function(){
					doPrint();
					//savePrint();
				}
		 };
		 printPreviewBtn={
				id: "printPreview",
				text: "打印预览",
				icon:"preview",
				click: function(){
					printPreview();
					return true;
				}
		 };
		 /**
		 setLayoutBtn={
				id: "printSet",
				text: "页面设置",
				icon:"setting",
				click: function(){
					setLayout();
					return true;
				}
		 };**/
		 fullScreenBtn={
				id: "fullScreen",
				text: "全屏",
				icon:"provide",
				click: function(){
					fullScreen();
					return true;
				}
		 };
	
		var itemArr=new Array();
		
			itemArr.push(printBtn);
			itemArr.push(printPreviewBtn);
		   	itemArr.push(fullScreenBtn);
		   	itemArr.push(closeBtn);
		   	
	   /*  toolBar=$("#toolbar").ligerButton({
			items:itemArr
		}); */
    }

	function savePrint(){
  		var url = "reportPrintAction/doAfterPrint.do?infoIds=${param.infoId}&re_print=${param.re_print}" ;
		//alert("${param.flowNodeId}"+"     "+"${param.activityId}");
		$.ajax({
            url: url,
            type: "POST",
            datatype: "json",
            async:false,
            data: {dataStr : "",
            	   flowNodeIds : "${param.flowNodeId}",
            	   activityIds : "${param.activityId}"},
            success: function (data, stats) {
            	$("body").unmask();
                if (data["success"]) {
                	top.$.dialog.notice({content:'【'+data["report_sn"]+'】打印成功'});
                }else {
                    $.ligerDialog.error('【'+data["report_sn"]+'】打印后处理数据失败');
                }
            },
            error: function (data,stats) {
            	$("body").unmask();
                $.ligerDialog.error('打印后处理数据失败');
            }
        });
		api.close();
  	}
  //报告打印完成后自动调用
	function MRViewer_AfterPrint(flag)
	{
		if(flag=='1'){
			
			var id = "${param.infoId}";
			url = "department/basic/flow_print.do?infoId=" + id + "&acc_id="
					+ "${param.activityId}" + "&flow_num=" + "${param.flowNodeId}&re_print=${param.re_print}";
			$.ajax({
				url : url,
				type : "POST",
				async:false,
				dataType : "json",
				success : function(data) {
					/* $("body").unmask();
	                if (data["success"]) {
	                	top.$.dialog.notice({content:'【'+data["report_sn"]+'】打印成功'});
	                }else {
	                    $.ligerDialog.error('【'+data["report_sn"]+'】打印后处理数据失败');
	                } */
				},
				error : function(resp) {
					$("body").unmask();
                    $.ligerDialog.error('打印后处理数据失败');
				}
			});
			
			try {
				api.close();
			} catch (e) {
				// TODO: handle exception
			}
			//调用列表页面处理
			try {
				parent.left.setAlreadyPrint();
			} catch (e) {
				// TODO: handle exception
			}
		}
	}
  	
</script>
</head>
<body onload="initPage();">
<div class="layout">
	<div position="center" id="editor_container" style="width:100%;height:100%"></div>
	<div position="bottom">
		<div class="div1" id="toolbar" style="padding:1px;text-align:right;"></div>
	</div>
</div>
<!-- <iframe id="export_doc_iframe" style="display:none;">
</iframe> -->
</body>
</html>