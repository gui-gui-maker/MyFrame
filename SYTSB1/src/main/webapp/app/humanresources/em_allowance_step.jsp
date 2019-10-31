<%@page import="com.khnt.utils.StringUtil"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.base.Factory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>审核结论页面</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<%
	String step = request.getParameter("step");
	String startFlow = request.getParameter("startFlow");
	String sub_status = request.getParameter("sub_status");
	String dept_id = request.getParameter("dept_id");
	String departmentType = request.getParameter("dept_Type");
	System.out.println("===================================" + step);
	StringBuffer sql = new StringBuffer();
	StringBuffer sql2 = new StringBuffer();

/* 	sql.append(
			"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur,sys_org o where ur.user_id = u.id and ur.role_id = r.id ");
	sql2.append("select u.id,u.name from TJY2_RL_ORGID_LEADERID tol,SYS_USER u,sys_role r,sys_user_role ur,sys_org o where ur.user_id = u.id and ur.role_id = r.id ");
	if ("0".equals(step)) {
		sql.append("and r.name = '部门负责人' and u.org_id = o.id and o.id = '" + dept_id + "'");
	} else if ("1".equals(step)) {
		sql.append("and r.name = '人事查看' and u.org_id = o.id");
	} else if ("2".equals(step)) {
		sql2.append("and r.name = '院领导' and u.org_id = o.id and tol.fk_sys_org_id like '%"+dept_id+"%' and tol.fk_rl_emplpyee_id = u.employee_id");
	} else if ("3".equals(step)) {
		sql.append("and r.name = '院长' and u.org_id = o.id ");
	}  */
	if ("0".equals(step)) {
		sql.append(
				"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur,sys_org o where ur.user_id = u.id and ur.role_id = r.id ");
		sql.append("and r.name = '部门负责人' and u.org_id = o.id and o.id = '" + dept_id + "'");
	} else if ("1".equals(step)) {
		sql.append(
				"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur  where ur.user_id = u.id and ur.role_id = r.id ");
		sql.append("and r.name = '人事查看' ");
	} else if ("2".equals(step)) {
		sql.append(
				"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur  where ur.user_id = u.id and ur.role_id = r.id ");
		sql.append("and r.name = '院领导' ");
		//sql.append("and r.name = '院领导' and u.org_id = o.id and tol.fk_sys_org_id like '%"+dept_id+"%' and tol.fk_rl_emplpyee_id = u.employee_id");
	} else if ("3".equals(step)) {
		sql.append(
				"select u.id,u.name from SYS_USER u,sys_role r,sys_user_role ur  where ur.user_id = u.id and ur.role_id = r.id ");
		sql.append("and r.name = '院长' ");
	} 
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = sim.format(new Date());
%>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '保存',
				icon : 'save',
				click : save
			}, {
				text : '关闭',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {
			},
			success : function(res) {
				if (res.success) {
					top.$.dialog.notice({
						content : '提交成功！'
					});
					api.close();
				} else {
					$.ligerDialog.error('提示：提交失败！');
				}
			}
		});

		$('#op_time').val(currentTime());
	})

	function close(url) {
		api.close();
	}
	//保存
	function save(url) {
		if ("${param.step}" != '0' && "${param.startFlow}" != '1') {
			var is_pass = $('#conclusion').ligerGetRadioGroupManager()
					.getValue();
			if (is_pass == "1" && "${param.step}" != "4") {
				if("${param.step}" != '3' ||("${param.step}" == '3'&&"${param.roleStatus}" == '1') ) {
					if ($('#next_op_id').val() == null
							|| $('#next_op_id').val() == undefined
							|| $('#next_op_id').val() == "") {
						top.$.dialog.notice({
							content : '请选择下一步操作人！'
						});
	
						return;
					}
				}
				if ($('#remark').val() == "请在此处填写不通过原因！") {
					$('#remark').val("");
				}
			} else if (is_pass == "0") {
				if ($('#remark').val() == null
						|| $('#remark').val() == undefined
						|| $('#remark').val() == ""
						|| $('#remark').val() == "请在此处填写不通过原因！") {
					top.$.dialog.notice({
						content : '请在此处填写不通过原因！'
					});
					return;
				}

			}
		} else {
			if ($('#next_op_id').val() == null
					|| $('#next_op_id').val() == undefined
					|| $('#next_op_id').val() == "") {
				top.$.dialog.notice({
					content : '请选择下一步操作人！'
				});

				return;
			}
		}
		$("#form1").submit();

	}

	//设值
	function setValue(valuex, name) {
		if (valuex == "") {
			return;
		}
		if (name == 'next_op') {
			$('#next_op').val(valuex)
		}
	}

	//当前日期
	function currentTime() {
		var d = new Date(), str = '';

		str += d.getFullYear() + '-';
		if (d.getMonth() < 10) {
			str += "0";
		}

		str += d.getMonth() + 1 + '-';
		if (d.getDate() < 10) {
			str += "0";
		}
		str += d.getDate() + ' ';
		return str;
	}

	//改变结论事件
	function changeFlag(flag) {
		$("#back").hide();
		if (flag == "1") {
			$("#sub_op").show();
			$("#change_reason").hide();
		} else {
			$("#back").show();
			$("#sub_op").hide();
			$("#change_reason").show();
		}
	}
