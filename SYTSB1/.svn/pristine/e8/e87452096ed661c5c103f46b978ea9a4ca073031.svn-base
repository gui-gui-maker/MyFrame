<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%
    CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User u = (User) sessionUser.getSysUser();
	Employee e = u.getEmployee();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户信息</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields:[
			{name : "cardno", compare : "like", value : ""},
			{group: [
						{name: "actiontime", compare: ">=", labelWidth: 50},
						{label: "到", name: "actiontime", compare: "<=", labelAlign: "center", labelWidth: 20}
					]},
	    ],
		 tbar:[{ text:'详情', id:'detail',icon:'detail', click: detail}], 
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({detail:count==1});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			}
		}
	};


	
	//查看人员信息
	function detail() {
		var id = Qm.getValueByName("id");
		top.$.dialog({
			width : 700, 
			height : 580, 
			lock : true, 
			title : "详细信息", 
			data: {"window": window},
			content : 'url:app/fwxm/dining/account_detail.jsp?pageStatus=detail&id='+id
		});
  	}

	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="food_card_account" script="false" singleSelect="false"></qm:qm>
</body>
</html>
