<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
		sp_fields : [
		{
			name : "device_name",
			compare : "like",
			value : ""
		},{
			name : "com_name",
			compare : "like",
			value : ""
		},
		{name:"device_status",compare:"=",value:""}
		],
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}
		
		],
	
	
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					modify : count == 1,
					detail : count == 1,
					copy : count == 1,
					editcom : count>0,
					margeCom : count>0,
					del : count > 0,
					enable:count>0,
					disable:count>0,
					useless:count>0,
					modifyDevice:count>0
				});
			}
		}
	};

	var device_id = '2610';
	

	function detail() {
		device_type = device_id.substring(0, 1);
		
		top.$.dialog({
			width : 800,
			height : 500,
			lock : true,
			title : "特种设备信息",
			content : 'url:app/device/device_detail.jsp?status=detail&id='
					+ Qm.getValueByName("id") + "&device_category="+device_id+"&device_type="
					+ Qm.getValueByName("big_class"),
			data : {
				"window" : window
			}
		});
	}
	


	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="device_info_tanker" singleSelect="false">
	</qm:qm>
	<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>;
		Qm.config.columnsInfo.device_status.binddata = [
			{id: '2', text: '使用'},
			{id: '5', text: '停用'},
			{id: '6', text: '报废'}
		];
	</script>
</body>
</html>