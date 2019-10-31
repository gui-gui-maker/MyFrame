<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/enter/device_list.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";		



$(function() {
	createDeviceGrid();
	$("#formObj").initForm({
		/*
		  toolbar:[
                   {text:"保存", icon:"save", click:function(){
                           submitForm();
                       }
                   },
	            	{text:"取消", icon:"cancel", click:function(){
			        	api.close();
		    }}
               ],
        */
        toolbarPosition :"bottom",
		
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("保存成功！");
				
					api.data.window.submitAction();//执行列表页面函数
					api.close();
				
		}
	});
	/*
	if ("${param.status}" == "add") {
		$.get("rbac/user/newUserCode.do", function(response) {
			if (response.success) {
				$("#userCode").val(response.data.value);
			} else {
				$.ligerDialog.error("获取用户编号发生错误，请稍后再试，或者请联系系统维护人员!");
			}
		});
	}
	*/
});
</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" Action="message/saveBasic.do"
			getAction="message/detail.do?id=${param.id}">
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>基本信息</div>
		</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input id="id" name="id" type="hidden" />
		
		<tr> 
        	<td class="l-t-td-left"> 短信类型:</td>
        	<td class="l-t-td-right" > 

<u:combo name="step_no"
							code="message_type" validate="required:true" />
        		
        		

        	
        	</td>
        	 <td class="l-t-td-left"> 是否自动发送:</td>
	        <td class="l-t-td-right"> 
	        	<input type="radio" name="flag"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/>
	        </td>
        	
        	</tr>
        	<tr>
			<td class="l-t-td-left"> 短信内容:</td>
        	<td class="l-t-td-right"  colspan="3"> 
        	
        	<textarea name="info"   rows="4"    validate="{maxlength:600}" ></textarea>
        		
        	</td>
		</tr>
       	
		
		</table>
		
		
		
		
	</fieldset>	
	
</form>
</body>
</html>
