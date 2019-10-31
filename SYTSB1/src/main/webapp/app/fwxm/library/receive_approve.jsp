<%@page import="com.khnt.rbac.bean.Org"%>
<%@page import="com.khnt.rbac.bean.User"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
User user = SecurityUtil.getSecurityUser().getSysUser();
Org o = user.getOrg();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript">
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar : [ {
				text : '保存',
				id : 'save',
				icon : 'save',
				click : save
			},
			{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : function close(){	
						 	api.close();
						}
			}],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
		});
	});

	function save(){
		//验证表单数据
		if($('#formObj').validate().form()){
			var formData = $("#formObj").getValues();
	        $("body").mask("正在保存数据，请稍后！");
	        $.post("lib/receive/approve.do",formData,function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	if(api.data.window.Qm){
	                		api.data.window.Qm.refreshGrid();
	                	}
	                    top.$.dialog.notice({content:'保存成功'});
	                    api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                	$("#save").removeAttr("disabled");
	                }
	            }
	        );
		}
	}
</script>
</head>
<body>
	<form id="formObj" action="">
		<input id="ids" name="ids" value="${param.ids}" type="hidden"/>
	    <table cellpadding="3" class="l-detail-table">
	        <tr>
	            <td class="l-t-td-left">审批结果：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<input name="approveResult" id="approveResult" type="radio" validate="{required:true}"
	            		ltype="radioGroup" ligerui="{initValue:'1',data:[{id:'1',text:'同意'},{id:'0',text:'不同意'}]}" />
	            </td>
	        </tr>
	        <tr>
	            <td class="l-t-td-left">审批意见：</td>
	            <td class="l-t-td-right" colspan="3">
	            	<textarea name="approveSuggestion" id="approveSuggestion" rows="2"></textarea>
	            </td>
	        </tr>
	    </table>
	</form>
</body>
</html>
