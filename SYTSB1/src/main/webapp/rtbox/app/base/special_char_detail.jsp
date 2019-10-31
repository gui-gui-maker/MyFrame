<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
//var pageStatus="${param.status}"; 
//var bigClassify="${param.bigClassify}";
//var little_type = "${param.device_sort}"
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			}
		});
		 
	}); 

</script>
</head>
<body>
	
		<form name="formObj" id="formObj" method="post" Action="specialCharacterAction/save.do"
				getAction="specialCharacterAction/detail.do?id=${param.id}">
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
			<td class="l-t-td-left">
				<input id="id" name="id" type="hidden" />
			</td>
			<td class="l-t-td-right"></td>
		</tr>
		<tr>
			<td class="l-t-td-left">特殊字符名字：</td>
			<td class="l-t-td-right" >
				<input type="text" id="name" name="name" type="text" ltype="text" validate="{required:true,maxlength:32}" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">符号：</td>
			<td class="l-t-td-right" >
				<input id="value" name="value" type="text"  ltype='text' validate="{required:true,maxlength:32}"/>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">类型：</td>
			<td class="l-t-td-right" >
				<input name="type" type="text" ltype="select" validate="{required:true}" ligerUi="data:[{id:'1',text:'数学符号'},{id:'2',text:'特殊文字'}]"/>
			</td>
		</tr>
		</table>
		</form>
	
</body>
	
</html>
