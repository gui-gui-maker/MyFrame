<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="pub/common/common.js"></script>
    <script type="text/javascript">
        $(function () {
			$("#form1").initForm({
				toolbar:[
					{id:"chooseunit",icon:'search',text:'选择人员',click:function(){
						chooseSystemUser({
						    multiple: true,
						    idField: "userId",
						    range: "${param.rangeType}",
						    nameField: "userName",
						    callback: function(result){
						    	$("#userName").val(result.name.replace(/,/g,'，'));
						    }
						});
					}},
					{icon:'save',text:'保存',click:function(){$("#form1").submit();}},
			        {icon:'cancel',text:'关闭',click:function(){api.close()}}
				],
				success: function (response) {
					if(response.success){
						api.data.callback(response.data);
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + (response.msg||""));
					}
				}
			});
		});
    </script>
    <style type="text/css">#chooseunit{float:left;}</style>
</head>
<body>
<form id="form1" action="rbac/user/group/save.do" getAction="rbac/user/group/detail.do?id=${param.id}">
    <input type="hidden" name="id" />
    <input type="hidden" name="userId" id="userId" />
    <input type="hidden" name="rangeType" value="${param.rangeType}" />
    <table cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left" style="width:60px;">分组名称：</td>
            <td class="l-t-td-right">
            	<input name="groupName" type="text" ltype="text" validate="{required:true,maxlength:20}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-left">人员：</td>
            <td class="l-t-td-right">
            	<textarea rows="10" cols="20" name="userName" id="userName" readonly="readonly" ltype="textarea" validate="{required:true,maxlength:4000}"></textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>