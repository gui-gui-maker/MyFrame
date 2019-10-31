<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
	<!-- 每个页面引入，页面编码、引入js，页面标题 -->
	<script type="text/javascript">
	$(function(){
		$('input[name="zjNo"]').change(function(){
			var num = $(this).val();
			var result = $.kh.validte(num);
			if (!result.msg == "") 
			{
				$.ligerDialog.error(result.msg);
				$(this).val("");
			}
		})
		$('#formObj').initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					api.data.window.submitAction();
					api.close();
				} else {
					$.ligerDialog.error(responseText.msg)
				}
			},
		 getSuccess:function(responseText){
		   
		 }
		})
	})
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="app/zp/user/save.do"
			getAction="app/zp/user/detail.do?id=${param.id }">
			<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>网站用户信息</div>
			</legend>
				<table border="0" cellpadding="3" cellspacing="0" width="" height=""
					align="" class="l-detail-table">
					<input type="hidden" name="id"/>
					<input type="hidden" name="createdTime"/>
					<tr>
						<td class="l-t-td-left">姓名：</td>
						<td class="l-t-td-right">
							 <input type="text" name="name" ltype="text" validate="{required:true,maxlength:32}"/>
						</td>
						<td class="l-t-td-left">邮箱：</td>
						<td class="l-t-td-right">
							 <input type="text" name="email" ltype="text" validate="{required:true,email:true,maxlength:32}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">身份证号码：</td>
						<td class="l-t-td-right">
							<input type="text" name="zjNo" ltype="text" validate="{idno:true,required:true}"/>
						</td>
						<td class="l-t-td-left">密码：</td>
						<td class="l-t-td-right">
							 <input type="password" name="password" ltype="text" validate="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">是否启用：</td>
						<td class="l-t-td-right">
							 	<u:combo name="sign" code="isOrNot" attribute="initValue:1"></u:combo>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</body>
</html>
