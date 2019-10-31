<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="edit">
<title>修改密码</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#formObj2").initForm({
		success : function(response) {//处理成功
			if (response.success) {
				top.$.notice("设置成功!",4);
				api.data.window.menuclose();
				api.data.window.menuset(parseInt(response.data.showType))
				api.close();
			} else {
				$.ligerDialog.error(response.msg)
			}
		},toolbar : [{
			text : "保存", icon : "save", click : function() {
				$("#formObj2").submit();
			}}, {
				text : "关闭", icon : "cancel", click : function() {
					//api.data.window.menuclose();
					api.close();
				}
			}
		]
	});
});
</script>
<style type="text/css">
	td,input{font-size:14px;}
</style>
</head>
<body>
	<form name="formObj2" id="formObj2" method="post" 
		action="pub/syspersonmenuset/saveset.do"  getAction="pub/syspersonmenuset/getset.do">
		<table id="tab1" border="0" cellpadding="3" cellspacing="0" width=""
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">显示方式：</td>
				<td class="l-t-td-right">
					<input type="hidden"  id="id" name="id"/>
					<input id="userid" type="hidden" name="user.id" value="<sec:authentication property='principal.id' />"/>
					<input name="showType" type="radio" ltype="radioGroup" ligerui="{initValue:'1',lineWrap:false,data:[{'id':'1','text':'纵向展开'},{'id':'3','text':'始终最大化'}]}"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>