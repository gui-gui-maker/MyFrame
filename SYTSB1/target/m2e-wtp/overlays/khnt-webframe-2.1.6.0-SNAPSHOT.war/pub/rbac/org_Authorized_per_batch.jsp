<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>组织机构批量授权配置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var orgTreeManager;
	var unitId = "<sec:authentication property="principal.department.id" htmlEscape="false"/>";
	var unitName = "<sec:authentication property="principal.department.orgName" htmlEscape="false"/>";
	<sec:authorize access="hasRole('unit_administrate')">
		var unitId = "<sec:authentication property="principal.unit.id" htmlEscape="false"/>";
		var unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
	</sec:authorize>
	<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
		var unitId = "";//表示获取最顶层
	</sec:authorize>
	$(function() {
		$(".layout").ligerLayout({
			leftWidth : 250,
			rightWidth : 280,
			space : 5,
			allowLeftCollapse : false,
			allowRightCollapse : false
		});
		var ztreeSetting = {
			data: {
				key : {
					name:"orgName"
				}
			},
			check: {
				enable: true,
				chkboxType: { "Y" : "", "N" : "" }
			} ,
			async: {
				enable: true,
				url : "rbac/org/getSubordinate.do",
				autoParam : ["id=orgid"]
			},
			callback: {
				onNodeCreated : function(event, treeId, treeNode){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					if(treeNode["level"]==0)
						treeNode.icon = "k/kui/images/icons/16/home.png";
					else if (treeNode["level"] == 1)
						treeNode.icon = "k/kui/images/icons/16/org.png";
	            	else
	            		treeNode.icon = "k/kui/images/icons/16/group.png";
					treeObj.updateNode(treeNode);
				}
			}
		};
		if(unitId==""){
			$.getJSON("rbac/org/getTopOrg.do",function(res){
				if(res.success){
					setOrgTree(res.data.id,res.data.orgName,ztreeSetting);
				}else{
					$.ligerDialog.warn("获取机构信息出错！");
				}
			})
		}else{
			setOrgTree(unitId,unitName,ztreeSetting);
		}
	});
	function setOrgTree(unitId,unitName,ztreeSetting){
		$.getJSON("rbac/org/getSubordinate.do?orgid=" + unitId,function(dataList){
				var strData = JSON.stringify(dataList);
				strData = strData.replace(/isexpand/g, "open");
				var zNodes = eval(strData)
				orgTreeManager = $.fn.zTree.init($("#orgtree"),ztreeSetting, [{
				id: unitId,
				orgName: unitName,
				children: zNodes,
				open: true
				}]);
			});
	}
	function authorizedPermission() {
		var sorgs = orgTreeManager.getCheckedNodes();
		if(sorgs.length==0){
			$.ligerDialog.warn("请选择至少一个组织机构！");
			return;
		}
		var orgIds;
		for(var i in sorgs){
			if(i==0) orgIds = sorgs[i].id;
			else orgIds += ("," + sorgs[i].id);
		}
		var perids = getPerStr();
		if(perids==null || perids==""){
			$.ligerDialog.warn("请选择至少一个权限！");
			return;
		}
		$("body").mask("配置执行中，请稍候...");
		$.post("rbac/org/authorizedPerBatch.do", {
			orgIds : orgIds,
			permissions : perids
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
		if (isAdd && $("#orgPers option[value='" + role.id + "']").size() == 0)
			$("#orgPers").append("<option value='" + role.id +  "'>" + role.p_name + "【" + role.remark + "】" + "</option>");
		else
			$("#orgPers option[value='" + role.id + "']").remove();
	}

	//双击select列表，移除被点击的角色项
	function removeRole() {
		var idRange = [];
		$("#orgPers option").each(function() {
			if (this.selected == true)
				$(this).remove();
			else
				idRange.push(this.value);
		});
		var rightWin = $("#rightFrame").get(0).contentWindow.window;
		rightWin.initGridSelectRange();
	}

	//将已选择的角色转换为ID数组，供左边表格动态设置使用
	function getRoleArr() {
		var idRange = [];
		$("#orgPers option").each(function() {
			idRange.push(this.value);
		});
		return idRange;
	}

	function getPerStr() {
		var idRange = "";
		$("#orgPers option").each(function() {
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
	<div class="layout">
		<div position="left" title="组织机构">
			<ul id="orgtree" class="ztree"></ul>
		</div>
		<div position="center">
			<iframe id="rightFrame" frameborder="0" name="rightFrame"
				src="pub/rbac/org_Authorized_per_list.jsp" width="100%"
				height="100%" scrolling="no"></iframe>
		</div>
		<div position="right" title="已选择权限">
			<select id="orgPers" multiple="multiple"
				ondblclick="removeRole()" title="双击项目可移除"
				style="height: 100%; width: 100%; padding: 5px;border:none;">
			</select>
		</div>
	</div>
</body>
</html>
