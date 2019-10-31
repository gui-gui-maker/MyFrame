<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/k/kui-base-list.jsp"%>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_fields: [
					{name:"p_name",compare:"like"},
					{name:"p_code",compare:"like"},
					{name:"remark",compare:"like"}
				],
				<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="tbar" code="permission_list" />,
				</sec:authorize>
				<sec:authorize access="hasRole('super_administrate')">
					tbar:[{
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
					}],
				</sec:authorize>
				listeners : {
					pageSizeOptions: [10, 20, 30, 40, 50,100,500,1000],
					tree : {
						columnId : 'p_name',
						idField : 'id',
						parentIDField : 'parent_id'
					},
					selectionChange : function(rowData, rowIndex) {
						var count = Qm.getSelectedCount();
						Qm.setTbar({
							re_authorized: count==1,
							modify: count==1,
							role_authorized: count==1,
							del: count>0
						});
					},
					rowDblClick : function(rowData, rowIndex) {
						detail(rowData.id);
					}
				}
			};
			
			function loadGridData(){
				Qm.refreshGrid();
			}
			//新增系统权限
			function add() {
				top.$.dialog({
					width : 600,
					height : 300,
					lock : true,
					title : "新增系统权限",
					content : 'url:pub/rbac/permission_detail.jsp?status=add&pid='
							+ Qm.getValueByName("id"),
					data : {
						window : window
					}
				});
			}
			//修改系统权限
			function modify() {
				top.$.dialog({
					width : 600,
					height : 300,
					lock : true,
					title : "修改系统权限",
					data : {
						window : window
					},
					content : 'url:pub/rbac/permission_detail.jsp?status=modify&id='
							+ Qm.getValueByName("id")
				});
			}

			//删除任务节点功能
			function del() {
				$.ligerDialog.confirm("删除将会删除自身权限及子权限？</br>注意：删除后不可恢复!", function(r) {
					if (!r)
						return;
					$("body").mask("删除中...")
					$.getJSON("rbac/permission/delete.do?ids=" + Qm.getValueByName("id"), function(resp) {
						$("body").unmask();
						if (resp.success) {
							Qm.refreshGrid();
						} else
							$.ligerDialog.error("删除失败！<br/>" + resp.msg);
					});
				});
			}
			
			//资源授权
			function re_authorized() {
				top.$.dialog({
					width : 450,
					height : $(top).height(),
					lock : true,
					title : "菜单资源配置",
					content : 'url:pub/rbac/permission_Authorized_resource.jsp?perId='
							+ Qm.getValueByName("id")+"&checked=true",
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
				top.$.dialog({
					width : 1000,
					height : 450,
					lock : true,
					title : "所属角色配置",
					content : 'url:rbac/permission/initAuthorizedRole.do?perId='
							+ Qm.getValueByName("id"),
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
		<qm:qm pageid="sys_permission" script="false" singleSelect="true" pagesize="1000"/>
	</body>
</html>