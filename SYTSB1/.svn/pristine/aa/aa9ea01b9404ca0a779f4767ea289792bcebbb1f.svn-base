<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="detail">
</head>
<body>
	<form name="baseForm" id="baseForm" method="post" action="tjy2ScientificResearchAction/saveScientific.do" getAction="tjy2ScientificResearchAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<input type="hidden" name="status" />
		<input type="hidden" name="createDate"/> 
		<input type="hidden" name="createMan"/>
		<input type="hidden" name="fileName" id="fileName" />
		
		<input type="hidden" name="taskFileName" id="taskFileName" />
		
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">项目名称：</td>
				<td class="l-t-td-right" colspan="3"><input type="text" ltype='text'
					name="projectName" id="projectName"
					validate="{required:true}"/></td>
				
			</tr>
			<tr>
			<td class="l-t-td-left">项目类别：</td>
				<td class="l-t-td-right">
					<u:combo name="projectType" code="project_type" attribute="required:true"></u:combo></td>
				<td class="l-t-td-left">专业类别：</td>
				<td class="l-t-td-right"><input type="text" ltype='text'
					name="professionalType" id="professionalType" validate="{required:true}"
					 /></td>

			</tr>
			<tr>
				<td class="l-t-td-left">开始日期：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='date'
					name="startDate" id="startDate"    validate="{required:true}"/></td>
				<td class="l-t-td-left">结束日期：</td>
				<td class="l-t-td-right"><input type="text" ltype='date'
					 validate="{required:true}"
					name="endDate" id="endDate" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">项目负责人:</td>
				<td class="l-t-td-right"><input type="hidden" name="projectHeadId" id="projectHeadId"></input>
				<input type="text" ltype='text' validate="{required:true}"
					name="projectHead" id="projectHead" onClick="selectUser()" readonly="readonly" /></td> </td>
				<td class="l-t-td-left">填表日期：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='date'
					name="fillDate" id="fillDate"   validate="{required:true}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">项目资金：</td>
				<td class="l-t-td-right" colspan="3"><input type="text" ltype='text'
					 validate="{required:true}"
					name="projectMoney" id="projectMoney" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">项目成果形式：</td>
				<td class="l-t-td-right" colspan="3"><input type="text" ltype='text'
					name="projectResultsType" id="projectResultsType"
					validate="{required:true}"/></td>
				
			</tr>
			<tr>
			<td class="l-t-td-left">项目成果：</td>
				<td class="l-t-td-right1" colspan="3"><textarea name="projectResults" id="projectResults" rows="4" cols="25" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right1" colspan="3">
				<textarea name="remark"  id ="remark" rows="4" class="l-textarea" validate="{maxlength:2000}"></textarea></td>
			</tr>
			
		</table>
	</form>
</body>
</html>