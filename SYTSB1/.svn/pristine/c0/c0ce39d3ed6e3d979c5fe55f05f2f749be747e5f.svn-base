<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<head>
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser useres = SecurityUtil.getSecurityUser();
User uu = (User)useres.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields:[
	         {name:"test_nature",compare: "like"},
	         {name:"report_number",compare: "like"},
	         {group: [
	  				{name: "bjwtsj", compare: ">="},
	  				{label: "到", name: "bjwtsj", compare: "<=", labelAlign: "center", labelWidth:20}
	  		 ],columnWidth:0.4}
	    ],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '填写', id: 'edit', icon: 'modify', click: edit
		}],
	   
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					edit: count==1
				});
			}
		}
	};
	
	function dy(){
		
	}
	function add() {
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "新增",
			content: 'url:app/qualitymanage/taskbook_datail.jsp?pageStatus=add'
		});
	}

	function edit() {
		var id = Qm.getValueByName("id");
		var contract_number = Qm.getValueByName("contract_number");
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "修改",
			content: 'url:app/qualitymanage/taskbook_datail.jsp?id=' + id + '&pageStatus=modify&contract_number='+contract_number
		});
	}
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width: 900,
			height: 550,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/qualitymanage/taskbook_datail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		var id = Qm.getValueByName("id");
		$.ligerDialog.confirm('您确定要删除所选数据吗？', function (yes){
        	if(!yes){return false;}
			top.$.ajax({
	            url: "/delete.do?ids="+id,
	            type: "POST",
	            dataType:'json',
	            async: false,
	            success:function (data) {
	               if(data.success){
	                  top.$.notice(data.msg,3);
	                   Qm.refreshGrid();//刷新
	               }else{
	                   $.ligerDialog.warn(data.msg);
	                   Qm.refreshGrid();//刷新
	               }
	            },
	            error:function () {
	           	 $.ligerDialog.error("出错了！请重试！!");
	            }
	        });
		});
	}
	function refreshGrid() {
	    Qm.refreshGrid();
	}
	function close(){
		api.close();
	}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_ZL_TASKBOOK" singleSelect="true">
	<!-- 	登记人 --><!-- 	申请人 -->
	<sec:authorize access="!hasRole('TJY2_ZL_GLY')">
		<qm:param name="registrant_id" value="<%=uId%>" compare="=" />
	</sec:authorize>
<%-- 		<qm:param name="registrant_id" value="<%=uId%>" compare="=" /> --%>
<%-- 		<qm:param name="apply_man_id" value="<%=userId%>" compare="=" logic="or"/> --%>
	</qm:qm>
</body>
</html>