﻿<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <title></title>

    <%@include file="/k/kui-base-list.jsp"%> 
<%
    CurrentSessionUser sessionUser=(CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userid= sessionUser.getId();
    String user=sessionUser.getSysUser().getName();
    String userBm= sessionUser.getDepartment().getId();
    Map<String,String> roles=sessionUser.getRoles();
%>
    <script type="text/javascript">
    var qmUserConfig = {
    		sp_fields: [ 
    			{name: "cardno",compare: "like", labelWidth: 50},
    			{name: "cuser",compare: "like", labelWidth: 50},
    			{name: "tel",compare: "like", labelWidth: 50}
    		],
    		tbar: [ {
    			text: '详情', id: 'detail', icon: 'detail', click: detail
    		}, "-", {
    			text: '办卡', id: 'add', icon: 'add', click: add
    		}, "-", {
    			text: '充值', id: 'recharge', icon: 'modify', click: recharge
    		},"-", {
    			text: '修改', id: 'edit', icon: 'modify', click: edit
    		},"-", {
    			text: '挂失', id: 'del', icon: 'delete', click: del
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
    					edit: count==1,
    					recharge:count==1,
    					del: count>0
    				});
    			}
    		}
    	};
    	function add() {
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			parent: api,
    			data: {
    				window: window
    			},
    			title: "新增",
    			content: 'url:app/fwxm/dining/food_card_detail.jsp?pageStatus=add'
    		});
    	}
    	function detail() {
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			parent: api,
    			data: {
    				window: window
    			},
    			title: "详情",
    			content: 'url:app/fwxm/dining/food_card_detail.jsp?id=' + id + '&pageStatus=detail'
    		});
    	}
    	function edit() {
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			parent: api,
    			data: {
    				window: window
    			},
    			title: "修改",
    			content: 'url:app/fwxm/dining/food_card_detail.jsp?id=' + id + '&pageStatus=modify'
    		});
    	}
    	function recharge() {
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			parent: api,
    			data: {
    				window: window
    			},
    			title: "修改",
    			content: 'url:app/fwxm/dining/cardRecharge.jsp?id=' + id 
    		});
    	}
    	function balance() {
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			parent: api,
    			data: {
    				window: window
    			},
    			title: "修改",
    			content: 'url:app/fwxm/dining/card_balance.jsp?pageStatus=add'
    		});
    	}
    	function del() {
    		$.del("你确定要挂失？", "dining/foodCard/deleteCards.do", {
    			"ids": Qm.getValuesByName("id").toString()
    		});
    	}
    </script>
</head>
<body>
	<qm:qm pageid="food_card">
		<qm:param name="cardStatus" value="1" compare="=" />
	</qm:qm>
</body>
</html>
