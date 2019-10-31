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
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="rbac/position/save.do"
		getAction="rbac/position/detail.do?id=${param.id}">
			<table id="tab1" border="0" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
				<input type="hidden" name="id" />
				<input type="hidden" name="org.id" value="${param.orgid}" />
				
				<tr>
					<td class="l-t-td-left">岗位编号：</td>
					<td class="l-t-td-right"><input name="posCode" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td>
					<td class="l-t-td-left">岗位名称：</td>
					<td class="l-t-td-right"><input name="posName" type="text" ltype="text"
							validate="{required:true,maxlength:32}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">岗位类型：</td>
					<td class="l-t-td-right">
						<u:combo name="posType" code="position_type"></u:combo>
						<!-- <input name="posType" type="text" ltype="select" ligerui="{data:[{id:'1',text:'行政岗位'},{id:'2',text:'管理岗位'},{id:'3',text:'其他'}]}"/> -->
					</td>
					<td class="l-t-td-left">排序：</td>
					<td class="l-t-td-right">
						<input name="sort" type="text" ltype="text" validate="{maxlength:32}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea rows="5" cols="10" name="remark" ltype="textarea" validate="{maxlength:512}"></textarea>
					</td>
				</tr>
			</table>
	</form>
</body>
</html>
