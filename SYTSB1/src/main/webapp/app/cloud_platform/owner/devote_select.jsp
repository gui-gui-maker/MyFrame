<%@page import="util.TS_Util"%>
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
                   {text:"确认", id:'save',icon:"save", click:save},
                   {text:"关闭", icon:"close", click:function(){
                	  api.close();
                  	 }
               		}]
	});
	
});

function save(){
	$("#save").attr("disabled","disabled");
	if ($("#formObj").validate().form()) {
		var org_id = "<%=TS_Util.getCurOrg(user).getId()%>";
		var taget_id="";
		var depart = 0;
		var government = 0;
		if($("#depart").attr("checked")=="checked"){
			depart = 1;
		}
		if($("#depart1").attr("checked")=="checked"){
			government = 1;
		}
		if(government==0&&depart==0){
			top.$.notice("请选择贡献空间！");
			return;
		}else if(government==0&&depart==1){
			taget_id = org_id;
		}else if(government==1&&depart==0){
			taget_id = "100000";
		}else if(government==1&&depart==1){
			taget_id = org_id+",100000";
		}
		
		var name = $("#name").val();
		//alert(taget_id+"--"+api.data.ids)
		$.post("resourceInfo/resourceShare.do?id="+api.data.ids+"&userId="+taget_id+"&spaceId="+sessionStorage.getItem("owner_root"),function(res){
			if(res.success){
				top.$.notice("贡献成功！");
				api.close();
			}else{
				$.ligerDialog.error(res.msg);
			}
			
		})
	}else{
		$("#save").removeAttr("disabled"); 
	}
	
}

</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
			<br />
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
			<td class="l-t-td-left">选择空间 ：</td>
			<td class="l-t-td-right">
			<input id="depart" name="depart" type='checkbox'  ligerui="{initValue:'',data:[{text:'部门空间',id:'1'}]}" ltype='checkboxGroup' />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left"></td>
			<td class="l-t-td-right">
			<input id="depart1" name="depart" type='checkbox'  ligerui="{initValue:'',data:[{text:'院空间',id:'2'}]}" ltype='checkboxGroup' />
			</td>
		</tr>
		</table>

	</form>
</body>
</html>
