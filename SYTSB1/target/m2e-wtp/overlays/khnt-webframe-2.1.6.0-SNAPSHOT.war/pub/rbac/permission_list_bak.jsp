<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="k/qm/ligerui/css/paging.css" />
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
				<tbar:toolBar type="ligerToolBar" code="permission_list">
				</tbar:toolBar>
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
					{
		 items : [ "-", {
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
			}, "-", {
				text : '资源配置',
				id : 're_authorized',
				icon : 'settings',
				click : re_authorized
			}, "-", {
				text : '授权给角色',
				id : 'role_authorized',
				icon : 'setting',
				click : role_authorized
			}, "-", {
				text : '查询',
				id : 'search',
				icon : 'search',
				click : loadGridData
			}, "-" ] 
		} 
			</sec:authorize>
				);

		pgrid = $("#grid").ligerGrid({
			columns : [ {
				name : 'id',
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
				minWidth : 400,
				align : "left"
			} ],
			height : "100%",
			tree : {columnName : "name"},
			data : {data : []},
			alternatingRow : true,
			usePager : false,
			rownumbers : true,
			frozen: false,
			root : "data"
		});
		loadGridData();
	});

	function loadGridData() {
		$("body").mask("数据加载。。。");
		var n = $("#p-name").val();
		var c = $("#p-code").val();
		$.post("rbac/permission/getTree.do",{name : n,code : c},function(response){
			if(response.success){
				var gd = clearEmptyChildren(response.data);
				pgrid.loadData({data:gd});
			}else{
				$.ligerDialog.error("数据加载失败！");
			}
			$("body").unmask();
		},"json");
	}

	function clearEmptyChildren(data) {
		for ( var i in data) {
			if (data[i].children==undefined || data[i].children.length == 0){
				data[i] = {
					id : data[i].id,
					name : data[i].name,
					code : data[i].code,
                    remark : data[i].remark
				};
			}
			else{
				data[i].children = clearEmptyChildren(data[i].children);
			}
		}
		return data;
	}

	//新增系统权限
	function add() {
		var pid = "";
		var sr = pgrid.getSelectedRow();
		if (sr != null)
			pid = sr.id;
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "新增系统权限",
			content : 'url:pub/rbac/permission_detail.jsp?status=add&pid='
					+ pid,
			data : {
				window : window
			}
		});
	}

	//修改系统权限
	function modify() {
		var sr = pgrid.getSelectedRow();
		if (sr == null) {
			$.ligerDialog.warn("请选择要修改的数据行！");
			return;
		}
		top.$.dialog({
			width : 600,
			height : 300,
			lock : true,
			title : "修改系统权限",
			data : {
				window : window
			},
			content : 'url:pub/rbac/permission_detail.jsp?status=modify&id='
					+ sr.id
		});
	}

	//删除任务节点功能
	function del() {
		var sr = pgrid.getSelectedRow();
		if (sr == null) {
			$.ligerDialog.warn("请选择要修改的数据行！");
			return;
		}
		
		$.ligerDialog.confirm("删除将会删除自身权限及子权限？</br>注意：删除后不可恢复!", function(r) {
			if (!r)
				return;
			$("body").mask("删除中...")
			$.getJSON("rbac/permission/delete.do?ids=" + sr.id, function(resp) {
				$("body").unmask();
				if (resp.success) {
					pgrid.remove(sr);
				} else
					$.ligerDialog.error("删除失败！<br/>" + resp.msg);
			});
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
			content : 'url:pub/rbac/permission_Authorized_resource.jsp?perId='
					+ sr.id+"&checked=true",
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
			content : 'url:rbac/permission/initAuthorizedRole.do?perId='
					+ sr.id,
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
												<input type="text" id="p-name" class="l-text-field"><div class="l-text-l"></div>
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
