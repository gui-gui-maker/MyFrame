﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>为用户授权角色</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var orgTreeManager = null;
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 230,
			rightWidth : 245,
			sapce : 5
		});
		
		//当前用户机构信息
		var unitId = "<sec:authentication property="principal.department.id" />";
		var unitName = "<sec:authentication property="principal.department.orgName" htmlEscape="false"/>";
		var levelCode = "<sec:authentication property="principal.department.levelCode" />";
		<sec:authorize access="hasRole('unit_administrate')">
			unitId = "<sec:authentication property="principal.unit.id" />";
			unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
			levelCode = "<sec:authentication property="principal.unit.levelCode" />";
		</sec:authorize>
		<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
			unitId = "";
		</sec:authorize>
		if(unitId==""){
			$.getJSON("rbac/org/getTopOrg.do",function(res){
				if(res.success){
					setOrgTree(res.data.id,res.data.orgName,res.data.levelCode)
				}else{
					$.ligerDialog.warn("获取机构信息出错！");
				}
			});
		}else{
			setOrgTree(unitId,unitName,levelCode);
		}
		//右侧列表框选择双击事件（移除被双击的项目）
		$("#roleUsers").dblclick(removeUser);
	});
	
	function setOrgTree(unitId,unitName,levelCode){
		//获取机构数据
		$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId, function(dataList) {
			//创建机构树
			orgTreeManager = $("#tree1").ligerTree({
				checkbox : false,
				iconFieldName : "level",
				iconParse : function(data) {
					if (data["level"] == 0)
						return "k/kui/images/icons/16/home.png";
					else if (data["level"] == 1)
						return "k/kui/images/icons/16/org.png";
					else
						return "k/kui/images/icons/16/group.png";
				},
				data : [{
					id : unitId,
					text : unitName,
					levelCode : levelCode,
					level : "0",
					children : dataList
				}],
				onSelect : function(node) {
					var win = $("#rightFrame").get(0).contentWindow.window;
					if(win.loadGridData) win.loadGridData(node.data.levelCode);
				},
				onBeforeExpand : function(node) {
					if (unitId != node.data.id && !node.data.expandChild){
						this.loadData(node.data, "rbac/org/getSubordinate.do?orgid=" + node.data.id);
						node.data.expandChild = true;
					}
				}
			});
			//初始化右侧iframe中用户列表，默认为本级机构向下所有用户
			$("#rightFrame").attr("src","pub/rbac/role_Authorized_user_list.jsp?levelCode=" + levelCode);
		});
	}

	//角色与用户授权
	function authorizedUser() {
		$("body").mask("授权执行中，请稍候...");
		$.post("rbac/role/authorizedUser.do", {
			roleId : '${param.roleId}',
			userIds : getRoleUserStr()
		}, function(data) {
			$("body").unmask();
			if (data.success) {
				top.$.notice('授权成功', 3);
				api.close();
			} else {
				$.ligerDialog.success('授权失败!');
			}
		});
	}

	//从已选择角色中删除或者添加
	function addOrRemoveUser(isAdd, user) {
		if (isAdd && $("#roleUsers option[value='" + user.id + "']").size() == 0)
			$("#roleUsers").append(
					"<option value='" + user.id +  "'>" + user.name + "【" + user.org_name + "】" + "</option>");
		else
			$("#roleUsers option[value='" + user.id + "']").remove();
	}

	//双击select列表，移除被点击的角色项
	function removeUser() {
		var idRange = [];
		$("#roleUsers option").each(function() {
			if (this.selected == true)
				$(this).remove();
			else
				idRange.push(this.value);
		});
		rightFrame.Qm.getQmgrid().selectRange("id", idRange);
	}

	//将已选择的角色用户转换为ID数组，供左边表格动态设置使用
	function getRoleUserArr() {
		var idRange = [];
		$("#roleUsers option").each(function() {
			idRange.push(this.value);
		});
		return idRange;
	}

	function getRoleUserStr() {
		var idRange = "";
		$("#roleUsers option").each(function() {
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
		<div position="left" title="组织机构" class="overflow-auto">
			<ul id="tree1"></ul>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="rightFrame" src="" width="100%" height="100%" scrolling="no"></iframe>
		</div>
		<div position="right" title="已选择用户">
			<select id="roleUsers" multiple="multiple" title="双击项目可移除"
				style="height: 100%; width: 100%; padding: 5px;border:none;">
				<c:forEach items="${roleUsers}" var="user">
					<option value="${user.id}">${user.name}【${user.org.orgName}】</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>
