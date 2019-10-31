<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>组织机构管理-编辑、查看</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/rbac/js/area.js"></script>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({
			success : function(resp) {
				if (resp.success) {
					top.$.dialog.notice({
						content : '保存成功!'
					});
					var status = "${param.status}";
					api.data.window.refresh(resp.data, status);
					api.close();
				} else {
					$.ligerDialog.error('保存失败\<br/>' + resp.msg)
				}
			},
			getSuccess : function(resp) {
				if (resp.data.parent)
					$("#parentId").val(resp.data.parent.id);
			}
		});
	});
	function showData(id) {
		$.getJSON("rbac/org/detail.do", {
			id : id
		}, function(resp) {
			if (resp.success) {
				$("#form1").setValues(resp.data);
			}
		});
	}
	function showlist(){
     	$(this).data('areacode','<sec:authentication property="principal.unit.areaCode" />');
     	showAreaList.call(this);
    }
</script>
</head>
<body>
	<form name="form1" id="form1" method="post"
		action="rbac/org/saveOrg.do"
		getAction="rbac/org/detail.do?id=${param.orgid}">
		<input type="hidden" name="id" /> 
		<input type="hidden" name="levelCode" /> 
		<input type="hidden" name="pid" value="${param.orgid}" id="parentId" />
		<table class="l-detail-table">
			<tr>
				<td class="l-t-td-left" style="width:10em;">编码：</td>
				<td class="l-t-td-right"><input name="orgCode" type="text"
					ltype='text' validate="{required:true,maxlength:32}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">全称：</td>
				<td class="l-t-td-right"><input name="orgName" type="text"
					ltype='text' validate="{required:true,maxlength:200}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">简称：</td>
				<td class="l-t-td-right"><input name="simpleName" type="text" ligerui="width:400"
					ltype='text' validate="{required:false,maxlength:200}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">快速检索代码：</td>
				<td class="l-t-td-right"><input name="searchCode" type="text" ligerui="width:300"
					ltype='text' validate="{required:false,maxlength:20}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">联系电话：</td>
				<td class="l-t-td-right"><input name="tellphone" type="text" ligerui="width:300"
					ltype='text' validate="{required:false,maxlength:64}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">机构性质：</td>
				<td class="l-t-td-right"><u:combo name="property"
						code="sys_org_property" ltype="radioGroup" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">单位类型：</td>
				<td class="l-t-td-right"><u:combo name="type" attribute="width:200"
						code="pub_org_type" /></td>
			</tr>
			<c:if test="${param.division=='yes'}"><tr>
				<td class="l-t-td-left">行政区划：</td>
				<td class="l-t-td-right"><input id="areaCode" name="areaCode"
					type="hidden" ltype='text' validate="{maxlength:50}" /> <input
					name="areaName" type="text" ltype='select'
					validate="{required:true,maxlength:100}"
					ligerui="{textModel:true,valueFieldID:'areaCode',onBeforeOpen:showlist,width:300}" />
				</td>
			</tr></c:if>
			<tr>
				<td class="l-t-td-left">排序：</td>
				<td class="l-t-td-right"><input name="sort" type="text" ligerui="width:150"
					ltype='text' validate="{required:false,maxlength:8}" title="按字母数字顺序进行排序" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备注：</td>
				<td class="l-t-td-right"><textarea name="discrible" cols="15"
						rows="3" class="l-textarea"
						validate="{required:false,maxlength:200}"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>