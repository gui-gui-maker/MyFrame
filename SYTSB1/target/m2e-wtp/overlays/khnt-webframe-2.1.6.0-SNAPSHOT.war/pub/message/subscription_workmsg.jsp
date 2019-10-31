<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消息订阅设置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
    $(function() {
    	$("#tabs").ligerTab({
    		height: "100%",
    		onBeforeSelectTabItem: function(tid){
    			if(tid=="workmsg" && $("#workmsgframe").attr("src")=="")
    				$("#workmsgframe").attr("src","pub/message/work_message_map.jsp");
    		}
    	});
    });
</script>
</head>
<body>
	<div id="tabs">
		<div title="订阅管理" tabid="subs">
			<iframe id="subsframe" frameborder="0" style="height:100%;height:100%" src="pub/message/subscription_default.jsp"></iframe>
		</div>
		<div title="工作任务模块配置" tabid="workmsg">
			<iframe id="workmsgframe" style="height:100%;height:100%" src=""></iframe>
		</div>
	</div>
</body>
</html>