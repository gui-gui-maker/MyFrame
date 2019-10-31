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
		$("#ids").val(api.data.ids);
	});	
	
	function close(){
		api.close();
	}	
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post" action="disciplinePlanAction/saveResult.do">
			<input id="ids" name="ids"  type="hidden"  />
		
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left" style="width: 200px;">检验检测工作服务满意程度：</td>
						<td class="l-t-td-right">
							<input type="text" id="inspector_grade" name="inspector_grade" ltype="select" validate="{required:true}" ligerui="{value:'',data:[{'id':1,text:1},{'id':2,text:2},{'id':3,text:3}]}"/>
							
						
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">廉政自律方面满意程度：</td>
						<td class="l-t-td-right" >
						
						<input name="self_discipline" id="self_discipline"  ltype="select"   ligerui="{value:'',data:[{'id':1,text:1},{'id':2,text:2},{'id':3,text:3}]}"/> 
							<!-- <textarea type="text" id="self_discipline" name="self_discipline" ltype="text" validate="{required:false}"></textarea> -->
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left" style="width: 200px;">其他意见及建议：</td>
						<td class="l-t-td-right">
							<textarea type="text" name="other_suggest" id="other_suggest" ltype="text" validate="{required:false}"  rows="4
							"></textarea> 
						</td>
						
					</tr>
					
				</table>
			
		</form>
	</body>
</html>