<%@ page import="com.khnt.security.util.SecurityUtil" %>
<%@ page import="com.khnt.security.CurrentSessionUser" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String userid = user.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>列表页面</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults: {columnWidth: 0.33, labelAlign: 'right', labelSeparator: '', labelWidth: 100},//可以自己定义 layout:column,float
		/*
		sp_fields: [ 
			{label: "字符：",name: "str_val",compare: "like", labelWidth: 50}, 
			{group: [
				{label: "数字从", name: "int_val", compare: ">=", labelWidth: 50},
				{label: "到", name: "int_val", compare: "<=", labelAlign: "center", labelWidth: 20}
			]},
			{group: [
				{label: "日期从", name: "date_val", compare: ">=", labelWidth: 50},
				{label: "到", name: "date_val", compare: "<=", labelAlign: "center", labelWidth: 20}
			]}
		],
		*/
		/*tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}, "-", {
			text: '新增', id: 'add', icon: 'add', click: add
		}, "-", {
			text: '修改', id: 'edit', icon: 'modify', click: edit
		}, "-", {
			text: '删除', id: 'del', icon: 'delete', click: del
		}],*/
		<tbar:toolBar type="tbar"></tbar:toolBar>,
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                if(rowData.列名1=='XXX') // 记录为绿色
                {
                    return "style='color:green'";
                }
                if(rowData.列名2=='YYY') // 记录为红色
                {
                    return "style='color:red'";
                }
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
			content: 'url:rtbox/config/rtRule_detail.jsp?pageStatus=add'
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
			content: 'url:rtbox/config/rtRule_detail.jsp?id=' + id + '&pageStatus=modify'
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
			content: 'url:rtbox/config/rtRule_detail.jsp?id=' + id + '&pageStatus=detail'
		});
	}
	function del() {
		$.del(kFrameConfig.DEL_MSG, "com/rt/rule/delete.do", {
			"ids": Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body>
	<qm:qm pageid="rt_rule" script="false" singleSelect="false">
    	 <!--qm:param name="str1" compare="like" value="A"/-->
    </qm:qm>
</body>
</html>