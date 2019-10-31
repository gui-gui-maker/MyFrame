<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<head>
<title></title>
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

<% 
String userid=e.getId();%>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
$(document).ready(function() { 

var yzm=<%=request.getParameter("yzm")%>;
if(yzm==null||yzm==""||yzm=="null"){
var pathName=window.document.location.pathname;  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
 return window.location.href=projectName+"/app/archives/archives_read2_list.jsp"; 
  }

}); 
	var userId="<sec:authentication property='principal.id' htmlEscape='false' />";
	var	step_name="报告归档";
	$(function() {qx(); }); 
	var status={};
	var reportNumberId=null;
	var qmUserConfig = {
		tbar: [ {
			text: '查看档案', id: 'detail', icon: 'detail', click: detail
		}, "-",{ text:'查看报告', id:'showReport',icon:'detail', click: showReport}, "-", {
			text: '归还', id: 'add', icon: 'return', click: add
		}],
		listeners: {
				
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					showReport: count==1,
					//add: count==1,
				});	
			},afterQmReady : function() {
			    var condition = [];
			    condition.push({name:"id", logic:"and", "compare":"in", dataType:"user", value: reportNumberId});
				Qm.setCondition(condition);
				Qm.searchData();
			}
		}
	};

	function qx(){//
		var yzm=<%=request.getParameter("yzm")%>;
		
		top.$.ajax({
            url: "archives/yijina/getbg.do?yzm="+yzm,
            type: "GET",
            dataType:'json',
            async: false,
            success:function (data) {
            	reportNumberId="('"+data.mss+"')";
            	//alert(data.mss);
            }
		});
	}
	function showReport(){	
		var id = Qm.getValueByName("id");
		var report_id = Qm.getValueByName("report_type");	// 报告类型
		//var is_user_defined = Qm.getValueByName("is_user_defined").toString();	// 自定义报告
		//if(is_user_defined==""){
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
			//var fileValue = window.showModalDialog(url,[],"dialogwidth:"+w+";dialogheight:"+h+";help=no;status=no;center=yes;edge=sunken;resizable=yes");
	}
	function add() {
		var yzm=<%=request.getParameter("yzm")%>;
	top.$.dialog({
		width: 1000,
		height: 550,
		lock: true,
		parent: api,
		data: {
			window: window
		},
		title: "归还",
		content: 'url:app/archives/archives_return_report.jsp?yzm='+yzm
	});
	}

	function detail() {
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

</script>
</head>
<body>
<!-- sql="select * from TJY2_ARCHIVES_FILE" pagesize="30" seachOnload="false" -->

	<qm:qm pageid="TJY2_report56" singleSelect="true" seachOnload="false">
<%-- 		<qm:param name="op_user_id" compare="=" value="<%=uId%>"/> --%>
	
	</qm:qm>
</body>
</html>