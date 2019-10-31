<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<!-- 需要导入的特种设备的公用js -->
<script type="text/javascript"
	src="app/tzsb/inspection/common/js/tzsbMain.js"></script>
<script type="text/javascript">
var infoId = "";
var deviceId = "";
var fkReportId = "";
	var qmUserConfig = {
		sp_fields : [
		{
			label : "基础信息名字",
			name : "name",
			compare : "like",
			value : ""
		},{
			label : "类型",
			name : "type",
			compare : "like",
			value : ""
		} ],
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
		}, 

		{
			text : '修改',
			id : 'modify',
			icon : 'modify',
			click : modify
		}
		,{
			text : '删除',
			id : 'del',
			icon : 'delete',
			click : del
		}
		],
		listeners : {
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					late: count>0,
					modify : count == 1,
					detail : count == 1,
					copy : count == 1,
					del : count > 0,
					addIns:count <=1,
					addSup:count==1
				});
			}
		}
	};
	

	//添加设备
	function add() {
		
			top.$.dialog({
				width : 1000,
				height : 600,
				lock : true,
				title : "添加设备信息",
				content : 'url:rtbox/app/base/base_config_detail.jsp?status=add',
				data : {
					"window" : window
				}
			});
	}

	function modify() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "修改特种设备信息",
			content : 'url:rtbox/app/base/base_config_detail.jsp?status=modify&id='
					+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}
	function del() {
		$.del("确定删除?", "baseConfigAction/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}

	function detail() {
		top.$.dialog({
			width : 1000,
			height : 600,
			lock : true,
			title : "特种设备信息",
			content : 'url:rtbox/app/base/base_config_detail.jsp?status=detail&id='
					+ Qm.getValueByName("id"),
			data : {
				"window" : window
			}
		});
	}


	function loadGridData(treeCode, bigClassify) {
		/* deviceBigClassify = bigClassify;
		treeNode = treeCode;
		if (treeCode != null) {
			Qm.setCondition([ {
				name : "tree_code",
				compare : "like",
				value : treeCode
			} ]);
		} else {
			Qm.setCondition([]);
		}
		Qm.searchData(); */
	}
	function submitAction() {
		Qm.refreshGrid();
	}
</script>
</head>
<body>
	<qm:qm pageid="base_name_config" script="false" singleSelect="false">
	</qm:qm>
</body>
</html>
