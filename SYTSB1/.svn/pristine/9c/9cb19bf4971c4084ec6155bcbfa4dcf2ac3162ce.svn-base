<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<script type="text/javascript">
var userId = "<sec:authentication property='principal.id' htmlEscape='false' />";

var	step_name="报告归档";

	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
			sp_fields:[
				{name:"report_sn",compare:"like"},
				{name:"sn",compare:"like"},
				{name:"enter_op_name",compare:"like"},
				{name:"scan_upload_by_name",compare:"like"},
				{group: [
			  				{name: "scan_upload_time", compare: ">="},
			  				{label: "到", name: "scan_upload_time", compare: "<=", labelAlign: "center", labelWidth:20}
			 	  		 ]}
		    ],
		tbar: [
		       {text: '编辑附件', id: 'add', icon: 'add', click: add}, "-",
		     /*   {text: '大文件上传', id: 'add', icon: 'add', click: uploadBigFile}, "-", */
		       {text:'查看报告', id:'showReport', icon:'detail', click: showReport}, "-", 
		       {text: '查看档案', id: 'archives2', icon: 'detail', click: archives2},"-",
		       {text: '扫描上传', id: 'scanFiles', icon: 'add', click: scanFiles},"-",
		       {text: "新增文件盒", id:'addBox', icon: "add", click: addBox},"-",
		       {text: "打印", id:'print', icon: "print", click: print}
		       <sec:authorize access="hasRole('archives_download')">
		       ,"-",{text: '文件下载', id: 'download', icon: 'appropriation', click: download}
	    	   </sec:authorize>
		       ],
	   
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				archives();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					add : count==1,
					showReport :count==1,
					archives2 : count==1,
					scanFiles : count==1,
					addBox : true,
					download : count==1,
					print:count==1
				});
			},afterQmReady : function() {
				Qm.setCondition([ //{name : "activity_name",compare : "=",value : step_name},
				                 // {name : "handler_id",compare : "=",value : userId}
								]);
				Qm.searchData();
			} 
		}
	};
	
	function archives2(){
		top.$.dialog({
			width: "90%",
			height:"90%",
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "查询档案",
			content: 'url:app/archives/archives_information2.jsp?pageStatus=detail&bh='+Qm.getValueByName("report_sn")+"&fileId="+Qm.getValueByName("id")
		});
	}
	//扫描上传
	function scanFiles(){
		var id = Qm.getValueByName("id");
		if(!id){
          top.$.notice('请先选择数据！',3);
          return;
        }
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/archives/archives_scan_upload.jsp?bh='+Qm.getValueByName("report_sn")+'&id=' + id + '&pageStatus=modify'
		}).max();
	}
	//扫描上传
	function uploadBigFile(){
		var id = Qm.getValueByName("id");
		if(!id){
          top.$.notice('请先选择数据！',3);
          return;
      	}
		var doc = Qm.getValueByName("report_sn");
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "大文件上传",
			content: 'url:app/archives/upload_big_file.jsp?report_sn='+doc+'&id=' + id
		}).max();
	}
	// 查看报告
	function showReport(){	
		var id = Qm.getValueByName("id").toString();
		var report_id = Qm.getValueByName("report_type").toString();	// 报告类型
			var w=window.screen.availWidth;
			var h=(window.screen.availHeight);
			var url = "report/query/showReport.do?id="+id+"&report_id="+report_id;
			top.$.dialog({
				width : w, 
				height : h, 
				lock : true, 
				title:"报告信息",
				content: 'url:'+url,
				data : {"window" : window}
			}).max();
	}
	function archives(){
		top.$.dialog({
			width: "90%",
			height:"90%",
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "查询档案",
			content: 'url:app/archives/archives_information.jsp?pageStatus=detail&bh='+Qm.getValueByName("report_sn")+"&fileId="+Qm.getValueByName("id")
		});
	}
	
	function add() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 950,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "添加",
			content: 'url:app/archives/archives_upload.jsp?id=' + id 
					+ '&pageStatus=modify&report_sn='+Qm.getValueByName("report_sn")	
		});
	}
	function print() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 850,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "打印",
			content: 'url:app/archives/archives_print.jsp?id=' + id 
					+ '&pageStatus=modify&report_sn='+Qm.getValueByName("report_sn")	
		});
	}
	function download() {
		//报告id,sn
		var id = Qm.getValueByName("id");
		var sn = Qm.getValueByName("report_sn");
		top.$.dialog({
			width: 850,
			height: 500,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "文件下载",
			content: 'url:app/archives/archives_download.jsp?id=' + id+'&sn='+sn
		});
	}
	function addBox(item) {
		top.$.dialog({
 			width : 700, 
 			height : 400, 
 			lock : true, 
 			title : "新增文件盒", 
 			data : {"window" : window}, 
			content : 'url:app/archives/archives_box_detail.jsp?status=add'
 		}); 
	}
	
</script>
</head>
<body>
	<qm:qm pageid="TJY2_report55" singleSelect="true" script="false" seachOnload="false">
<%-- 	<qm:param name="handler_id" compare="=" value="<%=uId%>"/> --%>
	</qm:qm>
</body>
</html>