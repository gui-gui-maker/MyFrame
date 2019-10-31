<%@page import="com.lsts.IdFormat"%>
<%@page import="util.TS_Util"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<title>签发分配统计</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var w = window.screen.availWidth;
	var h = window.screen.availHeight;
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:100},	// 默认值，可自定义
		sp_fields : [    
		],
		tbar : [
			{ text : '刷新', id:'refresh', icon:'refresh', click: refreshGrid}
		],
		listeners : {
			rowClick : function(rowData, rowIndex) {
			},
			rowDblClick : function(rowData, rowIndex) {
			},
			selectionChange : function(rowData, rowIndex) {
			},
			rowAttrRender : function(rowData, rowid) {
				var fontColor="black";
		    	return "style='color:"+fontColor+"'";
			}
		}
	};
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="report_issue_count">
	</qm:qm>
</body>
</html>
