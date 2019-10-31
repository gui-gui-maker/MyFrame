<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="edit">
<title>修改密码</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$.getJSON("rbac/resource/getIconCss.do",function(data){
		if(data.success){
			for(var i in data.data){
				//alert(data.data[i].iconName);
				var tr = "<tr>"+"<td><input type=\"radio\" name=\"icon\" value=\""+data.data[i].iconName+"\"/></td>"
				+"<td>"+data.data[i].iconZwName+"</td>"+
				"<td><div class=\"l-icon "+data.data[i].iconCss+"\" style=\"margin:0 auto\"></div></td>"
				+"<td>"+data.data[i].iconName+"</td>"+"</tr>"
				$("#tbar").append(tr);
			}
		}
	})
	$("#formObj2").initForm({
		toolbar : [{
			text : "确定", icon : "save", click : function() {
				var icon = $('input:radio:checked').val();
				if(icon){
					api.data.window.setIcon(icon);
				}
				api.close();
			}}, {
				text : "关闭", icon : "cancel", click : function() {
					api.close();
				}
			}
		]
	});
});
</script>
<style type="text/css">
table, tr, td, th, img { margin: 0; padding: 0; border: 0; }
.tbl-tlbar { border: 1px solid #ccc; border-collapse: collapse; width: 99%; margin: 5px auto; }
.tbl-tlbar td, .tbl-tlbar th { border: 1px solid #ccc; padding: 3px 5px; text-align: center; }
.tbl-tlbar th { font-size: 14px; font-weight: bold; background:#eee; }
.red {color:#f00;}
</style>
</head>
<body>
	<form name="formObj2" id="formObj2">
		<table border="0" cellspacing="0" cellpadding="0" class="tbl-tlbar" id="tbar">
			<tr>
				<th>选择</th>
				<th>功能</th>
				<th>图标 icon</th>
				<th>图标样式名称</th>
			</tr>
		</table>
	</form>
</body>
</html>