<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields : [
		{
			label : "设备注册代码:",
			name : "device_registration_code",
			compare : "like",
			value : ""
		},{
			label : "设备名称:",
			name : "device_name",
			compare : "like",
			value : ""
		},{
			label : "出厂编号:",
			name : "factory_code",
			compare : "like",
			value : ""
		},{
			label : "使用单位:",
			name : "com_name",
			compare : "like",
			value : ""
		},{
			label : "设备使用地点:",
			name : "device_use_place",
			compare : "like",
			value : ""
		}],
		tbar : [

		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}, "-",

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
		<sec:authorize access="hasRole('margeCompany')">
		, "-",
		{
			text : '合并使用单位',
			id : 'margeCom',
			icon : 'modify',
			click : margeCom
		}
		</sec:authorize>
		<sec:authorize access="hasRole('delDevice')">
		, "-",{
			text : '删除',
			id : 'del',
			icon : 'delete',
			click : del
		}
		</sec:authorize>
		<sec:authorize access="hasRole('relData')">
		, "-",{
			text : '同步',
			id : 'rela',
			icon : 'modify',
			click : checkData
		}
		</sec:authorize>
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
					modify : count == 1,
					detail : count == 1,
					copy : count == 1,
					editcom : count>0,
					margeCom : count>0,
					del : count > 0
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
			if("9" == device_type){
				top.$.dialog({
						width : 1000,
						height : 600,
						lock : true,
						title : "添加客运索道",
						content : 'url:app/device/device_detail_cableway.jsp?status=add&device_type='
								+ device_type,
						data : {
							"window" : window
						}
					});
			}else{
				top.$.dialog({
						width : 1000,
						height : 600,
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
	}

	function modify() {
			device_type = Qm.getValueByName("big_class").substring(0, 1);
			if("9" == device_type){
					top.$.dialog({
							width : 1000,
							height : 600,
							lock : true,
							title : "添加客运索道",
							content : 'url:app/device/device_detail_cableway.jsp?status=modify&device_type='
									+ device_type + '&id='+Qm.getValueByName("id"),
							data : {
								"window" : window
							}
						});
			}else{
					top.$.dialog({
						width : 1000,
						height : 600,
						lock : true,
						title : "修改特种设备信息",
						content : 'url:app/device/device_detail.jsp?status=modify&id='
								+ Qm.getValueByName("id") + "&device_type="
								+ Qm.getValueByName("big_class")+'&mdy_drc=${param.mdy_drc}',
						data : {
							"window" : window
						}
					});
			}
	}
	function editcom(){
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		if("9" == device_type){
			alert("客运索道暂不支持批量修改！");
			return;
		}else{
			top.$.dialog({
				width : 600,
				height : 260,
				lock : true,
				title : "批量修改",
				content : 'url:app/device/device_change.jsp?status=modify&id='
						+ Qm.getValuesByName("id"),
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
		device_type = Qm.getValueByName("big_class").substring(0, 1);
		
		
		if("9" == device_type){
				top.$.dialog({
						width : 1000,
						height : 600,
						lock : true,
						title : "客运索道详情",
						content : 'url:app/device/device_detail_cableway.jsp?status=detail&device_type='
								+ device_type + '&id='+Qm.getValueByName("id"),
						data : {
							"window" : window
						}
					});
		}else{
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

	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="device_info" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
