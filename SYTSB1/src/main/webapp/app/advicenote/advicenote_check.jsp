<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>通知书审核</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
			      	if(api.data.window.Qm)
			    	{
			         	api.data.window.Qm.refreshGrid();
			       	}
					top.$.dialog.notice({
	             		content: "保存成功！"
	            	});	
			   		api.data.window.api.close();
			     	api.close();  
			     	api.data.window.api.data.pwindow.Qm.refreshGrid();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){

			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(checkForm()){
				    			if(confirm("确定保存？")){
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
						api.data.window.showBB();
					}
				}
			], toolbarPosition: "bottom"
		});
	});
	
	function checkForm(){
		var check_result = $("#check_result").val();
		var check_content = $("#check_content").val();
		if("" == check_result){
			$.ligerDialog.alert("请选择审核结果！");			
			return false;
		}
		if("2" == check_result){	// 2：审核不通过
			if("" == check_content){
				$.ligerDialog.alert("请填写审核意见！");
				return false;
			}
		}
		return true;
	}
	
	//切换审核是否通过时，隐藏或展开不通过原因
	function changevalue(value) {  
  		if(value=='1'){
		  	$("#check_content").val("");
		  	$("#check_result").val("1");
  		}else{
	  		$("#check_result").val("2");
  		}
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="advicenote/check.do?status=${param.status}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<input name="check_result" id="check_result" type="hidden"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>通知书审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核结果：</td>
					<td class="l-t-td-right">
						<input type="radio" name="shjg" id="shjg" ltype="radioGroup"
							ligerui="{onChange:changevalue, initValue:1, data: [ { text:'通过', id:'1' }, { text:'不通过', id:'2' } ] }"/>	
					</td>										
				</tr>
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right">
						<textarea name="check_content" id="check_content" rows="2" cols="25" class="l-textarea" validate="{maxlength:256}"></textarea>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
