﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>菜单授权管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="k/qm/ligerui/css/paging.css" />
<script type="text/javascript">
	var manager = null;
	var pgrid = null;
	$(function() {
		//页面布局
		$("#layout1").ligerLayout({
			leftWidth : 240,
			allowTopResize : false,
			topHeight : 30,
			rightWidth : 235,
			space : 3
		});
		//菜单树
		manager = $("#tree1").ligerTree({
			checkbox : false,
			selectCancel : false,//第二次点击节点不取消选择
			url : 'rbac/resource/transformTree.do?id=root',
			onSelect : initPermisstionList
		});
		//按钮工具栏
		$("#toptoolbar").ligerToolBar({
			items : [ "-", {
				text : '授权',
				click : doAuthorizedPermission,
				icon : 'setting',
				id : "authorized"
			} ]
		});
		pgrid = $("#grid").ligerGrid({
			columns : [ {
				name : 'id',
				id : 'id',
				minWidth : 200,
				hide : true
			}, {
				display : '权限名称',
				name : 'name',
				minWidth : 200,
				align : "left"
			}, {
				display : '权限代码',
				name : 'code',
				minWidth : 200,
				align : "left"
			}, {
				display : '描述',
				name : 'remark',
				minWidth : 200,
				align : "left"
			} ],
			height : "100%",
			tree : {
				columnName : "name"
			},
			data : {
				data : []
			},
			checkbox : true,
			alternatingRow : true,
			usePager : false,
			autoCheckChildren : false,
			rownumbers : true,
			frozen : false,
			root : "data",
			onCheckRow : function(checked, rowdata, rd, dom) {
				addOrRemovePermision(checked, rowdata);
			},
			onCheckAllRow:function(checked,row){
				removeAllPermision();
				if(checked){
					var data = pgrid.getData();
					for(var i in data){
						addOrRemovePermision(checked, data[i]);
					}
				}
			}
		});
		loadGridData();
	});

	function loadGridData() {
		$("body").mask("数据加载。。。");
		var n = $("#p-name").val();
		var c = $("#p-code").val();
		$.post("rbac/permission/getTree.do", {
			name : n,
			code : c
		}, function(response) {
			if (response.success) {
				var gd = clearEmptyChildren(response.data);
				pgrid.loadData({
					data : gd
				});
				initGridSelectRange(getResourcePermissionArr());
			} else {
				$.ligerDialog.error("数据加载失败！");
			}
			$("body").unmask();
		},"json");
	}

	function clearEmptyChildren(data) {
		for ( var i in data) {
			if (data[i].children == undefined || data[i].children.length == 0) {
				data[i] = {
					id : data[i].id,
					name : data[i].name,
					code : data[i].code
				};
			} else {
				data[i].children = clearEmptyChildren(data[i].children);
			}
		}
		return data;
	}

	/**
	 * 选择数据，这将会只选择参数指定的数据，其他都取消选择
	 *
	 * @param ids 要选择或取消选择的数据id
	 */
	function initGridSelectRange(ids) {
		var datas = pgrid.getData();
		for ( var i in datas) {
			if (ids.length == 0)
				pgrid.unselect(i);
			for (var j = 0; j < ids.length; j++) {
				if (datas[i]["id"] == ids[j]){
					pgrid.select(i);
					break;
				}else{
					pgrid.unselect(i);
				}
			}
		}
	}

	//权限表加载
	function initPermisstionList(node) {
		$("#resourcePermissions").html("");
		if (node == null) {
			initGridSelectRange([]);
		} else {
			$("body").mask("正在初始化列表...");
			$.getJSON("rbac/resource/initResourcePermission.do?resourceId="
					+ node.data.id, function(response) {
				if (response.success, response.data) {
					var pids = [];
					$.each(response.data, function() {
						$("#resourcePermissions").append(
								"<option value='" + this.id +  "'>" + this.name
										+ "</option>");
						pids.push(this.id);
					});
					initGridSelectRange(pids);
				}
				$("body").unmask();
			});
		}
	}

	//授权
	function doAuthorizedPermission() {
		var node = manager.getSelected();
		var pid = getResourcePermissionStr();
		if (node == null) {
			$.ligerDialog.warn('请选择菜单项！');
			return;
		}
		$("body").mask("授权执行中，请稍候...");
		$.post("rbac/resource/authorizedPermission.do", {
			pIds : pid.toString(),
			resourceId : node.data.id
		}, function(data) {
			$("body").unmask();
			if (data.success)
				top.$.notice('授权成功', 3);
			else
				$.ligerDialog.error('设置失败');
		}, "json");
	}

	//从已选择角色中删除或者添加
	function addOrRemovePermision(isAdd, permission) {
		if (isAdd
				&& $(
						"#resourcePermissions option[value='" + permission.id
								+ "']").size() == 0)
			$("#resourcePermissions").append(
					"<option value='" + permission.id +  "'>" + permission.name
							+ "</option>");
		else
			$("#resourcePermissions option[value='" + permission.id + "']")
					.remove();
	}

	//双击select列表，移除被点击的角色项
	function removeAllPermision() {
		$("#resourcePermissions option").each(function() {
			$(this).remove();
		});
	}
	//双击select列表，移除被点击的角色项
	function removePermision() {
		var idRange = [];
		$("#resourcePermissions option").each(function() {
			if (this.selected == true)
				$(this).remove();
			else
				idRange.push(this.value);
		});
		initGridSelectRange(idRange);
	}

	//将已选择的角色转换为ID数组，供左边表格动态设置使用
	function getResourcePermissionArr() {
		var idRange = [];
		$("#resourcePermissions option").each(function() {
			idRange.push(this.value);
		});
		return idRange;
	}

	function getResourcePermissionStr() {
		var idRange = "";
		$("#resourcePermissions option").each(function() {
			idRange += "," + this.value;
		});
		return idRange ? idRange.substring(1) : "";
	}
