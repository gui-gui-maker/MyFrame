<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>新增单位</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
					top.$.dialog.notice({
	             		content: "保存成功！"
	            	});	
			   		api.data.window.submitAction();
			     	api.close();  
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){

			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(confirm("确定保存？")){
							    //表单提交
							    $("#formObj").submit();
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
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="enter/basic/save.do">
		<input type="hidden" name="com_type" value="${param.com_type}" /><!-- 02：制造单位 10：缴费单位 -->
		<input type="hidden" name="com_status" value="01" /><!-- 01：正常 -->
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>单位信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">组织机构代码：</td>
					<td class="l-t-td-right">
						<input name="com_code" type="text" ltype='text' validate="{maxlength:32}"/>
				    </td>
				    <td class="l-t-td-left">法定代表人：</td>
					<td class="l-t-td-right">
						<input name="com_legal_rep" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
				    </td>									
				</tr>
				
				<tr>
					<td class="l-t-td-left">联系人：</td>
					<td class="l-t-td-right">
						<input name="com_contact_man" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
				    </td>
				    <td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right">
						<input name="com_tel" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
				    </td>									
				</tr>
				<tr>
					<td class="l-t-td-left">单位名称：</td>
					<td class="l-t-td-right">
						<input name="com_name" type="text" ltype='text' validate="{required:true,maxlength:255}"/>
				    </td>
				    								
				</tr>
				<tr>
					<td class="l-t-td-left">单位地址：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="com_address" id="com_address" rows="2" cols="25" class="l-textarea" validate="{maxlength:255}"></textarea>
				    </td>								
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
