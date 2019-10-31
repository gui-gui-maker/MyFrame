<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户角色配置</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [{
			name : "name", compare : "like", columnWidth:0.45,labelWidth:60
		},{
			name : "remark", compare : "like", columnWidth:0.45,labelWidth:60
		}],
		listeners : {
			onAfterShowData : initGridSelectRange,
			onCheckRow : function(checked, rowdata, rowindex, rowDomElement){
				parent.addOrRemoveRole(checked,rowdata);
			},
			onCheckAllRow: function(checked,row){
				var data = Qm.getQmgrid().getData();
				for(var i in data){
					parent.addOrRemoveRole(checked, data[i]);
				}
			}
		}
	};
	
	//表格渲染时，将被选择的角色勾选
	function initGridSelectRange(){
		var idRange = parent.getUserRoleArr();
		Qm.getQmgrid().selectRange("id", idRange);
	}
</script>
</head>
<body class="p0">
	<sec:authorize ifAnyGranted="sys_administrate,super_administrate">
		<qm:qm pageid="sys_role" singleSelect="false">
			<qm:param name="isdefault" value="0" compare="=" />
		</qm:qm>
	</sec:authorize>
	<sec:authorize ifNotGranted="sys_administrate,super_administrate">
		<sec:authorize ifAllGranted="unit_administrate">
			<qm:qm pageid="sys_org_role" singleSelect="false">
				<qm:param name="isdefault" value="0" compare="=" />
			</qm:qm>
		</sec:authorize>
		<sec:authorize ifNotGranted="unit_administrate"
			ifAllGranted="dep_administrate">
			<qm:qm pageid="sys_orgdep_role" singleSelect="false">
				<qm:param name="isdefault" value="0" compare="=" />
			</qm:qm>
		</sec:authorize>
	</sec:authorize>
	<script>
	Qm.config.columnsInfo.isdefault.visible="0";
	Qm.config.columnsInfo.remark.width=500;
	Qm.config.columNum=true;
	</script>
</body>
</html>
