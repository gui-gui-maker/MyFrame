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
			{ name : "content", compare : "like", value : "" },
			{ name:"send_type", id:"send_type", compare:"like"},
			{ name:"send_time", id:"send_time", compare:"like"},
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
				
			},"-",
            { text:'启用', id:'enable',icon:'userUnlock', click: function(){setEnabled(0)}},
            "-",
            { text:'禁用', id:'disenable',icon:'userLock', click: function(){setEnabled(1)}}
           /*  ,'-',{
				text : '删除',
				id : 'del',
				click : del,
				icon : 'del'
				
			} */
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
						detail : count==1,
						enable:count==1,
						disenable:count==1
					});
			},
			rowAttrRender : function(rowData, rowid) {
				var data_status = rowData.data_status;
					if(data_status!="0"){
	                    		
	                     return "style='color: red'";
					}
                 }
		}
	};

	//行选择改变事件
	function selectionChange() {
		
	}

	//查看
	function detail() {
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/message/message_config_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}
	
	function add(){
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/message/message_config_detail.jsp?status=add'
		});
	}
	
	function modify(){
		top.$.dialog({
			width : 800,
			height : 600,
			lock : true,
			title : "修改",
			data : {
				"window" : window,'params':Qm.getValueByName("param_01")
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/message/message_config_detail.jsp?status=modify&id='+ Qm.getValueByName("id")
		});
	}
	
	function del(){
		$.del("确定删除?",
				"mMessageContentConAction/del.do",
			    	{"ids":Qm.getValuesByName("id").toString()});
	}
	
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
	function setEnabled(status){
		$.getJSON("messageContentConAction/disable.do",{'id':Qm.getValueByName("id"),'status':status},function(resp){
			if(resp.success){
				top.$.notice("操作成功！");
				Qm.refreshGrid();
			}
			else
				$.ligerDialog.error("操作失败：<br/>" + resp.msg);
		});
	}
</script>
</head>
<body>
<div class="item-tm" id="titleElement">
    <div class="l-page-title">
        <div class="l-page-title-note">提示：列表数据项
            <font color="red">“红色”</font>代表——配置已禁用
        </div>
    </div>
    </div>
	<qm:qm pageid="tj2_msg_content_c" script="false">
		<qm:param name="data_status" value="99" compare="<>" />
	</qm:qm>
</body>
</html>
