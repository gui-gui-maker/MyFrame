<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆保养历史记录列表</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {columnWidth : 0.33, labelAlign : 'right', labelSeparator : '', labelWidth : 60 },//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like" },
				{ name : "ins_com_name", compare : "like" }],
		tbar : [{ text : '详情', id : 'detail', click : detail, icon : 'detail' },
			{ text : '新增', id : 'add', click : add, icon : 'add' },
			{ text : '修改', id : 'modify', click : modify, icon : 'modify' },
			{ text : '删除', id : 'del', click : del, icon : 'del' },
			{ text : '返回', id : 'back', click : back, icon : 'back' }
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
				if(rowData.data_status=="车队负责人待审核"){
					fontColor = "blue";
				}else if(rowData.data_status=="办公室负责人待审核"){
					fontColor = "maroon";
				}else if(rowData.data_status=="车队负责人待盖章"){
					fontColor = "orange";
				}else if(rowData.data_status=="已盖章"){
					fontColor = "green";
				}else if(rowData.data_status=="维保已完成"){
					fontColor = "#8FBC8F";
				}else if(rowData.data_status=="审核不通过"){
					fontColor = "red";
				}
	            return "style='color:"+fontColor+"'";
			}
		}
	};

	//行选择改变事件
	function selectionChange() {
		var count = Qm.getSelectedCount();//行选择个数
		var data_status = Qm.getValueByName("data_status");
		if (count == 1) {
			Qm.setTbar({ modify : true, detail : true, del : true});
		} else {
			Qm.setTbar({ modify : false, detail : false, del : false});
		}
	}
	//详情
	function detail() {
		var selectedId = Qm.getValueByName("id");
		var width = 800;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			parent: api,
			title : "详情",
			data : {
				"window" : window,
				fk_car_id: "${param.fk_car_id}"
			},
			content : 'url:app/car/car_wb/pc/car_cm_detail.jsp?pageStatus=detail&id='+ selectedId
		});
	}
	//新增保险历史
	function add() {
		top.$.dialog({
			width : 800,
			height : 800,
			lock : true,
			parent: api,
			title : "新增保险历史",
			data : {
				window: window,
				fk_car_id: "${param.fk_car_id}"
			},
			content : 'url:app/car/car_wb/pc/car_cm_detail.jsp?pageStatus=add&opType=history'
		});
	}
	//修改
	function modify() {
		var selectedId = Qm.getValueByName("id");
		var width = 800;
		var height = 800;
		var windows = top.$.dialog({
			width : width,
			height : height,
			lock : true,
			parent: api,
			title : "修改",
			data : {
				"window" : window,
				fk_car_id: "${param.fk_car_id}"
			},
			content : 'url:app/car/car_wb/pc/car_cm_detail.jsp?pageStatus=modify&id=' + selectedId + '&opType=history'
		});
	}
	//删除
	function del() {
		var selectedId = Qm.getValueByName("id");
		$.ligerDialog.confirm(kui.DEL_MSG, function(yes) {
			if (yes) {
				$.ajax({
					url : "carCmDetailAction/deleteInfo.do?id=" + selectedId,
					type : "post",
					async : false,
					success : function(data) {
						if (data.success) {
							top.$.notice("删除成功！");
							Qm.refreshGrid();
						}
					}
				});
			}
		});
	}
	//返回
	function back(){
		window.location.href= "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/car/car_wb/pc/car_cm_remind.jsp";
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>

<body>
	<qm:qm pageid="TJY2_CAR_CM_LIST" script="false" singleSelect="true">
		<qm:param name="fk_car_id" value="${param.fk_car_id}" compare="=" />
	</qm:qm>
</body>
</html>