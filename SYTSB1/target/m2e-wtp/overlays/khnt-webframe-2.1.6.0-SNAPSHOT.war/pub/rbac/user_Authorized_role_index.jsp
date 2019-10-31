<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="main_head">
<%@include file="/k/kui-base-list.jsp"%>
<title>给用户授权</title>
<script type="text/javascript">
	$(function() {
		$("#layout1").ligerLayout({
			space : 5,
			rightWidth : 350
		});
		$("#userRoles").dblclick(removeRole);
	});

	//用户对应角色授权  
	function authorizedRole() {
		$("body").mask("授权执行中，请稍候...");
		$.post("rbac/user/authorizedRole.do", {
			userId : '${param.userId}',
			roleIds : getUserRoleStr()
		}, function(data) {
			$("body").unmask();
			if (data.success) {
				top.$.notice('授权成功', 3);
				api.close();
			} else {
				$.ligerDialog.error('授权失败');
			}
		});
	}

	//从已选择角色中删除或者添加
	function addOrRemoveRole(isAdd, role) {
		if (isAdd
				&& $("#userRoles option[value='" + role.id + "']").size() == 0)
			$("#userRoles").append(
					"<option value='" + role.id +  "'>" + role.name + "【"
							+ role.remark + "】" + "</option>");
		else
			$("#userRoles option[value='" + role.id + "']").remove();
	}
	//双击select列表，移除被点击的角色项
	function removeAllRole() {
		$("#userRoles option").each(function() {
			$(this).remove();
		});
	}
	//双击select列表，移除被点击的角色项
	function removeRole() {
		var idRange = [];
		$("#userRoles option").each(function() {
			if (this.selected == true)
				$(this).remove();
			else
				idRange.push(this.value);
		});
		rightFrame.Qm.getQmgrid().selectRange("id", idRange);
	}

	//将已选择的角色转换为ID数组，供左边表格动态设置使用
	function getUserRoleArr() {
		var idRange = [];
		$("#userRoles option").each(function() {
			idRange.push(this.value);
		});
		return idRange;
	}

	function getUserRoleStr() {
		var idRange = "";
		$("#userRoles option").each(function() {
			idRange += "," + this.value;
		});
		return idRange ? idRange.substring(1) : "";
	}
</script>
<style type="text/css">
.l-tree .l-tree-icon-none img {
	height: 16px;
	margin: 3px;
	width: 16px;
}
</style>
</head>
<body class="p5">
	<div id="layout1">
		<div position="center">
			<iframe id="rightFrame" frameborder="0"
				src="pub/rbac/user_Authorized_role_list.jsp" name="rightFrame"
				width="100%" height="100%" scrolling="no"></iframe>
		</div>
		<div position="right" title="已选择的角色" class="overflow-auto">
			<select id="userRoles" multiple="multiple" title="双击项目可移除"
				style="height: 100%; width: 100%; padding: 5px;border:none;">
				<c:forEach items="${userRoles}" var="role">
					<option value="${role.id}">${role.name}【${role.remark}】</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>
