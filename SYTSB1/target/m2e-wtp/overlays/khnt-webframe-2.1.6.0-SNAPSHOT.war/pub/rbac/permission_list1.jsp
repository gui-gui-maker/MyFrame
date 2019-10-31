<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="k/qm/ligerui/css/paging.css" />
<script type="text/javascript">
	var pgrid;
	var ztree;
	var setting = {
		data: {
			key: {
				name: "text",
				url:""
			}
		},
		check: {
			enable: false,
			chkboxType: { "Y" : "ps", "N" : "s" }
		},
		callback: {
			onNodeCreated:function(event, treeId, treeNode){
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				if(treeNode.image!="null" && treeNode.image!="" && treeNode.image!=undefined){
					treeNode.icon = treeNode.image;
					treeObj.updateNode(treeNode)
				}
			}
		}
	};
	$(function() {
		
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
		$("#layout").ligerLayout({
			topHeight : 30,
			allowTopResize : false,
			space : 0
		});
		$.getJSON("rbac/permission/ininAuthorizedResource.do?perId=${param.perId}",function(data){
			var strData = JSON.stringify(data);
			strData = strData.replace(/isexpand/g, "open");
			var zNodes = eval(strData)
			ztree = $.fn.zTree.init($("#tree1"), setting, zNodes);
		});
	});

	function loadGridData() {
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
<style type="text/css">
.ztree * {font-size: 10pt;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
.ztree li ul{ margin:0; padding:0}
.ztree li {line-height:30px;}
.ztree li a {width:200px;height:30px;padding-top: 0px;}
.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
.ztree li a span.button.switch {visibility:hidden}
.ztree.showIcon li a span.button.switch {visibility:visible}
.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
.ztree li span {line-height:30px;}
.ztree li span.button {margin-top: -7px;}
.ztree li span.button.switch {width: 16px;height: 16px;}

.ztree li a.level0 span {font-size: 150%;font-weight: bold;}
.ztree li span.button {background-image:url("./left_menuForOutLook.png"); *background-image:url("./left_menuForOutLook.gif")}
.ztree li span.button.switch.level0 {width: 20px; height:20px}
.ztree li span.button.switch.level1 {width: 20px; height:20px}
.ztree li span.button.noline_open {background-position: 0 0;}
.ztree li span.button.noline_close {background-position: -18px 0;}
.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}
	</style>

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
			<ul id="tree1" class="ztree"></ul>
		</div>
	</div>
</body>
</html>
