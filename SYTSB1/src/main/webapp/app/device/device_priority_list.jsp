<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>重点监控设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<style type="text/css">
    .l-icon-import{background:url('k/kui/skins/icons/16/database_go.png') no-repeat center;}
</style>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields : [
		{
			name : "device_registration_code",
			compare : "like",
			value : ""
		}, {
			name : "com_name",
			compare : "like",
			value : ""
		}, {
			name : "factory_code",
			compare : "like",
			value : ""
		}],
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}, "-",{
			text : '导入',
			id : 'import',
			icon : 'import',
			click : importDevice
		}],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					detail : count == 1
				});
			}
		}
	};

	var device_id = null;
	function add() {
		if (device_id == null) {
			$.ligerDialog.alert("请选择一个设备类别！");
			return;
		} else {
			device_type = device_id.substring(0, 1);
			top.$.dialog({
						width : 1000,
						height : 600,
						lock : true,
						title : "添加设备信息",
						content : 'url:app/device/device_detail.jsp?status=add&device_type='
								+ device_type,
						data : {
							"window" : window
						}
					});
		}
	}

	function modify() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "修改特种设备信息",
			content : 'url:app/device/device_detail.jsp?status=modify&id='
					+ Qm.getValueByName("id") + "&device_type="
					+ Qm.getValueByName("big_class"),
			data : {
				"window" : window
			}
		});
	}
	
	function del() {
		$.del("确定删除?", "device/basic/del.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}

	function detail() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "特种设备信息",
			content : 'url:app/device/device_detail.jsp?status=detail&id='
					+ Qm.getValueByName("id") + "&device_type="
					+ Qm.getValueByName("big_class"),
			data : {
				"window" : window
			}
		});
	}

	function loadGridData(nodeId, unitId, url) {
		device_id = nodeId;
		if (nodeId != null) {
			if (nodeId.substring(1, 4) == '000') {
				Qm.setCondition([ {
					name : "device_sort_code",
					compare : "llike",
					value : nodeId.substring(0, 1)
				} ]);
			} else {
				Qm.setCondition([ {
					name : "device_sort_code",
					compare : "=",
					value : nodeId
				} ]);
			}
		} else {
			Qm.setCondition([]);
		}
		Qm.searchData();
	}
	
	function importDevice(){
		top.$.dialog({
			width : 500,
			height : 300,
			lock : true,
			title : "导入",
			content : 'url:app/device/device_import.jsp',
			data : {
				"window" : window
			}
		});
	}
	

	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="device_priority_list" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
