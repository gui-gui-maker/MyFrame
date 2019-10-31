<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>组织机构角色配置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".layout").ligerLayout({
			rightWidth : 330,
			space : 5
		});
	});
	function authorizedRole() {
		var select = getRoleStr();
		$("body").mask("配置执行中，请稍候...");
		$.post("rbac/org/authorizedRole.do", {
			orgId : '${param.orgId}',
			roles : select
		}, function(data) {
			$("body").unmask();
			if (data.success) {
				top.$.notice('配置成功', 3);
				api.close();
			} else {
				$.ligerDialog.error('操作失败');
			}
		});
	}

	//从已选择角色中删除或者添加
	function addOrRemoveRole(isAdd, role) {
		if (isAdd && $("#orgRoles option[value='" + role.id + "']").size() == 0)
			$("#orgRoles").append("<option value='" + role.id +  "'>" + role.name + "【" + role.remark + "】" + "</option>");
		else
			$("#orgRoles option[value='" + role.id + "']").remove();
	}

	//双击select列表，移除被点击的角色项
	function removeRole() {
		var idRange = [];
		$("#orgRoles option").each(function() {
			if (this.selected == true)
				$(this).remove();
			else
				idRange.push(this.value);
		});
		rightFrame.Qm.getQmgrid().selectRange("id", idRange);
	}

	//将已选择的角色转换为ID数组，供左边表格动态设置使用
	function getRoleArr() {
		var idRange = [];
		$("#orgRoles option").each(function() {
			idRange.push(this.value);
		});
		return idRange;
	}

	function getRoleStr() {
		var idRange = "";
		$("#orgRoles option").each(function() {
			idRange += "," + this.value;
		});
		return idRange ? idRange.substring(1) : "";
	}
</script>
</head>
<body class="p5">
	<div class="layout">
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="rightFrame"
				src="pub/rbac/org_Authorized_role_list.jsp" width="100%"
				height="100%" scrolling="no"></iframe>
		</div>
		<div position="right" title="已选择角色">
			<select id="orgRoles" multiple="multiple"
				ondblclick="removeRole()" title="双击项目可移除"
				style="height: 100%; width: 100%; padding: 5px;border:none;">
				<c:forEach var="role" items="${roles}">
					<option value="${role.id}">${role.name}【${role.remark}】</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>
