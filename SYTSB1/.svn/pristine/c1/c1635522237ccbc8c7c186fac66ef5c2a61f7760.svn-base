<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {
        	
        	
			$("#form1").initForm({    //参数设置示例
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
<form id="form1" action="demo/mtmp/save.do" getAction="demo/mtmp/detail.do?id=${param.id}">
    <input type="hidden" id="id" name="id">
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">姓名：</td>
            <td class="l-t-td-right"><input name="name" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">课程：</td>
            <td class="l-t-td-right">
            	<input ltype="select" name="mtmcIds" validate="{required:true}" ligerui="{data:<u:dict code='' sql='select id,name from TEST_MTMC_DEMO'/>,isMultiSelect:true}"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
