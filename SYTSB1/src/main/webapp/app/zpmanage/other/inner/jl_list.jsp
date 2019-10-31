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
		title:"查询",
        sp_defaults:{columnWidth:0.5,labelAlign:'top',labelSeparator:'',labelWidth:60},// 可以自己定义
		 sp_fields:[
					  {name:"name",compare:"like",value:""},
		              {name:"certificates_num",compare:"like",value:""},
		              {name:"academic",compare:"=",value:""},
		              {name:"college",compare:"like",value:""}
		           ]
		};
   		function getSelected()
   		{
   			var tt = new Array();
   			tt.push(Qm.getValuesByName("id"));
   			tt.push(Qm.getValuesByName("name"));
   			return tt;
   		}
</script>
</head>
<body>
	<qm:qm pageid="selectjl" script="false" singleSelect="true">
	</qm:qm>
</body>
</html>