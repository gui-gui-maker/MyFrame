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
var file_type = null;
var file_type_name = null;
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields: [ 
			{name: "file_num",compare: "like"}, 
			{name: "file_name",compare: "like"},
			{group: [
				{name: "implement_date", compare: ">="},
				{label: "到", name: "implement_date", compare: "<=", labelAlign: "center", labelWidth: 20}
			]}
		],
		tbar: [ {
			text: '详情', id: 'detail', icon: 'detail', click: detail
		}],
		listeners: {
			rowClick: function(rowData, rowIndex) {
			},
			rowDblClick: function(rowData, rowIndex) {
				detail();
			},
			rowAttrRender: function (rowData, rowid) {
                
            },
			selectionChange: function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();
				Qm.setTbar({
					detail: count==1,
					modify: count==1,
					use: count>0,
					apply: count==1,
					del: count>0
				});
			}
		}
	};
	

	function detail() {
		var id = Qm.getValueByName("id");
		var file_id_old = Qm.getValueByName("file_id_old");
		top.$.dialog({
			width: 850,
			height: 600,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "详情",
			content: 'url:app/qualitymanage/qualityFiles_old_detail.jsp?id=' + id + '&pageStatus=detail&file_id_old='+file_id_old
		});
	}
	function submitAction() {
		Qm.refreshGrid();
	}
	
	function loadGridData(nodeId, nodeName, url) {
		file_type = nodeId;
		file_type_name = nodeName;
		//device_id = nodeId;
		if (nodeId != null) {
				Qm.setCondition([ {
					name : "file_type",
					compare : "=",
					value : nodeId
				} ]);
		} else {
			Qm.setCondition([]);
		}
		Qm.searchData();
	}
</script>
</head>
<body>
	<qm:qm pageid="tzsb_quality_files_o" script="false" singleSelect="false">
    	 <!--qm:param name="str1" compare="like" value="A"/-->
    </qm:qm>
</body>
</html>