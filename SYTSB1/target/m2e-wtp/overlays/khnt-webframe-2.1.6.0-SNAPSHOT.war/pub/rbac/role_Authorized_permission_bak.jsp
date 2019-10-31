<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>为角色配置权限</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="k/qm/ligerui/css/paging.css" />
<script type="text/javascript" src="k/kui/frame/ligerGrid-1.2.3.js"></script>
<script type="text/javascript">
var pgrid;
	$(function() {
		$(".layout").ligerLayout({
			rightWidth : 255,
			space : 5
		});
		pgrid = $("#grid").ligerGrid({
			columns : [ {
				name : 'id',
				id : 'id',
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
				name : 'code',
				minWidth : 200,
				align : "left"
			}, {
				display : '描述',
				name : 'remark',
				minWidth : 200,
				align : "left"
			} ],
			height : "99%",
			tree : {
				columnId : 'name',
	            idField: 'id',
	            parentIDField: 'pid'
			},
			rowHeight: 22,
	        headerRowHeight: 22,
	        data:{data:[]},
	        checkbox : true,
			alternatingRow : true,
			usePager : false,
			autoCheckChildren : false,
			rownumbers : true,
			frozen : false,
			root : "data",
			onCheckRow : function(checked, rowdata, rd, dom) {
				addOrRemovePermision(checked, {id:rowdata.id,name:rowdata.p_name});
			},
			onCheckAllRow:function(checked,row){
				removeAllPermision();
				if(checked){
					var data = pgrid.getData();
					for(var i in data){
						addOrRemovePermision(checked, {id:data[i].id,name:data[i].p_name});
					}
				}
			}
		});
		$("#rolePermissions").dblclick(removePermision);
		loadGridData();
	});

	function loadGridData() {
		$("body").mask("数据加载。。。");
		var n = $("#p-name").val();
		var c = $("#p-code").val();
		var url = "<sec:authorize ifAnyGranted="sys_administrate,super_administrate">rbac/permission/getLinear.do</sec:authorize><sec:authorize ifNotGranted="sys_administrate,super_administrate"><sec:authorize ifAllGranted="unit_administrate">rbac/permission/getOrgLinearByUnit.do</sec:authorize><sec:authorize ifNotGranted="unit_administrate" ifAllGranted="dep_administrate">rbac/permission/getOrgLinearByDept.do</sec:authorize></sec:authorize>";
		$.getJSON(url, {
			name : n,
			code : c
		}, function(response) {
			if (response.success) {
				pgrid.loadData(response);
				//初始化加载左边check状态
				var tempp = getRolePermissionArr();
				initGridSelectRange(tempp);
			} else {
				$.ligerDialog.error("数据加载失败！");
			}
			$("body").unmask();
		});
	}
	// 初始化时将授权的权限选中
	function initGridSelectRange(ids) {
		var datas = pgrid.getData();
        for (var i in datas) {
        	if(ids.length==0)pgrid.unselect(i);
            for (var j = 0; j < ids.length; j++) {
                if (datas[i]["id"] == ids[j]) pgrid.select(i);
            }
        }
	}
	
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
		initGridSelectRange(idRange);
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
<body>
	<div class="layout">
		<div position="center">
			<div id="qm-search-wrap">
				<form onsubmit="loadGridData();return false;">
					<table id="qm-search-p" class="qm-search-table" width="100%">
						<tr>
							<td class="qm-search-table-td1"><div class="column" style="width: 45%;">
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
								<div class="column" style="width: 45%;">
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
								<div class="column">
									<input type="submit" value="" style="border: none;" />
								</div></td>
							<td class="qm-search-table-td2"><a onclick="loadGridData();" class="l-button"><span
									class="l-button-main l-button-hasicon"><span class="l-button-text">查询</span><span
										class="l-button-icon l-icon-search-list"></span></span></a></td>
						</tr>
					</table>
				</form>
			</div>
			<div position="center" id="grid"></div>
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
