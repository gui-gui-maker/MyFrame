<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.base.Factory"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status }">
<title>审核结论页面</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = sim.format(new Date());
%>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
	$(function() {
	
	})
	
	function changeExpertType(val,text){
		$("#person_type_name").val(text);
	}
	
	function changeNocome(val,text){
		$("#person_name").val(text);
	}
</script>
</head>
<body>

	<form id="form1" action="expertPreAction/save.do" getAction="expertPreAction/detail.do?id=${param.id }">

		</br>
		<table border="1" cellpadding="0" cellspacing="0" width=""
			class="l-detail-table">
			<input type="hidden" name="id" value="${param.id}" />
			<input type="hidden" name="data_status" value="1" />
			<input type="hidden" name="del_date"  />
			<input type="hidden" name="create_date" value="<%=date %>" />
				<tr>
					<td class="l-t-td-left" style="width: 120px">机选专家组：</td>
					<td class="l-t-td-right">
					<input type="hidden" name="person_type_name" id="person_type_name"/>
					<input validate="required:true"
						type="text" name="person_type" id="person_type"
						ltype="select"
						ligerui="{initValue:'',
						onSelected:changeExpertType,
						readonly:true,data: <u:dict code='PERSON_TYPE' />}" />
					</td>
				</tr>

				<tr id="change_reason">
					<td class="l-t-td-left" style="width: 120px">人员：</td>
					<td class="l-t-td-right">
					<input type="hidden" name="person_name" id="person_name"   />
					<input validate="required:true" 
						type="text" name="person_id" id="person_id" 
						ltype="select"
						ligerui="{initValue:'',
						readonly:true,
						onSelected:changeNocome,
						tree:{checkbox:false,data:<u:dict sql="select a.id, a.pid, a.code, a.text
												  from (
												select t.id,t.id code, t.emp_name text, t.person_type pid
												  from TJY2_RL_EMPLOYEE_BASE t
												union all
												select v.value id,v.value code, v.name text, '0' pid
												  from PUB_CODE_TABLE t, Pub_Code_Table_Values v
												 where t.id = v.code_table_id
												   and t.code = 'PERSON_TYPE')a
												   start with a.pid ='0' 
												   connect by a.pid = prior a.id" />}}" />
					</td>
				</tr>
		
		</table>
	</form>
</body>
</html>
