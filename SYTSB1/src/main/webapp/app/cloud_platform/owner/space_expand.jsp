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
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userid= user.getId();


%>
<%@ include file="/k/kui-base-form.jsp"%>
<link rel="stylesheet" href="app/cloud_platform/upload/bootstrap/easyui.css" type="text/css"></link>
<!-- <script type="text/javascript" src="app/cloud_platform/upload/jquery-1.8.0.min.js"></script> -->
<script type="text/javascript" src="app/cloud_platform/upload/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";	
var select = {};
var a = [];
var owner_id = "";
var queryInfo =null;
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 save();
    }
};	
$(function() {
	
	$("#formObj").initForm({
		
		  toolbar:[
                   {text:"提交", id:'save',icon:"save", click:save},
                   {text:"关闭", icon:"close", click:function(){
                	  api.close();
                  	 }
               		}]
	});
	
});

function save(){
	$("#save").attr("disabled","disabled");
	if ($("#formObj").validate().form()) {
		var rj = $("#rj").val();
		var desc = $("#desc").val();
		$.ajax({
            url: "tjyptSpaceExpandAction/applyExpand.do",
            type: "POST",
            data: {rj:rj,desc:desc},
            success: function (data, stats) {
                if (data["success"]) {
                		top.$.notice("提交成功！");
                        api.close();
                } else {
                	$("#save").removeAttr("disabled"); 
                	$.ligerDialog.error(data["msg"]);
                }
            },
            error: function (data) {
            	$("#save").removeAttr("disabled"); 
                $.ligerDialog.error('保存失败！');
            }
        });
	}else{
		$("#save").removeAttr("disabled"); 
	}
	
}

function changeSize(val){
	var size = api.data.size;
	if((val-0)<=(size-0)){
		alert('申请的空间容积应比现有空间大！');
		$("#rj").val("");
	}
}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
			<br />
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
			<td class="l-t-td-left">申请容积：</td>
			<td class="l-t-td-right">
			<input name="rj" id="rj" type="text" ltype="text" validate="{required:true,number:true}" ligerui="{suffix:'G'}" onchange="changeSize(this.value)"/>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">备注：</td>
			<td class="l-t-td-right">
				<textarea rows="4" cols="" name="desc" id="desc" ltype="text"></textarea>
			</td>
		</tr>
		</table>

	</form>
</body>
</html>
