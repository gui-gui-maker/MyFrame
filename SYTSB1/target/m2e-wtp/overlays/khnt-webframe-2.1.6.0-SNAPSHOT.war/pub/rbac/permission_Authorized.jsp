<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限授权</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="k/qm/ligerui/css/paging.css" />
<script type="text/javascript" src="k/kui/frame/ligerGrid-1.2.3.js"></script>
<script type="text/javascript">
	var pgrid;
	$(function() {
		$("#layout").ligerLayout({
			topHeight : 30,
			allowTopResize : false,
			space : 0
		});
		$("#toolbar").ligerToolBar(
			<sec:authorize ifNotGranted="super_administrate">
			<tbar:toolBar type="ligerToolBar" code="permissionAuthorized"/>
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">{
			items : [{
				text : '资源配置',
				id : 're_authorized',
				icon : 'settings',
				click : re_authorized
			},"-", {
				text : '授权给角色',
				id : 'role_authorized',
				icon : 'setting',
				click : role_authorized
			}, "->", "-", {
				text : '查询',
				id : 'search',
				icon : 'search',
				click : loadGridData
			}, "-" ]
			}</sec:authorize>);

		pgrid = $("#grid").ligerGrid({
			columns : [ {
				name : 'id',
				minWidth : 200,
				hide : true
			}, {
				display : '权限名称',
				id : 'name',
				name : 'name',
				minWidth : 200,
				align : "left"
			}, {
				display : '权限代码',
				name : 'url',
				minWidth : 200,
				align : "left"
			}, {
				display : '描述',
				name : 'count',
				minWidth : 400,
				align : "left"
			} ],
			height : "100%",
			tree : {
				columnId : 'name',
	            idField: 'id',
	            parentIDField: 'pid'
			},
			url: "pub/wrv/visit_stsc.do",
			alternatingRow : true,
			usePager : false,
			rownumbers : true,
			frozen : false,
			rowHeight: 22,
	        headerRowHeight: 22
		});
	});

	function loadGridData() {
		$("body").mask("数据加载。。。");
		var n = $("#p-name").val();
		var c = $("#p-code").val();
		$.getJSON("<sec:authorize ifAnyGranted="sys_administrate,super_administrate">rbac/permission/getLinear.do</sec:authorize><sec:authorize ifNotGranted="sys_administrate,super_administrate">rbac/permission/getUserLinear.do</sec:authorize>", {
			name : n,
			code : c
		}, function(response) {
			if (response.success) {
				pgrid.loadData(response);
			} else {
				$.ligerDialog.error("数据加载失败！");
			}
			$("body").unmask();
		});
	}

	//资源授权
	function re_authorized() {
		var sr = pgrid.getSelectedRow();
		if (sr == null) {
			$.ligerDialog.alert("请选择一个权限！");
			return;
		}
		top.$.dialog({
			width : 450,
			height : $(top).height(),
			lock : true,
			title : "菜单资源配置",
			content : 'url:pub/rbac/permission_Authorized_resource.jsp?perId=' + sr.id+"&checked=true",
			ok : function(w) {
				var iframe = this.iframe.contentWindow;
				iframe.authorizedResource();
				return false;
			},
			cancel : true
		});
	}

	//所属角色授权
	function role_authorized() {
		var sr = pgrid.getSelectedRow();
		if (sr == null) {
			$.ligerDialog.alert("请选择一个权限！");
			return;
		}
		top.$.dialog({
			width : 1000,
			height : 450,
			lock : true,
			title : "所属角色配置",
			content : 'url:rbac/permission/initAuthorizedRole.do?perId=' + sr.id,
			ok : function(w) {
				var iframe = this.iframe.contentWindow;
				iframe.authorizedRole();
				return false;
			},
			cancel : true
		});
	}
</script>
</head>
<body>
	<div id="layout">
		<div position="top" id="toolbar"></div>
		<div position="center">
			<div id="qm-search-wrap">
				<form onsubmit="loadGridData();return false;">
					<table id="qm-search-p" class="qm-search-table" width="100%">
						<tr>
							<td class="qm-search-table-td1"><div class="column" style="width: 30%;">
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
								<div class="column" style="width: 30%;">
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
								<div class="column" style="width: 30%;">
									<input type="submit" value="" style="border: none;" />
								</div></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="grid"></div>
		</div>
	</div>
</body>
</html>
