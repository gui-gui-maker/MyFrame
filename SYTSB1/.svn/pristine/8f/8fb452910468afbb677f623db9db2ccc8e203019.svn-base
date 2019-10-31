<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>业务公式配置</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/formula/js/formula.js"></script>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({
			success : function(responseText) {
				if (responseText.success) {
					top.$.dialog.notice({
						content : '操作成功！'
					});
					api.data.window.submitAction();
					api.close();
				} else {
					$.ligerDialog.error(responseText.msg)
				}
			}
		});
	});

	//选择公式
	function chooseMyFormula() {
		chooseFormula("1",function(data){
			alert(data.name);
		});
	}

	//选取对象回调函数
	function chooseCallback(data) {
		if (data) {
			$("#formulaId").val(data.id);
			$("#formulaName").val(data.name);
		}
	}
</script>
</head>
<body>
	<form id="form1" action="pub/formula/serviceConfig/save.do"
		getAction="pub/formula/serviceConfig/detail.do?id=${param.id}" style="padding-top:1em">
		<input type="hidden" id="id" name="id">
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">业务名称：</td>
					<td class="l-t-td-right"><u:combo name="service"
							code="pub_formula_service" validate="required:true" tree="true" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">规则公式：</td>
					<td class="l-t-td-right"><input name="formula.id"
						type="hidden" id="formulaId" validate="{required:true}" /> <input
						type="text" ltype='text' id="formulaName"
						validate="{required:true}" onclick="chooseMyFormula()" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right"><textarea name="remark" rows="4"
							validate="{maxlength:500}"></textarea></td>
				</tr>
			</table>
	</form>
</body>
</html>