</script>
</head>
<body class="p5">
	<div id="layout1">
		<div id="toptoolbar" position="top"></div>
		<div position="left" title="菜单" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<div id="qm-search-wrap">
				<form onsubmit="loadGridData();return false;">
					<table id="qm-search-p" class="qm-search-table" width="100%">
						<tr>
							<td class="qm-search-table-td1"><div class="column" style="width: 45%;">
									<div class="label-left" style="width: 80px; text-align: right;">权限名称</div>
									<div class="field-left-column" style="width: auto; margin: 3px 3px 3px 83px;">
										<div class="l-text-wrapper">
											<div class="l-text">
												<input type="text" id="p-name" class="l-text-field">
													<div class="l-text-l"></div>
													<div class="l-text-r"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="column" style="width: 45%;">
									<div class="label-left" style="width: 80px; text-align: right;">权限代码</div>
									<div class="field-left-column" style="width: auto; margin: 3px 3px 3px 83px;">
										<div class="l-text-wrapper">
											<div class="l-text">
												<input type="text" id="p-code" class="l-text-field">
													<div class="l-text-l"></div>
													<div class="l-text-r"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="column">
									<input type="submit" value="" style="border: none;" />
								</div></td>
							<td class="qm-search-table-td2"><a onclick="loadGridData();" class="l-button"><span
									class="l-button-main l-button-hasicon"><span class="l-button-text">查询</span><span
										class="l-button-icon l-icon-search-list"></span></span></a></td>
						</tr>
					</table>
				</form>
			</div>
			<div position="center" id="grid"></div>
		</div>
		<div position="right" title="已选择权限">
			<select id="resourcePermissions" multiple="multiple" ondblclick="removePermision()" title="双击项目可移除"
				style="height: 100%; width: 100%; padding: 5px; border: none;">
			</select>
		</div>
	</div>
</body>
</html>
