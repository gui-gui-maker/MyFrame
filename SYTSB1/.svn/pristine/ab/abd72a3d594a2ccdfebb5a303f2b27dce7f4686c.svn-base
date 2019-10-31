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
		{name:"is_cur_check",compare:"=",value:""}
		],
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}, "-",
		/* {
			text : '检验情况',
			id : 'check',
			icon : 'detail',
			click : checkInfo
		}, "-", */
		{
			text : '新增',
			id : 'add',
			icon : 'add',
			click : add
		}, "-",

		{
			text : '修改',
			id : 'modify',
			icon : 'modify',
			click : modify
		}, "-",
		{
			text : '批量修改',
			id : 'editcom',
			icon : 'modify',
			click : editcom
		}
		, "-",{
			text : '删除',
			id : 'del',
			icon : 'delete',
			click : del
		}
	
	
		, "-",{
			text : '复制',
			id : 'copy',
			icon : 'copy',
			click : copy
		}],
	
	
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					check:count==1,
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
	function add() {

		if (device_id == null) {

			$.ligerDialog.alert("请选择一个设备类别！");
			return;

		} else {
			device_type = device_id.substring(0, 1);
				top.$.dialog({
						width : 800,
						height : 500,
						lock : true,
						title : "添加设备信息",
						content : 'url:app/device/device_detail.jsp?status=add&device_type='
								+ device_type + '&device_category='+device_id+'&mdy_drc=${param.mdy_drc}',
						data : {
							"window" : window
						}
					});
			
		}
	}
	function checkInfo() {
		
		top.$.dialog({
			width : 800,
			height : 500,
			lock : true,
			title : "检验情况",
			content : 'url:app/device/device_checkInfo.jsp?id='
					+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
			
	}
	function modify() {
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		
		//var device_id = Qm.getValueByName("device_sort").toString();
		top.$.dialog({
			width : 800,
			height : 500,
			lock : true,
			title : "修改特种设备信息",
			content : 'url:app/device/device_detail.jsp?status=modify&id='
					+ Qm.getValueByName("id") + "&device_type="
					+ Qm.getValueByName("big_class")+'&device_category='+device_id+'&mdy_drc=${param.mdy_drc}',
			data : {
				"window" : window
			}
		});
			
	}
	function editcom(){
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		var device_area_code = Qm.getValueByName("device_area_code");
		if("9" == device_type){
			alert("客运索道暂不支持批量修改！");
			return;
		}else{
			top.$.dialog({
				width : 600,
				height : 300,
				lock : true,
				title : "批量修改",
				content : 'url:app/device/device_change.jsp?status=modify&id='
						+ Qm.getValuesByName("id") + '&device_type=' + device_type + '&device_area_code=' + device_area_code,
				data : {
					"window" : window
				}
			});
		}
	}
	
	function margeCom(){
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		var com_ids = Qm.getValuesByName("fk_company_info_use_id");
		top.$.dialog({
			width : 600,
			height : 100,
			lock : true,
			title : "合并使用单位",
			content : 'url:app/device/device_marge.jsp?status=modify&id='
						+ Qm.getValuesByName("id")+'&com_ids='+com_ids,
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
	
	function checkData() {
		$.del("确定同步?", "department/basic/send_data.do", {
			
		});
	}

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
	
	// 复制
	function copy() {
		var count = 1;	// 默认复制数量为1
		$.ligerDialog.prompt('确定复制所选设备？请输入需要复制的台数！', '1', function (yes, value){
	   		if (yes){
	   			if(isNaN(value)){
	   				$.ligerDialog.error("注意：只能输入数字！");
	   				return;
	   			}else{
	   				count = value;
	   				$.getJSON("device/basic/copyDevice.do?id="+Qm.getValueByName("id")+"&count="+count, function(resp){
						if(resp.success){
					   		$.ligerDialog.success("复制成功！");
					   		submitAction();
					  	}else{
					  		$.ligerDialog.error(resp.msg);
					  	}
					})
	   			}
	   		}
		}); 
	}
	

	function loadGridData(nodeId, unitId, url) {

		device_id = nodeId;
		
		
		
		

		if (nodeId != null) {

			if (nodeId.substring(1, 4) == '000') {

				Qm.setCondition([ {
					name : "device_sort",
					compare : "like",
					value : nodeId.substring(0, 1)+"%"
				} ]);
			}else if(nodeId.substring(2, 4) == '00'){
				Qm.setCondition([ {
					name : "device_sort",
					compare : "=",
					value : nodeId
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
	
	// 启用
	function enable() {
		var statusArr = Qm.getValuesByName("device_status").toString();
		if(statusArr.indexOf("使用") != -1){
			$.ligerDialog.alert("所选设备中，包含已启用的设备哦，请选择未启用的设备！");
			return;
		}
		$.ligerDialog.confirm("亲，确定要启用所选设备吗？", function(yes) {
			if (yes) {
				$.ajax({
					url : "device/basic/enable.do?ids=" + Qm.getValuesByName("id").toString(),
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
		var statusArr = Qm.getValuesByName("device_status").toString();
		if(statusArr.indexOf("停用") != -1){
			$.ligerDialog.alert("所选设备中，包含已停用的设备哦，请选择未停用的设备！");
			return;
		}
		$.ligerDialog.confirm("亲，确定要停用所选设备吗？停用设备请谨慎操作哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "device/basic/disable.do?ids=" + Qm.getValuesByName("id").toString(),
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
	
	// 报废
	function useless() {
		var statusArr = Qm.getValuesByName("device_status").toString();
		if(statusArr.indexOf("报废") != -1){
			$.ligerDialog.alert("所选设备中，包含已报废的设备哦，请选择未报废的设备！");
			return;
		}
		$.ligerDialog.confirm("亲，确定要报废所选设备吗？报废设备请谨慎操作哦！", function(yes) {
			if (yes) {
				$.ajax({
					url : "device/basic/useless.do?ids=" + Qm.getValuesByName("id").toString(),
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("报废成功！");
							Qm.refreshGrid();
						} else {
							top.$.notice("报废失败！" + data.msg);
						}
					}
				});
			}
		});
	}
	
	// 修改设备信息（成都市区县局）
	function modifyDevice(){
		var device_type = device_type = Qm.getValueByName("big_class").substring(0, 1);
		var device_area_code = Qm.getValueByName("device_area_code");
		top.$.dialog({
			width : 600,
			height : 280,
			lock : true,
			title : "修改设备信息",
			content : 'url:app/device/device_detail2.jsp?status=modify&id='
					+ Qm.getValuesByName("id").toString() + '&device_type=' + device_type + '&device_area_code=' + device_area_code,
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
	//	Qm.config.columnsInfo.area_id.binddata=<u:dict sql='select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE'></u:dict>;
		Qm.config.columnsInfo.device_status.binddata = [
			{id: '2', text: '使用'},
			{id: '5', text: '停用'},
			{id: '6', text: '报废'}
		];
	</script>
</body>
</html>
