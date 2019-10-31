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
	request.setAttribute("emp_id",e.getId());
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
			{name : "card_no", compare : "=", value : ""},
			{name : "active_type", compare : "=", value : ""},
			{name : "active_time", compare : "=", value : ""} 
	    ],
		tbar:[
             { text:'详情', id:'detail',icon:'detail', click: detail}
             ,"-",
             { text:'进门', id:'inTheDoor',icon:'add', click: inTheDoor}
             ,"-",
             { text:'领餐', id:'getMeal',icon:'add', click: getMeal}
             ,"-",
             { text:'出门', id:'outTheDoor',icon:'add', click: outTheDoor}
             ,"-",
             { text:'删除', id:'del',icon:'delete', click: del}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({modify:count==1,detail:count==1,del:count>0,initSecondPwd:count>0});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detail();
			}
		}
	};

	function detail(){
		var id = Qm.getValueByName("id");
		top.$.dialog({
				width : 560, 
				height : 360, 
				lock : true, 
				title : "修改订单信息", 
				data: {"window": window},
				content : 'url:app/fwxm/dining/push_detail.jsp?status=detail'
			});
	}
	//领餐
	function getMeal() {
      			top.$.dialog({
      				width : 560, 
      				height : 360, 
      				lock : true, 
      				title : "领餐", 
      				data: {"window": window},
      				content : 'url:app/fwxm/dining/push_detail.jsp?status=add&act=1'
      			});
	}
	//进门
	function inTheDoor() {
      			top.$.dialog({
      				width : 560, 
      				height : 360, 
      				lock : true, 
      				title : "进门", 
      				data: {"window": window},
      				content : 'url:app/fwxm/dining/push_detail.jsp?status=add&act=0'
      			});
	}
	//出门
	function outTheDoor() {
      			top.$.dialog({
      				width : 560, 
      				height : 360, 
      				lock : true, 
      				title : "出门", 
      				data: {"window": window},
      				content : 'url:app/fwxm/dining/push_detail.jsp?status=add&act=2'
      			});
	}
	//删除人员信息
	function del(item) {
		$.del("您确定要删除吗？", "dining/foodOrder/deleteOrders.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	// 刷新Grid
	function refreshGrid() {
   		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="food_card_active" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
