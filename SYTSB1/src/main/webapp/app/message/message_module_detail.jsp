<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<% CurrentSessionUser user = SecurityUtil.getSecurityUser();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String now = df.format(new Date());
	%>
	<script type="text/javascript">
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	        getSuccess:function(resp){
	        	if(resp.success){
					$("#formObj").setValues(resp.data);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error(response.msg);
	      		}
			}
		});

	});	
	
	function close(){
		api.close();
	}	
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post" action="messageContentModAction/save.do"
			getAction="messageContentModAction/detail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<input id="create_op_id" name="create_op_id"  type="hidden" value="<%=user.getId() %>" />
			<input id="create_op" name="create_op"  type="hidden"  value="<%=user.getName()%>"/>
			<input id="create_date" name="create_date"  type="hidden" value="<%=now %>" />
			<input id="data_status" name="data_status"  type="hidden"  value="0"/>
		
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">模块编号：</td>
						<td class="l-t-td-right">
							<input type="text" id="module_code" name="module_code" ltype="text" validate="{required:true}"/>
						</td>
						<td class="l-t-td-left">模块名称：</td>
						<td class="l-t-td-right">
							<input type="text" id="module_name" name="module_name" ltype="text" validate="{required:true}" />
						</td>	
					</tr>
					<tr>
						<td class="l-t-td-left">模块功能描述：</td>
						<td class="l-t-td-right">
							<input type="text" name="module_desc" id="module_desc" ltype="text" validate="{required:true}" /> 
						</td>
						<td class="l-t-td-left">发送对象：</td>
						<td class="l-t-td-right">
							<input type="text" id="send_target" name="send_target" ltype="text" validate="{required:true}"/>
						</td> 
					</tr>
					<tr>
						<td class="l-t-td-left">可用参数：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="param_01" id="param_01" rows="2" type="text" ltype="text"  validate="{required:false,maxlength:1000}"/>
						</td> 								
					</tr>
				</table>
			
		</form>
	</body>
</html>