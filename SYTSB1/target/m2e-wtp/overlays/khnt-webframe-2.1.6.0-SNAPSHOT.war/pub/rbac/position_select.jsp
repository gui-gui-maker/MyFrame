<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	$(function(){
		var data = api.data.data;
		for(var i in data){
			var tr = "<tr style='height:30px'><td align='left'><input type='radio' name='position' value='"+data[i].id+"' id='"+data[i].id+"'/><label for='"+data[i].id+"'> "+ data[i].org.orgName+"["+data[i].posName+"]"+"</label></td></tr>"
			$("#position").append(tr);
		}
	})
	function getPostionId(){
		var positionId = $('input[name="position"]:checked').val();
		return positionId;
	}
</script>
</head>
<body>
<table id="position" style="font-size:14px;padding-top: 10px;padding-left:15px;border-collapse:separate; " border="0"
	   cellpadding="3" width="100%">
	</table>
</body>
</html>