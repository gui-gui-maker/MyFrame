<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>用户信息编辑、查看</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(resp) {
				if (resp.success) {
					top.$.notice("保存成功！",3);
					api.data.window.submitAction();//刷新列表
					api.close();
				} else {
					$.ligerDialog.error('保存失败！错误信息：<br />' + resp.msg)
				}
			}
		});
	});
	
	//选择用户对应的人员
    function selEmployee(){
		var windows=top.$.dialog({
			width: 800,
            height:500,
            max: false,
	        min: false,
			lock:true,
			parent:api,
			title : "选择人员",
			data : {callback : function(data){
				if(data){
					$("#employeeId").val(data.id);
					$("#employeeName").val(data.name);
				}
			}},
			content: 'url:pub/rbac/employee_dialog.jsp'
		});
	}
	
	function change(a){
		alert(a);
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="rbac/user/save.do"
		getAction="rbac/user/detail.do?id=${param.id}">
		<table style="margin-top:1em" class="l-detail-table">
			<input type="hidden" name="id" />
			<input type="hidden" name="org.id" value="${param.orgid}" />
			<tr>
				<td class="l-t-td-left">姓名：</td>
				<td class="l-t-td-right"><input name="name" id="employeeName" type="text" ltype="text" 
					validate="{required:true,maxlength:50}"/>
			</tr>
			<tr>
				<td class="l-t-td-left">用户名：</td>
				<td class="l-t-td-right"><input name="account" type="text" ltype="text" validate="{required:true,maxlength:64}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text" ltype="text" validate="{maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">是否启用：</td>
				<td class="l-t-td-right"><u:combo name="status" code="sys_sf" ltype="radioGroup" attribute="initValue:'1'" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
