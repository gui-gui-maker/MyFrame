<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@ include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
var qmUserConfig = {
		sp_defaults:{columnWidth:0.5,labelWidth:80},
		sp_fields:[
		              {name:"gw_name",compare:"like",value:""},
		              {name:"gw_num",compare:"like",value:""}
		          ]
		};
   		function getSelected()
   		{
   			var tt = new Array();
   			tt.push(Qm.getValuesByName("id"));
   			tt.push(Qm.getValuesByName("gw_name"));
   			return tt;
   		}
</script>
</head>
<body>
	<qm:qm pageid="selectgw" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>