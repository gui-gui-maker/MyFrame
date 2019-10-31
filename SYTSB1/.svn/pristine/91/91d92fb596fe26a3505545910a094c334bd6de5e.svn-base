<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>车辆保养提醒列表</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {columnWidth : 0.33, labelAlign : 'right', labelSeparator : '', labelWidth : 60 },//可以自己定义 layout:column,float,
		sp_fields : [
				{ name : "car_num", compare : "like"},
				{ name : "km_waring", compare : "like" ,logic : "or"},
				{ name : "date_waring", compare : "like" ,logic : "or"}],
		tbar : [{ text : '详情', id : 'detail', click : detail, icon : 'detail' },
			{ text : '新增', id : 'add', click : add, icon : 'add' },
			{ text : '保养历史', id : 'lookHistory', click : lookHistory, icon : 'follow' }
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
				var date_waring = rowData.date_waring;
				var km_waring = rowData.km_waring;
				var fontColor="black";
				if(date_waring == "已过期"){
					fontColor="red";
				}else if(date_waring == "即将过期"){
					fontColor="orange";
					if(km_waring == "已过期"){
						fontColor="red";
					}
				}else if(date_waring == "未过期"){
					fontColor="black";
					if(km_waring == "已过期"){
						fontColor="red";
					}else if(km_waring == "即将过期"){
						fontColor="orange";
					}
				}else if(date_waring == "未填写保养信息"){
					fontColor="blue";
					if(km_waring == "已过期"){
						fontColor="red";
					}else if(km_waring == "即将过期"){
						fontColor="orange";
					}else if(km_waring == "未过期"){
						fontColor="black";
					}
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
			Qm.setTbar({ add : true, detail : true, lookHistory : true});
		} else {
			Qm.setTbar({ add : false, detail : false, lookHistory : false});
		}
	}
	//详情
	function detail() {
		var selectedId = Qm.getValueByName("fk_cm_detail_id");
		if(selectedId == null || selectedId == "" || selectedId == "undefined"){
			$.ligerDialog.alert("此车最新保养信息不存在或已被删除！请添加保养信息！");
		}else{
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
					fk_car_id: Qm.getValueByName("id")
				},
				content : 'url:app/car/car_wb/pc/car_cm_detail.jsp?pageStatus=detail&id='+ selectedId
			});
		}
	}
	//新增
	function add() {
		top.$.dialog({
			width : 800,
			height : 800,
			lock : true,
			parent: api,
			title : "新增保养信息",
			data : {
				window: window,
				fk_car_id: Qm.getValueByName("id")
			},
			content : 'url:app/car/car_wb/pc/car_cm_detail.jsp?pageStatus=add'
		});
	}
	
	//保养历史
	function lookHistory() {
		window.location.href = "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/app/car/car_wb/pc/car_cm_detail_list.jsp?fk_car_id="
				+ Qm.getValueByName("id")
				+ "&car_num="
				+ encodeURI(encodeURI(Qm.getValueByName("car_num")));
	}
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>

<body>
	<div class="item-tm" id="titleElement">
		<div class="l-page-title">
			<div class="l-page-title-note">
				提示： 
				<font color="black">“黑色”</font>代表保养未到期，
				<font color="orange">“橙色”</font>代表保养即将到期，
				<font color="red">“红色”</font>代表保养已过期，
				<font color="blue">“蓝”</font>代表未填写保养信息。
			</div>
		</div>
	</div>
	<qm:qm pageid="TJY2_CAR_CM" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>