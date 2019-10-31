<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消息管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
var qmUserConfig = {
		listeners : {
			rowAttrRender: function (rd, rowid) {
				if(rd.status=="发送失败"){
					 rd.status = "<a style='color:red;text-decoration:underline' href='javascript:showMsg(\"" + rd.result + "\")' title='点击查看'>发送失败</a>"; 
					return "style='color:red'";
				} 
			}
		}
}
function showMsg(msg){
	top.$.dialog({
		width : 500,
		height : 200,
		cancel : true,
		title : "查看发送结果",
		content : "<p style='text-align:left;padding:5px;width:100%;'>" + msg + "</p>"
	});
}
</script>
</head>
<body>
	<qm:qm pageid="pub_message_two" script="false" singleSelect="true">
	  <qm:param name="fk_msg" value="${param.createId}" compare="=" />
	</qm:qm>
</body>
</html>