<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(resp) {//处理成功
				if (resp.success) {
					top.$.notice("保存成功！");
					api.data.window.submitAction();//执行列表页面函数
					api.close();
				} else {
					top.$.dialog.tips('保存失败！错误信息：<br />' + resp.msg,5,"k/kui/images/icons/dialog/32X32/hits.png",null,0)
				}
			}
		});
	});

	function getParam(el) {
		var value = el.value;
		var parser = new ClsIDCard();
		if (parser.IsValid(value)) {
			$("#formObj").setValues({
				"employee.birthDate" : parser.GetBirthDate(),
				"employee.gender" : parser.GetSex()
			})
		}
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="rbac/user/save.do"
		getAction="rbac/user/detail.do?id=${param.id}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>用户信息</div>
			</legend>
			<table id="tab1" border="0" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
				<input type="hidden" name="id" />
				<input type="hidden" name="org.id" value="${param.orgid}" />
				
				<tr>
					<td class="l-t-td-left">姓名：</td>
					<td class="l-t-td-right"><input name="name" type="text" ltype="text" validate="{required:true,maxlength:50}" /></td>
					<td class="l-t-td-left">登录名：</td>
					<td class="l-t-td-right"><input name="account" type="text" id="userName" ltype="text"
							validate="{required:true,maxlength:64}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">是否启用：</td>
					<td class="l-t-td-right"><u:combo name="status" code="pub_sf" ltype="radioGroup" attribute="initValue:'1'"/></td>
					<td class="l-t-td-left">排序：</td>
					<td class="l-t-td-right"><input name="sort" type="text" ltype="text" validate="{maxlength:32}" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>员工信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<input type="hidden" name="employee.id" />
				<tr>
					<td class="l-t-td-left">身份证号码：</td>
					<td class="l-t-td-right"><input name="employee.idNo" id="idNo"
						type="text" ltype="text" validate="{required:false,idno:true}"
						onchange="getParam(this)" /></td>
					<td class="l-t-td-left">性别：</td>
					<td class="l-t-td-right" id="gender"><u:combo ltype="radioGroup"
							name="employee.gender" code="pub_xb"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">出生年月：</td>
					<td class="l-t-td-right"><input name="employee.birthDate"
						id="birthDate" type="text" ltype='date'
						ligerui="{format:'yyyy-MM-dd'}" /></td>
					<td class="l-t-td-left">民族：</td>
					<td class="l-t-td-right"><u:combo name="employee.nation" code="ba_mz" modify="true" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">婚姻状况：</td>
					<td class="l-t-td-right"><input id="maritalStatus-txt"
						type="text" ltype="select"
						ligerui="{ valueFieldID:'employee.maritalStatus',data: [{id:'未婚',text:'未婚'},{id:'已婚',text:'已婚'},{id:'离异',text:'离异'}] }" /></td>
					<td class="l-t-td-left">藉贯：</td>
					<td class="l-t-td-right"><input name="employee.birthPlace" type="text"
						ltype="text" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">家庭地址：</td>
					<td class="l-t-td-right" colspan="3"><input name="employee.homePlace"
						type="text" ltype="text" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">现住址：</td>
					<td class="l-t-td-right" colspan="3"><input
						name="employee.currentPlace" type="text" ltype="text"></td>
				</tr>
				<tr>
					<td class="l-t-td-left">政治面貌：</td>
					<td class="l-t-td-right"><u:combo name="employee.employeeGov"
							code="ba_zzmm" /></td>
					<td class="l-t-td-left">学历：</td>
					<td class="l-t-td-right"><u:combo name="employee.educationbackground"
							code="pub_xl" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">毕业院校：</td>
					<td class="l-t-td-right"><input name="employee.schoolName" type="text"
						ltype="text" /></td>
					<td class="l-t-td-left">专业：</td>
					<td class="l-t-td-right"><input name="employee.major" type="text"
						ltype="text" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">学位：</td>
					<td class="l-t-td-right"><u:combo name="employee.degree" code="pub_xw" /></td>
					<td class="l-t-td-left">毕业时间：</td>
					<td class="l-t-td-right"><input name="employee.graduateTime"
						type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">职务：</td>
					<td class="l-t-td-right"><input name="employee.duty" type="text"
						ltype="text" /></td>
					<td class="l-t-td-left">办公电话：</td>
					<td class="l-t-td-right"><input name="employee.officeTel" type="text"
						ltype="text" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">移动电话：</td>
					<td class="l-t-td-right"><input name="employee.mobileTel" type="text"
						ltype="text" /></td>
					<td class="l-t-td-left">V网：</td>
					<td class="l-t-td-right"><input name="employee.tel" type="text"
						ltype="text" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">Email：</td>
					<td class="l-t-td-right"><input name="employee.email" type="text"
						ltype="text" /></td>
					<td class="l-t-td-left">有无驾照：</td>
					<td class="l-t-td-right"><input name="employee.isDriver" type="radio"
						ltype="radioGroup"
						ligerui="{data:[{id:'有',text:'有'},{id:'无',text:'无'}],value:'无'}" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
