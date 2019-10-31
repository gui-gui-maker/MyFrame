<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	var qmUserConfig = {
		sp_defaults:{columnWidth:1,labelAlign:'right',labelSeparator:''},//可以自己定义 layout:column,float,
		sp_fields : [
				{group:[
					       {name:"start_time",compare:">=",value:""},
					       {label:"到",name:"start_time",compare:"<=",value:""}
					       ],columnWidth:1
				},
				{group:[
					       {name:"end_time",compare:">=",value:""},
					       {label:"到",name:"end_time",compare:"<=",value:""}
					       ],columnWidth:1
				}
		],
	};
</script>
</head>
<body>
	<qm:qm pageid="oa_c_used" script="false" singleSelect="true">
		
		<qm:param name="(state" value="未派车" compare="=" />
		<qm:param name="state" value="已派车" compare="=" logic="or"/>
		
		<qm:param name="car_id" value="${param.id}" compare="=" logic=")and"/>
	</qm:qm>

</body>
</html>