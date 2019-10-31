<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>业务流程配置管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 60
		},
		sp_fields : [ {
			label : "业务编码",
			name : "service_name",
			compare : "=",
			width : 180
		}, {
			label : "单位",
			name : "org_name",
			compare : "like"
		}, {
			label : "工作流",
			name : "flow_name",
			compare : "like"
		} ],
  		<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="tbar" code="bpm_service_flow_config">
				</tbar:toolBar>,
				</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
				tbar : [ {
			text : '增加 ',
			id : 'add',
			icon : 'add',
			click : add
		}, "-", {
			text : '修改 ',
			id : 'modify',
			icon : 'modify',
			click : modify
		}, "-", {
			text : '删除 ',
			id : 'del',
			icon : 'delete',
			click : del
		} ],
            </sec:authorize>
		listeners : {
			rowDblClick : function(rowData, rowIndex) {
				detail(rowData.id);
			},
			selectionChange : function(rowData, rowIndex) {
				var count = Qm.getSelectedCount();//行选择个数
				Qm.setTbar({
					modify : count == 1,
					detail : count == 1,
					del : count >= 1
				});
			}
		}
	};

	function add() {
		top.$.dialog({
			width : 500,
			height : 280,
			lock : true,
			data : {
				window : window
			},
			title : "新增业务流程配置",
			content : 'url:pub/bpm/flow_service_config_detail.jsp?status=add'
		});
	}

	function submitAction() {
		Qm.refreshGrid();
	}

	function loadGridData(flowtype) {
		Qm.setCondition({
			name : "flow_type",
			compare : "=",
			value : flowtype
		});
		submitAction();
	}

	function modify() {
		top.$
				.dialog({
					width : 500,
					height : 280,
					lock : true,
					data : {
						window : window
					},
					title : "修改业务流程配置",
					content : 'url:pub/bpm/flow_service_config_detail.jsp?status=modify&id='
							+ Qm.getValueByName("id")
				});
	}

	function del() {
		$.del(kFrameConfig.DEL_MSG, "bpm/serviceConfig/delete.do", {
			"ids" : Qm.getValuesByName("id").toString()
		});
	}
</script>
</head>
<body class="p0">
	<qm:qm pageid="bpm_8" script="false" singleSelect="false"></qm:qm>
</body>
</html>
