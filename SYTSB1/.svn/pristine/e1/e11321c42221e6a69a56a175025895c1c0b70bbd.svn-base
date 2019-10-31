<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消息订阅设置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/message/js/subscription.js"></script>
<script type="text/javascript">
    $(function() {
    	$("#mform").initForm({
    		toolbar: [{
    			text: "保存",
    			icon: "save",
    			click: saveSubsData
    		}],
    		afterParse: function(){
    			getMsgModules(function(data){
    				createModuleView(data,<u:dict code="sys_message_types"/>);
    			})
    		}
    	});
    });
</script>
<style type="text/css">
html,body{overflow:auto;}
*{font-size:14px!important;}
ul{padding:0;margin:0;}
#module_view,#worktypes_view{margin:1em;}
.module{height:4mm;border-bottom:1px solid #dedede;padding: 2mm 1em 3mm 1mm}
.module.hover{background:#efefef;}
.cfg{float:right;}
.cfg .cfg-item{margin-left:2mm;display:inline-block;height:4mm;}
.cfg .cfg-item span{margin-top:1mm;display:inline-block;float:left;}
.cfg .cfg-item label{margin-left:0.5mm;display:inline-block;float:left;}
</style>
</head>
<body>
	<form id="mform" action="pub/message/subscription/saveDefault_new.do" getAction="pub/message/subscription/defualtSubscription.do">
    	<ul id="module_view"></ul>
	</form>
</body>
</html>