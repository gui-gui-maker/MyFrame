<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限角色配置</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults : {
			labelWidth : 60,
			columnWidth : 0.45
		},
		sp_fields : [ {
			name : "name",
			compare : "like"
		}, {
			name : "remark",
			compare : "like"
		} ],
		listeners : {
			onAfterShowData : function() {
				initGridSelectRange();
			},
			onCheckRow : function(checked, rowdata, rowindex, rowDomElement) {
				parent.addOrRemoveRole(checked, rowdata);
			},
			onCheckAllRow:function(checked,row){
				var data = Qm.getQmgrid().getData();
				for(var i in data){
					parent.addOrRemoveRole(checked, data[i]);
				}
			}
		}
	};

	//表格渲染时，将被选择的角色勾选
	function initGridSelectRange() {
		var idRange = parent.getPermissionRoleArr();
		Qm.getQmgrid().selectRange("id", idRange);
	}
</script>
</head>
<body class="p0">
	<sec:authorize ifAnyGranted="super_administrate,sys_administrate">
		<qm:qm pageid="sys_role" singleSelect="false" />
	</sec:authorize>
	<sec:authorize ifNotGranted="super_administrate,sys_administrate">
		<qm:qm pageid="sys_org_role" singleSelect="false" />
	</sec:authorize>
</body>
</html>
