<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>产品监检提交</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
					top.$.dialog.notice({
	             		content: "提交成功！"
	            	});	
			   		api.data.window.refreshGrid();
			     	api.close();  
	    		} else {
	           		$.ligerDialog.error('提交失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){

			}, toolbar: [
	      		{
	      			text: "提交", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(checkForm()){
				    			if(confirm("确定提交？提交后不能修改！")){
							    	//表单提交
							    	$("#formObj").submit();
								}
				    		}
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});
	});
	
	function checkForm(){
		var send_user_id = $("#send_user_id").val();
		var send_user_name = $("#send_user_name").val();
		if("" == send_user_id || "" == send_user_name){
			$.ligerDialog.alert("请选择监检人员！");			
			return false;
		}
		return true;
	}
	
	// 选择流转监检人员
	function selectUser(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择监检人员",
			content: 'url:app/sinspection/choose_user_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#send_user_id').val(id);		// 监检人员ID
		$('#send_user_name').val(name);	// 监检人员姓名
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="supervision/inspection/submit.do?status=${param.status}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>产品监检提交</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">监检人员：</td>
					<td class="l-t-td-right">
						<input name="send_user_id" id="send_user_id" type="hidden" />
						<input id="send_user_name" name="send_user_name" type="text" ltype='text' onclick="selectUser()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}"/>
				    </td>									
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