</script>
</head>
<body>

	<form id="form1" action="employeeallowanceAction/subOvertime.do">

		<h1 id="sg2" align="center"
			style="padding: 5mm 0 2mm 0; font-family: 微软雅黑; font-size: 5mm;">提交审核
		</h1>
		</br>
		<table border="1" cellpadding="0" cellspacing="0" width=""
			class="l-detail-table">
			<input type="hidden" name="ids" value="${param.id}" />
			<input type="hidden" name="step" value="${param.nextStep}" />
			<c:if test="${param.step!='0' && param.startFlow != '1'}">
				<tr>
					<td class="l-t-td-left" style="width: 120px">结论：</td>
					<td class="l-t-td-right"><input type="radio" name="conclusion"
						id="conclusion" ltype="radioGroup" validate="{required:false}"
						ligerui="{onChange:changeFlag,value:'1',data: [ { text:'通过', id:'1' }, { text:'不通过', id:'0' } ] }" /></td>
				</tr>

				<tr id="change_reason">
					<td class="l-t-td-left" style="width: 120px">不通过原因：</td>
					<td class="l-t-td-right"><textarea name="remark" id="remark"
							rows="3" cols="" ext_type="string" ext_maxLength="200"
							ext_name="备注" isNull="Y" class="area_text"
							onfocus="this.innerHTML='';">请在此处填写不通过原因！</textarea></td>
				</tr>
			</c:if>
			<c:if test="${param.step!='4' }">
				<tr>
					<td class="l-t-td-left" style="width: 120px">处理时间：</td>
					<td class="l-t-td-right"><input name="op_date" type="text"
						ltype="date" validate="{required:false}"
						ligerui="{initValue:'<%=date%>',format:'yyyy-MM-dd hh:mm:ss'}"
						id="op_time" /></td>
				</tr>
			<c:if test="${param.step!='3'}">
				<tr id="sub_op">
					<td class="l-t-td-left" style="width: 120px">指定下一步操作人：</td>
					<td class="l-t-td-right"><input type="text" name="next_op_id"
						id="next_op_id" ltype="select" validate="{required:false}"
						onchange="setValue(this.value,'next_op')"
						ligerui="{readonly:true,data: <u:dict sql="<%=sql.toString()%>"/>}" /></td>
					<input name="next_op" id="next_op" type="hidden">
				</tr>
				
			</c:if>
			<c:if test="${param.step=='3'&& param.roleStatus == '1'}"> 
				<tr id="sub_op">
					<td class="l-t-td-left" style="width: 120px">指定下一步操作人：</td>
					<td class="l-t-td-right"><input type="text" name="next_op_id"
						id="next_op_id" ltype="select" validate="{required:false}"
						onchange="setValue(this.value,'next_op')"
						ligerui="{readonly:true,data: <u:dict sql="<%=sql.toString()%>"/>}" /></td>
					<input name="next_op" id="next_op" type="hidden">
				</tr>
			</c:if>
		</c:if>
		</table>

	</form>



</body>
</html>
