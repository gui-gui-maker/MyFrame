<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
	String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

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
		var audit_expand = $("#audit_expand").val();
		var audit_desc = $("#audit_desc").val();
		var audit_date = $("#audit_date").val();
		var audit_conclusion = $("#audit_conclusion").ligerGetRadioGroupManager().getValue();
		var id = api.data.id;
		if(audit_conclusion=="1"&&audit_expand==""){
			 $.ligerDialog.alert('请填写容积！');
			 return;
		}
		$.ajax({
            url: "tjyptSpaceExpandAction/auditExpand.do",
            type: "POST",
            data: {audit_expand:audit_expand,audit_desc:audit_desc,audit_date:audit_date,audit_conclusion:audit_conclusion,id:id},
            success: function (data, stats) {
                if (data["success"]) {
                		top.$.notice("保存成功！");
                		api.data.window.submitAction();
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

function changeAudit(val,text){
	//审核结论改变事件
	//alert(val)
	if(val=="0"){
		$("#audit_expand").val("");
		$("#tong").hide();
	}else{
		$("#tong").show();
	}
}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
			<br />
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<c:if test="${param.status=='detail'}">
			<tr >
				<td class="l-t-td-left">申请人：</td>
				<td class="l-t-td-right">
				<input name="apply_user_name" id="apply_user_name" type="text" ltype="text" />
				</td>
			</tr>
			<tr >
				<td class="l-t-td-left">申请容积：</td>
				<td class="l-t-td-right">
				<input name="apply_expand" id="apply_expand" type="text" ltype="text" validate="{number:true}" ligerui="{suffix:'G'}"/>
				</td>
			</tr>
			<tr >
				<td class="l-t-td-left">申请时间：</td>
				<td class="l-t-td-right">
				<input name="apply_date" id="apply_date" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">申请备注：</td>
				<td class="l-t-td-right">
					<textarea rows="4" cols="" name="apply_desc" id="apply_desc" ltype="text"></textarea>
				</td>
			</tr>
		</c:if>
		<tr>
			<td class="l-t-td-left">审核结论：</td>
			<td class="l-t-td-right">
			<input name="audit_conclusion" id="audit_conclusion" type="radio" ltype="radioGroup" validate="{required:true}" 
			ligerui="{
				initValue:'1',
				data:[{id:'1',text:'通过'},{id:'0',text:'不通过'}],
				onChange:changeAudit
			}"
			/>
			</td>
		</tr>
		<tr id="tong">
			<td class="l-t-td-left">确认容积：</td>
			<td class="l-t-td-right">
			<input name="audit_expand" id="audit_expand" type="text" ltype="text" value="${param.apply_expand}" validate="{number:true}" ligerui="{suffix:'G'}"/>
			</td>
		</tr>
		<tr >
				<td class="l-t-td-left">审核时间：</td>
				<td class="l-t-td-right">
				<input name="audit_date" id="audit_date" type="text" value='<%=now %>' ltype="date" ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" />
				</td>
			</tr>
		<tr>
			<td class="l-t-td-left">审核说明：</td>
			<td class="l-t-td-right">
				<textarea rows="4" cols="" name="audit_desc" id="audit_desc" ltype="text"></textarea>
			</td>
		</tr>
		</table>

	</form>
</body>
</html>
