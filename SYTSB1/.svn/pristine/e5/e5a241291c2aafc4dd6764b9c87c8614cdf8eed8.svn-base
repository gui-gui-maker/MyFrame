<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
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
<!-- 不是order_list -->
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
    			{group: [
    				{name: "use_time", compare: ">=", labelWidth: 50},
    				{label: "到", name: "use_time", compare: "<=", labelAlign: "center", labelWidth: 20}
    			]},
    			{name: "meal_name",compare: "=", labelWidth: 50}
    		],
    		tbar: [{
	    			text: '订餐', id: 'person', icon: 'add', click: add
	    			},"-", /* {
	    			text: '未领餐订单', id: 'own', icon: 'edit', click: own
	    		  },"-", */ {
	    			text: '我的订单', id: 'history', icon: 'detail', click: perAll
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
    					person: count==1,
    				});
    			}
    		}
    	};
    	
    	function own() {
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			data: {"window": window},
    			title: "新增",
    			content: 'url:app/fwxm/dining/order_per_list.jsp?status=modify&id=' + id
    		}).max();
    	}
    	function add() {
    		var id = Qm.getValueByName("id");
    		var url = 'dining/pubm/getFoods.do?id='+id;
   			$.ajax({
    			type:"GET",
    			async:false,
    			url: url,
    	   		success:function(data){
    	      		try{
    	      			top.$.dialog({
    	        			width: 600,
    	        			height: 350,
    	        			lock: true,
    	        			data: {"window": window,"foods":data.datalist,"quan":data.datalist2},
    	        			title: "新增",
    	        			content: 'url:app/fwxm/dining/food_order.jsp?id=' + id +'&pageStatus=modify'
    	        		});
    				}catch(e){
    						
    				}
    	   		}
			}); 
    	}
    	function perAll() {
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 600,
    			height: 350,
    			lock: true,
    			data: {"window": window},
    			title: "新增",
    			content: 'url:app/fwxm/dining/order_per_list.jsp?status=detail&id=' + id
    		}).max();
    	}
    	function del() {
    		$.del(kFrameConfig.DEL_MSG, "foodUseTimeAction/delete.do", {
    			"ids": Qm.getValuesByName("id").toString()
    		});
    	}
    	
    	// 刷新Grid
    	function refreshGrid() {
       		Qm.refreshGrid();
    	}
    </script>
</head>
<body>
	<qm:qm pageid="food_pubo" singleSelect="true">
		 <qm:param name="pub_status" value="1" compare="=" />
	</qm:qm>
</body>
</html>
