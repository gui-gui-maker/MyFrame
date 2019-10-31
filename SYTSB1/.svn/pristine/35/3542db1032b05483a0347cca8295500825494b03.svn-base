<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<title></title>
<script type="text/javascript">
	$(function() {
		$("#formObj").initForm({
			success : function(resp) {
				if (resp.success) {
					top.$.notice("复制成功",3);
					api.data.window.Qm.refreshGrid();
					api.close();
					api.data.window.modifyApp(resp.id);
				}
			}
		});
	});

</script>
</head>
<body>
	<form action="chart/chart/copyChart.do" method="post" id="formObj" style="margin-top: 1em">
		<input type="hidden" name="id" value="${param.id}" />
		<table class="l-detail-table">
			<tr>
						<td class="l-t-td-left">图表ID：</td>
						<td class="l-t-td-right">
							<input id="chartid" name="chartid"	ltype='text' validate="{required:true,maxlength:100}" />
						</td> 
			</tr>
		</table>
	</form>
</body>
</html>