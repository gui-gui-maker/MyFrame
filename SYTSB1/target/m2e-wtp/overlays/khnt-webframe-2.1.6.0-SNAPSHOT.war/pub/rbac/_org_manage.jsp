<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
<title>组织机构管理</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>

<script type="text/javascript">
	var unitId = "<sec:authentication property="principal.department.id" htmlEscape="false"/>";
	var unitName = "<sec:authentication property="principal.department.orgName" htmlEscape="false"/>";
	var orgCode = "<sec:authentication property="principal.department.orgCode" />";
	var sort = 	"<sec:authentication property="principal.department.sort" />";
	var utellphone = "<sec:authentication property="principal.department.tellphone" />";
	var tellphone = utellphone=="null"?"":utellphone;
	var property = "<sec:authentication property="principal.department.property" />";
	var type = "<sec:authentication property="principal.department.type" />";
	var areaName = "<sec:authentication property="principal.department.areaName" />";
	<sec:authorize access="hasRole('unit_administrate')">
		unitId = "<sec:authentication property="principal.unit.id" htmlEscape="false"/>";
		unitName = "<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>";
		orgCode = "<sec:authentication property="principal.unit.orgCode" />";
		sort = 	"<sec:authentication property="principal.unit.sort" />";
		utellphone = "<sec:authentication property="principal.unit.tellphone" />";
		tellphone = utellphone=="null"?"":utellphone;
		property = "<sec:authentication property="principal.unit.property" />";
		type = "<sec:authentication property="principal.department.type" />";
	    areaName = "<sec:authentication property="principal.department.areaName" />";
	</sec:authorize>
	<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
		unitId = "";//表示获取最顶层
	</sec:authorize>
	
	$(function(){
		// 工具按钮栏
		$("#toptoolbar").ligerToolBar(
			<sec:authorize ifNotGranted="super_administrate">
				<tbar:toolBar type="ligerToolBar" code="org_manage_division" />
			</sec:authorize>
			<sec:authorize access="hasRole('super_administrate')">
			{
			items : [ {
				text : '新增',
				click : newOrg,
				icon : 'add'
			}, "-", {
				text : '修改',
				click : modify,
				icon : "modify"
			}, "-", {
				text : '删除',
				click : remove,
				icon : "del"
			}, "-", {
				text : '调整',
				click : config,
				icon : "config"
			}, "-", {
				id: "op",
				text : '机构权限',
				click : authorizePer,
				icon : "setting"
			} , "-", {
				id: "opb",
				text : '批量机构权限',
				click : authorizePerBatch,
				icon : "setting"
			}  , "-", {
				id: "or",
				text : '机构角色',
				click : authorizeRole,
				icon : "setting"
			} , "-", {
				id: "orb",
				text : '批量机构角色',
				click : authorizeRoleBatch,
				icon : "setting"
			} ]
			} 
			</sec:authorize>
		);
	});
	var _org_division = '${param.division}';
</script>
<script type="text/javascript" src="pub/rbac/js/org_manage.js"></script>
<style type="text/css">
    .l-tree .l-tree-icon-none img {
        height: 18px;
        margin: 4px;
        width: 16px;
    }
</style>
</head>
<body class="p5">
	<div id="layout1">
		<div position="top" id="toptoolbar"></div>
		<div position="left" title="组织机构" style="overflow:auto;">
			<ul id="tree1" class="ztree"></ul>
		</div>
		<div position="center">
			<form name="form1" id="form1" method="post">
				<table class="l-detail-table">
					<tr>
						<td class="l-t-td-left">编号：</td>
						<td class="l-t-td-right"><input name="orgCode" type="text" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">全称：</td>
						<td class="l-t-td-right"><input name="orgName" type="text" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">简称：</td>
						<td class="l-t-td-right"><input name="simpleName" type="text" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">快速检索码：</td>
						<td class="l-t-td-right"><input name="searchCode" type="text" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">联系电话：</td>
						<td class="l-t-td-right"><input name="tellphone" type="text" ltype='text' /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">机构性质：</td>
						<td class="l-t-td-right"><u:combo name="property" code="sys_org_property" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">单位类型：</td>
						<td class="l-t-td-right"><u:combo name="type" code="pub_org_type" /></td>
					</tr>
					<c:if test="${param.division=='yes'}"><tr>
						<td class="l-t-td-left">行政区划：</td>
						<td class="l-t-td-right"><input name="areaName" type="text" ltype='text' /></td>
					<tr></c:if>
					<tr>
						<td class="l-t-td-left">排序：</td>
						<td class="l-t-td-right"><input name="sort" type="text"
							ltype='text' validate="{required:false,maxlength:8}" /></td>
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right"><textarea name="discrible" class="l-textarea"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
