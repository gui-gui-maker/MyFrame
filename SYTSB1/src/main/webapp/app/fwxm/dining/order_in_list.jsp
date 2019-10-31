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
			{group: [
						{name: "fpout", compare: ">=", labelWidth: 50},
						{label: "到", name: "fpout", compare: "<=", labelAlign: "center", labelWidth: 20}
					]},
			{name : "fpomn", compare : "=", value : ""}
	    ],
		tbar:[
             { text:'详情', id:'detail',icon:'detail', click: detailOrder}
             ,"-",
             { text:'修改', id:'modify',icon:'modify', click: modifyOrder},
             "-",
             { text:'删除', id:'del',icon:'delete', click: delOrder}
        ],
        listeners: {
			selectionChange :function(rowData,rowIndex){
     			var count = Qm.getSelectedCount();//行选择个数
               	Qm.setTbar({modify:count==1,detail:count==1,del:count>0,initSecondPwd:count>0});
			},
			rowDblClick : function(rowData, rowIndex) {
				Qm.getQmgrid().selectRange("id", [rowData.id]);
				detailOrder();
			}
		}
	};

	

	//修改
	function modifyOrder() {
		 var id = Qm.getValueByName("id");
		 var qt =  Qm.getValueByName("quantum").substr(0,1)+'';
		 var fpo = Qm.getValueByName("fpo_id")+'';
		 var url = 'dining/pubm/getFoods.do?id='+fpo;
			$.ajax({
 			type:"GET",
 			async:false,
 			url: url,
 	   		success:function(data){
 	   			alert("seccess");
 	      		try{
        			top.$.dialog({
        				width : 700, 
        				height : 580, 
        				lock : true, 
        				title : "修改订单信息", 
        				data: {"window": window,"foods":data.datalist},
        				content : 'url:app/fwxm/dining/order_per_detail.jsp?status=modify&id=' +id+'&qt='+qt+'&fpo='+fpo
        			});
 				}catch(e){
 						
 				}
 	   		}
			}); 
		
	}
	
	//查看人员信息
	function detailOrder() {
		 var id = Qm.getValueByName("id");
		 var qt =  Qm.getValueByName("quantum").substr(0,1)+'';
		 var fpo = Qm.getValueByName("fpo_id")+'';
		 var url = 'dining/pubm/getFoods.do?id='+fpo;
			$.ajax({
 			type:"GET",
 			async:false,
 			url: url,
 	   		success:function(data){
 	      		try{
        			top.$.dialog({
        				width : 700, 
        				height : 580, 
        				lock : true, 
        				title : "修改订单信息", 
        				data: {"window": window,"foods":data.datalist},
        				content : 'url:app/fwxm/dining/order_per_detail.jsp?status=detail&id=' +id+'&qt='+qt+'&fpo='+fpo
        			});
 				}catch(e){
 						
 				}
 	   		}
			}); 
	}
	
	//删除人员信息
	function delOrder(item) {
		$.del("您确定要删除吗？", "dining/foodOrder/logDelOrders.do", {
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
	<qm:qm pageid="food_orders" script="false" singleSelect="false" sql="select fo.*,fpo.use_time fpout,fpo.meal_name fpomn from food_order fo,food_pubo fpo where fo.fpo_id = fpo.id order by intime">
		<qm:param name="ostatus" value="1" compare="="/>
	</qm:qm>
</body>
</html>
