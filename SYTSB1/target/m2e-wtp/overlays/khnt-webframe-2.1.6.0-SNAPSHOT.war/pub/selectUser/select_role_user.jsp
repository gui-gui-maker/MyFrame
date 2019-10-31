<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>机构人员列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_fields: [{
			name: "unit",
			compare: "like",
			labelWidth: 40,
			width: 150
		},{
			name: "dept",
			compare: "like",
			labelWidth: 40,
			width: 150
		},{
			name: "name",
			compare: "like",
			labelWidth: 40,
			width: 150
		}]
	};
	
	function getSelectResult(){
		var ids = Qm.getValuesByName("id");
		var names = Qm.getValuesByName("name");
		return {
			code : Qm.getValuesByName("id"),
			name : Qm.getValuesByName("name")
		};
	}
</script>
</head>
<body>
	<qm:qm pageid="sys_role_user" singleSelect="${param.checkbox=='0'}" pagesize="9999">
		<qm:attr name="role_id" value="${param.roles}" />
		<qm:param name="unit_id" value="${param.range}" compare="=" logic="or"/>
		<qm:param name="dept_id" value="${param.range}" compare="=" logic="or"/>
	</qm:qm>
</body>
</html>
