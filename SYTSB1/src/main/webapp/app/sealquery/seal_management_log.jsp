<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
 <html xmlns="http://www.w3.org/1999/xhtml"> 
 <head> 
 <title>印章管理日志</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:120},//可以自己定义 layout:column,float,
		sp_fields : [
			{group: [
							{name: "action_time", compare: ">="},
							{label: "到", name: "action_time", compare: "<=", labelAlign: "center", labelWidth: 20}
							]}, 					 
	          
	        ],}
</script>
</head>
<body>
	<qm:qm pageid="TJY2_YZ_YZGLRZ" script="false" singleSelect="true"></qm:qm>
</body>
</html>