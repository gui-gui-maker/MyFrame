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
			name : "device_registration_code",
			compare : "like",
			value : ""
		},{
			name : "device_name",
			compare : "like",
			value : ""
		},{
			name : "factory_code",
			compare : "like",
			value : ""
		},{
			name : "internal_num",
			compare : "like",
			value : ""
		},{
			name : "com_name",
			compare : "like",
			value : ""
		},{
			name : "maintain_unit_name",
			compare : "like",
			value : ""
		},{
			name : "area_id",
			compare : "llike",treeLeafOnly:false
		},{
				name : "device_qr_code",
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
		<sec:authorize ifAnyGranted="report_inspection,PL_INSPECTION,sys_administrate">
		, "-",
		{
			text : '新增',
			id : 'add',
			icon : 'add',
			click : add
		}
		</sec:authorize>
		<%
			String mdy_drc = request.getParameter("mdy_drc");
			if("0".equals(mdy_drc)){
				%>
				, "-",
				{
					text : '修改',
					id : 'modify',
					icon : 'modify',
					click : modify
				}
				<%
			}else{
				%>
				<sec:authorize ifAnyGranted="sys_administrate">
				, "-",
				{
					text : '修改',
					id : 'modify',
					icon : 'modify',
					click : modify
				}
				</sec:authorize>
				<%
			}
		%>
		<sec:authorize ifAnyGranted="report_inspection,PL_INSPECTION,sys_administrate">
		, "-",
		{
			text : '批量修改',
			id : 'editcom',
			icon : 'modify',
			click : editcom
		}
		</sec:authorize>
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
		<sec:authorize ifAnyGranted="report_inspection,PL_INSPECTION,sys_administrate">
		, "-",{
			text : '复制',
			id : 'copy',
			icon : 'copy',
			click : copy
		}
		</sec:authorize>
		<sec:authorize access="hasRole('qxj')">
		,'-', {
			text : '启用',
			id : 'enable',
			click : enable,
			icon : 'modify'
		}, '-', {
			text : '停用',
			id : 'disable',
			click : disable,
			icon : 'modify'
		}, '-', {
			text : '报废',
			id : 'useless',
			click : useless,
			icon : 'modify'
		}, '-', {
			text : '修改设备信息',
			id : 'modifyDevice',
			click : modifyDevice,
			icon : 'modify'
		}
		</sec:authorize>],
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
						height : 650,
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
			
			var device_id = Qm.getValueByName("device_sort").toString();
			if("9" == device_type){
					top.$.dialog({
							width : 1000,
							height : 600,
							lock : true,
							title : "添加客运索道",
							content : 'url:app/device/device_detail_cableway.jsp?status=modify&device_type='
									+ device_type + '&id='+Qm.getValueByName("id")+'&mdy_drc=${param.mdy_drc}',
							data : {
								"window" : window
							}
						});
			}
			else{
					top.$.dialog({
						width : 1000,
						height : 650,
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
				height : 360,
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
			height : 200,
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
		//device_flag = device_id.substring(0, 2);
		
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
						+ Qm.getValueByName("big_class")+'&device_category='+device_id,
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
	   				var mdy_drc = "<%=mdy_drc%>";
	   				if("1"==mdy_drc){
		   				top.$.dialog({
		   					width : 800,
		   					height : 400,
		   					lock : true,
		   					title : "设备复制",
		   					content : 'url:app/device/device_copy_detail.jsp?status=add&id='
		   							+ Qm.getValueByName("id") + "&count="
		   							+ count,
		   					data : {
		   						"window" : window
		   					}
		   				});
	   				}else{
	   					$.getJSON("device/basic/copyDevice2.do?id="+Qm.getValueByName("id")+"&count="+count, function(resp){
							if(resp.success){
						   		$.ligerDialog.success("复制成功！");
						   		submitAction();
						  	}else{
						  		$.ligerDialog.error(resp.msg);
						  	}
						})
	   				}
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
			width : 700,
			height : 380,
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
	<qm:qm pageid="device_info" singleSelect="false">
	</qm:qm>
	<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		Qm.config.columnsInfo.area_id.binddata=<u:dict sql="select id,parent_id pid,case when substr(regional_code, length(regional_code) - 1, length(regional_code)) = '00' then substr(regional_code, 0, length(regional_code) - 2) else regional_code end code, regional_name text from V_AREA_CODE"></u:dict>;
		Qm.config.columnsInfo.device_status.binddata = [
			{id: '2', text: '使用'},
			{id: '5', text: '停用'},
			{id: '6', text: '报废'}
		];
	</script>
</body>
</html>
