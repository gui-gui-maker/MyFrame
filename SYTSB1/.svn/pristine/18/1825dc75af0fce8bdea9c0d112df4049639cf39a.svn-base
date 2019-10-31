<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>检验检测收费标准配置信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
			sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
			sp_fields : [
			{
				name : "device_type",
				compare : "=",
				value : "",
				treeLeafOnly: false
			},{
				name : "device_category",
				compare : "=",
				value : ""
			},{
				name : "device_name",
				compare : "like",
				value : ""
			},{
				name : "check_type",
				compare : "=",
				value : ""
			},{
				name : "pay_key",
				compare : "like",
				value : ""
			},{
				name : "pay_value",
				compare : "="
			},{ 
				name:"data_status",
				compare:"=",
				value:""
			}
		],
		tbar : [
		{
			text : '查看',
			id : 'detail',
			icon : 'detail',
			click : detail
		}, "-",{
			text : '新增',
			id : 'add',
			icon : 'add',
			click : add
		}, "-",{
			text : '修改',
			id : 'modify',
			icon : 'modify',
			click : modify
		}, "-",{
			text : '删除',
			id : 'del',
			icon : 'delete',
			click : del
		}],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					modify : count == 1,
					detail : count == 1,
					del : count > 0
				});
			}
		}
	};

	function add() {
		top.$.dialog({
			width : 800,
			height : 320,
			lock : true,
			title : "添加",
			content : 'url:app/payment/pay_detail.jsp?status=add',
			data : {
				"window" : window
			}
		});			
	}

	function modify() {
		top.$.dialog({
			width : 800,
			height : 320,
			lock : true,
			title : "修改",
			content : 'url:app/payment/pay_detail.jsp?status=modify&id='
								+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
			
	}
	
	function del() {
		$.del("确定删除？", "inspectionInfo/pay/del.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
	
	//查看
	function detail() {
		top.$.dialog({
			width : 800, 
			height : 320, 
			lock : true, 
			title : "查看详情", 
			data : {"window" : window},
			cancel : true,
			content : 'url:app/payment/pay_detail.jsp?status=detail&id=' + Qm.getValueByName("id")
		});
	}
	
	function refreshGrid() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="pay_list" singleSelect="false">
	</qm:qm>
	<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		Qm.config.columnsInfo.device_type.binddata=<u:dict code="device_classify"></u:dict>;
		Qm.config.columnsInfo.device_category.binddata=<u:dict code="device_classify"></u:dict>;
		Qm.config.columnsInfo.check_type.binddata=<u:dict code="BASE_CHECK_TYPE"></u:dict>;
		Qm.config.columnsInfo.data_status.binddata = [
			{id: '0', text: '启用'},
			{id: '99', text: '停用'}
		];
	</script>
</body>
</html>
