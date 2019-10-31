<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户临时角色配置</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields : [ {
			name : "name", compare : "like", columnWidth:0.45,labelWidth:60
		}, {
			name : "remark", compare : "like", columnWidth:0.45,labelWidth:60
		}]
	};
	
	//表格渲染时，将被选择的角色勾选
	function authorizedRole(){
		var rids = Qm.getValuesByName("id");
		if(rids.length==0){
			$.ligerDialog.warn("请选择角色！");
			return;
		}
		$.ligerDialog.prompt("请设置临时权限有效期！",function(isOk,days){
			if(!isOk) return;
			if(!/^[1-9]+$/.test(days)){
				$.ligerDialog.warn("请输入大于0的整数！");
				return;
			}
			$.post("rbac/user/authorizedRole.do",{
				userId: "${param.userId}",
				roleIds: rids.toString(),
				isTemp: true,
				tempDays: days
			},function(resp){
				if(resp.success){
					top.$.notice("设置成功。<br/>系统将会在"+days+"天后自动回收该角色！",5);
					api.close();
				}else{
					$.ligerDialog.error("操作失败，请稍后重试！");
				}
			},"json");
		});
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
