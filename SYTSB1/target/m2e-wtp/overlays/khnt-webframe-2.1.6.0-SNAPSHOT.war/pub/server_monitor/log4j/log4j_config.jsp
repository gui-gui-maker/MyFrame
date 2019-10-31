<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Log4j用户配置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var cfgGrid;
	$(function() {
		$("#layout").ligerLayout({
			topHeight: 30,
			space: 0,
			allowTopResize: false
		});
		$("#toolbar").ligerToolBar(
			<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="ligerToolBar" code="run_log4j_cfg">
				</tbar:toolBar>
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
				{
			items : [ "-",{
				id : "save",
				text : "保存",
				icon : "save",
				click : save
			},"-",{
				id : "add",
				text : "添加",
				icon : "add",
				click : add
			},"-",{
				id : "del",
				text : "删除",
				icon : "del",
				click : del
			} ]
		}
			</sec:authorize>
	);
		cfgGrid = $("#content").ligerGrid({
			columns : [ {
				display : '',
				name : 'icon',
				width : 30,
				align : "center",
				isSort : false
			},{
				display : '名称',
				name : 'name',
				width : "80%",
				align : "left",
				editor : {
					type : "text"
				},
				isSort : true
			}, {
				display : '级别',
				name : 'value',
				width : "8%",
				align : "left",
				editor : {
					type : "select",
					ext:{
						emptyOption: false
					},
					data : [ {
						id : "DEBUG",
						text : "DEBUG"
					}, {
						id : "WARN",
						text : "WARN"
					}, {
						id : "INFO",
						text : "INFO"
					}, {
						id : "ERROR",
						text : "ERROR"
					}, {
						id : "TRACE",
						text : "TRACE"
					} ]
				},
				isSort : false
			} ],
			enabledEdit : true,
			width : '100%',
			height : '100%',
			usePager : false,
			data : {
				Rows : []
			}
		});
		$.getJSON("pub/log4j/config/logger.do", function(response) {
			var gridData = [];
			if (response.success && response.data) {
				$.each(response.data, function(k, v) {
					gridData.push({
						icon: "<img src='k/kui/images/icons/16/cog.png' style='margin-top:3px' />",
						name : k,
						value : v
					});
				});
			} else {
				$.ligerDialog.error(response.msg);
			}
			cfgGrid.loadData({
				Rows : gridData
			});
		});
	});

	function save() {
		var d = cfgGrid.getData();
		$.post("pub/log4j/config/saveLogger.do",{config:$.ligerui.toJSON(d)},function(resp){
			if(resp.success)top.$.notice("保存成功！",3);
			else $.ligerDialog.error(resp.msg);
		},"json");
	}
	
	function add(){
		cfgGrid.addRow({icon:"<img src='k/kui/images/icons/16/cog.png' style='margin-top:3px' />",name:"",value:""});
	}
	
	function del(){
		cfgGrid.remove(cfgGrid.getSelectedRow());
	}
</script>
</head>
<body>
	<div id="layout">
		<div position="top" id="toolbar"></div>
		<div position="center" id="content"></div>
	</div>
</body>
</html>