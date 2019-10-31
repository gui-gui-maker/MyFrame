<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/fwxm/contract/contract_custom_list.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";		
<%
String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
CurrentSessionUser user = SecurityUtil.getSecurityUser();
String user_id = user.getId();
String user_name = user.getName();
%>

var  flag = ${param.flag}

$(function() {
	$("#formObj").initForm({
		 toolbar:[
                   {text:"保存", icon:"save", click:function(){
                	   
                           $("#formObj").submit();
                   	}
                   },
	            	{text:"取消", icon:"cancel", click:function(){
			        	api.close();
		    }}
               ],
        
        toolbarPosition :"bottom",
		getSuccess : function(resp) {
		},
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("保存成功！");
				api.data.window.submitAction();
				api.close();
			} else {

					$.ligerDialog.error('保存失败!')
			}
		}
	});

});


</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" Action="contractEvaluateAction/save.do"
			getAction="contractEvaluateAction/detail.do?id=${param.id}">
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>评价信息</div>
		</legend>
		
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input id="id" name="id" type="hidden" />
		<input id="data_status" name="data_status" type="hidden" value="0"/>
		<input id="evaluate_op_id" name="evaluate_op_id" type="hidden" value="<%=user_id %>" />
		<input id="evaluate_op" name="evaluate_op" type="hidden" value="<%=user_name%>"/>
		<input id="evaluate_date" name="evaluate_date" type="hidden" value="<%=now%>"/>
		<input id="contract_id" name="contract_id" type="hidden" value="${param.contract_id}"/>
		
		<tr> 
			<td class="l-t-td-left">执行评价/跟踪内容:</td>
        	<td class="l-t-td-right" colspan="3"> 
        	<textarea rows="10" cols=""  id="evaluate_contain" name="evaluate_contain"  ltype='text' validate="{required:true,maxlength:1000}"></textarea>
        	</td>
        </tr>
		</table>
	</fieldset>	
	
</form>
</body>
</html>
