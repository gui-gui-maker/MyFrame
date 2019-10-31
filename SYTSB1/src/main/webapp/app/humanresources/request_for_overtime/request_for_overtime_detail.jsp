<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
	String status = request.getParameter("status");
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User) curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee) uu.getEmployee();
	String userId = curUser.getId();
	String uId = SecurityUtil.getSecurityUser().getId();
	String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	String sql = "select t.id, t.parent_id pid, t.id code, t.org_name text from sys_org t where t.property = 'dep' and t.status = 'used' connect by prior t.id = t.parent_id start with t.parent_id = '100000' order by t.orders";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
	var pageStatus = "${param.status}";
	var startDate;
	var endDate;
	$(function() {
		$("#formObj").initForm({
			success : function(response) {//处理成功
				$("body").unmask();
				if (response.success) {
					top.$.dialog.notice({
						content : "保存成功！"
					});
					api.data.window.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败！<br/>' + response.msg);
				}
			},
			getSuccess : function(resp) {
				if (resp.success) {
					$("#formObj").setValues(resp.data);
					if (resp.attachs != null && resp.attachs != undefined) {
						showAttachFile(resp.attachs);
					}
				}
			},
			toolbar : [ {
				text : "保存",
				icon : "save",
				click : function() {
					//表单验证
					if ($("#formObj").validate().form()) {
						$("#formObj").submit();
					}
				}
			}, {
				text : "关闭",
				icon : "cancel",
				click : function() {
					api.close();
				}
			} ],
			toolbarPosition : "bottom"
		});

	});

	/*
	function selectUser() {
		var org_id = $("#department_id").val();
		var org_name = $("#department").val();
		if (org_id == "" || org_name == "") {
			$.ligerDialog.alert("请先选择报告部门！");
			return;
		}
		top.$
				.dialog({
					width : 200,
					height : 420,
					lock : true,
					title : "选择申请人",
					content : 'url:app/humanresources/request_for_overtime/choose_user_list.jsp?org_id='
							+ org_id,
					data : {
						"window" : window
					}
				});
	}

	function callUser(id, name) {
		$('#applicants_id').val(id);
		$('#applicants').val(name);
	}
	 */
	function changeFlag(flag) {
		$("#back").hide();
		if (flag == "补休") {
			$("#overType").show();
		} else {
			$("#back").show();
			$("#overType").hide();
		}
	}

	//加班时间
	function overTimeDate() {
		startDate = $("#overtime_date_start").val();
		if (startDate == "") {
			$.ligerDialog.alert("请先选择开始时间！");
			document.getElementById("overtime_date_end").value = "";
			return;
		}
		endDate = $("#overtime_date_end").val();
		var startDate_text = new Date(startDate.replace('-', '/'));
		var endDate_text = new Date(endDate.replace('-', '/'));

		if ((endDate_text - startDate_text) < 0) {
			$.ligerDialog.alert("结束时间不能小于开始时间！");
			document.getElementById("overtime_date_end").value = "";
			return;
		}
		var Hour = (endDate_text - startDate_text) / 1000 / 3600;
		var Day = Math.floor(Hour / 8);
		if(Day==0&&Hour!=0){
			Day = 1;
		}
		var LastHour = (Hour - Day * 24).toFixed(1);
		//document.getElementById("overtime_day").value = Day;
		//document.getElementById("overtime_hour").value = Hour;
	}
	/*
	//补休时间
	function restDate() {
		startDate = $("#take_time_date").val();
		if (startDate == "") {
			$.ligerDialog.alert("请先选择开始时间~");
			document.getElementById("take_enddate").value = "";
			return;
		}
		endDate = $("#take_enddate").val();
		var startDate_text = new Date(startDate.replace('-', '/'));
		var endDate_text = new Date(endDate.replace('-', '/'));
		if ((endDate_text - startDate_text) < 0) {
			$.ligerDialog.alert("结束时间不能小于开始时间哦~");
			document.getElementById("take_enddate").value = "";
			return;
		}
	}
	 */
	function selectUser() {
		var org_id = $("#department_id").val();
		var org_name = $("#department").val();
		if (org_id == "" || org_name == "") {
			$.ligerDialog.alert("请先选择报告部门！");
			return;
		}
		top.$
				.dialog({
					width : 200,
					height : 420,
					lock : true,
					title : "选择申请人",
					content : 'url:app/humanresources/request_for_overtime/choose_user_list.jsp?org_id='
							+ org_id,
					data : {
						"window" : window
					}
				});
	}

	function callUser(id, name) {
		$('#other_applicants_id').val(id);
		$('#other_applicants').val(name);
	}
	
	function changeDept(id,name) {
		$('#department').val(name);
	}
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="requestForOvertimeAction/savaOvertime.do"
		getAction="requestForOvertimeAction/detail.do?id=${param.id}">

		<h1 id="sg2" align="center"
			style="padding: 5mm 0 2mm 0; font-family: 微软雅黑; font-size: 6mm;">加班申请
		</h1>
		</br>

		<table border="1" cellpadding="3" cellspacing="0" width=""
			class="l-detail-table" id="form-tab">


			<input name="id" id="id" type="hidden" />
			<input id="flow_step" name="flow_step" type="hidden" value="0" />
			<input name="leader_op_id" id="leader_op_id" type="hidden" />
			<input id="data_status" name="data_status" type="hidden" value="1" />
			<input name="handle_op" id="handle_op" type="hidden" />
			<input name="handle_op_id" id="handle_op_id" type="hidden" />
			<input name="subsidy_money" id="subsidy_money" type="hidden" />
			<input name="enter_op" id="enter_op" type="hidden" value="<%=e.getName()%>" />
			<input name="enter_op_id" id="enter_op_id" type="hidden" value="<%=userId%>"/>
			<input name="enter_time" id="enter_time" type="hidden" value="<%=now %>" />
			<input name="minister_audit" id="minister_audit" type="hidden" />
			<input name="minister_audit_id" id="minister_audit_id" type="hidden" />
			<input name="minister_audit_time" id="minister_audit_time"
				type="hidden" />
			<input name="minister_audit_remark" id="minister_audit_remark"
				type="hidden" />
			<input name="personnel_audit" id="personnel_audit" type="hidden" />
			<input name="personnel_audit_id" id="personnel_audit_id"
				type="hidden" />
			<input name="personnel_audit_time" id="personnel_audit_time"
				type="hidden" />
			<input name="personnel_audit_remark" id="personnel_audit_remark"
				type="hidden" />
			<input name="leader_audit" id="leader_audit" type="hidden" />
			<input name="leader_audit_op" id="leader_audit_op" type="hidden" />
			<input name="leader_audit_time" id="leader_audit_time" type="hidden" />
			<input name="leader_audit_remark" id="leader_audit_remark"
				type="hidden" />
			<input name="dean_audit" id="dean_audit" type="hidden" />
			<input name="dean_id" id="dean_id" type="hidden" />
			<input name="dean_audit_time" id="dean_audit_time" type="hidden" />
			<input name="dean_audit_remark" id="dean_audit_remark" type="hidden" />
			<input name="sub_status" id="sub_status" type="hidden" value="0" />
			<input name="start_flow" id="start_flow" type="hidden" value="1" />
			<input name="role_flag" id="role_flag" type="hidden" />
			<input name="applicants_id" id="applicants_id" type="hidden"
				value="<%=userId%>" />
			<input name="applicants" id="applicants" type="hidden"
				value="<%=e.getName()%>" />
			<input name="role_status" id="role_status" type="hidden"/>
			
			<c:if test="${param.status=='detail'||param.status=='modify'}">
			
				<tr>

				<td class="l-t-td-left">编号：</td>
				<td class="l-t-td-right" colspan="3">
				<input type="text"
					name="sn" id="sn"  ltype="text" readonly="readonly"/>
				</td>
			</tr>
			</c:if>
			<tr>

				<td class="l-t-td-left">申请人：</td>
				<td class="l-t-td-right" colspan="3"><input type="hidden"
					name="other_applicants_id" id="other_applicants_id" /> <input
					type="text" name="other_applicants" id="other_applicants"
					ltype="text" validate="{required:true}"
					onclick="selectUnitOrUser(1,1,'other_applicants_id','other_applicants')"
					ligerui="{iconItems:[
							{img:'k/kui/images/icons/16/user.png',click:function(val,e,srcObj){selectUnitOrUser(1,1,'other_applicants_id','other_applicants')}}
							]}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">所在部门：</td>
				<td class="l-t-td-right" colspan="3"><input type="hidden"
					name="department" id="department" /><input name="department_id"
					id="department_id" title="点击此处选择部门"
					ltype='select' validate="{required:true}"
					ligerui="{
				selectBoxHeight:400,
				initValue:'3000',
				readonly:true,
				onSelected:changeDept,
				tree:{checkbox:false,data: <u:dict sql="<%=sql%>"/>}
				}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">加班地点：</td>
				<td class="l-t-td-right"><input id="overtime_place"
					name="overtime_place" type="text" ltype='text'</input></td>

				<td class="l-t-td-left">加班种类：</td>
				<td class="l-t-td-right"><input type="text" id="overtime_type"
					name="overtime_type" ltype="select" validate="{required:false}"
					ligerui="{					value:'',
												readonly:true,
												data: <u:dict code="OVERTIME_TYPE"/>,
												suffixWidth:'140'
											}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">开始时间：</td>
				<td class="l-t-td-right"><input name="overtime_date_start"
					type="text" ltype='date' id="overtime_date_start"
					validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm'}"
					onchange="overTimeDate()" /></td>
				<td class="l-t-td-left">结束时间：</td>
				<td class="l-t-td-right"><input name="overtime_date_end"
					type="text" ltype='date' id="overtime_date_end"
					validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:mm'}"
					onchange="overTimeDate()" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">加班事由：</td>
				<td class="l-t-td-right" colspan="3"><input
					id="overtime_reason" name="overtime_reason" type="text"
					ltype='text'</input></td>
				<!-- 
				<td class="l-t-td-left">加班人意见：</td>
				<td class="l-t-td-right"><input type="text"
					id="overtime_opinion" name="overtime_opinion" ltype="select"
					validate="{required:false}"
					ligerui="{					onChange:changeFlag,value:'补休',
												readonly:true,
												data: <u:dict code="OVERTIME_OPINION"/>,
												suffixWidth:'140'
											}" /></td>
											
			</tr>
			<tr id="overType">
				<td class="l-t-td-left">补休开始时间：</td>
				<td class="l-t-td-right"><input name="take_time_date"
					type="text" ltype='date' id="take_time_date"
					validate="{required:false}"
					ligerui="{format:'yyyy-MM-dd',readonly:true}" /></td>
				<td class="l-t-td-left">补休结束时间：</td>
				<td class="l-t-td-right"><input name="take_enddate" type="text"
					ltype='date' id="take_enddate" validate="{required:false}"
					ligerui="{format:'yyyy-MM-dd',readonly:true}" onchange="restDate()" /></td>
			</tr>
			 -->
				<tr>
					<td class="l-t-td-left">加班天数：</td>
					<td class="l-t-td-right"><input id="overtime_day"
						name="overtime_day" type="text" ltype='text'
						ligerui="{readonly:false}"</input></td>
					<td class="l-t-td-left">小时</td>
					<td class="l-t-td-right"><input name="overtime_hour"
						type="text" ltype='text' id="overtime_hour"
						ligerui="{readonly:false}"></input></td>
				</tr>
				<!-- 
			<c:if test="${param.status == 'detail' }">
				<tr>
					<td class="l-t-td-left">部门负责人意见：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							id="minister_audit_remark" name="minister_audit_remark" rows="4"
							validate="{maxlength:2000}"></textarea></td>
				</tr>
				<tr>
					<td class="l-t-td-left">部门填写时间：</td>
					<td colspan="3" class="l-t-td-right"><input
						name="dept_op_date" type="text" ltype='date' id="dept_op_date"
						validate="{required:false}"
						ligerui="{format:'yyyy-MM-dd hh:mm',readonly:true}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">分管领导意见：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							id="leader_audit_remark" name="leader_audit_remark"
							validate="{maxlength:2000}"></textarea></td>
				</tr>
				<tr>
					<td class="l-t-td-left">领导填写时间：</td>
					<td colspan="3" class="l-t-td-right"><input name="leader_date"
						type="text" ltype='date' id="leader_date"
						validate="{required:false}"
						ligerui="{format:'yyyy-MM-dd hh:mm',readonly:true}" /></td>
				</tr>
			</c:if>
			 -->
		</table>
	</form>
</body>
</html>

