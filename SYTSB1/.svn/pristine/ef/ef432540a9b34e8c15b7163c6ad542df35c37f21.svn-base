<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	function getAccount(){
		var ret={};
		ret["userAccount"]=$('input[name="user"]:checked').val();
		ret["userId"]=$('input[name="user"]:checked').parent().find("input[name='userId']").val();
		ret["positionId"]=$('input[name="user"]:checked').parent().find("input[name='positionId']").val();
		return ret;
	}
	$(function(){
		$.getJSON("entrust/user/getLogEnUser.do?userId=${param.userid}",function(data){
			var tt = data;
			for(var i in tt){
				var tr = "<tr style='height:30px'><td align='left' style='margin-left:20px'><input type='hidden' name='userId' value='"+tt[i].userId+"'/><input type='hidden' name='positionId' value='"+tt[i].positionId+"'/><input type='radio' name='user' value='"+tt[i].userAccount+"' id='"+tt[i].userAccount+"'/><label for='"+tt[i].userAccount+"'>"+tt[i].userName+"</label></td></tr>"
				$("#users").append(tr);
			}
		})
	})
</script>
</head>
<body>
	<table id="users">
	</table>
</body>
</html>