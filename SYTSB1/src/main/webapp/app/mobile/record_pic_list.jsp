<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>原始记录图片信息列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
	//var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";
 	var bar = [ 
		{ text:'批量预览', id:'batchPreview',icon:'detail', click: batchPreview},
		{ text:'批量下载', id:'batchDownload',icon:'detail', click: batchDownload}
 	]
 	
 	var qmUserConfig = {
 			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},		
 			sp_fields:[
	    	{name:"file_name", compare:"like"}
	   	],
		tbar:bar,
	   	listeners: {
	    	selectionChange : function(rowData,rowIndex){
	       		var count=Qm.getSelectedCount();//行选择个数
	         	Qm.setTbar({batchPreview:count>0, batchDownload:count>0});
	     	}
		}
	};
	
	// 批量预览
	function batchPreview(){
		var	selectIds = Qm.getValuesByName("id").toString();
		var	file_names = Qm.getValuesByName("file_name").toString();
		var selects = [];
		var selectIdList = selectIds.split(",");
		var file_nameList = file_names.split(",");
		
		for(var i=0;i<selectIdList.length;i++){
			var pics = [];
			pics["id"]=selectIdList[i];
			pics["name"]=file_nameList[i];
			selects[i] = pics;
		}
		
		var previewData = {
			first: 0,		// 首先显示的文件序号（数组元素序号）
			images: selects
		};
		top.$.dialog({
		     title: "批量预览",
		      width: $(top).width()-100,
		      height: $(top).height()-100,
		      resize: false,
		      max: false,
		      min: false,
		      content: "url:app/mobile/record_pic_preview.jsp",
		      data: previewData
		  });
	}
	
	// 批量预览
	function batchDownload(){
		window.location.href = $("base").attr("href")+"fileupload/downloadPackDb.do?ids="+Qm.getValuesByName("id").toString();
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
	</head>
	<body>
		<qm:qm pageid="record_pic_list" singleSelect="false">
				<qm:param name="BUSINESS_ID" compare="=" value="${param.info_id}"/>	
		</qm:qm>
	</body>
</html>
