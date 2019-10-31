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
		<form name="formObj" id="formObj" method="post" action="disciplinePlanAction/save.do" 
		 getAction="disciplinePlanAction/detail.do?id=${param.id }">
			<input id="id" name="id"  type="hidden"  />
			<input id="enter_op" name="enter_op"  type="hidden"  value="<%=user.getName()%>" />
			<input id="enter_op_id" name="enter_op_id"  type="hidden"  value="<%=user.getId()%>"/>
			<input id="enter_time" name="enter_time"  type="hidden"  value="<%=now%>"/>
			<input id="data_status" name="data_status"  type="hidden" value="0" />
			<input id="flag" name="flag"  type="hidden" value="0" />
			<br />
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">年份：</td>
						<td class="l-t-td-right">
							<input type="text" id="year" name="year" ltype="text" validate="{required:true}"/>
						</td>
						<td class="l-t-td-left">月份：</td>
						<td class="l-t-td-right">
							<input type="text" id="month" name="month" ltype="text" validate="{required:true}"/>
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">序号：</td>
						<td class="l-t-td-right">
							<input type="text" id="orders" name="orders" ltype="text" validate="{required:true}"/>
						</td>
						<td class="l-t-td-left">部门：</td>
						<td class="l-t-td-right">
							<input type="text" id="unit" name="unit" ltype="text" validate="{required:true}"/>
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">报检单位：</td>
						<td class="l-t-td-right">
							<input type="text" id="com_name" name="com_name" ltype="text" validate="{required:true}"/>
						</td>
						<td class="l-t-td-left">电梯维保单位：</td>
						<td class="l-t-td-right">
							<input type="text" id="maintain_name" name="maintain_name" ltype="text" validate="{required:true}"/>
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">联系人：</td>
						<td class="l-t-td-right">
							<input type="text" id="contact_man" name="contact_man" ltype="text" validate="{required:true}"/>
						</td>
						<td class="l-t-td-left">联系电话：</td>
						<td class="l-t-td-right">
							<input type="text" id="phone_num" name="phone_num" ltype="text" validate="{required:true,number:true}"/>
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">检验时间：</td>
						<td class="l-t-td-right">
							<input type="text" id="inspect_date" name="inspect_date" ltype="text" validate="{required:true}"/>
						</td>
						<td class="l-t-td-left">检验人员：</td>
						<td class="l-t-td-right">
							<input type="text" id="check_op" name="check_op" ltype="text" validate="{required:true}"/>
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">检验检测工作 <br/>服务满意程度：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea type="text" id="inspector_grade" name="inspector_grade" ltype="text" validate="{required:false}"></textarea>
						</td>
						
						
					</tr>
					<tr>
						<td class="l-t-td-left">廉政自律方面<br/>满意程度：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea type="text" id="self_discipline" name="self_discipline" ltype="text" validate="{required:false}"></textarea>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">其他意见及建议：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea type="text" id="other_suggest" name="other_suggest" ltype="text" validate="{required:false}"></textarea>
						</td>
					</tr>
					
				</table>
			
		</form>
	</body>
</html>