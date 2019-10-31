<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>消息发送模块管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<%
	String data_status = request.getParameter("data_status");
%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name : "module_code", compare : "=", value : "" },
			{ name : "module_name", compare : "like", value : "" },
			{ name : "module_desc", compare : "like", value : "" },
			{ name:"send_target", id:"send_target", compare:"like"},
			{ name:"create_op", id:"create_op", compare:"like"}
		],
		tbar : [
			{
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'},'-',
			{
				text : '新增',
				id : 'add',
				click : add,
				icon : 'add'
				
			},{
				text : '修改',
				id : 'modify',
				click : modify,
				icon : 'modify'
				
			},'-',{
				text : '删除',
				id : 'del',
				click : del,
				icon : 'del'
				
			}
			],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					modify : count==1,
					del : count>0,
					detail : count==1
				});
			},
			rowAttrRender : function(rowData, rowid) {

			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		
	}

	//查看
	function detail() {
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/message/message_module_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}
	
	function add(){
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/message/message_module_detail.jsp?status=add'
		});
	}
	
	function modify(){
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/message/message_module_detail.jsp?status=modify&id='+ Qm.getValueByName("id")
		});
	}
	
	function del(){
		$.del("确定删除?",
				"messageContentModAction/del.do",
			    	{"ids":Qm.getValuesByName("id").toString()});
	}
	
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="tjy2_msg_content_m" script="false">
	</qm:qm>
</body>
</html>
