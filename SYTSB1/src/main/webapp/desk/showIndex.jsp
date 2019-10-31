<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>单位信息列表</title>
<%@include file="/k/kui-base-list.jsp"%>

<script type="text/javascript">
function  showInfo()
{
	alert(111);
	$.ajax({        
		type:"post",      
		url:"department/basic/getShowInfo.do",
	 	dataType:"text", 
	 	success:function(data) {
	 		
	 		
		}
		
		
	})

}
	
</script>
</head>
<body onload="showInfo();">
	
</body>
</html>
