<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.rbac.bean.Org"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title></title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [ 
			{name : "title", compare : "like", value : "" },
			{group:[
				{name:"create_date", id:"create_date", compare:">="},
				{label:"到", name:"create_date", id:"create_date1", compare:"<=", labelAlign:"center", labelWidth:20}
			]}
		],
		tbar : [ {
			text : '详情',
			id : 'detail',
			click : detail,
			icon : 'detail'
		}, '-', {
			text : '新增',
			id : 'add',
			click : add,
			icon : 'add'
		}, '-', {
			text : '修改',
			id : 'modify',
			click : modify,
			icon : 'modify'
		}, '-', {
			text : '删除',
			id : 'del',
			click : del,
			icon : 'del'
		}],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
				detail();
			},
			selectionChange : function(rowData, rowIndex) {
				selectionChange()
			},
			rowAttrRender : function(rowData, rowid) {

			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		if (count == 0) {
			Qm.setTbar({
				modify : false,
				del : false,
				detail : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				modify : true,
				del : true,
				detail : true
			});
		} else {
			Qm.setTbar({
				modify : false,
				del : true,
				detail : false
			});
		}
	}

	//新增
	function add() {
		top.$.dialog({
			width : 1000,
			height : 620,
			lock : true,
			title : "新增",
			data : {
				"window" : window
			},
			content : 'url:app/maintenance/maintenance_detail.jsp?status=add'
		});
	}

	//修改
	function modify() {
		top.$.dialog({
			width : 1000,
			height : 620,
			lock : true,
			title : "修改",
			data : {
				"window" : window
			},
			content : 'url:app/maintenance/maintenance_detail.jsp?status=modify&id='+ Qm.getValueByName("id")
		});
	}

	//查看
	function detail() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "详情",
			data : {
				"window" : window
			},//把当前页面窗口传入下一个窗口，以便调用。
			content : 'url:app/maintenance/maintenance_detail.jsp?status=detail&id='+ Qm.getValueByName("id")
		});
	}

	//删除
	function del() {
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "maintenance/del.do?ids=" + Qm.getValuesByName("id").toString(),
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
	<qm:qm pageid="maintenance_list" script="false">
	</qm:qm>
</body>
</html>
