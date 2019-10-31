<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
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
User u = (User) sessionUser.getSysUser();
Employee e = u.getEmployee();
request.setAttribute("emp_id",e.getId());
%>
    <script type="text/javascript">
    var qmUserConfig = {
    		sp_fields: [ 
    			{group: [
    				{name: "order_time", compare: ">=", labelWidth: 50},
    				{label: "到", name: "order_time", compare: "<=", labelAlign: "center", labelWidth: 20}
    			]},
    			{name: "is_used",compare: "=", labelWidth: 50}
    		],
    		tbar: [{text: '详情', id: 'detail', icon: 'detail', click: detail},"-",
    		       {text: '添加', id: 'add', icon: 'add', click: add},"-",
    		       {text: '修改', id: 'edit', icon: 'edit', click: edit},"-",
    		       {text: '删除', id: 'del', icon: 'delete', click: del}],
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
    					del:count>0
    				});
    			}
    		}
    	};
    	
    function detail() {
		var id = Qm.getValueByName("id");
		var url = 'dining/food/getSeatFoodList.do';
			$.ajax({
			type:"GET",
			async:false,
			url: url,
	   		success:function(data){
	      		try{
	      			top.$.dialog({
	        			width: 800,
	        			height: 550,
	        			lock: true,
	        			data: {"window": window,"foods":data.data},
	        			title: "新增",
	        			content: 'url:app/fwxm/dining/seat_order_detail.jsp?status=detail&id=' + id
	        		});
				}catch(e){
						
				}
	   		}
		}); 
		
	}
    	function add() {
    		var url = 'dining/food/getSeatFoodList.do';
   			$.ajax({
    			type:"GET",
    			async:false,
    			url: url,
    	   		success:function(data){
    	      		try{
    	      			top.$.dialog({
    	        			width: 800,
    	        			height: 550,
    	        			lock: true,
    	        			data: {"window": window,"foods":data.data},
    	        			title: "新增",
    	        			content: 'url:app/fwxm/dining/seat_order_detail.jsp?status=add'
    	        		});
    				}catch(e){
    						
    				}
    	   		}
			}); 
    	}
    	
    	function edit() {
    		var ostatus = Qm.getValueByName("is_used");
    		if(ostatus!='未审核'){
    			alert('订单已生效！');
    			return;
    		}
    		var id = Qm.getValueByName("id");
    		var url = 'dining/food/getSeatFoodList.do';
    			$.ajax({
    			type:"GET",
    			async:false,
    			url: url,
    	   		success:function(data){
    	      		try{
    	      			top.$.dialog({
    	        			width: 800,
    	        			height: 550,
    	        			lock: true,
    	        			data: {"window": window,"foods":data.data},
    	        			title: "新增",
    	        			content: 'url:app/fwxm/dining/seat_order_detail.jsp?status=modify&id=' + id
    	        		});
    				}catch(e){
    						
    				}
    	   		}
    		}); 
    	}
    	function del() {
    		var ostatus = Qm.getValueByName("is_used");
    		if(ostatus!='未审核'){
    			alert('订单已生效！');
    			return;
    		}
    		$.del(kFrameConfig.DEL_MSG, "dining/seatOrder/deleteSeatOrders.do", {
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
	<qm:qm pageid="food_seat_order" singleSelect="false">
		<qm:param name="emp_id" value="${emp_id}" compare="="/>
	</qm:qm>
</body>
</html>
