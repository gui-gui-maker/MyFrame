<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统角色列表</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
        sp_fields:[
			{label:"角色名称", name:"name", compare:"like",labelWidth:60},
			{label:"描述", name:"remark", compare:"like",labelWidth:60}
        ]
    };
	function getSelectResult() {
		var s_role_id = Qm.getValuesByName("id");
		//可以添加备注到名称后面，解决业务系统把name当code的问题
		var s_role_name = Qm.getValuesByName("name");// +"("+ Qm.getValuesByName("remark")+")";
		return {code:s_role_id,name:s_role_name};
	}
</script>
</head>
<body>
	<qm:qm pageid="sys_02" singleSelect="${param.checkbox=='0'?'true':'false'}" pagesize="100" />
</body>
</html>