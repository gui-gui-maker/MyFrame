<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%Date cdate = new Date();Date ndate = new Date();ndate.setDate(ndate.getDate()+1);%>
<input type="hidden" name="id" value="${param.id}" />
<input type="hidden" name="personId" id="person_id" value="<sec:authentication property="principal.id" />" />
<input type="hidden" name="deptId" id="dept_id" value="<sec:authentication property="principal.department.id" />" />
<table class="l-detail-table" id="app_form" cellpadding="3">
	<tr>
		<td class="l-t-td-left">请假人：</td>
		<td class="l-t-td-right">
			<input type="text" name="person" id="person_name" ltype='text' value="<sec:authentication property="principal.name" />"
				validate="{required:true,maxlength:40}" 
				ligerui="{iconItems:[{icon:'user',click:function(){selectUnitOrUser(1, 0, 'person_id', 'person_name');}}]}"/>
		</td>
		<td class="l-t-td-left">部门：</td>
		<td class="l-t-td-right">
			<input type="text" name="dept" id="dept_name" ltype='text' validate="{required:true,maxlength:100}" 
				value="<sec:authentication property="principal.department.orgName" />" 
				ligerui="{iconItems:[{icon:'org',click:function(){selectUnitOrUser(0, 0, 'dept_id', 'dept_name');}}]}"/>
		</td>
	</tr>
	<tr>
		<td class="l-t-td-left">休假日期：</td>
		<td class="l-t-td-right">
			<input type="text" name="startsDate" ltype='date' value="<%=new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(ndate)%>"
				ligerui="{format:'yyyy-MM-dd hh:00:00',showTime:true}" validate="{required:true}" />
		</td>
		<td class="l-t-td-left">休假天数：</td>
		<td class="l-t-td-right">
			<input type="text" name="totalDay" ltype='text'
				ligerui="{initValue:1,suffix:'天',type:'float',decimalplace:1,step:0.5}" validate="{required:true}" />
		</td>
	</tr>
	<tr>
		<td class="l-t-td-left">请假类型：</td>
		<td class="l-t-td-right" colspan="3">
			<input type="radio" name="types" ltype='radioGroup' validate="{required:true}" 
				ligerui="{initValue:'0',data:[{id:'0',text:'事假'},{id:'1',text:'病假'},{id:'2',text:'婚假'},{id:'3',text:'生育假'},{id:'4',text:'丧假'},{id:'5',text:'年假'},{id:'6',text:'倒休'}]}"  />
		</td>
	</tr>
	<tr>
		<td class="l-t-td-left">事由：</td>
		<td class="l-t-td-right" colspan="3">
			<textarea name="reson" ltype='textarea' validate="{maxlength:200}"></textarea>
		</td>
	</tr>
	<tr>
		<td class="l-t-td-left">备注：</td>
		<td class="l-t-td-right" colspan="3">
			<textarea name="remark" ltype='textarea' validate="{maxlength:200}"></textarea>
		</td>
	</tr>
	<tr>
		<td class="l-t-td-left">填表时间：</td>
		<td class="l-t-td-right">
			<input type="text" name="createTime" ltype='date' 
				ligerui="{initValue:'<%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(cdate)%>',readonly:true}" validate="{required:true}" />
		</td>
		<td class="l-t-td-left">填表人：</td>
		<td class="l-t-td-right">
			<input type="text" name="creater" ltype='text'
				ligerui="{initValue:'<sec:authentication property="principal.name" />',readonly:true}" validate="{required:true}" />
		</td>
	</tr>
</table>