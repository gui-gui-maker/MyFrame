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
	request.setAttribute("emp_id", e.getId());
%>
    <script type="text/javascript">
    var qmUserConfig = {
    		sp_fields: [ 
    			{group: [
    				{name: "use_time", compare: ">=", labelWidth: 50},
    				{label: "到", name: "use_time", compare: "<=", labelAlign: "center", labelWidth: 20}
    			]}
    			
    		],
    		tbar: [ {text: '详情', id: 'detail', icon: 'detail', click: detail},'-', 
    		        {text: '修改', id: 'edit', icon: 'modify', click: edit},'-',
    		        {text: '删除', id: 'del', icon: 'delete', click: del}
    		],
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
    					del: count>0
    				});
    			}
    		}
    	};
    	
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
    			content: 'url:app/fwxm/dining/food_eval_detail.jsp?id=' + id + '&status=modify'
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
    			content: 'url:app/fwxm/dining/food_eval_detail.jsp?id=' + id + '&status=detail'
    		});
    	}
    	
    	function del() {
    		$.del(kFrameConfig.DEL_MSG, "dining/eval/deleteEvals.do", {
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
	<qm:qm pageid="evals" >
		<c:if test = "${param.type=='person' }">
			<qm:param name="emp_id" value="${emp_id }" compare="="/>
		</c:if>
	</qm:qm>
</body>
</html>
