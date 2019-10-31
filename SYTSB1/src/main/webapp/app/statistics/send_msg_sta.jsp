<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus = "${param.status}";		
$(function() {
	$("#formObj").initForm({
		success:function(res){
			if(res.success){
				top.$.notice("保存成功！");
				$("#formObj").html('<br /><div  align="center" style="color:red;">2016年度大数据账单微信消息已发送成功，请查看微信结果！</div>')
			}else{
				 $.ligerDialog.error('消息发送失败！');
			}
		}
	});
})
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="enterSearchAction/sendWeixinStaUrl.do">
			<br />
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<td class="l-t-td-left">填写消息内容：</td>
			<td class="l-t-td-right">
				<textarea rows="5" cols=""  type="text" id="text" name="text"   ltype="text"  validate="{required:true}" ></textarea>
			</td>
		</table>

	</form>
</body>
</html>
