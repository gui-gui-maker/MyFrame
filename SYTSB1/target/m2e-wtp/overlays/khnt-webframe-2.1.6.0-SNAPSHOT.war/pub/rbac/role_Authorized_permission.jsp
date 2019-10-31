<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>为角色配置权限</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".layout").ligerLayout({
			rightWidth : 255,
			space : 5
		});
		$("#rightFrame").attr("src","pub/rbac/role_Authorized_permission_list.jsp");
		$("#rolePermissions").dblclick(removePermision);
	});
	
	
	//执行授权
	function authorizedRole() {
		var select = getRolePermissionStr();
		$("body").mask("授权执行中，请稍候...");
		$.post("rbac/role/authorizedPermission.do", {
			roleId : '${param.roleId}',
			pIds : select
		}, function(data) {
			$("body").unmask();
			if (data.success) {
				top.$.notice('授权成功', 3);
				api.close();
			} else {
				$.ligerDialog.error('设置失败');
			}
		});
	}
	
	//从已选择角色中删除或者添加
	function addOrRemovePermision(isAdd, permission) {
		if (isAdd && $("#rolePermissions option[value='" + permission.id + "']").size() == 0)
			$("#rolePermissions").append("<option value='" + permission.id +  "'>" + permission.name + "</option>");
		else
			$("#rolePermissions option[value='" + permission.id + "']").remove();
	}
	//双击select列表，移除被点击的角色项
	function removeAllPermision() {
		$("#rolePermissions option").each(function() {
			$(this).remove();
		});
	}
	//双击select列表，移除被点击的角色项
	function removePermision() {
		var idRange = [];
		$("#rolePermissions option").each(function() {
			if (this.selected == true) $(this).remove();
			else idRange.push(this.value);
		});
		var rightWin = $("#rightFrame").get(0).contentWindow.window;
		rightWin.initGridSelectRange();
	}
	
	//将已选择的角色转换为ID数组，供左边表格动态设置使用
	function getRolePermissionArr() {
		var idRange = [];
		$("#rolePermissions option").each(function() {
			idRange.push(this.value);
		});
		return idRange;
	}

	function getRolePermissionStr() {
		var idRange = "";
		$("#rolePermissions option").each(function() {
			idRange += "," + this.value;
		});
		return idRange ? idRange.substring(1) : "";
	}
	
</script>
</head>
<body class="p5">
	<div class="layout">
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="rightFrame" src="" width="100%" height="100%" scrolling="no"></iframe>
		</div>
		<div position="right" title="已选择的权限">
			<select id="rolePermissions" multiple="multiple" title="双击项目可移除"
				style="width: 100%; height: 100%; padding: 5px; border: none;" ondblclick="removePermision()">
				<c:forEach var="permission" items="${rPermission}">
					<option value="${permission.id}">${permission.name}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>
