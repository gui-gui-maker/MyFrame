<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>员工排序设置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	/* $(function() {
		var tbar=[{text: "保存", icon: "save", click: function(){$("#formObj").submit();}},
  			{text: "关闭", icon: "cancel", click: function(){api.close();}}];
		$("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
	}); */
</script>
<style> 
.class_specification{
	color:#F00;
	position:relative;
	margin-top: 35px;
	margin-left:6px;
	font-family: "微软雅黑","宋体";
    font-size: 12px
} 
</style> 
</head>
<body>
	<form id="formObj" action="employeeBaseAction/saveSort.do" getAction="employeeBaseAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<table style="margin-top:1em" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">员工姓名</td>
				<td class="l-t-td-right"><input name="empName" id="empName" type="text" ltype="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">所属部门</td>
				<td class="l-t-td-right"><input name="workDepartmentName" id="workDepartmentName" type="text" ltype="text" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">排序</td>
				<td class="l-t-td-right"><input name="sort" id="sort" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td>
			</tr>
		</table>
		<div class="class_specification">说明：各个部门人员排序情况请参照腾讯通，并按照“XXX”格式填写，例如  011。</div>
	</form>
</body>
</html>
