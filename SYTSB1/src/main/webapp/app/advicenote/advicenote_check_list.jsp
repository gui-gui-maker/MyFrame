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
		sp_fields : [ {
			name : "advicenote_name",
			compare : "like",
			value : ""
		},{
			name : "advicenote_type",
			compare : "=",
			value : ""
		}],
		tbar : [ {
			text : '审核',
			id : 'check',
			click : check,
			icon : 'check'
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
				check : false
			});
		} else if (count == 1) {
			Qm.setTbar({
				check : true
			});
		} else {
			Qm.setTbar({
				check : false
			});
		}
	}

	// 审核
	function check() {
		var id = Qm.getValueByName("id");
		var docId = Qm.getValueByName("docId");
		var type = Qm.getValueByName("type");
		top.$.dialog({
			width : $(top).width(),
			height :  $(top).height()-40,
			lock : true,
			title : "正文内容",
			parent: api,
			content : 'url:app/advicenote/docEditor.jsp?status=check&docId='+docId+'&type='+type,
			data: {pwindow: window,bean: id}
		}).max();
	}


	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="advicenote_check" script="false">
	</qm:qm>
</body>
</html>
