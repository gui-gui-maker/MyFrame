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
    			click: saveDataWithAccount
    		},{
    			text: "关闭",
    			icon: "close",
    			click: function(){api.close();}
    		}],
    		afterParse: function(){
    			var msgTypes = <u:dict code="sys_message_types" containRemark="true"/>;
    			var userExtCfg = <%=SecurityUtil.getSecurityUser().getSysUser().getExtConfig()%>;
    			var userMsgCfg = userExtCfg==null?{}:userExtCfg["msg_cfg"];
    			$.each(msgTypes,function(){
    				if(this.id=="system")return;
    				if(this.remark!="no")
    					$("#userAccount").append("<span class='ua'>" + this.text + '<input type="text" name="' + 
    						this.id + '" value="' + ($.kh.isNull(userMsgCfg)?"":userMsgCfg[this.id]||"") + '"/></span>');
    			});
    			getMsgModules(function(data){
    				createModuleView(data,msgTypes,0,0);
    			})
    		}
    	});
    });
</script>
<style type="text/css">
html,body{overflow:auto;}
*{font-size:14px!important;}
ul{padding:0;margin:0}
.user-account{min-height:5mm;background:#ebebeb;padding:1.5mm;text-align:right;}
.user-account .text{margin:1mm 0 1mm 2mm;font-weight:bold;float:left;}
.user-account .ua{display:inline-block;margin:0.5mm 0}
.user-account input{margin:0 2mm 0 1mm;width:4cm;height:5.5mm;}
.module{height:4mm;border-bottom:1px solid #dedede;padding: 2mm 1em 3mm 1mm}
.module.hover{background:#efefef;}
.cfg{float:right;}
.cfg .cfg-item{margin-left:2mm;display:inline-block;height:4mm;}
.cfg .cfg-item span{margin-top:1mm;display:inline-block;float:left;}
.cfg .cfg-item label{margin-left:0.5mm;display:inline-block;float:left;}
</style>
</head>
<body>
	<form id="mform" action="pub/message/subscription/save_new.do"  getAction="pub/message/subscription/userSubscription.do">
		<div id="userAccount" class='user-account'><span class="text">账号设置：</span></div>
    	<ul id="module_view"></ul>
	</form>
</body> 
</html>