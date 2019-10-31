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
    String userid= sessionUser.getId();
    String user=sessionUser.getSysUser().getName();
    String userBm= sessionUser.getDepartment().getId();
    Map<String,String> roles=sessionUser.getRoles();
%>
    <script type="text/javascript">
    var foodList = '';
	$.ajax({
		type:"GET",
		async:false,
		url: "dining/food/foodList.do",
			success:function(data){
				foodList=data.data;
				//alert(foodList);
			}
	});
    var qmUserConfig = {
    		sp_fields: [ 
    			{group: [
    				{name: "use_time", compare: ">=", labelWidth: 50},
    				{label: "到", name: "use_time", compare: "<=", labelAlign: "center", labelWidth: 20}
    			]},
    			{name: "meal_name",compare: "=", labelWidth: 50},
    			{name: "pub_status",compare: "=", labelWidth: 50}
    		],
    		tbar: [ {
    			text: '详情', id: 'detail', icon: 'detail', click: detail
    		}, "-", {
    			text: '新增', id: 'add', icon: 'add', click: add
    		}, "-", {
    			text: '提交', id: 'submit', icon: 'save', click: submit
    		}, "-", {
    			text: '修改', id: 'edit', icon: 'modify', click: edit
    		}, "-", {
    			text: '取消', id: 'cancel', icon: 'delete', click: cancel
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
    					cancel: count>0,
    					submit:count==1
    				});
    			},
    			rowAttrRender : function(rowData, rowid) {
    				var fontColor= '';
    				if(rowData.pub_status == '已保存'){
    					fontColor="red";
    				}else if(rowData.pub_status == '已提交') {
		       			fontColor="green";
		       		}
		       		var usedate = new Date(rowData.use_time).Format("yyyy-MM-dd");
		       		var today = new Date().Format("yyyy-MM-dd");
		       		var background = '';
		       		if(today==usedate){
		       			background='#ccc;';
		       		}
		       		if(background!=''){
		       			return "style='color:"+fontColor+";background-color:"+background+";'";
		       		}else{
		       			return "style='color:"+fontColor+";'";
		       		}
				}
    		}
    	};
    function addDate(dd,dadd){
    	var a = new Date(dd)
    	a = a.valueOf()
    	a = a + dadd * 24 * 60 * 60 * 1000
    	a = new Date(a)
    	return a;
    	}
    Date.prototype.Format = function (fmt) { //author: meizz 
        var o = {
            "M+": this.getMonth() + 1, //月份 
            "d+": this.getDate(), //日 
            "h+": this.getHours(), //小时 
            "m+": this.getMinutes(), //分 
            "s+": this.getSeconds(), //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
            "S": this.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    	function add() {
    		top.$.dialog({
    			width: 800,
    			height: 500,
    			lock: true,
    			data: {"window": window,"foods":foodList},
    			title: "新增",
    			content: 'url:app/fwxm/dining/food_publish_detail.jsp?pageStatus=add'
    		});
    	}
    	
    	function edit() {
    		/* var pub_status = Qm.getValueByName("pub_status");
    		if(pub_status=="已提交"){
    			 $.ligerDialog.error('提示：' + "数据已提交！");
    			 return;
    		} */
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 800,
    			height: 500,
    			lock: true,
    			parent: api,
    			data: {
    				"window": window,
    				"foods":foodList
    			},
    			title: "修改",
    			content: 'url:app/fwxm/dining/food_publish_detail.jsp?id=' + id + '&pageStatus=modify'
    		});
    	}
    	function detail() {
    		var id = Qm.getValueByName("id");
    		top.$.dialog({
    			width: 800,
    			height: 500,
    			lock: true,
    			parent: api,
    			data: {
    				"window": window,
    				"foods":foodList
    			},
    			title: "详情",
    			content: 'url:app/fwxm/dining/food_publish_detail.jsp?id=' + id + '&pageStatus=detail'
    		});
    	}
    	function cancel() {
    		$.del(kFrameConfig.DEL_MSG, "dining/pubo/cancelos.do", {
    			"ids": Qm.getValuesByName("id").toString()
    		});
    	}
    	
    	function del() {
    		$.del(kFrameConfig.DEL_MSG, "dining/pubo/deleteos.do", {
    			"ids": Qm.getValuesByName("id").toString()
    		});
    	}
    	function submit() {
    		$.getJSON("dining/pubo/submit.do",{"ids": Qm.getValuesByName("id").toString()},function(res){
    			if(res.success){
    				refreshGrid();
    				top.$.dialog.notice({
	             		content: "提交成功！"
	            	});
    			}else{
    				$.ligerDialog.error('提交失败！<br/>' + res.data);
    			}
    		});
    	}
    	
    	// 刷新Grid
    	function refreshGrid() {
       		Qm.refreshGrid();
    	}
    	
    	
    </script>
</head>
<body>
	<qm:qm pageid="foodpb">
		<%-- <qm:param name="pub_status" value="1" compare="=" /> --%>
	</qm:qm>
</body>
</html>
