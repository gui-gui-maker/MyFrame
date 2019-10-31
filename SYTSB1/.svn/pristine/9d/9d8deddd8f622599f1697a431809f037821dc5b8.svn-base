<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>微信应用配置信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name:"app_code", id:"app_code", compare:"like"},
			{name:"app_name", id:"app_name", compare:"like"},
			{name:"data_status", id:"data_status", compare:"="}
		],
		tbar : [
			{
				text : '详情',
				id : 'detail',
				click : detail,
				icon : 'detail'
			}, '-', {
				text : '新增',
				id : 'add',
				click : add,
				icon : 'add'
			},'-', {
				text : '修改',
				id : 'modify',
				click : modify,
				icon : 'modify'
			}, '-', {
				text : '删除',
				id : 'del',
				click : del,
				icon : 'del'
			},'-', {
				text : '启用',
				id : 'enable',
				click : enable,
				icon : 'accept'
			}, '-', {
				text : '停用',
				id : 'disable',
				click : disable,
				icon : 'forbid'
			}
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange();
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
				if (rowData.status == '98'){
		    		fontColor="grey";
		    	}
		   		if (rowData.status == '99'){
	          		fontColor="red";
	            }
				return "style='color:"+fontColor+";'";
		 		//return "style='color:"+fontColor+";font-weight: bold;'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				detail : false,
				modify : false,
				enable : false,
				disable : false,
				del : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				detail : true,
				modify : true,
				enable : true,
				disable : true,
				del : true
			});
		} else {
			Qm.setTbar({
				detail : false,
				modify : false,
				enable : true,
				disable : true,
				del : true
			});
		}
	}
	
	//新增
	function add() {
		top.$.dialog({
			width : 800,
			height : 450,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/weixininfo/app_info/weixin_app_detail.jsp?status=add'
		});
	}
	
	// 启用
	function enable() {
		var statusArr = Qm.getValuesByName("data_status").toString();
		if(statusArr.indexOf("启用") != -1){
			$.ligerDialog.alert("所选记录中，包含已启用的数据哦，请重新选择！");
			return;
		}
		$.ligerDialog.confirm("亲，确定启用所选记录吗？", function(yes) {
			if (yes) {
				$.ajax({
					url : "weixin/app/info/enable.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("启用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("启用失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 停用
	function disable() {
		var statusArr = Qm.getValuesByName("data_status").toString();
		if(statusArr.indexOf("停用") != -1){
			$.ligerDialog.alert("所选记录中，包含已停用的数据哦，请重新选择！");
			return;
		}
		$.ligerDialog.confirm("亲，确定停用所选记录吗？停用操作请谨慎操作哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "weixin/app/info/disable.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("停用成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("停用失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	//查看
	function detail() {
		top.$.dialog({
			width : 800,
			height : 450,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/weixininfo/app_info/weixin_app_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}
	
	//修改
	function modify() {
		top.$.dialog({
			width : 800,
			height : 450,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/weixininfo/app_info/weixin_app_detail.jsp?status=modify&id='+ Qm.getValueByName("id")
		});
	}
	
	//删除
	function del() {
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "weixin/app/info/del.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("删除失败！" + data.msg);
						}
					}
				});
			}
		});
	}

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="wx_app_list">
	</qm:qm>
</body>
</html>
